package com.zyl.xuezhibao.view_;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;

import com.alibaba.fastjson.JSONObject;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zyl.xuezhibao.R;
import com.zyl.xuezhibao.base.BaseActivity;
import com.zyl.xuezhibao.base.BaseSharePerence;
import com.zyl.xuezhibao.base.Constants;
import com.zyl.xuezhibao.network.Token;
import com.zyl.xuezhibao.utils.Globals;

import java.util.HashMap;

import okhttp3.Call;

public class MainActivity extends BaseActivity {

    private BaseSharePerence mSharePerence;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initListener();
    }

    @Override
    public void initView() {
        mSharePerence = BaseSharePerence.getInstance(this.getApplication());
        mImageView = (ImageView) findViewById(R.id.image_main_bg);
    }

    @Override
    public void initData() {
        getImgURL();
        getOssURL(this);
        mImageView.postDelayed(new Runnable() {
            @Override
            public void run() {
//                    gotoActivity(MainTabsActivity.class);
                gotoActivity(MainFragmentActivity.class);
                mSharePerence.setMemberInfo(null);
                finish();
            }
        }, 1000);
    }

    @Override
    public void initListener() {

    }

    /**
     * 刷新用户信息
     */
    private void refreshUserInfo() {
        HashMap<String, Object> data = new HashMap<>();
        HashMap<String, String> requestMap = Token.getRequestMap(data);
        OkHttpUtils.post().url(Constants.Url.GetMemberInfo_URL).params(requestMap).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                gotoActivity(MainFragmentActivity.class);
                mSharePerence.setMemberInfo(null);
                finish();
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    JSONObject json = JSONObject.parseObject(response);
                    String resultData = new String(Base64.decode(json.getString("data"), Base64.DEFAULT));//响应体需要解码,本地json数据
                    Globals.log("log login用户信息", resultData);
                    JSONObject object = JSONObject.parseObject(resultData);
                    if ("success".equals(object.getString("status"))) {
                        mSharePerence.setMemberInfo(resultData);
                        gotoActivity(MainFragmentActivity.class);
                        finish();
                    } else {
                        gotoActivity(MainFragmentActivity.class);
                        mSharePerence.setMemberInfo(null);
                        finish();
                    }
                } catch (Exception e) {
                    Globals.log("log login用户信息", e.toString());
                    gotoActivity(MainFragmentActivity.class);
                    mSharePerence.setMemberInfo(null);
                    finish();
                }
            }
        });
    }

    /**
     * 获取阿里云图片的域名
     */
    private void getImgURL() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("type", 0);
        HashMap<String, String> requestMap = Token.getRequestMap(data);
        OkHttpUtils.post().url(Constants.Url.imageUrl_URl).params(requestMap).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    JSONObject json = JSONObject.parseObject(response);
                    String resultData = new String(Base64.decode(json.getString("data"), Base64.DEFAULT));//响应体需要解码,本地json数据
                    JSONObject object = JSONObject.parseObject(resultData);
                    if (object.getString("status").equals("success")) {
                        Globals.log("log zh 获取阿里云图片的域名", object.getString("imageUrl"));
                        mSharePerence.setImgUrl(object.getString("imageUrl"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * imageParam_url
     * 获取阿里云域名
     */
    private void getOssURL(final Context context) {
        HashMap<String, Object> data = new HashMap<>();
        HashMap<String, String> requestMap = Token.getRequestMap(data);
        OkHttpUtils.post().url(Constants.Url.imageParam_URl).params(requestMap).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    JSONObject json = JSONObject.parseObject(response);
                    String resultData = new String(Base64.decode(json.getString("data"), Base64.DEFAULT));//响应体需要解码,本地json数据
                    Globals.log("log zh getOssURL" + resultData);
                    mSharePerence.setOssInfo(resultData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 跳转具体页面
     */
    private void gotoActivity(Class<?> class1) {
        Intent intent = new Intent(this, class1);
        startActivity(intent);
    }

    /**
     * 退出登录接口,为了删除设备号
     */
    private void exitHttp() {
        HashMap<String, Object> data = new HashMap<>();
        if (mSharePerence.getDeviceToken() != null) {
            data.put("deviceToken", mSharePerence.getDeviceToken());//设备号
        }
        HashMap<String, String> requestMap = Token.getRequestMap(data);
        OkHttpUtils.post().url(Constants.Url.loginOut_URL).params(requestMap).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    JSONObject json = JSONObject.parseObject(response);
                    String resultData = new String(Base64.decode(json.getString("data"), Base64.DEFAULT));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
