package com.shield.frame.sysmng.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shield.frame.base.persistence.DeptmentMapper;
import com.shield.frame.base.persistence.impl.DeptmentMapperImpl;
import com.shield.frame.sysmng.dto.DeptDTO;
import com.shield.frame.sysmng.qvo.DepartRow;
import com.shield.frame.sysmng.service.DeptmentService;

@Service
public class DeptmentServiceImpl implements DeptmentService {

    @Autowired
    private DeptmentMapper deptmentMapper;
    @Autowired
    private DeptmentMapperImpl deptmentMapperImpl;
    @Autowired
    private Mapper mapper;

    public List<DepartRow> getList(DepartRow departRow) {
        return deptmentMapper.getList(departRow);
    }

    public int add(DepartRow departRow) {
        String pid = departRow.getPid();
        int num = deptmentMapperImpl.add(departRow);
        return num;
    }

    public DepartRow getByNo(String no) {
        return deptmentMapperImpl.getByNo(no);
    }

    public DepartRow checkNoById(HashMap<String, Object> map) {
        return deptmentMapperImpl.checkNoById(map);
    }

    public List<DepartRow> getDeptList(HashMap map) {
        List<DepartRow> list = deptmentMapper.getDeptList(map);
        return list;
    }

    public DepartRow getById(String id) {
        return deptmentMapper.getById(id);
    }

    public int updateByPK(DepartRow departRow) {
        int num = deptmentMapperImpl.updateByPK(departRow);
        return num;
    }

    public int delDept(String id) {
        int sum = deptmentMapper.delDept(id);
        return sum;
    }

    public List getDeptById(String id) {
        List list = deptmentMapper.getDeptById(id);
        return list;
    }

    public List getUserById(String id) {
        List list = deptmentMapper.getUserById(id);
        return list;
    }

    public String getIdByName(String name) {

        return this.deptmentMapperImpl.getIdByName(name);
    }

    @Override
    public List<DeptDTO> getDeptSelectTree(HashMap<String, Object> map) {

        return this.deptmentMapperImpl.getDeptSelectTree(map);
    }

    @Override
    public List<DepartRow> getAllList() {

        return this.deptmentMapperImpl.getAllDept();
    }

    @Override
    public List<DeptDTO> getDeptByName(Map map) {
        List<DeptDTO> list = deptmentMapperImpl.getDeptByName(map);
        return list;
    }

    @Override
    public List<DepartRow> getDeptListByPrior(HashMap<String, Object> map) {
        List<DepartRow> list = deptmentMapper.getDeptListByPrior(map);
        return list;
    }

}
