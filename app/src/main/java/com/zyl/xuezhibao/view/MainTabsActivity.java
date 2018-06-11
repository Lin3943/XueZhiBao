package com.zyl.xuezhibao.view;

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
import com.zyl.xuezhibao.base.MyBaseApplication;
import com.zyl.xuezhibao.bean.VideoInfo;
import com.zyl.xuezhibao.view.channel.ChannelFragment;
import com.zyl.xuezhibao.view.index.IndexFragment;
import com.zyl.xuezhibao.view.mine.PersonalFragment;
import com.zyl.xuezhibao.view.find.FindFragment;

import java.util.ArrayList;
import java.util.List;

import static com.zyl.xuezhibao.view.FileUtils.VIDEO_DOWNLAOD_DIR;

public class MainTabsActivity extends FragmentActivity {

    private FragmentTabHost mTabHost;
    private List<VideoInfo> mVideoInfos;
    private long exitTime = 0;

    private Class classFragment[] = new Class[] {
            IndexFragment.class,
            FindFragment.class,
            ChannelFragment.class,
            PersonalFragment.class
    };

    private int image[] = new int[] {
            R.drawable.selector_btn_home_home,
            R.drawable.selector_btn_home_nearby,
            R.drawable.selector_btn_home_shopping_cart,
            R.drawable.selector_btn_home_mine
    };

    private String strText[] = new String[] {"首页", "发现", "频道","我的" };
//    private LocationService mLocationService;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tabs);
        initUI();
        //版本更新查询
//        UpgradeUtils.initVersion(this);

//        setTabIsLoginOnClickListener(0);
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
//                Globals.log("log 切换监听tabId",tabId);
                switch (tabId){
                    //隐藏MarketMineFragment页的返回键
                    case "tab3":
//                        mineFragment = (MarketMineFragment) mFragmentManager.findFragmentByTag("tab3");
//                        if (mineFragment == null) {
//                            //为空时需要延迟一会
//                            new Handler().postDelayed(new Runnable() {
//                                @Override
//                                public void run() {
//                                    mineFragment = (MarketMineFragment) mFragmentManager.findFragmentByTag("tab3");
//                                    mineFragment.setBackVisibility();
//                                }
//                            }, 50);
//                        }else {
//                            mineFragment.setBackVisibility();
//                        }
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
//        mLocationService = LocationService.getInstance(this.getApplication());
        setTabHost();
    }

    @Override
    protected void onStart() {
        super.onStart();
//        mLocationService.start();
        initTestData();
    }

    /*
    构造测试数据
     */
    public List<VideoInfo> getVideoInfos() {
        return mVideoInfos;
    }

    private void initTestData() {
        mVideoInfos = new ArrayList<>();
        VideoInfo info = new VideoInfo();
        info.setCollection(false);
        info.setPlayed(false);
        info.setVideoImageUrl(null);
        info.setVideoIntro("inuyasha");
        info.setVideoName("犬夜叉");
        String url_0 = "http://7sbsl4.com1.z0.glb.clouddn.com/gogogo.mp4";
        info.setVideoUrl(url_0);
        info.setVideoCacheUrl(null);
        info.setVideoNum(10000);
        mVideoInfos.add(info);
        VideoInfo info1 = new VideoInfo();
        info1.setCollection(false);
        info1.setPlayed(false);
        info1.setVideoImageUrl(null);
        info1.setVideoIntro("natsu");
        info1.setVideoName("妖精的尾巴");
        String url_1 = "http://www.modrails.com/videos/passenger_nginx.mov";
        info1.setVideoUrl(url_1);
        info1.setVideoCacheUrl(null);
        info1.setVideoNum(10001);
        mVideoInfos.add(info1);
        VideoInfo info2 = new VideoInfo();
        info2.setCollection(false);
        info2.setPlayed(false);
        info2.setVideoImageUrl(null);
        info2.setVideoIntro("luffy");
        info2.setVideoName("海贼王");
        String url_2 = VIDEO_DOWNLAOD_DIR + "11.mov";
        info2.setVideoCacheUrl(url_2);
        info2.setVideoNum(10002);
        mVideoInfos.add(info2);
        VideoInfo info3 = new VideoInfo();
        info3.setCollection(false);
        info3.setPlayed(false);
        info3.setVideoImageUrl(null);
        info3.setVideoIntro("简介简介");
        info3.setVideoName("测试测试");
        String url_3 =  VIDEO_DOWNLAOD_DIR + "avitest.avi";
        info3.setVideoCacheUrl(url_3);
        info3.setVideoNum(10003);
        mVideoInfos.add(info3);

    }

    @Override
    protected void onStop() {
        super.onStop();
//        mLocationService.stop();
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
                MyBaseApplication.getInstance().exit();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private boolean isLogin(){
//        if (mSharePerence.getMemberInfo() == null){-
//            startActivity(new Intent(this,LoginActivity.class));
//            finish();
//            return false;
//        }
        return true;
    }
}
