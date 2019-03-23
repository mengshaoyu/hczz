package com.shield.frame.sysmng.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shield.frame.common.Constants;
import com.shield.frame.common.qvo.BaseVO;
import com.shield.frame.sysmng.dto.RoleDTO;
import com.shield.frame.sysmng.dto.TreeNode;
import com.shield.frame.sysmng.service.RoleService;
import com.shield.frame.sysmng.service.UserRoleService;
import com.shield.frame.utils.AuditLogUtil;
import com.shield.frame.utils.OsCacheUtil;
import com.shield.frame.utils.SylogicCacheUtil;

@Controller
@RequestMapping(value = "/role/*")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;

    /**
     * 菜单页面初始化
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "init")
    public String init(ModelMap modelMap) {
        return "sysmng/role";
    }

    /**
     * 获取list
     * @param parent
     * */
    @ResponseBody
    @RequestMapping(value = "getList")
    public List<TreeNode> getList() {
        List<TreeNode> treeNodes = new ArrayList<TreeNode>();
        List<RoleDTO> list = null;
        list = this.roleService.getList();
        if (null != list && list.size() > 0) {
            for (RoleDTO d : list) {
                TreeNode t = new TreeNode();
                t.setId(d.getRoleId().toString());
                t.setText(d.getRolename());
                t.setName(d.getRolename());
                t.setParentid("0");
                t.setpId("0");
                treeNodes.add(t);
            }
            TreeNode t = new TreeNode();
            t.setId("0");
            t.setText("全部");
            t.setName("全部");
            t.setParentid("");
            t.setpId("");
            t.setOpen(true);
            treeNodes.add(t);
        }
        return treeNodes;
    }

    /**
     * 用户授予角色
     * @param uids,rids
     * */
    @ResponseBody
    @RequestMapping(value = "addRole")
    public BaseVO addRole(String uids, String rids, HttpServletRequest request) {
        BaseVO baseVO = new BaseVO();
        try {
            String[] uidrr = uids.split(",");
            String[] ridrr = rids.split(",");
            int sum = -1;
            HashMap<String, Object> map = new HashMap<String, Object>();
            sum = userRoleService.save(uidrr, ridrr);
            // 授予角色成功
            if (sum >= 0) {
                baseVO = new BaseVO("comm_001");
                AuditLogUtil.addLog(request, "角色管理", "9", "授予用户角色", "", "0");
                // 授予角色失败
            }
            else {
                baseVO = new BaseVO("comm_002");
                AuditLogUtil.addLog(request, "角色管理", "9", "授予用户角色", "", "1");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return baseVO;
    }

    //	/**
    //	 * 获得角色菜单
    //	 * 
    //	 * @return
    //	 */
    //	@ResponseBody
    //	@RequestMapping(value="roleList")
    //	public List<RoleDTO> roleList(){
    //		List<RoleDTO> list = roleService.getList();
    //		return list;
    //	}

    /**
     * 取得角色分页一览表
     *
     * @param modelMap
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "rolePList")
    public HashMap<String, Object> getPaginationList(String page, String rows) {
        //当前页   
        int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);
        //每页显示条数   
        int intRows = Integer.parseInt((rows == null || rows == "0") ? "10" : rows);
        //开始条数
        HashMap<String, Object> datalist = new HashMap<String, Object>();
        HashMap<String, Object> map = new HashMap<String, Object>();
        List<RoleDTO> list = this.roleService.getPaginationList(map, intPage, intRows);
        int total = this.roleService.getCount();
        datalist.put("rows", list);
        datalist.put("total", total);
        return datalist;
    }

    /**
     * 添加角色
     * 
     * @param roleDTO
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "add")
    public BaseVO add(RoleDTO roleDTO, BindingResult result, HttpServletRequest request) {
        BaseVO baseVO = new BaseVO();
        try {
            if (result.hasErrors()) {
                baseVO.setMsgCode("comm_101");
                return baseVO;
            }
            int num = this.roleService.add(roleDTO);
            if (num >= 0) {
                baseVO.setMsgCode("comm_001");
                AuditLogUtil.addLog(request, "角色管理", "3", "角色新增", "", "0");
            }
            else {
                baseVO.setMsgCode("comm_002");
                AuditLogUtil.addLog(request, "角色管理", "3", "角色新增", "", "1");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return baseVO;
    }

    /**
     * 编辑角色
     * 
     * @param roleDTO
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "edit")
    public BaseVO edit(@Valid RoleDTO roleDTO, BindingResult result, HttpServletRequest request) {
        BaseVO baseVO = new BaseVO();

        try {
            if (result.hasErrors()) {
                baseVO.setMsgCode("comm_101");
                return baseVO;
            }
            int num = this.roleService.edit(roleDTO);
            if (num >= 0) {
                baseVO.setMsgCode("comm_001");
                AuditLogUtil.addLog(request, "角色管理", "4", "角色编辑", "", "0");
                //清理缓存
                //SylogicCacheUtil.removeElement(Constants.INTERCEPT_URL_PREX+roleDTO.getRoleId());
            }
            else {
                baseVO.setMsgCode("comm_002");
                AuditLogUtil.addLog(request, "角色管理", "4", "角色编辑", "", "1");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return baseVO;
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "del")
    public BaseVO del(String ids, HttpSession session, HttpServletRequest request) {
        BaseVO baseVO = new BaseVO();
        try {
            String[] idrr = ids.split(",");
            int sum = -1;
            sum = roleService.del(idrr);
            // 删除成功
            if (sum >= 0) {
                baseVO = new BaseVO("comm_001");
                AuditLogUtil.addLog(request, "角色管理", "5", "角色删除", "", "0");
                // 删除失败
            }
            else {
                baseVO = new BaseVO("comm_002");
                AuditLogUtil.addLog(request, "角色管理", "5", "角色删除", "", "1");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return baseVO;
    }

    /**
     * 角色授予权限
     * @param uids,rids
     * */
    @ResponseBody
    @RequestMapping(value = "addPow")
    public BaseVO addPow(String uid, String rids, HttpServletRequest request) {
        BaseVO baseVO = new BaseVO();
        try {
        	SylogicCacheUtil.removeElement(Constants.INTERCEPT_URL_PREX + uid);
            String[] ridrr = rids.split(",");
            int sum = -1;
            sum = this.roleService.save(uid, ridrr);
            // 删除成功
            if (sum >= 0) {
                baseVO = new BaseVO("comm_001");
                AuditLogUtil.addLog(request, "角色管理", "9", "授予角色权限", "", "0");
                // 删除失败
            }
            else {
                baseVO = new BaseVO("comm_002");
                AuditLogUtil.addLog(request, "角色管理", "9", "授予角色权限", "", "1");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return baseVO;
    }

    /**
     * 添加角色数据权限
     * @param authType , roloeIds 
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addDataPow")
    public BaseVO addDataPow(String authType, String ids, HttpServletRequest request) {
        BaseVO baseVO = new BaseVO();
        try {
            String[] idss = ids.split(",");
            int sum = -1;
            sum = this.roleService.addDataPow(idss, authType);
            // 删除成功
            if (sum >= 0) {
                baseVO = new BaseVO("comm_001");
                AuditLogUtil.addLog(request, "角色管理", "9", "授予用户数据角色", "", "0");
                // 删除失败
            }
            else {
                baseVO = new BaseVO("comm_002");
                AuditLogUtil.addLog(request, "角色管理", "9", "授予用户数据角色", "", "1");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return baseVO;

    }

    /**
     * 验证要删除的角色是否已被用户使用
     * 
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "CheckDept")
    public BaseVO CheckDept(String ids) {
        BaseVO baseVO = new BaseVO();
        String[] idss = ids.split(",");
        List<RoleDTO> userList = roleService.getUserById(idss);
        Map map = new HashMap();
        if (userList.size() == 0) {
            baseVO.setMsgInfo("0");
        }
        else {
            for (RoleDTO d : userList) {
                baseVO.setMsgInfo(d.getRolename().toString());
            }
        }
        return baseVO;
    }

    /**
     * 验证要保存的角色名是否已存在
     * 
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "checkRole")
    public BaseVO checkRole(RoleDTO R) {
        BaseVO baseVO = new BaseVO();

        List<RoleDTO> userList = roleService.getUser(R);
        if (userList.size() == 0) {
            baseVO.setMsgInfo("0");
        }
        else {
            baseVO.setMsgInfo("1");
        }
        return baseVO;
    }

    /**
     * 验证要操作的角色是否已被删除
     * 
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "checkRoleById")
    public BaseVO checkRoleById(String ids) {
        BaseVO baseVO = new BaseVO();
        baseVO.setMsgInfo("1");
        List<RoleDTO> userList = new ArrayList();
        String[] idss = ids.split(",");
        for (int i = 0; i < idss.length; i++) {
            userList = roleService.getRole(idss[i]);
            if (userList.size() > 0 && userList != null) {
                RoleDTO d = (RoleDTO) userList.get(0);
                if (d.getDeleteBy() != null && !("").equals(d.getDeleteBy())) {
                    String roleName = d.getRolename().toString();
                    baseVO.setMsgCode(roleName);
                    baseVO.setMsgInfo("0");
                }
            }
            else {
                baseVO.setMsgInfo("0");
            }
        }
        return baseVO;
    }

}
