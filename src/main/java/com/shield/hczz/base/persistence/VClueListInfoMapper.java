package com.shield.hczz.base.persistence;

import com.shield.hczz.base.domain.VClueListInfo;

import java.util.List;
import java.util.Map;

public interface VClueListInfoMapper {
    int add(VClueListInfo record);

    List<VClueListInfo> getList(Map<String, Object> qo, int currPage, int pageSize);

    int getCount(Map<String, Object> qo);
}