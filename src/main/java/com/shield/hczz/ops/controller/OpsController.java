package com.shield.hczz.ops.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shield.frame.common.qvo.ResultVO;
import com.shield.frame.utils.AuditLogUtil;
import com.shield.hczz.base.domain.Ops;
import com.shield.hczz.ops.qvo.OpsQO;
import com.shield.hczz.ops.qvo.OpsVO;
import com.shield.hczz.ops.serviceimpl.OpsServiceImpl;

@Controller
@RequestMapping(value = "/ops/*")
public class OpsController {


	// 注入opsService
	@Autowired
	private OpsServiceImpl opsServiceImlpl;

	// 页面初始化
	@ResponseBody
	@RequestMapping("init")
	public ModelAndView init(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("ops/ops");
		if (opsServiceImlpl.checkUserRole(request)) {
			mv.addObject("userRole", "1007");
		} else {
			mv.addObject("userRole", "0000");
		}
		return mv;
	}

	// 获取全部运维意见
	@ResponseBody
	@RequestMapping("getOpsList")
	public ResultVO getOpsList(OpsQO qo) {
		List<OpsVO> list = opsServiceImlpl.getOpsList(qo);
		return ResultVO.ok(list, "200");
	}

	// 新增一条运维意见记录
	@ResponseBody
	@RequestMapping("addOps")
	public ResultVO addOps(Ops ops,HttpServletRequest request) {
		String id = UUID.randomUUID().toString().replaceAll("-", "");
		ops.setSopOpsId(id);
		ops.setSopOpsState("0");
		if (opsServiceImlpl.addOps(ops)) {
		    AuditLogUtil.addLog(request, "系统意见反馈", "3", "新建系统意见", id, "0"); // 记录日志
			return ResultVO.ok();
		} else {
		    AuditLogUtil.addLog(request, "系统意见反馈", "3", "新建系统意见失败", id, "1"); // 记录日志
			return ResultVO.error();
		}
	}

	// 更新意见 回复内容
	@ResponseBody
	@RequestMapping("updateOps")
	public ResultVO upadteOps(Ops ops,HttpServletRequest request) {
		if (opsServiceImlpl.updateOps(ops)) {
		    AuditLogUtil.addLog(request, "系统意见反馈", "4", "回复系统意见", ops.getSopOpsId(), "0"); // 记录日志
			return ResultVO.ok();
		} else {
		    AuditLogUtil.addLog(request, "系统意见反馈", "4", "回复系统意见", ops.getSopOpsId(), "1"); // 记录日志
			return ResultVO.error();
		}
	}
	
	public static void main(String[] args) {
//		String ss = UUID.randomUUID().toString().replaceAll("-", "");
//		System.out.println(ss);
		
	}

}
