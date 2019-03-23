package com.shield.hczz.pzlog.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.shield.hczz.flow.qvo.TaskFlowVO;

public interface PzLogService {

    Map<String, Object> initData(String pzid, HttpServletRequest request);

    List<TaskFlowVO> getLogsById(String pzApplyId, String key);

	List<TaskFlowVO> getDealNow(String pzid);

	List<TaskFlowVO> getTaskFlow(String pzid);
	
	List<Integer> getPoliceByPzid(String pzid);
	
	Set<String> getPoliceTypeByPzid(String pzid);
}
