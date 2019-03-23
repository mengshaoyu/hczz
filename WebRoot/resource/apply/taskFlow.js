/**
 * 工作进展
 */
$(function(){
	var width=window.screen.width;
	if(width>1440){
		
		$('#taskFlowDiv').css('margin-left','150px');
	}else if(width>1366){
		
		$('#taskFlowDiv').css('margin-left','50px');
	}
	init();
});

function init(){
	$.ajax({
		type : 'get',
		url : ctxPath + '/pztask/getTaskLogs',
		dataType : 'json',
		data : {
			pzid : pzid
		},
		success : function(data){
			if(data.rows){
				var rows = data.rows;
				if(rows.length){
					$('ul.flow-path-c').empty();
					
					var ymd="";//定义日期变量
					
					for(var index in rows){
						var item = rows[index];
							
						//分离日期与时间
						var date=item.createDt;
						var ymd1=(typeof(date) == "undefined")?"无":date.substring(0,10);//日期
						var time=(typeof(date) == "undefined")?"无":date.substring(11,21);//时间
						var $div0=null;
						var $li = $('<li></li>');
						if(ymd!=""){//控制显示日期
							if(ymd!=ymd1){
								$div0 = $('<div class="day-time"></div>').addClass('day-time').html(ymd1);
								$li.addClass('fist-info');
								ymd=ymd1;
							}
						}else{
							ymd=ymd1;
							$div0 = $('<div class="day-time"></div>').addClass('day-time').html(ymd1);
							$li.addClass('fist-info');
						}
						
						var $div=$('<div class="task-info"></div>');
						
						var $p1=$('<p class="info-line"></p>');
						//任务状态
						var $span0 = $('<span></span>').addClass('flow-box flow-box01');
						if(index==rows.length - 1){
							$li.addClass('last-info');
						}else if(index==0){
							if(item.taskStatus!='评价核实'){
								$span0.removeClass('flow-box01');
								$span0.addClass('flow-box-resent');
								$p1.addClass('info-line-first');
							}else{
								$p1.addClass('info-line-end');
							}
							$li.addClass('resent');
						}
						$span0 = $span0.html(""==item.taskStatus?"&nbsp;":item.taskStatus);
						
						var $span01=$('<span></span>').addClass('flow-box-unit');
						var $span1 = $('<span></span>').addClass('flow-box flow-box-username').html(item.username);
						
						//设置人员警种类型
						var $span11 = $('<span></span>').html("");
						if(null!=item.policeType&&""!=item.policeType){
							var types=item.policeType.split(",");
							for(var ii in types){
								var typeStr="";
								if(types[ii]==1024){
									typeStr="图";
								}else if(types[ii]==1025){
									typeStr="情";
								}else if(types[ii]==1026){
									typeStr="技";
								}else if(types[ii]==1027){
									typeStr="网";
								}else if(types[ii]==1028){
									typeStr="刑";
								}
								if(""!=typeStr){
									if(ii==0){
										$span11 = $('<span></span>').addClass('flow-box flow-box-type').html("");
									}
									var $span_type=$('<span></span>').addClass('police-'+types[ii]).html(typeStr)
									$span11.append($span_type);
								}
							}
						}
						var $span2 = $('<span></span>').addClass('flow-box flow-box-deptname').html(item.deptName);
						var $span3 = $('<span></span>').addClass('flow-box flow-box-phone').html((null==item.mobilePhone)?"暂无":item.mobilePhone);
						var $span4 = $('<span></span>').addClass('flow-box flow-box-time-right').html(time);
						$span01.append($span1).append($span11).append($span2).append($span3);
						$p1.append($span0).append($span01).append($span4);
						$div.append($p1);
						
						//若待办流程中有多个环节处理人员，均显示
						var desc=item.taskDesc;//用于判断待办，行是否需要辅助标题
						var flow=item.flowList;
						if(null!=flow&&flow.length>0){
							var flag=0;//标识：判断第一次出现时间不一致，给P1添加时间和后缀描述
							for(var i in flow){
								var task=flow[i];
								var $p_0=$('<p style="padding-left:30px;"></p>');
								//任务状态
								var $span_0 = $('<span></span>').addClass('flow-box flow-box01');
								$span_0 = $span_0.html("&nbsp;&nbsp;");
								$span_0.removeClass('flow-box01');
								$span_0.addClass('flow-box-resent');
								
								var $span_01=$('<span></span>').addClass('flow-box-unit');
								var $span_1 = $('<span></span>').addClass('flow-box flow-box-username').html(task.username);
								
								//设置人员警种类型
								var $span_11 = $('<span></span>').html("");
								if(null!=task.policeType&&""!=task.policeType){
									var types=task.policeType.split(",");
									for(var jj in types){
										var typeStr="";
										if(types[jj]==1024){
											typeStr="图";
										}else if(types[jj]==1025){
											typeStr="情";
										}else if(types[jj]==1026){
											typeStr="技";
										}else if(types[jj]==1027){
											typeStr="网";
										}else if(types[jj]==1028){
											typeStr="刑";
										}
										if(""!=typeStr){
											if(jj==0){
												$span_11 = $('<span></span>').addClass('flow-box flow-box-type').html("")
											}
											var $span_type=$('<span></span>').addClass('police-'+types[jj]).html(typeStr)
											$span_11.append($span_type);
										}
									}
								}
								var $span_2 = $('<span></span>').addClass('flow-box flow-box-deptname').html(task.deptName);
								var $span_3 = $('<span></span>').addClass('flow-box flow-box-phone').html((null==task.mobilePhone)?"暂无":task.mobilePhone);
								
								$span_01.append($span_1).append($span_11).append($span_2).append($span_3);
								$p_0.append($span_0).append($span_01);
								
								desc1=task.taskDesc;
								if(desc!=desc1){
									if(i==0){
										var $p_02=$('<p></p>');
										var $span_06 = $('<span></span>').addClass('flow-desc').html(desc);
										
										$p_02.append($span_06);
										$div.append($p_02);
									}
								}
								$div.append($p_0);
								
								time1=(typeof(task.createDt) == "undefined")?"无":task.createDt.substring(11,21);//时间
								if(desc!=desc1){
									var $span_4 = $('<span></span>').addClass('flow-box flow-box-time-right').html(time1);
									$p_0.append($span_4);
									
									var $p_2=$('<p></p>');
									var $span_6 = $('<span></span>').addClass('flow-desc').html(task.taskDesc);
									
									$p_2.append($span_6);
									$div.append($p_2);
								}else{
									if(i==flow.length-1){
										var $p_02=$('<p></p>');
										var $span_06 = $('<span></span>').addClass('flow-desc').html(task.taskDesc);
										
										$p_02.append($span_06);
										$div.append($p_02);
									}
								}
								desc=desc1
							}
						}else{
							var $p2=$('<p></p>');
							var $span6 = $('<span></span>').addClass('flow-desc').html(item.taskDesc);
							
							$p2.append($span6);
							$div.append($p2);
						}
						
						
						//显示日期
						if(null!=$div0){
							$li.append($div0);
						}
						$li.append($div);
						
						$li.appendTo($('ul.flow-path-c'));

					}
				}
			}
		}
	});
}