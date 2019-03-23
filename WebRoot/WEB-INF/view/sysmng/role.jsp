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
<link rel="stylesheet" type="text/css"
	href="${ctx}/resource/style/skynetIcon.css" />
<link rel="stylesheet"
	href="${ctx}/resource/common/jQuery_zTree/css/zTreeStyle/zTreeStyle.css"
	type="text/css">
<script type="text/javascript"
	src="${ctx}/resource/common/jQuery_zTree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript"
	src="${ctx}/resource/common/jQuery_zTree/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="${ctx }/resource/sysmng/roleTree.js"></script>
<script type="text/javascript"
	src="${ctx }/resource/sysmng/permissionTree.js"></script>
<script type="text/javascript" src="${ctx}/resource/sysmng/role.js"></script>
<script type="text/javascript">
    	window.onload = function(){
			$('#loading-mask').fadeOut();//页面初始加载，覆盖层
		}
    </script>
</head>
<body class="body-bg">
	<div id="loading-mask"
		style="position: absolute; top: 0px; left: 0px; width: 101%; height: 100%; background: #F4F4F4; z-index: 20000">
		<div id="pageloading"
			style="position: absolute; top: 50%; left: 50%; margin: -120px 0px 0px -120px; text-align: center; border: 0px solid #8DB2E3; width: 0px; height: 0px; font-size: 14px; padding: 10px; font-weight: bold; background: #f4f4f4; color: #15428B;">
		</div>
	</div>
	<!-- 工具条 -->
	<!-- 	  <div class="wd98-bg">
	  	<div id="toolbar" url="/login/getToolBar" page="100103"  class="toolbar-style"></div>
	  </div> -->

	<!-- 数据列 -->
	<div class="wd98-bg">
		<div id="toolbar" url="/login/getToolBar" page="100103"
			class="toolbar-style"></div>
		<div id="dg"></div>
	</div>
	<div id="vditDiv" class="easyui-dialog"
		style="width: 290px; padding: 10px; text-align: center;"
		data-options="
				buttons: [{
					text:'保存',
					handler:function(){
						addsave();
					}
				},{
					text:'取消',
					handler:function(){
						$('#vditDiv').dialog('close');
					}
				}]">
		<form id="vditForm" name="vditForm">
			<table cellspacing="5"
				style="width: 250px; height: 90px; font-size: 12px;">
				<tr>
					<td width=35% align=right>角色名称：</td>
					<td width=65% align=left><input id="roleId" name="roleId"
						type="hidden" /> <input class="easyui-validatebox"
						data-options="required:true,validType:['length[1,50]']"
						name="rolename" id="vName" onblur="" type="text"
						style="border: 1px solid #DDDDDD; width: 140px;"></td>
				</tr>
				<tr>
					<td width=35% align=right>描述：</td>
					<td width=65% align=left><textarea class="easyui-validatebox"
							data-options="validType:['length[1,200]']" name="description"
							id="vDescrip" style="border: 1px solid #95B8E7; width: 140px;"></textarea>
					</td>
				</tr>
			</table>
		</form>

	</div>

	<!-- 权限树 -->
	<div id="treeDialog1">
		<ul id="treePanel1" class="ztree"></ul>
	</div>
	<div id="treeDialog2">
		<ul id="treePanel2" class="ztree"></ul>
	</div>
	<!-- 数据权限 -->
	<div id="dataDiv" class="easyui-dialog">
		<form id="dataForm">
			<table style="width: 250px; height: 150px; font-size: 12px;">
				<tr>
					<td align="left" colspan="2">请填选数据权限：</td>
				</tr>
				<tr>
					<td align="center" width="30%">&nbsp;</td>
					<td align="left" width="70%" title="用户不控制数据权限"
						style="line-height: 23px;"><input type="checkbox" value="1"
						align="left" name="dataPow" id="all"
						style="float: left; margin-right: 5px;">所有(不控制)</input></td>
				</tr>
				<tr>
					<td align="center" width="30%">&nbsp;</td>
					<td align="left" title="进行部门数据权限控制" style="line-height: 23px;">
						<input type="checkbox" align="left" name="allPow1" id="dept"
						style="float: left; margin-right: 5px;" />部门权限
					</td>
				</tr>
				<tr>
					<td align="center" width="30%">&nbsp;</td>
					<td align="lefth" title="控制人员数据权限" style="line-height: 23px;">
						<input type="checkbox" align="left" name="allPow2" id="peo"
						style="float: left; margin-right: 5px;" />人员权限
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>