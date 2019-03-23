<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>评价核实 - 合成作战平台</title>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<meta name="generator" content="MSHTML 8.00.7600.16588">

<script type="text/javascript"
	src="${ctx}/resource/common/jquery.form.js"></script>
<script type="text/javascript"
	src="${ctx}/resource/recordsmng/common/configService.js"></script>
<script type="text/javascript" src="${ctx}/resource/apply/evaluate.js"></script>
<!-- <link rel="stylesheet" type="text/css" href="http://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css" /> -->
<link rel="stylesheet" type="text/css" href="${ctx}/resource/css/ionicons.min.css" />

<script type="text/javascript">
	window.onload = function() {
		$('#loading-mask').fadeOut();//页面初始加载，覆盖层
	}
	
	var pzApplyId='${pzApplyId}';
	
</script>
<style type="text/css">
.ld,.pj,.pic {
	height:30px;
	padding-left:10px;
	}
.pj-textarea{
	width:380px;
	padding:5px;
	border:0 none;
}	
.t-table {
	margin: 0;
	padding: 0;
}

.t-table {
	border-top: 1px solid #ccc;
	border-left: 1px solid #ccc;
	margin:10px auto 0 auto;
}

.t-table td,
.t-table th {
	border-bottom: 1px solid #ccc;
	border-right: 1px solid #ccc;
}

.t-table .t-nav {
	padding: 25px 15px;
	background-color: #efefef;
}

.t-table .t-body {
	padding: 5px 10px;
	color: #848484;
}

.t-table .t-time {
	display: inline-block;
	position: relative;
	color: #81d8d0;
	font-size: 26px;
	vertical-align: middle;
	margin-right: 6px;
}

.t-table span {
	vertical-align: middle;
}

.t-table .t-bage {
	position: absolute;
	top: 0px;
	right: -8px;
	height: 16px;
	width: 16px;
	font-size: 12px;
	background-color: #269fd6;
	color: white;
	line-height: 16px;
	text-align: center;
}

.t-table .t-check {
	padding: 0;
	margin: 0;
}

.t-table .t-check>li {
	padding: 0;
	margin: 0;
	list-style-type: none;
	float: left;
	text-align: center;
	margin: 0 15px;
}

.t-table .t-check>li:last-child::after {
	content: '';
	display: block;
	clear: all;
}
.t-table .t-check>li label{
	display: block;
}
.t-table .radios{
	display: none;
}
.t-table .t-yuandian{
	height: 16px;
	width: 16px;
	display: inline-block;
	border-radius: 100%;
	border: 1px solid #81d8d0;
	margin-top: 5px;
	padding: 3px;
	box-sizing: border-box;
	position: relative;
}
.t-table .radios:checked~.t-yuandian:after{
	content: '';
	display: block;
	height: 8px;
	width: 8px;
	border-radius: 100%;
	top: 0px;
	left: 0px;
	background-color:  #81d8d0;
}
.LinkUl { 
	float:left; 
}
	</style>
