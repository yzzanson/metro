package com.jy.metro.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by anson on 18/2/7.
 */
public class DateUtil {

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

    public static void main(String[] args) {

    }

}
