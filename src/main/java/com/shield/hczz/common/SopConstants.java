package com.shield.hczz.common;

import com.shield.frame.utils.ConfigUtil;

public class SopConstants {
    /**
     * 待提报
     */
    public static final String FLOW_SQPZ = "HCZZ_1001";
    /**
     * 退帧补充
     */
    public static final String FLOW_TZBC = "HCZZ_1010";
    /**
     * 分管领导审批
     */
    public static final String FLOW_FGLDSP = "HCZZ_1002";
    /**
     * 值班长签收
     */
    public static final String FLOW_ZBZQS = "HCZZ_1003";
    /**
     * 待值班长审批
     */
    public static final String FLOW_DZBZSP = "HCZZ_1004";
    /**
     * 待分配
     */
    public static final String FLOW_DFP = "HCZZ_1005";
    /**
     * 待反馈
     */
    public static final String FLOW_DFK = "HCZZ_1006";
    /**
     * 待生成反馈报告
     */
    public static final String FLOW_DSC = "HCZZ_1007";
    /**
     * 待核实
     */
    public static final String FLOW_DHS = "HCZZ_1008";

    /***
     * 完结
     */
    public static final String FLOW_OVER = "end";
    
    /**
     * 角色--普通民警
     */
    public static final String ROLE_PTMJ = "1000";
    /**
     * 角色--分管领导
     */
    public static final String ROLE_FGLD = "1001";
    /**
     * 角色--值班长
     */
    public static final String ROLE_ZBZ = "1002";
    /**
     * 角色--合成中心民警
     */
    public static final String ROLE_HCZXMJ = "1003";
    
    /**
     * 角色--小领导
     */
    public static final String ROLE_HCZXLD1 = "1005";
    
    /**
     * 角色--大领导
     */
    public static final String ROLE_HCZXLD2 = "1006";

    /**
     * 角色--运维
     */
    public static final String ROLE_YW = "1007";

    /**
     * 默认提醒的配侦状态
     */
    public static final String DEFAULT_NOTICE_STATUS = "2,3,4,5,6,8,9";
    
    /**
     * 附件上传位置
     */
    public static final String UPLOAD_DISK_PATH = ConfigUtil.getMsg("UPLOAD_DISK_PATH");
    
    public static final String UPLOAD_ACCESS_PATH = ConfigUtil.getMsg("UPLOAD_ACCESS_PATH");
    
    public static final String MAX_UPLOAD_SIZE = ConfigUtil.getMsg("MAX_UPLOAD_SIZE");
    
    /**
     * 小领导效能评定
     */
    public static final String EFFECT_TYPE0 = "0";
    
    /**
     * 大领导效能评定总结
     */
    public static final String EFFECT_TYPE1 = "1";
}
