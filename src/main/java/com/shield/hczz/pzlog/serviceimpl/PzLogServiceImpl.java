package com.shield.hczz.pzlog.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shield.frame.base.persistence.impl.UserMapperImpl;
import com.shield.hczz.base.persistence.impl.PzTaskLogMapperImpl;
import com.shield.hczz.common.SopConstants;
import com.shield.hczz.flow.qvo.TaskFlowVO;
import com.shield.hczz.pzlog.service.PzLogService;

@Service
public class PzLogServiceImpl implements PzLogService {

    @Autowired
    private PzTaskLogMapperImpl pzTaskLogMapperImpl;
    
    @Autowired
    private UserMapperImpl userMapperImpl;

    @Override
    public Map<String, Object> initData(String pzid, HttpServletRequest request) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 根据配侦ID，获取配侦日志及当前待办环节（将待办事项放置到最新配侦日志对象中）
     * @param	配侦主键
     * return	配侦日志集合，含待办环节
     */
    @Override
    public List<TaskFlowVO> getLogsById(String pzApplyId,String key) {
    	
    	List<TaskFlowVO> flowlist=new ArrayList<TaskFlowVO>();
    	List<TaskFlowVO> list=pzTaskLogMapperImpl.getLogsById(pzApplyId);
    	
    	if(null!=list){
    		
    		//待反馈环节，关联线索信息
    		List<TaskFlowVO> flow;
    		boolean flag=false;
    		if(!"".equals(key)&&SopConstants.FLOW_DFK.equals(key)){
    			flag=true;
    			flow=this.getDealNowConnClue(pzApplyId);
    		}else{
    			flow=this.getDealNow(pzApplyId);
    		}
    		
    		if(null!=flow){
    			TaskFlowVO task=flow.get(0);//当前待办环节
				flow.remove(0);
				task.setFlowList(flow);
				
    			for(int i=0;i<list.size();i++){
    				if(flag&&list.get(i).getStatus().equals(SopConstants.FLOW_DFK)){//若配侦日志中有与最新配侦日志对应环节一样
    					flow.add(list.get(i));
    				}else{
    					flowlist.add(list.get(i));
    				}
    			}
    			flowlist.add(0, task);
    		}else{
    			flowlist=list;
    		}
    	}
    	
        return flowlist;
    }

    private List<TaskFlowVO> getDealNowConnClue(String pzid) {
		// TODO Auto-generated method stub
    	List<TaskFlowVO> list=this.pzTaskLogMapperImpl.getDealNowConnClue(pzid);
		
		if(null!=list&&list.size()>0&&null!=list.get(0)){
			
			return list;
		}
		
		return null;
	}

	/**
     * 当前待办环节情况获取
     * @param	配侦主键
     * @return	待办环节情况，转换为配侦日志集合
     */
    @Override
	public List<TaskFlowVO> getDealNow(String pzid) {
		// TODO Auto-generated method stub
		List<TaskFlowVO> list=this.pzTaskLogMapperImpl.getDealNow(pzid);
		
		if(null!=list&&list.size()>0&&null!=list.get(0)){
			
			return list;
		}
		
		return null;
	}

    /**
     * 根据配侦ID，获取配侦日志及当前待办环节（将待办事项与配侦日志集合合并）
     * @param	配侦主键
     * return	配侦日志集合，含待办环节
     */
	@Override
	public List<TaskFlowVO> getTaskFlow(String pzApplyId) {
		// TODO Auto-generated method stub
		List<TaskFlowVO> list=pzTaskLogMapperImpl.getLogsById(pzApplyId);
    	
    	if(null!=list){
    		List<TaskFlowVO> flow=this.getDealNow(pzApplyId);
    		if(null != flow){
    		    list.addAll(flow);
    		}
    	}
		return list;
	}

    @Override
    public List<Integer> getPoliceByPzid(String pzid) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("status", SopConstants.FLOW_DFK);
        param.put("pzid", pzid);
        return pzTaskLogMapperImpl.getPoliceByPzid(param);
    }

    /**
     * 获取该配侦涉及的所有中心民警的警种
     * @param pzid 配侦ID
     * @return
     */
    @Override
    public Set<String> getPoliceTypeByPzid(String pzid) {
        List<Integer> ids = this.getPoliceByPzid(pzid);
        Set<String> sets = new HashSet<String>();
        if(null != ids && ids.size()>0){
            for(Integer id : ids){
                String typeStr = userMapperImpl.getPoliceTypeById(String.valueOf(id));
                if(null != typeStr){
                    String[] types = typeStr.split(",");
                    for(String type : types){
                        sets.add(type);
                    }
                }
            }
        }
        return sets;
    }

    
}
