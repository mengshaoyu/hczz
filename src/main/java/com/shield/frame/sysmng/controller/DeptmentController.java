package com.shield.frame.sysmng.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shield.frame.common.qvo.BaseVO;
import com.shield.frame.sysmng.dto.DeptDTO;
import com.shield.frame.sysmng.dto.TreeNode;
import com.shield.frame.sysmng.qvo.DepartRow;
import com.shield.frame.sysmng.service.DeptmentService;
import com.shield.frame.utils.AuditLogUtil;

@Controller
@RequestMapping(value = "/deptment/*")
public class DeptmentController {
    @Autowired
    private DeptmentService deptmentService;

    /**
     * 菜单页面初始化
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "init")
    public String init(ModelMap modelMap) {
        return "sysmng/dept";
    }

    /**
     * 验证添加/编辑的部门编号是否已存在
     * 
     * @param name
     * @return
     */

    @ResponseBody
    @RequestMapping(value = "checkDeptno")
    public BaseVO checkDeptno(String deptno, String oldid, String id) {
        BaseVO baseVO = new BaseVO();
        if (oldid != null && oldid.equals(deptno)) {
            baseVO.setMsgInfo("0");
            return baseVO;
        }
        if (StringUtils.isNotBlank(id)) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", id);
            map.put("no", deptno);
            DepartRow deptList = deptmentService.checkNoById(map);
            if (deptList != null) {
                baseVO.setMsgInfo("1");
            }
            else {
                baseVO.setMsgInfo("0");
            }
        }
        else {
            DepartRow deptList = deptmentService.getByNo(deptno);
            if (deptList != null) {
                baseVO.setMsgInfo("1");
            }
            else {
                baseVO.setMsgInfo("0");
            }
        }

