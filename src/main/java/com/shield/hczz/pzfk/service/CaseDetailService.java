package com.shield.hczz.pzfk.service;

import java.util.List;
import java.util.Map;

public interface CaseDetailService {
	
	Map<String, Object> selectClueInfo(Map<String, Object> map);

	String decide(int userId);

	List<Map<String, String>> selectEnd(String caseId);

	Map<String, String> selectCaseInfo(String caseId);

	List<String> selectUser(String deptId);
}
