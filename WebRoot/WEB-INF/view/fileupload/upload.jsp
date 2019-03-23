<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../common/common.jsp"%>
<!DOCTYPE html  
PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"  
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>附件上传页</title>
<link rel="stylesheet" type="text/css"
	href="${ctx}/resource/style/skynetIcon.css" />
<script type="text/javascript">
	/**function addFile(){
	    var myDate = new Date();
	    var num=myDate.getTime();
	    var delid=num+2222222222222;
		var upFile = '<input type="file" style="width: 300" name="file'+num+'" id='+num+' onchange="selecton(document.getElementById('+num+'))" />'
		+'<input type="button" id='+delid+' value="删除附件" onclick="deleteFile(this)" /><br/>';
		document .getElementById ("files").insertAdjacentHTML("beforeEnd",upFile);
		}
	
	function deleteFile(){
		var file = document .getElementById ("files").lastChild;
		if(file == null)
		return;
		document.getElementById ("files").removeChild(file);
		
		file = document .getElementById ("files").lastChild; //移除换行符<br>所以要移两次
		document .getElementById ("files").removeChild(file); //如果在表格里面不加<br>就自动换行的，可以去掉，自己把握
		
		}
		**/
	function deleteFile(id){
		 document.getElementById('th_'+id).innerHTML = "";
		 document.getElementById('td_'+id).innerHTML = "";
	}
	var i=0;
	function addfile() {
	 var myDate = new Date();
	 var num=myDate.getTime();
	 var oTR  = document.createElement("tr");
	 var oTH = document.createElement("th");
	 var oTD = document.createElement("td");
	 var oD=document.getElementById('files');
	 tr = oD.appendChild(oTR);
	 th = tr.appendChild(oTH);
	 td = tr.appendChild(oTD);
	 td.setAttribute("id","td_" + i);
	 th.setAttribute("id","th_" + i);
	 td.innerHTML='<input type="file" style="width: 300" name="file'+num+'" id='+num+' onchange="selecton(document.getElementById('+num+'))" />'
	 +'<a href="#" onclick="deleteFile('+i+')" id="dButton" class="easyui-linkbutton" iconCls="icon-add">删除</a>';
	 
	 i++;
	}
	/**
	设定上传格式
	**/
	function selecton(byid){
	    var  aa=byid.value;
		var re = /.txt$/i;
		var re1 = /.xlsx$/i;
		var re2 = /.docx$/i;
		var re3 = /.doc$/i;
		var re4 = /.ppt$/i;
		var re5 = /.xls$/i;
		var re6 = /.rar$/i;
		var re7 = /.zip$/i;
		var re8 = /.gif$/i;
		var re9 = /.jpg$/i;
		var re10 = /.png$/i;
		if(re.test(aa)||re1.test(aa)||re2.test(aa)||re3.test(aa)
		  ||re4.test(aa)||re5.test(aa)||re6.test(aa)||re7.test(aa)
		  ||re8.test(aa)||re9.test(aa)||re10.test(aa)){
		}else{
			showMessage('此附件格式不允许上传!');
		//byid.select();  
	    // document.selection.clear();
	    byid.outerHTML=byid.outerHTML;
		}
		
    }
</script>
</head>
<body>
	&nbsp;&nbsp;
	<input type="file" style="width: 300" name="file1" id="file1"
		onchange="selecton(document.getElementById('file1'))" />
	<a href="#" onclick="addfile()" id="aButton"><img style="border: 0"
		src="${ctx}/resource/common/jquery-easyui-1.3.5/icons/edit_add.png" /></a>
	<div id="first">
		<table>
			<tbody id="files" name="filess">
			</tbody>
		</table>
	</div>
</body>
</html>
