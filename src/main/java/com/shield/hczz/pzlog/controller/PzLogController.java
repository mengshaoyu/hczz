package com.shield.hczz.pzlog.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shield.hczz.pzlog.service.PzLogService;

@Controller
@RequestMapping("/pzlog/*")
public class PzLogController {

    @Autowired
    private PzLogService pzLogService;

    @RequestMapping("init")
    public String init() {
        return "apply/applyList";
    }

    @ResponseBody
    @RequestMapping("initData")
    public Map<String, Object> initData(String pzid, HttpServletRequest request) {
        return pzLogService.initData(pzid, request);
    }
}
