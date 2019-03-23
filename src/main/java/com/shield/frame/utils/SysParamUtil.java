package com.shield.frame.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shield.frame.common.Constants;
import com.shield.frame.sysmng.dto.CodeValueDTO;
import com.shield.frame.sysmng.dto.SysparamDTO;
import com.shield.frame.sysmng.service.SysParamService;

public class SysParamUtil {
    /**
     * 从缓存中获取系统参数数据
     * @param sysKey
     * @param domainName
     * @return
     */
    public static String getSysParamByKey(String sysKey, String domainName) {

        String key = Constants.SYSPARAM_PREX + domainName + sysKey;
        String sysValue = (String) OsCacheUtil.getFromCache(key);

        if (null == sysValue || ("").equals(sysValue)) {
            SysParamService sysParamService = (SysParamService) SpringConfigLoadHelper
                .getBean(SysParamService.class);
            Map map = new HashMap();
            map.put("sysKey", sysKey);
            map.put("domainName", domainName);
            SysparamDTO sysparamDTO = sysParamService.getSysParam(map);
            sysValue = sysparamDTO.getSysValue();
            if (sysValue != null || !("").equals(sysValue)) {
                OsCacheUtil.putInCache(key, sysValue);
            }

            //			List<SysparamDTO> list = sysParamService.getSysParam(map);
            //			for(SysparamDTO d:list){
            //				sysValue = d.getSysValue();
            //			}
            //			if(sysValue!=null || !("").equals(sysValue)){
            //				OsCacheUtil.putInCache(key, sysValue);
            //			}
        }
        return sysValue;
    }

    public static void delSysParamCache(String sysKey, String domainName) {
        String key = Constants.SYSPARAM_PREX + domainName + sysKey;
        SylogicCacheUtil.removeElement(key);

    }

}
