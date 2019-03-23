package com.shield.hczz.pztb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shield.frame.base.domain.Attach;
import com.shield.frame.base.domain.User;
import com.shield.frame.base.persistence.AttachMapper;
import com.shield.frame.base.persistence.DeptmentMapper;
import com.shield.frame.utils.DateUtil;
import com.shield.hczz.apply.qvo.PzApplyVO;
import com.shield.hczz.base.domain.ClueInfo;
import com.shield.hczz.base.domain.PzApply;
import com.shield.hczz.base.persistence.CaseInfoMapper;
import com.shield.hczz.base.persistence.ClueInfoMapper;
import com.shield.hczz.base.persistence.HctbMapper;
import com.shield.hczz.base.persistence.PzApplyMapper;
import com.shield.hczz.pztb.dto.CaseInfoDTO;
import com.shield.hczz.pztb.dto.HctbDTO;
import com.shield.hczz.pztb.service.PztbService;

@Service
public class PztbServiceImpl implements PztbService {
    @Autowired
    private CaseInfoMapper caseInfoMapperImpl;
    @Autowired
    private AttachMapper attachMapper;
    @Autowired
    private PzApplyMapper pzApplyMapperImpl;
    @Autowired
    private ClueInfoMapper ClueInfoMapper;
    @Autowired
    private HctbMapper hctbMapper;
    @Autowired
    private DeptmentMapper deptmentMapper;

    /**
     * 保存
     */
    @Override
    @Transactional
    public Map<String, Object> save(HctbDTO hctb, String newaj, User usr) {
        Map<String, Object> map = new HashMap<>();
        try {
            Map<String, Object> param = new HashMap<>();
            param.put("pzid", hctb.getPzApply().getPzApplyId());
            param.put("pzApplyGrade", hctb.getPzApply().getPzApplyGrade());
            List<CaseInfoDTO> cases = hctbMapper.selectCaseInfo(param);
            //如果案件已存在则更新案件
            if (cases.size() > 0) {
                hctb.getCaseInfo().setCaseId(cases.get(0).getCaseId());
                hctbMapper.updatePzApplyByKey(param);
                hctbMapper.deleteClueInfoByPzId(param);
                Map<String, Object> caseParam = new HashMap<>();
                caseParam.put("caseId", hctb.getCaseInfo().getCaseId());
                caseParam.put("caseType", hctb.getCaseInfo().getCaseType());
                hctbMapper.updateCaseInfo(hctb.getCaseInfo());
            }
            else {
                PzApply pzApply = hctb.getPzApply();
                pzApply.setPzApplyNo(DateUtil.getDateToStr(new Date(), "yyyyMM"));
                pzApply.setCaseId(hctb.getCaseInfo().getCaseId());
                pzApply.setCreateBy(usr.getUserId().toString());
                pzApply.setUpdateBy(usr.getUserId().toString());
                pzApply.setActByType("B");
                pzApplyMapperImpl.insertSelective(pzApply);// 存配帧表
                if (Boolean.parseBoolean(newaj)) {// 如果是新案件就存案件，否则就更新案件
                    hctb.getCaseInfo().setUndertakeUnit(deptmentMapper.getById(usr.getDeptId().toString()).getNum());
                    hctb.getCaseInfo().setCreateBy(usr.getUserId().toString());
                    hctb.getCaseInfo().setUpdateBy(usr.getUserId().toString());
                    caseInfoMapperImpl.insertSelective(hctb.getCaseInfo());
                }else{
                    Map<String, Object> caseParam = new HashMap<>();
                    caseParam.put("caseId", hctb.getCaseInfo().getCaseId());
                    caseParam.put("caseType", hctb.getCaseInfo().getCaseType());
                    hctbMapper.updateCaseInfo(hctb.getCaseInfo());
                }
            }
            // 移除空的线索
            hctb.setClueList(removeNullAndAddFile(hctb.getClueList(), hctb.getClueFile()));
            // 存线索
            int clueSort = 0;
            for (ClueInfo clue : hctb.getClueList()) {
                clueSort ++;
                String clueId = UUID.randomUUID().toString().replaceAll("-", "");
                clue.setClueId(clueId);
                clue.setCaseId(hctb.getCaseInfo().getCaseId());
                clue.setCaseNo(hctb.getCaseInfo().getCaseNo());
                clue.setClueType(1);
                clue.setCreateBy(usr.getUserId().toString());
                clue.setUpdateBy(usr.getUserId().toString());
                clue.setPzId(hctb.getPzApply().getPzApplyId());
                clue.setPzType(clue.getPzTypeDetail().substring(0, 1));
                clue.setActByType(clueSort+"");
                ClueInfoMapper.insertSelective(clue);
                if(clue.getLyzm() != null){
                    // 更新附件表的bus_id为线索附件
                    for (String str : clue.getLyzm()) {
                        Attach a = new Attach();
                        a.setAttId(str);
                        a.setBusId(clueId);
                        attachMapper.updateByPK(a);
                    }
                }
            }
            for (String str : hctb.getLajds()) {// 存立案决定书附件
                Attach a = new Attach();
                a.setAttId(str);
                a.setBusId(hctb.getCaseInfo().getCaseId());
                attachMapper.updateByPK(a);
            }
            for (String str : hctb.getSadjb()) {// 存受案登记表附件
                Attach a = new Attach();
                a.setAttId(str);
                a.setBusId(hctb.getCaseInfo().getCaseId());
                attachMapper.updateByPK(a);
            }
            for (String str : hctb.getCqlabgs()) {// 存呈请立案决定书附件
                Attach a = new Attach();
                a.setAttId(str);
                a.setBusId(hctb.getCaseInfo().getCaseId());
                attachMapper.updateByPK(a);
            }
            map.put("code", 0);
            map.put("msg", "保存成功");

            return map;
        }
        catch (Exception e) {
            map.put("code", -1);
            map.put("msg", "保存出错！" + e.getMessage());
            e.printStackTrace();
            return map;
        }
    }
    
