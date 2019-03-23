package com.shield.hczz.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.shield.frame.utils.ConfigUtil;
import com.shield.hczz.common.SopConstants;


/**
 * <b>功能：附件工具类</b><br> 
 * @version 1.0
 */
public class FileUrlUtil {

    private static final String UPLOADDIR = ConfigUtil.getMsg("fileUpload.tmp");
    private static final String UPLOADHOST = ConfigUtil.getMsg("fileUpload.uploadServer");
    private static final String SYSTEMNAME = ConfigUtil.getMsg("fileUpload.systemName");


    public static List<Map<String, String>> uploadFiles2Local(String ajid,
        HttpServletRequest request, String type) throws ClientProtocolException, IOException {
        //	    String message = ""; // 返回信息
        List<Map<String, String>> returnMessage = new ArrayList<Map<String, String>>(); // 返回所有的上传文件返回值

        // 接收上传文件
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = mRequest.getFileMap();
        // 上传路径
        //String uploadDir = request.getSession().getServletContext().getRealPath("/resource/files/");
        String uploadDir = SopConstants.UPLOAD_DISK_PATH;
        uploadDir += "/" + ajid + "/";
        File file = new File(uploadDir);
        if (!file.exists()) {
            file.mkdirs();
        }
        Map<String, String> result = null;
        int i = 0;
        for (Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator(); it
            .hasNext(); i++) {
            result = new HashMap<String, String>();
            Map.Entry<String, MultipartFile> entry = it.next();
            MultipartFile mFile = entry.getValue();
            // 路径加文件名
            String filePathName = uploadDir + mFile.getOriginalFilename(); // 绝对路径文件
            File srcfile = new File(filePathName);
            if (srcfile.isDirectory()) {
                continue;
            }
            InputStream in = mFile.getInputStream();
            FileOutputStream out = new FileOutputStream(srcfile);
            byte buffer[] = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer)) > 0) {
                //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                out.write(buffer, 0, len);
            }
            in.close();
            out.close();
            result.put("name", mFile.getOriginalFilename());
            result.put("url",
                SopConstants.UPLOAD_ACCESS_PATH + "/" + ajid + "/"
                    + mFile.getOriginalFilename());
            result.put("type", type);
            returnMessage.add(result);
        }
        //AuditLogUtil.addLog(request, ARCHIVETYPE.get(function), "9", "上传附件", "", "0");
        return returnMessage;
    }


    /**
     * <b>功能：文件上传</b><br>
     * @param function 功能名称
     * @param file 上传文件
     * @return CommonVO
     * @throws IOException 
     * @throws ClientProtocolException 
     **/
    public static String upload(String function, File file) throws ClientProtocolException,
        IOException {

        String message = ""; // 返回信息
        // 构造POST请求
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(UPLOADHOST);
        FileBody bin = new FileBody(file);
        StringBody system = new StringBody(SYSTEMNAME, ContentType.MULTIPART_FORM_DATA);
        StringBody subdir = new StringBody(function, ContentType.MULTIPART_FORM_DATA);
        HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("bin", bin)
            .addPart("system", system).addPart("subdir", subdir).build();
        httppost.setEntity(reqEntity);
        CloseableHttpResponse uploadResponse = httpclient.execute(httppost); // 发送请求
        try {
            HttpEntity resEntity = uploadResponse.getEntity(); // 接收返回值
            if (resEntity != null) {
                message = EntityUtils.toString(resEntity, "UTF-8"); // 返回文本
            }
            EntityUtils.consume(resEntity);
        }
        finally {
            uploadResponse.close();
        }

        return message;
    }
}
