package com.shield.hczz.apply.controller;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shield.frame.base.domain.Role;
import com.shield.frame.base.domain.User;
import com.shield.frame.common.Constants;
import com.shield.frame.common.qvo.CommonVO;
import com.shield.frame.common.qvo.DataGridVO;
import com.shield.frame.common.qvo.ResultVO;
import com.shield.frame.sysmng.dto.UserDTO;
import com.shield.frame.sysmng.qvo.ToolBarVO;
import com.shield.frame.utils.AuditLogUtil;
import com.shield.hczz.apply.qvo.PzApplyQO;
import com.shield.hczz.apply.qvo.PzApplyVO;
import com.shield.hczz.apply.service.PzApplyService;
import com.shield.hczz.base.controller.FlowController;
import com.shield.hczz.base.domain.ClueInfo;
import com.shield.hczz.base.domain.FlowWait;
import com.shield.hczz.base.domain.PzApply;
import com.shield.hczz.base.persistence.ActivitiMapper;
import com.shield.hczz.common.SopConstants;
import com.shield.hczz.flow.qvo.TaskFlowVO;
import com.shield.hczz.pzfk.service.PzfkService;
import com.shield.hczz.pzlog.service.PzLogService;
import com.shield.hczz.utils.BeanUtil;
import com.shield.hczz.utils.ImgUtil;
import com.shield.hczz.utils.XwpfTUtil;

@Controller
@RequestMapping("/pztask/*")
public class PzApplyController extends FlowController{
    
    private static final String signature_src = "/attachServer/signature";

    @Autowired
    private PzApplyService pzApplyService;

    @Autowired
    private PzLogService pzLogService;

    @Autowired
    private PzfkService pzfkServiceImpl;
    
    @Autowired
    private ActivitiMapper activitiMapper;

    /**
     * 列表初始化
     * @param request
     * @param isdbsx 是否仅显示代办任务
     * @param status 
     * @return
     */
    @RequestMapping("init")
    public String init(HttpServletRequest request, String isdbsx,String status) {
        if(isdbsx != null && !"".equals(isdbsx)){
            request.setAttribute("isdbsx", isdbsx);
        }
        if(status != null && !"".equals(status)){
            request.setAttribute("status", status);
        }
        AuditLogUtil.addLog(request, "任务管理", "2", "进入任务管理页面", "", "0");
        return "apply/applyList";
    }

    /**
     * 初始化任务详情界面
     * @param pzApplyId	配侦主键
     * @param sfbj	是否编辑的标识：0，不可编辑；1，编辑
     * @param flag	详情界面Tab页签的标识：0，任务基本信息；1，线索反馈
     * @param request	当前请求对象
     * @return	返回指定跳转界面
     */
    @RequestMapping("initDetail")
    public String initDetail(String pzApplyId, String sfbj,String flag, HttpServletRequest request) {
        request.setAttribute("pzApplyId", pzApplyId);
        request.setAttribute("sfbj", sfbj);
        //获取合成申请信息
        PzApplyVO apply=this.pzApplyService.getInfoById(pzApplyId);
        request.setAttribute("caseName", apply.getCaseName());
        String roleFK = "";
        User user = (User) request.getSession().getAttribute("loginUser");
        List<Role> roleList = user.getRoleList();
        for (int i = 0; i < roleList.size(); i++) {
            Role r = roleList.get(i);
            String roleId = r.getRoleId().toString();
            if (null != roleId && (roleId.equals("1003") || roleId.equals("2")))
                roleFK = "1003";
        }
        request.setAttribute("roleFK", roleFK);
        request.setAttribute("flag", flag);//详情界面Tab的位置标识
        return "apply/applyListDetail";

    }

	/**
	 * 获取配侦任务列表
	 * @param qo 查询参数
	 * @param page 页数
	 * @param rows 每页显示的行数
	 * @param request
	 * @return
	 */
	@ResponseBody
    @RequestMapping("getlist")
    public ResultVO getlist(PzApplyQO qo, String page, String rows, HttpServletRequest request) {
    	User user = (User) request.getSession().getAttribute("loginUser");
        qo.setUserId(String.valueOf(user.getUserId()));
        AuditLogUtil.addLog(request, "任务管理", "1", "查询任务管理列表", "", "0");
       return pzApplyService.getlist(qo, page, rows,user);
    }
	
