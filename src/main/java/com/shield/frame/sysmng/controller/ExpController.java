package com.shield.frame.sysmng.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/exp/*")
public class ExpController {

    @RequestMapping("download")
    public void download(String filePath, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        //输出文件
        //解决导出中文名称乱码问题的两种方式
        //方式一 String filename = new String(filePath.getBytes("gb2312"),"ISO8859-1");
        //方式二URLEncoder.encode(***)
        String filename = URLEncoder.encode(
            filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length()), "utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=" + filename);
        OutputStream os = null;
        InputStream inputStream = null;
        try {
            File file = new File(filePath);
            inputStream = new FileInputStream(file);
            os = response.getOutputStream();
            byte[] b = new byte[1024];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            os.close();
            os.flush();
        }
    }
}
