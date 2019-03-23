package com.shield.frame.sysmng.service;

import java.math.BigDecimal;
import java.util.List;

import com.shield.frame.sysmng.qvo.MenuQO;
import com.shield.frame.sysmng.qvo.MenuRow;
import com.shield.frame.sysmng.qvo.MenuTreeNode;
import com.shield.frame.sysmng.qvo.MenuVO;
import com.shield.frame.sysmng.qvo.RootMenuVO;
import com.shield.frame.sysmng.qvo.ToolBarVO;

public interface MenuService {

    /**
     * 取得菜单一览表
     * 
     * @param menuQO
     * @return
     */
    List<MenuRow> getList(MenuQO menuQO);

    //	/**
    //	 * 取得图标下拉列表内容
    //	 * 
    //	 * @param dicType
    //	 *            字典编号
    //	 * @return
    //	 */
    //	List<DropDownItem> getIconClsList(int dicType);
    //
    //	/**
    //	 * 添加菜单
    //	 * 
    //	 * @param menuDTO
    //	 * @param creator
    //	 * @return
    //	 */
    //	int add(MenuDTO menuDTO, String creator);
    //
    //	/**
    //	 * 修改菜单
    //	 * 
    //	 * @param menuDTO
    //	 * @param modifier
    //	 * @return
    //	 */
    //	int edit(MenuDTO menuDTO, String modifier);
    //
    //	/**
    //	 * 根据菜单UUID删除菜单
    //	 * 
    //	 * @param id
    //	 * @param type
    //	 * @param modifier
    //	 * @return
    //	 */
    //	int del(String id, Short type, String modifier);

    /**
     * 取得菜单的详情
     * 
     * @param id
     * @return
     */
    MenuVO getInfo(String id);

    /**
     * 取得用户权限下的功能目录
     * 
     * @param usrId
     * @return
     */
    List<RootMenuVO> getRootMenuList(BigDecimal usrId);

    /**
     * 取得左侧菜单列表
     * 
     * @param itemId
     * @param usrUUID
     * @return
     */
    List<MenuTreeNode> getMenuTreeNodeList(String itemId, BigDecimal usrId);

    /**
     * 取得用户在此页面拥有的功能按钮
     *
     * @param menuId 页面ID
     * @param usrId  用户ID
     * @return
     */
    List<ToolBarVO> getToolBar(BigDecimal menuId, BigDecimal usrId);
    
    List<ToolBarVO> getToolBarByType(BigDecimal menuId, BigDecimal usrId, String toolbarType);

}
