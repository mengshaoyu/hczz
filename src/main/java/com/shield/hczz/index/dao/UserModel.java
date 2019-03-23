package com.shield.hczz.index.dao;

import java.util.Date;

public class UserModel {
	
		private Integer userId;

	    private String userName;

	    private String gender;

	    private Integer age;

	    private String loginAccount;

	    private String loginPwd;

	    private String idNo;

	    private String mobilePhone;

	    private String emial;

	    private String curState;

	    private Integer loginAmount;

	    private Integer deptId;
	    
	    private String deptName;

	    private String userNo;

	    private String address;

	    private String remark;

	    private String policeType;

	    private String createBy;
	    
	    private Date createDt;
	    
	    private String updateBy;
	    
	    private Date updateDt;
	    
	    private String deleteBy;
	    
	    private String actByType;

	    
	    
		public String getDeptName() {
			return deptName;
		}

		public void setDeptName(String deptName) {
			this.deptName = deptName;
		}

		public Integer getUserId() {
			return userId;
		}

		public void setUserId(Integer userId) {
			this.userId = userId;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}

		public String getLoginAccount() {
			return loginAccount;
		}

		public void setLoginAccount(String loginAccount) {
			this.loginAccount = loginAccount;
		}

		public String getLoginPwd() {
			return loginPwd;
		}

		public void setLoginPwd(String loginPwd) {
			this.loginPwd = loginPwd;
		}

		public String getIdNo() {
			return idNo;
		}

		public void setIdNo(String idNo) {
			this.idNo = idNo;
		}

		public String getMobilePhone() {
			return mobilePhone;
		}

		public void setMobilePhone(String mobilePhone) {
			this.mobilePhone = mobilePhone;
		}

		public String getEmial() {
			return emial;
		}

		public void setEmial(String emial) {
			this.emial = emial;
		}

		public String getCurState() {
			return curState;
		}

		public void setCurState(String curState) {
			this.curState = curState;
		}

		public Integer getLoginAmount() {
			return loginAmount;
		}

		public void setLoginAmount(Integer loginAmount) {
			this.loginAmount = loginAmount;
		}

		public Integer getDeptId() {
			return deptId;
		}

		public void setDeptId(Integer deptId) {
			this.deptId = deptId;
		}

		public String getUserNo() {
			return userNo;
		}

		public void setUserNo(String userNo) {
			this.userNo = userNo;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public String getPoliceType() {
			return policeType;
		}

		public void setPoliceType(String policeType) {
			this.policeType = policeType;
		}

		public String getCreateBy() {
			return createBy;
		}

		public void setCreateBy(String createBy) {
			this.createBy = createBy;
		}

		public Date getCreateDt() {
			return createDt;
		}

		public void setCreateDt(Date createDt) {
			this.createDt = createDt;
		}

		public String getUpdateBy() {
			return updateBy;
		}

		public void setUpdateBy(String updateBy) {
			this.updateBy = updateBy;
		}

		public Date getUpdateDt() {
			return updateDt;
		}

		public void setUpdateDt(Date updateDt) {
			this.updateDt = updateDt;
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

		@Override
		public String toString() {
			return "UserModel [userId=" + userId + ", userName=" + userName
					+ ", gender=" + gender + ", age=" + age + ", loginAccount="
					+ loginAccount + ", loginPwd=" + loginPwd + ", idNo="
					+ idNo + ", mobilePhone=" + mobilePhone + ", emial="
					+ emial + ", curState=" + curState + ", loginAmount="
					+ loginAmount + ", deptId=" + deptId + ", userNo=" + userNo
					+ ", address=" + address + ", remark=" + remark
					+ ", policeType=" + policeType + ", createBy=" + createBy
					+ ", createDt=" + createDt + ", updateBy=" + updateBy
					+ ", updateDt=" + updateDt + ", deleteBy=" + deleteBy
					+ ", actByType=" + actByType + "]";
		}
}
