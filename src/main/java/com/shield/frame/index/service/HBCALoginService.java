package com.shield.frame.index.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shield.frame.index.qvo.LoginVO;

/**
 * @description 证书登录
 * */
public interface HBCALoginService {
    /**
     * 验证用户身份信息
     *
     * @param IdNo
     * @param request
     * @param response
     * @return
     */
    LoginVO checkCerUsr(String idNo, HttpServletRequest request, HttpServletResponse response);

}