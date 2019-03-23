package com.shield.hczz.apply.qvo;

import java.util.List;

import com.shield.frame.sysmng.qvo.ToolBarVO;

public class PzApplyVO {

    private String pzApplyId;
    private String pzApplyNo;
    private String pzApplyStatus;
    private String pzApplyType;
    private String pzApplyGrade;
    private String pzApplyGradeName;
    private String caseId;
    private String caseNo;
    private String caseName;
    private String caseType; // 案件类别
    private String caseTypeDesc;// 类别文字描述
    private String incidentDate;// 案发时间
    private String incidentPlace;// 案发地点
    private String caseDesc;// 简要案情
    private String userId;
    private String username;
    private String statusDesc;
    private String typeDesc;
    private String gradeDesc;
    private String deptId;
    private String deptName;
    private String submitTime;//申请时间
    private String acceptTime;// 受理时间
    private String receiveTime;// 签收时间
    private String feedbackTime;// 反馈时间
    private String flowId;
    private String flowStatus;
    private String flowDesc;//流程名称
    private String pzMainAccept;

    private String flowTime;// 反馈时效
    private double timeDiff;//反馈时差，负数表示提前完成
    private String feedBackPolice;// 反馈人
    private String policePhone;// 反馈人联系电话
    private String backDate;//反馈日期
    
    private String sumUp;//综述

    private String isOpen;
    
    private List<ToolBarVO> toolbarList;

    public List<ToolBarVO> getToolbarList() {
        return toolbarList;
    }

    public void setToolbarList(List<ToolBarVO> toolbarList) {
        this.toolbarList = toolbarList;
    }

    public String getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
    }

    public String getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(String acceptTime) {
        this.acceptTime = acceptTime;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getFeedbackTime() {
        return feedbackTime;
    }

    public void setFeedbackTime(String feedbackTime) {
        this.feedbackTime = feedbackTime;
    }

    public String getFlowStatus() {
        return flowStatus;
    }

    public void setFlowStatus(String flowStatus) {
        this.flowStatus = flowStatus;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getCaseTypeDesc() {
        return caseTypeDesc;
    }

    public void setCaseTypeDesc(String caseTypeDesc) {
        this.caseTypeDesc = caseTypeDesc;
    }

    public String getIncidentDate() {
        return incidentDate;
    }

    public void setIncidentDate(String incidentDate) {
        this.incidentDate = incidentDate;
    }

    public String getIncidentPlace() {
        return incidentPlace;
    }

    public void setIncidentPlace(String incidentPlace) {
        this.incidentPlace = incidentPlace;
    }

    public String getCaseDesc() {
        return caseDesc;
    }

    public void setCaseDesc(String caseDesc) {
        this.caseDesc = caseDesc;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getPzApplyId() {
        return pzApplyId;
    }

    public void setPzApplyId(String pzApplyId) {
        this.pzApplyId = pzApplyId;
    }

    public String getPzApplyNo() {
        return pzApplyNo;
    }

    public void setPzApplyNo(String pzApplyNo) {
        this.pzApplyNo = pzApplyNo;
    }

    public String getPzApplyStatus() {
        return pzApplyStatus;
    }

    public void setPzApplyStatus(String pzApplyStatus) {
        this.pzApplyStatus = pzApplyStatus;
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

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public String getGradeDesc() {
        return gradeDesc;
    }

    public void setGradeDesc(String gradeDesc) {
        this.gradeDesc = gradeDesc;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    public String getPzMainAccept() {
        return pzMainAccept;
    }

    public void setPzMainAccept(String pzMainAccept) {
        this.pzMainAccept = pzMainAccept;
    }

    public String getFlowTime() {
        return flowTime;
    }

    public void setFlowTime(String flowTime) {
        this.flowTime = flowTime;
    }

    public String getFeedBackPolice() {
        return feedBackPolice;
    }

    public void setFeedBackPolice(String feedBackPolice) {
        this.feedBackPolice = feedBackPolice;
    }

    public String getPolicePhone() {
        return policePhone;
    }

    public void setPolicePhone(String policePhone) {
        this.policePhone = policePhone;
    }

    public String getPzApplyGradeName() {
        return pzApplyGradeName;
    }

    public void setPzApplyGradeName(String pzApplyGradeName) {
        this.pzApplyGradeName = pzApplyGradeName;
    }

	public String getFlowDesc() {
		return flowDesc;
	}

	public void setFlowDesc(String flowDesc) {
		this.flowDesc = flowDesc;
	}

	public double getTimeDiff() {
		return timeDiff;
	}

	public void setTimeDiff(double timeDiff) {
		this.timeDiff = timeDiff;
	}

	public String getSumUp() {
		return sumUp;
	}

	public void setSumUp(String sumUp) {
		this.sumUp = sumUp;
	}

	public String getBackDate() {
		return backDate;
	}

	public void setBackDate(String backDate) {
		this.backDate = backDate;
	}
    

}
