<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>青岛市公安局合成作战平台</title>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<meta name="generator" content="MSHTML 8.00.7600.16588">

<link rel="stylesheet" type="text/css"
	href="${ctx}/resource/style/sop/taskFlow.css" />
<script type="text/javascript"
	src="${ctx}/resource/recordsmng/common/configService.js"></script>
<script type="text/javascript" src="${ctx}/resource/apply/taskFlow.js"></script>

<script type="text/javascript">
    var pzid = '${pzid}';
	window.onload = function() {
		$('#loading-mask').fadeOut();//页面初始加载，覆盖层
	}
	
</script>
</head>
<body>
	<div class="task-flow-box" id="taskFlowDiv">
		<ul class="flow-path-c">
			<span style="font-size: 16px; padding-left: 10px;">暂未查询到合成申请流转记录。</span>
		</ul>
		<div class='bgzhong'><img src='${ctx}/resource/image/taskFlow/zhong.png'></div>
	</div>
</body>
</html>