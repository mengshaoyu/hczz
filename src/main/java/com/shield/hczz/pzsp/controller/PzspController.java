package com.shield.hczz.pzsp.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shield.frame.base.domain.ApplyExp;
import com.shield.frame.base.domain.User;
import com.shield.frame.utils.AuditLogUtil;
import com.shield.frame.utils.CommonUtil;
import com.shield.hczz.apply.qvo.PzApplyVO;
import com.shield.hczz.base.controller.FlowController;
import com.shield.hczz.base.domain.FlowWait;
import com.shield.hczz.base.persistence.ActivitiMapper;
import com.shield.hczz.base.persistence.HctbMapper;
import com.shield.hczz.base.persistence.PzApplyMapper;
import com.shield.hczz.common.SopConstants;
import com.shield.hczz.index.mapper.IndexMapper;
import com.shield.hczz.pzsp.dto.PzspDTO;
import com.shield.hczz.pzsp.dto.WordDataDTO;
import com.shield.hczz.pztb.dto.AssigneeCompleteInfo;
import com.shield.hczz.pztb.service.PztbService;
import com.shield.hczz.utils.XwpfTUtil;

@Controller
@RequestMapping("pzsp")
public class PzspController extends FlowController {
    @Autowired
    private PztbService pztbService;
    @Autowired
    private PzApplyMapper pzApplyMapper;
    @Autowired
    private ActivitiMapper activitiMapper;
    
    @Autowired
    private HctbMapper hctbMapper;
    @Autowired
    private IndexMapper indexMapper;
    
    /**
     * 初始化页面
     * @param mod
     * @param pzsp 
     * @param request
     * @return
     */
    @RequestMapping("init")
    public String init(Model mod, String isprint, PzspDTO pzsp,HttpServletRequest request) {
        mod.addAttribute("pzid", pzsp.getPzid());
        mod.addAttribute("spjl",pzsp.getSpjl());
        if(isprint != null && !isprint.equals("")){
            mod.addAttribute("isprint", "1");
            AuditLogUtil.addLog(request, "任务管理", "6", "打印合成作战申请表", pzsp.getPzid(), "0"); // 记录日志
        }else{
            AuditLogUtil.addLog(request, "任务管理", "1", "查看合成作战申请表", pzsp.getPzid(), "0"); // 记录日志
        }
        Map<String, Object> flowInfoparam = new HashMap<>();
        flowInfoparam.put("pzid", pzsp.getPzid());
        List<FlowWait> fw = activitiMapper.getFlowInfo(flowInfoparam);
        String actId = "";
        if(fw.size() > 0){
            actId = fw.get(0).getTaskKey();
        }
        if(actId.equals(SopConstants.FLOW_FGLDSP) || actId.equals(SopConstants.FLOW_SQPZ) || actId.equals(SopConstants.FLOW_TZBC)){
            mod.addAttribute("actId", "1");
        }else{
            mod.addAttribute("actId", "2");
        }
        return "pzsp/pzsp";
    }

