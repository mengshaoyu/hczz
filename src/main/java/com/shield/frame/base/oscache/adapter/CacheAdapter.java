package com.shield.frame.base.oscache.adapter;

/**
 * 缓存适配器接口
 */
public interface CacheAdapter {

    String KV_SEP = "===";
    String CACHE_SEPERATOR = ":";

    Object get(String key);

    void put(String key, Object object);

    void put(String key, Object object, String group);

    void put(String key, Object object, String[] groups);

    void remove(String key);

    void flushAll();

    void flushEntry(String key);

    void flushGroup(String group);

}
