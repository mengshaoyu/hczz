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
<script type="text/javascript" src="${ctx}/resource/manager/menu.js"></script>
<script type="text/javascript">
      function formatOpt(val,row){
         var opt = '<span><a href="#" onClick="edit(\''+row.id+'\')"><font color="red">修改</font></a></span>&nbsp;&nbsp;';
         opt+= '<span><a href="#" onClick="del(\''+row.id+'\')"><font color="red">删除</font></a></span>';
	    return opt;
      }
    
    </script>
</head>
<body>
	<center>
		<div>
			<form id='queryForm'>
				<table cellspacing="1" cellpadding="10">
					<tr>
						<td nowrap="nowrap">菜单名称： <input type="text" id="qName"
							name="name" maxlength="20" /> &nbsp; 类型： <input id="qType"
							name="type" style="width: 135px;" /> &nbsp; 状态： <input
							id="qOnuse" name="onuse" style="width: 135px;" />
						</td>
						<td nowrap="nowrap"><a href="#" id="qButton"
							class="easyui-linkbutton" iconCls="icon-search">查询</a>&nbsp;&nbsp;
							<a href="#" id="aButton" class="easyui-linkbutton"
							iconCls="icon-add">添加</a>&nbsp;</td>
					</tr>
				</table>
			</form>
			<table id="dg">
				<thead>
					<tr>
						<th field="sysflg" width="0" hidden="true">系统</th>
						<th data-options="field:'id',checkbox:true"></th>
						<th field="name" width="28%">菜单名称</th>
						<th field="url" width="26%">路径</th>
						<th field="type" width="0" hidden="true">类型</th>
						<th field="typeName" width="9%">类型</th>
						<th field="onUseName" width="9%" align="center">状态</th>
						<th field="orderNum" width="9%" align="center">显示序号</th>
						<th field="sysName" width="9%" align="center">所属系统</th>
						<th field="opt" width="10%" align="center" formatter="formatOpt">操作</th>
					</tr>
				</thead>
			</table>
		</div>
		<div id="editDiv" align="center" style="overflow-y: hidden">
			<div id="msgDivId"
				style="height: 18px; color: red; text-align: left; margin-left: 134px"></div>
			<form id="editForm" name="editForm">
				<table cellspacing="5">
					<tr>
						<td style="text-align: right;">所属系统：</td>
						<td style="text-align: left;"><input id="eSysFlg"
							name="sysflg" /></td>
					</tr>
					<tr>
						<td style="text-align: right;">上级菜单：</td>
						<td style="text-align: left;"><label id="eParentName">无</label>
							<input type="hidden" id="eParentId" name="parent" value='0' /></td>
					</tr>
					<tr>
						<td style="text-align: right;">名称：</td>
						<td><input type="hidden" id="id" name="uuid" /> <input
							type="text" id="eName" name="name" maxlength="20" /></td>
					</tr>
					<tr>
						<td style="text-align: right;">路径：</td>
						<td><input type="text" id="eUrl" name="url" maxlength="100" />
						</td>
					</tr>
					<tr>
						<td style="text-align: right;">类型：</td>
						<td><input id="eType" name="type" /></td>
					</tr>
					<tr>
						<td style="text-align: right;">图标：</td>
						<td><input id="iconCls" name="iconCls"></input></td>
					</tr>
					<tr>
						<td style="text-align: right;">状态：</td>
						<td style="text-align: left;"><input type="radio"
							name="onuse" value="1" checked="checked" />启用 <input
							type="radio" name="onuse" value="0" />停用</td>
					</tr>
					<tr>
						<td style="text-align: right;">显示序号：</td>
						<td><input type="text" id="eOrderNum" name="orderNum"
							maxlength="3" /></td>
					</tr>
				</table>
			</form>
		</div>
		<div id="viewDiv" align="left" title="详情" iconCls="icon-info">
			<table cellspacing="5" cellpadding="1" style="margin-left: 10px;">
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td style="text-align: right; width: 60px;">上级菜单：</td>
					<td style="width: 230px;"><label id="vParent">无</label></td>
				</tr>
				<tr>
					<td style="text-align: right;">名称：</td>
					<td><label id="vName"></label></td>
				</tr>
				<tr>
					<td style="text-align: right;">路径：</td>
					<td><label id="vUrl"></label></td>
				</tr>
				<tr>
					<td style="text-align: right;">类型：</td>
					<td><label id="vType"></label></td>
				</tr>
				<tr>
					<td style="text-align: right;">图标：</td>
					<td><label id="vIconcls"></label></td>
				</tr>
				<tr>
					<td style="text-align: right;">状态：</td>
					<td><label id="vOnuse"></label></td>
				</tr>
				<tr>
					<td style="text-align: right;">显示序号：</td>
					<td><label id="vOrdernum"></label></td>
				</tr>
			</table>
		</div>
	</center>
</body>
</html>