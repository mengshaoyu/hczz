$(function(){
	$('#qType').combobox({
        valueField: 'code',
        textField: 'label',
        data:[{
        		code:"",
        		label:"全部"
       		   },{
        		code:"1",
        		label:"菜单"
       		   },{
       		    code:"2",
        		label:"菜单项"
       		   },{
       		    code:"3",
        		label:"链接"
       		   }]
    });
	$('#eType').combobox({
        valueField: 'code',
        textField: 'label',
        data:[{
        		code:"1",
        		label:"菜单"
       		   },{
       		    code:"2",
        		label:"菜单项"
       		   },{
       		    code:"3",
        		label:"链接"
       		   }]
    });
	
	$('#qOnuse').combobox({
        valueField: 'code',
        textField: 'label',
        data:[{
        	    code:"",
        		label:"全部"
       		   },{
        		code:"1",
        		label:"在用"
       		   },{
       		    code:"0",
        		label:"停用"
       		   }]
    });
    
	$('#eSysFlg').combobox({
        valueField: 'code',
        textField: 'label',
        data:[{
        	    code:"1",
        		label:"治安"
       		   },{
        		code:"2",
        		label:"所内"
       		   },{
       		    code:"3",
        		label:"管理"
       		   }]
    });
    
    $('#iconCls').combobox({
    	url: 'getIconClsList',
		valueField:'code',
		textField:'label',
		formatter:function(row){
			return '<span class="'+row.label+'" style="display:inline-block;vertical-align:middle;width:16px;height:16px;"></span> ' + row.label;
 		}
	});
    
    $("#editDiv").dialog({
       closed:true,
       height:340,
       width:354,
       modal:true,
       position:'center',
       resizable:false,
       buttons:[{
        text:'保存',
        iconCls:'icon-ok',
            handler:function() {
            //	if(doCheck()){
            		var url;
                    if($('#id').val() == ''){
                        url = 'add'; 
                    }else{
                        url = 'edit';
                    }
                    
			        var hasSysFlg = false;
			        var fields =$('#editForm').serializeArray();
			        $.each(fields, function(i, field){
			            if(field.name == 'sysflg'){
			            	hasSysFlg =true;
			            }
			        });
			        if(!hasSysFlg){
			        	fields[fields.length]={name:"sysflg", value:$('#eSysFlg').combobox('getValue')};
			        }
                    
                    $.post(url,
                        fields,
                        function(data){
                           if(hb.Obejct.parseJSON(data)){
                           	    // 添加成功
                              	if(data.msgCode == 'comm_001'){
					           		$('#editDiv').dialog('close');
                                	$.messager.alert('提示',data.msgInfo,'info');
                                	refreshDG();
                              	}else if(data.msgCode == 'comm_101'){
                              		$('#msgDivId').html(data.msgInfo);
					            }else{
					            	$.messager.alert('错误', data.msgInfo, 'warning');
					            }
							}
                        });
                	//}
                }
           },{
               text:'取消',
               iconCls:'icon-cancel',
               handler:function() {
                   $('#editDiv').dialog('close');
               }
           }]
    });
    
    $("#viewDiv").dialog({
           closed:true,
           height:290,
           width:354,
           modal:true,
           position:'center',
           resizable:false,
		   buttons:[{
               text:'关闭',
               iconCls:'icon-cancel',
               handler:function() {
                   $('#viewDiv').dialog('close');
               }
           }]
    });
    
    $("#qButton").on('click', function(){
        $("body").data("queryParam", $('#queryForm').serialize());
        refreshDG();
    	
    });
    
    $("#aButton").on('click', add);
    
    $('#dg').treegrid({
        title:'菜单列表',
        loadMsg:'正在处理，请稍等...',
        height:428,
        fitColumns: true,
        url:'getList',
        idField:'id',
        treeField:'name',
        rownumbers:true,
        singleSelect:true,
        onDblClickRow:showInfo,
        toolbar:[
	        {   text:'添加',
				iconCls:'icon-add',
				handler:add
			}, {
				text:'编辑',
				iconCls:'icon-edit',
				handler:edit
			},'-',{
				text:'删除',
				iconCls:'icon-cancel',
				handler:del
			}
        ],
        loadFilter:function(data, parentId){
        	if(data){
				if (hb.Obejct.parseJSON(data)){
					return data;
				}
        	}
        }            
    });
	
});

