package com.shield.frame.index.serviceimpl;

import java.math.BigDecimal;
import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shield.frame.base.domain.Role;
import com.shield.frame.base.domain.User;
import com.shield.frame.base.persistence.UserMapper;
import com.shield.frame.base.persistence.impl.UserMapperImpl;
import com.shield.frame.common.Constants;
import com.shield.frame.index.dto.LoginDTO;
import com.shield.frame.index.qvo.LoginVO;
import com.shield.frame.index.service.LoginService;
import com.shield.frame.utils.MD5Util;
import com.shield.spring.AutoLog;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapperImpl userMapper;

    @AutoLog(desc = "验证用户登录信息")
    public LoginVO checkUsr(LoginDTO loginDTO, HttpServletRequest request,
        HttpServletResponse response) {
        LoginVO loginVO = new LoginVO();
        User user = userMapper.getUserInfo(loginDTO.getAccount());
        try {
            if (user == null || StringUtils.isBlank(user.getLoginAccount())) {
                // 帐号不存在
                loginVO.setMsgCode("index_001");
            }
            else if (!MD5Util.getMd5(loginDTO.getPasswd()).equals(user.getLoginPwd())) {
                // 密码错误
                loginVO.setMsgCode("index_002");
            }
            else if (StringUtils.isBlank(user.getCurState()) || !user.getCurState().equals("0")) {
                // 帐号已被停用
                loginVO.setMsgCode("index_003");
                // 下面处理cookie
            }
            else {
                // 要添加的cookie的名字
                String ckis[] = { "userName", "userPassword", "saveOR" };
                // 要添加的cookie的值
                String ckiVal[] = { loginDTO.getAccount(), loginDTO.getPasswd(),
                    loginDTO.getRememberMe() };

                Cookie cookie = null;
                // cookie的生命期
                int ckiAge = 5 * 24 * 60 * 60;

                // 添加cookie
                if ("on".equals(loginDTO.getRememberMe())) {
                    for (int i = 0; i < 3; i++) {
                        cookie = new Cookie(ckis[i], ckiVal[i]);
                        cookie.setMaxAge(ckiAge);
                        cookie.setPath(request.getContextPath());
                        response.addCookie(cookie);
                    }
                }
                else {
                    // 删除cookie
                    for (String cki : ckis) {
                        cookie = new Cookie(cki, null);
                        cookie.setMaxAge(0);
                        cookie.setPath(request.getContextPath());
                        response.addCookie(cookie);
                    }
                }
                user.setRoleList(userMapper.getUserRoles(loginDTO.getAccount()));

                String roles = "0";
                for (Role role : user.getRoleList()) {
                    roles += "," + role.getRoleId();
                }
                request.getSession().setAttribute("roles", roles);
                request.getSession().setAttribute("loginUser", user);
                request.getSession().setAttribute(Constants.SESN_USR_UID, user.getUserId());
                request.getSession()
                    .setAttribute(Constants.SESN_USR_ACOUNT, user.getLoginAccount());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return loginVO;
    }

    public int updatePwd(String oldPwd, String newPwd, BigDecimal usrId) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        try {
            hashMap.put("oldPwd", MD5Util.getMd5(oldPwd));//md5加密
            hashMap.put("newPwd", MD5Util.getMd5(newPwd));//md5加密
            hashMap.put("usrId", usrId);
            hashMap.put("updateBy", usrId);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return userMapper.update(UserMapper.class.getName() + ".updatePwd", hashMap);
    }
}
