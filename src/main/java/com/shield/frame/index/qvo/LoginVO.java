package com.shield.frame.index.qvo;

import com.shield.frame.common.qvo.BaseVO;

/**
 * 登录页面的VO
 */
public class LoginVO extends BaseVO {

    /** 系统标识 */
    private String sysChoose;

    public String getSysChoose() {
        return sysChoose;
    }

    public void setSysChoose(String sysChoose) {
        this.sysChoose = sysChoose;
    }
}
