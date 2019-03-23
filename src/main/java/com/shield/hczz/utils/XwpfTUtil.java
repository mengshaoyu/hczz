package com.shield.hczz.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;

import sun.misc.BASE64Encoder;

import com.shield.frame.base.domain.Attach;
import com.shield.frame.sysmng.dto.UserDTO;
import com.shield.hczz.apply.qvo.PzApplyVO;
import com.shield.hczz.base.domain.ClueInfo;
import com.shield.hczz.base.domain.PzResult;
import com.shield.hczz.pzsp.dto.WordDataDTO;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class XwpfTUtil {

    /**
     * 关闭输入流
     * @param is
     */
    private static void closeStream(InputStream is) {
        if (is != null) {
            try {
                is.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭输出流
     * @param os
     */
    private static void closeStream(OutputStream os) {
        if (os != null) {
            try {
                os.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void downApply(String templatePath, HttpServletResponse response, WordDataDTO wdd) {
        Map<String, Object> mainMap = new HashMap<>();
        mainMap.put("pzApplyNo", checkNull(wdd.getPzApplyNo()));
        mainMap.put("acceptUnit", checkNull(wdd.getAcceptUnit()));
        mainMap.put("sqTime", checkNull(wdd.getSqTime()));
        mainMap.put("caseNo", checkNull(wdd.getCaseNo()));
        mainMap.put("caseName", checkNull(wdd.getCaseName()));
        mainMap.put("caseType", checkNull(wdd.getCaseType()));
        mainMap.put("acceptDate", checkNull(wdd.getAcceptDate()));
        mainMap.put("incidentDate", checkNull(wdd.getIncidentDate()));
        mainMap.put("caseDesc", checkNull(wdd.getCaseDesc()));
        mainMap.put("pzType", checkNull(wdd.getPzType()));
        mainMap.put("pzApplyGrade", checkNull(wdd.getPzApplyGrade()));
        mainMap.put("clueName", checkNull(wdd.getClueName()));
        mainMap.put("clueAtt", checkNull(wdd.getClueAtt()));
        mainMap.put("flws", checkNull(wdd.getFlws()));
        mainMap.put("username", checkNull(wdd.getUsername()));
        mainMap.put("mobilePhone", checkNull(wdd.getMobilePhone()));
        mainMap.put("fgldsp", checkNull(wdd.getFgldsp()));
        mainMap.put("zbzsp", checkNull(wdd.getZbzsp()));
        mainMap.put("sprsqr", GetImageStrFromUrl(wdd.getSprsqr()));
        mainMap.put("sprsqrtext", GetImageStrFromUrl(wdd.getFgldspSign()));
        mainMap.put("sqsj", checkNull(wdd.getSqsj()));
        mainMap.put("fgldspsj", checkNull(wdd.getFgldspsj()));
        mainMap.put("zbzspsj", checkNull(wdd.getZbzspsj()));
        mainMap.put("img1", (null==wdd.getZbzspSign())?null:GetImageStrFromUrl(wdd.getZbzspSign()));
        mainMap.put("img2", (null==wdd.getDeptSign())?null:GetImageStrFromUrl(wdd.getDeptSign()));
        mainMap.put("img3", (null==wdd.getFgldspSign())?null:GetImageStrFromUrl(wdd.getFgldspSign()));
        
        Configuration configuration = new Configuration();
        String templateName = "apply.ftl";
        //设置编码
        configuration.setDefaultEncoding("UTF-8");

        //ftl模板文件统一放至 com.lun.template 包下面
        configuration.setClassForTemplateLoading(XwpfTUtil.class, "/com/shield/hczz/template");

        //获取模板 
        try {
            Template template = configuration.getTemplate(templateName);
            template.process(mainMap, response.getWriter());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void downApply1(String templatePath, HttpServletResponse response, WordDataDTO wdd) {
        try {
            InputStream is = new FileInputStream(templatePath);
            HWPFDocument doc = new HWPFDocument(is);
            Range range = doc.getRange();
            range.replaceText("${pzApplyNo}", checkNull(wdd.getPzApplyNo()));
            range.replaceText("${acceptUnit}", checkNull(wdd.getAcceptUnit()));
            range.replaceText("${sqTime}", checkNull(wdd.getSqTime()));
            range.replaceText("${caseNo}", checkNull(wdd.getCaseNo()));
            range.replaceText("${caseName}", checkNull(wdd.getCaseName()));
            range.replaceText("${caseType}", checkNull(wdd.getCaseType()));
            range.replaceText("${acceptDate}", checkNull(wdd.getAcceptDate()));
            range.replaceText("${incidentDate}", checkNull(wdd.getIncidentDate()));
            range.replaceText("${caseDesc}", checkNull(wdd.getCaseDesc()));
            range.replaceText("${pzType}", checkNull(wdd.getPzType()));
            range.replaceText("${pzApplyGrade}", checkNull(wdd.getPzApplyGrade()));
            range.replaceText("${clueName}", checkNull(wdd.getClueName()));
            range.replaceText("${clueAtt}", checkNull(wdd.getClueAtt()));
            range.replaceText("${flws}", checkNull(wdd.getFlws()));
            range.replaceText("${username}", checkNull(wdd.getUsername()));
            range.replaceText("${mobilePhone}", checkNull(wdd.getMobilePhone()));
            range.replaceText("${fgldsp}", checkNull(wdd.getFgldsp()));
            range.replaceText("${zbzsp}", checkNull(wdd.getZbzsp()));
            range.replaceText("${sprsqr}", checkNull(wdd.getSprsqr()));
            range.replaceText("${sprsqrtext}", checkNull(wdd.getSprsqrtext()));
            range.replaceText("${sqsj}", checkNull(wdd.getSqsj()));
            range.replaceText("${fgldspsj}", checkNull(wdd.getSqsj()));
            range.replaceText("${zbzspsj}", checkNull(wdd.getSqsj()));
            range.replaceText("${img1}", (null==wdd.getDeptSign())?null:GetImageStrFromUrl(wdd.getDeptSign()));
            range.replaceText("${img2}", (null==wdd.getZbzspSign())?null:GetImageStrFromUrl(wdd.getZbzspSign()));
            range.replaceText("${img3}", (null==wdd.getFgldspSign())?null:GetImageStrFromUrl(wdd.getFgldspSign()));
            OutputStream os = response.getOutputStream();
            //把doc输出到输出流中
            doc.write(os);
            closeStream(os);
            closeStream(is);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
    private String checkNull(String str){
        if(StringUtils.isEmpty(str)){
            return " ";
        }
        return str.replaceAll("<BR>", "<w:br />").replaceAll("<br>", "<w:br />");
    }
    
    /**
     * 反馈报告生成（数据填充）
     * @param pzApplyVO 配侦申请信息
     * @param clueList 线索列表（反馈列表）
     * @param userList 查询人列表
     * @return
     */
    public ByteArrayOutputStream uploadReply(PzApplyVO pzApplyVO, List<ClueInfo> clueList,List<UserDTO> userList) {
        //反馈表
        try {
            Map<String, Object> mainMap = BeanUtils.describe(pzApplyVO);
            for(ClueInfo info : clueList){
                List<PzResult> results = info.getPzResult();
                for(PzResult res : results){
                    List<Attach> atts = res.getAtt();
                    StringBuffer sb = new StringBuffer();
                    for(Attach att : atts){
                        sb.append(att.getAttName());
                        sb.append(" ");
                    }
                    System.out.println(sb.length()==0);
                    if(sb.length()>0){
                    	sb.deleteCharAt(sb.length()-1);
                    }
                    String listStr = sb.toString();
                    res.setAttchlist("".equals(listStr)?null:listStr);
                }
            }
            mainMap.put("clueList", clueList);
            mainMap.put("userlist", userList);
            //创建配置实例 
            @SuppressWarnings("deprecation")
            Configuration configuration = new Configuration();
            String templateName = "reply.ftl";
            //设置编码
            configuration.setDefaultEncoding("UTF-8");

            //ftl模板文件统一放至 com.lun.template 包下面
            configuration.setClassForTemplateLoading(XwpfTUtil.class, "/com/shield/hczz/template");

            //获取模板 
            Template template = configuration.getTemplate(templateName);
            Writer out = new StringWriter();
            template.process(mainMap, out);
            String str = out.toString();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            //关闭流  
            baos.write(str.getBytes("UTF-8"));

            System.out.println("完成");
            //关闭流
            out.flush();
            out.close();
            baos.close();
            return baos;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**  
     * 客户端下载word  
     * @param response  
     * @param bytes  
     * @param filename  
     */
    public static void renderWord(HttpServletResponse response, final byte[] bytes,
        final String filename) {
        setFileDownloadHeader(response, filename);
        if (null != bytes) {
            response.setHeader("Content-Length", String.valueOf(bytes.length));
            try {
                response.getOutputStream().write(bytes);
                response.getOutputStream().flush();
            }
            catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }

    /**  
     *  设置让浏览器弹出下载对话框的Header  
     * @param response  
     * @param fileName  
     * @param fileType  
     */
    public static void setFileDownloadHeader(HttpServletResponse response, String fileName) {
        try {
            // 中文文件名支持    
            String encodedfileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
            response.setContentType("application/msword");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedfileName
                + ".doc\"");
        }
        catch (UnsupportedEncodingException e) {
        }
    }
    
    public static String GetImageStrFromUrl(String imgURL) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        String imgTemp = "";
        for(int i=0;i<imgURL.length();i++){
            String charat = String.valueOf(imgURL.charAt(i));
            Matcher m = p.matcher(charat);
            if(m.find()){
                try {
                    imgTemp += URLEncoder.encode(charat, "UTF-8");
                }
                catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }else{
                imgTemp += charat;
            }
        }
        String s = "";
        try {  
            // 创建URL  
            URL url = new URL(imgTemp);  
            // 创建链接  
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();  
            conn.setRequestMethod("GET");  
            conn.setConnectTimeout(5 * 1000);  
            InputStream inStream = conn.getInputStream();
            byte[] data = readInputStream(inStream);  
            BASE64Encoder encode = new BASE64Encoder();  
            s = encode.encode(data); 
        } catch (Exception e) {  
            e.printStackTrace();  
            return s;
        }  
        return s;
    }  
    
    private static byte[] readInputStream(InputStream inStream) throws Exception{  
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        //创建一个Buffer字符串  
        byte[] buffer = new byte[1024];  
        //每次读取的字符串长度，如果为-1，代表全部读取完毕  
        int len = 0;  
        //使用一个输入流从buffer里把数据读取出来  
        while( (len=inStream.read(buffer)) != -1 ){  
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度  
            outStream.write(buffer, 0, len);  
        }  
        //关闭输入流  
        inStream.close();  
        //把outStream里的数据写入内存  
        return outStream.toByteArray();  
    }
    
    public static void main(String[] args) throws UnsupportedEncodingException {
        String imgURL = "http://127.0.0.1:8080/";
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        String imgTemp = "";
        for(int i=0;i<imgURL.length();i++){
            String charat = String.valueOf(imgURL.charAt(i));
            Matcher m = p.matcher(charat);
            if(m.find()){
                imgTemp += URLEncoder.encode(charat, "UTF-8");
            }else{
                imgTemp += charat;
            }
        }
        
        imgURL = imgTemp;
        System.out.println(imgTemp);
        try {  
            // 创建URL  
            URL url = new URL(imgURL);  
            // 创建链接  
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();  
            conn.setRequestMethod("GET");  
            conn.setConnectTimeout(5 * 1000);  
            InputStream inStream = conn.getInputStream();
            try {
                byte[] data = readInputStream(inStream);  
                BASE64Encoder encode = new BASE64Encoder();  
                String s = encode.encode(data); 
                System.out.println(s);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }
}
