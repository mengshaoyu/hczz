var cxnrstr = "";
var cxnrsize = 0;
var ajlxObj = {};
var markpointedit = true;
var cxrcn = '<table><tr><td>1、本人知悉所查询的内容属公民个人信息，本人承诺严格遵守国家相关法律、法规和政策，严格执行有关规定。'
				+ '<br>2、本人承诺严格遵守保密纪律，妥善保管查询结果，不向无关组织和个人透露查询内容，不将查询结果作为证据使用，如有违反将依法承担责任。'
				+ '<br>3、本人承诺所提交的证明材料和文件真实有效。</td></tr></table>';
$(function(){
	initCss();
	initSearchAjbh();
	initApproveUser();
	initYwlxAndAjlx();
	cxnrstr = $("#cxnr_panel .panelContent").prop("outerHTML");
	initData();
	
});
function initYwlxAndAjlx(){
	
	//解决谷歌浏览器下，案件状态一列下拉框宽度显示异常的问题
	var offwidth=0;
	var ua = navigator.userAgent.toLocaleLowerCase();
	if(ua.match(/chrome/)){
		offwidth=24;
		var width=window.screen.width;
		if(width>1600){
			offwidth=33;
		}else if(width>1440){
			offwidth=27;
		}
	}
	
	$('#task_panel').siblings('.panel-header').addClass('psn');
	$("#caseType").css("width",$("#caseNo").width()+"px");
	$("#acceptUnit").css("width",$("#caseNo").width()+"px");
	$("#rwjb").css("width",$("[name='clueList[0].clueName']").width()+"px");
	$("#caseAjly").css("width",$("#caseNo").width()+"px");
	$("#caseTypeImp").css("width",$("#caseNo").width()+"px");
	$("#caseStatus").css("width",($("#caseNo").width()+offwidth)+"px");
	
	//查询内容中的查询要求
	var ywlxObj = $.parseJSON(ywlx);
	var pcodeTemp = "";
	var $optgroup ;
	var $option ;
	$.each(ywlxObj, function(i, item){
		if(pcodeTemp != item.PCODE){
			pcodeTemp = item.PCODE;
			$optgroup = $("<optgroup></optgroup>");
			$optgroup.attr("label",item.PVAL).attr("value",item.PCODE);
			$("#pzTypeDetail").append($optgroup);
		}
		$option = $("<option></option>");
		$option.attr("value",item.CCODE).text(item.CVAL);
		$optgroup.append($option);
	});
	
	ajlxObj = $.parseJSON(ajlx);
	$("#caseType").combobox({
		width : "auto",
		height : "34",
		valueField:"codeValue",
		textField:"valueDesc",
		data:ajlxObj
	});
	$("#rwjb").combobox({
		width : "auto",
		height : "34",
		panelHeight : "100",
		valueField: 'value',
		textField: 'label',
		editable:false ,
		data: [{
			label: '三级',
			value: '3',
			selected : true
		},{
			label: '二级',
			value: '2'
		},{
			label: '一级',
			value: '1'
		}]
	});
	//$("#caseType").combobox("loadData",ajlxObj);
	
	$("#addFlwsType").combobox({
		width : "auto",
		height : "34",
		valueField: 'value',
		editable:false ,
		textField: 'label',
		data: [{
			label: '受案登记表',
			value: '2'
		},{
			label: '立案决定书',
			value: '3'
		},{
			label: '呈请立案报告书',
			value: '4'
		}]
	});
	
	$("#caseAjly").combobox({
		width : "auto",
		height : "34",
		panelHeight : "100",
		valueField: 'value',
		editable:false ,
		textField: 'label',
		data: [{
			label: '110指令',
			value: '1'
		},{
			label: '派出所上报',
			value: '2'
		},{
			label: '肇事逃逸',
			value: '3'
		}]
	});
	var caseTypeImpObj = $.parseJSON(caseTypeImp);
	$("#caseTypeImp").combobox({
		width : "auto",
		height : "34",
		panelHeight : "100",
		valueField: 'codeValue',
		textField: 'valueDesc',
		editable:false ,
		data: caseTypeImpObj,
		onSelect: function(rec){
			//案件类型和案件状态级联
			var ajztObj = $.parseJSON(ajzt);
			var ajztObjTemp = [];
			$.each(ajztObj,function(i,item){
				if(item.codeValue.substr(0,2) == rec.codeValue){
					ajztObjTemp.push(item);
				}
			});
			$("#caseStatus").combobox({
				width : "auto",
				height : "34",
				panelHeight : "250",
				valueField: 'codeValue',
				textField: 'valueDesc',
				editable:false ,
				data: ajztObjTemp
			});
		}
	});
	var ajztObj = $.parseJSON(ajzt);
	$("#caseStatus").combobox({
		width : "auto",
		height : "34",
		panelHeight : "250",
		valueField: 'codeValue',
		textField: 'valueDesc',
		editable:false ,
		data: ajztObj
	});
	$('#acceptUnit').combotree({
		width:'auto',
		height : "34",
		url:ctxPath+'/deptment/getDeptTreeByPrior?fid='+deptId
	})
}
function addWsfj(){
	opendialog('addFlws');
}
function initEdit(){

	if(sfbj == 0){
		$(".panelContent").find("input,select,textarea").attr("disabled","disabled");
		$(".deleteCxnr").remove();
		$(".saveCxnr").remove();
		$("#addWsfjBtn").hide();
		$(".upload_btn").remove();
		$(".removeFjButton").remove();
		$(".bottomBtn").remove();
		$("#addCxnrBtn").hide();
		//$('#markpointbtn').hide();
		$('#caseType').combobox('readonly');
		$('#acceptUnit').combobox('readonly');
		$('#caseAjly').combobox('readonly');
		$('#caseTypeImp').combobox('readonly');
		$('#caseStatus').combobox('readonly');
		$('#rwjb').combobox('readonly');
		$(".combo-text").css('color','#4d4d4d');
		
	}else{
		$.each($("#ajxx_panel input:not('.combo-text'),#ajxx_panel textarea"),function(i,item){
			//$("#ajxx_panel input:not('.combo-text'),#ajxx_panel textarea").attr("readOnly","readOnly").css("color","#545454");
			if(!!$(item).val()){
				$(item).attr("readOnly","readOnly").css("color","#545454");
			}
		});
		if(!!$("#caseAjly").combobox('getValue')){
			$("#caseAjly").combobox('readonly');
		}
		if(!!$("#acceptUnit").combobox('getValue')){
			$("#acceptUnit").combobox('readonly');
		}
		if(!!$("#caseTypeImp").combobox('getValue')){
			//案件类型和案件状态级联
			var ajztObj = $.parseJSON(ajzt);
			var ajztObjTemp = [];
			$.each(ajztObj,function(i,item){
				if(item.codeValue.substr(0,2) == $("#caseTypeImp").combobox('getValue')){
					ajztObjTemp.push(item);
				}
			});
			if(!$("#caseStatus").combobox('getValue')){
				$("#caseStatus").combobox({
					width : "auto",
					height : "34",
					panelHeight : "250",
					valueField: 'codeValue',
					textField: 'valueDesc',
					editable:false ,
					data: ajztObjTemp
				});
			}
			
			$("#caseTypeImp").combobox('readonly');
		}
		if(!!$("#caseStatus").combobox('getValue')){
			var caseTypeImpObj = $.parseJSON(caseTypeImp);
			var caseTypeImpObjTemp = [];
			$.each(caseTypeImpObj,function(i,item){
				if($("#caseStatus").combobox('getValue').substr(0,2) == item.codeValue){
					caseTypeImpObjTemp.push(item);
				}
			});
			if(!$("#caseTypeImp").combobox('getValue')){
				
				$("#caseTypeImp").combobox({
					width : "auto",
					height : "34",
					panelHeight : "100",
					valueField: 'codeValue',
					textField: 'valueDesc',
					editable:false ,
					data: caseTypeImpObjTemp,
					onSelect: function(rec){
					}
				});
			}
			$("#caseStatus").combobox('readonly');
		}
		if(!!$("#caseType").combobox('getValue')){
			$("#caseType").combobox('readonly');
		}
		if(!!$("#acceptDate").val()){
			$("#acceptDate").attr("disabled","disabled");
		}
		if(!!$("#incidentDate").val()){
			$("#incidentDate").attr("disabled","disabled");
		}
		if(!!$("#mapX").val() && !!$("#mapY")){
			//$('#markpointbtn').hide();
			markpointedit = false;
		}
		$(".combo input[readonly='readonly']").css('color','#4d4d4d');
	}
}

