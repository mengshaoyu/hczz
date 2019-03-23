package com.shield.frame.sysmng.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shield.frame.sysmng.dto.PermissionDTO;
import com.shield.frame.sysmng.dto.TreeNode;
import com.shield.frame.sysmng.service.PermissionService;

@Controller
@RequestMapping(value = "/permission/*")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * 菜单页面初始化
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "init")
    public String init(ModelMap modelMap) {
        return "manager/tree";
    }

    /**
     * 用户管理查看用户权限
     * @param parent
     * */
    @ResponseBody
    @RequestMapping(value = "getUPermissionTree")
    public List<TreeNode> getUPermisssionTree(String uid) {
        HashMap<String, Object> map = new HashMap<String, Object>();//查询对象map
        map.put("userId", uid);
        List<TreeNode> treeNodes = new ArrayList<TreeNode>();//返回树结点集合
        List<PermissionDTO> list = this.permissionService.getPermissionList(map);//获取数据对象
        if (null != list && list.size() > 0) {
            for (PermissionDTO d : list) {
                TreeNode t = new TreeNode();
                t.setId(d.getPermissionId().toString());
                t.setText(d.getAuthorityName());
                t.setName(d.getAuthorityName());
                t.setParentid((null == d.getParentId() || "".equals(d.getParentId()) ? "" : d
                    .getParentId().toString()));
                t.setpId((null == d.getParentId() || "".equals(d.getParentId()) ? "" : d
                    .getParentId().toString()));
                if ((d.getPermissionId() + "").length() > 4) {//菜单下级结点 不展开
                    t.setOpen(false);
                }
                else {
                    t.setOpen(true);
                }
                t.setChecked(true);
                t.setChkDisabled(true);
                treeNodes.add(t);
            }
            TreeNode t = new TreeNode();
            t.setId("0");
            t.setText("全部");
            t.setName("全部");
            t.setOpen(true);
            t.setChecked(true);
            t.setChkDisabled(true);
            treeNodes.add(t);
        }
        else {
            TreeNode t = new TreeNode();
            t.setId("");
            t.setText("该用户暂无权限！");
            t.setName("该用户暂无权限！");
            t.setpId("");
            t.setOpen(false);
            t.setChecked(false);
            treeNodes.add(t);
        }
        return treeNodes;
    }

    /**
     * 查看用户权限
     * @param parent
     * */
    @ResponseBody
    @RequestMapping(value = "getPermissionTree")
    public List<TreeNode> getPermisssionTree(String uid, String flag) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("userId", uid);

        List<TreeNode> treeNodes = new ArrayList<TreeNode>();
        List<PermissionDTO> list = null;
        List<PermissionDTO> list1 = null;
        if (("1").equals(flag)) {
            list = this.permissionService.getPermission(map);
        }
        else if (("2").equals(flag)) {
            list = this.permissionService.getPermList(map);
        }
        else {
            list = this.permissionService.getPermissionList(map);
        }
        Boolean open = false;
        if (null != list && list.size() > 0) {
            for (PermissionDTO d : list) {
                TreeNode t = new TreeNode();
                t.setId(d.getPermissionId().toString());
                t.setText(d.getAuthorityName());
                t.setName(d.getAuthorityName());
                t.setParentid((null == d.getParentId() || "".equals(d.getParentId()) ? "" : d
                    .getParentId().toString()));
                t.setpId((null == d.getParentId() || "".equals(d.getParentId()) ? "" : d
                    .getParentId().toString()));
                t.setOpen(true);
                if (("2").equals(flag)) {
                    if ("1".equals(d.getCkecked())) {
                        t.setChecked(true);
                        open = true;
                    }
                    else {
                        t.setChecked(false);
                    }
                }
                else {
                    t.setChecked(true);
                    open = true;
                }
                treeNodes.add(t);
            }
            TreeNode t = new TreeNode();
            t.setId("0");
            t.setText("全部");
            t.setName("全部");
            t.setOpen(true);
            if (uid == null || ("").equals(uid)) {
                t.setChecked(false);
            }
            else {
                t.setChecked(open);

            }
            treeNodes.add(t);
        }
        else {
            TreeNode t = new TreeNode();
            t.setId("");
            if (("1").equals(flag) || ("2").equals(flag)) {
                t.setName("该角色暂无权限");
                t.setText("该角色暂无权限");
            }
            else {
                t.setText("该用户暂无权限！");
                t.setName("该用户暂无权限！");
            }
            t.setpId("");
            t.setOpen(false);
            t.setChecked(false);
            treeNodes.add(t);
        }
        return treeNodes;
    }

}
