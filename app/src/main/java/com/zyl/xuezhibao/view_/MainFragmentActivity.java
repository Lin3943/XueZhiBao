package com.zyl.xuezhibao.view_;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.zyl.xuezhibao.R;
import com.zyl.xuezhibao.base.BaseApplication;
import com.zyl.xuezhibao.base.BaseSharePerence;
import com.zyl.xuezhibao.map.LocationService;
import com.zyl.xuezhibao.utils.Globals;

public class MainFragmentActivity extends FragmentActivity {

    private FragmentTabHost mTabHost;
    private long exitTime = 0;

    private Class classFragment[] = new Class[] {
                //            //MineFragment.class
    };

    private int image[] = new int[] {
            R.drawable.selector_btn_home_home,
            R.drawable.selector_btn_home_nearby,
            R.drawable.selector_btn_home_shopping_cart,
            R.drawable.selector_btn_home_mine
    };

    private String strText[] = new String[] {"首页", "附近", "商城","我的" };
    private LocationService mLocationService;
    private BaseSharePerence mSharePerence;
    private FragmentManager mFragmentManager;
//    private PersonalFragment mineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tabs);
        initUI();
        //版本更新查询

        setTabIsLoginOnClickListener(1);
        setTabIsLoginOnClickListener(2);
        setTabIsLoginOnClickListener(3);
        tabChange();
    }

    private void tabChange(){
        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                //tabId 页面标签
                Globals.log("log 切换监听tabId",tabId);
                switch (tabId){
                    //隐藏MarketMineFragment页的返回键
                    case "tab3":
                        break;
                }
            }
        });
    }

    private void setTabIsLoginOnClickListener(final int pos){
        mTabHost.getTabWidget().getChildTabViewAt(pos).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isLogin()) {
                    //如果已经登录，执行默认点击操作
                    //由于已经覆写了点击方法，所以需要实现tab切换
                    mTabHost.setCurrentTab(pos);
                    mTabHost.getTabWidget().requestFocus(View.FOCUS_FORWARD);
                }
            }
        });
    }

    private void initUI() {
        mSharePerence = BaseSharePerence.getInstance(this.getApplication());
        mLocationService = LocationService.getInstance(this.getApplication());
        setTabHost();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mLocationService.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mLocationService.stop();
    }

    private void setTabHost() {
        mFragmentManager = getSupportFragmentManager();
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, mFragmentManager, R.id.realtabcontent);
        for (int i = 0; i < classFragment.length; i++) {
            View layout = getLayoutInflater().inflate(R.layout.item_home, null);
            ImageView imageView = (ImageView) layout.findViewById(R.id.image_home);
            imageView.setImageResource(image[i]);
            TextView mTextView = (TextView) layout.findViewById(R.id.tv_home);
            mTextView.setText(strText[i]);
            mTabHost.addTab(mTabHost.newTabSpec("tab" + i).setIndicator(layout), classFragment[i], null);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {

            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(this, "再次点击退出", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            }else {
                BaseApplication.getInstance().exit();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private boolean isLogin(){
        return true;
    }
}
