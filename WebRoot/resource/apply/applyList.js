
var apply = {
	selectedIds: '',
	cacheBtns : {}
};
$(function() {
	
	initComboCss();
	//获取待办任务
	//getUnFinsh();
	if (parent.roles.indexOf("1002") != -1) {
		$('#receive').show();
	}
	if (parent.roles.indexOf("1000") != -1) {
		$('#create').show();
	}

	//获取tabs数据
	/*getTabsNum();*/
	//点击tab 加载数据
	$('.workListTabs').on('click', 'li', function(event) {
		event.preventDefault();
		isdbsx = $(this).attr('data-id');
		$(".workListTabs li").eq($(this).index()).addClass("tabActive").siblings().removeClass("tabActive");
		query();
	});

	//默认待办选中状态
	if (isdbsx == "newIndex" || isdbsx != '') {
		$('#ddrw').addClass('tabActive');
	} else {
		$('.workListTabs li:eq(0)').addClass('tabActive');
	}


	//默认点击tabs;
	/*	if (status != '') {
			var testId = "#"+ status;
			$(testId).click();
		}*/



	//签收和新建
	$('.newWork').on('click', 'li', function(event) {
		event.preventDefault();
		if ($(this).index() == 0) {
			// $('#nav-list li:eq(1)', parent.document).click();
			 $('#1002', parent.document).addClass('top-nav-active');
       $('#100201', parent.document).click();
		} else {
			receives();
		}
	});


	$('#toolbar').toolBar({
		url: '../login/getToolBar',
		page: 100202,
		nowrap: true
	});

	query();

	$('#addBtn').on('click', function() {
		//TODO 添加校验
		$.ajax({
			url: ctxPath + '/pztask/add',
			dataType: 'json',
			type: 'post',
			timeout: 50000,
			success: function(data) {
				if (data.success && data.success == true) {
					showMessage("添加成功！");
					refresh();
				} else {
					showMessage("添加失败！");
					removeload();
				}
			},
			error: function(err) {
				//if(console.error)
				//console.error(err.responseText);
			}
		});
	})
})

//初始化combox的宽度
function initComboCss(){
	$("#caseType").css("width",$("#caseNo").width()+"px");
	$("#caseType").combobox({
		width : "auto",
		multiple:false,
		url: ctxPath + '/code/getCodeValueListByTypeId?typeId=2001', 
		valueField:'codeValue', 
		textField:'valueDesc', 
		panelHeight:'auto',
        editable:false
	});
	$("#applyType").css("width",$("#caseNo").width()+"px");
	$("#applyType").combobox({
		url:ctxPath+'/code/getCodeValueListByTypeId?typeId=1002',
        valueField:'codeValue',
        textField:'valueDesc',
        editable:false,
        panelHeight:'auto',
        multiple:false
	});
	$("#applyStatus").css("width",$("#caseNo").width()+"px");
	$("#applyStatus").combobox({
		url:ctxPath+'/code/getCodeValueListByTypeId?typeId=1004',
        valueField:'codeValue',
        textField:'valueDesc',
        editable:false,
        panelHeight:'160',
        multiple:false
	});
	$(".combo-text").css("height","32px").css("line-height","32px");

}



//全局ajax请求带遮罩及提示
var gridoptions = null;
var currentTab = null;

