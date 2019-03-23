package com.shield.frame.utils;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EhcacheUtil {
	static final Logger log = LoggerFactory.getLogger(EhcacheUtil.class);
	
	private static CacheManager cacheManager = CacheManager.create();
	
	/**
	 * 根据cache名称取出对应的cache对象
	 * @param cacheName 需要取出的缓存名称
	 * @return Cache cache对象
	 */
	public static  Cache getCache(String cacheName){
		Cache ehCache = null;
		if(null != cacheName){
			ehCache = (Cache) cacheManager.getCache(cacheName);
		}else{
			log.warn("指定的cache名称为空");
		}
		return ehCache;
	}
	
	/**
	 * 根据cache名称和element 取出对应的element对象
	 * @param cacheName 需要取出的缓存名称  
	 * @param key 需要取出的element名称
	 * @return Element element对象
	 */
	public static Element getElement(String cacheName, Object key){
		Cache ehCache = getCache(cacheName);
		if(ehCache == null){
			return null;
		}
		if(key == null){
			log.warn("指定的key为空");
			return null;
		}
		Element element = ehCache.get(key);
		return element;
	}
	
	/**
	 * 根据cache名称和element 删除对应的element对象
	 * @param cacheName 缓存名称  
	 * @param elementName 需要删除的element名称
	 * @return 
	 */
	public static  void removeElement(String cacheName, Serializable key){
		Cache ehCache = getCache(cacheName);
		if(ehCache != null && key!=null){
			ehCache.remove(key);
			log.info("从"+cacheName+"缓存中删除"+key);
		}
	}
	
	/**
	 * 添加对象到指定的cache中
	 * @param cacheName 缓存名称  
	 * @param key 对象存入cache时对应的key （使用该key可以从cache中取出）
	 * @param Object 需要添加到cache的对象
	 * @return void
	 */
	public static  void addToCache(String cacheName,Object key,Object obj){
		Cache ehCache = getCache(cacheName);
		if(ehCache != null){
			if(key != null){
				Element element = new Element(key, obj);
				ehCache.put(element);
				log.info("向"+cacheName+"缓存中添加"+key);
			}else{
				log.warn("指定的key为空");
			}
		}
	}
	
	/**
	 * 从指定的cache中取出对象
	 * @param cacheName 缓存名称  
	 * @param key 需要取出对象 对应的key
	 * @return Object
	 */
	public static  Object getFromCache(String cacheName,Object key){
		Element element = getElement(cacheName, key);
		Object obj = null;
		if(element!=null){
			obj = element.getObjectValue();
			log.info("从"+cacheName+"缓存中获取"+key);
		}
		return obj;
	}
	
	public static void flushCache(String cacheName){
		Cache ehCache = getCache(cacheName);
		if(ehCache != null){
			ehCache.flush();
		}
	}
}
