package com.shield.frame.sysmng.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shield.frame.base.domain.User;
import com.shield.frame.base.persistence.impl.AuditLogMapperImpl;
import com.shield.frame.sysmng.dto.AuditLogDTO;
import com.shield.frame.sysmng.service.CommonService;
import com.shield.hczz.base.domain.PzTaskLog;
import com.shield.hczz.base.persistence.PzTaskLogMapper;
import com.shield.hczz.base.persistence.impl.PzTaskLogMapperImpl;

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private AuditLogMapperImpl auditLogMapperImpl;
    @Autowired
    private PzTaskLogMapperImpl taskLogMapperImpl;

    public int addAuditLog(AuditLogDTO alog) {

        return this.auditLogMapperImpl.addLog(alog);
    }

    @Override
    public int addTaskLog(PzTaskLog taskLog) {
        // TODO Auto-generated method stub
        return this.taskLogMapperImpl.insert(taskLog);
    }

}
