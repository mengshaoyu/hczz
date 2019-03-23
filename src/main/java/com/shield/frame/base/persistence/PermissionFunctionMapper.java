package com.shield.frame.base.persistence;

import com.shield.frame.base.domain.PermissionFunction;
import java.math.BigDecimal;
import java.util.List;

public interface PermissionFunctionMapper {
    int delByPK(BigDecimal pk);

    int add(PermissionFunction record);

    PermissionFunction getByPK(BigDecimal pk);

    List<PermissionFunction> getList();

    int updateByPK(PermissionFunction record);
}