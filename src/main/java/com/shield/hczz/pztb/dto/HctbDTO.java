package com.shield.hczz.pztb.dto;

import java.util.ArrayList;
import java.util.List;

import com.shield.hczz.base.domain.CaseInfo;
import com.shield.hczz.base.domain.ClueInfo;
import com.shield.hczz.base.domain.PzApply;

public class HctbDTO {
    private PzApply pzApply = new PzApply();// 配帧
    private CaseInfo caseInfo = new CaseInfo();// 案件
    private List<ClueInfo> clueList = new ArrayList<>();// 线索
    private List<String> clueFile = new ArrayList<>();// 线索文件
    private List<String> sadjb = new ArrayList<>();// 受案登记表
    private List<String> lajds = new ArrayList<>();// 立案决定书
    private List<String> cqlabgs = new ArrayList<>();// 呈请立案决定书
    private String[] approveUser;

    public String[] getApproveUser() {
        return approveUser;
    }

    public void setApproveUser(String[] approveUser) {
        this.approveUser = approveUser;
    }

    public PzApply getPzApply() {
        return pzApply;
    }

    public void setPzApply(PzApply pzApply) {
        this.pzApply = pzApply;
    }

    public CaseInfo getCaseInfo() {
        return caseInfo;
    }

    public void setCaseInfo(CaseInfo caseInfo) {
        this.caseInfo = caseInfo;
    }

    public List<ClueInfo> getClueList() {
        return clueList;
    }

    public void setClueList(List<ClueInfo> clueList) {
        this.clueList = clueList;
    }

    public List<String> getSadjb() {
        return sadjb;
    }

    public void setSadjb(List<String> sadjb) {
        this.sadjb = sadjb;
    }

    public List<String> getLajds() {
        return lajds;
    }

    public void setLajds(List<String> lajds) {
        this.lajds = lajds;
    }

    public List<String> getCqlabgs() {
        return cqlabgs;
    }

    public void setCqlabgs(List<String> cqlabgs) {
        this.cqlabgs = cqlabgs;
    }

    public List<String> getClueFile() {
        return clueFile;
    }

    public void setClueFile(List<String> clueFile) {
        this.clueFile = clueFile;
    }

}
