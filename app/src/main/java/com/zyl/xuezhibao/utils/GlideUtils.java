package com.zyl.xuezhibao.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.zyl.xuezhibao.R;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * 
 * Created by xy on 2016/9/30.
 * details:
 * Glide 工具类
 * Google推荐的图片加载库，专注于流畅的滚动
 * <p/>
 * Glide 比Picasso  加载快 但需要更大的内存来缓存
 * <p/>
 * Glide 不光接受Context，还接受Activity 和 Fragment ,图片加载会和Activity/Fragment的生命周期保持一致 在onPause（）暂停加载，onResume（）恢复加载
 * <p/>
 * 支持GIF格式图片加载
 * 在Activity里context上下文要使用getApplicationContext()
 *
 */


public class GlideUtils {

	private static GlideUtils instance;

	public static GlideUtils getInstance() {
		if (instance == null) {
			synchronized (GlideUtils.class) {
				if (instance == null) {
					instance = new GlideUtils();
				}
			}
		}
		return instance;
	}
	
	public static void displayImage(Context context,String url,ImageView myImageView){
		 Glide
		    .with(context)
		    .load(url)
		    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
			.error(R.drawable.prestrain_bg)
			.placeholder(R.drawable.prestrain_bg)
			.thumbnail(0.1f)
		    .crossFade()
		    .into(myImageView);
	}

	/**
	 * 圆形
	 * @param context
	 * @param url
	 * @param myImageView
	 */
	public static void displayCircleImage(final Context context,String url,final ImageView myImageView){
		Glide
			.with(context)
			.load(url)
			.asBitmap()
			.error(R.drawable.mine_img)
			.thumbnail(0.1f)
			.placeholder(R.drawable.mine_img)
//			.signature(new StringSignature(UUID.randomUUID().toString()))
			.into(new BitmapImageViewTarget(myImageView) {
				@Override
				protected void setResource(Bitmap resource) {
					RoundedBitmapDrawable circularBitmapDrawable =
	                    RoundedBitmapDrawableFactory.create(context.getResources(), resource);
					circularBitmapDrawable.setCircular(true);
					myImageView.setImageDrawable(circularBitmapDrawable);
				}
			});
	}

	/**
	 * 圆角
	 * @param context
	 * @param url
	 * @param myImageView
	 * @param dp
	 */
	public static void displayRoundImage(final Context context,String url,final ImageView myImageView,final int dp){
		Glide
		.with(context)
		.load(url)
		.asBitmap()
		.placeholder(R.drawable.prestrain_bg)
		.error(R.drawable.prestrain_bg)
		.thumbnail(0.1f)
//		.signature(new StringSignature(UUID.randomUUID().toString()))
		.into(new BitmapImageViewTarget(myImageView) {
			@Override
			protected void setResource(Bitmap resource) {
				RoundedBitmapDrawable circularBitmapDrawable =
						RoundedBitmapDrawableFactory.create(context.getResources(), resource);
				circularBitmapDrawable.setCornerRadius(dp);
				myImageView.setImageDrawable(circularBitmapDrawable);
			}
		});
	}

	/**
	 * 圆形加载带边框
	 *
	 * @param mContext
	 * @param path
	 * @param imageview
	 */
	public static void LoadCircleImage(Context mContext, String path, ImageView imageview) {
		Glide.with(mContext)
				.load(path)
				.centerCrop()
				.error(R.drawable.prestrain_bg)
				.placeholder(R.drawable.prestrain_bg)
				.thumbnail(0.1f)
				.bitmapTransform(new CropCircleTransformation(mContext))
//				.signature(new StringSignature(UUID.randomUUID().toString()))
				.transform(new GlideCircleTransform(mContext,4,mContext.getResources().getColor(R.color.white)))
				.into(imageview);

	}

	static class GlideCircleTransform extends BitmapTransformation {
		private Paint mBorderPaint;
		private float mBorderWidth;
		public GlideCircleTransform(Context context) {
			super(context);
		}
		public GlideCircleTransform(Context context, int borderWidth, int borderColor) {
			super(context);
			mBorderWidth = Resources.getSystem().getDisplayMetrics().density * borderWidth;
			mBorderPaint = new Paint();
			mBorderPaint.setDither(true);
			mBorderPaint.setAntiAlias(true);
			mBorderPaint.setColor(borderColor);
			mBorderPaint.setStyle(Paint.Style.STROKE);
			mBorderPaint.setStrokeWidth(mBorderWidth);
		}


		protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
			return circleCrop(pool, toTransform);
		}

