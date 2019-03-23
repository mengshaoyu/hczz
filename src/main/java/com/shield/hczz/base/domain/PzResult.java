package com.shield.hczz.base.domain;

import java.util.ArrayList;
import java.util.List;

import com.shield.frame.base.domain.Attach;

public class PzResult {
    private String resultId;
    
    private String id;	//反馈记录id

    private String clueId;

    private String caseId;

    private String caseNo;

    private String pzId;

    private String resultDesc;

    private String resultDate;

    private String resultBy;

    private int isFiles;

    private String receiverBy;
    
    private String createBy;

    private String createDt;

    private String updateDt;

    private String updateBy;

    private String deleteBy;

    private String actByType;
    
    private String attchlist;//附件名字集合，导出用
    
	private List<Attach> att = new ArrayList<>();

    private List<String> attIds = new ArrayList<>();
    
    private String resultRemark;	//详情描述
    
    private String resultDescReport;//反馈报告中显示反馈内容
    private String resultRemarkReport;//反馈报告中显示详情描述
    
    

    public String getResultRemark() {
		return resultRemark;
	}

	public void setResultRemark(String resultRemark) {
		this.resultRemark = resultRemark;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAttchlist() {
        return attchlist;
    }

    public void setAttchlist(String attchlist) {
        this.attchlist = attchlist;
    }

    public String getResultId() {
        return resultId;
    }

    public void setResultId(String resultId) {
        this.resultId = resultId == null ? null : resultId.trim();
    }

    public String getClueId() {
        return clueId;
    }

    public void setClueId(String clueId) {
        this.clueId = clueId == null ? null : clueId.trim();
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId == null ? null : caseId.trim();
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo == null ? null : caseNo.trim();
    }

    public String getPzId() {
        return pzId;
    }

    public void setPzId(String pzId) {
        this.pzId = pzId == null ? null : pzId.trim();
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc == null ? null : resultDesc.trim();
    }

    public String getResultBy() {
        return resultBy;
    }

    public void setResultBy(String resultBy) {
        this.resultBy = resultBy == null ? null : resultBy.trim();
    }

    public String getReceiverBy() {
        return receiverBy;
    }

    public void setReceiverBy(String receiverBy) {
        this.receiverBy = receiverBy == null ? null : receiverBy.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
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

    public String getResultDate() {
        return resultDate;
    }

    public void setResultDate(String resultDate) {
        this.resultDate = resultDate;
    }

    public int getIsFiles() {
        return isFiles;
    }

    public void setIsFiles(int isFiles) {
        this.isFiles = isFiles;
    }

    public String getCreateDt() {
        return createDt;
    }

    public void setCreateDt(String createDt) {
        this.createDt = createDt;
    }

    public String getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(String updateDt) {
        this.updateDt = updateDt;
    }

    public List<Attach> getAtt() {
        return att;
    }

    public void setAtt(List<Attach> att) {
        this.att = att;
    }

    public List<String> getAttIds() {
        return attIds;
    }

    public void setAttIds(List<String> attIds) {
        this.attIds = attIds;
    }

	public String getResultDescReport() {
		return resultDescReport;
	}

	public void setResultDescReport(String resultDescReport) {
		this.resultDescReport = resultDescReport;
	}

	public String getResultRemarkReport() {
		return resultRemarkReport;
	}

	public void setResultRemarkReport(String resultRemarkReport) {
		this.resultRemarkReport = resultRemarkReport;
	}

}