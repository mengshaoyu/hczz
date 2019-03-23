package com.shield.hczz.pzfk.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.shield.frame.base.domain.Role;
import com.shield.frame.base.domain.User;
import com.shield.frame.sysmng.service.RoleService;
import com.shield.frame.utils.AuditLogUtil;
import com.shield.hczz.common.SopConstants;
import com.shield.hczz.index.controller.BaseController;
import com.shield.hczz.pzfk.service.PzpjService;

/**
 * @ClassName: 评价核实相关控制器(pzpjController.java)
 * @Description: 接收评价核实相关操作
 * @author K.
 */
@RequestMapping("/pjhs")
@Controller
public class PzpjController extends BaseController{
	
	@Autowired
	private PzpjService pzpjService;
	@Autowired
	private RoleService roleService;

	@RequestMapping("/initData")
	public ModelAndView initData(@RequestParam("pzApplyId")String pzId,HttpServletRequest req){
		User user = super.getUser(req);
		ModelAndView mav = new ModelAndView("pzfk/pzhs");
		//根据pzId查询pz落地核实相关记录
		Map<String,String> map = pzpjService.selectPz(pzId);
		//判断反馈效能展示权限
		List<Role> list = user.getRoleList();
		boolean b = roleService.has(list, SopConstants.ROLE_HCZXLD2);
		String flag = "0";
		//返回true为大领导
		if(b){
			flag = "1";
		}
		mav.addObject("flag", flag);	//判断权限展示    合成效能
		mav.addObject("map", map);
		AuditLogUtil.addLog(req, "任务管理", "1", "进入落地评价核实页面", pzId, "0");
		return mav;
	}
}
