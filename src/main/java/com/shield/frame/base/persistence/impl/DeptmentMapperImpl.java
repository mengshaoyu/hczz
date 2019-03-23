package com.shield.frame.base.persistence.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.shield.frame.base.persistence.DeptmentMapper;
import com.shield.frame.common.dao.BaseDaoImpl;
import com.shield.frame.sysmng.dto.DeptDTO;
import com.shield.frame.sysmng.qvo.DepartRow;

@Repository
public class DeptmentMapperImpl extends BaseDaoImpl implements DeptmentMapper {

    public int add(DepartRow departRow) {
        return (int) this.create(DeptmentMapper.class.getName() + ".add", departRow);
    }

    public int delDept(String id) {

        return 0;
    }

    public DepartRow getById(String id) {

        return (DepartRow) this.queryForObject(DeptmentMapper.class.getName() + ".checkById", id);
    }

    public List getDeptById(String id) {

        return null;
    }

    public List<DepartRow> getDeptList(Map map) {

        return null;
    }

    public List<DepartRow> getList(DepartRow departRow) {

        return null;
    }

    public List getUserById(String id) {

        return null;
    }

    public int updateByPK(DepartRow departRow) {
        return (int) this.create(DeptmentMapper.class.getName() + ".updateByPK", departRow);
    }

    public String getIdByName(String name) {

        return (String) this.queryForObject(DeptmentMapper.class.getName() + ".getIdByName", name);
    }

    @Override
    public List<DeptDTO> getDeptSelectTree(HashMap<String, Object> map) {

        return this.queryForList(DeptmentMapper.class.getName() + ".getDeptSelectTree", map);
    }

    @Override
    public List<DepartRow> getAllDept() {

        return this.queryForList(DeptmentMapper.class.getName() + ".getAllDept");
    }

    public List<DeptDTO> getDeptByName(Map map) {
        return this.queryForList(DeptmentMapper.class.getName() + ".getDeptByName", map);
    }

    @Override
    public DepartRow getByNo(String no) {
        return (DepartRow) this.queryForObject(DeptmentMapper.class.getName() + ".checkById", no);
    }

    /**
     * <b>功能：根据id和部门编号判断是否重复</b><br>
     * <br>
     * @param map
     * @return DepartRow
     **/
    public DepartRow checkNoById(HashMap<String, Object> map) {
        return (DepartRow) this
            .queryForObject(DeptmentMapper.class.getName() + ".checkNoById", map);
    }

    @Override
    public List<DepartRow> getDeptListByPrior(Map map) {
        // TODO Auto-generated method stub
        return null;
    }
}
