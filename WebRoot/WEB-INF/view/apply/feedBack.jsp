<!DOCTYPE HTML>
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

<script type="text/javascript"
    src="${ctx}/resource/common/jquery.form.js"></script>
<script type="text/javascript"
    src="${ctx}/resource/recordsmng/common/configService.js"></script>
<link rel="stylesheet" type="text/css" 
    href="${ctx}/resource/style/sop/feedback_print.css" media="print" />
    <link rel="stylesheet" type="text/css" 
    href="${ctx}/resource/style/sop/feedback.css" media="screen" />
<script type="text/javascript" src="${ctx}/resource/apply/feedBack.js"></script>

<script type="text/javascript">
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
    
    var pzApplyId='${applyMap.pzApplyId}';
    var isprint = '${isprint}';
</script>

</head>
<body
    style="padding: 0px; margin: 0px; border: 0px; background-color: #FFF; overflow-x:hidden;">

    <!-- 等待加载 遮罩层 -->
    <div id="loading-mask"
        style="position: absolute; top: 0px; left: 0px; width: 100%; height: 100%; background: #F4F4F4; z-index: 20000">
        <div id="pageloading"
            style="width: 200px; height: 15px; position: absolute; top: 50%; left: 50%; margin: -20px 0px 0px -120px; text-align: center; border: 2px solid #ccc; font-size: 14px; padding: 10px; background: white;">
            <img style="vertical-align: middle; float: left;"
                src="../resource/image/loading.gif">页面加载中，请稍后...
        </div>
    </div>
    <c:if test="${show=='1'}">
    <div class="tbl-wrapper-c" style="overflow:hidden;width:80%;margin:10px auto">
        <div
            style="float: right;" class="noprint" id="print-out">
            <!-- <a id="btn" href="javascript:void(0);" class="easyui-linkbutton"
                onclick="printReview();">打印预览</a> -->
            <a id="btn" href="javascript:void(0);" class="easyui-linkbutton"
                onclick="exportFeedBack();">导出</a> <input type="hidden"
                id="flowId" value="${applyMap.flowId}" /> &nbsp;&nbsp;&nbsp;&nbsp;
        </div>
        <div id="print-view" class="print-button" style="float: right;padding-right:20px">
            <a class="easyui-linkbutton" onclick="window.open('${ctx}/pztask/initFeedback?pzid='+pzApplyId+'&isprint=1')">打印预览</a>
        </div>
        <div id="print-go" class="print-button" style="float: right;padding-right:20px;display:none">
            <a class="easyui-linkbutton" onclick="myPrint()">打印</a>
        </div>
        <object id="printWB" style="dispaly:none" classid="clsid:8856F961-340A-11D0-A96B-00C04FD705A2" height="0"></object>
    </div>
        <div class="fankui-container">
            <h3 class="first">配侦请求研判</h3>
            <table class="fankuiSheet" border="0" width="100%">
                <thead style="display:table-header-group;">
                <tr>
                    <td colspan="2">
                        <div class="cut-line"></div>
                    </td>
                </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><h3>反馈报告</h3></td>
                    </tr>
                    <tr>
                        <td>
                            <p class="bianhao">编号: 
                            <span class="tbl-value">${applyMap.pzApplyNo}</span></p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p class="title">一、申请信息</p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p>1、申请单位：${applyMap.deptName}</p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p>2、申请时间：${applyMap.submitTime}</p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p>3、申请人：${applyMap.username}</p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p>4、案别：${applyMap.caseType}</p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p>5、简要案情：</p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p>${applyMap.caseDesc}</p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p>6、查询内容：</p>
                        </td>
                    </tr>
                    
                    <c:forEach var="clue" items="${feedBackMap.clues}"
                        varStatus="clueStatus">
                        <tr>
                            <td>
                                <p class="text">（${clueStatus.index+1}）${clue.clueName}，${clue.pzTypeDetailName}</p>
                            </td>
                        </tr>
                    </c:forEach>
                    
                    <tr>
                        <td>
                            <p  class="title">二、查询结果</p>
                        </td>
                    </tr>
                    <c:forEach var="clue" items="${feedBackMap.clues}"
                        varStatus="clueStatus">
                        <tr>
                            <td>
                                <p>${clueStatus.index+1}、查询内容${clue.index}：</p>
                            </td>
                        </tr>
                        <c:forEach var="result" items="${clue.pzResult}"
                            varStatus="status">
                            <tr>
                                <td>
                                    <p class="text">
                                        （${status.index+1}）${result.resultDescReport}。
                                        <c:if test="${not empty result.resultRemarkReport}">${result.resultRemarkReport}</c:if>
                                    </p>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <p>
                                        <c:if test="${not empty result.att}">
                                            <p>（附：
                                                <c:forEach var="att" items="${result.att}"
                                                    varStatus="att_status">
                                                    <a target="_blank" href="http://${serverName}:${serverPort}${att.attPath}">${att.attName}</a>
                                                </c:forEach>
                                            ）</p>
                                        </c:if>
                                    </p>
                                </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td>
                                <p>
                                    <c:if test="${not empty clue.clueSumup}">${clue.clueSumup}</c:if>
                                </p>
                            </td>
                        </tr>
                    </c:forEach>
                    
                    <c:if test="${not empty applyMap.sumUp}">
                        <tr>
                            <td>
                                <p class="title">三、综合结论</p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <p>${applyMap.sumUp}</p>
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <td>
                            <c:forEach  var="user" items="${userlist}" varStatus="userStatus">
                                <c:if test="${userStatus.index==0}">
                                    <p class="right name">
                                    <span>查询人：</span>
                                </c:if>
                                <c:if test="${userStatus.index>0}">
                                    <p class="right">
                                </c:if>
                                    <span>
<%--                                     ${user.userName} --%>
                                    <img alt="" src="/attachServer/signature/user/${user.userNo}.gif">
                                    <c:if test="${not empty user.policeType }">（${user.policeType}）
                                    </c:if></span>
                                </p>
                            </c:forEach>
                            <p  class="right">${applyBack.backDate}</p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p class="shengming">
                                声明：以上研判信息基于申请人提供的查询条件，通过公安内容及部门互联网信息获取，使用时需结合案件实际情况，作为案件侦办的参考。不能作为证据使用。
                            </p>
                        </td>
                    </tr>
                </tbody>
        
                <tfoot style="display:table-footer-group;">
                    <tr>
                        <td colspan="2" align="center">
                            <div class="bottom-line"></div>
                        </td>
                    </tr>
                </tfoot>
            </table>
        </div>
    
        <%-- <c:if test="${applyMap.userId eq loginUser.userId and 1 eq dhs}">
            <div id="backRemark"
                style="width: 100%; text-align: center; padding-top:30px;padding-bottom: 30px;">
                <input type="hidden" id='roleList' value="${roles}" /> <a id="btn"
                    href="javascript:void(0);" class="easyui-linkbutton"
                    onclick="openEvaWin()">核实</a>
            </div>
        </c:if> --%>
    </c:if>
    <c:if test="${show!='1'}">
        <div>
            暂未生成合成申请反馈表，点击查看当前任务<a href="javascript:void(0);" onclick="">工作进展情况</a>。
        </div>
    </c:if>

</body>
</html>