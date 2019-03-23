package com.shield.frame.index.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shield.frame.index.qvo.LoginVO;
import com.shield.frame.index.service.HBCALoginService;
import com.shield.spring.AutoLog;

import sun.misc.BASE64Encoder;

/**
 * CA登录前生成随机数
 * @project cas
 * @filename HBCreateCALoginRandomAction.java
 */
@Controller
@RequestMapping(value = "caLogin")
public class HBCALoginRandomAndCaLoginCheckController {

    private static final Logger LOGGER = LoggerFactory
        .getLogger(HBCALoginRandomAndCaLoginCheckController.class);

    @Value("#{propertyConfig['cauthURL']}")
    private String cauthURL;
    @Value("#{propertyConfig['cappId']}")
    private String cappId;
    @Autowired
    private HBCALoginService caloginService;

    /**
     * 创建证书随机数，并获取客户端控件版本信息
     * @project cas
     * @method createCARandom
     * @param request
     * @param response
     * @return  LoginVO
     */
    @ResponseBody
    @RequestMapping(value = "createCARandom", method = RequestMethod.POST)
    @AutoLog(desc = "创建证书随机数")
    public LoginVO createCARandom(HttpServletRequest request, HttpServletResponse response)
        throws Exception {

        LoginVO loginvo = new LoginVO();
        String randNum = this.generateRandomNum();
        if (randNum == null || randNum.trim().equals("")) {
            LOGGER.error("证书认证数据不完整！");
            loginvo.setMsgInfo("证书认证数据不完整！");
        }
        loginvo.setSysChoose(randNum);
        /**************************
         * 第三步 服务端返回认证原文   *
         **************************/
        // 设置认证原文到session，用于程序向后传递，通讯报文中使用
        request.getSession().setAttribute("original_data", randNum);

        // 设置认证原文到页面，给页面程序提供参数，用于产生认证请求数据包
        request.setAttribute("original", randNum);

        return loginvo;
    }

