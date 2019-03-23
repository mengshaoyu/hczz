<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>合成作战平台</title>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<meta name="generator" content="MSHTML 8.00.7600.16588">

<script type="text/javascript"
	src="${ctx}/resource/recordsmng/auditLog/logList.js"></script>
<style type="text/css">
</style>
<script type="text/javascript">
		  	window.onload = function(){
				$('#loading-mask').fadeOut();//页面初始加载，覆盖层
			}
		</script>

</head>
<body class="body-bg">

	<!-- 等待加载 遮罩层 -->
	<div id="loading-mask"
		style="position: absolute; top: 0px; left: 0px; width: 100%; height: 100%; background: #F4F4F4; z-index: 20000">
		<div id="pageloading"
			style="width: 200px; height: 15px; position: absolute; top: 50%; left: 50%; margin: -20px 0px 0px -120px; text-align: center; border: 2px solid #ccc; font-size: 14px; padding: 10px; background: white;">
			<img style="vertical-align: middle; float: left;"
				src="../resource/image/loading.gif">页面加载中，请稍后...
		</div>
	</div>

	<!-- 查询条件 -->
	<form id="qf" class="wd98-bg">
		<table class="cxtj"
			style="border-collapse: separate; border-spacing: 10px;"
			cellspacing="10">
			<tr>
				<td>功能模块： <input id="function" name="function"
					style="border: 1px solid #DDDDDD; width: 150px;"
					class="easyui-validatebox" data-options="validType:'length[1, 15]'">&nbsp;
				</td>
				<td>操作内容： <input id="operContent" name="operContent"
					style="border: 1px solid #DDDDDD; width: 150px;"
					class="easyui-validatebox" data-options="validType:'length[1, 30]'">
				</td>
			</tr>
		</table>
	</form>

	<!-- 工具栏 -->
	<div class="wd98-bg query-nav">
		<div id="toolbar" class="toolbar-style"></div>
	</div>

	<!-- 页签  class="easyui-tabs"-->
	<!-- <div id="_tab" class="easyui-tabs"></div> -->

	<!-- 数据列 -->
	<div class="wd98-bg">
		<div id="_grid"></div>
	</div>

</body>
</html>
