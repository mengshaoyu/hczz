package com.shield.frame.common.qvo;

/**
 * 单纯下拉列表框的内容，列表项不包含样式、图标等<br/>
 * 复合下拉列表请参考tree相关的用法。
 */
public class DropDownItem {

    /** 下拉列表的值 */
    private String code;

    /** 下拉列表的标签 */
    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
