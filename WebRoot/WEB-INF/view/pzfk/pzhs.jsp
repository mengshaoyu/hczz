<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>青岛市公安局图像综合应用平台</title>
	<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="x-ua-compatible" content="ie=7" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${ctx}/resource/css/ionicons.min.css" />
	<style type="text/css">
		textarea {
			width:100%;
			height:100%;
			border:0 none; 
			outline:none;
			}
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
	<script type="text/javascript">
		$(function(){
			var heightFlag = $("#heightFlag").val();
			if(heightFlag == "0"){
				$(".t-table").css("height","240px");
			}
			var usaBility = $("#usaBilityHidden").val();
			if(usaBility == "0"){
				$("#0").attr("src","../resource/image/apply/redio-2.png");
			}
			if(usaBility == "1"){
				$("#1").attr("src","../resource/image/apply/redio-2.png");
			}
			if(usaBility == "2"){
				$("#2").attr("src","../resource/image/apply/redio-2.png");
			}
			if(usaBility == "3"){
				$("#3").attr("src","../resource/image/apply/redio-2.png");
			}
			if(usaBility == "4"){
				$("#4").attr("src","../resource/image/apply/redio-2.png");
			}
			if(usaBility == "5"){
				$("#4").attr("src","../resource/image/apply/redio-2.png");
			}
		});
	</script>
</head>
	<body>
		<form id="uf" method="post" style="padding: 0px; margin: 0px;">
		<table class="t-table" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<input type="hidden" value="${flag}" id="heightFlag" >
				<td class="t-nav">反馈时效</td>
				<td class="t-body">
					<input type="hidden" id="feedBackTime" value="${map.FEEDBACK_AGING}" />
					<c:if test="${map.FEEDBACK_AGING eq 'f'}">
						<img src="../resource/image/apply/no.png">&nbsp;&nbsp;反馈超时
					</c:if>
					<c:if test="${map.FEEDBACK_AGING eq 't'}">
						<img src="../resource/image/apply/yes.png">&nbsp;&nbsp;反馈及时
					</c:if>
				</td>
			</tr>
			<c:if test="${flag eq '1' }">
			<tr>
				<td class="t-nav">合成效能</td>
				<td class="t-body">
					<input type="hidden" value="${map.USABILITY}" id="usaBilityHidden" >
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
			</c:if>
			<tr>
				<td class="t-nav">核实情况</td>
				<td class="t-body">
					<textarea class="pj-textarea" id="backDesc"  maxlength="150"  class="tex1" rows="4" readonly="readonly">${map.LANDING }</textarea>
				</td>
			</tr>
		</table>
		</br>
			<div  class="bottomBtn" id="butt" style="padding: 15px 0">
				<a href="javascript:parent.closeEvaluate()" class="easyui-linkbutton" style="padding: 0 25px" >关闭</a>
		    </div>  
		</form>
	</body>
</html>
 