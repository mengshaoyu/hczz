package com.shield.hczz.base.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shield.frame.base.domain.ApplyExp;
import com.shield.hczz.apply.qvo.PzApplyVO;
import com.shield.hczz.base.domain.PzApply;

public interface PzApplyMapper {

    List<PzApplyVO> getlist(Map<String, Object> qo, int currPage, int pageSize);

    int add(PzApply pzApply);

    int getCount(Map<String, Object> qo);

    int updateDeleted(String ids, String account) throws Exception;

    int insertSelective(PzApply pzApply);

    int updateStatus(PzApply pzApply);

    int updateFlow(PzApply pzApply);

    PzApplyVO getById(String pzApplyId);

    PzApplyVO getInfoById(String pzApplyId);

    PzApplyVO getFlowTime(String pzid, String startFlow, String endFlow);

    List<Map<String, Object>> getToDoCount(Map<String, Object> map);

    int addExp(ApplyExp applyExp);

	PzApplyVO getFlowById(String pzid);
	
	int backUnread(ApplyExp applyExp);
	
	List<HashMap<String,Object>> getAssignees(Map<String,Object> param);

	Map<String,Object> getPjById(String pzid);
}
