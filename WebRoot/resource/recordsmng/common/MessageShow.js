/**
 * alert弹出
 * @param {Object} msg
 * @param {Object} type
 */
function alertMsg(msg,type){
	$.messager.alert("提示",msg,type);//type--null、info、error、question、warning
}
function alertMsgFunc(msg,type,func){
	$.messager.alert("提示",msg,type,func);//type--null、info、error、question、warning
}
function confirmMsgFunc(title,msg,func){
	$.messager.confirm(title,msg,func);
}
/**
 * 页面上方滑动展示提示信息，2s后自动关闭提示信息
 * @param {Object} msg
 */
function showMessage(msg){
	$.messager.show({
		title:'提示',
		msg:'<div style="font-size:14px;color:#F00; font-weight：700;font-family:"微软雅黑" ;">'+msg+'</div>',
		showType:'slide',//show
		timeout:3000,
		style:{
			right:'',
			top:0,
			bottom:''
		}
	});
}
/**
 * 页面中间滑动展示提示信息，2s后自动关闭提示信息
 * @param {Object} msg
 */
function showMessageCenterFull(msg){
	$.messager.show({
		title:'提示',
		msg:'<div style="font-size:14px;color:#F00; font-weight：700;font-family:"微软雅黑" ;">'+msg+'</div>',
		showType:'slide',//show
		timeout:3000,
		height:'100%',
		style:{
			right:'',
			top:10,
			bottom:''
		}
	});
}
function showMessageCenter(msg){
	$.messager.show({
		title:'提示',
		msg:'<div style="font-size:14px;color:#F00; font-weight：700;font-family:"微软雅黑" ;">'+msg+'</div>',
		showType:'slide',//show
		timeout:3000,
		style:{
			right:'',
			top:90,
			bottom:''
		}
	});
}
/**
 * 图片位置提示信息
 * @param {Object} msg
 */
function showMessageRight(msg){
	$.messager.show({
		title:'提示',
		msg:'<div style="font-size:12px;color:#F00; font-weight：700;font-family:"微软雅黑" ;">'+msg+'</div>',
		showType:'slide',//show
		timeout:3000,
		style:{
			right:'',
			left:520,
			top:document.body.scrollTop+document.documentElement.scrollTop,
			bottom:''
		}
	});
}

function showMessage3(msg){
	$.messager.show({
		title:'提示',
		msg:'<div style="font-size:12px;color:#F00; font-weight：700;font-family:"微软雅黑" ;">'+msg+'</div>',
		showType:'slide',//show
		timeout:3000,
		style:{
			right:'',
			left:520,
			top:document.body.scrollTop+document.documentElement.scrollTop,
			bottom:''
		}
	});
}

function showMessageFixed(msg){
	$.messager.show({
		title:'提示',
		msg:'<div style="font-size:14px;color:#F00; font-weight：700;font-family:"微软雅黑";">'+msg+'</div>',
		showType:'slide',//show
		timeout:2000,
		style:{
			right:'',
			top:100,
			bottom:'',
			position:'fixed'
		}
	});
}


//加载等待提示框
function showLoading(){  
     $("<div class=\"datagrid-mask\"></div>").css({display:"block","z-index":9999,width:"100%",height:$(document.body).height()}).appendTo("body");   
     $("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候 . . .").appendTo("body").css({display:"block",'font-size':"14px",left:($(document.body).outerWidth(true) - 190) / 2,top:($(document.body).height() - 45) / 2});   
}
//移除等待提示框  
function removeLoading(){  
    $(".datagrid-mask").remove();  
    $(".datagrid-mask-msg").remove();  
}

