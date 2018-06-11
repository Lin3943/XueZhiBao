package com.zyl.xuezhibao.network;

import android.util.Base64;

import com.alibaba.fastjson.JSONObject;
import com.zyl.xuezhibao.utils.Globals;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;

import okhttp3.Call;

/**
 * Created by Leo on 2017/5/10.
 */

public class MyHttpUtils {
    /**
     * 对 张鸿洋 的OkHttpUtils再次封装
     * @param url
     * @param data
     * @param myStringCallback
     */
    public static void httpString(String url, HashMap<String, Object> data, final MyStringCallback myStringCallback) {
        HashMap<String, String> requestMap = Token.getRequestMap(data);
        OkHttpUtils.post().url(url).params(requestMap).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                myStringCallback.MyOnError(call,e);
            }
            @Override
            public void onResponse(String response, int id) {
                try {
                    JSONObject json = JSONObject.parseObject(response);
                    String resultData = new String(Base64.decode(json.getString("data"), Base64.DEFAULT));//响应体需要解码,本地json数据
                    myStringCallback.MyOnResponse(resultData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public abstract static class MyStringCallback{
        public abstract void MyOnError(Call call, Exception e);
        public abstract void MyOnResponse(String resultData);
    }


















}
