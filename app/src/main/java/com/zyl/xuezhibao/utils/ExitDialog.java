package com.zyl.xuezhibao.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.zyl.xuezhibao.R;


/**
 * Created by Leo on 2017/7/12.
 */

public class ExitDialog extends Dialog  implements View.OnClickListener{

    private LayoutInflater mInflater;

    private TextView mDialogMessage;

    private OnButtonClickListener mListener;
    private TextView mDialogTitle;
    private View mView;
    private TextView mBtnOk;
    private TextView mBtnCan;


    public ExitDialog(Context context, String title,String message,OnButtonClickListener onButtonClickListener) {
        super(context, R.style.dialog_style);

        initVariable(context);
        initView(title,message);

        mListener = onButtonClickListener;

    }

    private void initVariable(Context context) {

        mInflater = LayoutInflater.from(context);

    }

    private void initView(String title,String message) {
        mView = mInflater.inflate(R.layout.out_dialog, null);

        mDialogTitle = (TextView) mView.findViewById(R.id.dialog_title);
        mDialogMessage = (TextView) mView.findViewById(R.id.dialog_message);
        mBtnOk = (TextView) mView.findViewById(R.id.dialog_cancel);
        mBtnCan = (TextView) mView.findViewById(R.id.dialog_sure);


        mDialogTitle.setText(title);
        mDialogMessage.setText(message);
        mBtnOk.setOnClickListener(this);
        mBtnCan.setOnClickListener(this);
        setContentView(mView);
    }



    public interface OnButtonClickListener {
         void onOk();

         void onCancel();
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_sure: {
                mListener.onOk();
                dismiss();
                break;
            }
            case R.id.dialog_cancel: {
                mListener.onCancel();
                dismiss();
                break;
            }
        }
    }
}
