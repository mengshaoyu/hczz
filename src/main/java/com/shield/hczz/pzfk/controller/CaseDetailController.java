package com.shield.hczz.pzfk.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shield.frame.base.domain.User;
import com.shield.frame.utils.AuditLogUtil;
import com.shield.hczz.base.persistence.HctbMapper;
import com.shield.hczz.index.controller.BaseController;
import com.shield.hczz.pzfk.service.CaseDetailService;


/**
 * @ClassName: 案件详情相关控制器(CaseDetailController.java)
 * @Description: 接收案件详情相关操作
 * @author K.
 */
@RequestMapping("/caseDetail")
@Controller
public class CaseDetailController extends BaseController{
	
	@Autowired
	private CaseDetailService caseDetailService;
	
	@RequestMapping("/init")
	public ModelAndView init(String caseId,HttpServletRequest req){
		//tab页展示的结果集
		List<Map<String,String>> resultList = new ArrayList<Map<String,String>>();
		ModelAndView mav = new ModelAndView("pzfk/caseDetail");
		//根据caseId查此案件相关信息
		Map<String,String> caseMap = caseDetailService.selectCaseInfo(caseId);
		//查询完结状态配帧记录
		List<Map<String,String>> endList = caseDetailService.selectEnd(caseId);
		//人员权限判断 1:普通民警  2：分管领导  3：合成民警、值班长
		User user = super.getUser(req);
		String flag = caseDetailService.decide(user.getUserId().intValue());
		if(flag.equals("1")){
			for (int i = 0; i < endList.size(); i++) {
				if(String.valueOf(user.getUserId().intValue()).equals(endList.get(i).get("CREATE_BY"))){
					resultList.add(endList.get(i));
				}
			}
		}
		if(flag.equals("2")){
			List<String> userList = caseDetailService.selectUser(String.valueOf(user.getDeptId().intValue()));
			for (int i = 0; i < endList.size(); i++) {
				for (int j = 0; j < userList.size(); j++) {
					 if(endList.get(i).get("CREATE_BY").equals(userList.get(j))){
						 resultList.add(endList.get(i));
					 }
				}
			}
		}
		if(flag.equals("3")){
			resultList.addAll(endList);
		}
		String pzid = null;
		if(resultList.size() > 0){
			pzid = resultList.get(0).get("PZ_APPLY_ID");
		}
		
		mav.addObject("map", caseMap);	//案件信息展示
		mav.addObject("relevance", resultList);	//tab页部分
		mav.addObject("pzid", pzid);
		AuditLogUtil.addLog(req, "案件管理", "2", "进入案件管理案件详情页面", caseId, "0");
		return mav;
	}
	
	@RequestMapping("/detail")
	@ResponseBody
	public Map<String, Object> detail(@RequestParam("pzApplyId")String pzId,HttpServletRequest req){
		Map<String, Object> map = new HashMap<>();
			map.put("pzid", pzId);
		//根据pzId查询线索信息
		return caseDetailService.selectClueInfo(map);
	}

}
