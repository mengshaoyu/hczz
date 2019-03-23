package com.shield.hczz.clue.qvo;

import java.math.BigDecimal;
import java.util.Date;

public class VClueListInfoVO {

    private String clueId;

    private String caseNo;

    private String clueTypeName;

    private BigDecimal clueType;

    private String clueDesc;

    private String filename;

    private String createBy;

    private String username;

    private Date createDt;

    public String getClueId() {
        return clueId;
    }

    public void setClueId(String clueId) {
        this.clueId = clueId == null ? null : clueId.trim();
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo == null ? null : caseNo.trim();
    }

    public String getClueTypeName() {
        return clueTypeName;
    }

    public void setClueTypeName(String clueTypeName) {
        this.clueTypeName = clueTypeName == null ? null : clueTypeName.trim();
    }

    public BigDecimal getClueType() {
        return clueType;
    }

    public void setClueType(BigDecimal clueType) {
        this.clueType = clueType;
    }

    public String getClueDesc() {
        return clueDesc;
    }

    public void setClueDesc(String clueDesc) {
        this.clueDesc = clueDesc == null ? null : clueDesc.trim();
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

}
