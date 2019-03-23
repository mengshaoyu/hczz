<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>合成作战平台</title>

<meta http-equiv="x-ua-compatible" content="ie=7" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="${ctx}/resource/style/skynetIcon.css" />

<script type="text/javascript" src="${ctx}/resource/sysmng/code.js"></script>
<script type="text/javascript" src="${ctx}/resource/common/validate.js"></script>
<script type="text/javascript">
    	window.onload = function(){
			$('#loading-mask').fadeOut();//页面初始加载，覆盖层
		}
    </script>
<style>
body,html{
	height:100%;
}
</style>
</head>
<body class="body-bg">
	<!-- 等待加载 遮罩层 -->
	<div id="loading-mask"
		style="position: absolute; top: 0px; left: 0px; width: 101%; height: 100%; background: #F4F4F4; z-index: 20000">
		<div id="pageloading"
			style="position: absolute; top: 50%; left: 50%; margin: -120px 0px 0px -120px; text-align: center; border: 0px solid #8DB2E3; width: 0px; height: 0px; font-size: 14px; padding: 10px; font-weight: bold; background: #f4f4f4; color: #15428B;">
		</div>
	</div>
	<!-- 工具栏 -->
	<div class="wd98-bg">
		<div id="toolbar" url="/login/getToolBar" page="100104"
			class="toolbar-style"></div>
	</div>
	<div class="wd98-bg">
		<table id="cdlist">
			<thead>
				<tr>
					<th data-options="field:'typeId',checkbox:true"></th>
					<th field="typeName" width="20%" align="center">字典名称</th>
					<th field="typeDesc" width="40%" align="left"
						data-options="formatter:function(value,row,index){
              		if(value!=null && value!=('')){
	              		if(value.length>30){
	              			return '<a title=\''+value+'\'>'+value.substr(0,30)+'...</a>';
						}else{ return value; } }else{ return value; } }">描述
					</th>
					<th field="editFlag" width="10" align="center">读写可控</th>
					<th data-options="field:'domainName',width:30,align:'center'">所属域</th>
				</tr>
			</thead>
		</table>
	</div>

	<div id="udlg" class="easyui-dialog"
		style="width: 320px; height: 420px; padding: 0px; text-align: center;"
		data-options="
				title:'字典表编辑',
				draggable:false,
				buttons: [{
					text:'保存',
					handler:function(){
						upd();
					}
				},{
					text:'取消',
					handler:function(){
						$('#udlg').dialog('close');
					}
				}]">
		<form id="uf" method="post"
			style="padding: 0px; margin: 0px; width: 100%">
			<table id="t1" style="height: 200">
				<tr>
					<td><input class="easyui-validatebox"
						data-options="required:true,validType:['length[1,10]']"
						name="typeId" id="typeId" type="hidden"
						style="border: 1px solid #DDDDDD; width: 180px;"></td>
				</tr>
				<tr>
					<td><input class="easyui-validatebox"
						data-options="required:true,validType:['length[1,10]']"
						name="editFlag" id="editFlag" type="hidden"
						style="border: 1px solid #DDDDDD; width: 180px;"></td>
				</tr>
				<tr>
					<td width=35% align=right>字典名称：</td>
					<td><input class="easyui-validatebox"
						data-options="required:true,validType:['length[1,50]']"
						name="typeName" id="typeName" type="text"
						style="border: 1px solid #DDDDDD; width: 180px;"></td>
				</tr>
				<tr>
					<td width=35% align=right>描述：</td>
					<td><textarea class="easyui-validatebox" height="30"
							data-options="required:true,validType:['length[1,200]']"
							name="typeDesc" id="typeDesc" type="text"
							style="border: 1px solid #DDDDDD; width: 180px; font-family: arial;"></textarea>
					</td>

				</tr>
				<tr height="30">
					<td width=35% align=right>所属域：</td>
					<td><input readonly="readonly" class="easyui-validatebox"
						height="30"
						data-options="required:true,validType:['length[1,50]']"
						name="domainName" id="domainName" type="text"
						style="border: 1px solid #DDDDDD; width: 180px; background-color: white;" />
					</td>
				</tr>

			</table>


			<table id="t2" class="easyui-datagrid"
				style="height: 230px; width: 300px">
				<thead>
					<tr>
						<th></th>
						<th></th>
						<th><a href="#" onclick="append()" id="aButton"><img
								style="border: 0"
								src="${ctx}/resource/common/jquery-easyui-1.3.5/icons/edit_add.png" /></a>&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="#" onclick="removeit()" id="bButton"><img
								style="border: 0"
								src="${ctx}/resource/common/jquery-easyui-1.3.5/icons/edit_remove.png" /></a>
						</th>
					</tr>
					<tr>
						<th data-options="field:'pk',checkbox:true"></th>
						<th data-options="field:'valueDesc',width:140,align:'center'"
							editor="{type:'validatebox',options:{required:true,validType:['length[1,50]']}}">字典值名称</th>
						<th data-options="field:'codeValue',width:120,align:'center'"
							editor="{type:'validatebox',options:{required:true,validType:['length[1,20]']}}">字典值</th>

					</tr>
				</thead>


			</table>

		</form>

	</div>




	<div id="udlg1" class="easyui-dialog"
		style="width: 380px; height: 420px; padding: 10px; text-align: center;"
		data-options="
				draggable:false,
				title:'字典表查看'
				">
		<form id="uf1" method="post"
			style="padding: 0px; margin: 0px; width: 100%">
			<table id="t11" style="height: 200">
				<tr>
					<td><input class="easyui-validatebox"
						data-options="required:true,validType:['length[1,30]']"
						name="typeId1" id="typeId1" type="hidden"
						style="border: 1px solid #DDDDDD; width: 180px;"></td>
				</tr>
				<tr>
					<td><input class="easyui-validatebox"
						data-options="required:true,validType:['length[1,30]']"
						name="editFlag1" id="editFlag1" type="hidden"
						style="border: 1px solid #DDDDDD; width: 180px;"></td>
				</tr>
				<tr>
					<td width=35% align=right>字典名称：</td>
					<td><input readonly="readonly" data-options="required:true"
						name="typeName1" id="typeName1" type="text"
						style="border: 1px solid #DDDDDD; width: 180px; background-color: white;">
					</td>
				</tr>
				<tr height="30">
					<td width=35% align=right>描述：</td>
					<td height="30"><textarea readonly="readonly" height="30"
							data-options="required:true" name="typeDesc1" id="typeDesc1"
							type="text"
							style="border: 1px solid #DDDDDD; width: 180px; background-color: white; font-family: arial;"></textarea>
					</td>

				</tr>
				<tr height="30">
					<td width=35% align=right>所属域：</td>
					<td height="30"><input readonly="readonly" height="30"
						data-options="required:true" name="domainName1" id="domainName1"
						type="text"
						style="border: 1px solid #DDDDDD; width: 180px; background-color: white;" />
					</td>
				</tr>

			</table>

			<br> <br>
			<table id="t21" class="easyui-datagrid"
				style="height: 190px; width: 330px">
				<thead>
					<tr>
						<th data-options="field:'valueDesc',width:140,align:'center'"
							readonly="readonly"
							editor="{type:'validatebox',options:{required:true,validType:['length[1,10]']}}">字典值名称</th>
						<th data-options="field:'codeValue',width:140,align:'center'"
							readonly="readonly"
							editor="{type:'validatebox',options:{required:true,validType:['length[1,10]']}}">字典值</th>

					</tr>
				</thead>


			</table>
		</form>

	</div>
</body>
</html>
