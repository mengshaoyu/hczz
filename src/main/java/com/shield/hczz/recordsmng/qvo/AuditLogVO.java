package com.shield.hczz.recordsmng.qvo;

import com.shield.hczz.base.domain.AuditLog;

public class AuditLogVO extends AuditLog {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String userName; // 操作人姓名

    private String resultStr; // 操作结果，0成功，1失败

    private String operTypeStr; // 操作类型:1-查询,2-查看,3-新增,4-编辑,5-删除,6-导出,9-其他

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getResultStr() {
        return resultStr;
    }

    public void setResultStr(String resultStr) {
        this.resultStr = resultStr;
    }

    public String getOperTypeStr() {
        return operTypeStr;
    }

    public void setOperTypeStr(String operTypeStr) {
        this.operTypeStr = operTypeStr;
    }

}
