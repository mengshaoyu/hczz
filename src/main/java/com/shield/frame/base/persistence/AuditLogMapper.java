package com.shield.frame.base.persistence;

import com.shield.frame.sysmng.dto.AuditLogDTO;

public interface AuditLogMapper {
    int addLog(AuditLogDTO alog);
}