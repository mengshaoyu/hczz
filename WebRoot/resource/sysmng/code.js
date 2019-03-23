$(function(){
	$('#udlg').dialog('close');
	$('#udlg1').dialog('close');
	// 加载toolbar的功能按钮
	$('#toolbar').toolBar({
			url:'../login/getToolBar',
			page:100104,
			nowrap:true
	});
	query();
	//加载数据
});
var gridoptions = null;

function query(){
	onloading();
	$('#cdlist').datagrid({
      // height:1000,
        fitColumns: true,
        url:'getPageCodeList',
        rownumbers:true,
        pagination:true,
		pageSize:20,
	//	width:$(document.body).width(),//设置dg宽度
		height:document.body.clientHeight - 40,//设置dg高度
        toolbar :"#toolbar",//工具栏
        queryParams : {
			'page':gridoptions==null?'0':gridoptions.pageNumber,
			'rows':gridoptions==null?'0':gridoptions.pageSize
		},
		onBeforeLoad:function(param){
			$('div.datagrid-header-check').children().get(0).checked=false;//取消全选框选中 
			$('div.datagrid-cell-check').each(function(){
				$(this).children().get(0).checked=false; 
			}); 
		},
		onLoadSuccess:function(data){
			if(hb.Obejct.parseJSON(data)){
				//获取dg分页对象
				gridoptions = $("#cdlist").datagrid('getPager').data("pagination").options;
				$('div.datagrid-header-check').children().get(0).checked=false;//取消全选框选中
			}
			removeload();
		},
        onClickRow: function(rowIndex, rowData){//加载完毕后获取所有的checkbox遍历       
			$("th[type='checkbox']").each(function(index, el){//如果当前的复选框不可选，则不让其选中
				if (el.disabled == true) {
					$('#cdlist').datagrid('unselectRow', index - 1);
				}
			});
		} 
		
    });
};
function getRows(){
	return $("#cdlist").datagrid("getRows");
}
//获取选中项
function getSeleces(){
			var ids = [];
			var rows = $('#cdlist').datagrid('getSelections');
			for(var i=0;i<rows.length;i++){
				ids.push(rows[i].typeId);
				ids.push(rows[i].typeName);
				ids.push(rows[i].typeDesc);
				ids.push(rows[i].domainName);
				ids.push(rows[i].editFlag);
			}
			return ids.join(',');
}
//编辑
var st="[";
var lastIndex;
function edit(){
	lastIndex=null;
	var uarray = getSeleces().split(",");
	if(uarray.length>5){
		sing_msg("请选择一条数据进行编辑！");
		return;
	}else if(uarray.length<5 && (null==uarray || ""==uarray)){
		sing_msg("请选择待编辑的数据！");
		return;
	}else{
	    var editFlag=uarray[4];
	    if(editFlag=='可编辑'){
	    	dlglistener("udlg");
	    	$.post('checkCode',
				{'typeId':uarray[0]},function(data){
					if(data==null||data==('')){
						sing_msg('此字典已被删除！');
						$('#cdlist').datagrid('reload');
					}else{
				    	$.post('getCodeValueListByTypeId',{'typeId':uarray[0],'domainName':uarray[3]},function(data){
								if(hb.Obejct.parseJSON(data)){
								var editIndex = undefined;
								//oldpwd = data.loginPwd;
								$('#udlg').dialog('open');
								$("#typeId").val(uarray[0]);
								$("#typeName").val(uarray[1]);
								$("#typeDesc").val(uarray[2]);
								//$("#domainName").combotree('setText',uarray[3]);
								$("#domainName").val(uarray[3]);
								$("#editFlag").val(uarray[4]);
								st="[";

								$("#t2").datagrid("loadData",data);
								for(var j=0;j<data.length;j++){
									if(j>0){
										st+=",";
									}
								   var pk=data[j].pk; 
								   var valueDesc=data[j].valueDesc;
				  				   var codeValue=data[j].codeValue; 
				  				   st+="{";
				  				   st+='"pk":'+'"'+pk+'"'+",";
				  	               st+='"valueDesc":'+'"'+valueDesc+'"'+",";
				  	               st+='"codeValue":'+'"'+codeValue+'"';
				  	               st+="}";
								}
								//endedit:$('#t2').datagrid('endEdit', lastIndex); 
								$('#t2').datagrid({    
								    onClickRow:function(rowIndex){ 
								        if (lastIndex != rowIndex){ 
								        	if(lastIndex!=null){
								        		 $('#t2').datagrid('endEdit', lastIndex); 
								        	}
								            $('#t2').datagrid('beginEdit', rowIndex);    
								            setEditing(rowIndex);    
								        }    
								        lastIndex = rowIndex;    
								    }    
								}); 
								//数据验证
							 	$("#uf").form('validate');
				 			}
					});
				    }
				});
	    }else{
	    	sing_msg("不可编辑！");
	    	return false;
	    }
		
	}
}

