package com.shield.hczz.utils;

import java.io.InputStream;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * <b>功能：http工具</b><br>
 * @version 1.0
 */
public class HttpUtil {

    public static final String HTTP_RESULT = "httpResult";
    public static final String HTTP_CONTENT = "httpContent";
    public static final String HTTP_CONTENTLENGTH = "httpContentLength";
    public static final String HTTP_CONTENTTYPE = "httpContentType";

    /**
     * <b>功能：解析浏览器参数</b><br>
     * @param request
     * @return
     * @throws Exception
     *             HttpServletRequest
     **/
    public static HttpServletRequest decodeParam(HttpServletRequest request) throws Exception {
        @SuppressWarnings("unchecked")
        Enumeration<String> enume = request.getParameterNames();
        String key = "";
        while (enume.hasMoreElements()) {
            key = enume.nextElement().toString();
            request.setAttribute(key, URLDecoder.decode(request.getParameter(key), "UTF-8"));
        }
        return request;
    }

    /**
     * <b>功能：文件下载</b><br>
     * @param url
     * @return InputStream
     **/
    public static Map<String, Object> download(String url) {
        Map<String, Object> map = new HashMap<String, Object>();
        InputStream is = null;
        boolean result = false;
        try {
            CloseableHttpClient client = HttpClients.createDefault(); // 构造客户端请求资源
            HttpGet httpget = new HttpGet(url);
            HttpResponse response = client.execute(httpget);
            int statusCode = response.getStatusLine().getStatusCode(); // HTTP状态码
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                is = entity.getContent(); // 返回资源体
                result = true;
                map.put(HTTP_CONTENTLENGTH, entity.getContentLength()); // 资源长度
                map.put(HTTP_CONTENTTYPE, entity.getContentType()); // 资源类型
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        map.put(HTTP_RESULT, result);
        map.put(HTTP_CONTENT, is);
        return map;
    }

    /**
     * <b>功能：查看网络资源是否存在</b><br>
     * @param url
     * @return int
     **/
    public static int checkExsits(String url) {
        int status = 0;
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response1 = httpclient.execute(httpGet);
            status = response1.getStatusLine().getStatusCode(); // HTTP状态码
        }
        catch (Exception e) {
            e.printStackTrace();
            status = 0;
        }
        return status;
    }

    public static void main(String[] args) {
        
    }
}
