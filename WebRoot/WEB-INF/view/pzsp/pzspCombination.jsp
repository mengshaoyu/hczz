<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>任务审批 - 合成作战平台</title>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<meta name="generator" content="MSHTML 8.00.7600.16588">
<script type="text/javascript" src="${ctx}/resource/pzsp/pzspCombination.js"></script>
<script type="text/javascript">
	var pzid = '${pzid}';
	var actId = '${actId}';
	window.onload = function() {
		$('#loading-mask').fadeOut();//页面初始加载，覆盖层
	}
	
</script>
<style type="text/css" media="screen">
html, body, div, span, applet, object, iframe, h1, h2, h3, h4, h5, h6, p,
	blockquote, pre, a, abbr, acronym, address, big, cite, code, del, dfn,
	em, img, ins, kbd, q, s, samp, small, strike, strong, sub, sup, tt, var,
	b, u, i, center, dl, dt, dd, ol, ul, li, fieldset, form, label, legend,
	table, caption, tbody, tfoot, thead, tr, th, td, article, aside, canvas,
	details, embed, figure, figcaption, footer, header, hgroup, menu, nav,
	output, ruby, section, summary, time, mark, audio, video {
	margin: 0;
	padding: 0;
	border: 0;
	font-size: 100%;
	font: inherit;
}
/* HTML5 display-role reset for older browsers */
article, aside, details, figcaption, figure, footer, header, hgroup,
	menu, nav, section {
	display: block;
}

body {
	line-height: 1;
	overflow:hidden;
	font-family:"Microsoft Yahei";
}

ol, ul {
	list-style: none;
}

blockquote, q {
	quotes: none;
}

blockquote:before, blockquote:after, q:before, q:after {
	content: '';
	content: none;
}

table {
	border-collapse: collapse;
	border-spacing: 0;
}

.tbl-table, .tbl-wrapper-c p {
	border-collapse: collapse;
	border-spacing: 0;
	font-family: '仿宋';
}

.tbl-wrapper-c {
	width: 100%;
}

.tbl-wrapper-c p {
	font-size: 18px;
	line-height: 30px;
}

.tbl-wrapper-c h3 {
	font-family: '方正小标宋简体';
	text-align: center;
	font-size: 22px;
	font-weight: bold;
	padding-bottom: 10px;
	padding-top: 30px;
}

.tbl-wrapper-c table {
	width: 80%;
	border: 1px solid #333;
}

.tbl-wrapper-c table td {
	border: 1px solid #333;
	text-align: center;
	font-size: 16px;
	min-width: 50px;
	padding-left:5px;
}

.tbl-wrapper-c .tbl-value-left {
	display: block;
	text-align: left;
}

.tbl-wrapper-c table tr {
	height: 60px;
}

.tbl-wrapper-c  .situation {
	height: 120px;
}

.tbl-wrapper-c .left-title {
	width: 60px;
}

.tbl-wrapper-c .big-grid td {
	width: 80px;
}

.changehasSpxx {
	font-size: 14px;
	padding: 50px 10px 0;
	text-align: right;
}

#spform {
	width: 100%;
	background-color: #fff;
	padding-top: 1px;
}
.print-button{
	margin-top:10px;
}
.tbl-wrapper-c iframe {
	width: 100%;
	height:800px;
	border: 0px solid #333;
}
iframe {
	border: none;
}
.spBtn{
	padding: 0 15px;
	height: 24px;
	border: 0;
	line-height: 24px;
	background: #4381e6;
	color: #a7a7a7;
	font-color:white;
	cursor:pointer;
	color:#FFF;
	height:25px;
	width:50px;
	display:block;
}
.spBtn:hover{
	background:#3d74cf;
	color:#fff;
}
.sp-box td{
	height:40px;
	font-size:14px;
}
.cyy{color:#3dc47d;font-size:12px; cursor:pointer;}
.cyy:hover{ color:#25b067; text-decoration:underline;}
.cyy-btn textarea{
	width:310px;
	padding:5px;
	font-family:"Microsoft Yahei";
}
.cyy-btn > td{
	height:65px;

}
.noprint{display : none } 

.spdialog-table{
    width: 376px;
    margin: 1% auto;
    font-size: 14px;
    border-collapse: collapse;
}
.spdialog-table td{
    padding-right:10px;
    border:1px dotted #d9d9d9;
}
.spdialog-table td.left{
    background:#f1f1f1;
}
.spdialog-table td.top{
    border:0 none;
    color:#4381e6;
    height:50px;
    line-height:50px; 
    padding-left:10px;
    font-weight:700;
    font-size:14px;
}
#goffdiv{
    text-align:center;
    height:120px;
    font-size:25px;
    padding-left:20px;
    line-height:120px;
    background: url(${ctx}/resource/image/spwc.png) no-repeat 100px center;
}
#goffdivbtn{
    text-align: center;
    height:70px;
    line-height: 70px
}
</style>

