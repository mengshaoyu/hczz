package com.shield.frame.utils;

/**
 * 文件操作工具类
 */
public class FileUtil {

    /**
     * 获得文件后缀名
     * 
     * @param  filename
     * 
     * @return String 后缀名
     */
    public static String getExtension(String filename) {
        String ext = null;
        if ((filename != null) && (filename.length() > 0)) {
            int i = filename.lastIndexOf('.');
            if ((i > -1) && (i < (filename.length() - 1))) {
                ext = filename.substring(i + 1);
            }
        }
        return ext;
    }
}
