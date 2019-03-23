package com.shield.frame.sysmng.serviceimpl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shield.frame.base.domain.Permission;
import com.shield.frame.base.persistence.PermissionMapper;
import com.shield.frame.base.persistence.impl.PermissionMapperImpl;
import com.shield.frame.sysmng.dto.PermissionDTO;
import com.shield.frame.sysmng.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapperImpl permissionMapperImpl;

    public List<PermissionDTO> getPermissionList(HashMap<String, Object> map) {
        return this.permissionMapperImpl.getPermissionTree(map);
    }

    public List<PermissionDTO> getPermission(HashMap<String, Object> map) {
        return this.permissionMapperImpl.getPermission(map);
    }

    public List<PermissionDTO> getPermList(HashMap<String, Object> map) {

        return this.permissionMapperImpl.getPermList(map);
    }

    public int del(String id) {

        return 0;
    }

    public Permission getById(String id) {

        return null;
    }

    public int getCount() {

        return 0;
    }

    public List<Permission> getList() {

        return null;
    }

    public List<Permission> getPaginationList(HashMap<String, Object> map, int start, int limit) {

        return null;
    }

    public int save(Permission permission) {

        return 0;
    }

    public int updateByPK(Permission permission) {

        return 0;
    }

}
