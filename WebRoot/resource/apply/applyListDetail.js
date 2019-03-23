var INDEX = '';
$(function() {
	
	//设置iframe的高度
	var height=$(document).height()-170;
	$("#detail").css("height", height+"px");
	 
	//显示按钮
	showButton(pzApplyId);
	
	//控制Tab显示
	showTab(pzApplyId);
	if(flag == '0'){
		$('#c0').attr('src',ctxPath+'/pztb/init?pzid='+pzApplyId+'&sfbj='+sfbj+'&fromDetail=1').show();
	}
	else if(flag == '1'){
		$('#c1').attr('src',ctxPath+'/pzfk/init?pzid='+pzApplyId).show();
	}
	else if(flag == '4'){
		$('#c4').attr('src',ctxPath+'/effect/init?pzid='+pzApplyId).show();
	}
	else if(flag == '2'){
		$('#c3').attr('src',ctxPath+'/pztask/initReport?pzid='+pzApplyId).show();
		flag++;
	}
	$('#limenu li').eq(flag).addClass('active');
	$('#litab li').eq(flag).addClass('active');
	
	$('#limenu li').on(
			'click',
			function() {
				var i = $('#limenu li').index(this);
				
				INDEX = $('#limenu li.active').index();
				$('#limenu li').removeClass('active');
				$(this).addClass('active');
				clearContent();
				$('#detail iframe').eq(i).css('display', 'block');
				var iframeSrc = $('.detail').eq(i).attr('src');
//				if (typeof (iframeSrc) == 'undefined') {
				var toUrl = '';
				if (i == 0) {// <li>基本信息</li>
					if(sfbj==1||editTrue==1){
						editTrue=1;
					}
					toUrl = ctxPath + '/pztb/init?pzid=' + pzApplyId+'&sfbj='+editTrue;
				}else if (i == 1) {// <li>反馈线索</li>
					toUrl = ctxPath + '/pzfk/init?pzid=' + pzApplyId;
				} else if (i == 2) {// <li>工作进展</li>
					toUrl = ctxPath + '/pztask/initFlow?pzid='
					+ pzApplyId;
				} else if (i == 3){// <li>报告</li>
					toUrl = ctxPath + '/pztask/initReport?pzid=' + pzApplyId;
				} else if (i == 4){// <li>效能评定</li>
					toUrl = ctxPath + '/effect/init?pzid=' + pzApplyId;
				}
				$('#detail iframe').eq(i).attr('src', toUrl);
//				}
			});
	// 清除内容
	function clearContent() {
		for (i = 0; i < $('#detail iframe').length; i++) {
			$('#detail iframe').eq(i).css('display', 'none');
		}
	}
	

});
function showalert(msg) {
	$.messager.alert("提示", msg);
}
function showconfirm(title, msg, func) {
	$.messager.confirm(title, msg, func);
}

//控制按钮显示与隐藏
function showButton(pzid,id){
	$.ajax({
		url : ctxPath + '/pztask/getMenuButton',
		type : 'post',
		dataType : 'text',
		data : {
			'pzid' : pzid,
			'menuId':'100202',
			'toolbar':"3"
		},
		success : function(data) {
			var data = $.parseJSON(data);
			var rows=data.body.rows;
			$('div.edit-button').empty();
			for(var index in rows){
				var item=rows[index];
				if('1'==item.show&&"0"==sfbj){
					var $a=$('<a href="javascript:void(0);" onclick="'+item.jsMethod+'()"></a>').addClass('easyui-linkbutton').html(item.name);
					$a.appendTo($('div.edit-button'));
				}
			}
		}
	});
}

//控制任务基本信息等Tab页签的显示情况
function showTab(pzid){
	$.ajax({
		url : ctxPath + '/pztask/getTabShow',
		type : 'post',
		dataType : 'text',
		data : {
			'pzid' : pzid
		},
		success : function(data) {
			var rows = data.split(",");
			for(var index in rows){
				var item=rows[index];
				if('0'==item){
					$('#limenu li').eq(index).hide();
//					$('#detail iframe').eq(index).hide();
				}else{
					$('#limenu li').eq(index).show();
//					$('#detail iframe').eq(index).show();
				}
			}
		}
	});
}

