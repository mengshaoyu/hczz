var uploadBtn = "<div style='position: relative;'><a class='upload_btn' href='javascript:;'>添加附件</a>"
    		+ "<input type='file' class='fileContent'	></div>";
$(function() {
	initData();
});
//查询数据
function initData() {
	var url = ctxPath + "/pzfk/initData";
	$.post(url, {
		pzid : pzid,
		flowid : $("#hiddenFlowId").val()
	}, function(data) {
		if(data.err == 'err'){
			parent.showMessageCenter(data.errMsg);
		}
		
		$("#fkContent").html("");
		$.each(data.clues, function(i, item) {
			//拼装线索信息
			var $clue_div=$('<div class="xiansuo-container" id="clueId_'+item.clueId+'"></div>');
			$("#fkContent").append($clue_div);
			var clue_msg='<h3>查询内容'+(i+1)+'</h3>'+
			'<div class="xiansuo-box">'+
			'<table cellpadding="0" cellspacing="0" class="xiansuo-table" ><tr>'+
			'<td class="left">查询内容：</td>'+
			'<td class="right">'+item.clueName+'</td>'+
			'<td class="left">查询要求：</td>'+
			'<td class="right">'+item.serviceRequest+'</td></tr>'+
			'<tr><td class="left">线索来源：</td>'+
			'<td class="right">'+item.clueSource+'</td>'+	
			'<td class="left">来源证明：</td>'+
			'<td class="right" id="attNameDiv" >';
			for (var j = 0;j < item.att2.length ;j++ ){
				var strs= new Array();
				strs = item.att2[j].split(",");
				var attId = strs[0];
				var attName = strs[1];
				var attPath = strs[2];
				if(attPath == "0"){//无附件是attPath为0
					clue_msg += '<a href="javascript:;" title="'+attName+'">'+attName+'</a>';
				}else{
					clue_msg += '<a href="'+attPath+'" target="_blank" style="display:inline-block;color:#4080ee;" title="'+attName+'">'+attName+'</a>'+'&nbsp;';
				}
			}
			clue_msg += '</td></tr>'+
			'<tr><td class="left">详情描述：</td>'+
			'<td class="right" colspan="3">'+item.clueDesc+'</td>'+
			'</tr></table>'+
			'</div>';
			var flag_state="无";
			var sign_div='';
			if(item.flag==2){
				//当前用户、非当前用户在办状态
				flag_state='侦办中';
				sign_div='<div class="yinzhang"><img src="'+ctxPath+'/resource/image/pzfk/zbz.png" alt=""></div>';
			}else if(item.flag==1){
				//当前用户、非当前用户非在办状态
				flag_state='已完成反馈';
				sign_div='<div class="yinzhang"><img src="'+ctxPath+'/resource/image/pzfk/ywc.png" alt=""></div>';
			}
			var deal_div='<div class="shuming"><p style="text-indent:14px;">责任人：<span>'+item.clueAuxiluary+'</span></p><p style="text-indent:28px;">状态：<span>'+flag_state+'</span></p></div>';
			
			var button_div="<a href='javascript:void(0);' style='margin-right:10px;'  class='easyui-linkbutton l-btn' onclick='addResult(\""+item.clueId+"\");'><span class='l-btn-left'><span class='l-btn-text'>添加反馈</span></span></a>"+
				"<a href='javascript:void(0);' class='easyui-linkbutton l-btn' onclick='openClueSumup(\""+item.clueId+"\");' ><span class='l-btn-left'><span class='l-btn-text'>提交反馈</span></span></a>";
			var button_div1 = "<a href='javascript:void(0);' class='easyui-linkbutton l-btn' onclick='addResult(\""+item.clueId+"\");'><span class='l-btn-left'><span class='l-btn-text'>补充反馈</span></span></a>";
			
			//片段拼装
			if(item.flag1 == 1 ){	
				clue_msg+=deal_div+button_div1;
			}else if(item.flag1 == 3){
				clue_msg+=deal_div+button_div;
			}else if(item.flag1 == 2){
				clue_msg+=deal_div;
			}
			
			clue_msg+=sign_div;
			var pzResultInfo = "<div style='height:10px'></div><div id='result_" + item.clueId + "'></div>";
			$clue_div.append(clue_msg+pzResultInfo);
			var resultDatas = [];
			$.each(item.pzResult, function(j, jtem){
				var resultAtt = "";
				$.each(jtem.att, function(c, ctem){
					resultAtt += '<a href="'+ctem.attPath+'" target="_blank" style=" color:#4080ee;">'+ctem.attName+'</a></br>';
				});
				var resultData = {resultId : jtem.resultId,
								  resultNo : j + 1,
								  resultDesc : jtem.resultDesc,
								  resultBy : jtem.resultBy,
								  createBy : jtem.createBy,
								  resultRemark : jtem.resultRemark,
								  resultDate : jtem.resultDate,
								  resultAtt : !!resultAtt ? resultAtt : "无附件"};
				resultDatas.push(resultData);
			});
			$('#result_' + item.clueId).datagrid({
				fitColumns:true,
				columns : [ [{
					field : 'resultDesc',
					title : '反馈内容',
					width : 200,
					align:'left'
				}, {
					field : 'resultBy',
					title : '反馈来源',
					width : 200,
					align:'left'
				},{
					field : 'createBy',
					title : '责任人',
					width : 80,
					align:'center'
				},{
					field : 'resultAtt',
					title : '附件',
					width : 150,
					align:'left'
				}, {
					field : 'resultRemark',
					title : '详细说明',
					width : 250,
					align:'left'
				}, {
					field : 'resultDate',
					title : '登记时间',
					width : 100,
					align:'center'
				}, {
					field:'resultId',
					title:'操作',
					width:100,
					align:'center',
					formatter : function(value, row, index){
						var resultId = value;
						var str = "";
						if(item.flag1 == 3 || item.flag1 == 1){
							str += "<a title='编辑' href='javascript:void(0)' class='deletebtn' onclick='update(\""+row.resultId+"\")'>编辑</a>&nbsp;&nbsp;";
			            	str += "<a title='删除' href='javascript:void(0)' class='deletebtn' onclick='remove1(\""+row.resultId+"\")'>删除</a>";
						}
						return str;
					}
				} ] ],
				nowrap:false,
				data:resultDatas
			});
			if(item.flag==1){
				if(!!item.clueSumup){
					var textArea = "<div><span style='float:left;margin-bottom:5px;'>线索综述：</span><textarea rows='3' cols='200' readonly class='clue-textarea'>"+item.clueSumup+"</textarea></div>"
					$clue_div.append(textArea);
				}
			}
			$clue_div.append('<div class="clear"></div>');
		});
		if(null!=data.pjMap&&null!=data.pjMap.SUM_UP&&""!=data.pjMap.SUM_UP){
			var sumup="<div class='xiansuo-container'><h3>综合结论</h3><textarea rows='3' cols='200' readonly class='clue-textarea'>"+data.pjMap.SUM_UP+"</textarea><div class='clear'></div></div>";
			$("#fkContent").append(sumup);
		}
		if(null!=data.pjMap&&null!=data.pjMap.LANDING&&""!=data.pjMap.LANDING){
			var evuation="<div class='xiansuo-container'><h3>核实情况</h3><textarea rows='3' cols='200' readonly class='clue-textarea'>"+data.pjMap.LANDING+"</textarea><div class='clear'></div></div>";
			$("#fkContent").append(evuation);
		}
		
		//1.确认反馈  2.完成反馈 
		if(flag == 1 || flag == 2){
			$(".bottomBtn").show();
			$(".result-add").css("display","inline-block");
		}
	});
}
/**
 * 添加反馈
 */
