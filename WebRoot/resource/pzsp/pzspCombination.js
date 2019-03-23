$(function(){
	initData();
	initApproveUser();
	var isGoneSt="";
	var isGoBackSt="";
	$('#setComWin').dialog('close');
	$('#pzspIframe').css('height',$(window).height()-130);
});

/*function changehasSpxx(){
	var hasspxx = $('input:checkbox[name="hasSpxx"]').is(':checked');
	if(hasspxx){
		$(".spinfo").show();
	}else{
		$(".spinfo").hide();
	}
}*/

//IE8 下不支持indexOf方法,自行封装兼容IE8
if(!Array.prototype.indexOf){    
   Array.prototype.indexOf = function(val){    
       var value = this;    
       for(var i =0; i < value.length; i++){    
          if(value[i] == val) return i;    
       }    
      return -1;    
   };    
}  
//初始化
function initData(){
	var url = ctxPath + "/commLang/loadLangs";
	$.post(url, {}, function(data){
		if(data.code == 0){
			$.each(data.data, function(i, item){
				if(item.langType=='1'){
					
					$("#isGone").text(item.commonLanguage);
					$("#isGone").val(item.commonLanguage);//兼容IE
					isGoneSt=item.commonLanguage;
				}else if(item.langType=='2'){
					
					$("#goBack").text(item.commonLanguage);
					$("#goBack").val(item.commonLanguage);//兼容IE
					
					isGoBackSt=item.commonLanguage;
				}
			});
			$('#spInfo').text(isGoneSt);
		}
	});
}
//常用语弹出
function commonClick(){
	
	$("#isGone").val(isGoneSt);//兼容IE
	$("#goBack").val(isGoBackSt);//兼容IE
//	$('#roleId').val("");
//	$('#vName').val("");
//	$('#vDescrip').val("");
	
	$("#setComWin").dialog({ 
		top : 150,
		closed:false,
		closable: false,
		draggable:false,
        title:'常用语编辑',
        modal : true,//遮罩
        height:225,
        width:430
    });
}
//设置审批意见显示
function setSPInfo(agree){
	if(agree=='1'){
		$('#spInfo').text(isGoneSt);
		$('#spInfo').val(isGoneSt);
	}else if(agree=='2'){
		$('#spInfo').text(isGoBackSt);
		$('#spInfo').val(isGoBackSt);
	}
}
//重置审批意见显示
function setReSetSPInfo(){
		$('#spInfo').val(isGoneSt);
		$('#spjl').attr("checked",true);
		$('#fgspjl').attr("checked",false);
}
function saveNext(){
	if(validate()){
		confirmMsgFunc('审批', '您是否确认审批该任务?',function (r){
			if(r){
				var spjl=$('input:radio[name="spjl"]:checked').val();
				if(actId == "1" && spjl == "1"){
					opendialog('spdialog');
				}else{
					saveSpInfoNext();
				}
			}
		});
	}
}
function saveSpInfoNext(){
	var spjl=$('input:radio[name="spjl"]:checked').val();
	$("#spdialog-ok").attr("disabled","disabled");
	var appUsers = "";
	$.each($("input[name='approveUser']"),function(i, item){
		if($(item).prop('checked')){
			isChecked = true;
			appUsers += "spr="+$(item).val()+"&";
		}
	});
	if(actId == "1" && spjl == "1" && appUsers == ""){
		parent.alertMsg("请选择审批人！","warning");
		$("#spdialog-ok").removeAttr("disabled");  
		return;
	}
	var spyj=$('#spInfo').val();
	var param = appUsers+'spjl='+spjl+'&spyj='+spyj+'&pzid='+pzid;
	var url = ctxPath + "/pzsp/saveNext";
	$.post(url,param,function(data){
		if(data.code=='0'){
			$("#spdialog-ok").removeAttr("disabled");
			if(actId == "2" && spjl == "1"){
				opendialog('goffdialog');
			}else{
				parent.alertMsgFunc(data.msg,"info",function(){
					window.close();
				});
			}
		}else{
			alertMsg(data.msg,'error');
			$("#spdialog-ok").removeAttr("disabled");
		}
	});
}

function goff(){
	$("body").append("<form id='refreshView' action='"+ctxPath+"/pzsp/initdis'><input type='hidden' name='pzid' value='"+pzid+"'></form>");
	$("#refreshView").submit();
}

function validate(){

	var spyj = $('textarea[id="spInfo"]').val();
	if(!!spyj==false){
		parent.alertMsg("请填写审批意见！");
		return false;
	}
	if(spyj.length > 100){
		parent.alertMsg("审批意见不能超过100个字符！");
		return false;
	}
	return true;
}


function checkCont(str){
	if(str.indexOf("<TEXTAREA") > -1){
		return "";
	}
	return str;
}
function addAssign() {
	parent.addAssign(pzid);
}


