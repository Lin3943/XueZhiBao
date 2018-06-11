package com.zyl.xuezhibao.utils;

import android.content.Context;

/**
 * Created by qwe on 2017/11/10.
 */

public class UMengAliasUtils {
    class ALIAS_TYPE{
        public static final String MINE = "mine";
    }

    /**
     * 设置友盟推送别名
     * @param context
     * @param phone
     */
    public static void setUMengAlias(Context context,String phone){
        removeAlias(context,phone);
    }

    public static void removeAlias(final Context context, final String phone){
//        PushAgent.getInstance(context).removeAlias(phone, ALIAS_TYPE.MINE, new UTrack.ICallBack() {
//            @Override
//            public void onMessage(boolean isSuccess, String message) {
//                Globals.log("log zh removeAlias","isSuccess:"+isSuccess+",message:"+message);
//                addExclusiveAlias(context,phone);
//            }
//        });
    }

    /**
     *设置用户id和device_token的一一映射关系，确保同一个alias只对应一台设备
     * @param context
     * @param phone
     */
    private static void addExclusiveAlias(Context context,String phone){
//        PushAgent.getInstance(context).addExclusiveAlias(phone, ALIAS_TYPE.MINE, new UTrack.ICallBack() {
//            @Override
//            public void onMessage(boolean isSuccess, String message) {
//                Globals.log("log zh addAlias","isSuccess:"+isSuccess+",message:"+message);
//            }
//        });
    }

    /**
     * 设置用户id和device_token的一对多的映射关系
     * @param context
     * @param phone
     */
    private static void addAlias(Context context,String phone){
//        PushAgent.getInstance(context).addAlias(phone, ALIAS_TYPE.MINE, new UTrack.ICallBack() {
//            @Override
//            public void onMessage(boolean isSuccess, String message) {
//                Globals.log("log zh addAlias","isSuccess:"+isSuccess+",message:"+message);
//            }
//        });
    }
}
