<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>合成作战平台</title>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<meta name="generator" content="MSHTML 8.00.7600.16588">
<script type="text/javascript" src="${ctx}/resource/pzsp/pzsp.js"></script>
<script type="text/javascript">
    var pzid = '${pzid}';
    var spjl='${spjl}';
    var actId = '${actId}';
    var isprint = '${isprint}';
    window.onload = function() {
        $('#loading-mask').fadeOut();//页面初始加载，覆盖层
        if(isprint){
            $('#print-out,#print-view').css('display','none');
            $('#print-go').css('display','block');
        }
    }
    function myPrint(){
        document.getElementById('print-go').style.display='none';
        if (window.ActiveXObject || "ActiveXObject" in window) {
            var wb=document.getElementById('printWB');
            console.log(wb);
            wb.ExecWB(7, 1);
        }else{
            window.print();
        }
        document.getElementById('print-go').style.display='block';
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
    width: 80%;
    margin: auto;
    color: #2f2f2f;
}

.tbl-wrapper-c p {
    font-size: 21px;
    line-height: 30px;
}

.tbl-wrapper-c h3 {
    font-family: '方正小标宋简体';
    text-align: center;
    font-size: 29px;
    padding-bottom: 10px;
    padding-top: 30px;
    color: #2f2f2f;
}

.tbl-wrapper-c table {
    width: 100%;
    border: 1px solid #333;
}

.tbl-wrapper-c table td {
    border: 1px solid #333;
    text-align: center;
    font-size: 21px;
    min-width: 60px;
    padding:15px;
    line-height:26px;
}

.tbl-wrapper-c .tbl-value-left {
    display: block;
    text-align: left;
}
.tbl-wrapper-c .tbl-value {
    word-break:break-all;
}

.tbl-wrapper-c table tr {
    height: 60px;
}

.tbl-wrapper-c  .situation {
    height: 120px;
}

.tbl-wrapper-c .left-title {
    width: 15%;
}
.tbl-wrapper-c .right-value {
    width: 35%;
}

.tbl-wrapper-c .big-grid td {
    width: 80px;
}

.changehasSpxx {
    font-size: 14px;
    padding: 50px 10px 0;
    text-align: right;
}

.print-button{
    margin-top:10px;
}

