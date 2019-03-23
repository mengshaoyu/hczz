package com.shield.frame.base.persistence;

import java.util.List;
import java.util.Map;

import com.shield.frame.sysmng.dto.CodeValueDTO;
import com.shield.frame.sysmng.dto.SysparamDTO;

public interface SysParamMapper {

    List<SysparamDTO> getSysparamList();

    int getCount();

    int addSysparam(SysparamDTO sysParam);

    int updSysparam(SysparamDTO sysParam);

    int delSysparam(String id);

    SysparamDTO getSysParam(Map map);

    List<CodeValueDTO> getDomainName();

    SysparamDTO getSysparamDTOById(String id);

}