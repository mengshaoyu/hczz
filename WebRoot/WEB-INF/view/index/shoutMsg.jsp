<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
		function sub(){
			$.ajax({
	        type: "POST",
	        url:"<%=request.getContextPath()%>/index/sendMessage",
	        data: {
	        		mobiles:$("#tell").val(),
	        		content:$("#content").val()
	        	  },
	     	dataType: "json",
	        success: function(data) {
	        	alert(JSON.stringify(data)); //可以将json对象转换成json对符串 
	        }
	        });
		}
	</script>
  </head>
  
  <body>
   		手机号:<input type="text" id="tell" placeholder="支持多手机号，请用英文,隔开" style="width: 400px">
		短信内容:<input type="text" id="content" style="width: 400px">
		<input type="button" value="发送" onclick="sub()">
  </body>
</html>
