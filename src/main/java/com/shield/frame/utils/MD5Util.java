package com.shield.frame.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5工具类
 */
public class MD5Util {

    /**
     * 获取md5加密后字符串<br />
     * 根据已知字符串，进行MD5加密
     * 
     * @param  str 预加密字符串
     * @return String 加密后字符串
     * @throws NoSuchAlgorithmException 加密失败
     */
    public static String getMd5(String str) throws NoSuchAlgorithmException {
        if (str == null) {
            return "";
        }
        else {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte by[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < by.length; offset++) {
                i = by[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        }
    }
}
