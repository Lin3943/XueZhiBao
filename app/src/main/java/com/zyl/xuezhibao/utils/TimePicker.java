package com.zyl.xuezhibao.utils;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zyl.xuezhibao.R;
import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.listener.CustomListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by qwe on 2017/6/28.
 */

public class TimePicker {
    private static TimePickerView pvEndTime;
    private static TimePickerView pvStartTime;
    public static void initEndTimePicker(Context context,TimePickerView.OnTimeSelectListener timeSelectListener,
                                         Date date,final String strTitle) {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
//        startDate.set(2010, 1, 28);
        startDate.setTime(date);
        Calendar endDate = Calendar.getInstance();
//        endDate.set(2030, 1, 28);
        endDate.setTime(selectedDate.getTime());
        //时间选择器 ，自定义布局
        pvEndTime = new TimePickerView.Builder(context, timeSelectListener)
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        ImageView ivSubmit = (ImageView) v.findViewById(R.id.image_timepicker_start_sure);
                        ImageView ivCancel = (ImageView) v.findViewById(R.id.image_timepicker_start_cancel);
                        TextView title = (TextView) v.findViewById(R.id.tv_timepicker_start_title);
                        title.setText(strTitle);
                        ivSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvEndTime.returnData();
                                pvEndTime.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvEndTime.dismiss();
                            }
                        });
                    }
                })
                .setType(new boolean[]{true, true, true,false,false,false})
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(Color.GRAY)
                .build();


    }

    public static void initStartTimePicker(Context context, TimePickerView.OnTimeSelectListener timeSelectListener,
                                           final String strTitle) {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(2010, 1, 28);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2030, 1, 28);
        //时间选择器 ，自定义布局
        pvStartTime = new TimePickerView.Builder(context, timeSelectListener)
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        ImageView ivSubmit = (ImageView) v.findViewById(R.id.image_timepicker_start_sure);
                        ImageView ivCancel = (ImageView) v.findViewById(R.id.image_timepicker_start_cancel);
                        TextView title = (TextView) v.findViewById(R.id.tv_timepicker_start_title);
                        title.setText(strTitle);
                        ivSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvStartTime.returnData();
                                pvStartTime.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvStartTime.dismiss();
                            }
                        });
                    }
                })
                .setType(new boolean[]{true, true, true,false,false,false})
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(Color.GRAY)
                .build();

    }

    public static TimePickerView getEndTimePickerView(){
        return pvEndTime;
    }
    public static TimePickerView getStartTimePickerView(){
        return pvStartTime;
    }

    public static String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(date);
    }
}
