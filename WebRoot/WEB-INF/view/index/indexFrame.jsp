<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8">
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<link rel="stylesheet" type="text/css"
    href="${ctx}/resource/style/sop/indexFrame.css" />
<script type="text/javascript"
    src="${ctx}/resource/common/echarts/dist/echarts.js"></script>
<script type="text/javascript" src="${ctx}/resource/index/indexFrame.js"></script>
<script type="text/javascript">
	var flag = '${flag}';
</script>
<title>主页 - 合成作战平台</title>
</head>
<body>
    <div class="container">
        <div class="workZone">
            <div class="workList">
                <ul class="workList1">
                    <li class="workList1-1">
                        <div><i><img src="${ctx}/resource/image/index/fqsq.png" /></i><span>发起合成申请</span></div>
                    </li>
                    <li class="workList1-2" data-id="HCZZ_1007">
                        <div><i><img src="${ctx}/resource/image/index/dbrw.png" /></i><span>待办任务</span><span class="wl1">()</span></div>
                    </li>
                    <!-- data-id不定 -->
                    <li class="workList1-3" data-id="HCZZ_1008">
                        <div><i><img src="${ctx}/resource/image/index/receivePj.png" /></i><span>评价核实</span><span class="wl2">()</span></div>
                    </li>
                </ul>
                <ul class="workList2">
                    <li class="workList1-1">
                        <div><i><img src="${ctx}/resource/image/index/fqsq.png" /></i><span>发起合成申请</span></div>
                    </li>
                    <li class="workList1-2" data-id="null">
                        <div><i><img src="${ctx}/resource/image/index/dbrw.png" /></i><span>待办任务</span><span class="wl1">()</span></div>
                    </li>
                    <li class="workList1-3" data-id="end">
                        <div><i><img src="${ctx}/resource/image/index/receivePj.png" /></i><span>收到评价</span><span class="wl2">()</span></div>
                    </li>
                </ul>
            </div>
            <div class="workTotal">
                <ul class="workTotal1">
                    <li>
                        <div>
                            <p class="workTotal-title">我的工作</p>
                            <p class="workTotal-content">本月合成申请</p>
                            <p class="workTotal-number"><!-- <a href="#">查看</a> --><span class="wk1"></span><i>个</i></p>
                        </div>
                    </li>
                    <li class="no-border">
                        <div>
                            <p class="workTotal-title">本单位</p>
                            <p class="workTotal-content">本月共合成申请</p>
                            <p class="workTotal-number"><!-- <a href="#">查看</a> --><span class="wk2"></span><i>次</i></p>
                        </div>
                    </li>
                </ul>
                <ul class="workTotal2">
                    <li>
                        <div>
                            <p class="workTotal-title">我的工作</p>
                            <p class="workTotal-content">本月反馈线索</p>
                            <p class="workTotal-number"><!-- <a href="#">查看</a> --><span class="wk1"></span><i>个</i></p>
                        </div>
                    </li>
                    <li class="no-border">
                        <div>
                            <p  class="workTotal-title">合成中心</p>
                            <p  class="workTotal-content">本月共计反馈线索</p>
                            <p class="workTotal-number"><span class="wk2"></span><i>个</i></p>
                        </div>
                    </li>
                </ul>
            </div>
        </div>

        <div class="jqTotal">
            <div class="jqfb">
                <div class="jqfb-title">
                    <h1>最近一月各单位配侦提报情况</h1>
                    <!-- <a>查看更多</a> -->
                </div>
                <div class="jqfbChart">
                    <!-- <img src="${ctx}/resource/image/index/map.jpg"> -->
                </div>
            </div>
            <div class="chartList">
    			<div id="trendSort" class="chart"></div>
    			<div id="compare" class="chart"></div>
            </div>
        </div>
    </div>
</body>
</html>
