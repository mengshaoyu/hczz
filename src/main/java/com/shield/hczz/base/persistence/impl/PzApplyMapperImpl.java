package com.shield.hczz.base.persistence.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.shield.frame.base.domain.ApplyExp;
import com.shield.frame.common.dao.BaseDaoImpl;
import com.shield.hczz.apply.qvo.PzApplyVO;
import com.shield.hczz.base.domain.PzApply;
import com.shield.hczz.base.persistence.PzApplyMapper;

@Repository
public class PzApplyMapperImpl extends BaseDaoImpl implements PzApplyMapper {

    private static final String mapperName = PzApplyMapper.class.getName();

    @SuppressWarnings("unchecked")
    @Override
    public List<PzApplyVO> getlist(Map<String, Object> qo, int currPage, int pageSize) {
        return this.queryForListPagination(mapperName + ".getlist", qo, currPage, pageSize);
    }

    @Override
    public int add(PzApply pzApply) {
        return this.getSqlSession().insert(mapperName + ".add", pzApply);
    }

    @Override
    public int getCount(Map<String, Object> qo) {
        return this.queryForPageCount(mapperName + ".getCount", qo);
    }

    @Override
    public int insertSelective(PzApply pzApply) {
        return this.getSqlSession().insert(PzApplyMapper.class.getName() + ".insertSelective",
            pzApply);
    }

    @Override
    public int updateFlow(PzApply pzApply) {
        return this.getSqlSession().update(mapperName + ".updateFlow", pzApply);
    }

    @Override
    public int updateDeleted(String ids, String userId) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("uuid", ids.split(","));
        map.put("userId", userId);
        return this.getSqlSession().update(PzApplyMapper.class.getName() + ".updateDeleted", map);
    }

    @Override
    public PzApplyVO getById(String pzApplyId) {
        return this.getSqlSession().selectOne(mapperName + ".getById", pzApplyId);
    }

    @Override
    public int updateStatus(PzApply pzApply) {
        return this.getSqlSession().update(mapperName + ".updateStatus", pzApply);
    }

    @Override
    public PzApplyVO getInfoById(String pzApplyId) {
        return this.getSqlSession().selectOne(mapperName + ".getInfoById", pzApplyId);
    }

    /**
     * 获取两个流程之间流转的时间差
     * 
     * @param pzid
     *            配侦申请主键
     * @param startFlow
     *            开始流程ID
     * @param endFlow
     *            结束流程ID
     * @return
     */
    @Override
    public PzApplyVO getFlowTime(String pzid, String startFlow, String endFlow) {
        // TODO Auto-generated method stub
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("pzid", pzid);
        map.put("startFlow", startFlow);
        map.put("endFlow", endFlow);

        List<PzApplyVO> list = this.getSqlSession().selectList(mapperName + ".getFlowTime", map);

        return (null != list && list.size() > 0) ? list.get(0) : null;
    }

    @Override
    public List<Map<String, Object>> getToDoCount(Map<String, Object> map) {
        return this.getSqlSession().selectList(mapperName + ".getToDoCount", map);
    }

    /**
     * 根据配侦ID主键，更新落地情况及评价信息
     * 
     * @param pzApply
     *            实体类
     * @return
     */
    public int updateLanding(PzApply pzApply) {
        // TODO Auto-generated method stub
        return this.getSqlSession().update(mapperName + ".updateLanding", pzApply);
    }

    @Override
    public int addExp(ApplyExp applyExp) {
        return this.getSqlSession().insert(mapperName + ".addExp", applyExp);
    }

    /***
     * 依据配侦ID，获取当前申请所在流程
     * @param pzid	配侦申请ID
     * @return
     */
    @Override
	public PzApplyVO getFlowById(String pzid) {
		// TODO Auto-generated method stub
    	Map<String, Object> hashMap=new HashMap<String,Object>();
    	hashMap.put("pzid", pzid);
		return (PzApplyVO)this.queryForObject(mapperName+".getFlowNameById", hashMap);
	}

	public PzApplyVO getApplyMsgById(String pzid) {
		// TODO Auto-generated method stub
		Map<String, Object> hashMap=new HashMap<String,Object>();
    	hashMap.put("pzid", pzid);
		return (PzApplyVO)this.queryForObject(mapperName+".getApplyMsgById", hashMap);
	}

    @Override
    public int backUnread(ApplyExp applyExp) {
        return this.getSqlSession().delete(mapperName+".backUnread", applyExp);
    }

    @Override
    public List<HashMap<String, Object>> getAssignees(Map<String, Object> param) {
        return this.getSqlSession().selectList(mapperName+".getAssignees", param);
    }

    @Override
    public Map<String, Object> getPjById(String pzid) {
        return this.getSqlSession().selectOne(mapperName+".getPjById", pzid);
    }

}