    /**
     * 产生认证原文
     */
    private String generateRandomNum() {
        /**************************
         * 第二步 服务端产生认证原文   *
         **************************/
        String num = "1234567890abcdefghijklmnopqrstopqrstuvwxyz";
        int size = 6;
        char[] charArray = num.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < size; i++) {
            sb.append(charArray[((int) (Math.random() * 10000) % charArray.length)]);
        }
        return sb.toString();
    }

    /**
     * CA数字证书认证
     * @project cas
     * @filename HBCALoginCheckAction.java
     */
    @ResponseBody
    @RequestMapping(value = "caLoginCheck", method = RequestMethod.POST)
    @AutoLog(desc = "CA数字证书认证")
    public LoginVO caLoginCheck(HttpServletRequest request, HttpServletResponse response,
        String signedData, String originalJsp) throws Exception {

        LoginVO loginVo = new LoginVO();

        //如果密文不为空，则进行数字证书登录验证。
        if (isNotNull(signedData) && isNotNull(originalJsp)) {
            // in login's webflow : we can get the value from context as it has already been stored

            /***************************************************************************
             * isSuccess 认证是否成功,true成功/false失败;errCode 错误码;errDesc 错误描述 *
             * ************************************************************************/
            //第四步：客户端认证
            //第五步：服务端验证认证原文
            //第六步：应用服务端认证
            //第七步：网关返回认证响应
            //第八步：服务端处理
            /***********************************
             * 获取应用标识及网关认证地址 *
             ***********************************/

            boolean isSuccess = true;
            String errCode = null, errDesc = null;

            // 可以根据需求使用不同的获取方法
            String appId = cappId;
            String authURL = cauthURL;

            if (!isNotNull(appId) || !isNotNull(authURL)) {
                isSuccess = false;
                errDesc = "应用标识或网关认证地址不可为空";
                LOGGER.error(errDesc);
                loginVo.setMsgInfo(errDesc);
            }

            String originalData = null;
            /**************************
             * 获取认证数据信息 *
             **************************/
            if (isSuccess) {

                //System.out.println("应用标识及网关的认证地址读取成功！\n应用标识：" + appId + "\n认证地址："+ authURL + "\n");

                if (isNotNull((String) request.getSession().getAttribute(KEY_ORIGINAL_DATA))) {
                    // 获取session中的认证原文
                    originalData = (String) request.getSession().getAttribute(KEY_ORIGINAL_DATA);
                    // 获取request中的认证原文--original_jsp

                    /**************************
                     * 第五步：服务端验证认证原文 *
                     **************************/
                    if (!originalData.equalsIgnoreCase(originalJsp)) {
                        isSuccess = false;
                        errDesc = "客户端提供的认证原文与服务端的不一致";
                        LOGGER.error(errDesc);
                        //System.out.println(errDesc);
                        loginVo.setMsgInfo(errDesc);

                    }
                    else {
                        // 获取证书认证请求包-- signed_data

                        /* 随机密钥 */
                        originalData = new BASE64Encoder().encode(originalJsp.getBytes("UTF-8"));
                        //System.out.println("读取认证原文和认证请求包成功！
                        //\n认证原文：" + original_jsp+ "\n认证请求包：" + signed_data + "\n");
                    }

                }
                else {
                    isSuccess = false;
                    errDesc = "证书认证数据不完整";
                    LOGGER.error(errDesc);
                    loginVo.setMsgInfo(errDesc);
                    //System.out.println(errDesc);
                }

            }

            /**************************
             * 第六步：应用服务端认证 *
             **************************/
            // 认证处理
            try {
                byte[] messagexml = null;
                if (isSuccess) {

                    /*** 1 组装认证请求报文数据 ** 开始 **/
                    Document reqDocument = DocumentHelper.createDocument();
                    Element root = reqDocument.addElement(MSG_ROOT);
                    Element requestHeadElement = root.addElement(MSG_HEAD);
                    Element requestBodyElement = root.addElement(MSG_BODY);
                    /* 组装报文头信息 */
                    requestHeadElement.addElement(MSG_VSERSION).setText(MSG_VSERSION_VALUE);
                    requestHeadElement.addElement(MSG_SERVICE_TYPE).setText(MSG_SERVICE_TYPE_VALUE);

                    /* 组装报文体信息 */

                    //组装客户端信息
                    Element clientInfoElement = requestBodyElement.addElement(MSG_CLIENT_INFO);

                    Element clientIPElement = clientInfoElement.addElement(MSG_CLIENT_IP);

                    clientIPElement.setText(request.getRemoteAddr());

                    // 组装应用标识信息
                    requestBodyElement.addElement(MSG_APPID).setText(appId);

                    Element authenElement = requestBodyElement.addElement(MSG_AUTH);

                    Element authCredentialElement = authenElement.addElement(MSG_AUTHCREDENTIAL);

                    // 组装证书认证信息
                    authCredentialElement.addAttribute(MSG_AUTH_MODE, MSG_AUTH_MODE_CERT_VALUE);

                    authCredentialElement.addElement(MSG_DETACH).setText(signedData);
                    authCredentialElement.addElement(MSG_ORIGINAL).setText(originalData);

                    //支持X509证书  认证方式
                    //获取到的证书
                    // javax.security.cert.X509Certificate x509Certificate = null;
                    //certInfo 为base64编码证书
                    //可以使用  "certInfo =new BASE64Encoder().encode(x509Certificate.getEncoded());" 进行编码
                    // authCredentialElement.addElement(MSG_CERT_INFO).setText(certInfo);

                    requestBodyElement.addElement(MSG_ACCESS_CONTROL).setText(
                        MSG_ACCESS_CONTROL_FALSE);

                    // 组装口令认证信息
                    //username = request.getParameter( "" );//获取认证页面传递过来的用户名/口令
                    //password = request.getParameter( "" ); 
                    //authCredentialElement.addAttribute(MSG_AUTH_MODE,MSG_AUTH_MODE_PASSWORD_VALUE );
                    //authCredentialElement.addElement( MSG_USERNAME ).setText(username);
                    //authCredentialElement.addElement( MSG_PASSWORD ).setText(password);

                    // 组装属性查询列表信息
                    Element attributesElement = requestBodyElement.addElement(MSG_ATTRIBUTES);

                    attributesElement.addAttribute(MSG_ATTRIBUTE_TYPE, MSG_ATTRIBUTE_TYPE_PORTION);

                    // TODO 取公共信息
                    addAttribute(attributesElement, "X509Certificate.SubjectDN",
                        "http://www.jit.com.cn/cinas/ias/ns/saml/saml11/X.509");

                    //下列信息注释掉，认证服务器返回的报文就不会包含  BY : LC 2015-06-26
                    addAttribute(attributesElement, "UMS.UserID",
                        "http://www.jit.com.cn/ums/ns/user");
                    addAttribute(attributesElement, "机构字典", "http://www.jit.com.cn/ums/ns/user");

                    /*** 1 组装认证请求报文数据 ** 完毕 **/

                    StringBuffer reqMessageData = new StringBuffer();
                    try {
                        /*** 2 将认证请求报文写入输出流 ** 开始 **/
                        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                        XMLWriter writer = new XMLWriter(outStream);
                        writer.write(reqDocument);
                        messagexml = outStream.toByteArray();

                        /*** 2 将认证请求报文写入输出流 ** 完毕 **/

                        reqMessageData.append("请求内容开始！\n");
                        reqMessageData.append(outStream.toString("UTF-8") + "\n");
                        reqMessageData.append("请求内容结束！\n");
                        //System.out.println(reqMessageData.toString() + "\n");
                        LOGGER.info(reqMessageData.toString());
                    }
                    catch (Exception e) {
                        isSuccess = false;
                        errDesc = "组装请求时出现异常";
                        LOGGER.error(errDesc);
                        loginVo.setMsgInfo(errDesc);
                        //System.out.println(errDesc);
                    }
                }

                /****************************************************************
                 * 创建与网关的HTTP连接，发送认证请求报文，并接收认证响应报文*
                 ****************************************************************/
                /*** 1 创建与网关的HTTP连接 ** 开始 **/
                int statusCode = 500;
                HttpClient httpClient = null;
                PostMethod postMethod = null;
                if (isSuccess) {
                    // HTTPClient对象
                    httpClient = new HttpClient();
                    postMethod = new PostMethod(authURL);

                    // 设置报文传送的编码格式
                    postMethod.setRequestHeader("Content-Type", "text/xml;charset=UTF-8");
                    /*** 2 设置发送认证请求内容 ** 开始 **/
                    postMethod.setRequestBody(new ByteArrayInputStream(messagexml));
                    /*** 2 设置发送认证请求内容 ** 结束 **/
                    // 执行postMethod
                    try {
                        /*** 3 发送通讯报文与网关通讯 ** 开始 **/
                        statusCode = httpClient.executeMethod(postMethod);
                        /*** 3 发送通讯报文与网关通讯 ** 结束 **/
                    }
                    catch (Exception e) {
                        isSuccess = false;
                        errCode = String.valueOf(statusCode);
                        errDesc = e.getMessage();
                        LOGGER.error("与网关连接出现异常" + errDesc);
                        //System.out.println("与网关连接出现异常\n");
                    }
                }

                /****************************************************************
                 *  第七步：网关返回认证响应*
                 ****************************************************************/

                StringBuffer respMessageData = new StringBuffer();
                String respMessageXml = null;
                if (isSuccess) {
                    // 当返回200或500状态时处理业务逻辑
                    if (statusCode == HttpStatus.SC_OK
                        || statusCode == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
                        // 从头中取出转向的地址
                        try {
                            /*** 4 接收通讯报文并处理 ** 开始 **/
                            byte[] inputstr = postMethod.getResponseBody();

                            ByteArrayInputStream byteinputStream = new ByteArrayInputStream(
                                inputstr);
                            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                            int ch = 0;
                            try {
                                while ((ch = byteinputStream.read()) != -1) {
                                    int upperCh = (char) ch;
                                    outStream.write(upperCh);
                                }
                            }
                            catch (Exception e) {
                                isSuccess = false;
                                errDesc = e.getMessage();
                            }

                            if (isSuccess) {
                                // 200 表示返回处理成功
                                if (statusCode == HttpStatus.SC_OK) {
                                    respMessageData.append("响应内容开始！\n");
                                    respMessageData.append(new String(outStream.toByteArray(),
                                        "UTF-8") + "\n");
                                    respMessageData.append("响应内容开始！\n");
                                    respMessageXml = new String(outStream.toByteArray(), "UTF-8");
                                }
                                else {
                                    // 500 表示返回失败，发生异常
                                    respMessageData.append("响应500内容开始！\n");
                                    respMessageData.append(new String(outStream.toByteArray(),
                                        "UTF-8") + "\n");
                                    respMessageData.append("响应500内容结束！\n");
                                    isSuccess = false;
                                    errCode = String.valueOf(statusCode);
                                    errDesc = new String(outStream.toByteArray(), "UTF-8");
                                }
                                //System.out.println(respMessageData.toString()+ "\n");
                                LOGGER.info(respMessageData.toString());
                            }
                            /*** 4 接收通讯报文并处理 ** 结束 **/
                        }
                        catch (IOException e) {
                            isSuccess = false;
                            errCode = String.valueOf(statusCode);
                            errDesc = e.getMessage();
                            LOGGER.error("读取认证响应报文出现异常！");
                            //System.out.println("读取认证响应报文出现异常！");
                        }

                    }
                }

                /*** 1 创建与网关的HTTP连接 ** 结束 **/

                /**************************
                 *第八步：服务端处理 *
                 **************************/
                Document respDocument = null;
                Element headElement = null;
                Element bodyElement = null;
                if (isSuccess) {
                    respDocument = DocumentHelper.parseText(respMessageXml);

                    headElement = respDocument.getRootElement().element(MSG_HEAD);
                    bodyElement = respDocument.getRootElement().element(MSG_BODY);

                    /*** 1 解析报文头 ** 开始 **/
                    if (headElement != null) {
                        boolean state = Boolean.valueOf(
                            headElement.elementTextTrim(MSG_MESSAGE_STATE)).booleanValue();
                        if (state) {
                            isSuccess = false;
                            errCode = headElement.elementTextTrim(MSG_MESSAGE_CODE);
                            errDesc = headElement.elementTextTrim(MSG_MESSAGE_DESC);
                            LOGGER.error("认证业务处理失败！\t" + errDesc + "\n");
                            //System.out.println("认证业务处理失败！\t" + errDesc + "\n");
                        }
                    }
                }

                if (isSuccess) {
                    //System.out.println("解析报文头成功！\n");
                    /* 解析报文体 */
                    // 解析认证结果集
                    Element authResult = bodyElement.element(MSG_AUTH_RESULT_SET).element(
                        MSG_AUTH_RESULT);

                    isSuccess = Boolean.valueOf(authResult.attributeValue(MSG_SUCCESS))
                        .booleanValue();
                    if (!isSuccess) {
                        errCode = authResult.elementTextTrim(MSG_AUTH_MESSSAGE_CODE);
                        errDesc = authResult.elementTextTrim(MSG_AUTH_MESSSAGE_DESC);
                        LOGGER.error("身份认证失败，失败原因：" + errDesc);
                        //System.out.println("身份认证失败，失败原因：" + errDesc);
                    }
                }

                if (isSuccess) {
                    //System.out.println("身份认证成功！\n");
                    LOGGER.info("身份认证成功！\n");
                    String accessControlResult = bodyElement.elementTextTrim("accessControlResult");
                    //System.out.println(accessControlResult);

                    // 解析用户属性列表
                    Element attrsElement = bodyElement.element(MSG_ATTRIBUTES);
                    Map attributeNodeMap = new HashMap();
                    Map childAttributeNodeMap = new HashMap();
                    String[] keyes = new String[2];
                    if (attrsElement != null) {
                        List attributeNodeList = attrsElement.elements(MSG_ATTRIBUTE);
                        for (int i = 0; i < attributeNodeList.size(); i++) {
                            keyes = new String[2];
                            Element userAttrNode = (Element) attributeNodeList.get(i);
                            String msgParentName = userAttrNode.attributeValue(MSG_PARENT_NAME);
                            String name = userAttrNode.attributeValue(MSG_NAME);
                            String value = userAttrNode.getTextTrim();
                            keyes[0] = name;
                            if (msgParentName != null && !msgParentName.equals("")) {
                                keyes[1] = msgParentName;
                                childAttributeNodeMap.put(keyes, value);
                            }
                            else {
                                attributeNodeMap.put(keyes, value);
                            }

                        }

                        attributeNodeMap.putAll(childAttributeNodeMap);

                        //解析封装成map的认证网管返回的数据  BY：LC 2015-06-25 
                        Iterator iter = attributeNodeMap.entrySet().iterator();
                        while (iter.hasNext()) {
                            Map.Entry entry = (Map.Entry) iter.next();
                            Object key = entry.getKey();
                            String[] keys = null;
                            if (key != null && !key.equals("")) {
                                keys = (String[]) key;
                                Object val = entry.getValue();

                                if (val != null) {
                                    if (keys[0].equals("X509Certificate.SubjectDN")) {
                                        //此处获取到用户信息字符串,放到UsernamePasswordCredential对象中
                                        String[] uInfos = (val.toString()).split(",")[0].split("=")[1]
                                            .split(" ");
                                        loginVo = caloginService.checkCerUsr(uInfos[1], request,
                                            response);

                                    }
                                }
                            }
                        }

                        //从网管请求回来的用户数字证书信息===================================>
                        //request.setAttribute("attributeNodeMap", attributeNodeMap);

                    }
                }
            }
            catch (Exception e) {
                isSuccess = false;
                errDesc = e.getMessage();
                LOGGER.error(errDesc);
            }

            if (!isSuccess) {
                //设置数字证书认证标志，认证失败！
                if (isNotNull(errCode)) {
                    loginVo.setMsgInfo(errCode);
                }
                if (isNotNull(errDesc)) {
                    loginVo.setMsgInfo(errDesc);
                }
                //System.out.println("处理数据结束，业务处理失败，失败原因：" + errDesc + "\n");
                LOGGER.error("处理数据结束，业务处理失败，失败原因：" + errCode + errDesc + "\n");
            }
            else {
                //设置数字证书认证标志，认证成功！

                //System.out.println("处理数据结束，一切正常！\n");
                LOGGER.info("处理数据结束，一切正常！");
            }

            //request.setAttribute("isSuccess", new Boolean(isSuccess).toString());         
        }

        return loginVo;
    }

    /**
     * 判断是否是空串
     */
    private boolean isNotNull(String str) {
        if (str == null || str.trim().equals(""))
            return false;
        else
            return true;
    }

    /**
     * 向xml插入结点
     */
    private void addAttribute(Element attributesElement, String name, String namespace) {
        Element attr = attributesElement.addElement(MSG_ATTRIBUTE);
        attr.addAttribute(MSG_NAME, name);
        attr.addAttribute(MSG_NAMESPACE, namespace);
    }

    /******************************* 报文公共部分 ****************************/
    /** 报文根结点 */
    private final String MSG_ROOT = "message";

    /** 报文头结点 */
    private final String MSG_HEAD = "head";

    /** 报文体结点 */
    private final String MSG_BODY = "body";

    /** 服务版本号 */
    private final String MSG_VSERSION = "version";

    /** 服务版本值 */
    private final String MSG_VSERSION_VALUE = "1.0";

    /** 服务类型 */
    private final String MSG_SERVICE_TYPE = "serviceType";

    /** 服务类型值 */
    private final String MSG_SERVICE_TYPE_VALUE = "AuthenService";

    /** 报文体 认证方式 */
    private final String MSG_AUTH_MODE = "authMode";

    /** 报文体 证书认证方式 */
    private final String MSG_AUTH_MODE_CERT_VALUE = "cert";

    /** 报文体 口令认证方式 */
    private final String MSG_AUTH_MODE_PASSWORD_VALUE = "password";

    /** 报文体 属性集 */
    private final String MSG_ATTRIBUTES = "attributes";

    /** 报文体 属性 */
    private final String MSG_ATTRIBUTE = "attr";

    /** 报文体 属性名 */
    private final String MSG_NAME = "name";

    /** 报文父级节点 */
    //--hegd
    public static final String MSG_PARENT_NAME = "parentName";

    /** 报文体 属性空间 */
    private final String MSG_NAMESPACE = "namespace";
    /*********************************************************************/

    /******************************* 请求报文 ****************************/
    /** 报文体 应用ID */
    private final String MSG_APPID = "appId";

    /**访问控制*/
    private final String MSG_ACCESS_CONTROL = "accessControl";

    private final String MSG_ACCESS_CONTROL_TRUE = "true";

    private final String MSG_ACCESS_CONTROL_FALSE = "false";

    /** 报文体 认证结点 */
    private final String MSG_AUTH = "authen";

    /** 报文体 认证凭据 */
    private final String MSG_AUTHCREDENTIAL = "authCredential";

    /** 报文体 客户端结点 */
    private final String MSG_CLIENT_INFO = "clientInfo";

    /** 报文体 公钥证书 */
    private final String MSG_CERT_INFO = "certInfo";

    /** 报文体 客户端结点 */
    private final String MSG_CLIENT_IP = "clientIP";

    /** 报文体 detach认证请求包 */
    private final String MSG_DETACH = "detach";

    /** 报文体 原文 */
    private final String MSG_ORIGINAL = "original";

    /** 报文体 用户名 */
    private final String MSG_USERNAME = "username";

    /** 报文体 口令 */
    private final String MSG_PASSWORD = "password";

    /** 报文体 属性类型 */
    private final String MSG_ATTRIBUTE_TYPE = "attributeType";

    /** 指定属性 portion*/
    private final String MSG_ATTRIBUTE_TYPE_PORTION = "portion";

    /** 指定属性 all*/
    private final String MSG_ATTRIBUTE_TYPE_ALL = "all";
    /*********************************************************************/

    /******************************* 响应报文 ****************************/
    /** 报文体 认证结果集状态 */
    private final String MSG_MESSAGE_STATE = "messageState";

    /** 响应报文消息码 */
    private final String MSG_MESSAGE_CODE = "messageCode";

    /** 响应报文消息描述 */
    private final String MSG_MESSAGE_DESC = "messageDesc";

    /** 报文体 认证结果集 */
    private final String MSG_AUTH_RESULT_SET = "authResultSet";

    /** 报文体 认证结果 */
    private final String MSG_AUTH_RESULT = "authResult";

    /** 报文体 认证结果状态 */
    private final String MSG_SUCCESS = "success";

    /** 报文体 认证错误码 */
    private final String MSG_AUTH_MESSSAGE_CODE = "authMessageCode";

    /** 报文体 认证错误描述 */
    private final String MSG_AUTH_MESSSAGE_DESC = "authMessageDesc";
    /*********************************************************************/

    /**************************** 业务处理常量 ****************************/
    /** 认证地址 */
    private final String KEY_AUTHURL = "authURL";

    /** 应用标识 */
    private final String KEY_APP_ID = "appId";

    /** 认证方式 */
    private final String KEY_CERT_AUTHEN = "certAuthen";

    /** session中原文 */
    private final String KEY_ORIGINAL_DATA = "original_data";

    /** 客户端返回的认证原文，request中原文 */
    private final String KEY_ORIGINAL_JSP = "original_jsp";

    /** 证书认证请求包 */
    private final String KEY_SIGNED_DATA = "signed_data";

    /** 证书 */
    private final String KEY_CERT_CONTENT = "certInfo";

    /*********************************************************************/
}
