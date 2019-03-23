package com.shield.hczz.analyze.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shield.hczz.base.controller.SopBaseController;

@Controller
@RequestMapping("/analyze/*")
public class AnalyzeController extends SopBaseController {
    
    @RequestMapping("init")
    public String init() {
        return "analyze/analyze";
    }
}
