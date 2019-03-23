package com.shield.frame.sysmng.serviceimpl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shield.frame.base.domain.UserRole;
import com.shield.frame.base.persistence.UserRoleMapper;
import com.shield.frame.base.persistence.impl.UserRoleMapperImpl;
import com.shield.frame.sysmng.dto.UserRoleDTO;
import com.shield.frame.sysmng.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private UserRoleMapperImpl userRoleMapperImpl;

    public int delByUPK(String ids) {
        return this.userRoleMapperImpl.delByUPK(ids);
    }

    public int save(String[] uidrr, String[] ridrr) {
        int count = 0;
        for (String uid : uidrr) {
            this.userRoleMapperImpl.delByUPK(uid);
            for (String rid : ridrr) {
                if (!"0".equals(rid)) {
                    UserRoleDTO ur = new UserRoleDTO();
                    ur.setUserId(new BigDecimal(uid));
                    ur.setRoleId(new BigDecimal(rid));
                    int sum = this.userRoleMapperImpl.add(ur);
                    if (sum > 0) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public int del(String id) {

        return 0;
    }

    public UserRole getById(String id) {

        return null;
    }

    public int getCount() {

        return 0;
    }

    public List<UserRole> getDeptList(HashMap<String, Object> map) {

        return null;
    }

    public List<UserRole> getList() {

        return null;
    }

    public List<UserRole> getPaginationList(HashMap<String, Object> map, int start, int limit) {

        return null;
    }

    public int save(UserRole role) {

        return 0;
    }

    public int updateByPK(UserRole role) {

        return 0;
    }
}
