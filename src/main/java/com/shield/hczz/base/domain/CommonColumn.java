package com.shield.hczz.base.domain;

public class CommonColumn {

    /**
     * 自定义简易jdbcType字段类型(只支持数字和字符串类型)
     */
    public enum JdbcType {
        /**
         * 数字类型
         */
        NUMBER(0),
        /**
         * 字符串类型
         */
        VARCHAR(1);
        public final int typeCode;

        JdbcType(int typeCode) {
            this.typeCode = typeCode;
        }

        /**
         * 获取code值
         * @return
         */
        public int getCode() {
            return this.typeCode;
        }

        /**
         * 通过code获取jdbcType字段类型
         * @param typeCode code 1：字符串 0：数字
         * @return
         */
        public static JdbcType find(int typeCode) {
            return 1 == typeCode ? VARCHAR : NUMBER;
        }
    }

    /**
     * 字段长度默认36位
     */
    protected Integer length = 36;
    /**
     * 是否可空 默认是true 可空
     */
    protected boolean nullable = true;
    /**
     * 模板文件的对应标题
     */
    protected String title;
    /**
     * 根据标题自动生成的拼音字段名称
     */
    protected String field;
    /**
     * jdbcType 字段类型（为简化配置只支持数字和字符串两种格式）
     */
    protected JdbcType type = JdbcType.find(1);

    public JdbcType getType() {
        return type;
    }

    public void setType(JdbcType type) {
        this.type = type;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
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

}