function query() {
	onloading(); //数据加载等待提示
	//信息查询加载
	$('#_grid').datagrid({
		fitColumns: true,
//		rownumbers: true,
		showFooter: false, //显示底部统计数
		pagination: true,
		pageSize: 20,
		//		width:$(document.body).width(),			//设置grid宽度
		height: document.body.clientHeight - 300, //设置grid高度
		url: ctxPath + '/pztask/getlist',
		queryParams: getParam(false),
		columns: [
			[{
			
					checkbox: true
				}, {
					field: 'caseNo',
					title: '案件编号',
					width: 332,
					align: 'center',
					formatter: function(value, row, index) {
						value = value == null ? "" : value;
						if(row.isOpen != '1')
							return "<span title='"+value+"'><img src='"+ctxPath+"/resource/image/apply/unread.png'>&nbsp;"+value+"</span>";
						else
							return "<span title='"+value+"'>"+value+"</span>";
					}
				}, {
					field: 'caseName',
					title: '案件名称',
					width: 200,
					align: 'center',
					formatter: function(value, row, index) {
						value = value == null ? "" : value;
						return "<span title='"+value+"'>"+value+"</span>";
					}
				}, {
					field: 'pzApplyNo',
					title: '任务编号',
					width: 220,
					align: 'center',
					formatter: function(value, row, index) {
						value = value == null ? "" : value;
						return "<span title='"+value+"'>"+value+"</span>";;
					}
				}, {
					field: 'deptName',
					title: '申请单位',
					width: 200,
					align: 'center',
					formatter: function(value, row, index) {
						value = value == null ? "" : value;
						return "<span title='"+value+"'>"+value+"</span>";;
					}
				}, {
					field: 'submitTime',
					title: '提报时间',
					width: 250,
					align: 'center',
					formatter: function(value, row, index) {
						value = value == null ? "" : value;
						return "<span title='"+value+"'>"+value+"</span>";;
					}
				},
				//			{field:'typeDesc',title:'业务种类',width:200,align:'center'},
				{
					field: 'gradeDesc',
					title: '任务等级',
					width: 100,
					align: 'center',
					formatter: function(value, row, index) {
						value = value == null ? "" : value;
						return "<span title='"+value+"'>"+value+"</span>";;
					}
				}, {
					field: 'statusDesc',
					title: '任务状态',
					width: 200,
					align: 'center',
					formatter: function(value, row, index) {
						value = value == null ? "" : value;
						return "<span title='"+value+"'>"+value+"</span>";;
					}
				}, {
					field: 'option',
					title: '操作',
					width: 200,
					align: 'center',
					formatter: function(value, row, index) {

						//console.log(row,index);
						var htm = "<a title=查看详情 href=javascript:; class='tab-link1' onclick=toApplyDetail('" + row.pzApplyId + "',0,'" + row.isOpen + "','" + row.pzApplyStatus + "')>详情</a>";

						// if (row.pzApplyStatus == 'HCZZ_1001' || row.pzApplyStatus == 'HCZZ_1010') {
						// 	var temp = "<a title='编辑' class='tab-link2' href=javascript:; onclick=toApplyDetail('"+row.pzApplyId+"',1,'"
						// +row.isOpen+"','"+row.pzApplyStatus+"')>编辑</a>";
						// }else{
						var btns = row.toolbarList;
						var temp;
						if(btns && btns.length){
							temp = "<a title='操作' class='tab-link2' href=javascript:; onclick=showToDos(this,'"+ row.pzApplyId + "')>操作</a>";
							apply.cacheBtns[row.pzApplyId] = btns;
						}
						else{
							temp = "<a title='操作' class='tab-link2 disClick' href='javascript:;'>操作</a>";
						}
						// }
						htm = htm + temp;
						return htm;
					}
				}
			]
		],
		onBeforeLoad: function(param) {
			if (!getQueryParam()){
				return;
			}
			apply.cacheBtns = {};//重置操作按钮缓存
			//			var a = $('#qf').serializeArray();
			//			$.each(a, function(i, ob){
			//				param[ob.name] = hb.StrUtil.trim(ob.value);
			//			});
			$('div.datagrid-header-check').children().get(0).checked = false; //取消全选框选中
			$('div.datagrid-cell-check').each(function() {
				$(this).children().get(0).checked = false;
			});
		},
		loadFilter: function(data) {
			if (!hb.VO.check(data)) {
				showMessage(hb.VO.getMsg(data));
			}
			return hb.VO.getBody(data);
		},
		onLoadSuccess: function(data) {
			//获取grid分页对象
			gridoptions = $("#_grid").datagrid('getPager').data("pagination").options;
			$('div.datagrid-header-check').children().get(0).checked = false; //取消全选框选中
			if (data.total <= 0) {
				showMessage("没有查询到符合条件的数据！");
			}
			$(this).datagrid("fixRownumber");
			removeload();
			getTodoNums();
			//阻止详情和操作按钮的冒泡事件
			$('.tab-link1,.tab-link2').on("click",function(e){
				e.stopPropagation();
			})
		},
		onLoadError: function(err) {},
		onClickRow: function(rowIndex, rowData) { //加载完毕后获取所有的checkbox遍历
			$("input[type='checkbox']").each(function(index, el) { //如果当前的复选框不可选，则不让其选中
				if (el.disabled == true) {
					$('#_grid').datagrid('unselectRow', index - 1);
				}
			});
		}
	});
};

