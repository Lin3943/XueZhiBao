package com.zyl.xuezhibao.view.index;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;
import com.zyl.xuezhibao.R;
import com.zyl.xuezhibao.base.MyBaseApplication;
import com.zyl.xuezhibao.widget.UpDownTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class IndexFragment extends Fragment implements View.OnClickListener{

    private List<String> messageList;
    private List<String> titles;
    private List<Integer> imgList;
    private View mView;
    private TextView mTvName;
    private ImageButton mBack;
    private Banner mBanner;
    private UpDownTextView mUpDownTextView;
    private long mExitTime = 0;
    private Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public IndexFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null){
            mView = inflater.inflate(R.layout.fragment_index, container, false);
            initUI();
            initData();
            initLinstener();
        }
        return mView;
    }

    private void initViewPager() {
        imgList = new ArrayList<>();
//        titles = new ArrayList<>();
//        //设置banner样式
//        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
//        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
//        //资源文件
        int img1 = R.drawable.bg_colorful;
        int img2 = R.drawable.mine_img_bg;
        int img3 = R.mipmap.icon_natsu;
        imgList.add(img1);
        imgList.add(img2);
        imgList.add(img3);
//        Integer[] images={R.mipmap.icon_inuyasha,R.mipmap.icon_luffy,R.mipmap.icon_natsu};
//        imgList.addAll(Arrays.asList(images));
        //设置图片集合
        mBanner.setImages(imgList);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.DepthPage);
//        //设置标题集合（当banner样式有显示title时）
//        Integer[] integers ={1,2,3};
//        imgList.addAll(Arrays.asList(integers));
//        mBanner.setBannerTitles(titles);
//        //设置自动轮播，默认为true
//        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(2500);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }

    @Override
    public void onStart() {
        super.onStart();
        showIntroduction();
        //开始轮播
        initViewPager();
//        mBanner.startAutoPlay();
    }

    protected void initData() {

    }

    private void initUI() {
        mTvName = (TextView) mView.findViewById(R.id.tv_index_abstract);
        mTvName.setMovementMethod(ScrollingMovementMethod.getInstance());
        mBack = (ImageButton) mView.findViewById(R.id.img_btn_back);
        mBanner = (Banner) mView.findViewById(R.id.vp_viewpager);
        mUpDownTextView = (UpDownTextView) mView.findViewById(R.id.tv_scroll_sign);
        mUpDownTextView.setFocusable(true);//解决不显示在顶部
        mUpDownTextView.setFocusableInTouchMode(true);
        mUpDownTextView.requestFocus();
        messageList = new ArrayList<String>();
    }

    private void initLinstener() {
        mBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_btn_back:
                if ((System.currentTimeMillis() - mExitTime) > 2000) {
                    Toast.makeText(mContext, "再次点击退出", Toast.LENGTH_SHORT).show();
                    mExitTime = System.currentTimeMillis();
                }else {
                    MyBaseApplication.getInstance().exit();
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    private void showIntroduction() {
        String brief_introduction =
                "       公司始终坚持以“科技创新服务、跨界整合传播、诚信高效共赢”为服务理念，" +
                "致力于为广大人民提供一个优质公平公开的一站式终身学习平台，现已得到教育部、" +
                "农业部、文化部、人社部的大力支持及民间品牌机构和个体家教资源等参与的终身学习平台建设运营单位，" +
                "通过与各级政府、各大机构、高校和企业界广泛深入合作，形成了以打造多网融合新媒体播控平台为主营，" +
                "现代化农村教育系统项目开发、高清触控大屏幕显示系统开发推广、机器人项目合作、第三方支付系统、" +
                "新媒体学习终端教育平台运作、投资运营、影视文化活动策划，专业影视前后期制作、企业形象策划、" +
                "独家广告代理、平面设计制作、网络技术开发与推广和服务为一体的多元化高新技术创新企业。确立了" +
                "多网融合新媒体终端播控平台的战略性规划与投资、网络技术开发与推广两大核心业务，在教育项目，" +
                "高清触控大屏显示系统项目、投资运营、网络科技等项目中积累了丰富经验和良好声誉，具有跨界资源整合能力。\n" +
                "       目前公司正朝着实现全民终身学习的大道上前进，努力让所有人能随时、随地的学习到优质的教育课程，让学习无处不在。";
        mTvName.setText(brief_introduction);
        String sign_0 = "欢迎来到学智宝，学智宝为您开启学习之旅";
        String sign_1 = "网络连接世界,信息沟通心灵";
        String sign_2 = "点击兴趣,激活智慧,观看世界,连通未来";
        messageList.add(sign_0);
        messageList.add(sign_1);
        messageList.add(sign_2);
        mUpDownTextView.setTextList(messageList);
        mUpDownTextView.setAnimMode(0);
        mUpDownTextView.setTextSize(16);
        mUpDownTextView.setGravity(Gravity.CENTER);
        mUpDownTextView.setTextColor(getResources().getColor(R.color.magenta));
        mUpDownTextView.setAnimTime(750);
        mUpDownTextView.startAutoScroll();

    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        mBanner.stopAutoPlay();
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            /**
             注意：
             1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
             2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
             传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
             切记不要胡乱强转！
             */

            //Glide 加载图片简单用法
            Glide.with(context).load(path).into(imageView);

            //Picasso 加载图片简单用法
//            Picasso.with(context).load(path).into(imageView);

            //用fresco加载图片简单用法，记得要写下面的createImageView方法
//            Uri uri = Uri.parse((String) path);
//            imageView.setImageURI(uri);
        }

        //提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建
//        @Override
//        public ImageView createImageView(Context context) {
//            //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
//            SimpleDraweeView simpleDraweeView=new SimpleDraweeView(context);
//            return simpleDraweeView;
//        }
    }
}
