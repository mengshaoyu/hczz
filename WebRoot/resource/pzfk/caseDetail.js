$(function() {
	initData();
	initCss();
	initChangeDate();
});

//受案时间、案发时间字段截取
function initChangeDate(){
	var incidentDate = $("#incidentDate").text();
	var acceptDate = $("#acceptDate").text();
	$("#acceptDate").html(acceptDate.substring(0,acceptDate.length-2));
	$("#incidentDate").html(incidentDate.substring(0,acceptDate.length-2));
}

function initCss(){
	$(".ajxx td:nth-child(2n+1),.cxnr td:nth-child(2n+1),.task td:nth-child(2n+1)").css("width","5%").attr("align","left");
	$(".ajxx td:nth-child(2n),.cxnr td:nth-child(2n),.task td:nth-child(2n)").css("width","22%").css("padding-left","10px");
}

//tab页选中表单提交刷新当前页面
function reload(pzid){
	var url =  ctxPath + "/caseDetail/init?pzApplyId="+pzid;
	$("#pzid").val(pzid);
	initData();
}

//查询数据
function initData() {
	var pzid = $("#pzid").val();
	//table选中事件
	$("li").each(function(){
		var liId = this.id;
		var obj = document.getElementById(""+liId);
		obj.style.backgroundImage="url()";
		if(pzid == liId){
			obj.style.backgroundImage="url(../resource/image/apply/selected.png)";
		}
	})
	if(pzid != ""){
		var url =  ctxPath + "/caseDetail/detail";
		$.post(url, {
			pzApplyId : $("#pzid").val()
		}, function(data) {
			if(data.err == 'err'){
				parent.showMessageCenter(data.errMsg);
			}
			$("#fkContent").html("");
			$.each(data.clues, function(i, item) {
				//案件等级
				$("#level").html("任务级别:"+"&nbsp;&nbsp;"+item.flag);
				//拼装线索信息
				var $clue_div=$('<div class="xiansuo-container" id="clueId_'+item.clueId+'"></div>');
				$("#fkContent").append($clue_div);
				var clue_msg='<h3>查询内容'+(i+1)+'</h3>'+
				'<div class="xiansuo-box">'+
				'<table cellpadding="0" cellspacing="0" class="xiansuo-table" ><tr>'+
				'<td class="left"><span class="requ">*</span>查询要求：</td>'+
				'<td class="right">'+item.serviceRequest+'</td>'+
				'<td class="left"><span class="requ">*</span>线索来源：</td>'+ 
				'<td class="right">'+item.clueSource+'</td></tr>'+ 
				'<tr><td class="left"><span class="requ">*</span>线索内容：</td>'+
				'<td class="right">'+item.clueName+'</td>'+	
				'<td class="left"><span class="requ">*</span>来源证明：</td>'+
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
				'<tr><td class="left"><span class="requ1">*</span>详情描述：</td>'+
				'<td class="right" colspan="3">'+item.clueDesc+'</td>'+
				'</tr></table>'+
				'</div>';
				var deal_div='<div class="shuming"></div>';
				var pzResultInfo = "<div style='height:10px'></div><div id='result_" + item.clueId + "'></div>";
				$clue_div.append(clue_msg+deal_div+pzResultInfo);
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
					},{
						field : 'resultRemark',
						title : '详细说明',
						width : 250,
						align:'left'
					},{
						field : 'resultDate',
						title : '登记时间',
						width : 100,
						align:'center'
					}] ],
					nowrap:false,
					data:resultDatas
				});
				$clue_div.append('<div class="clear"></div>');
			});
		});
	}
}

//打开地图选点dialog
function openMap(){
	var mapX = $.trim($("#mapX").html());
	var mapY = $.trim($("#mapY").html());
	$("#savemappoint").hide();
	$('#mapmarkframe')[0].src = ctxPath + "/hbmap/init?x="+mapX+"&y="+mapY+"&editable=0";
	opendialog('mapmark');
	$('#mapmark').closest('div.panel').css('position', 'fixed');
	//判断位置
	var height = $(window).height();
	var width = $(window).width();
	var a = $("#mapmark").height();
	var b = $("#mapmark").width();
	var _posiTop = (height - a)/2;
	var _posiLeft = (width - b)/2; 
	$("#mapmark").css("top",_posiTop);
	$("#mapmark").css("left",_posiLeft);
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