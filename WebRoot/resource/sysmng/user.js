var oldpwd;
// 保存查询条件
var username = '';
var account = '';
var userno = '';
var dept = '';
var gender = '';
var state = '';
$(document).ready(function() {
	// 加载toolbar的功能按钮
	$('#toolbar').toolBar({
		url : '../login/getToolBar',
		page : 100101,
		nowrap : true
	});
	window.setTimeout(function() {
		// 加载数据
		query();
	}, 200)
	$('#qsex').combobox({
		editable : false
	});
	$('#qdept').combotree({
		editable : false
	});
	$('#qstate').combobox({
		editable : false
	});
	$("#gender").combobox({
		editable : false
	});
});
// 全局ajax请求带遮罩及提示
var gridoptions = null;
function query() {
	if (checkSql(hb.StrUtil.trim($("#qname").val()))) {
		sing_msg("查询条件禁止输入特殊字符！");
		$("#qname").select();
		return;
	}
	if (checkSql(hb.StrUtil.trim($("#qaccount").val()))) {
		sing_msg("查询条件禁止输入特殊字符！");
		$("#qaccount").select();
		return;
	}
	if (checkSql(hb.StrUtil.trim($("#quserno").val()))) {
		sing_msg("查询条件禁止输入特殊字符！");
		$("#quserno").select();
		return;
	}
	$('#querylog').dialog('close');
	onloading();// 数据加载等待提示
	// 信息查询加载
	$('#_grid')
			.datagrid(
					{
						toolbar : "#toolbar",// 工具栏
						fitColumns : true,
						rownumbers : true,
						showFooter : false,// 显示底部统计数
						pagination : true,
						pageSize : 20,
					//	width : $(document.body).width(),// 设置grid宽度
						height : document.body.clientHeight - 20,// 设置grid高度
						url : ctxPath + '/usr/getPList',
						queryParams : {
							'page' : gridoptions == null ? '0'
									: gridoptions.pageNumber,
							'rows' : gridoptions == null ? '0'
									: gridoptions.pageSize,
							'username' : hb.StrUtil.trim($("#qname").val()),
							'account' : hb.StrUtil.trim($("#qaccount").val()),
							'userno' : hb.StrUtil.trim($("#quserno").val()),
							'dept' : hb.StrUtil.trim($("#qdept").combotree(
									'getValue')),
							'gender' : hb.StrUtil.trim($("#qsex").combobox(
									'getValue')),
							'state' : hb.StrUtil.trim($("#qstate").combobox(
									'getValue'))
						},
						columns : [ [
								{
									field : 'userId',
									title : 'id',
									align : 'center',
									sortable : true,
									checkbox : true
								},
								{
									field : 'userName',
									title : '用户姓名',
									width : 150,
									align : 'center'
								},
								{
									field : 'loginAccount',
									title : '用户名',
									width : 150,
									align : 'center',
									formatter : function(value, row, index) {
										return value;
									}
								},
								{
									field : 'userNo',
									title : '警号',
									width : 180,
									align : 'center'
								},
								{
									field : 'curState',
									title : '状态',
									width : 180,
									align : 'center',
									formatter : function(value, row, index) {
										if (value == 1) {
											return "<span style='color:red;'>停用</span>";
										} else if (value == 0) {
											return "<span style='color:green;'>启用</span>";
										}
									}
								},
								{
									field : 'idNo',
									title : '身份证号码',
									width : 300,
									align : 'center'
								},
								{
									field : 'age',
									title : '年龄',
									width : 150,
									align : 'center'
								},
								{
									field : 'gender',
									title : '性别',
									width : 150,
									align : 'center',
									formatter : function(value, row, index) {
										return (value == 2 ? "女"
												: (value == 1 ? "男" : ""));
									}
								},
								{
									field : 'deptname',
									title : '所在部门',
									width : 300,
									align : 'center',
									formatter : function(value, row, index) {
										if ("" != value && null != value) {
											if (value.length > 12) {
												return '<span title="' + value
														+ '">'
														+ value.substr(0, 12)
														+ '...</span>';
											} else {
												return '<span title="' + value
														+ '">' + value
														+ '</span>';
											}
										} else {
											return "<span style='color:#999;'>暂无</span>";
										}
									}
								},
								{
									field : 'rolenames',
									title : '拥有角色',
									width : 300,
									align : 'left',
									formatter : function(value, row, index) {
										if ("" != value && null != value) {
											if (value.length > 12) {
												return '<span title="' + value
														+ '">'
														+ value.substr(0, 12)
														+ '...</span>';
											} else {
												return '<span title="' + value
														+ '">' + value
														+ '</span>';
											}
										} else {
											return "<span style='color:#999;'>暂无</span>";
										}
									}
								},
								{
									field : 'mobilePhone',
									title : '联系方式',
									width : 300,
									align : 'center'
								},
								{
									field : 'address',
									title : '联系地址',
									width : 300,
									align : 'left',
									formatter : function(value, row, index) {
										if (value != null && "" != value
												&& value.length > 13) {
											return "<span title='" + value
													+ "'>"
													+ value.substr(0, 13)
													+ "...</span>";
										} else {
											return value;
										}
									}
								},
								{
									field : 'emial',
									title : '邮箱地址',
									width : 300,
									align : 'left',
									formatter : function(value, row, index) {
										if (null != value && "" != value
												&& value.length > 20) {
											return "<span title='" + value
													+ "'>"
													+ value.substr(0, 20)
													+ "...</span>";
										} else {
											return value;
										}
									}
								} ] ],
						onBeforeLoad : function() {
							username = hb.StrUtil.trim($("#qname").val());
							account = hb.StrUtil.trim($("#qaccount").val());
							userno = hb.StrUtil.trim($("#quserno").val());
							dept = hb.StrUtil.trim($("#qdept").combotree(
									'getValue'));
							gender = hb.StrUtil.trim($("#qsex").combobox(
									'getValue'));
							state = hb.StrUtil.trim($("#qstate").combobox(
									'getValue'));
							$('div.datagrid-header-check').children().get(0).checked=false;//取消全选框选中 
							$('div.datagrid-cell-check').each(function(){ 
								$(this).children().get(0).checked=false; 
							}); 
						},
						onLoadSuccess : function(data) {
							// 获取grid分页对象
							gridoptions = $("#_grid").datagrid('getPager')
									.data("pagination").options;
							if (data.total <= 0) {
								sing_msg("没有查询到符合条件的数据！");
							}
							$(this).datagrid("fixRownumber");
							removeload();
						},
						onLoadError : function() {
						},
						onClickRow : function(rowIndex, rowData) {// 加载完毕后获取所有的checkbox遍历
							$("input[type='checkbox']").each(
									function(index, el) {// 如果当前的复选框不可选，则不让其选中
										if (el.disabled == true) {
											$('#_grid').datagrid('unselectRow',
													index - 1);
										}
									});
						}
					});
};
function termquery() {
	dlglistener("querylog");
	$('#querylog').dialog('open');
}
function excelimp() {
	dlglistener("implog");
	$('#implog').dialog('open');
}
function getRows() {
	return $("#_grid").datagrid("getRows");
}
function add() {
	// sing_msg("添加");
	dlglistener("dlg");
	$('#uuaccount').val("");// 情况编辑用户名标志
	$('#dlg').dialog('open');
	$("#af").form('clear');
	$("#gender").combobox('setValue', '1');
	$("#passwd").val("888888");
	$("#af").form('validate');
	$('#department').combotree('tree').tree('options').url = ctxPath
			+ '/deptment/getDeptTree?fid=0';
	$('#department').combotree('tree').tree('reload');
}
function del() {
	// msg("删除",'error');
	var array = new Array();
	array = getSeleces();
	if (array.split(",").length <= 1 && (array == null || "" == array)) {
		sing_msg("请选择待删除的数据！");
		return;
	} else {
		// ajax 请求
		$.messager.confirm('提示', '确认要删除选中用户吗?', function(r) {
			if (r) {
				$.post('del', {
					'ids' : array
				}, function(data) {
					sing_msg(data.msgInfo);
					$("#_grid").datagrid('reload');
				})
			}
		});
	}
}
// 编辑
function edit() {
	// msg("编辑",'quertion');
	var uarray = getSeleces().split(",");
	if (uarray.length > 1) {
		sing_msg("请选择一条数据进行编辑！");
		return;
	} else if (uarray.length <= 1 && (null == uarray || "" == uarray)) {
		sing_msg("请选择待编辑的数据！");
		return;
	} else {
		$.post('getById', {
			'id' : uarray.join(",")
		}, function(data) {
			if (null == data || hb.StrUtil.trim(data + "") == '') {
				sing_msg("该用户已被删除！");
				$("#_grid").datagrid("reload");
			} else {
				dlglistener("udlg");
				oldpwd = data.loginPwd;
				$('#udlg').dialog('open');
				$("#uuid").val(data.userId);
				$("#uuaccount").val(data.loginAccount);// 编辑用户名标志
				$("#uaccount").val(data.loginAccount);
				$("#upasswd").val(data.loginPwd);
				$("#uname").val(data.userName);
				$("#uidcard").val(data.idNo);
				$("#upoliceno").val(data.userNo);
				$("#uage").val(data.age);
				$("#ugender").combobox('setValue', data.gender);
				$("#umobile_phone").val(data.mobilePhone);
				$("#uaddress").val(data.address);
				$("#uemail").val(data.emial);
				$("#udepartment").combotree('setValue', data.deptId);
				// $("#udepartment").combotree('setText',data.deptname);
				// 数据验证
				$("#uf").form('validate');
			}
		})
	}
}
function search1() {
	$("#_grid").datagrid('reload');
}
function msg(msg, type) {
	$.messager.alert("提示", msg, type);// type--null、info、error、question、warning
}
function sing_msg(msg) {
	$.messager.show({
		title : '提示',
		msg : msg,
		showType : 'slide',// show
		timeout : 2000,
		style : {
			right : '',
			top : document.body.scrollTop + document.documentElement.scrollTop,
			bottom : ''
		}
	});
}
function addsave() {
	var isValid = $("#af").form('validate');
	if (isValid) {
		$.post(ctxPath + '/usr/save', {
			'userName' : $("#name").val(),
			'loginAccount' : $("#account").val(),
			'loginPwd' : $("#passwd").val(),
			'gender' : $("#gender").combobox('getValue'),
			'age' : $("#age").val(),
			'idNo' : $("#idcard").val(),
			'userNo' : $("#policeno").val(),
			'mobilePhone' : $("#mobile_phone").val(),
			'address' : $("#address").val(),
			'emial' : $("#email").val(),
			'deptId' : $("#department").combotree('getValue')
		}, function(data) {
			sing_msg(data.msgInfo);
			$("#_grid").datagrid('reload');
			$('#dlg').dialog('close');
		})
	}
	return isValid;
}
// 编辑数据
function upd() {
	var isValid = $("#uf").form('validate');
	if (isValid) {
		$.post('upd', {
			'userId' : $("#uuid").val(),
			'userName' : $("#uname").val(),
			'loginAccount' : $("#uaccount").val(),
			'loginPwd' : ($("#upasswd").val() == oldpwd ? '' : $("#upasswd")
					.val()),
			'gender' : $("#ugender").combobox('getValue'),
			'age' : $("#uage").val(),
			'idNo' : $("#uidcard").val(),
			'userNo' : $("#upoliceno").val(),
			'mobilePhone' : $("#umobile_phone").val(),
			'address' : $("#uaddress").val(),
			'emial' : $("#uemail").val(),
			'deptId' : $("#udepartment").combotree('getValue')
		}, function(data) {
			sing_msg(data.msgInfo);
			$("#_grid").datagrid('reload');
			$('#udlg').dialog('close');
			$('#uuaccount').val("");// 清空编辑账号标志
		})
	}
	return isValid;
}
// 获取选中项
function getSeleces() {
	var ids = [];
	var rows = $('#_grid').datagrid('getSelections');
	for (var i = 0; i < rows.length; i++) {
		ids.push(rows[i].userId);
	}
	return ids.join(',');
}
// 启用
function onuse() {
	var uarray = getSeleces().split(",");
	if (uarray.length <= 1 && (null == uarray || "" == uarray)) {
		sing_msg("请选择待启用的用户！");
		return;
	} else {
		$.post('upuse', {
			'ids' : uarray.join(","),
			'useflag' : 0
		}, function(data) {
			sing_msg(data.msgInfo);
			$("#_grid").datagrid('reload');
		})
	}
}
// 停用
function nouse() {
	var uarray = getSeleces().split(",");
	if (uarray.length <= 1 && (null == uarray || "" == uarray)) {
		sing_msg("请选择待停用的用户！");
		return;
	} else {
		$.messager.confirm('提示', '确认停用选中用户？', function(r) {
			if (r) {
				$.post('upuse', {
					'ids' : uarray.join(","),
					'useflag' : 1
				}, function(data) {
					sing_msg(data.msgInfo);
					$("#_grid").datagrid('reload');
				})
			}
		});

	}
}
// 授予角色
function addRole() {
	var array = new Array();
	array = getSeleces();
	if (array.split(",").length <= 1 && (array == null || "" == array)) {
		sing_msg("请选择待授予角色的用户！");
		return;
	} else {
		dlglistener("treeDialog");
		getRoleTree('roleids', 'name');
	}
}
// 查看权限
function showPower() {
	var uarray = getSeleces().split(",");
	if (uarray.length > 1) {
		sing_msg("请选择一个用户进行查看！");
		return;
	} else if (uarray.length <= 1 && (null == uarray || "" == uarray)) {
		sing_msg("请选择待查看的用户！");
		return;
	} else {
		$.post('getById', {
			'id' : uarray.join(",")
		}, function(data) {
			if (null == data || hb.StrUtil.trim(data + "") == '') {
				sing_msg("该用户已被删除！");
				$("#_grid").datagrid("reload");
			} else {
				dlglistener("treeDialog1");
				getPermissionTree('pid', 'name', uarray, '0');
			}
		})
	}
}
// 密码重置
function resetPw() {
	var uarray = getSeleces().split(",");
	if (uarray.length <= 1 && (null == uarray || "" == uarray)) {
		sing_msg("请选择待重置的用户！");
		return;
	} else {
		$.messager.confirm('提示', '确认初始化选中用户密码?', function(r) {
			if (r) {
				$.post('resetPw', {
					'ids' : uarray.join(","),
					'repw' : '888888'
				}, function(data) {
					sing_msg(data.msgInfo);
					$("#_grid").datagrid('reload');
				})
			}
		});
	}
}
// 导出
function excel() {
	// 验证导出树是否超过最大限制
	$.ajax({
		url : ctxPath + '/usr/expCheck',
		type : 'post',
		dataType : 'text',
		data : {
			"username" : username,
			"account" : account,
			"userno" : userno,
			"dept" : dept,
			"gender" : gender,
			"state" : state
		},
		success : function(data) {
			if (data.split(",")[0] == true || data.split(",")[0] == "true") {
				var url = ctxPath + '/usr/expUsers';// 导出方法只修改该url值和参数即可
				window.location.href = url + "?username=" + username
						+ "&account=" + account + "&userno=" + userno
						+ "&dept=" + dept + "&gender=" + gender + "&state="
						+ state;
			} else {
				sing_msg("导出数据量超出最大导出限制数（" + data.split(",")[1] + "）")
			}
		}
	})
}
// 展示树
function showTree() {
	getTree('departmentid', 'department', 0);
}
function isExists(obj) {
	var account = $("#" + obj).val()
	$.post(ctxPath + '/usr/getByAccount', {
		'account' : account
	}, function(data) {
		if (null != data && "" != data) {
			sing_msg("用户名[" + account + "]已被其他用户使用！");
			$("#" + obj).select()
		}
	})
}
// 自定义验证方法
function policeNo1() {
	var po = $("#policeno").val();
	var reg = /^[a-zA-Z0-9]+$/;
	if (!reg.test(po)) {
		oflag = false;
	} else {
		oflag = true;
	}
}
function policeNo2() {
	var po = $("#upoliceno").val();
	var reg = /^[a-zA-Z0-9]+$/;
	if (!reg.test(po)) {
		oflag = false;
	} else {
		oflag = true;
	}
}
// 自定义验证方法
function accountw() {
	var po = $("#account").val();
	var pattern = /^[a-zA-z0-9_]+$/gi;
	if (!pattern.test(po)) {
		oflag = false;
	} else {
		oflag = true;
	}
}
// 自定义验证方法
function accounts() {
	var po = $("#uaccount").val();
	var pattern = /^[a-zA-z0-9_]+$/gi;
	if (!pattern.test(po)) {
		oflag = false;
	} else {
		oflag = true;
	}
}
// 自定义验证方法
function pwd() {
	var po = $("#passwd").val();
	var pattern = /^\S+$/gi;
	if (!pattern.test(po)) {
		oflag = false;
	} else {
		oflag = true;
	}
}
// 自定义验证方法
function upwd() {
	var po = $("#upasswd").val();
	var pattern = /^\S+$/gi;
	if (!pattern.test(po)) {
		oflag = false;
	} else {
		oflag = true;
	}
}
// 自定义验证方法
function customValid() {
	var baccount = $("#uuaccount").val();
	if (baccount != null && baccount != "" && baccount == $("#uaccount").val()) {// 如果是编辑操作不修改用户名则不验证
		oflag = true;
	} else {
		$.ajax({
			type : 'POST',
			url : ctxPath + '/usr/getByAccount',
			data : {
				'account' : $("#uaccount").val()
			},
			async : false,
			success : function(data) {
				if (data == null || "" == data) {
					oflag = true;
				} else {
					oflag = false;
				}
			}
		});
	}
}
// 下载excel 模板
function getModel() {
	window.location.href = ctxPath + "/usr/getModel";
	return false;
}
// 控制file选择文件格式
function selectionCheck() {
	var file = document.getElementById('impfile');
	var aa = file.value;
	var re = /.xls$/i;
	// var re1 = /.xlsx$/i;
	// ...
	if (!re.test(aa)) {
		sing_msg("只能选择.xls格式的文件！");
		fileclear("impfile");
	}
}
// file 组件清空
function fileclear(id) {
	var filepar = $("#" + id).parent();// 文件控件 父级元素
	var inpstr = '<input   name="impfile" id="impfile" onchange="selectionCheck()" type="file" style="border:1px solid #DDDDDD;width:90%;" />';
	$("#" + id).remove();// 移除旧控件
	filepar.append($(inpstr));// 添加新控件
}
/**
 * ajax 无刷新文件上传
 * @param fileElementId表示文件域ID
 */
