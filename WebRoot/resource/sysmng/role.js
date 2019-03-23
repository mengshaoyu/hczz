$(function(){
	$('#vditDiv').dialog('close');
	$('#dlg').dialog('close');
	$('#toolbar').toolBar({
		url:'../login/getToolBar',
		page:100103
	});
	window.setTimeout(function(){
		//加载数据
		query();
	},200)
	$('#treeDialog1').dialog( {
		width : 250,
		height : 400,
		top: 50,
		collapsible : false,//折叠
		minimizable : false,//最小化
		maximizable : false,//最大化
		title : '授予权限',
		draggable:false,
		modal : true,//遮罩
//		iconCls : 'icon-role',//图标
		closed : true,//默认隐藏
		buttonAlign : 'left',
		buttons : [ {
			text : '确定',
//			iconCls : 'icon-ok',
			handler : function() {
				getChecked();
			}
		//}, {
		//	text : '清空',
			//handler : function() {
			//	var zTree = $.fn.zTree.getZTreeObj("treePanel");
			//	zTree.checkAllNodes(false);
			//}
		},{
			text : '取消',
//			iconCls:'icon-canceling',
			handler : function() {
			//	$('#' + returnId).val('');
			//	$('#' + returnName).val('');
			$('#treeDialog1').dialog('close');
		}
		} ]
	});
	
	$('#treeDialog2').dialog( {
		width : 270,
		height : 380,
		top : 50,
		collapsible : false,//折叠
		minimizable : false,//最小化
		maximizable : false,//最大化
		title : '查看权限',
		draggable:false,
		modal : true,//遮罩
//		iconCls : 'icon-search',//图标
		closed : true,//默认隐藏
		buttonAlign : 'left'
	});
	
	$('#dataDiv').dialog({
		closed : true,
		title : '数据权限',
		width : 300,
		height : 300,
		draggable:false,
		top : 50,
		modal : true,
//	    iconCls : 'icon-filter',
	    buttons : [{
	    	text : '保存',
			handler : function(){
				addDataPow();
			} 
	    }, {
	    	text : '取消',
//	    	iconCls:'icon-canceling',
	    	handler : function(){
	    		$('#dataDiv').dialog('close');
	    	}
	    }]
		
	});
});

var gridoptions = null;
function query(){
	$('#dg').datagrid({
		fitColumns:true,
		url:'rolePList',
		rownumbers:true,
		showFooter: false,//显示底部统计数
		pagination:true,
		pageSize:20,
	//	width:$(document.body).width(),//设置dg宽度
		height:document.body.clientHeight - 20,//设置dg高度
		singleSelect:false,
		toolbar :"#toolbar",
		queryParams : {
			'page':gridoptions==null?'0':gridoptions.pageNumber,
			'rows':gridoptions==null?'0':gridoptions.pageSize
		},
		columns:[[
			{field:'roleId',title:'id',align:'center',sortable:true,checkbox:true},
			{field:'rolename',title:'角色名称',width:40,align:'left',formatter:function(value,row,index){
					if(value.length>12){
						return '<span title="'+value+'">'+value.substr(0,12)+'...</span>';							
					}else{
						return '<span title="'+value+'">'+value+'</span>'
					}
				
			}},
			{field:'authType',title:'数据权限',width:40,align:'left'},
			{field:'description',title:'描述',width:80,align:'left',formatter:function(value,row,index){
					if(null!=value && value.length>30){
						return '<span title="'+value+'">'+value.substr(0,30)+'...</span>';							
					}else if(null!=value){
						return '<span title="'+value+'">'+value+'</span>'
					}else{
						return '';
					}
				
			}}
			
		]],
		onLoadSuccess:function(data){
			//获取dg分页对象
			if(hb.Obejct.parseJSON(data)){
			gridoptions = $("#dg").datagrid('getPager').data("pagination").options;
			}
		},
		onBeforeLoad:function(){
			$('div.datagrid-header-check').children().get(0).checked=false;//取消全选框选中
			$('div.datagrid-cell-check').each(function(){
				$(this).children().get(0).checked=false;
			});
		}
	
	});
}

function add(){
	$('#vditForm').form("clear");
	$('#roleId').val("");
	$('#vName').val("");
	$('#vDescrip').val("");
	dlglistener("vditDiv");
	$("#vditDiv").dialog({ 
		top : 50,
		closed:false,
		draggable:false,
        title:'角色新增',
//        iconCls:'icon-add',
        buttons: [{
					text:'保存',
//					iconCls:'icon-ok',
					handler:function(){
						addsave();
					}
				},{
					text:'取消',
//					iconCls:'icon-canceling',
					handler:function(){
						$('#vditDiv').dialog('close');
					}
				}]
    });
}

