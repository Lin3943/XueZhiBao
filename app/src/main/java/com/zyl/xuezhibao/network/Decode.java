package com.zyl.xuezhibao.network;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

/**
 * Created by Leo on 2017/4/13.
 *
 * 加密解码
 */

public class Decode {

    protected static final String mDefaultCharacterEncode = "UTF-8";
    protected static final int mDefaultBase64Encode = Base64.DEFAULT;
    public static final String NULL_CONTENT = "";

    /**
     * base64 加密  强转为string进行加密 供外部使用
     * @param contentObj
     * @return
     */

    public static String encode(Object contentObj) {
        String inputString = (String) contentObj;
        String encodeString = "";
        encodeString = encodeToBase64String(inputString);
        return encodeString;
    }


    /**
     * 将String转为Base64编码格式String
     *
     * @param stringContent
     * @return 编码成功返回 base64 String， 失败返回null。
     */
    protected static String encodeToBase64String(String stringContent) {
        byte[] utf8Data = null;
        byte[] base64bytes = null;
        String strData = ""; // 最终编码后要送出的资料

        try {
            // 得到UTF-8编码的byte[]
            utf8Data = stringContent.getBytes(mDefaultCharacterEncode);
            // 将byte[]编码为BASE64数据
            base64bytes = Base64.encode(utf8Data, mDefaultBase64Encode);
            // 将编码为BASE64后的数据转成String
            strData = new String(base64bytes, mDefaultCharacterEncode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return strData;
    }




    /**
     * 解码 强转为string进行解码 供外部使用
     */
    public static Object decodeRespone(Object raw) {
        if (raw != null) {
            Object obj = raw;
            String content = obj.toString();
            String resultString = decodeBase64(content);

            if (resultString.length() == 4) {  // 4:空数据乱码字符串的长度resultString.length
                resultString = NULL_CONTENT;
            }
            return resultString;
        }
        return null;
    }



    /**
     * 解码Base64格式数据
     *
     * @param stringContent stringContent
     * @return 解码成功返回解码后的 byte[]数据，失败返回原数据。
     */
    protected static String decodeBase64(String stringContent) {
        String base64Data = "";

        if (stringContent != null) {
            try {
                byte[] baseBytes = Base64.decode(stringContent,
                        mDefaultBase64Encode);

                base64Data = new String(baseBytes, mDefaultCharacterEncode);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                return stringContent;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return stringContent;
            }
        }

        return base64Data;
    }






}