</head>
<body
	style="padding: 0px; margin: 0px; border: 0px; background-color: #FFF;">

	<!-- 等待加载 遮罩层 -->
	<div id="loading-mask"
		style="position: absolute; top: 0px; left: 0px; width: 100%; height: 100%; background: #F4F4F4; z-index: 20000">
		<div id="pageloading"
			style="width: 200px; height: 15px; position: absolute; top: 50%; left: 50%; margin: -20px 0px 0px -120px; text-align: center; border: 2px solid #ccc; font-size: 14px; padding: 10px; background: white;">
			<img style="vertical-align: middle; float: left;"
				src="../resource/image/loading.gif">页面加载中，请稍后...
		</div>
	</div>
	<%-- <form id="uf" method="post" style="padding: 0px; margin: 0px;">
		<div class="box">
			<div class="box_left">
				<fieldset class="bord" style="border:0 none;">
					<div class="char1">
						<div class="ld">核实情况：</div>
						<textarea class="pj-textarea" id="backDesc" name="backDesc" maxlength="150" title="最多输入150个字符"
							class="tex1"></textarea>
					</div>
					<div class="star">
						<div class="pj">评价：</div>
						<div class="con"></div>
						<div class="pic">
							<span>反馈实效&nbsp;&nbsp;&nbsp;&nbsp;</span> <input type="hidden" id="feedBackTime"
								value="${applyBack.flowTime}" />
							<c:choose>
								<c:when test="${applyBack.timeDiff>0}">
									<img src="../resource/image/apply/no.png">&nbsp;&nbsp;反馈超时
								</c:when>
								<c:otherwise>
									<img src="../resource/image/apply/yes.png">&nbsp;&nbsp;反馈时效正常
								</c:otherwise>
							</c:choose>
						</div>
						<div class="pic">
							<span>反馈效能&nbsp;&nbsp;&nbsp;&nbsp;</span> <a href="javascript:void(0);"
								onclick="selectFeedUseful(0);"><img name="feedBackValue"
								src="../resource/image/apply/star-1.png"></a> <a
								href="javascript:void(0);" onclick="selectFeedUseful(1);"><img
								name="feedBackValue" src="../resource/image/apply/star-1.png"></a>
							<a href="javascript:void(0);" onclick="selectFeedUseful(2);"><img
								name="feedBackValue" src="../resource/image/apply/star-1.png"></a>
							<a href="javascript:void(0);" onclick="selectFeedUseful(3);"><img
								name="feedBackValue" src="../resource/image/apply/star-1.png"></a>
							<a href="javascript:void(0);" onclick="selectFeedUseful(4);"><img
								name="feedBackValue" src="../resource/image/apply/star-1.png"></a>
						</div>
						<div class="pic">
							<span>使用效能&nbsp;&nbsp;&nbsp;&nbsp;</span> <a href="javascript:void(0);"
								onclick="selectUseful(0);"><img name="useValue"
								src="../resource/image/apply/star-1.png"></a> <a
								href="javascript:void(0);" onclick="selectUseful(1);"><img
								name="useValue" src="../resource/image/apply/star-1.png"></a>
							<a href="javascript:void(0);" onclick="selectUseful(2);"><img
								name="useValue" src="../resource/image/apply/star-1.png"></a>
							<a href="javascript:void(0);" onclick="selectUseful(3);"><img
								name="useValue" src="../resource/image/apply/star-1.png"></a>
							<a href="javascript:void(0);" onclick="selectUseful(4);"><img
								name="useValue" src="../resource/image/apply/star-1.png"></a>
						</div>
				</fieldset>
			</div>
		</div>
	</form>  --%>
	
	
	<!-- 新  核实弹窗 -->
	<form id="uf" method="post" style="padding: 0px; margin: 0px;">
		<table class="t-table" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td class="t-nav">反馈时效</td>
				<td class="t-body">
					<input type="hidden" id="feedBackTime" value="${applyBack.timeDiff}" />
					<c:choose>
						<c:when test="${applyBack.timeDiff>0}">
							<img src="../resource/image/apply/no.png">&nbsp;&nbsp;反馈超时
						</c:when>
						<c:otherwise>
							<img src="../resource/image/apply/yes.png">&nbsp;&nbsp;反馈及时
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td class="t-nav">合成效能</td>
				<td class="t-body">
					<ul class="t-check">
						<li>
							<label >非常不满意</label>
							<img name="useValue1" src="../resource/image/apply/redio-1.png" class="img" id="0">
						</li>
						<li>
							<label>不满意</label>
							<img name="useValue1" src="../resource/image/apply/redio-1.png" class="img" id="1">
						</li>
						<li>
							<label>一般</label>
							<img name="useValue1" src="../resource/image/apply/redio-1.png" class="img" id="2">
						</li>
						<li>
							<label>满意</label>
							<img name="useValue1" src="../resource/image/apply/redio-1.png" class="img" id="3">
						</li>
						<li>
							<label>非常满意</label>
							<img name="useValue1" src="../resource/image/apply/redio-1.png" class="img" id="4">
						</li>
					</ul>
				</td>
			</tr>
			<tr>
				<td class="t-nav">核实情况</td>
				<td class="t-body">
					<textarea class="pj-textarea" id="backDesc" name="backDesc"  title="最多输入150个字符" class="tex1" rows="4" ></textarea>
				</td>
			</tr>
		</table>
	</form>
	</br> 
	
	
	
	
	<div style="text-align: right;">
		<a id="saveNext" href="javascript:void(0)" class="easyui-linkbutton"
			 onclick="saveEvaluate()">提交</a> <a
			id="addAssign" href="javascript:void(0)" class="easyui-linkbutton"
			 onclick="closeWin()">取消</a>
	</div>
</body>
</html>