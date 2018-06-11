package com.zyl.xuezhibao.utils;

import java.text.DecimalFormat;

/**
 * Created by qwe on 2017/11/22.
 */

public class DecimalFormatUtil {
    private static DecimalFormatUtil decimalFormatUtil;
    private DecimalFormat mDecimalFormat;
    private DecimalFormatUtil(){
        mDecimalFormat = new DecimalFormat("#0.00");
    }

    public static DecimalFormatUtil getInstance() {
        if (decimalFormatUtil == null){
            synchronized (DecimalFormatUtil.class) {
                if (decimalFormatUtil == null) {
                    decimalFormatUtil = new DecimalFormatUtil();
                }
            }
        }
        return decimalFormatUtil;
    }

    public String getDecimalFormat(double price){
        return mDecimalFormat.format(price);
    }
}
