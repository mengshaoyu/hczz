package com.shield.hczz.recordsmng.service;

import java.util.HashMap;
import java.util.List;

import com.shield.hczz.recordsmng.qvo.AuditLogVO;
import com.shield.frame.common.qvo.CommonVO;

/**
 * <b>功能：日志管理</b><br>
 * @version 1.0
 */
public interface SyAuditLogService {

    /**
     * <b>功能：获得日志列表</b><br>
     * @param map
     * @param page
     * @param rows
     * @return HashMap<String,Object>
     **/
    HashMap<String, Object> getList(HashMap<String, Object> map, String page, String rows);

    /**
     * <b>功能：验证导出的数据是否存在</b><br>
     * @param logPKs
     * @return CommonVO
     **/
    CommonVO expPartCheck(String logPKs);

    /**
     * <b>功能：获得导出的数据</b><br>
     * @param logPKs
     * @param page
     * @param rows
     * @return List<AuditLogVO>
     **/
    List<AuditLogVO> getList(String logPKs, Integer page, Integer rows);

    /**
     * <b>功能：全部数据导出验证</b><br>
     * @param map
     * @return CommonVO
     **/
    int expCheck(HashMap<String, Object> map);

    /**
     * <b>功能：全部数据导出</b><br>
     * @param map
     * @param page
     * @param rows
     * @return List<AuditLogVO>
     **/
    List<AuditLogVO> exportAll(HashMap<String, Object> map);

}
