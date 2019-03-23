package com.shield.frame.base.persistence.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.shield.frame.base.domain.Permission;
import com.shield.frame.base.persistence.PermissionMapper;
import com.shield.frame.common.dao.BaseDaoImpl;
import com.shield.frame.sysmng.dto.PermissionDTO;

@Repository
public class PermissionMapperImpl extends BaseDaoImpl implements PermissionMapper {

    public int add(Permission record) {

        return 0;
    }

    public int delByPK(BigDecimal permissionId) {

        return 0;
    }

    public Permission getByPK(BigDecimal permissionId) {

        return null;
    }

    public List<Permission> getList() {

        return null;
    }

    public int updateByPK(Permission record) {

        return 0;
    }

    public List<PermissionDTO> getPermissionTree(HashMap<String, Object> map) {

        return this.queryForList(PermissionMapper.class.getName() + ".getPermissionTree", map);
    }

    public List<PermissionDTO> getPermission(HashMap<String, Object> map) {

        return this.queryForList(PermissionMapper.class.getName() + ".getPermission", map);
    }

    public List<PermissionDTO> getPermList(HashMap<String, Object> map) {

        return this.queryForList(PermissionMapper.class.getName() + ".getPermList", map);
    }

}
