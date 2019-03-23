/*
 * @Author: K.
 * @Date:   2017-11-28 11:12:39
 * @Last Modified by:   Administrator
 * @Last Modified time: 2017-12-04 11:09:25
 */



//对象：AJAX请求对象
var ajaxData = {
	currPage: 1,
	currPagePlus: 1,
	rows: 10,
	canLoad: true
};

//对象：本页的操作对象
var actionObj = {
	compuUser: function() {
		if (userRole.indexOf('1007') != -1) {
			$('.opinion-state-icon').css('display', 'inline-block');
		}

	},
	compuHeight: function() {
		var hh = $(window).height() - 360;
		$('.ops-response-list').css({
			'min-height': '100px',
			'max-height': '1000px',
			'height': hh + 'px',
			'overflow-x': 'hidden',
			'overflow-y': 'scroll'
		});
	}
};

ajaxData.clear = function() {
	$('.ops-response-list>div').empty();
	this.currPagePlus = 1;
}

ajaxData.add = function() {
	var content = $('#ops-content-textarea').val();
	var id = userId;
	if (content.length == 0) {
		showMessageFixed('意见内容不能为空!');
		return false;
	} else if (content.length > 500) {
		showMessageFixed('请输入小于500字的意见内容!');
		return false;
	}

	$.ajax({
		url: ctxPath + '/ops/addOps',
		type: 'post',
		data: {
			sopOpsContent: content,
			createBy: userId
		},
		success: function(data) {
			console.log(data);
			if (hb.VO.check(data)) {
				showMessageFixed('意见反馈成功!');
				$('#ops-content-textarea').val('');
				ajaxData.clear();
				ajaxData.loadlist(ajaxData.currPage, ajaxData.rows);
			}
		},
		error: function(data) {
			console.log(data);
		}
	});
};

ajaxData.update = function(id, content) {
	if (content.length == 0) {
		showMessageFixed('意见内容不能为空!');
		return false;
	} else if (content.length > 500) {
		showMessageFixed('请输入小于500字的意见内容!');
		return false;
	}

	$.ajax({
		url: ctxPath + '/ops/updateOps',
		type: "POST",
		data: {
			sopOpsId: id,
			updateBy: userId,
			sopOpsResponse: content
		},
		success: function(data) {
			console.log(data);
			if (hb.VO.check(data)) {
				showMessageFixed('意见回复成功!');
				$('#ops-content-textarea').val('');
				ajaxData.clear();
				ajaxData.loadlist(ajaxData.currPage, ajaxData.rows);
			}
		},
		error: function(data) {
			console.log(data);

		}
	});
};

ajaxData.loadlist = function(currPagePlus, size) {
	this.canLoad = false;
	$.ajax({
		url: ctxPath + '/ops/getOpsList',
		type: 'get',
		data: {
			"page": currPagePlus,
			"rows": size,
			"sorter": 't.update_dt',
			"order": "DESC"
		},
		success: function(data) {
			// console.log(data);
			ajaxData.currPagePlus++;
			data.body.rows.length && $.each(data.body.rows, function(index, v) {
				var rel = '<div class="ops-response-border">\
					<div class="ops-response-content">\
					  <div class="response-info">\
					    <textarea placeholder="内容小于500字" name="" id="' + v.sopOpsId + '" ></textarea>\
					  </div>\
					  <div class="response-buttons">\
					    <p>\
					      <button class="response-send">发送</button>\
					      <button class="canel">收起</button>\
					    </p>\
					  </div>\
					</div>\
				  </div></div>';
				v.sopOpsState == 1 && (rel = '<div class="ops-responsed-border">\
				    <div class="ops-responsed-content">\
				      <div class="responsed-info">\
				        <p id="" >' + v.sopOpsResponse + '</p>\
				      </div>\
				    </div>\
				  </div></div>');
				$('.ops-response-list>div').append(' \
				<div class="ops-response">\
				  <div class="ops-opinion">\
				    <div class="opinion-content">\
				      <p class="opinion-user">\
				        <span>' + v.userName + '(' + v.userNo + ')</span><span>' + v.deptName + '</span>\
				      </p>\
				      <p class="opinion-info">' + v.sopOpsContent + '</p>\
				    </div>\
				    <div class=' + (v.sopOpsState == 1 ? "opinion-state-2" : "opinion-state-1") + '>\
				      <div class="opinion-state-content">\
				        <p class="opinion-state-info">' + (v.sopOpsState == 1 ? "已回复" : "等待回复中...") + '</p>\
				        <p class=' + (v.sopOpsState == 1 ? "opinion-update" : "opinion-create") + '>' + (v.sopOpsState == 1 ? v.updateDt : v.createDt) + '</p>\
				      </div>\
				      <div class=' + (v.sopOpsState == 1 ? "" : "opinion-state-icon") + '>\
				      </div>\
				    </div>\
				  </div>' + rel);
				ajaxData.canLoad = true;
				actionObj.compuUser();
			});
		},
		error: function(data) {
			// console.log(data);
		}
	});
}

//执行：初始化操作
$(function() {
	actionObj.compuHeight();
	ajaxData.loadlist(ajaxData.currPage, ajaxData.rows);
});

//执行：页面重置时加载

$(window).resize(function() {
	actionObj.compuHeight();
	ajaxData.loadlist(ajaxData.currPage, ajaxData.rows);
});



//执行：绑定事件
$(function() {
	$('#ops-content-sub').on('click', function() {
		// event.preventDefault();
		ajaxData.add();
	});

	$('.ops-response-list').on('click', '.response-send', function(event) {
		// event.preventDefault();
		var $temp = $(this).closest('.ops-response-border').find('textarea');
		var content = $temp.val();
		var id = $temp.attr('id');
		ajaxData.update(id, content);
	});

	$('.ops-response-list').on('click', '.canel', function() {
		$(this).closest('.ops-response-border').slideUp(300).find('textarea').val('');
	});

	$('.ops-response-list').on('click', '.opinion-state-icon', function() {
		// $('.ops-response-border').slideUp(300);
		$(this).closest('.ops-opinion').next('.ops-response-border').slideToggle(300);
	});
	$('.ops-response-list').scroll(function() {
		$(this).scrollTop() + $(this).height() + 100 >= $('.ops-response-list>div').height() && ajaxData.canLoad &&
			ajaxData.loadlist(ajaxData.currPagePlus, ajaxData.rows);
	});

});
