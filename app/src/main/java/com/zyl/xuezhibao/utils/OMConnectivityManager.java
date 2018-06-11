package com.zyl.xuezhibao.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by leo
 */
public class OMConnectivityManager {


    /**
     * 用于查看是否有网络
     */
    private static final Object mSyncLock = new Object();
    private static OMConnectivityManager mInstance;
    private static ConnectivityManager mConnectivityManager;

    public static OMConnectivityManager getInstance(Context context) {
        synchronized (mSyncLock) {
            if (mInstance == null) {
                mInstance = new OMConnectivityManager(context);
            }

            return mInstance;
        }
    }

    private OMConnectivityManager(Context context) {
        mConnectivityManager = (ConnectivityManager) context.
                getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public boolean isAvailable() {
        NetworkInfo networkInfo = mConnectivityManager.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isAvailable()) {
            return false;
        }
        return true;
    }
}
