package com.shield.frame.sysmng.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.shield.frame.common.BaseTO;

public class UserDTO extends BaseTO {
    /**
     * 
     */
    private static final long serialVersionUID = 1778992099993619594L;

    private String uuid = UUID.randomUUID().toString().replaceAll("-", "");
    
    private String userId;

    private String userName;

    private String gender;

    private Short age;

    private String loginAccount;

    private String loginPwd;

    private String idNo;

    private String mobilePhone;

    private String emial;

    private String curState;

    private BigDecimal loginAmount;

    private BigDecimal deptId;

    private String userNo;

    private String address;

    private String remark;

    private String rolenames;

    private String deptname;

    private String policeType;
    
    private String indexBody;
    
    private String signatureBody;
    
    private String linkBody;
    
    private String signatureStr;

    public String getLinkBody() {
        return linkBody;
    }

    public void setLinkBody(String linkBody) {
        this.linkBody = linkBody;
    }

    public String getSignatureBody() {
        return signatureBody;
    }

    public void setSignatureBody(String signatureBody) {
        this.signatureBody = signatureBody;
    }

    public String getIndexBody() {
        return indexBody;
    }

    public void setIndexBody(String indexBody) {
        this.indexBody = indexBody;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getSignatureStr() {
        return signatureStr;
    }

    public void setSignatureStr(String signatureStr) {
        this.signatureStr = signatureStr;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public Short getAge() {
        return age;
    }

    public void setAge(Short age) {
        this.age = age;
    }

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount == null ? null : loginAccount.trim();
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd == null ? null : loginPwd.trim();
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo == null ? null : idNo.trim();
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
    }

    public String getEmial() {
        return emial;
    }

    public void setEmial(String emial) {
        this.emial = emial == null ? null : emial.trim();
    }

    public String getCurState() {
        return curState;
    }

    public void setCurState(String curState) {
        this.curState = curState == null ? null : curState.trim();
    }

    public BigDecimal getLoginAmount() {
        return loginAmount;
    }

    public void setLoginAmount(BigDecimal loginAmount) {
        this.loginAmount = loginAmount;
    }

    public BigDecimal getDeptId() {
        return deptId;
    }

    public void setDeptId(BigDecimal deptId) {
        this.deptId = deptId;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo == null ? null : userNo.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getRolenames() {
        return rolenames;
    }

    public void setRolenames(String rolenames) {
        this.rolenames = rolenames;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public String getPoliceType() {
        return policeType;
    }

    public void setPoliceType(String policeType) {
        this.policeType = policeType;
    }

}