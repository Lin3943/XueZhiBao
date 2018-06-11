package com.zyl.xuezhibao.widget;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.lcodecore.tkrefreshlayout.IBottomView;
import com.lcodecore.tkrefreshlayout.IHeaderView;
import com.lcodecore.tkrefreshlayout.OnAnimEndListener;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.lcodecore.tkrefreshlayout.IHeaderView;
import com.lcodecore.tkrefreshlayout.OnAnimEndListener;
import com.lcodecore.tkrefreshlayout.R;

/**
 * Created by lcodecore on 2016/10/2.
 */

public class RefreshLoadView extends FrameLayout implements IBottomView {

    private ImageView refreshArrow;
    private ImageView loadingView;
    private TextView refreshTextView;

    public RefreshLoadView(Context context) {
        this(context, null);
    }

    public RefreshLoadView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshLoadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View rootView = View.inflate(getContext(), com.lcodecore.tkrefreshlayout.R.layout.view_sinaheader, null);
        refreshArrow = (ImageView) rootView.findViewById(com.lcodecore.tkrefreshlayout.R.id.iv_arrow);
        refreshTextView = (TextView) rootView.findViewById(com.lcodecore.tkrefreshlayout.R.id.tv);
        loadingView = (ImageView) rootView.findViewById(com.lcodecore.tkrefreshlayout.R.id.iv_loading);
        addView(rootView);
    }
    public void setArrowResource(@DrawableRes int resId) {
        refreshArrow.setImageResource(resId);
    }

    public void setTextColor(@ColorInt int color) {
        refreshTextView.setTextColor(color);
    }

    public void setPullDownStr(String pullDownStr1) {
        pullDownStr = pullDownStr1;
    }

    public void setReleaseRefreshStr(String releaseRefreshStr1) {
        releaseRefreshStr = releaseRefreshStr1;
    }

    public void setRefreshingStr(String refreshingStr1) {
        refreshingStr = refreshingStr1;
    }

    private String pullDownStr = "上拉加载";
    private String releaseRefreshStr = "释放加载";
    private String refreshingStr = "正在加载";


    @Override
    public View getView() {
        return this;
    }

    @Override
    public void onPullingUp(float fraction, float maxBottomHeight, float bottomHeight) {
        if (fraction < 1f) refreshTextView.setText(pullDownStr);
        if (fraction > 1f) refreshTextView.setText(releaseRefreshStr);
        refreshArrow.setRotation(fraction * bottomHeight / maxBottomHeight * 180);
    }

    @Override
    public void startAnim(float maxBottomHeight, float bottomHeight) {
        refreshTextView.setText(refreshingStr);
        refreshArrow.setVisibility(GONE);
        loadingView.setVisibility(VISIBLE);
        ((AnimationDrawable) loadingView.getDrawable()).start();
    }

    @Override
    public void onPullReleasing(float fraction, float maxBottomHeight, float bottomHeight) {
        if (fraction < 1f) {
            refreshTextView.setText(pullDownStr);
            refreshArrow.setRotation(fraction * bottomHeight / maxBottomHeight * 180);
            if (refreshArrow.getVisibility() == GONE) {
                refreshArrow.setVisibility(VISIBLE);
                loadingView.setVisibility(GONE);
            }
        }
    }

    @Override
    public void onFinish() {
    }

    @Override
    public void reset() {
        refreshArrow.setVisibility(VISIBLE);
        loadingView.setVisibility(GONE);
        refreshTextView.setText(pullDownStr);
    }
}
