<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>合成作战-能效评定</title>
<script type="text/javascript" src="${ctx}/resource/effect/effect.js"></script>
<link rel="stylesheet" href="${ctx}/resource/style/sop/effect.css">
<script type="text/javascript">
  var pzid = '${pzid}';
  var show1 = '${show1}';
  var show2 = '${show2}';
  var roleType = '${roleType}';
</script>
</head>
<body>  
  <!-- 等待加载 遮罩层 -->
<!--   <div id="loading-mask"
    style="position: absolute; top: 0px; left: 0px; width: 100%; height: 100%; background: #F4F4F4; z-index: 20000">
    <div id="pageloading"
      style="width: 200px; height: 15px; position: absolute; top: 50%; left: 50%; margin: -20px 0px 0px -120px; text-align: center; border: 2px solid #ccc; font-size: 14px; padding: 10px; background: white;">
      <img style="vertical-align: middle; float: left;"
        src="../resource/image/loading.gif">页面加载中，请稍后...
    </div>
  </div> -->
  <div class="content">
    <div class="content-in">
      <!-- 评分div-->
      <div class="content-score">
      <h3>合成能效评价</h3>
        <div class="content-score-num">
          <div class="content-score-1">
            <p class="p-first">反馈时效</p>
            <p class="p-last-child jishi" id="scoreTime"><span>及时反馈</span></p>
          </div>
          <div class="content-score-2">
            <p class="p-first">合成效能</p>
            <p class="p-last-child" id="scoreGrade"><span class="star"></span><span class="manyidu">满意</span></p>
          </div>
        </div>
        <div class="content-score-3">
          <p class="p-first">核实情况</p>
          <p id="scoreRemark">Lorem ipsum darchitecto</p>
        </div>
      </div>
     
      <div class="score"> 
        <h3>合成比重评定</h3>

        <!-- 能效已评价 -->
        <div class="scored">
          <div class="scored-content-list" id="scored-content-list">
            <!-- 一条评价记录 -->
  <!--           <div class="jobScroeInfo">
              <p class="userInfo">评定人：<span class="userNAme">叶飞</span></p>
              <div class="jobScroe">
                <p class="p-first">任务分值评定</p>
                <p class="p-last-child"><span class="scoreNum">2</span><span>分</span></p>
              </div>
              <div class="scored-content">
                <p class="p-first">参与合成民警</p>
                <div class="policeList">
                  <p class="scored-pl"><span class="kind1025">情侦</span><span>20%</span></p>
                  <p class="scored-pl"><span class="kind1026">技侦</span><span>20%</span></p>
                  <p class="scored-pl"><span class="kind1027">网侦</span><span>20%</span></p>
                  <p class="scored-pl"><span class="kind1024">图侦</span><span>20%</span></p>
                  <p class="scored-pl"><span class="kind1028">刑侦</span><span>20%</span></p>
                </div>
              </div>
            </div>
            </div> -->

          </div>
        </div>

        <!-- 能效未评价 -->
        <div class="un-score" id="un-score">
          <p class="p-first">合成比重评定</p>
          <p class="p-last-child"><span style="font-size: 20px;color:#999999">尚未评定</span><button class="smallLeader">立即评定</button></p>
        </div>
      </div>
          <!--最终评价 -->
        <div class="final-scored" id="final-scored">
          <h3>最终比重评定</h3>
          <!-- <div class="jobScroeInfo">
            <p class="userInfo">评定人：<span class="userNAme">叶飞</span></p>
            <div class="jobScroe">
              <p class="p-first">任务分值评定</p>
              <p class="p-last-child"><span class="scoreNum">2</span><span>分</span></p>
            </div>
            <div class="scored-content">
              <p class="p-first">参与合成民警</p>
              <div class="policeList">
                <p class="scored-pl"><span class="kind1025">情侦</span><span>20%</span></p>
                <p class="scored-pl"><span class="kind1026">技侦</span><span>20%</span></p>
                <p class="scored-pl"><span class="kind1027">网侦</span><span>20%</span></p>
                <p class="scored-pl"><span class="kind1024">图侦</span><span>20%</span></p>
                <p class="scored-pl"><span class="kind1028">刑侦</span><span>20%</span></p>
              </div>
            </div>
          </div> -->
        </div>
      </div>

      <!-- 最终评定按钮 -->
      <div class="final-scored" id="final-socre">
        <p ><button class="bigLeader">最终评定</button></p>
      </div>
    </div>
  </div>


  <!-- 比重评定对话框 -->
  <div id="effectScore" class="easyui-dialog"
  style="width: 500px; height: 470px; padding: 10px; position: fixed;top:50px;"
  data-options="
              top:10,
              draggable:false,
              modal:true,
              closed:true,
              closable: false,
              title:'合成比重评定'">
    <div class="effectScoreDiv">
      <div class="effectScoreAdd">
        <p class="p-wd-first">
          <span>任务分值评定(分)</span>
        </p>
        <p class="p-wd-last">
          <span class="addSub" id="numSub">-</span>
          <input id="totalscore" type="number" value="0">
          <span class="addSub" id="numAdd">+</span>
        </p>
      </div>
      <div class="effecPoliceKind">
        <div class="effectListTitle">
          <p class="p-wd-first">参与合成警种</p>
          <p class="p-wd-last">比重</p>
        </div>
        <div class="effectListKind" id="1025">
          <p class="p-wd-first"><span class="kind1025">情侦</span></p>
          <p class="p-wd-last"><input type="number" value="0" ><span>%</span></p>
        </div>
        <div class="effectListKind" id="1026">
          <p class="p-wd-first"><span class="kind1026">技侦</span></p>
          <p class="p-wd-last"><input type="number" value="0" ><span>%</span></p>
        </div>
        <div class="effectListKind" id="1027">
          <p class="p-wd-first"><span class="kind1027">网侦</span></p>
          <p class="p-wd-last"><input type="number" value="0" ><span>%</span></p>
        </div>
        <div class="effectListKind" id="1024">
          <p class="p-wd-first"><span class="kind1024">图侦</span></p>
          <p class="p-wd-last"><input type="number" value="0" ><span>%</span></p>
        </div>
        <div class="effectListKind" id="1028">
          <p class="p-wd-first"><span class="kind1028">刑侦</span></p>
          <p class="p-wd-last"><input type="number" value="0" ><span>%</span></p>
        </div>
      </div>
    </div>
    <div class="effectButton">
      <p><button id="effectButtonSave">提交</button><button id="effectButtonCanel">取消</button></p>
    </div>
  </div>
</body>
</html>
