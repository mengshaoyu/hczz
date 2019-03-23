package com.shield.hczz.pzfk.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shield.frame.base.domain.Attach;
import com.shield.frame.base.domain.User;
import com.shield.frame.base.persistence.AttachMapper;
import com.shield.frame.base.persistence.UserMapper;
import com.shield.frame.sysmng.dto.CodeValueDTO;
import com.shield.frame.sysmng.dto.UserDTO;
import com.shield.frame.utils.CodeTypeUtil;
import com.shield.hczz.base.domain.ClueInfo;
import com.shield.hczz.base.domain.FlowWait;
import com.shield.hczz.base.domain.PzResult;
import com.shield.hczz.base.persistence.ActivitiMapper;
import com.shield.hczz.base.persistence.ClueApplyMapper;
import com.shield.hczz.base.persistence.ClueInfoMapper;
import com.shield.hczz.base.persistence.HctbMapper;
import com.shield.hczz.base.persistence.PzResultMapper;
import com.shield.hczz.common.SopConstants;
import com.shield.hczz.index.mapper.IndexMapper;
import com.shield.hczz.index.service.IndexService;
import com.shield.hczz.pzfk.service.PzfkService;
import com.shield.hczz.utils.NumberUtil;

@Service
public class PzfkServiceImpl implements PzfkService {
    @Autowired
    private HctbMapper hctbMapper;
    @Autowired
    public PzResultMapper pzResultMapper;
    @Autowired
    private AttachMapper attachMapper;
    @Autowired
    private ClueApplyMapper clueApplyMapper;
    @Autowired
    private IndexMapper indexMapper;
    @Autowired
    private ActivitiMapper activitiMapper;
    @Autowired
    private IndexService indexService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ClueInfoMapper clueInfoMapper;
    
    
	@Override
    public Map<String, Object> initData(String pzid,HttpServletRequest request){
    	User user = (User) request.getSession().getAttribute("loginUser");
        Map<String, Object> returnMap = new HashMap<>();
        Map<String, Object> param = new HashMap<>();
        param.put("pzid", pzid);
        //返回前台
        List<ClueInfo> clueInfos = new ArrayList<>();
        ClueInfo clue = new ClueInfo();
        // 查询线索
        List<Map<String, Object>> clues = hctbMapper.selectClueByPz(param);
        String clueId = "";
        
        Map<String,String> pzMap = indexService.selectPzZhuBan(pzid);
        Map<String,Object> mapparam = new HashMap<>();
        mapparam.put("userId", user.getUserId().toString());
        mapparam.put("procId", pzMap.get("FLOW_ID"));
        mapparam.put("taskKey", SopConstants.FLOW_DFK);
        for (int i=0;i<clues.size();i++) {
            Map<String, Object> map = clues.get(i);
            String clueIdTemp = map.get("CLUE_ID").toString();
            if (!clueIdTemp.equals(clueId)) {
                clueId = clueIdTemp;
                clue = new ClueInfo();
                clueInfos.add(clue);
            }
            clue.setServiceRequest(map.get("SERVICE_REQUEST").toString());
            clue.setIndex(NumberUtil.toChinese(i+1));
            clue.setClueSumup(map.containsKey("CLUE_SUMUP")?map.get("CLUE_SUMUP").toString():"");
            clue.setClueId(clueIdTemp);
            clue.setClueName(map.get("CLUE_NAME").toString());
            clue.setClueSource(map.get("CLUE_SOURCE").toString());
            clue.setClueDesc(null == map.get("CLUE_DESC") ? "暂无详情描述" : map.get("CLUE_DESC").toString());
            clue.setPzTypeDetail(map.get("PZ_TYPE_DETAIL").toString());
            clue.setCreateBy(map.get("CREATE_BY").toString());
            clue.setCreateDt(map.get("CREATE_DT").toString());
            clue.setPzTypeDetailName(map.get("SERVICE_REQUEST")+"");
            //有无附件展示
            List<String> list = pzResultMapper.selectatt(clue.getClueId());
            String[] arr = new String[list.size()];
            for (int j = 0; j < list.size(); j++) {
            	Map<String,String> attMap = pzResultMapper.selectAttById(list.get(j));
            	String attString = attMap.get("ATT_ID")+","+attMap.get("ATT_NAME")+","+attMap.get("ATT_PATH");
            	arr[j] = attString;
			}
            if(list.size() == 0){
            	String[] arr1 = new String[]{"0,暂无文件,0"};
            	clue.setAtt2(arr1);
            }else{
            	clue.setAtt2(arr);
            }
            if(!map.containsKey("CLUE_AUXILIARY")){
            	returnMap.put("err", "err");
            	returnMap.put("errMsg", map.get("CLUE_NAME").toString()+"  线索反馈人为空,请联系管理员");
            	return returnMap;
            }
            clue.setClueAuxiluary(map.get("CLUE_AUXILIARY").toString());
            
            //线索责任人查当前任务是否在办
            mapparam.put("userId", clue.getClueAuxiluary());
            mapparam.put("procId", pzMap.get("FLOW_ID"));
            mapparam.put("taskKey", SopConstants.FLOW_DFK);
            List<FlowWait> resultWait = activitiMapper.flowWait(mapparam);
            
            //查询流程
            Map<String,Object> map1 = new HashMap<String,Object>();
				map1.put("pzid", pzid);
				map1.put("userId", map.get("CREATE_BY"));
			List<Map<String, Object>> taskMap = indexService.pzfk(map1);
            if(null!=clue.getClueSumup()&&!("").equals(clue.getClueSumup())){
            	//线索完成
    			clue.setFlag("1");
    		}else{
    			//线索未完成
    			clue.setFlag("2");
    		}
            //判断权限
            if(clue.getFlag().equals("1")){
            	clue.setFlag1("2");	//不显示
            	//是否主办
            	if(String.valueOf(map.get("PZ_MAIN_ACCEPT")).equals(String.valueOf(user.getUserId()))){
            		clue.setFlag1("1");	//新增按钮
            		//判断主办人 完结状态查看反馈信息
            		if(taskMap.size() == 0 || taskMap == null){
            			clue.setFlag1("2");	//不显示
            		}
            	}
            }else{
            	//本人登陆
            	if(clue.getClueAuxiluary().equals(user.getUserId().toString())){
            		clue.setFlag1("3");//新增、提交按钮
            	}else{
            		clue.setFlag1("2");	//不显示
            	}
            }
            //判断是否已提交至待核实1008状态
	    	for (Map<String, Object> taskMaps : taskMap) {
	    		if(taskMaps.get("TASK_DEF_KEY_").equals(SopConstants.FLOW_DHS)){
	    			clue.setFlag1("2");	//不显示
	    		}
			}
            
            //用户id查用户信息，生成字符串保存到线索
            Map<String, String> userMap = indexMapper.selectById(map.get("CLUE_AUXILIARY").toString());
            clue.setClueAuxiluary(userMap.get("USERNAME")+"（"+(userMap.containsKey("USER_NO")?userMap.get("USER_NO"):"暂无警号")+"）"+"， "+(userMap.containsKey("MOBILE_PHONE")?userMap.get("MOBILE_PHONE"):"暂无联系方式"));
            //附件Model
            Attach att = new Attach();
            //判断附件是否存在
            if(map.containsKey("ATT_ID")){
            	att.setAttId(map.get("ATT_ID").toString());
            	att.setAttName(map.get("ATT_NAME").toString());
            	att.setAttPath(map.get("ATT_PATH").toString());
            }
            clue.getAtt().add(att);

            // 查询反馈信息
            Map<String, Object> paramClueid = new HashMap<>();
            paramClueid.put("clueid", clueId);
            List<Map<String, Object>> results = pzResultMapper.selectResultByClueid(paramClueid);
            List<PzResult> resultInfo = new ArrayList<>();
            PzResult pzResult = new PzResult();
            String resultId = "";
            
            for (Map<String, Object> resultmap : results) {
                String resultIdTemp = resultmap.get("RESULT_ID").toString();
                if (!resultIdTemp.equals(resultId)) {
                    resultId = resultIdTemp;
                    pzResult = new PzResult();
                    resultInfo.add(pzResult);
                }
                pzResult.setResultId(resultIdTemp);
                pzResult.setId(resultmap.get("RESULT_ID").toString());	
                
                //处理反馈内容字段，在反馈报告中的显示，若最后有句号，则去掉
                String resultDesc=resultmap.get("RESULT_DESC").toString();
                pzResult.setResultDesc(resultDesc);//  ---反馈记录内容描述
                
                String lastDesc=resultDesc.substring(resultDesc.length()-1, resultDesc.length());
                String resultDescReport=resultDesc;
                if(lastDesc.equals("。")){
                	resultDescReport=resultDesc.substring(0, resultDesc.length()-1);
                }
                pzResult.setResultDescReport(resultDescReport);
                
                //处理详细描述字段，在反馈报告中的显示，若最后有句号，则去掉
                String resultRemark=null==resultmap.get("RESULT_REMARK")?"":resultmap.get("RESULT_REMARK").toString();
                pzResult.setResultRemark(resultRemark);
                if(null!=resultRemark&&!"".equals(resultRemark)){
                	//处理反馈内容字段，在反馈报告中的显示，若最后有句号，则去掉
                	String lastRemark=resultRemark.substring(resultRemark.length()-1, resultRemark.length());
                	String resultRemarkReport=resultRemark;
                	if(!lastRemark.equals("。")){
                		resultRemarkReport+="。";
                	}
                	pzResult.setResultRemarkReport(resultRemarkReport);
                }
                
                pzResult.setResultDate(resultmap.get("RESULT_DATE").toString());//  ---反馈内容的登记时间
                pzResult.setResultBy(resultmap.get("RESULT_BY").toString());//  ---反馈来源
                Map<String,String> createByMap = indexMapper.selectById(resultmap.get("CREATE_BY").toString());
                pzResult.setCreateBy(createByMap.get("USERNAME"));//  ---创建人:创建人
                pzResult.setResultRemark(null==resultmap.get("RESULT_REMARK")?"":resultmap.get("RESULT_REMARK").toString());
                if (resultmap.get("ATT_ID") != null) {
                    Attach resultAtt = new Attach();
                    resultAtt.setAttId(resultmap.get("ATT_ID").toString());
                    resultAtt.setAttName(resultmap.get("ATT_NAME").toString());
                    resultAtt.setAttPath(resultmap.get("ATT_PATH").toString());
                    pzResult.getAtt().add(resultAtt);
                }
            }
            clue.setPzResult(resultInfo);
        }
        
        //获取评价信息
        Map<String, String> evalutionMap = indexMapper.selectPzZhuBan(pzid);
        returnMap.put("pjMap", evalutionMap);
        returnMap.put("code", 0);
        returnMap.put("clues", clueInfos);
        return returnMap;
    }

