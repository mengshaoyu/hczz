package com.shield.hczz.base.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shield.frame.base.domain.User;
import com.shield.hczz.base.domain.CaseInfo;
import com.shield.hczz.pztb.dto.CaseInfoDTO;

public interface HctbMapper {

    List<CaseInfoDTO> selectCaseInfo(Map<String, Object> map);

    List<Map<String, Object>> selectClueInfo(Map<String, Object> map);

    int updatePzApplyByKey(Map<String, Object> map);

    int deleteClueInfoByPzId(Map<String, Object> map);

    int deleteClueAttByPzId(Map<String, Object> map);

    List<Map<String, Object>> getUsersByIds(Map<String, Object> map);

    int updateCaseInfo(CaseInfo caseParam);

    List<User> getApprovers(Map<String, Object> param);
    
    List<Map<String, Object>> selectClueByPzId(Map<String, Object> map);
    
    List<Map<String, String>> selectClueByPzIdAndUser(Map<String, String> map);

	int updateSumup(HashMap<String, Object> hashMap);
	
	//配帧表关联查询线索主办
	List<Map<String,Object>> selectClueByPz(Map<String,Object> map); 
	
	int countLajds(Map<String,Object> map);
	
	int countSadjb(Map<String,Object> map);
}