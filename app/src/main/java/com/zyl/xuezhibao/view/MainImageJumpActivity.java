package com.zyl.xuezhibao.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.zyl.xuezhibao.R;
import com.zyl.xuezhibao.base.MyBaseActivity;

public class MainImageJumpActivity extends MyBaseActivity {

    private ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_jump);
        initView();
        initData();
        initListener();
    }

    @Override
    public void initView() {
        mImageView = (ImageView) findViewById(R.id.image_jump_bg);
    }

    @Override
    public void initData() {
        mImageView.postDelayed(new Runnable() {
            @Override
            public void run() {
                gotoActivity(MainTabsActivity.class);
                finish();
            }
        },1000);
    }

    @Override
    public void initListener() {

    }

    /**
     * 跳转具体页面
     */
    private void gotoActivity(Class<?> class1) {
        Intent intent = new Intent(this, class1);
        startActivity(intent);
    }
}
