package com.jcy.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateUtils {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 设置时间格式
        
        Map<String, Date> date = convertWeekByDate(new Date());
        Date monday = date.get("monday");
        Date sunday = date.get("sunday");
        
        System.out.println("星期一：" + sdf.format(setUpDateDetail(monday, 0, 0, 0)));
        System.out.println("星期日：" + sdf.format(setUpDateDetail(sunday, 23, 59, 59)));
    }

    public static Map<String, Date> convertWeekByDate(Date time) {
        Map<String, Date> resut = new HashMap<>();
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得传入日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int day = cal.get(Calendar.DAY_OF_WEEK);// 获得传入日期是一个星期的第几天
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给传入日期减去星期几与一个星期第一天的差值
        resut.put("monday", cal.getTime());
        cal.add(Calendar.DATE, 1);//周二时间
        cal.add(Calendar.DATE, 1);//周三时间
        cal.add(Calendar.DATE, 1);//周四时间
        cal.add(Calendar.DATE, 1);//周五时间
        cal.add(Calendar.DATE, 1);//周六时间
        cal.add(Calendar.DATE, 1);//周日时间
        resut.put("sunday", cal.getTime());
        return resut;
    }
    
    
    public static Date setUpDateDetail(Date date, int hour, int minute, int second){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        return calendar.getTime();
    }
}
