package com.shield.hczz.utils;

import org.springframework.stereotype.Repository;

@Repository
public class ToolsUtils {
    /**
     * iso编码转utf-8,判断若为非UTF编码，则转义
     * 
     * @param isoStr
     * @return
     * @throws Exception
     */
    public static String isoToUTF8(String isoStr) throws Exception {
        if (null != isoStr && !"".equals(isoStr)
            && (isoStr.equals(new String(isoStr.getBytes("iso8859-1"), "iso8859-1")))) {
            isoStr = new String(isoStr.getBytes("ISO-8859-1"), "UTF-8");
        }
        return isoStr;
    }
}
