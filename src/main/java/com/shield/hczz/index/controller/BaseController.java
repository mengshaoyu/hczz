package com.shield.hczz.index.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import com.shield.frame.base.domain.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;


/**
 * @ClassName: 基本控制类 (BaseController.java)
 * @Description: 封装提供一些在控制层中常用的方法
 */
@Controller
public class BaseController {

	private static final Log logger = LogFactory.getLog(BaseController.class);

	public BaseController() {
	}

	/**
	 * 向前台发送字符串
	 */
	protected static void flushResponse(HttpServletResponse response,String responseContent) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(responseContent);
		} catch (IOException e) {
			logger.error(e);
		} finally {
			writer.flush();
			writer.close();
		}
	}

	/**
	 * 向前台发送json格式字符串
	 */
	protected static void flushResponseMsg(HttpServletResponse response,boolean success, String msg) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("success", success);
		jsonObj.put("msg", msg);
		String jsonStr = jsonObj.toString();
		flushResponse(response, jsonStr);
	}

	/**
	 * 获取表单提交的name属性，封装承map
	 */
	@SuppressWarnings("rawtypes")
	protected static Map<String, Object> getParamValues(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Enumeration names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String key = (String) names.nextElement();
			String value = request.getParameter(key);
			if (value != null && value != "") {
				map.put(key, value);
			}
		}
		return map;
	}

	/**
	 * 从session中取user对象信息
	 */
	protected User getUser(HttpServletRequest request) {
		return (User) request.getSession().getAttribute("loginUser");
	}
	
}
