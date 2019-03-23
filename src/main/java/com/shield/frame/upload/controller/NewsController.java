package com.shield.frame.upload.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shield.frame.upload.dto.AttachDTO;
import com.shield.frame.upload.service.AttachService;
import com.shield.frame.upload.util.UpDownUtil;

@Controller
@RequestMapping(value = "/news/*")
public class NewsController {

    @Autowired
    private AttachService attachService;

    @RequestMapping(value = "to_upload")
    public ModelAndView toUpload() {
        return new ModelAndView("fileupload/addnews");
    }

    @RequestMapping(value = "to_download")
    public ModelAndView downUpload() {
        return new ModelAndView("fileupload/download");
    }

    @RequestMapping(value = "upload")
    public ModelAndView upload(HttpServletRequest request) throws Exception {

        int busid = 1213123;
        String moduleType = "邮件";
        List<AttachDTO> list = UpDownUtil.upload(request, busid, moduleType);
        //判断是否有超出规定文件大小的项
        if (list != null) {
            AttachDTO dto = list.get(0);
            String error = dto.getError();
            if (null != error && !error.equals("")) {
                Map<String, Object> map = new HashMap<String, Object>();
                request.getSession().setAttribute("error", error);
                map.put("result", error);
                return new ModelAndView("fileupload/error");
            }
            else {
                attachService.save(list);
                return new ModelAndView("fileupload/sucess");
            }
        }
        else {
            return new ModelAndView("fileupload/sucess");
        }

    }

    @RequestMapping(value = "download")
    public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String attname = "第二研发部_20140402.xls";
        String path = "/Skynet/resource/upload/20140418/20140418145316第二研发部_20140402.xls";

        UpDownUtil.download(attname, path, request, response);

    }

}