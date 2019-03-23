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
<script type="text/javascript" src="${ctx}/resource/sysmng/dept.js"></script>
<script type="text/javascript">
    	window.onload = function(){
			$('#loading-mask').fadeOut();//页面初始加载，覆盖层
		}
    </script>
<style type="text/css">
.tree li {
	white-space: nowrap;
	height: auto;
	line-height: auto;
}
</style>
</head>
<body class="body-bg">
	<div id="loading-mask"
		style="position: absolute; top: 0px; left: 0px; width: 101%; height: 100%; background: #F4F4F4; z-index: 20000">
		<div id="pageloading"
			style="position: absolute; top: 50%; left: 50%; margin: -120px 0px 0px -120px; text-align: center; border: 0px solid #8DB2E3; width: 0px; height: 0px; font-size: 14px; padding: 10px; font-weight: bold; background: #f4f4f4; color: #15428B;">
		</div>
	</div>

	<!-- 工具栏 -->
	<!--     	<div class="wd98-bg">
			  <div id="toolbar" url="/login/getToolBar" page="100102" class="toolbar-style"></div>
    	</div> -->

	<div class="wd98-bg">
		<div id="toolbar" url="/login/getToolBar" page="100102"
			class="toolbar-style"></div>
		<table id="dg">
			<thead>
				<tr>
					<th field="sysflg" width="0" hidden="true">系统</th>
					<th field="name" width="28%">部门树</th>
				</tr>
			</thead>
		</table>
	</div>
	<div id="vditDiv" class="easyui-dialog"
		style="width: 300px; padding: 10px; text-align: center;"
		data-options="
				top:50,
				width:360,
				draggable:false,
				title:'部门编辑',
				buttons: [{
					text:'保存',
					handler:function(){
						upd();
					}
				},{
					text:'取消',
					handler:function(){
						$('#vditDiv').dialog('close');
					}
				}]">
		<form id="uf" method="post" style="padding: 0px; margin: 0px;">
			<table style="width: 250px; height: 200px; font-size: 12px;"
				id="utable">
				<tr>
					<td align="right">部门编号：</td>
					<td><input class="easyui-numberbox" name="num"
						data-options="validType:['length[1,12]']" id="depNum" type="text"
						style="border: 1px solid #DDDDDD; width: 160px;"></td>
				</tr>
				<tr>
					<td align="right">部门名称：</td>
					<td><input type="hidden" id="id" name="id" /> <input
						class="easyui-validatebox"
						data-options="required:true,validType:['length[1,100]']"
						name="name" id="depName" type="text"
						style="border: 1px solid #DDDDDD; width: 160px;"></td>
				</tr>
				<tr>
					<td align=right>上级部门：</td>
					<td align=left><input type="hidden" id="pid" name="pid" /> <input
						name="num" id="deNum" class="easyui-combotree"
						style="border: 1px solid #DDDDDD; width: 160px;"
						data-options="
						required:true,
						url:'${ctx}/deptment/getDeptTree?fid=0',
						onBeforeExpand: function(rec){
							if(rec.state=='closed'){
								$('#deNum').combotree('tree').tree('options').url=ctxPath+'/deptment/getDeptTree?fid=' + rec.id;
							}			
				    	}
					">
					</td>
				</tr>
				<tr>
					<td align="right">同级排序号：</td>
					<td><input name="orderNum"
						data-options="validType:['length[1,5]']" class="easyui-numberbox"
						id="depOrder" type="text"
						style="border: 1px solid #DDDDDD; width: 160px;"></td>
				</tr>

			</table>
		</form>
	</div>
	<div id="editDiv"
		style="width: 300px; padding: 10px; text-align: center;"
		class="easyui-dialog">
		<form id="editForm" name="editForm">
			<table cellspacing="5"
				style="width: 250px; height: 200px; font-size: 12px;">
				<tr>
					<td align="right" width="40%">部门编号：</td>
					<td><input class="easyui-numberbox" onblur=""
						data-options="validType:['length[1,12]']" type="text" id="aNum"
						name="num" style="border: 1px solid #DDDDDD; width: 160px;" /></td>
				</tr>
				<tr>
					<td align="right" width="60%">部门名称：</td>
					<td><input class="easyui-validatebox"
						data-options="required:true,validType:['length[1,100]']"
						name="name" id="aName" type="text"
						style="border: 1px solid #DDDDDD; width: 160px;"></td>
				</tr>
				<tr>
				<tr>
					<td align=right>上级部门：</td>
					<td align=left><input type="hidden" id="apid" name="pid" /> <input
						type="text" id="aParent" name="pname" readOnly=true
						style="border: 1px solid #DDDDDD; width: 160px;" /></td>
				</tr>
				<tr>
					<td align="right" nowrap="nowrap">同级排序号：</td>
					<td><input class="easyui-numberbox"
						data-options="validType:['length[1,5]']" type="text" id="aOrder"
						name="orderNum" style="border: 1px solid #DDDDDD; width: 160px;" />
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="viewDiv" align="left" title="部门查看" 
		class="easyui-dialog" style="overflow-y: hidden">
		<table cellspacing="5"
			style="width: 280px; height: 240px; font-size: 12px; text-align: center;">
			<tr>
				<td style="text-align: right;" width="40%">部门编号：</td>
				<td style="text-align: left;" width="60%"><label id="vnum"></label>
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">部门名称：</td>
				<td style="text-align: left;"><label id="vname"></label></td>
			</tr>
			<tr>
				<td style="text-align: right;">上级部门：</td>
				<td style="text-align: left;"><label id="vPname"></label></td>
			</tr>
			<tr>
				<td style="text-align: right;">同级排序号：</td>
				<td style="text-align: left;"><label id="vOrdernum"></label></td>
			</tr>
		</table>
	</div>

</body>
</html>