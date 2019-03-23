package com.shield.hczz.pztb.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.shield.frame.base.domain.ApplyExp;
import com.shield.frame.base.domain.Attach;
import com.shield.frame.base.domain.Role;
import com.shield.frame.base.domain.User;
import com.shield.frame.base.persistence.AttachMapper;
import com.shield.frame.base.persistence.CodeValueMapper;
import com.shield.frame.base.persistence.UserMapper;
import com.shield.frame.sysmng.dto.CodeValueDTO;
import com.shield.frame.upload.dto.AttachDTO;
import com.shield.frame.utils.AuditLogUtil;
import com.shield.frame.utils.CommonUtil;
import com.shield.frame.utils.ConfigUtil;
import com.shield.frame.utils.DateUtil;
import com.shield.frame.utils.JsonUtil;
import com.shield.hczz.apply.qvo.PzApplyVO;
import com.shield.hczz.base.controller.FlowController;
import com.shield.hczz.base.domain.ClueInfo;
import com.shield.hczz.base.persistence.HctbMapper;
import com.shield.hczz.base.persistence.PzApplyMapper;
import com.shield.hczz.common.SopConstants;
import com.shield.hczz.index.mapper.IndexMapper;
import com.shield.hczz.pzfk.mapper.CaseDetailMapepr;
import com.shield.hczz.pztb.dto.CaseInfoDTO;
import com.shield.hczz.pztb.dto.HctbDTO;
import com.shield.hczz.pztb.service.PztbService;
import com.shield.hczz.utils.FileUrlUtil;

@Controller
@RequestMapping("pztb/*")
public class PztbController extends FlowController {

    @Autowired
    private AttachMapper attachMapper;
    @Autowired
    private PztbService pztbService;
    @Autowired
    private CodeValueMapper codeValueMapper;
    @Autowired
    private HctbMapper hctbMapper;
    @Autowired
    private PzApplyMapper pzApplyMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IndexMapper indexMapper;
    @Autowired
    private CaseDetailMapepr caseDetailMapepr;
    /**
     * 初始化
     * @param mod
     * @param pzid  传入的配帧id
     * @param sfbj  是否编辑：1--可编辑  ，  0--不可编辑
     * @param request
     * @return
     */
    @RequestMapping("init")
    public String init(Model mod, String pzid, String sfbj, String fromDetail, HttpServletRequest request) {
        if("0".equals(sfbj)){
            return "redirect:/pztb/detail?pzApplyId=" + pzid;
        }
        User u = getUser();
        //若pzid为说明是新建的配帧申请，不为空则说明是从详情页中进入
        if (!StringUtils.isEmpty(pzid)) {
            //配帧id
            mod.addAttribute("pzid", pzid);
            //是否新案件  1：是   0：否
            mod.addAttribute("newaj", 0);
        }
        else {
            mod.addAttribute("pzid", UUID.randomUUID().toString().replaceAll("-", ""));
            mod.addAttribute("newaj", 1);
        }
        //是否编辑
        mod.addAttribute("sfbj", sfbj);
        //业务类型
        List<HashMap<String, Object>> list = codeValueMapper.getYwlxMap();
        String ywlx = JsonUtil.writeValueAsString(list);
        mod.addAttribute("ywlx", ywlx);
        //案件类型
        List<CodeValueDTO> ajlxlist = codeValueMapper.getListByTypeId("2005");
        String ajlx = JsonUtil.writeValueAsString(ajlxlist);
        mod.addAttribute("ajlx", ajlx);
        //导入案件类型
        List<CodeValueDTO> caseTypeImplist = codeValueMapper.getListByTypeId("2001");
        String caseTypeImp = JsonUtil.writeValueAsString(caseTypeImplist);
        mod.addAttribute("caseTypeImp", caseTypeImp);
        
        //案件状态
        List<CodeValueDTO> ajztlist = codeValueMapper.getListByTypeId("1005");
        for(CodeValueDTO cvdAjzt : ajztlist){
            for(CodeValueDTO cvdCaseTypeImp : caseTypeImplist){
                if(cvdAjzt.getCodeValue().startsWith(cvdCaseTypeImp.getCodeValue())){
                    cvdAjzt.setValueDesc(cvdAjzt.getValueDesc()+"（"+cvdCaseTypeImp.getValueDesc()+"）");
                }
            }
        }
        String ajzt = JsonUtil.writeValueAsString(ajztlist);
        mod.addAttribute("ajzt", ajzt);
        
        String deptId = userMapper.getByPK(u.getUserId()).getDeptId().toString();
        for(Role r : u.getRoleList()){
            if(r.getRoleId().toString().equals(SopConstants.ROLE_HCZXMJ)
                || r.getRoleId().toString().equals(SopConstants.ROLE_ZBZ)){
                deptId = "1";
                break;
            }
        }
        mod.addAttribute("deptId", deptId);
        mod.addAttribute("deptName", userMapper.getByPK(u.getUserId()).getDeptname());
        mod.addAttribute("userName", u.getUserName());
        mod.addAttribute("currDate", DateUtil.getDateToStr(new Date(), "yyyy年MM月dd日"));
        if(fromDetail != null && fromDetail.equals("1")){
            mod.addAttribute("fromDetail", "1");
        }else{
            mod.addAttribute("fromDetail", "0");
        }
        AuditLogUtil.addLog(request, "合成申请", "2", "进入合成申请页面", "", "0"); // 记录日志
        return "pztb/pztb";
    }
    
