


//IE8 下不支持indexOf方法,自行封装兼容IE8
if(!Array.prototype.indexOf){
 Array.prototype.indexOf = function(val){
     var value = this;
     for(var i =0; i < value.length; i++){
        if(value[i] == val) return i;
     }
    return -1; 
 };
}



//废弃 
function addsave(){

	var uids= getSeleces();
	var uarray =uids.split(",");
	if (uarray.length > 1) {
		showMessage("请选择一条数据进行编辑！");
		return;
	}
	if (uarray.length <= 1 && (null == uarray || "" == uarray)) {
		showMessage("请选择分发人数据！");
		return;
	}
	var umain=uids;//个人
	$.ajax({
		url : ctxPath + '/pzapprove/addAssign',
		type : 'post',
		dataType : 'text',
		data : {
			'flowId' : '1005',
			'applyId':pzid,
			'actByType':umain,
			'userIds':uids
		},
		success : function(data) {
			var res = $.parseJSON(data);
			if (res.success) {
				parent.colseMissionWin();
				if(typeof(parent.hiddenButton)=="function"){
					parent.hiddenButton();
				}
				if(res.val == "-1"){
					parent.showMessage(res.msg);
					return;
				}
				parent.showMessage("已完成分发到指定人操作");
			} else {
				showMessage("分发指定人操作失败，请稍后再试");
			}
		}
	});
}



function query() {
	// 信息查询加载
	$('#_grid').datagrid(
		{
			fitColumns : false,
			rownumbers : false,
			showFooter : false,// 显示底部统计数
			pagination : false,
			pageSize : 20,
			toolbar : "#toolbar",// 工具栏
			width : 660,// 设置grid宽度
			height : 320,// 设置grid高度
			url : ctxPath + '/pzapprove/getSumlist',
			queryParams : {
				'flowId' : '1006',
				'applyId':pzid,
				'userName':$('#policeFind').val(),
				'page':0,
				'rows':20
			},
			columns : [ [
					{
						field : 'userId',
						title : 'id',
						align : 'center',
						sortable : true,
						checkbox : true
					},
					{
						field : 'userName',
						title : '用户姓名',
						width : 150,
						align : 'center',
						formatter : function(value, row, index) {
							if(null==value||""==value){
								return "";
							}
							var userno=row.userNo;
							if(null!=userno&&""!=userno){
								userno="("+userno+")";
							}else{
								userno="";
							}
							return value+userno;
						}
					},
					{
						field : 'policeType',
						title : '民警类型',
						width : 190,
						align : 'center',
						formatter : function(value, row, index) {
							if(null==value||""==value){
								return "";
							}
							var types=value.split(',');
							var typestr='';
							for(var i=0;i<types.length;i++){
								if (types[i] == '1024') {
									typestr+="<span class='policeTu'>图</span>";
								} else if (types[i] == '1025') {
									typestr+="<span class='policeQing'>情</span>";
								}else if (types[i] == '1026') {
									typestr+="<span class='policeJi'>技</span>";
								}else if (types[i] == '1027') {
									typestr+="<span class='policeWang'>网</span>";
								}else if (types[i] == '1028') {
									typestr+="<span class='policeXing'>刑</span>";
								}
/*								if(i!=(types.length-1)){
									typestr+=",";
								}*/
							}
							//console.log(typestr);
							return typestr;
						}
					},
					{
						field : 'sumIng',
						title : '进行中任务数',
						width : 120,
						align : 'center'
					},
					{
						field : 'sumAll',
						title : '任务总数',
						width : 120,
						align : 'center'
					}] ],
			onBeforeLoad : function() {
				$('div.datagrid-header-check').children().get(0).checked=false;//取消全选框选中
				$('div.datagrid-cell-check').each(function(){
					$(this).children().get(0).checked=false;
				});
			},
			onLoadSuccess : function(data) {
				if (data.total <= 0) {
					showMessage("没有查询到符合条件的数据！");
				}
				$(this).datagrid("fixRownumber");
				removeload();
			},
			onLoadError : function() {
			},
			onClickRow : function(rowIndex, rowData) {// 加载完毕后获取所有的checkbox遍历
				$("input[type='checkbox']").each(
						function(index, el) {// 如果当前的复选框不可选，则不让其选中
							if (el.disabled == true) {
								$('#_grid').datagrid('unselectRow',
										index - 1);
							}
							//设置行只能单选
							if(index==rowIndex){
								 $('#_grid').datagrid('selectRow', index);
							}else{
								$('#_grid').datagrid('unselectRow', index);
							}
						});
			}
		});
}

