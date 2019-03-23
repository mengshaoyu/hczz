package com.shield.hczz.approve.qvo;

public class PzApproveQO {

    private String pzApproveId;
    private String userId;
    private String roleId;
    private String applyId;
    private String result;
    private String flowId;
    private String deptId;
    private String userName;

    public PzApproveQO() {
    }

    public PzApproveQO(String pzApproveId) {
        this.pzApproveId = pzApproveId;
    }

    public PzApproveQO(String userId, String applyId, String result) {
        this.userId = userId;
        this.applyId = applyId;
        this.result = result;
    }

    public PzApproveQO(String userId, String applyId, String flowId, String result) {
        this.userId = userId;
        this.applyId = applyId;
        this.flowId = flowId;
        this.result = result;
    }

    public PzApproveQO(String userId, String roleId, String applyId, String flowId, String result,String deptId,String userName) {
        this.userId = userId;
        this.roleId = roleId;
        this.applyId = applyId;
        this.flowId = flowId;
        this.result = result;
        this.deptId=deptId;
        this.userName=userName;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getPzApproveId() {
        return pzApproveId;
    }

    public void setPzApproveId(String pzApproveId) {
        this.pzApproveId = pzApproveId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
    
}