function initApproveUser(){
	var url = ctxPath + "/pztb/getApproveUser";
	var fgldUsers = "";
	$.post(url,{flag:"2"},function(data){
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
			$("#zbz").html(tr);
		}
	});
	
}

//常用语恢复默认设置
function reSetSPInfo(){
	confirmMsgFunc('常用语', '您是否确认恢复默认设置常用语?',function (r){
		if(r){
			var url = ctxPath + "/commLang/deleteLangs";
			$.post(url,{},function(data){
				if(data.code=='0'){
					// alertMsg('恢复默认常用语设置完成','info');
					$.each(data.data, function(i, item){
						if(item.langType=='1'){
							
							$("#isGone").text(item.commonLanguage);
							$("#isGone").val(item.commonLanguage);
							isGoneSt=item.commonLanguage;
						}else if(item.langType=='2'){
							
							$("#goBack").text(item.commonLanguage);
							$("#goBack").val(item.commonLanguage);
							
							isGoBackSt=item.commonLanguage;
						}
					});
					//更新审批意见
					var spjl=$('input:radio[name="spjl"]:checked').val();
					setSPInfo(spjl);
					$('#setComWin').dialog('close');
				}else{
					alertMsg(data.msg,'error');
				}
			});
		}
	});
}

//常用语保存
function saveSPInfo(){
	if(!validateCom())
		return;
	var url = ctxPath + "/commLang/createOrUpdateLangs";
	var isGone=$('#isGone').val();
	var goBack=$('#goBack').val();
	var addCommon={'pcls':[{
		'commonLanguage':isGone,
		'langType':'1'},{
	'commonLanguage':goBack,
	'langType':'2'}]};
	//var jsonAdd=JSON.stringify(addCommon);
	var jsonAdd='pcls[0].commonLanguage='+isGone+'&pcls[0].langType=1&pcls[1].commonLanguage='+goBack+'&pcls[1].langType=2'
	$.post(url,jsonAdd,function(data){
		if(data.code=='0'){
			 alertMsg('常用语设置成功','info');
			 isGoneSt=isGone;
			 isGoBackSt=goBack;
			 //更新审批意见
				var spjl=$('input:radio[name="spjl"]:checked').val();
				setSPInfo(spjl);
				$('#setComWin').dialog('close');
		}else{
			 alertMsg(data.msg,'error');
		}
		 var spjl=$('input:radio[name="spjl"]:checked').val();
			setSPInfo(spjl);
	});
}

function validateCom(){

	var spyj = $('textarea[id="isGone"]').val();
	if(!!spyj==false){
		parent.alertMsg("请填写通过常用语！");
		return false;
	}
	if(spyj.length > 100){
		parent.alertMsg("通过常用语不能超过100个字符！");
		return false;
	}
	spyj = $('textarea[id="goBack"]').val();
	if(!!spyj==false){
		parent.alertMsg("请填写驳回常用语！");
		return false;
	}
	if(spyj.length > 100){
		parent.alertMsg("驳回常用语不能超过100个字符！");
		return false;
	}
	return true;
}

//常用语清空
function cleanSPInfo(agree){
	if(agree=='1'){
		$('#isGone').text('');
		$('#isGone').val('');//兼容IE
	}else if(agree=='2'){
		$('#goBack').text('');
		$('#goBack').val('');//兼容IE
	}
}
//常用语取消
function closeCommon(){
	var isGone=$('#isGone').val();
	var goBack=$('#goBack').val();
	if(isGone==isGoneSt&&goBack==isGoBackSt){
		$('#setComWin').dialog('close');
		return;
	}
	confirmMsgFunc('常用语', '您尚未保存是否需要保存?',function (r){
		if(r){
			saveSPInfo();
		}else{
			$('#setComWin').dialog('close');
		}
	});
}
//监听离开页面，隐藏父页面
$(window).unload(function(){
	//若打开审批界面的是任务管理界面，则调查询列表的方法
	if(undefined!=typeof(window.opener)&&'undefined'!=typeof(window.opener)&&
			undefined!=typeof(window.opener.query)&&'undefined'!=typeof(window.opener.query)){
		window.opener.query();
	}
	//若打开审批界面的是详情界面，则调显示审批按钮的方法，重新加载按钮
	if(undefined!=typeof(window.opener)&&'undefined'!=typeof(window.opener)&&
			undefined!=typeof(window.opener.showButton)&&'undefined'!=typeof(window.opener.showButton)){
		window.opener.showButton(pzid);
	}
});
function closedialog(id){
	$("body").css("overflow","auto");
	$('#'+id).dialog('close');
}
function opendialog(id){
	$("body").css("overflow","hidden");
	$('#'+id).dialog('open');
}