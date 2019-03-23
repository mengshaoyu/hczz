package com.shield.hczz.index.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.shield.frame.base.domain.User;
import com.shield.hczz.index.dao.UserModel;
import com.shield.hczz.index.service.IndexService;

/**
 * @ClassName: 首页相关操作控制器（IndexController.java)
 * @Description: 接收首页相关操作
 * @author K.
 */
@Controller
@RequestMapping("/index")
public class IndexController extends BaseController{
	
	@Autowired
	private IndexService indexService;
	
	/**
	 * 展示用户相关信息
	 */
	@RequestMapping("/show")
	@ResponseBody
	public UserModel show(UserModel userModel,HttpServletRequest req){
		userModel.setUserId(super.getUser(req).getUserId().intValue());
		//当前用户id查询au_t_uesr表下此id的用户相关信息
		UserModel resultModel = indexService.select(userModel);
		return resultModel;
	}
	
	/**
	 * 编辑用户相关信息
	 * @param pwd:用于密码比对
	 * 		  flag:为1 表示修改密码操作
	 */
	@RequestMapping("/edit")
	public void edit(HttpServletRequest req,UserModel userModel,HttpServletResponse res,String pwd,String flag){
		User user = super.getUser(req);
		//当前用户id、前台发送的原密码，查询密码是否正确
		Integer num = indexService.contrast(userModel, pwd,user);
		//默认flag表示修改失败
		String msg = "err";
		if(!(num ==0)){
			//用户信息修改
			int result = indexService.update(userModel,user,flag);
			if(result == 1){
				msg = "success";
			}
		}else{
			//原密码比对错误
			msg = "errCode";
		}
		super.flushResponse(res, msg);
	}
	
	/**
	 * 待办事项数量提醒
	 * @author K.
	 */
	@RequestMapping("/unFinish")
	@ResponseBody
	public Integer[] unFinish(HttpServletRequest req) {
		//当前用户id查询正在进行的流程
		User user = super.getUser(req);
		Integer[] arr = indexService.getUnFinish(String.valueOf(user.getUserId()));
		return arr;
	}
	
	/**
	 * 待办事项-判断角色
	 * @param flag：1:合成中心
	 *				2为民警
	 */
	@RequestMapping("/decide")
	@ResponseBody
	public Integer[] decide(HttpServletRequest req){
		//当前用户id判断权限，返回flag前台展示不同的按钮
		User user = super.getUser(req);
		Map<String,Integer> map = indexService.decide(user);
		Integer[] arr = new Integer[]{map.get("flag")};
		return arr;
	}
	
	/**
	 * 待办事项-统计数量count
	 * @param : taskKey
	 */
	@RequestMapping("/waitingTask")
	@ResponseBody
	public Integer[] waitingTask(HttpServletRequest req){
		//前台获取taskKey标志位状态，与用户id查询当前流程数量
		User user = super.getUser(req);
		Map<String, Object> map = super.getParamValues(req);
			map.put("userId", String.valueOf(user.getUserId()));
		Integer[] num  = indexService.waitingTask(map);
		return num;
	}
	
	/**
	 * 待办事项-个人本月发起合成提报统计数量count
	 *  @flag:flag	:1 ->合成中心 本月反馈线索、本月共计反馈线索
	 * 				:2 ->民警        本月合成申请、本单位合成申请
	 */
	@RequestMapping("/selectStart")
	@ResponseBody
	public Integer[] selectUserStart(HttpServletRequest req,String flag){
		//根据flag、用户id查询pz_t_apply表取flow_id，查询act_ru_task中正在走的流程数量
		User user = super.getUser(req);
		List<Integer> list = indexService.selectUserStart(user,flag);
		Integer[] arr = new Integer[]{list.get(0),list.get(1)};
		return arr;
	}
	
	@RequestMapping("/shortMsg")
	public ModelAndView shortMsg(){
		ModelAndView mav = new ModelAndView("index/shoutMsg");
		return mav;
	}
	
}
