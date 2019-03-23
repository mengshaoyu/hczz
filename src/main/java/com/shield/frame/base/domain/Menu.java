package com.shield.frame.base.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Menu {
    private BigDecimal menuId;

    private String menuName;

    private String menuAction;

    private BigDecimal menuParentid;

    private String menuDisabled;

    private Long menuSortOrder;

    private String domainName;

    private String createBy;

    private Date createDt;

    private Date updateDt;

    private String updateBy;

    private String deleteBy;

    private String actByType;

    public BigDecimal getMenuId() {
        return menuId;
    }

    public void setMenuId(BigDecimal menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getMenuAction() {
        return menuAction;
    }

    public void setMenuAction(String menuAction) {
        this.menuAction = menuAction == null ? null : menuAction.trim();
    }

    public BigDecimal getMenuParentid() {
        return menuParentid;
    }

    public void setMenuParentid(BigDecimal menuParentid) {
        this.menuParentid = menuParentid;
    }

    public String getMenuDisabled() {
        return menuDisabled;
    }

    public void setMenuDisabled(String menuDisabled) {
        this.menuDisabled = menuDisabled == null ? null : menuDisabled.trim();
    }

    public Long getMenuSortOrder() {
        return menuSortOrder;
    }

    public void setMenuSortOrder(Long menuSortOrder) {
        this.menuSortOrder = menuSortOrder;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName == null ? null : domainName.trim();
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