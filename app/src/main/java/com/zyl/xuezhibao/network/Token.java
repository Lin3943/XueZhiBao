package com.zyl.xuezhibao.network;

import android.util.Base64;

import com.alibaba.fastjson.JSONObject;
import com.zyl.xuezhibao.utils.MD5Utils;

import java.util.HashMap;

/**
 * Created by Leo on 2017/4/14.
 *
 * 对请求容器封装
 *
 */

public class Token {
//    token=版本号【version】+时间戳【timestamp】+应用密钥【secret】+消息体【data】


    private static String secret = "725Y2gu142t0s9tU0C0xaW860z6ZtBj2";
    private static String bima = "145158033844";


    public static HashMap<String, String> getRequestMap(HashMap<String, Object> data) {

        HashMap<String, Object> map = new HashMap<String, Object>();//请求集合
        HashMap<String, Object> head = new HashMap<String, Object>();//请求头

        HashMap<String, String> requestMap = new HashMap<String, String>();    //请求

        String dataStr = Base64.encodeToString(JSONObject.toJSONString(data).getBytes(), Base64.DEFAULT);

        StringBuilder sb = new StringBuilder();
        sb.append("1.0");
        sb.append(getTime());
        sb.append("725Y2gu142t0s9tU0C0xaW860z6ZtBj2");//密钥
        sb.append(dataStr);
        String token = MD5Utils.stringToMD5(sb.toString());

        head.put("version", "1.0");
        head.put("timestamp", getTime());
        head.put("code", "145158033844");
        head.put("token", token);


        map.put("head", head);
        map.put("data", dataStr);


        //请求集合再进行一次base64编码
        requestMap.put("data", Base64.encodeToString(JSONObject.toJSONString(map).getBytes(), Base64.DEFAULT));
        return requestMap;
    }


    /**
     * 获取时间戳
     */

    public static String getTime() {
        long time = System.currentTimeMillis() / 1000;//获取系统时间的10位的时间戳
//            new Date().getTime();
        return String.valueOf(time);

    }


}
