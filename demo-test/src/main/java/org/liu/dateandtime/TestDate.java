package org.liu.dateandtime;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
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

	public static Integer minusWithDay(Date firstDate, Date anotherDate) {
		long first = DateUtils.truncate(firstDate, Calendar.DAY_OF_MONTH).getTime();
		long another = DateUtils.truncate(anotherDate, Calendar.DAY_OF_MONTH).getTime();
		return (int) ((first - another) / (24 * 60 * 60 * 1000));
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
	
	public static void queryStartProfitTimeAndEndProfitTime(){
		Integer period = 6;//期数
        Date firstPeriodStartProfitTime = addDay(parse("2017-12-31 00:00:00", COMMON_PATTERN), 1);
        for(int i = 0; i < period; i ++){
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

	public static Date formatSecondsToZero(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	public static void main(String[] args) {
		System.out.println(new Date(1571724444532L));
        long time = parse("2019-10-24 19:09:14", COMMON_PATTERN).getTime();
        System.out.println(time);

		long time2 = parse("2019-10-23 16:45:00", COMMON_PATTERN).getTime();
		System.out.println(time2);

		long interval = time2 - time;
		System.out.println(interval);//1000*60*5

		System.out.println(interval / (12 * 60 * 60 * 1000));

		System.out.println(new Date().getTime() / 1000);
        System.out.println(System.currentTimeMillis() / 1000);

        Date day = getSameDayInAppointMonth(new Date(), 3);
        System.out.println(format(day, COMMON_PATTERN));

        Date date = addDay(new Date(), 10);
        System.out.println(format(date, COMMON_PATTERN));
		System.out.println(formatSecondsToZero(new Date()));

    }

}
