<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.Date"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="serverName" value="${pageContext.request.serverName}" />
<c:set var="serverPort" value="${pageContext.request.serverPort}" />
<script type="text/javascript">
    var ctxPath = "${ctx}";
    var serverName = "${serverName}";
    var serverPort = "${serverPort}";
	var pageTo = "${pageTo}";
   // alert(ctxPath);
    var sessionid = '<%=session.getId()%>';
    var sysdate = '<fmt:formatDate value="<%=new Date()%>" pattern="yyyy-MM-dd"/>';
    
    //IE8 下不支持indexOf方法,自行封装兼容IE8
    
/*     if(!Array.prototype.indexOf){    
    	   Array.prototype.indexOf = function(val){    
    	       var value = this;    
    	       for(var i =0; i < value.length; i++){    
    	          if(value[i] == val) return i;    
    	       }    
    	      return -1;    
    	   };    
    	}   */
</script>
<link type="image/x-icon" rel="shortcut icon" href="${ctx}/favicon.ico" />
<link type="image/x-icon" rel="bookmark" href="${ctx}/favicon.ico" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/resource/common/jquery-easyui-1.3.5/metro/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/resource/common/jquery-easyui-1.3.5/metro/linkbutton.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/resource/common/jquery-easyui-1.3.5/icon.css" />
<link rel="stylesheet" type="text/css" 
	href="${ctx}/resource/common/layui/layui-v2.1.3/layui/css/layui.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/resource/style/skynetIcon.css" />
<!-- 自定义样式 -->
<link rel="stylesheet" type="text/css"
	href="${ctx}/resource/style/common.css" />

<script type="text/javascript"
	src="${ctx}/resource/common/jquery.1.8.2.js"></script>
<script type="text/javascript"
	src="${ctx}/resource/common/jquery.easyui.min.js"></script>
<%-- <script type="text/javascript" src="${ctx}/resource/common/jquery.easyui.min.1.5.js"></script> --%>
<script type="text/javascript"
	src="${ctx}/resource/common/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resource/common/validate.js"></script>
<script type="text/javascript"
	src="${ctx}/resource/recordsmng/common/MessageShow.js"></script>
<script type="text/javascript" src="${ctx}/resource/common/common.js"></script>

<script type="text/javascript"
	src="${ctx}/resource/common/easuUI.datagrid.fixnum.js"></script>
	
<script type="text/javascript"
	src="${ctx}/resource/common/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"  
	src="${ctx}/resource/common/layui/layui-v2.1.3/layui/layui.all.js" charset="utf-8"></script>