function addResult(clueid){
	dlglistener("dlg");
	$('#dlg').dialog('open');
	$('#dlg').closest('div.panel').css('position', 'fixed');
	$("#af").form('clear');
	$("#clueId").val(clueid);
	$("#fileSpan").html("");
	$(".datebox :text").attr("readonly","readonly");
}

/**
 * 保存反馈信息
 */
function addsave(){
	var url = ctxPath + '/pzfk/addsave';
	var resultForm = $('#af').serialize()+"&pzId="+pzid;
	//信息校验
	var resultDesc = $("#resultDesc").val();
	var resultBy = $("#resultBy").val();
	var resultRemark = $("#resultRemark").val();
	var resultDate = $("#resultDate").val();
	if(resultDesc == ""){
		parent.showMessageCenter("反馈内容不能为空!");
		return false;
	}
	if(resultBy == ""){
		parent.showMessageCenter("来源不能为空!");
		return false;
	}
	if(resultRemark == ""){
		parent.showMessageCenter("详细说明不能为空!");
		return false;
	}
	if(resultDate == ""){
		parent.showMessageCenter("登记时间不能为空!");
		return false;
	}
	$.post(url, resultForm, function(data){
		if(data.code == 0){
			initData();
			$("#af").form('clear');
			$("#fileSpan").html("");
			$('#dlg').dialog('close');
			parent.showMessageCenter("保存成功！");
//			$.messager.alert("提示",data.msg);
		}
	});
}

