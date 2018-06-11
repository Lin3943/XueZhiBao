package com.zyl.xuezhibao.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class ScreenLockUnlockMonitor
{
    Context mContext;
    Handler mHandler;
    BroadcastReceiver mLockUnlockReceiver;
    public static final int ACTION_SCREEN_LOCKUNLOCK = 1001;
    public static final String KEY_SCREEN_LOCKUNLOCK = "Key_Screen_LockUnlock";

    public ScreenLockUnlockMonitor(Context context)
    {
        mContext = context;
    }

    public void setObserver(Handler hanler)
    {
        mHandler = hanler;
    }

    public void openMonitor()
    {
        if (null == mLockUnlockReceiver) {
            mLockUnlockReceiver = new ScreenOnReceiver();
        }

        final IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_USER_PRESENT);
        mContext.registerReceiver(mLockUnlockReceiver, filter);
    }

    public void closeMonitor()
    {
        if (null != mLockUnlockReceiver) {
            mContext.unregisterReceiver(mLockUnlockReceiver);
        }
    }

    private class ScreenOnReceiver extends BroadcastReceiver
    {
        private static final String TAG = "ScreenOnReceiver";

        public void onReceive(Context context, Intent intent)
        {
            final String action = intent.getAction();
            Message msg = Message.obtain();
            msg.what = ACTION_SCREEN_LOCKUNLOCK;
            Bundle data = new Bundle();
            if (Intent.ACTION_SCREEN_ON.equals(action)) {
                data.putString(KEY_SCREEN_LOCKUNLOCK, Intent.ACTION_SCREEN_ON);
            }
            else if (Intent.ACTION_USER_PRESENT.equals(action)) {
                data.putString(KEY_SCREEN_LOCKUNLOCK, Intent.ACTION_USER_PRESENT);
            }
            else {
                data.putString(KEY_SCREEN_LOCKUNLOCK, Intent.ACTION_SCREEN_OFF);
            }

            msg.setData(data);
            if (null != mHandler) {
                mHandler.sendMessage(msg);
            }
        }
    }
}