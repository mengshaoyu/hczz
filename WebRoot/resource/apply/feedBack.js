
$(function(){
	//
	var array=$('#roleList').val();
	var flowId=$('#flowId').val();
	if(null==array||null==flowId){
		return false;
	}
	if((array.indexOf(evaluate_role)>0)&&(flowId==evaluate_state)){//
		$('#backRemark').css('display','block');
	}

});

/** ***************落地情况反馈及评价****************** */

//评价并反馈落地情况
function openEvaWin() {
	parent.parent.openEvaWin();
}

/** ***************落地情况反馈及评价****************** */



//打印预览
function printReview(){
	showMessage("功能待建中，敬请期待！");
}

//清空DIV信息
function clearDiv(){
	var backDesc = $('#backDesc').val('');
	selectFeedUseful(-1);
	selectUseful(-1);
}

//反馈表导出
function exportFeedBack(){
//	showMessage("功能待建中，敬请期待！");
	var url = ctxPath + "/pztask/exportWord";
	var param = "pzid="+pzApplyId;
	window.open(url+"?"+param);
}
