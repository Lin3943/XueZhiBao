package com.zyl.xuezhibao.utils;

/**
 * Created by qwe on 2017/9/21.
 */

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;

import static android.content.Context.CONNECTIVITY_SERVICE;
import static android.content.Context.WIFI_SERVICE;

public class MyNetworkUtil {
    public MyNetworkUtil() {
    }

    public static boolean isNetworkAvailable(Context context) {
        NetworkInfo networkInfo = getNetworkInfo(context);
        return networkInfo == null?false:networkInfo.isConnected();
    }

    public static boolean isWifi(Context context) {
        NetworkInfo networkInfo = getNetworkInfo(context);
        return networkInfo != null && networkInfo.isAvailable()?networkInfo.getType() == 1 && networkInfo.getState() == NetworkInfo.State.CONNECTED:false;
    }

    public static NetworkInfo getNetworkInfo(Context context) {
        try {
            ConnectivityManager e = (ConnectivityManager)context.getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = e.getActiveNetworkInfo();
            if(networkInfo == null && Build.VERSION.SDK_INT >= 23) {
                Network network = e.getActiveNetwork();
                networkInfo = e.getNetworkInfo(network);
            }

            return networkInfo;
        } catch (Exception var4) {
            return null;
        }
    }

    public static WifiInfo getWifiInfo(Context context) {
        WifiManager wifi = (WifiManager)context.getSystemService(WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        return info;
    }
}