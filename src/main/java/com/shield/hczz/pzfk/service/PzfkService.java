package com.shield.hczz.pzfk.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.shield.frame.sysmng.dto.UserDTO;
import com.shield.hczz.base.domain.PzResult;

public interface PzfkService {

    Map<String, Object> initData(String pzid, HttpServletRequest request);

    Map<String, Object> addsave(PzResult result, HttpServletRequest request);

	int synchroFeedBack(String pzid);

	List<UserDTO> getBackUser(String pzid);

	/**
	 * 依据线索主键，更新补充线索综述
	 * @param clueId	线索主键
	 * @param clueSumup	线索综述
	 * @return
	 */
	int updateClue(String clueId, String clueSumup);

	/**
	 * 依据申请主键，更新补充综合结论
	 * @param pzid	申请主键
	 * @param sumup	综述
	 * @return
	 */
	int updateSumup(String pzid, String sumup);

	Integer update(PzResult result,String resultId,HttpServletRequest req);

}