//查看码值
function lookPow(){
     //msg("编辑",'quertion');
	var uarray = getSeleces().split(",");
	if(uarray.length>5){
		sing_msg("请选择一条数据进行查看！");
		return;
	}else if(uarray.length<5 && (null==uarray || ""==uarray)){
		sing_msg("请选择待查看的数据！");
		return;
	}else{
		dlglistener("udlg1");
		$.post('checkCode',
				{'typeId':uarray[0]},
				function(data){
					if(data==null||data==('')){
						sing_msg('此字典已被删除！');
						$('#cdlist').datagrid('reload');
					}else{
						$.post('getCodeValueListByTypeId',{'typeId':uarray[0],'domainName':uarray[3]},function(data){
							if(hb.Obejct.parseJSON(data)){
								var lastIndex; 
								var editIndex = undefined;
								//oldpwd = data.loginPwd;
								$('#udlg1').dialog('open');
								$("#typeId1").val(uarray[0]);
								$("#typeName1").val(uarray[1]);
								$("#typeDesc1").val(uarray[2]);
								//$("#domainName").combotree('setText',uarray[3]);
								$("#domainName1").val(uarray[3]);
								$("#editFlag1").val(uarray[4]);
								$("#t21").datagrid("loadData",data);
								//endedit:$('#t2').datagrid('endEdit', lastIndex); 
							}
						});	
					}
			});
	}
}
//删除
function del(){
	var selectRows = $('#cdlist').datagrid('getSelections');
	    if(selectRows.length==0){
	    sing_msg("请选择需要删除的数据！");
	    return;
	   }else{
		   $.messager.confirm('提示', '您确认想要删除选中的数据吗？', function(r){
				if (r){
					//var array = new Array();
	                var array = getSelecestypeId();
					$.post('del',{'typeId':array},function(data){
						if(hb.Obejct.parseJSON(data)){
							sing_msg("删除成功！");
							$("#cdlist").datagrid('reload');
						}
					})
				}
			});
	  
  }
  }
//获取选中项
function getSelecestypeId(){
			var ids = [];
			var rows = $('#cdlist').datagrid('getSelections');
			for(var i=0;i<rows.length;i++){
				ids.push(rows[i].typeId);
			}
			return ids.join(',');
}
function setEditing(rowIndex){    
    var editors = $('#t2').datagrid('getEditors', rowIndex);    
    var valueDesc = editors[0];    
    var codeValue = editors[1];  
    valueDesc.target.bind('change', function(){
        var name=valueDesc.target.val(); 
        var row=$("#t2").datagrid("getRows");
		var a=0;
		  for(var i=0;i<row.length;i++){
		    var name1=row[i].valueDesc;
		    if(row[i]!=rowIndex){
			    if(name==name1){
			        a=1;
			    }
		    }
		  }
		  if(a==0){
		  	$('#t2').datagrid('getRows')[rowIndex]['valueDesc'] = name; 
		  }else{
		  	$.messager.alert("系统提示","有重复项！","warning"); 
		  	$('#t2').datagrid('getRows')[rowIndex]['valueDesc'] = "";
		  	valueDesc.target.val('');
		  }
        
    });    
    codeValue.target.bind('change', function(){
    	 var name=codeValue.target.val(); 
        var row=$("#t2").datagrid("getRows");
		var a=0;
		  for(var i=0;i<row.length;i++){
		    var name1=row[i].codeValue;
		    if(row[i]!=rowIndex){
			    if(name==name1){
			        a=1;
			    }
		    }
		  }
		  if(a==0){
		   $('#t2').datagrid('getRows')[rowIndex]['codeValue'] = name; 
		  }else{
		  	$.messager.alert("系统提示","有重复项！","warning"); 
		  	$('#t2').datagrid('getRows')[rowIndex]['codeValue'] = "";  
		  	codeValue.target.val('');
		  }
    }); 
} 
 function endEditing(editIndex){
            if (editIndex == undefined){return true}
             var ed = $('#t2').datagrid('getEditor', {index:editIndex,field:'codeValue'});
                var codeValue = $(ed.target).val();
                $('#t2').datagrid('getRows')[editIndex]['codeValue'] = codeValue;
                $('#t2').datagrid('endEdit', editIndex);
                editIndex = undefined;
        }

 function countChar(textarea,editIndex)  
      {  
  		  endEditing(editIndex);
      }  
