package com.shield.frame.utils;

import java.net.URLDecoder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.shield.frame.sysmng.service.CodeValueService;
import com.shield.frame.sysmng.service.SysParamService;

public class SpringConfigLoadHelper {
    //	private static ApplicationContext _instance;
    private static Object object = new Object();
    private static ApplicationContext _wac = null;
    public static ApplicationContext context;
    static {
        context = new ClassPathXmlApplicationContext("appContext*.xml");
    }

    //	private static final String path = "/applicationContext*.xml";

    /*	static {
    		String urlPath =

    		URLDecoder.decode(SpringConfigLoadHelper.class.getResource("/")
    				.getFile());
    		urlPath = urlPath.substring(0, urlPath.indexOf("WEB-INF")
    				+ "WEB-INF".length());
    		urlPath += path;
    		if (_instance == null)
    			_instance = buildApplicationContext(urlPath);
    	}*/

    public static void buildApplicationContext(ApplicationContext wac) {
        if (_wac == null) {
            synchronized (object) {
                if (_wac == null) {
                    _wac = wac;
                }
            }
        }
    }

    public ApplicationContext getApplicationContext() {
        return _wac;
    }

    public static Object getBean(String name) {
        if (_wac == null)
            return context.getBean(name);
        return _wac.getBean(name);
    }

    public static Object getBean(Class className) {
        if (_wac == null)
            return context.getAutowireCapableBeanFactory().getBean(className);
        return _wac.getBean(className);
    }
}