function initData(){
	var url = ctxPath + "/pztb/loadHctb";
	$.post(url,{pzid:pzid},function(data){
		if(data.code == 0){
			showBhws(data);
			$(".rootItem").text(data.aj.caseNo);
			//案件信息加载
			for(x in data.aj){
				if(x == "caseType" || x == "caseAjly" || x == "caseTypeImp" || x == "caseStatus"){
					$('#'+x).combobox('setValue', data.aj[x]);
				}else if(x == "acceptUnit"){
					$('#'+x).combotree('setValue', data.aj[x]);
				}else{
					$("input[name='caseInfo."+x+"']").val(data.aj[x]);
				}
			}
			$("textarea[name='caseInfo.caseDesc']").val(data.aj.caseDesc);
			//$("#ajxx_panel input:not('.combo-text'),#ajxx_panel textarea").attr("readonly","readonly").css("color","#545454");
			//$("#acceptDate").attr("disabled","disabled");
			//法律文书加载
			var wsObj = {};
			if(!!data.lajds){
				wsObj.lajds = data.lajds;
			}
			if(!!data.sadjb){
				wsObj.sadjb = data.sadjb;
			}
			if(!!data.cqlabgs){
				wsObj.cqlabgs = data.cqlabgs;
			}
			for(x in wsObj){
				$("#"+x).nextAll(".fjspan").remove();
				$.each(wsObj[x],function(i,item){
					var temp = '<span id="'+item.attId+'" class="fjspan wsfjspan">';
						temp+='<input type="hidden" name="'+x+'" value="'+item.attId+'">';
						temp+='<a href="'+item.attPath+'" target="_blank" class="fjspancont" title="'+item.attName+'">'+item.attName
							+'</a><a class="removeFjButton" href="javascript:void(0)" onclick="removeFj(\''+item.attId+'\')">删除</a></span>';
					$("#"+x).append($(temp));
				});
			}
			//查询线索加载
			$("#cxnr_panel").find("div.panelContent").remove();
			$.each(data.clue,function(i,item){
				addCxnr();
				for(x in item){
					$("input[name='clueList["+cxnrsize+"]."+x+"']").val(item[x]);
					$("textarea[name='clueList["+cxnrsize+"].clueDesc']").val(item.clueDesc);
					$("select[name='clueList["+cxnrsize+"].pzTypeDetail']").val(item.pzTypeDetail);
					if(x == "att"){
						$.each(item[x],function(j,jtem){
							var temp = '<span id="'+jtem.attId+'" class="fjspan">';
								temp+='<input type="hidden" name="clueFile" value="'+jtem.attId+'##'+cxnrsize+'">';
								temp+='<a href="'+jtem.attPath+'" target="_blank" class="fjspancont">'+jtem.attName
									+'</a><a class="removeFjButton" href="javascript:void(0)" onclick="removeFj(\''+jtem.attId+'\')">删除</a></span>';
							$("input[name='clueList["+cxnrsize+"].lyzm']").parent().before($(temp));
						});
					}
				}
			});
			//
			ajid = data.ajid;
			$("select[name='PzApply.pzApplyGrade']").val(data.tasklevel);
			initEdit();
		
		}
	});
}

