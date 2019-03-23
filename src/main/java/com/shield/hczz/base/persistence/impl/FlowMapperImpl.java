package com.shield.hczz.base.persistence.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.shield.frame.base.domain.User;
import com.shield.frame.common.dao.BaseDaoImpl;
import com.shield.hczz.base.persistence.FlowMapper;
import com.shield.hczz.flow.qvo.FlowQO;
import com.shield.hczz.flow.qvo.FlowVO;

@Repository
public class FlowMapperImpl extends BaseDaoImpl implements FlowMapper {

    private static final String mapperName = FlowMapper.class.getName();

    @Override
    public FlowVO getStartFlow(FlowQO qo) {
        return this.getSqlSession().selectOne(mapperName + ".getStartFlow", qo);
    }

    @Override
    public FlowVO getById(String flowId) {
        return this.getSqlSession().selectOne(mapperName + ".getById", flowId);
    }

    @Override
    public List<User> getApprovers(Map<String, Object> param) {
        return this.getSqlSession().selectList(mapperName + ".getApprovers", param);
    }

}
