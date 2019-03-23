package com.shield.frame.base.persistence;

import com.shield.frame.base.domain.CodeValue;
import com.shield.frame.sysmng.dto.CodeValueDTO;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public interface CodeValueMapper {
    int delByPK(BigDecimal pk);

    int delByDto(CodeValueDTO dto);

    int delByTypeId(BigDecimal typeId);

    int add(CodeValueDTO record);

    CodeValue getByPK(BigDecimal pk);

    List<CodeValue> getList();

    int updateByPK(CodeValueDTO record);

    List<CodeValueDTO> getListByTypeId(String typeid);

    List<CodeValueDTO> getCodeVlueListByTypeName(String typeName);

    List<HashMap<String, Object>> getCodeValueMap(String typeId);

    List<HashMap<String, Object>> getYwlxMap();
}