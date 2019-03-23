package com.shield.frame.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileDownloadUtil {

    /**
     *@description 文件下载
     * @param filePath 文件地址
     * 					request 请求对象
     * 					response 响应对象
     * */
    public static void download(String filePath, String filename, HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        //输出文件
        //解决导出中文名称乱码问题的两种方式
        //方式一 String filename = new String(filePath.getBytes("gb2312"),"ISO8859-1");
        //方式二URLEncoder.encode(***)
        filename = URLEncoder.encode(filename, "utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=" + filename);
        OutputStream os = null;
        InputStream inputStream = null;
        File file = new File(filePath);
        inputStream = new FileInputStream(file);
        os = response.getOutputStream();
        byte[] b = new byte[1024];
        int length;
        while ((length = inputStream.read(b)) > 0) {
            os.write(b, 0, length);
        }
        os.close();
        os.flush();
    }

}
