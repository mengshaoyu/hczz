package com.shield.hczz.caseinfo.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shield.frame.common.qvo.CommonVO;
import com.shield.frame.common.qvo.DataGridVO;
import com.shield.frame.sysmng.serviceimpl.DeptmentServiceImpl;
import com.shield.frame.utils.CodeTypeUtil;
import com.shield.hczz.base.domain.CaseInfo;
import com.shield.hczz.base.persistence.impl.CaseInfoMapperImpl;
import com.shield.hczz.caseinfo.qvo.CaseInfoQO;
import com.shield.hczz.caseinfo.service.CaseInfoService;
import com.shield.hczz.index.mapper.IndexMapper;

@Service
public class CaseInfoServiceImpl implements CaseInfoService {

    @Autowired
    private CaseInfoMapperImpl caseInfoMapperImpl;
    @Autowired
    private DeptmentServiceImpl deptmentServiceImpl;
    @Autowired
    private IndexMapper indexMapper;

    @SuppressWarnings("unchecked")
    @Override
    public DataGridVO<CaseInfo> getlist(CaseInfoQO qo, String page, String rows) {
        DataGridVO<CaseInfo> result = new DataGridVO<CaseInfo>();
        if(!qo.getUndertakeUnit().isEmpty()){
        	String undertakeUnit = indexMapper.selectDeptById(qo.getUndertakeUnit());
        	qo.setUndertakeUnit(undertakeUnit.isEmpty()?null:undertakeUnit);
        }
        try {
            // 当前页
            int intPage = Integer.parseInt((page == null || page.equals("0")) ? "1" : page);
            // 每页显示条数
            int intRows = Integer.parseInt((rows == null || rows.equals("0")) ? "10" : rows);
            Map<String, Object> map = BeanUtils.describe(qo);
            int total = this.getCount(map);
            result.setTotal(total);
            List<CaseInfo> list = caseInfoMapperImpl.getlist(map, intPage, intRows);
            for (int i = 0; i < list.size(); i++) {
            	if(list.get(i).getAcceptUnit() != null){
            		List<String> deptNameList = indexMapper.selectDeptName(list.get(i).getAcceptUnit());
            		for (int j = 0; j < deptNameList.size(); j++) {
            			list.get(i).setAcceptUnit(deptNameList.get(j));
					}
            	}
            	list.get(i).setId(list.get(i).getCaseId());
			}
            result.setRows(list);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public CommonVO add(CaseInfo caseInfo) {
        int flag = caseInfoMapperImpl.add(caseInfo);
        boolean success = flag > 0 ? true : false;
        return new CommonVO(success);
    }

    @Override
    public CommonVO adds(List<CaseInfo> list) {
        int flag = caseInfoMapperImpl.adds(list);
        boolean success = flag > 0 ? true : false;
        return new CommonVO(success);
    }

    @Override
    public int getCount(Map<String, Object> map) {
        return caseInfoMapperImpl.getCount(map);
    }

    /**
     * 解析用户导入的案件信息
     * @param	list	导入的案件信息
     */
	@Override
	public HashMap<String, Object> parseCaseImport(List<CaseInfo> list,String userId) {
		HashMap<String, Object> hashMap=new HashMap<String, Object>();
		
		List<CaseInfo> errlist=new ArrayList<CaseInfo>();
		List<CaseInfo> oklist=new ArrayList<CaseInfo>();
		Map<String,String> checkMap = new HashMap<String,String>();
		for(CaseInfo caseInfo:list){
			caseInfo.setCreateBy(userId);
			caseInfo.setUpdateBy(userId);
			StringBuffer sbf=new StringBuffer();
			String caseNo = caseInfo.getCaseNo();
			if(null != checkMap.get(caseNo)){
			    sbf.append("模板内存在相同案件编号的案件信息！");
			}
			else{
			    checkMap.put(caseNo, caseNo);
			}
			//案件编号，去重
			CaseInfo info=this.caseInfoMapperImpl.getCaseInfoByNo(caseNo);
			if(null!=info){
				sbf.append("存在相同案件编号的案件信息！");
			}
			
			//受理单位，转义
			String acceptUnitId=this.deptmentServiceImpl.getIdByName(caseInfo.getAcceptUnit());
			if(null!=acceptUnitId&&!"".equals(acceptUnitId)){
				caseInfo.setAcceptUnit(acceptUnitId);
			}else{
				sbf.append("未查询到受理单位！");
			}
			
			//主办单位，转义handleTel
			String handleUnitId=this.deptmentServiceImpl.getIdByName(caseInfo.getHandleUnit());
			if(null!=handleUnitId&&!"".equals(handleUnitId)){
				caseInfo.setHandleUnit(handleUnitId);
			}else{
				sbf.append("未查询到主办单位！");
			}
			
			//案件状态，转义
			String state=CodeTypeUtil.getCodeByDesc(caseInfo.getCaseStatus(),"1005","0");
			if(null!=state&&!"".equals(state)){
				caseInfo.setCaseStatus(state);
			}else{
				sbf.append("未查询到案件状态！");
			}
			
			//案件类型，转义
			String caseTypeImp=CodeTypeUtil.getCodeByDesc(caseInfo.getCaseTypeImp(),"2001","0");
			if(null!=caseTypeImp&&!"".equals(caseTypeImp)){
				caseInfo.setCaseTypeImp(caseTypeImp);
			}else{
				sbf.append("未查询到案件类型！");
			}
			
			caseInfo.setErrorMsg(sbf.toString());
			if(null!=sbf&&!"".equals(sbf.toString())){
				errlist.add(caseInfo);
			}else{
				oklist.add(caseInfo);
			}
		}
		
		hashMap.put("list", oklist);
		hashMap.put("errlist", errlist);
		
		return hashMap;
	}

}
