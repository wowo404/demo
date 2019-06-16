package org.liu.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * @author liuzhsh
 */
public class MyDateUtil {

	/**
	 * 格式化date的时间为00:00:00
	 */
	public static Date changeDateOne(Date date) {
		if (null == date)
			return null;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 00);
		c.set(Calendar.MINUTE, 00);
		c.set(Calendar.SECOND, 00);
		return c.getTime();
	}

	/**
	 * 格式化date的时间为23:59:59
	 */
	public static Date changeDateTwo(Date date) {
		if (date == null)
			return null;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);

		return c.getTime();
	}

	public static Date parseStringToDate(String dateStr, String pattern) {
		if (null == dateStr || "".equals(dateStr)) {
			return null;
		}
		try {
			return new SimpleDateFormat(pattern).parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 字符串格式为日期，格式：yyyy-MM-dd HH:mm:ss
	 */
	public static Date parseStringToDateOne(String dateStr) {
		return parseStringToDate(dateStr, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 字符串格式为日期，格式：yyyy/MM/dd HH:mm:ss
	 */
	public static Date parseStringToDateOne2(String dateStr) {
		return parseStringToDate(dateStr, "yyyy/MM/dd HH:mm:ss");
	}

	/**
	 * 字符串格式为日期，格式：yyyy-MM-dd
	 */
	public static Date parseStringToDateTwo(String dateStr) {
		return parseStringToDate(dateStr, "yyyy-MM-dd");
	}

	/**
	 * 字符串格式为日期，格式：yyyyMMdd
	 */
	public static Date parseStringToDateThree(String dateStr) {
		return parseStringToDate(dateStr, "yyyyMMdd");
	}

	/**
	 * 字符串格式为日期，格式：yyyyMMdd HH:mm:ss
	 */
	public static Date parseStringToDateFour(String dateStr) {
		return parseStringToDate(dateStr, "yyyyMMdd HH:mm:ss");
	}

	public static String formatDateToString(Date date, String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}

	/**
	 * 字符串格式为日期，格式：yyyy-MM-dd HH:mm:ss
	 */
	public static String formatDateToStringOne(Date date) {
		return formatDateToString(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 字符串格式为日期，格式：yyyy-MM-dd
	 */
	public static String formatDateToStringTwo(Date date) {
		return formatDateToString(date, "yyyy-MM-dd");
	}

	/**
	 * 字符串格式为日期，格式：yyyyMMddHHmmss
	 */
	public static String formatDateToStringThree(Date date) {
		return formatDateToString(date, "yyyyMMddHHmmss");
	}

	/**
	 * 字符串格式为日期，格式：yyyyMMdd
	 */
	public static String formatDateToStringFour(Date date) {
		return formatDateToString(date, "yyyyMMdd");
	}

	/**
	 * 字符串格式为日期，格式：HHmmss
	 */
	public static String formatDateToStringFive(Date date) {
		return formatDateToString(date, "HHmmss");
	}

	/**
	 * 字符串格式为日期，格式：HHmmssSSS
	 */
	public static String formatDateToStringSix(Date date) {
		return formatDateToString(date, "HHmmssSSS");
	}

	/**
	 * 字符串格式为日期，格式：yyyy-MM
	 */
	public static String formatDateToStringSeven(Date date) {
		return formatDateToString(date, "yyyy-MM");
	}

	/**
	 * 两个日期间隔多少天
	 */
	public static Long twoDateInterval(Date start, Date end) {
		long timeOne = start.getTime();
		long timeTwo = end.getTime();
		return (timeTwo - timeOne) / (1000 * 60 * 60 * 24);
	}

	/**
	 * 计算两个月份间隔多少个月，包含起始月和结束月，参数格式可以为：2015-09，2015-09-01
	 */
	public static Integer twoMonthInterval(String startMonth, String endMonth) {
		if (startMonth.equals(endMonth)) {
			return 1;
		}
		if(startMonth.matches("^\\d*-\\d*-\\d*$")){
			startMonth = startMonth.substring(0, startMonth.lastIndexOf("-"));
		}
		if(endMonth.matches("^\\d*-\\d*-\\d*$")){
			endMonth = endMonth.substring(0, endMonth.lastIndexOf("-"));
		}
		Integer sYear = Integer.valueOf(startMonth.split("-")[0]);
		Integer sMonth = Integer.valueOf(startMonth.split("-")[1]);
		Integer eYear = Integer.valueOf(endMonth.split("-")[0]);
		Integer eMonth = Integer.valueOf(endMonth.split("-")[1]);
		if (sYear > eYear) {
			return -1;
		}
		if (sYear == eYear && sMonth > eMonth) {
			return -1;
		}
		return (eYear - sYear) * 12 + eMonth - sMonth + 1;
	}

	/**
	 * 获取thisDate在range天前或者range天后的日期，range是指多少天
	 */
	public static Date queryDate(Date thisDate, Integer range) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(thisDate);
		cal.add(Calendar.DATE, range);
		return cal.getTime();
	}

	/**
	 * 获取thisDate在range个月前或者后的日期，range是指多少个月
	 */
	public static Date queryDateByMonth(Date thisDate, Integer range) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(thisDate);
		cal.add(Calendar.MONTH, range);
		return cal.getTime();
	}

	/**
	 * 得到本月的第一天
	 *
	 * @return
	 */
	public static Date getMonthFirstDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		//add by liuzhsh
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 得到本月的最后一天
	 *
	 * @return
	 */
	public static Date getMonthLastDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		//add by liuzhsh
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/**
	 * 获取date的年
	 */
	public static int getYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}

	/**
	 * 获取date的月
	 */
	public static int getMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取date所在季度
	 */
	public static int getQuarter(Date date) {
		int month = getMonth(date);
		if (month >= 1 && month <= 3) {
			return 1;
		} else if (month >= 4 && month <= 6) {
			return 2;
		} else if (month >= 7 && month <= 9) {
			return 3;
		} else {//month >= 10 && month <= 12
			return 4;
		}
	}

	/**
	 * 获取下一个月的第一天
	 */
	public static Date getNextMonthFirstDay() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.add(Calendar.MONTH, 1);
		return cal.getTime();
	}

	/**
	 * 得到下一个月的最后一天
	 *
	 * @return
	 */
	public static Date getNextMonthLastDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.add(Calendar.MONTH, 1);
		return calendar.getTime();
	}

	/**
	 * 获取date的下一年
	 */
	public static int getNextYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.YEAR, 1);
		return c.get(Calendar.YEAR);
	}

}
