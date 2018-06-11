package com.zyl.xuezhibao.view_;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zyl.xuezhibao.R;
import com.zyl.xuezhibao.base.BaseActivity;
import com.zyl.xuezhibao.base.BaseSharePerence;
import com.zyl.xuezhibao.base.Constants;
import com.zyl.xuezhibao.base.MyBaseApplication;
import com.zyl.xuezhibao.network.Token;
import com.zyl.xuezhibao.utils.Globals;
import com.zyl.xuezhibao.utils.ProgressDialogUtils;
import com.zyl.xuezhibao.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;


public class LoginActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 注册
     */
    private TextView mActivityLoginRegister;
    private ImageView mActivityLoginTopClose;
    private LinearLayout mActivityLoginTopCloseL;
    /**
     * 手机号码
     */
    private EditText mEditPhone;
    private LinearLayout mLinearPhone;
    /**
     * 请输入密码
     */
    private EditText mEditPsw;
    /**
     * 登录
     */
    private Button mActivityLoginBtnLogin;
    private LinearLayout mLinearWechat;
    private BaseSharePerence mSharePerence;

    private long exitTime = 0;
    //微信登录
    private UMShareAPI mShareAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        MyBaseApplication.getInstance().addActivity(this);
        initView();
        initData();
        initListener();
    }


    /**
     * 跳转具体页面
     */
    private void gotoActivity(Class<?> class1) {
        Intent intent = new Intent(LoginActivity.this, class1);
        startActivity(intent);
    }


    public void initView() {
        mShareAPI = UMShareAPI.get(this);
        UMShareConfig config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(true);
        mShareAPI.setShareConfig(config);
        mActivityLoginRegister = (TextView) findViewById(R.id.activity_login_register);
        mActivityLoginTopClose = (ImageView) findViewById(R.id.activity_login_top_close);
        mActivityLoginTopCloseL = (LinearLayout) findViewById(R.id.activity_login_top_closeL);
        mEditPhone = (EditText) findViewById(R.id.edit_phone);
        mLinearPhone = (LinearLayout) findViewById(R.id.linear_phone);
        mEditPsw = (EditText) findViewById(R.id.edit_psw);
        mActivityLoginBtnLogin = (Button) findViewById(R.id.activity_login_btn_login);
        mLinearWechat = (LinearLayout) findViewById(R.id.linear_text_login);
    }

    @Override
    public void initData() {
        mSharePerence = BaseSharePerence.getInstance(this);
//        if (mMemberInfo != null){
//            refreshUserInfo();
//        }

    }

    @Override
    public void initListener() {
        mActivityLoginRegister.setOnClickListener(this);
        mActivityLoginTopClose.setOnClickListener(this);
        mActivityLoginTopCloseL.setOnClickListener(this);
        mActivityLoginBtnLogin.setOnClickListener(this);
        mLinearWechat.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_login_register://注册
                gotoActivity(RegisterPhoneActivity.class);
                break;
            case R.id.activity_login_top_close:
            case R.id.activity_login_top_closeL:
                finish();
                break;

            case R.id.activity_login_btn_login://登录
                login();
//                mShareAPI.doOauthVerify(this, SHARE_MEDIA.WEIXIN, umAuthListener);//微信授权登录
                break;
            case R.id.linear_text_login://短信登录
                gotoActivity(TextLoginActivity.class);
                break;
        }
    }

    /**
     * 登录方法
     */
    private void login(){
        String phone = mEditPhone.getText().toString().trim();//去除输入的空格
        String password = mEditPsw.getText().toString().trim();
        if (phone.length() != 11){
            ToastUtils.showShort(this,"请输入正确手机号码！");//提示框
            return;
        }
        if (password.length() > 16 || password.length() < 6){
            ToastUtils.showShort(this,"请输入密码为6-16位数字或字母");
            return;
        }
        loginFinish(phone,password);
    }
    private void loginFinish(String phone,String password) {
        ProgressDialogUtils.setProgressDialog(this,"正在登录...");//登录进度框
        HashMap<String, Object> data = new HashMap<>();
        data.put("phone", phone);
        data.put("password", password);
        if (mSharePerence.getDeviceToken() != null){
            data.put("deviceToken", mSharePerence.getDeviceToken());
        }
        HashMap<String, String> requestMap = Token.getRequestMap(data);
        OkHttpUtils.post().url(Constants.Url.Login_URL).params(requestMap).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                //进行异常处理（在onError方法里进行异常处理）
                ProgressDialogUtils.cancelProgressDialog();
                ToastUtils.showShort(LoginActivity.this,"网络异常！");
            }
            @Override
            public void onResponse(String response, int id) {
                ProgressDialogUtils.cancelProgressDialog();
                try {
                    JSONObject json = JSONObject.parseObject(response);
                    String resultData = new String(Base64.decode(json.getString("data"), Base64.DEFAULT));
                    Globals.log("log zh登录" + resultData.toString());
                    JSONObject object = JSONObject.parseObject(resultData);
                    ToastUtils.showShort(LoginActivity.this,object.getString("message"));
                    if ("success".equals(object.getString("status"))){
                        mSharePerence.setMemberInfo(resultData);
                        gotoActivity(MainFragmentActivity.class);
                        finish();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //友盟微信回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //授权开始的回调
        }
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Globals.log("log 微信返回信息", data.toString());
            String access_token = data.get("access_token");
            String openid = data.get("openid");
            Globals.log("log 微信返回信息access_token", access_token);
            Globals.log("log 微信返回信息openid", openid);
            getUserMesg(access_token, openid);
        }
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Globals.log("log 微信返回信息", "Authorize fail");
        }
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Globals.log("log 微信返回信息", "Authorize cancel");
        }
    };

    /**
     * 获取微信的个人信息
     * @param access_token
     * @param openid
     */
    private void getUserMesg(final String access_token, final String openid) {
        String path = "https://api.weixin.qq.com/sns/userinfo?access_token="
                + access_token
                + "&openid="
                + openid;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            startActivity(new Intent(this,MainFragmentActivity.class));
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