function initApproveUser(){
	var url = ctxPath + "/pztb/getApproveUser";
	var fgldUsers = "";
	$.post(url,{flag:"1"},function(data){
		if(data.code == 0){
			var tr = "";
			$.each(data.list,function(i,item){
				if(i % 2 == 0){
					if(i == 0){
						tr += "<table style='width:100%'><tr>";
					}else{
						tr += "</tr><tr>";
					}
				}
				tr += '<td style="border:0;height:30px"><label style="display:inline-block"><input type="checkbox" style="width:30px;border:0" name="approveUser" value="'+item.userId+'">' + item.userName + "</label></td>";
				if(i == data.list.length -1){
					tr += "</tr></table>";
				}
				//fgldUsers += '<label style="display:inline-block"><input type="checkbox" style="width:30px;border:0" name="approveUser" value="'+item.userId+'">' + item.userName + "&nbsp;&nbsp;&nbsp;</label>";
			});
			$("#fgld").html(tr);
		}
	});
	
}
function showBhws(data){
	var sadjbcount = data.sadjbcount;
	var lajdscount = data.lajdscount;
	var bhwscounttext = "系统自动检测，当前案件在执法闭环管理系统中";
	if(sadjbcount > 0){
		bhwscounttext += "已生成受案登记表，";
	}else{
		bhwscounttext += "未生成受案登记表，";
	}
	if(lajdscount > 0){
		bhwscounttext += "已生成立案决定书。";
	}else{
		bhwscounttext += "未生成立案决定书。";
	}
	$("#bhwscount").text(bhwscounttext);
}
function initSearchAjbh(){
	$("#caseNo").on("blur",function(){
		if(hb.StrUtil.trim($("#caseNo").val())==""){
			return;
		}
		$("#caseNo").css("border","1px solid #ddd");
		$("#caseNo").nextAll("br,span.tooltips").remove();
		/*$(item).unbind("hover");*/
		if(!$.fn.validatebox.defaults.rules['ajbh'].validator($("#caseNo").val().trim(),'ajbh')){
			var errtext = $.fn.validatebox.defaults.rules['ajbh'].message.replace("{0}",$.fn.validatebox.parseOptions($("#caseNo"))['ajbh'][0]).replace("{1}",$.fn.validatebox.parseOptions($("#caseNo"))['ajbh'][1]);
			$("#caseNo").css("border","1px solid #f73723");
			$('<br><span class="tooltips">'+$("#caseNo").attr("label")+errtext+'</span>').appendTo($("#caseNo").parent());
			return;
		}
		if(!newaj){
			return;
		}
		var url = ctxPath+"/pztb/searchAj";
		$.post(url,{ajid:$("#caseNo").val()},function(data){
			if(data.code == -1){
				parent.alertMsg(data.msg,"warning");
			}else{
				showBhws(data);
				
				$(".rootItem").text(data.caseInfo.caseNo);
				newaj = false;
				ajid = data.caseInfo.caseId;
				for(x in data.caseInfo){
					if(x == "caseType" || x == "caseAjly" || x == "caseTypeImp" || x == "caseStatus"){
						$('#'+x).combobox('setValue', data.caseInfo[x]);
					}else if(x == "acceptUnit"){
						$('#'+x).combotree('setValue', data.caseInfo[x]);
					}else{
						$("#ajxx_form input[name='caseInfo."+x+"']").val(data.caseInfo[x]);
					}
				}
				$("#ajxx_form textarea[name='caseInfo.caseDesc']").val(data.caseInfo.caseDesc);
				$.each($("#ajxx_panel input:not('.combo-text'),#ajxx_panel textarea"),function(i,item){
					//$("#ajxx_panel input:not('.combo-text'),#ajxx_panel textarea").attr("readOnly","readOnly").css("color","#545454");
					if(!!$(item).val()){
						$(item).attr("readOnly","readOnly").css("color","#545454");
					}
				});
				if(!!$("#caseAjly").combobox('getValue')){
					$("#caseAjly").combobox('readonly');
					$("#caseAjly").next().find('input').css("color","#545454");
				}
				if(!!$("#acceptUnit").combobox('getValue')){
					$("#acceptUnit").combobox('readonly');
					$("#acceptUnit").next().find('input').css("color","#545454");
				}
				if(!!$("#caseTypeImp").combobox('getValue')){
					//案件类型和案件状态级联
					var ajztObj = $.parseJSON(ajzt);
					var ajztObjTemp = [];
					$.each(ajztObj,function(i,item){
						if(item.codeValue.substr(0,2) == $("#caseTypeImp").combobox('getValue')){
							ajztObjTemp.push(item);
						}
					});
					if(!$("#caseStatus").combobox('getValue')){
						$("#caseStatus").combobox({
							width : "auto",
							height : "34",
							panelHeight : "250",
							valueField: 'codeValue',
							textField: 'valueDesc',
							editable:false ,
							data: ajztObjTemp
						});
					}
					$("#caseTypeImp").combobox('readonly');
					$("#caseTypeImp").next().find('input').css("color","#545454");
				}
				if(!!$("#caseStatus").combobox('getValue')){
					var caseTypeImpObj = $.parseJSON(caseTypeImp);
					var caseTypeImpObjTemp = [];
					$.each(caseTypeImpObj,function(i,item){
						if($("#caseStatus").combobox('getValue').substr(0,2) == item.codeValue){
							caseTypeImpObjTemp.push(item);
						}
					});
					if(!$("#caseTypeImp").combobox('getValue')){
						$("#caseTypeImp").combobox({
							width : "auto",
							height : "34",
							panelHeight : "100",
							valueField: 'codeValue',
							textField: 'valueDesc',
							editable:false ,
							data: caseTypeImpObjTemp,
							onSelect: function(rec){
							}
						});
					}
					$("#caseStatus").combobox('readonly');
					$("#caseStatus").next().find('input').css("color","#545454");
				}
				if(!!$("#caseType").combobox('getValue')){
					$("#caseType").combobox('readonly');
					$("#caseType").next().find('input').css("color","#545454");
				}
				if(!!$("#acceptDate").val()){
					$("#acceptDate").attr("disabled","disabled");
				}
				if(!!$("#incidentDate").val()){
					$("#incidentDate").attr("disabled","disabled");
				}
				var wsObj = {};
				if(!!data.lajds){
					wsObj.lajds = data.lajds;
				}
				if(!!data.sadjb){
					wsObj.sadjb = data.sadjb;
				}
				if(!!data.cqlabgs){
					wsObj.cqlabgs = data.cqlabgs;
				}
				for(x in wsObj){
					$("#"+x).nextAll(".fjspan").remove();
					$.each(wsObj[x],function(i,item){
						var temp = '<span id="'+item.attId+'" class="fjspan wsfjspan">';
							temp+='<input type="hidden" name="'+x+'" value="'+item.attId+'">';
							temp+='<a href="'+item.attPath+'" target="_blank" class="fjspancont" title="'+item.attName+'">'+item.attName
								+'</a><a class="removeFjButton" href="javascript:void(0)" onclick="removeFj(\''+item.attId+'\')">删除</a></span>';
						$("#"+x).append($(temp));
					});
				}
				resizeFrameHeight();
			}
		});
	});
}
function initCss(){
	$(".ajxx td:nth-child(2n+1),.cxnr td:nth-child(2n+1),.task td:nth-child(2n+1)").css("width","8%").attr("align","right");
	$(".ajxx td:nth-child(2n),.cxnr td:nth-child(2n),.task td:nth-child(2n)").css("width","22%").css("padding-left","10px");
	if(fromDetail == "1"){
		$(".outertitle").hide();
	}else{
		$(".outertitle").show();
	}
}
function addCxnr(){
	if($("#cxnr_panel .panelTable").length > 5){
		parent.alertMsg("最多添加6个查询条件！","warning");
		return;
	}
	cxnrsize ++;
	$(cxnrstr).appendTo($("#cxnr_panel"));
	$("#cxnr_panel").find(".panelContent:last").find("input[type=file]").attr("id","lyzm"+cxnrsize);
	var formEle = $("#cxnr_panel").find(".panelContent:last").find("select,input[name],textarea");
	$.each(formEle,function(i,item){
		var name = $(item).attr("name");
		$(item).attr("name","clueList["+cxnrsize+"]."+name.split(".")[1]);
	});
	resizeFrameHeight();
}

