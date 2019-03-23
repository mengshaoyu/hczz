package com.shield.hczz.efficiency.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shield.hczz.base.domain.Effect;
import com.shield.hczz.base.persistence.impl.EffectMapperImpl;
import com.shield.hczz.base.persistence.impl.PzApplyMapperImpl;
import com.shield.hczz.efficiency.qvo.EffectQO;
import com.shield.hczz.efficiency.service.EffectService;

@Service
public class EffectServiceImpl implements EffectService {
    
    @Autowired
    private EffectMapperImpl effectMapperImpl;
    
    @Autowired
    private PzApplyMapperImpl pzApplyMapperImpl;
    
    @Override
    public int add(Effect effect) {
        return effectMapperImpl.add(effect);
    }

    @Override
    public List<Map<String, Object>> getlist(EffectQO qo) {
        return effectMapperImpl.getlist(qo);
    }

    @Override
    public int getCount(EffectQO qo) {
        return effectMapperImpl.getCount(qo);
    }

    @Override
    public Map<String, Object> getPjById(String pzid) {
        return pzApplyMapperImpl.getPjById(pzid);
    }

}
