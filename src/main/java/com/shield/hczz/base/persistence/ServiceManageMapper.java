package com.shield.hczz.base.persistence;

import java.util.List;

import com.shield.hczz.base.domain.ServiceManage;

public interface ServiceManageMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(ServiceManage record);

    int insertSelective(ServiceManage record);

    ServiceManage selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(ServiceManage record);

    int updateByPrimaryKey(ServiceManage record);

	List<ServiceManage> getMaxBackTime(String pzApplyId);
}