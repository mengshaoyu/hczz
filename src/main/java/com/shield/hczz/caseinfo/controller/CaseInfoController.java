package com.shield.hczz.caseinfo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.shield.frame.common.Constants;
import com.shield.frame.common.qvo.CommonVO;
import com.shield.frame.common.qvo.ResultVO;
import com.shield.frame.utils.AuditLogUtil;
import com.shield.hczz.base.domain.CaseInfo;
import com.shield.hczz.caseinfo.qvo.CaseInfoQO;
import com.shield.hczz.caseinfo.service.CaseInfoService;
import com.shield.hczz.utils.ExcelUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/caseinfo/*")
public class CaseInfoController {

    @Autowired
    private CaseInfoService caseInfoService;
    
    @ResponseBody
    @RequestMapping("getlist")
    public ResultVO getList(HttpServletRequest request,CaseInfoQO qo, String page, String rows) {
        AuditLogUtil.addLog(request, "案件管理", "1", "查询案件列表", "", "0");
        return ResultVO.ok(caseInfoService.getlist(qo, page, rows));
    }

    @ResponseBody
    @RequestMapping("add")
    public CommonVO add(HttpServletRequest request,CaseInfo caseInfo) {
        AuditLogUtil.addLog(request, "案件管理", "3", "添加案件", caseInfo.getCaseId(), "0");
        return caseInfoService.add(caseInfo);
    }

//    @ResponseBody
    @RequestMapping("imports")
    public void imports(HttpServletRequest request,HttpServletResponse response) {
        ResultVO result = null;
        try {
            String userId = ((BigDecimal)request.getSession().getAttribute(Constants.SESN_USR_UID)).toString();
            MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
            Map<String, MultipartFile> fileMap = mRequest.getFileMap();
            
            for (Map.Entry<String, MultipartFile> entry : fileMap.entrySet()) {
                MultipartFile mfile = entry.getValue();
                InputStream is = mfile.getInputStream();
                List<CaseInfo> list = ExcelUtil.getDataFromExcel(is,
                    ExcelUtil.getExcelType(mfile.getOriginalFilename()), CaseInfo.class);
                
                //解析案件信息
                HashMap<String, Object> map=this.caseInfoService.parseCaseImport(list,userId);
                
                list=(List<CaseInfo>)map.get("list");
                
                List<CaseInfo> errlist=(List<CaseInfo>)map.get("errlist");
                
                if (errlist.size() == 0 && list.size()>0){
                    caseInfoService.adds(list);
                    result = ResultVO.ok(map);
                    AuditLogUtil.addLog(request, "案件管理", "3", "案件导入", "", "0");
                }
                else if(errlist.size() > 0){
                    result = ResultVO.error();
                    result.put("errlist", errlist);
                    AuditLogUtil.addLog(request, "案件管理", "3", "案件导入", "", "1");
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            result = ResultVO.error(e.getMessage());
            JSONObject obj = JSONObject.fromObject(result);
            try {
                response.getWriter().println(obj.toString());
            }
            catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        JSONObject obj = JSONObject.fromObject(result);
        try {
            response.getWriter().println(obj.toString());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
//        result.setMsg(result.getSuccess()?"SUCCESS":"FAILED");
//        return result;
    }

    @RequestMapping("init")
    public String init(HttpServletRequest request) {
        AuditLogUtil.addLog(request, "案件管理", "2", "进入案件管理页面", "", "0");
        return "case/caseList";
    }
}
