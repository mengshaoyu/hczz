<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>重点人员管理系统</title>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<meta name="generator" content="MSHTML 8.00.7600.16588">
<style type="text/css">
</style>
<script type="text/javascript">
</script>
</head>
<body style="padding: 0px; margin: 0px; border: 0px;"
	onload="Leaf.dlg.query()">
	<table width="300px;">

		<tr>
			<td><input id="queryStr" name="queryStr"
				style="width: 230px; border: 1px solid #d7d5d5; height: 23px; line-height: 23px; border-sizing: border-box;"></td>
			<td><a href="#" class="easyui-linkbutton"
				onclick="Leaf.dlg.query()">查询</a></td>
		</tr>
		<tr>
			<td colspan="2">
				<div id="p_grid"></div>
			</td>
		</tr>
	</table>
</body>
</html>