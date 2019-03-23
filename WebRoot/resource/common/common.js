(function(){
    var hb = {};
    /** 日志输出类 */
    hb.LogUtil = {
        log:function(msg,param){ // 输出日志信息 log
        	if(window.console){
        		console.log(msg, typeof param == 'undefined' ? '':param);
        	}
        },
        warn:function(msg,param){ // 输出日志信息 warn
        	if(window.console){
        		console.warn(msg, typeof param == 'undefined' ? '':param);
        	}
        },
        error:function(msg,param){ // 输出日志信息 error
        	if(window.console){
        		console.error(msg, typeof param == 'undefined' ? '':param);
        	}
        },
        info:function(msg,param){ // 输出日志信息 info
        	if(window.console){
        		console.info(msg, typeof param =='undefined' ? '':param);
        	}
        }
    };
    hb.StrUtil = {
        trim:function(str){ // trim字符串前后空格
    		if(null==str||""==str){
    			return str;
    		}
           return str.replace(/(^\s*)|(\s*$)/g, ''); 
        },
        lTrim :function(str){ // trim字符串左边空格
           return str.replace(/(^\s*)/g, ''); 
        },
        rTrim :function(str){ // trim字符串右边空格
           return str.replace(/(\s*$)/g, ''); 
        },
        isNull:function(str){ // 判断字符串是否为null
            if(typeof str == 'undefined' || str == null){
                return true;
            }else{
                return false;
            }
        },
        isBlank:function(str){ // 判断字符串是否为空
            if(typeof str == 'undefined' || str == null || hb.StrUtil.trim(str) == ''){
                return true;
            }else{
                return false;
            }
        },
        isNumber: function(str) {  // 判断字符串是否全为数据
            if(typeof str == 'undefined' || str == null || hb.StrUtil.trim(str) == ''){
                return false;
            }
            if (!(/^\d*$/g).test(str)) {
                return false;
            }
            return true;
        }
    };

	hb.Obejct = {
     	parseJSON:function(obj){
     		if (typeof obj == "string") {
     			if(obj=="" || null==obj){//数据为空字符串返回false
     				return false;
     			}
				obj = $.parseJSON(obj);
				if(obj.msgCode == 'SYS_101'){//解析为无权 返回false
					//$.messager.alert('信息', '会话超时，请重新登陆！', 'info');
					showMessage('会话超时，请重新登录！');
					parent.location.href=ctxPath+"/login/goOut";
				}else if(obj.msgCode == 'SYS_102'){
					//$.messager.alert('信息', '系统错误，请联系管理员！', 'info');
					showMessage('系统错误，请联系管理员！');
					return false;
				}else{//数据为其他字符串 返回obj
					return obj;
				}
				return false;
			}else{//数据为json 返回true
				if(obj.msgCode == 'SYS_101'){//解析为无权 返回false
					//$.messager.alert('信息', '会话超时或您没有访问权限！', 'info');
					showMessage('会话超时，请重新登录！');
					parent.location.href=ctxPath+"/login/goOut";
					return false;
				}else if(obj.msgCode == 'SYS_102'){
					//$.messager.alert('信息', '系统错误，请联系管理员！', 'info');
					showMessage('系统错误，请联系管理员！');
					return false;
				}else{
					return obj;
				}
				return true;
			}
     	}
     	
     };
	/**
	 * 后台返回数据的处理工具
	 */
	hb.VO = {
		/**
		 * 验证后台返回数据是否正常
		 */
		check : function(vo){
			if(vo && vo.head && vo.head.success==true)
				return true;
			else 
				return false;
		},
		/**
		 * 获取后台返回数据的head部分
		 */
		getHead : function(vo){
			if(vo && vo.head)
				return vo.head;
			else
				return null;
		},
		/**
		 * 获取后台返回数据的body部分
		 */
		getBody : function(vo){
			if(vo && vo.body){
				return vo.body;
			}
			else
				return null;
		},
		/**
		 * 获取响应码 200正常，其他异常
		 */
		getCode : function(vo){
			var head = this.getHead(vo);
			if(head){
				return head.code;
			}
			else 
				return NaN;
		},
		/**
		 * 获取后台返回的异常信息
		 */
		getMsg : function(vo){
			var head = this.getHead(vo);
			if(head){
				return head.msg;
			}
			else 
				return null;
		}
	}
    window.hb = hb;
})(window)
;

