package com.shield.hczz.pztb.dto;

import com.shield.hczz.base.domain.CaseInfo;

public class CaseInfoDTO extends CaseInfo {
    private String pzApplyId;

    private String pzApplyGrade;
    
    private String caseTypeValue;

    public String getPzApplyId() {
        return pzApplyId;
    }

    public void setPzApplyId(String pzApplyId) {
        this.pzApplyId = pzApplyId;
    }

    public String getPzApplyGrade() {
        return pzApplyGrade;
    }

    public void setPzApplyGrade(String pzApplyGrade) {
        this.pzApplyGrade = pzApplyGrade;
    }

    public String getCaseTypeValue() {
        return caseTypeValue;
    }

    public void setCaseTypeValue(String caseTypeValue) {
        this.caseTypeValue = caseTypeValue;
    }

}
