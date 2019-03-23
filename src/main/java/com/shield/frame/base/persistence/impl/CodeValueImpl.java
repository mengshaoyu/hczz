package com.shield.frame.base.persistence.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.shield.frame.base.domain.CodeValue;
import com.shield.frame.base.persistence.CodeMapper;
import com.shield.frame.base.persistence.CodeValueMapper;
import com.shield.frame.common.dao.BaseDaoImpl;
import com.shield.frame.sysmng.dto.CodeValueDTO;

@Repository
public class CodeValueImpl extends BaseDaoImpl implements CodeValueMapper {

    public int add(CodeValueDTO record) {

        return (int) this.create(CodeValueMapper.class.getName() + ".add", record);
    }

    public int delByPK(BigDecimal pk) {

        return this.delete(CodeValueMapper.class.getName() + ".delByPK", pk);
    }

    public int delByTypeId(BigDecimal typeId) {

        return this.delete(CodeValueMapper.class.getName() + ".delByTypeId", typeId);
    }

    public CodeValue getByPK(BigDecimal pk) {

        return null;
    }

    public List<CodeValue> getList() {

        return null;
    }

    public int updateByPK(CodeValueDTO record) {

        return (int) this.create(CodeValueMapper.class.getName() + ".updateByPK", record);
    }

    public List<CodeValueDTO> getListByTypeId(String typeid) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("typeid", typeid);
        return (List<CodeValueDTO>) this.queryForList(CodeValueMapper.class.getName()
            + ".getListByTypeId", hashMap);
    }

    @Override
    public List<CodeValueDTO> getCodeVlueListByTypeName(String typeName) {

        return (List<CodeValueDTO>) this.queryForList(CodeValueMapper.class.getName()
            + ".getCodeVlueListByTypeName", typeName);
    }

    @Override
    public int delByDto(CodeValueDTO dto) {

        return this.delete(CodeValueMapper.class.getName() + ".delByDto", dto);
    }

    @Override
    public List<HashMap<String, Object>> getCodeValueMap(String typeId) {

        return null;
    }

    @Override
    public List<HashMap<String, Object>> getYwlxMap() {
        // TODO Auto-generated method stub
        return null;
    }

}
