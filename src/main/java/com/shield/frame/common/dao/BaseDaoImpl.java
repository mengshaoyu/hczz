package com.shield.frame.common.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shield.frame.common.BaseTO;
import com.shield.frame.common.Constants;
import com.shield.frame.utils.DataAuthUtil;
import com.shield.frame.utils.LocalUtil;

/**
 * 公共Dao,不使用泛型、考虑到使用泛型太约定优于配置
 */

@SuppressWarnings("unchecked")
@Component("baseDaoImpl1")
public class BaseDaoImpl extends SqlSessionDaoSupport implements BaseDao {

    @Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    protected <S> S getMapper(Class<S> clazz) {
        return getSqlSession().getMapper(clazz);
    }

    /**
     * 获取数据List
     * @param sqlKey
     * @return List
     */
    public List queryForList(String sqlKey) {
        return this.getSqlSession().selectList(sqlKey);
    }

    public List queryForList(String sqlKey, BaseTO obj) {
        return this.getSqlSession().selectList(sqlKey, obj);
    }

    public List queryForList(String sqlKey, Object obj) {
        return this.getSqlSession().selectList(sqlKey, obj);
    }

    public List queryForList(String sqlKey, String param) {
        return this.getSqlSession().selectList(sqlKey, param);
    }

    public List queryForList(String sqlKey, Long pk) {
        return this.getSqlSession().selectList(sqlKey, pk);
    }

    /**
     * 数据权限过滤queryForList方法，参数形式必须为map
     */
    public List queryForList(String sqlKey, Map map) {
        return this.getSqlSession().selectList(sqlKey, putFixAuthDataParam(map));
    }

    /**
     * MyBatis物理分页方法
     * @param sqlKey: SQL ID
     * @param paramMap: 查询参数Map对象 
     * @param curPage: 当前页数
     * @param pageSize: 每页记录数
     * @return List
     */
    public List queryForListPagination(String sqlKey, Map paramMap, int curPage, int pageSize) {
        List list = this.getSqlSession().selectList(sqlKey, putFixAuthDataParam(paramMap), //调用数据权限解析填充方法
            new RowBounds((curPage - 1) * pageSize + 1, pageSize));
        return list;
    }

    /**
     * MyBatis物理分页方法
     * @param sqlKey: SQL ID
     * @param paramMap: 查询参数Map对象 
     * @return List
     */
    public List queryForListPagination(String sqlKey, Map paramMap) {
        int curPage = (Integer) paramMap.get("curPage");
        int pageSize = (Integer) paramMap.get("pageSize");
        return this.getSqlSession().selectList(sqlKey, putFixAuthDataParam(paramMap),
            new RowBounds((curPage - 1) * pageSize + 1, pageSize));
    }

    public List queryForListPagination(String sqlKey, Object param, int curPage, int pageSize) {
        return this.getSqlSession().selectList(sqlKey, param,
            new RowBounds((curPage - 1) * pageSize + 1, pageSize));
    }

    public Object queryForObject(String sqlKey, Object param) {
        return this.getSqlSession().selectOne(sqlKey, param);
    }

    /**
     * 数据权限过滤、分页获取总数方法
     * @param sqlKey
     * @param param
     * @return int
     */
    public int queryForPageCount(String sqlKey, Map param) {
        return (Integer) this.getSqlSession().selectOne(sqlKey, putFixAuthDataParam(param));
    }

    public void putFixParaToMap(Map<String, Object> map, String actByType) {

        String userId = LocalUtil.getUserId();

        map.put("createBy", userId);
        map.put("createDt", new Date());
        map.put("updateBy", userId);
        map.put("updateDt", new Date());
        map.put("actByType", actByType);
    }

    /**
     * 填充数据权限控制信息到parmMap对象中去
     * @param parmMap
     * @return Map
     */
    public Map putFixAuthDataParam(Map parmMap) {

        Map authMap = (HashMap<String, String>) DataAuthUtil.getDataAuth();

        if (null != authMap && !authMap.isEmpty()) {

            if (authMap.containsKey(Constants.DATAAUTH_D)) {
                parmMap.put(Constants.DATAAUTH_D, authMap.get(Constants.DATAAUTH_D));
            }

            if (authMap.containsKey(Constants.DATAAUTH_U)) {
                parmMap.put(Constants.DATAAUTH_U, authMap.get(Constants.DATAAUTH_U));
            }
        }

        return parmMap;
    }

