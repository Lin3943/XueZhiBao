package com.zyl.xuezhibao.view_;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zyl.xuezhibao.R;
import com.zyl.xuezhibao.base.BaseActivity;
import com.zyl.xuezhibao.base.BaseSharePerence;
import com.zyl.xuezhibao.base.Constants;
import com.zyl.xuezhibao.network.Token;
import com.zyl.xuezhibao.utils.Globals;
import com.zyl.xuezhibao.utils.ProgressDialogUtils;
import com.zyl.xuezhibao.utils.TimeCount;
import com.zyl.xuezhibao.utils.ToastUtils;

import java.util.HashMap;

import okhttp3.Call;


/**
 * 短信登录页面（SHUA）
 */

public class TextLoginActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 注册
     */
    private TextView mActivityTextLoginRegister;
    private ImageView mActivityTextLoginTopClose;
    private AutoLinearLayout mActivityLoginTextTopCloseL;
    /**
     * 请输入账号
     */
    private EditText mTextLoginEditPhone;
    private AutoLinearLayout mLinearTextLoginPhone;
    /**
     * 请输入验证码
     */
    private EditText mTextLoginEditCode;
    /**
     * 登录
     */
    private Button mTextLoginBtnLogin;

    private BaseSharePerence mSharePerence;

    /**
     * 或
     */
    private TextView mTextOr;
    private AutoLinearLayout mLinearLogin;
    /**
     * 获取验证码
     */
    private TextView mTextCodeGet;

    private TimeCount mTimeCount;

    private String mStrPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_login);
        initView();
        initData();
    }

    @Override
    public void initView() {
        mActivityTextLoginRegister = (TextView) findViewById(R.id.activity_text_login_register);
        mActivityTextLoginRegister.setOnClickListener(this);
        mActivityTextLoginTopClose = (ImageView) findViewById(R.id.activity_text_login_top_close);
        mActivityTextLoginTopClose.setOnClickListener(this);
        mActivityLoginTextTopCloseL = (AutoLinearLayout) findViewById(R.id.activity_login_text_top_closeL);
        mActivityLoginTextTopCloseL.setOnClickListener(this);
        mTextLoginEditPhone = (EditText) findViewById(R.id.text_login_edit_phone);
        mLinearTextLoginPhone = (AutoLinearLayout) findViewById(R.id.linear_text_login_phone);
        mTextLoginEditCode = (EditText) findViewById(R.id.text_login_edit_code);
        mTextLoginBtnLogin = (Button) findViewById(R.id.text_login_btn_login);
        mTextLoginBtnLogin.setOnClickListener(this);

        mTextOr = (TextView) findViewById(R.id.text_or);
        mLinearLogin = (AutoLinearLayout) findViewById(R.id.linear_login);
        mLinearLogin.setOnClickListener(this);
        mTextCodeGet = (TextView) findViewById(R.id.text_code_get);
        mTextCodeGet.setOnClickListener(this);

        mTimeCount = new TimeCount(mTextCodeGet,60000, 1000);//获取验证码
    }

    @Override
    public void initData() {
        mSharePerence = BaseSharePerence.getInstance(this);
    }


    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_text_login_register://注册
                gotoActivity(RegisterPhoneActivity.class);
                break;

            case R.id.activity_text_login_top_close:
            case R.id.activity_login_text_top_closeL://关闭
                finish();
                break;

            case R.id.text_login_btn_login://登录（短信登录）
                textlogin();//短信登录

                break;
            case R.id.linear_login://账号登录
                finish();
                break;

            case R.id.text_code_get://获取验证码

                String phone = mTextLoginEditPhone.getText().toString().trim();//去除输入的空格
                if (phone.length() != 11){
                    ToastUtils.showShort(this,"请输入正确账号！");//提示框
                    return;
                }
                mStrPhone = mTextLoginEditPhone.getText().toString().trim();
                getSmsLogin(mStrPhone);
                break;
        }
    }

    /**
     * 短信登录
     */
    private void textlogin() {
        String code = mTextLoginEditCode.getText().toString().trim();
        String phone = mTextLoginEditPhone.getText().toString().trim();//去除输入的空格

        if (phone.length() != 11){
            ToastUtils.showShort(this,"请输入正确账号！");//提示框
            return;
        }

        if (code.length() == 0 ){
            ToastUtils.showShort(this,"请输入验证码");
            return;
        }

        loginFinish(phone,code);
    }

    private void loginFinish(String phone,String code) {
        ProgressDialogUtils.setProgressDialog(this,"正在登录...");//登录进度框
        HashMap<String, Object> data = new HashMap<>();
        data.put("phone", phone);
        data.put("code", code);
        if (mSharePerence.getDeviceToken() != null){
            data.put("deviceToken", mSharePerence.getDeviceToken());
        }
        HashMap<String, String> requestMap = Token.getRequestMap(data);
        OkHttpUtils.post().url(Constants.Url.DirectLogin_URL).params(requestMap).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                //进行异常处理（在onError方法里进行异常处理）
                ProgressDialogUtils.cancelProgressDialog();
                ToastUtils.showShort(TextLoginActivity.this,"网络异常！");
            }
            @Override
            public void onResponse(String response, int id) {
                ProgressDialogUtils.cancelProgressDialog();
                try {
                    JSONObject json = JSONObject.parseObject(response);
                    String resultData = new String(Base64.decode(json.getString("data"), Base64.DEFAULT));
                    Globals.log("log ss登录" + resultData.toString());
                    JSONObject object = JSONObject.parseObject(resultData);
                    ToastUtils.showShort(TextLoginActivity.this,object.getString("message"));
                    if ("success".equals(object.getString("status"))){
                        mSharePerence.setMemberInfo(resultData);
//                        UMengAliasUtils.setUMengAlias(TextLoginActivity.this,mSharePerence.getMemberInfo().getPhone());
                        gotoActivity(MainFragmentActivity.class);
                        finish();
                    }
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
        Intent intent = new Intent(TextLoginActivity.this, class1);
        startActivity(intent);
    }


    /**
     * 获取短信验证码
     * @param phone
     */
    private void getSmsLogin(String phone) {
        ProgressDialogUtils.setProgressDialog(this,"正在加载...");
        HashMap<String, Object> data = new HashMap<>();
        data.put("phone", phone);
        HashMap<String, String> requestMap = Token.getRequestMap(data);
        OkHttpUtils.post().url(Constants.Url.SmsLogin_URL).params(requestMap).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtils.showShort(TextLoginActivity.this,"系统异常！");
                ProgressDialogUtils.cancelProgressDialog();
            }
            @Override
            public void onResponse(String response, int id) {
                ProgressDialogUtils.cancelProgressDialog();
                try {
                    JSONObject json = JSONObject.parseObject(response);
                    String resultData = new String(Base64.decode(json.getString("data"), Base64.DEFAULT));
                    Globals.log("log ss获得验证码" + resultData.toString());
                    JSONObject object = JSONObject.parseObject(resultData);
                    if ("success".equals(object.getString("status"))){
                        mTimeCount.start();
                        ToastUtils.showShort(TextLoginActivity.this,object.getString("message"));
                    }else {
                        ToastUtils.showShort(TextLoginActivity.this,object.getString("message"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
