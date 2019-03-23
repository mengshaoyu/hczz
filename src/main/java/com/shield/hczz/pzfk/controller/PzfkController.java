package com.shield.hczz.pzfk.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.shield.frame.base.domain.ApplyExp;
import com.shield.frame.base.domain.User;
import com.shield.frame.common.qvo.CommonVO;
import com.shield.frame.utils.AuditLogUtil;
import com.shield.hczz.apply.qvo.PzApplyVO;
import com.shield.hczz.approve.service.PzApproveService;
import com.shield.hczz.base.controller.FlowController;
import com.shield.hczz.base.domain.ClueInfo;
import com.shield.hczz.base.domain.FlowWait;
import com.shield.hczz.base.domain.PzApprove;
import com.shield.hczz.base.domain.PzResult;
import com.shield.hczz.base.persistence.ActivitiMapper;
import com.shield.hczz.base.persistence.HctbMapper;
import com.shield.hczz.base.persistence.PzApplyMapper;
import com.shield.hczz.clue.service.ClueService;
import com.shield.hczz.common.SopConstants;
import com.shield.hczz.index.service.IndexService;
import com.shield.hczz.pzfk.service.PzfkService;

/**
 * @ClassName: 配帧线索反馈相关控制器(PzfkController.java)
 * @Description: 接收配帧反馈相关操作
 * @author K.
 */
@Controller
@RequestMapping("pzfk")
public class PzfkController extends FlowController{

    @Autowired
    public PzfkService pzfkServiceImpl;
    @Autowired
    public PzApplyMapper pzApplyMapperImpl;
    @Autowired
    private PzApproveService PzApproveServiceImpl;
    @Autowired
    private IndexService indexService;
    @Autowired
    private ClueService clueService;
    @Autowired
    private ActivitiMapper activitiMapper;
    @Autowired
    private HctbMapper hctbMapper;

    /**
     *  配侦反馈初始化方法
     *  @param :pzid 配帧ID
     */
    @RequestMapping("init")
    public ModelAndView init(Model mod, String pzid,HttpServletRequest req) {
    	ModelAndView mav = new ModelAndView("pzfk/pzfk");
    	User user = (User) request.getSession().getAttribute("loginUser");
    	Map<String,Object> map = new HashMap<String,Object>();
    		map.put("pzid", pzid);
    		map.put("userId", user.getUserId());
    	//根据用户id、pzid查当前流程状态
    	List<Map<String, Object>> taskMap = indexService.pzfk(map);
    	//默认标志位，不在待反馈状态
    	String flag = "3";
    	String flowid = null;
    	
    	//pzid查询配帧表  
    	Map<String,String> pzMap = indexService.selectPzZhuBan(pzid);
    	flowid = pzMap.get("FLOW_ID");
    	
    	Map<String,Object> mapparam = new HashMap<>();
        mapparam.put("userId", user.getUserId().toString());
        mapparam.put("procId", flowid);
        List<FlowWait> flowWait = activitiMapper.flowWait(mapparam);
    	
    	for(Map<String, Object> taskMaps : taskMap){
    		if(flowWait.size() >0 && taskMaps.get(("TASK_DEF_KEY_")).equals(SopConstants.FLOW_DFK)){
    			//展示确认按钮
    			flag = "1";
    		}
    		if(flowWait.size() >0 && taskMaps.get("TASK_DEF_KEY_").equals(SopConstants.FLOW_DSC)){
    			//判断 是否主办人--展示生成反馈报告按钮   
    			if(pzMap.get("PZ_MAIN_ACCEPT").equals(user.getUserId().toString())){
					flag = "2";
    			}
    		}
    	}
//    	mav.addObject("procInstId",taskMap.get(0).get("PROC_INST_ID_"));
    	mav.addObject("flag",flag);
    	mav.addObject("pzApplyId",pzid);
    	mav.addObject("flowid",flowid);
    	mav.addObject("hcr",pzMap.get("PZ_MAIN_ACCEPT"));	//合成人  PZ_MAIN_ACCEPT字段(主办人)
    	mav.addObject("fqr",pzMap.get("CREATE_BY"));	//发起人  CREATE_BY字段(发起人)
    	AuditLogUtil.addLog(request, "任务管理", "2", "进入线索反馈页面", pzid, "0");
        return mav;
    }
    
    /**
     *  确认反馈方法
     *  @Description：将流程改为HCZZ_1007
     */
	@RequestMapping("/next")
    @ResponseBody
    public String next(String procInstId,String pzid,String flowId,String clueId,String clueSumup){
    	User user = (User) request.getSession().getAttribute("loginUser");
    	Map<String,String> map = new HashMap<String,String>();
	    	map.put("pzid", pzid);
	    	map.put("userId", user.getUserId().toString());
	    String flag = null;
	    List<Task> list = taskService.createTaskQuery().processInstanceId(flowId).list();
	    	if(list.size() == 0){
	    		return "err";
	    	}
		    String def = list.get(0).getTaskDefinitionKey();
	    	if(!def.equals(SopConstants.FLOW_DFK)){
	    		return "err";
	    	}
	    Map<String, Object> vars = new HashMap<String, Object>();
	    Map<String,String> pzMap = indexService.selectPzZhuBan(pzid);
	    	vars.put("zbr", pzMap.get("PZ_MAIN_ACCEPT"));
	    Map<String, Object> resultMap = super.simpleTaskComplate(procInstId, vars, true);
	    	flag = resultMap.get("code").equals(0)?"success":"err";	
	    if(flag.equals("success")){
	    	//更新线索综述
	    	int count = 0;
	    	count = this.pzfkServiceImpl.updateClue(clueId,clueSumup);
	    	
	    	if(count==0){
	    		return "err";
	    	}
	    	//重置已读未读状态
	    	pzApplyMapperImpl.backUnread(new ApplyExp(pzid));
	    	//获取线索内容
	    	ClueInfo clueinfo=this.clueService.getClueById(clueId);
	    	String desc = "合成申请已反馈，线索内容："+clueinfo.getClueName();
	    	AuditLogUtil.addWorkLog(request, "", "", pzid, flowId, SopConstants.FLOW_DFK, "", desc);
	    }
	    AuditLogUtil.addLog(request, "任务管理", "4", "提交反馈", pzid, "0");
	    return flag;
    }
    
