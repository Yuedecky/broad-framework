package com.broad.web.framework.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author broad
 * @date 20191223
 **/
public class WorkDayUtils {

    private WorkDayUtils(){

    }


    /**
     * 特殊的工作日(星期六、日工作)
     */
    private static final List<String> SPECIAL_WORK_DAYS = new ArrayList<>();

    /**
     * 特殊的休息日(星期一到五休息)
     */
    private static final List<String> SPECIAL_REST_DAYS = new ArrayList<>();


    /**
     * 手工维护特殊日(因为是未知的,所以必须手工维护)
     */
    private static void initSpecialDays() {
        SPECIAL_REST_DAYS.add("2020-01-24");
        SPECIAL_REST_DAYS.add("2020-01-25");
        SPECIAL_REST_DAYS.add("2020-01-27");
        SPECIAL_REST_DAYS.add("2020-01-28");
        SPECIAL_REST_DAYS.add("2020-01-29");
        SPECIAL_REST_DAYS.add("2020-01-30");
        SPECIAL_REST_DAYS.add("2020-04-06");
        SPECIAL_REST_DAYS.add("2020-05-01");
        SPECIAL_REST_DAYS.add("2020-05-04");
        SPECIAL_REST_DAYS.add("2020-05-05");
        SPECIAL_REST_DAYS.add("2020-06-25");
        SPECIAL_REST_DAYS.add("2020-06-26");


        SPECIAL_WORK_DAYS.add("2020-05-09");
        SPECIAL_WORK_DAYS.add("2020-02-01");
        SPECIAL_WORK_DAYS.add("2020-10-10");
        SPECIAL_WORK_DAYS.add("2020-06-28");
        SPECIAL_WORK_DAYS.add("2020-09-27");

        SPECIAL_REST_DAYS.add("2020-10-01");
        SPECIAL_REST_DAYS.add("2020-10-02");
        SPECIAL_REST_DAYS.add("2020-10-05");
        SPECIAL_REST_DAYS.add("2020-10-06");
        SPECIAL_REST_DAYS.add("2020-10-07");
        SPECIAL_REST_DAYS.add("2020-10-08");
    }

    /**
     *
     * @param currentDate
     * @param days
     * @return
     */
    public static Date getDate(Date currentDate, int days) {
        if (days == 0) {
            return currentDate;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        int step = days < 0 ? -1 : 1;
        int i = 0;
        int daysAbs = Math.abs(days);
        while (i < daysAbs) {
            calendar.add(Calendar.DATE, step);
            i++;
            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
                    || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {

                // 周六日如果上班就算1个工作日
                if (!SPECIAL_WORK_DAYS.contains(DateUtils.formatDate(calendar.getTime(), DateUtils.PATTERN_YMD))) {
                    i--;
                }
            } else {
                // 周1到周五休息就算1个休息日
                if (SPECIAL_REST_DAYS.contains(DateUtils.formatDate(calendar.getTime(), DateUtils.PATTERN_YMD))) {
                    i--;
                }
            }
        }
        return calendar.getTime();
    }








}


