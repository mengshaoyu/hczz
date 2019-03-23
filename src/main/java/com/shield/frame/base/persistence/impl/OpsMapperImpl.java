package com.shield.frame.base.persistence.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;

import com.shield.frame.base.persistence.OpsMapper;
import com.shield.frame.common.dao.BaseDaoImpl;
import com.shield.hczz.base.domain.Ops;
import com.shield.hczz.ops.qvo.OpsQO;
import com.shield.hczz.ops.qvo.OpsVO;

@Repository
public class OpsMapperImpl extends BaseDaoImpl implements OpsMapper {

	@SuppressWarnings("unchecked")
	@Override
	public List<OpsVO> getOpsList(OpsQO qo) {
		Map<String, Object> param = null;
		try {
			param = BeanUtils.describe(qo);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return this.queryForListPagination(OpsMapper.class.getName() + ".getOpsList", param, qo.getPage(), qo.getRows());
	}

	@Override
	public boolean updateOps(Ops ops) {
		return false;
	}

	@Override
	public boolean insertOps(Ops ops) {
		return false;
	}

	@Override
	public Ops selectById(String opsId) {
		return null;
	}

}
