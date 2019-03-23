
$(function() {
	//加载一级目录
	getMenu.getMenuList();
	setTimeout(function () {
		getMenu.getSecondMenu();
	},500);
	getUnFinsh();
	
	//2s定时调用getUnFinsh()方法，返回小铃铛统计数量
	//setInterval("getUnFinsh()",2000);
	//Frame动态宽度
	deaulftFrame();
	
	//修改密码及用户信息
	$('#hiddenDiv').on('click', 'li', function() {
		if ($(this).index() == 0) {
			showUser();
		}else if($(this).index() == 1){
			changePwd();
		}else{
			openQuestion();
		}
	});
	
	//鼠标离开用户信息弹出div，隐藏此div
	$(document).ready(function(){
		$("#hiddenUl").mouseleave(function(){
			$('#hiddenDiv').hide();
		});
	});
	
	//初始化Frame src
	$("#contentFrame").attr("src", (ctxPath + "/newIndex/indexFrame"));

	//v1.0.3  下拉二级目录
	//鼠标点击首页
	$('.first-list li:eq(0)').click(function() {
		deaulftFrame();
		$("#contentFrame").attr("src", (ctxPath + "/newIndex/indexFrame"));
		$(".first-list>li").removeClass('top-nav-active');
		$(this).addClass('top-nav-active');
	});

	//鼠标划过一级 显示二级下拉 
	$(".first-list>li").hover(function() {
		$(this).toggleClass('top-nav-active1');
		$(".first-list>li").eq($(this).index()).find('ul').show().end().siblings().find('ul').hide();
	});
	//鼠标离开一级ul 隐藏下拉
	$('.first-menu').mouseleave(function() {
		$('.second-menu-list').hide();
	});
	//鼠标离开二级li 隐藏
	$('.second-menu-list').mouseleave(function() {
		$(this).hide();
	});
	//点击二级目录 跳转对应目录
	$('.first-list').on('click', '.second-menu-list>li', function() {
		refreshFrame();
		$(".first-list>li").removeClass('top-nav-active');
		$(this).parent().parent().addClass('top-nav-active');
		var src = $(this).attr('data-href');
		$("#contentFrame").attr("src", src);
	});

	//页面初始化响铃
	getUnFinsh1();
});

function refreshFrame() {
	var height = $(window).height();
	var hei = height - 48;
	var width = $('.content').width() + 17;
	var wid = width ;
	$('#contentFrame').height(hei);
	$('#contentFrame').width(wid);
}

function deaulftFrame() {
	var height = $(window).height();
	var hei = height - 48;
	var width = $('.content').width();
	$('#contentFrame').height(hei);
	$('#contentFrame').width(width);
}

//重载时iframe重载
$(window).resize(function () {
	//重载iframe
	if ($('#contentFrame').attr('src') == ctx+'/newIndex/indexFrame') {
		deaulftFrame();
	}else{
		refreshFrame();
	}
});

