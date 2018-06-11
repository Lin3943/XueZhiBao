package com.zyl.xuezhibao.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by admin on 2017/3/8.
 */
public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = BaseActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //友盟推送记录活动
//        PushAgent.getInstance(this).onAppStart();
    }

   public abstract void initView();
   public abstract void initData();
   public abstract void initListener();


}
