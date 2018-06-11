package com.zyl.xuezhibao.utils;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;

/**
 * Created by qwe on 2017/11/22.
 */

public class SimpleDateFormatUtil {
    private static SimpleDateFormatUtil mSimpleDateFormatUtil;
    private SimpleDateFormat mFormat;

    private SimpleDateFormatUtil(){
        mFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public static SimpleDateFormatUtil getInstance() {
        if (mSimpleDateFormatUtil == null){
            synchronized (SimpleDateFormatUtil.class) {
                if (mSimpleDateFormatUtil == null) {
                    mSimpleDateFormatUtil = new SimpleDateFormatUtil();
                }
            }
        }
        return mSimpleDateFormatUtil;
    }

    public String getDataFormat(long time){
        return mFormat.format(time);
    }
}