/***************查询开始**********************/

function openQuery() {
	$('#_grid').datagrid('reload');
}

/**
 * 重置查询条件
 */
function resetQuery() {
	$("#qf").form('reset'); // 重置表单
}
//批量签收
function receives() {
	var ids = '';
	var rows = $('#_grid').datagrid('getSelections');
	if (rows.length == 0) {
		showMessage("请选择至少一条签收记录！");
		return false;
	}
	ids = getSeletedIds('HCZZ_1003', true);
	if (ids) {
		var f = checkReceives(ids);
		if(!f){
			showMessage("请只选择您代办的数据");
		}
		else{
			startReceive(ids);
		}
	} else {
		showMessage("请只选择待签收的数据");
	}
}

function checkReceives(ids){
	var flag = false;
	$.ajax({
		type: 'post',
		url : ctxPath + '/pzapprove/checkReceives',
		async : false,
		data :{
			ids : ids
		},
		timeout : 2000,
		success : function(data){
			if(hb.VO.check(data)){
				flag = true;
			}
		}
	});
	return flag;
}

function startReceive(ids) {
	$.ajax({
		type: 'post',
		url: ctxPath + '/pzapprove/approves',
		dataType: 'json',
		data: {
			pzApplyIds: ids
		},
		success: function(data) {
			if (hb.VO.check(data)) {
				showMessage("签收成功！");
				refresh();
			} else {
				showMessage(hb.VO.getMsg(data));
			}
		},
		error: function(e) {

		}
	});
}

/**
 * 获取查询参数
 * @param getTotal 是否获取代办数量
 * @returns
 */
function getParam(getTotal) {
	var base = {
		'page': gridoptions == null ? '0' : gridoptions.pageNumber,
		'rows': gridoptions == null ? '0' : gridoptions.pageSize,
		'sorter': "nvl2(ae.apply_id,'1','0') asc,a.create_dt",
		'order': 'desc',
		'isdbsx': isdbsx
	};
	var param = {
		'caseNo': $('#caseNo').val(),
		'caseName': $('#caseName').val(),
		//		'caseType': $('#caseType').val(),
		'caseTypeImp': $('#caseType').combobox('getValue'),
		'clueDesc': $('#clueDesc').val(),
		'timeStart': $('#timeStart').val(),
		'clueName': $('#clueDesc').val(),
		'timeEnd': $('#timeEnd').val(),
		'pzApplyStatus': $('#applyStatus').combobox('getValue') == "" ? status : $('#applyStatus').combobox('getValue'),
//		'pzApplyType': $('#applyType').combobox('getValue'),
		'pzApplyNo' : $('#applyNo').val(),
		'deptId': $('#dept').val()
	}
	return getTotal ? param : ($.extend(param, base));
}

function openAdd() {
	parent.addTabPage(1002010001, '合成提报', '', '', 100201, 'pztb/init')
}

//审批弹窗
function openApprove() {
	var rows = $('#_grid').datagrid('getSelections');
	if (rows.length == 0) {
		showMessage("请选择至少一条审批记录！");
		return false;
	}
	var ids = getSeletedIds('2', true);
	if (ids) {
		apply.selectedIds = ids;
		$('#approveDialog').dialog('open');
	} else {
		showMessage("请只选择待本单位审批状态的记录！");
	}

}

