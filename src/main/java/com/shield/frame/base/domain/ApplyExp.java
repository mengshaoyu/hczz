package com.shield.frame.base.domain;

public class ApplyExp {
    private int aeId;
    private String applyId;
    private String userId;

    public int getAeId() {
        return aeId;
    }

    public void setAeId(int aeId) {
        this.aeId = aeId;
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ApplyExp(){}
    
    public ApplyExp(String applyId){
        this.applyId = applyId;
    }
    
    public ApplyExp(String applyId,String userId){
        this.applyId = applyId;
        this.userId = userId;
    }
}
