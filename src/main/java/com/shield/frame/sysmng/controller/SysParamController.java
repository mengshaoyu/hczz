package com.shield.frame.sysmng.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shield.frame.common.Constants;
import com.shield.frame.common.qvo.BaseVO;
import com.shield.frame.sysmng.dto.CodeValueDTO;
import com.shield.frame.sysmng.dto.SysparamDTO;
import com.shield.frame.sysmng.dto.TreeNode;
import com.shield.frame.sysmng.service.SysParamService;
import com.shield.frame.utils.AuditLogUtil;
import com.shield.frame.utils.CodeTypeUtil;
import com.shield.frame.utils.SysParamUtil;

@Controller
@RequestMapping(value = "/sysparam/*")
public class SysParamController {
    @Autowired
    private SysParamService sysParamService;

    /**
     * 菜单页面初始化
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "init")
    public String init(ModelMap modelMap) {
        return "sysmng/sysparam";
    }

    //	/**
    //	 * 获取系统参数列表
    //	 * 
    //	 * */
    //	@ResponseBody
    //	@RequestMapping(value="getSysparam")
    //	public List<SysparamDTO> getSysparam(){
    //		List<SysparamDTO> list = null;
    //		list = sysParamService.getSysparamList();
    //		
    //		return list;
    //	}

    /**
     * 获取系统参数分页列表
     * 
     * */
    @ResponseBody
    @RequestMapping(value = "getPsysparam")
    public HashMap<String, Object> getPsysparam(String page, String rows) {
        //当前页   
        int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);
        //每页显示条数   
        int intRows = Integer.parseInt((rows == null || rows == "0") ? "10" : rows);
        //开始条数
        HashMap<String, Object> datalist = new HashMap<String, Object>();
        HashMap<String, Object> map = new HashMap<String, Object>();
        List<SysparamDTO> list = this.sysParamService.getPsysparamList(map, intPage, intRows);
        //		List<CodeValueDTO> domainCode=CodeTypeUtil.getCodeValueByTypeId("1001", "0");
        //		for(){
        //			
        //		}
        int total = this.sysParamService.getCount();
        datalist.put("rows", list);
        datalist.put("total", total);
        return datalist;
    }

    //	/**
    //	 * 保存系统参数
    //	 * 
    //	 * @sysParam
    //	 * @return
    //	 */
    //	@ResponseBody
    //	@RequestMapping(value="addSysparam")
    //	public BaseVO addSysparam(SysparamDTO sysParam, BindingResult result, HttpServletRequest request){
    //		BaseVO baseVO = new BaseVO();
    //		if(result.hasErrors()) {
    //            baseVO.setMsgCode("comm_101");
    //            return baseVO;
    //        }
    //		int sum = -1;
    //		sum = sysParamService.addSysparam(sysParam);
    //		if(sum >= 0){
    //    		baseVO.setMsgCode("comm_001");
    //    	}else{
    //    		baseVO.setMsgCode("comm_002");
    //    	}
    //		return baseVO;
    //	}

    /**
     * 编辑系统参数
     * 
     * @sysParam
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updSysparam")
    public BaseVO updSysparam(SysparamDTO sysParam, String sysKey, String domainName,
        HttpServletRequest request) {
        BaseVO baseVO = new BaseVO();
        try {
            int sum = -1;
            sum = sysParamService.updSysparam(sysParam, sysKey, domainName);
            if (sum >= 0) {
                baseVO.setMsgCode("comm_001");
                AuditLogUtil.addLog(request, "系统参数", "4", "系统参数编辑", "", "0");
            }
            else {
                baseVO.setMsgCode("comm_002");
                AuditLogUtil.addLog(request, "系统参数", "4", "系统参数编辑", "", "1");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return baseVO;
    }

    /**
     * 删除系统参数
     * 
     * @ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delSysparam")
    public BaseVO delSysparam(String ids, String doms, String skeys, HttpServletRequest request) {
        BaseVO baseVO = new BaseVO();
        try {
            String[] idss = ids.split(",");
            String[] domss = doms.split(",");
            String[] skeyss = skeys.split(",");
            int sum = -1;
            sum = sysParamService.delSysparams(idss, domss, skeyss);
            if (sum >= 0) {
                baseVO.setMsgCode("comm_001");
                AuditLogUtil.addLog(request, "系统参数", "5", "系统参数删除", ids, "0");
            }
            else {
                baseVO.setMsgCode("comm_003");
                AuditLogUtil.addLog(request, "系统参数", "5", "系统参数删除", ids, "1");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return baseVO;
    }

    /**
     * 获取系统参数所属域下拉数据
     * @param parent
     * */
    @ResponseBody
    @RequestMapping(value = "getDemainTree")
    public List<CodeValueDTO> getDemainTree() {
        List<CodeValueDTO> list = sysParamService.getDomainName();
        return list;
    }

    /**
     * 验证系统域下系统参数的唯一性
     * 
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "ckeckSysParam")
    public BaseVO ckeckSysParam(String sysKey, String domainName, String id) {
        BaseVO baseVO = new BaseVO();
        Map map = new HashMap();
        map.put("sysKey", sysKey);
        map.put("domainName", domainName);
        map.put("id", id);
        SysparamDTO sysparam = sysParamService.getSysParam(map);
        if (sysparam != null) {
            baseVO = new BaseVO("comm_001");
        }
        else {
            baseVO = new BaseVO("comm_003");
        }

        return baseVO;
    }

    /**
     * 校验要操作的数据是否已被删除
     * 
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "ckeckSysP")
    public BaseVO ckeckSysP(String id) {
        BaseVO baseVO = new BaseVO();
        SysparamDTO sysparam = sysParamService.getSysP(id);
        if (sysparam != null) {
            baseVO = new BaseVO("comm_001");
        }
        else {
            baseVO = new BaseVO("comm_003");
        }

        return baseVO;
    }

    /**
     * 跳转到系统参数编辑页面
     */
    @RequestMapping(value = "sysparam_edit")
    public String sysparam_check(String id, HttpServletRequest request) {
        SysparamDTO sysparam = sysParamService.getSysP(id);
        request.setAttribute("sysparam", sysparam);

        return "sysmng/sysparam_edit";
    }

}
