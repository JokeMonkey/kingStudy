package com.jcy.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateUtils {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
        
        Date date = sdf.parse("2018-11-19");
        System.out.println("第" + dayOfWeek(date) + "天");
    }
    
    /**
     * 获取时间对应当周的周一与周日
     * 
     * @param time
     * @return Map<String, Date>
     * 
     * */
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
    
    /**
     * 获取改时间为这周的第几天,周一为第一天
     * 
     * */
    public static int dayOfWeek(Date time){
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得传入日期是一个星期的第几天
        if(1 == dayWeek){
            return 7;
        }
        return dayWeek - 1;
    }
    
    /**
     * 设置时间的时分秒
     * 
     * @param Date
     * @param int
     * @param int
     * @return Date
     * */
    public static Date setUpDateDetail(Date date, int hour, int minute, int second){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        return calendar.getTime();
    }
}
