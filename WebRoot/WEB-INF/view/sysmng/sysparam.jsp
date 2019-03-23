<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>合成作战平台</title>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<meta name="generator" content="MSHTML 8.00.7600.16588">
<link rel="stylesheet" type="text/css"
	href="${ctx}/resource/style/skynetIcon.css" />
<link rel="stylesheet"
	href="${ctx}/resource/common/jQuery_zTree/css/zTreeStyle/zTreeStyle.css"
	type="text/css">
<script type="text/javascript"
	src="${ctx}/resource/common/jQuery_zTree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript"
	src="${ctx}/resource/common/jQuery_zTree/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript"
	src="${ctx }/resource/sysmng/permissionTree.js"></script>
<script type="text/javascript" src="${ctx}/resource/sysmng/sysparam.js"></script>
<script type="text/javascript">
    	window.onload = function(){
			$('#loading-mask').fadeOut();//页面初始加载，覆盖层
		}
    </script>
</head>
<body class="body-bg">
	<div id="loading-mask"
		style="position: absolute; top: 0px; left: 0px; width: 101%; height: 100%; background: #F4F4F4; z-index: 20000">
		<div id="pageloading"
			style="position: absolute; top: 50%; left: 50%; margin: -120px 0px 0px -120px; text-align: center; border: 0px solid #8DB2E3; width: 0px; height: 0px; font-size: 14px; padding: 10px; font-weight: bold; background: #f4f4f4; color: #15428B;">
		</div>
	</div>
	<!-- 工具栏 -->
	<!--      <div class="wd98-bg">
     	<div id="toolbar" url="/login/getToolBar" page="100105"  class="toolbar-style"></div>
     </div> -->
	<!-- 列表DIV -->
	<div class="wd98-bg">
		<div id="toolbar" url="/login/getToolBar" page="100105"
			class="toolbar-style"></div>
		<div id="dg"></div>
	</div>
	<!-- 编辑DIV -->
	<div id="SysParam" class="easyui-dialog"
		style="padding: 10px; text-align: center; top: 0px;"></div>
	<!-- 列表系统域转义DIV -->
	<div id="domain">
		<input name="domainName1" id="sDomainName1" type="text"
			class="easyui-combobox"
			style="border: 1px solid #95B8E7; width: 140px;"
			data-options="
								valueField:'codeValue',
								textField:'valueDesc',
								panelHeight:'auto',
								url:ctxPath+'/sysparam/getDemainTree'
								
				">
	</div>
</body>
</html>