package com.shield.hczz.base.domain;

public class ExcelColumn extends CommonColumn {
    /**
     * 字段所在的索引
     */
    private Integer index;

    public ExcelColumn() {
    }

    public ExcelColumn(Integer index) {
        this.index = index;
    }

    public ExcelColumn(Integer index, String field) {
        this.index = index;
        this.field = field;
    }

    public ExcelColumn(Integer index, String field, String title) {
        this.index = index;
        this.field = field;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("title:");
        sb.append(this.title);
        sb.append(",index:");
        sb.append(this.index);
        sb.append(",field:");
        sb.append(this.field);
        sb.append(",length:");
        sb.append(this.length);
        sb.append(",nullable:");
        sb.append(this.nullable);
        sb.append(",type:");
        sb.append(JdbcType.VARCHAR.equals(type)?"VARCHAR":"NUMBER");
        return sb.toString();
    }
}