    @Override
    public Map<String, Object> addsave(PzResult result, HttpServletRequest request) {
        Map<String, Object> ret = new HashMap<>();
        User usr = (User) request.getSession().getAttribute("loginUser");
        String resultId = UUID.randomUUID().toString().replaceAll("-", "");
        result.setResultId(resultId);
        result.setCreateBy(usr.getUserId().toString());
        result.setUpdateBy(usr.getUserId().toString());
        List<String> attIds = result.getAttIds();
        if (attIds.size() > 0) {
            result.setIsFiles(1);
        }
        else {
            result.setIsFiles(0);
        }
        for (String att : attIds) {
            Attach a = new Attach();
            a.setAttId(att);
            a.setBusId(result.getResultId());
            attachMapper.updateByPK(a);
        }
        int count = pzResultMapper.insertSelective(result);
        if (count > 0) {
            ret.put("code", 0);
            ret.put("msg", "保存成功！");
        }
        else {
            ret.put("code", -1);
            ret.put("msg", "保存失败！");
        }
        return ret;
    }

	@Override
	public int synchroFeedBack(String pzid) {
		return this.clueApplyMapper.updateResultByPz(pzid);
	}

	/**
	 * 依据配侦申请主键，获取配侦反馈民警的信息
	 * @param	pzid	配侦申请主键
	 * @return	用户信息列表
	 */
	@Override
	public List<UserDTO> getBackUser(String pzid) {
		// TODO Auto-generated method stub
		List<UserDTO> list=this.userMapper.getBackUser(pzid);
		
		List<CodeValueDTO> codelist=CodeTypeUtil.getCodeValueByTypeId("1006", "0");
		
		for(UserDTO user:list){
			String policeType=user.getPoliceType();
			
			String policeTypeDesc="";
			if(null!=policeType&&!"".equals(policeType)){
				String[] policeAtt=policeType.split(",");
				for(int i=0;i<policeAtt.length;i++){
					for(CodeValueDTO code:codelist){
						if(policeAtt[i].equals(code.getPk().toString())){
							policeTypeDesc+=code.getValueDesc();
							if(i<policeAtt.length-1){
								policeTypeDesc+="、";
							}
						}
					}
				}
				user.setPoliceType("".equals(policeTypeDesc)?null:policeTypeDesc);
			}
		}
		
		return list;
	}

