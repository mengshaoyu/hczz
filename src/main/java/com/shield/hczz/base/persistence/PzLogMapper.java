package com.shield.hczz.base.persistence;

import com.shield.hczz.base.domain.PzLog;
import java.math.BigDecimal;
import java.util.List;

public interface PzLogMapper {
    int delByPK(BigDecimal featurePersonId);

    int add(PzLog record);

    PzLog getByPK(BigDecimal featurePersonId);

    List<PzLog> getList();

    int updateByPK(PzLog record);
}