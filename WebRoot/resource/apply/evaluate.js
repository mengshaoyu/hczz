
// 使用效能：选中/取消
function selectFeedUseful(id) {

	$("img[name='feedBackValue']").each(
			function(index) {

				if (index <= id) {
					$(this).attr('src',
							'../resource/image/apply/star-2.png');
				} else {
					$(this).attr('src',
							'../resource/image/apply/star-1.png');
				}

			})
}

// 反馈效能：选中/取消
function selectUseful(id) {
	$("img[name='useValue']").each(
		function(index) {
	
			if (index <= id) {
				$(this).attr('src',
						'../resource/image/apply/star-2.png');
			} else {
				$(this).attr('src',
						'../resource/image/apply/star-1.png');
			}
	
		})
}

//评价核实 新
$(function(){
	$(".img").click(function(){
		$(".img").attr("src","../resource/image/apply/redio-1.png").attr("name","useValue1");
		if(this.name == 'useValue1'){
			$(this).attr("src","../resource/image/apply/redio-2.png").attr("name","useValue2");
		}
	})
})

// 保存评价及落地反馈情况
function saveEvaluate() {
	//核实情况
	var backDesc = $('#backDesc').val();
	if (null == backDesc || ''==backDesc) {
		showMessage("请填写核实情况");
		return false;
	}
	if(backDesc.length>150){
		showMessage("核实情况超出字符限制");
		return false;
	}
	//反馈时效	
	var backTime = $("#feedBackTime").val();
	//合成效能
	var potency = $("*[name='useValue2']").attr("id");
	if(potency == null ){
		showMessage("请选择和成效能满意度");
		return false;
	}
	$.post('saveEvaluate', {
		'pzApplyId':pzApplyId,
		'landing' : backDesc,
		'usability' : potency,	//使用效能(旧)   封装合成效能，用户满意度redio,（0:非常不满意、1：不满意、2：一般、3：满意、4：非常满意）
		'feedbackAging' : backTime
	}, function(data) {
		parent.showMessage(data.msgInfo);
		closeWin("1");
	})
	
	/*var backDesc = $('#backDesc').val();
	if (null == backDesc || ''==backDesc) {
		showMessage("请填写落地反馈情况");
		return false;
	}
	var backTime = $("#feedBackTime").val();
	var feedUseful = 0;
	$("img[name='feedBackValue']").each(function(index) {

		var src = $(this).attr('src');
		if ('../resource/image/apply/star-2.png' == src) {
			feedUseful++;
		}
	});
	
	var useful = 0;
	$("img[name='useValue']").each(function(index) {

		var src = $(this).attr('src');
		if ('../resource/image/apply/star-2.png' == src) {
			useful++;
		}
	});

	if(feedUseful==0||useful==0){
		showMessage("请填写评价");
		return false;
	}
	
	if(null==backDesc&&""==backDesc){
		showMessage("请填写核实情况");
		return false;
	}

	if(backDesc.length>150){
		return false;
	}
	
	$.post('saveEvaluate', {
		'pzApplyId':pzApplyId,
		'landing' : backDesc,
		'feedbackUsed' : feedUseful,
		'usability' : useful,
		'feedbackAging' : $('#feedBackTime').val()
	}, function(data) {
//		parent.showMessage(data.msgInfo);
		parent.showMessage(data.msgInfo);
		closeWin("1");
	})*/
}

//关闭窗口
function closeWin(flag){
	parent.colseEvaluateWin(flag);
}

