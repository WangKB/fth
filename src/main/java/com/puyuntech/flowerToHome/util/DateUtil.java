package com.puyuntech.flowerToHome.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 
 * 日期工具类 . 
 * Created on 2015-9-24 下午3:48:05 
 * @author 王凯斌
 */
public class DateUtil {

    private static String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

	/**
	 * 
	 * 获取传入的时间为星期几 .
	 * <p>方法详细说明,如果要换行请使用<br>标签</p>
	 * <br>
	 * author: 王凯斌
	 *   date: 2015-9-24 下午3:48:59
	 * @param date
	 * @return
	 */
	public static String getWeekOfDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime( date );
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0){
            w = 0;
        }
        return weekDays[w];
	}
	
	/**
	 * 
	 * 获取指定日期之后七天 的星期 和 日期 .
	 * <p>方法详细说明,如果要换行请使用<br>标签</p>
	 * <br>
	 * author: 王凯斌
	 *   date: 2015-9-24 下午3:56:45
	 * @return
	 */
	public static List<String> getAWeekDate( Date date ){
		SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
		List<String> list = new ArrayList<String>();
		String temp = null ;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
        int w = 0 ;
		for(int i=0 ;i<7 ;i++){
			temp = sdf.format(calendar.getTime());
            w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
            if (w < 0){
                w = 0;
            }
			list.add(temp+"["+ weekDays[w] +"]");
			calendar.add(Calendar.DATE, 1);
		}
		return list;
	}

    /**
     *
     * 获取指定日期之后七天 的星期.
     * <p>方法详细说明,如果要换行请使用<br>标签</p>
     * <br>
     * author: 王凯斌
     *   date: 2015-9-24 下午3:56:45
     * @return
     */
    public static List<String> getAWeek( Date date ){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime( date );

        List<String> list = new ArrayList<String>();
        String temp = null ;
        int w = 0;

        for(int i=0 ;i<7 ;i++){
            w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
            if (w < 0){
                w = 0;
            }
            temp = weekDays[w];
            list.add(temp);
            calendar.add(Calendar.DATE, 1);
        }

        return list;
    }


    /**
     *  判断某个日期是否在一个特定日期范围内
     * @param comlpeteDate  需要比较的时间
     * @param targetDay 当前的时间
     * @param day 比较的天数
     * @return
     */
    public static boolean isInADay(Date comlpeteDate ,Date targetDay ,int day) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Calendar completeCalendar = Calendar.getInstance( );
        completeCalendar.setTime( comlpeteDate );

        Calendar targetCalendar = Calendar.getInstance( );
        targetCalendar.setTime( targetDay );

        completeCalendar.add( Calendar.DAY_OF_YEAR , day );

        if( completeCalendar.before( targetCalendar ) ){
            return  false;
        }else{
            return  true;
        }
    }
}