function startApprove() {
	$.ajax({
		type: 'post',
		url: ctxPath + '/pzapprove/approves',
		dataType: 'json',
		data: {
			pzApplyId: apply.selectedIds,
			flowId: '1003',
			result: $('#approveResult').combobox('getValue'),
			reason: $('#approveReason').val()
		},
		success: function(data) {
			if (data.success == true) {
				$('#approveDialog').dialog('close');
				showMessage("审批成功！");
				refresh();
			} else {
				showMessage(data.msgCode);
			}
		},
		error: function(e) {

		}
	});
}

/**
 * 获取选中的申请ID
 */
function getSeletedIds(status, isForce) {
	var idArr = [];
	var rows = $('#_grid').datagrid('getSelections');
	for (var i = 0; i < rows.length; i++) {
		//console.info(rows[i].pzApplyStatus)
		if (rows[i].pzApplyStatus == status) {
			idArr.push(rows[i].pzApplyId);
		} else {
			if (isForce)
				return false;
			else
				continue;
		}
	}
	return idArr.join(",");
}

/**
 * 审批
 */
function approve(pzApplyId) {
	var url = '../commLang/init?pzid=';
	var winName = 'approve_';
	approveOpen.openTab(url,pzApplyId,winName);

	// window.open('../commLang/init?pzid=' + pzApplyId, 'approveWin_' + pzApplyId);
}

/**
 * 分发
 */
function taskMission(pzApplyId) {
	var url = '../pzsp/initdis?pzid=';
	var winName = 'taskMission_';
	taskMissionOpen.openTab(url,pzApplyId,winName);

	// window.open('../pzsp/initdis?pzid=' + pzApplyId, 'taskAssignWin_' + pzApplyId);
}


/**
 * 反馈
 */
function feedback(pzApplyId) {
	var url = 'initDetail?sfbj=0&flag=1&pzApplyId=';
	var winName = 'feedbackWin_';
	feedbackOpen.openTab(url,pzApplyId,winName);

	// window.open('initDetail?pzApplyId=' + pzApplyId + '&sfbj=0&flag=1', 'taskdetailWin_' + pzApplyId);
}


/**
 * 查询条件检验
 * @returns {Boolean}
 */
/**/
function getQueryParam() {
	//添加校验
	if (checkSql(hb.StrUtil.trim($("#caseNo").val()))) {
		showMessage("案件编号禁止输入特殊字符！");
		$("#caseNo").select();
		return false;
	}
	if (checkSql(hb.StrUtil.trim($("#caseName").val()))) {
		showMessage("案件名称禁止输入特殊字符！");
		$("#caseName").select();
		return false;
	}
	if (checkSql(hb.StrUtil.trim($("#caseType").val()))) {
		showMessage("案件类型禁止输入特殊字符！");
		$("#caseType").select();
		return false;
	}
	if (checkSql(hb.StrUtil.trim($("#clueDesc").val()))) {
		showMessage("线索描述禁止输入特殊字符！");
		$("#clueDesc").select();
		return false;
	}
	if (checkSql(hb.StrUtil.trim($("#dept").val()))) {
		showMessage("部门禁止输入特殊字符！");
		$("#dept").select();
		return false;
	}

	return true;
}


/*********任务删除*******************************/
//删除指定状态下的任务信息：待提交
//可删除角色
function deleteApply(pzid) {
	if (pzid != null) {
		// ajax 请求
		$.messager.confirm('提示', '确认要删除选中任务记录吗?', function(r) {
			if (r) {
				$.post('delete', {
					'ids': pzid
				}, function(data) {
					if (hb.VO.check(data)) {
						$("#_grid").datagrid('reload');
					} else {
						showMessage(hb.VO.getMsg(data));
					}
				})
			}
		});
	} else {
		showMessage("任务异常");
	}
}

//IE8 下不支持indexOf方法,自行封装兼容IE8
if (!Array.prototype.indexOf) {
	Array.prototype.indexOf = function(val) {
		var value = this;
		for (var i = 0; i < value.length; i++) {
			if (value[i] == val) return i;
		}
		return -1;
	};
}