    /**
     * 导出word
     * @param wdd
     * @param request
     * @param response
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "exportWord")
    public void exportWord(WordDataDTO wdd, HttpServletRequest request, HttpServletResponse response)
        throws UnsupportedEncodingException {
        String fileName = "配侦请求申请表.doc";
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition",
            "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));
        XwpfTUtil util = new XwpfTUtil();
        //调用导出工具类（poi）
        util.downApply(request.getSession().getServletContext().getRealPath("/docModel/")
            + "/apply.xml", response, wdd);
        AuditLogUtil.addLog(request, "任务管理", "6", "导出配侦请求申请表word", wdd.getPzApplyNo(), "0"); // 记录日志
    }
    
    /**
     * 单位领导审批-->值班长签收 || 值班长审批-->分发
     * @param pzsp
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "saveNext")
    public Map<String, Object> saveNext(PzspDTO pzsp, HttpServletRequest request) {
        PzApplyVO pv = pzApplyMapper.getById(pzsp.getPzid());
        String procInstId = pv.getFlowId();
        Map<String, Object> result = new HashMap<>();
        ProcessInstance pi=runtimeService.createProcessInstanceQuery() // 根据流程实例id获取流程实例
            .processInstanceId(procInstId)
            .singleResult();
        //如果不是两个审批则return
        if(pi.getActivityId().equals(SopConstants.FLOW_DZBZSP) || pi.getActivityId().equals(SopConstants.FLOW_FGLDSP)){
            Map<String, Object> vars = new HashMap<>();
            if(pzsp.getSpr() != null && pzsp.getSpr().length > 0){
                vars.put("zbzusers", CommonUtil.joinWith(pzsp.getSpr(), ","));
            }
            //调用审批环节方法
            Map<String, Object> flowRet = approveTaskComplate(procInstId, vars, pzsp.getSpyj(), "1".equals(pzsp.getSpjl()));
            
            if ("0".equals(flowRet.get("code").toString())) {
                result.put("code", 0);
                result.put("msg", "审批成功!");
            } else {
                result.put("code", 500);
                result.put("msg", flowRet.get("msg").toString());
                return result;
            }
            String desc = "审批" + (pzsp.getSpjl().equals("1") ? "通过" : "驳回");
            //重置已读未读状态
            pzApplyMapper.backUnread(new ApplyExp(pzsp.getPzid()));
            //插入配帧记录
            AuditLogUtil.addWorkLog(request, "", "", pzsp.getPzid(), procInstId, pi.getActivityId(), "", desc);
            AuditLogUtil.addLog(request, "任务管理", "4", desc+" "+pv.getPzApplyNo(), pzsp.getPzid(), "0"); // 记录日志
            return result;
        }
        result.put("code", 500);
        result.put("msg", "当前任务已经有人审批，请勿重复操作！");
        return result;
    }
    
    /**
     * 加载配帧信息，用于前台申请表展示
     * @param pzid
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "loadHctb")
    public Map<String, Object> initData(String pzid, HttpServletRequest request) {
        User usr = (User) request.getSession().getAttribute("loginUser");
        //查询配帧信息
        Map<String, Object> map = pztbService.loadHctb(pzid);
        PzApplyVO pv = pzApplyMapper.getById(pzid);
        String procInstId = pv.getFlowId();
        Map<String, Object> param = new HashMap<>();
        param.put("actId", SopConstants.FLOW_SQPZ);
        param.put("procInstId", procInstId);
        //查询申请人信息
        List<AssigneeCompleteInfo> assigneeCompleteInfos = activitiMapper.assigneeCompleteInfo(param);
        map.put("assigneeCompleteInfo", assigneeCompleteInfos.get(0));
        Map<String, Object> flowInfoparam = new HashMap<>();
        flowInfoparam.put("pzid", pzid);
        List<FlowWait> flowInfo = activitiMapper.getFlowInfo(flowInfoparam);
        String actId = "";
        if(flowInfo.size() > 0){
            actId = flowInfo.get(0).getTaskKey();
        }
        param.put("actId", SopConstants.FLOW_FGLDSP);
        boolean fgldspflag = actId.equals(SopConstants.FLOW_SQPZ) 
                                || actId.equals(SopConstants.FLOW_FGLDSP);
        //查询分管领导审批意见
        Map<String, Object> fgldsp = fgldspflag ? null : activitiMapper.getComment(param);
        map.put("fgldsp", fgldsp);
        
        param.put("actId", SopConstants.FLOW_DZBZSP);
        boolean zbzspflag = fgldspflag 
                                || actId.equals(SopConstants.FLOW_ZBZQS)
                                || actId.equals(SopConstants.FLOW_DZBZSP);
        //查询值班长审批意见
        Map<String, Object> zbzsp = zbzspflag ? null : activitiMapper.getComment(param);
        map.put("zbzsp", zbzsp);
        
        Map<String, Object> flowWaitParam = new HashMap<>();
        flowWaitParam.put("procId", procInstId);
        flowWaitParam.put("userId", usr.getUserId().toString());
        List<FlowWait> flowList = activitiMapper.flowWait(flowWaitParam);
        map.put("spBtnAuth", flowList.size() > 0);
        map.put("currFlow", flowList.size() > 0 ? flowList.get(0).getTaskKey() : "");
        return map;
    }

    /**
     * 查询线索信息 -- 分发调用
     * @param mod
     * @param pzsp
     * @param request
     * @return
     */
    @RequestMapping("initdis")
    public String initdis(Model mod, PzspDTO pzsp,HttpServletRequest request) {
      //线索内容
        PzApplyVO pzApplyVO=pzApplyMapper.getInfoById(pzsp.getPzid());
        mod.addAttribute("pzid", pzsp.getPzid());
        mod.addAttribute("flowId", pzApplyVO.getFlowId());
        mod.addAttribute("caseName", pzApplyVO.getCaseName());
        mod.addAttribute("caseId", pzApplyVO.getCaseId());
        mod.addAttribute("caseNo", pzApplyVO.getCaseNo());
        AuditLogUtil.addLog(request, "任务管理", "4", "进入分发页面", pzsp.getPzid(), "0"); // 记录日志
        return "apply/taskAssgin";
    }
    
    /**
     * 查询线索信息
     * @param mod
     * @param pzsp
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("loadClue")
    public Map<String,Object>  loadClue(Model mod, PzspDTO pzsp,HttpServletRequest request) {
       Map<String ,Object > pzMap=new HashMap<String ,Object >();
        pzMap.put("pzid", pzsp.getPzid());
        //线索内容
        List<Map<String,Object>> clueMap = hctbMapper.selectClueByPzId(pzMap);
        for (int i = 0; i < clueMap.size(); i++) {
            //根据code查码表value
            clueMap.get(i).put("PZ_TYPE_DETAIL", indexMapper.selectCodeBycodeValue(clueMap.get(i).get("PZ_TYPE_DETAIL").toString()));
        }
        pzMap.put("clue",clueMap);
        return pzMap;
    }
}