    /**
     * 详情
     * @param pzid  传入的配帧id
     * @param request
     * @return
     */
    @RequestMapping("detail")
    public ModelAndView detail(@RequestParam("pzApplyId")String pzId, HttpServletRequest request) {
    	//跳转至配帧详情页面
    	ModelAndView mav = new ModelAndView("pztb/pztbDetail");
    	Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("pzid", pzId);
		
		//受案单位、配帧级别
		Map<String,String> map1 = indexMapper.selectAccept(pzId);
		PzApplyVO pv = new PzApplyVO();
		if(map1.containsKey("PZ_APPLY_GRADE")){
			map1.put("PZ_APPLY_GRADE", map1.get("PZ_APPLY_GRADE").equals("1")?"一":map1.get("PZ_APPLY_GRADE").equals("2")?"二":"三");
			pv.setPzApplyGradeName(map1.get("PZ_APPLY_GRADE"));
		}
		if(map1.containsKey("ACCEPT_UNIT")){
			pv.setDeptName(caseDetailMapepr.selectDept(map1.get("ACCEPT_UNIT")));
		}
        
    	//根据pzId查询案件信息、任务级别
        List<CaseInfoDTO> cases = hctbMapper.selectCaseInfo(paramMap);
        if(null!=cases&&cases.size()>0){
        	//案件来源
            if(null!=cases&&cases.size()>0&&null!=cases.get(0).getCaseAjly()){
            	cases.get(0).setCaseAjly(cases.get(0).getCaseAjly().equals("1")?"110指令":
                cases.get(0).getCaseAjly().equals("2")?"派出所上报":"肇事逃逸");
            }
            //案件状态
            List<CodeValueDTO> ajztlist = codeValueMapper.getListByTypeId("1005");
            for (int i = 0; i < ajztlist.size(); i++) {
            	if(ajztlist.get(i).getCodeValue().equals(cases.get(0).getCaseStatus())){
            		cases.get(0).setCaseStatus(ajztlist.get(i).getValueDesc());
            	}
    		}
            //案件类型
            List<CodeValueDTO> ajlxlist = codeValueMapper.getListByTypeId("2001");
            for (int i = 0; i < ajlxlist.size(); i++) {
            	if(ajlxlist.get(i).getCodeValue().equals(cases.get(0).getCaseTypeImp())){
            		cases.get(0).setCaseTypeImp(ajlxlist.get(i).getValueDesc());
            	}
    		}
            //案由
            List<CodeValueDTO> ayList = codeValueMapper.getListByTypeId("2005");
            for (int i = 0; i < ayList.size(); i++) {
				if(ayList.get(i).getCodeValue().equals(cases.get(0).getCaseType())){
					cases.get(0).setCaseType(ayList.get(i).getValueDesc());
				}
			}
           
            if(cases.get(0).getAcceptUnit() != null){
            	cases.get(0).setAcceptUnit(pv.getDeptName());
            }
        
	        //根据pzId查询线索信息
	        List<Map<String, Object>> clues = hctbMapper.selectClueInfo(paramMap);
	        List<ClueInfo> cluesList = new ArrayList<>();
	        ClueInfo clue = new ClueInfo();
	        String clueId = "";
	        for (Map<String, Object> map : clues) {
	            String clueIdTemp = map.get("CLUE_ID").toString();
	            if (!clueIdTemp.equals(clueId)) {
	                clueId = clueIdTemp;
	                clue = new ClueInfo();
	                cluesList.add(clue);
	            }
	            clue.setClueId(clueIdTemp);
	            clue.setClueName(map.get("CLUE_NAME").toString());
	            clue.setClueSource(map.get("CLUE_SOURCE").toString());
	            clue.setClueDesc(map.get("CLUE_DESC") == null ? "" : map.get("CLUE_DESC").toString());
	            clue.setPzTypeDetail(map.get("PZ_TYPE_DETAIL").toString());
	            clue.setPzTypeDetailName(map.get("PZ_TYPE_DETAIL_NAME").toString());
	            clue.setPzTypeName(map.get("PZ_TYPE_NAME").toString());
	            clue.setPzType(map.get("PZ_TYPE").toString());
	            if(null != map.get("ATT_ID") && !map.get("ATT_ID").equals("")){
	                Attach att = new Attach();
	                att.setAttId(map.get("ATT_ID").toString());
	                att.setAttName(map.get("ATT_NAME").toString());
	                att.setAttPath(map.get("ATT_PATH").toString());
	                clue.getAtt().add(att);
	            }
	        }
	        //法律文书
	        Map<String,Object> map = new HashMap<>();
	        	map.put("busId", cases.get(0).getCaseId());
	        List<Attach> flws = attachMapper.getList(map);
	        mav.addObject("cases", cases.get(0));//案件信息
	        mav.addObject("pv", pv);//配帧级别
	        mav.addObject("cluesList", cluesList);//线索信息
	        mav.addObject("flws", flws);
	        
	        /**
	         * 添加查询闭环对接的文书
	         */
	        Map<String, Object> wsCountParam = new HashMap<>();
	        wsCountParam.put("ajbh", cases.get(0).getCaseNo());
	        int lajdscount = hctbMapper.countLajds(wsCountParam);
	        int sadjbcount = hctbMapper.countSadjb(wsCountParam);
	        mav.addObject("sadjbcount", sadjbcount);
	        mav.addObject("lajdscount", lajdscount);
        }
        
        return mav;
    }

