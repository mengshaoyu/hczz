package com.shield.hczz.recordsmng.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shield.hczz.recordsmng.qvo.AuditLogVO;
import com.shield.hczz.recordsmng.service.SyAuditLogService;
import com.shield.frame.common.Constants;
import com.shield.frame.common.qvo.CommonVO;
import com.shield.frame.utils.AuditLogUtil;
import com.shield.frame.utils.Column;
import com.shield.frame.utils.PoiUtil;
import com.shield.frame.utils.SysParamUtil;

/**
 * <b>功能：日志管理</b><br>
 * @version 1.0
 */

@Controller
@RequestMapping("auditlog")
public class SyAuditLogController {
    @Autowired
    private SyAuditLogService syAuditLogService;

    /**
     * <b>功能：初始化界面</b><br>
     * @return String
     **/
    @RequestMapping("init")
    public String init() {
        return "recordsmng/auditLog/logList";
    }

    /**
     * <b>功能：列表查询</b><br>
     * @param param
     * @param page
     * @param rows
     * @return HashMap<String,Object>
     **/
    @ResponseBody
    @RequestMapping("getList")
    public HashMap<String, Object> getList(AuditLogVO log, String page, String rows) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        if (log != null) {
            map.put("function", log.getFunction());
            map.put("operContent", log.getOperContent());
        }

        HashMap<String, Object> dataMap = this.syAuditLogService.getList(map, page, rows);

        return dataMap;
    }

    /**
     * <b>功能：检验导出的数据是否存在</b><br>
     * @param gangPKs
     * @return CommonVO
     **/
    @ResponseBody
    @RequestMapping(value = "expPartCheck")
    public CommonVO expPartCheck(String logPKs) {
        return syAuditLogService.expPartCheck(logPKs);
    }

    /**
     * <b>功能：导出选中数据</b><br>
     * @param rs
     * @param request
     * @throws Exception
     *             void
     **/
    @ResponseBody
    @RequestMapping(value = "exportChoose")
    public void exportChoose(HttpServletResponse rs, HttpServletRequest request) throws Exception {
        String max_export = SysParamUtil.getSysParamByKey(Constants.SYSPARAM_PREX + "maxExport",
            Constants.DOMAIN_NAME);

        String logPKs = request.getParameter("logPKs"); // 导出数据主键

        if (StringUtils.isNotBlank(logPKs)) {
            List<AuditLogVO> list = syAuditLogService.getList(logPKs, 1,
                Integer.valueOf(max_export)); // 数据源

            AuditLogUtil.addLog(request, "日志管理", "6", "导出选中日志列表", logPKs, "0"); // 记录日志
                                                                                // 此处主键字符串过长，未存储主键

            Column[] ucolumns = getColumn();

            String fileName = "日志管理";

            String reportTitle = "日志管理";

            String filename = new String(fileName.getBytes("GBK"), "ISO8859-1") + ".xls";
            HSSFWorkbook wb = new HSSFWorkbook();

            // getExcel 参数：数据集合、导出列、导出文件名和标题名、打印格式（TRUE横向 false 纵向）
            wb = PoiUtil.getExcel(list, ucolumns, reportTitle, true);

            if (wb != null) {
                PoiUtil.sendExcel(wb, rs, filename); // 导出文件
            }
        }
    }

    /**
     * <b>功能：全部导出验证是否为空</b><br>
     * @param gang
     * @return CommonVO
     **/
    @ResponseBody
    @RequestMapping(value = "expCheck")
    public Map<String, Object> expCheck(AuditLogVO log, HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> result = new HashMap<String, Object>();
        if (log != null) {
            map.put("function", log.getFunction());
            map.put("operContent", log.getOperContent());
        }
        int total = syAuditLogService.expCheck(map);
        // 导出量控制
        String max_export = SysParamUtil.getSysParamByKey(Constants.SYSPARAM_PREX + "maxExport",
            Constants.DOMAIN_NAME);
        boolean valid = false, success = false; // 是否符合条件
        if (total <= Integer.parseInt(max_export)) {
            valid = true;
            success = true;
        }
        result.put("success", success);
        result.put("valid", valid);
        result.put("max_export", max_export);
        // 添加日志
        if (success) {
            AuditLogUtil.addLog(request, "日志管理", "6", "导出日志信息验证", "", "0");
        }
        else {
            AuditLogUtil.addLog(request, "日志管理", "6", "导出日志信息验证", "", "1");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "exportAll")
    public void exportAll(HttpServletResponse rs, HttpServletRequest request, AuditLogVO log)
        throws Exception {
        String max_export = SysParamUtil.getSysParamByKey(Constants.SYSPARAM_PREX + "maxExport",
            Constants.DOMAIN_NAME);
        HashMap<String, Object> map = new HashMap<String, Object>();
        if (log != null) {
            map.put("function", log.getFunction());
            map.put("operContent", log.getOperContent());
        }
        map.put("maxExp", Integer.valueOf(max_export));
        List<AuditLogVO> list = syAuditLogService.exportAll(map); // 数据源

        AuditLogUtil.addLog(request, "日志管理", "6", "导出全部日志列表", "", "0"); // 记录日志
                                                                        // 此处主键字符串过长，未存储主键

        Column[] ucolumns = getColumn();

        String fileName = "日志管理";

        String reportTitle = "日志管理";

        String filename = new String(fileName.getBytes("GBK"), "ISO8859-1") + ".xls";
        HSSFWorkbook wb = new HSSFWorkbook();
        // getExcel 参数：数据集合、导出列、导出文件名和标题名、打印格式（TRUE横向 false 纵向）
        wb = PoiUtil.getExcel(list, ucolumns, reportTitle, true);

        if (wb != null) {
            PoiUtil.sendExcel(wb, rs, filename); // 导出文件
        }
    }

    /**
     * 导出时excel的列表题
     */
    private Column[] getColumn() {
        Column[] ucolumns = new Column[] {
            new Column("操作者 ", "userName", Column.ENUM_TYPE.VARCHAR2, 10, 6000, false),
            new Column("功能模块", "function", Column.ENUM_TYPE.VARCHAR2, 10, 6000, false),
            new Column("操作类型", "operTypeStr", Column.ENUM_TYPE.VARCHAR2, 10, 3000, false),
            new Column("操作内容", "operContent", Column.ENUM_TYPE.VARCHAR2, 10, 6000, false),
            new Column("操作结果", "resultStr", Column.ENUM_TYPE.VARCHAR2, 10, 6000, false),
            new Column("操作时间", "createDt", Column.ENUM_TYPE.DATE, 10, 6000, false) };

        return ucolumns;
    }

}
