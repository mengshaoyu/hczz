
$(document).ready(function(){
	reportDivResize();
	$(window).resize(function() {
		reportDivResize();
	});
	
	var flag=show=='1'?true:false;
	
	var zTreeObj,
	setting = {
		view: {
			fontCss : {color:"#FFF"},
			selectedMulti: false
		},
		callback: {
			onClick: openReport
		}
	},
	zTreeNodes = [
		{"name":caseNo, open:true,  
			icon:ctxPath+"/resource/image/apply/floder1.png",
			children: [
			  { "name":"合成作战申请表", "flag":0,icon:ctxPath+"/resource/image/apply/file1.png"},
			  { "name":"合成作战反馈表", "flag":1,isHidden :!flag,icon:ctxPath+"/resource/image/apply/file1.png"}
			]
		}
	];
	zTreeObj = $.fn.zTree.init($("#tree"), setting, zTreeNodes);
	$('ul ul li').eq(show).find('a').click();
});

/**
 * 打开指定报表
 * @param flag 0，申请表；1，反馈表
 */
function openReport(event, treeId, treeNode){
	var flag=treeNode.flag;
	
	var toUrl="";
	if('0'==flag){
		toUrl=ctxPath + '/pzsp/init?pzid=' + pzApplyId;
	}else if('1'==flag){
		toUrl=ctxPath + '/pztask/initFeedback?pzid='+pzApplyId;
	}
	if(""!=toUrl){
		document.getElementById('iframe_report').src=toUrl;
	}
	
}

function reportDivResize(){
	//计算DIV高度
	var bodyHeight=$(window).height();
	var bodywidth=$(window).width();
	$("#treeDiv").css("height",bodyHeight);
	$("#reportDiv").css("height",bodyHeight);
	$("#iframe_report").css("height",bodyHeight);
	$("#reportDiv").css("width",bodywidth-260);
}