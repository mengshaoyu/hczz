$(function(){
	$('#vditDiv').dialog('close');
	$('#editDiv').dialog('close');
	$('#viewDiv').dialog('close');
	$('#toolbar').toolBar({
			url:'../login/getToolBar',
			page:100102
	});
	query();
	query_add();
	query_view();
});

function query(){
	$('#dg').treegrid({
     //  width:$(document.body).width(),//设置grid宽度
		height:document.body.clientHeight - 20,//设置grid高度
        fitColumns: true,
        url:'getList',
        idField:'id',
        treeField:'name',
        rownumbers:true,
        singleSelect:true,
        toolbar :"#toolbar"//工具栏
        /**
        onLoadSuccess:function(node,data){
        	if(hb.Obejct.parseJSON(data)){
        		var t=$(this);
        		if(data){
	        		$(data).each(function(index,d){
	        			if(this.state=='closed'){
	        				//t.treegrid('expandAll');
	        			}
	        		});
        		}
       		}
       	},
        loadFilter:function(data, parentId){
        	if(data){
				if (hb.Obejct.parseJSON(data)){
					return data;
				}
        	}
        } 
		*/
    });
}

function query_add(){
	  $('#editDiv').dialog({
   		closed:true,
   		top:50,
   		width:320,
   		draggable:false,
   		modal:true,
   		closable: false,
   		position:'center',
   		resizable:false,
   		buttons:[{
   			text:'保存',
//   			iconCls:'icon-ok',
   			handler:function(){
                var isValid = $("#editForm").form('validate');
	  			if (isValid){
	  				var url = 'add';
					//var fields =$('#editForm').serializeArray();
	  				var name=hb.StrUtil.trim($('#aName').val());
	  				var deptno=hb.StrUtil.trim($('#aNum').val());
	  				var num=hb.StrUtil.trim($('#aNum').val());
					var pname=$('#aParent').val();
					var pid=$('#apid').val();
					var orderNum=$('#aOrder').val();
					//var deptType=$('#atype').combobox('getValue');
					//var deptType=$('#deptType').combobox('getValue');
					//var imageUrl=$('#aimageUrl').val();
					var sortNo = $('#aOrder').val();
					$.post(
						'checkDeptno',
					    {'deptno':deptno},
					    function(data){
					    	if(hb.Obejct.parseJSON(data)){
					    	if(data.msgInfo==0){
					    		$.post(url,
					    			{'name':name,'num':num,'pname':pname,'orderNum':orderNum,'pid':pid},
					                function(data){
					                   if(hb.Obejct.parseJSON(data)){
					                   	    // 添加成功
							           		$('#editDiv').dialog('close');
					                    	sing_msg(data.msgInfo);
					                    	refreshDG();
										}
				                 });
					    	}else{
					    		$.messager.alert("提示","该部门编号已存在！");
					    		$('#adeptno').val('');
					    		$('#adeptno').select();
					    	}
					    	}
					    });
					                
	                 }
	  				return isValid;
   			}
   		},{
   			text:'取消',
//            iconCls:'icon-canceling',
            handler:function(){
						$('#editDiv').dialog('close');
					}
   		}]
   }) 
}

function query_view(){
	 $("#viewDiv").dialog({
           closed:true,
           top:50,
           width:320,
           draggable:false,
           modal:true,
           position:'center',
           resizable:false
    });
}

function add(){
	var row=$('#dg').treegrid('getSelected');
	$('#editForm').form('clear');
	if(row){
		$.post('getById',{'id':row.id},function(data){
			
			if(data==null||data==('')){
				sing_msg('此部门已被删除！');
				refreshDG();
			}else{
				if(hb.Obejct.parseJSON(data)){
				dlglistener("editDiv");
				$('#aParent').val(row.name);
				$('#apid').val(row.id);
				$('#editDiv').dialog({
					top:50,
					width:340,
					title:'部门新增'
//					iconCls:'icon-add'
				});
				$('#editDiv').dialog('open');
				$('#aName').focus();
			}
			}
		});
		
	}else{
		sing_msg("请选择待添加部门的上级部门！");
		return;
	}
	
}

