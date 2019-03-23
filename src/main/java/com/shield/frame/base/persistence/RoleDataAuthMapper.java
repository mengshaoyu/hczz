package com.shield.frame.base.persistence;

import com.shield.frame.base.domain.RoleDataAuth;
import java.math.BigDecimal;
import java.util.List;

public interface RoleDataAuthMapper {
    int delByPK(BigDecimal roleId);

    int add(RoleDataAuth record);

    RoleDataAuth getByPK(BigDecimal roleId);

    List<RoleDataAuth> getList();

    int updateByPK(RoleDataAuth record);
}