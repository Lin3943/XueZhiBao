package com.zyl.xuezhibao.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Maisi on 2016/11/9.
 */

public class RegexUtils {
    /**
     * 提取所有ASCII字符
     * @param content
     * @return
     */
    public static final String getAsciiAll(String content) {
        Pattern pattern = Pattern.compile("[\\x00-\\x7f-[0-9)]]+");
        Matcher matcher = pattern.matcher(content.toString() + "");
        StringBuffer buffer = new StringBuffer();
        while (matcher.find()) {
            String result = matcher.group();
            buffer.append(result);
        }
        return buffer.toString();
    }
}
