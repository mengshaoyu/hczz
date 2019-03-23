package com.shield.frame.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.shield.frame.common.qvo.BaseVO;
import com.shield.frame.utils.AjaxUtil;

/**
 * 统一异常处理类
 */
public class CommonHandlerExceptionResolver implements HandlerExceptionResolver {

    private static final Logger log = LoggerFactory.getLogger(CommonHandlerExceptionResolver.class);

    /**
     * 共通异常处理
     * 
     * @param  request HttpServletRequest
     * @param  response HttpServletResponse
     * @param  handler 
     * @param  e 系统抛出的异常 
     * 
     * @return ModelAndView spring的ModelAndView
     */
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
        Object handler, Exception e) {

        log.error("系统异常！", e);

        StringBuilder path = new StringBuilder();
        path.append("请求路径：").append("http://").append(request.getServerName()).append(":")
            .append(request.getServerPort()).append(request.getContextPath())
            .append(request.getServletPath());

        StringBuilder requestContent = new StringBuilder();
        requestContent.append("请求参数:");

        Enumeration enume = request.getParameterNames();
        Object key = null;
        String[] values;
        while (enume.hasMoreElements()) {
            key = enume.nextElement();
            values = request.getParameterValues(key.toString());
            for (String value : values) {
                requestContent.append(",").append(key).append(":").append(value);
            }
        }
        if (requestContent.length() > 5 && requestContent.charAt(5) == ',') {
            requestContent.deleteCharAt(5);
        }

        // StringBuilder parameterMapStr = new StringBuilder();
        // Map parameterMap = request.getParameterMap();
        // for(Object key : parameterMap.keySet()){
        // parameterMapStr.append(key.toString()).append(":").append(parameterMap.get(key).toString());
        // }
        // requestContent.append(parameterMapStr);
        BaseVO baseVO = new BaseVO("SYS_102");
        if (AjaxUtil.isAjaxRequest(request)) {
            try {
                response.setContentType("application/json");
                response.setStatus(200);
                PrintWriter out = response.getWriter();
                out.print("{\"msgCode\":\"SYS_102\", \"msgInfo\":\"" + baseVO.getMsgInfo()
                    + "\",\"total\":0,\"rows\":[]}");
                out.flush();
                out.close();
            }
            catch (IOException e1) {
                log.error("记录系统异常时出现异常！", e1);
            }
            return null;
        }
        else {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("msgInfo", baseVO.getMsgInfo());
            map.put("msgCode", "SYS_102");
            map.put("errorInfo", e.getMessage());
            map.put("path", path);
            map.put("requestContent", requestContent);
            return new ModelAndView("common/error/500", map);
        }
    }
}