/** ***************控制分发界面****************** */

var pzid = "";
function addAssign() {
	var src = '../pzsp/initdis?pzid=' + pzApplyId;
	window.open(src, 'taskAssignWin_'+pzApplyId);
	
}

function hiddenButton(){
	$('#c1').contents().find("#addAssign").css("display","none");
}

// 关闭任务分发窗口
function colseMissionWin() {
	$('#missionWin').dialog('close');// 隐藏任务分发的窗口
	removeload();
}
/** ***************控制分发界面****************** */


/** ***************落地情况反馈及评价****************** */

//评价并反馈落地情况
function openEvaWin() {
	var width = (document.body.clientWidth - 450) / 2;
	onloading();
	var src = '../pztask/initEvaluate?pzApplyId=' + pzApplyId;
	$("#iframe_evaluate").attr("src", src);
	$('#evaluateWin').dialog({
		width : 530,
		height : 380,
		left : width,
		closable: false,
		closed : false,
		title : '评价核实',
		onBeforeClose : function() {
			$("#iframe_evaluate").attr("src", "");
			removeload();
		},
		draggable : false
	});
}

//关闭任务分发窗口
function colseEvaluateWin(flag) {
	$('#evaluateWin').dialog('close');// 隐藏任务分发的窗口
	removeload();
	if("1"==flag){
		$('#c3').contents().find("#iframe_report").attr("src",
				$('#c3').contents().find("#iframe_report").attr('src'));
		
		//显示按钮
		showButton(pzApplyId);
		
		//控制Tab显示
		showTab(pzApplyId);
	}
}

/** ***************落地情况反馈及评价****************** */

function addsave() {
	var uids = getSeleces();
	var uarray = uids.split(",");
	if (uarray.length > 1) {
		showMessageCenter("请选择一条数据进行编辑！");
		return;
	}
	if (uarray.length <= 1 && (null == uarray || "" == uarray)) {
		showMessageCenter("请选择分发人数据！");
		return;
	}
	var umain = uids;// 个人
	$.ajax({
		url : ctxPath + '/pzapprove/addAssign',
		type : 'post',
		dataType : 'text',
		data : {
			'flowId' : '1005',
			'applyId' : pzid,
			'actByType' : umain,
			'userIds' : uids
		},
		success : function(data) {
			var res = $.parseJSON(data);
			if (res.success) {
				$('#tolg').dialog('close');
				$('#c1').contents().find("#addAssign").hide();
				showMessageCenter("已完成分发到指定人操作")
			} else {
				showMessageCenter("分发指定人操作失败，请稍后再试")
			}
		}
	});
}

//控制某个Tab显示，如反馈报告
function showIndexTab(index){
	$('#limenu li').eq(index).show();
	$('#detail iframe').eq(index).show();
}

//编辑按钮
var editTrue=0;
function editApply(){
	editTrue=1;
	document.getElementById('c0').src="../pztb/init?pzid="+pzApplyId+"&sfbj="+editTrue;
}

//审批
function approve(){
	window.open('../commLang/init?pzid='+pzApplyId, 'approveWin_'+pzApplyId);
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

// 刷新页面
function refreshDetailList_(pzid,sfbj,flag){
	try{
		window.opener.query();
	}catch(e){
		
	}finally {
		var refstr = "<form id='refreshView' action='"+ctxPath+"/pztask/initDetail'>"
				+"<input type='hidden' name='pzApplyId' value='"+pzid+"'>"
				+"<input type='hidden' name='sfbj' value='"+sfbj+"'>"
				+"<input type='hidden' name='flag' value='"+flag+"'>"
				+"</form>";
		$("body").append(refstr);
		$("#refreshView").submit();
	}
}

//监听离开页面，隐藏父页面
$(window).unload(function(){
	//若打开审批界面的是任务管理界面，则调查询列表的方法
	if(undefined!=typeof(window.opener)&&'undefined'!=typeof(window.opener)&&
			undefined!=typeof(window.opener.query)&&'undefined'!=typeof(window.opener.query)){
		window.opener.query();
	}
});