package com.shield.frame.upload.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class FileOperateUtil {
    private static final String REALNAME = "attName";//文件原名称
    private static final String STORENAME = "rName";//文件重命名
    private static final String ATTPATH = "attpath";//文件保存的相对路径
    private static final String SIZE = "size";
    private static final String SUFFIX = "suffix";
    private static final String CONTENTTYPE = "contentType";
    private static final String CREATETIME = "createTime";
    private static final String UPLOADDIR = "resource/upload/";
    private static final String ERROR = "error";

    //	private static String rename(String name) {
    //
    //		Long now = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss")
    //				.format(new Date()));
    //		Long random = (long) (Math.random() * now);
    //		String fileName = now + "" + random;
    //
    //		if (name.indexOf(".") != -1) {
    //			fileName += name.substring(name.lastIndexOf("."));
    //		}
    //
    //		return fileName;
    //	}
    /**
     * 文件重命名
     */
    private static String rename(String name) {
        Long now = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        String fileName = now + name;
        return fileName;
    }

    //	private static String zipName(String name) {
    //		String prefix = "";
    //		if (name.indexOf(".") != -1) {
    //			prefix = name.substring(0, name.lastIndexOf("."));
    //		} else {
    //			prefix = name;
    //		}
    //		return prefix + ".zip";
    //	}
    /*****
     * 文件上传
     */
    public static List<Map<String, Object>> upload(HttpServletRequest request) throws Exception {

        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = mRequest.getFileMap();
        //上传路径
        String uploadDir = request.getSession().getServletContext().getRealPath("/")
            + FileOperateUtil.UPLOADDIR;
        String uploadpath = request.getSession().getServletContext().getContextPath() + "/"
            + FileOperateUtil.UPLOADDIR;

        //获取当前日期
        Date dt = new Date();
        SimpleDateFormat matter1 = new SimpleDateFormat("yyyyMMdd");
        String date = matter1.format(dt);
        String path = uploadDir + date + "/";//绝对路径
        String path1 = uploadpath + date + "/";//相对路径
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        String fileName = null;
        int i = 0;
        //判断文件大小
        int a = 0;
        //		List<String>  l=new ArrayList<String>();
        StringBuffer bf = new StringBuffer();
        for (Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator(); it
            .hasNext(); i++) {
            Map.Entry<String, MultipartFile> entry = it.next();
            MultipartFile mFile = entry.getValue();
            if (mFile.getSize() > 10000000) {
                a = 1;
                String fname = mFile.getOriginalFilename();
                bf.append(fname + "文件大小已经超过10M!; \n");
            }
        }
        if (a == 1) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(FileOperateUtil.ERROR, bf.toString());
            result.add(map);
            return result;
        }
        //执行上传
        for (Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator(); it
            .hasNext(); i++) {

            Map.Entry<String, MultipartFile> entry = it.next();
            MultipartFile mFile = entry.getValue();

            fileName = mFile.getOriginalFilename();

            String rName = rename(fileName);
            //路径加文件名
            String filePathName = path + rName;//绝对路径文件
            String filePathName1 = path1 + rName;//相对路径文件
            OutputStream outputStream = (OutputStream) (new BufferedOutputStream(
                new FileOutputStream(filePathName)));
            FileCopyUtils.copy(mFile.getInputStream(), outputStream);

            Map<String, Object> map = new HashMap<String, Object>();
            // 固定参数值对
            map.put(FileOperateUtil.REALNAME, fileName);
            map.put(FileOperateUtil.STORENAME, rName);
            map.put(FileOperateUtil.ATTPATH, filePathName1);
            map.put(FileOperateUtil.SIZE, new File(filePathName).length());
            map.put(FileOperateUtil.CONTENTTYPE, "application/octet-stream");
            map.put(FileOperateUtil.CREATETIME, new Date());
            result.add(map);
        }
        return result;
    }

    /****
     * 文件下载
     * @param request
     * @param response
     * @param downLoadPath
     * @param contentType
     * @param realName
     * @throws Exception
     */
    public static void download(HttpServletRequest request, HttpServletResponse response,
        String downLoadPath, String contentType, String realName) throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        long fileLength = new File(downLoadPath).length();

        response.setContentType(contentType);
        response.setHeader("Content-disposition",
            "attachment; filename=" + new String(realName.getBytes("GBK"), "ISO8859-1"));
        response.setHeader("Content-Length", String.valueOf(fileLength));

        bis = new BufferedInputStream(new FileInputStream(downLoadPath));
        bos = new BufferedOutputStream(response.getOutputStream());
        byte[] buff = new byte[2048];
        int bytesRead;
        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
            bos.write(buff, 0, bytesRead);
        }
        bis.close();
        bos.close();
    }

    public static void downloadFromUrl(HttpServletRequest request, HttpServletResponse response,
        String url, String fileName) throws Exception {

        String dirtemp = request.getSession().getServletContext().getRealPath("");
        URL httpurl = new URL(url);
        File tempFile = new File(dirtemp + fileName);
        FileUtils.copyURLToFile(httpurl, tempFile);

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        long fileLength = tempFile.length();

        String contentType = "application/octet-stream";
        response.setContentType(contentType);
        response.setHeader("Content-disposition",
            "attachment; filename=" + new String(fileName.getBytes("GBK"), "ISO8859-1"));
        response.setHeader("Content-Length", String.valueOf(fileLength));

        bis = new BufferedInputStream(new FileInputStream(tempFile));
        bos = new BufferedOutputStream(response.getOutputStream());
        byte[] buff = new byte[2048];
        int bytesRead;
        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
            bos.write(buff, 0, bytesRead);
        }
        bis.close();
        bos.close();
    }

    public static String getFileNameFromUrl(String url) {
        String name = new Long(System.currentTimeMillis()).toString() + ".X";
        int index = url.lastIndexOf("/");
        if (index > 0) {
            name = url.substring(index + 1);
            if (name.trim().length() > 0) {
                return name;
            }
        }
        return name;
    }

}
