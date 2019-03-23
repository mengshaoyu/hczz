package com.shield.hczz.base.domain;

import java.util.UUID;

import com.shield.hczz.caseinfo.anno.ExcelField;

/**
 * 案件信息表
 */
public class CaseInfo {

	private String id;//一案一档使用,误用
    private String caseId = UUID.randomUUID().toString().replaceAll("-", "");
    @ExcelField(field = "发案地点")
    private String incidentPlace;// 发案地点
    private String incidentDate;// 案发时间
    private String incidentDateBegin;// 案发时间起
    private String incidentDateEnd;// 案发时间止
    private String belongArea;// 所辖区域
    @ExcelField(field = "案件编号")
    private String caseNo;// 案件编号
    @ExcelField(field = "案件名称")
    private String caseName;// 案件名称
    @ExcelField(field = "嫌疑人姓名")
    private String criminalName;// 嫌疑人名称
    @ExcelField(field = "简要案情")
    private String caseDesc;// 简要案情
    @ExcelField(field = "受理时间")
    private String acceptDate;// 受理时间
    @ExcelField(field = "受理单位")
    private String acceptUnit;// 受理单位
    @ExcelField(field = "立案时间")
    private String caseDate;// 立案时间
    @ExcelField(field = "主办人")
    private String handlePeople;// 主办人
    @ExcelField(field = "主办人身份证号")
    private String handlePeopleId;
    @ExcelField(field = "主办单位")
    private String handleUnit;// 主办单位
    private String handleTel;// 主办人电话号码
    @ExcelField(field = "案件状态")
    private String caseStatus;// 案件状态
    private String caseType;// 案件类型
    @ExcelField(field = "案件类型")
    private String caseTypeImp;
    @ExcelField(field = "承办单位")
    private String undertakeUnit;// 承办单位
    @ExcelField(field = "承办区域")
    private String undertakeArea;// 承办单位区域
    @ExcelField(field = "承办单位区域")
    private String undertakeAreaId;// 承办单位区域ID
    @ExcelField(field = "警情编号")
    private String callNo;// 警情编号
    @ExcelField(field = "处罚时间")
    private String punishDate;// 处罚时间
    @ExcelField(field = "录入时间")
    private String createDt;
    private String editFlag;
    private String createBy;
    private String updateDt;
    private String updateBy;
    private String deleteBy;
    private String actByType = "B";
    
    private String jqNo;
    private String latX;
    private String lonY;
    private String caseAjly;
    /**
     * 合成提报新增字段
     */
    @ExcelField(field = "警情编号")
    private String situationNo;// 警情编号
    @ExcelField(field = "案件来源")
    private String caseSource;// 案件来源
    @ExcelField(field = "案由")
    private String caseReason;// 案由
    @ExcelField(field = "地里坐标")
    private String coordinate;// 地里坐标
    
    /**
     * 案件导入
     * @return
     */
    private String	errorMsg;
    private String 	errorNum;

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getActByType() {
        return actByType;
    }

    public void setActByType(String actByType) {
        this.actByType = actByType;
    }

    public String getEditFlag() {
        return editFlag;
    }

    public void setEditFlag(String editFlag) {
        this.editFlag = editFlag;
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

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getIncidentPlace() {
        return incidentPlace;
    }

    public void setIncidentPlace(String incidentPlace) {
        this.incidentPlace = incidentPlace;
    }

    public String getIncidentDate() {
        return incidentDate;
    }

    public void setIncidentDate(String incidentDate) {
        this.incidentDate = incidentDate;
    }

    public String getIncidentDateBegin() {
        return incidentDateBegin;
    }

    public void setIncidentDateBegin(String incidentDateBegin) {
        this.incidentDateBegin = incidentDateBegin;
    }

    public String getIncidentDateEnd() {
        return incidentDateEnd;
    }

    public void setIncidentDateEnd(String incidentDateEnd) {
        this.incidentDateEnd = incidentDateEnd;
    }

    public String getBelongArea() {
        return belongArea;
    }

    public void setBelongArea(String belongArea) {
        this.belongArea = belongArea;
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

    public String getCriminalName() {
        return criminalName;
    }

    public void setCriminalName(String criminalName) {
        this.criminalName = criminalName;
    }

    public String getCaseDesc() {
        return caseDesc;
    }

    public void setCaseDesc(String caseDesc) {
        this.caseDesc = caseDesc;
    }

    public String getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(String acceptDate) {
        this.acceptDate = acceptDate;
    }

    public String getAcceptUnit() {
        return acceptUnit;
    }

    public void setAcceptUnit(String acceptUnit) {
        this.acceptUnit = acceptUnit;
    }

    public String getCaseDate() {
        return caseDate;
    }

    public void setCaseDate(String caseDate) {
        this.caseDate = caseDate;
    }

    public String getHandlePeople() {
        return handlePeople;
    }

    public void setHandlePeople(String handlePeople) {
        this.handlePeople = handlePeople;
    }

    public String getHandlePeopleId() {
        return handlePeopleId;
    }

    public void setHandlePeopleId(String handlePeopleId) {
        this.handlePeopleId = handlePeopleId;
    }

    public String getHandleUnit() {
        return handleUnit;
    }

    public void setHandleUnit(String handleUnit) {
        this.handleUnit = handleUnit;
    }

    public String getHandleTel() {
        return handleTel;
    }

    public void setHandleTel(String handleTel) {
        this.handleTel = handleTel;
    }

    public String getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getUndertakeUnit() {
        return undertakeUnit;
    }

    public void setUndertakeUnit(String undertakeUnit) {
        this.undertakeUnit = undertakeUnit;
    }

    public String getUndertakeArea() {
        return undertakeArea;
    }

    public void setUndertakeArea(String undertakeArea) {
        this.undertakeArea = undertakeArea;
    }

    public String getUndertakeAreaId() {
        return undertakeAreaId;
    }

    public void setUndertakeAreaId(String undertakeAreaId) {
        this.undertakeAreaId = undertakeAreaId;
    }

    public String getCallNo() {
        return callNo;
    }

    public void setCallNo(String callNo) {
        this.callNo = callNo;
    }

    public String getPunishDate() {
        return punishDate;
    }

    public void setPunishDate(String punishDate) {
        this.punishDate = punishDate;
    }

    public String getCaseTypeImp() {
        return caseTypeImp;
    }

    public void setCaseTypeImp(String caseTypeImp) {
        this.caseTypeImp = caseTypeImp;
    }

    public String getJqNo() {
        return jqNo;
    }

    public void setJqNo(String jqNo) {
        this.jqNo = jqNo;
    }

    public String getLatX() {
        return latX;
    }

    public void setLatX(String latX) {
        this.latX = latX;
    }

    public String getLonY() {
        return lonY;
    }

    public void setLonY(String lonY) {
        this.lonY = lonY;
    }

    public String getSituationNo() {
        return situationNo;
    }

    public void setSituationNo(String situationNo) {
        this.situationNo = situationNo;
    }

    public String getCaseSource() {
        return caseSource;
    }

    public void setCaseSource(String caseSource) {
        this.caseSource = caseSource;
    }

    public String getCaseReason() {
        return caseReason;
    }

    public void setCaseReason(String caseReason) {
        this.caseReason = caseReason;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getCaseAjly() {
        return caseAjly;
    }

    public void setCaseAjly(String caseAjly) {
        this.caseAjly = caseAjly;
    }

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getErrorNum() {
		return errorNum;
	}

	public void setErrorNum(String errorNum) {
		this.errorNum = errorNum;
	}

}
