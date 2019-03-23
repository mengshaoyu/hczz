package com.shield.frame.base.persistence.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.shield.frame.base.domain.UserRole;
import com.shield.frame.base.persistence.UserRoleMapper;
import com.shield.frame.common.dao.BaseDaoImpl;
import com.shield.frame.sysmng.dto.UserRoleDTO;

@Repository
public class UserRoleMapperImpl extends BaseDaoImpl implements UserRoleMapper {

    public int add(UserRoleDTO record) {
        return (int) this.create(UserRoleMapper.class.getName() + ".add", record);
    }

    public int delByPK(BigDecimal relId) {

        return 0;
    }

    public UserRole getByPK(BigDecimal relId) {

        return null;
    }

    public List<UserRole> getList() {

        return null;
    }

    public int updateByPK(UserRole record) {

        return 0;
    }

    public int delByUPK(String userId) {

        return this.delete(UserRoleMapper.class.getName() + ".delByUPK", userId);
    }

    public int add(UserRole record) {

        return 0;
    }

}
