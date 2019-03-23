package com.shield.frame.index.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shield.frame.base.domain.User;
import com.shield.frame.common.Constants;
import com.shield.frame.common.qvo.BaseVO;
import com.shield.frame.index.dto.LoginDTO;
import com.shield.frame.index.qvo.LoginVO;
import com.shield.frame.index.service.LoginService;
import com.shield.frame.sysmng.qvo.MenuTreeNode;
import com.shield.frame.sysmng.qvo.RootMenuVO;
import com.shield.frame.sysmng.qvo.ToolBarVO;
import com.shield.frame.sysmng.service.CodeValueService;
import com.shield.frame.sysmng.service.MenuService;
import com.shield.frame.utils.CommonUtil;
import com.shield.frame.utils.DateUtil;
import com.shield.frame.utils.JsonUtil;
import com.shield.hczz.index.dao.UserModel;
import com.shield.hczz.index.mapper.IndexMapper;
import com.shield.spring.AutoLog;

/**
 * 登录页面对应的controller
 *
 */
@Controller
@RequestMapping(value = "/login/*")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private CodeValueService codeValueService;
    
    @Autowired
    private IndexMapper indexMapper;

    /**
     * 验证用户登录信息
     * 
     * @param loginDTO
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "checkUsr", method = RequestMethod.POST)
    @AutoLog(desc = "验证用户登录信息")
    public LoginVO checkUsr(@Valid LoginDTO loginDTO, BindingResult result,
        HttpServletRequest request, HttpServletResponse response) {
        LoginVO loginVo = null;

        if (result.hasErrors()) {
            loginVo = new LoginVO();
            loginVo.setMsgCode("comm_101");

            return loginVo;
        }
        else {

            return loginService.checkUsr(loginDTO, request, response);
        }
    }

    /**
     * 根据登录时选择的系统模块，进入相应的系统
     *
     * @param sys 系统标识
     * @return
     */
    @RequestMapping(value = "login")
    public String login(ModelMap modelMap, HttpServletRequest request) {
        if (StringUtils.isNotEmpty(request.getParameter(""))) {

        }
        return "login";
    }

    /**
     * 根据登录时选择的系统模块，进入相应的系统
     *
     * @param sys 系统标识
     * @return
     */
    @RequestMapping(value = "gotoSys")
    public String gotoSys() {
        // 主界面首頁
        return "redirect:/login/init";
    }

    /**
     * 主界面初始化
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "init")
    public String init(@CookieValue(value = "pageTo", required = false) String pageTo,
        ModelMap modelMap, HttpServletRequest request, HttpServletResponse rs) {

        User usr = (User) request.getSession().getAttribute("loginUser");
        //添加用户deptName
        UserModel userModel = new UserModel();
        	userModel.setUserId(usr.getUserId().intValue());
        UserModel model = indexMapper.select(userModel);
        usr.setDeptName(model.getDeptName());

        //用于处理admin获取所有菜单 
        BigDecimal userID = null;
        if (!"admin".equals(usr.getLoginAccount())) {
            userID = usr.getUserId();
        }

        modelMap.addAttribute("usrName", usr.getUserName());
        modelMap.addAttribute("deptName",usr.getDeptName());
        modelMap.addAttribute("currentTime", DateUtil.getDateToStr(new Date(), "yyyy-MM-dd HH:mm"));

        List<RootMenuVO> rootMenuList = menuService.getRootMenuList(userID);
        
        modelMap.put("rootMenuList", rootMenuList);
        modelMap.put("rootMenuListTemp", JsonUtil.writeValueAsString(rootMenuList));

        // 可打开面签的总数
        modelMap.put("tabTotal", 10);

        // 直接传参数将跳转
        String menuId = request.getParameter("pageTo");
        if (StringUtils.isNotBlank(menuId)) {
            pageTo = menuId;
        }

        // 执行跳转
        if (StringUtils.isNotBlank(pageTo) && !"null".equals(pageTo)) {
            Cookie cookie = new Cookie("pageTo", "xxx");
            cookie.setPath("/");
            cookie.setMaxAge(0);
            rs.addCookie(cookie);
            modelMap.put("pageTo", pageTo);
        }
        return "index/newIndex";
    }

    @ResponseBody
    @RequestMapping(value = "getMenuTreeNodeList")
    public List<MenuTreeNode> getMenuTreeNodeList(String itemId, HttpSession session) {
        String loginAccount = (String) session.getAttribute(Constants.SESN_USR_ACOUNT);
        //管理员获取所有模块菜单
        if ("admin".equals(loginAccount)) {
            return menuService.getMenuTreeNodeList(itemId, null);
        }
        return menuService.getMenuTreeNodeList(itemId,
            (BigDecimal) session.getAttribute(Constants.SESN_USR_UID));
    }

    /**
     * 根据页面ID取得用户在此页面上的toolBar
     *
     * @param page 菜单表中此页面的主键编号
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getToolBar")
    public List<ToolBarVO> getToolBar(BigDecimal page, HttpSession session) {

        //管理员则获取所有Toolbar
        String loginAccount = (String) session.getAttribute(Constants.SESN_USR_ACOUNT);
        if ("admin".equals(loginAccount)) {
            return menuService.getToolBar(page, null);
        }
        return menuService.getToolBar(page,
            (BigDecimal) session.getAttribute(Constants.SESN_USR_UID));
    }
    
    /**
     * 退出系统
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "goOut")
    public String goOut(HttpServletRequest request, HttpServletResponse rs) {
        String pageTo = String.valueOf(request.getAttribute("pageTo"));
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        if (StringUtils.isNotBlank(pageTo) && !"null".equals(pageTo)) { // 缓存登录成功后跳转的页面
            Cookie cookie = new Cookie("pageTo", pageTo);
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60 * 24 * 365 * 10);
            rs.addCookie(cookie);
        }
        return "redirect:/";
    }

    /**
     * 更新用户的密码
     *
     * @param oldPwd 原密码
     * @param newPwd  新密码
     * @param newPwd2 确认新密码
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updatePwd")
    public BaseVO updatePwd(String oldPwd, String newPwd, String newPwd2, HttpServletRequest request) {
        BigDecimal usrId = (BigDecimal) request.getSession().getAttribute(Constants.SESN_USR_UID);
        int num = loginService.updatePwd(oldPwd, newPwd, usrId);

        if (num < 1) {
            return new BaseVO("index_005");
        }

        return new BaseVO("comm_001");
    }

    /**
     * 根据typeId获取码值，用作tabs标签
     * @param typeId
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("getRecordsTabs")
    public HashMap<String, Object> getRecordsTabs(String typeId, HttpServletRequest request) {
        HashMap<String, Object> dataMap = new HashMap<String, Object>();
        List<HashMap<String, Object>> list = this.codeValueService.getCodeValueMap(typeId);
        dataMap.put("list", list);
        return dataMap;
    }

    @ResponseBody
    @RequestMapping("getUUID")
    public String getUUID(String module, HttpServletRequest request) {
        return CommonUtil.getUUID();
    }

    /**
     * <b>功能：跳转任意页面</b><br>
     * <br>
     * @param id
     * @param url
     * @param request
     * @return String
     **/
    @RequestMapping(value = "redirect")
    public String redirect(String id, String url, HttpServletRequest request) {

        Enumeration<String> enume = request.getParameterNames();
        String key = "";
        while (enume.hasMoreElements()) {
            key = enume.nextElement().toString();
            request.setAttribute(key, request.getParameter(key));
        }

        return url;
    }
}