    /**
     * 处理前台传来的附件
     * @param clueList
     * @param list
     * @return
     */
    private List<ClueInfo> removeNullAndAddFile(List<ClueInfo> clueList, List<String> list) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : list) {
            String[] ss = str.split("##");
            if (map.containsKey(ss[1])) {
                map.get(ss[1]).add(ss[0]);
            }
            else {
                List<String> l = new ArrayList<>();
                l.add(ss[0]);
                map.put(ss[1], l);
            }
        }
        List<ClueInfo> clue = new ArrayList<>();
        for (int i = 0; i < clueList.size(); i++) {
            clueList.get(i).setLyzm(map.get(String.valueOf(i)));
            if (!StringUtils.isEmpty(clueList.get(i).getClueName())) {
                clue.add(clueList.get(i));
            }
        }
        return clue;
    }
    
    /**
     * 根据案件id查询案件
     */
    @Override
    public Map<String, Object> queryCaseById(String ajid) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> qo = new HashMap<>();
        qo.put("caseNo", ajid);
        List<CaseInfoDTO> list = hctbMapper.selectCaseInfo(qo);
        if (list.size() == 0) {
            result.put("code", -1);
            result.put("msg", "未检索到案件信息，请手动填写!");
            return result;
        }
        qo = new HashMap<>();
        qo.put("busId", list.get(0).getCaseId());

        List<Attach> sadjb = new ArrayList<>();
        List<Attach> lajds = new ArrayList<>();
        List<Attach> cqlajds = new ArrayList<>();
        List<Attach> att = attachMapper.getList(qo);
        for (Attach a : att) {
            if (null != a.getModuleType() && "2".equals(a.getModuleType())) {
                sadjb.add(a);
            }
            else if (null != a.getModuleType() && "3".equals(a.getModuleType())) {
                lajds.add(a);
            }
            else if (null != a.getModuleType() && "4".equals(a.getModuleType())) {
                cqlajds.add(a);
            }
        }
        result.put("sadjb", sadjb);
        result.put("lajds", lajds);
        result.put("cqlabgs", cqlajds);
        result.put("code", 0);
        result.put("msg", "");
        result.put("caseInfo", list.get(0));
        
        /**
         * 添加查询闭环对接的文书
         */
        Map<String, Object> wsCountParam = new HashMap<>();
        wsCountParam.put("ajbh", ajid);
        int lajdscount = hctbMapper.countLajds(wsCountParam);
        int sadjbcount = hctbMapper.countSadjb(wsCountParam);
        result.put("sadjbcount", sadjbcount);
        result.put("lajdscount", lajdscount);
        return result;
    }
    
    /**
     * 查询配帧信息（包含案件、配帧、线索、附件）
     */
    @Override
    public Map<String, Object> loadHctb(String pzid) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> param = new HashMap<>();
        param.put("pzid", pzid);
        List<CaseInfoDTO> cases = hctbMapper.selectCaseInfo(param);
        if (cases.size() == 0) {
            result.put("code", -1);
            return result;
        }
        List<ClueInfo> clueInfos = new ArrayList<>();
        ClueInfo clue = new ClueInfo();
        List<Map<String, Object>> clues = hctbMapper.selectClueInfo(param);
        String clueId = "";
        for (Map<String, Object> map : clues) {
            String clueIdTemp = map.get("CLUE_ID").toString();
            if (!clueIdTemp.equals(clueId)) {
                clueId = clueIdTemp;
                clue = new ClueInfo();
                clueInfos.add(clue);
            }
            clue.setClueId(clueIdTemp);
            clue.setClueName(map.get("CLUE_NAME").toString());
            clue.setClueSource(map.get("CLUE_SOURCE").toString());
            clue.setClueDesc(map.get("CLUE_DESC") == null ? "" : map.get("CLUE_DESC").toString());
            clue.setPzTypeDetail(map.get("PZ_TYPE_DETAIL").toString());
            clue.setPzTypeDetailName(map.get("PZ_TYPE_DETAIL_NAME").toString());
            clue.setPzTypeName(map.get("PZ_TYPE_NAME").toString());
            clue.setPzType(map.get("PZ_TYPE").toString());
            if(null != map.get("ATT_ID") && !map.get("ATT_ID").equals("")){
                Attach att = new Attach();
                att.setAttId(map.get("ATT_ID").toString());
                att.setAttName(map.get("ATT_NAME").toString());
                att.setAttPath(map.get("ATT_PATH").toString());
                clue.getAtt().add(att);
            }
        }
        param = new HashMap<>();
        param.put("busId", cases.get(0).getCaseId());
        List<Attach> flws = attachMapper.getList(param);
        List<Attach> sadjb = new ArrayList<>();
        List<Attach> lajds = new ArrayList<>();
        List<Attach> cqlajds = new ArrayList<>();
        for (Attach a : flws) {
            if (null != a.getModuleType() && "2".equals(a.getModuleType())) {
                sadjb.add(a);
            }
            else if (null != a.getModuleType() && "3".equals(a.getModuleType())) {
                lajds.add(a);
            }
            else if (null != a.getModuleType() && "4".equals(a.getModuleType())) {
                cqlajds.add(a);
            }
        }
        PzApplyVO pv = pzApplyMapperImpl.getById(pzid);
        result.put("code", 0);
        result.put("pzInfo", pv);
        if(pv.getSubmitTime() != null){
            result.put("sqsj", DateUtil.getDateToStr(DateUtil.getStrToDate(pv.getSubmitTime(), "yyyy-MM-dd HH:mm:ss"), "yyyy年MM月dd日"));
        }else{
            result.put("sqsj", "无");
        }
        result.put("aj", cases.get(0));
        result.put("clue", clueInfos);
        result.put("sadjb", sadjb);
        result.put("lajds", lajds);
        result.put("cqlabgs", cqlajds);
        result.put("pzid", cases.get(0).getPzApplyId());
        result.put("ajid", cases.get(0).getCaseId());
        result.put("tasklevel", cases.get(0).getPzApplyGrade());
        
        /**
         * 添加查询闭环对接的文书
         */
        Map<String, Object> wsCountParam = new HashMap<>();
        wsCountParam.put("ajbh", cases.get(0).getCaseNo());
        int lajdscount = hctbMapper.countLajds(wsCountParam);
        int sadjbcount = hctbMapper.countSadjb(wsCountParam);
        result.put("sadjbcount", sadjbcount);
        result.put("lajdscount", lajdscount);
        return result;
    }
}
