package com.shield.frame.sysmng.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class MenuDTO {
    private String uuid;

    @NotEmpty
    @Length(max = 50)
    private String name;

    @Length(max = 100)
    @Pattern(regexp = "^(([A-Za-z\\d\\-_]+[\\/]{0,1}[A-Za-z\\d\\-_]+)+|[\\/|#]{0,1})$")
    private String url;

    @Max(value = 3)
    @Min(value = 1)
    @Digits(integer = 1, fraction = 0)
    @NotNull
    private Short type;

    @Max(value = 1)
    @Min(value = 0)
    @Digits(integer = 1, fraction = 0)
    private Short onuse;

    @Max(value = 999)
    @Min(value = 0)
    @Digits(integer = 3, fraction = 0)
    private Short orderNum;

    private String parent;

    @Max(value = 9999)
    @Min(value = 1)
    @Digits(integer = 4, fraction = 0)
    private Short iconCls;

    @Max(value = 3)
    @Min(value = 1)
    @Digits(integer = 1, fraction = 0)
    private Short sysflg;

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

    public Short getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Short orderNum) {
        this.orderNum = orderNum;
    }

    public Short getIconCls() {
        return iconCls;
    }

    public void setIconCls(Short iconCls) {
        this.iconCls = iconCls;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public Short getSysflg() {
        return sysflg;
    }

    public void setSysflg(Short sysflg) {
        this.sysflg = sysflg;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
