package com.shield.hczz.apply.qvo;

import com.shield.frame.common.qvo.DataGridQO;
import com.shield.hczz.common.SopConstants;

public class PzApplyQO extends DataGridQO {

    private String userId;
    private String authType;
    private String pzApplyId;
    private String pzApplyNo;
    private String caseId;
    private String caseNo;
    private String caseName;
    private String caseType;
    private String caseTypeImp;
    private String pzApplyType;
    private String pzApplyStatus;
    private String clueName;
    private String timeStart;
    private String timeEnd;
    private String deptId;
    private String sorter;
    private String order;
    private String isdbsx;
    private String applyTaskKey = SopConstants.FLOW_SQPZ;

    public String getCaseTypeImp() {
        return caseTypeImp;
    }

    public void setCaseTypeImp(String caseTypeImp) {
        this.caseTypeImp = caseTypeImp;
    }

    public String getApplyTaskKey() {
        return applyTaskKey;
    }

    public void setApplyTaskKey(String applyTaskKey) {
        this.applyTaskKey = applyTaskKey;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public String getClueName() {
        return clueName;
    }

    public void setClueName(String clueName) {
        this.clueName = clueName;
    }

    public String getSorter() {
        return sorter;
    }

    public void setSorter(String sorter) {
        this.sorter = sorter;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getPzApplyId() {
        return pzApplyId;
    }

    public void setPzApplyId(String pzApplyId) {
        this.pzApplyId = pzApplyId;
    }

    public String getPzApplyNo() {
        return pzApplyNo;
    }

    public void setPzApplyNo(String pzApplyNo) {
        this.pzApplyNo = pzApplyNo;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getPzApplyType() {
        return pzApplyType;
    }

    public void setPzApplyType(String pzApplyType) {
        this.pzApplyType = pzApplyType;
    }

    public String getPzApplyStatus() {
        return pzApplyStatus;
    }

    public void setPzApplyStatus(String pzApplyStatus) {
        this.pzApplyStatus = pzApplyStatus;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getIsdbsx() {
        return isdbsx;
    }

    public void setIsdbsx(String isdbsx) {
        this.isdbsx = isdbsx;
    }

}
