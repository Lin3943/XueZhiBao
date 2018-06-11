package com.zyl.xuezhibao.utils;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * 圆形加载进度条
 */
public class ProgressDialogUtils {

    public static ProgressDialog progressDialog;
    private static Object mObject = new Object();

    public static void setProgressDialog(Context context, String info) {
        synchronized (mObject) {

            if (progressDialog == null) {
                progressDialog = new ProgressDialog(context);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.setMessage(info);
                progressDialog.show();
            }
        }
    }

    public static void cancelProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog.cancel();
            progressDialog = null;
        }
    }
}
