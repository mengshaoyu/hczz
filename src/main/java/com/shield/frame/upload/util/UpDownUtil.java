package com.shield.frame.upload.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shield.frame.upload.dto.AttachDTO;

public class UpDownUtil {

    /**
     * 文件上传
     * @param request
     * @param busid
     * @param moduleType
     * @return
     */
    public static List<AttachDTO> upload(HttpServletRequest request, int busid, String moduleType) {

        List<AttachDTO> result = null;
        try {
            List<Map<String, Object>> returnValue = FileOperateUtil.upload(request);
            result = new ArrayList<AttachDTO>();
            //判断是否有超出文件大小的项
            int a = 0;
            for (Map<String, Object> item : returnValue) {
                AttachDTO attachDTO = new AttachDTO();
                Object obj1 = item.get("error");
                if (null != obj1 && !obj1.toString().equals("")) {
                    a = 1;
                    attachDTO.setError(obj1.toString());
                    result.add(attachDTO);
                }
            }
            if (a == 1) {
                return result;
            }

            for (Map<String, Object> item : returnValue) {
                AttachDTO attachDTO = new AttachDTO();
                Object obj1 = item.get("attName");
                Object obj2 = item.get("rName");
                Object obj3 = item.get("attpath");
                if (null != obj1 && null != obj2 && null != obj3 && !obj1.toString().equals("")
                    && !obj2.toString().equals("") && !obj3.toString().equals("")) {
                    attachDTO.setAttName(obj1.toString());
                    attachDTO.setAttRname(obj2.toString());
                    attachDTO.setAttPath(obj3.toString());
                    attachDTO.setBusId(String.valueOf(busid));
                    attachDTO.setModuleType(moduleType);
                    result.add(attachDTO);
                }
            }
            return result;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 文件下载
     * @param attname
     * @param path
     * @param request
     * @param response
     */
    public static void download(String attname, String path, HttpServletRequest request,
        HttpServletResponse response) {
        try {
            String ctxPath = request.getSession().getServletContext().getRealPath("");
            String[] s = path.split("/");
            StringBuffer bf = new StringBuffer();
            for (int i = 0; i < s.length; i++) {
                if (i != 1 && i != s.length - 1) {
                    bf.append(s[i]);
                    bf.append("/");
                }
                if (i == s.length - 1) {
                    bf.append(s[i]);
                }
            }
            String uploadpath = ctxPath + bf;
            String contentType = "application/octet-stream";
            FileOperateUtil.download(request, response, uploadpath, contentType, attname);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

}
