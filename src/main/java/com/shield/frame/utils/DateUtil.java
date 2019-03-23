package com.shield.frame.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 *
 */
public class DateUtil {
    
    public static Date getStrToDate(String dateStr, String dateFormatStr){
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatStr);
        Date date = null;
        try {
            date = dateFormat.parse(dateStr);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    /**
     * 获取当前日期<br />
     * 根据输入格式，返回当前日期
     * 
     * @param  date 转换日期
     * @param  dateFormatStr 日期格式，默认形式为yyyy-MM-dd HH:mm:ss
     * @return 日期字符串
     */
    public static String getDateToStr(Date date, String... dateFormatStr) {
        SimpleDateFormat dateFormat = null;
        if (dateFormatStr != null && dateFormatStr.length > 0) {
            dateFormat = new SimpleDateFormat(dateFormatStr[0]);
        }
        else {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        return dateFormat.format(date);
    }

    /**
     * 获取指定过去日期零时<br />
     * 根据输入格式，获取指定过去日期零时
     * 
     * @param  date 转换日期
     * @param  dateFormatStr 日期格式，默认形式为yyyy-MM-dd HH:mm:ss
     * @return 日期字符串
     */
    public static String getOldDateToStr(Date date, int days, String... dateFormatStr) {
        SimpleDateFormat dateFormat = null;
        if (dateFormatStr != null && dateFormatStr.length > 0) {
            dateFormat = new SimpleDateFormat(dateFormatStr[0]);
        }
        else {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - days);

        return dateFormat.format(calendar.getTime());
    }

    /**
     * 根据日期偏移量获得日期字符串
     * 
     * @param date   起始日期
     * @param months 日期偏移量
     * @param dateFormatStr 日期格式，默认形式为yyyy-MM-01
     * 
     * @return 日期字符串
     */
    public static String getOldMonthToStr(Date date, int months, String... dateFormatStr) {
        SimpleDateFormat dateFormat = null;
        if (dateFormatStr != null && dateFormatStr.length > 0) {
            dateFormat = new SimpleDateFormat(dateFormatStr[0]);
        }
        else {
            dateFormat = new SimpleDateFormat("yyyy-MM-01");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + months);

        return dateFormat.format(calendar.getTime());
    }

    /**
     * 转换为时间（天,时:分:秒.毫秒）
     * @param timeMillis
     * @return
     */
    public static String formatDateTime(long timeMillis) {
        long day = timeMillis / (24 * 60 * 60 * 1000);
        long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
        long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60
            * 1000 - s * 1000);
        return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
    }
}