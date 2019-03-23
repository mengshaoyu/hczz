package com.shield.frame.base.persistence;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shield.frame.base.domain.Role;
import com.shield.frame.base.domain.RoleDataAuth;
import com.shield.frame.sysmng.dto.DataPowDTO;
import com.shield.frame.sysmng.dto.RoleDTO;
import com.shield.frame.sysmng.dto.RolePermissionDTO;

public interface RoleMapper {
    int delByPK(BigDecimal roleId);

    int add(RoleDTO record);

    Role getByPK(BigDecimal roleId);

    List<RoleDTO> getList();

    int updateByPK(Role record);

    int getCount();

    /**
     * 取得角色下的关联功能表
     *
     * @param roleId 角色ID
     * @return
     */
    HashMap<Object, Object> getRoleFunction(String roleId);

    int edit(RoleDTO role);

    int del(Map map);

    void delByUPK(Map map);

    int save(RolePermissionDTO rolePermission);

    void delData(String id);

    int addDataPow(DataPowDTO dataPowDTO);

    RoleDataAuth getRoleDataAuth(String roleId);

    List<RoleDTO> getUserById(String id);

    List<RoleDTO> getUser(RoleDTO roleDTO);

    List<RoleDTO> getRole(String roleId);
}