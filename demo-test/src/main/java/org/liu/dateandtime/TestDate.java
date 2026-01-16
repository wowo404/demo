package org.liu.dateandtime;

import cn.hutool.core.date.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class TestDate {

    public static final long ONE_DAY_MILSEC = 24 * 60 * 60 * 1000;
    public static final long FIRST_DAY_OFFSET_MILSEC = 8 * 60 * 60 * 1000;
    public static final String COMMON_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final int ONE_YEAR_MONTH_TOTAL = 12;

    /**
     * 计算2个日期相差多少 天 (d1-d2)
     */
    public static Long dateDayDiff(Date d1, Date d2) {
        long millis1 = d1.getTime() + FIRST_DAY_OFFSET_MILSEC;
        long millis2 = d2.getTime() + FIRST_DAY_OFFSET_MILSEC;
        long day1 = millis1 / ONE_DAY_MILSEC;
        long day2 = millis2 / ONE_DAY_MILSEC;
        return day1 - day2;
    }

    public static Long dateIntervalContainStartDay(Date d1, Date d2) {
        return dateDayDiff(d1, d2) + 1;
    }

    /**
     * 死算2个日期相差多少天(d1-d2)
     */
    public static Double dayTimeDiff(Date d1, Date d2) {
        return (double) dateMilliSecondDiff(d1, d2) / (double) ONE_DAY_MILSEC;
    }

    /**
     * 计算2个日期相差毫秒数 (d1-d2)
     */
    public static Long dateMilliSecondDiff(Date d1, Date d2) {
        long millis1 = d1.getTime();
        long millis2 = d2.getTime();
        return millis1 - millis2;
    }

    /**
     * 计算两个日期的间隔月份
     *
     * @param beginDate
     * @param endDate
     * @return endDate - beginDate的间隔月份
     */
    public static int calMonthDiff(Date beginDate, Date endDate) {

        Calendar beginCal = Calendar.getInstance();
        beginCal.setTime(beginDate);

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);

        int yearDiff = endCal.get(Calendar.YEAR) - beginCal.get(Calendar.YEAR);
        int monthDiff = ONE_YEAR_MONTH_TOTAL * yearDiff + endCal.get(Calendar.MONTH) - beginCal.get(Calendar.MONTH);

        return monthDiff;
    }

    public static Date parse(String source, String pattern) {
        try {
            return new SimpleDateFormat(pattern).parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String format(Date source, String pattern) {
        return new SimpleDateFormat(pattern).format(source);
    }

    /**
     * 根据date获取range个月后的同一天，如果当天不存在，则获取这个月的最后一天
     */
    public static Date getSameDayInAppointMonth(Date date, Integer range) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, range);
        return c.getTime();
    }

    public static Date addDay(Date source, int range) {
        Calendar c = Calendar.getInstance();
        c.setTime(source);
        c.add(Calendar.DAY_OF_MONTH, range);
        return c.getTime();
    }

    public static void queryStartTimeAndEndTime() {
        Date endProfitTime = parse("2018-05-31 00:00:00", COMMON_PATTERN);
        int period = 6;
        for (int i = 0; i < period; i++) {
            Date endTime = getSameDayInAppointMonth(endProfitTime, -(period - 1) + i);
            Date startTime = null;
            if (i == 0) {
                startTime = parse("2017-12-25 00:00:00", COMMON_PATTERN);
            } else {
                Date lastPeriodTime = getSameDayInAppointMonth(endProfitTime, -period + i);
                startTime = addDay(lastPeriodTime, 1);
            }

            System.out.println(format(startTime, COMMON_PATTERN) + "---" + format(endTime, COMMON_PATTERN));
            System.out.println(dateIntervalContainStartDay(endTime, startTime).intValue());
        }
    }

    public static void queryStartProfitTimeAndEndProfitTime() {
        Integer period = 6;//期数
        Date firstPeriodStartProfitTime = addDay(parse("2017-12-31 00:00:00", COMMON_PATTERN), 1);
        for (int i = 0; i < period; i++) {
            Date startProfitTime = null;
            if (i == 0) {
                startProfitTime = firstPeriodStartProfitTime;
            } else {
                startProfitTime = getSameDayInAppointMonth(firstPeriodStartProfitTime, i);
            }
            Date endProfitTime = addDay(getSameDayInAppointMonth(firstPeriodStartProfitTime, i + 1), -1);
            System.out.println(format(startProfitTime, COMMON_PATTERN) + " --- " + format(endProfitTime, COMMON_PATTERN));
        }
    }

    public static Integer queryPeriodTimes(Date startTime, Date endTime) {
        int i = 0;
        while (true) {
            Date lastTime = getSameDayInAppointMonth(endTime, -(i + 1));
            i++;
            if (lastTime.compareTo(startTime) <= 0) {
                break;
            }
        }
        return i;
    }

    public static Date formatSecondsToZero(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    private static String parseStartTime(String startTime) {
        if (startTime.contains("+")) {
            startTime = startTime.substring(0, startTime.indexOf("+"));
        }
        if (startTime.contains(".")) {
            startTime = startTime.substring(0, startTime.indexOf("."));
        }
        return startTime;
    }

    public static void parseByZone() {
        //2025-07-21 06:31:12
        //2025-07-21 05:17:48
        //2025-07-21 04:02:05
        //2025-07-21 03:44:58
        //2025-07-21 03:35:42
        //2025-07-21 02:24:26
        //2025-07-21 02:18:06
        //2025-07-21 00:47:25
        ZoneId zoneShanghai = ZoneId.of("Asia/Shanghai");
        ZonedDateTime zonedDateTime = LocalDateTime.parse("2025-07-21 06:31:12", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).atZone(zoneShanghai);
        //转换成非洲马里的巴马科时间
        ZoneId zoneId = ZoneId.of("Africa/Bamako");
        ZonedDateTime zonedDateTime1 = zonedDateTime.withZoneSameInstant(zoneId);
        System.out.println(zonedDateTime1);
    }

    public static void main(String[] args) {
//		System.out.println(format(new Date(1576836000000L), COMMON_PATTERN));
//		System.out.println(format(new Date(1573693980000L), COMMON_PATTERN));
        Date parse = parse("1970-01-01 00:00:00", COMMON_PATTERN);
        System.out.println(parse.getTimezoneOffset());
        long time = parse.getTime();
        System.out.println(time);

        long time2 = parse("2020-04-16 18:35:00", COMMON_PATTERN).getTime();
        System.out.println(time2);

        long interval = time2 - time;
        System.out.println(interval);

        System.out.println(interval / (24 * 60 * 60 * 1000L));

        System.out.println(new Date().getTime() / 1000);
        System.out.println(System.currentTimeMillis() / 1000);

        Date day = getSameDayInAppointMonth(new Date(), 3);
        System.out.println(format(day, COMMON_PATTERN));

        Date date = addDay(new Date(), 10);
        System.out.println(date.getTime());
        System.out.println(format(date, COMMON_PATTERN));
        System.out.println(formatSecondsToZero(new Date()));
        System.out.println(Integer.MAX_VALUE);
        System.out.println(new Date(1));
        System.out.println(System.currentTimeMillis());
        System.out.println(Date.UTC(2023, 7, 28, 15, 46, 50));

        Date onlyTime = parse("12:12:12", "HH:mm:ss");
        System.out.println(onlyTime);
        String format = DateUtil.format(new Date(), "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSXXX");
        System.out.println(format);

        System.out.println(DateUtil.parse(parseStartTime("2024-07-04T15:36:16.000918+08:00"), "yyyy-MM-dd'T'HH:mm:ss"));

//		DateTime parse1 = DateUtil.parse("2024-07-04T15:36:16.000918+08:00", "YYYY-MM-DD'T'hh:mm:ss");
//		System.out.println(parse1);
//		DateTime parse2 = DateUtil.parse("2024-07-04T20:50:30.5311753+08:00", "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSXXX");
//		System.out.println(parse2);
        parseByZone();
    }

}
