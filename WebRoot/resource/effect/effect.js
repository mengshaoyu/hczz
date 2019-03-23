/**
 * 
 */

/**
 * 页面初始化 类
 * @Author   K.
 * @DateTime 2017-11-06
 * @return   {[type]}   [description]
 */
function PageInit() {
  
  var that = this;
  var policeList = [];
  var html = 
  // 获取反馈评价
  that.getInfo = function (id) {
    var id = pzid;
    $.ajax({
      url: ctxPath + '/effect/getPjByPzid',
      type: 'GET',
      data: {pzid: id},
      success:function (data) {
        if (hb.VO.check(data)) {
          var temp = hb.VO.getBody(data);
          if (temp.TIME > 0) {
            $('#scoreTime span').text('反馈不及时');
            $('#scoreTime').addClass('bujishi');
          }else{
            $('#scoreTime span').text('反馈及时');
          }
          if (roleType == 1) {
            var n = parseInt(temp.GRADE);
            if (n == 0) {
              $('#scoreGrade .star').text('★');
              $('#scoreGrade .manyidu').text('非常不满意');
            }else if (n == 1) {
              $('#scoreGrade .star').text('★★');
              $('#scoreGrade .manyidu').text('不满意');
            }else if (n == 2) {
              $('#scoreGrade .star').text('★★★');
              $('#scoreGrade .manyidu').text('一般');
            }else if (n == 3) {
              $('#scoreGrade .star').text('★★★★');
              $('#scoreGrade .manyidu').text('满意');
            }else if (n == 4) {
              $('#scoreGrade .star').text('★★★★★');
              $('#scoreGrade .manyidu').text('非常满意');
            }
            if (temp.TIME > 0) {
              $('#scoreTime span').text('反馈不及时');
              $('#scoreTime').addClass('bujishi');
            }
          }else{
            if (temp.TIME > 0) {
              $('#scoreTime span').text('反馈不及时');
              $('#scoreTime').addClass('wid-bujishi');
            }else{
              $('#scoreTime span').text('反馈及时');
              $('#scoreTime').addClass('wid-jishi');
            }
            $('.content-score-2').hide();
            $('.content-score-1').css('width', '100%');
          }
          $('#scoreRemark').text(temp.REMARK);
          removeload();
        }
      },
      error:function (data) {
        
      }
    })
  };
  //获取是否有评定列表 并渲染列表
  that.getScoreInfo = function (id) {
    onloading();
  	var id = pzid;
    var type = roleType;
    if (type == 0) {
      if (show1 == 2) {
        $('#un-score').show();
        return false;
      }else if(show1 == 1){
        that.getScoreList("0");
      }else if(show1 == 0){
        that.getScoreList("0");
        that.getScoreList("1");
      }
    }else{
      if (show2 == 2 || show2 == 1 ) {
        that.getScoreList("0");
      }else{
        that.getScoreList("0");
        that.getScoreList("1");
      }
    }
  };
  that.getScoreList = function (type) {
    var id = pzid;
    var temp = '';
    var html = '';
    $.ajax({
      url: ctxPath + '/effect/getlist',
      type: 'GET',
      data: {pzApplyId : id,effectType : type},
      success:function (data) {
        if (hb.VO.check(data)) {
          var templist = hb.VO.getBody(data);
          var datas = templist.rows;
          var len = datas.length;
          if (len != 0) {
            for (var i = 0; i < len; i++) {
              html += "<div class=jobScroeInfo><p class=userInfo>评定人：<span class=userNAme>"+datas[i].USERNAME+"</span></p>"
                    + "<div class=jobScroe><p class=p-first>任务分值评定</p><p class=p-last-child><span class=scoreNum>"+datas[i].TOTAL+"</span><span>分</span></p></div>"
                    + "<div class=scored-content><p class=p-first>参与合成民警</p><div class=policeList>";
              if (datas[i].TYPE1 != 0) {
                temp += "<p class=scored-pl><span class=kind1024>图侦</span><span>"+datas[i].TYPE1+"%</span></p>";
              }
              if (datas[i].TYPE2 != 0) {
                temp += "<p class=scored-pl><span class=kind1025>情侦</span><span>"+datas[i].TYPE2+"%</span></p>";
              }
              if (datas[i].TYPE3 != 0) {
                temp += "<p class=scored-pl><span class=kind1026>技侦</span><span>"+datas[i].TYPE3+"%</span></p>";
              }
              if (datas[i].TYPE4 != 0) {
                temp += "<p class=scored-pl><span class=kind1027>网侦</span><span>"+datas[i].TYPE4+"%</span></p>";
              }
              if (datas[i].TYPE5 != 0) {
                temp += "<p class=scored-pl><span class=kind1028>刑侦</span><span>"+datas[i].TYPE5+"%</span></p>";
              }
            html += temp ;
            html += "</div></div></div>";
            if (type == 0) {
              $('#scored-content-list').append(html);
            }else{
              $('#final-scored').append(html);
              $('#final-scored').show();
            }
            html = '';
            temp = '';
            }
            removeload();
          }else{
            removeload();
            return false;
          }
          // console.log(data);
        }
      },
      error:function (data) {
        
      }
    })
  };
  // 获取本次合成任务 参与的警种
  that.getPoliceList = function (id) {
    var id = pzid;
    $.ajax({
      url: ctxPath + '/effect/getPoliceTypes',
      type: 'GET',
      data: {pzid: id},
      async:false,
      success:function (data) {
        if (hb.VO.check(data)) {
          policeList = hb.VO.getBody(data);
        }
      },
      error:function (data) {
        
      }
    })
    return policeList;
  };
  //根据权限及评定状态 显示对应按钮
  that.getUserBtn = function () {
    var type = roleType;
    if (type == 0) {
      if(show1 == 1 || show1 == 2){
        //小领导显示
        $('#un-score').show();
      }
    }else{
      if(show2 == 1){
        // 大领导显示
        $('#final-socre').show();
      }else if(show2 == 2){
        //大领导禁用
        $('#final-socre').show();
        $('#final-socre .bigLeader').attr('disabled', true).addClass('btnDis');
      }
    }
  };
}