	/**
     * 依据线索主键，更新线索综述
     * @param clueId	线索主键
     * @param clueSumup	线索综述
     * @return
     */
	@Override
	public int updateClue(String clueId, String clueSumup) {
		HashMap<String,Object> hashMap=new HashMap<String, Object>();
		hashMap.put("clueId", clueId);
		hashMap.put("clueSumup", clueSumup);
			
		return this.clueInfoMapper.updateClueSumup(hashMap);
	}

	/**
     * 依据申请主键，更新综述
     * @param pzid	申请主键
     * @param sumup	综述
     * @return
     */
	@Override
	public int updateSumup(String pzid, String sumup) {
		HashMap<String,Object> hashMap=new HashMap<String, Object>();
		hashMap.put("pzid", pzid);
		hashMap.put("sumup", sumup);
			
		return this.hctbMapper.updateSumup(hashMap);
	}

	@SuppressWarnings("unused")
	@Override
	public Integer update(PzResult result,String resultId,HttpServletRequest req) {
		User user = (User) req.getSession().getAttribute("loginUser");
		Integer num = null;
		if(result.getAttIds().size()>0){
			result.setIsFiles(1);
		}
		num = pzResultMapper.updateByResultId(result);
		//修改附件
		List<String> attIds = result.getAttIds();
		Map<String,String> selectMap = new HashMap<String,String>();
		//查询此resultId下全部的附件
		List<String> list = pzResultMapper.selectAtt(result.getResultId());
			//将此resultId下全部附件封装入map   删除使用
			for (int i = 0; i < list.size(); i++) {
				selectMap.put(list.get(i),list.get(i));
			}
			for (int j = 0; j < attIds.size(); j++) {
				//修改用户编辑修改提交的附件的bus_id
				Attach a = new Attach();
		         	a.setAttId(attIds.get(j));
		         	a.setBusId(result.getResultId());
	         	attachMapper.updateByPK(a);
	         	//如果用户编辑修改的附件在此反馈记录下全部的附件中存在，map.remove（剩下的就是用户不需要的附件 删除）
				if(selectMap.containsKey(attIds.get(j))){
					selectMap.remove(attIds.get(j));
				}
			}
			//删除附件
			for (String maps : selectMap.keySet()) {
				String attId = selectMap.get(maps);
				num = pzResultMapper.deleteAtt(attId);        
			}
		return num;
	}


}
