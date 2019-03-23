
var roleStatus = {
	'1000':'6,8',
	'1001':'2',
	'1002':'3,4,9',
	'1003':'5'
}

var tabObj = function() {};
function addTabPage(tabId, title, iconCls, accordionTitle, treeId, url){
	title +=  '<div id="'+tabId+'" style="display:hidden;"></div>';
	
	// 判断是打开面签，还是展示已经打开的页签
    if($("#" + tabId).html() == undefined || $("#" + tabId).html() == null){
    		// 如果打开的面签过多，则提示信息
		if($('#mainTab').tabs('tabs').length > tabTatol){
			$.messager.show({
				title:'提示',
				msg:'打开过多的页签会导致浏览器速度变慢！',
				showType:'slide',
				timeout:2000,
				style:{
					right:'',
					top:window.screen.height/5,
					bottom:''
				}
			});
		}
	
        $('#mainTab').tabs('add',{
            title: title,
            iconCls:iconCls,
            closable:true,
            cache : false,
            content : '<iframe id="' + tabId + '" name = "fm_'+tabId+'"src="../' + url + '" accordionTitle="'+ accordionTitle +'" width="100%" height="100%" frameborder="0" scrolling="auto" ></iframe>'
        });
    } else {
        $('#mainTab').tabs('select', title);
    }
}
function alwaysAddTabPage(tabId, title, iconCls, accordionTitle, treeId, url){
	var oldTitle = title + '<div id="'+tabId+'" style="display:hidden;"></div>';
	
	// 判断是打开面签，还是展示已经打开的页签
	if($("#" + tabId).html() == undefined || $("#" + tabId).html() == null){
		// 如果打开的面签过多，则提示信息
		if($('#mainTab').tabs('tabs').length > tabTatol){
			$.messager.show({
				title:'提示',
				msg:'打开过多的页签会导致浏览器速度变慢！',
				showType:'slide',
				timeout:2000,
				style:{
					right:'',
					top:window.screen.height/5,
					bottom:''
				}
			});
		}
		
		$('#mainTab').tabs('add',{
			title: oldTitle,
			iconCls:iconCls,
			closable:true,
			cache : false,
			content : '<iframe id="' + tabId + '"src="../' + url + '" accordionTitle="'+ accordionTitle +'" width="100%" height="100%" frameborder="0" scrolling="auto" ></iframe>'
		});
	} else {
		$('#mainTab').tabs('close', oldTitle);
		addTabPage(tabId, title, iconCls, accordionTitle, treeId, url)
	}
}

/*
 * 关闭当前页，并跳转到执行的tab页签
 * @param {Object} index
 * @param {Object} tabID
 * @param {Object} title
 * @param {Object} refresh	true,不刷新列表；false或不传参数，刷新列表
 */
function closeCurPanel(index,tabID,title,refresh){
	//关闭当前页
	var curTab = $('#mainTab').tabs('getSelected');
	var thisIndex = $('#mainTab').tabs('getTabIndex',curTab);
	$('#mainTab').tabs('close', thisIndex);
	
	//跳转到指定的tab页签
	if(null!=index){//编号跳转
		$('#mainTab').tabs('select', index);
	}else if(null!=tabID&&null!=title){
		var title =  title+'<div id="'+tabID+'" style="dispaly:hidden;"></div>';
		$('#mainTab').tabs('select', title);
	}

	//处理关闭后刷新
	if(refresh){
		var curTab_2 = $("#mainTab").tabs("getSelected");
		if (curTab_2 && curTab_2.find("iframe").length > 0) {
			var _refresh_ifram = curTab_2.find("iframe")[0];
			_refresh_ifram.contentWindow.location.href = _refresh_ifram.src;
		}
	}
}
function closeCurPanelMsg(title, type){
	//关闭当前页
	var curTab = $('#mainTab').tabs('getSelected');
	var thisIndex = $('#mainTab').tabs('getTabIndex',curTab);
	$('#mainTab').tabs('close', thisIndex);
	if(type && 2 == type){
		showMessageCenterFull(title);
	}else {
		showMessageCenter(title);
	}
}

function showCenterMessage(title, type){
	if(type && 2 == type){
		showMessageCenterFull(title);
	}else {
		showMessageCenter(title);
	}
}

//设置关联界面的元素值
function setTabParam(title,param,value){
	var tab=$('#mainTab').tabs('getTab', title);
	var _refresh_ifram = tab.find("iframe")[0];
	_refresh_ifram.contentWindow.onParams(param,value);
	
}

