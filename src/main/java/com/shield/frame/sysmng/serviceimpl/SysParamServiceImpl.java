package com.shield.frame.sysmng.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shield.frame.base.persistence.SysParamMapper;
import com.shield.frame.base.persistence.impl.SysParamMapperImpl;
import com.shield.frame.sysmng.dto.CodeValueDTO;
import com.shield.frame.sysmng.dto.SysparamDTO;
import com.shield.frame.sysmng.service.SysParamService;
import com.shield.frame.utils.SysParamUtil;

@Service
public class SysParamServiceImpl implements SysParamService {

    @Autowired
    private SysParamMapperImpl sysParamMapperImpl;

    @Autowired
    private SysParamMapper sysParamMapper;

    public List<SysparamDTO> getSysparamList() {
        return sysParamMapper.getSysparamList();
    }

    public int getCount() {
        return this.sysParamMapper.getCount();
    }

    public List<SysparamDTO> getPsysparamList(HashMap<String, Object> map, int intPage, int intRows) {
        return sysParamMapperImpl.getPsysparamList(map, intPage, intRows);
    }

    public int addSysparam(SysparamDTO sysParam) {
        return sysParamMapperImpl.addSysparam(sysParam);
    }

    public int updSysparam(SysparamDTO sysParam, String skey, String dom) {
        int sum = sysParamMapperImpl.updSysparam(sysParam);
        SysParamUtil.delSysParamCache(skey, dom);
        return sum;
    }

    public int delSysparams(String[] idss, String[] domss, String[] skeyss) {
        int sum = -1;
        for (int i = 0; i < idss.length; i++) {
            sum = sysParamMapper.delSysparam(idss[i]);
            SysParamUtil.delSysParamCache(domss[i], skeyss[i]);
        }
        return sum;
    }

    public SysparamDTO getSysParam(Map map) {
        SysparamDTO list = sysParamMapper.getSysParam(map);
        return list;
    }

    public List<CodeValueDTO> getDomainName() {
        List<CodeValueDTO> list = sysParamMapperImpl.getDomainName();
        return list;
    }

    @Override
    public SysparamDTO getSysP(String id) {
        SysparamDTO sysparamDTO = sysParamMapperImpl.getSysparamDTOById(id);
        return sysparamDTO;
    }

}
