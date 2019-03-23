$(function(){
	document.getElementById('domain').style.display="none";
	$('#SysParam').dialog('close');
	$('#toolbar').toolBar({
		url:'../login/getToolBar',
		page:100105
	});
	//加载数据
	query();
});

var skey;
var dom;
var gridoptions = null;
function query(){
	$('#dg').datagrid({
		fitColumns:true,
		url:'getPsysparam',
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
			{field:'id',title:'id',align:'center',sortable:true,checkbox:true},
			{field:'sysKey',title:'Key值',width:35,align:'center'},
			{field:'sysValue',title:'参数值',width:35,align:'left'},
			{field:'description',title:'参数描述',width:45,align:'left'},
			{field:'editFlag',title:'读写控制',width:35,align:'center',formatter:function(value,row,index){
				if(value==1){
					return "可编辑";
				}else if(value==0){
					return "不可编辑";
				}
			}},
			{field:'domainName',title:'所属域',width:35,align:'left',formatter:function(value,row,index){
				var domain=$('#sDomainName1').combobox('getData');
				for(var i=0;i<domain.length;i++){
					if(domain[i].codeValue==value){
						return domain[i].valueDesc;
					}
				}
			}}
			
		]],
		onBeforeLoad:function(param){
			$('div.datagrid-header-check').children().get(0).checked=false;//取消全选框选中 
			$('div.datagrid-cell-check').each(function(){ 
				$(this).children().get(0).checked=false; 
			}); 
		},
		onLoadSuccess:function(data){
			if(hb.Obejct.parseJSON(data)){
				//获取dg分页对象
				gridoptions = $("#dg").datagrid('getPager').data("pagination").options;
				$('div.datagrid-header-check').children().get(0).checked=false;//取消全选框选中
			}
		}
	
	});
}


//编辑保存
function saveSysParam(){
	var isValid = $("#sysParamForm").form('validate');
	if (isValid){
		var url='updSysparam';
		var sysParams =$('#sysParamForm').serializeArray();
		var id=hb.StrUtil.trim($('#sKey').val());
		var sysKey=hb.StrUtil.trim($('#sSysKey').val());
		var SysValue=hb.StrUtil.trim($('#sSysValue').val());
		var DomainName=$("#sDomainName").combobox('getValue');
		var Description=hb.StrUtil.trim($('#sDescription').val());
		$.post(
			'ckeckSysParam',
			{"id":id,"sysKey":sysKey,"sysValue":SysValue,"domainName":DomainName},
			function(check){
				if(check.msgCode=='comm_001'){
					$.messager.alert("信息","该系统参数已存在！",'info');
				}else{
					$.post(
						url,
						{"id":id,"sysKey":sysKey,"sysValue":SysValue,"domainName":DomainName,"description":Description,
						"skey":skey,"dom":dom},
						function(data){
							if(hb.Obejct.parseJSON(data)){
								//添加成功
								$('#SysParam').dialog('close');
								sing_msg(data.msgInfo);
								$('#sDomainName1').combobox('reload');
								$("#dg").datagrid('reload');
							}
						});
				}
			});
	}else{
		return isValid;
	}
}
function edit1(){
	$('#sDomainName1').combobox('reload',ctxPath+'/sysparam/getDemainTree');
}
//进入编辑窗口
function edit(){
	var rows = $('#dg').datagrid('getSelections');
	if(rows.length>1){
		sing_msg("请选择一个系统参数进行编辑！");
		return
	}else if(rows.length<1){
		sing_msg("请选择待编辑的数据！");
		return;
	}else{
		if(rows[0].editFlag=='0'){
			sing_msg("该系统参数为只读参数，不能编辑！");
		}else{
			dlglistener("SysParam");
			$.post('ckeckSysP',
				{'id':rows[0].id},
				function(data){
					if(hb.Obejct.parseJSON(data)){
					if(data.msgCode=='comm_003'){
						sing_msg('此系统参数已被删除！');
						$('#dg').datagrid('reload');
					}else{
					    $('#SysParam').dialog({
					    	width:320,
					    	height:320,
					    	top : 50,
							closed:false,
					        title:'系统参数编辑',
					        draggable:false,
							href:'sysparam_edit?id='+rows[0].id,
							buttons: [{
								text:'保存',
								handler:function(){
									saveSysParam();
								}
							},{
								text:'取消',
								handler:function(){
									$('#SysParam').dialog('close');
								}
							}]
			    		});
			    		$("#sysParamForm").form('validate');
					}
					}
				});
			}
		
	}
}

//删除
function del(){
	var rows = $('#dg').datagrid('getSelections');
	var ids = [];
	var doms = [];
	var skeys = [];
	
	for(var i=0;i<rows.length;i++){
		ids.push(rows[i].id);
		doms.push(rows[i].domainName==null?' ':rows[i].domainName);
		skeys.push(rows[i].sysKey);
	}
	var array=ids.join(',');
	var sdoms=doms.join(',');
	var sskeys=skeys.join(',');
	
	
	if(array.split(",").length<=1 && (array==null || ""==array) ){
		sing_msg("请选择需要删除的数据！");
		return;
	}else{
		//ajax 请求
		$.messager.confirm('提示', '您确认想要删除选中的数据吗？', function(r){
			if (r){
				$.post('delSysparam',{'ids':array,'doms':sdoms,'skeys':sskeys},function(data){
					if(hb.Obejct.parseJSON(data)){
						sing_msg(data.msgInfo);
						$("#dg").datagrid('reload');
					}
				})
			}
		});
	}
}

//进入查看窗口
function look(){
	
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