// 退出登录
function logout(){
	$.messager.confirm('提示','确认要注销登录吗？',function(result){
		if (result){
			window.location.href = 'goOut';
		}
	});
};

// 修改密码
function changePwd(){
	// alert("修改密码对话框  >>TODO");
	
	$('#oldPwd').val('');
	$('#newPwd').val('');
	$('#newPwd2').val('');
	$('#msg').html('');
	
	$('#divEdit').dialog('open');
	
};
// 保存新密码
function savePasswd(){
	// oldPwd  newPwd  newPwd2
	if(hb.StrUtil.isBlank($('#oldPwd').val())){
		$('#msg').html('原密码不能为空!');
		return;
	}else if(hb.StrUtil.isBlank($('#newPwd').val())){
		$('#msg').html('新密码不能为空!');
		return;
	}else if($('#newPwd').val() != $('#newPwd2').val()){
		$('#msg').html('两次输入的新密码不相同!');
		return;
	}
	$.post("updatePwd", $("#pwdForm").serialize(), function(data){
		if(hb.Obejct.parseJSON(data)){
			$.messager.alert('信息',data.msgInfo,'info');
			$('#divEdit').dialog('close');
		}
	});
}

$(function(){
    $('#mainTab').tabs({  
        border:false,
        onContextMenu:function(e, title){
            $('#closeDiv').unbind('click');
            $('#closeDiv').bind('click', function(){
                if (title != "欢迎页") {
                    $('#mainTab').tabs('close', title);
                }
                $('#mm').menu('hide');
            });
        }
    });  

    $('#divEdit').dialog({
    	width:294,
    	height:240,
    	modal:true,
    	resizable:false,
    	iconCls:'icon-edit',
    	buttons:[{
				text:'保存',
				iconCls:'icon-ok',
				handler:savePasswd
			},{
				text:'关闭',
				iconCls:'icon-cancel',
				handler:function(){ 
					$('#divEdit').dialog('close');
				  }
			}]
    });
    $('#divEdit').dialog('close');
    
    // 系统登录后自动跳转
    window.setTimeout(function(){
    	if(null != pageTo && '' != pageTo){
//    		$.post(ctxPath + "/menu/getInfo?id="+pageTo, function(node){
    		$.post(ctxPath + "/index/unFinish", function(node){
    			if (hb.Obejct.parseJSON(node)){
    				alwaysAddTabPage(node.uuid, node.name, node.iconCls, "remindLog", node.uuid, node.url + "?is"); // 打开功能菜单
    			}
    		});
    	}
	},100);
    notice();
    setInterval(function(){
    	notice();
    },2000);
});

/**
 * 复次调用
 */
function clientCheck() {
	var isOpen = 1;
	$.post(ctxPath + "/menu/getInfo?id=400101", function(node){
		if (hb.Obejct.parseJSON(node)){
			alwaysAddTabPage(node.uuid, node.name, node.iconCls, "remindLog", node.uuid, node.url); // 打开功能菜单
		}
	});
	return isOpen; 
}

function notice(){
	$.ajax({
		type : 'get',
		url : ctxPath + '/pztask/getToDoCounts?'+new Date().getTime(),
		data :{status:""},
		dataType : 'json',
		timeout : 19990,
		success : function(res){
			if(res.total){
				var data = res.rows;
				var counts = 0;
				for(var index in data){
					if(window.console){
						//console.log(data[index])
					}
					counts+=data[index].COUNT;
				}
				if(counts>=0){
					$('#notice_count').html(counts);
				}
			}
		}
	});
}
function getStatusStr(){
	var str = "";
	var rolesArr = roles.split(",");
	if(rolesArr.length>1){
		for(var i=1;i<rolesArr.length; i++){
			var role = rolesArr[i];
			if(role in roleStatus){
				str += roleStatus[role]+",";
			}
		}
		str = str.substring(0,str.length-1);
	}
	return str;
}
function goTaskList(sta){
	var pzStatus = '';
	if(sta==null || sta ==''){
		pzStatus = getStatusStr();
	}
	else{
		pzStatus = sta;
	}
	var title = "待办事项";
	if($("#100202-dbsx").html() == undefined || $("#100202-dbsx").html() == null){
		addTabPage('100202-dbsx',title,'','',1002,'pztask/init?isdbsx=1');
	}
	else{
		title +=  '<div id="100202-dbsx" style="display:hidden;"></div>';
		$('#mainTab').tabs('select', title);
//		$('')
	}
}



function SOP_showAlertMsg(title,msg){
	
}
function SOP_showAlertMsg(msg){
	
}






