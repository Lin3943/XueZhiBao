package com.zyl.xuezhibao.utils;

import android.content.Context;
import android.text.TextUtils;

import java.io.Serializable;

public class ConvertUtils implements Serializable {

//    public static float px2mm(float px, DisplayMetrics metrics) {
//        return (25.4f * px) / metrics.xdpi;
//    }
//
//    /**
//     * 将data字节型数据转换为0~255 (0xFF 即BYTE)。
//     *
//     * @param data
//     * @return
//     */
//    public int getUnsignedByte(byte data) {
//        return data & 0x0FF;
//    }
//
//    /**
//     * 将data字节型数据转换为0~65535 (0xFFFF)。
//     *
//     * @param data
//     * @return
//     */
//    public int getUnsignedByte(short data) {
//        return data & 0x0FFFF;
//    }
//
//    /**
//     * 将int数据转换为0~4294967295 (0xFFFFFFFF)。
//     *
//     * @param data
//     * @return
//     */
//    public long getUnsignedInt(int data) {
//        return data & 0x0FFFFFFFFl;
//    }

    /**
     * 将bytes数组中指定的2个字节转成int数值
     *
     * @param src 两字节的byte[]，长度必须为2
     * @return 转换失败返回-1
     */
    synchronized public int get2ByteToInt(final byte[] src) {
        try {
            int value = 0;
            value = ((src[0] & 0xff) << 8) | value;
            value = (src[1] & 0xff) | value;
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 将bytes数组中指定长度的字节转成int数值
     *
     * @param src
     * @param srcPost
     * @param len
     * @return
     */
    synchronized public int getBytesToInt(final byte[] src, final int srcPost, final int len) {
        int value = 0;
        byte[] bytes = new byte[len];
        byte[] srccopy = src;
        try {
            System.arraycopy(srccopy, srcPost, bytes, 0, len);
        } catch (Exception e) {
            // TODO crash debug
            e.printStackTrace();
            return -1;
        }

        try {
            for (int i = (len < 3 ? len - 1 : 3); i >= 0; i--) {
                value = ((bytes[i] & 0xff) << (24 - (8 * i))) | value;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }


        return value;
    }

    /**
     * 将int数值转换为byte数组
     *
     * @return byte[]
     */
    public byte[] getIntToBytes(int n) {
        int len = 4; // 四个byte来存储int
        byte[] b = new byte[len];
        for (int i = 0; i < len; i++) {
            b[i] = (byte) (n >> (24 - i * 8));
        }
        return b;
    }

    public byte[] getIntArrayToBytes(int[] src) {
        byte[] data = new byte[src.length * 4];
        int pos = 0;
        for (int val : src) {
            System.arraycopy(getIntToBytes(val), 0, data, pos, 4);
            pos += 4;
        }

        return data;
    }

    /**
     * 将byte数组转成对应的十六进制字符串
     *
     * @param b
     * @return
     */
    synchronized public String getBytesToHexString(final byte[] b) {
        if (b == null) {
            return "";
        }

        String ret = "";
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = "0" + hex;
            }
            ret += hex.toUpperCase();
        }
        return ret;
    }

//    /**
//     * 适用读取2个字节的数据
//     *
//     * @param b
//     * @return
//     */
//    @SuppressLint("DefaultLocale")
//    public int byte2Int(byte[] b) {
//        int intValue = 0;
//        for (int i = 3; i > 1; i--) {
//            intValue += (b[i] & 0xFF) << (8 * (i - 2));
//        }
//        return intValue;
//    }
//
//    /**
//     * 适用写入2个字节的数据
//     *
//     * @return
//     */
//    public byte[] int2Byte(int value) {
//        byte[] b = new byte[2];
//        b[1] = (byte) (value >> 8 & 0xFF);
//        b[0] = (byte) (value & 0xFF);
//        return b;
//    }

//    /**
//     * 整型转换为4位字节数组
//     *
//     * @return
//     */
//    public byte[] int2Byte4bit(int value) {
//        byte[] b = new byte[4];
//        b[3] = (byte) (value >> 24 & 0xFF);
//        b[2] = (byte) (value >> 16 & 0xFF);
//        b[1] = (byte) (value >> 8 & 0xFF);
//        b[0] = (byte) (value & 0xFF);
//        return b;
//    }

//    /**
//     * 4位字节数组转换为整型
//     *
//     * @param b
//     * @return
//     */
//    public int byte2Int4bit(byte[] b) {
//        int intValue = 0;
//        for (int i = 5; i > 1; i--) {
//            intValue += (b[i] & 0xFF) << (8 * (i - 2));
//        }
//        return intValue;
//    }

    /**
     * eg:1.0002000->1.0002
     *
     * @param str
     * @return
     */
    public static final String removeZero(String str) {
        // 空值处理
        if (TextUtils.isEmpty(str)) {
            return "";
        }

        // 如果已经是整数
        if (!str.contains(".")) {
            return str;
        }

        // 小数处理
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            sb.append(c);
        }

        char zero = "0".charAt(0);
        char dot = ".".charAt(0);

        for (int i = sb.length() - 1; i >= 0; i--) {
            // 如果最后一位是0
            if (zero == sb.charAt(i)) {
                sb.deleteCharAt(i);
                continue;
            }
            // 如果最后一位是.
            else if (dot == sb.charAt(i)) {
                sb.deleteCharAt(i);
                break;
            }

            // 最后一位是非0
            break;
        }
        return sb.toString();
    }

    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     *
     * @param pxValue （DisplayMetrics类中属性density）
     * @return
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue （DisplayMetrics类中属性density）
     * @return
     */
    public static int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * Float类型数值转为Double类型数值
     *
     * @param value
     * @return double类型数值
     */
    public static Double floatToDouble(float value) {
        return Double.parseDouble(Float.toString(value));
    }

    /**
     * 把重量值去掉小数点
     *
     * @param var
     * @return
     */
    public static String reserveInteger(String var) {
        String[] split = var.split("\\.");
        if (split.length > 0) {
            return split[0];
        }
        return var;
    }

    public static float strToFloat(String value) {
        try {
            return Float.parseFloat(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0f;
    }

    public static int strToInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
