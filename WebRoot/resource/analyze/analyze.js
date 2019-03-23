$(function() {
	initHcbztjPie();
	initHcbztjTable();
	initPjtjTable();
});
function initPjtjTable(){
	var tableData = [['','最近1周','最近1个月','最近6个月','6个月前','总计'],
	                 ['非常满意','2','3','4','5','6'],
	                 ['满意','2','3','4','5','6'],
	                 ['一般','2','3','4','5','6'],
	                 ['不满意','2','3','4','5','6'],
	                 ['非常不满意','2','3','4','5','6'],
	                 ['总计','2','3','4','5','6']];
	
	assemblyTable(tableData, "pjtj-table-content");
}
function initHcbztjTable(){
	var tableData = [['合成警种','0~20%','20~40%','40~60%','60~80%','80~100%','参与合成次数'],
	                 ['图侦','2','3','4','5','6','7'],
	                 ['情报','2','3','4','5','6','7'],
	                 ['网帧','2','3','4','5','6','7'],
	                 ['技侦','2','3','4','5','6','7'],
	                 ['刑侦','2','3','4','5','6','7']];
	
	assemblyTable(tableData, "hcbztj-table-content");
}
function assemblyTable(tableData,tableid){
	var $table = $("#"+tableid);
	$.each(tableData, function(i, item){
		var $tr = $("<tr></tr>");
		if(i == 0){
			$tr.addClass("table-th");
		}else{
			$tr.addClass("table-td");
		}
		if(i % 2 == 0){
			$tr.addClass("tr-background");
		}
		$.each(item, function(j, chitem){
			var $td = $("<td></td>");
			if(i == 0 || j == 0 || j == item.length - 1){
				$td.addClass("td-bold");
			}
			$td.text(chitem);
			$tr.append($td);
		});
		$table.append($tr);
	});
}
function initHcbztjPie() {
	var hcbztjPieOption = {
		title : {
			text : '合成比重统计',
			x : 'center',
			y : 20,
			textStyle:{
		        color:'#444',
		        fontStyle:'normal',
		        fontFamily:'Microsoft yahei',
		        fontSize:14
		    }
		},
		tooltip : {
			trigger : 'item',
			formatter : "{a} <br/>{b} : {c} ({d}%)"
		},
		legend : {
			orient : 'vertical',
			x : 'right',
			y : 'top',
			data : [ '图侦', '网帧', '情报', '技侦', '刑侦' ]
		},
		calculable : false,
		animation : false,
		color : ['#FC7257','#717ACB','#50CEFF','#8DD06B','#FFB760'],
		series : [ {
			name : '合成比重',
			type : 'pie',
			radius : [30, 110],
			center : [ '50%', '50%' ],
			data : [ {
				value : 335,
				name : '刑侦'
			}, {
				value : 310,
				name : '网帧'
			}, {
				value : 234,
				name : '情报'
			}, {
				value : 135,
				name : '技侦'
			}, {
				value : 1548,
				name : '图侦'
			} ]
		} ]
	};
	var hcbztjPie = echarts.init(document.getElementById('hcbztj-pie'));
	hcbztjPie.setOption(hcbztjPieOption);
}