//提交反馈时打开线索综述窗口
var submit_clueid="";
function openClueSumup(clueId){
	var fl = $("#result_"+clueId).datagrid("getRows").length > 0 ;
	if(!fl){
		$.messager.alert("提示","请添加反馈记录！");
	}else {
		submit_clueid=clueId;
		dlglistener("clue_sumup_dlg");
		$('#clue_sumup_dlg').dialog('open');
		$('#clue_sumup_dlg').closest('div.panel').css('position', 'fixed');
		$("#af_sumup").form('clear');
	}
}

//确认反馈，状态改为1007待生成反馈报告
function next(procInstId){
	var fl = true;
	$.each($("a[lab^='result_']"),function(i,item){
		var datagid = $(item).attr("lab");
		if($("#"+datagid).datagrid('getRows').length == 0){
			fl = false;
			$.messager.alert("提示","请添加反馈记录！");
			return false;
		}
	});
	if(fl){
		var sumup=$("#clueSumup").val();
		if(null==sumup||""==sumup||sumup.length==0){
			parent.showMessageCenter("请填写线索综述!");
			return false;
		}
		$.ajax({
	        type: "POST",
	        url:ctxPath + "/pzfk/next",
	        data: {
	        		procInstId:procInstId,
	        		pzid:$("#hiddenPzId").val(),
	        		clueId:submit_clueid,
	        		clueSumup:$("#clueSumup").val(),
	        		flowId:$("#hiddenFlowId").val()
	        	  },
	     	dataType: "text",
	        success: function(data) {
	            if(data == "success"){
	            	parent.alertMsgFunc("反馈成功!","info",function(){
	            		$("body").append("<form id='refreshView' action='"+ctxPath+"/pzfk/init'><input type='hidden' name='pzid' value='"+pzid+"'></form>");
						$("#refreshView").submit();
	            	})
	            }else{
	            	parent.showMessageCenter("反馈失败!");
	            }
	        }
	    });
	}
}

//完成反馈时打开综合结论窗口
var submit_clueid="";
function openSumup(){
	dlglistener("sumup_dlg");
	$('#sumup_dlg').dialog('open');
	$('#sumup_dlg').closest('div.panel').css('position', 'fixed');
	$("#af_sum").form('clear');
}

//待生成反馈报告确认，状态改为1008待生成反馈报告
function saveNext(procInstId){
	var sumup=$("#sumup").val();
	if(null==sumup||""==sumup||sumup.length==0){
		parent.showMessageCenter("请填写综合结论!");
		return false;
	}
	$.ajax({
        type: "POST",
        url:ctxPath + "/pzfk/nextCreate",
        data: {
        		procInstId:procInstId,
        		pzid:$("#hiddenPzId").val(),
        		sumup:$("#sumup").val(),
        		flowId:$("#hiddenFlowId").val()
        	  },
     	dataType: "text",
        success: function(data) {
            if(data == "success"){
            	$('#sumup_dlg').dialog('close');
            	parent.alertMsgFunc("确认生成反馈报告成功!","info",function(){
            		$("body").append("<form id='refreshView' action='"+ctxPath+"/pzfk/init'><input type='hidden' name='pzid' value='"+pzid+"'></form>");
					$("#refreshView").submit();
            	})
            }else{
            	parent.showMessageCenter("反馈失败!");
            }
           
        }
    });
}

//反馈删除
function remove1(resultId){
	confirmMsgFunc('删除反馈内容', '您是否确认删除本条反馈内容?',function (r){
		if(r){
			$.ajax({
				type : "POST",
				url : ctxPath + "/pzfk/remove",
				data : {
					resultId : resultId
				},
				dataType : "text",
				success : function(data) {
					if (data == "success") {
						parent.alertMsgFunc("反馈内容删除成功!","info",function(){
		            		$("body").append("<form id='refreshView' action='"+ctxPath+"/pzfk/init'><input type='hidden' name='pzid' value='"+pzid+"'></form>");
							$("#refreshView").submit();
		            	})
					} else{
						parent.showMessageCenter("反馈内容删除失败!");
					}
				}
			})
		}
	});
}

