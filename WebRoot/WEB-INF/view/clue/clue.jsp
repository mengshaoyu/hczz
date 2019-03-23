<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>市北合成作战平台</title>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<meta name="generator" content="MSHTML 8.00.7600.16588">

<script type="text/javascript"
	src="${ctx}/resource/common/jquery.form.js"></script>
<script type="text/javascript" src="${ctx}/resource/clue/clue.js"></script>
<style type="text/css">
</style>
<script type="text/javascript">
	window.onload = function() {
		$('#loading-mask').fadeOut();//页面初始加载，覆盖层
	}
</script>

<style>
.cxtj td {
    border: 0;
}
</style>

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

	<div class="bread-nav">
		线索联查
	</div>
	<!-- 查询条件 -->
	<form id="qf" class="wd98-bg"  style="margin-top:0px;">
		<table class="cxtj"
			style="width:100%;table-layout: fixed;"
			cellspacing="0">
			<tr>
				<td  class="tb-left" style="width:100px;height:34px;line-height: 34px;padding: 0;margin: 0;text-align: center">线索类型</td>
				<td  class="td-content tb-right" style="width:310px;height:34px;text-align: left" align="left">
				    <input id="clueType" name="clueType" style="border: 1px solid #e5e5e5; width: 290px; height: 34px;">
					<!-- <select id="clueType" name="clueType"
						style="border: 1px solid #e5e5e5; width: 290px; height: 34px;">
							<option value="">全部</option>
							<option value="1">配侦申请</option>
							<option value="2">反馈报告</option>
							<option value="3">社会资源</option>
					</select> -->
				</td>
				<td class="td-title">&nbsp;</td>
				<td class="td-content tb-right">&nbsp;</td>
				<td class="td-title ">&nbsp;</td>
				<td class="td-content tb-right">&nbsp;</td>
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

	<div id="dd" class="easyui-dialog" title="线索新增" style="width:400px;height:200px;"data-options="resizable:true,modal:true"closed="true">
		<div class="per_information">
			<div class="td_01">反馈内容：</div>
				<div class="td_02">
					<textarea rows="3" cols="20"></textarea>
				</div>
		</div>
		<div class="per_information">
			<div class="td_01">线索来源：</div>
				<div class="td_02">
					<input  type="text" id="" name="">
				</div>
			<div class="td_01">登记时间：</div>
				<div class="td_02">
					<input type="text" id="" name="">
				</div>
			<div class="td_01">上传附件：</div>
				<div class="td_04">
					<input  type="text" id="" name="" >
				</div>
		</div>
		<br>
		<div class="oni-dialog-footer oni-helper-clearfix" >
			<div class="oni-dialog-btns" >
				<input class="tg_laber_btn" onclick="save()" type="button" value="保存">
				<input class="tg_laber_btn" onclick="close()" type="button" value="取消">
			</div>
		</div>
	</div>
</body>
</html>