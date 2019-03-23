package com.shield.frame.sysmng.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.shield.frame.common.BaseTO;

public class RolePermissionDTO extends BaseTO {
    private BigDecimal id;
    private BigDecimal permissionId;
    private BigDecimal roleId;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(BigDecimal permissionId) {
        this.permissionId = permissionId;
    }

    public BigDecimal getRoleId() {
        return roleId;
    }

    public void setRoleId(BigDecimal roleId) {
        this.roleId = roleId;
    }
}