    /**
     *  确认生成反馈报告方法
     *  @Description：将流程改为HCZZ_1008
     */
    @RequestMapping("/nextCreate")
    @ResponseBody
    public String nextCreate(String procInstId,String pzid,String flowId,String sumup){
    	Map<String,String> pzMap = indexService.selectPzZhuBan(pzid);
    	List<Task> list = taskService.createTaskQuery().processInstanceId(flowId).list();
	        if(list.size() == 0){
	            return "err";
	        }
	        String def = list.get(0).getTaskDefinitionKey();
	        if(!def.equals(SopConstants.FLOW_DSC)){
	            return "err";
	        }
    	Map<String, Object> vars = new HashMap<String, Object>();
			vars.put("user", pzMap.get("CREATE_BY"));
		Map<String, Object> resultMap = super.simpleTaskComplate(procInstId, vars, false);
		String flag = resultMap.get("code").equals(0)?"success":"err";
    	if(flag.equals("success")){
    		//更新线索综述
        	int count=this.pzfkServiceImpl.updateSumup(pzid,sumup);
        	if(count==0){
        		return "err";
        	}
    		String desc = "合成作战反馈表已生成";
    		//重置已读未读状态
            pzApplyMapperImpl.backUnread(new ApplyExp(pzid));
            //同步线索库
            this.clueService.updateResultByPz(pzid);
    		AuditLogUtil.addWorkLog(request, "", "", pzid, flowId, SopConstants.FLOW_DSC, "", desc);
    	}
    	AuditLogUtil.addLog(request, "任务管理", "4", "确认生成反馈报告", pzid, "0");
    	return flag;
    }
    
    /**
     *  反馈记录删除
     */
    @RequestMapping("/remove")
    @ResponseBody
    public String remove(String resultId,HttpServletRequest req){
    	String flag = "err";
    	//判断当前用户是否是这条反馈记录的创建者
    	User user = (User) request.getSession().getAttribute("loginUser");
    	Integer num = indexService.remove(resultId,user);
    	flag = (num == 0) ? "err":"success";
    	AuditLogUtil.addLog(request, "任务管理", "5", "线索反馈删除", resultId, "0");
    	return flag;
    }
    

    /**
     *  配侦反馈查询数据展示
     *  @param :pzid 配帧ID
     */
    @ResponseBody
    @RequestMapping("initData")
    public Map<String, Object> initData(String pzid, HttpServletRequest request) {
			return pzfkServiceImpl.initData(pzid, request);
    }

    @ResponseBody
    @RequestMapping("addsave")
    public Map<String, Object> addsave(PzResult result, HttpServletRequest request) {
    	AuditLogUtil.addLog(request, "任务管理", "3", "线索反馈新增", "", "0");
        return pzfkServiceImpl.addsave(result, request);
    }

    @ResponseBody
    @RequestMapping("saveNext")
    public Map<String, Object> saveNext(String pzid, HttpServletRequest request) {
        User usr = (User) request.getSession().getAttribute("loginUser");
        Map<String, Object> map = new HashMap<>();
        // 提报并添加下一级审批信息
        PzApprove pzApprove = new PzApprove();
        pzApprove.setApplyId(pzid);
        pzApprove.setUpdateBy(String.valueOf(usr.getUserId().intValue()));
        PzApplyVO pz = pzApplyMapperImpl.getById(pzid);
        CommonVO vo = PzApproveServiceImpl.next(pz.getUserId(),pzApprove);
        String desc = "合成申请已反馈";
        //重置已读未读状态
        pzApplyMapperImpl.backUnread(new ApplyExp(pzid));
        AuditLogUtil.addWorkLog(request, "", "", pzid, pz.getFlowId(), SopConstants.FLOW_DFK, "", desc);
        map.put("code", vo.getSuccess() ? 0 : 500);
        map.put("msg", vo.getSuccess() ? "提报成功！" : "提报失败！");
        return map;
    }
    
    @RequestMapping("/update")
    @ResponseBody
    public Map<String,Object> update(String resultId){
    	Map<String,Object> map = indexService.selectResultById(resultId);
    	AuditLogUtil.addLog(request, "任务管理", "2", "进入线索反馈编辑页面", resultId, "0");
    	return map;
    }
    
    @RequestMapping("/updateAdd")
    @ResponseBody
    public String updateAdd(PzResult result,HttpServletRequest req,String resultId){
    	Integer num = pzfkServiceImpl.update(result,resultId,req);
    	String flag = (num == 1)?"success":"err";
    	AuditLogUtil.addLog(request, "线索反馈", "4", "线索反馈修改", resultId, "0");
    	return flag;
    }
    
}
