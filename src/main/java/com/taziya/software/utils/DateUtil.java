package com.taziya.software.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期处理类
 */
public class DateUtil {

    /**
     * 将Date转换成String
     * @param date
     * @return
     */
    public String date2String(Date date) {
        if(date==null){
            return "";
        }else{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateStr = sdf.format(date);
            return dateStr;
        }

    }

    /**
     * 将Timestamp转换成String
     * 用于数据库中字段类型为datetime
     * @param timestamp
     * @return
     */
    public String time2String(Timestamp timestamp) {
        Date date = new Date(timestamp.getTime());
        String dateStr = date2String(date);
        return dateStr;
    }

    /**
     * 将DateTime格式的数据分割为xml文件需要的格式
     * @param date
     * @return
     */
    public String[] getDateAndTime(Date date){
        if(date==null){
            return new String[]{"/","/","/"};
        }else {
            String Date;
            String TimeM;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Date=sdf.format(date);
            SimpleDateFormat sdf1 = new SimpleDateFormat("HHmm");
            TimeM  = sdf1.format(date);
            SimpleDateFormat sdf2 = new SimpleDateFormat("HHmmss");
            String TimeS = sdf2.format(date);
            return new String[]{Date,TimeM,TimeS};
        }

    }

//    public static void main(String[] args) {
//        DateUtil dateUtil = new DateUtil();
//        String[] dateAndTime = dateUtil.getDateAndTime(new Date());
//        System.out.println(dateAndTime[0]);
//        System.out.println(dateAndTime[1]);
//    }

}
