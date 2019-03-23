/*
* @Author: K.
* @Date:   2019-02-11 18:53:11
*/

var refreshTime ={
	time1:2000 // 2秒
}


//获取全部任务
function uerInit() {
   $.ajax({
        url: ctxPath + '/index/decide',
        type: 'GET',
        async:false,
        success:function (data) {
            //console.log(data);
        	if (data[0] == 1) {
        		$('.workTotal2').css('display', 'block');
        		$('.workList2').css('display', 'block');
        		$('.workTotal1').css('display', 'none');
        		$('.workList1').css('display', 'none');
                getWrokTodo('null','end');
        	}
            if (data[0] == 2) {
                $('.workTotal1').css('display', 'block');
                $('.workList1').css('display', 'block');
                $('.workTotal2').css('display', 'none');
                $('.workList2').css('display', 'none');
                getWrokTodo('HCZZ_1007','HCZZ_1008');
            }
            getWorkAll(data[0]);
        },
        error:function () {
            //console.log(data);
        }
    });
}

function getWorkAll(arg) {
       $.ajax({
        url: ctxPath + '/index/selectStart?'+new Date().getTime(),
        type: 'GET',
        data:{
            flag:arg
        },
        success:function (data) {
            $('.wk1').text(data[0]);
            $('.wk2').text(data[1]);
        },
        error:function () {
        }
    });
}

function getWrokTodo(arg1,arg2) {
       $.ajax({
        url: ctxPath + '/index/waitingTask?'+new Date().getTime(),
        type: 'GET',
        data:{
            taskKey1:arg1,
            taskKey2:arg2
        },
        success:function (data) {
            $('.wl1').text('('+data[0]+')');
            $('.wl2').text('('+data[1]+')');
        },
        error:function () {
        }
    });
}



var sortOption,totalOption,chartSort,chartTotal,
compareOption,chartCompare;
sortOption = {
        title : {
            top:20,
            left:20,
            text: '配侦统计'
        },
    tooltip : {
        trigger: 'axis'
    },
    color:['#ff956e','#6ee2ff'],
    legend: {
    	bottom:20,
        data:['发起申请','配侦研判']
    },
    grid: {
        top:'20%',
        left: '10%',
        right: '10%',
        bottom: '20%'
    },
    calculable : true,
    xAxis : [
        {
            type : 'category',
            boundaryGap : false,
            data : ['四月','五月','六月','七月','八月','九月']
        }
    ],
    yAxis : [
        {
            type : 'value',
            axisLabel : {
                formatter: '{value} 件'
            }
        }
    ],
    series : [
        {
            name:'发起申请',
            type:'line',
            data:[11, 6, 15, 7, 10, 19],
            symbolSize:10,
            itemStyle : {  
                normal : {  
                    lineStyle:{  
                        color:'#ff956e', 
                        width:3
                    }  
                }  
            }
        },
        {
            name:'配侦研判',
            type:'line',
            data:[3, 10, 2, 5,14,3],
            symbolSize:10,
            itemStyle : {  
                normal : {
                    lineStyle:{  
                        color:'#6ee2ff',  
                        width:3
                    }  
                }  
            }
        }
    ]
};
totalOption = {
    tooltip : {
        trigger: 'axis'
    },
    legend: {
        data:['线索数量']
    },
    calculable : true,
    xAxis : [
        {
            type : 'category',
            boundaryGap : false,
            data : ['四月','五月','六月','七月']
        }
    ],
    yAxis : [
        {
            type : 'value',
            axisLabel : {
                formatter: '{value} 个'
            }
        }
    ],
    series : [
        {
            name:'线索数量',
            type:'line',
            data:[13, 16, 18, 15]
        }
    ]
};
compareOption = {
        title : {
            top:20,
            left:20,
            text: '不同类型线索反馈情况'
        },
        tooltip : {
            trigger: 'axis'
        },
        polar : [
           {
        	   name:{
                   show: true,
                   formatter: null,
                   color: '#4D4D4D'
               },
               indicator : [
                   { text: '图侦', max: 6000},
                   { text: '刑侦', max: 16000},
                   { text: '国保', max: 30000},
                   { text: '情报', max: 38000},
                   { text: '其他', max: 52000}
                ]
            }
        ],
        calculable : true,
        series : [
            {
                name: '',
                type: 'radar',
                symbolSize:10,
                data : [
                    {

                        value : [4300, 10000, 28000, 35000, 35000],
                        name : '数量',
                        itemStyle: {
                            normal: {
                                color: '#3DC47D'
                            }
                        },
                        lineStyle: {
                        normal: {
                            width:3
                        }
                    }
                    }
                ]
            }
        ]
    };

//加载后调用
$(function(){
	
	
	
    //初始化
    uerInit();
    //动态charts
    setChartWid();
    //警情态势分类统计
    chartSort = echarts.init(document.getElementById('trendSort'));
    chartSort.setOption(sortOption);
    //同环比比较
    chartCompare = echarts.init(document.getElementById('compare'));
    chartCompare.setOption(compareOption);

    //点击任务模块跳转到对应页面
    $('.workList>ul').on('click', 'li', function(event) {
        // v1.0.3 任务模块跳转
        if ($(this).index() == 0) {
            parent.refreshFrame();
            $('.top-nav-active', parent.document).removeClass('top-nav-active');
            $('#1002', parent.document).addClass('top-nav-active');
            $('#100201', parent.document).click();
        }else{
            parent.refreshFrame();
            $('.top-nav-active', parent.document).removeClass('top-nav-active');
            $('#1002', parent.document).addClass('top-nav-active');
            var src = ctxPath + '/pztask/init?isdbsx=' + $(this).attr('data-id');
            $('#contentFrame', parent.document).attr('src', src);
        }
    });
});

function setChartWid() {
    var hei = $(window).height() - 200;
    var wid = $(window).width();
    $('.jqfb').height(hei);
    $('.chartList').height(hei);
    $('.jqfbChart').height(hei - 68)
    $('#trendSort').height(hei - 300);
}



$(window).resize(function () {
    setChartWid();
    chartCompare.resize();
    chartSort.resize();
})


var testJson = {
    "list": [{
        "name": "阜新路派出所",
        "num": 2200
    }, {
        "name": "大港路派出所",
        "num": 1900
    }, {
        "name": "洪山坡派出所",
        "num": 1870
    }, {
        "name": "延安路派出所",
        "num": 1500
    }, {
        "name": "镇江路派出所",
        "num": 1000
    }, {
        "name": "敦化路派出所",
        "num": 800
    }, {
        "name": "四方派出所",
        "num": 700
    }, {
        "name": "水清沟派出所",
        "num": 600
    }, {
        "name": "小港派出所",
        "num": 500
    }, {
        "name": "台东派出所",
        "num": 100
    }]
}

function TestJson() {
    var data = testJson.list;
    var len = data.length;
    for (var i = 0; i < len; i++) {
        if (i <= 4) {
            $('.top10-1 tr:eq('+i+') td:first-child span').text(data[i].name);
            $('.top10-1 tr:eq('+i+') td:last-child').text(data[i].num);
        }else{
            $('.top10-2 tr:eq('+(i-5)+') td:first-child span').text(data[i].name);
            $('.top10-2 tr:eq('+(i-5)+') td:last-child').text(data[i].num);
        }
    }
}




