package com.shield.frame.common.qvo;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

/**
 * 返回客户端是否成功标识并且可以自定义信息内容（不需要在messages.properties中配置）
 *
 */
public class CommonVO extends BaseVO {
    private boolean success;
    private int type;
    private String val;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
        this.success = false;
    }

    public CommonVO() {
        this.success = true;
        this.setMsgInfo("操作成功");
    };

    //框架原始messages调用方式
    public CommonVO(String msgCode) {
        super(msgCode);
    }

    public CommonVO(boolean success) {
        this.success = success;
        this.msg = success == true ? "操作成功" : "操作失败";
    }

    //增加是否成功标识
    public CommonVO(boolean success, String msgCode) {
        super(msgCode);
        this.success = success;
    }

    //自定义成功信息
    public CommonVO getOK(String msgInfo) {
        this.success = true;
        if (msgInfo == null || "".equals(msgInfo)) {
            msgInfo = "操作成功";
        }
        this.setMsgInfo(msgInfo);
        return this;
    }

    //自定义失败信息
    public CommonVO getError(String msgInfo) {
        this.success = false;
        if (msgInfo == null || "".equals(msgInfo)) {
            msgInfo = "操作失败";
        }
        this.setMsgInfo(msgInfo);
        return this;
    }
    public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
        
    }
}
