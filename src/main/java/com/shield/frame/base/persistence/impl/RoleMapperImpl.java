package com.shield.frame.base.persistence.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.shield.frame.base.domain.Role;
import com.shield.frame.base.domain.RoleDataAuth;
import com.shield.frame.base.persistence.RoleMapper;
import com.shield.frame.common.dao.BaseDaoImpl;
import com.shield.frame.sysmng.dto.DataPowDTO;
import com.shield.frame.sysmng.dto.RoleDTO;
import com.shield.frame.sysmng.dto.RolePermissionDTO;

@Repository
public class RoleMapperImpl extends BaseDaoImpl implements RoleMapper {

    public int add(Role record) {

        return 0;
    }

    public int delByPK(BigDecimal roleId) {

        return 0;
    }

    public Role getByPK(BigDecimal roleId) {

        return null;
    }

    public List<RoleDTO> getList() {
        return this.queryForList(RoleMapper.class.getName() + ".getList");
    }

    public List<RoleDTO> getPaginationList(HashMap<String, Object> map, int start, int limit) {
        return this.queryForListPagination(RoleMapper.class.getName() + ".getList", map, start,
            limit);
    }

    public int updateByPK(Role record) {
        return 0;
    }

    /**
     * 根据角色ID获取功能权限信息
     */
    public HashMap<Object, Object> getRoleFunction(String roleId) {
        return (HashMap<Object, Object>) this.getSqlSession().selectMap(
            RoleMapper.class.getName() + ".getRoleFunction", roleId, "url");
    }

    public int add(RoleDTO record) {
        return (int) this.create(RoleMapper.class.getName() + ".add", record);
    }

    public int getCount() {
        List list = this.queryForList(RoleMapper.class.getName() + ".getCount");
        if (null != list && list.size() > 0) {
            return Integer.parseInt(String.valueOf(list.get(0)));
        }
        return 0;
    }

    public int edit(RoleDTO role) {
        return (int) this.create(RoleMapper.class.getName() + ".edit", role);
    }

    public int del(Map map) {
        return (int) this.update(RoleMapper.class.getName() + ".del", map);
    }

    public void delByUPK(Map map) {
        this.update(RoleMapper.class.getName() + ".delByUPK", map);

    }

    public void delData(String id) {
        this.delete(RoleMapper.class.getName() + ".delData", id);

    }

    public int save(RolePermissionDTO rolePermission) {
        return (int) this.create(RoleMapper.class.getName() + ".save", rolePermission);
    }

    public int addDataPow(DataPowDTO dataPowDTO) {
        return (int) this.create(RoleMapper.class.getName() + ".addDataPow", dataPowDTO);
    }

    /**
     * 根据角色ID获取数据权限信息
     */
    public RoleDataAuth getRoleDataAuth(String roleId) {
        return (RoleDataAuth) this.queryForObject(RoleMapper.class.getName() + ".getRoleDataAuth",
            roleId);
    }

    public List<RoleDTO> getUserById(String id) {
        List<RoleDTO> list = this.queryForList(RoleMapper.class.getName() + ".getUserById", id);
        return list;
    }

    @Override
    public List<RoleDTO> getUser(RoleDTO roleDTO) {
        List<RoleDTO> list = this.queryForList(RoleMapper.class.getName() + ".getUser", roleDTO);
        return list;
    }

    public List<RoleDTO> getRole(String roleId) {
        List<RoleDTO> list = this.queryForList(RoleMapper.class.getName() + ".getRole", roleId);
        return list;
    }

}
