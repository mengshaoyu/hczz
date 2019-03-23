package com.shield.hczz.base.domain;

import java.util.UUID;

public class Effect {

    private String pzEffectId = UUID.randomUUID().toString().replaceAll("-", "");
    private String pzApplyId;
    private Integer total;
    private String effectType;
    private int type1;//图侦
    private int type2;//情报
    private int type3;//技侦
    private int type4;//网侦
    private int type5;//刑警
    private String remark;
    private String createDt;// 提报时间
    private String createBy;// 申请人ID
    private String updateDt;
    private String updateBy;
    private String deleteBy;
    private String actByType = "B";

    public String getPzEffectId() {
        return pzEffectId;
    }

    public void setPzEffectId(String pzEffectId) {
        this.pzEffectId = pzEffectId;
    }

    public String getPzApplyId() {
        return pzApplyId;
    }

    public void setPzApplyId(String pzApplyId) {
        this.pzApplyId = pzApplyId;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getEffectType() {
        return effectType;
    }

    public void setEffectType(String effectType) {
        this.effectType = effectType;
    }

    public int getType1() {
        return type1;
    }

    public void setType1(int type1) {
        this.type1 = type1;
    }

    public int getType2() {
        return type2;
    }

    public void setType2(int type2) {
        this.type2 = type2;
    }

    public int getType3() {
        return type3;
    }

    public void setType3(int type3) {
        this.type3 = type3;
    }

    public int getType4() {
        return type4;
    }

    public void setType4(int type4) {
        this.type4 = type4;
    }

    public int getType5() {
        return type5;
    }

    public void setType5(int type5) {
        this.type5 = type5;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateDt() {
        return createDt;
    }

    public void setCreateDt(String createDt) {
        this.createDt = createDt;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(String updateDt) {
        this.updateDt = updateDt;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
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
