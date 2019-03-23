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
	cxnrstr = $("#cxnr_panel .panelContent").prop("outerHTML");
	});
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
		$(".combo-text").css('color','#545454');
		
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
			$("#caseTypeImp").combobox('readonly');
		}
		if(!!$("#caseStatus").combobox('getValue')){
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
	}
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
					$("#caseTypeImp").combobox('readonly');
					$("#caseTypeImp").next().find('input').css("color","#545454");
				}
				if(!!$("#caseStatus").combobox('getValue')){
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
	$(".ajxx td:nth-child(2n+1),.cxnr td:nth-child(2n+1),.task td:nth-child(2n+1)").css("width","8%").attr("align","left");
	$(".ajxx td:nth-child(2n),.cxnr td:nth-child(2n),.task td:nth-child(2n)").css("width","22%").css("padding-left","10px");
	//$(".cxnr td:nth-child(1),.cxnr td:nth-child(3),.task td:nth-child(1),.task td:nth-child(3)").css("width","6%").attr("align","right");
	//$(".cxnr td:nth-child(2),.cxnr td:nth-child(4),.task td:nth-child(2),.task td:nth-child(4)").css("width","22%").css("padding-left","10px");
	//$(".cxnr td:nth-child(5),.task td:nth-child(5)").css("width","12%").attr("align","center");
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
			$(item).css("border","1px solid #ddd");
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
	if(flag){
		location.href = "#first-div";
		// parent.alertOverRide(errmsg,"none","loading-mask");
	}
	return flag;
}
function clickFileUper(obj){
	
}

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
//打开地图选点dialog
function openMap(){
	var mapX = $.trim($("#mapX").html());
	var mapY = $.trim($("#mapY").html());
	$("#savemappoint").hide();
	$('#mapmarkframe')[0].src = ctxPath + "/hbmap/init?x="+mapX+"&y="+mapY+"&editable=0";
	opendialog('mapmark');
}
//关闭地图选点dialog
function closedialog(id){
	$("body").css("overflow","auto");
	$('#'+id).dialog('close');
}
function opendialog(id){
	location.href = "#first-div";
	$("body").css("overflow","hidden");
	$('#'+id).dialog('open');
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