function add(){
   	var node = $('#dg').treegrid('getSelected');
    if(node){
        $('#eParentName').html(node.name);
        $('#eParentId').val(node.id);
        $('#eSysFlg').combobox('setValue', node.sysflg);
        $('#eSysFlg').combobox('disable');
    }else{
    	$('#eParentName').html("无");
        $('#eParentId').val("");
        $('#eSysFlg').combobox('enable');
        $('#eSysFlg').combobox('setValue', 1);
    }
    $('#eUrl').val('');
    
    $("#editDiv").dialog({ 
        title:'添加',
        iconCls:'icon-add'
    });
    $('#editDiv').dialog('open');
}

function edit(id){
	if(typeof id  == 'string'){
		$('#dg').datagrid('selectRow', id);
	}
	
	var row = $('#dg').treegrid('getSelected');
	if(row){
	  $.post("getInfo?id="+row.id, function(data){
	  	  if (hb.Obejct.parseJSON(data)){
	  	    $('#eSysFlg').combobox('setValue',data.sysflg);
	  	    $('#eSysFlg').combobox('disable');
	  	    $('#eParentName').html(data.parent==null?'无':data.parent);
	  	    $('#id').val(data.uuid);
	  	    $('#eName').val(data.name);
	  	    $('#eUrl').val(data.url);
	  	    $('#eType').combobox('setValue', data.type);
	  	    if(data.iconcls){
	  	      $('#iconCls').combobox('setValue', data.iconcls);
	  	    }else{
	  	      $('#iconCls').combobox('clear');
	  	    }
	  	    if(data.onuse ==1){
	  	    	editForm.onuse[0].checked = true;
	  	    }else{
	  	    	editForm.onuse[1].checked = true;
	  	    }
	  	    $('#eOrderNum').val(data.ordernum);
	  	    
	  	    $("#editDiv").dialog({ 
		        title:'编辑',
		        iconCls:'icon-edit'
		    });
		    $('#editDiv').dialog('open');
	  	  }
	  	}
	  );
	}else{
		$.messager.alert("信息","请选择要修改的菜单！","info")
	}
}

function del(id){
	if(typeof id  == 'string'){
		$('#dg').datagrid('selectRow', id);
	}
	
	var row = $('#dg').treegrid('getSelected');
	if(row){
		var msgInfo = "您确定想要删除选中的数据吗？";
        $.messager.confirm('提示', msgInfo, function(result){
            if (result) {
                $.post('del',
                    {"id":row.id, "type":row.type},
                    function(data) {
                    	if(hb.Obejct.parseJSON(data)){
                    		if('comm_001'==data.msgCode){
                    			$.messager.alert("信息",data.msgInfo,"info");
                    			refreshDG();
                    		}else{
                    			$.messager.alert("信息",data.msgInfo,"info");
                    		}
                    	}
                    });
            }
        });
		
		
	}else{
		$.messager.alert("信息","请选择想要删除的数据！","info")
	}
}

/**
 * 双击时，展示详情对话框
 * 
 * @param {} index
 * @param {} data
 */
function showInfo(row){
	$.post("getInfo?id="+row.id, function(data){
	  	  if (hb.Obejct.parseJSON(data)){
	  	    $('#vParent').html(data.parent==null?'无':data.parent);
	  	    $('#vName').html(data.name);
	  	    $('#vUrl').html(data.url);
	  	    $('#vType').html(data.typeName);
	  	    if(data.iconclsName){
	  	      $('#vIconcls').html('<span class="'+data.iconclsName+'" style="display:inline-block;vertical-align:middle;width:16px;height:16px;"></span> ');
	  	    }else{
	  	    	$('#vIconcls').html('无');
	  	    }
	  	    if(data.onuse == 1){
	  	      $('#vOnuse').html('在用');
	  	    }else{
	  	      $('#vOnuse').html('停用');
	  	    }

	  	    $('#vOrdernum').html(data.ordernum);
		    $('#viewDiv').dialog('open');
	  	  }
	  	}
	  );
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