function addsave(){
	var isValid = $("#vditForm").form('validate');
	if (isValid){
		var url;
        if($('#roleId').val() == ''){
            url = 'add'; 
        }else{
            url = 'edit';
        }
		var fields =$('#vditForm').serializeArray();
		var rolename = $('#vName').val();
		var roleId=$('#roleId').val();
		$.post(
			'checkRole',
			{'rolename':rolename,'roleId':roleId},
			function(data){
				if(hb.Obejct.parseJSON(data)){
				if(data.msgInfo != 0){
					$.messager.alert("提示","您输入的角色名已经存在，请重新输入！");
				}else{
					$.post(
						url,
						fields,
						function(data){
							if(hb.Obejct.parseJSON(data)){
								//添加成功
								$('#vditDiv').dialog('close');
								sing_msg(data.msgInfo);
								$("#dg").datagrid('reload');
							}
						});
					}
				}
			});
			
	}
	return isValid;
}

function edit(){
	$('#vditForm').form("clear");
	var rows = $('#dg').datagrid('getSelections');
	var array = getSeleces();
	if(rows.length>1){
		sing_msg("请选择一个角色进行编辑！");
		return
	}else if(rows.length<1){
		sing_msg("请选择待编辑的数据！");
		return;
	}else{
		dlglistener("vditDiv");
		$.post(
			'checkRoleById',
			{'ids':array},
			function(data){
				if(hb.Obejct.parseJSON(data)){
				if(data.msgInfo==1||data.msgInfo=='1'){
					$('#roleId').val(rows[0].roleId);
					$('#vName').val(rows[0].rolename);
					$('#vDescrip').val(rows[0].description);
					$("#vditForm").form('validate');
					$("#vditDiv").dialog({ 
						top : 50,
						height : 250,
						closed:false,
				        title:'角色编辑',
//				        iconCls:'icon-edit',
				        buttons: [{
								text:'保存',
//								iconCls:'icon-ok',
								handler:function(){
									addsave();
								}
							},{
								text:'取消',
//								iconCls:'icon-canceling',
								handler:function(){
									$('#vditDiv').dialog('close');
								}
							}]
			    	});
			  	}else{
					sing_msg("该角色已被删除！");	
					$('#dg').datagrid('reload');
				}
				}
			});
	}
}

function del(){
	//msg("删除",'error');
	var array = new Array();
	array = getSeleces();
	if(array.split(",").length<=1 && (array==null || ""==array) ){
		sing_msg("请选择需要删除的数据！");
		return;
	}else if(array.split(",").length>1){
		sing_msg("请选择一条数据进行删除！");
		return;
	}else{
		//ajax 请求
		$.messager.confirm('提示', '您确认想要删除选中的数据吗？', function(r){
			if (r){
				if (r){
				$.post('CheckDept',{'ids':array},function(data){
					if(hb.Obejct.parseJSON(data)){
					if(data.msgInfo != 0 ){
						$.messager.alert('提示',data.msgInfo+"正被用户使用，不能删除！",'info');
						}else{
						$.post('del',{'ids':array},function(data){
							if(hb.Obejct.parseJSON(data)){
								sing_msg(data.msgInfo);
								$("#dg").datagrid('reload');
							}
					})
					}
					}
				})
			}
		}
		});
}
}
//授予权限
function pow(){
	var array = getSeleces();
	var uarray = getSeleces().split(",");
	if(uarray.length<=1 && (null==uarray || ""==uarray)){
		sing_msg("请选择待授予权限的角色！");
		return;
	}else if(uarray.length>1){
		sing_msg("请选择一个角色进行授权！");
		return;
	}else{
		$.post(
			'checkRoleById',
			{'ids':array},
			function(data){
				if(hb.Obejct.parseJSON(data)){
					if(data.msgInfo==1||data.msgInfo=='1'){
						getPermissionTree('pid','name',uarray,'2');
					}else{
						sing_msg("该角色已被删除！");	
						$('#dg').datagrid('reload');
					}
				}
			});
	}
}

//加载授予权限面板树
function getPermissionTree(returnid, returnname,uid,flag){
	$('#treeDialog1').window('open');
	URL = ctxPath+"/permission/getPermissionTree?uid="+uid+"&flag="+flag;
	checkbox=true;
	$.fn.zTree.init($("#treePanel1"), setting);
	returnId = returnid;
	returnName = returnname;
}