//取消
function closeWin(){
	parent.colseMissionWin();
}

//获取选中项
function getSeleces() {
	var ids = [];
	var rows = $('#_grid').datagrid('getSelections');
	for (var i = 0; i < rows.length; i++) {
		ids.push(rows[i].userId);
	}
	return ids.join(',');
}



/**
 * 警员选择类
 * @Author   K.
 * @DateTime 2017-10-20
 */
function SelectPoliceList() {
	var that = this;
	var arr = [];
	var kindArr = [];
	//打开警员选择框
	that.openPoliceList = function () {
		$('#selectPoliceList').dialog('open');
		query();
		// 加载部门树
		$('#policeFind').val('');
		$("#qdept").combotree('clear');
	};
	//只选取一个警员
	that.selectOnePolice = function () {
		var row = $('#_grid').datagrid('getSelections');
		if (row.length == 0) {
			showMessage('请选择一个警员!');
		}else if(row.length == 1) {
			//console.log(row);
			return row;
		}else{
			showMessage('最多只能选择一个警员!');
		}
	};
	//保存
	that.savePoliceSelect = function () {
		arr = that.selectOnePolice();
		if (arr != undefined) {
			var template = "<p class='policeCode'><span policeType="+arr[0].policeType+" id="+arr[0].userId+">"+arr[0].userName+"("+arr[0].userNo+")</span><i></i></p>";
			var templist = $('.clueTable input:checked').not('#selectAll').closest('tr').next().find("td:eq(0)");
			var len = templist.length;
			var $templist = $(templist);
			$templist.each(function(index, el) {
				$(this).find(".policeCode").remove();
				$(this).append(template);
			});
			that.close();

			//选择好警员后,清空选中线索
			$('.clueTable input').prop('checked', false);
			$('#selectPolice').addClass('btnDis');
			$('#selectPolice').prop('disabled', true);
			if ($('.policeCode').length == $('.clueName').length) {
				$('#distributeBtn').removeClass('btnDis').prop('disabled', false);
			}
		}
	};

	//取消
	that.close = function () {
		$('#selectPoliceList').dialog('close');
		$('#policeFind').val('');
		$("#qdept").combotree('clear');
		arr = [];
		kindArr = [];
	};
	that.clearArr = function () {
		arr = [];
	}

}

var selectPolice = new SelectPoliceList();
//警员选择结束



/**
 * 任务分发
 * @Author   K.
 * @DateTime 2017-10-20
 */
