package com.shield.frame.base.persistence;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import com.shield.frame.base.domain.Role;
import com.shield.frame.base.domain.User;
import com.shield.frame.sysmng.dto.UserDTO;

public interface UserMapper {
    int delByPK(BigDecimal userId);

    int add(UserDTO record);

    UserDTO getByPK(BigDecimal userId);

    UserDTO getByAccount(String userId);

    List<UserDTO> getList(HashMap<String, Object> map);

    int updateByPK(UserDTO record);

    /**
     * 取得用户信息
     *
     * @param account 用户帐号
     * @return
     */
    User getUserInfo(String account);

    /**
     * 取得用户的角色信息
     *
     * @param account 用户帐号
     * @return
     */
    List<Role> getUserRoles(String account);

    List<Role> getRolesById(String userId);

    int delByPK(String uuid);

    UserDTO getByPK(String uuid);

    int getCount(HashMap<String, Object> hashMap);

    int upuse(HashMap<String, Object> hashMap);

    int resetPw(HashMap<String, Object> hashMap);

    /**
     * 修改用户的密码
     *
     * @param  hashMap
     * 
     * @return 执行的结果
     */
    int updatePwd(HashMap<String, Object> hashMap);

    /**
     *批量添加
     * @param  hashMap
     * @return 执行的结果
     */
    int addMore(List<UserDTO> list);

    UserDTO getByIdNo(String userNo);

    int blindPT(HashMap<String, Object> hashMap);
    
    List<UserDTO> getBackUser(String pzid);
    
    String getPoliceTypeById(String userId);
    
    int getCountByRoleId(String roleId);
}