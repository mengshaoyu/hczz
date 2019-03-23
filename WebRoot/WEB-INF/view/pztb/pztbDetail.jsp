<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<link rel="stylesheet" type="text/css" href="${ctx}/resource/style/sop/pztbDetail.css" />
<script type="text/javascript" src="${ctx}/resource/pztb/pztbDetail.js"></script>
<script type="text/javascript">
	
	/* function openMap1(){
		var mapX = $.trim($("#mapX").html());
		var mapY = $.trim($("#mapY").html());
		$('#mapmarkframe')[0].src = ctxPath + "/hbmap/init?x="+mapX+"&y="+mapY+"&editable=0";
		opendialog('mapmark1');
	} */
</script>
<style type="text/css">
	html{
		margin:10px 0;
	}
</style>
</head>
<body style="padding: 0px; margin: 0px; border: 0px;">
	<div class="outerborder" id="first-div">
	<form id="ajxx_form">
		<div id="ajxx_panel">
		    <div class="panelTitle panelTitle-noTopBorder">案件信息</div>
			<div class="panelContent">
				<table class="panelTable ajxx" cellspacing="0" cellpadding="0" style="table-layout: fixed;">
					<tr>
						<td class="tb-left"><span class="requ">*</span>案件编号</td>
						<td class="tb-right"><div class="border-div">${cases.caseNo}</div></td>
						<td class="tb-left">警情编号</td>
                        <td class="tb-right">
                        	<div class="border-div">
                        		<c:if test="${not empty cases.jqNo}">${cases.jqNo}</c:if>
                        		<c:if test="${empty cases.jqNo}">&nbsp;</c:if>
                        	</div>
                        </td>
                        <td class="tb-left">案件来源</td>
                        <td class="tb-right">
                        	<div class="border-div">
                        		<c:if test="${not empty cases.caseAjly}">${cases.caseAjly}</c:if>
                        		<c:if test="${empty cases.caseAjly}">&nbsp;</c:if>
                        	</div>
                        </td>
					</tr>
					<tr>
					    <td class="tb-left"><span class="requ">*</span>案件名称</td>
                        <td class="tb-right" title="${cases.caseName}"><div class="border-div">${cases.caseName}</div></td>
						<td class="tb-left">案件类型</td>
                        <td class="tb-right">
                        	<div class="border-div" >
                        		<c:if test="${not empty cases.caseTypeImp}">${cases.caseTypeImp}</c:if>
                        		<c:if test="${empty cases.caseTypeImp}">&nbsp;</c:if>
                        	</div>
                        </td>
						<td class="tb-left">案件状态</td>
                        <td class="tb-right">
                        	<div class="border-div">
                        		<c:if test="${not empty cases.caseStatus}">${cases.caseStatus}</c:if>
                        		<c:if test="${empty cases.caseStatus}">&nbsp;</c:if>
                        	</div>
                        </td>
					</tr>
					<tr>
					    <td class="tb-left">案别</td>
                        <td class="tb-right">
                        	<div class="border-div">
                        		<c:if test="${not empty cases.caseType}">${cases.caseType}</c:if>
                        		<c:if test="${empty cases.caseType}">&nbsp;</c:if>
                        	</div>
                        </td>
                        <td class="tb-left">案发地点</td>
                        <td class="tb-right"  title="${cases.incidentPlace}">
                        	<div class="border-div" style="overflow:hidden" title="${cases.incidentPlace}">
                        		<c:if test="${not empty cases.incidentPlace}">${cases.incidentPlace}</c:if>
                        		<c:if test="${empty cases.incidentPlace}">&nbsp;</c:if>
                        	</div>
                        </td>
						<td class="tb-left">受案单位</td>
						<td class="tb-right">
							<div class="border-div">
								<c:if test="${not empty cases.acceptUnit}">${cases.acceptUnit}</c:if>
                        		<c:if test="${empty cases.acceptUnit}">&nbsp;</c:if>
							</div>
						</td>
						
					</tr>
					<tr>
					    <td class="tb-left">地理坐标</td>
					    <td class="tb-right">
					    	<div style="float:left; width:45%;">
						       <span class="dlzbspan">X</span>
						       	<div class="border-div" style="width:76%; float:left;" id="mapX">
						       		<c:if test="${not empty cases.latX}">${cases.latX}</c:if>
	                        		<c:if test="${empty cases.latX}">&nbsp;</c:if>
						       	</div>
					    	</div>
					    	<div style="float:left; width:45%;">
						       <span class="dlzbspan">Y</span>
						       	<div class="border-div" style="width:76%; float:left;" id="mapY">
						       		<c:if test="${not empty cases.lonY}">${cases.lonY}</c:if>
	                        		<c:if test="${empty cases.lonY}">&nbsp;</c:if>
						       	</div>
					    	</div>
					       <!-- <div id="markpointbtn" style="float:left;margin-left:5px;" onclick="openMap()">&nbsp;</div> -->
					    </td>
					    <td class="tb-left">案发时间</td>
                        <td class="tb-right">
                        	<div class="border-div">
								<c:if test="${not empty cases.incidentDate}">${cases.incidentDate}</c:if>
                        		<c:if test="${empty cases.incidentDate}">&nbsp;</c:if>
							</div>
                        </td>
					    <td class="tb-left"><span class="requ">*</span>受案时间</td>
                        <td class="tb-right">
                        	<div class="border-div">
								<c:if test="${not empty cases.acceptDate}">${cases.acceptDate}</c:if>
                        		<c:if test="${empty cases.acceptDate}">&nbsp;</c:if>
							</div>
                        </td>
					</tr>
					<tr>
						<td class="tb-left"><span class="requ">*</span>简要案情</td>
						<td class="tb-right" colspan="5" style="height: 110px;">
							<textarea rows="5" cols="40" style="border:1px solid #e5e5e5;height:110px;padding:5px;" readonly="readonly">${cases.caseDesc}</textarea>
						</td>
					</tr>
				</table>
			</div>
		</div>
        <div id="flws_panel">
            <div class="panelTitle">法律文书&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </div>
            <table class="panelTable cxnr" style="table-layout: fixed;">
            	<c:if test="${not empty sadjbcount and not empty lajdscount}">
           		 <tr>
                	<td class="tb-right" align="left" style="margin-left:10px;border-bottom:0px none;">
               			<div style="height:14px;line-height:14px;min-height:14px;margin-left:30px;margin-bottom:20px;">
                			系统自动检测，当前案件在执法闭环管理系统中
			            	<c:choose>
			            		<c:when test="${sadjbcount>0}">
			            			已生成受案登记表，
			            		</c:when>
			            		<c:otherwise>
			            			未生成受案登记表，
			            		</c:otherwise>
			            	</c:choose>
			            	<c:choose>
			            		<c:when test="${lajdscount>0}">
			            			已生成立案决定书。
			            		</c:when>
			            		<c:otherwise>
			            			未生成立案决定书。
			            		</c:otherwise>
			            	</c:choose>
		            	</div>
                	</td>
				</tr>
            	</c:if>
            	<c:if test="${not empty flws}">
	            	<tr>
	                	<td class="tb-right" align="left">                	
	                		<div style="height:auto; min-height:30px;">
	                        	<c:forEach items="${flws}" var="flws" varStatus="status">
									<a href="${flws.attPath}" target="_blank" class="fjspancont" title="${flws.attName}" style="color: #0080FF;height:16px;line-height:14px;">${flws.attName}</a>
	                			</c:forEach>
	                			<div class="clear"></div>
	                    	</div>
	                	</td>
					</tr>
            	</c:if>
            </table>
        </div>
        <div id="task_panel">
            <div class="panelTitle">选择任务级别</div>
            <div class="panelContent">
                <table class="panelTable task" style="table-layout: fixed; margin-bottom: 10px;">
                    <tr>
                        <td class="tb-left"><span class="requ">*</span>配帧级别</td>
                        <td class="tb-right"><div class="border-div">${pv.pzApplyGradeName}</div>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                    </tr>
                </table>
            </div>
        </div>
		<div id="cxnr_panel">
			<div class="panelTitle">查询内容</div>
			<div class="panelContent">
        	<c:forEach items="${cluesList}" var="cluesList" varStatus="status">
        			<c:if test="${status.index == 0}">
        				<table class="panelTable cxnr" style="table-layout: fixed;">
        			</c:if>
						<c:if test="${status.index>0}">
							<!-- <table class="panelTable cxnr">
							<tr>
								<td colspan="6">
					        		<hr style="margin:0px;color:#e5e5e5;width:100%;"/>
								</td>
							</tr> -->
							<table class="panelTable cxnr" style="table-layout: fixed;">
						</c:if> 
						<tr>
							<td class="tb-left"><span class="requ">*</span>查询要求</td>
							<td class="pzTypeDetail_td tb-right" title="${cluesList.pzTypeDetailName}">
								<div class="border-div">${cluesList.pzTypeDetailName}</div>
							<td class="tb-left"><span class="requ">*</span>线索来源</td>
	                        <td class="tb-right"><div class="border-div" title="${cluesList.clueSource}">${cluesList.clueSource}</div></td>
	                        <td class="tb-left"><span class="requ">*</span>查询内容</td>
	                        <td class="tb-right"><div class="border-div" title="${cluesList.clueName}">${cluesList.clueName}</div></td>
						</tr>
						<tr>
						    <td class="tb-left">来源证明</td>
	                        <td  class="tb-right" colspan="5">
	                           <div class="border-div" style="width:99.9%;height: auto;text-indent: 0;min-height: 32px">
	                               <c:forEach items="${cluesList.att}" var="att" varStatus="status">
	                        			<a href="${att.attPath}" title="${att.attName}" target="_blank" style="margin:0 10px 10px 10px;color: #0080FF">${att.attName}</a>
	                        		</c:forEach>
	                           </div>
	                        </td>
						</tr>
						<tr>
							<td class="tb-left">详细描述</td>
							<td class="tb-right" colspan="5">
								<textarea rows="5" cols="40" style="border:1px solid #e5e5e5" readonly="readonly">${cluesList.clueDesc}</textarea>
							</td>
						</tr>
					</table>
					</br>
			</c:forEach>
			</div>
		</div>
	</form>
    </div>
    <!-- 地图选点dialog -->
    <div id="mapmark" class="easyui-dialog" closed="true" data-options="modal:true,closable: false" buttons="#mapmark-buttons" title="地图标点" style="width:600px;height:400px;top:40px">
        <iframe scrolling="auto" id='mapmarkframe' name="mapmarkframe" frameborder="0"  src="" style="width:100%;height:98%;"></iframe>
    </div> 
    <div id="mapmark-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closedialog('mapmark')">关闭</a>
    </div>
</body>
</html>