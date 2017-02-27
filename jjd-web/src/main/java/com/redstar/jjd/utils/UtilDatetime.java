/*
 * @(#)UtilDatetime.java	 2012-3-21 下午05:42:35
 *
 * Copyright (c) 2006-2011 Origami Frontiers Pte Ltd. All Rights Reserved.
 * You may obtain more information and a copy of the License at: http://www.origami-frontiers.com
 */
package com.redstar.jjd.utils;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * 
 * ClassName: UtilDatetime <br/>
 * Date: 2015年12月18日 下午3:10:46 <br/>
 * Description: 日期处理工具 <br/>
 * 
 * @author huangr
 * @version
 * @see
 */
public class UtilDatetime {
    public static final SimpleDateFormat MONTH_FORMAT = new SimpleDateFormat(
            "yyyy-MM");

    public static final SimpleDateFormat MONTH_NO_ZERO_FORMAT = new SimpleDateFormat(
            "yyyy-M");

    public static final SimpleDateFormat MONTHFORMAT = new SimpleDateFormat(
            "yyyyMM");

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
            "yyyy-MM-dd");

    public static final SimpleDateFormat DATE_FORMAT_NO_HYPHEN = new SimpleDateFormat(
            "yyyyMMdd");

    public static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat(
            "yyyyMMddHHmmss");

    public static final SimpleDateFormat DATE_TIME_FULL_FORMAT = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    private static final SimpleDateFormat DATE_TIME_FORMAT_FULL_FORMAT = new SimpleDateFormat(
            "dd/MM/yyyy HH:mm:ss");

    public static final SimpleDateFormat TIME_FULL_FORMAT = new SimpleDateFormat(
            "HH:mm:ss");
    public static final SimpleDateFormat HOUR_FORMAT = new SimpleDateFormat(
            "HH:mm");

    public static final SimpleDateFormat SPECIAL_DATE_FORMAT = new SimpleDateFormat(
            "yyyy/MM/dd");

    public static final SimpleDateFormat MONTH_DAY_FORMAT = new SimpleDateFormat(
            "M月d日");

    public static final SimpleDateFormat MONTH_DAY_HOUR_FORMAT = new SimpleDateFormat(
            "M月d日 HH:00");

    public static final SimpleDateFormat YEAR_MONTH_DAY_FORMAT = new SimpleDateFormat(
            "yyyy.M.d");

    /**
     * 长日期格式
     * 
     * @since OF-v5.0
     * @author Mason Zou, Jul 10, 2011 10:56:46 AM
     */
    public static final String DATE_FMT_LONG = "yyyy-MM-dd HH:mm:ss";
    /**
     * 短日期格式
     * 
     * @since OF-v5.0
     * @author Mason Zou, Jul 10, 2011 10:56:46 AM
     */
    public static final String DATE_FMT_SHORT = "yyyy-MM-dd";
    /**
     * 短日期格式
     * 
     * @since OF-v5.0
     * @author Mason Zou, Jul 10, 2011 10:56:46 AM
     */
    public static final String DATE_FMT_SHORT_NO_ZERO = "yyyy-M-d";

    /**
     * 获取昨天日期(Format:yyyy-MM-dd)
     * 
     * @return
     */
    public static String getYestodayYMD() {
        return getPreviusDay(new Date(), 1, DATE_FMT_SHORT);
    }

    /**
     * 获取昨天日期(Format:yyyy-M-d)
     * 
     * @return
     */
    public static String getYestodayYMD_NoZero() {
        return getPreviusDay(new Date(), 1, DATE_FMT_SHORT_NO_ZERO);
    }

    /**
     * 获取当前日期的前n天
     * 
     * @param date
     * @param n
     * @param dateFormat
     * @return
     */
    public static String getPreviusDay(Date date, int n, String dateFormat) {
        Calendar day = Calendar.getInstance();
        day.setTime(date);
        day.add(Calendar.DAY_OF_MONTH, -n);
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(day.getTime());
    }

    /**
     * 获得明天
     * 
     * @return
     * @since OF-v1.0
     * @author Oscar Xu, 2013-9-27 下午4:54:32
     */
    public static String getTomorrowYMD() {
        return getSpecifyDay(new Date(), 1, DATE_FMT_SHORT);
    }

    /**
     * 获取指定天的前几天或者后几天
     * 
     * @param date
     * @param n
     * @param dateFormat
     * @return
     * @since OF-v1.0
     * @author Oscar Xu, 2013-9-27 下午4:53:28
     */
    public static String getSpecifyDay(Date date, int n, String dateFormat) {
        Calendar day = Calendar.getInstance();
        day.setTime(date);
        day.add(Calendar.DAY_OF_MONTH, n);
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(day.getTime());
    }

    /**
     * 获得系统当前时间
     * 
     * @return
     * @since OF-v5.0
     * @author Mason Zou, Jul 10, 2011 10:56:46 AM
     */
    public static Date getSysCurrentDate() {
        return new Date();
    }

    /**
     * 获得系统当前的日期，日期格式是 2011-01-01 00:00:00
     * 
     * @return
     * @since OF-v5.0
     * @author Mason Zou, Jul 10, 2011 10:56:46 AM
     */
    public static Date getSysCurrentShortDate() {
        Date sysCurrentTime = getSysCurrentDate();
        Date sysCurrentShortDate = null;
        try {
            sysCurrentShortDate = convertDateToShortDate(sysCurrentTime);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sysCurrentShortDate;
    }

    /**
     * 解析日期Date对象为短格式日期字符串
     * 
     * @param date
     * @return
     * @throws Exception
     * @since OF-v5.0
     * @author Mason Zou, Jul 10, 2011 10:56:46 AM
     */
    public static String parseDateToShortString(Date date) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FMT_SHORT);
        return sdf.format(date);
    }

    /**
     * 转换日期Date对象为只有日期的Date对象
     * 
     * @param date
     * @return
     * @throws Exception
     * @since OF-v5.0
     * @author Mason Zou, Jul 10, 2011 10:56:46 AM
     */
    public static Date convertDateToShortDate(Date date) throws Exception {
        String strDate = parseDateToShortString(date);
        return parseStringToShortDate(strDate);
    }

    /**
     * 解析短日期格式字符串为日期Date对象
     * 
     * @param yyyymmdd
     * @return
     * @throws Exception
     * @since OF-v5.0
     * @author Mason Zou, Jul 10, 2011 10:56:46 AM
     */
    public static Date parseStringToShortDate(String yyyymmdd) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FMT_SHORT);
        return sdf.parse(yyyymmdd);
    }

    public static Timestamp getCurrentSystemDate() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * Get the year of the given date
     * 
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * Get the month of the given date
     * 
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static List<String> getMonthList(Date from, int offset) {
        List<String> monthList = new ArrayList<String>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(from);
        Date date;
        monthList.add(0, formatYM(from));
        if (offset > 0) {
            for (int i = 1; i < offset; i++) {
                calendar.add(Calendar.MONTH, 1);
                date = calendar.getTime();
                monthList.add(formatYM(date));
            }
        } else {
            for (int i = offset; i < 0; i++) {
                calendar.add(Calendar.MONTH, -1);
                date = calendar.getTime();
                // monthList.add(0, formatYM(date));
                monthList.add(formatYM(date));
            }
        }
        return monthList;
    }

    public static List<String> getDayList(Date from, int offset) {
        List<String> dayList = new ArrayList<String>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(from);
        Date date;
        dayList.add(0, formatYMD(from));
        if (offset > 0) {
            for (int i = 1; i < offset; i++) {
                calendar.add(Calendar.DATE, 1);
                date = calendar.getTime();
                dayList.add(formatYMD(date));
            }
        } else {
            for (int i = offset; i < 0; i++) {
                calendar.add(Calendar.DATE, -1);
                date = calendar.getTime();
                dayList.add(formatYMD(date));
            }
        }
        return dayList;
    }

    /**
     * Returns elapsed date
     * 
     * @param dateFrom
     * @param dateTo
     * @return
     */
    public static int getElapsedDate(Date dateFrom, Date dateTo) {
        long result = dateTo.getTime() - dateFrom.getTime();
        return (int) result / 86400000;
    }

    public static long constructDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DATE, day);
        return calendar.getTime().getTime();
    }

    /**
     * Function : stripTime Description : Strip the time from a date Parameters
     * : Return Values : long Last Modified On : 22 Feb 2001 @ 1502 by Budi
     * Boentaran
     */
    public static long stripTime(Date date) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime().getTime();
    }

    public static Date getTime(Date date, long secs) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        if (date != null)
            calendar.setTime(date);
        calendar.setTimeInMillis(calendar.getTimeInMillis() + secs * 1000L);

        return calendar.getTime();
    }

    public static String getTimeStr(Date date, long secs) {
        return DATE_TIME_FULL_FORMAT.format(getTime(date, secs));
    }

    /**
     * Gavin Hou add 4/14
     * 
     * @return
     */
    public static String getNowDate() {
        return DATE_TIME_FORMAT.format(new java.util.Date());
    }

    public static String getNowDateTime() {
        return DATE_TIME_FULL_FORMAT.format(new Date());
    }

    public static String getFormatNowDateTime() {
        return DATE_TIME_FORMAT_FULL_FORMAT.format(new Date());
    }

    public static String getNowDateYMD() {
        return DATE_FORMAT.format(new java.util.Date());

    }

    public static String getNowDateYMDNHYPHEN() {
        return DATE_FORMAT_NO_HYPHEN.format(new java.util.Date());

    }

    public static String getNowDateYM() {
        return MONTH_FORMAT.format(new java.util.Date());
    }

    /**
     * 
     * @param fmt
     * @return
     */
    public static String geFormatDateStr(String fmt, Date dt) {
        if (dt == null)
            return "";
        return new SimpleDateFormat(fmt).format(dt);
    }

    public static String formatYM(Date dt) {
        return MONTH_FORMAT.format(dt);
    }

    public static String formatYMD(Date dt) {
        return DATE_FORMAT.format(dt);
    }

    public static String formatYMD(String dateStr) {
        Date date = getDateByStr(dateStr);
        return DATE_FORMAT.format(date);
    }

    public static String formatYMD(String dateStr, String format) {
        Date date = null;
        try {
            date = new SimpleDateFormat(format).parse(dateStr);
        } catch (ParseException e) {
            date = Timestamp.valueOf(dateStr + " 00:00:00.000000000");
        }
        return DATE_FORMAT.format(date);
    }

    public static String formatYMDWithException(String dateStr)
            throws ParseException {
        Date date = DATE_FORMAT.parse(dateStr);
        return DATE_FORMAT.format(date);
    }

    public static String formatYMDWithException(String dateStr, String format)
            throws ParseException {
        Date date = new SimpleDateFormat(format).parse(dateStr);
        return DATE_FORMAT.format(date);
    }

    public static String formatYMDHMS(Date dt) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dt);
    }

    public static String formatHMS(Date dt) {
        return new SimpleDateFormat("HH:mm:ss").format(dt);
    }

    public static String formatHM(Date dt) {
        return new SimpleDateFormat("HH:mm").format(dt);
    }

    public static String formatHM(String dateStr) {
        Date date = getSpecialDate(dateStr);
        return new SimpleDateFormat("HH:mm").format(date);
    }

    /**
     * get Sun. and Sat. of the week(determined by theDay)
     * 
     * @param theDay
     * @return
     * @author Gavin.Hou 2006/7/24
     */
    public static String[] getWeekStartAndEnd(String theDay) {
        Date dt = null;
        if (theDay == null || theDay.trim().equals("")) {
            dt = new Date();
        } else {
            try {
                dt = DATE_FORMAT.parse(theDay);
            } catch (Exception e) {
            }
        }

        String[] dst = new String[2];

        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int n = cal.get(Calendar.DAY_OF_WEEK);
        if (n == Calendar.SUNDAY) {
            // cal.add(Calendar.WEEK_OF_YEAR, -1);
        }

        cal.add(Calendar.DATE, 1 - n);
        dst[0] = formatYMD(cal.getTime());

        cal.add(Calendar.DATE, 7);
        dst[1] = formatYMD(cal.getTime());

        return dst;
    }

    /**
     * get Monday and Sunday of the week(determined by theDay)
     * 
     * @param theDay
     * @return
     * @author Gavin 2006/12/19
     * @update Gavin 2006/12/28
     */
    public static String[] getMondayAndSunday(String theDay) {
        Date dt = null;
        if (theDay == null || theDay.trim().equals("")) {
            dt = new Date();
        } else {
            try {
                dt = DATE_FORMAT.parse(theDay);
            } catch (Exception e) {
            }
        }

        String[] dst = new String[2];

        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int n = cal.get(Calendar.DAY_OF_WEEK);

        if (n == Calendar.SUNDAY)
            cal.add(Calendar.WEEK_OF_YEAR, -1);

        cal.add(Calendar.DATE, 2 - n);
        dst[0] = formatYMD(cal.getTime());

        cal.add(Calendar.DATE, 6);
        dst[1] = formatYMD(cal.getTime());

        return dst;
    }

    /**
     * 
     * @param dateStr
     * @param offset
     * @return
     */
    public static String getFirstDayOfWeek(String dateStr, int offset) {
        Date date = null;
        try {
            date = DATE_FORMAT.parse(dateStr);
        } catch (ParseException e) {
            date = Timestamp.valueOf(dateStr + " 00:00:00.000000000");
        }

        return DATE_FORMAT.format(getFirstDayOfWeek(date, offset));
    }

    /**
     * 
     * @param date
     * @param offset
     * @return
     */
    public static Date getFirstDayOfWeek(Date date, int offset) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.add(Calendar.DATE, offset);
        String days[] = getMondayAndSunday(formatYMD(cal.getTime()));
        cal.setTime(getDateByStr(days[0]));

        return cal.getTime();
    }

    /**
     * 
     * @param dateStr
     * @return
     */
    public static Date getDateByStr(String dateStr) {
        Date date = null;
        try {
            if (dateStr.indexOf(" ") > 0) {
                date = DATE_TIME_FULL_FORMAT.parse(dateStr);
            } else {
                date = DATE_FORMAT.parse(dateStr);
            }

        } catch (ParseException e) {
            System.out.println("Input Parameter is : " + dateStr);
            date = Timestamp.valueOf(dateStr + " 00:00:00.000000000");
            e.printStackTrace();
        }

        return date;
    }

    public static Date getSpecialDate(String datetime) {
        try {
            return DATE_TIME_FULL_FORMAT.parse(datetime);
        } catch (Exception e) {
            System.out.println("Input Parameter is : " + datetime);
            return Timestamp.valueOf(datetime + " 00:00:00.000000000");
        }
    }

    public static Date getSpecialDateFormat(String date) {
        try {
            return SPECIAL_DATE_FORMAT.parse(date);
        } catch (Exception e) {
            System.out.println("Input Parameter is : " + date);
            return Timestamp.valueOf(date);
        }
    }

    /**
     * 
     * @description 时间格式转化为M月d日 <br/>
     * 
     * @param date
     * @return String
     * @throws
     */
    public static String getMonthDayDateFormat(Date date) {
        try {
            return MONTH_DAY_FORMAT.format(date);
        } catch (Exception e) {
            System.out.println("Input Parameter is : " + date);
            return "";
        }
    }

    /**
     * 
     * @description 时间格式转化为y.m.d<br/>
     * 
     * @param date
     * @return String
     * @throws
     */
    public static String getYearMonthDayDateFormat(Date date) {
        try {
            return YEAR_MONTH_DAY_FORMAT.format(date);
        } catch (Exception e) {
            System.out.println("Input Parameter is : " + date);
            return "";
        }
    }

    /**
     * 
     * @description 时间格式转化为M月d日 HH:00 <br/>
     * 
     * @param date
     * @return String
     * @throws
     */
    public static String getMonthDayHourDateFormat(Date date) {
        try {
            return MONTH_DAY_HOUR_FORMAT.format(date);
        } catch (Exception e) {
            System.out.println("Input Parameter is : " + date);
            return "";
        }
    }

    public static Date getDateByStr(String dateStr, int offset) {
        Date date = null;
        try {
            date = DATE_FORMAT.parse(dateStr);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, offset);
            date = cal.getTime();
        } catch (ParseException e) {
            System.out.println("Input Parameter is : " + dateStr);
            date = Timestamp.valueOf(dateStr + " 00:00:00.000000000");
        }

        return date;
    }

    public static String retrieveTomorrowDate(String todayDate) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(todayDate);
            cal.setTime(date);
            cal.set(Calendar.HOUR, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);

            cal.add(Calendar.DAY_OF_MONTH, 1);
            return format.format(cal.getTime());
        } catch (Exception e) {
            System.out.println("Input Parameter is : " + todayDate);
            e.printStackTrace();
        }

        return "";
    }

    public static int retrieveWeekNoInYear(String date) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            cal.setTime(format.parse(date));
            return cal.get(Calendar.WEEK_OF_YEAR);
        } catch (Exception e) {
            System.out.println("Input Parameter is : " + date);
            e.printStackTrace();
        }
        return 0;
    }

    public static int retrieveWeekNoInYearStartFromMon(String date) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            cal.setFirstDayOfWeek(Calendar.MONDAY);
            cal.setTime(format.parse(date));
            return cal.get(Calendar.WEEK_OF_YEAR);
        } catch (Exception e) {
            System.out.println("Input Parameter is : " + date);
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Get First Day Of Week
     * 
     * @param year
     * @param week
     * @return String
     * @author Jacky 2007/11/05
     */
    public static String getFirstDayOfWeek(int year, int week) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, 1);

        Calendar cal = (Calendar) c.clone();
        cal.add(Calendar.DATE, (week - 1) * 7);

        c.setFirstDayOfWeek(Calendar.SUNDAY);
        c.setTime(cal.getTime());
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Sunday
        return formatYMD(c.getTime());
    }

    /**
     * Get Last Day Of Week
     * 
     * @param year
     * @param week
     * @return String
     * @author Jacky 2007/11/05
     */
    public static String getLastDayOfWeek(int year, int week) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, 1);

        Calendar cal = (Calendar) c.clone();
        cal.add(Calendar.DATE, (week - 1) * 7);

        c.setFirstDayOfWeek(Calendar.SUNDAY);
        c.setTime(cal.getTime());
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
        return formatYMD(c.getTime());
    }

    public static String[] getWeekStartAndEnd(int year, int week) {
        String weeks[] = new String[2];
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.SUNDAY);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, week);

        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        weeks[0] = formatYMD(cal.getTime());

        cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        weeks[1] = formatYMD(cal.getTime());

        return weeks;
    }

    public static List<String> getDateFromTo(String dateFrom, String dateTo) {
        List<String> dateList = new ArrayList<String>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(UtilDatetime.getDateByStr(dateFrom));

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(UtilDatetime.getDateByStr(dateTo));

        while (cal.getTimeInMillis() <= cal2.getTimeInMillis()) {
            dateList.add(UtilDatetime.formatYMD(cal.getTime()));
            cal.add(Calendar.DATE, 1);
        }
        return dateList;
    }

    /**
     * 
     * @param theDay
     * @return
     */
    public static String[] getWeekDates(String theDay) {
        Date dt = null;
        if (theDay == null || theDay.trim().equals("")) {
            dt = new Date();
        } else {
            try {
                dt = DATE_FORMAT.parse(theDay);
            } catch (Exception e) {
                System.out.println("Input Parameter is : " + theDay);
            }
        }

        String[] dst = new String[7];

        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int n = cal.get(Calendar.DAY_OF_WEEK);

        if (n == Calendar.SUNDAY)
            cal.add(Calendar.WEEK_OF_YEAR, -1);
        // cal.setFirstDayOfWeek(Calendar.SUNDAY);
        cal.add(Calendar.DATE, 1 - n);

        for (int i = 0; i < 7; ++i) {
            dst[i] = formatYMD(cal.getTime());
            cal.add(Calendar.DATE, 1);
        }
        return dst;
    }

    public static String[] getWeekStartAndEnd17(String yyyyww) {
        int year = Integer.parseInt(yyyyww.substring(0, 4));
        int week = Integer.parseInt(yyyyww.substring(4));
        return getWeekStartAndEnd(year, week);
    }

    public static String getHourTime() {
        return TIME_FULL_FORMAT.format(new Date());
    }

    public static String getHourMinuteTime() {
        return HOUR_FORMAT.format(new Date());
    }

    public static String getStartDateOfMonth(String date) {
        try {
            Date start = DATE_FORMAT.parse(date);
            Calendar cal = Calendar.getInstance();
            cal.setTime(start);
            cal.set(Calendar.DATE, 1);
            Date end = cal.getTime();
            return DATE_FORMAT.format(end);
        } catch (Exception e) {
            System.out.println("Input Parameter is : " + date);
            return null;
        }
    }

    public static Date getStartDateOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DATE, 1);
        return cal.getTime();
    }

    public static String getEndDateOfMonth(String startDate) {
        try {
            Date start = DATE_FORMAT.parse(startDate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(start);
            cal.add(Calendar.MONTH, 1);
            cal.set(Calendar.DATE, 1);
            cal.add(Calendar.DATE, -1);
            Date end = cal.getTime();
            return DATE_FORMAT.format(end);
        } catch (Exception e) {
            System.out.println("Input Parameter is : " + startDate);
            return null;
        }
    }

    public static Date getEndDateOfMonth(Date date) {
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.MONTH, 1);
            cal.set(Calendar.DATE, 1);
            cal.add(Calendar.DATE, -1);
            Date end = cal.getTime();
            return end;
        } catch (Exception e) {
            System.out.println("Input Parameter is : " + date);
            return null;
        }
    }

    /**
     * get represented date period for special logicDate to solve the problem of
     * month crossing. eg. 2008-10-06 --> 2008-10-01~2008-10-12, 2008-10-13 -->
     * 2008-10-13~2008-10-19, 2008-10-27 --> 2008-10-27~2008-10-31
     * 
     * @param logicDate
     * @return String
     */
    public static String getLogicDatePeriod(Date logicDate) {
        try {
            String period = null;

            Calendar cal = Calendar.getInstance();
            cal.setTime(logicDate);

            int n = cal.get(Calendar.DAY_OF_WEEK);
            if (n == Calendar.SUNDAY)
                cal.add(Calendar.WEEK_OF_YEAR, -1);

            // get Monday of this week
            cal.add(Calendar.DATE, 2 - n);
            Date monday = cal.getTime();
            int firstMonth = cal.get(Calendar.MONTH);
            Calendar preMonday = (Calendar) cal.clone();
            preMonday.add(Calendar.DATE, -7);

            // get Sunday of this week
            cal.add(Calendar.DATE, 6);
            Date sunday = cal.getTime();
            int endMonth = cal.get(Calendar.MONTH);
            Calendar preSunday = (Calendar) cal.clone();
            preSunday.add(Calendar.DATE, -7);

            if (firstMonth != endMonth) {
                // get end date of this month
                cal.set(Calendar.MONTH, endMonth);
                cal.set(Calendar.DATE, 1);
                cal.add(Calendar.DATE, -1);

                period = DATE_FORMAT.format(monday) + "~"
                        + DATE_FORMAT.format(cal.getTime());
            } else {
                // check if date crossing month this week
                if (preMonday.get(Calendar.MONTH) != preSunday
                        .get(Calendar.MONTH)) {
                    // yes
                    cal.set(Calendar.MONTH, firstMonth);
                    cal.set(Calendar.DATE, 1);

                    period = DATE_FORMAT.format(cal.getTime()) + "~"
                            + DATE_FORMAT.format(sunday);
                } else {
                    // non crossing
                    period = DATE_FORMAT.format(monday) + "~"
                            + DATE_FORMAT.format(sunday);
                }
            }

            return period;
        } catch (Exception e) {
            return null;
        }
    }

    // public static String getFirstLogicDate(Date logicDate){
    // Calendar cal = Calendar.getInstance();
    // cal.setTime(logicDate);
    //
    // int n = cal.get(Calendar.DAY_OF_WEEK);
    // if( n == Calendar.SUNDAY )
    // cal.add(Calendar.WEEK_OF_YEAR, -1);
    //
    // //get Monday of this week
    // cal.add(Calendar.DATE, 2-n);
    // return DATE_FORMAT.format(cal.getTime());
    // }

    public static String getLastWeekLogicPeriod(Date logicDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(logicDate);
        cal.add(Calendar.DATE, -7);

        return getLogicDatePeriod(cal.getTime());
    }

    public static List getDateList(int year, int month, int dayOfWeek) {
        List list = new ArrayList();

        // init calendar to the first day of month
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        while (cal.get(Calendar.MONTH) == month - 1) {

            if (cal.get(Calendar.DAY_OF_WEEK) - 1 == dayOfWeek)
                list.add(cal.getTime());

            cal.add(Calendar.DATE, 1);
        }

        return list;
    }

    /**
     * Get Monday and Sunday of last week. If last week contains the day of last
     * month and this month, just return the days of last month. If previous
     * week of last week cross the months, last week start from 1st day of this
     * month.
     * 
     * @param today
     *            : today yyyy-mm-dd
     * @return array of String, Monday and Sunday / or last day of last month
     */
    public static String[] getLastWeek(String today) {
        String[] twoDays = new String[2];
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(DATE_FORMAT.parse(today));
            Calendar currentCal = (Calendar) cal.clone();

            cal.add(Calendar.DATE, -7);

            // last week
            String[] wks = getWeekDates(DATE_FORMAT.format(cal.getTime()));
            if (wks[0].startsWith(wks[6].substring(0, 7))) {
                twoDays[0] = wks[0];
                twoDays[1] = wks[6];

                cal = Calendar.getInstance();
                cal.setTime(DATE_FORMAT.parse(twoDays[0]));
                cal.add(Calendar.DATE, -7);
                String firstDayOfPreWeek = DATE_FORMAT.format(cal.getTime());
                if (!wks[0].startsWith(firstDayOfPreWeek.substring(0, 7))) {
                    cal = (Calendar) currentCal.clone();
                    cal.set(Calendar.DATE, 1);
                    twoDays[0] = DATE_FORMAT.format(cal.getTime());
                }
            } else {
                cal = (Calendar) currentCal.clone();
                cal.set(Calendar.DATE, 1);
                cal.add(Calendar.DATE, -1);

                twoDays[0] = wks[0];
                twoDays[1] = DATE_FORMAT.format(cal.getTime());
            }
        } catch (Exception e) {
            return null;
        }
        return twoDays;
    }

    /**
     * @see getLastWeek(String)
     * @param today
     * @return
     */
    public static Date[] getLastWeek(Date today) {
        String[] sDays = getLastWeek(DATE_FORMAT.format(today));
        if (sDays == null)
            return null;
        Date[] twoDays = new Date[2];
        try {
            twoDays[0] = DATE_FORMAT.parse(sDays[0]);
            twoDays[1] = DATE_FORMAT.parse(sDays[1]);
        } catch (ParseException e) {
            return null;
        }
        return twoDays;
    }

    /**
     * get day of week:
     * 
     * @param day
     * @return 1-Mon, ..., 5-Fri, 6-Sat, 7-Sun
     */
    public static int getDayOfWeek(Date day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayOfWeek == 0)
            return 7;
        return (dayOfWeek == 0 ? 7 : dayOfWeek);
    }

    public static int getDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 
     * getDateByWeekDay 计算周几对应的日期
     * 
     * @param year
     * @param week
     * @param weekDay
     * @return String 返回类型
     */
    public static String getDateByWeekDay(int year, int week, int weekDay) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.DAY_OF_WEEK, weekDay + 1);
        cal.set(Calendar.WEEK_OF_YEAR, week);
        cal.setFirstDayOfWeek(Calendar.SUNDAY);
        return formatYMD(cal.getTime());
    }

    public static int getDayOfWeek(String day) {
        try {
            return getDayOfWeek(DATE_FORMAT.parse(day));
        } catch (Exception e) {
            return 0;
        }
    }

    // 获取日期所在月第几周
    public static int getWeekOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(cal.WEEK_OF_MONTH);
    }

    // 获取当前周是所在月第几周
    public static int getWeekOfMonth(String yearWeek) {
        Calendar cal = Calendar.getInstance();
        int year = 0;
        int week = 0;
        year = Integer.parseInt(yearWeek.split("-")[0]);
        week = Integer.parseInt(yearWeek.split("-")[1]);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, week);
        return cal.get(cal.WEEK_OF_MONTH);
    }

    public static Date retrieveLogicDateOfLastWeek(Date specialDate,
            int dayOfWeek) {
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(specialDate);

            // relative last week's date
            cal.add(Calendar.DATE, -7);

            int val = cal.get(Calendar.DAY_OF_WEEK) - 1;
            int offset = val - dayOfWeek;

            cal.add(Calendar.DATE, -offset);

            return cal.getTime();
        } catch (Exception e) {
            return null;
        }
    }

    public static Date retrieveLogicDateOfCurrentWeek(Date specialDate,
            int dayOfWeek) {
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(specialDate);

            int val = cal.get(Calendar.DAY_OF_WEEK) - 1;
            int offset = val - dayOfWeek;

            cal.add(Calendar.DATE, -offset);

            return cal.getTime();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 某一时间的偏移运算(增或减几日)
     * 
     * @param Date
     * @param skipDate
     *            日偏移量
     * @return Date 偏移日期
     */
    public static Date getSkipTime(Date date, int skipDay) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, skipDay);
        return cal.getTime();
    }

    public static String getSkipTime(String strdate, String format, int skipDay) {
        try {
            Date date = new SimpleDateFormat(format).parse(strdate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DAY_OF_MONTH, skipDay);
            return new SimpleDateFormat(format).format(cal.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getSkipMinute(String strdate, String format,
            int skipMinute) {
        try {
            Date date = new SimpleDateFormat(format).parse(strdate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.MINUTE, skipMinute);
            return new SimpleDateFormat(format).format(cal.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date getSkipTime(Date date, int skipYear, int skipMonth,
            int skipDay, int skipHour, int skipMinute) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, skipYear);
        cal.add(Calendar.MONTH, skipMonth);
        cal.add(Calendar.DAY_OF_MONTH, skipDay);
        cal.add(Calendar.HOUR_OF_DAY, skipHour);
        cal.add(Calendar.MINUTE, skipMinute);

        return cal.getTime();
    }

    public static String getLogicDateOfCurrentWeek(String dateStr) {
        String firstdate = getFirstDayOfWeek(dateStr, 0);
        String logicDate = getFirstDayOfWeek(dateStr, 0);
        int m1 = getMonth(getDateByStr(firstdate));
        int m2 = getMonth(getDateByStr(dateStr));
        if (m1 != m2) {
            logicDate = formatYMD(getDateByStr(firstdate, 7));
        }
        return logicDate;
    }

    /**
     * get difference of day
     * 
     * @param startDate
     * @param endDate
     * @return
     */
    public static long getDaysDistance(String startDate, String endDate) {
        try {
            return getDaysDistance(DATE_FORMAT.parse(startDate),
                    DATE_FORMAT.parse(endDate));
        } catch (Exception e) {

        }
        return 0;
    }

    public static double getHoursDistance(String startDate, String endDate) {
        try {
            SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date begin = dfs.parse(startDate);
            java.util.Date end = dfs.parse(endDate);
            double between = (end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒
            DecimalFormat fnum = new DecimalFormat("##0.00");

            return Double
                    .parseDouble(fnum.format(between % (24 * 3600) / 3600));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static double getMinutesDistance(String startDate, String endDate) {
        try {
            SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date begin = dfs.parse(startDate);
            java.util.Date end = dfs.parse(endDate);
            double between = (end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒
            DecimalFormat fnum = new DecimalFormat("#");

            return Double.parseDouble(fnum.format(between % (24 * 3600) / 60));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static double convertMinute2Hour(double minute) {
        DecimalFormat fnum = new DecimalFormat("#.#");
        return Double.parseDouble(fnum.format(minute / 60));
    }

    /**
     * get difference of day
     * 
     * @param startDate
     * @param endDate
     * @return
     */
    public static long getDaysDistance(Date startDate, Date endDate) {
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(startDate);
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(endDate);
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);
        toCalendar.set(Calendar.MINUTE, 0);
        toCalendar.set(Calendar.SECOND, 0);
        toCalendar.set(Calendar.MILLISECOND, 0);

        return (toCalendar.getTime().getTime() - fromCalendar.getTime()
                .getTime()) / (1000 * 60 * 60 * 24);
    }

    /**
     * 从月初到选择时间的日期间隔，单位天
     * 
     * @param endDate
     * @return
     * @since OF-v1.0
     * @author Oscar Xu, 2013-10-2 下午8:36:08
     */
    public static long getDaysDistanceFromMonthStart(String endDate) {
        String startDate = getStartDateOfMonth(endDate);
        return getDaysDistance(startDate, endDate);
    }

    /**
     * 开始时间到这个月底的日期间隔，单位天
     * 
     * @param startDate
     * @return
     * @since OF-v1.0
     * @author Oscar Xu, 2013-10-2 下午8:37:15
     */
    public static long getDaysDistanceToMonthEnd(String startDate) {
        String endDate = getEndDateOfMonth(startDate);
        return getDaysDistance(startDate, endDate);
    }

    /**
     * get differences of month
     * 
     * @param beginDate
     * @param endDate
     * @return
     * @throws ParseException
     * @author Gavin
     */
    public static int getMonthDistance(String beginDate, String endDate)
            throws ParseException {
        return getMonthDistance(DATE_FORMAT.parse(beginDate),
                DATE_FORMAT.parse(endDate));
    }

    /**
     * get differences of month
     * 
     * @param begindate
     * @param enddate
     * @return
     */
    public static int getMonthDistance(Date begindate, Date enddate) {
        int diff = 0;

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(begindate);
        int y1 = cal1.get(Calendar.YEAR) + 1;
        int m1 = cal1.get(Calendar.MONTH) + 1;

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(enddate);
        int y2 = cal2.get(Calendar.YEAR) + 1;
        int m2 = cal2.get(Calendar.MONTH) + 1;

        if (cal1.before(cal2)) {
            diff = (y2 - y1) * 12 + m2 - m1;
        } else {
            diff = -((y1 - y2) * 12 + m1 - m2);
        }

        return diff;
    }

    public static int getChineseWeekOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.setFirstDayOfWeek(cal.MONDAY);
        return cal.get(Calendar.WEEK_OF_MONTH);
    }

    public static String getStartSaleDate(Date endDate, int week, int hour) {
        String endDateStr = DATE_FORMAT.format(endDate);
        String startDateStr = null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(endDate);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(endDate);
        cal2.set(Calendar.DATE, 1);

        // is Monday
        if (getDayOfWeek(endDateStr) == 1 && endDate.before(cal.getTime())) {
            // is 1st Day?
            if (getStartDateOfMonth(endDateStr).compareTo(
                    DATE_FORMAT.format(getSkipTime(endDate, -7))) >= 0
                    && !getStartDateOfMonth(endDateStr).equals(endDateStr)) {
                startDateStr = getStartDateOfMonth(endDateStr);
            } else {
                startDateStr = DATE_FORMAT.format(getSkipTime(endDate, -7));
            }
        }
        // is 1st Day of Month (not monday)
        else if (getStartDateOfMonth(endDateStr).equals(endDateStr)
                && endDate.before(cal.getTime())) {
            startDateStr = getFirstDayOfWeek(endDateStr, 0);
        }
        // non monday or 1st day
        else {
            if (getStartDateOfMonth(endDateStr).compareTo(
                    getFirstDayOfWeek(endDateStr, 0)) >= 0) {
                startDateStr = getStartDateOfMonth(endDateStr);
            } else {
                startDateStr = getFirstDayOfWeek(endDateStr, 0);
            }
        }
        return startDateStr;
    }

    /**
     * 获取日期date的前n个月的月份值
     * 
     * @param date
     * @param n
     * @return 返回值格式:yyyy-mm,返回示例:2013-2
     */
    public static String getPreviusMonth(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -n);// 在当前月的基础上取n月
        return new SimpleDateFormat("yyyy-MM").format(cal.getTime());
    }

    /**
     * 获取date前或者后的几个月
     * 
     * @param date
     * @param n
     * @return 返回值格式:yyyy-mm,返回示例:2013-2
     * @since OF-v1.0
     * @author Oscar Xu, 2013-10-2 下午8:52:31
     */
    public static String getMonth(Date date, int n) {
        return getMonth(date, n, false);
    }

    public static String getMonth(Date date, int n, boolean noZero) {
        SimpleDateFormat format = null;
        if (noZero) {
            format = MONTH_NO_ZERO_FORMAT;
        } else {
            format = MONTH_FORMAT;
        }
        Calendar cal = Calendar.getInstance();
        if (date != null)
            cal.setTime(date);
        cal.add(Calendar.MONTH, n);// 在当前月的基础上取n月
        return format.format(cal.getTime());
    }

    public static String getMonthStr(String dateStr, int n, boolean noZero) {
        String month = null;
        try {
            Date date = MONTH_FORMAT.parse(dateStr);
            return getMonth(date, n, noZero);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return month;
    }

    public static String getMonthStr(String dateStr, int n) {
        return getMonthStr(dateStr, n, false);
    }

    public static String getMonth(int n) {
        return getMonth(null, n);
    }

    public static String getPreviousMonthFirst(int k) {
        String str = "";
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE, 1);
        lastDate.add(Calendar.MONTH, k);

        str = DATE_FORMAT.format(lastDate.getTime());
        return str;
    }

    public static String getPreviousMonthLast(int k) {
        String str = "";
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE, 1);
        lastDate.add(Calendar.MONTH, k + 1);
        lastDate.add(Calendar.DATE, -1);

        str = DATE_FORMAT.format(lastDate.getTime());
        return str;
    }

    public static Date add(Date originalDate, int addYear, int addMonth,
            int addDate, int addHour, int addMin, int addSec, int addMilliSec) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(originalDate);
        cal.add(Calendar.YEAR, addYear);
        cal.add(Calendar.MONTH, addMonth);
        cal.add(Calendar.DATE, addDate);
        cal.add(Calendar.HOUR, addHour);
        cal.add(Calendar.MINUTE, addMin);
        cal.add(Calendar.SECOND, addSec);
        cal.add(Calendar.MILLISECOND, addMilliSec);
        return cal.getTime();
    }

    public static String getYearSeasonByDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int season = month / 3 + 1;
        String yearSeason = year + "0" + season;
        return yearSeason;
    }

    public static String getCurrentYearSeason() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int season = month / 3 + 1;
        String yearSeason = year + "0" + season;
        return yearSeason;
    }

    public static int getCurrentYear() {
        return getYear(new Date());
    }

    public static String getDifferenceDay(String dayOne, String dayTwo, int unit) {
        long day = 0;
        long period = 1;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (unit == Calendar.DATE) {
            period = 1000 * 60 * 60 * 24;
        } else if (unit == Calendar.HOUR) {
            period = 1000 * 60 * 60;
        } else if (unit == Calendar.MINUTE) {
            period = 1000 * 60;
        } else if (unit == Calendar.SECOND) {
            period = 1000;
        }
        try {
            java.util.Date dateOne = format.parse(dayOne);
            java.util.Date dateTwo = format.parse(dayTwo);
            day = (dateOne.getTime() - dateTwo.getTime()) / period;
        } catch (Exception e) {
            return "";
        }
        return day + "";
    }

    // 获取当前时间所在年的第几周
    public static int getWeekOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(cal.WEEK_OF_YEAR);
    }

    // 获取当前时间所在年的最大周数
    public static int getMaxWeekNumOfYear(int year) {
        Calendar c = new GregorianCalendar();
        c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setMinimalDaysInFirstWeek(7);
        c.setTime(c.getTime());
        return c.get(Calendar.WEEK_OF_YEAR);

    }

    /**
     * 
     * getNextWeek (根据当前周获取下周)
     * 
     * @param theYearWeek
     * @return 实例：2014-1 String 返回类型
     */
    public static String getNextWeek(String theYearWeek) {
        String nextWeek = "";
        int year = 0;
        if (Integer.parseInt(theYearWeek.split("-")[1]) > getMaxWeekNumOfYear(Integer
                .parseInt(theYearWeek.split("-")[0]))) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.parseInt(theYearWeek.split("-")[0]));
        calendar.set(Calendar.WEEK_OF_YEAR,
                Integer.parseInt(theYearWeek.split("-")[1]));
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        year = calendar.get(Calendar.YEAR);
        if (Integer.parseInt(theYearWeek.split("-")[1]) > calendar
                .get(Calendar.WEEK_OF_YEAR)) {
            year += 1;
        }
        nextWeek = year + "-" + calendar.get(Calendar.WEEK_OF_YEAR);
        return nextWeek;
    }

    /**
     * 
     * getPreviousWeek (根据当前周获取上一周)
     * 
     * @param theYearWeek
     * @return String 返回类型
     */
    public static String getPreviousWeek(String theYearWeek) {
        String previousWeek = "";
        int year = 0;
        if (Integer.parseInt(theYearWeek.split("-")[1]) > getMaxWeekNumOfYear(Integer
                .parseInt(theYearWeek.split("-")[0]))) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.parseInt(theYearWeek.split("-")[0]));
        calendar.set(Calendar.WEEK_OF_YEAR,
                Integer.parseInt(theYearWeek.split("-")[1]));
        calendar.add(Calendar.DAY_OF_MONTH, -7);
        year = calendar.get(Calendar.YEAR);
        if (Integer.parseInt(theYearWeek.split("-")[1]) > calendar
                .get(Calendar.WEEK_OF_YEAR)) {
            year = Integer.parseInt(theYearWeek.split("-")[0]);
        }
        previousWeek = year + "-" + calendar.get(Calendar.WEEK_OF_YEAR);
        /*
         * int currentWeek = 0; int year = 0; year =
         * Integer.parseInt(currentYearWeek.split("-")[0]); currentWeek =
         * Integer.parseInt(currentYearWeek.split("-")[1]); if (currentWeek > 1)
         * { previousWeek = String.valueOf(year) + "-" +
         * String.valueOf(currentWeek - 1); } else { previousWeek =
         * String.valueOf(year - 1) + "-" +
         * String.valueOf(getMaxWeekNumOfYear(year - 1)); }
         */
        return previousWeek;
    }

    /**
     * 获取某年第一天日期
     * 
     * @param year
     *            年份
     * @return Date
     */
    public static Date getCurrYearFirst(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    /**
     * 获取某年最后一天日期
     * 
     * @param year
     *            年份
     * @return Date
     */
    public static Date getCurrYearLast(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();

        return currYearLast;
    }

    public static String getYearWeek(Date date) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        int week = cl.get(Calendar.WEEK_OF_YEAR);
        cl.add(Calendar.DAY_OF_MONTH, -7);
        int year = cl.get(Calendar.YEAR);
        if (week < cl.get(Calendar.WEEK_OF_YEAR)) {
            year += 1;
        }
        return year + "-" + week;
    }

    public static List<String> getWeekAllDatesList(int year, int week,
            String format) {
        List<String> result = new ArrayList<String>();
        String weeks[] = new String[2];
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.SUNDAY);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, week);
        /* Format */
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        for (int i = GregorianCalendar.SUNDAY; i <= GregorianCalendar.SATURDAY; i++) {
            cal.set(Calendar.DAY_OF_WEEK, i);
            result.add(sdf.format(cal.getTime()));
        }
        return result;
    }

    public static List<String> getWeekDatesByDateAndFormat(Date date,
            String format) {
        List<String> result = new ArrayList<String>();
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        /* Format */
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        for (int i = GregorianCalendar.SUNDAY; i <= GregorianCalendar.SATURDAY; i++) {
            c.set(Calendar.DAY_OF_WEEK, i);
            result.add(sdf.format(c.getTime()));
        }
        return result;
    }

    public static String getFirstDateOfWeekByDateAndFormat(Date date,
            String format) {
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, GregorianCalendar.SUNDAY);
        /* Format */
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(c.getTime());
    }

    public static String getLastDateOfWeekByDateAndFormat(Date date,
            String format) {
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, GregorianCalendar.SATURDAY);
        /* Format */
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(c.getTime());
    }

    public static int getWeekNumberByDate(Date date) {
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        return c.get(Calendar.WEEK_OF_YEAR);
    }

    public static int getYearNumberByDate(Date date) {
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    /**
     * @param date
     * @param n
     * @param dateFormat
     * @return
     */
    public static Date getPreviusDate(Date date, int n) {
        Calendar day = Calendar.getInstance();
        day.setTime(date);
        day.add(Calendar.DAY_OF_MONTH, -n);
        return day.getTime();
    }

    public static Date getPreviusDate(String date, int n) {
        Calendar day = Calendar.getInstance();
        day.setTime(getDateByStr(date));
        day.add(Calendar.DAY_OF_MONTH, -n);
        return day.getTime();
    }

    public static String getPreviusStringDate(Date date, int n,
            String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(getPreviusDate(date, n));
    }

    public static String getPreviusStringDate(String date, int n,
            String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(getPreviusDate(date, n));
    }

    public static Date getSkipDate(Date date, int n) {
        return getPreviusDate(date, -n);
    }

    public static Date getSkipDate(String date, int n) {
        return getPreviusDate(date, -n);
    }

    public static String getSkipStringDate(Date date, int n, String dateFormat) {
        return getPreviusStringDate(date, -n, dateFormat);
    }

    public static String getSkipStringDate(String date, int n, String dateFormat) {
        return getPreviusStringDate(date, -n, dateFormat);
    }

    /**
     * 
     * @Title: getWeekOfDate
     * @Description: 获取日期是周几
     * @param @param dt
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     */
    public static String getWeekDay(Date dt) {
        String[] weekDays = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 取天的开始时间.
     * 
     * @param date
     *            日期
     * @return Date
     */
    public static Date getStartTimeByDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 取天的结束时间.
     * 
     * @param date
     *            日期
     * @return Date
     */
    public static Date getEndTimeByDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public static Timestamp Date2Time(String datetime) {
        if (null == datetime || "".equals(datetime)) {
            return null;
        }
        try {
            return new Timestamp(DATE_TIME_FULL_FORMAT.parse(datetime)
                    .getTime());
        } catch (ParseException e) {
            return null;
        }
    }

    public static String Time2Date(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        } else {
            Date currentTime = timestamp;
            String dateString = DATE_TIME_FULL_FORMAT.format(currentTime);
            return dateString;
        }
    }

    public static void main(String[] args) throws Exception {
        // System.out.println(UtilDatetime.getDayOfWeek(new Date()));
        // System.out.println(UtilDatetime.getMonthDayDateFormat(new Date()));

        /*
         * long n = getDaysDistance("2014-05-01", "2014-05-01");
         * System.out.println(getDateByStr("2014-06-24 01:00:02"));
         * System.out.println(getDateByStr("2014-06-24"));
         * 
         * Date[] dates = getLastWeek(new Date()); for (Date d : dates) {
         * System.out.println(d); } System.out.println(""); List<String> result
         * = getWeekDatesByDateAndFormat(new Date(), "yyyy-MM-dd");
         * System.out.println(""); for (String dd : result) {
         * System.out.println(dd); }
         * 
         * int year = UtilDatetime.getYearNumberByDate(new Date()); int week =
         * UtilDatetime.getWeekNumberByDate(new Date());
         * System.out.println("year:" + year); System.out.println("week:" +
         * week);
         * 
         * List<String> ywdates = UtilDatetime.getWeekAllDatesList(year, week,
         * "yyyy-MM-dd"); for (String dd : ywdates) { System.out.println(dd); }
         */

        String hostDate = "2016/12/20" + " " + "20:46:03";
        SimpleDateFormat DATE_TIME_FULL_FORMAT = new SimpleDateFormat(
                "yyyy/MM/dd HH:mm:ss");
        // DATE_TIME_FULL_FORMAT.parse(hostDate);

        System.out.println(DATE_TIME_FULL_FORMAT.parse(hostDate));
    }

}