/**
 * 暂存
 */
function saveTemp(zcOrTb){
	onloading();
	if(validateForm("ajxx_form")){
		removeload();
		return;
	}
	var param = $('#ajxx_form').serialize()
				+"&newaj="+newaj
				+"&pzApply.pzApplyId="+pzid;
	if(!!ajid){
		param += "&caseInfo.caseId="+ajid;
	}
	$.post(ctxPath+"/pztb/saveTemp",param,function(data){
		parent.alertMsgFunc(data.msg,"info",function(){
			if(data.code==0){
				$("body").append("<form id='refreshView' action='"+ctxPath+"/pztb/init'><input type='hidden' name='pzid' value='"+pzid+"'><input type='hidden' name='sfbj' value='1'></form>");
				removeload();
				$("#refreshView").submit();
			}else{
				$("body").append("<form id='refreshView' action='"+ctxPath+"/pztb/init'><input type='hidden' name='pzid' value='"+pzid+"'><input type='hidden' name='sfbj' value='0'></form>");
				removeload();
				$("#refreshView").submit();
			}
		});
	});
}

/**
 * 查询人承诺确认
 */
function cxrcnok(){
	closedialog('cxrcndialog');
	$("#deptname").html(deptname);
	opendialog('spdialog');
}
/**
 * 提报
 */