/**
 * 初始化   结束
 */


/**
 * 比重评定 对话框 类
 * @Author   K.
 * @DateTime 2017-11-06
 */
function LeaderScore() {
  var that = this;
  var leaderType = '';
  var tempList = '';
  //打开对话框
  that.open = function (type) {
    leaderType = type;
    tempList = pageinit.getPoliceList();
    var len = tempList.length;
    for (var i = 0; i < len; i++) {
      var tempid = tempList[i];
      tempid = "#"+tempid;
      $(tempid).show();
      tempid = null;
    }
    $('#effectScore').dialog('open');
    $('#effectScore').closest('div.panel').css('position', 'fixed');
  };
  //关闭对话框
  that.close = function () {
    $('.effectListKind').hide();
    leaderType = '';
    tempList = '';
    $('.p-wd-last input').val('0');
    $('#totalscore').val('0');
    $('#effectScore').dialog('close');
  };
  //加法
  that.add = function () {
    var n = $('#totalscore').val();
    if (n < 10 && n >= 0) {
      $('#totalscore').val(++n);
    }
  };
  //减法
  that.sub = function () {
    var n = $('#totalscore').val();
    if (n <= 10 && n >= 1) {
      $('#totalscore').val(--n);
    }
  };
  that.checkNum = function () {
    var sum = 0;
    var flag = true;
    var m = $('#totalscore').val(); 
    if(m <= 0 || m >= 10 || m == '' || m == undefined || parseInt(m) != m){
        if (flag) {
          showMessageFixed('任务分值请输入小于10的正整数！!');
        }
        flag = false;
    }
    $('.effectListKind .p-wd-last input').each(function(index, el) {
      // var n = $(this).val();
      if (!$(this).is(':hidden')) {
        var n = $(this).val();
        if ( n <= 0 || n == '' || n == undefined || parseInt(n) != n) {
          if (flag) {
        	  showMessageFixed('比重数请输入大于0的整数!');
          }
          flag = false;
          }
        }
    });

    $('.effectListKind .p-wd-last input').each(function(index, el) {
      if (!$(this).is(':hidden')) {
        sum += parseInt($(this).val());
      }
    });
    if (sum != 100) {
      if (flag) {
    	  showMessageFixed('警种比重数之和不等于100%!');
      }
      sum = 0;
      flag = false;
    }
    return flag;
  }
  //评定保存
  that.save = function (type) {
    if (that.checkNum()) {
      $.ajax({
        url: ctxPath + '/effect/add',
        type: 'POST',
        data: {
          pzApplyId : pzid,//配侦ID
          effectType : leaderType,//评定类型 0小领导评定，1大领导评定
          total : $('#totalscore').val(),//总分
          type1 : $('#1024 .p-wd-last input').val(),//图侦
          type2 : $('#1025 .p-wd-last input').val(),//情报
          type3 : $('#1026 .p-wd-last input').val(),//技侦
          type4 : $('#1027 .p-wd-last input').val(),//网侦
          type5 : $('#1028 .p-wd-last input').val()//刑警
          // remark : xxx//评语
        },
        success:function (data) {
          if (hb.VO.check(data)) {
            alertMsgFunc('评价成功!', 'info',function (){
              if(true){
              that.close();
              window.location.reload();
              }
            });
          };
        },
        error:function (data) {
          
        }
      })
    }
  };
}


//页面初始化 实例
var pageinit = new PageInit();
//领导评定 实例
var leaderscore = new LeaderScore();



$(function(){
  //页面数据加载
  pageinit.getInfo(); //反馈信息
  pageinit.getScoreInfo();//评定列表
  pageinit.getUserBtn();//评定按钮

  //小领导 评定 按钮
  $('.smallLeader').click(function(event) {
    leaderscore.open('0');
  });
  //大领导评价 按钮
  $('.bigLeader').click(function(event) {
	  leaderscore.open('1');
  });
  //对话框关闭
  $('#effectButtonCanel').click(function(event) {
    leaderscore.close();
  }); 
  //保存评价
  $('#effectButtonSave').click(function(event) {
    leaderscore.save();
  });
  
  $('#numAdd').click(function(event) {
    leaderscore.add();
  });
  
  $('#numSub').click(function(event) {
    leaderscore.sub();
  });

  
});
