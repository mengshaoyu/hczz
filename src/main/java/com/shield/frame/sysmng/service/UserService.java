package com.shield.frame.sysmng.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shield.frame.sysmng.dto.UserDTO;

public interface UserService {

    List<UserDTO> getList(HashMap<String, Object> map);

    List<UserDTO> getPaginationList(HashMap<String, Object> map, int start, int limit);

    int save(UserDTO userManager);

    int del(String[] ids);

    UserDTO getById(String id);

    int updateByPK(UserDTO userManger);

    int getCount(HashMap<String, Object> map);

    int upuse(String[] ids, String useflag);

    int resetPw(String[] ids, String repwd) throws Exception;

    UserDTO getByAccount(String account);

    String getRoleStr(String userId);

    HashMap<String, Object> addUsers(List<Map<String, String>> resultList,
        List<Map<String, String>> errorList) throws Exception;

    int blindPT(String ids, String userId) throws Exception;
}
