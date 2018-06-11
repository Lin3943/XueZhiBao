package com.zyl.xuezhibao.base;

import android.app.Activity;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.Base64;

import com.alibaba.fastjson.JSONObject;
import com.baidu.mapapi.SDKInitializer;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.common.QueuedWork;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.MemoryCookieStore;
import com.zhy.http.okhttp.https.HttpsUtils;
import com.zyl.xuezhibao.network.Token;
import com.zyl.xuezhibao.utils.Globals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.OkHttpClient;

/**
 * 不能用Application缓存数据！
 * 因为Application会因为进入background后内存不足被系统干掉，
 * 进入后系统会重现创建一个Application类，而导致缓存在Application类里的数据全部初始化而丢失。
 **/


public class BaseApplication extends MultiDexApplication {

    private static BaseApplication instance;
    public static IWXAPI api;
    public final static String APP_ID = "wxaafdebca81beae3e";
    private BaseSharePerence mSharePerence;

    public static BaseApplication getInstance() {
        if (null == instance) {
            instance = new BaseApplication();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);//分包初始化
        initPushSDK();
        initOKHttp();
        ZXingLibrary.initDisplayOpinion(this);//二维码库初始化
        initWX();
        initUMengShare();
//        setupDatabase();
        //百度地图
        SDKInitializer.initialize(this);
//        initLoadSir();
//        LeakCanary.install(this);//内存检查
    }

    private void initPushSDK() {
        mSharePerence = BaseSharePerence.getInstance(this);
//        PushAgent mPushAgent = PushAgent.getInstance(this);
//        mPushAgent.setDebugMode(false);//是否打开日志
//        //注册推送服务，每次调用register方法都会回调该接口
//        mPushAgent.register(new IUmengRegisterCallback() {
//            @Override
//            public void onSuccess(String deviceToken) {
//                //注册成功会返回device token
//                Globals.log("log zh deviceToken:",deviceToken);
//                mSharePerence.setDeviceToken(deviceToken);
//            }
//            @Override
//            public void onFailure(String s, String s1) {
//            }
//        });
    }



    private void initUMengShare() {
        UMShareAPI.get(this); //友盟授权、分享
        Config.DEBUG = true;
        QueuedWork.isUseThreadPool = false;
        String secretWX = "b72534f4319c401bc1652fe52b3e19ba";
//        友盟微信分享
        PlatformConfig.setWeixin(APP_ID, secretWX);
    }
    /**
     * OKHttpUtils 网络配置
     */
    private void initOKHttp() {
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .cookieJar(new CookieJarImpl(new MemoryCookieStore()))//内存存储cookie
                .connectTimeout(8000L, TimeUnit.MILLISECONDS)
                .readTimeout(8000L, TimeUnit.MILLISECONDS)
                .hostnameVerifier(new HostnameVerifier() {//允许访问https网站,并忽略证书
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });

        OkHttpUtils.initClient(okHttpClientBuilder.build());
    }

    /**
     * 初始化微信
     */
    private void initWX() {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, APP_ID, true);
        // 将该app注册到微信
        api.registerApp(APP_ID);
    }

    /**
     * 配置 GreenDao数据库
     */
    private void setupDatabase() {
        //创建数据库SearchHistoryBean.db"
//        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "SearchHistoryBean.db", null);
//        MigrationHelper.DEBUG = true; //如果你想查看日志信息，请将DEBUG设置为true
//        MySQLiteOpenHelper helper = new MySQLiteOpenHelper(this, "SearchHistoryBean.db", null);
//        //获取可写数据库
//        SQLiteDatabase db = helper.getWritableDatabase();
//        //获取数据库对象
//        DaoMaster daoMaster = new DaoMaster(db);
//        //获取Dao对象管理者
//        daoSession = daoMaster.newSession();
    }
//    public static DaoSession getDaoInstant() {
//        return daoSession;
//    }

    /**
     * 获取阿里云图片的域名
     */
    private void getImgURL() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("type", 0);
        HashMap<String, String> requestMap = Token.getRequestMap(data);
        OkHttpUtils.post().url(Constants.Url.imageUrl_URl).params(requestMap).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
            }
            @Override
            public void onResponse(String response, int id) {
                   try {
                       JSONObject json = JSONObject.parseObject(response);
                       String resultData = new String(Base64.decode(json.getString("data"), Base64.DEFAULT));//响应体需要解码,本地json数据
                       JSONObject object = JSONObject.parseObject(resultData);
                        if (object.getString("status").equals("success")) {
                            Globals.log("log zh 获取阿里云图片的域名",object.getString("imageUrl"));
                            BaseSharePerence.getInstance(getApplicationContext()).setImgUrl(object.getString("imageUrl"));
                        }
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
            }
        });
    }

    /**imageParam_url
     * 获取阿里云域名
     */
    private void getOssURL(final Context context) {
        HashMap<String, Object> data = new HashMap<>();
        HashMap<String, String> requestMap = Token.getRequestMap(data);
        OkHttpUtils.post().url(Constants.Url.imageParam_URl).params(requestMap).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
            }
            @Override
            public void onResponse(String response, int id) {
                try {
                    JSONObject json = JSONObject.parseObject(response);
                    String resultData = new String(Base64.decode(json.getString("data"), Base64.DEFAULT));//响应体需要解码,本地json数据
                    Globals.log("log zh getOssURL"+resultData);
                    BaseSharePerence.getInstance(context).setOssInfo(resultData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
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