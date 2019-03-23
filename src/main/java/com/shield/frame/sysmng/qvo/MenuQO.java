package com.shield.frame.sysmng.qvo;

/**
 * 菜单页面查询QO
 */
public class MenuQO {
    /** 菜单的UUID */
    private String id;

    /** 菜单名称*/
    private String name;
    /** 菜单URL*/
    private Short type;
    /** 是否启用*/
    private Short onuse;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