//反馈编辑
function update(resultId){
	   $.ajax({
			method:"post",
	        url:ctxPath +"/pzfk/update",
	        data : {
				resultId : resultId
			},
	        dataType:"json",
	        success:function(data){
	     			$("#hiddenFlag").val('1');
	     			$("#hiddenResultId").val(data.RESULT_ID);
	     			$("#resultDesc").val(data.RESULT_DESC);
	     			$("#resultBy").val(data.RESULT_BY);
	     			$("#resultRemark").val(data.RESULT_REMARK);
	     			$('#resultDate').val(data.RESULT_DATE);
	     			var attIds = 'attIds';
	     			if(data.attMap != null){
	     				$.each(data.attMap, function(idx, obj) {
			        	    var temp = '<span id="'+obj.ATT_ID+'" class="fjspan" style="display:inline-block;margin-top:-10px">';
			        	    temp+='<input type="hidden" id="attIds" name="'+attIds+'" value="'+obj.ATT_ID+'">';
			        	    temp+='<a href="'+obj.ATT_PATH+'" target="_blank" class="fjspancont">'+obj.ATT_NAME
			        	    +'</a><a class="removeFjButton" href="javascript:void(0)" onclick="removeFj(\''+obj.ATT_ID+'\')">删除</a></span>';
			        	    $("#fileSpan").append($(temp));
	     				});
	     			}	
	     			dlglistener("dlg");
	     			$(".panel-tool-close").hide();
		        	$('#dlg').dialog("open");
		        	$('#dlg').closest('div.panel').css('position', 'fixed');
	            }
	        });
}
//反馈保存
function updateadd(resultId){
	//信息校验
	var resultDesc = $("#resultDesc").val();
	var resultBy = $("#resultBy").val();
	var resultRemark = $("#resultRemark").val();
	var resultDate = $("#resultDate").val();
	if(resultDesc == ""){
		parent.showMessageCenter("反馈内容不能为空!");
		return false;
	}
	if(resultBy == ""){
		parent.showMessageCenter("来源不能为空!");
		return false;
	}
	if(resultRemark == ""){
		parent.showMessageCenter("详细说明不能为空!");
		return false;
	}
	if(resultDate == ""){
		parent.showMessageCenter("登记时间不能为空!");
		return false;
	}
	$.ajax({
     	type: "POST",
     	url:ctxPath +"/pzfk/updateAdd?resultId="+resultId,
     	data : $("#af").serialize(),
     	dataType: "text",
        success: function(data) {
            if(data == "success"){
    			location.reload();
            }else{
            	parent.showMessageCenter("反馈修改失败!");
            }
            
        }
    });
}


/**
 * 上传附件
 * 
 * @param type
 * @param obj
 * @param postName
 */
function getFileUrl(type,obj,postName) {
	var id=$(obj).attr("id");
	var app = $("#"+id).val();
	var appArr = app.split(".");
	/*if (appArr.length > 0) {
		var after = appArr[appArr.length - 1];
		after = after.toUpperCase();
		if (after != 'JPG' && after != 'PNG' && after != 'BMP' && after != 'GIF') {
			return;
		}
	}*/
	var dataTemp = {'ajid' : pzid, 'type' : type};

	//上传到服务器
	$.ajaxFileUpload({
		url : ctxPath + '/pztb/uploadFiles', // 上传文件的服务端
		secureuri : false, // 是否启用安全提交
		contentType : "text/html;charset=UTF-8",
		dataType : 'json', // 数据类型 xml / json /text
		data : dataTemp,
		fileElementId : id, // 表示文件域ID
		success : function(data) {
			if(data.code == 0){
				var temp = '<span id="'+data.list[0].attId+'" class="fjspan" style="display:inline-block;margin-top:-10px">';
				
					temp+='<input type="hidden" name="'+postName+'" value="'+data.list[0].attId+'">';
				
					temp+='<a href="'+data.list[0].url+'" target="_blank" class="fjspancont">'+data.list[0].name
						+'</a><a class="removeFjButton" href="javascript:void(0)" onclick="removeFj(\''+data.list[0].attId+'\')">删除</a></span>';
				if($("#fileSpan").nextAll(".fjspan").length > 0){
					$("#fileSpan").nextAll(".fjspan:last").append($(temp));
				}else{
					$("#fileSpan").append($(temp));
				}
			}else{
				showMessage(data.msg);
			}
		},
		error : function(data) {
			showMessage("附件上传失败");
		}
	})
}
function removeFj(str){
	var url = ctxPath + "/pztb/removeFiles";
	$.post(url,{attId : str},function(data){
		if(data.code == 0){
			$("#"+str).remove();
		}
	});
}

