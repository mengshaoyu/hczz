package com.shield.hczz.pzsp.dto;

public class PzspDTO {
    private String pzid;
    private String spjl; // 审批结论 1同意 2驳回
    private String spyj; // 审批意见
    private String[] spr; // 审批人
    private String fgspjl; // 值班长审批结论
    private String fgspyj; // 值班长审批意见
    private String[] fgspr; // 审批人

    public String getPzid() {
        return pzid;
    }

    public void setPzid(String pzid) {
        this.pzid = pzid;
    }

    public String getSpjl() {
        return spjl;
    }

    public void setSpjl(String spjl) {
        this.spjl = spjl;
    }

    public String getSpyj() {
        return spyj;
    }

    public void setSpyj(String spyj) {
        this.spyj = spyj;
    }

    public String[] getSpr() {
        return spr;
    }

    public void setSpr(String[] spr) {
        this.spr = spr;
    }

    public String getFgspjl() {
        return fgspjl;
    }

    public void setFgspjl(String fgspjl) {
        this.fgspjl = fgspjl;
    }

    public String getFgspyj() {
        return fgspyj;
    }

    public void setFgspyj(String fgspyj) {
        this.fgspyj = fgspyj;
    }

    public String[] getFgspr() {
        return fgspr;
    }

    public void setFgspr(String[] fgspr) {
        this.fgspr = fgspr;
    }

}
