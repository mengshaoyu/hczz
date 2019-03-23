package com.shield.frame.utils;

import java.io.Serializable;

import com.shield.frame.common.Constants;

public class SylogicCacheUtil {
	
	/**
	 * 添加对象到指定的cache中
	 * @param key 对象存入cache时对应的key （使用该key可以从cache中取出）
	 * @param Object 需要添加到cache的对象
	 * @return void
	 */
	public static void putInCache(Object key,Object obj){
		EhcacheUtil.addToCache(Constants.EHCACHE_SYLOGIC, key, obj);
	}

	/**
	 * 从指定的cache中取出对象
	 * @param key 需要取出对象 对应的key
	 * @return Object
	 */
	public static Object getFromCache(Object key){
		return EhcacheUtil.getFromCache(Constants.EHCACHE_SYLOGIC, key);
	}
	
	/**
	 * 删除对应的element对象
	 * @param elementName 需要删除的element名称
	 * @return 
	 */
	public static void removeElement( Serializable key){
		EhcacheUtil.removeElement(Constants.EHCACHE_SYLOGIC, key);
	}
	
	/**
	 * 刷新缓存
	 */
	public static void flushCache(){
		EhcacheUtil.flushCache(Constants.EHCACHE_SYLOGIC);
	}
}
