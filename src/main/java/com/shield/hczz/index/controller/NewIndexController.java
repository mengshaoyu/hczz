package com.shield.hczz.index.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/newIndex")
public class NewIndexController {
    
    @RequestMapping("/init")
    public ModelAndView init(){
        ModelAndView mav = new ModelAndView("index/newIndex");
        return mav;
    }

    @RequestMapping("/indexFrame")
    public ModelAndView indexFrame(){
        ModelAndView mav = new ModelAndView("index/indexFrame");
        return mav;
    }


}
