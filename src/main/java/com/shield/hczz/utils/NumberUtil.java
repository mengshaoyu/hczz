package com.shield.hczz.utils;

/**
 * 数字工具类
 */
public class NumberUtil {

    private static final String SHI = "十";
    private static final String BAI = "百";
    private static final String QIAN = "千";
    private static final String WAN = "万";
    private static final char[] carr = { '零', '一', '二', '三', '四', '五', '六', '七', '八', '九' };

    /**
     * 数字转汉字（一亿之内）
     * @param num 数字
     * @return 汉字
     */
    public static String toChinese(Integer num) {
        //0直接返回‘零’字
        if (num == 0) {
            return String.valueOf(carr[0]);
        }
        StringBuffer sb = new StringBuffer();
        Integer r = num / 10000;
        Integer l = num % 10000;
        if (r == 0) {
            sb.append(belowW(num));
        }
        else {
            sb.append(belowW(r));
            sb.append(WAN);
            if (l / 1000 == 0) {
                handleZero(sb);
            }
            sb.append(belowW(l));
        }
        return result(sb);
    }

    private static String belowS(Integer num) {
        //中间的‘零’字去掉
        if (num == 0)
            return "";
        return String.valueOf(carr[num]);
    }

    private static String belowB(Integer num) {
        StringBuffer sb = new StringBuffer();
        Integer r = num / 10;
        Integer l = num % 10;
        if (r == 0) {
            return belowS(num);
        }
        else {
            sb.append(belowS(r));
            sb.append(SHI);
            if (l == 0) {
                handleZero(sb);
            }
            sb.append(belowS(l));
        }
        return result(sb);
    }

    private static String belowQ(Integer num) {
        StringBuffer sb = new StringBuffer();
        Integer r = num / 100;
        Integer l = num % 100;
        if (r == 0) {
            return belowB(num);
        }
        else {
            sb.append(belowB(r));
            sb.append(BAI);
            if (l / 10 == 0) {
                handleZero(sb);
            }
            sb.append(belowB(l));
        }
        return result(sb);
    }

    private static String belowW(Integer num) {
        StringBuffer sb = new StringBuffer();
        Integer r = num / 1000;
        Integer l = num % 1000;
        if (r == 0) {
            return belowQ(num);
        }
        else {
            sb.append(belowQ(r));
            sb.append(QIAN);
            if (l / 100 == 0) {
                handleZero(sb);
            }
            sb.append(belowQ(l));
        }
        return result(sb);
    }

    /** 防止‘零’字重复
     * @param sb
     */
    private static void handleZero(StringBuffer sb){
        if (sb.charAt(sb.length() - 1) != carr[0])
            sb.append(carr[0]);
    }
    
    
    /**
     * 结果处理（去掉末尾的零）
     * @param sb
     * @return
     */
    private static String result(StringBuffer sb) {
        if (sb.charAt(sb.length() - 1) == carr[0])
            sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

}
