package com.shield.hczz.base.persistence.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.shield.frame.common.dao.BaseDaoImpl;
import com.shield.hczz.base.domain.Effect;
import com.shield.hczz.base.persistence.EffectMapper;
import com.shield.hczz.efficiency.qvo.EffectQO;

@Repository
public class EffectMapperImpl extends BaseDaoImpl implements EffectMapper {

    @Override
    public int add(Effect effect) {
        return this.getSqlSession().insert(EffectMapper.class.getName()+".add", effect);
    }

    @Override
    public List<Map<String,Object>> getlist(EffectQO qo) {
        return this.getSqlSession().selectList(EffectMapper.class.getName()+".getlist", qo);
    }

    @Override
    public int getCount(EffectQO qo) {
        return this.getSqlSession().selectOne(EffectMapper.class.getName()+".getCount", qo);
    }

}
