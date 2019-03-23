package com.shield.hczz.approve.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shield.frame.base.domain.Role;
import com.shield.frame.base.domain.User;
import com.shield.frame.base.persistence.impl.UserMapperImpl;
import com.shield.frame.common.qvo.CommonVO;
import com.shield.frame.common.qvo.DataGridVO;
import com.shield.hczz.apply.qvo.PzApplyVO;
import com.shield.hczz.approve.qvo.PzApproveQO;
import com.shield.hczz.approve.qvo.PzApproveVO;
import com.shield.hczz.approve.service.PzApproveService;
import com.shield.hczz.base.domain.PzApply;
import com.shield.hczz.base.domain.PzApprove;
import com.shield.hczz.base.persistence.impl.FlowMapperImpl;
import com.shield.hczz.base.persistence.impl.PzApplyMapperImpl;
import com.shield.hczz.base.persistence.impl.PzApproveMapperImpl;
import com.shield.hczz.common.SopConstants;
import com.shield.hczz.flow.qvo.FlowVO;
import com.shield.hczz.index.mapper.IndexMapper;

@Service
public class PzApproveServiceImpl implements PzApproveService {

    @Autowired
    private PzApproveMapperImpl pzApproveMapperImpl;

    @Autowired
    private PzApplyMapperImpl pzApplyMapperImpl;

    @Autowired
    private FlowMapperImpl flowMapperImpl;

    @Autowired
    private UserMapperImpl userMapperImpl;
    
    @Autowired
    private IndexMapper indexMapper;

    /**
     * 下发审批记录的方式
     */
    enum ApproveType {
        /**
         * 通过角色下发
         */
        ROLE, /**
               * 该部门角色的所有用户ID
               */
        ALL_IDS, /**
                  * 指定用户ID（应该通过角色过滤）
                  */
        APPOINTED_IDS
    }

    @Override
    public CommonVO add(PzApprove pzApprove) {
        int count = pzApproveMapperImpl.add(pzApprove);
        boolean success = count > 0 ? true : false;
        return new CommonVO(success);
    }

    @Override
    public CommonVO approve(String userIds, PzApprove pzApprove) {
        return this.approve(userIds, pzApprove, true);
    }

    @Override
    public CommonVO approve(String userIds, PzApprove pzApprove, boolean hasPower) {
        String pzApplyId = pzApprove.getApplyId();
        PzApplyVO pzApplyVO = pzApplyMapperImpl.getById(pzApplyId);
        if (pzApplyVO != null && pzApplyVO.getFlowId().equals("1005")) {
            String mainAccept = pzApprove.getActByType();
            if (null != mainAccept)
                pzApplyVO.setPzMainAccept(mainAccept);
        }
        if (hasPower) {
            if ("1".equals(pzApprove.getResult())) {
                return this.agree(pzApplyVO, userIds, pzApprove);
            }
            else if ("2".equals(pzApprove.getResult())) {//驳回后流程转到申请人
                /** 申请用户ID **/
                String applyUserId = pzApplyVO.getUserId();
                return this.refuse(pzApplyVO, applyUserId, pzApprove);
            }
            else {
                return new CommonVO(false, "审批异常");
            }
        }
        else if (pzApprove.getUpdateBy() != null && !"".equals(pzApprove.getUpdateBy())) {
            String flowId = pzApplyVO.getFlowId();
            return this.updateOneApprove(pzApplyId, flowId, pzApprove);
        }
        else {
            return new CommonVO(false, "审批异常！");
        }
    }

    @Override
    public CommonVO approve(String[] pzApplyIds, String userIds, PzApprove pzApprove) {
        return this.approve(pzApplyIds, userIds, pzApprove, true);
    }

