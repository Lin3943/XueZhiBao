package com.zyl.xuezhibao.view.mine.history;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zyl.xuezhibao.R;
import com.zyl.xuezhibao.view.channel.ChannelDetialActivity;

import java.util.ArrayList;
import java.util.List;

public class VideosHistoryActivity extends FragmentActivity implements View.OnClickListener{

    private ImageButton mImageButtonBack;//关闭按钮
    private RecyclerView mRecyclerView;
    private LinearLayout mNotVideoHistory;
    private LinearLayout mShowVideoHistory;
    private List<String> mVideoNameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_history);
        initView();
        initData();
        initListener();
    }

    public void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rcv_video_history);
        mNotVideoHistory = (LinearLayout) findViewById(R.id.llt_not_video_history);
        mShowVideoHistory = (LinearLayout) findViewById(R.id.llt_show_video_history);
        mImageButtonBack = (ImageButton) findViewById(R.id.img_btn_back);

    }

    public void initData() {
        mVideoNameList = new ArrayList<>();
    }

    public void initListener() {
        mImageButtonBack.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initRecyclerView();
    }

    private void initRecyclerView() {
        if (mVideoNameList != null && mVideoNameList.size()!=0) {
            VideoHistoryAdapter adapter = new VideoHistoryAdapter(mVideoNameList, this);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
            mRecyclerView.setLayoutManager(gridLayoutManager);
            mRecyclerView.setAdapter(adapter);
            adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                    Intent intent = new Intent(VideosHistoryActivity.this, ChannelDetialActivity.class);
                    String path = mVideoNameList.get(position);
                    intent.putExtra("VIDEO_PATH", path);
                    startActivity(intent);
                }

                @Override
                public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                    return false;
                }
            });
        }else {
            mNotVideoHistory.setVisibility(View.VISIBLE);
            mShowVideoHistory.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_btn_back:
                finish();
                break;
        }
    }
}
