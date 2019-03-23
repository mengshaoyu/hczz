package com.shield.hczz.base.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.shield.frame.common.BaseTO;

/**
 * 排班自动生成日志表
 */
public class RotaAuto extends BaseTO {

    private static final long serialVersionUID = 1L;

    private BigDecimal rotaAutoId;
    private String userIds;
    private BigDecimal duration;
    private Date startTime;
    private Date endTime;
    private BigDecimal assigner;
    private String remark;

    public BigDecimal getRotaAutoId() {
        return rotaAutoId;
    }

    public void setRotaAutoId(BigDecimal rotaAutoId) {
        this.rotaAutoId = rotaAutoId;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
