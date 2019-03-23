package com.shield.hczz.caseinfo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shield.frame.common.qvo.CommonVO;
import com.shield.frame.common.qvo.DataGridVO;
import com.shield.hczz.base.domain.CaseInfo;
import com.shield.hczz.caseinfo.qvo.CaseInfoQO;

public interface CaseInfoService {

    DataGridVO<CaseInfo> getlist(CaseInfoQO qo, String currPage, String pageSize);

    CommonVO add(CaseInfo caseInfo);

    CommonVO adds(List<CaseInfo> list);

    int getCount(Map<String, Object> map);

    /**
     * 解析导入的案件信息
     * @param list	导入的案件信息
     * @return	result	导入结果
     */
    HashMap<String, Object> parseCaseImport(List<CaseInfo> list,String userId);
}