    @Override
    public CommonVO approve(String[] pzApplyIds, String userIds, PzApprove pzApprove,
        final boolean hasPower) {
        try {
            for (String applyId : pzApplyIds) {
                pzApprove.setApplyId(applyId);
                this.approve(userIds, pzApprove, hasPower);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return new CommonVO(false, "批量审批失败");
        }
        return new CommonVO(true, "审批成功");
    }

    @Override
    public CommonVO agree(PzApplyVO pzApplyVO, String userIds, PzApprove pzApprove) {
        String applyId = pzApplyVO.getPzApplyId();
        /** 当前登录用户 **/
        String userId = pzApprove.getUpdateBy();
        /** 申请用户ID **/
        String applyUserId = pzApplyVO.getUserId();
        String nextFlowId = "0";
        /** 获取流程并判断流程类型 做不同操作 **/
        String flowId = pzApplyVO.getFlowId();
        FlowVO flowVO = flowMapperImpl.getById(flowId);
        if (flowVO == null) {
            return new CommonVO(false, "流程不存在【" + flowId + "】");
        }
        // 判断流程类型 1并行审批 0串行审批
        if ("1".equals(flowVO.getFlowType())) {
            // 开始节点不去审批
            if (!"1".equals(flowVO.getIsStart())) {
                // 查询是否已经审批过
                PzApproveQO approveQO = new PzApproveQO("", applyId, flowId, "0");
                List<PzApproveVO> vos = this.queryApprove(approveQO);
                if (vos == null || vos.size() <= 0) {
                    return new CommonVO(false, "该审批已经被审批过了");
                }
                CommonVO commonVO = this.updateApprove(applyId, flowId, pzApprove);
                if (!commonVO.getSuccess()) {
                    return commonVO;
                }
            }

            // 若不是流程结束节点则添加新的审批记录
            if (!"0".equals(flowVO.getNextId()) && flowVO.getNextId() != null) {
                nextFlowId = flowVO.getNextId();
                if (StringUtils.isNotBlank(userIds)) {
                    nextFlowId = this.addNextApprove(userIds, applyId, nextFlowId,
                        ApproveType.APPOINTED_IDS, userId);
                }
                else {
                    nextFlowId = this.addNextApprove(applyUserId, applyId, nextFlowId,
                        ApproveType.ROLE, userId);
                }
                if ("-1".equals(nextFlowId)) {
                    return new CommonVO(false, "申请人或审批人ID为空");
                }
                if (StringUtils.isBlank(nextFlowId) || "0".equals(nextFlowId)) {
                    return new CommonVO(true, "审批通过，剩余流程已无人员");
                }
            }
        }
        else if ("0".equals(flowVO.getFlowType())) {
            CommonVO commonVO = this.updateOneApprove(applyId, flowId, pzApprove);
            if (!commonVO.getSuccess()) {
                return commonVO;
            }
            List<User> us = this.getCurrApprovers(applyUserId, flowId);
            int len = us.size();
            PzApproveQO approveQO = new PzApproveQO("", applyId, flowId, "1");
            List<PzApproveVO> list = this.queryApprove(approveQO);
            // 如果全部通过
            if (len == list.size()) {
                // 若不是流程结束节点则添加新的审批记录
                if (!"0".equals(flowVO.getNextId()) && StringUtils.isNotBlank(flowVO.getNextId())) {
                    nextFlowId = flowVO.getNextId();
                    if (StringUtils.isNotBlank(userIds)) {
                        nextFlowId = this.addNextApprove(userIds, applyId, nextFlowId,
                            ApproveType.APPOINTED_IDS, userId);
                    }
                    else {
                        nextFlowId = this.addNextApprove(applyUserId, applyId, nextFlowId,
                            ApproveType.ROLE, userId);
                    }
                    if (StringUtils.isBlank(nextFlowId) || "0".equals(nextFlowId)) {
                        return new CommonVO(true, "审批通过，剩余流程已无人员");
                    }
                }
            }
        }
        if ("0".equals(nextFlowId) || StringUtils.isBlank(nextFlowId)) {
            return new CommonVO(false, "该流程已无后续流程！");
        }
        PzApply apply = new PzApply();
        apply.setPzApplyId(pzApplyVO.getPzApplyId());
        apply.setPzMainAccept(pzApplyVO.getPzMainAccept());
        // 审批通过
        apply.setFlowId(nextFlowId);
        apply.setUpdateBy(userId);
        // 更新申请记录表
        int count1 = pzApplyMapperImpl.updateFlow(apply);
        if (count1 <= 0) {
            return new CommonVO(false, "更新申请记录异常");
        }
        return new CommonVO(true, "审批成功！");
    }

    @Override
    public CommonVO refuse(PzApplyVO pzApplyVO, String userIds, PzApprove pzApprove) {
        String applyId = pzApplyVO.getPzApplyId();
        /** 获取流程并判断流程类型 做不同操作 **/
        String flowId = pzApplyVO.getFlowId();
        FlowVO flowVO = flowMapperImpl.getById(flowId);
        if (flowVO == null) {
            return new CommonVO(false, "流程不存在【" + flowId + "】");
        }
        if ("1".equals(flowVO.getFlowType())) {
            // 查询是否已经审批过
            PzApproveQO approveQO = new PzApproveQO("", applyId, flowId, "0");
            List<PzApproveVO> vos = this.queryApprove(approveQO);
            if (vos == null || vos.size() <= 0) {
                return new CommonVO(false, "该审批已经被审批过了");
            }
            // 开始节点不去审批
            if (!"1".equals(flowVO.getIsStart())) {
                CommonVO commonVO = this.updateApprove(applyId, flowId, pzApprove);
                if (!commonVO.getSuccess()) {
                    return commonVO;
                }
            }
            String rebutId = flowVO.getRebutId();
            if ("0".equals(rebutId) || StringUtils.isBlank(rebutId)) {
                return new CommonVO(false, "该流程已无返回流程节点！");
            }
            //返回退侦补充后添加approve记录
            if(SopConstants.FLOW_TZBC.equals(rebutId)){
                this.addNextApprove(userIds, applyId, rebutId,
                    ApproveType.APPOINTED_IDS, pzApprove.getUpdateBy());
            }
            // 当前设定只有这种方式才能直接更改申请记录状态
            PzApply apply = new PzApply();
            apply.setPzApplyId(pzApplyVO.getPzApplyId());
            // 审批不通过
            apply.setFlowId(rebutId);
            apply.setUpdateBy(pzApprove.getUpdateBy());
            // 更新申请记录表
            int count1 = pzApplyMapperImpl.updateFlow(apply);
            if (count1 <= 0) {
                return new CommonVO(false, "更新申请记录异常");
            }
        }
        else if ("0".equals(flowVO.getFlowType())) {
            // 这种方式只改变审批状态更新申请状态 需要确定的人去更改申请状态
            CommonVO commonVO = this.updateOneApprove(applyId, flowId, pzApprove);
            if (!commonVO.getSuccess()) {
                return commonVO;
            }
        }
        return new CommonVO(true, "审批成功！");
    }

    /**
     * 添加下一个流程的审批记录
     * 
     * @param param
     *            申请人ID 或者指定审批人IDS 通过ApproveType 判断
     * @param applyId
     *            配侦申请ID
     * @param nextId
     *            下一级流程ID
     * @return
     */
    private String addNextApprove(final String param, String applyId, String nextId,
        ApproveType type, String userId) {
        if (StringUtils.isBlank(param)) {
            return "-1";
        }
        FlowVO nextFlow = flowMapperImpl.getById(String.valueOf(nextId));
        // 指定审批人
        if (ApproveType.APPOINTED_IDS.equals(type)) {
            String[] userArr = param.split(",");
            for (String uId : userArr) {
                PzApprove approve = new PzApprove();
                if(StringUtils.isBlank(nextFlow.getNextId())||"0".equals(nextFlow.getNextId())){
                    approve.setResult("1");
                }
                approve.setApplyId(applyId);
                approve.setCreateBy(userId);
                approve.setUpdateBy(userId);
                approve.setUserId(Integer.valueOf(uId));
                approve.setFlowId(nextId);
                pzApproveMapperImpl.add(approve);
            }
            return nextId;
        }
        // 未指定审批人
        List<User> users = this.getCurrApprovers(param, String.valueOf(nextId));
        // 如果该流程无审批人
        if (users == null || users.size() == 0) {
            String repareUsers = nextFlow.getRepareUsers();
            String repareRoles = nextFlow.getRepareRoles();
            // 如果没有后补审批人和审批人角色则跳过
            if (StringUtils.isBlank(repareUsers) && StringUtils.isBlank(repareRoles)) {
                nextId = nextFlow.getNextId();
                // 如果还存在下一级流程
                if (nextId != null && "0".equals(nextId)) {
                    // 递归调用自身
                    return this.addNextApprove(param, applyId, nextId, ApproveType.ROLE, userId);
                }
                return nextId;
            }
            else if (StringUtils.isNotBlank(repareUsers)) {// 如果存在后补审批人
                String[] userArr = repareUsers.split(",");
                for (String uId : userArr) {
                    PzApprove approve = new PzApprove();
                    if(StringUtils.isBlank(nextFlow.getNextId())||"0".equals(nextFlow.getNextId())){
                        approve.setResult("1");
                    }
                    approve.setApplyId(applyId);
                    approve.setCreateBy(userId);
                    approve.setUpdateBy(userId);
                    approve.setUserId(Integer.valueOf(uId));
                    approve.setFlowId(nextId);
                    pzApproveMapperImpl.add(approve);
                }
            }
            else {// 存在后补审批角色
                String[] roleArr = repareRoles.split(",");
                List<User> lists = new ArrayList<User>();
                for (String rId : roleArr) {
                    List<User> list = this.getApproversByRole(param, Integer.valueOf(rId));
                    if (list != null && list.size() == 0) {
                        continue;
                    }
                    else {
                        PzApprove approve = new PzApprove();
                        approve.setApplyId(applyId);
                        approve.setCreateBy(userId);
                        approve.setUpdateBy(userId);
                        approve.setRoleId(Integer.valueOf(rId));
                        approve.setFlowId(nextId);
                        pzApproveMapperImpl.add(approve);
                        lists.addAll(list);
                    }
                }
                // 如果所有的备用审批角色都无人选则跳过
                if (lists.size() == 0) {
                    nextId = nextFlow.getNextId();
                    // 如果还存在下一级流程
                    if (nextId != null && "0".equals(nextId)) {
                        // 递归调用自身
                        return this
                            .addNextApprove(param, applyId, nextId, ApproveType.ROLE, userId);
                    }
                    return nextId;
                }
            }
        }
        else {
            if (ApproveType.ROLE.equals(type)) {
                PzApprove approve = new PzApprove();
                if(StringUtils.isBlank(nextFlow.getNextId())||"0".equals(nextFlow.getNextId())){
                    approve.setResult("1");
                }
                approve.setApplyId(applyId);
                approve.setCreateBy(userId);
                approve.setUpdateBy(userId);
                approve.setRoleId(Integer.valueOf(nextFlow.getStartRole()));
                approve.setFlowId(nextId);
                pzApproveMapperImpl.add(approve);
            }
            else {
                for (User user : users) {
                    PzApprove approve = new PzApprove();
                    if(StringUtils.isBlank(nextFlow.getNextId())||"0".equals(nextFlow.getNextId())){
                        approve.setResult("1");
                    }
                    approve.setApplyId(applyId);
                    approve.setCreateBy(userId);
                    approve.setUpdateBy(userId);
                    approve.setUserId(user.getUserId().intValue());
                    approve.setFlowId(nextId);
                    pzApproveMapperImpl.add(approve);
                }
            }
        }
        return nextId;
    }

    /**
     * 查询并审批该流程所有记录
     * 
     * @param userId
     *            审批人ID
     * @param applyId
     *            配侦申请ID
     * @param pzApprove
     *            审批信息
     * @return
     */
    private CommonVO updateApprove(String applyId, String flowId, PzApprove pzApprove) {
        PzApproveQO qo = new PzApproveQO("", applyId, flowId, "0");
        List<PzApproveVO> vos = this.queryApprove(qo);
        int count = 0;
        // 更新所有的该流程的审批记录
        if (vos != null && vos.size() > 0) {
            for (PzApproveVO vo : vos) {
                pzApprove.setPzApproveId(vo.getPzApproveId());
                // 更新审批记录表
                count += pzApproveMapperImpl.updateApprove(pzApprove);
            }
            if (count <= 0) {
                return new CommonVO(false, "更新审批记录异常");
            }
        }
        else {
            return new CommonVO(false, "未查询到该审批记录");
        }
        return new CommonVO(true);
    }

    /**
     * 查询并审批记录(只审批自己的记录)
     * 
     * @param applyId
     *            配侦申请ID
     * @param pzApprove
     *            审批信息
     * @return
     */
    private CommonVO updateOneApprove(String applyId, String flowId, PzApprove pzApprove) {
        PzApproveQO qo = new PzApproveQO(pzApprove.getCreateBy(), applyId, flowId, "0");
        List<PzApproveVO> vos = this.queryApprove(qo);
        PzApproveVO vo = null;
        // 更新所有的该流程的审批记录
        if (vos != null && vos.size() > 0) {
            vo = vos.get(0);
            pzApprove.setPzApproveId(vo.getPzApproveId());
            // 更新审批记录表
            int count = pzApproveMapperImpl.updateApprove(pzApprove);
            if (count <= 0) {
                return new CommonVO(false, "更新审批记录异常");
            }
        }
        else {
            return new CommonVO(false, "未查询到该审批记录");
        }
        return new CommonVO(true);
    }

    /**
     * 查询并审批记录(只审批自己角色的记录)暂不使用
     * 
     * @param applyId
     *            配侦申请ID
     * @param pzApprove
     *            审批信息
     * @return
     */
    @SuppressWarnings("unused")
    @Deprecated
    private CommonVO updateOneGroupApprove(String applyId, String flowId, PzApprove pzApprove) {
        List<Role> roles = userMapperImpl.getRolesById(pzApprove.getUpdateBy());
        String roleId = "";
        if (roles != null && roles.size() > 0) {
            roleId = String.valueOf(roles.get(0).getRoleId().intValue());
        }
        else {
            return new CommonVO(false, "当前用户无角色");
        }
        PzApproveQO qo = new PzApproveQO("", applyId, flowId, "0");
        List<PzApproveVO> vos = this.queryApprove(qo);
        // 更新所有的该流程的审批记录
        if (vos != null && vos.size() > 0) {
            int count = 0;
            for (PzApproveVO vo : vos) {
                pzApprove.setPzApproveId(vo.getPzApproveId());
                // 更新审批记录表
                count += pzApproveMapperImpl.updateApprove(pzApprove);
            }
            if (count <= 0) {
                return new CommonVO(false, "更新审批记录异常");
            }
        }
        else {
            return new CommonVO(false, "未查询到该审批记录");
        }
        return new CommonVO(true);
    }

    @Override
    public List<User> getCurrApprovers(String userId, String flowId) {
        FlowVO flowVO = flowMapperImpl.getById(flowId);
        Integer startRole = Integer.valueOf(flowVO.getStartRole());
        return this.getApproversByRole(userId, startRole);
    }

    @Override
    public List<User> getNextApprovers(String userId, String flowId) {
        FlowVO flowVO = flowMapperImpl.getById(flowId);
        String nextId = String.valueOf(flowVO.getNextId());
        return this.getCurrApprovers(userId, nextId);
    }

    @Override
    public List<User> getApproversByRole(String userId, Integer roleId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", userId);
        param.put("roleId", roleId);
        return flowMapperImpl.getApprovers(param);
    }

    @Override
    public List<PzApproveVO> queryApprove(PzApproveQO qo) {
        return pzApproveMapperImpl.queryApprove(qo);
    }

    @Override
    public CommonVO updateApprove(PzApprove pzApprove) {
        int count = pzApproveMapperImpl.updateApprove(pzApprove);
        if (count <= 0) {
            return new CommonVO(false, "更新审批记录失败");
        }
        else {
            return new CommonVO(true, "更新审批记录成功");
        }
    }

    @SuppressWarnings({})
    @Override
    public DataGridVO<PzApproveVO> getSumlist(PzApproveQO qo, String page, String rows) {
        DataGridVO<PzApproveVO> result = new DataGridVO<PzApproveVO>();
        try {
            List<PzApproveVO> list = pzApproveMapperImpl.getSumlist(qo, page, rows);
            for (int i = 0; i < list.size(); i++) {
            	Map<String, String> map = indexMapper.selectById(String.valueOf(list.get(i).getUserId()));
            	list.get(i).setUserNo(map.get("USER_NO"));
			}
            result.setRows(list);
            result.setTotal(list.size());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public CommonVO next(String userIds, PzApprove pzApprove) {
        pzApprove.setResult("1");
        return this.approve(userIds, pzApprove);
    }

    @Override
    public CommonVO next(PzApprove pzApprove) {
        return this.next("", pzApprove);
    }

    @Override
    public CommonVO approve(PzApprove pzApprove, boolean hasPower) {
        return this.approve("", pzApprove, hasPower);
    }

    @Override
    public CommonVO approve(PzApprove pzApprove) {
        return this.approve("", pzApprove);
    }

    @Override
    public CommonVO approve(String[] pzApplyIds, PzApprove pzApprove, boolean hasPower) {
        return this.approve(pzApplyIds, "", pzApprove, hasPower);
    }

    @Override
    public CommonVO approve(String[] pzApplyIds, PzApprove pzApprove) {
        return this.approve(pzApplyIds, "", pzApprove);
    }

    @Override
    public CommonVO agree(PzApplyVO pzApplyVO, PzApprove pzApprove) {
        return this.agree(pzApplyVO, "", pzApprove);
    }

    @Override
    public CommonVO refuse(PzApplyVO pzApplyVO, PzApprove pzApprove) {
        return this.refuse(pzApplyVO, "", pzApprove);
    }

    @Override
    public boolean checkReceives(String[] ids,String userId) {
        Map<String,Object> param = new HashMap<String,Object>();
        for(String id : ids){
            param.put("pzid", id);
            List<HashMap<String,Object>> userlist = pzApplyMapperImpl.getAssignees(param);
            boolean flag = false;
            for(HashMap<String,Object> user : userlist){
                String uid = user.get("USER_ID")==null?"":user.get("USER_ID").toString();
                if(uid.equals(userId)){
                    flag = true;
                    break;
                }
            }
            if(flag){
                continue;
            }
            else{
                return false;
            }
        }
        return true;
    }

}
