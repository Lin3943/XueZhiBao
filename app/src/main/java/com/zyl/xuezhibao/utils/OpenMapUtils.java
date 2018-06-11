package com.zyl.xuezhibao.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.io.File;

public class OpenMapUtils {
	//判定是否装有地图APP
	private static boolean isInstallPackage(String packageName) {
		return new File("/data/data/" + packageName).exists();
		}
	//GCJ-02 == BD-09 地图坐标系互转
	private static double[] bdToGaoDe(double bd_lat, double bd_lon) {
	    double[] gd_lat_lon = new double[2];
	    double PI = 3.14159265358979324 * 3000.0 / 180.0;
	    double x = bd_lon - 0.0065, y = bd_lat - 0.006;
	    double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * PI);
	    double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * PI);
	    gd_lat_lon[0] = z * Math.cos(theta);
	    gd_lat_lon[1] = z * Math.sin(theta);
	    return gd_lat_lon;
	 }

	public static double[] gaoDeToBaidu(double gd_lon, double gd_lat) {
	    double[] bd_lat_lon = new double[2];
	    double PI = 3.14159265358979324 * 3000.0 / 180.0;
	    double x = gd_lon, y = gd_lat;
	    double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * PI);
	    double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * PI);
	    bd_lat_lon[0] = z * Math.cos(theta) + 0.0065;
	    bd_lat_lon[1] = z * Math.sin(theta) + 0.006;
	    return bd_lat_lon;
	}

	//百度方式
	public static void openBaiduMap(Context context,String lon, String lat,String describle) {
		try {
		StringBuilder loc = new StringBuilder();
		loc.append("intent://map/direction?origin=latlng:");
		loc.append(lat);
		loc.append(",");
		loc.append(lon);
		loc.append("|name:");
		loc.append("我的位置");
		loc.append("&destination=latlng:");
		loc.append(lat);
		loc.append(",");
		loc.append(lon);
		loc.append("|name:");
		loc.append(describle);
		loc.append("&mode=walking");
		loc.append("&referer=Autohome|GasStation#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
		Intent intent = Intent.getIntent(loc.toString());
		if (isInstallPackage("com.baidu.BaiduMap")) {
			context.startActivity(intent); //启动调用
		Log.e("GasStation", "百度地图客户端已经安装");
		} else {
		Log.e("GasStation", "没有安装百度地图客户端");
		ToastUtils.showShort(context, "没有安装百度地图客户端");
		}
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	
	//高德方式
	public static void openGaoDeMap(Context context,double lon, double lat,String describle) {
		try {
		double[] gd_lat_lon = bdToGaoDe(lon, lat);
		StringBuilder loc = new StringBuilder();
		loc.append("androidamap://viewMap?sourceApplication=XX");
		loc.append("&poiname=");
		loc.append(describle);
		loc.append("&lat=");
		loc.append(gd_lat_lon[0]);
		loc.append("&lon=");
		loc.append(gd_lat_lon[1]);
		loc.append("&dev=0");
		Intent intent = Intent.getIntent(loc.toString());
		if (isInstallPackage("com.autonavi.minimap")) {
			context.startActivity(intent);//启动调用
			Log.e("GasStation", "高德地图客户端已经安装");
		}else {
			Log.e("GasStation", "没有安装高德地图客户端");
			ToastUtils.showShort(context, "没有安装高德地图客户端");
		}
		} catch (Exception e) {
		e.printStackTrace();
		}
	}

}