function Distribute() {
	var that = this;
	var arrList = [];
	var idList = [];
	var temp = {
		id:null,
		name:null,
		type:null
	};

	//分发保存
	var jsonData = {
		applyId : null,
		userId : null,
		flowId:null,
		cluelist: []
	};
	// console.log(obj1);
	var html = '';
	//打开分发Dialog
	that.openDialog = function () {
		$('.missionNoteContent').val("");
		var temp = that.getList();
		if (temp.length > 0) {
			that.getSelectPolice();
			$('#missionWin').dialog('open');
		}else{
			showMessage('未分配民警!');
		}

	};
	//获取已选择的警员
	that.getList =  function () {
		$('.policeCode span').each(function(index, el) {
			if ($('.policeCode span').length > 0) {
				var tempId = $(this).attr('id');
				var tempName = $(this).text();
				var tempType = $(this).attr('policeType');
				if (idList.indexOf(tempId) == -1) {
					temp.id = tempId;
					temp.name = tempName;
					temp.type = tempType;
					arrList.push(temp);
					idList.push(tempId);
					temp = {
							id:null,
							name:null,
							type:null
						};
				}
			}
		});

		return arrList;
	};
	//把已选择警员加载到分发页面
	that.getSelectPolice = function () {
		var templist = that.getList();
		var len = templist.length;
		// var typeList = "";
			for (var i = 0; i < len; i++) {
				html = "<p><i><input id="+templist[i].id+" type='radio' name='zbr' /></i><span class='zbrList'>"+templist[i].name+"</span>";
				
				var tmpT = templist[i].type;

				if (tmpT.indexOf('1025') != -1) {
					$('.missionWinKind span.kind1025').css('display', 'inline-block');
					html += "<span class='policeQing'>情</span>"
				}
				if (tmpT.indexOf('1026') != -1) {
					$('.missionWinKind span.kind1026').css('display', 'inline-block');
					html += "<span class='policeJi'>技</span>"
				}
				if (tmpT.indexOf('1027') != -1) {
					$('.missionWinKind span.kind1027').css('display', 'inline-block');
					html += "<span class='policeWang'>网</span>"
				}
				if (tmpT.indexOf('1024') != -1) {
					$('.missionWinKind span.kind1024').css('display', 'inline-block');
					html += "<span class='policeTu'>图</span>"
				}
				if (tmpT.indexOf('1028') != -1) {
					$('.missionWinKind span.kind1028').css('display', 'inline-block');
					html += "<span class='policeXing'>刑</span>"
				}
				
				html += "</p>";
				$('.selectList').append(html);
				html = '';
			}
		jsonData.applyId = pzid;
		jsonData.flowId = flowid;
		var $cluelist = $('.clueName');
		$cluelist.each(function(index, el) {
			var obj1 = {
				clueId: null,
				pzResultCreateBy:null
			};
			//console.log(obj1);
			obj1.clueId = $(this).attr('id');
			var $span = $(this).find('span');
			obj1.pzResultCreateBy = $span.attr('id');
			jsonData.cluelist.push(obj1);
			obj1 = {
				clueId:null,
				pzResultCreateBy:null
			};
		});
		//console.log(jsonData);
	};
	//关闭分发页
	that.close = function () {
		$('#missionWin').dialog('close');
		$('.selectList').empty();
		$('.missionNoteContent').val("");
		$('.missionWinKind span').css('display', 'none');
		html = '';
		arrList = [];
		idList = [];
		jsonData = {
			applyId : null,
			userId : null,
			flowId : null,
			cluelist: [],
			remark:null
		};

	};
	//确定分发
	that.save = function () {
		jsonData.userId = $('.selectList :checked').attr('id');
		jsonData.remark = $('.missionNoteContent').val();
		if (jsonData.remark.length > 300) {
			showMessage('备注内容不能多于300字!');
			return  false;
		}
		if(jsonData.userId == '' || jsonData.userId == undefined){
			showMessage('请选择主办人!');
		}else{
			var data = JSON.stringify(jsonData);
			//console.log(data);
			$.ajax({
				url: ctxPath + '/pzapprove/addAssign',
				type: 'POST',
				data: {jsonStr : data},
				success:function (data) {
					//console.log(data);
					if (data.success) {

						alertMsgFunc('分发成功!', 'info',function (){
						      if(true){
									that.close();
									window.close();
									$("#ddrw",window.opener.document).click();
						      }
						    });

					}

				},error:function (data) {
					if(!data.success){
						alertMsg('未分发成功!','error');
					}
				}
			});
		}

	};
	//重置分发
	that.clear = function () {
		$('.clueTable input').prop('checked', false);
		$('.clueTable input').not('#selectAll').closest('tr').next().find('.policeCode').remove();
		$('#selectPolice').addClass('btnDis').prop('disabled', true);
		$('#distributeBtn').addClass('btnDis').prop('disabled', true);
	}
}


var distribute = new Distribute();
//分发结束



/**
 * 判断是否有线索被选中
 * @Author   K.
 * @DateTime 2017-10-20
 */
function CheckInputStatus() {
	var that = this;
	//全选
	that.selectAll = function () {
		if ($('#selectAll').prop("checked")) {
			$('.clueTable input').prop('checked', true);
			$('#selectPolice').prop('disabled',false);
			$('#selectPolice').removeClass('btnDis');
		}else{
			$('.clueTable input').prop('checked', false);
			$('#selectPolice').addClass('btnDis');
			$('#selectPolice').prop('disabled', true);

		}
	},
	//单选
	that.checkStatus = function () {
		var list = $('.clueTable input:checked').not('#selectAll').length;
		var totalList = $('.clueTable input').not('#selectAll').length;
		if (list == 0) {
			$('#selectAll').prop('checked', false);
			$('#selectPolice').addClass('btnDis');
			$('#selectPolice').prop('disabled', true);
		}else if (list == totalList){
			$('#selectAll').prop('checked', true);
			$('#selectPolice').prop('disabled',false);
			$('#selectPolice').removeClass('btnDis');
		}else if (list != totalList) {
			$('#selectAll').prop('checked', false);
			$('#selectPolice').prop('disabled',false);
			$('#selectPolice').removeClass('btnDis');
		}
	}
}

var checkInput = new CheckInputStatus();


/*
	判断是否有线索被选中  结束
 */



 /**
  * 分发页面数据初始化
  * @Author   K.
  * @DateTime 2017-10-20
  * @return   {[type]}   [description]
  */
