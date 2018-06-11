package com.zyl.xuezhibao.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.zyl.xuezhibao.R;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

/**
 * Created by qwe on 2017/5/25.
 */

public class UmShare {
    //分享图片
    public static void shareImage(Activity activity, String url, SHARE_MEDIA platform, UMShareListener umShareListener){
        UMImage image1 = new UMImage(activity, url);//网络图片
        new ShareAction(activity)
                .setPlatform(platform)
//              .withText("hello,微信盆友圈")
                .withMedia(image1)
                .setCallback(umShareListener)
                .share();
    }

    public static void shareImage(Activity activity, Bitmap mBitmap, SHARE_MEDIA platform, UMShareListener umShareListener){
        UMImage image1 = new UMImage(activity, mBitmap);
        new ShareAction(activity)
                .setPlatform(platform)
//              .withText("hello,微信盆友圈")
                .withMedia(image1)
                .setCallback(umShareListener)
                .share();
    }

    //分享链接
    public static void shareLink(Activity activity,String url,String title,String content,SHARE_MEDIA platform){
        Bitmap bmp = BitmapFactory.decodeResource(activity.getResources(), R.drawable.logo);
        UMImage image = new UMImage(activity,bmp);
        UMWeb web = new UMWeb(url);
        web.setTitle(title);//标题
        web.setThumb(image);  //缩略图
        web.setDescription(content);//描述
        new ShareAction(activity)
                .setPlatform(platform)
                .withMedia(web)
                .share();
    }


}
