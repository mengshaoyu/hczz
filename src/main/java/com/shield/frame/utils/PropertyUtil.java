package com.shield.frame.utils;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * 属性文件操作工具类<br/> 读取消息文件中定义的消息，如果消息中含有占位符，则使用参数依次替换。
 */
public class PropertyUtil {

    private static final String BUNDLE_NAME = "messages";

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

    private PropertyUtil() {
    }

    /**
     * 直接取得属性文件中的消息，消息中没有需要替换的内容
     * 
     * @param key
     *            消息Key
     * @return
     */
    public static String getMsg(String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        }
        catch (MissingResourceException e) {
            return "消息不存在";
        }
    }

    /**
     * 取得属性文件中的消息，消息中有一个需要替换的参数
     * 
     * @param key
     *            消息Key
     * @param parm
     *            替换内容
     * @return
     */
    public static String getMsg(String key, String parm) {
        try {
            return MessageFormat.format(RESOURCE_BUNDLE.getString(key), new Object[] { parm });
        }
        catch (MissingResourceException e) {
            return "消息不存在";
        }
    }

    /**
     * 取得属性文件中的消息，消息中有多个需要替换的参数
     * 
     * @param key
     *            消息Key
     * @param parm
     *            多个替换内容
     * @return
     */
    public static String getMsg(String key, Object... parm) {
        try {
            return MessageFormat.format(RESOURCE_BUNDLE.getString(key), parm);

        }
        catch (MissingResourceException e) {
            return "消息不存在";
        }
    }
    
    public static String getProperty(String file,String key){
        ResourceBundle resource = ResourceBundle.getBundle(file);
        if(resource != null)
            return resource.getString(key);
        return null;
    }
}
