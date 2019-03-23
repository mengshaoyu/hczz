package com.shield.frame.sysmng.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shield.frame.sysmng.dto.DeptDTO;
import com.shield.frame.sysmng.qvo.DepartRow;

public interface DeptmentService {
    //获得部门列表菜单
    List<DepartRow> getList(DepartRow departRow);

    //添加部门
    int add(DepartRow departRow);

    //编辑部门
    //	int edit(DepartRow departRow);
    //获得部门树
    List<DepartRow> getDeptList(HashMap map);

    //通过ID获得部门信息
    DepartRow getById(String id);

    //通过ID获得部门信息
    DepartRow getByNo(String no);

    DepartRow checkNoById(HashMap<String, Object> map);

    //通过ID编辑部门
    int updateByPK(DepartRow departRow);

    //通过部门ID获得该部门下用户
    List getUserById(String id);

    //通过部门ID获得该部门下子部门
    List getDeptById(String id);

    //通过ID删除部门
    int delDept(String id);

    String getIdByName(String name);

    List<DeptDTO> getDeptSelectTree(HashMap<String, Object> map);

    //获得所有部门
    List<DepartRow> getAllList();

    List<DeptDTO> getDeptByName(Map map);

    List<DepartRow> getDeptListByPrior(HashMap<String, Object> map);
}
