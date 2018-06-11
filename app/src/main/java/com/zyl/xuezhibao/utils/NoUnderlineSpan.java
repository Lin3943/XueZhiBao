package com.zyl.xuezhibao.utils;

import android.text.TextPaint;
import android.text.style.UnderlineSpan;

/**
 * 无下划线的Span
 * Created by qwe on 2017/10/24.
 */
public class NoUnderlineSpan extends UnderlineSpan {
    @Override
    public void updateDrawState(TextPaint ds) {
//        ds.setColor(ds.linkColor);
        ds.setUnderlineText(false);
    }
}
