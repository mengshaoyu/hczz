<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<style>
#id {
	width: 50px;
	height: 20px;
}
</style>
<TITLE>Delete</TITLE>
<script>
var i=0;
function addfile() {
 var oTR  = document.createElement("tr");
 var oTH = document.createElement("th");
 var oTD = document.createElement("td");
 var oD=document.getElementById('files');
 tr = oD.appendChild(oTR);
 th = tr.appendChild(oTH);
 td = tr.appendChild(oTD);
 td.setAttribute("id","td_" + i);
 th.setAttribute("id","th_" + i)
 td.innerHTML = '文件：<input type="file" name="file[]" id="file[]" value="" size="22" /><a href="javascript:delefile('+i+');">删除</a>';
 
 i++;
}

function delefile(id){
 document.getElementById('th_'+id).innerHTML = "";
 document.getElementById('td_'+id).innerHTML = "";
}
  </script>
</HEAD>
<BODY>
	<input type="button" id="add" value="add" onclick="addfile();">
	<div id="first">
		<table>
			<tbody id="files" name="filess">

			</tbody>
		</table>
	</div>
</BODY>
</HTML>

