package com.shield.hczz.base.persistence.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.shield.frame.common.dao.BaseDaoImpl;
import com.shield.hczz.base.domain.VClueListInfo;
import com.shield.hczz.base.persistence.VClueListInfoMapper;

@Repository
public class VClueListInfoMapperImpl extends BaseDaoImpl implements VClueListInfoMapper {

    private static final String mapperName = VClueListInfoMapper.class.getName();

    @Override
    public int add(VClueListInfo record) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<VClueListInfo> getList(Map<String, Object> qo, int currPage, int pageSize) {
        return this.queryForListPagination(mapperName + ".getList", qo, currPage, pageSize);
    }

    @Override
    public int getCount(Map<String, Object> qo) {
        return this.queryForPageCount(mapperName + ".getCount", qo);
    }

}
