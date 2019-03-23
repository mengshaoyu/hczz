package com.shield.frame.sysmng.dto;

import com.shield.frame.common.BaseTO;

public class DataPowDTO extends BaseTO {
    private String authType;
    private String roleId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

}
