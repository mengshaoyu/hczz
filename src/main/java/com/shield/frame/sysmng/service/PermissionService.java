package com.shield.frame.sysmng.service;

import java.util.HashMap;
import java.util.List;

import com.shield.frame.base.domain.Permission;
import com.shield.frame.sysmng.dto.PermissionDTO;

public interface PermissionService {

    List<Permission> getList();

    List<PermissionDTO> getPermissionList(HashMap<String, Object> map);

    List<PermissionDTO> getPermission(HashMap<String, Object> map);

    List<PermissionDTO> getPermList(HashMap<String, Object> map);

    List<Permission> getPaginationList(HashMap<String, Object> map, int start, int limit);

    int save(Permission permission);

    int del(String id);

    Permission getById(String id);

    int updateByPK(Permission permission);

    int getCount();
}
