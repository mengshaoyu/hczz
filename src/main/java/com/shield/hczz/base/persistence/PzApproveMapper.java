package com.shield.hczz.base.persistence;

import java.util.List;
import java.util.Map;

import com.shield.hczz.approve.qvo.PzApproveQO;
import com.shield.hczz.approve.qvo.PzApproveVO;
import com.shield.hczz.base.domain.PzApprove;

public interface PzApproveMapper {

    int add(PzApprove pzApprove);

    List<PzApproveVO> queryApprove(PzApproveQO qo);

    int updateApprove(PzApprove pzApprove);

    List<Map<String, Object>> getApproveUser(String pzid);

    List<PzApproveVO> getSumlist(PzApproveQO qo, String page, String rows);
}
