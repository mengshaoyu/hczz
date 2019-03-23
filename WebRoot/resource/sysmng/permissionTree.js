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
	$('#treeDialog1').dialog( {
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

function getUrl() {  
      return URL;  
 } 


//加载树面板
function getPermissionTree(returnid, returnname,uid,flag){
	$('#treeDialog1').window('open');
	URL = ctxPath+"/permission/getUPermissionTree?uid="+uid;
	checkbox=false;
	$.fn.zTree.init($("#treePanel1"), setting);
	returnId = returnid;
	returnName = returnname;
}

