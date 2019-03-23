package com.shield.hczz.act;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shield.hczz.base.controller.JumpService;
import com.shield.hczz.common.SopConstants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:springMVC-servlet.xml",
    "classpath:mybatisConfig.xml", "classpath:appContext.xml" })
public class ActivitiTest {
    
    @Autowired
    private ProcessEngine processEngine;
    
    @Test
    public void createFlow() {
        Deployment deployment = processEngine.getRepositoryService() //用于流程定义和部署相关对象的Service  
            .createDeployment() //创建一个部署对象
            .name("合成作战V1").addClasspathResource("process/hczz_v1.bpmn") //从ClassPath资源中加载，一次只能加载一个文件  
            .addClasspathResource("process/hczz_v1.png") //从ClassPath资源中加载，一次只能加载一个文件  
            .deploy();

        System.out.println("deployment" + deployment.getId()); //1  
        System.out.println("deployment" + deployment.getName());//部门程序  
    }
    
    @Test
    public void startFlow() {
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("user", "1");
        ProcessInstance pi = processEngine.getRuntimeService()
            .startProcessInstanceById("hczz_v1:1:4",userMap);//使用流程定义的key的最新版本启动流程  
        System.out.println("流程实例ID：" + pi.getId());
        System.out.println("流程定义的ID：" + pi.getProcessDefinitionId());
    }
    /**
     * 提报
     */
    @Test
    public void tb(){
        //流程id
        String procInstId = "197501";
        //下一环节变量
        Map<String, Object> vars = new HashMap<>();
        vars.put("users", "2");
        //是否会签（待反馈）环节
        boolean isMultiSign = false;
        //当前环节处理人id
        String userId = "1";
        simpleTaskComplate(procInstId, vars, isMultiSign, userId);
    }
    
    /**
     * 分管领导审批、值班长审批
     */
    @Test
    public void sp(){
        //流程id
        String procInstId = "197501";
        //审批意见
        String comment = "同意";
        //是否同意
        boolean isAgree = true;
        //当前环节处理人id
        String userId = "13621";
        //变量
        Map<String, Object> vars = new HashMap<>();
        vars.put("role", SopConstants.ROLE_ZBZ);
        approveTaskComplate(procInstId, vars, comment, isAgree, userId);
    }
    /**
     * 值班长签收
     */
    @Test
    public void qs(){
        //流程id
        String procInstId = "197501";
        //下一环节变量
        Map<String, Object> vars = new HashMap<>();
        vars.put("role", SopConstants.ROLE_ZBZ);
        //是否会签（待反馈）环节
        boolean isMultiSign = false;
        //当前环节处理人id
        String userId = "13621";
        simpleTaskComplate(procInstId, vars, isMultiSign, userId);
    }
    /**
     * 分发
     */
    @Test
    public void ff(){
        //流程id
        String procInstId = "197501";
        //下一环节变量(分发给多个人用逗号隔开)
        Map<String, Object> vars = new HashMap<>();
        vars.put("users", "1,2");
        //是否会签（待反馈）环节
        boolean isMultiSign = false;
        //当前环节处理人id
        String userId = "13621";
        simpleTaskComplate(procInstId, vars, isMultiSign, userId);
    }
    /**
     * 反馈
     */
    @Test
    public void fk(){
        //流程id
        String procInstId = "197501";
        //下一环节变量(合成民警id)
        Map<String, Object> vars = new HashMap<>();
        vars.put("user", "1");
        //是否会签（待反馈）环节
        boolean isMultiSign = true;
        //当前环节处理人id
        String userId = "1";
        simpleTaskComplate(procInstId, vars, isMultiSign, userId);
    }
    /**
     * 生成反馈报告
     */
    @Test
    public void scfkbg(){
        //流程id
        String procInstId = "197501";
        //下一环节变量(主办人id)
        Map<String, Object> vars = new HashMap<>();
        vars.put("user", "1");
        //是否会签（待反馈）环节
        boolean isMultiSign = false;
        //当前环节处理人id
        String userId = "1";
        simpleTaskComplate(procInstId, vars, isMultiSign, userId);
    }
    /**
     * 核实
     */
    @Test
    public void hs(){
        //流程id
        String procInstId = "197501";
        //下一环节变量(提报人id)
        Map<String, Object> vars = new HashMap<>();
        vars.put("user", "1");
        //是否会签（待反馈）环节
        boolean isMultiSign = false;
        //当前环节处理人id
        String userId = "1";
        simpleTaskComplate(procInstId, vars, isMultiSign, userId);
    }
    /**
     * 审批流程
     */
    public void approveTaskComplate(String procInstId, Map<String, Object> vars, String comment, boolean isAgree, String userId){
        TaskService taskService = processEngine.getTaskService();
        HistoryService historyService = processEngine.getHistoryService();
        Task task = taskService.createTaskQuery().processInstanceId(procInstId).singleResult();
        taskService.claim(task.getId(), userId);
        taskService.addComment(task.getId(), procInstId, comment);
        if(isAgree){
            vars.put("sp", "1");
        }else{
            List<HistoricActivityInstance> hais = historyService.createHistoricActivityInstanceQuery()
                                .processInstanceId(procInstId).activityId(SopConstants.FLOW_SQPZ).list();
            if(hais.size() == 0){
                System.out.println("error");
            }
            HistoricActivityInstance hai = hais.get(0);
            vars.put("sp", "2");
            vars.put("user", hai.getAssignee());
        }
        taskService.complete(task.getId(), vars);
    }
    