</head>
<body style="padding: 0px; margin: 0px; border: 0px;">

	<!-- 等待加载 遮罩层 -->
	<div id="loading-mask"
		style="position: absolute; top: 0px; left: 0px; width: 100%; height: 100%; background: #F4F4F4; z-index: 20000">
		<div id="pageloading"
			style="width: 200px; height: 15px; position: absolute; top: 50%; left: 50%; margin: -20px 0px 0px -120px; text-align: center; border: 2px solid #ccc; font-size: 14px; padding: 10px; background: white;">
			<img style="vertical-align: middle; float: left;"
				src="../resource/image/loading.gif">页面加载中，请稍后...
		</div>
	</div>
	<%@ include file='../index/public-header.jsp' %>
	<form id="spform">
		<div class="bread-nav">${caseName}</div>
		<table style="width: 100%">
		  <tr>
		    <td valign="top" style="width: 210px;padding-top:30px;">
			    <p class="noprint">
	                <div style="float: left; height:270px; width:200px; border:1px solid #4381e6; margin-left:10px; ">
	                    <table width="100%" class="sp-box">
	                        <tr>
	                            <td style="background:#4381e6; color:#FFF; height:30px!important;" align="center" colspan="3">审核/审批</td>
	                        </tr>
	                        <tr>
	                            <td colspan="2" height="35px" style="padding-left:10px;">审批结果 </td>
	                        </tr>
	                        <tr>
	                            <td><input type='radio' onclick="setSPInfo(1);" name='spjl' id='spjl' value='1' style="margin-left:10px;" checked /><span style="padding-left:5px;">通过</span></td>
	                            <td><input type='radio' onclick="setSPInfo(2);" name='spjl' id='fgspjl'  value='2' style="margin-left:10px;" /><span style="padding-left:5px">驳回</span></td>
	                        </tr>
	                        <tr>
	                            <td  colspan="2" style=" height:1px;border-bottom:1px dotted #f1f1f1;"></td>
	                        </tr>
	                        <tr>
	                            <td colspan="2" style="padding-left:10px;">审批意见  &nbsp;&nbsp; <a class="cyy" onclick="commonClick();">常用语</a></td>
	                        </tr>
	                        <tr>
	                            <td colspan="2"><textarea  id="spInfo" style="margin-left:10px; width:178px;" rows="3" placeholder="请输入审批意见" ></textarea></td>
	                        </tr>
	                        <tr>
	                            <td style=" height:1px; border-bottom:1px dotted #f1f1f1;" colspan="2"></td>
	                        </tr>
	                        <tr>
	                            <td align="center" > <p><a class="spBtn" href="javascript:void(0)" onclick="saveNext()">审核</a></p></td>
	                            <td align="center" > <p><a class="spBtn"  href="javascript:void(0)" onclick="setReSetSPInfo();">重置</a></p></td>
	                        </tr>
	                    </table>
	                </div>
	            </p>
		    </td>
		    <td>
			    <div class="tbl-wrapper-c">
	                <iframe id="pzspIframe" frameborder="no" src="${ctx}/pzsp/init?pzid=${pzid}"></iframe>
	            </div>
		    </td>
		  </tr>
		</table>
	</form>
	<p class="noprint">
		<!--  常用语弹出框 -->
	<div id="setComWin" class="easyui-dialog" data-options="
				buttons: [{
					text:'保存',
					handler:function(){
						saveSPInfo();
					}
				},{
					text:'取消',
					handler:function(){
						closeCommon();
					}
				},{
					text:'重置',
					handler:function(){
						reSetSPInfo();
					}
				}]">
		<table id="" class="cyy-btn">
			<tr><td width="60" align="right" style="padding-right:10px;"><span>通过</span></td><td><textarea id="isGone" rows="2" cols="1" style="resize:none"maxlength="100" class="easyui-validatebox textarea " validtype="length[1,100]"  invalidMessage="最大长度100位"></textarea></td></tr>
			<tr><td style="height:5px;">&nbsp;&nbsp;</td></tr>
			<tr><td  width="60" align="right" style="padding-right:10px;"><span>驳回</span></td><td><textarea id="goBack" rows="2" cols="1" style="resize:none" maxlength="100" class="easyui-validatebox textarea " validtype="length[1,100]"  invalidMessage="最大长度100位"></textarea></td></tr>
		</table>
	</div>
	
	<!-- 提交审批dialog -->
    <div id="spdialog" class="easyui-dialog" closed="true" data-options="modal:true,closable: false" buttons="#spdialog-buttons" title="提交审批" style="width:400px;height:350px;top:100px">
        <table class="spdialog-table">
            <tr>
                <td colspan="2" class="top">请选择值班长审批</td>
            </tr>
            <tr>
                <td align="right" class="left" style="height:40px;width:100px;">审批人：</td>
                <td id="zbz" style="padding-left: 20px;">&nbsp;</td>
            </tr>
        </table>
    </div> 
    <div id="spdialog-buttons">
        <a id="spdialog-ok" href="javascript:void(0)" class="easyui-linkbutton" onclick="saveSpInfoNext()">提交</a> 
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closedialog('spdialog')">取消</a>
    </div>
    
    <!-- 是否跳转分发页面dialog -->
    <div id="goffdialog" class="easyui-dialog" closed="true" data-options="modal:true,closable: false" title="审批完成" style="width:400px;height:250px;top:100px">
        <div id="goffdiv">审批完成</div>
        <div id="goffdivbtn">
            <a id="goffdialog-ok" href="javascript:void(0)" class="easyui-linkbutton" onclick="goff()">立即分发任务</a> 
            &nbsp;&nbsp;&nbsp;&nbsp;
            <a href="javascript:void(0)" class="easyui-linkbutton" style="background-color: #ccc" onclick="window.close();">完成关闭页面</a>
        </div>
    </div>
    
	</p>
</body>
</html>