//验证选中记录状态是否符合条件
function checkState() {
	var ids = [];
	var rows = $('#_grid').datagrid('getSelections');
	var flag = 0; //具备删除条件
	for (var i = 0; i < rows.length; i++) {
		var item = rows[i];
		if (task_delete_state.indexOf(item.pzApplyStatus) < 0 || usrId != item.userId) {
			flag = 1;
		}
	}
	return flag;
}

/*************任务删除****结束*****************************/

/*************任务分发****开始**********************************/

function addAssign(pzAppid) {
	var array = new Array();
	array = getSelects();

	if (array.split(",").length <= 1 && (array == null || "" == array)) {
		showMessage("请选择待分发的数据！");
		return;
	} else {
		var state = checkTaskState();

		if (state == 1) {
			showMessage("请选择待分配状态的记录进行操作！");
			return;
		}
		var width = (document.body.clientWidth - 700) / 2;
		onloading();
		var src = '../pztask/initMission?pzApplyId=' + pzAppid;
		$("#iframe_mission").attr("src", src);
		$('#missionWin').dialog({
			width: 700,
			height: 450,
			left: width,
			closed: false,
			title: '任务分发',
			onBeforeClose: function() {
				$("#iframe_mission").attr("src", "");
				removeload();
			},
			draggable: false
		});
	}
}

//验证选中记录状态是否符合条件
function checkTaskState() {
	var ids = [];
	var rows = $('#_grid').datagrid('getSelections');
	var flag = 0; //具备分发条件
	for (var i = 0; i < rows.length; i++) {
		if (task_mission_state.indexOf(rows[i].pzApplyStatus) < 0) {
			flag = 1;
		}
	}
	return flag;
}

//关闭任务分发窗口
function colseMissionWin() {
	$('#missionWin').dialog('close'); //隐藏任务分发的窗口
	$('#loading-mask').css('display', 'none');
	query();
}
/*************任务分发****结束**********************************/


/** ***************落地情况反馈及评价****************** */

/**
 * 评价并反馈落地情况
 * @param    {string}   pzApplyId 任务id
 * @return   {[type]}             [description]
 */
function evaluate1(pzApplyId) {
	var url = '../pztask/initDetail?sfbj=0&flag=2&pzApplyId=';
	var winName = 'valuateWin_';
	evaluateOpen.openTab(url,pzApplyId,winName);

	// window.open('../pztask/initDetail?pzApplyId=' + pzApplyId+ "&sfbj=0&flag=2", 'taskdetailWin_' + pzApplyId);
}

/**
 * 查看报告
 * @param pzApplyId 任务id
 */
function toreports(pzApplyId){
	var url = '../pztask/initDetail?sfbj=0&flag=2&pzApplyId=';
	var winName = 'toreportsWin_';
	toreportsOpen.openTab(url,pzApplyId,winName);

	// window.open('../pztask/initDetail?pzApplyId=' + pzApplyId+ "&sfbj=0&flag=2", 'taskdetailWin_' + pzApplyId);
}

//关闭任务分发窗口
function colseEvaluateWin() {
	$('#evaluateWin').dialog('close'); // 隐藏任务分发的窗口
	removeload();
}

//重新加载反馈报告界面
function reloadFkb() {
	$('#c4').contents().find("#backRemark").hide();
}

function reloadIframe(id) {
	$(id).attr('src', $(id).attr('src'));
}

/** ***************落地情况反馈及评价****************** */



//获取选中项
function getSelects() {
	var ids = [];
	var rows = $('#_grid').datagrid('getSelections');
	for (var i = 0; i < rows.length; i++) {
		ids.push(rows[i].pzApplyId);
	}
	return ids.join(',');
}


//刷新
function refresh() {
	$('#qf').form('clear');
	query();
}

/**
 * 跳转请求详情页面
 * @Author   K.
 * @DateTime 2017-10-20
 * @param    {string}   pzApplyId 任务id
 * @param    {string}   sfbj      是否编辑
 * @param    {Boolean}  isOpen    [description]
 * @param    {string}   status    任务状态
 * @return   {[type]}             [description]
 */
