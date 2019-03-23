package com.shield.hczz.base.persistence;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.shield.hczz.base.domain.PzTaskLog;
import com.shield.hczz.flow.qvo.TaskFlowVO;

public interface PzTaskLogMapper {
    int deleteByPrimaryKey(BigDecimal featurePersonId);

    int insert(PzTaskLog record);

    int insertSelective(PzTaskLog record);

    PzTaskLog selectByPrimaryKey(BigDecimal featurePersonId);

    int updateByPrimaryKeySelective(PzTaskLog record);

    int updateByPrimaryKey(PzTaskLog record);

    List<TaskFlowVO> getLogsById(String pzApplyId);
    
    List<Integer> getPoliceByPzid(Map<String,Object> param);
}