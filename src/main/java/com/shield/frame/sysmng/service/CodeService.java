package com.shield.frame.sysmng.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import com.shield.frame.base.domain.Code;
import com.shield.frame.sysmng.dto.CodeDTO;
import com.shield.frame.sysmng.dto.CodeValueDTO;

public interface CodeService {

    List<CodeDTO> getCodeList();

    int updateCodeandCodevalue(String typeId, CodeDTO codeDTO, List<CodeValueDTO> codeValuelist,
        List<CodeValueDTO> delList);

    int delByPK(BigDecimal typeId);

    int delCodeandCodeValueByTypeId(String[] ids);

    List<CodeDTO> getPageCodeList(HashMap<String, Object> map, int curPage, int pageSize);

    int getCount();

    Code getCode(String typeId);

}
