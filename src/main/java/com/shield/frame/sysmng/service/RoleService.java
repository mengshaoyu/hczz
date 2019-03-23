package com.shield.frame.sysmng.service;

import java.util.HashMap;
import java.util.List;

import com.shield.frame.base.domain.Role;
import com.shield.frame.base.domain.RoleDataAuth;
import com.shield.frame.sysmng.dto.RoleDTO;

public interface RoleService {

    List<RoleDTO> getList();

    List<RoleDTO> getDeptList(HashMap<String, Object> map);

    List<RoleDTO> getPaginationList(HashMap<String, Object> map, int start, int limit);

    int save(String uid, String[] ridrr);

    int del(String[] idrr);

    RoleDTO getById(String id);

    int updateByPK(RoleDTO role);

    int getCount();

    HashMap<Object, Object> getRoleFunction(String roleId);

    int add(RoleDTO role);

    int edit(RoleDTO role);

    void delByUPK(String uid);

    int addDataPow(String[] ids, String authType);

    RoleDataAuth getRoleDataAuth(String roleId);

    List<RoleDTO> getUserById(String[] idss);

    List<RoleDTO> getUser(RoleDTO roleDTO);

    List<RoleDTO> getRole(String id);

    boolean has(List<Role> roles,String roleId);
}
