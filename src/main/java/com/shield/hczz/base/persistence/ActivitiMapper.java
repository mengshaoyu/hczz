package com.shield.hczz.base.persistence;

import java.util.List;
import java.util.Map;

import com.shield.hczz.base.domain.FlowWait;
import com.shield.hczz.pztb.dto.AssigneeCompleteInfo;

public interface ActivitiMapper {
    List<AssigneeCompleteInfo> assigneeCompleteInfo(Map<String,Object> map);
    Map<String,Object> getComment(Map<String,Object> map);
    List<FlowWait> flowWait(Map<String,Object> map);
    List<FlowWait> getFlowInfo(Map<String,Object> map);
}
