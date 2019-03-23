<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>任务详情 - 合成作战平台</title>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
	<meta name="generator" content="MSHTML 8.00.7600.16588">

		<script type="text/javascript" src="${ctx}/resource/common/jquery.form.js"></script>
		<link rel="stylesheet" type="text/css" href="${ctx}/resource/style/sop/applyListDetail.css" />
		<script type="text/javascript"
			src="${ctx}/resource/apply/applyListDetail.js"></script>
		<script type="text/javascript"
			src="${ctx}/resource/recordsmng/common/configService.js"></script>
		<script type="text/javascript">
		    var pzApplyId='${pzApplyId}';
		    var sfbj='${sfbj}';
		    var roleFK='${roleFK}';
		    var flag='${flag}';
		    window.onload = function() {
		        $('#loading-mask').fadeOut();//页面初始加载，覆盖层
		    }
		</script>
</head>
<body style="padding: 0px; margin: 0px; border: 0px; overflow: hidden;">

	<!-- 等待加载 遮罩层 -->
	<div id="loading-mask"
		style="position: absolute; top: 0px; left: 0px; width: 100%; height: 100%;  z-index: 20000">
	</div>
	<%@ include file='../index/public-header.jsp' %>
	<div class="bread-nav">
		${caseName}
		<div class="edit-button">
				
		</div>
	</div>
	<div class="wrapper-bg-c">
		<div class="header-menu "
			style=" top:50; width: 100%; height: 45px; left: 0; background: #2c80e9; margin:0;">
			<div class="deploy-form-menu toolbar" style="margin-left: 10px;">
				<div class="tab-ul-menu" id="limenu">
					<ul>
					<li>申请信息</li>
					<li style="display:none;">反馈线索</li>
					<li style="display:none;">工作进展</li>
					<li style="display:none;">报告</li>
					<li style="display:none;">合成比重及效能</li>
					</ul>
				</div>
			</div>
			<!-- 按钮显示 -->
		</div>
		<div id="detail">
			<iframe id="c0" class="detail" style="display: none;" frameborder="0"></iframe>
			<iframe id="c1" class="detail" style="display: none;" frameborder="0"></iframe>
			<iframe id="c2" class="detail" style="display: none;" frameborder="0"></iframe>
			<iframe id="c3" class="detail" style="display: none;" frameborder="0"></iframe>
			<iframe id="c4" class="detail" style="display: none;" frameborder="0"></iframe>

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
			<iframe id="iframe_evaluate" name="iframe_evaluate" src=""	frameborder="0" width="100%"></iframe>
		</div>
		
		<div id="dlg" class="easyui-dialog"
		style="width: 500px; height: 380px; padding: 10px; text-align: center; position:fixed;" 
		data-options="
                top:5,
                iconCls: 'icon-add',
                draggable:false,
                closed:true,
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
		<form id="af" method="post" style="padding: 0px; margin: 0px;">
			<input id="clueId" type="hidden" name="clueId">
			<input id="hiddenFlag" type="hidden" name="" value="">
			<input id="hiddenResultId" type="hidden" value="">
			<table id="addTable">
				<tr>
					<td style="width: 100px" align="right"><span style="font-size: 14px;">反馈内容：</span></td>
					<!-- <td><input style="width: 300px" name="resultDesc" id="resultDesc"
						class="easyui-validatebox"
						data-options="validType:'length[1,100]'" type="text" maxlength="100" value=""></td> -->
					<td><textArea cols="35" rows="2" name="resultDesc" id="resultDesc" maxlength="100"></textArea></td>
				</tr>
				<tr>
					<td align="right"><span style="font-size: 14px; ">来源：</span></td>
					<td><input style="width: 300px" name="resultBy" id="resultBy"
						class="easyui-validatebox"
						data-options="validType:'length[1,100]'" type="text" maxlength="100" value=""></td>
				</tr>
				<tr>
					<td align="right"><span style="font-size: 14px; ">详细说明：</span></td>
					<td><input style="width: 300px" name="resultRemark" id="resultRemark"
						class="easyui-validatebox" data-options="validType:'length[1,100]'" type="text" maxlength="100" value=""></td>
				</tr>
				<tr>
					<td align="right"><span style="font-size: 14px;">登记日期：</span></td>
					<td><input style="width: 300px" name="resultDate" id="resultDate"
						class="easyui-datebox" type="text" value=""></td>
				</tr>
				<tr>
					<td align="right"><span style="font-size: 14px; ">添加附件：</span></td>
					<td><div style='position: relative;'>
							<a class='upload_btn' href='javascript:;'>添加附件</a> <input
								type='file' id="att" class='fileContent'
								onchange="getFileUrl('5',this,'attIds')">
						</div></td>
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
                title:'添加综述并提交反馈',
                buttons: [{
                    text:'确定',
                    handler:function(){
                        next(${procInstId});
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
                title:'完成反馈',
                buttons: [{
                    text:'完成并生成反馈报告',
                    handler:function(){
                        saveNext(${procInstId});
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
