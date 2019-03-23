var apply = {
	selectedIds : ''
}
$(function(){
	$('#toolbar').toolBar({
		url:'../login/getToolBar',
		page:1003,
		nowrap:true
	});
	$("#clueType").combobox({
		width : "auto",
		height : "34",
		panelHeight : "150",
		valueField: 'value',
		textField: 'label',
		editable:false ,
		data: [{
			label: '全部',
			value: '',
			selected : true
		},{
			label: '配侦申请',
			value: '1'
		},{
			label: '反馈报告',
			value: '2'
		},{
			label: '社会资源',
			value: '3'
		}]
	});
	query();
	//页面变化时 数据刷新
	$(window).resize(function() {
		setTimeout(function(){
			query();
		},200);
		
	});
})

//全局ajax请求带遮罩及提示
var gridoptions = null;
var currentTab=null;

function query(){
	onloading();						//数据加载等待提示
	//信息查询加载
	$('#_grid').datagrid({
		fitColumns: true,
		showFooter: false,				//显示底部统计数
		pagination:true,
		pageSize:20,
//		width:$(document.body).width(),			//设置grid宽度
		height:document.body.clientHeight-150,	//设置grid高度
		url:ctxPath+'/clue/getlist',
		queryParams : {
			'clueType': $('#clueType').combobox("getValue"),
			'clueDesc': $('#clueDesc').val(),
			'page':gridoptions==null?'0':gridoptions.pageNumber,
			'rows':gridoptions==null?'0':gridoptions.pageSize
		},
		columns:[[
			
			{checkbox:true},
			//{field:'caseNo',title:'案件编号',width:200,align:'left'},
			{field:'clueTypeName',title:'线索类型',width:100,align:'center'},
			{field:'clueDesc',title:'线索内容',width:200,align:'left',
				formatter : function(value,row,index){
					if(value==null)
						value='';
					return "<span title='"+value+"' href='#'>"+value+"</span>";	}
			},
			{field:'filename',title:'关联附件',width:200,align:'left',
				formatter : function(value,row,index){
					if(value==null)
						value='';
					return "<span title='"+value+"' href='#'>"+value+"</span>";	}
			},
			{field:'username',title:'上传人',width:100,align:'center'},
			{field:'createDt',title:'录入时间',width:150,align:'center',
				formatter : function(value,row,index){
					var valueFormat=new Date(value).format('yyyy-MM-dd hh:mm:ss');
					return "<span title='"+valueFormat+"' href='#'>"+valueFormat+"</span>";	}},
			{field:'clueType',title:'操作',width:200,align:'center',
				formatter : function(value,row,index){
						return "<a title='碰撞分析' href='javascript:void(0);' onclick='analysis();'>碰撞分析</a>";
				}
			}
		]],
		onBeforeLoad:function(param){
			if(!getQueryParam())
				return;
//			var a = $('#qf').serialrizeArray();
//			$.each(a, function(i, ob){
//				param[ob.name] = hb.StrUtil.trim(ob.value); 
//			});
			$('div.datagrid-header-check').children().get(0).checked=false;//取消全选框选中 
			$('div.datagrid-cell-check').each(function(){ 
				$(this).children().get(0).checked=false; 
			}); 
		},
		onLoadSuccess:function(data){
			//获取grid分页对象
			gridoptions = $("#_grid").datagrid('getPager').data("pagination").options;
			$('div.datagrid-header-check').children().get(0).checked=false;				//取消全选框选中
			if(data.total<=0){
				showMessage("没有查询到符合条件的数据！");
			}
			$(this).datagrid("fixRownumber");
			removeload();
		},
		onLoadError:function(err){
		},
		onClickRow: function(rowIndex, rowData){//加载完毕后获取所有的checkbox遍历             
			$("input[type='checkbox']").each(function(index, el){//如果当前的复选框不可选，则不让其选中
				if (el.disabled == true) {
					$('#_grid').datagrid('unselectRow', index - 1);
				}
			});
		}
	});
};
//获得查询参数
function getQueryParam(){
	//添加校验
	if(checkSql(hb.StrUtil.trim($("#clueDesc").val()))){
		showMessage("线索关键字禁止输入特殊字符！");
		$("#clueDesc").select();
		return false;
	}


	return true;
}
/***************查询开始**********************/

function openQuery(){
	$('#_grid').datagrid('reload');
}

/**
 * 重置查询条件
 */
function resetQuery(){
	$("#qf").form('reset'); // 重置表单
}



/**
 * 获取选中的申请ID
 */
function getSeletedIds(status,isForce){
	var idArr=[];
	var rows = $('#_grid').datagrid('getSelections');
	for(index in rows){
		if(rows[index].pzApplyStatus==status){
			idArr.push(rows[index].pzApplyId);
		}else{
			if(isForce)
				return false;
			else
				continue;
		}
	}
	return idArr.join(",");
}

function analysis(){
	showMessage("功能待建中，敬请期待！");
}


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
function refresh(){
//	$("#qf").form('reset'); // 重置表单
	query();
}

function showAdd(){
	$('#dd').dialog({
	    title: '添加反馈线索',
	    width: 300,
	    height: 250,
	    closable: false,
	    closed: false,
	    cache: false,
	    /*href: '${ctx}/index/showPwd',*/
	    modal: false
	});
	$('#dd1').dialog('refresh');
}