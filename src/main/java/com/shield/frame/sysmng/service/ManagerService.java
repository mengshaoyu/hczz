package com.shield.frame.sysmng.service;

import java.util.List;

import com.shield.frame.sysmng.qvo.MenuTreeNode;
import com.shield.frame.sysmng.qvo.RootMenuVO;

public interface ManagerService {

    /**
     * 取得用户权限下的功能目录
     *
     * @param usrUUID
     * @return
     */
    List<RootMenuVO> getRootMenuList(String usrUUID);

    /**
     * 取得左侧菜单列表
     *
     * @param itemId
     * @param usrUUID
     * @return
     */
    List<MenuTreeNode> getMenuTreeNodeList(String itemId, String usrUUID);
}
