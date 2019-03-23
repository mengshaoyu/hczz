package com.shield.hczz.ops.serviceimpl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shield.frame.base.domain.Role;
import com.shield.frame.base.domain.User;
import com.shield.frame.base.persistence.OpsMapper;
import com.shield.frame.base.persistence.impl.OpsMapperImpl;
import com.shield.frame.sysmng.serviceimpl.RoleServiceImpl;
import com.shield.hczz.base.domain.Ops;
import com.shield.hczz.common.SopConstants;
import com.shield.hczz.ops.qvo.OpsQO;
import com.shield.hczz.ops.qvo.OpsVO;
import com.shield.hczz.ops.service.OpsService;

@Service
public class OpsServiceImpl implements OpsService {

	@Autowired
	private RoleServiceImpl roleServiceImpl;

	@Autowired
	private OpsMapper opsMapper;

	@Autowired
	private OpsMapperImpl opsMapperImpl;

	/**
	 * 判断用户是否有 回复运维反馈意见的权限
	 */
	@Override
	public boolean checkUserRole(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("loginUser");
		List<Role> roles = user.getRoleList();
		if (roleServiceImpl.has(roles, SopConstants.ROLE_YW)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取全部运维意见列表
	 */
	@Override
	public List<OpsVO> getOpsList(OpsQO qo) {
		qo.setPage(qo.getPage() <= 0 ? 1 : qo.getPage());
		qo.setRows(qo.getRows() <= 0 ? 1 : qo.getRows());
		List<OpsVO> list = opsMapperImpl.getOpsList(qo);
		return list;
	}

	/**
	 * 运维意见 更新回复
	 */
	@Override
	public boolean updateOps(Ops ops) {
		if (!this.checkOpsHas(ops.getSopOpsId())) {
			ops.setSopOpsState("1");
			opsMapper.updateOps(ops);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 新增运维意见
	 */
	@Override
	public boolean addOps(Ops ops) {
		if (this.checkOpsHas(ops.getSopOpsId())) {
			opsMapper.insertOps(ops);
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 判断运维意见是否存在
	 * 
	 * @return false 为存在
	 */
	@Override
	public boolean checkOpsHas(String opsId) {
		Ops ops = opsMapper.selectById(opsId);
		if (ops != null) {
			return false;
		} else {
			return true;
		}
	}

}
