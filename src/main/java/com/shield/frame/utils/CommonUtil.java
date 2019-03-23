package com.shield.frame.utils;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

/**
 * 共通类
 */
public class CommonUtil {

    /**
     * 获取UUID<br />
     * 返回UUID，做为主键使用
     * 
     * @return uuid（32位）
     */
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }

    /**
     * 获取客户端ip地址
     *
     * @param request
     */
    public static String getRemoteIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 判断是否为空
     * @param String str
     */
    public static boolean isEmpty(String str) {
        boolean flag = false;
        if (null == str || "".equals(str) || "" == str) {
            flag = true;
        }
        return flag;
    }

    /**
     * 判断是否为空
     * @param short str
     */
    public static boolean isEmpty(Short str) {
        boolean flag = false;
        if (null == str || "".equals(str)) {
            flag = true;
        }
        return flag;
    }

    /**
     * 判断是否为空
     * @param Long str
     */
    public static boolean isEmpty(Long str) {
        boolean flag = false;
        if (null == str || "".equals(str)) {
            flag = true;
        }
        return flag;
    }

    /**
    * 判断是否为空
    * @param Object obj
    */
    public static boolean isEmpty(Object obj) {
        boolean flag = false;
        if (null == obj || "".equals(obj) || "" == obj) {
            flag = true;
        }
        return flag;
    }

    public static String joinWith(Object[] arr, String separator) {
        if (arr.length <= 0) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        for (Object obj : arr) {
            sb.append(obj == null ? "" : obj.toString());
            sb.append(separator);
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
