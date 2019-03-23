package com.shield.frame.base.oscache.adapter;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;

/**
 * 缓存适配器
 */
public class StdCacheAdapter implements CacheAdapter {

    private static Logger log = (Logger) LoggerFactory.getLogger(StdCacheAdapter.class);

    private static final GeneralCacheAdministrator cacheAdmin = new GeneralCacheAdministrator();

    private static CacheAdapter __instance__ = new StdCacheAdapter();

    /**
     * 获取缓存适配器类
     * @return
     */
    public static CacheAdapter getInstance() {
        return __instance__;
    }

    /**
     * 同步所有缓存
     */
    public void flushAll() {
        cacheAdmin.flushAll();
    }

    /**
     * 指定KEY的缓存同步
     */
    public void flushEntry(String key) {
        if (key == null || key.length() == 0) {
            log.debug("StdCacheEngine: key is null, flushEntry operate canncelled.");
            return;
        }
        cacheAdmin.flushEntry(key);
    }

    /**
     * 指定组的缓存同步
     */
    public void flushGroup(String group) {
        if (group == null || group.length() == 0) {
            log.debug("StdCacheEngine: group is null, flushGroup operate canncelled.");
            return;
        }
        cacheAdmin.flushGroup(group);
    }

    /**
     * 获取缓存对象
     */
    public Object get(String key) {
        if (key == null || key.length() == 0) {
            log.debug("StdCacheEngine: key is null, get operate canncelled.");
            return null;
        }
        try {
            return cacheAdmin.getFromCache(key);
        }
        catch (NeedsRefreshException nre) {
            log.debug("StdCacheEngine:NeedsRefreshException exception raised.");
            cacheAdmin.cancelUpdate(key);
            return null;
        }
    }

    /**
     * 设置缓存对象
     */
    public void put(String key, Object object) {
        if (key == null || key.length() == 0 || object == null) {
            log.debug("StdCacheEngine: key or object is null, put operate canncelled.");
            return;
        }
        cacheAdmin.putInCache(key, object);
    }

    /**
     * 指定组指定KEY 设置缓存对象
     */
    public void put(String key, Object object, String group) {
        if (key == null || key.length() == 0 || object == null) {
            log.debug("StdCacheEngine: key or object is null, put operate canncelled.");
            return;
        }
        if (group == null || group.length() == 0) {
            log.debug("StdCacheEngine: group is null, put operate canncelled.");
            return;
        }
        cacheAdmin.putInCache(key, object, new String[] { group });
    }

    /**
     * 指定组指定KEY 设置缓存对象
     */
    public void put(String key, Object object, String[] groups) {
        if (key == null || key.length() == 0 || object == null) {
            log.debug("StdCacheEngine: key or object is null, put operate canncelled.");
            return;
        }
        if (groups == null || groups.length == 0) {
            log.debug("StdCacheEngine: groups is null, put operate canncelled.");
            return;
        }
        for (String group : groups) {
            if (group == null || group.length() == 0) {
                log.debug("StdCacheEngine: groups is null, put operate canncelled.");
                return;
            }
        }
        cacheAdmin.putInCache(key, object, groups);
    }

    /**
     * 指定KEY的缓存移除
     */
    public void remove(String key) {
        if (key == null || key.length() == 0) {
            log.debug("StdCacheEngine: key is null, remove operate canncelled.");
            return;
        }
        cacheAdmin.removeEntry(key);
    }
}
