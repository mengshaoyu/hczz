package com.shield.frame.base.persistence.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.shield.frame.base.domain.Attach;
import com.shield.frame.base.persistence.AttachMapper;
import com.shield.frame.common.dao.BaseDaoImpl;
import com.shield.frame.upload.dto.AttachDTO;

@Repository
public class AttachImpl extends BaseDaoImpl implements AttachMapper {

    public int add(AttachDTO record) {

        return this.create(AttachMapper.class.getName() + ".add", record);
    }

    public int delByPK(BigDecimal attId) {

        return 0;
    }

    public Attach getByPK(BigDecimal attId) {

        return null;
    }

    public List<Attach> getList(Map<String, Object> map) {

        return null;
    }

    public int updateByPK(Attach record) {

        return 0;
    }

    @Override
    public int logicDelByPK(Attach record) {
        // TODO Auto-generated method stub
        return 0;
    }

}
