package com.shield.hczz.base.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.shield.frame.base.domain.User;

public class SopBaseController {
    
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpSession session;

    @Autowired
    protected ServletContext application;
    
    /**
     * 获取返回
     * @param code
     * @param msg
     * @return
     */
    protected Map<String, Object> getRet(int code, String msg, Map<String, Object> others){
        Map<String, Object> ret = new HashMap<>();
        if(others != null && others.size() > 0){
            ret.putAll(others);
        }
        ret.put("code", code);
        ret.put("msg", msg);
        return ret;
    }
    
    /**
     * 从session中取user信息
     */
    protected User getUser() {
        return (User) request.getSession().getAttribute("loginUser");
    }
}
