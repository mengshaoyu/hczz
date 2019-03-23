package com.shield.hczz.rotamng.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shield.frame.common.Constants;
import com.shield.frame.sysmng.qvo.ToolBarVO;
import com.shield.frame.sysmng.service.MenuService;
import com.shield.frame.utils.DateUtil;
import com.shield.hczz.rotamng.qvo.RotaVO;
import com.shield.hczz.rotamng.service.RotaService;

@Controller
@RequestMapping("rota")
public class RotaController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private RotaService rotaService;

    @RequestMapping("init")
    public String init(Model mod) {
        mod.addAttribute("beginTime",
            DateUtil.getOldDateToStr(new Date(), 20, "yyyy-MM-dd HH:mm:ss"));
        mod.addAttribute("endTime", DateUtil.getDateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
        return "rota/rota";
    }

    @ResponseBody
    @RequestMapping("getToolBar")
    public List<ToolBarVO> getToolBar(BigDecimal page, HttpSession session) {

        // 管理员则获取所有Toolbar
        String loginAccount = (String) session.getAttribute(Constants.SESN_USR_ACOUNT);
        if ("admin".equals(loginAccount)) {
            return menuService.getToolBar(page, null);
        }
        return menuService.getToolBar(page,
            (BigDecimal) session.getAttribute(Constants.SESN_USR_UID));
    }

    @ResponseBody
    @RequestMapping("list")
    public Map<String, Object> getList(RotaVO vo, String page, String rows) {
        return rotaService.getList(vo, page, rows);
    }
}
