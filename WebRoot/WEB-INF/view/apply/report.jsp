<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>报告查看 - 合成作战平台</title>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<meta name="generator" content="MSHTML 8.00.7600.16588">

<link rel="stylesheet" href="${ctx}/resource/common/jQuery_zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${ctx}/resource/common/jQuery_zTree/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx}/resource/common/jQuery_zTree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${ctx}/resource/common/jQuery_zTree/js/jquery.ztree.exhide-3.5.js"></script>
<script type="text/javascript"
	src="${ctx}/resource/common/jquery.form.js"></script>
<script type="text/javascript"
	src="${ctx}/resource/recordsmng/common/configService.js"></script>
<script type="text/javascript" src="${ctx}/resource/apply/report.js"></script>

<script type="text/javascript">
	var caseNo='${apply.caseNo}';
	var pzApplyId='${apply.pzApplyId}';
	var show='${show}';
	window.onload = function() {
		$('#loading-mask').fadeOut();//页面初始加载，覆盖层
	}
</script>	

</head>
<body
	style="padding: 0px; margin: 0px; border: 0px; overflow:hidden; ; background-color: #FFF;">

	<!-- 等待加载 遮罩层 -->
	<div id="loading-mask"
		style="position: absolute; top: 0px; left: 0px; width: 100%; height: 100%; background: #F4F4F4; z-index: 20000">
		<!-- <div id="pageloading"
			style="width: 200px; height: 15px; position: absolute; top: 50%; left: 50%; margin: -20px 0px 0px -120px; text-align: center; border: 2px solid #ccc; font-size: 14px; padding: 10px; background: white;">
			<img style="vertical-align: middle; float: left;"
				src="../resource/image/loading.gif">页面加载中，请稍后...
		</div> -->
	</div>
	<div style="width:100%;">
		<!-- 显示树形列表 -->
		<div id="treeDiv" style="width:250px;border-right: 1px solid #f1f1f1; background:#416385;float: left;color:#FFF;">
			<ul id="tree" class="ztree" style="width:250px; overflow:auto;"></ul>
		</div>
		<!-- 显示报告内容 -->
		<div id="reportDiv" style="float: left;">
			<iframe id="iframe_report" name="iframe_report" src="" frameborder="0" width="100%"></iframe>
		</div>
	</div>
</body>
</html>