package com.shield.frame.common;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

import org.apache.commons.beanutils.PropertyUtils;

public abstract class BaseTO implements Serializable {

    private String createBy;

    private Date createDt;

    private String updateBy;

    private Date updateDt;

    private String actByType = "B";

    private Integer version = 0;

    private String deleteBy;

    private String lastSortFieldName;

    private String sortByAD;

    /**
     * 返回lastSortFieldName
     * 
     * @return lastSortFieldName
     */
    public String getLastSortFieldName() {
        return lastSortFieldName;
    }

    /**
     * 设定lastSortFieldName
     * 
     * @param lastSortFieldName
     */
    public void setLastSortFieldName(String lastSortFieldName) {
        this.lastSortFieldName = lastSortFieldName;
    }

    /**
     * 返回sortByAD
     * 
     * @return sortByAD
     */
    public String getSortByAD() {
        return sortByAD;
    }

    /**
     * 设定sortByAD
     * 
     * @param sortByAD
     */
    public void setSortByAD(String sortByAD) {
        this.sortByAD = sortByAD;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(Date updateDt) {
        this.updateDt = updateDt;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * Returns a multi-line String with key=value pairs.
     * 
     * @return a String representation of this class.
     */
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("\n");
        sb.append("\t" + this.getClass().getName() + " {\n");
        Class cls = this.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            String propertyName = fields[i].getName();
            if (PropertyUtils.isReadable(this, propertyName)) {
                try {
                    Object obj = PropertyUtils.getProperty(this, propertyName);
                    sb.append("\t\t" + propertyName);
                    sb.append("=[");
                    if (obj != null) {
                        sb.append(obj.toString());
                    }
                    sb.append("]\n");
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        sb.append("\t}");
        return sb.toString();
    }

    /**
     * Compares object equality. When using Hibernate, the primary key should
     * not be a part of this comparison.
     * 
     * @param o
     *            object to compare to
     * @return true/false based on equality tests
     */
    public boolean equals(Object o) {
        return super.equals(o);
    }

    public String getDeleteBy() {
        return deleteBy;
    }

    public void setDeleteBy(String deleteBy) {
        this.deleteBy = deleteBy;
    }

    /**
     * When you override equals, you should override hashCode. See "Why are
     * equals() and hashCode() importation" for more information:
     * http://www.hibernate.org/109.html
     * 
     * @return hashCode
     */
    public int hashCode() {
        return super.hashCode();
    }

    public String getActByType() {
        return actByType;
    }

    public void setActByType(String actByType) {
        this.actByType = actByType;
    }
}
