package com.shield.frame.base.persistence;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import com.shield.frame.base.domain.Menu;
import com.shield.frame.sysmng.qvo.MenuRow;
import com.shield.frame.sysmng.qvo.RootMenuVO;
import com.shield.frame.sysmng.qvo.ToolBarVO;

public interface MenuMapper {
    int delByPK(BigDecimal menuId);

    int add(Menu record);

    Menu getByPK(BigDecimal menuId);

    List<MenuRow> getList(HashMap<String, Object> hashMap);

    int updateByPK(Menu record);

    /**
     * 取得左侧根级菜单
     *
     * @param userId
     * @return
     */
    List<RootMenuVO> getRootMenuList(BigDecimal userId);

    /**
     * 取得用户在此页面上拥有的功能按钮
     *
     * @param menuId 页面ID
     * @param usrId  用户ID
     * @return
     */
    List<ToolBarVO> getToolBar(BigDecimal menuId, BigDecimal usrId);
    
    List<ToolBarVO> getToolBarByType(String menuId, BigDecimal userId,String toolbarType);
}