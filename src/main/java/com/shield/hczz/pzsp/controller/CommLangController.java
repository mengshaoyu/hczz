package com.shield.hczz.pzsp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shield.frame.base.domain.User;
import com.shield.frame.utils.AuditLogUtil;
import com.shield.hczz.apply.qvo.PzApplyVO;
import com.shield.hczz.apply.service.PzApplyService;
import com.shield.hczz.base.controller.SopBaseController;
import com.shield.hczz.base.domain.FlowWait;
import com.shield.hczz.base.domain.PzCommLang;
import com.shield.hczz.base.persistence.ActivitiMapper;
import com.shield.hczz.base.persistence.PzCommLangMapper;
import com.shield.hczz.common.SopConstants;
import com.shield.hczz.pzsp.dto.CommLangDTO;

@Controller
@RequestMapping("commLang")
public class CommLangController extends SopBaseController {

    @Autowired
    private PzCommLangMapper pzCommLangMapper;
    @Autowired
    private PzApplyService pzApplyService;
    @Autowired
    private ActivitiMapper activitiMapper;
    
    @RequestMapping("init")
    public String init(Model mod, String pzid, HttpServletRequest request) {
        mod.addAttribute("pzid", pzid);
        PzApplyVO apply=this.pzApplyService.getInfoById(pzid);
        request.setAttribute("caseName", apply.getCaseName());
        Map<String, Object> flowInfoparam = new HashMap<>();
        flowInfoparam.put("pzid", pzid);
        List<FlowWait> fw = activitiMapper.getFlowInfo(flowInfoparam);
        String actId = "";
        if(fw.size() > 0){
            actId = fw.get(0).getTaskKey();
        }
        if(actId.equals(SopConstants.FLOW_DZBZSP)){
            mod.addAttribute("actId", "2");
            AuditLogUtil.addLog(request, "任务管理", "2", "进入值班长审批页面", pzid, "0"); // 记录日志
        }
        if(actId.equals(SopConstants.FLOW_FGLDSP)){
            mod.addAttribute("actId", "1");
            AuditLogUtil.addLog(request, "任务管理", "2", "进入分管领导审批页面", pzid, "0"); // 记录日志
        }
        
        return "pzsp/pzspCombination";
    }
    
    /**
     * M3_1001
     * 加载常用语
     * @param type 通过--1 、退查--2
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "loadLangs")
    public Map<String, Object> loadLangs(String type) {
        User user = getUser();
        PzCommLang cl = new PzCommLang();
        cl.setCreateBy(String.valueOf(user.getUserId()));
        cl.setLangType(type);
        List<PzCommLang> pcs = pzCommLangMapper.selectLang(cl);
        Map<String, Object> map = new HashMap<>();
        map.put("data", pcs);
        return getRet(0, "", map);
    }
    
    /**
     * M3_1002、M3_1003
     * 添加或更新常用语
     * @param pz 
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "createOrUpdateLangs")
    public Map<String, Object> createOrUpdateLangs(CommLangDTO pz){
        User user = getUser();
        for(PzCommLang p : pz.getPcls()){
            p.setCreateBy(String.valueOf(user.getUserId()));
            p.setUpdateBy(String.valueOf(user.getUserId()));
            int i = pzCommLangMapper.countLang(p);
            if(i > 0){
                pzCommLangMapper.updateLang(p);
            }else{
                pzCommLangMapper.insertLang(p);
            }
        }
        AuditLogUtil.addLog(request, "任务管理", "4", "编辑了常用语", "", "0"); // 记录日志
        return getRet(0, "", null);
    }
    
    /**
     * M3_1004
     * 恢复默认值
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "deleteLangs")
    public Map<String, Object> deleteLangs(){
        User user = getUser();
        PzCommLang pc = new PzCommLang();
        pc.setCreateBy(String.valueOf(user.getUserId()));
        pzCommLangMapper.deleteLang(pc);
        
        PzCommLang cl = new PzCommLang();
        cl.setCreateBy(String.valueOf(user.getUserId()));
        List<PzCommLang> pcs = pzCommLangMapper.selectLang(cl);
        Map<String, Object> map = new HashMap<>();
        map.put("data", pcs);
        AuditLogUtil.addLog(request, "任务管理", "4", "重置了常用语", "", "0"); // 记录日志
        return getRet(0, "", map);
    }
    
}