function toApplyDetail(pzApplyId, sfbj, isOpen, status) {
	if (status == 'HCZZ_1003' && checkReceives(pzApplyId)) {
		startReceive(pzApplyId);
	}
	if (isOpen == '0') {
		$.post(ctxPath + '/pztask/read', {
			pzid: pzApplyId
		}, function(data) {
			if (hb.VO.check(data)) {
				query();
			} else {
				showMessage(hb.VO.getMsg(data));
			}
		});
	}
	var url = 'initDetail?flag=0' + '&sfbj=' + sfbj + '&pzApplyId=';
	var winName = 'taskdetailWin_';
	detailOpen.openTab(url,pzApplyId,winName);
}



/**
 * 跳转编辑页面
 * @Author   K.
 * @DateTime 2017-10-20
 * @param    {string}   pzApplyId 任务id
 * @return   {[type]}             [description]
 */
function edit(pzApplyId) {
	toApplyDetail(pzApplyId, 1, 1);
}


//页面变化时 数据刷新
$(window).resize(function() {
	setTimeout(function() {
		query();
	}, 200);

});


/**
 * //获取待办任务数量
 * @Author   K.
 * @DateTime 2017-10-20
 * @return   {[type]}   [description]
 */
function getUnFinsh() {
	$.ajax({
		url: ctxPath + '/index/unFinish',
		type: 'GET',
		success: function(data) {
			//console.log(data);
			$('#ddrw span').text(data);
		},
		error: function(data) {

		}
	})
}




/**
 * 获取tabs数据  暂时不用
 * @Author   K.
 * @DateTime 2017-10-20
 * @return   {[type]}   [description]
 */
function getTabsNum() {
	$.ajax({
		url: ctxPath + '/pztask/getToDoCounts',
		type: 'GET',
		success: function(data) {
			if (hb.VO.check(data)) {
				var body = hb.VO.getBody(data);
				if (body.rows != null) {
					var rows = body.rows;
					var len = rows.length;
					var fkCount = 0;
					for (var i = 0; i < len; i++) {
						if (rows[i].STATUS != 'HCZZ_1006' && rows[i].STATUS != 'HCZZ_1007') {
							var id = '#' + rows[i].STATUS;
							var count = rows[i].COUNT;
							$(id).find('span').text(count);
						} else if (rows[i].STATUS == 'HCZZ_1006' || rows[i].STATUS == 'HCZZ_1007') {
							fkCount += rows[i].COUNT;
						}
						$('#HCZZ_1006').find('span').text(fkCount)
						id = null;
						count = null;
					}
				} else {
					return false;
				}
			}
		},
		error: function(data) {
			//console.log(data);
		}
	});
}


//操作按钮list
/**
 * @Author   K.
 * @DateTime 2017-10-20
 * @param    {string}   context  目标Dom元素
 * @param    {string}   pzid    任务id
 * @return   {[type]}           [description]
 */
function showToDos(context,pzid) {
	var $this = $(context);
	var ul = $('#toDos ul');
	var html = '';
	ul.empty();
	var toolbars = apply.cacheBtns[pzid];
	for (var i = 0; i < toolbars.length; i++) {
		var btn = toolbars[i];
		html += "<li class='toDosMenu' onclick=" + btn.jsMethod + "('" + pzid + "')>" + btn.name + "</li>"
			/*var li = $('<li></li>').text(btn.name);*/
	}
	ul.append(html);
	var left = $this.offset().left - 22;
	var top = $this.offset().top + $this.height();
	$('#toDos').css({
		left: left,
		top: top
	}).show();
	//鼠标离开时自动隐藏
	$('#toDos').mouseleave(function() {
		$(this).hide();
	});
}

$(document).click(function(event) {
	$('#toDos').hide();
});

/**
 * 查看评价
 */
