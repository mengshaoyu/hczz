package com.shield.frame.sysmng.qvo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.shield.frame.common.BaseTO;
import com.shield.frame.common.qvo.TreeRow;

public class DepartRow extends BaseTO {
    private String name; //部门名称
    private String num; //部门编号
    private String pid; //上级部门ID
    private Short orderNum; //显示序号
    private String address; //地址
    private String pname; //上级部门名称
    private Short Leaf;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Short getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Short orderNum) {
        this.orderNum = orderNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Short getLeaf() {
        return Leaf;
    }

    public void setLeaf(Short leaf) {
        Leaf = leaf;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

}
