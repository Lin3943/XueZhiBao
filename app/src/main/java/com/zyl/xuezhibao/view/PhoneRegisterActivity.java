package com.zyl.xuezhibao.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
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
import com.zyl.xuezhibao.view_.MainFragmentActivity;

import java.util.HashMap;

import okhttp3.Call;

/**
 * 注册页面
 */

public class PhoneRegisterActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 登录
     */
    private TextView mActivityRegisterLogin;
    private ImageView mActivityRegisterTopClose;
    /**
     * 手机号码
     */
    private EditText mEditPhone;
    /**
     * 下一步
     */
    private Button mRegisterBtnNext;
    /**
     * 验证码
     */
    private EditText mEditCode;
    /**
     * 获取验证码
     */
    private TextView mTextGetCode;
    /**
     * 密码为6-16位数字或字母
     */
    private EditText mEditPsw;
    private EditText mEditInviterPhone;
    /**
     * 完成注册
     */
    private Button mRegisterBtnFinish;
    private View mViewPhone;
    private View mViewCode;
    private String mStrPhone;
    private ImageView mImageBackPhone;
    private TimeCount mTimeCount;
    private BaseSharePerence mSharePerence;
    private String mInvitePhone;
    private CheckBox mCheckBoxSure;
    private View mAgreementVine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_phone_register);
        initView();
        initData();
        initListener();

    }

    public void initView() {
        mActivityRegisterLogin = (TextView) findViewById(R.id.tv_gobackto_login);
        mImageBackPhone = (ImageView) findViewById(R.id.register_top_back);
        mActivityRegisterTopClose = (ImageView) findViewById(R.id.activity_register_top_close);
        mEditPhone = (EditText) findViewById(R.id.edit_phone);
        mRegisterBtnNext = (Button) findViewById(R.id.register_btn_next);
        mEditCode = (EditText) findViewById(R.id.edit_code);
        mTextGetCode = (TextView) findViewById(R.id.text_get_code);
        mEditPsw = (EditText) findViewById(R.id.edit_psw);
        mEditInviterPhone = (EditText) findViewById(R.id.edit_inviter_phone);
        mRegisterBtnFinish = (Button) findViewById(R.id.register_btn_finish);

        mViewPhone = findViewById(R.id.layout_register_phone);
        mViewCode = findViewById(R.id.layout_register_code);

        mTimeCount = new TimeCount(mTextGetCode,60000, 1000);
        mCheckBoxSure = (CheckBox) findViewById(R.id.checkbox_sure);
        mAgreementVine = findViewById(R.id.linearLayout_register_agreement);
        findViewById(R.id.tv_register_agreement).setOnClickListener(this);
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        if (intent != null){
            //从扫描二维码过来邀请注册
            mInvitePhone = intent.getStringExtra(Constants.IntentKey.STR_Phone);
            mEditInviterPhone.setText(mInvitePhone);
        }
        mSharePerence = BaseSharePerence.getInstance(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void initListener() {
        mRegisterBtnNext.setOnClickListener(this);
        mActivityRegisterTopClose.setOnClickListener(this);
        mActivityRegisterLogin.setOnClickListener(this);
        mImageBackPhone.setOnClickListener(this);
        mRegisterBtnFinish.setOnClickListener(this);
        mTextGetCode.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_gobackto_login://去登录
                finish();
                break;
            case R.id.register_top_back://返回，
                mViewPhone.setVisibility(View.VISIBLE);
                mViewCode.setVisibility(View.GONE);
                mActivityRegisterLogin.setVisibility(View.VISIBLE);
                mImageBackPhone.setVisibility(View.GONE);
                mAgreementVine.setVisibility(View.VISIBLE);
                break;
            case R.id.activity_register_top_close:
                finish();
                break;
            case R.id.register_btn_next://下一步
                mStrPhone = mEditPhone.getText().toString().trim();
                if (mStrPhone.length() != 11){
                    ToastUtils.showShort(this,"请输入正确手机号码！");
                    return;
                }
                if (!mCheckBoxSure.isChecked()){
                    ToastUtils.showShort(this,"请勾选用户协议！");
                    return;
                }
                mViewPhone.setVisibility(View.GONE);
                mViewCode.setVisibility(View.VISIBLE);
                mActivityRegisterLogin.setVisibility(View.GONE);
                mImageBackPhone.setVisibility(View.VISIBLE);
                mAgreementVine.setVisibility(View.GONE);
                break;
            case R.id.text_get_code://获得验证码
                getSendSms(mStrPhone);
                break;
            case R.id.register_btn_finish://完成注册
                String code = mEditCode.getText().toString().trim();
                String password = mEditPsw.getText().toString().trim();
                String inviterPhone = mEditInviterPhone.getText().toString().trim();
                if (code.length() == 0){
                    ToastUtils.showShort(this,"请输入验证码");
                    return;
                }
                if (password.length() > 16 || password.length() < 6){
                    ToastUtils.showShort(this,"请输入密码为6-16位数字或字母");
                    return;
                }
                if (inviterPhone.length() != 11){
                    ToastUtils.showShort(this,"请输入邀请人手机号码！");
                    return;
                }
                registerFinish(mStrPhone,code,password,inviterPhone);
                break;
            case R.id.tv_register_agreement:
                break;
        }
    }

    /**
     * 获得注册验证码
     * @param phone
     */
    private void getSendSms(String phone) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("phone", phone);
        Globals.log("log zh获得验证码data" + data.toString());
        HashMap<String, String> requestMap = Token.getRequestMap(data);
        OkHttpUtils.post().url(Constants.Url.SendSms_URL).params(requestMap).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtils.showShort(PhoneRegisterActivity.this,"网络异常！");
            }
            @Override
            public void onResponse(String response, int id) {
                Globals.log("log zh获得验证码response：" + response);
                try {
                    JSONObject json = JSONObject.parseObject(response);
                    String resultData = new String(Base64.decode(json.getString("data"), Base64.DEFAULT));
                    Globals.log("log zh获得验证码" + resultData.toString());
                    JSONObject object = JSONObject.parseObject(resultData);
                    if ("success".equals(object.getString("status"))){
                        mTimeCount.start();
                        ToastUtils.showShort(PhoneRegisterActivity.this,object.getString("message"));
                    }else {
                        ToastUtils.showShort(PhoneRegisterActivity.this,object.getString("message"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 手机注册
     * @param phone
     * @param code
     * @param password
     * @param inviterPhone
     */
    private void registerFinish(final String phone, String code, final String password, String inviterPhone) {
        ProgressDialogUtils.setProgressDialog(this,"正在注册...");
        HashMap<String, Object> data = new HashMap<>();
        data.put("phone", phone);
        data.put("code", code);
        data.put("password", password);
        data.put("refreePhone", inviterPhone);
        Globals.log("log zh注册data" + data.toString());
        HashMap<String, String> requestMap = Token.getRequestMap(data);
        OkHttpUtils.post().url(Constants.Url.Register_URL).params(requestMap).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                ProgressDialogUtils.cancelProgressDialog();
                ToastUtils.showShort(PhoneRegisterActivity.this,"网络异常！");
            }
            @Override
            public void onResponse(String response, int id) {

                Globals.log("log zh注册 " +response.toString());
                ProgressDialogUtils.cancelProgressDialog();
                try {
                    JSONObject json = JSONObject.parseObject(response);
                    String resultData = new String(Base64.decode(json.getString("data"), Base64.DEFAULT));
                    JSONObject object = JSONObject.parseObject(resultData);
                    ToastUtils.showShort(PhoneRegisterActivity.this,object.getString("message"));
                    if ("success".equals(object.getString("status"))){
                        //注册完，没用户数据返回，还要调登录接口
                        //登录
                        loginFinish(phone,password);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void loginFinish(String phone,String password) {
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
                ToastUtils.showShort(PhoneRegisterActivity.this,"网络异常！");
            }
            @Override
            public void onResponse(String response, int id) {
                try {
                    JSONObject json = JSONObject.parseObject(response);
                    String resultData = new String(Base64.decode(json.getString("data"), Base64.DEFAULT));
                    Globals.log("log zh RegisterPhoneActivity登录" + resultData.toString());
                    JSONObject object = JSONObject.parseObject(resultData);
                    String status = object.getString("status");
                    if (status.equals("success")){
                        mSharePerence.setMemberInfo(resultData);
                        Intent intent = new Intent(PhoneRegisterActivity.this,MainFragmentActivity.class);
//                        UMengAliasUtils.setUMengAlias(PhoneRegisterActivity.this,mSharePerence.getMemberInfo().getPhone());
                        startActivity(intent);
                        finish();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}

