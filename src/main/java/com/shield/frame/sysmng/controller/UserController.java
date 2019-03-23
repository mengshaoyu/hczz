package com.shield.frame.sysmng.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.shield.frame.base.domain.User;
import com.shield.frame.common.Constants;
import com.shield.frame.common.qvo.BaseVO;
import com.shield.frame.sysmng.dto.UserDTO;
import com.shield.frame.sysmng.service.UserService;
import com.shield.frame.utils.AuditLogUtil;
import com.shield.frame.utils.Column;
import com.shield.frame.utils.CommonUtil;
import com.shield.frame.utils.FileDownloadUtil;
import com.shield.frame.utils.MD5Util;
import com.shield.frame.utils.PoiUtil;
import com.shield.frame.utils.SysParamUtil;

@Controller
@RequestMapping(value = "/usr/*")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户页面初始化
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "init")
    public String init(ModelMap modelMap) {
        return "sysmng/user";
    }

    /**
     * 新增页面初始化
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "initadd")
    public String initadd(ModelMap modelMap) {
        return "sysmng/addUser";
    }

    /**
     * 编辑页面初始化
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "initedit")
    public String initedit(String id, HttpServletRequest requet) {
        requet.setAttribute("id", id);
        return "sysmng/editUser";
    }

    /**
     * 编辑页面初始化
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "initquery")
    public String initquery(String id, HttpServletRequest requet) {
        requet.setAttribute("id", id);
        return "sysmng/editUser";
    }

    /**
     * 取得用户分页一览表
     *
     * @param modelMap
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getPList")
    public HashMap<String, Object> getPaginationList(String page, String rows, String username,
        String account, String userno, String dept, String state, String gender,
        HttpServletRequest request) {
        //当前页   
        int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);
        //每页显示条数   
        int intRows = Integer.parseInt((rows == null || rows == "0") ? "10" : rows);
        //开始条数
        HashMap<String, Object> datalist = new HashMap<String, Object>();
        HashMap<String, Object> map = new HashMap<String, Object>();//封装查询条件
        map.put("username", username);
        map.put("account", account);
        map.put("userno", userno);
        map.put("dept", dept);
        map.put("state", state);
        map.put("gender", gender);
        //cyl 注释掉：数据权限用法错误
        /* if(null!=DataAuthUtil.getDataAuth() && !CommonUtil.isEmpty(DataAuthUtil.getDataAuth().get(Constants.DATAAUTH_D).toString())){
         	//将部门权限id  字符串转换成部门id 集合
             List<String> deptrray = Arrays.asList(DataAuthUtil.getDataAuth().get(Constants.DATAAUTH_D).toString().split(","));
             map.put("hbDataAuthD", deptrray);
         }*/
        List<UserDTO> list = this.userService.getPaginationList(map, intPage, intRows);
        int total = this.userService.getCount(map);
        datalist.put("rows", list);
        datalist.put("total", total);
        return datalist;
    }

    /**
     * 导出限制验证
     *
     * @param 查询条件
     * @return int
     */
    @ResponseBody
    @RequestMapping(value = "expCheck")
    public String expCheck(String username, String account, String userno, String dept,
        String state, String gender, HttpServletRequest request, HttpServletResponse rs) {
        HashMap<String, Object> map = new HashMap<String, Object>();//封装查询条件
        map.put("username", username);
        map.put("account", account);
        map.put("userno", userno);
        map.put("dept", dept);
        map.put("state", state);
        map.put("gender", gender);
        int total = this.userService.getCount(map);
        //导出量控制
        String max_export = SysParamUtil.getSysParamByKey(Constants.SYSPARAM_PREX + "maxExport",
            Constants.DOMAIN_NAME);
        if (total > Integer.parseInt(max_export)) {
            return "false" + "," + max_export;
        }
        return "true" + "," + max_export;
    }

    /**
     * 导出用户数据
     *
     * @param modelMap
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "expUsers")
    public void expUsers(String username, String account, String userno, String dept, String state,
        String gender, HttpServletRequest request, HttpServletResponse rs) throws Exception {
        BaseVO bv = new BaseVO();
        HashMap<String, Object> map = new HashMap<String, Object>();//封装查询条件
        map.put("username", username);
        map.put("account", account);
        map.put("userno", userno);
        map.put("dept", dept);
        map.put("state", state);
        map.put("gender", gender);
        List<UserDTO> list = this.userService.getList(map); //查询数据结果集
        //添加日志
        AuditLogUtil.addLog(request, "用户管理", "6", "导出", "", "0");
        Map<String, String> statemap = new HashMap<String, String>();//用户状态可能值
        statemap.put("0", "启用");
        statemap.put("1", "停用");
        Map<String, String> gendermap = new HashMap<String, String>();//用户性别可能值
        gendermap.put("1", "男");
        gendermap.put("2", "女");
        Column[] ucolumns = new Column[] {
            new Column("用户姓名", "userName", Column.ENUM_TYPE.VARCHAR2, 10, 2000, false),
            new Column("用户名", "loginAccount", Column.ENUM_TYPE.VARCHAR2, 10, 3000, false),
            new Column("警号", "userNo", Column.ENUM_TYPE.VARCHAR2, 10, 2000, false),
            new Column("状态", "curState", Column.ENUM_TYPE.VARCHAR2, 10, 1300, false, statemap),
            new Column("身份证号码", "idNo", Column.ENUM_TYPE.VARCHAR2, 10, 5000, false),
            new Column("年龄", "age", Column.ENUM_TYPE.VARCHAR2, 10, 1200, false),
            new Column("性别", "gender", Column.ENUM_TYPE.VARCHAR2, 10, 1000, false, gendermap),
            new Column("所在部门", "deptname", Column.ENUM_TYPE.VARCHAR2, 10, 4000, false),
            new Column("拥有角色", "rolenames", Column.ENUM_TYPE.VARCHAR2, 10, 5000, false),
            new Column("联系方式", "mobilePhone", Column.ENUM_TYPE.VARCHAR2, 10, 4000, false),
            new Column("联系地址", "address", Column.ENUM_TYPE.VARCHAR2, 10, 5000, false),
            new Column("邮箱地址", "emial", Column.ENUM_TYPE.VARCHAR2, 10, 5000, false), };
        String reportTitle = "用户信息";
        String filename = new String(reportTitle.getBytes("GBK"), "ISO8859-1") + ".xls";
        HSSFWorkbook wb = new HSSFWorkbook();
        //getExcel 参数：数据集合、导出列、导出文件名和标题名、打印格式（TRUE横向 false 纵向）
        wb = PoiUtil.getExcel(list, ucolumns, reportTitle, true);
        //设置打印区域
        //			int sheetIndex=0;//–从0开始的sheet的索引编号
        //			int startColumn=0;//-打印区域的开始列号
        //			int endColumn=5;//-打印区域的结束列号
        //			int startRow=0;//-打印区域的开始行号
        //			int endRow=5;//-打印区域的结束行号
        //			wb.setPrintArea(sheetIndex,startColumn,endColumn,startRow,endRow);
        if (wb != null) {
            PoiUtil.sendExcel(wb, rs, filename); // 导出文件
        }
    }

    /**
     * 获取导入模板
     *
     * @param filePath request response
     * @return
     */
    @RequestMapping(value = "getModel")
    public void getModel(String filePath, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        String catalina = request.getSession().getServletContext().getRealPath("/");//System.getProperty("user.dir");
        String path = catalina + "excelModel";
        filePath = path + "/用户.xls";
        FileDownloadUtil.download(filePath, "用户.xls", request, response);
    }

    /**
     * 数据导入
     *
     * @param filePath request response
     * @return
     * @throws Exception 
     */
    @ResponseBody
    @RequestMapping(value = "excelImp")
    public String excelImp(String fileid, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        List<Map<String, String>> resultList = null;//解析结果集
        List<Map<String, String>> errorList = null;//解析异常结果集
        String message = "";//成功信息
        String errormessage = "";//错误信息
        //1、将request对象转换成file支持的request对象
        MultipartHttpServletRequest mrequest = (MultipartHttpServletRequest) request;
        MultipartFile file = mrequest.getFile(fileid);//获取上传文件对象
        if (file.isEmpty() && !CommonUtil.isEmpty(file.getOriginalFilename())) {
            message = "文件为空或已被删除！";
        }
        else {
            //2、获取excel文件流对象
            InputStream is = file.getInputStream();
            //3、解析文件
            Map<String, List<Map<String, String>>> resultMap = PoiUtil.imp(is);
            resultList = resultMap.get("resultList");//获取解析结果集
            errorList = resultMap.get("errorList");//获取解析异常结果集
            //导入量控制
            String max_import = SysParamUtil.getSysParamByKey(
                Constants.SYSPARAM_PREX + "maxImport", Constants.DOMAIN_NAME);
            if (resultList.size() > Integer.parseInt(max_import)) {
                message = "导入数据量不能超过" + max_import + "条！";
            }
            else if (null == resultList || resultList.size() < 1) {
                message = "模板中没有要导入的用户信息！";
            }
            else {
                HashMap<String, Object> datamap = this.userService.addUsers(resultList, errorList);
                //获取处理结果
                int count = Integer.parseInt(String.valueOf(datamap.get("count")));
                errorList = (List<Map<String, String>>) datamap.get("errorList");
                //返回结果信息
                if (errorList.size() > 0) {
                    message = "存在不合法数据，导入失败！";
                    AuditLogUtil.addLog(request, "用户管理", "9", message, "", "1");
                }
                else {
                    message = "导入成功！";
                    AuditLogUtil.addLog(request, "用户管理", "9", message, "", "0");
                }
            }
        }
        //添加日志
        response.getWriter().write(
            "{msg:'" + message + "',errormsg:'" + errorList + "',total:'"
                + (null == errorList ? '0' : errorList.size()) + "'}");
        response.getWriter().flush();
        response.getWriter().close();
        return null;
    }

    /**
     * 数据保存
     *
     * @param modelMap
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "save")
    public BaseVO save(UserDTO userManger, BindingResult result, HttpServletRequest request)
        throws Exception {
        BaseVO baseVO = new BaseVO();
        if (result.hasErrors()) {
            baseVO = new BaseVO("comm_001");
            return baseVO;
        }
        User user = (User) request.getSession().getAttribute("loginUser");
        //密码进行MD5加密
        String pwd = userManger.getLoginPwd();
        pwd = MD5Util.getMd5(pwd);
        userManger.setLoginPwd(pwd);
        userManger.setCurState("0");
        userManger.setActByType("b");
        int num = this.userService.save(userManger);
        if (num >= 0) {
            baseVO = new BaseVO("comm_001");
            //添加日志
            AuditLogUtil.addLog(request, "用户管理", "3", "新增", "", "0");
        }
        else {
            baseVO = new BaseVO("comm_002");
            //添加日志
            AuditLogUtil.addLog(request, "用户管理", "3", "新增", "", "1");
        }
        return baseVO;
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "del")
    public BaseVO del(String ids, HttpSession session, HttpServletRequest request) {
        BaseVO baseVO = null;
        String[] idrr = ids.split(",");
        int sum = userService.del(idrr);
        // 删除成功
        if (sum >= 0) {
            baseVO = new BaseVO("comm_001");
            //添加日志
            AuditLogUtil.addLog(request, "用户管理", "5", "删除", "", "0");
            // 删除失败
        }
        else {
            baseVO = new BaseVO("comm_002");
            //添加日志
            AuditLogUtil.addLog(request, "用户管理", "5", "删除", "", "1");
        }
        return baseVO;
    }

    /**
     * id查询用户
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getById")
    public UserDTO getById(String id, HttpSession session) {
        UserDTO user = userService.getById(id);
        return user;
    }

    /**
     * account查询用户
     *
     * @param account
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getByAccount")
    public UserDTO getByAccount(String account, HttpSession session) {
        UserDTO user = userService.getByAccount(account);
        return user;
    }

    /**
     * 编辑用户
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "upd")
    public BaseVO upd(UserDTO userManger, BindingResult result, HttpServletRequest request)
        throws Exception {
        BaseVO baseVO = null;
        User user = (User) request.getSession().getAttribute("loginUser");
        //密码进行MD5加密
        String pwd = userManger.getLoginPwd();
        if (!"".equals(pwd)) {
            pwd = MD5Util.getMd5(pwd);
            userManger.setLoginPwd(pwd);
            userManger.setUpdateBy(user.getUserId().toString());
        }
        int sum = userService.updateByPK(userManger);
        // 删除成功
        if (sum >= 0) {
            baseVO = new BaseVO("comm_001");
            //添加日志
            AuditLogUtil.addLog(request, "用户管理", "4", "用户编辑", userManger.getUserId(), "0");
            // 删除失败
        }
        else {
            baseVO = new BaseVO("comm_002");
            //添加日志
            AuditLogUtil.addLog(request, "用户管理", "4", "用户编辑", userManger.getUserId(), "1");
        }
        return baseVO;
    }

    /**
     * 启用/停用用户
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "upuse")
    public BaseVO upuse(String ids, String useflag, HttpServletRequest request) {
        BaseVO baseVO = null;
        String[] idrr = ids.split(",");
        int sum = userService.upuse(idrr, useflag);
        // 删除成功
        if (sum >= 0) {
            baseVO = new BaseVO("comm_001");
            //添加日志
            AuditLogUtil.addLog(request, "用户管理", "9", "0".equals(useflag) ? "启用" : "停用", ids, "0");
            // 删除失败
        }
        else {
            baseVO = new BaseVO("comm_002");
            //添加日志
            AuditLogUtil.addLog(request, "用户管理", "9", "0".equals(useflag) ? "启用" : "停用", ids, "1");
        }
        return baseVO;
    }

    /**
     * 密码重置
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "resetPw")
    public BaseVO resetPw(String ids, String repw, HttpServletRequest request) throws Exception {
        BaseVO baseVO = null;
        String[] idrr = ids.split(",");
        int sum = userService.resetPw(idrr, repw);
        // 删除成功
        if (sum >= 0) {
            baseVO = new BaseVO("comm_001");
            AuditLogUtil.addLog(request, "用户管理", "9", "密码重置", ids, "0");
            // 删除失败
        }
        else {
            baseVO = new BaseVO("comm_002");
            AuditLogUtil.addLog(request, "用户管理", "9", "密码重置", ids, "1");
        }
        return baseVO;
    }

    /**
     * 用户绑定警种
     */
    @ResponseBody
    @RequestMapping(value = "blindPT")
    public BaseVO blindPT(String ids, String userId, HttpServletRequest request) throws Exception {
        BaseVO baseVO = null;

        int sum = userService.blindPT(ids, userId);
        // 绑定（更新）成功
        if (sum > 0) {
            baseVO = new BaseVO("comm_001");
            AuditLogUtil.addLog(request, "用户管理", "9", "绑定警种", userId, "0");
            // 绑定（更新）失败
        }
        else {
            baseVO = new BaseVO("comm_002");
            AuditLogUtil.addLog(request, "用户管理", "9", "绑定警种", userId, "1");
        }
        return baseVO;
    }
}
