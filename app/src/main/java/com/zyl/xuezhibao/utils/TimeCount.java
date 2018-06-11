package com.zyl.xuezhibao.utils;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created by qwe on 2017/6/15.
 */

public class TimeCount extends CountDownTimer {
    private TextView mCodePhone;
    public TimeCount(TextView mCodePhone,long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.mCodePhone = mCodePhone;
    }
    @Override
    public void onTick(long millisUntilFinished) {

        mCodePhone.setClickable(false);
        mCodePhone.setText(millisUntilFinished / 1000 + "秒后重新发送");
    }

    @Override
    public void onFinish() {
        mCodePhone.setText("获取验证码");
        mCodePhone.setClickable(true);
//            mCodePhone.setBackgroundColor(Color.parseColor("#4EB84A"));
    }
}