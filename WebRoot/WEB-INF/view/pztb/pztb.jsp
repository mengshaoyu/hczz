<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>合成作战平台</title>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<meta name="generator" content="MSHTML 8.00.7600.16588">
<link rel="stylesheet" type="text/css"
	href="${ctx}/resource/style/sop/pztb.css" />
<script type="text/javascript" src="${ctx}/resource/pztb/pztb.js"></script>
<script type="text/javascript"
	src="${ctx}/resource/common/ajaxfileupload.js"></script>
<script type="text/javascript">
    var newaj = '${newaj}' == '1';
    var ajid = '';
    var deptname = '${deptName}';
    var deptId = '${deptId}';
    var pzid = '${pzid}';
    var sfbj = '${sfbj}';
    var ywlx = '${ywlx}';
    var ajlx = '${ajlx}';
    var ajzt = '${ajzt}';
    var caseTypeImp = '${caseTypeImp}';
    var fromDetail = '${fromDetail}';
	window.onload = function() {
		$('#loading-mask').fadeOut();//页面初始加载，覆盖层
		resizeFrameHeight();
	}
</script>
<style type="text/css">
	html{
		margin:10px 0;
	}
</style>
</head>
<body style="padding: 0px; margin: 0px; border: 0px;" >
	<!-- 等待加载 遮罩层 -->
	<div id="loading-mask"
		style="position: absolute; top: 0px; left: 0px; width: 100%; height: 100%; background: #F4F4F4; z-index: 20000">
		<div id="pageloading"
			style="width: 200px; height: 15px; position: absolute; top: 50%; left: 50%; margin: -20px 0px 0px -120px; text-align: center; border: 2px solid #ccc; font-size: 14px; padding: 10px; background: white;">
			<img style="vertical-align: middle; float: left;"
				src="../resource/image/loading.gif">页面加载中，请稍后...
		</div>
	</div>
	<div id="first-div" class="outerborder">
	<form id="ajxx_form">
	    <div class="outertitle">合成申请</div>
		<div id="ajxx_panel">
		    <div class="panelTitle panelTitle-noTopBorder">案件信息&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <!-- <input type="checkbox" value=""> -->
                <span style="color:#f00;font-size: 12px;padding-left:10px;font-weight: normal">支持输入案件编号检索案件信息</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
			<div class="panelContent">
				<table class="panelTable ajxx" cellspacing="0" cellpadding="0">
					<tr>
						<td class="tb-left"><span class="requ">*</span>案件编号</td>
						<td class="tb-right"><input id="caseNo" type="text" label="案件编号" 
							name="caseInfo.caseNo" data-options="notnull:true,ajbh:true"></td>
						<td class="tb-left">警情编号</td>
                        <td class="tb-right"><input type="text" label="警情编号" name="caseInfo.jqNo"
                            data-options="jqbh:true"></td>
                        <td class="tb-left">案件来源</td>
                        <td class="tb-right"><input id="caseAjly" type="text" name="caseInfo.caseAjly"></td>
					</tr>
					<tr>
					    <td class="tb-left"><span class="requ">*</span>案件名称</td>
                        <td class="tb-right"><input type="text" label="案件名称" name="caseInfo.caseName"
                            data-options="notnull:true,len:[1,50]"></td>
						<td class="tb-left">案件类型</td>
                        <td class="tb-right"><input id="caseTypeImp" type="text" name="caseInfo.caseTypeImp"></td>
						<td class="tb-left">案件状态</td>
                        <td class="tb-right"><input id="caseStatus" type="text" name="caseInfo.caseStatus"></td>
					</tr>
					<tr>
					    <td class="tb-left">案别</td>
                        <td class="tb-right"><input id="caseType" label="案由" type="text" name="caseInfo.caseType" ></td>
                        <td class="tb-left">发案地点</td>
                        <td class="tb-right"><input type="text" label="发案地点"
                            name="caseInfo.incidentPlace"
                            data-options="len:[0,50]"></td>
						<td class="tb-left">受案单位</td>
						<td class="tb-right"><input name="caseInfo.acceptUnit" id="acceptUnit" class="easyui-combotree" ></td>
						
					</tr>
					<tr>
					    <td class="tb-left">地理坐标</td>
					    <td class="tb-right">
					       <span class="dlzbspan">X</span>
					       <input id="mapX" type="text" name="caseInfo.latX" class="dlzbinput" readonly="readonly">
					       <span class="dlzbspan">Y</span>
					       <input id="mapY" type="text" name="caseInfo.lonY" class="dlzbinput" readonly="readonly">
					    </td>
					    <td class="tb-left">案发时间</td>
                        <td class="tb-right"><input name="caseInfo.incidentDate" label="案发时间" id="incidentDate"
                            onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"
                            class="Wdate" data-options="len:[0,50]"></td>
					    <td class="tb-left"><span class="requ">*</span>受案时间</td>
                        <td class="tb-right"><input name="caseInfo.acceptDate" label="受案时间" id="acceptDate"
                            onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"
                            class="Wdate" data-options="notnull:true,len:[1,50]"></td>
					</tr>
					<tr>
						<td class="tb-left"><span class="requ">*</span>简要案情</td>
						<td class="tb-right" colspan="5"><textarea label="简要案情"
								name="caseInfo.caseDesc" rows="5" cols="40"
								data-options="notnull:true,len:[1,1200]"></textarea>
								<div style="clear:both"></div>
						</td>
					</tr>
				</table>

			</div>
		</div>
		<div id="flws_panel">
            <div class="panelTitle panelTitle-button">法律文书&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <a id="addWsfjBtn" href="javascript:void(0)" class="easyui-linkbutton" onclick="addWsfj()">+添加</a>
                <!-- 闭环文书提示语 -->
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <span id="bhwscount"></span>
            </div>
            <div class="panelContent wspanel">
                <span id="sadjb"></span>
                <span id="lajds"></span>
                <span id="cqlabgs"></span>
                <div style="clear:both"></div>
            </div>
        </div>
        <div id="task_panel">
            <div class="panelTitle">选择任务级别</div>
            <div class="panelContent">
                <table class="panelTable task">
                    <tr>
                        <td class="tb-left"><span class="requ">*</span>配帧级别</td>
                        <td class="tb-right"><input id="rwjb" name="PzApply.pzApplyGrade" onchange=""/></td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                    </tr>
                </table>
            </div>
        </div>
		<div id="cxnr_panel">
			<div class="panelTitle panelTitle-button">查询内容&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			  <a id="addCxnrBtn" href="javascript:void(0)" class="easyui-linkbutton" onclick="addCxnr()">+添加</a>
			</div>
			<div class="panelContent">
				<table class="panelTable cxnr">
					<tr>
						<td class="tb-left"><span class="requ">*</span>查询要求</td>
						<td class="pzTypeDetail_td tb-right" >
						  <select id="pzTypeDetail" name="clueList[0].pzTypeDetail"></select>
						</td>
						<td class="tb-left"><span class="requ">*</span>线索来源</td>
                        <td class="tb-right"><input type="text" label="线索来源"
                           name="clueList[0].clueSource"
                           data-options="notnull:true,len:[1,50]"></td>
                        <td class="tb-left"><span class="requ">*</span>查询内容</td>
                        <td class="tb-right"><input type="text" label="线索内容"
                            name="clueList[0].clueName"
                            data-options="notnull:true,len:[1,50]"></td>
					</tr>
					<tr>
					    <td class="tb-left">来源证明</td>
                        <td  class="tb-right" colspan="5" valign="top">
                           <div class="fileadd">
                               <a class="upload_btn" href="javascript:;">+添加附件</a> <input
                                   type="file" label="来源证明" name="clueList[0].lyzm" id="lyzm0"
                                   class="fileContent" onchange="getFileUrl('1',this,'clueFile')" >
                           </div>
                        </td>
					</tr>
					<tr>
						<td class="tb-left">详细描述</td>
						<td class="tb-right" colspan="5">
						  <textarea label="详细描述" name="clueList[0].clueDesc" rows="5" cols="40" data-options="len:[0,300]"></textarea>
						  <div style="clear:both"></div>
						</td>
					</tr>
					<tr>
					   <td colspan="6" align="right">
					     <a class="option-cxnr saveCxnr" onclick="saveCxnr(this)" href="javascript:void(0)">保存</a>
                         <a class="option-cxnr deleteCxnr" onclick="deleteCxnr(this)" href="javascript:void(0)">删除</a>
					   </td>
					</tr>
				</table>
			</div>
		</div>
        <div class="bottomBtn">
		    <a href="javascript:void(0)" class="easyui-linkbutton"  onclick="startsp()">申请</a>
		    <a href="javascript:void(0)" class="easyui-linkbutton"  onclick="saveTemp()">保存</a>
	    </div>
	    <div id="bottomDiv"></div>
	</form>
    </div>
    <!-- 法律文书dialog -->
    <div id="addFlws" class="easyui-dialog" closed="true" data-options="modal:true,closable: false" buttons="#addFlws-buttons" title="添加文书附件" style="width:400px;height:250px;top:100px">
        <table style="width: 100%;font-size: 14px;color : #4d4d4d; margin-top:20px;">
            <tr>
                <td align="center" style="height:50px">文书类型</td>
                <td><input id="addFlwsType"/></td>
            </tr>
            <tr>
                <td align="center" style="width: 128px;margin-left: 20px">附件上传</td>
                <td style="width: 260px"><input id="fjfile" type="file" style="width: 200px;"></td>
            </tr>
        </table>
    </div>
    <div id="addFlws-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="uploadFlws()">上传</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closedialog('addFlws')">取消</a>
    </div>
    <!-- 查询人承诺dialog -->
    <div id="cxrcndialog" class="easyui-dialog" closed="true" data-options="modal:true,closable: false" buttons="#cxrcndialog-buttons" title="查询人承诺" style="width:500px;height:400px;top:30px;line-height: 34px;padding:0 10px;">
        <div style="text-align: center;font-size: 16px;font-weight: bold;">查询人承诺</div>
        1、本人知悉所查询的内容属公民个人信息，本人承诺严格遵守国家相关法律、法规和政策，严格执行有关规定。
        <br>2、本人承诺严格遵守保密纪律，妥善保管查询结果，不向无关组织和个人透露查询内容，不将查询结果作为证据使用，如有违反将依法承担责任。
        <br>3、本人承诺所提交的证明材料和文件真实有效。</td></tr></table>
        <br>
        <div style="text-align: right;padding-right:30px">
                        查询人：${userName}
            <br>
            ${currDate}
        </div>
    </div> 
    <div id="cxrcndialog-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="cxrcnok()">确认</a> 
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closedialog('cxrcndialog')">取消</a>
    </div>
    <!-- 提交审批dialog -->
    <div id="spdialog" class="easyui-dialog" closed="true" data-options="modal:true,closable: false" buttons="#spdialog-buttons" title="提交审批" style="width:400px;height:350px;top:100px">
        <table class="spdialog-table">
            <tr>
                <td colspan="2" class="top">请选择单位领导审批</td>
            </tr>
            <tr>
                <td align="right" class="left" style="height:40px;width:100px;">单位：</td>
                <td id="deptname" style="padding-left: 20px">&nbsp;</td>
            </tr>
            <tr>
                <td align="right" class="left" style="height:40px;">审批人：</td>
                <td id="fgld" style="padding-left: 20px;">&nbsp;</td>
            </tr>
        </table>
    </div> 
    <div id="spdialog-buttons">
        <a id="spdialog-ok" href="javascript:void(0)" class="easyui-linkbutton" onclick="saveNext()">提交</a> 
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closedialog('spdialog')">取消</a>
    </div>
</body>
</html>