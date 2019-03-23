package com.shield.frame.base.persistence;

import com.shield.frame.base.domain.RolePermission;
import java.math.BigDecimal;
import java.util.List;

public interface RolePermissionMapper {
    int delByPK(BigDecimal pk);

    int add(RolePermission record);

    RolePermission getByPK(BigDecimal pk);

    List<RolePermission> getList();

    int updateByPK(RolePermission record);
}