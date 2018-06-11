package com.zyl.xuezhibao.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;


import com.zyl.xuezhibao.BuildConfig;

import java.io.File;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by Maisi on 2015/1/26.
 */
public class Globals {

    private static final boolean RELEASE_VERSION_FLAG = BuildConfig.LEO_DEBUG;

    public static final String LOG_TAG = "YouXiangFa";

    /**
     * default priority：Log.DEBUG ；
     * default TAG:OMS-E
     * @param msg
     */
    public static final void log(String msg) {
        if (RELEASE_VERSION_FLAG) {
            Log.e(LOG_TAG, msg + "");
        }
    }

    /**
     * @param priority eg：log(Log.DEBUG, msg); == Log.d(TAG, msg);
     * @param msg
     */
    public static final void log(int priority, String msg) {
        if (RELEASE_VERSION_FLAG) {
            Log.println(priority, LOG_TAG, msg + "");
        }
    }

    public static final void log(String tag, String msg) {
        if (RELEASE_VERSION_FLAG) {
            Log.e(tag, msg + "");
        }
    }

    public static String makeLogTag(Class<?> cls) {
        return cls.getName();
    }

    /**
     * 保存Bitmap到指定目录
     *
     * @param dir      目录
     * @param fileName 文件名
     * @param bitmap
     * @throws IOException
     */
    public static void savaBitmap(File dir, String fileName, Bitmap bitmap) {
//        FileUtils.savaBitmap(dir, fileName, bitmap);
    }

    /**
     * 判断数组中的最大数
     *
     * @param args
     * @return
     */
    public static int getMaxNumber(int args[]) {
        int max = 0;
        for (int
             i = 0; i < args.length; i++) {
            if (args[i] > args[max]) {
                max = i;
            }
        }
        return args[max];
    }

    /**
     * 判断数组中的最小数
     *
     * @param args
     * @return
     */
    public static int getMinNumber(int args[]) {
        int min = 0;
        for (int
             i = 0; i < args.length; i++) {
            if (args[i] < args[min]) {
                min = i;
            }
        }
        return args[min];
    }

    /**
     * 去处url中特殊字符作为文件的名称
     *
     * @param url
     * @return
     */
    public static String parseFileName(String url) {
        // 去处url中特殊字符作为文件的名称
        String urlKey = "";
        String temp[] = url.split("/");
        if (temp.length > 1) {
            urlKey = temp[temp.length - 1];
        }
        return urlKey;
    }

    public static long getApkUpdateTime(Context context) {
        PackageManager pm = context.getPackageManager();
        ZipFile zf = null;
        try {
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            zf = new ZipFile(packageInfo.applicationInfo.sourceDir);
            ZipEntry ze = zf.getEntry("classes.dex");
            return ze.getTime();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (zf != null) {
                try {
                    zf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }

    /**
     * 移除url尾部的/
     * @param url
     * @return
     */
    public static String removeUrlEndSlash(String url) {
        if (TextUtils.isEmpty(url)) {
            return url;
        }
        if (url.endsWith("/")) {
            try {
                int last = url.lastIndexOf("/");
                return url.substring(0, last);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return url;
    }

}
