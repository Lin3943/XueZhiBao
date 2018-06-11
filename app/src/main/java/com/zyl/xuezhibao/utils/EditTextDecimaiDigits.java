package com.zyl.xuezhibao.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by qwe on 2017/9/21.
 */

public class EditTextDecimaiDigits {
    /**
     * 限制小数位数
     * @param editText
     * @param decimaiDigits 小数位数
     */
    public static void setPointNum(final EditText editText, final int decimaiDigits) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,int count) {
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > decimaiDigits) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + decimaiDigits+1);
                        editText.setText(s);
                        editText.setSelection(s.length());
                    }
                }
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    editText.setText(s);
                    editText.setSelection(2);
                }
                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        editText.setText(s.subSequence(0, 1));
                        editText.setSelection(1);
                        return;
                    }
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}
