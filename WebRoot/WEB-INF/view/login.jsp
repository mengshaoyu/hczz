<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="common/common.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8">
<title>泗洪县公安局合成作战平台</title>
<link rel="stylesheet" type="text/css"
	href="${ctx}/resource/style/login.css" />
<script type="text/javascript" src="${ctx}/resource/index/login.js"></script>
<script type="text/javascript">
   		$(function(){
			//判断当前登录窗口是否是内嵌窗口，是则跳转到顶级窗口
	  		if(window.parent.$("iframe").length>0){
  				parent.top.location= ctxPath+'/login/goOut';
	  		}
   		})
		 function goEneter() {
            if (event.keyCode == 13) {
                document.getElementById("pwdBtn").click();
              	return false;
            }
        }
	</script>
</head>
<body onkeypress="goEneter()">
	<div class="login-bg">
	</div>
	<div class="container">
		<div class="login-body-logo">
				<h1>泗洪县公安局合成作战平台</h1>
				<h4>Synthetic Operational Platform of Sihong County Public Security Burea</h4>
		</div>
		<div class="login-box">
			<div class="login-box-title">
				<p>
					<span style="float:left;">Login/登录</span>
					<span style="float:right;"><a href="${ctx}/resource/help/help.html" target="_blank">常见问题</a></span>
				</p>
			</div>
			<div id="accountLogin">
				<input type="text" id="account" name="account" required="required"
					onfocus="onfocus" placeholder="请输入用户名"> <input class="inp1"
					id="passwd" name="passwd" type="password" required="required"
					placeholder="请输入密码">
				<p class="notice" id="msg">&nbsp;</p>
			</div>
			<button id="CARBtn">
				<span>证书登录</span>
				<input type="hidden" id="RootCADN" value=""/>
			</button>
			<button id="pwdBtn">
				<span>账号登录</span>
			</button>
		</div>
	</div>
	<div class="login-footer">
		<p>技术支持电话：025-58821826</p>
	</div>
	<object classid="CLSID:707C7D52-85A8-4584-8954-573EFCE77488" id="JITDSignOcx" width="0" codebase="${ctx}/resource/JITDSign.cab#version=2,0,24,19"></object>
</body>
</html>
