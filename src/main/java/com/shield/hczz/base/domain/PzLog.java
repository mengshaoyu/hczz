package com.shield.hczz.base.domain;

import java.math.BigDecimal;
import java.util.Date;

public class PzLog {
    private BigDecimal featurePersonId;

    private String clueNoRef;

    private String caseNoRef;

    private String pzApplyId;

    private String flowId;

    private String taskStatus;

    private String nextBy;

    private String taskDesc;

    private String createBy;

    private Date createDt;

    private Date updateDt;

    private String updateBy;

    private String deleteBy;

    private String actByType;

    public BigDecimal getFeaturePersonId() {
        return featurePersonId;
    }

    public void setFeaturePersonId(BigDecimal featurePersonId) {
        this.featurePersonId = featurePersonId;
    }

    public String getClueNoRef() {
        return clueNoRef;
    }

    public void setClueNoRef(String clueNoRef) {
        this.clueNoRef = clueNoRef == null ? null : clueNoRef.trim();
    }

    public String getCaseNoRef() {
        return caseNoRef;
    }

    public void setCaseNoRef(String caseNoRef) {
        this.caseNoRef = caseNoRef == null ? null : caseNoRef.trim();
    }

    public String getPzApplyId() {
        return pzApplyId;
    }

    public void setPzApplyId(String pzApplyId) {
        this.pzApplyId = pzApplyId == null ? null : pzApplyId.trim();
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId == null ? null : flowId.trim();
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus == null ? null : taskStatus.trim();
    }

    public String getNextBy() {
        return nextBy;
    }

    public void setNextBy(String nextBy) {
        this.nextBy = nextBy == null ? null : nextBy.trim();
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc == null ? null : taskDesc.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public Date getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(Date updateDt) {
        this.updateDt = updateDt;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public String getDeleteBy() {
        return deleteBy;
    }

    public void setDeleteBy(String deleteBy) {
        this.deleteBy = deleteBy == null ? null : deleteBy.trim();
    }

    public String getActByType() {
        return actByType;
    }

    public void setActByType(String actByType) {
        this.actByType = actByType == null ? null : actByType.trim();
    }
}