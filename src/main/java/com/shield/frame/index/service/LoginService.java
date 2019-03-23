package com.shield.frame.index.service;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shield.frame.index.dto.LoginDTO;
import com.shield.frame.index.qvo.LoginVO;

/**
 * 登录页面对应的Service
 *
 */
public interface LoginService {

    /**
     * 验证用户的登录信息
     *
     * @param loginDTO
     * @param request
     * @param response
     * @return
     */
    LoginVO checkUsr(LoginDTO loginDTO, HttpServletRequest request, HttpServletResponse response);

    /**
     * 修改用户的密码
     *
     * @param oldPwd 原密码
     * @param newPwd 新密码
     * @param usrId  确认新密码
     * 
     * @return 执行的结果
     */
    int updatePwd(String oldPwd, String newPwd, BigDecimal usrId);
}
