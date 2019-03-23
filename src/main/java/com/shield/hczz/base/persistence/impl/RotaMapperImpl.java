package com.shield.hczz.base.persistence.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.shield.frame.common.dao.BaseDaoImpl;
import com.shield.hczz.base.persistence.RotaMapper;
import com.shield.hczz.rotamng.qvo.RotaVO;

@Repository
public class RotaMapperImpl extends BaseDaoImpl implements RotaMapper {

    @Override
    public List<RotaVO> getList(Map<String, Object> map, Integer page, Integer rows) {
        return this
            .queryForListPagination(RotaMapper.class.getName() + ".getList", map, page, rows);
    }

    @Override
    public int getCount(Map<String, Object> map) {
        return this.queryForPageCount(RotaMapper.class.getName() + ".getCount", map);
    }

}
