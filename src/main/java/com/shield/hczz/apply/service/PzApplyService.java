package com.shield.hczz.apply.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.shield.frame.base.domain.User;
import com.shield.frame.common.qvo.CommonVO;
import com.shield.frame.common.qvo.DataGridVO;
import com.shield.frame.common.qvo.ResultVO;
import com.shield.frame.sysmng.qvo.ToolBarVO;
import com.shield.hczz.apply.qvo.PzApplyQO;
import com.shield.hczz.apply.qvo.PzApplyVO;
import com.shield.hczz.base.domain.PzApply;

public interface PzApplyService {

    ResultVO getlist(PzApplyQO qo, String page, String rows,User user);

    CommonVO add(HttpServletRequest request, PzApply pzApply);

    int getCount(Map<String, Object> map);

    int updateDeleted(String ids, String userId) throws Exception;

    CommonVO updateStatus(HttpServletRequest request, PzApply pzApply);

    PzApplyVO getInfoById(String pzid);

    PzApplyVO getFlowTime(String pzid, String string, String string2, String backTime);

    DataGridVO<Map<String, Object>> getToDoCount(String userId, String status);

    DataGridVO<Map<String, Object>> getToDoCount(String userId);

    int saveEvaluate(PzApply pzApply);

    ResultVO read(String pzApplyId, String userId);
    
    ResultVO unread(String pzApplyId);

	String getFlowNameById(String pzid);

	/**
	 * 获取指定界面的按钮信息
	 * @param apply	当前配侦申请实体
	 * @param key	流程实例ID
	 * @param user	当前用户主键
	 * @param menuId	菜单主键
	 * @param toolbar	按钮类型标识：1，toolbar；2，任务管理操作；3，任务详情按钮
	 * @return
	 */
	List<ToolBarVO> getMenuButton(PzApplyVO apply, String key,User user,String menuId,String toolbar,boolean isCurrent);

	/**
	 * 获取当前配侦申请对应的有效反馈时长
	 * @param pzApplyId	配侦主键
	 * @return
	 */
	String getBackTimeLength(String pzApplyId);

	PzApplyVO getApplyMsgById(String pzid);
	
	/**
	 * 获取当前流程的签收人的集合
	 * @param pzid 配侦id
	 * @return 当前流程用户id集合
	 */
	List<HashMap<String,Object>> getAssignees(String pzid);
}
