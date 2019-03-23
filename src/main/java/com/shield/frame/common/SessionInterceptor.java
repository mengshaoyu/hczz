package com.shield.frame.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.shield.frame.base.domain.Function;
import com.shield.frame.base.domain.Role;
import com.shield.frame.base.domain.RoleDataAuth;
import com.shield.frame.base.domain.User;
import com.shield.frame.sysmng.service.RoleService;
import com.shield.frame.utils.AjaxUtil;
import com.shield.frame.utils.DataAuthUtil;
import com.shield.frame.utils.LocalUtil;
import com.shield.frame.utils.SylogicCacheUtil;

@ContextConfiguration(locations = { "classpath:config.properties" })
public class SessionInterceptor extends HandlerInterceptorAdapter {
    static final Logger log = LoggerFactory.getLogger(SessionInterceptor.class);

    @Autowired
    private RoleService roleService;

    @Value("#{propertyConfig['server.first.entry.url']}")
    private String entryURl;

    @Value("#{propertyConfig['dataauth.cfg.child.dept']}")
    private String childDeptFlag;

    //用户拥有多角色、且功能被授予多角色时，若某角色未设置数据权限过滤，提供系统开关来控制取所有不控制还是当成未设置处理，默认Y当成所有不控制处理
    @Value("#{propertyConfig['dataauth.cfg.dont.contr']}")
    private String contrFlag;

    private static final String PASSPATH = "login/init";

    /**
     * 处理session过期
     * <br> 
     * 如果是Ajax请求，那么返回Json格式：{"msgInfo" : "会话超时！"}
     * 否则跳转到500页面，提示"会话超时！"
     * 
     * @param  request HttpServletRequest
     * @param  response HttpServletResponse
     * @param  handler 处理
     * 
     * @return boolean true继续进入controller，false直接返回
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {
        // 测试工程名是什么？
        String accessURl = request.getRequestURI().replace(request.getContextPath() + "/", "");
        DataAuthUtil.destory();

        if ("login/goOut".equals(accessURl) || "remind/timing".equals(accessURl)
            || "caLogin/createCARandom".equals(accessURl)
            || "caLogin/caLoginCheck".equals(accessURl) || "remind/timing2".equals(accessURl)
            || "login/login".equals(accessURl)) {//注销请求定时任务不拦截
            return true;
        }
        // 初次进入系统，且缓存不存在非功能URL，则把非功能URL加载进缓存
        if (accessURl.equals(entryURl)) {
            if (SylogicCacheUtil.getFromCache(Constants.ANTI_INTERCEPT_URL) == null) {

                // 取得非功能URl
                ResourceBundle antiInterceptURL = ResourceBundle.getBundle("antiIntercept");

                // 加入缓存
                SylogicCacheUtil.putInCache(Constants.ANTI_INTERCEPT_URL, antiInterceptURL);
            }

            // 初次进入后台，不拦截
            return true;
        }

        // 验证结果 0:无验证错误 1:session过期 2:无权限
        int errorFlg = 0;
        HttpSession session = request.getSession(false);
        //session=null ;
        // 验证session
        if (session == null || session.getAttribute(Constants.SESN_USR_ACOUNT) == null) {
            errorFlg = 1;
        }
        else {
            //设置本地UserID到threadlocal中
            User usr = (User) request.getSession().getAttribute("loginUser");
            if (null == usr || "".equals(usr.getUserId())) {
                LocalUtil.setUserId("1"); //null时默认使用1-admin
            }
            else {
                LocalUtil.setUserId(usr.getUserId().toString());
            }

            ResourceBundle antiInterceptURL = (ResourceBundle) SylogicCacheUtil
                .getFromCache(Constants.ANTI_INTERCEPT_URL);
            // 非功能URL或者当前登录者为超级管理员用户，直接通过拦截
            if (antiInterceptURL.containsKey(accessURl.replace("/", "."))
                || "admin".equalsIgnoreCase((String) session
                    .getAttribute(Constants.SESN_USR_ACOUNT))) {
                String pageTo = request.getParameter("pageTo");
                if (StringUtils.isNotBlank(pageTo) && PASSPATH.equals(accessURl)) {
                    Cookie cookie = new Cookie("pageTo", pageTo);
                    cookie.setPath("/");
                    cookie.setMaxAge(60 * 60 * 24 * 365 * 10);
                    response.addCookie(cookie);
                }
                return true;
            }

            // 缓存中存在功能URL，则直接进行验证；如果不存在功能URl，则先从DB取得功能URL并放入缓存，再进行拦截验证
            List<Role> usrRoles = usr.getRoleList();
            if (usrRoles == null || usrRoles.size() == 0) {
                errorFlg = 2;
            }
            else if (!checkFunctionURL(accessURl.replace("/", "."), usrRoles, usr)) {
                // 不是功能URl
                errorFlg = 2;
            }
        }

        // 依据验证结果，进行返回处理
        if (errorFlg != 0) {
            if (AjaxUtil.isAjaxRequest(request)) {
                log.error("会话超时，请重新登录！  >>----------------------------------> " + accessURl);
                String resultstr = "{\"msgCode\":\"SYS_101\", \"msgInfo\":\"\",\"total\":0,\"rows\":[]}";
                response.setStatus(200);
                response.setHeader("sessionstatus", "timeout");
                response.setHeader("headerresult", resultstr);
                //                response.setStatus(200);
                //                PrintWriter pw = response.getWriter();
                //                pw.print("{\"msgCode\":\"SYS_101\", \"msgInfo\":\"会话超时，请重新登录！\",\"total\":0,\"rows\":[]}");
                //                pw.flush();
                //                pw.close();
            }
            else {
                log.error("会话超时，请重新登录！  >>----------------------------------> " + accessURl);
                // 从客户端跳转单独放开
                String pageTo = request.getParameter("pageTo");
                request.setAttribute("msgInfo", "会话超时，请重新登录！");
                request.setAttribute("msgCode", "SYS_101");
                if (StringUtils.isNotBlank(pageTo)) {
                    Cookie cookie = new Cookie("pageTo", pageTo);
                    cookie.setPath("/");
                    cookie.setMaxAge(60 * 60 * 24 * 365 * 10);
                    response.addCookie(cookie);
                    request.getRequestDispatcher("goOut").forward(request, response);
                }
                else {
                    request.getRequestDispatcher("/WEB-INF/view/common/error/500.jsp").forward(
                        request, response);
                }
            }
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * 验证当前的URL是功能URL
     *
     * @param URl 当前URL
     * @param usrRoles 用户角色列表
     * @return true:是  false:否
     */
    private boolean checkFunctionURL(String URl, List<Role> usrRoles, User sessionUsr) {
        boolean isFuncURL = false;
        HashMap<Object, Object> hashMap = null;

        for (Role role : usrRoles) {
            String roleId = role.getRoleId().toString();
            hashMap = (HashMap<Object, Object>) SylogicCacheUtil
                .getFromCache(Constants.INTERCEPT_URL_PREX + roleId);

            // 如果不存在此角色id的内容，则从DB取得并存入缓存
            if (hashMap == null || hashMap.isEmpty()) {
                hashMap = roleService.getRoleFunction(roleId);
                //将角色数据权限信息纳入缓存中
                hashMap.put(Constants.DATA_AUTH_PREX + roleId, roleService.getRoleDataAuth(roleId));
                SylogicCacheUtil.putInCache(Constants.INTERCEPT_URL_PREX + roleId, hashMap);
            }

            // 验证缓存中此角色id下是否包含当前访问的URL
            if (hashMap != null && !hashMap.isEmpty() && hashMap.containsKey(URl)) {
                //判断是否需要数据权限过滤
                Function funObj = (Function) hashMap.get(URl);
                if (null != funObj.getHasaudata() && "Y".equals(funObj.getHasaudata())) {
                    //调用解析数据权限方法
                    analysisDataAuth(URl, usrRoles, sessionUsr);
                }
                // 如果包含，则返回true，并退出循环
                isFuncURL = true;
                break;
            }
        }

        return isFuncURL;
    }

