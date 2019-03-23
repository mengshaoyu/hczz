package com.shield.hczz.flow.qvo;

import java.util.List;

public class TaskFlowVO {

    private String featurePersonId;// 配侦记录主键
    private String createDt;
    private String deptName;
    private String username;
    private String taskDesc;
    private String taskStatus;
    private String mobilePhone;
    
    private String status;//任务状态
    private List<TaskFlowVO> flowList;
    private String userId;
    
    private String policeType;

    public String getFeaturePersonId() {
        return featurePersonId;
    }

    public void setFeaturePersonId(String featurePersonId) {
        this.featurePersonId = featurePersonId;
    }

    public String getCreateDt() {
        return createDt;
    }

    public void setCreateDt(String createDt) {
        this.createDt = createDt;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public List<TaskFlowVO> getFlowList() {
		return flowList;
	}

	public void setFlowList(List<TaskFlowVO> flowList) {
		this.flowList = flowList;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPoliceType() {
		return policeType;
	}

	public void setPoliceType(String policeType) {
		this.policeType = policeType;
	}

}