    /**
     * 保存方法
     * @param sqlKey 数据保存SQL ID
     * @param BaseTO
     * @return int >0 保存成功
     */
    public int create(String sqlKey, BaseTO obj) {
        String userId = LocalUtil.getUserId();

        obj.setCreateBy(userId);
        obj.setCreateDt(new Date());
        obj.setUpdateBy(userId);
        obj.setUpdateDt(new Date());
        int result = this.getSqlSession().insert(sqlKey, obj);
        return result;
    }

    /**
     * <b>功能：批量保存方法 <br/>  TODO 泛型在方法中的应用????? 这里不对 以后修改</b><br>
     * <br>
     * @param sqlKey
     * @param obj
     * @return int
     **/
    public int createBatch(String sqlKey, List<? extends BaseTO> obj) {
        List list = new ArrayList<>();
        String userId = LocalUtil.getUserId();
        for (int i = 0, len = obj.size(); i < len; i++) {
            BaseTO baseTo = obj.get(i);
            baseTo.setCreateBy(userId);
            baseTo.setCreateDt(new Date());
            baseTo.setUpdateBy(userId);
            baseTo.setUpdateDt(new Date());
            list.add(baseTo);
        }
        return this.getSqlSession().insert(sqlKey, list);
    }

    /**
     * 删除方法
     * @param sqlKey 数据保存SQL ID
     * @return int >0 保存成功
     */
    public int delete(String sqlKey) {
        return this.getSqlSession().delete(sqlKey);
    }

    /**
     * 删除方法
     * @param sqlKey 数据删除SQL ID
     * @param pk 主键
     * @return int >0 删除成功
     */
    public int delete(String sqlKey, Long pk) {
        int result = this.getSqlSession().delete(sqlKey, pk);
        return result;
    }

    /**
     * 删除方法
     * @param sqlKey 数据删除SQL ID
     * @param Object
     * @return int >0 删除成功
     */
    public int delete(String sqlKey, Object ids) {
        int result = this.getSqlSession().delete(sqlKey, ids);
        return result;
    }

    public int delete(String sqlKey, BaseTO obj) {
        int result = this.getSqlSession().delete(sqlKey, obj);
        return result;
    }

    /**
     * <b>功能：逻辑删除</b><br>
     * <br>
     * @param sqlKey
     * @param map
     * @return int
     **/
    public int logicalDelete(String sqlKey, Map map) {
        String userId = LocalUtil.getUserId();
        map.put("deleteBy", userId);
        map.put("isDeleted", 1);
        map.put("updateDt", new Date());
        return this.getSqlSession().update(sqlKey, map);
    }

    /**
     * <b>功能：批量逻辑删除方法</b><br>
     * <br>
     * @param sqlKey
     * @param objs
     * @return int
     **/
    public int deleteLogicBatch(String sqlKey, List<? extends BaseTO> objs) {
        List list = new ArrayList<BaseTO>();
        String userId = LocalUtil.getUserId();
        for (int i = 0, len = objs.size(); i < len; i++) {
            BaseTO baseTo = objs.get(i);
            baseTo.setDeleteBy(userId);
            baseTo.setUpdateDt(new Date());
            list.add(baseTo);
        }
        return this.getSqlSession().update(sqlKey, list);
    }

    /**
     * <b>功能：逻辑删</b><br>
     * <br>
     * @param sqlKey
     * @param obj
     * @return int
     **/
    public int logicalDelete(String sqlKey, BaseTO obj) {
        String userId = LocalUtil.getUserId();
        obj.setDeleteBy(userId);
        obj.setUpdateDt(new Date());
        return this.getSqlSession().update(sqlKey, obj);
    }

    /**
     * 更新方法
     * @param sqlKey 数据更新SQL ID
     * @param BaseTO
     * @return int >0 更新成功
     */
    public int update(String sqlKey, BaseTO obj) {
        String userId = LocalUtil.getUserId();

        obj.setUpdateBy(userId);
        obj.setUpdateDt(new Date());
        obj.setActByType(obj.getActByType());
        return this.getSqlSession().update(sqlKey, obj);
    }

    /**
     * 更新方法
     * @param sqlKey 数据更新SQL ID
     * @param Map
     * @return int >0 更新成功
     */
    public int update(String sqlKey, Map map) {
        String userId = LocalUtil.getUserId();
        map.put("updateBy", userId);
        map.put("updateDt", new Date());
        return this.getSqlSession().update(sqlKey, map);
    }

    public int update(String sqlKey) {
        return this.getSqlSession().update(sqlKey);
    }
}