function getPermissionTree2(returnid, returnname,uid,flag){
	$('#treeDialog2').window('open');
	URL = ctxPath+"/permission/getPermissionTree?uid="+uid+"&flag="+flag;
//	alert("1111");
	checkbox=false;
	$.fn.zTree.init($("#treePanel2"), setting);
	returnId = returnid;
	returnName = returnname;
}

//获取授予角色权限节点
function getChecked() {
	var zTree = $.fn.zTree.getZTreeObj("treePanel1");
	var nodes = zTree.getCheckedNodes(true);
	var ids = '';
	var names = '';
	for ( var i = 0; i < nodes.length; i++) {
		if (ids != '')
			ids += ',';
		ids += nodes[i].id;
		if (names != '')
			names += ',';
		names += nodes[i].name;
	}
	if("0"==ids){
		sing_msg("请先选择权限！");
	}else{
		var uarray = getSeleces();
		$.post(ctxPath+'/role/addPow',{'uid':uarray,'rids':ids},function(data){
			if(hb.Obejct.parseJSON(data)){
				sing_msg(data.msgInfo);
				$('#treeDialog1').window('close');
				$("#dg").datagrid('reload');
			}
		})
	}
}


function dataPow(){
	var uarray = getSeleces().split(",");
	var array = getSeleces();
	if(uarray.length<=1 && (null==uarray || ""==uarray)){
		sing_msg("请选择待授予数据权限的角色！");
		return;
	}else{
		$.post(
			'checkRoleById',
			{'ids':array},
			function(data){
				if(hb.Obejct.parseJSON(data)){
					if(data.msgInfo==1||data.msgInfo=='1'){
						$('#dataForm').form('clear');
						$('#dataDiv').dialog('open');
					}else{
						sing_msg("角色‘"+data.msgCode+"’已被删除！");	
						$('#dg').datagrid('reload');
					}
				}
			});
					
	}
}

//添加数据权限
function addDataPow(){
	var inputs = $('#dataDiv input');
	var auth_type = [];
	$.each(inputs,function(index,item){
		if(item.checked){
		 auth_type.push(index+1);
		 
		}
	})
	auth_type = auth_type.join(',');
	if(""==auth_type || null==auth_type || auth_type.length==0){
		sing_msg("请先选择数据权限！");
	}else{
		var uarray = getSeleces();
			$.post(ctxPath+'/role/addDataPow',{'authType':auth_type,'ids':uarray},function(data){
				if(hb.Obejct.parseJSON(data)){
					sing_msg(data.msgInfo);
					$('#dataDiv').window('close');
					$("#dg").datagrid('reload');
				}
			})
	}
}
//初始化
$(function() {
	$('#treeDialog2').dialog( {
		width : 270,
		height : 380,
		draggable:false,
		collapsible : false,//折叠
		minimizable : false,//最小化
		maximizable : false,//最大化
		title : '拥有权限',
		modal : true,//遮罩
//		iconCls : 'icon-power',//图标
		closed : true,//默认隐藏
		buttonAlign : 'left'
	});

})
//查看权限
function lookPow(){
	var array = getSeleces();
	var uarray = getSeleces().split(",");
	if(uarray.length>1){
		sing_msg("请选择一个角色进行查看！");
		return;
	}else if(uarray.length<=1 && (null==uarray || ""==uarray)){
		sing_msg("请选择待查看的角色！");
		return;
	}else{
		$.post(
			'checkRoleById',
			{'ids':array},
			function(data){
				if(hb.Obejct.parseJSON(data)){
					if(data.msgInfo==1||data.msgInfo=='1'){
						getPermissionTree2('pid','name',uarray,'1');
					}else{
						sing_msg("该角色已被删除！");	
						$('#dg').datagrid('reload');
					}
				}
			});
	}
}
//获取选中项
function getSeleces(){
	var ids = [];
	var rows = $('#dg').datagrid('getSelections');
	var crows=$(".datagrid-cell-check [type='checkbox']");
	for(var i=0;i<crows.length;i++){
		if(crows[i].checked ||crows[i].checked=='true' ||crows[i].checked=='checked'){
			ids.push(crows[i].value);
		}
	}
	return ids.join(',');
}

function sing_msg(msg){
	$.messager.show({
		title:'提示',
		msg:msg,
		showType:'slide',//show
		timeout:3000,
		style:{
			right:'',
			top:document.body.scrollTop+document.documentElement.scrollTop,
			bottom:''
		}
	});
}



//页面变化时 数据刷新
$(window).resize(function() {
	setTimeout(function(){
		query();
	},200);
	
});

