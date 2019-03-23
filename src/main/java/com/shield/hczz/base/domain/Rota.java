package com.shield.hczz.base.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.shield.frame.common.BaseTO;

/**
 * 排班记录表
 */
public class Rota extends BaseTO {

    private static final long serialVersionUID = 1L;

    private BigDecimal rotaId;
    private BigDecimal userId;
    private BigDecimal duration;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
    private BigDecimal assigner;
    private BigDecimal rotaAutoId;
    private String remark;

    public BigDecimal getRotaId() {
        return rotaId;
    }

    public void setRotaId(BigDecimal rotaId) {
        this.rotaId = rotaId;
    }

    public BigDecimal getUserId() {
        return userId;
    }

    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }

    public BigDecimal getDuration() {
        return duration;
    }

    public void setDuration(BigDecimal duration) {
        this.duration = duration;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getAssigner() {
        return assigner;
    }

    public void setAssigner(BigDecimal assigner) {
        this.assigner = assigner;
    }

    public BigDecimal getRotaAutoId() {
        return rotaAutoId;
    }

    public void setRotaAutoId(BigDecimal rotaAutoId) {
        this.rotaAutoId = rotaAutoId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