function edit(){
	//msg("编辑",'quertion');
	var row = $('#dg').treegrid('getSelected');
	if(row){
		if(row.name==('所有部门')){
			sing_msg("该部门无法编辑！");
		}else{
			dlglistener("vditDiv");
			$.post('getById',{'id':row.id},function(data){
				
				if(data==null||data==('')){
					sing_msg('此部门已被删除！');
					refreshDG();
				}else{
					if(hb.Obejct.parseJSON(data)){
					$('#vditDiv').dialog('open');
					$("#id").val(data.id);
					$("#depName").val(data.name);
					$("#depNum").val(data.num);
					$('#depNum').numberbox('setValue',data.num);
					$("#pid").val(data.pid);
					$("#depOrder").val(data.orderNum);
					$('#depOrder').numberbox('setValue',data.orderNum);
					$('#deNum').combotree('reload',+ctxPath+'/deptment/getDeptTree?fid=0');
					$("#deNum").combotree('setValue',(data.pid==null||data.pid=='0')?'无':data.pid);
					$("#deNum").combotree('setText',data.pname==null?'无':data.pname);
					$("#uf").form('validate');
					}
				}
			});
		}
	}else{
		sing_msg("请选择待编辑的数据！");
		return;
	}
} 

function upd(){
	  var isValid = $("#uf").form('validate');
	  if (isValid){
	  	$.post(
			'checkDname',
		    {'name':hb.StrUtil.trim($("#depName").val()),'id':$("#id").val()},
		    function(data){
		    	if(hb.Obejct.parseJSON(data)){
		    	if(data.msgInfo==0){
		    		var deptno=hb.StrUtil.trim($('#depNum').val());
		    		$.post(
					'checkDeptno',
				    {'deptno':deptno,
				    'id':$("#id").val()},
				    function(data){
				    	if(hb.Obejct.parseJSON(data)){
		    				if(data.msgInfo==0){
					    		$.post('upd',
								{
									'id':$("#id").val(),
									'name':hb.StrUtil.trim($("#depName").val()),
									'num':$("#depNum").val(),
									'pid':$("#deNum").combotree('getValue'),
									'orderNum':$("#depOrder").val()
								},
								function(data){
									if(hb.Obejct.parseJSON(data)){
										$('#vditDiv').dialog('close');
										sing_msg(data.msgInfo);
										//$('#deNum').combotree('collapseAll');
										refreshDG();
									}
								})
					    	}else{
					    		$.messager.alert("提示","该部门编号已存在！");
					    		$('#adeptno').val('');
					    		$('#adeptno').select();
					    	}
				    	}
				    });
				}else{
					showMessage("该部门已存在！");
		    		$('#depName').val('');
		    		$('#depName').select();
		    	}
		    }
	    });
	  }
	  return isValid;
	
}

function del(){
	var row = $('#dg').treegrid('getSelected');
	if(row){
		//ajax 请求
		$.messager.confirm('提示', '您确认想要删除选中的数据吗？', function(r){
			if (r){
				$.post('CheckDept',{'id':row.id},function(data){
					if(hb.Obejct.parseJSON(data)){
					if(data.msgCode==("comm_001")){
						$.messager.alert('提示',"该部门下有子部门或用户，不能删除！",'info');
					}else{
						$.post('del',{'id':row.id},function(data){
							if(hb.Obejct.parseJSON(data)){
								sing_msg(data.msgInfo);
								refreshDG();
							}
						})
					}
					}
				})
			}
		});
		
	}else{
		sing_msg("请选择需要删除的数据！");
		return;
	}
}

function showInfo(row){
	var row = $('#dg').treegrid('getSelected');
	if(row){
		$.post("getById?id="+row.id, function(data){
			if(data==null||data==('')){
					sing_msg('此部门已被删除！');
					refreshDG();
				}else{
			  	  if (hb.Obejct.parseJSON(data)){
			  	    $('#vPname').html(data.pname==null?'无':dealNameNo(data.pname,"",140));
			  	    $('#vname').html(dealNameNo(data.name,"",210));
			  	    $('#vnum').html(dealNameNo(data.num,"",160));
			  	    $('#vOrdernum').html(dealNameNo(data.orderNum,"",140));
			  	    $('#viewDiv').dialog('open');
			  	   
			  	  }
			}
			});
	}else{
		sing_msg("请先选择待查看的记录！");
		return;
	}
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

function refreshDG(){
    $.post('getList',
        $("body").data("queryParam"),
        function(data){
            if(data){
				if (hb.Obejct.parseJSON(data)){
                	$('#dg').treegrid('loadData', data);
                }
            }
    });
}

function int1(){
	var reg = reg=/^([1-9]\d*)$/;
	var value= $('#aNum').val();
	if(reg.test(value)){
		oflag = true;
	}else{
		oflag = false;
	}
 }

//页面变化时 数据刷新
$(window).resize(function() {
	setTimeout(function(){
		query();
	},200);
	
});

