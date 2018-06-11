package com.zyl.xuezhibao.base;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by admin on 2017/3/8.
 */
public class BaseSharePerence {
    private static final String TAG = BaseSharePerence.class.getSimpleName();


    public static final String NAME_TUTU_SHARE = "name_tutu";   //缓存文件名

    /**
     * 用于保存是否第一次打开app
     */
    public static final String KEY_FIRST = "key_first";
    public static final boolean VALUE_DEFAULT_FIRST = true; //默认值

    /**
     * 保存更新提示显示的时间
     */
    public static final String KEY_UPGRADE_TIME= "key_upgrade_time";
    public static final long VALUE_DEFAULT_UPGRADE_TIME = 0; //默认值

    /**
     * 用于保存是否第一次授权登录商家后台
     */
    public static final String KEY_FIRST_BUSSINESS = "key_first_business";
    public static final boolean VALUE_BUSSINESS_FIRST = true; //默认值,是第一次


    /**
     * 用于保存微信的两个key
     */
    public static final String KEY_APP_SECRET = "key_wx";
    public static final String VALUE_APP_SECRET = ""; //默认值


    public static final String KEY_WEIXIN_APP_ID = "key_wx";
    public static final String VALUE_WEIXIN_APP_ID = ""; //默认值

    /**
     * 用于保存是否已经登录
     */
    public static final String KEY_LOGON = "key_logon";
    public static final boolean VALUE_LOGON = false; //默认值


    /**
     * 用于保存是否同意协议
     */
    public static final String KEY_AGREE = "key_agree";
    public static final boolean VALUE_AGREE = true; //默认值


    /**
     * 保存用户个人信息
     */
    public static final String KEY_LOGININFO = "key_logininfo";
    public static final String VALUE_DEFAULT_LOGININFO = ""; //默认值


    /**
     * 账号
     */
    public static final String KEY_ACCOUNT = "key_account";
    public static final String VALUE_ACCOUNT = ""; //默认值


    /**
     * 密码
     */
    public static final String KEY_PASSWORD = "key_password";
    public static final String VALUE_PASSWORD = ""; //默认值


    /**
     * 是否有点击退出登录(清空数据)
     */
    public static final String KEY_EXIT = "key_exit";
    public static final boolean VALUE_EXIT = false; //默认值


    /**
     * 保存友盟的获取的设备信息
     */
    public static final String KEY_DEVICE = "key_device_token";
    public static final String VALUE_DEVICE = ""; //默认值


    /**
     * 保存用户是否同意风险协议
     */
    public static final String KEY_RISK = "key_risk";
    public static final boolean VALUE_RISK = false; //默认值


    /**
     * 保存用户登录成功的时间
     */
    public static final String KEY_LOGON_TIME = "key_logon_time";
    public static final long VALUE_LOGON_TIME = 0;


    /**
     * 保存用户登录成功的时间
     */
    public static final String KEY_CLEAR_LOGIN = "key_clear_login";
    public static final boolean VALUE_CLEAR_LOGIN = false;


    /**
     * 保存apk升级的url路径
     */
    public static final String KEY_APK_URL = "key_apk";
    public static final String VALUE_APK_URL = "";


    public static final String KEY_MONEY = "key_money";
    public static final String VALUE_MONEY = "";

    /**
     * 保存图片的下载路径
     */
    public static final String KEY_IMG_URL = "key_img_url";
    public static final String VALUE_IMG_URL = "";

    /**
     * 保存阿里云图片上传参数
     */
    public static final String KEY_OSS_INFO = "key_ossinfo";
    public static final String VALUE_OSS_INFO = "";


    private final SharedPreferences mSharedPreferences;
    private static BaseSharePerence mInstance;
    private static Object mSyncLock = new Object();

    private Context mContext;

    public static BaseSharePerence getInstance(Context context) {
        synchronized (mSyncLock) {
            if (mInstance == null) {
                mInstance = new BaseSharePerence(context);
            }
        }
        return mInstance;
    }

    private BaseSharePerence(Context context) {
        mContext = context;
        mSharedPreferences = mContext.getSharedPreferences(NAME_TUTU_SHARE, Context.MODE_PRIVATE);
    }


    public void removeInstance() {
        if (mInstance != null) {
            mInstance.removeInstance();
            mInstance = null;
        }
    }


    /**
     * 是否第一次打开
     * @param firstTime
     */
    public void setFirst(boolean firstTime) {
        mSharedPreferences.edit().putBoolean(KEY_FIRST, firstTime).commit();
    }
    public boolean getFirst() {
        return mSharedPreferences.getBoolean(KEY_FIRST, VALUE_DEFAULT_FIRST);
    }

    /**
     * 保存更新提示显示的时间
     * @param time
     */
    public void setUpgradeTime(long time) {
        mSharedPreferences.edit().putLong(KEY_UPGRADE_TIME, time).commit();
    }
    public long getUpgradeTime() {
        return mSharedPreferences.getLong(KEY_UPGRADE_TIME, VALUE_DEFAULT_UPGRADE_TIME);
    }

    /**
     * 是否第一次授权登录商家后台，true为第一次
     *
     * @param firstTime
     */
    public void setIsFirstBusinessManage(boolean firstTime) {
        mSharedPreferences.edit().putBoolean(KEY_FIRST_BUSSINESS, firstTime).commit();
    }

