package com.shield.frame.utils;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import com.shield.frame.base.domain.User;
import com.shield.frame.sysmng.dto.AuditLogDTO;
import com.shield.frame.sysmng.service.CommonService;
import com.shield.hczz.base.domain.PzTaskLog;

/*
  * *系统日志工具类* 
  * 
  **/
public class AuditLogUtil {

    /**
     * <b>功能：添加系统日志</b><br>
     * <br>
     * @param request 本次请求
     * @param function 操作功能模块名
     * @param operType 操作类型(1-查询,2-查看,3-新增,4-编辑,5-删除,6-导出,9-其他)
     * @param content 操作内容
     * @param resourceid 操作资源对象id
     * @param result 操作结果
     * @return int
     **/
    public static int addLog(HttpServletRequest request, String function, String operType,
        String content, String resourceid, String result) {
        //获取当前登录用户
        User user = (User) request.getSession().getAttribute("loginUser");
        //实例化日志对象
        AuditLogDTO ald = new AuditLogDTO();
        //赋值
        ald.setUuid(CommonUtil.getUUID());
        ald.setUserId(user.getUserId());
        ald.setUserIp(CommonUtil.getRemoteIpAddr(request));
        ald.setFunction(function);
        ald.setOperType(operType);
        ald.setOperContent(content);
        ald.setResourceId(resourceid);
        ald.setResult(result);
        //获取日志处理对象
        CommonService commonService = (CommonService) SpringConfigLoadHelper
            .getBean(CommonService.class);
        //返回处理结果
        return commonService.addAuditLog(ald);
    }

    /**
     * <b>功能：添加工作日志</b><br>
     * <br>
     * @param request 本次请求
     * @param 	clueNoRef 关联线索编号
     * @param	caseNoRef	关联案件编号
     * @param	pzApplyId	关联配侦申请
     * @param	flowId	流程ID
     * @param	taskStatus	任务状态
     * @param	nextBy	下一级流转人
     * @param	TaskDesc	简要描述
     * @return int
     **/
    public static int addWorkLog(HttpServletRequest request, String clueNoRef, String caseNoRef,
        String pzApplyId, String flowId, String taskStatus, String nextBy, String taskDesc) {
        //获取当前登录用户
        User user = (User) request.getSession().getAttribute("loginUser");
        //实例化日志对象
        PzTaskLog taskLog = new PzTaskLog();
        //赋值
        taskLog.setClueNoRef(clueNoRef);
        taskLog.setCaseNoRef(caseNoRef);
        taskLog.setPzApplyId(pzApplyId);
        taskLog.setFlowId(flowId);
        taskLog.setTaskStatus(taskStatus);
        taskLog.setNextBy(nextBy);
        taskLog.setTaskDesc(taskDesc);
        taskLog.setCreateBy(user.getUserId().toString());
        taskLog.setUpdateBy(user.getUserId().toString());
        //获取日志处理对象
        CommonService commonService = (CommonService) SpringConfigLoadHelper
            .getBean(CommonService.class);
        //返回处理结果
        return commonService.addTaskLog(taskLog);
    }

}
