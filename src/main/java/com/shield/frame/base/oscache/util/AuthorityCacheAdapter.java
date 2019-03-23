package com.shield.frame.base.oscache.util;

import java.util.List;
import java.util.Map;

import com.shield.frame.base.oscache.adapter.CacheAdapter;
import com.shield.frame.base.oscache.adapter.StdCacheAdapter;

/**
 * 权限缓存UTIL类
 */
public class AuthorityCacheAdapter {

    public static final String CACHE_AU_MODULE_PREFIX = "au_module_";

    public static final String CACHE_AU_ROLE_GROUP = CACHE_AU_MODULE_PREFIX + "roles";

    private CacheAdapter cacheSupport = StdCacheAdapter.getInstance();
    private static Object lock = new Object();

    //权限Service类
    //private PermissionService permissionService;

    /**
     * 
     * @param roleId :
     *            缓存对象对应的主健
     * 
     * @return : 缓存对象
     */
    public List<Map<String, Object>> getFuncObjByRoleId(Long roleId, Map<String, Object> param) {
        String key = getRoleCacheKey(roleId);
        Object object = cacheSupport.get(key);
        if (object == null) {
            synchronized (lock) {
                if (object == null) {
                    //缓存对象若为空，调用权限Service从数据库中查询获取角色对应URL的权限信息
                    // object = this.getPermissionService().getFuncUrlsByRoleId(param);

                    if (object == null)
                        return null;
                    putOneRoleFuncUrlListObj(roleId, (List<Map<String, Object>>) object);
                }
            }
        }
        return (List<Map<String, Object>>) object;
    }

    /**
     * 
     * @param roleId :
     *            缓存对象对应的主健
     * 
     * @return :
     */
    public void removeFuncUrlByRoleId(Long roleId) {
        if (roleId == null)
            return;
        String key = getRoleCacheKey(roleId);
        Object object = cacheSupport.get(key);
        if (object != null) {
            synchronized (lock) {
                if (object != null) {
                    cacheSupport.flushEntry(key);
                }
            }
        }
    }

    public void putOneRoleFuncUrlListObj(Long roleId, List<Map<String, Object>> auFuncObj) {
        String key = getRoleCacheKey(roleId);
        cacheSupport.put(key, auFuncObj, CACHE_AU_ROLE_GROUP);
    }

    private String getRoleCacheKey(Long roleId) {
        return CACHE_AU_ROLE_GROUP + cacheSupport.CACHE_SEPERATOR + roleId;
    }

    public static void main(String[] a) {
        AuthorityCacheAdapter authorityCacheAdapter = new AuthorityCacheAdapter();

    }
}
