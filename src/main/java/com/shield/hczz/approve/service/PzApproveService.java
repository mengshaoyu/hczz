package com.shield.hczz.approve.service;

import java.util.List;

import com.shield.frame.base.domain.User;
import com.shield.frame.common.qvo.CommonVO;
import com.shield.frame.common.qvo.DataGridVO;
import com.shield.hczz.apply.qvo.PzApplyVO;
import com.shield.hczz.approve.qvo.PzApproveQO;
import com.shield.hczz.approve.qvo.PzApproveVO;
import com.shield.hczz.base.domain.PzApprove;

public interface PzApproveService {

    /**
     * 添加一条审批记录
     * 
     * @param pzApprove
     *            审批记录
     * @return
     */
    CommonVO add(PzApprove pzApprove);

    /**
     * 审批
     * 
     * @param userIds
     *            指定下级流向的用户id
     * @param pzApprove
     *            流程信息
     * @return
     */
    CommonVO next(String userIds, PzApprove pzApprove);

    /**
     * @param pzApprove
     *            流程信息
     * @return
     */
    CommonVO next(PzApprove pzApprove);

    /**
     * 审批
     * 
     * @param pzApprove
     *            审批信息
     * @param hasPower
     *            是否有权限跳转
     * @return
     */
    CommonVO approve(PzApprove pzApprove, boolean hasPower);

    CommonVO approve(String userIds, PzApprove pzApprove, boolean hasPower);

    /**
     * 审批
     * 
     * @param pzApprove
     *            审批信息
     * @return
     */
    CommonVO approve(PzApprove pzApprove);

    /**
     * 审批
     * 
     * @param userIds
     *            指定审批的用户id
     * @param pzApprove
     *            审批信息
     * @return
     */
    CommonVO approve(String userIds, PzApprove pzApprove);

    /**
     * 批量审批
     * 
     * @param pzApplyIds
     *            配侦申请ID数组
     * @param pzApprove
     *            审批信息
     * @param hasPower
     *            是否有权限跳转
     * @return
     */
    CommonVO approve(String[] pzApplyIds, PzApprove pzApprove, boolean hasPower);

    /**
     * 批量审批
     * 
     * @param pzApplyIds
     *            配侦申请ID数组
     * @param userIds
     *            指定审批的用户id
     * @param pzApprove
     *            审批信息
     * @param hasPower
     *            是否有权限跳转
     * @return
     */
    CommonVO approve(String[] pzApplyIds, String userIds, PzApprove pzApprove, boolean hasPower);

    /**
     * 批量审批
     * 
     * @param pzApplyIds
     *            配侦申请ID数组
     * @param pzApprove
     *            审批信息
     */
    CommonVO approve(String[] pzApplyIds, PzApprove pzApprove);

    /**
     * @param pzApplyIds
     *            配侦申请ID数组
     * @param userIds
     *            指定审批的用户id
     * @param pzApprove
     *            审批信息
     * @return
     */
    CommonVO approve(String[] pzApplyIds, String userIds, PzApprove pzApprove);

    /**
     * 审批通过
     * 
     * @param pzApplyVO
     *            申请信息
     * @param pzApprove
     *            审批信息
     * @return
     */
    CommonVO agree(PzApplyVO pzApplyVO, PzApprove pzApprove);

    /**
     * 审批通过
     * 
     * @param pzApplyVO
     *            申请信息
     * @param userIds
     *            指定审批的用户id
     * @param pzApprove
     *            审批信息
     * @return
     */
    CommonVO agree(PzApplyVO pzApplyVO, String userIds, PzApprove pzApprove);

    /**
     * 审批不通过
     * 
     * @param pzApplyVO
     *            申请信息
     * @param pzApprove
     *            审批信息
     * @return
     */
    CommonVO refuse(PzApplyVO pzApplyVO, PzApprove pzApprove);

    /**
     * @param pzApplyVO
     *            申请信息
     * @param userIds
     *            指定审批的用户id
     * @param pzApprove
     *            审批信息
     * @return
     */
    CommonVO refuse(PzApplyVO pzApplyVO, String userIds, PzApprove pzApprove);

    /**
     * 获取当前流程的审批人列表
     * 
     * @param applyUserId
     *            申请人ID
     * @param flowId
     *            当前流程ID
     * @return
     */
    List<User> getCurrApprovers(String applyUserId, String flowId);

    /**
     * 获取下一级流程的审批人列表
     * 
     * @param applyUserId
     *            申请人ID
     * @param flowId
     *            当前流程ID
     * @return
     */
    List<User> getNextApprovers(String applyUserId, String flowId);

    /**
     * 根据申请人和审批角色获取审批人
     * 
     * @param applyUserId
     *            申请人ID
     * @param roleId
     *            审批角色ID
     * @return
     */
    List<User> getApproversByRole(String applyUserId, Integer roleId);

    /**
     * 查询审批记录
     * 
     * @param qo
     *            查询类
     *            {pzApproveId：审批记录id,userId:'当前审批人ID',result:'审批结果（多个用‘，’隔开）,
     *            flowId:'流程ID'}
     * @return
     */
    List<PzApproveVO> queryApprove(PzApproveQO qo);

    /**
     * 更新审批记录
     * 
     * @param pzApprove
     *            审批记录{pzApproveId:id,result:'审批结果',reason:'审批意见',updateBy:'更新人'
     *            }
     * @return
     */
    CommonVO updateApprove(PzApprove pzApprove);

    DataGridVO<PzApproveVO> getSumlist(PzApproveQO qo, String page, String rows);
    
    boolean checkReceives(String[] ids,String userId);
}
