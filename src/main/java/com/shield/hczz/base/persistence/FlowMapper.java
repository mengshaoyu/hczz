package com.shield.hczz.base.persistence;

import java.util.List;
import java.util.Map;

import com.shield.frame.base.domain.User;
import com.shield.hczz.flow.qvo.FlowQO;
import com.shield.hczz.flow.qvo.FlowVO;

public interface FlowMapper {

    FlowVO getStartFlow(FlowQO qo);

    FlowVO getById(String flowId);

    List<User> getApprovers(Map<String, Object> param);

}
