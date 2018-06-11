package com.zyl.xuezhibao.base;

import android.app.Activity;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import java.util.ArrayList;
import java.util.List;

/**
 * 不能用Application缓存数据！
 * 因为Application会因为进入background后内存不足被系统干掉，
 * 进入后系统会重现创建一个Application类，而导致缓存在Application类里的数据全部初始化而丢失。
 **/


public class MyBaseApplication extends MultiDexApplication {

    private static MyBaseApplication instance;
    public static IWXAPI api;
    public final static String APP_ID = "wxaafdebca81beae3e";

    public static MyBaseApplication getInstance() {
        if (null == instance) {
            instance = new MyBaseApplication();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);//分包初始化
//        initOKHttp();
        ZXingLibrary.initDisplayOpinion(this);//二维码库初始化
        initWX();
    }

    /**
     * OKHttpUtils 网络配置
     */
//    private void initOKHttp() {
//        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
//        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
//                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
//                .cookieJar(new CookieJarImpl(new MemoryCookieStore()))//内存存储cookie
//                .connectTimeout(8000L, TimeUnit.MILLISECONDS)
//                .readTimeout(8000L, TimeUnit.MILLISECONDS)
//                .hostnameVerifier(new HostnameVerifier() {//允许访问https网站,并忽略证书
//                    @Override
//                    public boolean verify(String hostname, SSLSession session) {
//                        return true;
//                    }
//                });
//
//        OkHttpUtils.initClient(okHttpClientBuilder.build());
//    }

    /**
     * 初始化微信
     */
    private void initWX() {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, APP_ID, true);
        // 将该app注册到微信
        api.registerApp(APP_ID);
    }

    private List<Activity> activities ;
    public void addActivity(Activity activity) {
        if (activities == null){
            activities = new ArrayList<>();
        }
        activities.add(activity);
    }
    // 遍历所有Activity并finish
    public void exit() {
        if (activities != null){
            for (Activity activity : activities) {
                activity.finish();
            }
        }
        activities = null;
        System.exit(0);
    }

}