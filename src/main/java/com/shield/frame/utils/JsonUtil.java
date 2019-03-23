package com.shield.frame.utils;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Json操作类
 */
public class JsonUtil {

    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * 字符串转化成对象
     *
     * @param <T>
     * @param str Json字符串
     * @param clazz  类
     * @return
     */
    public static <T> T readValue(String str, Class<T> clazz) {
        T t = null;
        if (StringUtils.isBlank(str)) {
            return null;
        }
        try {
            t = mapper.readValue(str, clazz);
        }
        catch (JsonParseException e) {
            e.printStackTrace();
        }
        catch (JsonMappingException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 把实体类转化成Json字符串
     *
     * @param obj 实体对象
     * @return Json字符串
     */
    public static String writeValueAsString(Object obj) {
        String str = null;
        try {
            str = mapper.writeValueAsString(obj);
        }
        catch (JsonGenerationException e) {
            e.printStackTrace();
        }
        catch (JsonMappingException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}
