package com.shield.frame.utils;

public class LocalUtil {

    private static final ThreadLocal threadlocal = new ThreadLocal();

    private LocalUtil() {
    }

    private static ThreadLocal initialThreadLocal() {
        if (threadlocal != null)
            return threadlocal;
        else
            return new ThreadLocal();
    }

    public static String getUserId() {
        return (String) initialThreadLocal().get();
    }

    public static void setUserId(String userId) {
        initialThreadLocal().set(userId);
    }
}
