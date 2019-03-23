package com.shield.frame.sysmng.service;

import com.shield.frame.sysmng.dto.AuditLogDTO;
import com.shield.hczz.base.domain.PzTaskLog;

public interface CommonService {

    int addAuditLog(AuditLogDTO alog);

    int addTaskLog(PzTaskLog taskLog);
}
