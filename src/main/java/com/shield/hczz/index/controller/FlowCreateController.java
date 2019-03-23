package com.shield.hczz.index.controller;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FlowCreateController {
    @Autowired
    protected ProcessEngine processEngine;
    /**
     * 创建流程
     * @param request
     */
    @RequestMapping("/createFlow")
    public void createFlow(String flowName,HttpServletRequest request) {
        Deployment deployment = processEngine.getRepositoryService() //用于流程定义和部署相关对象的Service  
            .createDeployment() //创建一个部署对象
            .name("合成作战"+flowName).addClasspathResource("process/"+flowName+".bpmn") //从ClassPath资源中加载，一次只能加载一个文件  
            .addClasspathResource("process/"+flowName+".png") //从ClassPath资源中加载，一次只能加载一个文件  
            .deploy();

        System.out.println("deployment" + deployment.getId()); //1  
        System.out.println("deployment" + deployment.getName());//部门程序  
    }
}
