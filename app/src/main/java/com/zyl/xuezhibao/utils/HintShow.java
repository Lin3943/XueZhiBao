package com.zyl.xuezhibao.utils;

import android.view.View;

import java.util.List;

/**
 * Created by qwe on 2017/5/2.
 */

public class HintShow {
    public static void setHintShow(List mList, int pageNo, View view, View mListview){
        if (mList.size() == 0 && pageNo == 1){
            view.setVisibility(View.VISIBLE);
            mListview.setVisibility(View.GONE);
        }else {
            view.setVisibility(View.GONE);
            mListview.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 使用java正则表达式去掉多余的.与0
     * @param s
     * @return
     */
    public static String subZeroAndDot(String s){
        if(s.length() > 0 && s.indexOf(".") > 0){
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }

    /**
     * 处理字符串括号乱码
     * @param s
     * @return
     */
    public static String disposeString(String s){
        String name = null;
        if (s != null){
            name = s.replaceAll("&#40;","(");
            name = name.replaceAll("&#41;",")");
        }
        return name;
    }
}
