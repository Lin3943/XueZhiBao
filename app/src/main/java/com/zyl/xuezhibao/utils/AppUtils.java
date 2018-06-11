package com.zyl.xuezhibao.utils;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.util.UUID;

/**
 * 跟App相关的辅助类
 * 
 */
public class AppUtils
{
	
	private AppUtils()
	{
		throw new UnsupportedOperationException("cannot be instantiated");

	}

	/**
	 * 获取应用程序名称
	 */
	public static String getAppName(Context context)
	{
		try
		{
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(
					context.getPackageName(), 0);
			int labelRes = packageInfo.applicationInfo.labelRes;
			return context.getResources().getString(labelRes);
		} catch (NameNotFoundException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * [获取应用程序版本名称信息]
	 * @param context
	 * @return 当前应用的版本名称
	 */
	public static String getVersionName(Context context)
	{
		try
		{
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(
					context.getPackageName(), 0);
			return packageInfo.versionName;

		} catch (NameNotFoundException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	 /**
     * 获取版本号
     * @return
     */
    public static int getVersionCode(Context context){
        PackageManager manager = context.getPackageManager();//获取包管理器
        try {
            //通过当前的包名获取包的信息
            PackageInfo info = manager.getPackageInfo(context.getPackageName(),0);//获取包对象信息
            return  info.versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

	/**
	 * 获得设备唯一标识
	 * 需要权限READ_PHONE_STATE
	 */
	public static String getUniqueId(Context context){
		final String tmDevice, tmSerial, androidId;
		try {
			final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			tmDevice = "" + tm.getDeviceId();
			tmSerial = "" + tm.getSimSerialNumber();
			androidId = "" + android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
			UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
			String uniqueId = deviceUuid.toString().replaceAll("-", "");
			return MD5Utils.stringToMD5(uniqueId);
		}catch (Exception error){}
		return null;
	}
    
}
