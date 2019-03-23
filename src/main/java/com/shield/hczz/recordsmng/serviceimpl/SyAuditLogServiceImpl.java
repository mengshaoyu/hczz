package com.shield.hczz.recordsmng.serviceimpl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shield.hczz.base.persistence.impl.SyAuditLogMapperImpl;
import com.shield.hczz.recordsmng.qvo.AuditLogVO;
import com.shield.hczz.recordsmng.service.SyAuditLogService;
import com.shield.frame.common.qvo.CommonVO;

@Service
public class SyAuditLogServiceImpl implements SyAuditLogService {
    @Autowired
    private SyAuditLogMapperImpl syAuditLogMapperImpl;

    @Override
    public HashMap<String, Object> getList(HashMap<String, Object> map, String page, String rows) {
        HashMap<String, Object> dataMap = new HashMap<String, Object>();

        // 当前页
        int intPage = Integer.parseInt((page == null || page.equals("0")) ? "1" : page);
        // 每页显示条数
        int intRows = Integer.parseInt((rows == null || rows.equals("0")) ? "10" : rows);

        // 获取总数
        int total = this.getCount(map);

        List<AuditLogVO> list = this.syAuditLogMapperImpl.getList(map, intPage, intRows);

        dataMap.put("total", total);
        dataMap.put("rows", list);

        return dataMap;
    }

    public int getCount(HashMap<String, Object> map) {
        return this.syAuditLogMapperImpl.getCount(map);
    }

    @Override
    public CommonVO expPartCheck(String logPKs) {
        CommonVO commonVO = new CommonVO();
        // 将前台传递的主键组装成List
        int result = 0;
        if (StringUtils.isNotBlank(logPKs)) {
            String[] id = logPKs.split(",");
            List<String> array = Arrays.asList(id);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("logPKs", array);
            result = this.syAuditLogMapperImpl.getCount(map);
        }
        if (result > 0) {
            return commonVO;
        }
        else {
            return commonVO.getError("导出数据不存在！");
        }
    }

    @Override
    public List<AuditLogVO> getList(String logPKs, Integer page, Integer rows) {
        String[] id = logPKs.split(",");
        List<String> array = Arrays.asList(id);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("logPKs", array);

        return this.syAuditLogMapperImpl.getList(map, page, rows);
    }

    @Override
    public int expCheck(HashMap<String, Object> map) {
        return this.syAuditLogMapperImpl.getCount(map);
    }

    @Override
    public List<AuditLogVO> exportAll(HashMap<String, Object> map) {
        return this.syAuditLogMapperImpl.exportAll(map, 0, 0);
    }
}