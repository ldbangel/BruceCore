package com.quicksure.insurpal.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class InsurpalDateUtil {
	/**
     * 校验起保止期不应晚于当前日期的下一天
	 * @param bTInsrncBgnTm
	 * @return
	 */
	public static boolean checkDate(Date bTInsrncBgnTm) {
		try {
		Date newDate=new Date();//获取当前日期
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(newDate);
		// 从下一天0点开始算
		calendar.add(Calendar.DATE, 1);
		Date nextDate=calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(DateStyle.YYYY_MM_DD.value);
		bTInsrncBgnTm=sdf.parse(sdf.format(bTInsrncBgnTm));
		nextDate =sdf.parse(sdf.format(nextDate));
		if(bTInsrncBgnTm.before(nextDate)){
				return false;
			}
			else{
				return true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return true;
	}
	
//*****************************************雷达所需的时间格式转换*****************************************************
	/**
	 * 将日期类型字符串转换成(yyyy/MM/dd 'T' HH:mm:ss +HH:mm)
	 * @param str
	 * @return
	 */
	 //将日期类型字符串转换成(yyyy/MM/dd 'T' HH:mm:ss +HH:mm)
		public static String toStandard(String str){
		        String[] dateTime = str.split("\\s");
		        String date = dateTime[0];
		        String time = dateTime.length>1?dateTime[1]:"";
		        return toStandardDate(date) +" "+ toStandardTime(time);
		    }
	/**
	 * 
	 * @param date
	 * @return
	 */
	
    static String toStandardDate(String date){
        String ymd[] = date.split("-");
        String year = ymd[0];
        String month = ymd.length>1?fill(ymd[1]):"01";
        String day = ymd.length>2?fill(ymd[2]):"01";
        return year +"/"+month+"/"+day+" T";
    }
   
    /**
	 * 
	 * @param time
	 * @return
	 */
    static String toStandardTime(String time){
        String[] hms = time.split(":");
        String hh = hms.length>0?fill(hms[0]):"00";
        String mm = hms.length>1?fill(hms[1]):"00";
        String ss = hms.length>2?fill(hms[2]):"00";
        return hh+":"+mm+":"+ss+" +"+hh+":"+mm;
    }
   
    /**
	 * 
	 * @param str
	 * @return
	 */
    static String fill(String str) {
        if(str.length() == 2){
            return str;
        }else if(str.length() == 1){
            return "0"+str;
        }else if(str.length() == 0){
            return "00";
        }
        throw new IllegalArgumentException("参数不合法!");
    }
//*****************************************雷达所需的时间格式转换*****************************************************
}
