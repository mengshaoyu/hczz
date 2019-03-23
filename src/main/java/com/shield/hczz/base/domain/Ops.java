package com.shield.hczz.base.domain;

/**
 * @description 运维意见 类
 */
public class Ops {

	private String sopOpsId; // 主键

	private String sopOpsContent; // 意见内容

	private String sopOpsState; // 意见反馈 回复状态

	private String sopOpsResponse; // 意见反馈 回复内容

	private String createBy; // 意见创建者

	private String createDt; // 意见创建时间

	private String updateDt; // 意见更新时间

	private String updateBy; // 意见更新者

	private String deleteBy; // 意见删除者
	
	private String actByType = "B"; //

	public String getSopOpsId() {
		return sopOpsId;
	}

	public void setSopOpsId(String sopOpsId) {
		this.sopOpsId = sopOpsId;
	}

	public String getSopOpsContent() {
		return sopOpsContent;
	}

	public void setSopOpsContent(String sopOpsContent) {
		this.sopOpsContent = sopOpsContent;
	}

	public String getSopOpsState() {
		return sopOpsState;
	}

	public void setSopOpsState(String sopOpsState) {
		this.sopOpsState = sopOpsState;
	}

	public String getSopOpsResponse() {
		return sopOpsResponse;
	}

	public void setSopOpsResponse(String sopOpsResponse) {
		this.sopOpsResponse = sopOpsResponse;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
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