    @ResponseBody
    @RequestMapping(value = "loadHctb")
    public Map<String, Object> loadHctb(String pzid, HttpServletRequest request) {
        Map<String, Object> map = pztbService.loadHctb(pzid);
        return map;
    }

    /**
     * 查询案件信息
     * @param ajid  案件编号
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "searchAj")
    public Map<String, Object> searchAj(String ajid) {
        return pztbService.queryCaseById(ajid);
    }

    /**
     * 保存
     * @param hctb 前台表单内容
     * @param newaj 是否新案件 -- 1：是   0：否
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "saveTemp")
    public Map<String, Object> saveTemp(HctbDTO hctb, String newaj, HttpServletRequest request) {
        User usr = (User) request.getSession().getAttribute("loginUser");
        PzApplyVO pv = pzApplyMapper.getById(hctb.getPzApply().getPzApplyId());
        //查询该任务是否存在
        //不存在则创建流程
        //存在则仅保存
        if(pv == null){
        	String ACT_PROCCESS_DEFINITION = ConfigUtil.getMsg("ACT_PROCCESS_DEFINITION_REQUEST");
            //开始流程
            Map<String, Object> map = startFlow(ACT_PROCCESS_DEFINITION);
            if(!"0".equals(map.get("code").toString())){
                return map;
            }
            ProcessInstance pi = (ProcessInstance) map.get("pi");
            //保存流程实例id
            hctb.getPzApply().setFlowId(pi.getId());
        }
        //保存任务
        Map<String, Object> map = pztbService.save(hctb, newaj, usr);
        AuditLogUtil.addLog(request, "合成申请", "4", "保存合成申请信息", hctb.getPzApply().getPzApplyId(), "0"); // 记录日志
        return map;
    }

    /**
     * 提交至单位领导审批
     * @param approveUser 选择的审批人
     * @param pzid 配帧id
     * @param request 
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "saveNext")
    public Map<String, Object> saveNext(String[] approveUser, String pzid, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        //根据配帧id查询流程实例id
        PzApplyVO pv = pzApplyMapper.getById(pzid);
        String procInstId = pv.getFlowId();
        Map<String, Object> vars = new HashMap<>();
        String approveUserStr = CommonUtil.joinWith(approveUser, ",");
        vars.put("users", approveUserStr);
        //调用普通流程流转 HCZZ_1001/HCZZ_1010-->HCZZ_1002
        Map<String, Object> flowRet = simpleTaskComplate(procInstId, vars, false);
        if ("0".equals(flowRet.get("code").toString())) {
            Map<String, Object> userparam = new HashMap<>();
            userparam.put("ids", approveUserStr);
            List<Map<String, Object>> userlist = hctbMapper.getUsersByIds(userparam);
            String users = "";
            for (Map<String, Object> u : userlist) {
                users += u.get("USERNAME") + " ";
            }
            String desc = "合成申请已提交至" + users;
            //重置已读未读状态
            pzApplyMapper.backUnread(new ApplyExp(pzid));
            //插入配帧日志
            AuditLogUtil.addWorkLog(request, "", "", pzid, pv.getFlowId(), SopConstants.FLOW_SQPZ, "", desc);
            AuditLogUtil.addLog(request, "合成申请", "4", "提交合成申请", pzid, "0"); // 记录日志
        }
        map.put("code", "0".equals(flowRet.get("code").toString()) ? 0 : 500);
        map.put("msg", "0".equals(flowRet.get("code").toString()) ? "提报成功！" : "提报失败！");
        return map;
    }

    /**
     * 上传附件
     * @param request
     * @param response
     * @param ajid 配帧id
     * @param type 1来源证明  2受案登记表 3立案决定书 4呈请立案报告书 5配帧反馈
     */
    @ResponseBody
    @RequestMapping(value = "uploadFiles")
    public void uploadFiles(HttpServletRequest request, HttpServletResponse response, String ajid,
        String type) {
        String fjmc = "";
        switch (type) {
        default:
            break;
        case "1":
            fjmc = "来源证明";
            break;
        case "2":
            fjmc = "受案登记表";
            break;
        case "3":
            fjmc = "立案决定书";
            break;
        case "4":
            fjmc = "呈请立案报告书";
            break;
        case "5":
            fjmc = "配帧反馈";
            break;
        }
        
        response.setStatus(200);
        response.setCharacterEncoding("UTF-8");//设置编码格式
        response.setContentType("text/html;charset=UTF-8");//设置浏览器响应编码格式
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "上传成功！");
        try {
            MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
            Map<String, MultipartFile> fileMap = mRequest.getFileMap();
            for(String key : fileMap.keySet()){
                MultipartFile mf = fileMap.get(key);
                //附件名长度校验
                if(mf.getOriginalFilename().length() > 30){
                    map.put("code", -1);
                    map.put("msg", "上传附件名长度不能超过30个字符!");
                    response.getWriter().write(JsonUtil.writeValueAsString(map));
                    response.getWriter().close();
                    response.getWriter().flush();
                    return;
                }else if(mf.getSize() > Long.parseLong(SopConstants.MAX_UPLOAD_SIZE)*1024*1024){
                    //文件大小校验
                    map.put("code", -1);
                    map.put("msg", "上传附件大小不能超过"+SopConstants.MAX_UPLOAD_SIZE+"MB！");
                    response.getWriter().write(JsonUtil.writeValueAsString(map));
                    response.getWriter().close();
                    response.getWriter().flush();
                    return;
                }
            }
            List<Map<String, String>> list = FileUrlUtil.uploadFiles2Local(ajid, request, type);
            saveFile(list, ajid, request);
            map.put("list", list);
            response.getWriter().write(JsonUtil.writeValueAsString(map));
            response.getWriter().close();
            response.getWriter().flush();
            
            AuditLogUtil.addLog(request, "附件上传", "3", "添加附件"+fjmc, "", "0"); // 记录日志
        }
        catch (IOException e) {
            map.put("code", -1);
            map.put("msg", "上传失败！");
            AuditLogUtil.addLog(request, "附件上传", "3", "添加附件"+fjmc, "", "1"); // 记录日志
            e.printStackTrace();
        }
    }
    
    /**
     * 删除附件
     * @param request
     * @param attId 附件id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "removeFiles")
    public Map<String, Object> removeFiles(HttpServletRequest request, String attId) {
        User usr = (User) request.getSession().getAttribute("loginUser");
        Map<String, Object> map = new HashMap<>();
        Attach record = new Attach();
        record.setDeleteBy(usr.getUserId().toString());
        record.setAttId(attId);
        int i = attachMapper.logicDelByPK(record);
        if (i > 0) {
            map.put("code", 0);
        }
        else {
            map.put("code", -1);
        }
        AuditLogUtil.addLog(request, "附件上传", "5", "删除附件", attId, "0"); // 记录日志
        return map;
    }
    
    /**
     * 获取本单位审批人
     * @param request
     * @param flag 1：分管领导  2：值班长
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getApproveUser")
    public Map<String, Object> getApproveUser(HttpServletRequest request, String flag) {
        Map<String, Object> map = new HashMap<>();
        User usr = (User) request.getSession().getAttribute("loginUser");
        Map<String, Object> param = new HashMap<>();
        if(flag!= null && flag.equals("1")){
            param.put("roleId", SopConstants.ROLE_FGLD);
            param.put("deptId", usr.getDeptId().toString());
        }else if(flag!= null && flag.equals("2")){
            param.put("roleId", SopConstants.ROLE_ZBZ);
        }
        List<User> users = hctbMapper.getApprovers(param);
        map.put("code", 0);
        map.put("list", users);
        return map;
    }
    
    /**
     * 插入FDI_T_ATTACH表
     * @param list
     * @param ajid
     * @param request
     */
    private void saveFile(List<Map<String, String>> list, String ajid, HttpServletRequest request) {
        User usr = (User) request.getSession().getAttribute("loginUser");
        for (Map<String, String> map : list) {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            AttachDTO att = new AttachDTO();
            att.setAttId(uuid);
            att.setAttName(map.get("name"));
            att.setAttPath(map.get("url"));
            att.setAttRname(map.get("name"));
            att.setModuleType(map.get("type"));
            att.setCreateBy(usr.getUserId().toString());
            att.setCreateDt(new Date());
            att.setUpdateDt(new Date());
            att.setUpdateBy(usr.getUserId().toString());
            attachMapper.add(att);
            map.put("attId", uuid);
        }
    }

}
