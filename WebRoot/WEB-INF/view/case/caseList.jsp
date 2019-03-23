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

<script type="text/javascript"
	src="${ctx}/resource/common/jquery.form.js"></script>
<script type="text/javascript" src="${ctx}/resource/case/caseList.js"></script>
<style type="text/css">

table.cxtj td input{
	height: 34px !important;
	line-height: 34px !important;
}
table.cxtj td span.combo,table.cxtj td span.combo-arrow{
    height: 32px !important;
}

.td-title{
	width:8%;
	text-align: center !important;
}
.td-content{
	width:22%;
	text-align: left !important;
}
.combo-p {
    border: 1px solid #ddd;
}
</style>
<script type="text/javascript">
    window.onload = function() {
        $('#loading-mask').fadeOut();//页面初始加载，覆盖层
    }
</script>
</head>
<body class="body-bg" style="overflow:hidden;">

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
		案件管理
	</div>
	<!-- 查询条件 -->
	<form id="qf" class="wd98-bg" style="margin-top:0px;">
        <table class="cxtj" style="width:100%;" cellspacing="0" id="queryTable">
            <tr>
                <td  class="td-title tb-left">案件编号</td>
                 <td class="td-content tb-right">
					 <input id="caseNo" name="caseNo"
	                    style="border: 1px solid #DDDDDD; width: 300px;"
	                    class="easyui-validatebox" data-options="validType:'length[1, 30]'">
                </td>
                <td class="td-title tb-left">案件名称
                </td>
                <td class="td-content tb-right">
					<input id="caseName" name="caseName"
	                    style="border: 1px solid #DDDDDD; width: 300px;"
	                    class="easyui-validatebox" data-options="validType:'length[1, 30]'">
                </td>
                <td class="td-title tb-left">案件类型
                </td>
                <td class="td-content tb-right"> <input id="caseType" name="caseType"
                    style="border: 1px solid #DDDDDD; width: 300px;"
                    class="easyui-combobox"
                    data-options="
                            url:'${ctx}/code/getCodeValueListByTypeId?typeId=2001',
                            valueField:'codeValue',
                            textField:'valueDesc',
                            editable:false,
                            panelHeight:'auto',
                            multiple:false">
                </td>
            </tr>
            <tr>
                <td class="td-title tb-left">受案时间
                </td>
                 <td class="td-content tb-right"> <input id="timeStart" name="timeStart"
                    style="border: 1px solid #DDDDDD; width: 300px;" class="Wdate"
                    onfocus="WdatePicker({isShowClear:false,dateFmt : 'yyyy-MM-dd HH:mm:ss',minDate:'%y-{%M-1}-%d',maxDate:'#F{$dp.$D(\'timeEnd\')}'})"
                    readOnly></td>
                <td class="td-title tb-left">--
                </td>
                 <td class="td-content tb-right"><input id="timeEnd" name="timeEnd"
                    style="border: 1px solid #DDDDDD; width: 300px;" class="Wdate"
                    onfocus="WdatePicker({dateFmt : 'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'timeStart\')}'})"
                    readOnly>
                </td>
                <td class="td-title tb-left">案件状态
                </td>
                <td class="td-content tb-right"> <input id="caseStatus" name="caseStatus"
                    style="border: 1px solid #DDDDDD; width: 300px;"
                    class="easyui-combobox"
                    data-options="
                            url:'${ctx}/code/getCodeValueListByTypeId?typeId=1005',
                            valueField:'codeValue',
                            textField:'valueDesc',
                            editable:false,
                            panelHeight:'160',
                            multiple:false">
                </td>
            </tr>
            <tr>
                <td class="td-title tb-left">嫌疑人姓名</td>
                <td class="td-content tb-right"><input id="criminalName" name="criminalName"
                    style="border: 1px solid #DDDDDD; width: 300px;"
                    class="easyui-validatebox" data-options="validType:'length[1, 15]'">
                </td>
                <td class="td-title tb-left">主办单位</td>
                <td class="td-content tb-right"><input id="undertakeUnit" name="undertakeUnit"
                    style="border: 1px solid #DDDDDD; width: 300px;"
                    class="easyui-validatebox" data-options="validType:'length[1, 30]'">
                </td>
                <td style="display:none;" class="td-title tb-left">承办区域
                </td>
                
                <td style="display:none;" class="td-content tb-right"> <input id="undertakeArea" name="undertakeArea"
                    style="border: 1px solid #DDDDDD; width: 300px;"
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


	<div style="display: none;">
        <!-- 	导入弹出框 -->
		<div id="importDialog" class="easyui-dialog"
			style="width: 400px; height: 150px; padding: 10px; text-align: center;"
			data-options="
            modal:true,
            top:200,
            title:'导入',
            closed:true,
            buttons: [{
                text:'关闭',
                handler:function(){
                    $('#importDialog').dialog('close');
                }
            },
            {
                id: 'btn_apply_save',
                text:'导入',
                handler:function(){
                    startImport();
                }
            }]">
			<form id="fileForm" method="post" style="padding: 0px; margin: 0px;">
				<input class="l-btn" type="file" id="importFile" name="importFile"
					accept=".xls,.xlsx">
			</form>
		</div>
        <!-- 		导入错误信息弹出框 -->
		<div id="errorDialog" class="easyui-dialog"
            style="width: 1050px; height: 450px; padding: 10px; text-align: center;"
            data-options="
            modal:true,
            top:20,
            title:'案件错误信息',
            closed:true,
            buttons: [{
                text:'关闭',
                handler:function(){
                    $('#errorDialog').dialog('close');
                }
            }]">
            <div id="_grid2"></div>
        </div>
	</div>
</body>
</html>