/*****************重写消息确认框(confirm):
*添加参数：
* 样式，样式的格式为(left:100)
* 遮罩层的ID：用于清除自己定义的遮罩层
*****************************/
function messageWinSelf(_272,_273,_274,style,loadMask){
	var win=$("<div class=\"messager-body\"></div>").appendTo("body");
	win.append(_273);
	if(_274){
	var tb=$("<div class=\"messager-button\"></div>").appendTo(win);
	for(var _275 in _274){
	$("<a></a>").attr("href","javascript:void(0)").text(_275).css("margin-left",10).bind("click",eval(_274[_275])).appendTo(tb).linkbutton();
	}
	}
	win.window({title:_272,noheader:(_272?false:true),width:300,height:"auto",modal:true,collapsible:false,minimizable:false,maximizable:false,resizable:false,onClose:function(){
		hideLoadMask(loadMask);
		setTimeout(function(){
			win.window("destroy");
		},100);
	}});
	if(null!=style&&""!=style&&undefined!=style&&'undefined'!=style){
		win.window(style);
	}
	win.window("window").addClass("messager-window");
	win.children("div.messager-button").children("a:last").focus();
	return win;
};

function confirm(_27a,msg,fn,style,loadMask){
	if(loadMask!=undefined&&loadMask!="undefined"&&loadMask!=""&&loadMask!=null){
		if($("#"+loadMask).css("display")!="block"){
			$("#"+loadMask).css("display","block");
		}
	}
	$.messager.defaults={ok:"确定",cancel:"取消"};
	_27b="";
	var _27c={};
	_27c[$.messager.defaults.ok]=function(){
		hideLoadMask(loadMask);
		win.window("close");
		if(fn){
			fn(true);
			return false;
		}
	};
	_27c[$.messager.defaults.cancel]=function(){
		hideLoadMask(loadMask);
		win.window("close");
		if(fn){
			fn(false);
			return false;
		}
	};
	var win=messageWinSelf(_27a,_27b,_27c,style,loadMask);
	return win;
}

/**********等待提示*******************/
//展示等待信息提示框：调用前若浮在控件之上，则先显示遮罩
function showProgress(msg){
    var win = $.messager.progress({
        title:"请稍候",
        msg:msg,
        text:""
    });
}

//关闭等待提示框：若之前已显示遮罩，则先隐藏，再调用方法
function closeProcess(){
	 $.messager.progress('close');
}

//展示等待信息提示框:方法中自带遮罩层的显示
function showProgressRe(msg,loadMask){
	if(loadMask!=undefined&&loadMask!="undefined"&&loadMask!=""&&loadMask!=null){
		if($("#"+loadMask).css("display")!="block"){
			$("#"+loadMask).css("display","block");
		}
	}
    var win = $.messager.progress({
        title:"请稍候",
        msg:msg,
        text:""
    });
}

//关闭等待提示框:方法中自带遮罩层的隐藏
function closeProcessRe(loadMask){
	 $.messager.progress('close');
	 if(loadMask!=undefined&&loadMask!="undefined"&&loadMask!=""&&loadMask!=null){
		$("#"+loadMask).css("display","none");
	}
}

/************重写show：解决提示信息被控件遮住的问题,使用此方法，需要在对应的jsp中定义遮罩层*********************************/
function showMessageRe(msg,loadMask){
	messageReWin({
		title:'提示',
		msg:'<div style="font-size:12px;color:#F00; font-weight：700;font-family:"微软雅黑" ;">'+msg+'</div>',
		showType:'slide',//show
		timeout:3000,
		loadMask:loadMask,
		style:{
			top:document.body.scrollTop+document.documentElement.scrollTop,
			bottom:''
		}
	});
}

function messageReWin(_270){//重写show方法
	var opts=$.extend({},$.fn.window.defaults,{collapsible:false,minimizable:false,maximizable:false,shadow:false,draggable:false,resizable:false,closed:true,style:{left:"",top:"",right:0,zIndex:$.fn.window.defaults.zIndex++,bottom:-document.body.scrollTop-document.documentElement.scrollTop},onBeforeOpen:function(){
	show(this,opts.showType,opts.showSpeed,opts.timeout,opts.loadMask);
	return false;
	},onBeforeClose:function(){
	hide(this,opts.showType,opts.showSpeed);
	return false;
	}},{title:"",width:250,height:100,showType:"slide",showSpeed:600,msg:"",timeout:4000},_270);
	opts.style.zIndex=$.fn.window.defaults.zIndex++;
	var win=$("<div class=\"messager-body\"></div>").html(opts.msg).appendTo("body");
	$("").appendTo(win);
	win.window(opts);
	win.window("window").css(opts.style);
	
	//添加遮罩，若遮罩存在且已经隐藏的情况下，展示遮罩
	if(opts.loadMask!=undefined&&opts.loadMask!="undefined"&&opts.loadMask!=""&&opts.loadMask!=null){
		if($("#"+opts.loadMask).css("display")!="block"){
			showLoadMask(opts.loadMask,opts.style.width,opts.style.height);
		}
	}
	win.window("open");
	return win;
}

