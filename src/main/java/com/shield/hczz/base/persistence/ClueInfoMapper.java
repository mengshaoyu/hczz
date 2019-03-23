package com.shield.hczz.base.persistence;

import java.util.HashMap;
import java.util.List;

import com.shield.hczz.base.domain.ClueInfo;

public interface ClueInfoMapper {
    int deleteByPrimaryKey(String clueId);

    int insert(ClueInfo record);

    int insertSelective(ClueInfo record);

    ClueInfo selectByPrimaryKey(String clueId);

    int updateByPrimaryKeySelective(ClueInfo record);

    int updateByPrimaryKey(ClueInfo record);

    int delByPK(String clueId);

    int add(ClueInfo record);

    ClueInfo getByPK(String clueId);

    int updateByPK(ClueInfo record);

    List<ClueInfo> getList();

	int updateClueSumup(HashMap<String, Object> hashMap);
}