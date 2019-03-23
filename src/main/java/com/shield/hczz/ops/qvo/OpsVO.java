package com.shield.hczz.ops.qvo;

import com.shield.hczz.base.domain.Ops;

/**
 * 运维意见 关联查询 类
 * 
 * @author K.
 * @description
 */
public class OpsVO extends Ops {

	private String userName;

	private String deptName;

	private String userNo;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

}