function importXls() {
	var file = document.getElementById('impfile');
	var path = file.value;
	if (null == path || "" == path) {
		sing_msg("请先选择要导入的文件！");
		return;
	} else {
		$('#implog').dialog('close');
		onloading();// 数据加载等待提示
		$.ajaxFileUpload({
			url : ctxPath + '/usr/excelImp', // 上传文件的服务端
			secureuri : false, // 是否启用安全提交
			dataType : 'json', // 数据类型 xml / json /text
			fileElementId : 'impfile', // 表示文件域ID
			data : {
				'fileid' : 'impfile'
			},// 请求参数
			success : function(data, status) { // 提交成功后处理函数,html为返回值，status为执行的状态
				removeload();// 隐藏等待层
				sing_msg(data.msg);// 提示信息
				$('#_grid').datagrid('reload');// 刷新数据
				// 解析异常数据
				loaderrordata(data);
			},
			error : function(data, status, e) {
				removeload();
				sing_msg(data.msg);
				$('#_grid').datagrid('reload');
			}
		});
	}
}
function loaderrordata(data) {
	// 解析异常数据
	var str = Trim(data.errormsg, "all").replaceAll("}", "'}").replaceAll("=",
			":'").replaceAll(",", "',").replaceAll("}',{", "},{");
	if ("null" != str && null != str && "[]" != str) {
		str = '{"total":' + data.total + ',"rows":' + str + '}';
		$.messager
				.confirm(
						'提示',
						'是否查看本次导入不合法数据?',
						function(r) {
							if (r) {
								var errors = eval('(' + str + ')'); // (map->json)
								$('#errorinfo')
										.datagrid(
												{
													fitColumns : false,
													rownumbers : true,
													pagination : true,
													height : $("#errorwin")
															.attr("height"),
													columns : [ [
															{
																field : 'errmsg',
																title : '失败原因',
																width : 200,
																align : 'center',
																formatter : function(
																		value,
																		row,
																		index) {
																	var colu = value
																			.substr(
																					0,
																					value
																							.lastIndexOf(']') + 1);
																	if ("" != value
																			&& value.length > 15) {
																		var lastr = value
																				.substr(
																						value
																								.lastIndexOf(']') + 1,
																						(15 - colu.length));
																		return '<a  title="'
																				+ value
																				+ '"><span style="color:red;">'
																				+ colu
																				+ '</span>'
																				+ lastr
																				+ '...</a>';
																	} else {
																		var lastr = value
																				.substr(
																						value
																								.lastIndexOf(']') + 1,
																						value.length
																								- colu.length);
																		return '<a><span style="color:red;">'
																				+ colu
																				+ '</span>'
																				+ lastr
																				+ '</a>';
																	}
																}
															},
															{
																field : 'direct',
																title : '错误位置',
																width : 60,
																align : 'center'
															},
															{
																field : '用户名',
																title : '用户名',
																width : 60,
																align : 'center'
															},
															{
																field : '用户姓名',
																title : '用户姓名',
																width : 60,
																align : 'center'
															},
															{
																field : '年龄',
																title : '年龄',
																width : 60,
																align : 'center'
															},
															{
																field : '性别',
																title : '性别',
																width : 40,
																align : 'center'
															},
															{
																field : '警号',
																title : '警号',
																width : 60,
																align : 'center'
															},
															{
																field : '身份证号码',
																title : '身份证号码',
																width : 120,
																align : 'center'
															},
															{
																field : '所在部门',
																title : '所在部门',
																width : 100,
																align : 'center'
															},
															{
																field : '联系方式',
																title : '联系方式',
																width : 100,
																align : 'center'
															},
															{
																field : '联系地址',
																title : '联系地址',
																width : 120,
																align : 'center'
															},
															{
																field : '邮箱地址',
																title : '邮箱地址',
																width : 120,
																align : 'center'
															} ] ]
												});
								$('#errorwin').dialog('open');
								$('#errorinfo').datagrid({
									loadFilter : pagerFilter
								}).datagrid('loadData', errors);
							}
						});
	}
}
// datagrid 分页工具
function pagerFilter(data) {
	if (typeof data.length == 'number' && typeof data.splice == 'function') { // 判断数据是否是数组
		data = {
			total : data.length,
			rows : data
		}
	}
	var dg = $(this);
	var opts = dg.datagrid('options');
	var pager = dg.datagrid('getPager');
	pager.pagination({
		onSelectPage : function(pageNum, pageSize) {
			opts.pageNumber = pageNum;
			opts.pageSize = pageSize;
			pager.pagination('refresh', {
				pageNumber : pageNum,
				pageSize : pageSize
			});
			dg.datagrid('loadData', data);
		}
	});
	if (!data.originalRows) {
		data.originalRows = (data.rows);
	}
	var start = (opts.pageNumber - 1) * parseInt(opts.pageSize);
	var end = start + parseInt(opts.pageSize);
	data.rows = (data.originalRows.slice(start, end));
	return data;
}
function replaceAddress(obj) {
	$("#" + obj).val(hb.StrUtil.trim($("#" + obj).val()));
}


//页面变化时 数据刷新
$(window).resize(function() {
	setTimeout(function(){
		query();
	},200);
	
});

