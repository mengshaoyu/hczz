package com.shield.hczz.index.service.impl;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shield.frame.base.domain.User;
import com.shield.frame.utils.MD5Util;
import com.shield.hczz.base.domain.FlowWait;
import com.shield.hczz.base.persistence.ActivitiMapper;
import com.shield.hczz.base.persistence.PzResultMapper;
import com.shield.hczz.common.SopConstants;
import com.shield.hczz.index.dao.UserModel;
import com.shield.hczz.index.mapper.IndexMapper;
import com.shield.hczz.index.service.IndexService;

@Service
public class IndexServiceImpl implements IndexService{
    
    @Autowired
    private IndexMapper indexMapper;
    @Autowired
    private ActivitiMapper activitiMapper;
    @Autowired
    private PzResultMapper pzResultMapper;

    @Override
    public int update(UserModel userModel,User user,String flag) {
    	try {
	    	//用户信息修改
	    	userModel.setUserId(user.getUserId().intValue());
	    	userModel.setUpdateBy(user.getUserNo());
	        userModel.setUpdateDt(new Date());
	    	//用户密码修改
	    	if(flag != null){
	    		//密码加密
	            userModel.setLoginPwd(MD5Util.getMd5(userModel.getLoginPwd()));
	    	}
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return indexMapper.update(userModel);
    }

    @Override
    public UserModel select(UserModel userModel) {
        UserModel resultModel = indexMapper.select(userModel);
        //判断男女
        if(null!=resultModel){
            resultModel.setGender(resultModel.getGender().equals("1")?"男":"女");
        }
        return resultModel;
    }

    @Override
    public Integer[] getUnFinish(String userId) {
        Integer[] arr = new Integer[]{indexMapper.selectUnFinish(userId)};
        return arr;
    }

    @Override
    public Map<String, Integer> decide(User user) {
        //根据用户id查询用户roleName
        List<Map<String,String>> list = indexMapper.decide(user.getUserId().intValue());
        Map<String,Integer> resultMap = new HashMap<String, Integer>();
        Integer flag = null;
        //判空操作，为空默认民警
        if(list.size() ==0 || list == null){
            resultMap.put("flag", 2);
        }
        for (int i = 0; i < list.size(); i++) {
            //判断权限
            String roleId = String.valueOf(list.get(i).get("ROLE_ID"));
            if(roleId.equals(SopConstants.ROLE_HCZXMJ) || roleId.equals(SopConstants.ROLE_ZBZ)){
                flag = 1;
            }else{
                if(flag == null){
                    flag = 2;
                }
            }
            resultMap.put("flag",flag);
        }
        return resultMap;
    }

    @Override
    public Integer[] waitingTask(Map<String, Object> map) {
        //待办任务默认查全部，前台传值null
        if(map.containsKey("taskKey1")){
            map.put("taskKey1", "");
        }
        List<Integer> resultList = indexMapper.waitingTask(map);
        Integer[] num = new Integer[]{resultList.get(0),resultList.get(1)};
        return num;
    }

    @Override
    public List<Integer> selectUserStart(User user,String flag) {
        Map<String,Object> map = new HashMap<String, Object>();
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
            int year = calendar.get(Calendar.YEAR); 
            int month = calendar.get(Calendar.MONTH)+1; 
            int day = calendar.get(Calendar.DATE); 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try {
            calendar.add(Calendar.MONTH, 0);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            //系统当前日期
            Date newDate = sdf.parse(year+"/"+month+"/"+(day+1));
            //当前月的月初日
            Date oldDate = sdf.parse(sdf.format(calendar.getTime()));
            map.put("newDate", newDate);
            map.put("oldDate", oldDate);
            map.put("userId", user.getUserId());
            map.put("clueType", "2");
        } catch (Exception e) {
            e.printStackTrace();
        }  
        //判断flag类型，执行不同的查询
        List<Integer> resultList = new ArrayList<Integer>();
        if(flag.equals("1")){
            //根据用户Id查反馈，出重取线索id，统计反馈线索数
            Integer num3 = indexMapper.selectClue(map);
            map.remove("userId");
            map.put("deptId", user.getDeptId());
            //根据部门id查反馈，出重取线索id，统计部门反馈线索数
            Integer num4 = indexMapper.selectClue(map);
            resultList.add(num3);
            resultList.add(num4);
        }
        if(flag.equals("2")){
            map.put("taskKey1",SopConstants.FLOW_SQPZ);//待提报
            map.put("taskKey2",SopConstants.FLOW_FGLDSP);//分管领导审批
            map.put("taskKey3",SopConstants.FLOW_TZBC);//退帧补充
            //用户Id查询当前任务中从待分管领导审批状态以后的
            Integer num1 = indexMapper.selectUserStart(map);
            num1 += indexMapper.selectEnd(map);
            map.put("deptId", user.getDeptId());
            //用户deptId查询当前任务中从待分管领导审批状态以后的
            Integer num2 = indexMapper.selectDeptStart(map);
            map.remove("userId");
            num2 += indexMapper.selectEnd(map);
            resultList.add(num1);
            resultList.add(num2);
        }
        return resultList;
    }

    @Override
    public List<Map<String, Object>> pzfk(Map<String, Object> map) {
        //配侦ID查询返回taskKey、procId
        List<FlowWait> list = activitiMapper.getFlowInfo(map);
        if(list.size() == 0 || list == null){
        	List<Map<String, Object>> resultMap = new ArrayList<Map<String, Object>>();
        	return resultMap;
        }
        //配侦taskKey、procId查询task表获取当前流程
        List<Map<String, Object>> resultMap = indexMapper.selectFk(list.get(0).getProcId(),list.get(0).getTaskKey(),map.get("userId").toString());
        return resultMap;
    }
    
    @Override
    public Map<String, Object> clueInfo(String pzid) {
        //pzId查当前线索
        Map<String, Object> map = indexMapper.clueInfo(pzid);
        return map;
    }

    @Override
    public Integer contrast(UserModel model,String pwd,User user) {
        Integer num = null;
        try {
            UserModel m = new UserModel();
                m.setUserId(user.getUserId().intValue());
                m.setLoginPwd(MD5Util.getMd5(pwd));
            //用户id、原密码查询是否存在，返回1表示密码输入正确，返回2表示密码不正确
            num = indexMapper.selectCount(m);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return num;
    }

    @Override
    public Map<String,String> selectPzZhuBan(String pzId) {
        Map<String,String> map = indexMapper.selectPzZhuBan(pzId);
        return map;
    }

    @Override
    public Integer remove(String resultId,User user) {
        Map<String,String> map = new HashMap<String,String>();
            map.put("resultId", resultId);
            map.put("userId", String.valueOf(user.getUserId()));
        //根据用户id、反馈id删除反馈记录
        Integer num = pzResultMapper.remove(map);
        return num;
    }

    @Override
    public Map<String, Object> selectResultById(String resultId) {
        Map<String, Object> map = pzResultMapper.selectByResultId(resultId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        map.put("RESULT_DATE", sdf.format(map.get("RESULT_DATE")));
        //判断是否有附件
        if(String.valueOf(map.get("IS_FILES")).equals("1")){
            List<Map<String,String>> attMap = indexMapper.selectAtt(String.valueOf(map.get("RESULT_ID")));
            map.put("attMap", attMap);
        }
        return map;
    }

    @Override
    public Integer addClue(Map<String, String> clueMap) {
        Integer num = indexMapper.addClue(clueMap);
        return num;
    }

}
