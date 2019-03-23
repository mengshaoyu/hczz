package com.shield.frame.sysmng.serviceimpl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shield.frame.base.persistence.impl.MenuMapperImpl;
import com.shield.frame.sysmng.qvo.MenuQO;
import com.shield.frame.sysmng.qvo.MenuRow;
import com.shield.frame.sysmng.qvo.MenuTreeNode;
import com.shield.frame.sysmng.qvo.MenuVO;
import com.shield.frame.sysmng.qvo.RootMenuVO;
import com.shield.frame.sysmng.qvo.ToolBarVO;
import com.shield.frame.sysmng.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapperImpl menuMapper;

    //@Autowired
    //private MenuDao menuDao;
    //	
    //	@Autowired
    //	private DicdetailMapper dicDetailMapper;

    @Autowired
    private Mapper mapper;

    public List<MenuRow> getList(MenuQO menuQO) {

        HashMap<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("id", menuQO.getId());
        paraMap.put("name", menuQO.getName());
        paraMap.put("type", menuQO.getType());
        paraMap.put("onuse", menuQO.getOnuse());
        return menuMapper.getList(paraMap);
        //return menuDao.getList(menuQO);
    }

    //	public List<DropDownItem> getIconClsList(int dicType) {
    //		return dicDetailMapper.getDropList(dicType);
    //	}

    //	public int add(MenuDTO menuDTO, String creator) {
    //		Menu menu = mapper.map(menuDTO, Menu.class);
    //		menu.setUuid(CommonUtil.getUUID());
    //		menu.setCreator(creator);
    //		menu.setCreatetime(new Date());
    //		
    //		return menuMapper.add(menu);
    //	}

    //	public int edit(MenuDTO menuDTO, String modifier) {
    //		Menu menu = mapper.map(menuDTO, Menu.class);
    //		menu.setModifier(modifier);
    //		menu.setModifytime(new Date());
    //
    //		return menuMapper.updateByPK(menu);
    //	}

    //	public int del(String id, Short type, String modifier) {
    //		// 如果类型不是3，侧验证是否有子项
    //		int sum = 0;
    //		if (type != 3) {
    //			sum = menuMapper.getLeaf(id);
    //		}
    //		
    //		// 符合删除条件，则进行删除操作
    //		if(sum ==0){
    //			HashMap<String, Object> hashMap = new HashMap<String, Object>();
    //			hashMap.put("id", id);
    //			hashMap.put("modifier", modifier);
    //			hashMap.put("modifyTime", new Date());
    //			
    //			sum =menuMapper.delByPK(hashMap);
    //		}else{
    //			sum = -1;
    //		}
    //		
    //		return sum;
    //	}

    public MenuVO getInfo(String id) {
        return menuMapper.getInfo(id);
    }

    public List<RootMenuVO> getRootMenuList(BigDecimal userId) {

        return menuMapper.getRootMenuList(userId);
    }

    public List<MenuTreeNode> getMenuTreeNodeList(String itemId, BigDecimal usrId) {

        return menuMapper.getMenuTreeNodeList(itemId, usrId);
    }

    public List<ToolBarVO> getToolBar(BigDecimal menuId, BigDecimal usrId) {
        return menuMapper.getToolBar(menuId, usrId);
    }
    
    @Override
    public List<ToolBarVO> getToolBarByType(BigDecimal menuId, BigDecimal usrId,String toolbarType) {
        return menuMapper.getToolBarByType(menuId.toString(), usrId, toolbarType);
    }
}
