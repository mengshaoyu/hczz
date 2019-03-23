package com.shield.frame.sysmng.serviceimpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shield.frame.base.domain.Role;
import com.shield.frame.base.domain.RoleDataAuth;
import com.shield.frame.base.persistence.impl.RoleMapperImpl;
import com.shield.frame.base.persistence.impl.UserRoleMapperImpl;
import com.shield.frame.common.Constants;
import com.shield.frame.sysmng.dto.DataPowDTO;
import com.shield.frame.sysmng.dto.RoleDTO;
import com.shield.frame.sysmng.dto.RolePermissionDTO;
import com.shield.frame.sysmng.service.RoleService;
import com.shield.frame.utils.SylogicCacheUtil;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapperImpl roleMapperImpl;
    @Autowired
    private UserRoleMapperImpl userRoleMapperImpl;

    public List<RoleDTO> getList() {
        return this.roleMapperImpl.getList();
    }

    public int del(String[] idrr) {
        int sum = 0;
        for (String id : idrr) {
            Map map = new HashMap();
            map.put("id", id);
            sum = roleMapperImpl.del(map);
        }
        return sum;
    }

    public RoleDTO getById(String id) {

        return null;
    }

    public int getCount() {
        return this.roleMapperImpl.getCount();
    }

    public List<RoleDTO> getDeptList(HashMap<String, Object> map) {

        return null;
    }

    public List<RoleDTO> getPaginationList(HashMap<String, Object> map, int start, int limit) {
        List<RoleDTO> list = roleMapperImpl.getPaginationList(map, start, limit);
        return list;
    }

    public int updateByPK(RoleDTO role) {

        return 0;
    }

    /**
     * 根据角色ID获取功能权限HashMap
     */
    public HashMap<Object, Object> getRoleFunction(String roleId) {
        return roleMapperImpl.getRoleFunction(roleId);
    }

    public int add(RoleDTO role) {
        int sum = roleMapperImpl.add(role);
        return sum;
    }

    public int edit(RoleDTO role) {
        int sum = roleMapperImpl.edit(role);
        return sum;
    }

    public void delByUPK(String uid) {
        Map map = new HashMap();
        map.put("uid", uid);
        this.roleMapperImpl.delByUPK(map);

    }

    public int save(String uid, String[] ridrr) {
        int sum = 0;
        Map map = new HashMap();
        map.put("uid", uid);
        this.roleMapperImpl.delByUPK(map);//通过角色ID删除该角色所有权限
        if (ridrr.length != 0) {
            for (String rid : ridrr) {
                if ((!"0".equals(rid)) && (!("").equals(rid))) {
                    RolePermissionDTO ur = new RolePermissionDTO();
                    ur.setRoleId(new BigDecimal(uid));
                    ur.setPermissionId(new BigDecimal(rid));
                    sum = this.roleMapperImpl.save(ur);
                }
            }
        }
        return sum;
    }

    public int addDataPow(String[] ids, String authType) {
        int sum = 0;
        for (String id : ids) {
        	SylogicCacheUtil.removeElement(Constants.INTERCEPT_URL_PREX + id);
            this.roleMapperImpl.delData(id);//通过角色ID删除该角色所有数据权限
            DataPowDTO dataPowDTO = new DataPowDTO();
            dataPowDTO.setRoleId(id);
            dataPowDTO.setAuthType(authType);
            sum = roleMapperImpl.addDataPow(dataPowDTO);
        }
        return sum;
    }

    /**
     * 根据角色ID获取角色数据权限信息
     */
    public RoleDataAuth getRoleDataAuth(String roleId) {
        return roleMapperImpl.getRoleDataAuth(roleId);
    }

    public List<RoleDTO> getUserById(String[] idss) {
        List<RoleDTO> list = new ArrayList();
        for (String id : idss) {
            list = roleMapperImpl.getUserById(id);
            if (list.size() > 0) {
                break;
            }
        }
        return list;
    }

    @Override
    public List<RoleDTO> getUser(RoleDTO roleDTO) {
        List<RoleDTO> list = roleMapperImpl.getUser(roleDTO);
        return list;
    }

    public List<RoleDTO> getRole(String id) {
        List<RoleDTO> list = roleMapperImpl.getRole(id);
        return list;
    }

    @Override
    public boolean has(List<Role> roles, String roleId) {
        for(Role r : roles){
            if(Integer.valueOf(roleId) == r.getRoleId().intValue()){
                return true;
            }
        }
        return false;
    }

}
