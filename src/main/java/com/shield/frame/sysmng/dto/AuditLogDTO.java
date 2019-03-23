package com.shield.frame.sysmng.dto;

import java.math.BigDecimal;

import com.shield.frame.common.BaseTO;

public class AuditLogDTO extends BaseTO {

    private String uuid;

    private BigDecimal userId;

    private String userIp;

    private String function;

    private String operType;

    private String operContent;

    private String resourceId;

    private String result;

    public AuditLogDTO() {
    }

    public AuditLogDTO(String uuid, BigDecimal userId, String userIp, String function,
        String operType, String operContent, String resourceId, String result) {
        super();
        this.uuid = uuid;
        this.userId = userId;
        this.userIp = userIp;
        this.function = function;
        this.operType = operType;
        this.operContent = operContent;
        this.resourceId = resourceId;
        this.result = result;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public BigDecimal getUserId() {
        return userId;
    }

    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getOperType() {
        return operType;
    }

    public void setOperType(String operType) {
        this.operType = operType;
    }

    public String getOperContent() {
        return operContent;
    }

    public void setOperContent(String operContent) {
        this.operContent = operContent;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}