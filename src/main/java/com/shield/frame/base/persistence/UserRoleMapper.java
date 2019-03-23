package com.shield.frame.base.persistence;

import com.shield.frame.base.domain.UserRole;
import java.math.BigDecimal;
import java.util.List;

public interface UserRoleMapper {
    int delByPK(BigDecimal relId);

    int add(UserRole record);

    UserRole getByPK(BigDecimal relId);

    List<UserRole> getList();

    int updateByPK(UserRole record);

    int delByUPK(String userId);
}