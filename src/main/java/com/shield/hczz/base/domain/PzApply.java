package com.shield.hczz.base.domain;

/**
 * 配侦申请表
 */
public class PzApply {

    private String pzApplyId;
    private String flowId;
    private String pzApplyNo;// 配侦申请编号
    private String pzApplyType;// 业务类型
    private String pzApplyGrade;// 任务等级
    private String caseId;// 案件ID
    private String advanceEndTime;// 预计有效结束时间
    private String isOpen;
    private String pzMainAccept;// 主办人
    private String remark;// 备注

    private String landing;// 落地情况反馈
    private String feedbackUsed;// 反馈效能
    private String usability;// 使用效能
    private String feedbackAging;// 反馈时长
    private String signAging;// 签收时长

    private String createDt;// 提报时间
    private String createBy;// 申请人ID
    private String updateDt;
    private String updateBy;
    private String deleteBy;
    private String actByType = "B";

    public String getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
    }

    public String getPzMainAccept() {
        return pzMainAccept;
    }

    public void setPzMainAccept(String pzMainAccept) {
        this.pzMainAccept = pzMainAccept;
    }

    public String getPzApplyId() {
        return pzApplyId;
    }

    public void setPzApplyId(String pzApplyId) {
        this.pzApplyId = pzApplyId;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getPzApplyNo() {
        return pzApplyNo;
    }

    public void setPzApplyNo(String pzApplyNo) {
        this.pzApplyNo = pzApplyNo;
    }

    public String getPzApplyType() {
        return pzApplyType;
    }

    public void setPzApplyType(String pzApplyType) {
        this.pzApplyType = pzApplyType;
    }

    public String getPzApplyGrade() {
        return pzApplyGrade;
    }

    public void setPzApplyGrade(String pzApplyGrade) {
        this.pzApplyGrade = pzApplyGrade;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getAdvanceEndTime() {
        return advanceEndTime;
    }

    public void setAdvanceEndTime(String advanceEndTime) {
        this.advanceEndTime = advanceEndTime;
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

    public String getLanding() {
        return landing;
    }

    public void setLanding(String landing) {
        this.landing = landing;
    }

    public String getFeedbackUsed() {
        return feedbackUsed;
    }

    public void setFeedbackUsed(String feedbackUsed) {
        this.feedbackUsed = feedbackUsed;
    }

    public String getUsability() {
        return usability;
    }

    public void setUsability(String usability) {
        this.usability = usability;
    }

    public String getFeedbackAging() {
        return feedbackAging;
    }

    public void setFeedbackAging(String feedbackAging) {
        this.feedbackAging = feedbackAging;
    }

    public String getSignAging() {
        return signAging;
    }

    public void setSignAging(String signAging) {
        this.signAging = signAging;
    }

}
