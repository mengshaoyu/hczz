package com.shield.frame.upload.dto;

import java.math.BigDecimal;

import com.shield.frame.common.BaseTO;

public class AttachDTO extends BaseTO {

    private String attId;

    private String busId;

    private String moduleType;

    private String attName;

    private String attRname;

    private String attPath;

    private String deleteBy;

    private String actByType;

    private String error;

    public String getAttId() {
        return attId;
    }

    public void setAttId(String attId) {
        this.attId = attId;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType == null ? null : moduleType.trim();
    }

    public String getAttName() {
        return attName;
    }

    public void setAttName(String attName) {
        this.attName = attName == null ? null : attName.trim();
    }

    public String getAttRname() {
        return attRname;
    }

    public void setAttRname(String attRname) {
        this.attRname = attRname == null ? null : attRname.trim();
    }

    public String getAttPath() {
        return attPath;
    }

    public void setAttPath(String attPath) {
        this.attPath = attPath == null ? null : attPath.trim();
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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
