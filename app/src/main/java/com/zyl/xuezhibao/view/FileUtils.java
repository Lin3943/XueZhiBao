package com.zyl.xuezhibao.view;

import android.os.Environment;

import java.io.File;

public class FileUtils {
    public final static String SD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
    public final static String VIDEO_DOWNLAOD_DIR_NAME = "/XueZhiBao/Video";
    public final static String VIDEO_DOWNLAOD_DIR = SD_PATH + VIDEO_DOWNLAOD_DIR_NAME;
    /**
     *
     * @param path 文件夹路径
     */
    public static File creatDir(String path) {
        File file = new File(path);
        //判断文件夹是否存在,如果不存在则创建文件夹
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }
}