	/**
	 * 查询代办任务数量
	 * @param qo 查询类
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("getToDoCount")
	public ResultVO getToDoCount(PzApplyQO qo, HttpServletRequest request) throws Exception {
	    User user = (User) request.getSession().getAttribute("loginUser");
	    qo.setUserId(String.valueOf(user.getUserId()));
	    qo.setIsdbsx("1");
	    String status = qo.getPzApplyStatus();
        if(status != null && !"".equals(status)){
            String[] statusArr = status.split(",");
            StringBuffer sb = new StringBuffer();
            for(String sta : statusArr){
                sb.append("'");
                sb.append(sta);
                sb.append("',");
            }
            sb.deleteCharAt(sb.length()-1);
            qo.setPzApplyStatus(sb.toString());
        }
	    Map<String,Object> param = BeanUtil.toMap(qo);
	    int count = pzApplyService.getCount(param);
	    return ResultVO.ok(count);
	}
	
    @ResponseBody
    @RequestMapping("add")
    public CommonVO add(HttpServletRequest request, PzApply pzApply) {
        return pzApplyService.add(request, pzApply);
    }

    @ResponseBody
    @RequestMapping("delete")
    public ResultVO delete(String ids, HttpServletRequest request) {
        try {
            User usr = (User) request.getSession().getAttribute("loginUser");
            String userId = usr.getUserId().toString();
            pzApplyService.updateDeleted(ids, userId);
            AuditLogUtil.addLog(request, "任务管理", "5", "删除配侦任务", "", "0");
            return ResultVO.ok();
        }
        catch (Exception e) {
            e.printStackTrace();
            AuditLogUtil.addLog(request, "任务管理", "5", "删除配侦任务", ids, "1");
            return ResultVO.error("删除失败");
        }

    }

    /**
     * 初始化反馈报告界面
     * @param pzid	配侦申请主键
     * @param request
     * @return	返回反馈表界面
     */
    @RequestMapping("initFeedback")
    public String initFeedback(String pzid, String isprint, HttpServletRequest request) {
        System.out.println("配侦ID：" + pzid);
        if(isprint != null && !isprint.equals("")){
            request.setAttribute("isprint", "1");
            AuditLogUtil.addLog(request, "任务管理", "6", "打印配侦反馈报告", pzid, "0");
        }else{
            AuditLogUtil.addLog(request, "任务管理", "1", "查看配侦反馈报告", pzid, "0");
        }
        //获取当前配侦申请的流程信息
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("pzid", pzid);
        List<FlowWait> flowlist=activitiMapper.getFlowInfo(map);
        FlowWait pv = (null!=flowlist&&flowlist.size()>0)?activitiMapper.getFlowInfo(map).get(0):null;
        
        //判断是否显示落地情况按钮
        if((null==pv)||(null!=pv&&pv.getTaskKey().equals(SopConstants.FLOW_DHS)||pv.getTaskKey().equals(SopConstants.FLOW_OVER))){
        	request.setAttribute("show", "1");
        }else{
        	request.setAttribute("show", "0");
        }

        // 获取配侦申请信息
        PzApplyVO apply = this.pzApplyService.getApplyMsgById(pzid);

        // 获取指定任务信息的反馈报告
        Map<String, Object> feedBackMap = this.pzfkServiceImpl.initData(pzid, request);

        //根据配侦主键、流程环节标识，获取配侦信息，主要获取对应的环节流转时间
        PzApplyVO applyBack = this.pzApplyService.getFlowTime(pzid, SopConstants.FLOW_DZBZSP,
                SopConstants.FLOW_DHS,"");
        request.setAttribute("applyBack", applyBack);
        
        //根据配侦主键，获取待反馈环节
        List<UserDTO> userlist=this.pzfkServiceImpl.getBackUser(pzid);
        request.setAttribute("userlist", userlist);
        
        if((null!=pv&&pv.getTaskKey().equals(SopConstants.FLOW_DHS))){
        	request.setAttribute("dhs", "1");
        }
        request.setAttribute("applyMap", apply);
        request.setAttribute("feedBackMap", feedBackMap);
        
        return "apply/feedBack";
    }