function taskAssginInit() {
	//var template = "<tr><td colspan='2' class='cluePolice' style='border:1px solid #f1f1f1;padding-left:40px;'></td></tr>";
	var html = '';
	var id = pzid;
	$.ajax({
		url: ctxPath +'/pzsp/loadClue',
		type: 'GET',
		data:{pzid:id},
		success:function (data) {
			// console.log(data);
			var data = data.clue;
			var len = data.length;
			if (len != 0) {
				for (var i = 0; i < len; i++) {
					html += "<tr><td colspan='2' style='background:#2c80e9;border:1px solid #f2f2f2;color:#FFF;'><input style='margin-left:10px;' type='checkbox' />查询内容"+(i+1)+":</td></tr>";
					html += "<tr><td colspan='2' style='border:1px solid #f1f1f1;padding-left:40px;' id="+data[i].CLUE_ID+" class='clueName'><div style='line-height:40px;'>"+data[i].CLUE_NAME+"&nbsp;&nbsp;，&nbsp;&nbsp;"+data[i].PZ_TYPE_DETAIL+"</div></td></tr>";
					html += "<tr><td style='height:20px'></td><td></td></tr>";
					//html += template;
				}
			}else{
				//console.log('未查寻到数据!');
			}
		$('#caseTitle').text(casename);
		$('.clueTable').append(html);
		html = '';
		},
		error:function (data) {
			//console.log(data);
		}
	});
};

//加载摄像机树
	function getBeinglongTree() {
		// 部门ztree树
		var pid = '';
		var setting = {
				async : {
					enable : true,
					url : getURL,
					dataFilter : filter
				},
				callback : {
					onClick : onClick,
					beforeClick : beforeClick,
					beforeExpand : beforeExpand
				}
		};
		function getURL() {
			var url = ctxPath + "/pzapprove/getDeptTree?fid=" + pid
			return url
		}
		function onClick() {
			// $("#panelDiv").hide()
			// $("#haschild").show()
		}
		function beforeClick(treeId, treeNode) {
			$("#belongingId").val(treeNode.id)
			$("#belonging").val(treeNode.name)
		}
		function beforeExpand(treeId, treeNode) {
			pid = treeNode.id
		}
		function filter(treeId, parentNode, childNodes) {

			 if (childNodes.code!=0) return null;

			for (var i = 0, l = childNodes.length; i < l; i++) {
				childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
			}
			return childNodes;
		}
		$.fn.zTree.init($("#belongContent"), setting);
	}






$(function() {
	//线索初始化
	taskAssginInit();
	//民警选择
	$('#selectPolice').on('click', function(event) {
		// event.preventDefault();
		selectPolice.openPoliceList();
	});

	$("#policeSave").on('click', function(event) {
		// event.preventDefault();
		selectPolice.savePoliceSelect();
	});

	$("#policeCanel").on('click', function(event) {
		// event.preventDefault();
		selectPolice.close();
	});
	//清除选中警员
	$(".clueTable").on('click', 'i', function(event) {
		$(this).closest('p').remove();
		$('#distributeBtn').addClass('btnDis').prop('disabled', true);
	});

	//分发
	$('#distributeBtn').on('click', function(event) {
		// event.preventDefault();
		distribute.openDialog();
	});

	$("#missionSave").on('click', function(event) {
		// event.preventDefault();
		distribute.save();
	});

	$("#missionCanel").on('click', function(event) {
		// event.preventDefault();
		distribute.close();
	});

	//input判断
	$('#selectAll').change(function () {
		checkInput.selectAll();
	})

	$('.clueTable').on('change', 'input', function() {
		checkInput.checkStatus();
	});

	//页面清空
	$('#distributeCanel').on('click', function() {
		distribute.clear();
	});


});
//警员查询
function search1() {
	 $('#_grid').datagrid({ queryParams: {
		 	'flowId' : '1006',
			'applyId':pzid,
			'userName':$('#policeFind').val(),
			'page':0,
			'rows':20}
	 			});   //点击搜索
	 //console.log($("#qdept").combotree('getValue'));
}
//监听离开页面，隐藏父页面
$(window).unload(function(){
	//alert(window.parent==window);
	//若打开审批界面的是详情界面，则调显示审批按钮的方法，重新加载按钮
	//console.log(typeof window.opener.showButton);
	if(undefined!=typeof(window.opener)&&'undefined'!=typeof(window.opener)&&
			undefined!=typeof(window.opener.showButton)&&'undefined'!=typeof(window.opener.showButton)){
		window.opener.showButton(pzid);
	}
});

