package com.shield.hczz.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dozer.DozerBeanMapper;

/**
 * 简单封装Dozer, 实现深度转换Bean<->Bean的Mapper.实现:
 * 
 * 1. 持有Mapper的单例. 2. 返回值类型转换. 3. 批量转换Collection中的所有对象. 4.
 * 区分创建新的B对象与将对象A值复制到已存在的B对象两种函数.
 * 
 */
public class BeanUtil {
    /**
     * 持有Dozer单例, 避免重复创建DozerMapper消耗资源.
     */
    private static DozerBeanMapper dozer = new DozerBeanMapper();

    /**
     * 基于Dozer转换对象的类型.
     */
    public static <T> T map(Object source, Class<T> destinationClass) {
        return dozer.map(source, destinationClass);
    }

    /**
     * 基于Dozer转换Collection中对象的类型.
     */
    @SuppressWarnings("rawtypes")
    public static <T> List<T> mapList(Collection sourceList, Class<T> destinationClass) {
        List<T> destinationList = new ArrayList<T>();
        for (Object sourceObject : sourceList) {
            T destinationObject = dozer.map(sourceObject, destinationClass);
            destinationList.add(destinationObject);
        }
        return destinationList;
    }

    /**
     * 基于Dozer将对象A的值拷贝到对象B中.
     */
    public static void copy(Object source, Object destinationObject) {
        dozer.map(source, destinationObject);
    }
    
    public static Map<String, Object> toMap(Object obj) throws Exception {
        Class<?> cla = obj.getClass();
        Field[] fs = cla.getDeclaredFields();
        if (fs == null || fs.length == 0)
            return new HashMap<String, Object>();
        Map<String,Object> result = new HashMap<String,Object>();
        for (Field f : fs) {
            String name = f.getName();
            try {
                Method m = cla.getMethod("get" + name.substring(0, 1).toUpperCase() + name.substring(1));
                Object v = m.invoke(obj);
                result.put(name, v);
            }
            catch (Exception e) {
//                e.printStackTrace();
                continue;
            }
        }
        return result;
    }
}