//提示符
function sing_msg(msg){
	$.messager.show({
		title:'提示',
		msg:msg,
		showType:'slide',//show
		timeout:2000,
		style:{
			right:'',
			top:document.body.scrollTop+document.documentElement.scrollTop,
			bottom:''
		}
	});
}
//添加
function append(){
	/**$('#t2').datagrid('appendRow',{status:'P'});                
	editIndex = $('#dg').datagrid('getRows').length-1;                
	$('#t2').datagrid('selectRow', editIndex).datagrid('beginEdit', editIndex); 
	**/
	
    $('#t2').datagrid('appendRow',{
           pk:'',
           valueDesc:'',
           codeValue:'',
           status:'P'
      });
     editIndex = $('#t2').datagrid('getRows').length-1;
     $('#t2').datagrid('selectRow', editIndex);
     $('#t2').datagrid('beginEdit', editIndex); 
     setEditing(editIndex);
}

var editIndex = undefined;

//删除行
function removeit(){           
  var selectRows = $('#t2').datagrid('getSelections');
  if(selectRows.length==0){
    //sing_msg("请选择要删除的行!");
    $.messager.alert("系统提示","请选择需要删除的数据！","warning");  
    
    return;
  }else{
  	 $.messager.confirm('提示', '您确认想要删除选中的数据吗？', function(r){
				if (r){
					for(var i =0;i<selectRows.length;i++){ 
						var index = $('#t2').datagrid('getRowIndex',selectRows[i]);//获取某行的行号
					   $('#t2').datagrid('deleteRow',index);	//通过行号移除该行     
					   $('#t2').datagrid('unselectAll');
					   lastIndex=null;
				   }
				}
			});
   
  }        
}
//编辑数据,保存
function upd(){
	  var isValid = $("#uf").form('validate');
	  var row=$("#t2").datagrid("getRows");
	  var row1=$("#cdlist").datagrid("getRows");
	  var typeId=$("#typeId").val();
	  var typeName=hb.StrUtil.trim($("#typeName").val());
	  var typeDesc=hb.StrUtil.trim($("#typeDesc").val());
//	  if(typeName==""||typeDesc==""){
//	  $.messager.alert("系统提示","数据项为空，请重新填写！","warning"); 
//	  		return false;
//	  	}
	  //判断是否有重复的字典名称
	  for(var i=0;i<row1.length;i++){
	  	if(typeId!=row1[i].typeId&&typeName==row1[i].typeName){
	  		
	  		sing_msg("有重复的字典名称，请重新填写！"); 
	  		//document.getElementsByName("typeName1").select();
	  		$("#typeName").focus();
	  		return false;
	  	
	  	}
	  }
	  var strs = "";
	  strs+="[";
	  for(var i=0;i<row.length;i++){
	  	var pk=row[i].pk;
	  	var valueDesc=row[i].valueDesc;
	  	
	  	var codeValue=row[i].codeValue;
	  	if(valueDesc==""||codeValue==""){
	  		$.messager.alert("系统提示","数据项为空，请重新填写！","warning"); 
	  		$('#t2').datagrid('beginEdit', i);
	  		return false;
	  	}
	  	strs+="{";
	  	strs+='"pk":'+'"'+pk+'"'+",";
	  	strs+='"valueDesc":'+'"'+valueDesc+'"'+",";
	  	strs+='"codeValue":'+'"'+codeValue+'"';
	  	if(i==row.length-1){
	  		strs+="}";
	  	}else{
	  		strs+="},";
	  	}
	  	
	  }
	  strs+="]";
	  st+="]";
	  if(isValid){
	  	$.post('upd',
			{
				'typeId':$("#typeId").val(),
				'typeName':typeName,
				'typeDesc':typeDesc,
				'editFlag':($("#editFlag").val()),
				//'domainName':$("#domainName").combobox('getText'),
				'domainName':($("#domainName").val()),
				'data':strs,
				'delData':st
			},
			function(data){
				if(hb.Obejct.parseJSON(data)){
					sing_msg("保存成功！");
					$("#cdlist").datagrid('reload');
					$('#udlg').dialog('close');
					st="[";
				}
			});
	  }else{
	  		return isValid;
	  	}
	  
  }


//页面变化时 数据刷新
$(window).resize(function() {
	setTimeout(function(){
		query();
	},200);
	
});

