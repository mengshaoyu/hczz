$(function(){
	// 加载toolbar的功能按钮
	$('#toolbar').toolBar({
			url:'../rota/getToolBar',
			page:100107,
			nowrap:true
	});
	query();
});

//全局ajax请求带遮罩及提示
var gridoptions = null;
var currentTab=null;

function query(){
	onloading();						//数据加载等待提示
	//$('#querylog').dialog('close');
	//信息查询加载
	$('#_grid').datagrid({
		fitColumns: true,
		rownumbers: true,
		showFooter: false,				//显示底部统计数
		pagination:true,
		pageSize:20,
//		width:$(document.body).width(),			//设置grid宽度
		height:document.body.clientHeight - 120,	//设置grid高度
		url:ctxPath+'/rota/list',
		queryParams : {
			'page':gridoptions==null?'0':gridoptions.pageNumber,
			'rows':gridoptions==null?'0':gridoptions.pageSize
		},
		columns:[[
			{field:'rotaId',title:'id',align:'center',sortable:true,checkbox:true},
			{field:'userName',title:'值班长',width:100,align:'center'},
			{field:'duration',title:'值班时长',width:100,align:'center'},
			{field:'startTime',title:'值班开始时间',width:150,align:'center',
				formatter : function(value, row, index) {
					return new Date(value).format('yyyy-MM-dd hh:mm');
				}
			},
			{field:'endTime',title:'值班结束时间',width:150,align:'center',
				formatter : function(value, row, index) {
					return new Date(value).format('yyyy-MM-dd hh:mm');
				}
			},
			{field:'assignerName',title:'分配人',width:100,align:'center'},
			{field:'createDt',title:'分配时间',width:150,align:'center',
				formatter : function(value, row, index) {
					return new Date(value).format('yyyy-MM-dd hh:mm:ss');
				}
			}
		]],
		onBeforeLoad:function(param){
			if(!getQueryParam())
				return;
			var a = $('#qf').serializeArray();
			$.each(a, function(i, ob){
				param[ob.name] = hb.StrUtil.trim(ob.value); 
			});
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
		onLoadError:function(){
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

//获得查询参数
function getQueryParam(){
	//添加校验
	if(checkSql(hb.StrUtil.trim($("#userName").val()))){
		showMessage("功能模块禁止输入特殊字符！");
		$("#userName").select();
		return false;
	}
	return true;
}


//刷新
function refresh(){
	$('#qf').form('clear');
	query();
}

//获取选中项
function getSeleces() {
	var ids = [];
	var rows = $('#_grid').datagrid('getSelections');
	for (var i = 0; i < rows.length; i++) {
		ids.push(rows[i].uuid);
	}
	return ids.join(',');
}


//页面变化时 数据刷新
$(window).resize(function() {
	setTimeout(function(){
		query();
	},200);
	
});