function saveNext(){
	$("#spdialog-ok").attr("disabled","disabled");
	onloading();
	var isChecked = false;
	var appUsers = "";
	$.each($("input[name='approveUser']"),function(i, item){
		if($(item).prop('checked')){
			isChecked = true;
			appUsers += "approveUser="+$(item).val()+"&";
		}
	});
	if(appUsers == ""){
		removeload();
		parent.alertMsg("请选择审批人！","warning");
		$("#spdialog-ok").removeAttr("disabled");  
		return;
	}
	var appUsers = "";
	$.each($("input[name='approveUser']"),function(i, item){
		if($(item).prop('checked')){
			isChecked = true;
			appUsers += "approveUser="+$(item).val()+"&";
		}
	});
	if(validateForm("ajxx_form")){
		$("#spdialog-ok").removeAttr("disabled");  
		return;
	}
	var param = $('#ajxx_form').serialize()
				+"&newaj="+newaj
				+"&pzApply.pzApplyId="+pzid;
	if(!!ajid){
		param += "&caseInfo.caseId="+ajid;
	}
	$.post(ctxPath+"/pztb/saveTemp",param,function(data){
		if(data.code==0){
			appUsers += "pzid="+pzid;
			var url = ctxPath + '/pztb/saveNext';
			$.post(url, appUsers, function(data){
				parent.alertMsgFunc(data.msg,"info",function(){
					if(data.code==0){
						if(!!parent.refreshDetailList_){
							parent.refreshDetailList_(pzid,'0','0');
						}else{
							$("body").append("<form id='refreshView' action='"+ctxPath+"/pztb/init'><input type='hidden' name='pzid' value='"+pzid+"'><input type='hidden' name='sfbj' value='0'></form>");
							removeload();
							$("#refreshView").submit();
						}
					}
				});
			});
		}else{
			parent.alertMsgFunc(data.msg,"warning",function(){
				$("body").append("<form id='refreshView' action='"+ctxPath+"/pztb/init'><input type='hidden' name='pzid' value='"+pzid+"'><input type='hidden' name='sfbj' value='0'></form>");
				removeload();
				$("#refreshView").submit();
			});
		}
	});
}


