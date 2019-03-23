package com.shield.frame.base.persistence;

import java.util.List;

import com.shield.hczz.base.domain.Ops;
import com.shield.hczz.ops.qvo.OpsQO;
import com.shield.hczz.ops.qvo.OpsVO;


public interface OpsMapper {
	public List<OpsVO> getOpsList(OpsQO qo);
	public boolean updateOps(Ops ops);
	public boolean insertOps(Ops ops);
	public Ops selectById(String opsId);
}
