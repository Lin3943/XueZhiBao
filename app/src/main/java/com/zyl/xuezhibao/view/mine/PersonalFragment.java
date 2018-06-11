package com.zyl.xuezhibao.view.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zyl.xuezhibao.R;
import com.zyl.xuezhibao.base.MyBaseApplication;
import com.zyl.xuezhibao.view.LoginXZBActivity;
import com.zyl.xuezhibao.view.mine.history.VideosHistoryActivity;
import com.zyl.xuezhibao.view.mine.videocollection.VideosCollectionActivity;
import com.zyl.xuezhibao.view.mine.videoscache.VideosCacheActivity;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalFragment extends Fragment implements View.OnClickListener{

    private long mExitTime = 0;
    private List<String> messageList;
    private View mView;
    private TextView mTvCityName;
    private ImageView mIvLogin;
    private ImageButton mBack;
    private Context mContext;
    private LinearLayout mCache;
    private LinearLayout mCollection;
    private LinearLayout mHistory;
    private LinearLayout mSetting;
    private LinearLayout mHelp;
    private LinearLayout mLogout;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public PersonalFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null){
            mView = inflater.inflate(R.layout.fragment_personal, container, false);
            initUI();
            initData();
            initListener();
        }
        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    protected void initData() {

    }

    private void initUI() {
        mIvLogin = (ImageView) mView.findViewById(R.id.iv_login_head_portrait);
        mCache = (LinearLayout) mView.findViewById(R.id.llt_mine_cache);
        mCollection = (LinearLayout) mView.findViewById(R.id.llt_mine_collection);
        mHistory = (LinearLayout) mView.findViewById(R.id.llt_mine_history);
        mSetting = (LinearLayout) mView.findViewById(R.id.llt_mine_setting);
        mHelp = (LinearLayout) mView.findViewById(R.id.llt_mine_help);
        mLogout = (LinearLayout) mView.findViewById(R.id.llt_mine_exit);
        mBack = (ImageButton) mView.findViewById(R.id.img_btn_back);
    }

    private void initListener() {
        mIvLogin.setOnClickListener(this);
        mCache.setOnClickListener(this);
        mCollection.setOnClickListener(this);
        mHistory.setOnClickListener(this);
        mSetting.setOnClickListener(this);
        mHelp.setOnClickListener(this);
        mLogout.setOnClickListener(this);
        mBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_btn_back:
                exit();
                break;
            case R.id.iv_login_head_portrait:
                gotoActivity(LoginXZBActivity.class);
                break;
            case R.id.llt_mine_cache:
                gotoActivity(VideosCacheActivity.class);
                break;
            case R.id.llt_mine_collection:
                gotoActivity(VideosCollectionActivity.class);
                break;
            case R.id.llt_mine_history:
                gotoActivity(VideosHistoryActivity.class);
                break;
            case R.id.llt_mine_setting:
                break;
            case R.id.llt_mine_help:
                break;
            case R.id.llt_mine_exit:
                break;
        }
    }

    private void showSDCache() {

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    /**
     * 跳转具体页面
     */
    private void gotoActivity(Class<?> class1) {
        Intent intent = new Intent(mContext, class1);
        startActivity(intent);
    }

    private void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(mContext, "再次点击退出", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            MyBaseApplication.getInstance().exit();
        }
    }
}
