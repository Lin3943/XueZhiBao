package com.zyl.xuezhibao.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * @ explain:这个ScrlloView不拦截水平滑动事件，
 * 是用来解决 ScrollView里面嵌套ViewPager使用的
 */
public class VerticalScrollView extends ScrollView  {

    public VerticalScrollView(Context context) {
        super(context);
    }

    public VerticalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VerticalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //方便测试先固定。
    private int maxHeight = 464;
//    private RecyclerView mRecyclerView;
    private float mDownPosX = 0;
    private float mDownPosY = 0;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final float x = ev.getX();
        final float y = ev.getY();

        final int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mDownPosX = x;
                mDownPosY = y;

                break;
            case MotionEvent.ACTION_MOVE:
                final float deltaX = Math.abs(x - mDownPosX);
                final float deltaY = Math.abs(y - mDownPosY);
                if (deltaX > 0) {
                    return false;
                }
        }

        return super.onInterceptTouchEvent(ev);
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }


//
//    //返回true代表父view消耗滑动速度，子View将不会滑动
//    @Override
//    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
//        if (null == mRecyclerView) mRecyclerView = (RecyclerView) target;
//        if (mRecyclerView.computeVerticalScrollOffset() != 0) {
//            return false;
//        }
//        this.fling((int) velocityY);
//        return true;
//    }
//
//    //对应子view 的dispatchNestedPreScroll方法， 最后一个数组代表消耗的滚动量，下标0代表x轴，下标1代表y轴
//    @Override
//    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
//        //判断是否滚动到最大值
//        if ( dy >= 0 && this.getScrollY() < maxHeight) {
//            if (null == mRecyclerView) mRecyclerView = (RecyclerView) target;
//            //计算RecyclerView的偏移量， 等于0的时候说明recyclerView没有滑动，否则应该交给recyclerView自己处理
//            if (mRecyclerView.computeVerticalScrollOffset() != 0) return;
//            this.smoothScrollBy(dx, dy);
//            consumed[1] = dy; //consumed[1]赋值为 dy ，代表父类已经消耗了改滚动。
//        }
//    }
}