package com.shield.hczz.clue.serviceimpl;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shield.frame.common.qvo.DataGridVO;
import com.shield.hczz.base.domain.ClueInfo;
import com.shield.hczz.base.domain.VClueListInfo;
import com.shield.hczz.base.persistence.ClueInfoMapper;
import com.shield.hczz.base.persistence.impl.ClueApplyMapperImpl;
import com.shield.hczz.base.persistence.impl.VClueListInfoMapperImpl;
import com.shield.hczz.clue.qvo.VClueListInfoQO;
import com.shield.hczz.clue.service.ClueService;

@Service
public class ClueServiceImpl implements ClueService {

    @Autowired
    private VClueListInfoMapperImpl vClueListInfoMapperImpl;
    @Autowired
    private ClueApplyMapperImpl  clueApplyMapperImpl;
    @Autowired
    private ClueInfoMapper  clueInfoMapper;
    
    @Override
    public DataGridVO<VClueListInfo> getlist(VClueListInfoQO qo, String page, String rows) {
        DataGridVO<VClueListInfo> result = new DataGridVO<VClueListInfo>();
        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> param = BeanUtils.describe(qo);
            param.put("isTotal", true);
            int total = this.getCount(param);
            result.setTotal(total);
            // 当前页
            int intPage = Integer.parseInt((page == null || page.equals("0")) ? "1" : page);
            // 每页显示条数
            int intRows = Integer.parseInt((rows == null || rows.equals("0")) ? "10" : rows);
            List<VClueListInfo> list = vClueListInfoMapperImpl.getList(param, intPage, intRows);
            result.setRows(list);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int getCount(Map<String, Object> map) {
        return vClueListInfoMapperImpl.getCount(map);
    }

    /**
     * 同步线索库：配侦申请表中提到的线索内容，均同步到线索库
     * @param applyId
     */
    @Override
	public int synchroClueDb(String applyId) {
		// TODO Auto-generated method stub
		return this.clueApplyMapperImpl.synchroClue(applyId);
	}
    /**
     * 
     * @param clueList
     */
    @Override
	public int updateClueList( List<ClueInfo> clueList) {
    	int res=0;
    	for(int i=0;i<clueList.size();i++){
    		res=this.clueApplyMapperImpl.updateFlowAuxi(clueList.get(i));
    	}
		// TODO Auto-generated method stub
		return res;
	}

    /**
     * 同步线索库：反馈记录
     */
	@Override
	public int updateResultByPz(String pzid) {
		// TODO Auto-generated method stub
		return this.clueApplyMapperImpl.updateResultByPz(pzid);
	}

	/**
	 * 依据线索ID，获取线索内容
	 * @param	clueId	线索ID
	 */
	@Override
	public ClueInfo getClueById(String clueId) {
		// TODO Auto-generated method stub
		return this.clueInfoMapper.getByPK(clueId);
	}
}
