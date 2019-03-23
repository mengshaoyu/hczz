package com.shield.hczz.base.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.StringUtils;

public class JumpService {
    ProcessEngine processEngine;

    public JumpService(ProcessEngine processEngine) {
        this.processEngine = processEngine;
    }

    /** 
     * 跳转至指定活动节点 
     *  
     * @param targetTaskDefinitionKey 
     * @param var 
     * @throws Exception 
     */
    public void jump(String taskId, String targetTaskDefinitionKey, Map<String, Object> var)
        throws Exception {
        // 当前节点  
        ActivityImpl currActivity = getActiviti(taskId, null);
        // 清空当前流向  
        List<PvmTransition> oriPvmTransitionList = clearTransition(currActivity);

        // 创建新流向  
        TransitionImpl newTransition = currActivity.createOutgoingTransition();
        // 目标节点  
        ActivityImpl pointActivity = getActiviti(taskId, targetTaskDefinitionKey);
        // 设置新流向的目标节点  
        newTransition.setDestination(pointActivity);

        // 执行转向任务  
        processEngine.getTaskService().complete(taskId, var);
        // 删除目标节点新流入  
        pointActivity.getIncomingTransitions().remove(newTransition);

        // 还原以前流向  
        restoreTransition(currActivity, oriPvmTransitionList);
    }

    private ActivityImpl getActiviti(String taskId, String activityId) {
        Task task = processEngine.getTaskService().createTaskQuery().taskId(taskId).singleResult();
        ProcessInstance pi = processEngine.getRuntimeService().createProcessInstanceQuery()
            .processInstanceId(task.getProcessInstanceId()).singleResult();
        String currentActivitiId = pi.getActivityId();
        // 2. 获取到流程定义
        ProcessDefinitionEntity pde = (ProcessDefinitionEntity) processEngine
            .getRepositoryService().getProcessDefinition(task.getProcessDefinitionId());
        // 3. 使用流程定义通过currentActivitiId得到活动对象
        ActivityImpl activity = pde
            .findActivity(StringUtils.isEmpty(activityId) ? currentActivitiId : activityId);
        return activity;
    }

    private List<PvmTransition> clearTransition(ActivityImpl activityImpl) {
        // 存储当前节点所有流向临时变量  
        List<PvmTransition> oriPvmTransitionList = new ArrayList<PvmTransition>();
        // 获取当前节点所有流向，存储到临时变量，然后清空  
        List<PvmTransition> pvmTransitionList = activityImpl.getOutgoingTransitions();
        for (PvmTransition pvmTransition : pvmTransitionList) {
            oriPvmTransitionList.add(pvmTransition);
        }
        pvmTransitionList.clear();

        return oriPvmTransitionList;
    }

    private void restoreTransition(ActivityImpl activityImpl,
        List<PvmTransition> oriPvmTransitionList) {
        // 清空现有流向  
        List<PvmTransition> pvmTransitionList = activityImpl.getOutgoingTransitions();
        pvmTransitionList.clear();
        // 还原以前流向  
        for (PvmTransition pvmTransition : oriPvmTransitionList) {
            pvmTransitionList.add(pvmTransition);
        }
    }
}
