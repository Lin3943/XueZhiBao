package com.zyl.xuezhibao.view.find.schools;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zyl.xuezhibao.R;
import com.zyl.xuezhibao.view.find.schools.school.SchoolActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import static com.zyl.xuezhibao.view.Constant.FIND;

public class SchoolsActivity extends FragmentActivity {

    private ImageButton mBack;
    private List<String> mList;
    private RecyclerView mRecyclerView;
    private TextView mTvTitle;
    private SchoolsAdapter mAdapter;
    private SmartRefreshLayout mRefresh;
    private String mMsg;
    private LinearLayout mNotSchoolsNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_psychagogy);
        initUI();
        initData();
    }

    private void initUI() {
        mBack = (ImageButton) findViewById(R.id.img_btn_back);
        mRecyclerView = (RecyclerView) findViewById(R.id.rcv_find_psychagogy);
        mTvTitle = (TextView) findViewById(R.id.tv_find_title);
        mRefresh = (SmartRefreshLayout) findViewById(R.id.srlt_find_psychagogy_refresh);
        mNotSchoolsNow = (LinearLayout) findViewById(R.id.llt_not_psychagogy_now);
    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mMsg = bundle.getString(FIND);
            mTvTitle.setText(mMsg);
        }
        mList = new ArrayList<>();
        mList.add(mMsg);

    }


    private void itemClick() {
        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                gotoActivity(SchoolActivity.class);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    private void initRecylerView(List<String> date) {
        mAdapter = new SchoolsAdapter(date, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        itemClick();
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (mList != null && mList.size() != 0) {
            initRecylerView(mList);
        }else {
            mNotSchoolsNow.setVisibility(View.VISIBLE);
        }
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //SmartRefreshLayout下拉刷新、上拉加载
        mRefresh.setRefreshHeader(new ClassicsHeader(this).setSpinnerStyle(SpinnerStyle.Translate));
        mRefresh.setRefreshFooter(new ClassicsFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
        mRefresh.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mRefresh.finishLoadmore(2000, true);

            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mRefresh.finishRefresh(2000);
            }
        });
    }


    /**
     * handler 防止内存泄露写法
     * WeakReference<ChannelChooseActivity> 弱引用
     */
    private MyHandler handler = new MyHandler(this);

    class MyHandler extends Handler {
        // 弱引用 ，防止内存泄露
        private WeakReference<SchoolsActivity> weakReference;

        public MyHandler(SchoolsActivity activity) {
            weakReference = new WeakReference<SchoolsActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            // 通过  软引用  看能否得到activity示例
            SchoolsActivity activity = weakReference.get();
            // 防止内存泄露
            if (activity != null) {
                // 如果当前Activity，进行UI的更新
            } else {
                // 没有实例不进行操作
            }
        }
    }

    private void gotoActivity(Class<?> class1) {
        Intent intent = new Intent(this, class1);
        startActivity(intent);
    }

}
