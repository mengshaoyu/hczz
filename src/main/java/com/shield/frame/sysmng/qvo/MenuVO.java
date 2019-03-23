package com.shield.frame.sysmng.qvo;

import com.shield.frame.common.qvo.BaseVO;

public class MenuVO extends BaseVO {

    private String uuid;

    private String name;

    private String url;

    private Short type;
    private String typeName;

    private Short onuse;

    private Short ordernum;

    private String parent;

    private Short iconcls;
    private String iconclsName;

    private Short sysflg;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Short getOnuse() {
        return onuse;
    }

    public void setOnuse(Short onuse) {
        this.onuse = onuse;
    }

    public Short getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(Short ordernum) {
        this.ordernum = ordernum;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public Short getIconcls() {
        return iconcls;
    }

    public void setIconcls(Short iconcls) {
        this.iconcls = iconcls;
    }

    public Short getSysflg() {
        return sysflg;
    }

    public void setSysflg(Short sysflg) {
        this.sysflg = sysflg;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getIconclsName() {
        return iconclsName;
    }

    public void setIconclsName(String iconclsName) {
        this.iconclsName = iconclsName;
    }
}
