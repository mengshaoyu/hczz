package com.shield.frame.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.WebRequest;

/**
 * Ajax工具类
 */
public class AjaxUtil {

    /**
     * 判断是否Ajax请求
     * 
     * @param  webRequest 请求
     * 
     * @return boolean true表示该请求为Ajax请求
     */
    public static boolean isAjaxRequest(WebRequest webRequest) {
        String requestedWith = webRequest.getHeader("X-Requested-With");
        return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
    }

    /**
     * 判断是否Ajax上传
     * 
     * @param  webRequest 请求
     * 
     * @return boolean true表示该请求为Ajax请求
     */
    public static boolean isAjaxUploadRequest(WebRequest webRequest) {
        return webRequest.getParameter("ajaxUpload") != null;
    }

    /**
     * 判断是否Ajax请求
     * 
     * @param  request 请求
     * 
     * @return boolean true表示该请求为Ajax请求
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String requestedWith = request.getHeader("X-Requested-With");
        return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
    }

}
