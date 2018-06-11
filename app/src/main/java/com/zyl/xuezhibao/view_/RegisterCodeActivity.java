package com.zyl.xuezhibao.view_;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zyl.xuezhibao.R;
import com.zyl.xuezhibao.base.BaseActivity;


public class RegisterCodeActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mRegisterTopBack;
    private LinearLayout mRegisterTopBackL;
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
    /**
     * 完成注册
     */
    private Button mRegisterBtnFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_code);
        initView();
    }

    /**
     * 跳转具体页面
     */
    private void gotoActivity(Class<?> class1) {
        Intent intent = new Intent(RegisterCodeActivity.this, class1);
        startActivity(intent);
    }

    public void initView() {
        mRegisterTopBack = (ImageView) findViewById(R.id.register_top_back);
        mRegisterTopBack.setOnClickListener(this);
        mRegisterTopBackL = (LinearLayout) findViewById(R.id.register_top_backL);
        mRegisterTopBackL.setOnClickListener(this);
        mEditCode = (EditText) findViewById(R.id.edit_code);
        mTextGetCode = (TextView) findViewById(R.id.text_get_code);
        mEditPsw = (EditText) findViewById(R.id.edit_psw);
        mRegisterBtnFinish = (Button) findViewById(R.id.register_btn_finish);
        mRegisterBtnFinish.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_top_back:
            case R.id.register_top_backL:
                finish();
                break;
            case R.id.register_btn_finish:
                gotoActivity(LoginActivity.class);

                break;
        }
    }
}
