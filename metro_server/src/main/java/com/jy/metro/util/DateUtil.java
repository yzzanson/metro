package com.jy.metro.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by anson on 18/2/7.
 */
public class DateUtil {

    public static final String FORMATER_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取指定日期的前一天开始时间
     *
     * @return 指定日期的前一天开始时间
     * @Author hehao
     * @CreateDate 2016年3月12日
     */
    public static Date getBeforeDayStartDateTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date dBefore = calendar.getTime();
        return getDateStartDateTime(dBefore);
    }

    /**
     * 根据日期获取该日期的开始日期时间点
     *
     * @return 日期的开始日期时间点(示例:2016-9-26 00:00:00)
     * @author ZKT
     * @createTime 2012-9-26
     */
    public static Date getDateStartDateTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }


    /**
     * 根据日期获取该日期的结束日期时间点
     *
     * @return 日期的结束日期时间点(示例:2016-9-26 23:59:59)
     * @author ZKT
     * @createTime 2012-9-26
     */
    public static Date getDateEndDateTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 计算两个日期之间的分钟数</br>
     * 任何一个参数传空都会返回-1</br>
     * 返回两个日期的时间差，不关心两个日期的先后</br>
     *
     * @param dateStart
     * @param dateEnd
     * @return
     * @author Saber
     * Date 2017/4/18 14:07
     */
    public static long getMinutesBetweenTwoDate(Date dateStart, Date dateEnd) {
        if (null == dateStart || null == dateEnd) {
            return -1;
        }
        long l = Math.abs(dateStart.getTime() - dateEnd.getTime());
        l = l / (1000 * 60);
        return l;
    }

    /**
     * 获取当前时间的UTC时间
     *
     * @return
     */
    public static Date getCurrentTime() {
        return new Timestamp(System.currentTimeMillis());
    }


    public static String getDateFromLong(Long longTime){
        if(longTime==null) return null;
        DateFormat formatter = new SimpleDateFormat(FORMATER_YYYY_MM_DD_HH_MM_SS);
        return formatter.format(new Date(longTime));
    }


    public static void main(String[] args) {

    }

}