iframe {
    border: none;
}
.spBtn{
    padding: 0 15px;
    height: 24px;
    border: 0;
    line-height: 24px;
    background: #00A0FF;
    color: #a7a7a7;
    font-color:white;
    cursor:pointer;
    color:#FFF;
    font-size:12px;
    height:25px;
    width:50px;
    display:block;
}
.spBtn:hover{
    background:#505973;
}
.sp-box td{
    height:40px;
}
.cyy{color:#3dc47d;font-size:12px; cursor:pointer;}
.cyy:hover{ color:#25b067; text-decoration:underline;}
.cxrsm {
    padding:40px 100px;
}
.cxrsm li{
  text-indent: 36px;
  line-height:34px;
  text-indent:32px;
  font-family: "仿宋"; 
  font-size: 18px !important;
}
.cxrsm p{
  font-family: "仿宋"; 
  padding-right: 40px;
  font-size: 18px !important;
}
.cxrsm .shuming{
  width: 300px;
  float: right;
}
.cut-line{
    height:2px;
    border-top:3px solid #000;
    border-bottom: 1px solid #000;
}
.bottom-line{
    height:2px;
    border-top:1px solid #000;
    border-bottom: 3px solid #000;
}
</style>
<style type="text/css" media="print">

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
@page{
    size:A4;
} 
body {
    line-height: 1;
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
    font-size:16pt;
}
.tbl-wrapper-c {
    width: 100%;
}

.tbl-wrapper-c p {
    font-size: 16px;
    line-height: 30px;
}

.tbl-wrapper-c h3 {
    font-family: '方正小标宋简体';
    text-align: center;
    font-size: 20pt;
    font-weight: bold;
    padding-bottom: 20px;
}
#tt-head{margin-top:-20px;}

.tbl-wrapper-c table {
    width: 100%;
    border: 1px solid #333;
}

.tbl-wrapper-c table td {
    border: 1px solid #333;
    text-align: center;
    font-size: 14pt;
    min-width: 50px;
    font-family: '仿宋';
    padding:10px;
    line-height: 26px;
}

.tbl-wrapper-c .tbl-value-left {
    display: block;
    text-align: left;
    word-break:break-all;
}

.tbl-wrapper-c table tr {
    height: 40px;
}

.tbl-wrapper-c  .situation {
    height: 120px;
}

.tbl-wrapper-c .left-title {
    min-width: 80px;
}

.tbl-wrapper-c .big-grid td {
    width: 80px;
}

.changehasSpxx {
    font-size: 14px;
    padding: 50px 10px 0;
    text-align: right;
    display:none;
}
.changehasSpxx h3{
    font-family:"方正小标宋简体"!important;
    font-size:22pt!important;
    padding-top:10px;
}
#spform {
    width: 95%;
    background-color: #fff;
    margin: 0 auto;
}
.print-button{
    display:none;
}
iframe {
    border: 0 none!important;
    frameborder:none!important;
}
.bottomBtn{
    display:none!important;
}
.tbl-wrapper-c .right-value{
    padding:10px 5px;
    min-width:120px;
}
.cxrsm{margin-top:45px;}
.cxrsm p{
  font-family: "仿宋"; 
  padding-right: 30px;
  font-size: 14pt;
}
.noprint{display : none } 
.cxrsm li{font-size:14pt;line-height:34px;text-indent:32px;font-family: "仿宋";}
.cxrsm .shuming{width: 250px;float: right;}
</style>
</head>
<body style="padding: 0px; margin: 0px; border: 0px;">
    <div class="noprint">
    <!-- 等待加载 遮罩层 -->
    <div id="loading-mask"
        style="position: absolute; top: 0px; left: 0px; width: 100%; height: 100%; background: #F4F4F4; z-index: 20000">
        <div id="pageloading"
            style="width: 200px; height: 15px; position: absolute; top: 50%; left: 50%; margin: -20px 0px 0px -120px; text-align: center; border: 2px solid #ccc; font-size: 14px; padding: 10px; background: white;">
            <img style="vertical-align: middle; float: left;"
                src="../resource/image/loading.gif">页面加载中，请稍后...
        </div>
    </div>
    </div>

    <form id="spform">
        <div class="tbl-wrapper-c">
            <p class="noprint">
            <div id="print-out" class="print-button" style="float: right;padding-right:20px">
        <!--        <a id="btn" href="javascript:void(0);" class="easyui-linkbutton"
                 onclick="printReview();">打印预览</a> -->
                <a id="exportWord" href="javascript:void(0);" class="easyui-linkbutton"
                 onclick="exportWord();">导出</a>
            </div>
            <div id="print-view" class="print-button" style="float: right;padding-right:20px">
                <a class="easyui-linkbutton" onclick="window.open('${ctx}/pzsp/init?pzid='+pzid+'&isprint=1')">打印预览</a>
            </div>
            <div id="print-go" class="print-button noprint" style="float: right;padding-right:20px;display:none">
                <a class="easyui-linkbutton" onclick="myPrint()">打印</a>
            </div>
            <object id="printWB" style="dispaly:none" classid="clsid:8856F961-340A-11D0-A96B-00C04FD705A2" height="0"></object>
            <br><br>
            </p>

            <!-- <div class="changehasSpxx">
                <input id="hasSpxx" type="checkbox" name="hasSpxx" checked
                    onclick="changehasSpxx()">是否导出当前环节的领导意见
            </div> -->
            <!--startprint1-->
            <h3 id="tt-head">配侦请求申请表</h3>
            <br>
            <p style="float:right">
                编号: <span id="pzApplyNo" class="tbl-value"></span>
            </p>
            <p style="position: relative;">单位盖章
                <img id="deptSign" style="position: absolute;left:20px;top:-40px;height:200px">
            </p>
            <table class="tbl-table">
                <tr>
                    <td class="left-title" >申请单位</td>
                    <td class="right-value"><span id="acceptUnit" class="tbl-value"></span></td>
                    <td class="left-title">申请时间</td>
                    <td class="right-value"><span id="sqTime" class="tbl-value"></span></td>
                </tr>
                <tr>
                    <td class="left-title">案件编号</td>
                    <td class="right-value"><span id="caseNo" class="tbl-value"></span></td>
                    <td class="left-title">案别</td>
                    <td class="right-value"><span id="caseType" class="tbl-value"></span></td>
                </tr>
                <tr class="big-grid">
                    <td class="left-title">案件名称</td>
                    <td class="right-value"><span id="caseName" class="tbl-value"></span></td>
                    <td class="left-title">案发时间</td>
                    <td class="right-value"><span id="incidentDate" class="tbl-value"></span></td>
                </tr>
                <tr class="situation">
                    <td class="left-title">简要案情</td>
                    <td colspan="3"><span id="caseDesc"
                        class="tbl-value tbl-value-left"></span></td>
                </tr>
                <tr class="situation">
                    <td class="left-title">查询线索及线索来源</td>
                    <td colspan="3"><span id="clueName"
                        class="tbl-value tbl-value-left"></span></td>
                </tr>
                <tr>
                    <td class="left-title" >申请人</td>
                    <td class="right-value"><span id="username" class="tbl-value"></span></td>
                    <td class="left-title">联系电话</td>
                    <td class="right-value"><span id="mobilePhone" class="tbl-value"></span></td>
                </tr>
                <tr>
                    <td class="left-title" >申请单位领导意见</td>
                    <td style="word-break:break-all;text-align: left;height:140px" colspan="3">
                        <div id="spInfo"></div>
                        <div style="position: relative;height:100px">
                            <div style="position: absolute;bottom:0;right:10px;text-align: center">
                                <img id="fgldspSign" style="height:50px;">
                                <div id="spsj" style="text-align: right;"></div>
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="left-title" >合成作战中心领导意见</td>
                    <td style="word-break:break-all;text-align: left;height:140px" colspan="3">
                        <div id="fgspInfo"></div>
                        <div style="position: relative;height:100px">
                            <div style="position: absolute;bottom:0;right:10px;text-align: center">
                                <img id="zbzspSign" style="height:50px;">
                                <div id="fgspsj" style="text-align: right;"></div>
                            </div>
                        </div>
                    </td>
                </tr>
            </table>
                <div class="cxrsm">
                    <h3>查询人承诺</h3>
                    <ul>
                        <li>1、本人知悉所查询的内容属公民个人信息，本人承诺严格遵守国家相关法律、法规和政策，严格执行有关规定。</li>
                        <li>2、本人承诺严格遵守保密纪律，妥善保管查询结果，不向无关组织和个人透露查询内容，不将使用，如有违            反将依法承担责任。</li>
                        <li>3、本人承诺所提交的证明材料和文件真实有效。</li>
                    </ul>
                    <br><br>
                    <div class="shuming">
                        <p>
                            申请人：<img id="sprsqr" style="height:50px;">
                        </p>
                        <p>
                            审批人：<img id="sprsqrtext" style="height:50px;">
                        </p>
                        <p>
                            <span id="sqsj"></span>
                        </p>
                    </div>
                </div>
            
            <!--endprint1-->
        </div>
    </form>
    <div class="bottomBtn">
         <object id="WebBrowser" classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 height="0" width="0"> </object> 
    </div>
</body>
</html>