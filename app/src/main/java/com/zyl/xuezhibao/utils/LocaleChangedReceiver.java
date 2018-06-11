package com.zyl.xuezhibao.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class LocaleChangedReceiver extends BroadcastReceiver {

    public interface LocaleChangedListener {
        void onLocaleChanged();
    }

    private LocaleChangedListener mListener;

    public LocaleChangedReceiver(LocaleChangedListener listener) {
        mListener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_LOCALE_CHANGED.equals(intent.getAction())) {
            if(mListener != null){
                mListener.onLocaleChanged();
            }
        }
    }
}