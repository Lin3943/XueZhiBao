package com.zyl.xuezhibao.utils;

import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * Created by qwe on 2017/8/15.
 */

public class PopupWindowsUtil {
    private static PopupWindow mPopupWindow;
    public static void showPopup(View v,View mWindow) {
        if (mPopupWindow == null) {
            mPopupWindow = new PopupWindow(mWindow, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup
                    .LayoutParams.WRAP_CONTENT, true);
        }
        mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        if(android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.M) {
            // 适配 android 7.0
            int[] location = new int[2];
            v.getLocationOnScreen(location);
            int x = location[0];
            int y = location[1];
            mPopupWindow.showAtLocation(v, Gravity.NO_GRAVITY, 0, y + v.getHeight() + 5);
        }else {
//            mPopupWindow.showAsDropDown(v);
            mPopupWindow.showAsDropDown(v, 0, 5);
        }


        mWindow.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (mPopupWindow != null && mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                    mPopupWindow = null;
                }
                return false;
            }
        });
        mPopupWindow.setContentView(v);
    }

    public static void showPopupTab(View v,View mWindow) {
        mPopupWindow = new PopupWindow(mWindow, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        if(android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.M) {
            // 适配 android 7.0
            int[] location = new int[2];
            v.getLocationOnScreen(location);
            int x = location[0];
            int y = location[1];
            mPopupWindow.showAtLocation(v, Gravity.NO_GRAVITY, 0, y + v.getHeight() + 1);
        }else {
            mPopupWindow.showAsDropDown(v, 0, 1);
        }
        mPopupWindow.setContentView(v);
        mWindow.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (mPopupWindow != null && mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                    mPopupWindow = null;
                }
                return false;
            }
        });
    }

    public static void dismiss(){
        if (mPopupWindow != null){
            mPopupWindow.dismiss();
            mPopupWindow = null;
        }
    }
}
