package com.shield.frame.base.persistence;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import com.shield.frame.base.domain.Permission;
import com.shield.frame.sysmng.dto.PermissionDTO;

public interface PermissionMapper {
    int delByPK(BigDecimal permissionId);

    int add(Permission record);

    Permission getByPK(BigDecimal permissionId);

    List<Permission> getList();

    int updateByPK(Permission record);

    List<PermissionDTO> getPermissionTree(HashMap<String, Object> map);

    List<PermissionDTO> getPermission(HashMap<String, Object> map);

    List<PermissionDTO> getPermList(HashMap<String, Object> map);
}