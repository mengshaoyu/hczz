package com.shield.hczz.base.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.shield.frame.base.domain.User;
import com.shield.hczz.common.SopConstants;

public class FlowController extends SopBaseController {
    
    @Autowired
    protected ProcessEngine processEngine;
    
    @Autowired
    protected TaskService taskService;
    
    @Autowired
    protected HistoryService historyService;
    
    @Autowired
    protected RuntimeService runtimeService;
    
    @Autowired
    protected RepositoryService repositoryService;
    
    /**
     * 开始流程
     * @return
     */
    public Map<String, Object> startFlow(String ACT_PROCCESS_DEFINITION){
        Map<String, Object> pimap = new HashMap<>();
        try {
            User usr = (User) request.getSession().getAttribute("loginUser");
            List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().processDefinitionId(ACT_PROCCESS_DEFINITION).list();
            if(list.size() == 0){
                return getRet(-1, "ACT_PROCCESS_DEFINITION值错误！", null);
            }
            ProcessDefinition pd = list.get(0);
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("user", usr.getUserId().toString());
            ProcessInstance pi = processEngine.getRuntimeService()
                .startProcessInstanceById(pd.getId(), userMap);
            pimap.put("pi", pi);
            return getRet(0, "", pimap);
        }
        catch (Exception e) {
            e.printStackTrace();
            return getRet(-1, "环节流转出错："+e.getMessage(), null);
        }
    }
    
    /**
     * 一般流程处理、会签（多人确认后才会进入下一环节）<br>
     * 注意：审批流程请调用 approveTaskComplate <br>
     * 待申请-->待分管领导审批<br>
     * 待签收-->待值班长审批<br>
     * vars变量定义：<br>
     * 1、下一流程责任人 user<br>
     * 2、下一流程志愿者 users（用“,”隔开）<br>
     * 3、下一流程角色 role<br>
     * 4、流程流转方向，如值班长审批同意-->待分发，不同意-->退帧补充<br>
     * @param procInstId 流程实例id
     * @param vars 流程变量
     * @param isMultiSign 下一流程是否会签流程
     */
    public Map<String, Object> simpleTaskComplate(String procInstId, Map<String, Object> vars, boolean isMultiSign){
        try {
            if(isMultiSign){//会签流程（带反馈）
                User usr = (User) request.getSession().getAttribute("loginUser");
                List<Task> tasks = taskService.createTaskQuery().processInstanceId(procInstId).taskAssignee(usr.getUserId().toString()).list();
                if(tasks.size() > 0){
                	taskService.complete(tasks.get(0).getId(), vars);
                }
            }else{//普通流程
                List<Task> tasks = taskService.createTaskQuery().processInstanceId(procInstId).list();
                if(tasks.size() == 0){
                    return getRet(-1, "未查询到编号为："+procInstId+"的流程实例", null);
                }
                Task task = tasks.get(0);
                if(StringUtils.isEmpty(task.getAssignee())){
                	claim(task.getId());
                }
                if(SopConstants.FLOW_DFP.equals(task.getTaskDefinitionKey())){
                    String users = vars.get("users").toString();
                    //当待分配-->待反馈时，传递的变量users必须是List
                    vars.put("users", Arrays.asList(users.split(",")));
                }
                taskService.complete(task.getId(), vars);
            }
            return getRet(0, "", null);
        }
        catch (Exception e) {
            e.printStackTrace();
            return getRet(-1, "环节流转出错："+e.getMessage(), null);
        }
    }
    
