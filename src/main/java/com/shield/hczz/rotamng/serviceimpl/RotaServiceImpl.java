package com.shield.hczz.rotamng.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shield.hczz.base.persistence.RotaMapper;
import com.shield.hczz.rotamng.qvo.RotaVO;
import com.shield.hczz.rotamng.service.RotaService;

@Service
public class RotaServiceImpl implements RotaService {

    @Autowired
    private RotaMapper rotaMapperImpl;

    @Override
    public Map<String, Object> getList(RotaVO vo, String page, String rows) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put("userName", vo.getUserName());

        // 当前页
        int intPage = Integer.parseInt((page == null || page.equals("0")) ? "1" : page);
        // 每页显示条数
        int intRows = Integer.parseInt((rows == null || rows.equals("0")) ? "10" : rows);

        // 获取总数
        int total = rotaMapperImpl.getCount(paramMap);

        List<RotaVO> list = rotaMapperImpl.getList(paramMap, intPage, intRows);

        dataMap.put("total", total);
        dataMap.put("rows", list);

        return dataMap;
    }

}
