/**
 * desc: 警种绑定
 */
$(function() {
	$('#PtTreeDialog').dialog( {
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
				getPtChecked();
			}
		},{
			text : '取消',
//			iconCls : 'icon-canceling',//图标
			handler : function() {
				$('#PtTreeDialog').dialog('close');
			}
		}]
	});
});

//加载树面板
function openPolice(){
	var uarray = getSeleces().split(",");
	if (uarray.length > 1) {
		sing_msg("请选择一个用户进行操作！");
		return;
	} else if (uarray.length <= 1 && (null == uarray || "" == uarray)) {
		sing_msg("请选择要操作的用户！");
		return;
	} else {
		$('#PtTreeDialog').window('open');
		URL = ctxPath+"/code/getCodeTreeList?typeId=1006&domainName=0&userId="+uarray[0];
		checkbox = true;
		$.fn.zTree.init($("#PtTreePanel"), setting);
	}
}

//获取用户选择的节点
function getPtChecked() {
	var zTree = $.fn.zTree.getZTreeObj("PtTreePanel");
	var nodes = zTree.getCheckedNodes(true);
	var ids = '';
	for ( var i = 0; i < nodes.length; i++) {
		if(!nodes[i].isParent){
			if (ids != '')
				ids += ',';
			ids += nodes[i].id;
		}
	}

	if(""==ids || null==ids){
		sing_msg("请先选择警种类型！");
	}else{
		$('#PtTreeDialog').window('close');
		$.post(ctxPath+'/usr/blindPT',{'ids':ids, 'userId':getSeleces()},function(data){
			removeload();
			sing_msg(data.msgInfo);
		})
	}
}