//获取一级及一级下的二级目录
function GetMenu() {
	var that = this;
	var MENULIST = [];
	var SECONDMENU = {
		firstMenu: null,
		secondMenu: null
	};
	//获取目录方法
	that.getFirstMenuList = function(uuid) {
		var result = $.ajax({
			url: ctxPath + '/login/getMenuTreeNodeList?itemId=' + uuid,
			type: 'GET',
			async: false,
			success: function(data) {
				//				console.log(data);
			},
			error: function(data) {
				//console.log("未请求到数据!");
			}
		});
		return result.responseText;
	};
	//获取全部目录结构
	that.getMenuList = function() {
		var template = '';
		var tempList = JSON.parse(that.getFirstMenuList(0));
		//console.log(typeof tempList);
		var len = tempList.length;
		//遍历一级menu
		for (var i = 0; i < len; i++) {
			var uuid = tempList[i].uuid;
			var name = tempList[i].text;
			template += '<li id=' + uuid + '><span>' + name + '</span></li>';
		}
		$('.first-list').append(template);
		//把menu导入本地数组
		for (var i = 0; i < len; i++) {
			var uu = tempList[i].uuid;
			var tempResult = JSON.parse(that.getFirstMenuList(uu));
			SECONDMENU.firstMenu = tempList[i];
			SECONDMENU.secondMenu = tempResult;
			MENULIST.push(SECONDMENU);
			SECONDMENU = {
				firstMenu: null,
				secondMenu: null
			};
		}
	};
	//显示二级边栏目录
	that.showSecondMenu = function(id) {
		$('#nav-list').empty();
		var template = '';
		var len = MENULIST.length;
		for (var i = 0; i < len; i++) {
			if (MENULIST[i].firstMenu.uuid == id) {
				var l = MENULIST[i].secondMenu.length;
				var arr = MENULIST[i].secondMenu;
				for (var j = 0; j < l; j++) {
					template += '<li id=' + arr[j].uuid + ' data-href=' + ctxPath + '/' + arr[j].url + '><i></i><span>' + arr[j].text + '</span></li>';
				}
				$('#nav-list').append(template);
			}
		}
		$('#nav-list li:eq(0)').addClass('active1');
	};

	/*
	*下拉二级菜单 默认隐藏
	*author	K.
	 */
	that.getSecondMenu = function() {
		var template = '';
		var len = MENULIST.length;
		for (var i = 0; i < len; i++) {
			var tmul = "<ul class='second-menu-list'></ul>";
			var tmid = "#" + MENULIST[i].firstMenu.uuid;
			var l = MENULIST[i].secondMenu.length;
			var arr = MENULIST[i].secondMenu;
			for (var j = 0; j < l; j++) {
				template += '<li id=' + arr[j].uuid + ' data-href=' + ctxPath + '/' + arr[j].url + '><i></i><span>' + arr[j].text + '</span></li>';
			}
			var $tmul = $(tmul)
			$tmul.append(template);
			$(tmid).append($tmul);
			template = '';
			tmul = '';
		}
	};
}

var getMenu = new GetMenu();
//获取一级及一级下的二级目录  结束

/******************************退出登录***********************************/ 
function logout(){
	$.messager.confirm('提示','确认要注销登录吗？',function(result){
		if (result){
			window.location.href = 'goOut';
		}
	});
};

/******************************新开界面时，初始化消息提醒，声音提醒***********************************/ 
function getUnFinsh1(){
	$.ajax({
		url: ctxPath+'/index/unFinish?',
		type: 'GET',
		timeout : 1999,
		success:function (data) {
			if(data > 0){
				if(navigator.userAgent.indexOf("Chrome") > -1){
		    		//如果是Chrome：
		    		var audio = document.getElementById("warningaudio");
		    		audio.play();
				} else {
					//如果是IE：
					$("#warningaudio1").attr('src',"../resource/bgm/alarm.wav"); 
				}
			}
		}
	});
}


/******************************初始化消息提醒，声音提醒，定时提醒***********************************/ 
function getUnFinsh() {
	$.ajax({
		url: ctxPath+'/index/unFinish?'+new Date().getTime(),
		type: 'GET',
		timeout : 1999,
		success:function (data) {
			$('#notice_count').text(data);
			//小铃铛提示音判断
			var num = $('#hiddenUnFinsh').val();
			//收到新任务响铃
			if(num != ""){
				//播放音效
				if(data > num){
					if(navigator.userAgent.indexOf("Chrome") > -1){
			    		//如果是Chrome：
			    		var audio = document.getElementById("warningaudio");
			    		audio.play();
					} else {
						//如果是IE：
						$("#warningaudio1").attr('src',"../resource/bgm/alarm.wav"); 
		    		}
				}
			}
			$('#hiddenUnFinsh').val(data);
		},
		error:function (data) {

		}
	})
}

//用户信息弹出
function showHidden() {
	var pos=$('#lnkPwd').position();
	var left=pos.left;
	$('#hiddenDiv').css({'left' : left});
	document.getElementById('hiddenDiv').style.display = document.getElementById('hiddenDiv').style.display=="none"?"block":"none";
}

