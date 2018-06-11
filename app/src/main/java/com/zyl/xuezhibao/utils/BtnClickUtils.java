package com.zyl.xuezhibao.utils;

/**
 * 按钮重复点击
 */
public class BtnClickUtils {

    /**
     * 默认双击间隔时间
     */
    private static final long DEFAULT_CLICK_TIME = 1000;

    /**
     * 最小双击间隔时间
     */
    private static final long MIN_TIME = 100;

    /**
     * 双击间隔时间
     */
    private long mInterval = 0;

    /**
     * 上一次点击时间
     */
    private long mLastClickTime = 0;

    /**
     * 上一次下拉刷新时间
     */
    private static long mLastRefreshTime = 0;


    public BtnClickUtils()
    {
        mInterval = DEFAULT_CLICK_TIME;
    }

    public BtnClickUtils(long time)
    {
        setInterval(time);
    }

    /**
     * 是否为双击
     * @return true双击了，false未双击。
     */
    public boolean isFastDoubleClick()
    {
        long time = System.currentTimeMillis();
        long timeD = time - mLastClickTime;
        if (0 < timeD && timeD < mInterval)
        {
            return true;
        }
        mLastClickTime = time;
        return false;
    }

    /**
     * 设置双击间隔时间
     * @param time
     */
    public void setInterval(long time)
    {
        if (time < MIN_TIME)
        {
            mInterval = DEFAULT_CLICK_TIME;
        } else
        {
            mInterval = time;
        }
    }

    /**
     * 判断下拉刷新的时间间隔
     * 大于默认时间返回true,小于默认时间返回false
     */
    public static boolean isOverRefreshTime(long defaultTime){
        Long curTime = System.currentTimeMillis() ;
        long time = curTime - mLastRefreshTime;
        mLastRefreshTime = System.currentTimeMillis();
        if (time > defaultTime) {
            return true;
        } else {
            return false;
        }
    }
}