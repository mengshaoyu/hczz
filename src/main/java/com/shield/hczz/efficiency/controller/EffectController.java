package com.shield.hczz.efficiency.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shield.frame.base.domain.Role;
import com.shield.frame.base.domain.User;
import com.shield.frame.base.persistence.impl.UserMapperImpl;
import com.shield.frame.common.qvo.ResultVO;
import com.shield.frame.sysmng.service.RoleService;
import com.shield.frame.utils.AuditLogUtil;
import com.shield.hczz.base.domain.Effect;
import com.shield.hczz.common.SopConstants;
import com.shield.hczz.efficiency.qvo.EffectQO;
import com.shield.hczz.efficiency.service.EffectService;
import com.shield.hczz.pzlog.service.PzLogService;

@Controller
@RequestMapping(value="/effect/*")
public class EffectController {
    
    @Autowired
    private PzLogService pzLogService;
    
    @Autowired
    private EffectService effectService;
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private UserMapperImpl userMapperImpl;

    @RequestMapping(value="init")
    public String init(HttpServletRequest request,String pzid){
        User loginUser = (User)request.getSession().getAttribute("loginUser");
        List<Role> roles = loginUser.getRoleList();
        String show1 = "0";
        String show2 = "0";
        String roleType = "";
        //小领导数量
        int count = userMapperImpl.getCountByRoleId(SopConstants.ROLE_HCZXLD1);
        EffectQO qo = new EffectQO();
        qo.setPzApplyId(pzid);
        if(roleService.has(roles, SopConstants.ROLE_HCZXLD1)){
            roleType = "0";
            qo.setEffectType(SopConstants.EFFECT_TYPE0);
            int count0 = effectService.getCount(qo);
            //没人评定过
            if(count0 == 0){
                show1 = "2";
            }
            else{//自己没有评定过
                qo.setCreateBy(loginUser.getUserId().toString());
                count0 = effectService.getCount(qo);
                //未评定过才能看到
                if(count0 == 0){
                    show1 = "1";
                }
            }
        }
        else if(roleService.has(roles, SopConstants.ROLE_HCZXLD2)){
            roleType = "1";
            qo.setEffectType(SopConstants.EFFECT_TYPE0);
            int count1 = effectService.getCount(qo);
            //小领导未全部评定完 灰化
            if(count1 < count){
                show2 = "2";
            }
            else {//小领导已全部评定完
                qo.setEffectType(SopConstants.EFFECT_TYPE1);
                int count2 = effectService.getCount(qo);
                //大领导未评定过
                if(count2 == 0){
                    show2 = "1";
                }
            }
        }
        request.setAttribute("show1", show1);
        request.setAttribute("show2", show2);
        request.setAttribute("pzid", pzid);
        request.setAttribute("roleType", roleType);
        AuditLogUtil.addLog(request, "任务管理", "2", "进入效能评定页面", "", "0");
        return "effect/effect";
    }
    
    @ResponseBody
    @RequestMapping(value="getlist")
    public ResultVO getlist(HttpServletRequest request,EffectQO qo){
        List<Map<String,Object>> list = effectService.getlist(qo);
        AuditLogUtil.addLog(request, "任务管理", "2", "查看效能评定信息", "", "0");
        return ResultVO.ok(list);
    }
    
    @ResponseBody
    @RequestMapping(value="getPoliceTypes")
    public ResultVO getPoliceTypes(String pzid){
        Set<String> list = pzLogService.getPoliceTypeByPzid(pzid);
        return ResultVO.ok(list);
    }
    
    @ResponseBody
    @RequestMapping(value="getPjByPzid")
    public ResultVO getPjById(HttpServletRequest request,String pzid){
        Map<String,Object> result = effectService.getPjById(pzid);
        AuditLogUtil.addLog(request, "任务管理", "2", "查看任务评价信息", pzid, "0");
        return ResultVO.ok(result);
    }
    
    @ResponseBody
    @RequestMapping(value="add")
    public ResultVO add(HttpServletRequest request,Effect effect){
        User user = (User) request.getSession().getAttribute("loginUser");
        effect.setCreateBy(user.getUserId().toString());
        effect.setUpdateBy(user.getUserId().toString());
        int result = effectService.add(effect);
        if(result >0){
            AuditLogUtil.addLog(request, "任务管理", "3", "进行任务效能评定", effect.getPzEffectId(), "0");
            return ResultVO.ok();
        }
        else{
            AuditLogUtil.addLog(request, "任务管理", "3", "进行任务效能评定", effect.getPzEffectId(), "1");
            return ResultVO.error();
        }
    }
}
