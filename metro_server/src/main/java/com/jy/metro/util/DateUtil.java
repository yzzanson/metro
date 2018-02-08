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
    public static final String FORMATER_YYYY_MM_DD_HH_MM_SS2 = "yyyyMMddHHmm";



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
        String time = String.valueOf(longTime);
        String resultTime = "";
        if(time.length()==12){
            resultTime += time.substring(0, 4)+"-"+time.substring(4, 6)+"-"+time.substring(6, 8)+" "+time.substring(8, 10)+":"+time.substring(10, 12);
        }else if(time.length()<12 && time.length()>10){
            resultTime += time.substring(0, 4)+"-"+time.substring(4, 6)+"-"+time.substring(6, 8)+" "+time.substring(8, 10)+":"+time.substring(10, time.length() - 1);
        }else if(time.length()<10 && time.length()>8){
            resultTime += time.substring(0, 4)+"-"+time.substring(4, 6)+"-"+time.substring(6,8)+" "+time.substring(8, 10);
        }
        return resultTime;
    }

    public static Long getStringDateFromDate(Date date){
        if(date==null) return null;
        DateFormat formatter = new SimpleDateFormat(FORMATER_YYYY_MM_DD_HH_MM_SS2);
        return Long.parseLong(formatter.format(date));
    }


    /**
     * 将日期转换成yyyy-MM-dd HH:mm:ss 格式
     *
     * @return 指定日期的前一天开始时间
     * @Author hehao
     * @CreateDate 2016年3月12日
     */
    public static String getDisplayYMDHMS(Date date){
        return new SimpleDateFormat(FORMATER_YYYY_MM_DD_HH_MM_SS).format(date);
    }

    public static void main(String[] args) {
//        Long startTime = DateUtil.getStringDateFromDate(DateUtil.getBeforeDayStartDateTime(new Date()));
//        Long endTime = DateUtil.getStringDateFromDate(DateUtil.getDateEndDateTime(new Date()));
//        System.out.println(startTime);
//        System.out.println(endTime);

        long longTIme = 201802051200L;
        String time = String.valueOf(longTIme);
        String resultTime = "";
        System.out.println(time.substring(0, 4));
        System.out.println(time.substring(4, 6));
        System.out.println(time.substring(6, 8));
        System.out.println(time.substring(8, 10));
        System.out.println(time.substring(10, 12));
    }

}
