package com.shield.hczz.base.domain;

import java.util.ArrayList;
import java.util.List;
import com.shield.frame.base.domain.Attach;

public class ClueInfo {
	
    private String pzId;

    private String clueId;

    private String caseId;

    private String caseNo;

    private String clueName;

    private int clueType;

    private String clueSource;

    private String clueDesc;

    private String clueTableName;

    private String pzType;

    private String pzTypeName;

    private String pzTypeDetail;

    private String pzTypeDetailName;

    private String taskGlassId;

    private String createBy;

    private String createDt;

    private String updateDt;

    private String updateBy;

    private String deleteBy;
    
    private String actByType;
    
    private String flag;  //校验线索是否完成
    
    private String flag1;  //校验展示按钮
    
    private String clueAuxiluary;   //办理人ID
    
    private String userId;  //暂存当前用户，线索反馈校验用
    
    private String pzResultCreateBy;  //暂存反馈记录创建人，线索反馈校验用
    
    private String clueSumup;   //线索综述
    
    private String serviceRequest;	//查询要求
    
    private String att2[];
    
	public String[] getAtt2() {
		return att2;
	}

	public void setAtt2(String[] att2) {
		this.att2 = att2;
	}

	public String getServiceRequest() {
		return serviceRequest;
	}

	public void setServiceRequest(String serviceRequest) {
		this.serviceRequest = serviceRequest;
	}

	public String getClueSumup() {
		return clueSumup;
	}

    public void setClueSumup(String clueSumup) {
        this.clueSumup = clueSumup;
    }


    public String getFlag1() {
		return flag1;
	}

	public void setFlag1(String flag1) {
		this.flag1 = flag1;
	}


	private String index;
    
    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getUserId() {

        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPzResultCreateBy() {
        return pzResultCreateBy;
    }

    public void setPzResultCreateBy(String pzResultCreateBy) {
        this.pzResultCreateBy = pzResultCreateBy;
    }

    public String getClueAuxiluary() {
        return clueAuxiluary;
    }

    public void setClueAuxiluary(String clueAuxiluary) {
        this.clueAuxiluary = clueAuxiluary;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    private List<Attach> att = new ArrayList<>();
    private List<PzResult> pzResult = new ArrayList<>();// 反馈信息
    private List<String> lyzm = new ArrayList<>();// 来源证明


    public List<PzResult> getPzResult() {
        return pzResult;
    }

    public void setPzResult(List<PzResult> pzResult) {
        this.pzResult = pzResult;
    }

    public String getPzId() {
        return pzId;
    }

    public void setPzId(String pzId) {
        this.pzId = pzId;
    }

    public List<String> getLyzm() {
        return lyzm;
    }

    public void setLyzm(List<String> lyzm) {
        this.lyzm = lyzm;
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

    public String getClueName() {
        return clueName;
    }

    public void setClueName(String clueName) {
        this.clueName = clueName == null ? null : clueName.trim();
    }

    public int getClueType() {
        return clueType;
    }

    public void setClueType(int clueType) {
        this.clueType = clueType;
    }

    public String getClueSource() {
        return clueSource;
    }

    public void setClueSource(String clueSource) {
        this.clueSource = clueSource == null ? null : clueSource.trim();
    }

    public String getClueDesc() {
        return clueDesc;
    }

    public void setClueDesc(String clueDesc) {
        this.clueDesc = clueDesc == null ? null : clueDesc.trim();
    }

    public String getClueTableName() {
        return clueTableName;
    }

    public void setClueTableName(String clueTableName) {
        this.clueTableName = clueTableName == null ? null : clueTableName.trim();
    }

    public String getPzType() {
        return pzType;
    }

    public void setPzType(String pzType) {
        this.pzType = pzType == null ? null : pzType.trim();
    }

    public String getPzTypeDetail() {
        return pzTypeDetail;
    }

    public void setPzTypeDetail(String pzTypeDetail) {
        this.pzTypeDetail = pzTypeDetail == null ? null : pzTypeDetail.trim();
    }

    public String getTaskGlassId() {
        return taskGlassId;
    }

    public void setTaskGlassId(String taskGlassId) {
        this.taskGlassId = taskGlassId == null ? null : taskGlassId.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
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

    public List<Attach> getAtt() {
        return att;
    }

    public void setAtt(List<Attach> att) {
        this.att = att;
    }

    public String getPzTypeName() {
        return pzTypeName;
    }

    public void setPzTypeName(String pzTypeName) {
        this.pzTypeName = pzTypeName;
    }

    public String getPzTypeDetailName() {
        return pzTypeDetailName;
    }

    public void setPzTypeDetailName(String pzTypeDetailName) {
        this.pzTypeDetailName = pzTypeDetailName;
    }

}