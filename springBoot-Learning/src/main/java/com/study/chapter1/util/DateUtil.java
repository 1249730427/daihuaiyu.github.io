package com.study.chapter1.util;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 日期工具类
 *
 * @Author: daihuaiyu
 * @Date: 2020-11-07 11:09
 */
public class DateUtil {

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public static final String DATE_FORMAT_ALL = "yyyy-MM-dd HH:mm:ss";

    public static final String DATETIME_FORMAT_STRING="yyyyMMddhhmmSS";

    public static final String DATE_FORMAT_DAY = "yyyyMMdd";

    //单位:秒
    public static final String SECOND_UNIT="s";

    //单位:分钟
    public static final String MINUTE_UNIT="m";

    //单位:小时
    public static final String HOUR_UNIT="h";

    //单位:小时
    public static final String DAY_UNIT="d";

    private static AtomicLong atomicTimeMills = new AtomicLong(0);

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

    public static String getNextTime() {
        while (true) {
            long currentMill = atomicTimeMills.get();
            Long currentTimeMillis = Long.parseLong(LocalDateTime.now().format(formatter) + System.nanoTime()%100);
            if (currentTimeMillis > currentMill && atomicTimeMills.compareAndSet(currentMill, currentTimeMillis)) {
                return currentTimeMillis.toString();
            }
        }
    }

    public static LocalDateTime date2LocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static String format(Date date){
        return DateFormatUtils.format(date, DATE_FORMAT_ALL);
    }

    public static String format(LocalDateTime date){
        return DateTimeFormatter.ofPattern(DATE_FORMAT_ALL).format(date);
    }

    public static String getDateString(){
        return DateFormatUtils.format(new Date(),DATE_FORMAT);
    }

    public static String getDateString(Date date){
        return DateFormatUtils.format(date,"yyyyMMdd");
    }

    public static Date getDay(Date date) {
        String day=DateFormatUtils.format(date,"yyyyMMdd");
        Date resultDay = null;
        try {
            resultDay = DateUtils.parseDate(day,"yyyyMMdd");
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return resultDay;
    }

    /**
     * 取得两个日期间隔秒数（日期1-日期2）
     *
     * @param one
     *            日期1
     * @param two
     *            日期2
     *
     * @return 间隔秒数
     */
    public static long getDiffSeconds(Date one, Date two) {
        Calendar sysDate = new GregorianCalendar();
        sysDate.setTime(one);
        Calendar failDate = new GregorianCalendar();
        failDate.setTime(two);
        return (sysDate.getTimeInMillis() - failDate.getTimeInMillis()) / 1000;
    }

    public static Date add(Date beforeDate,Integer internal,String internalUnit){
        if (SECOND_UNIT.equalsIgnoreCase(internalUnit)){
            return DateUtils.addSeconds(beforeDate,internal);
        }else if (MINUTE_UNIT.equalsIgnoreCase(internalUnit)){
            return DateUtils.addMinutes(beforeDate,internal);
        }else if (HOUR_UNIT.equalsIgnoreCase(internalUnit)){
            return DateUtils.addHours(beforeDate,internal);
        }else if (DAY_UNIT.equalsIgnoreCase(internalUnit)){
            return DateUtils.addDays(beforeDate,internal);
        }else {
            throw new RuntimeException("不可识别的时间单位 internalUnit = "+internalUnit);
        }
    }
}
