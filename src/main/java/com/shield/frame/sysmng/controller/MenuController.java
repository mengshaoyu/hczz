package com.shield.frame.sysmng.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shield.frame.sysmng.qvo.MenuQO;
import com.shield.frame.sysmng.qvo.MenuRow;
import com.shield.frame.sysmng.qvo.MenuVO;
import com.shield.frame.sysmng.service.MenuService;

@Controller
@RequestMapping(value = "/menu/*")
public class MenuController {
    @Autowired
    private MenuService menuService;

    /**
     * 菜单页面初始化
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "init")
    public String init(ModelMap modelMap) {
        return "sysmng/menu";
    }

    /**
     * 取得菜单一览表
     *
     * @param modelMap
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getList")
    public List<MenuRow> getList(MenuQO menuQO) {
        return menuService.getList(menuQO);
    }

    //	/**
    //	 * 取得图标列表
    //	 *
    //	 * @return
    //	 */
    //	@ResponseBody
    //    @RequestMapping(value = "getIconClsList")
    //    public List<DropDownItem> getIconClsList(){
    //        // 取得图标下拉框内容
    //        return menuService.getIconClsList(Constants.DIC_TYPE_ICONCLS);
    //    }

    /**
     * 添加菜单
     *
     * @param menuDTO 菜单信息
     * @param result 自动验证的结果
     * @param request
     * @return
     */
    //	@ResponseBody
    //    @RequestMapping(value = "add")
    //	public BaseVO add(@Valid MenuDTO menuDTO, BindingResult result, HttpServletRequest request) {
    //		BaseVO baseVO = new BaseVO();
    //		if(result.hasErrors()) {
    //            baseVO.setMsgCode("comm_101");
    //            return baseVO;
    //        }
    //
    //		String usrUUID = (String)request.getSession().getAttribute(Constants.SESN_USR_UID);
    //		
    //		// 添加菜单
    //    	int num = menuService.add(menuDTO, usrUUID);
    //    	
    //    	if(num > 0){
    //    		baseVO.setMsgCode("comm_001");
    //    	}else{
    //    		baseVO.setMsgCode("comm_002");
    //    	}
    //	
    //    	return baseVO;
    //	}

    /**
     * 修改菜单
     *
     * @param menuDTO
     * @param result
     * @param request
     * @return
     */
    //	@ResponseBody
    //	@RequestMapping(value = "edit")
    //	public BaseVO edit(@Valid MenuDTO menuDTO, BindingResult result, HttpServletRequest request) {
    //		BaseVO baseVO = new BaseVO();
    //		if(result.hasErrors()) {
    //            baseVO.setMsgCode("comm_101");
    //            return baseVO;
    //        }
    //		
    //		String usrUUID = (String)request.getSession().getAttribute(Constants.SESN_USR_UID);
    //		
    //		// 添加菜单
    //    	int num = menuService.edit(menuDTO, usrUUID);
    //    	
    //    	if(num > 0){
    //    		baseVO.setMsgCode("comm_001");
    //    	}else{
    //    		baseVO.setMsgCode("comm_002");
    //    	}
    //		
    //		return baseVO;
    //	}

    /**
     * 删除菜单
     *
     * @param id
     * @param type
     * @param session
     * @return
     */
    //	@ResponseBody
    //	@RequestMapping(value = "del")
    //	public BaseVO del(String id, Short type, HttpSession session) {
    //		BaseVO baseVO = null;
    //		int sum = menuService.del(id, type, (String)session.getAttribute(Constants.SESN_USR_UID));
    //		
    //		// 存在叶子节点
    //		if(sum == -1){
    //			baseVO = new BaseVO("menu_001");
    //			
    //	    // 删除成功
    //		}else if (sum > 0) {
    //			baseVO = new BaseVO("comm_001");
    //	
    //		// 删除失败
    //		}else {
    //			baseVO = new BaseVO("comm_002");
    //		}
    //		
    //		return baseVO;
    //	}

    /**
     * 取得菜单的详情
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getInfo")
    public MenuVO getInfo(String id) {
        return menuService.getInfo(id);
    }
}
