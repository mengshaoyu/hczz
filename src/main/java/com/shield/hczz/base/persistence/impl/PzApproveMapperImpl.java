package com.shield.hczz.base.persistence.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.shield.frame.common.dao.BaseDaoImpl;
import com.shield.hczz.approve.qvo.PzApproveQO;
import com.shield.hczz.approve.qvo.PzApproveVO;
import com.shield.hczz.base.domain.PzApprove;
import com.shield.hczz.base.persistence.PzApproveMapper;

@Repository
public class PzApproveMapperImpl extends BaseDaoImpl implements PzApproveMapper {

    private static final String mapperName = PzApproveMapper.class.getName();

    @Override
    public int add(PzApprove pzApprove) {
        return this.getSqlSession().insert(mapperName + ".add", pzApprove);
    }

    @Override
    public List<PzApproveVO> queryApprove(PzApproveQO qo) {
        return this.getSqlSession().selectList(mapperName + ".queryApprove", qo);
    }

    @Override
    public int updateApprove(PzApprove pzApprove) {
        return this.getSqlSession().update(mapperName + ".updateApprove", pzApprove);
    }

    @Override
    public List<Map<String, Object>> getApproveUser(String pzid) {
        return this.getSqlSession().selectList(mapperName + ".getApproveUser", pzid);
    }

    @Override
    public List<PzApproveVO> getSumlist(PzApproveQO qo, String page, String rows) {
        return this.getSqlSession().selectList(mapperName + ".getSumlist", qo);
    }

}
