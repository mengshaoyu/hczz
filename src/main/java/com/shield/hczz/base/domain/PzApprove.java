package com.shield.hczz.base.domain;

import java.util.List;

/**
 * 配侦审批表
 */
public class PzApprove {

    private String pzApproveId;
    private String applyId;
    private String flowId;
    private Integer userId;
    private Integer roleId;
    private String result = "0";
    private String reason;

    private String createDt;
    private String createBy;
    private String updateDt;
    private String updateBy;
    private String deleteBy;
    private List<ClueInfo> cluelist;
    private String actByType = "B";
    private String remark;
    

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getActByType() {
        return actByType;
    }

    public void setActByType(String actByType) {
        this.actByType = actByType;
    }

    public String getPzApproveId() {
        return pzApproveId;
    }

    public void setPzApproveId(String pzApproveId) {
        this.pzApproveId = pzApproveId;
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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

	public List<ClueInfo> getCluelist() {
		return cluelist;
	}

	public void setCluelist(List<ClueInfo> cluelist) {
		this.cluelist = cluelist;
	}
    
    

}
