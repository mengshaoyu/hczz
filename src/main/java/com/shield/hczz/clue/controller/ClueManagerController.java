package com.shield.hczz.clue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shield.frame.common.qvo.DataGridVO;
import com.shield.hczz.base.domain.VClueListInfo;
import com.shield.hczz.clue.qvo.VClueListInfoQO;
import com.shield.hczz.clue.service.ClueService;

@Controller
@RequestMapping("/clue/*")
public class ClueManagerController {

    @Autowired
    private ClueService clueService;

    @RequestMapping("init")
    public String init() {
        return "clue/clue";
    }

    @ResponseBody
    @RequestMapping("getlist")
    public DataGridVO<VClueListInfo> getlist(VClueListInfoQO qo, String page, String rows) {
        return clueService.getlist(qo, page, rows);
    }

    @RequestMapping("building")
    public String loadInit() {
        return "common/building";
    }
    
    
}
