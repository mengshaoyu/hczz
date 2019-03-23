package com.shield.frame.utils;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.oscache.base.CacheEntry;
import com.opensymphony.oscache.base.EntryRefreshPolicy;
import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;

/**
 * OsCache缓存工具类
 */
public class OsCacheUtil {
    private static GeneralCacheAdministrator cacheAdmin = new GeneralCacheAdministrator();
    static final Logger log = LoggerFactory.getLogger(OsCacheUtil.class);

    /**
     * 从缓存中清除指定的对象
     *
     * @param key 对象的Key.
     */
    public static void removeEntry(String key) {
        cacheAdmin.removeEntry(key);
    }

    /**
     * 从缓存中取得一个对象
     *
     * @param key  对象的Key
     * @return  缓存的对象或null
     */
    public static Object getFromCache(String key) {
        Object obj = null;

        if (StringUtils.isBlank(key)) {
            log.info("指定的Key为空");

        }
        else {
            try {
                obj = cacheAdmin.getFromCache(key);
            }
            catch (NeedsRefreshException ex) {
                cacheAdmin.cancelUpdate(key);
            }
        }

        return obj;
    }

    /**
     * 从缓存中取得一个缓存对象
     *
     * @param key 缓存对象的Key
     * @param refreshPeriod  对象在缓存中的过期时间  不过期请设置成{@link CacheEntry#INDEFINITE_EXPIRY}
     * @return 缓存对象或null
     */
    public static Object getFromCache(String key, int refreshPeriod) {
        Object obj = null;

        if (StringUtils.isBlank(key)) {
            log.info("指定的Key为空");
        }
        else {
            try {
                obj = cacheAdmin.getFromCache(key, refreshPeriod);
            }
            catch (NeedsRefreshException ex) {
                cacheAdmin.cancelUpdate(key);
            }
        }

        return obj;
    }

    /**
     * 立即刷新所有缓存对象.
     */
    public static void flushAll() {
        cacheAdmin.flushAll(new Date());
    }

    /**
     * 在给定的时间点刷新缓存.
     *
     * @param 刷新时间点
     */
    public static void flushAll(Date date) {
        cacheAdmin.flushAll(date);
    }

    /**
     * 刷新指定的缓存对象.
     *
     * @param key 缓存对象Key
     * @return false：key为空  true：成功
     */
    public static void flushEntry(String key) {
        if (StringUtils.isBlank(key)) {
            log.info("指定的Key为空");
            return;
        }

        cacheAdmin.flushEntry(key);
    }

    /**
     * 刷新指定组的缓存对象.
     *
     * @param group 需要刷新的组
     */
    public static void flushGroup(String group) {
        cacheAdmin.flushGroup(group);
    }

    /**
     * 缓存一个对象
     *
     * @param key       对象Key
     * @param content   对象实体
     */
    public static void putInCache(String key, Object content) {
        if (StringUtils.isBlank(key)) {
            log.info("指定的Key为空");
            return;
        }

        cacheAdmin.putInCache(key, content);
    }

    /**
     * 缓存一个对象
     *
     * @param key       对象Key
     * @param content   对象实体
     * @param policy    更新逻辑对象
     */
    public static void putInCache(String key, Object content, EntryRefreshPolicy policy) {
        if (StringUtils.isBlank(key)) {
            log.info("指定的Key为空");
            return;
        }

        cacheAdmin.putInCache(key, content, policy);
    }

    /**
     * 缓存一个对象
     *
     * @param key      对象Key
     * @param content  对象实体
     * @param groups   对象所属分组
     */
    public static void putInCache(String key, Object content, String[] groups) {
        if (StringUtils.isBlank(key)) {
            log.info("指定的Key为空");
            return;
        }
        else if (groups == null || groups.length < 1) {
            log.info("指定的分组为空");
        }

        cacheAdmin.putInCache(key, content, groups);
    }

    /**
     * 缓存一个对象
     *
     * @param key      对象Key
     * @param content  对象实体
     * @param groups   对象所属分组
     * @param policy   更新逻辑对象
     */
    public static void putInCache(String key, Object content, String[] groups,
        EntryRefreshPolicy policy) {
        if (StringUtils.isBlank(key)) {
            log.info("指定的Key为空");
            return;
        }
        else if (groups == null || groups.length < 1) {
            log.info("指定的分组为空");
        }

        cacheAdmin.putInCache(key, content, groups, policy);
    }
}
