package com.zyl.xuezhibao.view.channel;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.zyl.xuezhibao.R;
import com.zyl.xuezhibao.utils.ToastUtils;

import java.io.File;
import java.lang.ref.WeakReference;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class ChannelDetialActivity extends FragmentActivity implements View.OnClickListener {

    private ImageButton mImageButtonBack;//关闭按钮
    private Button mButtonPre;//上一集按钮
    private Button mButtonNext;//下一集按钮
    private Button mButtonPlay;//播放
    private Button mButtonPause;//暂停
    private VideoView mVideoView;
    private String mPath;
    private long mSize;
    private boolean needResume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_detial);
        initUI();
        initData();
        initListener();
    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        mPath = bundle.getString("VIDEO_PATH");

    }

    private void initListener() {
        mButtonPre.setOnClickListener(this);
        mButtonNext.setOnClickListener(this);
        mButtonPlay.setOnClickListener(this);
        mButtonPause.setOnClickListener(this);
        mImageButtonBack.setOnClickListener(this);
    }

    private void initUI() {
        mVideoView = (VideoView) findViewById(R.id.video_view);
        mButtonPre = (Button) findViewById(R.id.btn_pre_video);
        mButtonNext = (Button) findViewById(R.id.btn_next_video);
        mButtonPlay = (Button) findViewById(R.id.btn_play_video);
        mButtonPause = (Button) findViewById(R.id.btn_pause_video);
        mImageButtonBack = (ImageButton) findViewById(R.id.img_btn_back);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_pre_video:
                break;
            case R.id.btn_next_video:
                break;
            case R.id.btn_play_video:
//                if (!mVideoView.isPlaying()) {
//                    mVideoView.start();
//                    mButtonPlay.setVisibility(View.GONE);
//                    mButtonPause.setVisibility(View.VISIBLE);
//                }else {
//                    mButtonPlay.setVisibility(View.VISIBLE);
//                    mButtonPause.setVisibility(View.GONE);
//                }
                break;
            case R.id.btn_pause_video:
//                if (mVideoView.isPlaying()) {
//                    mVideoView.pause();
//                    mButtonPlay.setVisibility(View.VISIBLE);
//                    mButtonPause.setVisibility(View.GONE);
//                }else {
//                    mButtonPlay.setVisibility(View.GONE);
//                    mButtonPause.setVisibility(View.VISIBLE);
//                }
                break;
            case R.id.img_btn_back:
                mVideoView.suspend();
                finish();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initVideoPlayer();

    }

    private void initVideoPlayer() {
        if (mPath == null) {
            ToastUtils.show(this, "播放地址不存在", 100);
            return;
        } else {
            //播放网络视频地址
            if (
                    mPath.startsWith("http://") ||
                            mPath.startsWith("https://") ||
                            mPath.startsWith("rtsp://") ||
                            mPath.startsWith("mms://")
                    ) {
                Log.e("lintest", " 播放网络视频地址  :");
                mVideoView.setVideoURI(Uri.parse(mPath));
            } else {
                //播放SD卡文件
                File file = new File(mPath);
                if (!file.exists()) {
                    ToastUtils.show(this, "播放文件不存在", 100);
                    return;
                } else {
                    Log.e("lintest", " 播放SD卡文件  :");
                    mVideoView.setVideoPath(mPath);
                }
            }
            //默认的controller
            MediaController controller = new MediaController(this);
            mVideoView.setMediaController(controller);
            mSize = mVideoView.getDuration();
        }
    }

    private void initVideo() {
        if (Vitamio.isInitialized(this)) {
            //默认的controller
            MediaController controller = new MediaController(this);
            mVideoView.setMediaController(controller);
            mVideoView.start();
            //缓冲监听
            mVideoView.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                @Override
                public void onBufferingUpdate(MediaPlayer mp, int percent) {
                    //percent 当前缓冲百分比

                }
            });
            mVideoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                @Override
                public boolean onInfo(MediaPlayer mp, int what, int extra) {
                    switch (what) {
                        case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                            //开始缓存，暂停播放
                            if (mVideoView.isPlaying()) {
                                stopPlayer();
                                needResume = true;
                            }
                            mButtonPause.setVisibility(View.VISIBLE);
                            mButtonPlay.setVisibility(View.GONE);
                            break;
                        case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                            //缓存完成，继续播放
                            if (needResume)
                                startPlayer();
                            mButtonPause.setVisibility(View.GONE);
                            mButtonPlay.setVisibility(View.VISIBLE);
                            break;
                        case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
                            //显示 下载速度
                            break;
                    }
                    return false;
                }
            });
        }
    }

    private void startPlayer() {
    }

    private void stopPlayer() {
    }

    /**
     * handler 防止内存泄露写法
     * WeakReference<ChannelChooseActivity> 弱引用
     */
    private MyHandler handler = new MyHandler(this);
    class MyHandler extends Handler {
        // 弱引用 ，防止内存泄露
        private WeakReference<ChannelDetialActivity> weakReference;

        public MyHandler(ChannelDetialActivity activity) {
            weakReference = new WeakReference<ChannelDetialActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            // 通过  软引用  看能否得到activity示例
            ChannelDetialActivity activity = weakReference.get();
            // 防止内存泄露
            if (activity != null) {
                // 如果当前Activity，进行UI的更新
            } else {
                // 没有实例不进行操作
            }
        }
    }
}

