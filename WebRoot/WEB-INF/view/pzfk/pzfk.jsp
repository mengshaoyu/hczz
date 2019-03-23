<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"> 
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>市北分局合成作战中心</title>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<meta name="generator" content="MSHTML 8.00.7600.16588">
<link rel="stylesheet" type="text/css"	href="${ctx}/resource/style/sop/pzfk.css" />
<script type="text/javascript" src="${ctx}/resource/pzfk/pzfk.js"></script>
<script type="text/javascript"
	src="${ctx}/resource/common/ajaxfileupload.js"></script>
<script type="text/javascript">
    var pzid = '${pzApplyId}';
   /*  var hasAuth = '${hasAuth}'; */
    var flag = '${flag}';
   /*  var flowid = '${flowid}'; */
    window.onload = function() {
        $('#loading-mask').fadeOut();//页面初始加载，覆盖层
    }
</script>
</head>
<body style="padding: 0px; margin: 0px; border: 0px;">
	<!-- 等待加载 遮罩层 -->
	<div id="loading-mask"
		style="position: absolute; top: 0px; left: 0px; width: 100%; height: 100%; background: #F4F4F4; z-index: 20000">
		<div id="pageloading"
			style="width: 200px; height: 15px; position: absolute; top: 50%; left: 50%; margin: -20px 0px 0px -120px; text-align: center; border: 2px solid #ccc; font-size: 14px; padding: 10px; background: white;">
			<img style="vertical-align: middle; float: left;"
				src="../resource/image/loading.gif">页面加载中，请稍后...
		</div>
	</div>
		<div id="fkContent"></div>
	
	<div id="dlg" class="easyui-dialog"
		style="width: 530px; height: 410px; padding: 10px; text-align: center; overflow-x:hidden;overflow-y:auto; " 
		data-options="
                top:5,
                draggable:false,
                closed:true,
                modal:true,
                title:'反馈',
                buttons: [{
                    text:'保存',
                    handler:function(){
                    	var flag = $('#hiddenFlag').val();
                    	var resultId = $('#hiddenResultId').val();
                    	if(flag == 1){
                        	updateadd(resultId);
                    	}else{
	                        addsave(); 
                    	}
                    }
                },{
                    text:'取消',
                    handler:function(){
                        $('#dlg').dialog('close');
                        $('#fileSpan').empty();
                    }
                }]">
		<form id="af" method="post" style="padding: 0px; margin: 0px;overflow-x:hidden;">
			<input id="clueId" type="hidden" name="clueId">
			<input id="hiddenFlag" type="hidden" name="" value="">
			<input id="hiddenResultId" type="hidden" value="">
			<table id="addTable" style="margin: 10px 0 0 20px">
				<tr>
					<td style="width: 100px" align="right"><span style="font-size: 14px;">反馈内容：</span></td>
					<td><textArea cols="40" rows="3" name="resultDesc" id="resultDesc" maxlength="100" style="width: 326px;padding: 5px;box-sizing:border-box;border:1px solid #e5e5e5"></textArea></td>
				</tr>
				<tr>
					<td align="right"><span style="font-size: 14px; ">来源：</span></td>
					<td><input style="height:32px;line-height:32px;width: 326px;text-indent:5px;box-sizing:border-box;border:1px solid #e5e5e5" name="resultBy" id="resultBy"
						class="easyui-validatebox"
						data-options="validType:'length[1,100]'" type="text" maxlength="100" value="" /> </td>
				</tr>
				<tr>
					<td align="right"><span style="font-size: 14px; ">详细说明：</span></td>
					<td><textArea cols="40" rows="3" name="resultRemark" id="resultRemark" maxlength="200" style="width: 326px;padding: 5px;box-sizing:border-box;border:1px solid #e5e5e5"></textArea></td>
				</tr>
				<tr>
					<td align="right"><span style="font-size: 14px;">登记日期：</span></td>
					<td><input style="height:32px;line-height:32px;width: 326px;border:1px solid #e5e5e5;text-indent:5px" name="resultDate" id="resultDate"
						class="Wdate" type="text" onfocus="WdatePicker({isShowClear:false,dateFmt : 'yyyy-MM-dd',maxDate:'%y-%M-%d'})" readOnly ></td>
				</tr>
				<tr>
					<td align="right"><span style="font-size: 14px; ">添加附件：</span></td>
					<td><div style='position: relative;'>
							<a class='upload_btn' style="float:left" href='javascript:;'>添加附件</a> <input
								type='file' id="att" class='fileContent'
								onchange="getFileUrl('5',this,'attIds')">
					</td></div>
				</tr>
			</table>
			<div id="fileSpan" style="padding-left: 110px;width:450px"></div>
		</form>
	</div>
	
	<div style="display: none" class="bottomBtn" id="butt">
	<input type="hidden" value="${pzApplyId }" id="hiddenPzId">
	<input type="hidden" value="${flowid }" id="hiddenFlowId">
	<input type="hidden" value="${fqr }" id="hiddenFqr">
	<input type="hidden" value="${hcr }" id="hiddenHcr">
			<%-- <c:if test="${flag eq '1'}">
				<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 130px"
					onclick="openClueSumup();" >提交反馈</a>
			</c:if> --%>
			<c:if test="${flag eq '2'}">
				<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 130px" title="确认反馈，点击生成反馈报告"
					onclick="openSumup();">完成反馈</a>
			</c:if>
	</div>  
	
	<!-- 线索综述 -->
	<div id="clue_sumup_dlg" class="easyui-dialog"
		style="width: 500px; height: 300px; padding: 10px; text-align: center;"
		data-options="
                top:10,
                draggable:false,
                closed:true,
                modal:true,
                title:'添加综述并提交反馈',
                buttons: [{
                    text:'确定',
                    handler:function(){
                        next(${flowid});
                    }
                },{
                    text:'取消',
                    handler:function(){
                        $('#clue_sumup_dlg').dialog('close');
                        submit_clueid='';
                    }
                }]">
		<form id="af_sumup" method="post" style="padding: 0px; margin: 0px;">
			<table id="addTable">
				<tr>
					<td style="width: 100px" align="right"><span style="font-size: 14px;">线索综述：</span></td>
					<td>
						<textarea style="width: 330px;height:160px;" name="clueSumup" id="clueSumup"
							class="easyui-validatebox"
							data-options="validType:'length[1,300]'"  maxlength="300"></textarea>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<!-- 确认反馈：综合论述 -->
	<div id="sumup_dlg" class="easyui-dialog"
		style="width: 500px; height: 300px; padding: 10px; text-align: center;"
		data-options="
                top:10,
                draggable:false,
                closed:true,
                modal:true,
                title:'完成反馈',
                buttons: [{
                    text:'完成并生成反馈报告',
                    handler:function(){
                        saveNext(${flowid});
                    }
                },{
                    text:'取消',
                    handler:function(){
                        $('#sumup_dlg').dialog('close');
                    }
                }]">
		<form id="af_sum" method="post" style="padding: 0px; margin: 0px;">
			<table>
				<tr>
					<td style="width: 100px" align="right"><span style="font-size: 14px;">综合结论：</span></td>
					<td>
						<textarea style="width: 330px;height:160px;" name="sumup" id="sumup"
							class="easyui-validatebox"
							data-options="validType:'length[1,300]'"  maxlength="300"></textarea>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>