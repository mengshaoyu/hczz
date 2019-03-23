package com.shield.frame.common.dao;

import java.util.List;
import java.util.Map;

import com.shield.frame.common.BaseTO;

/**
 * 公共Dao
 */

@SuppressWarnings("unchecked")
public interface BaseDao {

    List queryForList(String sqlKey);

    List queryForList(String sqlKey, Map map);

    List queryForList(String sqlKey, BaseTO obj);

    List queryForList(String sqlKey, Object obj);

    List queryForList(String sqlKey, String param);

    List queryForList(String sqlKey, Long pk);

    List queryForListPagination(String sqlKey, Map paramMap, int curPage, int pageSize);

    List queryForListPagination(String sqlKey, Map paramMap);

    Object queryForObject(String sqlKey, Object param);

    int queryForPageCount(String sqlKey, Map param);

    List queryForListPagination(String sqlKey, Object param, int curPage, int pageSize);

    void putFixParaToMap(Map<String, Object> map, String actByType);

    int create(String sqlKey, BaseTO obj);

    int delete(String sqlKey);

    int delete(String sqlKey, Long pk);

    int delete(String sqlKey, Object ids);

    int delete(String sqlKey, BaseTO obj);

    int update(String sqlKey, BaseTO obj);

    int update(String sqlKey, Map map);

    int update(String sqlKey);
}