/** 替换全部字符 */
String.prototype.replaceAll = function(s1,s2){   
	return this.replace(new RegExp(s1,"gm"),s2);
}
String.prototype.trim = function(){
	return hb.StrUtil.trim(this);
}

Date.prototype.format = function(format) {
	  var o = {
	    "M+" : this.getMonth() + 1, // 月
	    "d+" : this.getDate(), // 天
	    "h+" : this.getHours(), // 时
	    "m+" : this.getMinutes(), // 分
	    "s+" : this.getSeconds(), // 秒
	    "q+" : Math.floor((this.getMonth() + 3) / 3), // 刻
	    "S" : this.getMilliseconds() //毫秒
	  // millisecond
	  }
	  if (/(y+)/.test(format))
	    format = format.replace(RegExp.$1, (this.getFullYear() + "")
	        .substr(4 - RegExp.$1.length));
	  for ( var k in o)
	    if (new RegExp("(" + k + ")").test(format))
	      format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
	          : ("00" + o[k]).substr(("" + o[k]).length));
	  return format;
	}
$.messager.defaults={ok:"确定",cancel:"取消"};


(function($) {
  // options是js文件中设置的属性
  $.fn.toolBar = function(options) {
  	var cnt = this;
  	// 控件的默认属性
	var defaults={
  		url:'../login/getToolBar',
  		page:300001,
  		nowrap:false
  	};
 
  	// 页面中设置的属性
  	$.each($(this)[0].attributes,function(idx,item){
  		if(item.name=='page'){
  			defaults['page']=item.value;
  		}else if(item.name=='url'){
  			defaults['url']=item.value;
  		}else if(item.name=='nowrap'){
  			defaults['nowrap']=item.value;
  		}
  	});

	var opts = $.extend(defaults, options);
	if(hb.StrUtil.isBlank(opts.url)){
		hb.LogUtil.error('取得toolBar的相对URL路径不能为空！');
	}else if(!hb.StrUtil.isNumber(opts.page+'')){
		hb.LogUtil.error('此页面的UUID不能为空！');
	}
	
	// 如果要求不换行，则添加不换行的CSS
	if(opts.nowrap){
		cnt.css({'white-space':'nowrap','overflow': 'hidden'});
	}
	
	$.post(opts.url,[{name:'page',value:opts.page}],
	  function(data){
	  	var str="";
	    $.each(data,function(idx, item){
	    	if(idx==0){
	    		str = '<a id="toolbar'+ item.uuid+'-first" class="easyui-linkbutton"  href="javascript:;" ';
	    	}else if(idx==data.length-1){
	    		str = '<a id="toolbar'+ item.uuid+'-last" class="easyui-linkbutton"  href="javascript:;" ';
	    	}else{
	    		str = '<a id="toolbar'+ item.uuid+'-middle" class="easyui-linkbutton"  href="javascript:;" ';
	    	}

           if(!hb.StrUtil.isBlank(item.openMethod)){//判断页面打开方式
           		if(item.openMethod==2){
           			str+= 'onclick="'+item.jsMethod+'()"';
           		}else if(item.openMethod==1){
           			 str+= 'onclick="parent.addTabPage(\''+item.uuid+'\',\''+item.name+'\',\''+item.iconCls+'\',\'\',\'\',\''+item.url+'\')"';//通过新标签页
           		}
           }
            if(!hb.StrUtil.isBlank(item.iconCls)){//判断图标是否为空
            //	str+=" iconCls='"+item.iconCls+"'";
            }
            if(!hb.StrUtil.isBlank(item.title)){//判断title是否为空
            	str+=" title='"+item.title+"'";
            }
            str+=' >'+item.name+'</a>';
	    	$(cnt).append(str);
	    });
	    
	  	//页面部分从新渲染easyui 样式
   		$.parser.parse(cnt);
  	    
		var osWidth = window.document.body.offsetWidth;
		var lastOs = $('#'+cnt[0].id+' a').last().offset();
		var lastWdth = $('#'+cnt[0].id+' a').last().width();
		if(cnt.width() - lastOs.left - lastWdth < 17 || cnt.width() > osWidth){
			cnt.css({'margin-left':'16px', 'margin-right':'16px'});
		  	var ofset = cnt.first().offset();
			cnt.parent().prepend('<a class="toolbar_arrow" style="z-index:99999;left:'+(ofset.left-17)+'px;top:'+(ofset.top+1)+'px;" href="javaScript:void(0)" onclick="toolBarMove(2,\''+cnt[0].id+'\')"><span class="toolbar_left"></span></a>');
			cnt.parent().append('<a class="toolbar_arrow" style="z-index:99999;right:0px;top:'+(ofset.top+1)+'px;" href="javaScript:void(0)" onclick="toolBarMove(1,\''+cnt[0].id+'\')"><span class="toolbar_right"></span></a>');	  		  	
		}
	  }
	);
  };     
})(jQuery);

