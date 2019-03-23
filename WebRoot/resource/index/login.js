var loginParams={
		loginState:"cerBox",//默认数字证书登录
		loginRam:'',//证书随机数
		signedData:''
};

function accountLogin(){
	if(!doCheck()){
    	return;
    }
	$.post(ctxPath + '/login/checkUsr',{'account':$("#account").val(),'passwd':$("#passwd").val()},
        function(data){
        	// 统一处理session过期
        	if(hb.Obejct.parseJSON(data)){
        		// 存在错误信息
        		if(!hb.StrUtil.isNull(data.msgCode)){
					if(data.msgCode=="index_002"){
        				$("#passwd").focus();
        				$('#msg').html(data.msgInfo);
        			}else{
        				$("#account").focus();
        				$('#msg').html(data.msgInfo);
        			}
        		}else{
        			window.location.href = ctxPath+"/login/init";
        		}
        	}
    	}
    );
}

function showLoginDiv(showdiv,hiddendiv){
	$("#"+hiddendiv).css("display","none");
	$("#"+showdiv).css("display","block");
}

function doCheck(){
	var rlt = true;

	var account=$("#account").val().trim();
	$("#account").val(account);

	if(hb.StrUtil.isBlank(account)){
		$('#msg').html('用户名必须输入！');
		$("#account").focus();
		rlt = false;

	} else if(hb.StrUtil.isBlank($("#passwd").val())){
		$('#msg').html('登录密码必须输入！');
		$("#passwd").focus();
		rlt = false;
	}

	return rlt;
}


/** 打开新页面，且页面最大化 */
function nevigate(str){
	var Sys = {};
	var ua = navigator.userAgent.toLowerCase();
	var s;
	(s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] :
	(s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] :
	(s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] :
	(s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] :
	(s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;
	var ieversion;

	var width = screen.width-10;
	var height = screen.height-80 ;

	var winPara = "top=0," ;
	winPara +="left=0," ;
	winPara +="width="+width+"," ;
	winPara +="height="+height+"," ;
	winPara +="directories=no," ;
	winPara +="status=yes," ;
	winPara +="menubar=no" ;

	window.open(str,"",winPara) ;

	// IE内核
	if (Sys.ie){
		ieversion = parseInt(Sys.ie);
		if(ieversion<7) {
			window.opener = null;
		} else{
			window.opener = null;
			window.open("", "_self");
		}
	}

	// webkit内核
	if(Sys.chrome){
		window.opener = null;
		window.open("", "_self");
	}

	window.close();
}

/**
 * 数字登录随机数获取
 */
function createCARandom() {

	$.ajax({
		url : ctxPath+"/caLogin/createCARandom",
		type : "post",
		success : function(data, textStatus) {//ajax请求成功
			if (data.msgInfo == null) {//登录成功跳转1：N比对
				loginParams.loginRam=data.sysChoose;
				doDataProcess(true);
			} else {
				$('#datamsg').text(data.msgInfo);
			}
		},
		error : function(xhr, textStatus, errorThrown) {//ajax请求失败
			alert(textStatus + ":" + errorThrown + "ajax请求失败；");
		}
	});
}
/**
 * 根据原文和证书产生认证数据包
 * showMsg:boolean 标志，true表示显示错误信息，false，不显示错误信息
 * @return {TypeName}
 */
//根据原文和证书产生认证数据包
function doDataProcess(showMsg){
	//先删掉页面存在的错误提示信息
    //$("#msg").html("");
	var JITDSignOcx=document.getElementById("JITDSignOcx");
	showMsg=(showMsg==null?false:showMsg);
	var Auth_Content = loginParams.loginRam;
	var DSign_Subject = document.getElementById("RootCADN").value;
	if(loginParams.loginRam==""){
		$("#msg").html("认证原文不能为空!");
	}else{

		//如果没有安装控件，则直接退出。 BY ： LC 2016-01-12
		if(typeof(JITDSignOcx)=='undefined' || typeof(JITDSignOcx.SetCertChooseType)=="undefined")
		{
			$("#msg").html("请先安装数字证书插件!");
			return false;
		}

		//控制证书为一个时，不弹出证书选择框
		JITDSignOcx.SetCertChooseType(1);
		JITDSignOcx.SetCert("SC","","","",DSign_Subject,"");
		if(JITDSignOcx.GetErrorCode()!=0)
		{
			if(false!=showMsg)
			{
				//alert("错误码："+JITDSignOcx.GetErrorCode()+"　错误信息："+JITDSignOcx.GetErrorMessage(JITDSignOcx.GetErrorCode()));
				$("#msg").html(JITDSignOcx.GetErrorMessage(JITDSignOcx.GetErrorCode())+"("+JITDSignOcx.GetErrorCode()+")");
			}
			return false;
		}
		else
		{
			 var tempDSignResult = JITDSignOcx.DetachSignStr("",Auth_Content);
			 if(JITDSignOcx.GetErrorCode()!=0)
			 {
					if(false!=showMsg)
			        {
				  		//alert("错误码："+JITDSignOcx.GetErrorCode()+"　错误信息："+JITDSignOcx.GetErrorMessage(JITDSignOcx.GetErrorCode()));
				  		$("#msg").html(JITDSignOcx.GetErrorMessage(JITDSignOcx.GetErrorCode())+"("+JITDSignOcx.GetErrorCode()+")");
				  	}
					return false;
			 }

		//如果Get请求，需要放开下面注释部分
		//	 while(tempDSignResult.indexOf('+')!=-1) {
		//		 tempDSignResult=tempDSignResult.replace("+","%2B");
		//	 }
			 loginParams.signedData = tempDSignResult;		}

	}

	checkCerUser();//数字证书登录验证



}
/**
 * 登录账号密码验证
 */
function checkCerUser() {
	disabledLoginButton();
	$.ajax({
		url : ctxPath+"/caLogin/caLoginCheck",
		type : "post",
		dataType : 'json',
		data : {
			"originalJsp" : loginParams.loginRam,
			"signedData" : loginParams.signedData
		},
		success : function(data, textStatus) {//ajax请求成功
			if (data.msgInfo == null) {//登录成功跳转1：N比对
				window.location.href = ctxPath+"/login/init";
			} else {
				$("#cerbtn").html("证书登录");
				$('#msg').text(data.msgInfo);
			}
		},
		error : function(xhr, textStatus, errorThrown) {//ajax请求失败
			alert(textStatus + ":" + errorThrown + "ajax请求失败；");
		}
	});
}

/**
 * 禁用登录按钮
 */
function disabledLoginButton() {
	//屏蔽登录按钮，防止用户重复点击登录。
	$("#cerbtn").unbind("click").html("正在登录...");
}




//新版本登录
$(function() {
	var url = window.location.href.split("#");
	var reg = url[1];
	if(reg == "zhLogin"){
		$("#accountLogin").show();
		$("#cerLogin").hide();
		$("#pwdBtn").show();
		$("#CARBtn").hide();
	}else{
		$("#accountLogin").hide();
		$("#cerLogin").show();
		$("#pwdBtn").hide();
		$("#CARBtn").show();
	}
	
	$('#account').focus();

	$('#CARBtn').on('click', function(event) {
		// event.preventDefault();
		createCARandom();
	});
	$('#pwdBtn').on('click', function(event) {
		// event.preventDefault();
		accountLogin();
	});
});



