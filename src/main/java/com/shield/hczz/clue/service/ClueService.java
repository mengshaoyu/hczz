package com.shield.hczz.clue.service;

import java.util.List;
import java.util.Map;

import com.shield.frame.common.qvo.DataGridVO;
import com.shield.hczz.base.domain.ClueInfo;
import com.shield.hczz.base.domain.VClueListInfo;
import com.shield.hczz.clue.qvo.VClueListInfoQO;

public interface ClueService {

    /**
     * 查询线索记录
     * 
     * @param qo
     *            查询类
     * @return
     */

    DataGridVO<VClueListInfo> getlist(VClueListInfoQO qo, String page, String rows);

    int getCount(Map<String, Object> map);

	int synchroClueDb(String applyId);

	int updateClueList(List<ClueInfo> clueList);

	int updateResultByPz(String pzid);

	ClueInfo getClueById(String clueId);

}
