package com.nxedu.haircutreserve.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * <p>@description:时间工具类</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/3/23
 */

public class TimeUtils {

    private static DateFormat df;

    /**
     * 获取时间差
     *
     * @return
     */
    public static String getTimeDiff(String time) {
        if (time == null || time.isEmpty()) {
            return "";
        }
        String diff = "";
        df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        try {
            Date d = df.parse(time);
            long diff1 = System.currentTimeMillis() - d.getTime();//这样得到的差值是微秒级别

            long days = diff1 / (1000 * 60 * 60 * 24);
            long hours = (diff1 - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            long minutes = (diff1 - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);

            if (days > 0) {
                return days + "天";
            } else if (hours > 0) {
                return hours + "小时";
            } else if (minutes > 0) {
                return minutes + "分钟";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return diff;
    }


    /**
     * 时间比较大小
     *
     * @return
     */
    public static int timeCompare(String t1) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(formatter.parse(t1));
            c2.setTime(formatter.parse(getCurrentTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int result = c1.compareTo(c2);
        return result;
    }

    /*获得当前时间*/
    public static String getCurrentTime() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
}
