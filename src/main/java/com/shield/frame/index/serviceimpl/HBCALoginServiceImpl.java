package com.shield.frame.index.serviceimpl;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shield.frame.base.domain.User;
import com.shield.frame.base.persistence.impl.UserMapperImpl;
import com.shield.frame.common.Constants;
import com.shield.frame.index.qvo.LoginVO;
import com.shield.frame.index.service.HBCALoginService;
import com.shield.frame.sysmng.dto.UserDTO;

@Service
public class HBCALoginServiceImpl implements HBCALoginService {
    @Autowired
    private UserMapperImpl userMapper;

    @Override
    public LoginVO checkCerUsr(String idNo, HttpServletRequest request, HttpServletResponse response) {
        LoginVO loginVO = new LoginVO();
        UserDTO user = userMapper.getByIdNo(idNo);
        try {
            if (user == null || StringUtils.isBlank(user.getLoginAccount())) {
                // 帐号不存在
                loginVO.setMsgCode("index_001");
            }
            else if (StringUtils.isBlank(user.getCurState()) || !user.getCurState().equals("0")) {
                // 帐号已被停用
                loginVO.setMsgCode("index_003");
                // 设置session
            }
            else {
                User userSession = userMapper.getUserInfo(user.getLoginAccount());
                userSession.setRoleList(userMapper.getUserRoles(user.getLoginAccount()));
                request.getSession().setAttribute("loginUser", userSession);
                request.getSession().setAttribute(Constants.SESN_USR_UID,
                    new BigDecimal(user.getUserId()));
                request.getSession()
                    .setAttribute(Constants.SESN_USR_ACOUNT, user.getLoginAccount());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return loginVO;
    }

}
