package com.shield.frame.common.qvo;

import java.util.List;
import java.util.Map;

/**
 * gridtree的通用字段
 */
public class TreeRow {
    /** UUID*/
    private String id;

    /** 状态 是否在用*/
    private String state;

    /** 是否选中*/
    private boolean checked;

    /** 属性*/
    private Map<?, ?> attributes;

    /** 图标*/
    private String iconCls;

    /** 子项*/
    private List<TreeRow> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Map<?, ?> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<?, ?> attributes) {
        this.attributes = attributes;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public List<TreeRow> getChildren() {
        return children;
    }

    public void setChildren(List<TreeRow> children) {
        this.children = children;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}