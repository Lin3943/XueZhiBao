package com.zyl.xuezhibao.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.vov.vitamio.Vitamio;

/**
 * Created by admin on 2017/3/8.
 */
public abstract class MyBaseActivity extends AppCompatActivity {
    private static final String TAG = MyBaseActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化Vitamio
        Vitamio.isInitialized(this);
        //友盟推送记录活动
//        PushAgent.getInstance(this).onAppStart();
    }

   public abstract void initView();
   public abstract void initData();
   public abstract void initListener();


}
