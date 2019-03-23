package com.shield.hczz.index.service;

import java.util.List;
import java.util.Map;

import com.shield.frame.base.domain.User;
import com.shield.hczz.index.dao.UserModel;

public interface IndexService {

	//查询用户相关信息
	UserModel select(UserModel userModel);

	//修改用户相关信息
	int update(UserModel userModel,User user, String flag);

	//待办事项数量提醒(小铃铛)
	Integer[] getUnFinish(String userId);

	//待办事项-判断用户角色(民警、合成中心)
	Map<String,Integer> decide(User user);

	//待办事项-相关查询(待办任务、收到评价、查看反馈、评价核实)
	Integer[] waitingTask(Map<String, Object> map);
	
	//待办事项- 民警(本月合成申请、本单位合成申请)
	List<Integer> selectUserStart(User user,String flag);

	//根据taskKey、pzid返回task流程表记录
	List<Map<String,Object>> pzfk(Map<String, Object> map);

	//根据pzId查询线索表记录
	Map<String, Object> clueInfo(String pzid);

	//账号比对
	Integer contrast(UserModel model,String pwd, User user);

	//配帧ID查全部
	Map<String,String> selectPzZhuBan(String pzid);

	//根据resultId删除反馈表记录
	Integer remove(String resultId, User user);

	//根据resultId查询反馈记录
	Map<String, Object> selectResultById(String resultId);

	//反馈综述保存入线索表
	Integer addClue(Map<String, String> clueMap);
	
}
