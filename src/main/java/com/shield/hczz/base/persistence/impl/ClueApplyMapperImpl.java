package com.shield.hczz.base.persistence.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.shield.frame.common.dao.BaseDaoImpl;
import com.shield.hczz.base.domain.ClueInfo;
import com.shield.hczz.base.persistence.ClueApplyMapper;

@Repository
public class ClueApplyMapperImpl extends BaseDaoImpl implements ClueApplyMapper {

    private static final String mapperName = ClueApplyMapper.class.getName();

	/**
	 * 根据配侦主键，同步线索内容到线索库
	 * @param applyId	配侦主键
	 * @return
	 */
    @Override
	public int synchroClue(String applyId) {
		// TODO Auto-generated method stub
		HashMap<String,Object> hashMap=new HashMap<String, Object>();
		hashMap.put("applyId", applyId);
		
		return this.getSqlSession().insert(mapperName+".synchroClue",hashMap);
	}
    public int updateFlowAuxi(ClueInfo clue){
    	HashMap<String,Object> hashMap=new HashMap<String, Object>();
		hashMap.put("pzResultCreateBy", clue.getPzResultCreateBy());
		hashMap.put("clueId", clue.getClueId());
		
		return this.getSqlSession().update(mapperName+".updateFlowAuxi",hashMap);
    };

	@Override
	public int updateResultByPz(String pzid) {
		// TODO Auto-generated method stub
		HashMap<String,Object> hashMap=new HashMap<String, Object>();
		hashMap.put("pzid", pzid);
		
		return this.getSqlSession().insert(mapperName+".updateResultByPz",hashMap);
	}


   
}
