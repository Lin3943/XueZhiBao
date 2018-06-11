package com.zyl.xuezhibao.view.channel;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zyl.xuezhibao.R;
import com.zyl.xuezhibao.base.MyBaseApplication;
import com.zyl.xuezhibao.view.channel.adapter.ChannelAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.zyl.xuezhibao.view.Constant.CHOOSE_VIDEO;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChannelFragment extends Fragment implements View.OnClickListener {

    private long mExitTime = 0;
    private List<String> mList;
    private View mView;
    private ImageButton mBack;
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mRefresh;
    private Context mContext;
    private int mDownRefresh = 1;
    private int mUpLoad = 1;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public ChannelFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_channel, container, false);
            initUI();
            initData();
            initListener();
        }
        return mView;
    }

    protected void initData() {
        permission();
        mList = new ArrayList<>();
        String[] strings = {"基础教育", "高考热门", "财经讲座",
                "健康", "卡通动漫", "军事科技", "体育",
                "热门电视剧", "亲子互动", "十万个为什么"
        };
        mList.addAll(Arrays.asList(strings));

    }

    private void initUI() {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.rcv_channel_scroll_choose);
        mBack = (ImageButton) mView.findViewById(R.id.img_btn_back);

    }

    private void initListener() {
        mBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_btn_back:
                exit();
                break;

        }
    }

    private void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(mContext, "再次点击退出", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            MyBaseApplication.getInstance().exit();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onResume() {
        super.onResume();
        final ChannelAdapter channelAdapter = new ChannelAdapter(mList, mContext);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
        mRecyclerView.setLayoutManager(gridLayoutManager);
//        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(3, 10, false));
        mRecyclerView.setAdapter(channelAdapter);
        channelAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                String string = mList.get(position);
                Intent intent = new Intent(mContext, ChannelChooseActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(CHOOSE_VIDEO, string);
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        //SmartRefreshLayout下拉刷新、上拉加载
        mRefresh = (SmartRefreshLayout) mView.findViewById(R.id.srlt_channel_choose_refresh);
        ClassicsHeader classicsHeader = new ClassicsHeader(mContext);
        mRefresh.setRefreshHeader(classicsHeader);
        mRefresh.setRefreshFooter(new ClassicsFooter(mContext).setSpinnerStyle(SpinnerStyle.Scale));
        mRefresh.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {

                if (mUpLoad > 2) {
//                    mRefresh.setLoadmoreFinished(true);
                    mRefresh.finishLoadmore(1500, false);
                    mUpLoad = 1;
                } else {
                    String string1 = "上拉加载频道 " + mUpLoad;
                    mList.add(string1);
                    channelAdapter.notifyDataSetChanged();
                    mRefresh.setLoadmoreFinished(false);
                    mRefresh.finishLoadmore(1500, true);
                    mUpLoad++;
                }
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                if (mDownRefresh > 2) {
                    mRefresh.finishRefresh(1000,false);
                    mDownRefresh = 1;
                } else {
                    String string2 = "下拉刷新频道 " + mDownRefresh;
                    mList.add(string2);
                    mDownRefresh++;
                    channelAdapter.notifyDataSetChanged();
                    mRefresh.finishRefresh();
                }
            }
        });
    }

    private void permission(){
        if(ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            //没有授权
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }else{
            //已经授权
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 0:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //用户点击了同意授权
                }else{
                    //用户拒绝了授权
                    Toast.makeText(mContext,"权限被拒绝",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}
