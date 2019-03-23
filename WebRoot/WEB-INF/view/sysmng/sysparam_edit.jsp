<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>系统参数编辑</title>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<meta name="generator" content="MSHTML 8.00.7600.16588">
</head>
<body style="padding: 0px; margin: 0px; border: 0px;">
	<div id="SysParam2"
		style="padding: 10px; text-align: center; top: 0px;">
		<form id="sysParamForm" method="post"
			style="padding: 0px; margin: 0px;">
			<table style="width: 250px; height: 160px; font-size: 12px;"
				id="utable">
				<tr>
					<td align="right">key值：</td>
					<td><input type="hidden" id="sKey" name="id"
						value="${sysparam.id }" /> <input class="easyui-validatebox"
						disabled="disabled" readonly="readonly"
						data-options="required:true,validType:['length[1,50]']"
						name="sysKey" value="${sysparam.sysKey }" id="sSysKey" type="text"
						style="border: 1px solid #DDDDDD; width: 140px;"></td>
				</tr>
				<tr>
					<td align="right">参数值：</td>
					<td><input class="easyui-validatebox" onblur=""
						data-options="required:true,validType:['length[1,200]']"
						name="sysValue" id="sSysValue" value="${sysparam.sysValue }"
						type="text" style="border: 1px solid #DDDDDD; width: 140px;">
					</td>
				</tr>
				<tr>
					<td align="right">参数描述：</td>
					<td><input class="easyui-validatebox" onblur=""
						data-options="validType:['length[1,100]']" name="description"
						value="${sysparam.description }" id="sDescription" type="text"
						style="border: 1px solid #DDDDDD; width: 140px;"></td>
				</tr>
				<tr style="display: none;">
					<td align="right">所属域：</td>
					<td><input name="domainName" id="sDomainName" type="text"
						class="easyui-combobox"
						style="border: 1px solid #95B8E7; width: 140px;"
						data-options="
								valueField:'codeValue',
								textField:'valueDesc',
								panelHeight:'auto',
								url:ctxPath+'/sysparam/getDemainTree',
								value:'${sysparam.domainName }'
								
				">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>