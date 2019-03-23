<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>My JSP 'addnews.jsp' starting page</title>

</head>

<body>
	<form enctype="multipart/form-data"
		action="<c:url value="/news/upload/" />" method="post">

		<input type="text" name="title" id="title" width="300px" /><br>
		<br>



		<%@ include file="upload.jsp"%>
		<br> <br> <input type="submit" value="上传" />
	</form>
</body>
</html>
