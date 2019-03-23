$(function(){
//	initComboCss();
	/**
	 * 加载按钮
	 */
	$('#toolbar').toolBar({
		url:'../login/getToolBar',
		page:100108,
		nowrap:true
	});
	query();

})

//初始化combox的宽度
function initComboCss(){
	//form高度
	var form_width=$(".wd98-bg").width();
	
	var right_width=form_width*0.76;
	var width=right_width/3-35;
	//初始化查询条件的下拉元素宽度
	$("#queryTable td:nth-child(2n)").css("width",width+"px").css({"padding-left":"5px","box-sizing":"boder-box"});
	
	$(".combo").css("width",$("#caseNo").width()+"px");
	$(".combo-text").css("width",($("#caseNo").width()-20)+"px")
}

//全局ajax请求带遮罩及提示
var gridoptions = null;
var currentTab=null;
/**
 * 列表查询
 */
function query(){
	onloading();						//数据加载等待提示
	//信息查询加载
	$('#_grid').datagrid({
		fitColumns: true,
		rownumbers: true,
		showFooter: false,				//显示底部统计数
		pagination:true,
		pageSize:20,
	//	width:($(document.body).width()) * 0.98,			//设置grid宽度
		height:document.body.clientHeight - 250,	//设置grid高度
		url:ctxPath+'/caseinfo/getlist',
		queryParams : {
			'caseNo': $('#caseNo').val(),
			'caseName': $('#caseName').val(),
			'caseType': $("#caseType").combobox('getValue'),
			'caseStatus': $('#caseStatus').combobox('getValue'),
			'acceptDateStart':$('#timeStart').val(),
			'acceptDateEnd':$('#timeEnd').val(),
			'criminalName':$('#criminalName').val(),
//			'acceptUnit':$('#acceptUnit').val(),
			'undertakeUnit':$('#undertakeUnit').val(),
//			'undertakeArea':$('#undertakeArea').val(),
			'page':gridoptions==null?'0':gridoptions.pageNumber,
			'rows':gridoptions==null?'0':gridoptions.pageSize,
			'sorter' : 'c.create_dt',
			'order' : 'desc'
		},
		columns:[[
			{field:'caseId',title:'id',align:'center',sortable:true,checkbox:true},
			{field:'caseNo',title:'案件编号',width:200,align:'center',
				formatter: function(value,row,index){
					value = value == null?"":value;
					return '<labal title="'+value+'">'+value+'</labal>';
				}},
			{field:'caseName',title:'案件名称',width:180,align:'center',
				formatter: function(value,row,index){
					value = value == null?"":value;
					return '<labal title="'+value+'">'+value+'</labal>';
				}},
			{field:'caseStatus',title:'案件状态',width:80,align:'center',
				formatter: function(value,row,index){
					value = value == null?"":value;
					return '<labal title="'+value+'">'+value+'</labal>';
				}},
			{field:'caseType',title:'案件类型',width:80,align:'center',
				formatter: function(value,row,index){
					value = value == null?"":value;
					return '<labal title="'+value+'">'+value+'</labal>';
				}},
			{field:'criminalName',title:'嫌疑人姓名',width:80,align:'center',
				formatter: function(value,row,index){
					value = value == null?"":value;
					return '<labal title="'+value+'">'+value+'</labal>';
				}},
			{field:'acceptUnit',title:'受案单位',width:150,align:'center',
				formatter: function(value,row,index){
					value = value == null?"":value;
					return '<labal title="'+value+'">'+value+'</labal>';
				}},
			{field:'acceptDate',title:'受案时间',width:150,align:'center',
				formatter: function(value,row,index){
					value = value == null?"":value;
					return '<labal title="'+value+'">'+value+'</labal>';
				}},
			{field:'handlePeople',title:'主办人',width:100,align:'center',
				formatter: function(value,row,index){
					value = value == null?"":value;
					return '<labal title="'+value+'">'+value+'</labal>';
				}},
			{field:'caseDesc',title:'简要案情',width:320,align:'left',
				formatter: function(value,row,index){
					value = value == null?"":value;
					return '<labal title="'+value+'">'+value+'</labal>';
				}
			},
			{field:'id',title:'操作',width:100,align:'center',
				formatter: function(value,row,index){
					var id = value;
					return "<a title='详细' href='javascript:void(0)' style='display:inline-block;color:#4080ee;' onclick='detail(\""+row.id+"\")'>详细</a>";
				}
			}
		]],
		onBeforeLoad:function(param){
			if(!getQueryParam())
				return;
//			var a = $('#qf').serializeArray();
//			$.each(a, function(i, ob){
//				param[ob.name] = hb.StrUtil.trim(ob.value); 
//			});
			$('div.datagrid-header-check').children().get(0).checked=false;//取消全选框选中 
			$('div.datagrid-cell-check').each(function(){ 
				$(this).children().get(0).checked=false; 
			}); 
		},
		/**
		 * 数据结构修改
		 */
		loadFilter : function(data){
			if(!hb.VO.check(data)){
				showMessage(hb.VO.getMsg(data));
			}
			return hb.VO.getBody(data);
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

/**
 * 打开案件导入弹出框
 */
function openImport(){
	//重置案件导入输入框
	$('#importFile').replaceWith($('<input class="l-btn" type="file" id="importFile" name="importFile" accept=".xls,.xlsx">'));
	$('#importDialog').dialog('open');
}
/** 开始案件导入 **/
function startImport(){
	if($('#importFile').val()==''){
		showMessage("请先选择文件！");
		return false;
	}
	var fileName = $('#importFile').val();
	var prefix = fileName.split(".")[1];
	if(prefix=='xls' ||prefix=='xlsx'){
		$('#importDialog').dialog('close');
		onloading();
		$('#fileForm').ajaxSubmit({
			url : ctxPath+'/caseinfo/imports',
			dataType:'json',
			type : 'post',
			contentType: "application/x-www-form-urlencoded; charset=utf-8", 
			timeout: 50000,
			async :false,
			success : function(data){
				if(hb.VO.check(data)){
					showMessage("导入成功！");
					refresh();
				}
				else{
					removeload();
					var errlist = [];
					if(data.errlist && data.errlist.length)
						errlist = data.errlist;
					$('#_grid2').datagrid({
						fitColumns: true,
						rownumbers: true,
						//width:($(document.body).width()) * 0.98,			//设置grid宽度
//						height:document.body.clientHeight - 190,	//设置grid高度
						columns:[[
							{field:'errorMsg',title:'错误信息',align:'left',width:450,
								formatter: function(value,row,index){
									value = value == null?"":value;
									return '<labal title="'+value+'">'+value+'</labal>';
								}},
							{field:'caseNo',title:'案件编号',width:180,align:'center',
								formatter: function(value,row,index){
									value = value == null?"":value;
									return '<labal title="'+value+'">'+value+'</labal>';
								}},
							{field:'caseName',title:'案件名称',width:180,align:'center',
								formatter: function(value,row,index){
									value = value == null?"":value;
									return '<labal title="'+value+'">'+value+'</labal>';
								}}
						]]
					});
					$('#_grid2').datagrid('loadData',errlist);
					$('#errorDialog').dialog('open');
					alertMsg("导入失败！","error");
				}
			},
			error : function(err){
				removeload();
				showMessage("导入异常，请检验模板是否正确！");
				if(console.error)
					console.error(err.responseText);
			}
		});
	}
	else{
		showMessage("只能选择excel文档！");
	}
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
 * 查询条件检验
 * @returns {Boolean}
 */
function getQueryParam(){
	//添加校验
	if(checkSql(hb.StrUtil.trim($("#caseNo").val()))){
		showMessage("案件编号禁止输入特殊字符！");
		$("#caseNo").select();
		return false;
	}
	if(checkSql(hb.StrUtil.trim($("#caseName").val()))){
		showMessage("案件名称禁止输入特殊字符！");
		$("#caseName").select();
		return false;
	}
	if(checkSql(hb.StrUtil.trim($("#caseType").val()))){
		showMessage("案件类型禁止输入特殊字符！");
		$("#caseType").select();
		return false;
	}
	if(checkSql(hb.StrUtil.trim($("#criminalName").val()))){
		showMessage("嫌疑人名称禁止输入特殊字符！");
		$("#criminalName").select();
		return false;
	}
	if(checkSql(hb.StrUtil.trim($("#acceptUnit").val()))){
		showMessage("受理单位禁止输入特殊字符！");
		$("#acceptUnit").select();
		return false;
	}
	
	return true;
}

/**
 * 刷新
 */
function refresh(){
	$("#qf").form('reset'); // 重置表单
	query();
}

/**
 * 页面变化时 数据刷新
 */
$(window).resize(function() {
	setTimeout(function(){
		query();
	},200);
	
});

function detail(caseId){
	window.open("../caseDetail/init?caseId="+caseId);
}

