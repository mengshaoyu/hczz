package com.shield.frame.common.qvo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;

import com.shield.frame.utils.PropertyUtil;

/**
 * VO的基类,其它各个页面的VO酌情继承
 *
 */
public class BaseVO {
    /** 信息代码 */
    private String msgCode;

    /** 信息内容 */
    private String msgInfo;

    /**
     * 默认构造体
     */
    public BaseVO() {
    }

    /**
     * 有参构造体<br/>
     * 根据消息代码取得消息内容
     * 
     * @param msgCode 信息代码
     */
    public BaseVO(String msgCode) {
        this.msgCode = msgCode;
        this.msgInfo = PropertyUtil.getMsg(msgCode);
    }

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode, String... param) {
        this.msgCode = msgCode;
        if (param == null) {
            this.msgInfo = PropertyUtil.getMsg(msgCode);
        }
        else {
            this.msgInfo = PropertyUtil.getMsg(msgCode, (Object[]) param);
        }
    }

    public String getMsgInfo() {
        return msgInfo;
    }

    public void setMsgInfo(String msgInfo) {
        this.msgInfo = msgInfo;
    }

    /**
     * 测试
     * @param args
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonGenerationException 
     */
    public static void main(String args[]) throws Exception {
        ObjectMapper om = new ObjectMapper();
        Map<String,Object> on = new HashMap<String,Object>();
        Map<String,Object> head = new HashMap<String,Object>();
        head.put("success", true);
        head.put("code",200);
        head.put("msg", "操作成功");
        on.put("head", head);
        DataGridVO<BaseVO> dg = new DataGridVO<BaseVO>();
        on.put("body", dg);
        System.out.println(om.writeValueAsString(on));
    }
}