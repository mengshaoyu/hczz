package com.shield.frame.sysmng.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shield.frame.sysmng.dto.CodeValueDTO;
import com.shield.frame.sysmng.dto.SysparamDTO;

public interface SysParamService {

    List<SysparamDTO> getSysparamList();

    List<SysparamDTO> getPsysparamList(HashMap<String, Object> map, int intPage, int intRows);

    int getCount();

    int addSysparam(SysparamDTO sysParam);

    int updSysparam(SysparamDTO sysParam, String skey, String dom);

    int delSysparams(String[] idss, String[] domss, String[] Skeyss);

    SysparamDTO getSysParam(Map map);

    List<CodeValueDTO> getDomainName();

    SysparamDTO getSysP(String id);

}