        return baseVO;
    }

    /**
     * 取得菜单一览表
     *
     * @param modelMap
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getList")
    public List<DepartRow> getList(DepartRow departRow) {
        List<DepartRow> list = new ArrayList<DepartRow>();
        list = deptmentService.getList(departRow);

        return list;
    }

    /**
     * 添加部门
     * 
     * @DepartRow
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "add")
    public BaseVO add(@Valid DepartRow departRow, HttpServletRequest request) {
        BaseVO baseVO = new BaseVO();
        try {
            int num = deptmentService.add(departRow);
            if (num > 0) {
                baseVO.setMsgCode("comm_001");
                AuditLogUtil.addLog(request, "部门管理", "3", "部门新增", "", "0");
            }
            else {
                baseVO.setMsgCode("comm_002");
                AuditLogUtil.addLog(request, "部门管理", "3", "部门新增", "", "1");
            }
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return baseVO;
    }

    /**
     * 通过ID获得部门记录信息
     * 
     * @param id
     * @return 
     */
    @ResponseBody
    @RequestMapping(value = "getById")
    public DepartRow getById(String id, HttpSession session) {
        DepartRow deptRow = deptmentService.getById(id);

        return deptRow;
    }

    /**
     * 获得部门树
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getDeptTree")
    public List<TreeNode> getDeptTree(String fid) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("parent", fid);
        List<TreeNode> treeNodes = new ArrayList<TreeNode>();
        List<DepartRow> list = null;
        list = this.deptmentService.getDeptList(map);
        if (null != list && list.size() > 0) {
            for (DepartRow d : list) {
                TreeNode t = new TreeNode();
                t.setId(d.getId().toString());
                t.setText(d.getName());
                t.setName(d.getName());
                t.setParentid((null == d.getPid() || "".equals(d.getPid()) ? "" : d.getPid()
                    .toString()));
                if ("open".equals(d.getState())) {
                    t.setState("open");
                    t.setOpen(true);
                }
                else {
                    t.setState("closed");
                    t.setOpen(false);
                }
                treeNodes.add(t);
            }
        }
        return treeNodes;
    }
    
    @ResponseBody
    @RequestMapping(value = "getDeptTreeByPrior")
    public List<TreeNode> getDeptTreeByPrior(String fid) {
        fid = "370203000000";
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("deptId", fid);
        List<TreeNode> treeNodes = new ArrayList<TreeNode>();
        List<DepartRow> list = this.deptmentService.getDeptListByPrior(map);
        TreeNode tn = new TreeNode();
        if(list.size() > 0){
            tn.setId(list.get(0).getId());
            tn.setText(list.get(0).getName());
            tn.setChildren(createComboTreeChildren(list, fid, tn.getChildren()));
            treeNodes.add(tn);
        }
        return treeNodes;
    }

    private List<TreeNode> createComboTreeChildren(List<DepartRow> list, String fid, List<TreeNode> treeNodes) {
        for(DepartRow departRow : list){ 
            if(departRow.getPid().equals(fid)){
                TreeNode tn = new TreeNode();
                tn.setId(departRow.getId());
                tn.setText(departRow.getName());
                tn.setChildren(createComboTreeChildren(list, departRow.getId(), tn.getChildren()));
                treeNodes.add(tn);
            }
        }
        return treeNodes;
    }  
    
    
    
    /**
     * 编辑部门
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "upd")
    public BaseVO upd(DepartRow departRow, BindingResult result, HttpServletRequest request) {
        BaseVO baseVO = null;
        try {
            int sum = deptmentService.updateByPK(departRow);
            // 编辑成功
            if (sum >= 0) {
                baseVO = new BaseVO("comm_001");
                AuditLogUtil.addLog(request, "部门管理", "4", "部门编辑", departRow.getId(), "0");
                // 编辑失败
            }
            else {
                baseVO = new BaseVO("comm_002");
                AuditLogUtil.addLog(request, "部门管理", "4", "部门编辑", departRow.getId(), "1");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return baseVO;
    }

    /**
     * 删除部门
     * 
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "del")
    public BaseVO del(String id, HttpSession session, HttpServletRequest request) {
        BaseVO baseVO = new BaseVO();
        try {
            int sum = -1;
            sum = deptmentService.delDept(id);
            // 删除成功
            if (sum >= 0) {
                baseVO = new BaseVO("comm_001");
                AuditLogUtil.addLog(request, "部门管理", "5", "部门删除", id, "0");
                // 删除失败
            }
            else {
                baseVO = new BaseVO("comm_002");
                AuditLogUtil.addLog(request, "部门管理", "5", "部门删除", id, "1");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return baseVO;
    }

    /**
     * 验证要删除的部门下是否含有子部门或用户
     * 
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "CheckDept")
    public BaseVO CheckDept(String id) {
        BaseVO baseVO = new BaseVO();
        List userList = deptmentService.getUserById(id);
        List deptList = deptmentService.getDeptById(id);
        if (userList.size() > 0 || deptList.size() > 0) {
            baseVO = new BaseVO("comm_001");
        }
        else {
            baseVO = new BaseVO("comm_003");
        }

        return baseVO;
    }

    /**
     * 获取部门树
     * @param parent
     * */
    @ResponseBody
    @RequestMapping(value = "getDeptSelectTree")
    public List<TreeNode> getDeptSelectTree(String fid) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("parent", fid);
        List<TreeNode> treeNodes = new ArrayList<TreeNode>();
        List<DeptDTO> list = null;
        list = this.deptmentService.getDeptSelectTree(map);
        if (null != list && list.size() > 0) {
            for (DeptDTO d : list) {
                TreeNode t = new TreeNode();
                t.setId(d.getDeptId().toString());
                t.setText(d.getDeptName());
                t.setName(d.getDeptName());
                t.setParentid((null == d.getParentId() || "".equals(d.getParentId()) ? "" : d
                    .getParentId().toString()));
                if ("1".equals(d.getLeaf())) {
                    t.setState("open");
                    t.setOpen(true);
                }
                else {
                    t.setState("closed");
                    t.setOpen(false);
                }
                treeNodes.add(t);
            }
        }
        return treeNodes;
    }

    /**
     * 验证添加/编辑的部门名是否已存在
     * 
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "checkDname")
    public BaseVO checkDname(String name, String id) {
        BaseVO baseVO = new BaseVO();
        Map map = new HashMap();
        map.put("name", name);
        map.put("id", id);
        List<DeptDTO> deptList = deptmentService.getDeptByName(map);
        if (deptList.size() > 0) {
            baseVO.setMsgInfo("1");
        }
        else {
            baseVO.setMsgInfo("0");
        }

        return baseVO;
    }
}
