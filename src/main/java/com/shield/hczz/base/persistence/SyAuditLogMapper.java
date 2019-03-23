package com.shield.hczz.base.persistence;

import java.util.HashMap;
import java.util.List;

import com.shield.hczz.recordsmng.qvo.AuditLogVO;

public interface SyAuditLogMapper {

    List<AuditLogVO> getList(HashMap<String, Object> map, Integer page, Integer rows);

    int getCount(HashMap<String, Object> map);

}