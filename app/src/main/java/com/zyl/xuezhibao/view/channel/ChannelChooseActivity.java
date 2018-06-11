package com.zyl.xuezhibao.view.channel;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.arialyy.annotations.Download;
import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.download.DownloadTask;
import com.arialyy.aria.core.inf.AbsEntity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zyl.xuezhibao.R;
import com.zyl.xuezhibao.utils.ToastUtils;
import com.zyl.xuezhibao.view.channel.adapter.ChannelDetialAdapter;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import static com.zyl.xuezhibao.view.Constant.CHOOSE_VIDEO;

public class ChannelChooseActivity extends FragmentActivity {

    private Button mPlay;
    private ImageButton mBack;
    private List<String> mUrlList;
    private List<AbsEntity> mAbsEntityList;
    private RecyclerView mRecyclerView;
    private ChannelDetialAdapter mAdapter;
    private SmartRefreshLayout mRefresh;
    private String mChooseVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_choose);
        Aria.download(this).register();//注册Aria
        initUI();
        initData();
    }

    private void initUI() {
        mPlay = (Button) findViewById(R.id.btn_play_channel);
        mBack = (ImageButton) findViewById(R.id.img_btn_back);
        mRecyclerView = (RecyclerView) findViewById(R.id.rcv_channel_detial);
        mRefresh = (SmartRefreshLayout) findViewById(R.id.srlt_channel_detail_refresh);
    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        mChooseVideo = bundle.getString(CHOOSE_VIDEO);

    }

    /**
     * 先获取videoInfo实体类里面的url，再将url添加进list<AbsEntity>,
     *
     * @param string
     */
    private void getDate(String string) {
        String url_1 = "http://ugcws.video.gtimg.com/" +
                "j0645kmsadl.p712.1.mp4?sdtfrom=v1105&guid" +
                "=d1df6112f97d802289aba33a0a1659e9&vkey=" +
                "2DDE758E65BCACF95E7FDF03AB893F7D865976" +
                "599D1CAB281F9C51D0C581DF633D6DA9661E97" +
                "AC98B73900D7DA6A1A9C67F52DBC7F4B48CD065" +
                "34800A6EE78A72BFF17A5334B56A784C6C4B4BF2" +
                "84158C67BEEA0EA2C8C6711C62DC2A8C67E086A72" +
                "6BD2CC55E7F60D406DA810E122692041E66041367442";
        String url_2 = "http://7sbsl4.com1.z0.glb.clouddn.com/gogogo.mp4";
        String url_3 = "http://www.modrails.com/videos/passenger_nginx.mov";
        String url_4 = "http://devimages.apple.com/iphone/samples/bipbop/bipbopall.m3u8";
        String url_5 = "http://static.zqgame.com/html/playvideo.html?name=http://lom.zqgame.com/v1/video/LOM_Promo~2.flv";
        String url_6 = "http://bsyqncdn.miaopai.com/stream/t~gB32Ha~0TyT3~Uju8bqQ___16.mp4?ssig=7ba60e9a81f793906db0288aa31758f9&time_stamp=1526268351142";
//        String url_2 = "http://flv2.bn.netease.com/videolib3/1604/28/fVobI0704/SD/fVobI0704-mobile.mp4";
        mUrlList = new ArrayList<>();
        mAbsEntityList = new ArrayList<>();
        mUrlList.add(url_1);
        mUrlList.add(url_2);
        mUrlList.add(url_3);
        mUrlList.add(url_4);
        mUrlList.add(url_5);
        mUrlList.add(url_6);
        for (String url : mUrlList) {
            AbsEntity absEntity = Aria.download(this).load(url).getDownloadEntity();
            if (absEntity != null) {
                absEntity.setStr(url);
                mAbsEntityList.add(absEntity);
            }
        }
        initRecylerView(mAbsEntityList);
    }

    private void chooseVideo() {
        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                switch (position) {
                    case 0:
                        File mp4file = new File(Environment.getExternalStorageDirectory(), "/Movies/mp4test.mp4");
                        String mp4filePath = mp4file.getPath();
//                        String mp4filePath = "http://devimages.apple.com/iphone/samples/bipbop/bipbopall.m3u8";
                        Log.e("lintest", " mp4filePath  :" + mp4filePath);
                        initVideoPath(mp4filePath);
                        ToastUtils.show(ChannelChooseActivity.this, "mp4", 1000);
                        break;
                    case 1:
                        File movfile = new File(Environment.getExternalStorageDirectory(), "/Movies/movtest.mov");
//                        String movfilePath = "http://www.modrails.com/videos/passenger_nginx.mov";
                        String movfilePath = movfile.getPath();
                        Log.e("lintest", " movfilePath  :" + movfilePath);
                        initVideoPath(movfilePath);
                        break;
                    case 2:
                        File gpfile = new File(Environment.getExternalStorageDirectory(), "/Movies/3gptest.3gp");
//                        String gpfilePath = "rtsp://xgrammyawardsx.is.livestream-api.com/livestreamiphone/grammyawards";
                        String gpfilePath = gpfile.getPath();
                        Log.e("lintest", " gpfilePath  :" + gpfilePath);
                        initVideoPath(gpfilePath);
                        break;
                    case 3:
                        File mpgfile = new File(Environment.getExternalStorageDirectory(), "/Movies/mpgtest.mpg");
//                        String mpgfilePath = "mms://ting.mop.com/mopradio";
                        String mpgfilePath = mpgfile.getPath();
                        Log.e("lintest", " mpgfilePath  :" + mpgfilePath);
                        initVideoPath(mpgfilePath);
                        break;
                    case 4:
                        File avifile = new File(Environment.getExternalStorageDirectory(), "/Movies/avitest.avi");
                        String avifilePath = avifile.getPath();
                        Log.e("lintest", " avifilePath  :" + avifilePath);
                        initVideoPath(avifilePath);
                        break;
                    case 5:
                        File wmvfile = new File(Environment.getExternalStorageDirectory(), "/Movies/wmvtest.wmv");
                        String wmvfilePath = wmvfile.getPath();
                        Log.e("lintest", " wmvfilePath  :" + wmvfilePath);
                        initVideoPath(wmvfilePath);
                        break;
                    case 6:
                        String url = "http://7sbsl4.com1.z0.glb.clouddn.com/gogogo.mp4";
                        Log.e("lintest", " url  :" + url);
                        initVideoPath(url);
                        break;
                }

            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    private void initRecylerView(List<AbsEntity> date) {
        mAdapter = new ChannelDetialAdapter(date, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        chooseVideo();
    }

    private void initVideoPath(String path) {
        Intent intent = new Intent(this, ChannelDetialActivity.class);
        intent.putExtra("VIDEO_PATH", path);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        getDate(mChooseVideo);
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
        private WeakReference<ChannelChooseActivity> weakReference;

        public MyHandler(ChannelChooseActivity activity) {
            weakReference = new WeakReference<ChannelChooseActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            // 通过  软引用  看能否得到activity示例
            ChannelChooseActivity activity = weakReference.get();
            // 防止内存泄露
            if (activity != null) {
                // 如果当前Activity，进行UI的更新
            } else {
                // 没有实例不进行操作
            }
        }
    }

    @Download.onTaskRunning
    protected void running(DownloadTask task) {
        long len = task.getFileSize();
        mAdapter.setProgress(task.getEntity());
//        if (len == 0) {
////            getBinding().setProgress(0);
//            mProgressButton.setProgress(0);
//        } else {
////            getBinding().setProgress(task.getPercent());
//            mProgressButton.setProgress(task.getPercent());
//        }
////        getBinding().setSpeed(task.getConvertSpeed());
    }

    @Download.onTaskComplete
    void taskComplete(DownloadTask task) {
        Log.e("lintest", "  onTaskComplete :" + task.getFileSize());
        mAdapter.updateState(task.getEntity());
//        mAdapter.overLoad(task.getEntity());
//        getBinding().setProgress(100);
//        mProgressButton.setProgress(100);
//        mProgressButton.setText("完 成");
//        getBinding().setSpeed("");
    }

    @Download.onWait
    void onWait(DownloadTask task) {
        Log.e("lintest", "  onWait :" + task.getFileSize());
        mAdapter.updateState(task.getEntity());
    }

    @Download.onPre
    protected void onPre(DownloadTask task) {
        mAdapter.updateState(task.getEntity());
//        setBtState(false);
    }

    @Download.onTaskStart
    void taskStart(DownloadTask task) {
        mAdapter.updateState(task.getEntity());
//        getBinding().setFileSize(task.getConvertFileSize());
    }

    @Download.onTaskResume
    void taskResume(DownloadTask task) {
        mAdapter.updateState(task.getEntity());
//        mProgressButton.setText("暂停");
//        setBtState(false);
    }

    @Download.onTaskStop
    void taskStop(DownloadTask task) {
        mAdapter.updateState(task.getEntity());
    }

    @Download.onTaskCancel
    void taskCancel(DownloadTask task) {
        mAdapter.updateState(task.getEntity());
    }

    @Download.onTaskFail
    void taskFail(DownloadTask task) {
        mAdapter.updateState(task.getEntity());
        Toast.makeText(this, "下载失败", Toast.LENGTH_SHORT).show();
    }

    @Download.onNoSupportBreakPoint
    public void onNoSupportBreakPoint(DownloadTask task) {
        mAdapter.updateState(task.getEntity());
        Toast.makeText(this, "该下载链接不支持断点", Toast.LENGTH_SHORT).show();
    }
}
