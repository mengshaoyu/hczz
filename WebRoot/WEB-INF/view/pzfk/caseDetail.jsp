<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	<title>市北分局合成作战中心</title>
	<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
	<meta name="generator" content="MSHTML 8.00.7600.16588">
	<link rel="stylesheet" type="text/css"	href="${ctx}/resource/style/sop/caseDetail.css" />
	<link rel="stylesheet" type="text/css"	href="${ctx}/resource/style/sop/pzfk.css" />
	<script type="text/javascript" src="${ctx}/resource/pzfk/caseDetail.js"></script>
	<script type="text/javascript">
	    window.onload = function() {
	        $('#loading-mask').fadeOut();//页面初始加载，覆盖层
	    }
	</script>
</head>
<body style="padding: 0px; margin: 0px; border: 0px;">
	<!-- 等待加载 遮罩层 -->
	<div id="loading-mask"
		style="position: absolute; top: 0px; left: 0px; width: 100%; height: 100%; background: #F4F4F4; z-index: 20000">
		<div id="pageloading"
			style="width: 200px; height: 15px; position: absolute; top: 50%; left: 50%; margin: -20px 0px 0px -120px; text-align: center; border: 2px solid #ccc; font-size: 14px; padding: 10px; background: white;">
			<img style="vertical-align: middle; float: left;"
				src="../resource/image/loading.gif">页面加载中，请稍后...
		</div>
	</div>
	
	<div class="header-nav">
		<div class="nav-logo">
			<i><img src="${ctx}/resource/image/index/logo.png"></i>
			<h1><a href="${ctx}/login/init">合成作战平台</a></h1>
		</div>
	</div>
	
	<!-- 隐藏数据 -->
	<div style="display:none">
		<input type="hidden" value="${pzid}" id="pzid"/>
	</div>
	
	<!-- 表头部分 -->
	<div class="bread-nav">
		${map.CASE_NAME}
	</div>
	
	<!-- 案件信息部分 -->
		<div id="ajxx_panel" id="first-div">
			<div class="panelContent">
		    <div class="panelTitle panelTitle-noTopBorder">案件信息:</div>
				<table class="panelTable ajxx" cellspacing="0" cellpadding="0" style="table-layout: fixed;" >
					<tr>
						<td class="tb-left">案件编号</td>
						<td class="tb-right">${map.CASE_NO}</td>
						<td class="tb-left">警情编号</td>
                        <td class="tb-right">${map.JQ_NO}</td>
                        <td class="tb-left">案件来源</td>
                        <td class="tb-right">${map.CASE_AJLY}</td>
					</tr>
					<tr>
					    <td class="tb-left">案件名称</td>
                        <td class="tb-right" title="${map.CASE_NAME}">${map.CASE_NAME}</td>
						<td class="tb-left">案件类型</td>
                        <td class="tb-right">${map.CASE_TYPE_IMP}</td>
						<td class="tb-left">案件状态</td>
                        <td class="tb-right">${map.CASE_STATUS}</td>
					</tr>
					<tr>
					    <td class="tb-left">案别</td>
                        <td class="tb-right" title="${map.CASE_TYPE}">${map.CASE_TYPE}</td>
                        <td class="tb-left">案发地点</td>
                        <td class="tb-right" title="${map.INCIDENT_PLACE}">${map.INCIDENT_PLACE}</td>
						<td class="tb-left">受案单位</td>
						<td class="tb-right">${map.ACCEPT_UNIT}</td>
						
					</tr>
					<tr>
					    <td class="tb-left">地理坐标</td>
					    <td class="tb-right">
					    	<div style="float:left; width:43%;">
					    	   <span class="dlzbspan">X</span>
						       <div class="border-div" style="width:70%; float:left;" id="mapX" title="${map.LAT_X}">
							       ${map.LAT_X}
						       </div>
					    	</div>
					    	<div style="float:left; width:43%;">
					    	   <span class="dlzbspan">Y</span>
						       <div class="border-div" style="width:70%; float:left;" id="mapY" title="${map.LON_Y}">
						       		${map.LON_Y}
						       </div>
					    	   <a href="javascript:;" onclick="openMap()" style=" color:#4080ee;">查看</a>
					    	</div>
					    </td>
					    <td class="tb-left">案发时间</td>
                        <td class="tb-right" id="incidentDate">${map.INCIDENT_DATE}</td>
					    <td class="tb-left">受案时间</td>
                        <td class="tb-right" id="acceptDate">${map.ACCEPT_DATE}</td>
					</tr>
					<tr>
						<td class="tb-left" style="padding: 5px 0;vertical-align: top;">简要案情</td>
						<td class="tb-right" colspan="5" style="height: 110px;padding: 5px 0;vertical-align: top;">
							<textarea rows="5" cols="40" style="overflow-y:visible;border:none;height:90px;padding:5px;background: #f2f2f2;" readonly="readonly" title="${map.CASE_DESC}">${map.CASE_DESC}</textarea>
						</td>
					</tr>
				</table>
			</div>
		</div>
		
		<!-- 关联线索部分 -->
		<form id="form1" method="post" action="">
		<c:if test="${empty relevance}">
			<p style="font-size: 16px;margin-left:20px;color: #4d4d4d;font-weight:bold;margin-left: 35px; ">暂无关联线索</p>
		</c:if>
		<c:if test="${not empty relevance}">
			<p style="font-size: 16px;margin-left:20px;color: #4d4d4d;font-weight:bold;margin-left: 35px; ">关联线索:</p>
			<div id="relevance" class="relevance">
				<ul id="relevanceUl">
						<c:forEach var="relevance" items="${relevance }" varStatus="status">
							<li id="${relevance.PZ_APPLY_ID }"><a href="javascript:;" onclick="reload('${relevance.PZ_APPLY_ID }')" class="relevanceA">${relevance.PZ_APPLY_NO }</a></li>
						</c:forEach>
				</ul>
			</div>
		</c:if>
		</br>
		</form>
		
		<!-- 任务级别部分 -->
		<p class="level" id="level"></p></br>
		
		<!-- 线索部分 -->
		<div id="fkContent"></div>
	
		 <!-- 地图选点dialog -->
	    <div id="mapmark" class="easyui-dialog" closed="true" data-options="modal:true,closable: false" buttons="#mapmark-buttons" title="地图标点" style="width:600px;height:400px;top:40px">
	        <iframe scrolling="auto" id='mapmarkframe' name="mapmarkframe" frameborder="0"  src="" style="width:100%;height:98%;"></iframe>
	    </div> 
	    <div id="mapmark-buttons">
	        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closedialog('mapmark')">关闭</a>
	    </div>
		
</body>
</html>