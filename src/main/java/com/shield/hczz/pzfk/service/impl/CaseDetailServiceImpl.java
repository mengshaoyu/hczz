package com.shield.hczz.pzfk.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shield.frame.base.domain.Attach;
import com.shield.frame.base.persistence.CodeValueMapper;
import com.shield.frame.sysmng.dto.CodeValueDTO;
import com.shield.hczz.base.domain.ClueInfo;
import com.shield.hczz.base.domain.PzResult;
import com.shield.hczz.base.persistence.HctbMapper;
import com.shield.hczz.base.persistence.PzResultMapper;
import com.shield.hczz.common.SopConstants;
import com.shield.hczz.index.mapper.IndexMapper;
import com.shield.hczz.pzfk.mapper.CaseDetailMapepr;
import com.shield.hczz.pzfk.service.CaseDetailService;
import com.shield.hczz.utils.NumberUtil;

@Service
public class CaseDetailServiceImpl implements CaseDetailService{
	
	@Autowired
	private CaseDetailMapepr caseDetailMapepr;
	@Autowired
	private HctbMapper hctbMapper;
	@Autowired
    public PzResultMapper pzResultMapper;
	@Autowired
    private IndexMapper indexMapper;
	@Autowired
    private CodeValueMapper codeValueMapper;

	@Override
	public Map<String, Object> selectClueInfo(Map<String, Object> paramMap) {
		Map<String, Object> returnMap = new HashMap<>();
		//根据pzId查配帧等级
		String garde = caseDetailMapepr.selectGarde(String.valueOf(paramMap.get("pzid")));
		garde = garde.equals("1")?"一级":garde.equals("2")?"二级":"三级";
		//根据pzId查询线索信息
        List<Map<String, Object>> clues = hctbMapper.selectClueByPz(paramMap);
        List<ClueInfo> cluesList = new ArrayList<>();
        ClueInfo clue = new ClueInfo();
        String clueId = "";
        for (int i=0;i<clues.size();i++) {
            Map<String, Object> map = clues.get(i);
            String clueIdTemp = map.get("CLUE_ID").toString();
            if (!clueIdTemp.equals(clueId)) {
                clueId = clueIdTemp;
                clue = new ClueInfo();
                cluesList.add(clue);
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
            clue.setFlag(garde);	//封装配帧等级
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
            //附件Model
            Attach att = new Attach();
            //判断附件是否存在
            if(map.containsKey("ATT_ID")){
            	att.setAttId(map.get("ATT_ID").toString());
            	att.setAttName(map.get("ATT_NAME").toString());
            	att.setAttPath(map.get("ATT_PATH").toString());
            }
            clue.getAtt().add(att);
            
            //查询反馈信息
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
                pzResult.setCreateBy(createByMap == null ?"责任人为空，请联系管理员":createByMap.get("USERNAME"));//  ---创建人:创建人
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
        returnMap.put("clues", cluesList);
		return returnMap;
	}

	@Override
	public String decide(int userId) {
		String flag = null;
		//根据用户id查询用户roleName
        List<Map<String,String>> list = indexMapper.decide(userId);
        for (int i = 0; i < list.size(); i++) {
            //判断权限
            String roleId = String.valueOf(list.get(i).get("ROLE_ID"));
            //管理员账号
            String id = String.valueOf(list.get(i).get("USER_ID"));
            //普通民警
            if(roleId.equals(SopConstants.ROLE_PTMJ)){
            	flag = "1";
            }
            //分管领导
            if(roleId.equals(SopConstants.ROLE_FGLD)){
            	flag = "2";
            }
            //合成民警、值班长
            if(roleId.equals(SopConstants.ROLE_HCZXMJ) || roleId.equals(SopConstants.ROLE_ZBZ)){
            	flag = "3";
            }
            if(id.equals("1")){
            	flag = "3";
            }
        }
		return flag;
	}

	@Override
	public List<Map<String, String>> selectEnd(String caseId) {
		List<Map<String, String>> endList = new ArrayList<Map<String, String>>();
		//根据caseId查pz表中此caseId下所有流程id
		List<Map<String, String>> flowList = caseDetailMapepr.selectPzFlow(caseId);
		for (Map<String, String> flowId : flowList) {
			//完结状态的flwoId查询配帧表
			Map<String, String> pzMap = caseDetailMapepr.selectEnd(flowId.get("FLOW_ID"));
			if(pzMap != null){
				endList.add(pzMap);
			}
		}
		return endList;
	}

	@Override
	public Map<String, String> selectCaseInfo(String caseId) {
		List<Map<String, String>> cases = caseDetailMapepr.selectCaseInfo(caseId);
		Map<String,String> caseMap = cases.get(0);
		//案件来源
        if(null != caseMap.get("CASE_AJLY") && !("").equals(caseMap.get("CASE_AJLY"))){
        	caseMap.put("CASE_AJLY", caseMap.get("CASE_AJLY").equals("1")?"110指令":caseMap.get("CASE_AJLY").equals("2")?"派出所上报":"肇事逃逸");
        }
        //案件状态
        List<CodeValueDTO> ajztlist = codeValueMapper.getListByTypeId("1005");
        for (int i = 0; i < ajztlist.size(); i++) {
        	if(caseMap.containsKey("CASE_STATUS")){
        		if(ajztlist.get(i).getCodeValue().equals(caseMap.get("CASE_STATUS"))){
        			caseMap.put("CASE_STATUS",ajztlist.get(i).getValueDesc());
        		}
        	}
		}
        //案件类型
        List<CodeValueDTO> ajlxlist = codeValueMapper.getListByTypeId("2001");
        for (int i = 0; i < ajlxlist.size(); i++) {
        	if(caseMap.containsKey("CASE_TYPE_IMP")){
        		if(ajlxlist.get(i).getCodeValue().equals(caseMap.get("CASE_TYPE_IMP"))){
        			caseMap.put("CASE_TYPE_IMP",ajlxlist.get(i).getValueDesc());
        		}
        	}
		}
        //案别
        List<CodeValueDTO> ayList = codeValueMapper.getListByTypeId("2005");
        for (int i = 0; i < ayList.size(); i++) {
        	if(caseMap.containsKey("CASE_TYPE")){
        		if(ayList.get(i).getCodeValue().equals(caseMap.get("CASE_TYPE"))){
        			caseMap.put("CASE_TYPE",ayList.get(i).getValueDesc());
        		}
        	}
		}
        //受案单位
		if(caseMap.containsKey("ACCEPT_UNIT")){
			caseMap.put("ACCEPT_UNIT",caseDetailMapepr.selectDept(caseMap.get("ACCEPT_UNIT")));
		}
		return caseMap;
	}

	@Override
	public List<String> selectUser(String deptId) {
		return caseDetailMapepr.selectUser(deptId);
	}


}
