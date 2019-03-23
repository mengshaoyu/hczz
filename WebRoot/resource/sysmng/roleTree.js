/**
 * desc: Jquery-zTree多选树
 * time: 2013-8-23
 * author: haohongs
 */
var roleid;
var URL;
var saveOrUpd;
var setting = {
	check: {
		enable: true
	},
	data: {
		simpleData: {
			enable: true
		}
	},
	async : {
		enable : true,
		url : getUrl
	}
};

//初始化
$(function() {
	$('#treeDialog').dialog( {
		width : 270,
		height : 380,
		collapsible : false,//折叠
		minimizable : false,//最小化
		maximizable : false,//最大化
		draggable:false,
		title : '请选择',
		modal : true,//遮罩
//		iconCls : 'icon-role',//图标
		closed : true,//默认隐藏
		buttonAlign : 'left',
		buttons : [ {
			text : '确定',
//			iconCls : 'icon-ok',//图标
			handler : function() {
				//onloading();
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
//			iconCls : 'icon-canceling',//图标
			handler : function() {
			//	$('#' + returnId).val('');
			//	$('#' + returnName).val('');
			$('#treeDialog').dialog('close');
		}
		} ]
	});

})

function getUrl() {  
      return URL;  
 } 


//加载树面板
function getRoleTree(returnid, returnname){
	$('#treeDialog').window('open');
	URL = ctxPath+"/role/getList";
	$.fn.zTree.init($("#treePanel"), setting);
	returnId = returnid;
	returnName = returnname;
}
//获取用户选择的节点
function getChecked() {
	var zTree = $.fn.zTree.getZTreeObj("treePanel");
	var nodes = zTree.getCheckedNodes(true);
	var ids = '';
	var names = '';
//	if (nodes.length == 0) {
//		$.messager.alert('信息提示', '请先选择一个节点！', 'info');
//		return;
//	}
	for ( var i = 0; i < nodes.length; i++) {
		if (ids != '')
			ids += ',';
		ids += nodes[i].id;
		if (names != '')
			names += ',';
		names += nodes[i].name;
	}
	//$('#' + returnId).val(ids);
	//$('#' + returnName).val(names);
	if(""==ids || null==ids){
		sing_msg("请先选择角色！");
	}else{
		$('#treeDialog').window('close');
		var uarray = getSeleces();
		$.post(ctxPath+'/role/addRole',{'uids':uarray,'rids':ids},function(data){
			removeload();
			sing_msg(data.msgInfo);
			$("#_grid").datagrid('reload');
		})
	}
}
