package com.zyl.xuezhibao.view.mine.videoscache;

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
import com.zyl.xuezhibao.utils.ToastUtils;
import com.zyl.xuezhibao.view.FileUtils;
import com.zyl.xuezhibao.view.channel.ChannelDetialActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.zyl.xuezhibao.view.FileUtils.VIDEO_DOWNLAOD_DIR;

public class VideosCacheActivity extends FragmentActivity implements View.OnClickListener{

    private ImageButton mImageButtonBack;//关闭按钮
    private RecyclerView mRecyclerView;
    private LinearLayout mNotVideoCache;
    private LinearLayout mShowVideoCache;
    private List<String> mVideoNameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_cache);
        initView();
        initData();
        initListener();
    }

    public void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rcv_video_cache);
        mNotVideoCache = (LinearLayout) findViewById(R.id.llt_not_video_cache);
        mShowVideoCache = (LinearLayout) findViewById(R.id.llt_show_video_cache);
        mImageButtonBack = (ImageButton) findViewById(R.id.img_btn_back);

    }

    public void initData() {
        mVideoNameList = new ArrayList<String>();
    }

    public void initListener() {
        mImageButtonBack.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //创建文件夹目录
        File file = FileUtils.creatDir(VIDEO_DOWNLAOD_DIR);
        listDirectory(file);
    }

    private void initRecyclerView() {
        if (mVideoNameList != null && mVideoNameList.size()!=0) {
            VideoCacheAdapter videoCacheAdapter = new VideoCacheAdapter(mVideoNameList, this);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
            mRecyclerView.setLayoutManager(gridLayoutManager);
            mRecyclerView.setAdapter(videoCacheAdapter);
            videoCacheAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                    Intent intent = new Intent(VideosCacheActivity.this, ChannelDetialActivity.class);
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
            mNotVideoCache.setVisibility(View.VISIBLE);
            mShowVideoCache.setVisibility(View.GONE);
        }
    }

    /**
     * 遍历dir下的所有文件
     *
     * @param dir 目标文件夹目录
     */
    private void listDirectory(File dir) {
        if (!dir.exists()) {
            ToastUtils.show(this, "目录不存在", 10);
            return;
        }
        if (!dir.isDirectory()) {
            ToastUtils.show(this, "不是一个目录", 10);
            return;
        }
        File files[] = dir.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.isDirectory()) {
                    listDirectory(file);
                } else {
                    //进行文件的处理
                    String filePath = file.getAbsolutePath();
                    //添加
                    mVideoNameList.add(filePath);
                }
            }
            initRecyclerView();
        } else {
            mNotVideoCache.setVisibility(View.VISIBLE);
            mShowVideoCache.setVisibility(View.GONE);
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
