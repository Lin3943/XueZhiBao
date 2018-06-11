package com.zyl.xuezhibao.utils;

import android.os.SystemClock;
import android.view.View;

/**
 * Created by maisi on 2015/5/26.
 */
public class OnDoubleClickListener implements View.OnClickListener {

    public interface DoubleClickListener {
        public void onDoubleClick(View view);
    }

    private DoubleClickListener mListener;

    public OnDoubleClickListener(DoubleClickListener mListener) {
        this.mListener = mListener;
    }

    //存储时间的数组
    long[] mHits = new long[2];

    @Override
    public void onClick(View v) {
        if (isDoubleClick()) {
            if (mListener != null) {
                mListener.onDoubleClick(v);
            }
        }
    }

    // 双击事件响应
    private boolean isDoubleClick() {
        /**
         * arraycopy,拷贝数组
         * src 要拷贝的源数组
         * srcPos 源数组开始拷贝的下标位置
         * dst 目标数组
         * dstPos 开始存放的下标位置
         * length 要拷贝的长度（元素的个数）
         */
        //实现数组的移位操作，点击一次，左移一位，末尾补上当前开机时间（cpu的时间）
        System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
        mHits[mHits.length - 1] = SystemClock.uptimeMillis();
        //双击事件的时间间隔500ms
        if (mHits[0] >= (SystemClock.uptimeMillis() - 500)) {
            //双击后具体的操作
            //do
            return true;
        }
        return false;
    }
}