    /**
     * 一般流程
     */
    public void simpleTaskComplate(String procInstId, Map<String, Object> vars, boolean isMultiSign, String userId){
        TaskService taskService = processEngine.getTaskService();
        if(isMultiSign){//会签流程（带反馈）
            List<Task> tasks = taskService.createTaskQuery().processInstanceId(procInstId).taskAssignee(userId).list();
            for(Task t : tasks){
                //循环的目的是为了处理多个线索分给一个合成中心民警
                taskService.complete(t.getId(), vars);
            }
        }else{//普通流程
            List<Task> tasks = taskService.createTaskQuery().processInstanceId(procInstId).list();
            if(tasks.size() == 0){
                System.out.println("error");
            }
            Task task = tasks.get(0);
            if(StringUtils.isEmpty(task.getAssignee())){
                taskService.claim(task.getId(), userId);
            }
            
            if(SopConstants.FLOW_DFP.equals(task.getTaskDefinitionKey())){
                String users = vars.get("users").toString();
                //当待分配-->待反馈时，传递的变量users必须是List
                vars.put("users", Arrays.asList(users.split(",")));
            }
            taskService.complete(task.getId(), vars);
        }
    }
    
    
    /**
     * 会签
     */
    @Test
    public void multiSign(){
        String procId = "45001";
        List<Task> list = processEngine.getTaskService().createTaskQuery().processInstanceId(procId).list();
        String taskId = list.get(0).getId();
        Map<String, Object> userMap = new HashMap<>();
        String[] users = {"101", "102"};
        userMap.put("users", Arrays.asList(users));
        processEngine.getTaskService().complete(taskId, userMap);
    }
    
    @Test
    public void complate(){
        String procId = "45001";
        String taskId="70016";
        List<HistoricActivityInstance> listHis = processEngine.getHistoryService()
            .createHistoricActivityInstanceQuery().processInstanceId(procId).activityId("HCZZ_1001").list();
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("user", listHis.get(0).getAssignee());
        processEngine.getTaskService().complete(taskId, userMap);
    }
    
    @Test
    public void testJump() throws Exception{
        String procId = "45001";
        List<Task> list = processEngine.getTaskService().createTaskQuery().processInstanceId(procId).list();
        String taskId = list.get(0).getId();
        JumpService ts = new JumpService(processEngine);
        Map<String, Object> var = new HashMap<>();
        var.put("role", "1002");
        ts.jump(taskId, "HCZZ_1005", var);
    }
    
    public static void main(String[] args) {
        String[] cluelist = {"1","2","1"};
        Set<String> set = new HashSet<>();
        for(String c : cluelist){
            set.add(c);
        }
        String user = "";
        for(String s : set){
            user += s+",";
        }
        user = user.substring(0, user.length()-1);
        System.out.println(user);
    }
    
}
