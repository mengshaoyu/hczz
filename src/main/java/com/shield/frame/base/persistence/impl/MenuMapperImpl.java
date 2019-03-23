package com.shield.frame.base.persistence.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.shield.frame.base.domain.Menu;
import com.shield.frame.base.persistence.MenuMapper;
import com.shield.frame.common.dao.BaseDaoImpl;
import com.shield.frame.sysmng.qvo.MenuRow;
import com.shield.frame.sysmng.qvo.MenuTreeNode;
import com.shield.frame.sysmng.qvo.MenuVO;
import com.shield.frame.sysmng.qvo.RootMenuVO;
import com.shield.frame.sysmng.qvo.ToolBarVO;

@Repository
public class MenuMapperImpl extends BaseDaoImpl implements MenuMapper {

    public int add(Menu record) {

        return 0;
    }

    public int updateByPK(Menu record) {
        return 0;
    }

    public Menu getByPK(BigDecimal menuId) {
        return (Menu) this.queryForObject(MenuMapper.class.getName() + ".getByPK", menuId);
    }

    public int delByPK(BigDecimal menuId) {

        return 0;
    }

    public MenuVO getInfo(String id) {
        return (MenuVO) this.queryForObject(MenuMapper.class.getName() + ".getInfo",
            new BigDecimal(id));
    }

    public int getLeaf(String uuid) {
        return (Integer) this.queryForObject(MenuMapper.class.getName() + ".getLeaf", uuid);
    }

    public List<MenuRow> getList(HashMap<String, Object> hashMap) {
        List<MenuRow> menuList = this.queryForListPagination(MenuMapper.class.getName()
            + ".getList", hashMap, 1, 10);
        return menuList;
    }

    public List<MenuTreeNode> getMenuTreeNodeList(String itemId, BigDecimal usrId) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("itemId", itemId);
        hashMap.put("usrId", usrId);

        return this.queryForList(MenuMapper.class.getName() + ".getMenuTreeNodeList", hashMap);
    }

    public List<RootMenuVO> getRootMenuList(BigDecimal userId) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("usrId", userId);
        return this.queryForList(MenuMapper.class.getName() + ".getRootMenuList", hashMap);
    }

    public List<ToolBarVO> getToolBar(BigDecimal menuId, BigDecimal usrId) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("menuId", menuId);
        hashMap.put("usrId", usrId);

        return this.queryForList(MenuMapper.class.getName() + ".getToolBar", hashMap);
    }

	public List<ToolBarVO> getButtonShow(String menuId, String userId,String toolbar) {
		// TODO Auto-generated method stub
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("menuId", menuId);
        hashMap.put("usrId", userId);
        hashMap.put("toolbar", toolbar);
        
		return this.queryForList(MenuMapper.class.getName() + ".getButtonShow", hashMap);
	}

    @Override
    public List<ToolBarVO> getToolBarByType(String menuId, BigDecimal userId,String toolbarType) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("menuId", menuId);
        param.put("userId", userId);
        param.put("toolbarType", toolbarType);
        return this.queryForList(MenuMapper.class.getName() + ".getToolBarByType", param);
    }
}
