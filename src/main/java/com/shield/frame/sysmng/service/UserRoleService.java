package com.shield.frame.sysmng.service;

import java.util.HashMap;
import java.util.List;

import com.shield.frame.base.domain.UserRole;
import com.shield.frame.sysmng.dto.UserRoleDTO;

public interface UserRoleService {

    List<UserRole> getList();

    List<UserRole> getDeptList(HashMap<String, Object> map);

    List<UserRole> getPaginationList(HashMap<String, Object> map, int start, int limit);

    int save(String[] uidrr, String[] ridrr);

    int del(String id);

    int delByUPK(String ids);

    UserRole getById(String id);

    int updateByPK(UserRole role);

    int getCount();
}