    @RequestMapping("initFlow")
    public String initFlow(String pzid, HttpServletRequest request) {
        request.setAttribute("pzid", pzid);
        String name=this.pzApplyService.getFlowNameById(pzid);
        request.setAttribute("name", name);
        return "apply/taskFlow";
    }

    @ResponseBody
    @RequestMapping("saveEvaluate")
    public CommonVO saveEvaluate(PzApply pzApply, HttpServletRequest request) {
    	CommonVO com=new CommonVO();
    	try{
    		User usr = (User) request.getSession().getAttribute("loginUser");
    		String userId = usr.getUserId().toString();
    		pzApply.setUpdateBy(userId);
    		//更新配侦信息，补充落地情况反馈及评价
    		int count = pzApplyService.saveEvaluate(pzApply);
    		
    		//获取流程实例ID
    		Map<String,Object> map = new HashMap<String,Object>();
    		map.put("pzid", pzApply.getPzApplyId());
    		List<FlowWait> list=activitiMapper.getFlowInfo(map);
    		if(null==list||list.size()==0){
    			com.setSuccess(false);
    			com.setMsgInfo("评价失败！");
    			return com;
    		}
    		
    		//获取当前配侦申请的当前流转环节
    		FlowWait pv = activitiMapper.getFlowInfo(map).get(0);
    		if(null != pv){
    			//流程流转
    			String procId = pv.getProcId();
    			Map<String,Object> rs = this.simpleTaskComplate(procId, null, false);
    			
    			//重置已读未读状态
    			pzApplyService.unread(pzApply.getPzApplyId());
    			//填写配侦日志
    			if(null != rs && "0".equals(rs.get("code").toString())){
    				AuditLogUtil.addWorkLog(request, "", "", pzApply.getPzApplyId(), pv.getProcId(), pv.getTaskKey(),
    						"", "任务完成，核实评价已完成。");
    			}
    			com.setSuccess(true);
    			com.setMsgInfo("评价成功！");
    			AuditLogUtil.addLog(request, "任务管理", "4", "进行任务评价", pzApply.getPzApplyId(), "0");
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		com.setSuccess(false);
    		com.setMsgInfo(e.getMessage());
    		AuditLogUtil.addLog(request, "任务管理", "4", "进行任务评价", pzApply.getPzApplyId(), "1");
    	}

        return com;
    }

    /**
     * 获取工作进展列表
     * @param pzid	配侦主键
     * @return	返回工作进展界面
     */
    @ResponseBody
    @RequestMapping("getTaskLogs")
    public DataGridVO<TaskFlowVO> getTaskLogs(String pzid) {
        DataGridVO<TaskFlowVO> result = new DataGridVO<>();
        
      //获取流程实例ID
        String key="";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("pzid", pzid);
		List<FlowWait> flowlist=activitiMapper.getFlowInfo(map);
		if(null!=flowlist&&flowlist.size()>0){
			//获取当前配侦申请的当前流转环节
			FlowWait pv = flowlist.get(0);
			if(null!=pv){
				key=pv.getTaskKey();
			}
		}
        
        List<TaskFlowVO> list = pzLogService.getLogsById(pzid,key);
        if (!list.isEmpty()) {
            result.setRows(list);
        }
        AuditLogUtil.addLog(request, "任务管理", "2", "查看任务工作进展", pzid, "0");
        return result;
    }

    @ResponseBody
    @RequestMapping("getToDoCounts")
    public ResultVO getToDoCounts(HttpServletRequest request, String status) {
        Integer userId = ((BigDecimal) request.getSession().getAttribute(Constants.SESN_USR_UID))
            .intValue();
        return ResultVO.ok(pzApplyService.getToDoCount(String.valueOf(userId), status));
    }

    /**
     * 设置用户已读
     * @param request
     * @param pzid 配侦任务ID
     */
    @ResponseBody
    @RequestMapping("read")
    public ResultVO read(HttpServletRequest request, String pzid) {
        Integer userId = ((BigDecimal) request.getSession().getAttribute(Constants.SESN_USR_UID))
            .intValue();
        return pzApplyService.read(pzid, String.valueOf(userId));
    }
    
    @RequestMapping("initMission")
    public String initMission(HttpServletRequest request, String pzApplyId) {
        request.setAttribute("pzApplyId", pzApplyId);
        return "apply/taskAssgin";
    }
    
    /**
     * 初始化落地情况反馈及评价界面
     * @param request
     * @param pzApplyId	配侦申请主键
     * @return	落地情况反馈及评价界面
     */
    @RequestMapping("initEvaluate")
    public String initEvaluate(HttpServletRequest request, String pzApplyId) {
    	//获取当前配侦信息的最长反馈时效
    	String backTime=this.pzApplyService.getBackTimeLength(pzApplyId);
    	 // 获取反馈时效
        PzApplyVO applyBack = this.pzApplyService.getFlowTime(pzApplyId, SopConstants.FLOW_DFK,
            SopConstants.FLOW_DHS , backTime);
        request.setAttribute("applyBack", applyBack);
        request.setAttribute("pzApplyId", pzApplyId);
        request.setAttribute("backTime", backTime);
        
        return "apply/evaluate";
    }
    
    /**
     * 导出反馈表
     * @param pzid	配侦申请
     * @param request	
     * @param response
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "exportWord")
    public void exportWord(String pzid,HttpServletRequest request, HttpServletResponse response)
        throws UnsupportedEncodingException {
        // 获取配侦申请信息
        PzApplyVO apply = this.pzApplyService.getApplyMsgById(pzid);

        // 获取指定任务信息的反馈报告
        Map<String, Object> feedBackMap = this.pzfkServiceImpl.initData(pzid, request);

        // 获取反馈内容的条数
        @SuppressWarnings("unchecked")
        List<ClueInfo> list = (List<ClueInfo>) feedBackMap.get("clues");
        
        //根据配侦主键、流程环节标识，获取配侦信息，主要获取对应的环节流转时间
        PzApplyVO applyBack = this.pzApplyService.getFlowTime(pzid, SopConstants.FLOW_DZBZSP,
                SopConstants.FLOW_DHS,"");
        apply.setBackDate(applyBack.getBackDate());
        
        
        //根据配侦主键，获取待反馈环节
        List<UserDTO> userlist=this.pzfkServiceImpl.getBackUser(pzid);
        
        String root = request.getSession().getServletContext().getRealPath("").replaceAll("\\\\", "/");
        root = root.substring(0, root.lastIndexOf("/"));
        for(UserDTO dto : userlist){
            String base64Str = null;
            //添加电子签章
            try {
                String pic = dto.getUserNo()+".gif";
                String signaturePath = root + signature_src + "/user/" + pic;
                base64Str = ImgUtil.encodeToString(signaturePath);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            dto.setSignatureStr(base64Str);
        }
        
        String fileName = "配侦请求研判反馈报告.doc";
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition",
            "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));
        
        XwpfTUtil xwpfTUtil = new XwpfTUtil();
        ByteArrayOutputStream bas = xwpfTUtil.uploadReply( apply, list,userlist);
        AuditLogUtil.addLog(request, "任务管理", "6", "配侦请求研判反馈报告导出", pzid, "0");
        XwpfTUtil.renderWord(response, bas.toByteArray(), "配侦请求研判反馈报告");
        
    }
    
    /**
     * 初始化报告查看界面
     * @param pzid	配侦申请主键
     * @param request
     * @return	报告查看界面
     */
    @RequestMapping("initReport")
    public String initReport(String pzid, HttpServletRequest request) {
        System.out.println("配侦ID：" + pzid);
        // 获取配侦申请信息
        PzApplyVO apply = this.pzApplyService.getInfoById(pzid);
        
      //获取当前配侦申请的流程信息
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("pzid", pzid);
        List<FlowWait> flowlist=activitiMapper.getFlowInfo(map);
        FlowWait pv = (null!=flowlist&&flowlist.size()>0)?activitiMapper.getFlowInfo(map).get(0):null;
        
        //判断反馈报告是否已生成
        String key=null==pv?"":pv.getTaskKey()+"";
        if("".equals(key)||key.equals(SopConstants.FLOW_DHS)||key.equals(SopConstants.FLOW_OVER)){
        	request.setAttribute("show", "1");
        }else{
        	request.setAttribute("show", "0");
        }
        request.setAttribute("apply", apply);

        return "apply/report";
    }
    
    /**
     * 控制按钮的显示
     * @param request
     * @param pzid	配侦申请主键
     * @param menuId	按钮主键
     * @param toolbar	按钮类型标识：2，任务管理界面的操作；3，任务详情的接口
     * @return	检索结果
     */
    @ResponseBody
    @RequestMapping("getMenuButton")
    public ResultVO getMenuButton(HttpServletRequest request, String pzid,String menuId,String toolbar) {
    	User user = (User) request.getSession().getAttribute("loginUser");
        
        PzApplyVO apply = this.pzApplyService.getInfoById(pzid);
        String key = null;
        List<String> us = new ArrayList<String>();
        if(null != apply){
            String flowId = apply.getFlowId();
        	 List<Task> t = taskService.createTaskQuery().processInstanceId(flowId).list();
        	 List<HashMap<String,Object>> assignees = this.pzApplyService.getAssignees(pzid);
        	 for(HashMap<String,Object> p : assignees){
        	     us.add(p.get("USER_ID")==null?"":p.get("USER_ID").toString());
        	 }
             if(t.size() > 0){
               key=t.get(0).getTaskDefinitionKey();
             }
        }
        boolean isCurrent = us.contains(user.getUserId().toString());
        List<ToolBarVO> list=pzApplyService.getMenuButton(apply,key, user,menuId,toolbar,isCurrent);
        
        return ResultVO.ok(list);
    }
    
    /**
     * 控制任务详情界面Tab的显示
     * @param request
     * @param pzid	配侦申请主键
     * @return	每个Tab的显示状态
     */
    @ResponseBody
    @RequestMapping("getTabShow")
    public String getTabShow(HttpServletRequest request, String pzid) {
    	
    	User user = (User) request.getSession().getAttribute("loginUser");
        String[] atrr={"1","1","1","1","0"};
    	//获取当前配侦申请的流程信息
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("pzid", pzid);
        List<FlowWait> flowlist=activitiMapper.getFlowInfo(map);
        FlowWait pv = (null!=flowlist&&flowlist.size()>0)?activitiMapper.getFlowInfo(map).get(0):null;
        
        String key=(null==pv)?"":pv.getTaskKey();
        
        String roleFK = "";
        List<Role> roleList = user.getRoleList();
        for (int i = 0; i < roleList.size(); i++) {
            Role r = roleList.get(i);
            String roleId = r.getRoleId().toString();
            if (null != roleId && (roleId.equals("1003") || roleId.equals("2")))
                roleFK = "1003";
        }
        
        //反馈线索
        if(!((key.equals(SopConstants.FLOW_DFK)||key.equals(SopConstants.FLOW_DSC)&&"1003".equals(roleFK))||key.equals(SopConstants.FLOW_DHS)||key.equals(""))){
        		atrr[1]="0";
        }else{
        	//判断当前用户是否参与pzid的相关环节
        	List<TaskFlowVO> list=this.pzLogService.getTaskFlow(pzid);
        	
        	String	flag="0";
        	for(TaskFlowVO v:list){
        		//参与过待反馈及确认反馈环节的合成民警
        		if((key.equals("")||SopConstants.FLOW_DFK.equals(v.getStatus())||key.equals(SopConstants.FLOW_DHS)||SopConstants.FLOW_DSC.equals(v.getStatus()))&&user.getUserId().toString().equals(v.getUserId())){//状态相同，且是当前登录用户
        			flag="1";
        		}
        	}
        	if(!"1".equals(flag)){
        		atrr[1]="0";
        	}
        }
        //工作进展&报告
        if(key.equals(SopConstants.FLOW_SQPZ)){
        	atrr[2]="0";
        	atrr[3]="0";
        }
        boolean flag = false;
        for(Role r : user.getRoleList()){
            if(SopConstants.ROLE_HCZXLD1.equals(r.getRoleId().toString())||SopConstants.ROLE_HCZXLD2.equals(r.getRoleId().toString())){
                flag = true;
                break;
            }
        }
        //合成效能
        if((key == null || "".equals(key)) && flag){
            atrr[4] = "1";
        }
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < atrr.length; i++){
        	sb. append(atrr[i]);
        	if(i<atrr.length-1){
        		sb.append(",");
        	}
        }
       
        return sb.toString();
    }
}
