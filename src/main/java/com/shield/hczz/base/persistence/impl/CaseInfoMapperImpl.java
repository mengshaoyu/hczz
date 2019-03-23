package com.shield.hczz.base.persistence.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.shield.frame.common.dao.BaseDaoImpl;
import com.shield.hczz.base.domain.CaseInfo;
import com.shield.hczz.base.persistence.CaseInfoMapper;

@Repository
public class CaseInfoMapperImpl extends BaseDaoImpl implements CaseInfoMapper {

    @Override
    @SuppressWarnings("unchecked")
    public List<CaseInfo> getlist(Map<String, Object> qo, int currPage, int pageSize) {
        return this.queryForListPagination(CaseInfoMapper.class.getName() + ".getlist", qo,
            currPage, pageSize);
    }

    @Override
    public int add(CaseInfo caseInfo) {
        return this.getSqlSession().insert(CaseInfoMapper.class.getName() + ".add", caseInfo);
    }

    @Override
    public int adds(List<CaseInfo> list) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list);
        return this.getSqlSession().insert(CaseInfoMapper.class.getName() + ".adds", map);
    }

    @Override
    public int getCount(Map<String, Object> qo) {
        return this.queryForPageCount(CaseInfoMapper.class.getName() + ".getCount", qo);
    }

    @Override
    public int insertSelective(CaseInfo caseInfo) {
        return this.getSqlSession().insert(CaseInfoMapper.class.getName() + ".insertSelective",
            caseInfo);
    }

    /**
     * 依据案件编号，获取案件信息
     * @param caseNo	案件编号
     * @return	案件信息
     */
	public CaseInfo getCaseInfoByNo(String caseNo) {
		// TODO Auto-generated method stub
		return this.getSqlSession().selectOne(CaseInfoMapper.class.getName() + ".getCaseInfoByNo",
				caseNo);
	}

}
