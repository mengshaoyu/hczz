package com.shield.hczz.base.persistence.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.shield.frame.common.dao.BaseDaoImpl;
import com.shield.hczz.base.domain.ServiceManage;
import com.shield.hczz.base.persistence.ServiceManageMapper;

@Repository
public class ServiceManageMapperImpl extends BaseDaoImpl implements ServiceManageMapper {

    private static final String mapperName = ServiceManageMapper.class.getName();

	@Override
	public int deleteByPrimaryKey(String uuid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(ServiceManage record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(ServiceManage record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ServiceManage selectByPrimaryKey(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(ServiceManage record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(ServiceManage record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ServiceManage> getMaxBackTime(String pzApplyId) {
		// TODO Auto-generated method stub
		
		HashMap<String, Object> hashMap=new HashMap<String,Object>();
		hashMap.put("pzid", pzApplyId);
		
		return this.queryForList(mapperName+".getMaxBackTime", hashMap);
	}

   
}