function show(el,type,_26b,_26c,loadMask){
	var win=$(el).window("window");
	if(!win){
		return;
	}
	switch(type){
		case null:
			win.show();
			break;
		case "slide":
			win.slideDown(_26b);
			break;
		case "fade":
			win.fadeIn(_26b);
			break;
		case "show":
			win.show(_26b);
			break;
	}
	var _26d=null;
	if(_26c>0){
		_26d=setTimeout(function(){
			hide(el,type,_26b,loadMask);
		},_26c);
	}
	win.hover(function(){
		if(_26d){
			clearTimeout(_26d);
		}
	},function(){
		if(_26c>0){
			_26d=setTimeout(function(){
				hide(el,type,_26b,loadMask);
			},_26c);
		}
	});
};
function hide(el,type,_26e,loadMask){
	if(el.locked==true){
		return;
	}
	el.locked=true;
	var win=$(el).window("window");
	if(!win){
		return;
	}
	switch(type){
		case null:
			win.hide();
			break;
		case "slide":
			win.slideUp(_26e);
			break;
		case "fade":
			win.fadeOut(_26e);
			break;
		case "show":
			win.hide(_26e);
			break;
	}
	setTimeout(function(){
		$(el).window("destroy");
	},_26e);
	hideLoadMask(loadMask);
};

function showLoadMask(divId,width,height){
	var divwidth=250;
	var divheight=100;
	if(height!=undefined&&height!="undefined"){
		divheight=height;
	}
	if(width!=undefined&&width!="undefined"){
		divwidth=width;
	}
	var left=(document.body.clientWidth-divwidth)/2;
	$("#"+divId).css('left',left);
	$("#"+divId).css('width',divwidth);
	$("#"+divId).css('height',divheight);
	$("#"+divId).css('display','block');
}

function hideLoadMask(divId){
	if(divId!=undefined&&divId!="undefined"&&divId!=""&&divId!=null){
		$("#"+divId).css('display','none');
	}
}

/***************alert提示信息重写：解决遮罩问题****************************/

function alertOverRide(msg,type,loadMask){
	if(loadMask!=undefined&&loadMask!="undefined"&&loadMask!=""&&loadMask!=null){
		$("#"+loadMask).css("display","block");
	}
	initAlertDiv("提示",msg,type,null,loadMask);
}

function initAlertDiv(_277,msg,icon,fn,loadMask){
	var _278="<div>"+msg+"</div>";
	switch(icon){
		case "error":
			_278="<div class=\"messager-icon messager-error\"></div>"+_278;
			break;
		case "info":
			_278="<div class=\"messager-icon messager-info\"></div>"+_278;
			break;
		case "question":
			_278="<div class=\"messager-icon messager-question\"></div>"+_278;
			break;
		case "warning":
			_278="<div class=\"messager-icon messager-warning\"></div>"+_278;
			break;
		case "none":
			_278=""+_278;
			break;
	}
	_278+="<div style=\"clear:both;\"/>";
	var _279={};
	_279[$.messager.defaults.ok]=function(){
		win.window("close");
		if(loadMask!=undefined&&loadMask!="undefined"&&loadMask!=""&&loadMask!=null){
			$("#"+loadMask).css("display","none");
		}
		if(fn){
			fn();
			return false;
		}
	};
	var win=messageWinSelf(_277,_278,_279,null,"load-mask");
	return win;
}

/**********进度条重写：添加取消按钮*********************/
function processShow(msg){
	 processOverRide({
        title:"请稍候",
        msg:msg,
        text:""
    });
}

