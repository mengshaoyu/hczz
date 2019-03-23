<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<%@ include file="../common.jsp"%>
<%response.setStatus(200);%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>500 - 系统内部错误</title>
</head>
<!-- script type="text/javascript">
	   parent.window.location.href="${ctx}/index/doLogOut";
	</script -->
<body>
	<div>
		<br /> <br />
		<center>
			<div align="center">
				<img src="${ctx}/resource/image/500.png" />
			</div>
			<div style="font-family: 华文彩云; font-size: 30px;">
				${msgInfo} <a style="color: #red;" id="lnkLogout"
					href="${ctx}/login/goOut">退出</a>
			</div>
			<c:if test="${errorInfo != null}">
				<div style="font-family: 宋体; font-size: 20px; color: red">
					<br /> 错误信息：${errorInfo}
				</div>
			</c:if>

		</center>
	</div>
</body>
<script type="text/javascript">
	 	var msg = '${msgInfo}';
	 	var msgCode = '${msgCode}';
	 	if(msgCode=="SYS_101"){
	 		showMessage(msg);
			parent.location.href=ctxPath+"/login/goOut";
		}
</script>
</html>
