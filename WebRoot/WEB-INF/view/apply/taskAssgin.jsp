<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<%@ include file="../index/public-header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>任务分发 - 合成作战平台</title>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
	<meta name="generator" content="MSHTML 8.00.7600.16588">
	<link rel="stylesheet" type="text/css" href="${ctx}/resource/style/sop/taskAssgin.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/resource/common/zTree_v3-master/css/zTreeStyle/zTreeStyle.css">
	<script type="text/javascript"
		src="${ctx}/resource/common/jquery.form.js"></script>
	<script type="text/javascript"
		src="${ctx}/resource/apply/taskAssgin.js"></script>
	<script type="text/javascript"
		src="${ctx}/resource/recordsmng/common/configService.js"></script>

<script type="text/javascript"
	src="${ctx}/resource/common/zTree_v3-master/js/jquery.ztree.all.js"></script>
		<script type="text/javascript">
    var pzid='${pzid}';
    var casename = '${caseName}';
    var flowid ='${flowId}';
</script>
</head>
<body style="padding: 0px; margin: 0px; border: 0px; overflow-x: none;">
	<div class="container">
		<div id="caseTitle" class="bread-nav"></div>
		<div class="clueList">
			<table class="clueTable">
			<!-- 12 -->
				<tr>
					<td colspan="2"><input type='checkbox' id="selectAll"  style="margin-left:10px;"/>全选
					<button id="selectPolice" class="btnDis" disabled="disabled">选择民警</button>
					</td>
				</tr>
				<!-- 34 -->
<!--				<tr>
					<td><input type='checkbox'/>线索1:</td>
					<td id='22222' class='clueName'>苛夺是另苛晨国苛罪</td>
				</tr>
				<tr>
					<td></td>
					<td class="cluePolice"><p class='policeCode'><span id='111111'>乔布斯(1229)</span><i></i></p></td>
				</tr> -->
				<!-- 56 -->
			</table>
		</div>
		<div class="saveAndCanel">
			<p class="distribute">
				<button id="distributeBtn" class="btnDis" disabled="disabled">分发</button>
				<button id="distributeCanel" class='cancelBtn'>重置</button>
			</p>
		</div>
	</div>

	<!-- 任务分发 -->
	<div style="width:500px;height:620px;" class="easyui-dialog" id="missionWin" title="分发"
		data-options="closed:true,modal:true,closable: false" >
		<h4>本次任务合成警种</h4>
		<div class="missionWinKind">
			<span class="kind1025">情侦</span>
			<span class="kind1026">技侦</span>
			<span class="kind1027">网侦</span>
			<span class="kind1024">图侦</span>
			<span class="kind1028">刑侦</span>
		</div>
		<h4>本次任务的合成民警,请选择主办人</h4>
		<div class="selectList" >

		</div>
		<h4>备注(非必填,内容小于三百字)</h4>
		<div class="missionNote">
			<textarea class="missionNoteContent" placeholder="输入备注内容"></textarea>
		</div>
		<div class="missionBtn">
			<p class="missionBtns">
				<button id="missionSave">确认</button>
				<button id="missionCanel" class='cancelBtn'>取消</button>
			</p>
		</div>
	</div>

	<!-- 民警选择 -->
	<div style="width:660px; height:460px;overflow-x: hidden;" class="easyui-dialog" id="selectPoliceList" title="民警选择"
		data-options="closed:true,modal:true,closable: false">
		<div id="toolbar" style="height:40px;">
		<span style="padding-left: 15px;">警号/姓名</span>
			&nbsp;&nbsp;&nbsp;<input id="policeFind" title='警号/姓名' style="height:32px;line-height:32px;border:1px solid #ddd; text-indent:5px;" placeholder="警号 | 姓名"/>
			&nbsp;&nbsp;&nbsp;<button class="policeSearch"  onclick='search1()'>查询</button>
		</div>
		<div id="_grid"></div>
		<div class="missionBtn">
			<p class="missionBtns">
				<button id="policeSave">确认</button>
				<button id="policeCanel" class='cancelBtn'>取消</button>
			</p>
		</div>
	</div>

	<!-- 页面关闭 -->
	<div style="width:320px;height:360px;" class="easyui-dialog" id="missionWin" title="提示"
		data-options="closed:true,modal:true,closable: false" >
		<div class="selectList" style="width:460px;height: 220px;">

		</div>
		<div class="missionBtn">
			<p class="missionBtns">
				<button id="missionSave">确认</button>
				<button id="missionCanel" class='cancelBtn'>取消</button>
			</p>
		</div>
	</div>
	<!-- <div class="fenfabg"><img src="${ctx}/resource/image/taskFlow/fenfa.png" alt="" /></div> -->
	<!-- 警员数据列 -->

	<div class="easyui-tabs" style="border: 0;display:none;" >
<!-- 		<div title="个人侦办" style="border: 0;">
			数据列
			<div id="_grid"></div>
			<div style="padding: 5px; padding-bottom: 15px; text-align: center;">
				<a id="saveNext" href="javascript:void(0)" class="easyui-linkbutton"
					style="width: 80px;" onclick="addsave()">确认</a> <a id="addAssign"
					href="javascript:void(0)" class="easyui-linkbutton"
					style="width: 80px;" onclick="closeWin()">取消</a>
			</div>
		</div> -->
<%-- 		<div title="协同侦办" style="border: 0;">
			<div
				style="width: 100%; height: 380px; text-align: center; background-color: #EAEAEA;">
				<img src="${ctx}/resource/image/building.png"
					style="width: 90%; height: 90%" />
			</div>
			<div id="_gridDetail">
				线索1：XXXXXXXXXXXXXXXXXXXXXXXXXXX<select style="width:120px;margin-left:10px;"><option>张三</option><option>李四</option></select>
				<br/><br/>
				线索2：XXXXXXXXXXXXXXXXXXXXXXXXXXX<select style="width:120px;margin-left:10px;"><option>张三</option><option>李四</option></select>
				<br/><br/>
				线索3：XXXXXXXXXXXXXXXXXXXXXXXXXXX<select style="width:120px;margin-left:10px;"><option>张三</option><option>李四</option></select>
				<br/><br/>
				侦办人：<input type="checkbox" value="1"/>张三
			</div>
		</div> --%>
	</div>
</body>
</html>
