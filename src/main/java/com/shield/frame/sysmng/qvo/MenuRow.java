package com.shield.frame.sysmng.qvo;

import com.shield.frame.common.qvo.TreeRow;

/**
 * 菜单行项目
 */
public class MenuRow extends TreeRow {
    /** 菜单名称 */
    private String name;
    /** 菜单URL */
    private String url;
    /** 显示顺序 */
    private Short orderNum;
    /** 菜单类型 */
    private Short type;
    /** 类型名称 */
    private String typeName;
    /** 状态名称 */
    private String onUseName;
    /** 系统标志 */
    private Short sysflg;
    private String sysName;

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public void setSysflg(Short sysflg) {
        this.sysflg = sysflg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Short getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Short orderNum) {
        this.orderNum = orderNum;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getOnUseName() {
        return onUseName;
    }

    public void setOnUseName(String onUseName) {
        this.onUseName = onUseName;
    }

    public Short getSysflg() {
        return sysflg;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }
}
