package com.shield.hczz.base.persistence.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.shield.frame.common.dao.BaseDaoImpl;
import com.shield.hczz.base.persistence.SyAuditLogMapper;
import com.shield.hczz.recordsmng.qvo.AuditLogVO;

@Repository
public class SyAuditLogMapperImpl extends BaseDaoImpl implements SyAuditLogMapper {

    @SuppressWarnings("unchecked")
    @Override
    public List<AuditLogVO> getList(HashMap<String, Object> map, Integer page, Integer rows) {
        return this.queryForListPagination(SyAuditLogMapper.class.getName() + ".getList", map,
            page, rows);
    }

    @SuppressWarnings("unchecked")
    public List<AuditLogVO> exportAll(HashMap<String, Object> map, Integer page, Integer rows) {
        return this.queryForList(SyAuditLogMapper.class.getName() + ".getList", map);
    }

    @Override
    public int getCount(HashMap<String, Object> map) {
        return this.queryForPageCount(SyAuditLogMapper.class.getName() + ".getCount", map);
    }

}