//弹出用户信息
function showUser(){
	$.ajax({
		method:"post",
        url:ctxPath +"/index/show",
        data : {},
        dataType:"json",
        success:function(data){
     			$('#loginAccount').val(data.loginAccount);
     			$('#userName').val(data.userName);
     			$('#userNo').val(data.userNo);
     			$('#gender').val(data.gender);
     			$('#idNo').val(data.idNo);
     			$('#deptName').val(data.deptName);
     			$('#age').val(data.age);
     			$('#mobilePhone').val(data.mobilePhone);
     			$('#emial').val(data.emial);
     			$('#address').val(data.address);
     			$(".panel-tool-close").hide();
	        	$('#dd').dialog("open");
            }
        });
}

function closeUser(){
	$('#dd').dialog('close');
}

//弹出密码修改
function changePwd(){
	$(".panel-tool-close").hide();
	$('#dd2').dialog("open");
}

function closePwd(){
	$('#dd2').dialog('close');
}

//用户信息保存
function save(){
	var gender = $("#gender").val();	//性别
	var idNo = $("#idNo").val();	//身份证
	var age = $("#age").val();	//年龄
	var mobilePhone = $("#mobilePhone").val();	//手机号
	var emial = $("#emial").val();	//电子邮箱

	//校验
	if(!(gender == '男' || gender == '女')){
		showMessageCenterFull("请正确填写性别");
		return false;
	}
	if(gender == '男'){
		gender = '1';
	}else{
		gender = '2';
	}
	reg = /^\d{15}|\d{}18$/;
    if(!reg.test(idNo)){
    	showMessageCenterFull("请输入正确身份证");
    	return false;
    }
    reg1 = /^[0-9]*$/
    if(!reg1.test(age)){
    	showMessageCenterFull("请输入正确年龄");
    	return false;
    }
    reg2 = /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/
    if(!reg2.test(mobilePhone)){
    	showMessageCenterFull("请输入正确手机号");
    	return false;
    }
    reg3 = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/
    	if(!reg3.test(emial)){    
    		showMessageCenterFull("请输入正确邮箱");
    		return false;
    	}		
	$.ajax({
            type: "POST",
            url:ctxPath + "/index/edit",
            data: {
            		userName:$("#userName").val(),
					age : $("#age").val(),
					mobilePhone : $("#mobilePhone").val(),
					emial : $("#emial").val(),
					address : $("#address").val(),
					idNo : $("#idNo").val(),
					gender : gender
            	  },
         	dataType: "text",
            success: function(data) {
                if(data == "success"){
                	showMessageCenterFull("修改成功!");
                	$('#dd').dialog('close');
                	return;
                }
                showMessageCenterFull("修改失败!");
            }
        });
}

//密码保存
function editPwd(){
		var loginPwd = $("#loginPwd").val();	//新密码
		var loginPwd1 = $("#loginPwd1").val();	//确认密码
		//新密码与确认密码是否一致
		if(loginPwd != loginPwd1){
			showMessageCenterFull("修改失败，确认密码与新密码不同!");
			return false;
		}
		if(loginPwd == ''){
			showMessageCenterFull("修改失败，新密码不能为空!");
			return false;
		}
		$.ajax({
            type: "POST",
            url:ctxPath + "/index/edit?flag="+1,
            data: {
            		loginPwd:$("#loginPwd").val(),
            		pwd:$("#oldLoginPwd").val()	//原密码
            	  },
         	dataType: "text",
            success: function(data) {
                if(data == "success"){
                	showMessageCenterFull("密码修改成功!");
                	$('#dd2').dialog('close');
                	return;
                }
                if(data == "errCode"){
                	showMessageCenterFull("原密码错误!");
                	return;
                }
                showMessageCenterFull("密码修改失败!");
            }
        });
	}

/**
 * 跳转任务管理中的待办任务
 * @Author   K.
 * @DateTime 2017-11-02
 * @return   {[type]}   [description]
 */
function goTodos() {
	//$('#1002').click();
	refreshFrame();
	var src = ctxPath + '/pztask/init?isdbsx=newIndex';
	$('#contentFrame').attr('src', src);
	$('.top-nav-active').removeClass('top-nav-active');
	$('#1002').addClass('top-nav-active');
}

/**
 * 常见问题页面跳转
 * @Author   K.
 * @DateTime 2017-11-02
 * @return   {[type]}   [description]
 */
function openQuestion(){
	window.open(ctxPath+'/resource/help/help.html','_blank');
}
