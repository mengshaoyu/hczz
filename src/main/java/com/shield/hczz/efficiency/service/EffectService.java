package com.shield.hczz.efficiency.service;

import java.util.List;
import java.util.Map;

import com.shield.hczz.base.domain.Effect;
import com.shield.hczz.efficiency.qvo.EffectQO;

public interface EffectService {

    int add(Effect effect);
    
    List<Map<String,Object>> getlist(EffectQO qo);
    
    int getCount(EffectQO qo);
    
    Map<String,Object> getPjById(String pzid);
}
