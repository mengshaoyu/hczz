<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
<link rel="stylesheet" type="text/css"
	href="${ctx}/resource/style/sop/applyList.css" />
<script type="text/javascript"
	src="${ctx}/resource/common/jquery.form.js"></script>
<script type="text/javascript"
	src="${ctx}/resource/recordsmng/common/configService.js"></script>
<script type="text/javascript" src="${ctx}/resource/apply/applyList.js"></script>
<style type="text/css">
</style>
<script type="text/javascript">
	var usrId = '${usrId}';
	var status = '${status}';
	var isdbsx = '${isdbsx}';
</script>

</head>
<body class="body-bg" style="overflow:hidden;">
	<div class="bread-nav">
		任务管理
	</div>
	<!-- 查询条件 -->
	<form id="qf" style="margin-top:0px;">
		<table class="cxtj" id="queryTable" style="width:100%;" cellspacing="0">
			<tr>
				<td class="td-title tb-left">案件编号</td>
				<td class="td-content tb-right">
				    <input id="caseNo" name="caseNo" class="easyui-validatebox">
				</td>
				<td class="td-title tb-left">案件名称</td>
				<td class="td-content tb-right">
				    <input id="caseName" name="caseName" class="easyui-validatebox">
				</td>
				<td class="td-title tb-left">案件类型</td>
				<td class="td-content tb-right" id="stateTd">
					<input id="caseType" name="caseType">
				</td>
			</tr>
			<tr>
				<td class="td-title tb-left">提报时间</td>
				<td class="td-content tb-right">
				    <input id="timeStart" name="timeStart" class="Wdate"
					onfocus="WdatePicker({isShowClear:false,dateFmt : 'yyyy-MM-dd HH:mm:ss',minDate:'%y-{%M-1}-%d',maxDate:'#F{$dp.$D(\'timeEnd\')}'})"
					readOnly>
				</td>
				<td class="td-title tb-left">--
				</td>
				<td class="td-content tb-right">
				    <input id="timeEnd" name="timeEnd" class="Wdate"
					onfocus="WdatePicker({dateFmt : 'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'timeStart\')}'})" readOnly>
				</td>
<!-- 				<td class="td-title tb-left">业务类型</td> -->
<!-- 			        <td class="td-content tb-right"> -->
<!--                         <input id="applyType" name="applyType"> -->
<!-- 				</td> -->
				<td class="td-title tb-left">任务编号</td>
                <td class="td-content tb-right"><input id="applyNo" name="applyNo"
                    class="easyui-validatebox" data-options="validType:'length[1, 30]'">
                </td>

			</tr>
			<tr>
				<td class="td-title tb-left">线索描述</td>
				<td class="td-content tb-right">
				    <input id="clueDesc" name="clueDesc" class="easyui-validatebox">
				</td>
				<td class="td-title tb-left">任务状态</td>
				<td class="td-content tb-right">
				    <input id="applyStatus" name="applyStatus">
				</td>
				<td class="td-title tb-left">申请单位</td>
				<td class="td-content tb-right">
				    <input id="dept" name="dept" class="easyui-validatebox">
				</td>
			</tr>
		</table>
	</form>

	<!-- 工具栏 -->
	<div class="wd98-bg query-nav" style="border-bottom: 1px solid #f2f2f2; padding-bottom:10px; marign-bottom:0;">
		<div id="toolbar" class="toolbar-style"></div>
	</div>

	<!-- 页签  class="easyui-tabs"-->
	<!-- <div id="_tab" class="easyui-tabs"></div> -->
	<div class="workTabs wd98-bg">
		<ul class="workListTabs">
			<li data-id='' style="padding-left:20px;">全部</li>
			<li class="no-border" id="ddrw" data-id='ddrw' >待办任务<span>0</span></li>
<!-- 			<li id="HCZZ_1002" data-id="HCZZ_1002">初审中<span>0</span></li>
			<li id="HCZZ_1003" data-id="HCZZ_1003">待签收<span>0</span></li>
			<li id="HCZZ_1004" data-id="HCZZ_1004">待审批<span>0</span></li>
			<li id="HCZZ_1005" data-id="HCZZ_1005">待分发<span>0</span></li>
			<li id="HCZZ_1006" data-id="HCZZ_1006,HCZZ_1007">待反馈<span>0</span></li>
			<li id="HCZZ_1008" data-id="HCZZ_1008">待核实<span>0</span></li>
			<li id="end" data-id="end">完结<span>0</span></li>
			<li class="no-border" id="HCZZ_1010">退查补充<span>0</span></li> -->
		</ul>
		<ul class="newWork">
			<li id="create" style="float:right;">新建</li>
			<li class="no-border" style="float:right;" id="receive">签收</li>
		</ul>
	</div>
	<!-- 数据列 -->
	<div class="wd98-bg">
		<div id="_grid"></div>
	</div>

	<div style="display: none;">
		<!-- 批量审批窗口 -->
		<div id="approveDialog" class="easyui-dialog"
			style="width: 500px; height: 225px; padding: 10px; text-align: center;"
			data-options="
            modal:true,
            top:50,
            closed:true,
            title:'审批',
            buttons: [{
                text:'取消',
                handler:function(){
                    $('#approveDialog').dialog('close');
                }
            },{
                text:'提交',
                handler:function(){
                    startApprove();
                }
            }],
            onBeforeClose:function(){
                $('#approveForm').form('clear');
                $('#approveResult').combobox('select',1);
            }">
			<form id="approveForm" method="post"
				style="padding: 0px; margin: 0px;">
				<table class="xq-box">
					<tr>
						<td align=right>审批结果：</td>
						<td align=left><input name="approveResult" id="approveResult"
							type="text" class="easyui-combobox"
							style="border: 1px solid #95B8E7; width: 140px;"
							data-options="
                                    required:true,
                                    valueField:'id',
                                    textField:'name',
                                    panelHeight:'auto',
                                    editable:false,
                                    data:[{id:'1',name:'通过'},{id:'2',name:'不通过'}],
                                    value:1
                                " />
						</td>
					</tr>
					<tr>
						<td align=right>审批意见：</td>
						<td align=left colspan="3" style="padding: 10px 0;"><textarea
								name="approveReason" id="approveReason"
								class="easyui-validatebox"
								data-options="validType:['length[0,250]']" rows="3"
								style="border: 1px solid #DDDDDD; width: 330px; resize: none;"></textarea>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>

	<!-- 任务分发 -->
	<div style="" class="easyui-dialog" id="missionWin" title="任务分发"
		data-options="closed:true">
		<iframe id="iframe_mission" name="iframe_mission" src=""
			frameborder="0" width="100%" height="410px!important"></iframe>
	</div>

	<!-- 落地情况反馈及评价 -->
	<div style="" class="easyui-dialog" id="evaluateWin" title="落地情况反馈及评价"
		data-options="closed:true">
		<iframe id="iframe_evaluate" name="iframe_evaluate" src=""
			frameborder="0" width="100%" height="100%;"></iframe>
	</div>

	<!-- MenuList -->
	<div id="toDos" class="toDos"
		style="display: none; position: absolute; background: #CCE6FF;">
		<ul></ul>
	</div>
</body>
<style>
#testDiv {
	width: 100px;
	height: 150px;
	top: 0;
	left: 0;
	position: absolute;
	display: none;
	background-color: gray;
}
</style>
</html>
