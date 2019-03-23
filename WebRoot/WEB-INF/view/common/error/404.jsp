<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<%@ include file="../common.jsp"%>
<%response.setStatus(200);%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>404 - 找不到页面</title>
</head>

<body>
	<div>
		<br /> <br />
		<center>
			<div align="center">
				<img src="${ctx }/resource/image/404.png" />
			</div>
			<div style="font-family: 华文彩云; font-size: 30px;">您访问的页面不存在！</div>
		</center>
	</div>
</body>
</html>