//关闭等待提示框：若之前已显示遮罩，则先隐藏，再调用方法
function processClose(){
	 processOverRide('close');
}


//展示等待信息提示框:方法中自带遮罩层的显示
function showProgressMask(msg,loadMask,useType){
	if(loadMask!=undefined&&loadMask!="undefined"&&loadMask!=""&&loadMask!=null){
		if($("#"+loadMask).css("display")!="block"){
			$("#"+loadMask).css("display","block");
		}
	}
	//若使用类型为空或undefined时，默认为0
	if(useType==undefined||useType=="undefined"||useType==""||useType==null){
		useType="0";
	}
	
    processOverRide({
        title:"请稍候",
        msg:msg,
        text:""
    },loadMask,useType);
}

//关闭等待提示框:方法中自带遮罩层的隐藏
function closeProcessMask(loadMask){
	 processOverRide('close');
	 if(loadMask!=undefined&&loadMask!="undefined"&&loadMask!=""&&loadMask!=null){
		$("#"+loadMask).css("display","none");
	}
}

function processOverRide(_280,loadMask,useType){
	var _281={bar:function(){
		return $("body>div.messager-window").find("div.messager-p-bar");
	},close:function(){
		var win=$("body>div.messager-window>div.messager-body:has(div.messager-progress)");
		if(win.length){
			win.window("close");
		}
	}};
	if(typeof _280=="string"){
		var _282=_281[_280];
		return _282();
	}
	var opts=$.extend({title:"",msg:"",text:undefined,interval:300},_280||{});
	var _283="<div class=\"messager-progress\"><div class=\"messager-p-msg\"></div><div class=\"messager-p-bar\"></div></div>";
	
	var _27c={};
	_27c[$.messager.defaults.cancel]=function(){
		if(loadMask!=undefined&&loadMask!="undefined"&&loadMask!=""&&loadMask!=null){
			$("#"+loadMask).css("display","none");
		}
		win.window("close");
		//特别处理：视频分析时取消播放关闭页签
		if(useType=="1"){//视频分析中需要回调的函数
			closeTabAnalysis();
		}
		if(useType=="2"){//视频先下后播时，取消下载
			clearDownLoad();
		}
	};
	
	var win=messageWin(opts.title,_283,_27c);
	win.find("div.messager-p-msg").html(opts.msg);
	var bar=win.find("div.messager-p-bar");
	bar.progressbar({text:opts.text});
	win.window({closable:false,onClose:function(){
		if(this.timer){
			clearInterval(this.timer);
		}
		$(this).window("destroy");
	}});
	if(opts.interval){
		win[0].timer=setInterval(function(){
			var v=bar.progressbar("getValue");
			v+=10;
			if(v>100){
				v=0;
			}
			bar.progressbar("setValue",v);
		},opts.interval);
	}
	return win;
}

function messageWin(_272,_273,_274){
	var win=$("<div class=\"messager-body\"></div>").appendTo("body");
	win.append(_273);
	if(_274){
	var tb=$("<div class=\"messager-button\"></div>").appendTo(win);
	for(var _275 in _274){
	$("<a></a>").attr("href","javascript:void(0)").text(_275).css("margin-left",10).bind("click",eval(_274[_275])).appendTo(tb).linkbutton();
	}
	}
	win.window({title:_272,noheader:(_272?false:true),width:300,height:"auto",modal:true,collapsible:false,minimizable:false,maximizable:false,resizable:false,onClose:function(){
	setTimeout(function(){
		win.window("destroy");
	},100);
	}});
	win.window("window").addClass("messager-window");
	win.children("div.messager-button").children("a:last").focus();
	return win;
};
/******************进度条重写结束*******************************/



/*********系统需要共通处理的问题[因避免大家再变动界面进行引用]***********************/
//特殊字符转义：若参数值中包含#或&，java中获取的参数值会仅包含#或&之前的字符
function changeString(filePath){
	filePath=encodeURI(filePath);
	filePath=filePath.replaceAll("#",escape("#"));
	filePath=filePath.replaceAll("&",escape("&"));
	return filePath;
}
/************************************/

