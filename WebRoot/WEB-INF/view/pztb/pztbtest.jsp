<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>重点人员管理系统</title>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<meta name="generator" content="MSHTML 8.00.7600.16588">


<style type="text/css">
.panelTable {
	width: 100%;
	border-collapse: collapse;
	color: #444444;
	font-size: 14px;
}

.panelTable td {
	padding-top: 20px
}

.cxnrTitle {
	padding-top: 20px
}

.panelTable input {
	width: 100%;
	border: 1px solid #444444;
}

.requ {
	color: red;
}

.panelContent {
	width: 80%;
	padding-bottom: 20px
}

.bottomBtn {
	text-align: center;
	padding: 30px 0;
}
</style>
<script type="text/javascript">
    var ajbh = '${ajbh}';
    var ajid = '${ajid}';
    var isNew = true;
    if(!!ajbh){
    	isNew = false;
    }
	window.onload = function() {
		$('#loading-mask').fadeOut();//页面初始加载，覆盖层
	}
	
</script>

</head>
<body style="padding: 0px; margin: 0px; border: 0px;">
	<form id="ffddss" class="easyui-form" method="post"
		data-options="novalidate:true">
		<div style="margin-bottom: 20px">
			<input class="easyui-textbox" name="name" style="width: 100%"
				data-options="label:'Name:',required:true">
		</div>
		<div style="margin-bottom: 20px">
			<input class="easyui-textbox" name="email" style="width: 100%"
				data-options="label:'Email:',required:true,validType:'email'">
		</div>
		<div style="margin-bottom: 20px">
			<input class="easyui-textbox" name="subject" style="width: 100%"
				data-options="label:'Subject:',required:true">
		</div>
		<div style="margin-bottom: 20px">
			<input class="easyui-textbox" name="message"
				style="width: 100%; height: 60px"
				data-options="label:'Message:',multiline:true">
		</div>
		<div style="margin-bottom: 20px">
			<select class="easyui-combobox" name="language" label="Language"
				style="width: 100%"><option value="ar">Arabic</option>
				<option value="bg">Bulgarian</option>
				<option value="ca">Catalan</option>
				<option value="zh-cht">Chinese Traditional</option>
				<option value="cs">Czech</option>
				<option value="da">Danish</option>
				<option value="nl">Dutch</option>
				<option value="en" selected="selected">English</option>
				<option value="et">Estonian</option>
				<option value="fi">Finnish</option>
				<option value="fr">French</option>
				<option value="de">German</option>
				<option value="el">Greek</option>
				<option value="ht">Haitian Creole</option>
				<option value="he">Hebrew</option>
				<option value="hi">Hindi</option>
				<option value="mww">Hmong Daw</option>
				<option value="hu">Hungarian</option>
				<option value="id">Indonesian</option>
				<option value="it">Italian</option>
				<option value="ja">Japanese</option>
				<option value="ko">Korean</option>
				<option value="lv">Latvian</option>
				<option value="lt">Lithuanian</option>
				<option value="no">Norwegian</option>
				<option value="fa">Persian</option>
				<option value="pl">Polish</option>
				<option value="pt">Portuguese</option>
				<option value="ro">Romanian</option>
				<option value="ru">Russian</option>
				<option value="sk">Slovak</option>
				<option value="sl">Slovenian</option>
				<option value="es">Spanish</option>
				<option value="sv">Swedish</option>
				<option value="th">Thai</option>
				<option value="tr">Turkish</option>
				<option value="uk">Ukrainian</option>
				<option value="vi">Vietnamese</option></select>
		</div>
	</form>
	<div style="text-align: center; padding: 5px 0">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="submitForm()" style="width: 80px">Submit</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			onclick="clearForm()" style="width: 80px">Clear</a>
	</div>
	<script>
        function submitForm(){
            $('#ffddss').form('submit',{
                onSubmit:function(){
                    return $(this).form('enableValidation').form('validate');
                }
            });
        }
        function clearForm(){
            $('#ff').form('clear');
        }
    </script>
</body>
</html>