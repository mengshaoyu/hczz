package com.shield.frame.base.persistence.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.shield.frame.base.persistence.SysParamMapper;
import com.shield.frame.common.dao.BaseDaoImpl;
import com.shield.frame.sysmng.dto.CodeValueDTO;
import com.shield.frame.sysmng.dto.SysparamDTO;

@Repository
public class SysParamMapperImpl extends BaseDaoImpl implements SysParamMapper {

    public List<SysparamDTO> getSysparamList() {

        return null;
    }

    public int getCount() {

        return 0;
    }

    public List<SysparamDTO> getPsysparamList(HashMap<String, Object> map, int curPage, int pageSize) {
        List<SysparamDTO> list = this.queryForListPagination(SysParamMapper.class.getName()
            + ".getSysparamList", map, curPage, pageSize);
        return list;
    }

    public int addSysparam(SysparamDTO sysParam) {
        return (int) this.create(SysParamMapper.class.getName() + ".addSysparam", sysParam);
    }

    public int updSysparam(SysparamDTO sysParam) {
        return (int) this.create(SysParamMapper.class.getName() + ".updSysparam", sysParam);
    }

    public int delSysparam(String id) {

        return 0;
    }

    public SysparamDTO getSysParam(Map map) {

        return null;
    }

    public List<CodeValueDTO> getDomainName() {
        return (List<CodeValueDTO>) this.queryForList(SysParamMapper.class.getName()
            + ".getDomainName");
    }

    public SysparamDTO getSysparamDTOById(String id) {

        return (SysparamDTO) this.queryForObject(SysParamMapper.class.getName()
            + ".getSysparamDTOById", id);
    }

}
