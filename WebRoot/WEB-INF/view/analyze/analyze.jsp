<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<c:set var="usrId" value="${sessionScope.sesnUsrId}" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>合成作战平台</title>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<meta name="generator" content="MSHTML 8.00.7600.16588">
<link rel="stylesheet" type="text/css" href="${ctx}/resource/style/sop/analyze.css" />
<script type="text/javascript" src="${ctx}/resource/common/echarts/dist/echarts.js"></script>
<script type="text/javascript" src="${ctx}/resource/analyze/analyze.js"></script>
<style type="text/css">
</style>
<script type="text/javascript">
	
</script>

</head>
<body>
	<div class="bread-nav">合成比重统计</div>
	<div class="work-panel">
		<div class="date-condition">
		  <a class="date-condition-item" href="javascript:void(0)">今日</a>
		  <a class="date-condition-item" href="javascript:void(0)">最近一周</a>
		  <a class="date-condition-item" href="javascript:void(0)">最近一月</a>
		  <span>从</span>
		  <input id="incidentDate" class="Wdate date-condition-select" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" >
		  <span>到</span>
		  <input id="incidentDate" class="Wdate date-condition-select" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" >
		  <a href="javascript:void(0)" class="easyui-linkbutton"  style="margin-left:30px;">查询</a>
		</div>
		<table class="date-content">
			<tr>
				<td class="hcbztj-pie-td">
					<div id="hcbztj-pie" class="hcbztj-pie"></div>
				</td>
				<td id="hcbztj-table" class="hcbztj-table" align="center" valign="top">
					<table id="hcbztj-table-content" align="right"></table>
				</td>
			</tr>
		</table>
	</div>
	<div class="bread-nav">评价统计</div>
	<div class="work-panel">
	   <ul>
	       <li class="pjtj-li first">
	           <p>0</p>
	           <span>非常不满意</span>
	       </li>
	       <li class="pjtj-li second">
               <p>0</p>
               <span>不满意</span>
           </li>
           <li class="pjtj-li third">
               <p>2</p>
               <span>一般</span>
           </li>
           <li class="pjtj-li fourth">
               <p>16</p>
               <span>满意</span>
           </li>
           <li class="pjtj-li fifth">
               <p>598</p>
               <span>非常满意</span>
           </li>
	   </ul>
	   <div class="clear-both"></div>
	   <div id="pjtj-table">
	       <table id="pjtj-table-content"></table>
	   </div>
	</div>
</body>
</html>
