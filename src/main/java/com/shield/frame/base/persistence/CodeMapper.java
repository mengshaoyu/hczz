package com.shield.frame.base.persistence;

import com.shield.frame.base.domain.Code;
import com.shield.frame.sysmng.dto.CodeDTO;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public interface CodeMapper {
    int delByPK(BigDecimal typeId);

    int add(Code record);

    Code getByPK(BigDecimal typeId);

    List<Code> getList();

    int updateByPK(CodeDTO record);

    List<CodeDTO> getCodeList();

    List<CodeDTO> getPageCodeList(HashMap<String, Object> map, int curPage, int pageSize);

    int getCount();
}