package com.shield.frame.base.persistence.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.shield.frame.base.domain.Role;
import com.shield.frame.base.domain.User;
import com.shield.frame.base.persistence.AuditLogMapper;
import com.shield.frame.base.persistence.UserMapper;
import com.shield.frame.common.dao.BaseDaoImpl;
import com.shield.frame.sysmng.dto.AuditLogDTO;
import com.shield.frame.sysmng.dto.UserDTO;

@Repository
public class AuditLogMapperImpl extends BaseDaoImpl implements AuditLogMapper {

    public int addLog(AuditLogDTO alog) {

        return this.create(AuditLogMapper.class.getName() + ".add", alog);
    }

}