/**
 *  校验form
 * @returns
 */
function validateForm(formId,nameprefix){
	var ele = $("#"+formId).find("input[data-options],textarea[data-options]");
	var flag = false;
	//var errmsg = "";
	$.each(ele,function(i,item){
		var rule = $.fn.validatebox.parseOptions($(item));
		var errtext = "";
		for(x in rule){
			$(item).nextAll("br,span.tooltips").remove();
			/*$(item).unbind("hover");*/
			if(!$.fn.validatebox.defaults.rules[x].validator($(item).val().trim(),rule[x])){
				if($(item).attr("type") == "file" && $(item).nextAll($(".fjspan")).length > 0){
					break;
				}
				flag = true;
				errtext += $.fn.validatebox.defaults.rules[x].message.replace("{0}",rule[x][0]).replace("{1}",rule[x][1]);
				//$(item).css("border","1px solid #f73723");
				//errmsg += $(item).attr("label")+errtext+"<br>";
				$('<span class="tooltips">'+$(item).attr("label")+errtext+'</span>').appendTo($(item).parent());
				/*$(item).hover(function(){
			    	$(item).nextAll("span.tooltips").css("display","block");
			    },function(){
			    	$(item).nextAll("span.tooltips").css("display","none");
			    });*/
				break;
			}
		}
	});
	var caseTypeVal = false;
	$("#caseType").nextAll("br,span.tooltips").remove();
	if(!!$("#caseType").combobox('getValue')){
		$.each($("#caseType").combobox('getData'),function(i, item){
			caseTypeVal = true;
			if(item.valueDesc == $("#caseType").combobox('getText')){
				caseTypeVal = false;
				return false;
			}
		})
		if(caseTypeVal){
			$('<span class="tooltips">'+$("#caseType").attr("label")+'与选项值不匹配！</span>').appendTo($("#caseType").parent());
		}
	}
	if(flag || caseTypeVal){
		$("html, body").animate({scrollTop: $("#first-div").offset().top }, {duration: 50,easing: "swing"});
		// parent.alertOverRide(errmsg,"none","loading-mask");
	}
	return flag || caseTypeVal;
}
function clickFileUper(obj){
	
}