function showEvaluate(pzApplyId){
	var width = (document.body.clientWidth - 450) / 2;
	onloading();
	var src = '../pjhs/initData?pzApplyId=' + pzApplyId;
	$("#iframe_evaluate").attr("src", src);
	$('#evaluateWin').dialog({
		width: 520,
		height: 390,
		left: width,
		closable : false,//去掉关闭按钮
//		draggable: true,//可拖拽
		closed: false,
		title: '查看评价',
		onBeforeClose: function() {
			$("#iframe_evaluate").attr("src", "");
			query();
		}
	});
}

function closeEvaluate(){
	$('#evaluateWin').dialog("close");
}

/**
 * 评定
 */
function evaluate2(pzApplyId){
	var url = '../pztask/initDetail?sfbj=0&flag=4&pzApplyId=';
	var winName = 'taskdetailWin_';
	effectOpen.openTab(url,pzApplyId,winName);
}

//获取待办事件数量
/**
 * /
 * @Author   K.
 * @DateTime 2017-10-20
 * @return   {[type]}   [description]
 */
function getTodoNums() {

	$.ajax({
		url: ctxPath + '/pztask/getToDoCount',
		type: 'POST',
		data: getParam(true),
		success: function(data) {
			if (hb.VO.check(data)) {
				var nums = hb.VO.getBody(data);

				//console.log(nums);
				$('#ddrw span').text(nums == null ? 0 : nums);
			};
		},
		error: function(data) {

		}
	})
}

/**
 * 页面跳转,判断是否为IE
 * @Author   K.
 * @DateTime 2017-11-01
 * @param    {string}   pzApplyId 任务id
 * @param    {string}   sfbj      是否编辑
 * @param    {string}   url       路径
 * @param    {string}   winName   窗口标识
 */
function openNewTab() {
	var that = this;
	var fhwin = '';
	var tabsList = [];
	var len = tabsList.length;
	var idList = [];
	var temp = {
			pzid:null,
			winObj:null
	};
	that.IEOpen = function (url,pzApplyId,winName) {
		if(tabsList.length > 0){
			if(idList.indexOf(pzApplyId) != -1){ // 判断窗口是否是已打开的页面
				var j = idList.indexOf(pzApplyId);
				fhwin = tabsList[j].winObj;
				//console.log(fhwin);
				fhwin.close();//关闭已打开页面
				setTimeout(function(){
					//重新打开已关闭的页面
					fhwin = window.open(url + pzApplyId, winName + pzApplyId);
				},500);
			}else{
				//不是已有页面
				fhwin = window.open(url + pzApplyId, winName + pzApplyId); // 调用open，填入你希望的值。
				temp.pzid = pzApplyId;
				temp.winObj = fhwin;
				tabsList.push(temp);
				idList.push(pzApplyId);
				temp = {
						pzid:null,
						winObj:null
					};
				//fhwin = '';
			}
		}else{
			fhwin = window.open(url + pzApplyId, winName + pzApplyId); // 调用open，填入你希望的值。
			temp.pzid = pzApplyId;
			temp.winObj = fhwin;
			tabsList.push(temp);
			idList.push(pzApplyId);
			temp = {
					pzid:null,
					winObj:null
				};
			//fhwin = '';
		}
		return tabsList;
	};
	that.otherOpen = function (url,pzApplyId,winName) {
		window.open(url + pzApplyId , winName + pzApplyId);
	};
	that.openTab = function (url,pzApplyId,winName) {
		if (window.ActiveXObject || "ActiveXObject" in window) {
			that.IEOpen(url,pzApplyId,winName);
			//console.log('IE');
		} else {
			//console.log('Not IE');
			that.otherOpen(url,pzApplyId,winName);
		}
	};
}

//详情 跳转
var detailOpen = new openNewTab();
//审批 跳转
var approveOpen = new openNewTab();
//分发 跳转
var taskMissionOpen = new openNewTab();
//反馈 跳转
var feedbackOpen = new openNewTab();
//报告 跳转
var toreportsOpen = new openNewTab();
//评价 跳转
var evaluateOpen = new openNewTab();
//评定 跳转
var effectOpen = new openNewTab();



/**
 * =======   页面跳转结束  ========         
 */
