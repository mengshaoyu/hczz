package com.shield.frame.base.persistence;

import com.shield.frame.base.domain.Function;
import java.math.BigDecimal;
import java.util.List;

public interface FunctionMapper {
    int delByPK(BigDecimal functionId);

    int add(Function record);

    Function getByPK(BigDecimal functionId);

    List<Function> getList();

    int updateByPK(Function record);
}