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
<!-- 获取树 -->
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
<script type="text/javascript"
	src="${ctx }/resource/sysmng/policeType.js"></script>

<script type="text/javascript"
	src="${ctx}/resource/common/ajaxfileupload.js"></script>
<script type="text/javascript" src="${ctx}/resource/sysmng/user.js"></script>
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
	<!-- 等待加载 遮罩层 -->
	<div id="loading-mask"
		style="position: absolute; top: 0px; left: 0px; width: 100%; height: 100%; background: #F4F4F4; z-index: 20000">
		<div id="pageloading"
			style="width: 200px; height: 15px; position: absolute; top: 50%; left: 50%; margin: -20px 0px 0px -120px; text-align: center; border: 2px solid #ccc; font-size: 14px; padding: 10px; background: white;">
			<img style="vertical-align: middle; float: left;"
				src="../resource/image/loading.gif">页面加载中，请稍后...
		</div>
	</div>
	<!-- 工具栏 -->
	<!--     	<div class="wd98-bg">
			  <div id="toolbar" class="toolbar-style" url="/login/getToolBar" page="100101"></div>
    	</div> -->

	<!-- 数据列 -->

	<div class="wd98-bg">
		<div id="toolbar" class="toolbar-style" url="/login/getToolBar"
			page="100101"></div>
		<div id="_grid"></div>
	</div>
	<!-- 页面隐藏项 -->
	<input type="hidden" value="" id="uuid" />
	<input type="hidden" value="" id="uuaccount" />
	<!-- 添加窗口 -->
	<div id="dlg" class="easyui-dialog"
		style="width: 300px; height: 430px; padding: 10px; text-align: center;"
		data-options="
				top:50,
				draggable:false,
				closed:true,
				title:'新增用户',
				buttons: [{
					text:'保存',
					handler:function(){
						addsave();
					}
				},{
					text:'取消',
					handler:function(){
						$('#dlg').dialog('close');
					}
				}]">
		<form id="af" method="post" style="padding: 0px; margin: 0px;">
			<table style="width: 260px; height: 300px; font-size: 12px;">
				<tr>
					<td width=35% align=right>&nbsp;用户姓名：</td>
					<td width=65% align=left><input class="easyui-validatebox"
						data-options="required:true,validType:['enchina','length[1,30]']"
						name="userName" id="name" type="text"
						style="border: 1px solid #DDDDDD; width: 140px;"></td>
				</tr>
				<tr>
					<td align=right>用户名：</td>
					<td align=left><input class="easyui-validatebox"
						name="loginAccount" id="account" type="text"
						style="border: 1px solid #DDDDDD; width: 140px;"
						data-options="
																		required:true,
																		validType:['others[\'accountw\',\'用户名只能由字母数字和下划线组成！\']','replace[\'be\',\'account\']','remote[ctxPath+\'/usr/getByAccount\',\'account\',\'该用户名已被占用\']','length[1,30]']">
					</td>
				</tr>
				<tr>
					<td align=right>密码：</td>
					<td align=left><input title="默认密码888888" value="888888"
						class="easyui-validatebox"
						data-options="required:true,validType:['others[\'pwd\',\'密码内容不能输入空格！\']','length[1,50]']"
						name="loginPwd" id="passwd" type="password"
						style="border: 1px solid #DDDDDD; width: 140px;"></td>
				</tr>
				<tr>
					<td align=right>性别：</td>
					<td align=left><input name="gender" id="gender" type="text"
						class="easyui-combobox"
						style="border: 1px solid #95B8E7; width: 140px;"
						data-options="
									valueField:'id',
									textField:'name',
									panelHeight:'auto',
									editable:false,
									data:[{id:'1',name:'男'},{id:'2',name:'女'}],
									value:1
							"></td>
				</tr>
				<tr>
					<td align=right>年龄：</td>
					<td align=left><input class="easyui-numberbox"
						data-options="required:true,validType:'int[1,100]'" name="age"
						id="age" type="text"
						style="border: 1px solid #DDDDDD; width: 140px;"></td>
				</tr>
				<tr>
					<td align=right>身份证号码：</td>
					<td align=left><input class="easyui-validatebox"
						data-options="required:true,validType:'idnumber'" name="idNo"
						id="idcard" type="text"
						style="border: 1px solid #DDDDDD; width: 140px;"></td>
				</tr>
				<tr>
					<td align=right>警号：</td>
					<td align=left><input class="easyui-validatebox"
						data-options="required:true,validType:['others[\'policeNo1\',\'警号格式不正确！\']','length[6,30]']"
						name="userNo" id="policeno" type="text"
						style="border: 1px solid #DDDDDD; width: 140px;"></td>
				</tr>
				<tr>
					<td align=right>联系方式：</td>
					<td align=left><input class="easyui-validatebox"
						data-options="validType:['mobilephone']" name="mobilePhone"
						id="mobile_phone" type="text"
						style="border: 1px solid #DDDDDD; width: 140px;"></td>
				</tr>
				<tr>
					<td align=right>联系地址：</td>
					<td align=left><input class="easyui-validatebox"
						data-options="required:true,validType:['length[0,200]']"
						onblur="replaceAddress('address')" name="address" id="address"
						type="text" style="border: 1px solid #DDDDDD; width: 140px;"></td>
				</tr>
				<tr>
					<td align=right>电子邮箱：</td>
					<td align=left><input name="emial" class="easyui-validatebox"
						data-options="validType:['email','length[0,100]']" id="email"
						type="text" style="border: 1px solid #DDDDDD; width: 140px;"></td>
				</tr>
				<tr>
					<td align=right>所属部门：</td>
					<td align=left><input name="deptId" id="department"
						class="easyui-combotree" style="width: 140px;"
						data-options="
											required:true,
											url:ctxPath+'/deptment/getDeptTree?fid=0',
											onBeforeExpand: function(rec){
												if(rec.state=='closed'){
													$('#department').combotree('tree').tree('options').url=ctxPath+'/deptment/getDeptTree?fid=' + rec.id;
												}			
									    	},
							                onSelect:function(rec){
							                	if(rec.id==100){
							                		sing_msg('不能选择所有部门！');
							                		$('#department').combotree('clear');
							                		return false;
							                	}
							                }
								">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 修改窗口 -->
	<div id="udlg" class="easyui-dialog"
		style="width: 300px; height: 430px; padding: 10px; text-align: center;"
		data-options="
				top:50,
				draggable:false,
				title:'编辑用户',
				closed:true,
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
		<form id="uf" method="post" style="padding: 0px; margin: 0px;">
			<table style="width: 260px; height: 300px; font-size: 12px;"
				id="utable">
				<tr>
					<td width=35% align=right>用户姓名：</td>
					<td width=65% align=left><input class="easyui-validatebox"
						data-options="required:true,validType:['enchina','length[1,30]']"
						name="uname" id="uname" type="text"
						style="border: 1px solid #DDDDDD; width: 140px;"></td>
				</tr>
				<tr>
					<td align=right>用户名：</td>
					<td align=left><input class="easyui-validatebox"
						data-options="required:true,
								validType:['others[\'accounts\',\'用户名只能由字母数字和下划线组成！\']','replace[\'be\',\'uaccount\']','others[\'customValid\',\'用户名已被其他用户使用！\']','length[1,30]']"
						name="uamount" id="uaccount" type="text"
						style="border: 1px solid #DDDDDD; width: 140px;"></td>
				</tr>
				<tr>
					<td align=right>密码：</td>
					<td align=left><input title="默认密码888888"
						class="easyui-validatebox"
						data-options="required:true,validType:['others[\'upwd\',\'密码内容不能输入空格！\']','length[1,50]']"
						name="upasswd" id="upasswd" type="password"
						style="border: 1px solid #DDDDDD; width: 140px;"></td>
				</tr>
				<tr>
					<td align=right>性别：</td>
					<td align=left><input name="ugender" id="ugender" type="text"
						class="easyui-combobox"
						style="border: 1px solid #95B8E7; width: 140px;"
						data-options="
									valueField:'id',
									textField:'name',
									panelHeight:'auto',
									editable:false,
									data:[{id:'1',name:'男'},{id:'2',name:'女'}],
									value:0
							"></td>
				</tr>
				<tr>
					<td align=right>年龄：</td>
					<td align=left><input class="easyui-validatebox"
						data-options="required:true,validType:'int[1,100]'" name="uage"
						id="uage" type="text"
						style="border: 1px solid #DDDDDD; width: 140px;"></td>
				</tr>
				<tr>
					<td align=right>身份证号码：</td>
					<td align=left><input class="easyui-validatebox"
						data-options="required:true,validType:'idnumber'" name="uidcard"
						id="uidcard" type="text"
						style="border: 1px solid #DDDDDD; width: 140px;"></td>
				</tr>
				<tr>
					<td align=right>警号：</td>
					<td align=left><input class="easyui-validatebox"
						data-options="required:true,validType:['others[\'policeNo2\',\'警号格式不正确！\']','length[6,30]']"
						name="upoliceno" id="upoliceno" type="text"
						style="border: 1px solid #DDDDDD; width: 140px;"></td>
				</tr>
				<tr>
					<td align=right>联系方式：</td>
					<td align=left><input class="easyui-validatebox"
						name="umobile_phone" id="umobile_phone"
						data-options="validType:['mobilephone']" type="text"
						style="border: 1px solid #DDDDDD; width: 140px;"></td>
				</tr>
				<tr>
					<td align=right>联系地址：</td>
					<td align=left><input class="easyui-validatebox"
						data-options="required:true,validType:['length[0,200]']"
						onblur="replaceAddress('uaddress')" name="uaddress" id="uaddress"
						type="text" style="border: 1px solid #DDDDDD; width: 140px;"></td>
				</tr>
				<tr>
					<td align=right>电子邮箱：</td>
					<td align=left><input name="uemail" class="easyui-validatebox"
						data-options="validType:['email','length[0,100]']" id="uemail"
						type="text" style="border: 1px solid #DDDDDD; width: 140px;"></td>
				</tr>
				<tr>
					<td align=right>所属部门：</td>
					<td align=left><input name="udepartment" id="udepartment"
						class="easyui-combotree"
						style="border: 1px solid #DDDDDD; width: 140px;"
						data-options="
											required:true,
											url:ctxPath+'/deptment/getDeptTree?fid=0',
											onBeforeExpand: function(rec){
												if(rec.state=='closed'){
													$('#udepartment').combotree('tree').tree('options').url=ctxPath+'/deptment/getDeptTree?fid=' + rec.id;
												}			
									    	},
							                onSelect:function(rec){
							                	if(rec.id==100){
							                		sing_msg('不能选择所有部门！');
							                		$('#udepartment').combotree('clear');
							                		return false;
							                	}
							                }
							">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 查询窗口 -->
	<div id="querylog" class="easyui-dialog"
		style="width: 500px; height: 280px; padding: 10px; text-align: center;"
		data-options="
				top:100,
				draggable:false,
				title:'查询用户',
				closed:true,
				buttons: [{
					text:'查询',
					handler:function(){
						query();
					}
				},{
					text:'清空',
					handler:function(){
						$('#qf').form('clear');
					}
				}]">
		<form id="qf" method="post" style="padding: 0px; margin: 0px;">
			<table style="width: 450px; height: 150px; font-size: 12px;"
				id="utable">
				<tr>
					<td width=15% style="height: 30px;" align=right>用户姓名：</td>
					<td width=35% align=left><input name="qname" id="qname"
						type="text" style="border: 1px solid #DDDDDD; width: 140px;"></td>
					<td width=15% height=30 align=right>用户名：</td>
					<td width=35% align=left><input name="qaccount" id="qaccount"
						type="text" style="border: 1px solid #DDDDDD; width: 140px;"></td>
				</tr>
				<tr>
					<td width=15% height=30 align=right>警号：</td>
					<td width=35% align=left><input name="quserno" id="quserno"
						type="text" style="border: 1px solid #DDDDDD; width: 140px;"></td>
					<td width=15% height=30 align=right>所在部门：</td>
					<td width=35% align=left><input name="deptId" id="qdept"
						class="easyui-combotree" style="width: 140px;"
						data-options="
										url:ctxPath+'/deptment/getDeptTree?fid=0',
										onBeforeExpand: function(rec){
											if(rec.state=='closed'){
												$('#qdept').combotree('tree').tree('options').url=ctxPath+'/deptment/getDeptTree?fid=' + rec.id;
											}			
								    	}
							">
					</td>
				</tr>
				<tr>
					<td width=15% height=30 align=right>性别：</td>
					<td width=35% align=left><input name="qsex" id="qsex"
						type="text" class="easyui-combobox"
						style="border: 1px solid #95B8E7; width: 140px;"
						data-options="
								valueField:'id',
								textField:'name',
								panelHeight:'auto',
								editable:false,
								data:[{id:'1',name:'男'},{id:'2',name:'女'}]
						">
					</td>
					<td width=15% height=30 align=right>状态：</td>
					<td width=35% align=left><input name="qstate" id="qstate"
						type="text" class="easyui-combobox"
						style="border: 1px solid #95B8E7; width: 140px;"
						data-options="
								valueField:'id',
								textField:'name',
								panelHeight:'auto',
								editable:false,
								data:[{id:'0',name:'启用'},{id:'1',name:'停用'}]
						">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 数据导入窗口 -->
	<div id="implog" class="easyui-dialog"
		style="width: 500px; height: 200px; padding: 10px; text-align: center;"
		data-options="
				top:100,
				draggable:false,
				closed:true,
				title:'导入用户',
				buttons: [{
					text:'获取模板',
					handler:function(){
						getModel();
					}
				},{
					text:'导入',
					handler:function(){
						importXls();
					}
				},{
					text:'清空',
					handler:function(){
						fileclear('impfile');
					}
				}]">
		<form id="impf" action="" method="post" enctype="multipart/form-data"
			style="padding: 0px; margin: 0px;">
			<table style="width: 450px; height: 50px; font-size: 12px;"
				id="utable">
				<tr>
					<td colspan="2" style="font-size: 12px; color: #999;">请选择.xls格式的文件！</td>
				</tr>
				<tr>
					<td width=20% style="height: 30px;" align=left>请选择文件：</td>
					<td width=80% align=left><input name="impfile" id="impfile"
						onchange="selectionCheck()" type="file"
						style="border: 1px solid #DDDDDD; width: 90%;" /></td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 失败数据提示窗口 -->
	<div id="errorwin" class="easyui-dialog" title="导入失败数据"
		data-options="
			top:30,
			closed:true,
			modal:true,
			buttons: []"
		style="width: 750px; height: 455px; padding: 0px;">
		<div id="errorinfo" style="width: 736px; height: 408px; border: 0px;"></div>
	</div>
	<!-- 部门树 -->
	<div id="treeDialog">
		<ul id="treePanel" class="ztree"></ul>
	</div>
	<!-- 权限树 -->
	<div id="treeDialog1">
		<ul id="treePanel1" class="ztree"></ul>
	</div>
	<!-- 警种绑定窗口 -->
	<div id="PtTreeDialog">
		<ul id="PtTreePanel" class="ztree"></ul>
	</div>
</body>
</html>