		private Bitmap circleCrop(BitmapPool pool, Bitmap source) {
			if (source == null) return null;

			int size = (int) (Math.min(source.getWidth(), source.getHeight()) - (mBorderWidth / 2));
			int x = (source.getWidth() - size) / 2;
			int y = (source.getHeight() - size) / 2;
			// TODO this could be acquired from the pool too
			Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);
			Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
			if (result == null) {
				result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
			}
			Canvas canvas = new Canvas(result);
			Paint paint = new Paint();
			paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
			paint.setAntiAlias(true);
			float r = size / 2f;
			canvas.drawCircle(r, r, r, paint);
			if (mBorderPaint != null) {
				float borderRadius = r - mBorderWidth / 2;
				canvas.drawCircle(r, r, borderRadius, mBorderPaint);
			}
			return result;
		}

		@Override
		public String getId() {
			return getClass().getName();
		}
	}

		/**
		 * 加载bitmap，如果是GIF则显示第一帧
		 */
		public static String LOAD_BITMAP = "GLIDEUTILS_GLIDE_LOAD_BITMAP";
		/**
		 * 加载gif动画
		 */
		public static String LOAD_GIF = "GLIDEUTILS_GLIDE_LOAD_GIF";


		public void loadBitmap(Context context,String path,ImageView imageView){
			LoadContextBitmap(context,path,imageView,0,0,null);
		}

		/**
		 * 使用Application上下文，Glide请求将不受Activity/Fragment生命周期控制
		 * 使用activity 会受到Activity生命周期控制
		 * 使用FragmentActivity 会受到FragmentActivity生命周期控制
		 *
		 * @param
		 * @param path
		 * @param imageView
		 * @param placeid     占位
		 * @param errorid     错误
		 * @param bitmapOrgif 加载普通图片 或者GIF图片 ，GIF图片设置bitmap显示第一帧
		 */
		public void LoadContextBitmap(Context context, String path, ImageView imageView, int placeid, int errorid, String bitmapOrgif) {
			if (bitmapOrgif == null || bitmapOrgif.equals(LOAD_BITMAP)) {
				Glide.with(context).load(path).centerCrop().placeholder(placeid).error(errorid).crossFade().into(imageView);
			} else if (bitmapOrgif.equals(LOAD_GIF)) {
				Glide.with(context).load(path).asGif().crossFade().into(imageView);
			}
		}

		/**
		 * Glide请求图片，会受到Fragment 生命周期控制。
		 *
		 * @param fragment
		 * @param path
		 * @param imageView
		 * @param placeid
		 * @param errorid
		 * @param bitmapOrgif 加载普通图片 或者GIF图片 ，GIF图片设置bitmap显示第一帧
		 */
		public void LoadFragmentBitmap(android.app.Fragment fragment, String path, ImageView imageView, int placeid, int errorid, String bitmapOrgif) {
			if (bitmapOrgif == null || bitmapOrgif.equals(LOAD_BITMAP)) {
				Glide.with(fragment).load(path).placeholder(placeid).error(errorid).crossFade().into(imageView);
			} else if (bitmapOrgif.equals(LOAD_GIF)) {
				Glide.with(fragment).load(path).asGif().crossFade().into(imageView);
			}
		}

		/**
		 * Glide请求图片，会受到support.v4.app.Fragment生命周期控制。
		 *
		 * @param fragment
		 * @param path
		 * @param imageView
		 * @param placeid
		 * @param errorid
		 * @param bitmapOrgif 加载普通图片 或者GIF图片 ，GIF图片设置bitmap显示第一帧
		 */
		public void LoadSupportv4FragmentBitmap(android.support.v4.app.Fragment fragment, String path, ImageView imageView, int placeid, int errorid, String bitmapOrgif) {
			if (bitmapOrgif == null || bitmapOrgif.equals(LOAD_BITMAP)) {
				Glide.with(fragment).load(path).placeholder(placeid).error(errorid).crossFade().into(imageView);
			} else if (bitmapOrgif.equals(LOAD_GIF)) {
				Glide.with(fragment).load(path).asGif().crossFade().into(imageView);
			}
		}
		//---------------------圆形图片-------------------

		/**
		 * 加载设置圆形图片
		 * 使用Application上下文，Glide请求将不受Activity/Fragment生命周期控制
		 * <BR/>使用activity 会受到Activity生命周期控制
		 * <BR/>使用FragmentActivity 会受到FragmentActivity生命周期控制
		 *
		 * @param context
		 * @param path
		 * @param imageView
		 */
		@SuppressWarnings("unchecked")
		public void LoadContextCircleBitmap(Context context, String path, ImageView imageView) {
			Glide.with(context).load(path).bitmapTransform(new GlideCircleTransform(context)).into(imageView);
		}

		/**
		 * Glide请求图片设置圆形，会受到android.app.Fragment生命周期控制
		 *
		 * @param fragment
		 * @param path
		 * @param imageView
		 */
		@SuppressWarnings("unchecked")
		public void LoadfragmentCircleBitmap(android.app.Fragment fragment, String path, ImageView imageView) {
			Glide.with(fragment).load(path).bitmapTransform(new GlideCircleTransform(fragment.getActivity())).into(imageView);
		}

		/**
		 * Glide请求图片设置圆形，会受到android.support.v4.app.Fragment生命周期控制
		 *
		 * @param fragment
		 * @param path
		 * @param imageView
		 */
		@SuppressWarnings("unchecked")
		public void LoadSupportv4FragmentCircleBitmap(android.support.v4.app.Fragment fragment, String path, ImageView imageView) {
			Glide.with(fragment).load(path).bitmapTransform(new GlideCircleTransform(fragment.getActivity())).into(imageView);
		}
		//-----------------------圆角图片----------------------

		/**
		 * 加载设置圆角图片
		 * 使用Application上下文，Glide请求将不受Activity/Fragment生命周期控制
		 * <BR/>使用activity 会受到Activity生命周期控制
		 * <BR/>使用FragmentActivity 会受到FragmentActivity生命周期控制
		 *
		 * @param context
		 * @param path
		 * @param imageView
		 * @param roundradius 圆角大小（>0）
		 */
		@SuppressWarnings("unchecked")
		public void LoadContextRoundBitmap(Context context, String path, ImageView imageView, int roundradius) {
			if (roundradius < 0) {
				Glide.with(context).load(path).centerCrop().bitmapTransform(new GlideRoundTransform(context)).into(imageView);
			} else {
				Glide.with(context).load(path).bitmapTransform(new GlideRoundTransform(context, roundradius)).into(imageView);
			}
		}

		/**
		 * Glide请求图片设置圆角，会受到android.app.Fragment生命周期控制
		 *
		 * @param fragment
		 * @param path
		 * @param imageView
		 * @param roundradius
		 */
		@SuppressWarnings("unchecked")
		public void LoadfragmentRoundBitmap(android.app.Fragment fragment, String path, ImageView imageView, int roundradius) {
			if (roundradius < 0) {
				Glide.with(fragment).load(path).bitmapTransform(new GlideRoundTransform(fragment.getActivity())).into(imageView);
			} else {
				Glide.with(fragment).load(path).bitmapTransform(new GlideRoundTransform(fragment.getActivity(), roundradius)).into(imageView);
			}
		}

		/**
		 * Glide请求图片设置圆角，会受到android.support.v4.app.Fragment生命周期控制
		 *
		 * @param fragment
		 * @param path
		 * @param imageView
		 * @param roundradius
		 */
		@SuppressWarnings("unchecked")
		public void LoadSupportv4FragmentRoundBitmap(android.support.v4.app.Fragment fragment, String path, ImageView imageView, int roundradius) {
			if (roundradius < 0) {
				Glide.with(fragment).load(path).bitmapTransform(new GlideRoundTransform(fragment.getActivity())).into(imageView);
			} else {
				Glide.with(fragment).load(path).bitmapTransform(new GlideRoundTransform(fragment.getActivity(), roundradius)).into(imageView);
			}
		}
		//-------------------------------------------------

		/**
		 * Glide 加载模糊图片
		 * 使用Application上下文，Glide请求将不受Activity/Fragment生命周期控制
		 * <BR/>使用activity 会受到Activity生命周期控制
		 * <BR/>使用FragmentActivity 会受到FragmentActivity生命周期控制
		 *
		 * @param context
		 * @param path
		 * @param imageView
		 */
		@SuppressWarnings("unchecked")
		public void LoadContextBlurBitmap(Context context, String path, ImageView imageView) {
			Glide.with(context).load(path).centerCrop().bitmapTransform(new BlurTransformation(context)).into(imageView);
		}

		/**
		 * Glide 加载模糊图片 会受到Fragment生命周期控制
		 *
		 * @param
		 * @param path
		 * @param imageView
		 */
		@SuppressWarnings("unchecked")
		public void LoadFragmentBlurBitmap(android.app.Fragment fragment, String path, ImageView imageView) {
			Glide.with(fragment).load(path).bitmapTransform(new BlurTransformation(fragment.getActivity())).into(imageView);
		}

		/**
		 * Glide 加载模糊图片 会受到support.v4.app.Fragment生命周期控制
		 *
		 * @param
		 * @param path
		 * @param imageView
		 */
		@SuppressWarnings("unchecked")
		public void LoadSupportv4FragmentBlurBitmap(android.support.v4.app.Fragment fragment, String path, ImageView imageView) {
			Glide.with(fragment).load(path).bitmapTransform(new BlurTransformation(fragment.getActivity())).into(imageView);
		}
		//---------------------------------------------------------

		/**
		 * 旋转图片
		 * 使用Application上下文，Glide请求将不受Activity/Fragment生命周期控制
		 * <BR/>使用activity 会受到Activity生命周期控制
		 * <BR/>使用FragmentActivity 会受到FragmentActivity生命周期控制
		 *
		 * @param context
		 * @param path
		 * @param imageView
		 * @param rotateRotationAngle 旋转角度
		 */
		@SuppressWarnings("unchecked")
		public void LoadContextRotateBitmap(Context context, String path, ImageView imageView, Float rotateRotationAngle) {
			Glide.with(context).load(path).bitmapTransform(new RotateTransformation(context, rotateRotationAngle)).into(imageView);
		}

		/**
		 * Glide 加载旋转图片 会受到Fragment生命周期控制
		 *
		 * @param fragment
		 * @param path
		 * @param imageView
		 * @param rotateRotationAngle
		 */
		@SuppressWarnings("unchecked")
		public void LoadFragmentRotateBitmap(android.app.Fragment fragment, String path, ImageView imageView, Float rotateRotationAngle) {
			Glide.with(fragment).load(path).bitmapTransform(new RotateTransformation(fragment.getActivity(), rotateRotationAngle)).into(imageView);
		}

		/**
		 * Glide 加载旋转图片 会受到support.v4.app.Fragment生命周期控制
		 *
		 * @param fragment
		 * @param path
		 * @param imageView
		 * @param rotateRotationAngle
		 */
		@SuppressWarnings("unchecked")
		public void LoadSupportv4FragmentRotateBitmap(android.support.v4.app.Fragment fragment, String path, ImageView imageView, Float rotateRotationAngle) {
			Glide.with(fragment).load(path).bitmapTransform(new RotateTransformation(fragment.getActivity(), rotateRotationAngle)).into(imageView);
		}
		//----------------------旋转---------------------------

		/**
		 * 旋转
		 */
		public class RotateTransformation extends BitmapTransformation {

			private float rotateRotationAngle = 0f;

			public RotateTransformation(Context context, float rotateRotationAngle) {
				super(context);

				this.rotateRotationAngle = rotateRotationAngle;
			}

			@Override
			protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
				Matrix matrix = new Matrix();

				matrix.postRotate(rotateRotationAngle);

				return Bitmap.createBitmap(toTransform, 0, 0, toTransform.getWidth(), toTransform.getHeight(), matrix, true);
			}

			@Override
			public String getId() {
				return "rotate" + rotateRotationAngle;
			}
		}

		//-----------------------------图片模糊----------------------------------

		/**
		 * 图片模糊
		 */
		public class BlurTransformation extends BitmapTransformation {

			private RenderScript rs;

			public BlurTransformation(Context context) {
				super(context);

				rs = RenderScript.create(context);
			}

			@Override
			protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
				Bitmap blurredBitmap = toTransform.copy(Bitmap.Config.ARGB_8888, true);

				// Allocate memory for Renderscript to work with
				Allocation input = Allocation.createFromBitmap(
						rs,
						blurredBitmap,
						Allocation.MipmapControl.MIPMAP_FULL,
						Allocation.USAGE_SHARED
				);
				Allocation output = Allocation.createTyped(rs, input.getType());

				// Load up an instance of the specific script that we want to use.
				ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
				script.setInput(input);

				// Set the blur radius
				script.setRadius(10);

				// Start the ScriptIntrinisicBlur
				script.forEach(output);

				// Copy the output to the blurred bitmap
				output.copyTo(blurredBitmap);

				toTransform.recycle();

				return blurredBitmap;
			}

			@Override
			public String getId() {
				return "blur";
			}
		}
		//-------------------图片转换圆角图片------------------------------

		/**
		 * 图片转换圆角图片
		 */
		public class GlideRoundTransform extends BitmapTransformation {

			private float radius = 0f;

			public GlideRoundTransform(Context context) {
				this(context, 4);
			}

			/**
			 * 自定义圆角大小
			 *
			 * @param context
			 * @param dp
			 */
			public GlideRoundTransform(Context context, int dp) {
				super(context);
				this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
			}

			@Override
			protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
				return roundCrop(pool, toTransform);
			}

			private Bitmap roundCrop(BitmapPool pool, Bitmap source) {
				if (source == null) return null;

				Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
				if (result == null) {
					result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
				}

				Canvas canvas = new Canvas(result);
				Paint paint = new Paint();
				paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
				paint.setAntiAlias(true);
				RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
				canvas.drawRoundRect(rectF, radius, radius, paint);
				return result;
			}

			@Override
			public String getId() {
				return getClass().getName() + Math.round(radius);
			}
		}
}