    /**
     * 根据访问URL,解析数据权限
     * @param URl
     * @param usrRoles
     */
    private void analysisDataAuth(String URl, List<Role> usrRoles, User sessionUsr) {
        HashMap<Object, Object> hashMap = null;
        StringBuffer sb = new StringBuffer("");
        int i = 0; //用户具有多角色时记数标示

        for (Role role : usrRoles) {
            String roleId = role.getRoleId().toString();
            hashMap = (HashMap<Object, Object>) SylogicCacheUtil
                .getFromCache(Constants.INTERCEPT_URL_PREX + roleId);
            // 如果不存在此角色id的内容，则从DB取得并存入缓存
            if (hashMap == null || hashMap.isEmpty()) {
                hashMap = roleService.getRoleFunction(roleId);
                //将角色数据权限信息纳入缓存中
                hashMap.put(Constants.DATA_AUTH_PREX + roleId, roleService.getRoleDataAuth(roleId));
                SylogicCacheUtil.putInCache(Constants.INTERCEPT_URL_PREX + roleId, hashMap);
            }

            if (hashMap.containsKey(URl)) {
                RoleDataAuth authObj = (RoleDataAuth) hashMap
                    .get(Constants.DATA_AUTH_PREX + roleId);
                if (null != authObj) {
                    sb.append(authObj.getAuthType()).append(",");
                    i++;
                }
                else {
                    //未设置时,根据控制开关设置
                    if ("Y".equals(contrFlag)) {
                        sb.append("1,"); //所有不控制	
                        i++;
                    }
                }
            }
        }

        Map authMap = new HashMap<String, String>();
        //未设置数据权限,即不控制
        if ("".equals(sb.toString()))
            return;

        //用户拥有多角色、且功能被授予多角色时
        if (i > 1) {
            //所有不控制
            if (sb.toString().indexOf("1") > -1) {
                return;
            }
            //部门权限
            if (sb.toString().indexOf("2") > -1) {
                if ("N".equals(childDeptFlag)) {
                    authMap.put(Constants.DATAAUTH_D, sessionUsr.getDeptId());
                }
                else {
                    authMap.put(Constants.DATAAUTH_D, sessionUsr.getChildDepts());
                }
                DataAuthUtil.setDataAuth(authMap);
                return;
            }

            //人员权限
            if (sb.toString().indexOf("3") > -1) {
                authMap.put(Constants.DATAAUTH_U, sessionUsr.getUserId().toString());
                DataAuthUtil.setDataAuth(authMap);
            }
        }
        else {
            //所有不控制
            if (sb.toString().indexOf("1") > -1) {
                return;
            }

            //部门权限
            if (sb.toString().indexOf("2") > -1) {
                if ("N".equals(childDeptFlag)) {
                    authMap.put(Constants.DATAAUTH_D, sessionUsr.getDeptId());
                }
                else {
                    authMap.put(Constants.DATAAUTH_D, sessionUsr.getChildDepts());
                }
            }

            //人员权限
            if (sb.toString().indexOf("3") > -1) {
                authMap.put(Constants.DATAAUTH_U, sessionUsr.getUserId().toString());
            }
            DataAuthUtil.setDataAuth(authMap);
        }
    }
}
