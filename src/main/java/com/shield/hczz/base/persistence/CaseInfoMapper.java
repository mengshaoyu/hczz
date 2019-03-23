package com.shield.hczz.base.persistence;

import java.util.List;
import java.util.Map;

import com.shield.hczz.base.domain.CaseInfo;

public interface CaseInfoMapper {

    List<CaseInfo> getlist(Map<String, Object> qo, int currPage, int pageSize);

    int add(CaseInfo caseInfo);

    int adds(List<CaseInfo> list);

    int getCount(Map<String, Object> qo);

    int insertSelective(CaseInfo caseInfo);
}
