package com.shield.frame.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class Column {

    public static enum ENUM_TYPE {
        VARCHAR2, NUMBER, DATE,
    }

    /**
     * 逻辑字段名
     */
    private String name = null;

    /**
     * 物理字段名
     */
    private String col = null;

    /**
     * 数据类型
     */
    private ENUM_TYPE type = null;

    /**
     * 字段长度
     */
    private Integer length = 0;

    /**
     * 列宽度
     */
    private Integer width = 3000;

    /**
     * 是否可以为null
     */
    private boolean nullAble = false;

    /**
     * 关联字段
     */
    private String relCol = null;

    /**
     * 关联Code
     */
    private String relValueCode = null;

    /**
     * 可能的值
     */
    private Map<String, String> possibleValues = new LinkedHashMap<String, String>();

    /**
     * 构造函数
     * 
     * @param name
     * @param col
     * @param type
     * @param length
     * @param length
     * @param nullAble
     */
    public Column(String name, String col, ENUM_TYPE type, Integer length, Integer width,
        boolean nullAble) {
        this(name, col, type, length, width, nullAble, new String[] {});
    }

    /**
     * 构造函数
     * 
     * @param name
     * @param col
     * @param type
     * @param length
     * @param length
     * @param nullAble
     */
    public Column(String name, String col, ENUM_TYPE type, Integer length, Integer width,
        boolean nullAble, Map<String, String> possibleValues) {
        this.name = name;
        this.col = col;
        this.type = type;
        this.length = length;
        this.nullAble = nullAble;
        this.possibleValues = possibleValues;
        this.width = width;
    }

    /**
     * 构造函数
     * 
     * @param name
     * @param col
     * @param type
     * @param length
     * @param nullAble
     * @param searchCodeType
     *            在ZX_T_CODE_TYPE表里检索CodeType，如果存在，则将ZX_T_CODE表的数据填充到possibleValues
     */
    public Column(String name, String col, ENUM_TYPE type, Integer length, boolean nullAble,
        Map<String, String> possibleValues) {
        this.name = name;
        this.col = col;
        this.type = type;
        this.length = length;
        this.nullAble = nullAble;
        this.possibleValues = possibleValues;
    }

    /**
     * 构造函数
     * 
     * @param name
     * @param col
     * @param type
     * @param length
     * @param nullAble
     * @param relCol
     * @param relValueDesc
     * @param searchCodeType
     *            在ZX_T_CODE_TYPE表里检索CodeType，如果存在，则将ZX_T_CODE表的数据填充到possibleValues
     */
    public Column(String name, String col, ENUM_TYPE type, Integer length, boolean nullAble,
        String relCol, String relValueDesc, Map<String, String> possibleValues) {
        this.name = name;
        this.col = col;
        this.type = type;
        this.length = length;
        this.nullAble = nullAble;
        this.possibleValues = possibleValues;

        this.relCol = relCol;

        for (Map.Entry<String, String> entry : possibleValues.entrySet()) {
            String value = entry.getValue();
            if (relValueDesc.equals(value)) {
                this.relValueCode = entry.getKey();
                break;
            }
        }

    }

    /**
     * 构造函数
     * 
     * @param name
     * @param col
     * @param type
     * @param length
     * @param nullAble
     * @param possibleValues
     */
    public Column(String name, String col, ENUM_TYPE type, Integer length, Integer width,
        boolean nullAble, String... possibleValues) {
        this.name = name;
        this.col = col;
        this.type = type;
        this.length = length;
        this.width = width;
        this.nullAble = nullAble;

        if (possibleValues != null && possibleValues.length > 0) {
            for (int i = 0; i < possibleValues.length / 2; i++) {
                this.possibleValues.put(possibleValues[2 * i], possibleValues[2 * i + 1]);
            }
        }
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the col
     */
    public String getCol() {
        return col;
    }

    /**
     * @param col
     *            the col to set
     */
    public void setCol(String col) {
        this.col = col;
    }

    /**
     * @return the type
     */
    public ENUM_TYPE getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(ENUM_TYPE type) {
        this.type = type;
    }

    /**
     * @return the length
     */
    public Integer getLength() {
        return length;
    }

    /**
     * @param length
     *            the length to set
     */
    public void setLength(Integer length) {
        this.length = length;
    }

    /**
     * @return the nullAble
     */
    public boolean isNullAble() {
        return nullAble;
    }

    /**
     * @param nullAble
     *            the nullAble to set
     */
    public void setNullAble(boolean nullAble) {
        this.nullAble = nullAble;
    }

    /**
     * @return the possibleValues
     */
    public Map<String, String> getPossibleValues() {
        return possibleValues;
    }

    /**
     * @param possibleValues
     *            the possibleValues to set
     */
    public void setPossibleValues(Map<String, String> possibleValues) {
        this.possibleValues = possibleValues;
    }

    /**
     * @return the relCol
     */
    public String getRelCol() {
        return relCol;
    }

    /**
     * @param relCol
     *            the relCol to set
     */
    public void setRelCol(String relCol) {
        this.relCol = relCol;
    }

    /**
     * @return the relValueCode
     */
    public String getRelValueCode() {
        return relValueCode;
    }

    /**
     * @param relValueCode
     *            the relValueCode to set
     */
    public void setRelValueCode(String relValueCode) {
        this.relValueCode = relValueCode;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

}
