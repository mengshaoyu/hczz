package com.shield.frame.base.persistence.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.shield.frame.base.domain.Code;
import com.shield.frame.base.persistence.CodeMapper;
import com.shield.frame.common.dao.BaseDaoImpl;
import com.shield.frame.sysmng.dto.CodeDTO;

@Repository
public class CodeImpl extends BaseDaoImpl implements CodeMapper {

    public int add(Code record) {

        return 0;
    }

    public int delByPK(BigDecimal typeId) {

        return (int) this.delete(CodeMapper.class.getName() + ".delByPK", typeId);
    }

    public Code getByPK(BigDecimal typeId) {
        Code code = (Code) this.queryForObject(CodeMapper.class.getName() + ".getByPK", typeId);
        return code;
    }

    public List<Code> getList() {

        return null;
    }

    public int updateByPK(CodeDTO record) {

        return (int) this.create(CodeMapper.class.getName() + ".updateByPK", record);
    }

    public List<CodeDTO> getCodeList() {
        return this.queryForList(CodeMapper.class.getName() + ".getCodeList");
    }

    public List<CodeDTO> getPageCodeList(HashMap<String, Object> map, int curPage, int pageSize) {
        List<CodeDTO> list = this.queryForListPagination(CodeMapper.class.getName()
            + ".getCodeList", map, curPage, pageSize);
        return list;
    }

    public int getCount() {

        return 0;
    }

}
