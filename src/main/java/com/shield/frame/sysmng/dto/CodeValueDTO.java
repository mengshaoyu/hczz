package com.shield.frame.sysmng.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.shield.frame.common.BaseTO;

public class CodeValueDTO extends BaseTO {

    private BigDecimal pk;

    private String valueDesc;

    private String codeValue;

    private BigDecimal typeId;

    private String deleteBy;

    private String actByType;

    public BigDecimal getPk() {
        return pk;
    }

    public void setPk(BigDecimal pk) {
        this.pk = pk;
    }

    public String getValueDesc() {
        return valueDesc;
    }

    public void setValueDesc(String valueDesc) {
        this.valueDesc = valueDesc;
    }

    public String getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }

    public BigDecimal getTypeId() {
        return typeId;
    }

    public void setTypeId(BigDecimal typeId) {
        this.typeId = typeId;
    }

    public String getDeleteBy() {
        return deleteBy;
    }

    public void setDeleteBy(String deleteBy) {
        this.deleteBy = deleteBy;
    }

    public String getActByType() {
        return actByType;
    }

    public void setActByType(String actByType) {
        this.actByType = actByType;
    }

}
