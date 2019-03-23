package com.shield.hczz.index.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.shield.hczz.index.dao.UserModel;

@Repository
public interface IndexMapper {
	
	//修改用户相关信息
	int update(@Param("userModel")UserModel userModel);

	//查询用户相关信息
	UserModel select(@Param("userModel")UserModel userModel);
	
	//待办事项数量提醒(小铃铛)
	Integer selectUnFinish(String userId);

	//待办事项-判断用户角色(民警、合成中心)
	List<Map<String, String>> decide(@Param("userId")Integer userId);
	
	//待办事项-待办任务查询
	List<Integer> waitingTask(Map<String, Object> map);
	
	//待办事项- 民警-本月合成申请
	Integer selectUserStart(Map<String, Object> map);
	
	//待办事项- 民警-本单位合成申请
	Integer selectDeptStart(Map<String, Object> map);
	
	//待办事项- 合成中心-反馈
	Integer selectClue(Map<String, Object> map);
	
	//配侦反馈相关操作
	List<Map<String, Object>> selectFk(@Param("procId")String procId,@Param("taskKey")String taskKey,@Param("userId")String userId);
	
	//根据pzId查询线索表记录
	Map<String, Object> clueInfo(@Param("pzid")String pzid);

	//密码比对
	Integer selectCount(@Param("userModel")UserModel userModel);
	
	//根据userId查用户
	Map<String,String> selectById(@Param("userId")String userId);

	//根据配帧id查询配帧全部
	Map<String, String> selectPzZhuBan(@Param("pzId")String pzId);

	//根据code查码表Name
	String selectNameByCode(@Param("codeValue")String codeValue,@Param("typeId")String typeId);
	
	//根据Name查码表Name
	String selectCodeByName(@Param("valueDesc")String valueDesc,@Param("typeId")String typeId);
	
	//查询完结状态
	Integer selectEnd(Map<String, Object> map);

	//根据code查码表value
	String selectCodeBycodeValue(@Param("codeValue")String codeValue);

	//反馈综述保存入线索表
	Integer addClue(Map<String, String> clueMap);

	//根据resultId查询附件
	List<Map<String, String>> selectAtt(@Param("resultId")String resultId);

	//根据deptId查部门
	String selectDept(@Param("deptId")String deptId);
	
	List<String> selectDeptName(@Param("deptNo")String deptNo);
	
	//根据deptName查部门
	String selectDeptById(@Param("deptName")String deptName);

	//根据pzid查配帧等级、案件的受理单位
	Map<String, String> selectAccept(@Param("pzId")String pzId);
	
}