    /**
     * M3_2001
     * 审批流程处理<br>
     * 待分管领导审批-->待签收/退帧补充<br>
     * @param procInstId 流程实例id
     * @param vars 流程变量 
     * @param comment 审批意见
     * @param isAgree 是否同意
     */
    public Map<String, Object> approveTaskComplate(String procInstId, Map<String, Object> vars, String comment, boolean isAgree){
        try {
            Task task = taskService.createTaskQuery().processInstanceId(procInstId).singleResult();
            claim(task.getId());
            taskService.addComment(task.getId(), procInstId, comment);
            if(isAgree){
                vars.put("sp", "1");
            }else{
                List<HistoricActivityInstance> hais = historyService.createHistoricActivityInstanceQuery()
                                    .processInstanceId(procInstId).activityId(SopConstants.FLOW_SQPZ).list();
                if(hais.size() == 0){
                    return getRet(-1, "未查询到历史提报环节！", null);
                }
                HistoricActivityInstance hai = hais.get(0);
                vars.put("sp", "2");
                vars.put("user", hai.getAssignee());
            }
            taskService.complete(task.getId(), vars);
            return getRet(0, "", null);
        }
        catch (Exception e) {
            e.printStackTrace();
            return getRet(-1, "审批环节出错："+e.getMessage(), null);
        }
    }
    
    /**
     * 流程环节跳转<br>
     * 如：取回
     * @param procInstId 流程实例id
     * @param vars 流程变量 
     * @param jumpActId 跳转到的活动id
     * @return
     */
    public Map<String, Object> jump(String procInstId, Map<String, Object> vars, String jumpActId){
        try {
            List<Task> tasks = taskService.createTaskQuery().processInstanceId(procInstId).list();
            if(tasks.size() == 0){
                return getRet(-1, "未查询到编号为："+procInstId+"的流程实例", null);
            }
            String taskId = tasks.get(0).getId();
            JumpService ts = new JumpService(processEngine);
            ts.jump(taskId, jumpActId, vars);
            return getRet(0, "", null);
        }
        catch (Exception e) {
            e.printStackTrace();
            return getRet(-1, "流程跳转出错："+e.getMessage(), null);
        }
    }
    
    /**
     * 认领任务，如一个环节分配给了多个人，
     * 这种环节需要有人认领<br>
     * (不推荐外部调用)
     * @param taskId 当前环节实例id
     */
    public void claim(String taskId){
        User usr = (User) request.getSession().getAttribute("loginUser");
        taskService.claim(taskId, usr.getUserId().toString());
    }
    
    /**
     * 获取流程图
     * @param resp
     * @throws UnsupportedEncodingException
     */
    public void viewImage(HttpServletResponse resp) throws UnsupportedEncodingException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("multipart/form-data");
        resp.setHeader("Content-Disposition",
            "attachment;fileName=" + URLEncoder.encode("123.png", "UTF-8"));
        InputStream  in = processEngine.getRepositoryService().getResourceAsStream("1", "process/hczz_v1.png");//此处方法实际项目应该放在service里面
        try {
            OutputStream out = resp.getOutputStream();
            // 把图片的输入流程写入resp的输出流中
            byte[] b = new byte[1024];
            for (int len = -1; (len= in.read(b))!=-1; ) {
                out.write(b, 0, len);
            }
            // 关闭流
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 获取当前环节的绝对定位
     * @param taskId
     * @return
     */
    public Map<String, Object> getPosition(String taskId){
        Task task1 = processEngine.getTaskService().createTaskQuery().taskId(taskId).singleResult();
        // 2. 通过task对象的pdid获取流程定义对象
        ProcessDefinition pd = processEngine.getRepositoryService().getProcessDefinition(task1.getProcessDefinitionId());
        
        Map<String, Object> coordinates = new HashMap<String, Object>();
        // 1. 获取到当前活动的ID
        Task task = processEngine.getTaskService().createTaskQuery().taskId(taskId).singleResult();
        ProcessInstance pi = processEngine.getRuntimeService().createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
        String currentActivitiId = pi.getActivityId();
        // 2. 获取到流程定义
        ProcessDefinitionEntity pde = (ProcessDefinitionEntity) processEngine.getRepositoryService().getProcessDefinition(task.getProcessDefinitionId());
        // 3. 使用流程定义通过currentActivitiId得到活动对象
        ActivityImpl activity =  pde.findActivity(currentActivitiId);
        // 4. 获取活动的坐标
        coordinates.put("x", activity.getX());
        coordinates.put("y", activity.getY());
        coordinates.put("width", activity.getWidth());
        coordinates.put("height", activity.getHeight());
        return coordinates;
    }
}
