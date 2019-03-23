package com.shield.frame.utils;

import java.util.Map;

/**
 * 数据权限ThreadLocal类
 */
public class DataAuthUtil {

    private static final ThreadLocal threadlocal = new ThreadLocal();

    private DataAuthUtil() {
    }

    private static ThreadLocal initialThreadLocal() {
        if (threadlocal != null)
            return threadlocal;
        else
            return new ThreadLocal();
    }

    public static Map getDataAuth() {
        return (Map) initialThreadLocal().get();
    }

    public static void setDataAuth(Map authMap) {
        initialThreadLocal().set(authMap);
    }

    public static void destory() {
        initialThreadLocal().remove();
    }

}
