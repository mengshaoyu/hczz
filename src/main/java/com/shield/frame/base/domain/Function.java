package com.shield.frame.base.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Function {
    private BigDecimal functionId;

    private String functionname;

    private String url;

    private String descpt;

    private String isToolbar;

    private String toobarTitle;

    private String jsMethod;

    private String imageUrl;

    private Long toolbarOrder;

    private String openMethod;

    private String hasaudata;

    private String createBy;

    private Date createDt;

    private Date updateDt;

    private String updateBy;

    private String deleteBy;

    private String actByType;

    public BigDecimal getFunctionId() {
        return functionId;
    }

    public void setFunctionId(BigDecimal functionId) {
        this.functionId = functionId;
    }

    public String getFunctionname() {
        return functionname;
    }

    public void setFunctionname(String functionname) {
        this.functionname = functionname == null ? null : functionname.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getDescpt() {
        return descpt;
    }

    public void setDescpt(String descpt) {
        this.descpt = descpt == null ? null : descpt.trim();
    }

    public String getIsToolbar() {
        return isToolbar;
    }

    public void setIsToolbar(String isToolbar) {
        this.isToolbar = isToolbar == null ? null : isToolbar.trim();
    }

    public String getToobarTitle() {
        return toobarTitle;
    }

    public void setToobarTitle(String toobarTitle) {
        this.toobarTitle = toobarTitle == null ? null : toobarTitle.trim();
    }

    public String getJsMethod() {
        return jsMethod;
    }

    public void setJsMethod(String jsMethod) {
        this.jsMethod = jsMethod == null ? null : jsMethod.trim();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public Long getToolbarOrder() {
        return toolbarOrder;
    }

    public void setToolbarOrder(Long toolbarOrder) {
        this.toolbarOrder = toolbarOrder;
    }

    public String getOpenMethod() {
        return openMethod;
    }

    public void setOpenMethod(String openMethod) {
        this.openMethod = openMethod == null ? null : openMethod.trim();
    }

    public String getHasaudata() {
        return hasaudata;
    }

    public void setHasaudata(String hasaudata) {
        this.hasaudata = hasaudata == null ? null : hasaudata.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public Date getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(Date updateDt) {
        this.updateDt = updateDt;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public String getDeleteBy() {
        return deleteBy;
    }

    public void setDeleteBy(String deleteBy) {
        this.deleteBy = deleteBy == null ? null : deleteBy.trim();
    }

    public String getActByType() {
        return actByType;
    }

    public void setActByType(String actByType) {
        this.actByType = actByType == null ? null : actByType.trim();
    }
}