$(function(){
	initData();
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

function initData(){
	var url = ctxPath + "/pzsp/loadHctb";
	$.post(url, {pzid : pzid}, function(data){
		if(data.code == 0){
			$("#acceptUnit").text(data.pzInfo.deptName);
			if(actId == "2"){
				$("#deptSign").attr("src","http://"+serverName+":"+serverPort+"/attachServer/signature/dept/"+encodeURI(data.pzInfo.deptName)+".gif");
			}
			$("#pzApplyNo").text(data.pzInfo.pzApplyNo);
			$("#sqTime").text(new Date(data.assigneeCompleteInfo.endTime.replace(/-/g,"/")).format("yyyy年MM月dd日"));
			$("#caseNo").text(data.aj.caseNo);
			$("#caseName").text(data.aj.caseName);
			$("#caseType").text(!!data.aj.caseTypeValue?data.aj.caseTypeValue:'无');
			$("#acceptDate").text(data.aj.acceptDate);
			$("#incidentDate").text(!!data.aj.incidentDate?new Date(data.aj.incidentDate.replace(/-/g,"/")).format("yyyy年MM月dd日"):'无');
			$("#caseDesc").text(data.aj.caseDesc);
			$("#sprsqr").attr("src","http://"+serverName+":"+serverPort+"/attachServer/signature/user/"+data.assigneeCompleteInfo.userNo+".gif");
			if(actId == "2"){
				$("#sprsqrtext").attr("src","http://"+serverName+":"+serverPort+"/attachServer/signature/user/"+data.fgldsp.USER_NO+".gif");
			}
			$("#sqsj").text(data.sqsj);
			var pzType = "";
			var clueName = "";
			var clueAtt = "";
			$.each(data.clue, function(i, item){
				if(pzType.indexOf(item.pzTypeName) == -1){
					pzType += item.pzTypeName + "&nbsp;";
				}
				clueName += (i+1) + "、"+item.clueName+"，"+item.pzTypeDetailName+"，"+item.clueSource+"<br>";
				if(item.att.length > 0){
					clueAtt += (i+1) + "、";
					$.each(item.att, function(j, jtem){
						clueAtt += jtem.attName+ "&nbsp;";
					});
					clueAtt += "<br>";
				}else{
					clueAtt += (i+1) + "、无<br>";
				}
			});
			$("#pzType").html(pzType);
			$("#pzApplyGrade").text(data.pzInfo.pzApplyGradeName);
			$("#clueName").html(clueName);
			$("#clueAtt").html(clueAtt);
			var flws = "受案登记表：";
			if(data.sadjb.length > 0){
				$.each(data.sadjb, function(i, item){
					flws += item.attName+ "&nbsp;";
				});
				flws += "<br>";
			}else{
				flws += "无<br>";
			}
			flws += "立案决定书：";
			if(data.lajds.length > 0){
				$.each(data.lajds, function(i, item){
					flws += item.attName+ "&nbsp;";
				});
				flws += "<br>";
			}else{
				flws += "无<br>";
			}
			flws += "呈请立案报告书：";
			if(data.cqlabgs.length > 0){
				$.each(data.cqlabgs, function(i, item){
					flws += item.attName+ "&nbsp;";
				});
				flws += "<br>";
			}else{
				flws += "无<br>";
			}
			
			$("#flws").html(!!flws?flws:'无');
			$("#username").text(data.assigneeCompleteInfo.username);
			$("#mobilePhone").text(data.assigneeCompleteInfo.mobilePhone);
			
			if(data.spBtnAuth){
				if(data.currFlow == "HCZZ_1002" || data.currFlow == "HCZZ_1004"){
					$("#saveNext").show();
				}
				if(data.currFlow == "HCZZ_1005"){
					$("#addAssign").show();
				}
			}
			if(!!data.fgldsp){
				$("#spInfo").text(data.fgldsp.COMM);
				$("#spsj").text(new Date(data.fgldsp.COMMTIME.replace(/-/g,"/")).format("yyyy年MM月dd日"));
				$("#fgldspSign").attr("src","http://"+serverName+":"+serverPort+"/attachServer/signature/user/"+data.fgldsp.USER_NO+".gif");
			}
			if(!!data.zbzsp){
				$("#fgspInfo").text(data.zbzsp.COMM);
				$("#fgspsj").text(new Date(data.zbzsp.COMMTIME.replace(/-/g,"/")).format("yyyy年MM月dd日"));
				$("#zbzspSign").attr("src","http://"+serverName+":"+serverPort+"/attachServer/signature/user/"+data.zbzsp.USER_NO+".gif");
			}
			if(spjl=='1'){
				WebBrowser.ExecWB(7,1) //打印预览 
				//alert(window.location.href);
				setTimeout(function(){
					window.close();
				},2000);
			}
			$("img:not([src])").remove();
		}
	});
}
function spjlclick(){
	
}


function exportWord(){
	/*var url = ctxPath + "/pzsp/exportWord";
	var param = "";
	param += "fgldsp=" + checkCont($("#spInfo").html()) + "&";
	param += "zbzsp=" + checkCont($("#fgspInfo").html()) + "&";
	param += "sqsj="+ $("#sqsj").html() + "&";
	param += "fgldspsj=" + $("#spsj").text() + "&";
	param += "zbzspsj=" + $("#fgspsj").text() + "&";
	param += "sprsqr="+$("#sprsqr").attr("src")+"&";
	param += "sprsqrtext="+$("#sprsqrtext").attr("src")+"&";
	param += "deptSign=" + $("#deptSign").attr("src") + "&";
	param += "fgldspSign=" + $("#fgldspSign").attr("src") + "&";
	param += "zbzspSign=" + $("#zbzspSign").attr("src") + "&";
	
	$.each($(".tbl-value"),function(i, item){
		param += $(item).attr("id")+"="+$(item).html().replace(/&nbsp;/g, ' ')+"&";
	});
	window.open(url+"?"+param);*/
	var downform = $("<form id='refreshView' method='post' action='"+ctxPath+"/pzsp/exportWord' style='display:none'></form>");
	  $("body").append(downform);
	  downform.append("<input style='display:none;' name='fgldsp' value='"+checkCont($("#spInfo").html())+"'>");
	  downform.append("<input style='display:none;' name='zbzsp' value='"+checkCont($("#fgspInfo").html())+"'>");
	  downform.append("<input style='display:none;' name='sqsj' value='"+$("#sqsj").html()+"'>");
	  downform.append("<input style='display:none;' name='fgldspsj' value='"+$("#spsj").text()+"'>");
	  downform.append("<input style='display:none;' name='zbzspsj' value='"+$("#fgspsj").text()+"'>");
	  downform.append("<input style='display:none;' name='sprsqr' value='"+$("#sprsqr").attr("src")+"'>");
	  downform.append("<input style='display:none;' name='sprsqrtext' value='"+$("#sprsqrtext").attr("src")+"'>");
	  downform.append("<input style='display:none;' name='deptSign' value='"+$("#deptSign").attr("src")+"'>");
	  downform.append("<input style='display:none;' name='fgldspSign' value='"+$("#fgldspSign").attr("src")+"'>");
	  downform.append("<input style='display:none;' name='zbzspSign' value='"+$("#zbzspSign").attr("src")+"'>");
	  $.each($(".tbl-value"),function(i, item){
	    downform.append("<input style='display:none;' name='"+$(item).attr("id")+"' value='"+$(item).html().replace(/&nbsp;/g, ' ')+"'>");
	  });
	  $("#refreshView").submit();
	  $("#refreshView").remove();
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

//打印预览
function printReview(){
	var w = null;
//	$("div#myPrintArea").printArea(); 
	this.openW = function(){
		w = window.open('init?pzid='+pzid+'&spjl=1');
	};

	this.clo = function(){
		w.close();
	};
//	$("#spform").printArea(); 
//	if (oper < 10) 
//	{ 
//	bdhtml=document.pzspIframe.innerHTML;//获取当前页的html代码 
//	sprnstr="<!--startprint"+oper+"-->";//设置打印开始区域 
//	eprnstr="<!--endprint"+oper+"-->";//设置打印结束区域 
//	prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+18); //从开始代码向后取html 
//
//	prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));//从结束代码向前取html 
//	window.document.body.innerHTML=prnhtml; 
//	window.print(); 
//	window.document.body.innerHTML=bdhtml; 
//	} else { 
//	window.print(); 
//	} 
}


var testPrint = new printReview();

