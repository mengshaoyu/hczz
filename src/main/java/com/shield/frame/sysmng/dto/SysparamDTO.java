package com.shield.frame.sysmng.dto;

import java.math.BigDecimal;

import com.shield.frame.common.BaseTO;

public class SysparamDTO extends BaseTO {
    private BigDecimal id;//主键
    private String sysKey;//系统参数Key
    private String sysValue;//系统参数Value
    private char editFlag = '1';//是否可编辑
    private String domainName;//所属系统域
    private String description;//参数描述

    public String getSysKey() {
        return sysKey;
    }

    public void setSysKey(String sysKey) {
        this.sysKey = sysKey;
    }

    public String getSysValue() {
        return sysValue;
    }

    public void setSysValue(String sysValue) {
        this.sysValue = sysValue;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public char getEditFlag() {
        return editFlag;
    }

    public void setEditFlag(char editFlag) {
        this.editFlag = editFlag;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

}
