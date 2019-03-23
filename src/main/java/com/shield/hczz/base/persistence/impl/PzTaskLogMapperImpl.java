package com.shield.hczz.base.persistence.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.shield.frame.common.dao.BaseDaoImpl;
import com.shield.hczz.base.domain.PzTaskLog;
import com.shield.hczz.base.persistence.PzTaskLogMapper;
import com.shield.hczz.flow.qvo.TaskFlowVO;

@Repository
public class PzTaskLogMapperImpl extends BaseDaoImpl implements PzTaskLogMapper {

    @Override
    public int deleteByPrimaryKey(BigDecimal featurePersonId) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int insert(PzTaskLog record) {
        // TODO Auto-generated method stub
        return this.getSqlSession().insert(PzTaskLogMapper.class.getName() + ".insert", record);
    }

    @Override
    public int insertSelective(PzTaskLog record) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public PzTaskLog selectByPrimaryKey(BigDecimal featurePersonId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(PzTaskLog record) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int updateByPrimaryKey(PzTaskLog record) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<TaskFlowVO> getLogsById(String pzApplyId) {
        return this.getSqlSession().selectList(PzTaskLogMapper.class.getName() + ".getLogsById",
            pzApplyId);
    }

	public List<TaskFlowVO> getDealNow(String pzid) {
		// TODO Auto-generated method stub
		return this.getSqlSession().selectList(PzTaskLogMapper.class.getName()+".getDealNow", pzid);
	}

	public List<TaskFlowVO> getDealNowConnClue(String pzid) {
		// TODO Auto-generated method stub
		return this.getSqlSession().selectList(PzTaskLogMapper.class.getName()+".getDealNowConnClue", pzid);
	}

    @Override
    public List<Integer> getPoliceByPzid(Map<String, Object> param) {
        return this.getSqlSession().selectList(PzTaskLogMapper.class.getName()+".getPoliceByPzid", param);
    }

}
