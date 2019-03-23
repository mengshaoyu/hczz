package com.shield.hczz.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinUtil {

    /**
     * 多字拼音拼接格式
     */
    enum LinkType {
        /**
         * 空格间隔
         */
        blank,
        /**
         * 无间隔
         */
        empty,
        /**
         * 驼峰法
         */
        hump,
        /**
         * 下划线间隔
         */
        underline
    }

    /**
     * 大小写格式
     */
    enum CaseType {
        /**
         * 小写（默认）
         */
        lower,
        /**
         * 大写
         */
        upper
    }

    private static HanyuPinyinOutputFormat format = null;

    private static String[] pinyin = null;

    static {
        format = new HanyuPinyinOutputFormat();
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
    }

    private PinyinUtil(){}
    /**
     * 转换单个字符
     * @param c 待转换字符
     * @param ct 大小写格式
     * @return
     */
    public static String getCharacterPinYin(char c, CaseType ct) {
        try {
            //判断是否是中文
            if (Character.toString(c).matches("[\\u4E00-\\u9FA5]+")) {
                pinyin = PinyinHelper.toHanyuPinyinStringArray(c, format);
            }
            else {
                pinyin = null;
            }
        }
        catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        // 如果c不是汉字，toHanyuPinyinStringArray会返回null
        if (pinyin == null || pinyin.length == 0)
            return null;
        // 只取一个发音，如果是多音字，仅取第一个发音
        if (CaseType.upper.equals(ct)) {
            return pinyin[0].toUpperCase();
        }
        else {
            return pinyin[0];
        }
    }

    /**
     * 转换单个字符
     * @param c 待转换字符
     * @return
     */
    public static String getCharacterPinYin(char c) {
        return getCharacterPinYin(c, CaseType.lower);
    }

    /**
     * 转换一个字符串
     * @param str 待转换字符串
     * @param ct 大小写格式
     * @param lt 多字拼音拼接格式
     * @return
     */
    public static String getStringPinYin(String str, CaseType ct, LinkType lt) {
        StringBuilder sb = new StringBuilder();
        String tempPinyin = null;
        for (int i = 0; i < str.length(); i++) {
            tempPinyin = getCharacterPinYin(str.charAt(i), ct);
            if (tempPinyin == null) {
                // 如果str.charAt(i)非汉字，则保持原样
                sb.append(str.charAt(i));
            }
            else {
                if (sb.length() > 0 && tempPinyin.length() > 0) {
                    switch (lt) {
                    case underline:
                        tempPinyin = "_" + tempPinyin;
                        break;
                    case blank:
                        tempPinyin = " " + tempPinyin;
                        break;
                    case hump:
                        if (CaseType.lower.equals(ct)) {
                            tempPinyin = tempPinyin.substring(0, 1).toUpperCase()
                                + tempPinyin.substring(1);
                        }
                        else {
                            tempPinyin = tempPinyin.substring(0, 1).toLowerCase()
                                + tempPinyin.substring(1);
                        }
                        break;
                    default:
                        break;
                    }
                }
                sb.append(tempPinyin);
            }
        }
        return sb.toString();
    }

    /**
     * 转换一个字符串
     * @param str 待转换字符串
     * @param ct 大小写格式
     * @return
     */
    public static String getStringPinYin(String str, CaseType ct) {
        return getStringPinYin(str, ct, LinkType.empty);
    }

    /**
     * 转换一个字符串
     * @param str 待转换字符串
     * @param lt 多字拼音拼接格式
     * @return
     */
    public static String getStringPinYin(String str, LinkType lt) {
        return getStringPinYin(str, CaseType.lower, lt);
    }

    /**
     * 转换一个字符串
     * @param str 待转换字符串
     * @return
     */
    public static String getStringPinYin(String str) {
        return getStringPinYin(str, CaseType.lower, LinkType.empty);
    }

}