var bars=new Array;
/** 移动toolBar中的标签位置 */
function toolBarMove(direct, tlbar){
	// 向左移
	if(direct == 1){
		if($('#'+tlbar+' a').first().attr("id").split("-")[1]=='last'){//判断是否为最后一个
		}else{
			bars.push($('#'+tlbar+' a').first());//将移除的对象放入数组
			$('#'+tlbar+' a').first().remove();
			//$('#'+tlbar+' a').first().insertAfter($('#'+tlbar+' a').last());		
		}
	// 向右移
	}else{
		if($('#'+tlbar+' a').first().attr("id").split("-")[1]=='first'){//判断是否为第一个
		}else{
			bars[bars.length-1].insertBefore($('#'+tlbar+' a').first());
			bars.splice(bars.length-1,1);////将恢复的按钮对象重数组中移除
			//arr.splice(idx,num,element)  idx开始删除下标，num删除个数，element插入元素
			//$('#'+tlbar+' a').last().insertBefore($('#'+tlbar+' a').first());
		}
	}
}
//数字格式化
//s:待处理数字 n:小数位数
function fnumber(s, n)   
{   
   if((null==s || ""==s || undefined==s || isNaN(s)) && s!=0 && s!="0"){
	   return s;
   }
   s=Math.abs(s);//取绝对值
   n = n > 0 && n <= 20 ? n : 2;   
   s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";   
   var l = s.split(".")[0].split("").reverse(),   
   r = s.split(".")[1];   
   t = "";   
   for(i = 0; i < l.length; i ++ )   
   {   
      t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");   
   }
   return t.split("").reverse().join("") + "." + r;  
} 
//数字格式化还原
function rnumber(s)   
{   
   return parseFloat(s.replace(/[^\d\.-]/g, ""));   
} 
//去空格方法（type :be , all）
function Trim(str,type) 
{ 
	var result=str; 
	if(type.toLowerCase()=="be"){//去首尾空格
		result = str.replace(/(^\s+)|(\s+$)/g,""); 
	}else if(type.toLowerCase()=="all") {//去所有空格
		result = result.replace(/\s/g,""); 
	}
	return result; 
}

//加载等待提示框
function onloading(){  
	//判断是否是第一次加载当前页面
	if($('#loading-mask').attr('display') == 'block'){
		return false;
	}else{
		$("<div class=\"datagrid-mask\"></div>").css({display:"block",width:"100%",height:$(document.body).height()}).appendTo("body");   
	    $("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候 . . .").appendTo("body").css({display:"block",'font-size':"14px",left:($(document.body).outerWidth(true) - 190) / 2,top:(document.body.clientHeight - 45) / 2});   
	}
}

