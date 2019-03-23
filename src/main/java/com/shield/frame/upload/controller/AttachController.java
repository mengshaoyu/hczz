package com.shield.frame.upload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/fileupload/*")
public class AttachController {

    @RequestMapping(value = "to_upload")
    public ModelAndView toUpload() {
        return new ModelAndView("fileupload/addnews");
    }

    @RequestMapping(value = "load")
    public ModelAndView load() {
        return new ModelAndView("fileupload/upload");
    }
}