/**IE8 下不支持indexOf方法,自行封装兼容IE8
if(!Array.prototype.indexOf){    
   Array.prototype.indexOf = function(val){    
       var value = this;    
       for(var i =0; i < value.length; i++){    
          if(value[i] == val) return i;    
       }    
      return -1;    
   };    
}   */
function uploadFlws(){
	var flwsType = $('#addFlwsType').combobox('getValue');
	if(!flwsType){
		parent.alertMsg("请选择文书类型","error");
		return;
	}
	if(!$('#fjfile').val()){
		parent.alertMsg("请选择要上传的文书附件","error");
		return;
	}
	switch (flwsType){
		case "2":
			getFileUrl(flwsType,'','sadjb','fjfile');
			break;
		case "3":
			getFileUrl(flwsType,'','lajds','fjfile');
			break;
		case "4":
			getFileUrl(flwsType,'','cqlabgs','fjfile');
			break;
	}
	$('#addFlwsType').combobox('setValue','');
	$('#fjfile').val('');
	closedialog('addFlws');
}

function getFileUrl(type,obj,postName,idt) {
	var id=!!idt ? idt : $(obj).attr("id");
	var app = $("#"+id).val();
	var appArr = app.split(".");
	if(id.indexOf("lyzm") != -1){
		if($("#"+id).parent().parent().find(".fjspan").size() >= 7){
			parent.alertMsg("最多添加7个来源证明！","error");
			return;
		}
	}
	if (appArr.length > 0) {
		var after = appArr[appArr.length - 1];
		after = after.toUpperCase();
		if (after != 'JPG' && after != 'PNG' && after != 'BMP' && after != 'GIF' && after != 'JPEG'
			&& after != 'DOC' && after != 'DOCX' && after != 'XLS' && after != 'XLSX' && after != 'PDF') {
			parent.alertMsg("请上传JPG、PNG、BMP、GIF、JPEG、DOC、DOCX、XLS、XLSX、PDF格式的文件！","error");
			return;
		}
	}
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
				var temp = '';
				if(id.indexOf("lyzm") != -1){
					temp = '<span id="'+data.list[0].attId+'" class="fjspan">';
					temp+='<input type="hidden" name="'+postName+'" value="'+data.list[0].attId+'##'+id.charAt(id.length-1)+'">';
					temp+='<a href="'+data.list[0].url+'" target="_blank" class="fjspancont"  title="'+data.list[0].name+'">'+data.list[0].name
					+'</a><a class="removeFjButton" href="javascript:void(0)" onclick="removeFj(\''+data.list[0].attId+'\')">删除</a></span>';
				}else{
					temp = '<span id="'+data.list[0].attId+'" class="fjspan wsfjspan">';
					temp+='<input type="hidden" name="'+postName+'" value="'+data.list[0].attId+'">';
					temp+='<a href="'+data.list[0].url+'" target="_blank" class="fjspancont" title="'+data.list[0].name+'">'+data.list[0].name
					+'</a><a class="removeFjButton" href="javascript:void(0)" onclick="removeFj(\''+data.list[0].attId+'\')">删除</a></span>';
				}
				
				if(!!idt){
					$("#"+postName).append($(temp));
				}else{
					$("#"+id).parent().before($(temp));
				}
				resizeFrameHeight();
			}else{
				parent.alertMsg(data.msg,"error");
			}
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

function saveCxnr(obj){
	onloading();
	if(validateForm("ajxx_form")){
		removeload();
		return;
	}
	var param = $('#ajxx_form').serialize()
				+"&newaj="+newaj
				+"&pzApply.pzApplyId="+pzid;
	if(!!ajid){
		param += "&caseInfo.caseId="+ajid;
	}
	$.post(ctxPath+"/pztb/saveTemp",param,function(data){
		removeload();
		parent.alertMsg(data.msg,"info");
	});
}

function deleteCxnr(obj){
	if($("#cxnr_panel .panelTable").length > 1){
		parent.confirmMsgFunc('删除查询内容', '您是否确认删除本条查询内容?',function (r){
			if(r){
				$(obj).closest("table").parent().fadeTo("50", 0.01, function() {// fade
					$(obj).closest("table").parent().remove();// then remove from the DOM
					resizeFrameHeight();
				});
				//$(obj).closest("table").parent().remove();
				
			}
		});
		
	}else{
		parent.alertMsg("查询内容至少保留一个！","warning");
	}
}
function printPage(){
	
}
function startsp(){
	if(validateForm("ajxx_form")){
		return;
	}
	opendialog('cxrcndialog');
}
function resizeFrameHeight(){
	//$(window.parent.document).find("#contentFrame").css("height",($(document.body).height()+50)+"px");
}

