package com.shield.hczz.base.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public final class InitApplication implements ServletContextListener {

    public void contextInitialized(final ServletContextEvent sce) {

       //初始化Application中的全局变量
    	
    	try {
			String url = this.getClass().getResource("").getPath();  
			String path = url.substring(0, url.indexOf("WEB-INF")) + "WEB-INF/classes/config.properties";
			URI uri = new URI(path); 
			Properties config = new Properties();  
			InputStream in = new FileInputStream(uri.getPath());
			config.load(in);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

    public void contextDestroyed(final ServletContextEvent sce) {
       
    }

}