//移除等待提示框  
function removeload(){  
    $(".datagrid-mask").remove();  
    $(".datagrid-mask-msg").remove();  
} 
//自定义遮罩
function opencover(){
	$("<div class=\"datagrid-mask\"></div>").css({display:"block",width:"100%",height:$(document.body).height()}).appendTo("body");
}
//关闭遮罩
function closecover(){
	$(".datagrid-mask").remove();
}
//自定义dilog监听 打开关闭遮罩层
function dlglistener(obj){
	 $("#"+obj).dialog({
               onClose:function(){
                   closecover();
               },
               onOpen:function(){
               	opencover();
               }
          });
}
//验证输入查询条件中是否有非法字符
function checkSql(sql){  
	var patrn=/[`~!@#$%^&*_+<>?"{},.\/;'[\]]/im;
    if(patrn.test(sql)){  
        return true;     
    }     
    return false;  
}
function checkSql2(sql){  
	var patrn=/[`~!@#$%^&*_+<>?"{}.\/;'[\]]/im;
	if(patrn.test(sql)){  
		return true;     
	}     
	return false;  
}
//屏蔽页面中F5 ,变换成刷新当前标签页 
document.onkeydown = function () {
//屏蔽页面中不可编辑的文本框中的backspace按钮事件  
	if (window.event.keyCode == 8) {     
	        if (document.activeElement.readOnly == undefined || document.activeElement.readOnly == true) {   
	            return event.returnValue = false;   
	        }    
	}   
    if (window.event.keyCode == 116 && !(window.event.ctrlKey && window.event.keyCode==116)) { 
		location.reload()         
        //禁用F5 
        window.event.keyCode = 0;  
       	return false;   
    }   
}
//ajax 请求公共处理类
$.ajaxSetup({ 
	error: function (XMLHttpRequest, textStatus, errorThrown){
		if(XMLHttpRequest.status==403){
			showMessage('您没有权限访问此资源或进行此操作');
			return false;
		}
	},  
    complete:function(XMLHttpRequest,textStatus){
    	var sessionstatus;
    	var headerresult;
    	try{
    		sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus"); //通过XMLHttpRequest取得响应头,sessionstatus -- response.setHeader
    		headerresult=XMLHttpRequest.getResponseHeader("headerresult"); //通过XMLHttpRequest获取响应头信息,headerresult -- response.setHeader
    	} catch(e){
    		removeload();
    	}
   		//var resulttext = XMLHttpRequest.responseText;//取得相应信息text -- response.getWriter();
        if(sessionstatus=="timeout"){   
           hb.Obejct.parseJSON(headerresult);//通过公共方法处理 返回信息字符串
   	    }else{
	   	    //解决easyui datagrid 序号过大显示不全问题
		   	try{
		   		//返回的数据集字符串
		   		var textJson = eval('('+resulttext+')');
		    	if(textJson.total && textJson.rows && (textJson.total+'').length>3){
		    		//rownumber列加宽
		    		$('.datagrid-header-rownumber,.datagrid-cell-rownumber').width(8*(textJson.total+'').length);
		    		//datagrid resize
		    		$('.panel .datagrid-view2:visible').next().datagrid('resize');
		    	}
		    	//设置页数输入框宽度
		   		if((parseInt(textJson.total/20)+'').length>3){
		   			$(".pagination-num").width(10*(parseInt(textJson.total/20)+'').length)
		   		}
			}catch(e){}  
		} 
    }   
});

//名称、编号处理
//name 名称
//no 编号 
//单元格宽度 显示长度限制
function dealNameNo(name,no,width){
	var surwidth = width;
	var length = 0;
	var dealstr = "";//对应列表字符串
	var allstr="";//title 对应字符串
	var flag=true;
	if(null!=no && ""!=no){
		dealstr+="["+no+"]";
		allstr+="["+no+"]";
	}
	if(null!=name && ""!=name){
		allstr+=name;
	}
	if(width<dealstr.length*6){
		dealstr=no.substr(0,surwidth/6-2);
		flag=false;
	}else if(width>=(dealstr.length*6+(name+"").length*12)){
		dealstr+=name;
	}else{
		surwidth = width-dealstr.length*6;
		dealstr+=name.substring(0,surwidth/12-1);
		flag=false;
	}
	if(dealstr=="" || dealstr==null || dealstr=='null'){
		return "<span>- -</span>";
	}
	return flag?dealstr:("<span title=\""+allstr+"\">"+dealstr+"...</span>");
}

function itemIndexOf(arr, val){
	for (var i = 0; i < arr.length; i++) {
		if (arr[i].personnelPk == val)
			return i;
	}
	return -1;
}

function removeItem(arr, val){
	var index = itemIndexOf(arr, val);
	if (index > -1) {
		arr.splice(index, 1);
	}
	return arr;
}


/**
 * 文件下载
 * @param fileUrl
 * @param fileName
 */
function downloadAttach(fileUrl,fileName){
	//验证导出树是否超过最大限制
	$.post(ctxPath+'/comm/checkNetResource', {'url': fileUrl},
	function(data){
		if(data && parseInt(data) == 200){
			var url = ctxPath+'/comm/downFile';
			window.location.href=url+"?url="+fileUrl+"&fileName="+encodeURI(encodeURI($.trim(fileName)));
		}else{
			showMessage("文件不存在！")
		}
	});
}

//兼容IE console
window.console = window.console || (function(){   
    var c = {}; c.log = c.warn = c.debug = c.info = c.error = c.time = c.dir = c.profile   
    = c.clear = c.exception = c.trace = c.assert = function(){};   
    return c;   
})();





