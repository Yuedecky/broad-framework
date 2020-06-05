package com.broad.web.framework.tool;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.broad.web.framework.utils.PatternUtil;
import org.apache.commons.lang3.StringUtils;

public class DateTool {

	public final static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	public final static String YYYY_MM_DD = "yyyy-MM-dd";

	public final static String YYYYMMDD = "yyyyMMdd";

	/**
	 * 把日期字符串格式化成日期类型
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date convert2Date(String dateStr) {
		return convert2Date(dateStr, PatternUtil.getTimeFomart(dateStr));
	}

	/**
	 * 把日期字符串格式化成日期类型
	 * 
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static Date convert2Date(String dateStr, String format) {
		SimpleDateFormat simple = new SimpleDateFormat(format);
		try {
			simple.setLenient(false);
			return simple.parse(dateStr);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 把日期类型格式化成字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String convert2String(Date date) {
		return convert2String(date, YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 把日期类型格式化成字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String convert2String(Date date, String format) {
		SimpleDateFormat formater = new SimpleDateFormat(format);
		try {
			return formater.format(date);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * 获取一个时间（当天）的开始时间
	 *
	 * @param date
	 * @return
	 */
	public static Date getDateBegin(Date date) {
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.setTime(date);
		todayEnd.set(Calendar.HOUR_OF_DAY, 0);
		todayEnd.set(Calendar.MINUTE, 0);
		todayEnd.set(Calendar.SECOND, 0);
		todayEnd.set(Calendar.MILLISECOND, 0);
		return todayEnd.getTime();
	}

	/**
	 * 
	 * 获取一个时间（当天）的结束时间
	 *
	 * @param date
	 * @return
	 */
	public static Date getDateEnd(Date date) {
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.setTime(date);
		todayEnd.set(Calendar.HOUR_OF_DAY, 23);
		todayEnd.set(Calendar.MINUTE, 59);
		todayEnd.set(Calendar.SECOND, 59);
		todayEnd.set(Calendar.MILLISECOND, 999);
		return todayEnd.getTime();
	}

	/**
	 * 
	 * <pre>
	 * 转化为具体消耗时间
	 * </pre>
	 *
	 * @param mss
	 * @return
	 */
	public static String convert2times(Long mss) {
		if (null == mss)
			return "-";
		StringBuffer s = new StringBuffer();

		long days = mss / (1000 * 60 * 60 * 24);
		long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
		long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
		long seconds = (mss % (1000 * 60)) / 1000;
		if (days > 0)
			s.append(days + "天");
		if (hours > 0)
			s.append(hours + "小时");
		if (minutes > 0)
			s.append(minutes + "分钟");
		if (seconds > 0)
			s.append(seconds + "秒");

		return s.toString();
	}

	/**
	 * 
	 * <pre>
	 * 获取某时间当天的最初时刻
	 * </pre>
	 *
	 * @param now
	 * @return
	 */
	public static Date getTimeBegin(Date date) {
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.setTime(date);
		todayEnd.set(Calendar.HOUR_OF_DAY, 0);
		todayEnd.set(Calendar.MINUTE, 0);
		todayEnd.set(Calendar.SECOND, 0);
		todayEnd.set(Calendar.MILLISECOND, 0);
		return todayEnd.getTime();
	}

	/**
	 * 
	 * <pre>
	 * 获取当天的最初时刻
	 * </pre>
	 *
	 * @param now
	 * @return
	 */
	public static Date getTodayBeginTime() {
		return getTimeBegin(new Date());
	}

	/**
	 * 
	 * <pre>
	 * 获取某时间当天的最后时刻
	 * </pre>
	 *
	 * @param now
	 * @return
	 */
	public static Date getTimeEnd(Date date) {
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.setTime(date);
		todayEnd.set(Calendar.HOUR_OF_DAY, 23);
		todayEnd.set(Calendar.MINUTE, 59);
		todayEnd.set(Calendar.SECOND, 59);
		todayEnd.set(Calendar.MILLISECOND, 999);
		return todayEnd.getTime();
	}

	/**
	 * 
	 * <pre>
	 * 获取当天的 日信息
	 * </pre>
	 *
	 * @return
	 */
	public static Integer getNowDay() {
		return getDay(new Date());
	}

