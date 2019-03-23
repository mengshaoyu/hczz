package com.shield.hczz.pzfk.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CaseDetailMapepr {

	//根据deptNo查部门name
	String selectDept(@Param("deptNo")String deptNo);

	//根据flwoId查询所有end的任务
	Map<String, String> selectEnd(@Param("flwoId")String flwoId);

	//根据caseNo从case_info中查询此案件的caseId,再用caseId查pz表中此id下所有流程id
	List<Map<String, String>> selectPzFlow(@Param("caseId")String caseId);
	
	//根据caseId查线索内容
	List<Map<String, String>> selectCaseInfo(@Param("caseId")String caseId);

	//根据deptId查user
	List<String> selectUser(@Param("deptId")String deptId);
	
	//根据pzId查配帧等级
	String selectGarde(@Param("pzId")String pzId);

}
