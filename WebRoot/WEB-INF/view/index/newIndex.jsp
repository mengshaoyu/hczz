<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta content="text/html; charset=utf-8" http-equiv="Content-Type"/>
<title>泗洪县公安局合成作战平台</title>
<link rel="stylesheet" type="text/css"
	href="${ctx}/resource/style/sop/index.css" />
<script type="text/javascript" src="${ctx}/resource/index/newIndex.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/resource/css/index2.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resource/common/ui-dialog.css" />
<script src="//api.html5media.info/1.1.8/html5media.min.js"></script>
<script type="text/javascript">
	var roles = '${roles}';
	var ctx="${ctx}";
</script>
</head>
<body>
	<div class="header-nav">
		<div class="nav-logo">
			<i><img src="${ctx}/resource/image/index/logo.png" /></i>
			<h1><a href="../login/init">泗洪县公安局合成作战平台</a></h1>
		</div>
		<!-- 一级菜单 -->
		<div class="first-menu">
			<ul class="first-list">
				<li class="top-nav-active"><span>首页</span></li>
		</div>
		
		<div class="header-user">
			<div class="head_notice">
				<!-- 声音报警 -->
				<div style="display:none" >
					<audio  id='warningaudio' src="${ctx}/resource/bgm/alarm.wav" type='audio/wav' autostart='false'  hidden='true' ></audio>
					<bgsound id='warningaudio1' hidden="true" autostart='false'>
				</div>
				<div class="notice_box" id="notice_2" title="待办任务提醒" onclick="goTodos()">
					<input type="hidden" id="hiddenUnFinsh" value=""/>
					<i><img src="${ctx}/resource/image/index/dingding.png"></i><label id="notice_count">0</label>
				</div>
			</div>
			<div class="head_right">
				<span>您好，
					<a id="lnkPwd" href="#" class="user-name" onclick="showHidden()">
						${usrName}&nbsp;<img src="${ctx}/resource/image/index/down-white.png" />
					</a> &nbsp;&nbsp;&nbsp;${deptName}
					<div id="hiddenDiv" class="hiddenDiv" style="display: none">
						<ul id="hiddenUl">
						    <li>账号管理</li>
						    <li>修改密码</li>
						    <li>常见问题</li>
						 <!--    <li>改进意见</li> -->
						</ul>
					</div>
					<a id="lnkLogout" class="a-quit" href="javascript:void(0);" title="注销" onclick="logout()">
						<img src="${ctx}/resource/image/index/out.png" style="padding-left:10px;" />
					</a>
				</span>
			</div>
		</div>
	</div>
	<!-- 二级菜单 -->
	<div class="nav">
		<ul id="nav-list">
		</ul>
	</div>

	<div class="container">
		<!-- 内容iFrame -->
		<div class="content">
			<div class="content-in">
				<iframe frameborder="no" scrolling="yes" src="${ctx}/newIndex/indexFrame" width="100%" height="100%" id="contentFrame"></iframe>
			</div>
		</div>
	</div>
	<!-- 用户相关信息 -->
	<div id="dd" class="easyui-dialog" title="用户信息" style="width:723px;height:400px;" data-options="resizable:true,modal:true" closed="true">
			<div class="per_information">
				<div class="td_01">登录账号：</div>
					<div class="td_02">
						<input type="text" name="loginAccount" id="loginAccount" value="" disabled="disabled">
					</div>
			</div>
			<div class="per_information">
				<div class="td_01">姓名：</div>
					<div class="td_02">
						<input  type="text" id="userName" name="userName" value="" maxlength="5">
					</div>
				<div class="td_01">警号：</div>
					<div class="td_02">
						<input type="text" id="userNo" name="userNo" value="" disabled="disabled">
					</div>
				<div class="td_01">性别：</div>
					<div class="td_04">
						<input  type="text" id="gender" name="gender" value="" maxlength="1">
					</div>
			</div>
			<div class="per_information">
				<div class="td_01">身份证号：</div>
					<div class="td_02">
						<input type="text" id="idNo" name="idNo" value="" maxlength="18">
					</div>
				<div class="td_01">部门：</div>
					<div class="td_04">
						<input type="text" id="deptName" name="deptName" value="" disabled="disabled">
					</div>
			</div>
			<div class="per_information">
				<div class="td_01">年龄：</div>
					<div class="td_02">
						<input type="text" id="age" name="age" value="" maxlength="3">
					</div>
				<div class="td_01">手机号：</div>
					<div class="td_02">
						<input type="text" id="mobilePhone" name="mobilePhone" value="" maxlength="20">
					</div>
			</div>
			<div class="per_information">
				<div class="td_01">电子邮箱：</div>
					<div class="td_02">
						<input type="text" id="emial" name="emial" value="" maxlength="30">
					</div>
			</div>
			<div class="per_information">
				<div class="td_01">联系地址：</div>
					<div class="td_05">
						<input type="text" id="address" name="address" value=""  style="width: 300px;" />
					</div>
			</div>
			<br/>
			<div class="oni-dialog-footer oni-helper-clearfix" >
				<div class="oni-dialog-btns" >
					<input class="tg_laber_btn" onclick="save()" type="button" value="保存" />
					<input class="tg_laber_btn" onclick="closeUser()" type="button" value="关闭" />
				</div>
			</div>
	</div>
	<!-- 密码修改 -->
	<div id="dd2" class="easyui-dialog" title="密码修改" style="width:310px;height:260px;overflow: hidden;" data-options="resizable:true,modal:true" closed="true">
			<div class="per_information"  >
				<div class="td_01">原密码：</div>
					<div class="td_05">
						<input type="text" id="oldLoginPwd" >
					</div>
			</div>
			<div class="per_information"  >
				<div class="td_01">新密码：</div>
					<div class="td_05">
						<input type="text" id="loginPwd" name="loginPwd">
					</div>
			</div>
			<div class="per_information"  >
				<div class="td_01">确认密码：</div>
					<div class="td_05">
						<input type="text" id="loginPwd1">
					</div>
			</div>
				<div class="oni-dialog-footer oni-helper-clearfix" >
				<div class="oni-dialog-btns" >
					<input class="tg_laber_btn" onclick="editPwd()" type="button" value="修改密码">
					<input class="tg_laber_btn" onclick="closePwd()" type="button" value="取消">
				</div>
			</div>
			</div>
	</div>
</body>
</html>
