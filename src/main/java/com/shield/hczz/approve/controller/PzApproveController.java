package com.shield.hczz.approve.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shield.frame.base.domain.ApplyExp;
import com.shield.frame.base.domain.User;
import com.shield.frame.common.Constants;
import com.shield.frame.common.qvo.CommonVO;
import com.shield.frame.common.qvo.DataGridVO;
import com.shield.frame.common.qvo.ResultVO;
import com.shield.frame.sysmng.dto.AuditLogDTO;
import com.shield.frame.sysmng.dto.TreeNode;
import com.shield.frame.sysmng.qvo.DepartRow;
import com.shield.frame.sysmng.service.CommonService;
import com.shield.frame.sysmng.service.DeptmentService;
import com.shield.frame.utils.AuditLogUtil;
import com.shield.hczz.apply.qvo.PzApplyVO;
import com.shield.hczz.apply.service.PzApplyService;
import com.shield.hczz.approve.qvo.PzApproveQO;
import com.shield.hczz.approve.qvo.PzApproveVO;
import com.shield.hczz.approve.service.PzApproveService;
import com.shield.hczz.base.controller.FlowController;
import com.shield.hczz.base.domain.ClueInfo;
import com.shield.hczz.base.domain.FlowWait;
import com.shield.hczz.base.domain.PzApply;
import com.shield.hczz.base.domain.PzApprove;
import com.shield.hczz.base.domain.PzTaskLog;
import com.shield.hczz.base.persistence.ActivitiMapper;
import com.shield.hczz.base.persistence.PzApplyMapper;
import com.shield.hczz.clue.service.ClueService;
import com.shield.hczz.common.SopConstants;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/pzapprove/*")
public class PzApproveController extends FlowController {

    @Autowired
    private PzApproveService pzApproveService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private PzApplyMapper pzApplyMapperImpl;
    @Autowired
    private PzApplyService pzApplyServiceImpl;
    @Autowired
    private ActivitiMapper activitiMapper;
    @Autowired
    private ClueService clueService;
    @Autowired
    private DeptmentService deptmentService;

    @ResponseBody
    @RequestMapping("approve")
    public ResultVO approve(HttpServletRequest request, String applyId, PzApprove pzApprove) {
        Integer userId = ((BigDecimal) request.getSession().getAttribute(Constants.SESN_USR_UID))
            .intValue();
        pzApprove.setUpdateBy(String.valueOf(userId));
        pzApprove.setUserId(userId);
        PzApplyVO v = pzApplyMapperImpl.getById(applyId);
        pzApplyMapperImpl.backUnread(new ApplyExp(applyId));
        Map<String,Object> rs = null;
        if(null != v){
            String procId = v.getFlowId();
            Map<String,Object> vars = new HashMap<String,Object>();
            vars.put("role", "1002");
            rs = this.simpleTaskComplate(procId, vars, true);
        }
        if(null != rs && "0".equals(rs.get("code").toString())){
            return ResultVO.ok();
        }
        return ResultVO.error();
    }

    @ResponseBody
    @RequestMapping("update")
    public CommonVO update(HttpServletRequest request, String applyId, PzApprove pzApprove) {
        Integer userId = ((BigDecimal) request.getSession().getAttribute(Constants.SESN_USR_UID))
            .intValue();
        pzApprove.setUpdateBy(String.valueOf(userId));
        pzApprove.setUserId(userId);
        return pzApproveService.approve(applyId, pzApprove, false);
    }

    @ResponseBody
    @RequestMapping("updates")
    public CommonVO updates(HttpServletRequest request, String ids, PzApprove pzApprove) {
        Integer userId = ((BigDecimal) request.getSession().getAttribute(Constants.SESN_USR_UID))
            .intValue();
        pzApprove.setUpdateBy(String.valueOf(userId));
        pzApprove.setUserId(userId);
        if (StringUtils.isBlank(ids)) {
            return new CommonVO(false, "待审批记录为空");
        }
        return pzApproveService.approve(ids.split(","), pzApprove, false);
    }

    /**
     * 签收（批量）
     * @param request
     * @param pzApplyIds
     * @param pzApprove
     * @return
     */
    @ResponseBody
    @RequestMapping("approves")
    public ResultVO approves(HttpServletRequest request, String pzApplyIds, PzApprove pzApprove) {
    	Integer userId = ((BigDecimal) request.getSession().getAttribute(Constants.SESN_USR_UID))
            .intValue();
        pzApprove.setUpdateBy(String.valueOf(userId));
        pzApprove.setUserId(userId);
        if (StringUtils.isBlank(pzApplyIds)) {
            AuditLogUtil.addLog(request, "任务管理", "9", "配侦任务签收", pzApplyIds, "1");
            return ResultVO.error("待审批记录为空");
        }
        String[] idArr = pzApplyIds.split(",");
        for(String id : idArr){
            PzApplyVO v = pzApplyMapperImpl.getById(id);
            String status = v.getPzApplyStatus();
            //防止重复签收
            if(!SopConstants.FLOW_ZBZQS.equals(status))
                continue;
            if(null != v){
                String procId = v.getFlowId();
                Map<String,Object> vars = new HashMap<String,Object>();
                //vars.put("role", "1002");
                this.simpleTaskComplate(procId, vars, false);
                String desc="合成申请已签收";
                //重置已读未读状态
                pzApplyMapperImpl.backUnread(new ApplyExp(id));
                AuditLogUtil.addWorkLog(request, "", "", id, v.getFlowId(),
                    SopConstants.FLOW_ZBZQS, "", desc);
                AuditLogUtil.addLog(request, "任务管理", "9", "配侦任务签收", id, "0");
            }
        }
        return ResultVO.ok();
    }
    
    /**
     * 校验当前用户是否有签收权限
     * @param ids 多个配侦id用逗号隔开
     * @param request 用户信息
     * @return
     */
    @ResponseBody
    @RequestMapping("checkReceives")
    public ResultVO checkReceives(String ids,HttpServletRequest request){
        String userId = ((BigDecimal) request.getSession().getAttribute(Constants.SESN_USR_UID))
            .toString();
        String[] idArr = ids.split(",");
        boolean flag = pzApproveService.checkReceives(idArr, userId);
        if(flag){
            return ResultVO.ok();
        }
        else{
            return ResultVO.error();
        }
    }
    /**
     * 分发总数 矫彩辉
     * 
     * 
     */
    @ResponseBody
    @RequestMapping("getSumlist")
    public DataGridVO<PzApproveVO> getSumlist(PzApproveQO pzApprove, String page, String rows) {
        return pzApproveService.getSumlist(pzApprove, page, rows);
    }

    /**
     * 分发流程 矫彩辉
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonParseException 
     * 
     * 
     */
    @ResponseBody
    @RequestMapping("addAssign")
    public CommonVO addAssign(HttpServletRequest request, String jsonStr) throws Exception {
    	ObjectMapper om = new ObjectMapper();
    	PzApprove pzApprove = om.readValue(jsonStr, PzApprove.class);
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        CommonVO cres = new CommonVO();
        cres.setSuccess(false);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("pzid", pzApprove.getApplyId());
        List<FlowWait> list=activitiMapper.getFlowInfo(map);
        if(null==list||list.size()==0){
        	return new CommonVO(true,"流程流转异常");
        }
        FlowWait pv = activitiMapper.getFlowInfo(map).get(0);
        if(pv != null && !pv.getTaskKey().equals(SopConstants.FLOW_DFP)){
            cres.setVal("-1");
            cres.setMsg("该任务已被分发！");
            return cres;
        }
        
        pzApprove.setUpdateBy(loginUser.getUserId().toString());
        pzApprove.setCreateBy(loginUser.getUserId().toString());
        List<ClueInfo> clueList= pzApprove.getCluelist();
        String userIds = getUsers(pzApprove.getCluelist());
        Map<String,Object> rs = null;
        if(null != pv){
            String procId = pv.getProcId();
            Map<String,Object> vars = new HashMap<String,Object>();
            vars.put("users", userIds);//userIds
            rs = this.simpleTaskComplate(procId, vars, false);
        }
        if(null != rs && "0".equals(rs.get("code").toString())){
        	cres = new CommonVO(true,"任务分发成功");
        	//更新主办人
        	PzApply pzApply =new PzApply();
        	pzApply.setPzApplyId(pzApprove.getApplyId());
        	pzApply.setPzMainAccept(pzApprove.getUserId().toString());
        	pzApply.setRemark(pzApprove.getRemark());
        	pzApplyServiceImpl.updateStatus( request,  pzApply);
        	//更新线索协办人
        	clueService. updateClueList(clueList);
        }else{
        	cres = new CommonVO(false,rs.get("msg").toString());
        }
        if (null == cres)
            return cres;
        boolean resSuc = cres.getSuccess();

        if (resSuc) {
        	String desc="任务已派发给合成作战中心民警";
            PzTaskLog taskLog = new PzTaskLog();
            taskLog.setPzApplyId(pzApprove.getApplyId());
            taskLog.setFlowId(pzApprove.getFlowId());
            taskLog.setCreateBy(loginUser.getUserId().toString());
            taskLog.setUpdateBy(loginUser.getUserId().toString());
            taskLog.setTaskStatus(SopConstants.FLOW_DFP);
            taskLog.setTaskDesc(desc);
            taskLog.setCreateDt(new Date());
            taskLog.setUpdateDt(new Date());
            commonService.addTaskLog(taskLog);
            
            //同步线索库
            this.clueService.synchroClueDb(pzApprove.getApplyId());
            AuditLogUtil.addLog(request, "任务管理", "4", "任务分发成功", pzApprove.getApplyId(), "0"); // 记录日志
        }
        else {
            AuditLogDTO auditLogDTO = new AuditLogDTO();
            auditLogDTO.setResult("分发操作失败");
            AuditLogUtil.addLog(request, "任务管理", "4", "任务分发失败!", pzApprove.getApplyId(), "1"); // 记录日志
        }
        return cres;
    }
    
    /**
     * 取出选中的人
     * @param cluelist
     * @return
     */
    private String getUsers(List<ClueInfo> cluelist){
        List<String> list = new ArrayList<>();
        for(ClueInfo c : cluelist){
            list.add(c.getPzResultCreateBy());
        }
        String user = "";
        for(String s : list){
            user += s+",";
        }
        user = user.substring(0, user.length()-1);
        return user;
    }
    
    /**
     * 去重
     * @param cluelist
     * @return
     */
    private String distinct(List<ClueInfo> cluelist) {
        Set<String> set = new HashSet<>();
        for(ClueInfo c : cluelist){
            set.add(c.getPzResultCreateBy());
        }
        String user = "";
        for(String s : set){
            user += s+",";
        }
        user = user.substring(0, user.length()-1);
        return user;
    }

    /**
     * 获得部门树，根据传入的参数不同，会显示不同结果。
     * 如果两个参数都为空，则使用用户所辖业务范围（包含对应部门,admin不限制），若两个参数都不为空，则默认使用deptId这个参数
     * @param fid 父部门id，结果列表不会包含fid对应的部门
     * @param deptId 父部门id，结果会包含deptId对应的部门
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getDeptTree")
    public List<TreeNode> getDeptTree(String fid, String deptId, HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        if ((fid == null || "".equals(fid)) && (deptId == null || "".equals(deptId))) {
            User user = (User) request.getSession().getAttribute("loginUser");
            if ("admin".equals(user.getUserName())) {
                map.put("parent", 0);
            }
            else {
                map.put("deptId", user.getDeptId());
            }
        }
        else if (deptId != null && !"".equals(deptId)) {
            map.put("deptId", deptId);
        }
        else {
            map.put("parent", fid);
        }
        List<TreeNode> treeNodes = new ArrayList<TreeNode>();
        List<DepartRow> list = null;
        list = this.deptmentService.getDeptList(map);
        if (null != list && list.size() > 0) {
            for (DepartRow d : list) {
                TreeNode t = new TreeNode();
                t.setId(d.getId().toString());
                t.setText(d.getName());
                t.setName(d.getName());
                t.setpId(
                    (null == d.getPid() || "".equals(d.getPid()) ? "" : d.getPid().toString()));
                t.setParentid(
                    (null == d.getPid() || "".equals(d.getPid()) ? "" : d.getPid().toString()));
                if ("open".equals(d.getState())) {
                    t.setState("open");
                    t.setOpen(true);
                    t.setParent(false);
                }
                else {
                    t.setState("closed");
                    t.setOpen(false);
                    t.setParent(true);
                }
                treeNodes.add(t);
            }
        }
        return treeNodes;
    }
}
