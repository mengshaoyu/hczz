package com.shield.frame.base.persistence;

import com.shield.frame.base.domain.Attach;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.shield.frame.upload.dto.AttachDTO;

public interface AttachMapper {
    int delByPK(BigDecimal attId);

    int add(AttachDTO record);

    Attach getByPK(BigDecimal attId);

    List<Attach> getList(Map<String, Object> map);

    int updateByPK(Attach record);

    int logicDelByPK(Attach record);
}