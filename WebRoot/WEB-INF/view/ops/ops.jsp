<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<%@ include file="../index/public-header.jsp"%>
<c:set var="userId" value="${sessionScope.loginUser.userId}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>意见反馈 - 合成作战平台</title>
<link rel="stylesheet" type="text/css"
	href="${ctx}/resource/style/sop/ops.css" />
<script type="text/javascript" src="${ctx}/resource/ops/ops.js"></script>
<script>
  var userRole = "${userRole}";
  var userId = "${userId}";
</script>
</head>
<body>
	<!-- <h1>ops.jsp</h1> -->
	<div class="ops">
		<div class="content">
			<h2>意见反馈</h2>
			<div class="ops-content">
				<h4>发表意见反馈</h4>
				<div class="ops-content-area">
					<div class="ops-content-info">
						<textarea placeholder="内容小于500字" class="ops-content-textarea" name="" id="ops-content-textarea" ></textarea>
					</div>
					<!-- <div class="ops-content-sub"> -->
						<p class="ops-content-sub" id="ops-content-sub">发送反馈意见</p>
					<!-- </div> -->
				</div>
			</div>
			<h4 class="clear">历史意见反馈</h4>
			<div class="ops-response-list">
      <div>


        <!-- 运维意见 未回复-->
        <!-- <div class="ops-response"> -->
          <!-- 运维意见内容 -->
<!--           <div class="ops-opinion">
            <div class="opinion-content">
              <p class="opinion-user">
                <span>焦良(374819)</span><span>青岛市公安局市北分局</span>
              </p>
              <p class="opinion-info">这是用户的这是用户的意见这是</p>
            </div>
            <div class="opinion-state-1">
              <div class="opinion-state-content">
                <p class="opinion-state-info">等待回复中...</p>
                <p class="opinion-create">2017-11-27 17:23:45</p>
              </div>
              <div class="opinion-state-icon">
                <p class="state-icon"></p>
              </div>
            </div>
          </div> -->
          <!-- 运维意见 等回复 默认隐藏 -->
<!--           <div class="ops-response-border">
            <div class="ops-response-content">
              <div class="response-info">
                <textarea name="" id="" ></textarea>
              </div>
              <div class="response-buttons">
                <p>
                  <button class="response-send">发送</button>
                  <button class="canel">收起</button>
                </p>
              </div>
            </div>
          </div>
        </div> -->
        

        <!-- 运维意见 已回复 -->
        <!-- <div class="ops-response"> -->
          <!-- 运维意见内容 -->
<!--           <div class="ops-opinion">
            <div class="opinion-content">
              <p class="opinion-user">
                <span>焦良(374819)</span><span>青岛市公安局市北分局</span>
              </p>
              <p class="opinion-info">这是用户的意见</p>
            </div>
            <div class="opinion-state-2">
              <div class="opinion-state-content">
                <p class="opinion-state-info">已回复</p>
                <p class="opinion-update">2017-11-27 17:23:45</p>
              </div>
            </div>
          </div> -->
          <!-- 运维意见 已回复内容 -->
 <!--          <div class="ops-responsed-border">
            <div class="ops-responsed-content">
              <div class="responsed-info">
                <p id="" >已回复内容已回复内容已回复内容已回复内容已回复内容已回复内容已回复内容</p>
              </div>
            </div>
          </div>
        </div> -->
      </div>
			</div>
		</div>
	</div>
</body>
</html>
