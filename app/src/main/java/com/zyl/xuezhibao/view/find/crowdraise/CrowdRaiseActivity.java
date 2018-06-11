package com.zyl.xuezhibao.view.find.crowdraise;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zyl.xuezhibao.R;

import java.util.ArrayList;
import java.util.List;

import static com.zyl.xuezhibao.view.Constant.FIND;

public class CrowdRaiseActivity extends FragmentActivity implements View.OnClickListener,ViewPager.OnPageChangeListener {

    private ImageButton mBack;
    private TextView mTvTitle;
    private TextView tv_one;
    private TextView tv_two;
    private String mMsg;
    private ArrayList<View> listViews;
    private ViewPager mViewPager;
    private RecyclerView mRcvVPone;
    private RecyclerView mRcvVPtwo;
    private LinearLayout mNotCrowdRaiseNow;
    private LinearLayout mNotCrowdRaisePre;
    private List<String> mCrowdRaiseNowList;
    private List<String> mCrowdRaisePreList;
    private View mViewOne;
    private View mViewTwo;
    private ImageView img_cursor;
    private int offset = 0;//移动条图片的偏移量
    private int currIndex = 0;//当前页面的编号
    private int bmpWidth;// 移动条图片的长度
    private int one = 0; //移动条滑动一页的距离
//    private int two = 0; //滑动条移动两页的距离

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_crowdraise);
        initUI();
        initData();
        initListener();
    }

    private void initUI() {
        mBack = (ImageButton) findViewById(R.id.img_btn_back);
        mTvTitle = (TextView) findViewById(R.id.tv_crowdraise_title);
        tv_one = (TextView) findViewById(R.id.tv_one);
        tv_two = (TextView) findViewById(R.id.tv_two);
        mViewPager = (ViewPager) findViewById(R.id.vp_find_crowdraise);
        //往ViewPager填充View，同时设置点击事件与页面切换事件
        listViews = new ArrayList<View>();
        LayoutInflater mInflater = getLayoutInflater();
        mViewOne = mInflater.inflate(R.layout.vp_crowdraise_one, null, false);
        mViewTwo = mInflater.inflate(R.layout.vp_crowdraise_two, null, false);
        listViews.add(mViewOne);
        listViews.add(mViewTwo);
        mViewPager.setAdapter(new VPListViewAdapter(listViews));
        mViewPager.setCurrentItem(0);          //设置ViewPager当前页，从0开始算
        if (listViews != null && listViews.size() != 0) {
            setVP();
        }
        setOffset();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int index) {
        Animation animation = null;
        switch (index) {
            case 0:
                tv_one.setTextColor(Color.rgb(0,255,0));
                tv_two.setTextColor(Color.rgb(255,255,255));
                if (currIndex == 1) {
                    animation = new TranslateAnimation(one, 0, 0, 0);
                }
//                else if (currIndex == 2) {
//                    animation = new TranslateAnimation(two, 0, 0, 0);
//                }
                break;
            case 1:
                tv_one.setTextColor(Color.rgb(255,255,255));
                tv_two.setTextColor(Color.rgb(0,255,0));
//                tv_two.setTextColor(0x00ff00);
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, one, 0, 0);
                }
//                else if (currIndex == 2) {
//                    animation = new TranslateAnimation(two, one, 0, 0);
//                }
                break;
//            case 2:
//                if (currIndex == 0) {
//                    animation = new TranslateAnimation(offset, two, 0, 0);
//                } else if (currIndex == 1) {
//                    animation = new TranslateAnimation(one, two, 0, 0);
//                }
//                break;
        }
        currIndex = index;
        animation.setFillAfter(true);// true表示图片停在动画结束位置
        animation.setDuration(300); //设置动画时间为300毫秒
        img_cursor.startAnimation(animation);//开始动画
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_one:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.tv_two:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.img_btn_back:
                finish();
                break;
        }
    }

    private void initData() {
        mCrowdRaisePreList = new ArrayList<>();
        mCrowdRaiseNowList = new ArrayList<>();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mMsg = bundle.getString(FIND);
            mTvTitle.setText(mMsg);
        }
        mCrowdRaisePreList.add(mMsg);
    }

    private void initListener() {
        tv_one.setOnClickListener(this);
        tv_two.setOnClickListener(this);
        mBack.setOnClickListener(this);
        mViewPager.addOnPageChangeListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initRecycleView();
    }

    private void initRecycleView() {
        if (mCrowdRaiseNowList != null && mCrowdRaiseNowList.size() != 0) {
            CrowdRaiseOneAdapter adapter = new CrowdRaiseOneAdapter(mCrowdRaiseNowList, this);
            LinearLayoutManager gridLayoutManager = new LinearLayoutManager(this);
            mRcvVPone.setLayoutManager(gridLayoutManager);
            mRcvVPone.setAdapter(adapter);
        } else {
            mNotCrowdRaiseNow.setVisibility(View.VISIBLE);
        }
        if (mCrowdRaisePreList != null && mCrowdRaisePreList.size() != 0) {
            CrowdRaiseTwoAdapter adapter = new CrowdRaiseTwoAdapter(mCrowdRaisePreList, this);
            LinearLayoutManager gridLayoutManager = new LinearLayoutManager(this);
            mRcvVPtwo.setLayoutManager(gridLayoutManager);
            mRcvVPtwo.setAdapter(adapter);
        } else {
            mNotCrowdRaisePre.setVisibility(View.VISIBLE);
        }
    }

    private void setVP() {
        mRcvVPone = (RecyclerView) mViewOne.findViewById(R.id.rcy_crowdraise_1);
        mRcvVPtwo = (RecyclerView) mViewTwo.findViewById(R.id.rcy_crowdraise_2);
        mNotCrowdRaiseNow = (LinearLayout) mViewOne.findViewById(R.id.llt_not_crowdraise_now);
        mNotCrowdRaisePre = (LinearLayout) mViewTwo.findViewById(R.id.llt_not_crowdraise_pre);
    }

    private void setOffset() {
        //下划线动画的相关设置：
        img_cursor = (ImageView) findViewById(R.id.img_cursor);
        bmpWidth = BitmapFactory.decodeResource(getResources(), R.drawable.line).getWidth();// 获取图片宽度
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度
//        offset = (screenW / 3 - bmpWidth) / 2;// 计算偏移量
        offset = (screenW / 2 - bmpWidth) / 2;// 计算偏移量
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        img_cursor.setImageMatrix(matrix);// 设置动画初始位置
        //移动的距离
        one = offset * 2 + bmpWidth;// 移动一页的偏移量,比如1->2,或者2->3
//        two = one * 2;// 移动两页的偏移量,比如1直接跳3
    }


}
