package com.shield.frame.base.persistence.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.shield.frame.base.domain.Role;
import com.shield.frame.base.domain.User;
import com.shield.frame.base.persistence.UserMapper;
import com.shield.frame.common.dao.BaseDaoImpl;
import com.shield.frame.sysmng.dto.UserDTO;
import com.shield.hczz.common.SopConstants;

@Repository
public class UserMapperImpl extends BaseDaoImpl implements UserMapper {

    public User getUserInfo(String account) {
        User user = (User) this
            .queryForObject(UserMapper.class.getName() + ".getUserInfo", account);
        //获取子部门信息
        String childDepts = null;
        if (user != null && StringUtils.isNotBlank(user.getLoginAccount())) {
            childDepts = (String) this.queryForObject(
                UserMapper.class.getName() + ".getChildDepts", user.getDeptId());
            user.setChildDepts(childDepts);
        }
        return user;
    }

    public List<Role> getUserRoles(String account) {
        return (List<Role>) this
            .queryForList(UserMapper.class.getName() + ".getUserRoles", account);
    }

    public List<UserDTO> getPaginationList(HashMap<String, Object> map, int start, int limit) {
        List<UserDTO> list = null;
        list = this.queryForListPagination(UserMapper.class.getName() + ".getList", map, start,
            limit);
        return list;
    }

    public int add(UserDTO record) {
        return this.create(UserMapper.class.getName() + ".add", record);
    }

    public int delByPK(String userId) {
        return this.delete(UserMapper.class.getName() + ".delByPK", userId);
    }

    public int delByPK(BigDecimal uuid) {
        return 0;
    }

    public UserDTO getByPK(String userId) {
        List list = this.queryForList(UserMapper.class.getName() + ".getByPK", userId);
        if (null != list && list.size() > 0) {
            return (UserDTO) list.get(0);
        }
        return null;
    }

    public UserDTO getByPK(BigDecimal uuid) {

        return null;
    }

    public int getCount(HashMap<String, Object> hashMap) {

        return this.queryForPageCount(UserMapper.class.getName() + ".getCount", hashMap);
    }

    public List<UserDTO> getList(HashMap<String, Object> map) {

        return this.queryForList(UserMapper.class.getName() + ".getList", map);
    }

    public int resetPw(HashMap<String, Object> map) {
        return this.update(UserMapper.class.getName() + ".resetPw", map);
    }

    public int updateByPK(UserDTO record) {
        return this.update(UserMapper.class.getName() + ".updateByPK", record);
    }

    public int upuse(HashMap<String, Object> hashMap) {
        return this.update(UserMapper.class.getName() + ".upuse", hashMap);
    }

    public UserDTO getByAccount(String userId) {
        List list = this.queryForList(UserMapper.class.getName() + ".getByAccount", userId);
        if (null != list && list.size() > 0) {
            return (UserDTO) list.get(0);
        }
        return null;
    }

    public int updatePwd(HashMap<String, Object> hashMap) {

        return 0;
    }

    @Override
    public int addMore(List<UserDTO> list) {

        return 0;
    }

    public UserDTO getByIdNo(String idNo) {
        List list = this.queryForList(UserMapper.class.getName() + ".getByIdNo", idNo);
        if (null != list && list.size() > 0) {
            return (UserDTO) list.get(0);
        }
        return null;
    }

    @Override
    public List<Role> getRolesById(String userId) {
        return (List<Role>) this.queryForList(UserMapper.class.getName() + ".getRolesById", userId);
    }

    public int blindPT(HashMap<String, Object> hashMap) {
        return this.update(UserMapper.class.getName() + ".blindPT", hashMap);
    }
    
	/**
	 * 根据配侦主键，获取配侦民警信息
	 * @param	pzid	配侦申请主键
	 * @return	配侦反馈民警信息集合
	 */
	@Override
	public List<UserDTO> getBackUser(String pzid) {
		// TODO Auto-generated method stub
		HashMap<String,Object> hashMap=new HashMap<String, Object>();
		hashMap.put("pzid", pzid);
		
		return this.getSqlSession().selectList(UserMapper.class.getName()+".getBackUser",hashMap);
	}

    @Override
    public String getPoliceTypeById(String userId) {
        return this.getSqlSession().selectOne(UserMapper.class.getName()+".getPoliceTypeById", userId);
    }

    @Override
    public int getCountByRoleId(String roleId) {
        return this.getSqlSession().selectOne(UserMapper.class.getName()+".getCountByRoleId", SopConstants.ROLE_HCZXLD1);
    }
}
