package com.shield.hczz.ops.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.shield.hczz.base.domain.Ops;
import com.shield.hczz.ops.qvo.OpsQO;
import com.shield.hczz.ops.qvo.OpsVO;


public interface OpsService {
	//运维回复权限判断
	public boolean checkUserRole(HttpServletRequest request);
	
	//获取用户意见列表
	public List<OpsVO> getOpsList(OpsQO qo);
	
	//判断用户意见是否存在
	public boolean checkOpsHas(String opsId);
	
	//更新用户意见回复内容
	public boolean updateOps(Ops ops);
	
	//新增用户意见
	public boolean addOps(Ops ops);
}
