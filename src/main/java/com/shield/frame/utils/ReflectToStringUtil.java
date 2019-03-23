package com.shield.frame.utils;

import java.lang.reflect.Field;

public class ReflectToStringUtil {

    static StringBuffer sb = new StringBuffer();

    public static String toStringUtil(Object clazs, boolean isOutputNull) {

        getParamAndValue(clazs, clazs.getClass(), isOutputNull);
        String rtn = "{" + sb.toString() + "}";
        return rtn;
    }

    private static void getParamAndValue(Object clazs, Class<?> clazz, boolean isOutputNull) {

        Class<?> sc = clazz.getSuperclass();
        Field[] sfields = sc.getDeclaredFields();
        if (sfields.length > 0) {
            getParamAndValue(clazs, sc, isOutputNull);
        }
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            try {
                if (null != f.get(clazs) || isOutputNull) {
                    sb.append("\\\"" + f.getName() + "\\\":\\\"" + f.get(clazs) + "\\\",");
                }
            }
            catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}