    public boolean getIsFirstBusinessManage() {
        return mSharedPreferences.getBoolean(KEY_FIRST_BUSSINESS, VALUE_BUSSINESS_FIRST);
    }


    /**
     * 保存微信开发的key  APP_SECRET  WEIXIN_APP_ID
     */
    public void setWXAPPSECRET(String wXkey) {
        mSharedPreferences.edit().putString(KEY_APP_SECRET, wXkey).commit();
    }

    public String getWXAPPSECRET() {
        return mSharedPreferences.getString(KEY_APP_SECRET, VALUE_APP_SECRET);
    }

    public void setWEIXINAPPID(String wXkey) {
        mSharedPreferences.edit().putString(KEY_WEIXIN_APP_ID, wXkey).commit();
    }

    public String getWEIXINAPPID() {
        return mSharedPreferences.getString(KEY_WEIXIN_APP_ID, VALUE_WEIXIN_APP_ID);
    }


    /**
     * 是否处于登录
     *
     * @param phase
     */
    public void setLogin(boolean phase) {
        mSharedPreferences.edit().putBoolean(KEY_LOGON, phase).commit();
    }

    public boolean getLogin() {
        return mSharedPreferences.getBoolean(KEY_LOGON, VALUE_LOGON);
    }


    /**
     * 是否同意协议
     *
     * @param agree
     */
    public void setAgree(boolean agree) {
        mSharedPreferences.edit().putBoolean(KEY_AGREE, agree).commit();
    }

    public boolean getAgree() {
        return mSharedPreferences.getBoolean(KEY_AGREE, VALUE_AGREE);
    }


    /**
     * 保存用户个人信息
     */
    public void setMemberInfo(String memberInfo) {
        mSharedPreferences.edit().putString(KEY_LOGININFO, memberInfo).commit();
    }

    public String getLoginInfo() {
        return mSharedPreferences.getString(KEY_LOGININFO, VALUE_DEFAULT_LOGININFO);
    }




    /**
     * 保存用户账号
     */
    public void setAccount(String account) {
        mSharedPreferences.edit().putString(KEY_ACCOUNT, account).commit();
    }

    public String getAccount() {
        return mSharedPreferences.getString(KEY_ACCOUNT, VALUE_ACCOUNT);
    }


    /**
     * 保存用户密码
     */
    public void setPassword(String account) {
        mSharedPreferences.edit().putString(KEY_PASSWORD, account).commit();
    }

    public String getPassword() {
        return mSharedPreferences.getString(KEY_PASSWORD, VALUE_PASSWORD);
    }


    /**
     * 保存用户是否点击退出登录
     */
    public void setExit(boolean b) {
        mSharedPreferences.edit().putBoolean(KEY_EXIT, b).commit();
    }


    public boolean getExit() {
        return mSharedPreferences.getBoolean(KEY_EXIT, VALUE_EXIT);
    }


    /**
     * 保存用户友盟device_token
     */
    public void setDeviceToken(String device_token) {
        mSharedPreferences.edit().putString(KEY_DEVICE, device_token).commit();
    }


    public String getDeviceToken() {
        return mSharedPreferences.getString(KEY_DEVICE, VALUE_DEVICE);
    }


    /**
     * 保存用户是否同意风险协议
     */
    public void setRisk(boolean risk) {
        mSharedPreferences.edit().putBoolean(KEY_RISK, risk).commit();
    }


    public boolean getRisk() {
        return mSharedPreferences.getBoolean(KEY_RISK, VALUE_RISK);
    }


    /**
     * 保存用户登录成功的时间
     */
    public void setLoginTime(long time) {
        mSharedPreferences.edit().putLong(KEY_LOGON_TIME, time).commit();
    }


    public long getLoginTime() {
        return mSharedPreferences.getLong(KEY_LOGON_TIME, VALUE_LOGON_TIME);
    }


    /**
     * 保存是否清空账号
     */
    public void setClearLogin(boolean clearLogin) {
        mSharedPreferences.edit().putBoolean(KEY_CLEAR_LOGIN, clearLogin).commit();
    }


    public boolean getClearLogin() {
        return mSharedPreferences.getBoolean(KEY_CLEAR_LOGIN, VALUE_CLEAR_LOGIN);
    }


    /**
     * 保存apk升级路径
     */
    public void setKeyApkUrl(String url) {
        mSharedPreferences.edit().putString(KEY_APK_URL, url).commit();
    }


    public String getApkUrl() {
        return mSharedPreferences.getString(KEY_APK_URL, VALUE_APK_URL);
    }


    /**
     * 保存用户的资金情况
     */
    public void setMoney(String info) {
        mSharedPreferences.edit().putString(KEY_MONEY, info).commit();
    }


    public String getMoney() {
        return mSharedPreferences.getString(KEY_MONEY, VALUE_MONEY);
    }



    /**
     * 保存图片的域名
     */


    public void setImgUrl(String url)
    {
        mSharedPreferences.edit().putString(KEY_IMG_URL, url).commit();
    }

    public String getImgUrl()
    {
        return mSharedPreferences.getString(KEY_IMG_URL, VALUE_IMG_URL);
    }

    /**
     * 保存阿里云图片上传参数
     */
    public void setOssInfo(String ossInfo){
        mSharedPreferences.edit().putString(KEY_OSS_INFO, ossInfo).commit();
    }


}
