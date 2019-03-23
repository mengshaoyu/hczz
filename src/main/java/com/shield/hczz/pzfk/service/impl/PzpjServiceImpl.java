package com.shield.hczz.pzfk.service.impl;

import java.math.BigDecimal;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shield.hczz.index.mapper.IndexMapper;
import com.shield.hczz.pzfk.service.PzpjService;

@Service
public class PzpjServiceImpl implements PzpjService{
	
	@Autowired
	private IndexMapper indexMapper;

	@Override
	public Map<String, String> selectPz(String pzId) {
		Map<String, String> map = indexMapper.selectPzZhuBan(pzId);
		//反馈时效判空
		if(map.get("FEEDBACK_AGING") == null){
			return map;
		}
		BigDecimal bigDecimal = new BigDecimal(Double.valueOf(map.get("FEEDBACK_AGING")));
		int num = bigDecimal.intValue();
		//num大于0 反馈超时  flag=f
		//num小于0 反馈超时  flag=t
		map.put("FEEDBACK_AGING", (num > 0)?"f":"t");
		return map;
	}

}