	/**
	 * 
	 * <pre>
	 * 获取指定日期的 日信息
	 * </pre>
	 *
	 * @param now
	 * @return
	 */
	public static Integer getDay(Date now) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		return cal.get(Calendar.DATE);
	}

	/**
	 * 
	 * <pre>
	 * 获取指定时间的 年信息
	 * </pre>
	 *
	 * @param now
	 * @return
	 */
	public static Integer getYear(Date now) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		return cal.get(Calendar.YEAR);
	}

	/**
	 * 
	 * <pre>
	 * 获取指定时间的月份
	 * </pre>
	 *
	 * @param now
	 * @return
	 */
	public static Integer getMonth(Date now) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		return cal.get(Calendar.MONTH);
	}

	/**
	 * 
	 * <pre>
	 * 获取指定时间的 小时信息
	 * </pre>
	 *
	 * @param now
	 * @return
	 */
	public static Integer getHour(Date now) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		return cal.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 
	 * <pre>
	 * 增加指定秒数
	 * </pre>
	 *
	 * @param date
	 * @param second
	 * @return
	 */
	public static Date addSecond(Date date, Integer second) {
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.setTime(date);
		todayEnd.set(Calendar.SECOND, todayEnd.get(Calendar.SECOND) + second);
		return todayEnd.getTime();
	}

	/**
	 * 
	 * <pre>
	 * 增加指定分钟
	 * </pre>
	 *
	 * @param date
	 * @param minute
	 * @return
	 */
	public static Date addMinute(Date date, Integer minute) {
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.setTime(date);
		todayEnd.set(Calendar.MINUTE, todayEnd.get(Calendar.MINUTE) + minute);
		return todayEnd.getTime();
	}

	/**
	 * 
	 * <pre>
	 * 增加指定小时数
	 * </pre>
	 *
	 * @param date
	 * @param hour
	 * @return
	 */
	public static Date addHour(Date date, Integer hour) {
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.setTime(date);
		todayEnd.set(Calendar.HOUR_OF_DAY, todayEnd.get(Calendar.HOUR_OF_DAY) + hour);
		return todayEnd.getTime();
	}

	/**
	 * 
	 * <pre>
	 * 增加指定天数
	 * </pre>
	 *
	 * @param date
	 * @param afterDay
	 * @return
	 */
	public static Date addDay(Date date, Integer afterDay) {
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.setTime(date);
		todayEnd.set(Calendar.DAY_OF_YEAR, todayEnd.get(Calendar.DAY_OF_YEAR) + afterDay);
		return todayEnd.getTime();
	}

	/**
	 * 
	 * <pre>
	 * 增加指定月数
	 * </pre>
	 *
	 * @param date
	 * @param afterMonth
	 * @return
	 */
	public static Date addMonth(Date date, Integer afterMonth) {
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.setTime(date);
		todayEnd.set(Calendar.MONTH, todayEnd.get(Calendar.MONTH) + afterMonth);
		return todayEnd.getTime();
	}

	/**
	 * 
	 * <pre>
	 * 增加指定天数
	 * </pre>
	 *
	 * @param date
	 * @param afterDay
	 * @return
	 */
	public static Date addYear(Date date, Integer afterYear) {
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.setTime(date);
		todayEnd.set(Calendar.YEAR, todayEnd.get(Calendar.YEAR) + afterYear);
		return todayEnd.getTime();
	}

	/**
	 * 
	 * <pre>
	 * 减少指定秒
	 * </pre>
	 *
	 * @param date
	 * @param second
	 * @return
	 */
	public static Date cutSecond(Date date, Integer second) {
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.setTime(date);
		todayEnd.set(Calendar.SECOND, todayEnd.get(Calendar.SECOND) - second);
		return todayEnd.getTime();
	}

	/**
	 * 
	 * <pre>
	 * 减少指定分钟数
	 * </pre>
	 *
	 * @param date
	 * @param minute
	 * @return
	 */
	public static Date cutMinute(Date date, Integer minute) {
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.setTime(date);
		todayEnd.set(Calendar.MINUTE, todayEnd.get(Calendar.MINUTE) - minute);
		return todayEnd.getTime();
	}

	/**
	 * 
	 * <pre>
	 * 减少指定小时数
	 * </pre>
	 *
	 * @param date
	 * @param hour
	 * @return
	 */
	public static Date cutHour(Date date, Integer hour) {
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.setTime(date);
		todayEnd.set(Calendar.HOUR_OF_DAY, todayEnd.get(Calendar.HOUR_OF_DAY) - hour);
		return todayEnd.getTime();
	}

	/**
	 * 
	 * <pre>
	 * 减少指定天数
	 * </pre>
	 *
	 * @param date
	 * @param beforeDay
	 * @return
	 */
	public static Date cutDay(Date date, Integer beforeDay) {
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.setTime(date);
		todayEnd.set(Calendar.DAY_OF_YEAR, todayEnd.get(Calendar.DAY_OF_YEAR) - beforeDay);
		return todayEnd.getTime();
	}

	/**
	 * 
	 * <pre>
	 * 减少指定年数
	 * </pre>
	 *
	 * @param date
	 * @param beforeYear
	 * @return
	 */
	public static Date cutYear(Date date, Integer beforeYear) {
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.setTime(date);
		todayEnd.set(Calendar.YEAR, todayEnd.get(Calendar.YEAR) - beforeYear);
		return todayEnd.getTime();
	}

	/**
	 * 
	 * <pre>
	 * 获取两个时间相隔天数
	 * </pre>
	 *
	 * @param other
	 * @param now
	 * @return
	 */
	public static Integer getDifferenceDays(Date other, Date now) {
		if (null == other || null == now)
			return null;

		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(other);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(now);
		Double between_days = (double) ((cal1.getTimeInMillis() - cal2.getTimeInMillis()) / (1000D * 3600D * 24D));
		return (int) Math.abs(Math.ceil(between_days));
	}

	/**
	 * 
	 * <pre>
	 * 获取两个时间相隔天数
	 * </pre>
	 *
	 * @param other
	 * @param now
	 * @return
	 */
	public static Integer getDifferenceMonths(Date other, Date now) {
		int result = 0;
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();

		c1.setTime(other);
		c2.setTime(now);

		result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);

		return result;
	}

	/**
	 * 
	 * <pre>
	 * 获取两个时间相隔的秒数
	 * </pre>
	 *
	 * @param other
	 * @param now
	 * @return
	 */
	public static Integer getDifferenceSecond(Date startTime, Date endTime) {
		if (null == startTime || null == endTime)
			return null;

		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(startTime);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(endTime);

		Double between_days = (double) ((cal1.getTimeInMillis() - cal2.getTimeInMillis()) / (1000D));

		return (int) Math.abs(Math.ceil(between_days));
	}

	/**
	 * 
	 * <pre>
	 * 获取当前时间到当天结束还剩多少秒
	 * </pre>
	 *
	 * @return
	 */
	public static Long getDaySurplusSeconds() {
		Date now = new Date();
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(now);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(getDateEnd(now));

		Double between_Seconds = (double) ((cal2.getTimeInMillis() - cal1.getTimeInMillis()) / (1000D));

		return (long) Math.abs(between_Seconds);
	}

	/**
	 * 当前时间到后面2个小时还剩下多少分钟
	 * 
	 * @return
	 */
	public static Long getHourSurplusSeconds() {
		Date now = new Date();
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(now);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(DateTool.addHour(now, 2));

		Double between_Seconds = (double) ((cal2.getTimeInMillis() - cal1.getTimeInMillis()) / (1000D));

		return (long) Math.abs(between_Seconds);
	}

	/**
	 * 
	 * <pre>
	 * 判断2个时间是否为同一天
	 * </pre>
	 *
	 * @param date1
	 * @param date2
	 * @return
	 * @throws Exception
	 */
	public static Boolean compareDateIsSameDay(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		cal1.set(Calendar.HOUR_OF_DAY, 0);
		cal1.set(Calendar.MINUTE, 0);
		cal1.set(Calendar.SECOND, 0);
		cal1.set(Calendar.MILLISECOND, 0);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		cal2.set(Calendar.HOUR_OF_DAY, 0);
		cal2.set(Calendar.MINUTE, 0);
		cal2.set(Calendar.SECOND, 0);
		cal2.set(Calendar.MILLISECOND, 0);

		return cal1.getTimeInMillis() == cal2.getTimeInMillis();
	}

	/**
	 * 
	 * <pre>
	 * 获取出生年
	 * </pre>
	 *
	 * @param birthday
	 * @return
	 */
	public static Date getBirthday(Date birthday) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(birthday);
		cal.set(Calendar.YEAR, getYear(new Date()));
		return cal.getTime();
	}

	/**
	 * 
	 * <pre>
	 * 获取月日 如2017-8-16 20:45:44 得到 08-16
	 * </pre>
	 *
	 * @param now
	 * @return
	 */
	public static String getYearMonth(Date now) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		return (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day);
	}

	public static Integer getAgeWeek(String birthDay) {
		if (StringUtils.isBlank(birthDay)) {
			return null;
		}
		Date tmp = convert2Date(birthDay);
		if (tmp == null) {
			return null;
		}
		return getAgeWeek(tmp);
	}

	public static Integer getAgeWeek(Date birthDay) {
		if (null == birthDay)
			return null;
		Integer days = getDifferenceDays(new Date(), birthDay);
		return days / 7 - (days < 0 ? 1 : 0);
	}
	
	public static Integer getAgeDay(Date birthDay) {
		if (null == birthDay)
			return null;
		Integer days = getDifferenceDays(new Date(), birthDay);
		return days;
	}
	
	public static void main(String[] args) {
//		Date now = new Date();
//		Calendar todayEnd = Calendar.getInstance();
//		todayEnd.setTime(new Date());
//		todayEnd.set(Calendar.HOUR_OF_DAY, getHour(new Date()) + 1);
//		todayEnd.set(Calendar.MINUTE, 30);
//		System.out.println(getTodayBeginTime());
//
//		System.out.println(getHourSurplusSeconds());
////		System.out.println(DateUtil.addHour(new Date(), 2));
//		System.out.println(todayEnd.getTime());
		
//		System.out.println(getDifferenceMonths(convert2Date("2007-01-22"), now));
//		System.out.println(getDifferenceDays(convert2Date("2007-01-22"), now));
		System.out.println(getAgeWeek("2018-07-25"));
	}
}
