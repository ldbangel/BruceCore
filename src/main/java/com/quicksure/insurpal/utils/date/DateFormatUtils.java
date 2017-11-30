package com.quicksure.insurpal.utils.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.quicksure.insurpal.utils.string.StringUtils;
/** 
* @ClassName: DateFormatUtils 
* @Description: TODO(日期格式化处理工具类) 
* @author Liu Dongbo
*  
*/
public class DateFormatUtils {
	/**
	 *
	 * @Title: getSystemDateByParm
	 * @Description: 获得格式化后的日期（根据传入的字符串格式）的系统时间
	 * @param  "yyyy-MM-dd"、"yyyyMMddHHmmss"、"yyyyMMdd"、
	 * 			"yyyyMM"、"yyyy"、"yyyyMMddHHmmssSSS"
	 * @return
	 * @throws ParseException
	 */
	public static String getSystemDateByParm(String parm) {
		Date d = new Date();//获取时间
		SimpleDateFormat format = new SimpleDateFormat(parm);
		String ymd = format.format(d);
		return ymd ;
	}
	
	public static String getSystemDate() {
		Date d = new Date();//获取时间
		SimpleDateFormat format = new SimpleDateFormat();
		String ymd = format.format(d);
		return ymd ;
	}
	/** 
	* @Title: dateToString 
	* @Description: TODO(将DATE转成想要的string) 
	* @param @param date
	* @param @param parm string 格式
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	public static String dateToString(Date date,String parm){
		String dateStr = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(parm);
			dateStr = sdf.format(date);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return dateStr;
	}
	
	/** 
	* @Title: convertToDate 
	* @Description: 字符串转换成时间格式 ,dateString精度要比parms精度小
	* @param  dateString 要转化的字符串 例如：("2017-08-01 23:23:23")
	* @param  parms 转化后的格式，例如：yyyy-MM-dd HH:mm:ss
	* @param @return    设定文件 
	* @return Date    返回类型 
	* @throws 
	*/
	public static Date stringToDate(String dateString,String parms){
		SimpleDateFormat df = new SimpleDateFormat(parms);
		try {
			Date date = df.parse(dateString);
			return date;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}
	
	/**
	 *
	 * @Title：getMonthDayForDate
	 * @Description: 根据字符型日期，获取该日期所在月份共有多少天
	 * @param dateFormat 格式（yyy-MM-dd）
	 * @return 该日期所在月份共多少天
	 */
	public static int getMonthDayForDate(String dateFormat) throws ParseException{
		Calendar cDay = Calendar.getInstance();
		DateFormat dd = new SimpleDateFormat(DateStyle.YYYY_MM_DD.value);
		Date date = dd.parse(dateFormat);
		cDay.setTime(date);
		int lastDay = cDay.getActualMaximum(Calendar.DAY_OF_MONTH);
		return lastDay ;
	}
	
	/**
	 *
	 * @Title：getCalendarByweek
	 * @Description: 根据传递过来的日期，返回当前日期所在周的时间段
	 * @param dateFormat 日期（"2014-09-19"）
	 * @return 该周周一和周日的时间日期
	 * @throws ParseException
	 */
	public static String[] getCalendarByweek(String dateFormat) throws ParseException{
		String[] resultarr = new String[2] ;
		SimpleDateFormat sdf=new SimpleDateFormat(DateStyle.YYYY_MM_DD.getValue());
		Calendar cl = Calendar.getInstance();
		Date d = sdf.parse(dateFormat);
		cl.setTime(d); //nd为传过来的日期，Date 型，此步可省为当前日期
		int day_of_week = cl.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		cl.add(Calendar.WEEK_OF_MONTH, 0); //idx 参数，0为当前，1为下周 -1为上周以此类推
		cl.add(Calendar.DATE, -day_of_week + 1);
		String week1 = sdf.format(cl.getTime());//周一
		resultarr[0] = week1 ;
		cl.add(Calendar.DATE, +6);
		String week2 = sdf.format(cl.getTime());//周日
		resultarr[1] = week2 ;
	      
		return resultarr;
	}
	
	/**
	 *
	 * @Title：getWeekForDate
	 * @Description: 根据日期（yyyy-MM-dd）获得其是该月的第几周
	 * @param dateFormat 日期（"2014-09-19"）
	 * @return
	 * @throws ParseException
	 */
	public static Integer getWeekForDate(String dateFormat) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(DateStyle.YYYY_MM_DD.value);
		Date date = sdf.parse(dateFormat);
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONTH);
		calendar.setTime(date);
		int weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);
		return weekOfMonth ;
	}
	 
	/** 
	* @Title: countAge 
	* @Description: TODO(计算目标日期距离系统当前日期多少天) 
	* @param @param createDate yyyy-MM-dd HH:mm:ss
	* @param @return    设定文件 
	* @return Long    返回类型 
	* @throws 
	*/
	public static Long countAge(String createDate){
		SimpleDateFormat df = new SimpleDateFormat(DateStyle.YYYY_MM_DD.value);
		Long day =0l;
		try {
			String nowDate = DateFormatUtils.getSystemDateByParm(DateStyle.YYYY_MM_DD.value);
			long to = df.parse(nowDate).getTime();
			long from = df.parse(createDate).getTime();
			day = (to - from) / (1000 * 60 * 60 * 24);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return day;
	}
	
	/** 
	* @Title: getBetweenDay 
	* @Description: TODO(判断两个日期之间相差多少天) 
	* @param @param date1
	* @param @param date2
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	public static int getBetweenDay(Date date1, Date date2) {  
		Calendar d1 = new GregorianCalendar();  
		d1.setTime(date1);  
		Calendar d2 = new GregorianCalendar();  
		d2.setTime(date2);  
		int days = d2.get(Calendar.DAY_OF_YEAR)- d1.get(Calendar.DAY_OF_YEAR);  
		int y2 = d2.get(Calendar.YEAR);  
		if (d1.get(Calendar.YEAR) != y2) {  
			do {  
				days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);  
				d1.add(Calendar.YEAR, 1);  
			} while (d1.get(Calendar.YEAR) != y2);  
		}  
		return days;  
	}
	
	/** 
	* @Title: getCompareDate 
	* @Description: TODO(比较两个日期的大小，精确到秒) 
	* @param @param date1
	* @param @param date2
	* @param @return    设定文件 
	* @return Boolean    返回类型 
	* @throws 
	*/
	public static Boolean getCompareDate(Date date1,Date date2){
		java.util.Calendar c1=java.util.Calendar.getInstance();
		java.util.Calendar c2=java.util.Calendar.getInstance();
		c1.setTime(date1);
		c2.setTime(date2);
		int result=c1.compareTo(c2);
		if (result <=0) {
			return true;
		}else {
			return false;
		}
	}
	
	/** 
	* @Title: DateDifferentExample 
	* @Description: TODO(计算出两个日期之间相差多少天多少小时多少分多少秒) 
	* @param @param d1
	* @param @param d2
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	public static String DateDifferentExample (Date d1,Date d2){
		StringBuilder resultString = new StringBuilder();
		try {
			//毫秒ms
			long diff = d2.getTime() - d1.getTime();
			long diffSeconds = diff / 1000 % 60;
			long  diffMinutes  = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);
   
			resultString.append(diffDays+"天,");
			resultString.append(diffHours +"小时,");
			resultString.append(diffMinutes + " 分钟");
			resultString.append(diffSeconds +"秒.");
	       	} catch (Exception e) {
	       		e.printStackTrace();
	       	}
		return resultString.toString();
	}	
	
	/**
	 * 两个时间比较，相差一天及以上返回true
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean comparTwoDate(Date date1,Date date2){
		boolean isfalg;
		long i=(date2.getTime()-date1.getTime())/1000;
		if(i>24*3600){
			isfalg=true;
		}else{
			isfalg=false;
		}
		return isfalg;
	}
	
	/**
	 * 方法operationDate的简要说明 <br>
	 * 日期运算，将指定的（有符号的）时间量添加到给定的日历字段中
	 * <pre>
	 * </pre>
	 * @param dateStr　日期字符串
	 * @param field　日历字段（即年月日）；1:年字段;2：月字段;3:周;5:日字段
	 * @param amount　时间量，可以有符号，如果：-2
	 * @return
	 * @throws Exception
	 */
	public static String operationDate(String dateStr,int field,
			int amount,String formatString) throws Exception{
		SimpleDateFormat sf  =new SimpleDateFormat(formatString);
		GregorianCalendar gc=new GregorianCalendar(); 
		gc.setTime(DateFormatUtils.stringToDate(dateStr, DateStyle.YYYYMMDD.value));
		gc.add(field, amount);
		return sf.format(gc.getTime());
	}
	
	/**
	 * 方法operationDate的简要说明 <br>
	 * 日期运算，将指定的（有符号的）时间量添加到给定的日历字段中
	 * <pre>
	 * </pre>
	 * @param dateStr　日期字符串
	 * @param field　日历字段（即年月日）；1:年字段;2：月字段;3:周;5:日字段
	 * 
	 * @param amount　时间量，可以有符号，如果：-2
	 * @return
	 * @throws Exception
	 */
	public static String operationDate(Date date,int field,
			int amount,String formatString) throws Exception{
		if("".equalsIgnoreCase(StringUtils.trim(formatString))){
			formatString = DateStyle.YYYY_MM_DD_HH_MM_SS.value;
		}
		SimpleDateFormat sf  =new SimpleDateFormat(formatString);
		GregorianCalendar gc=new GregorianCalendar(); 
		gc.setTime(date);
		gc.add(field, amount);
		return sf.format(gc.getTime());
	}
	
	//雷达需要===============================================================
	public static int getHowYear(String  strStartDate,String strEndDate){
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		 int strCarYear=0;
	       try {
			    Date firstDateTimeDate = format.parse(strStartDate);
			    Date secondDateTimeDate = format.parse(strStartDate);
			    long firstDateMilliSeconds = firstDateTimeDate.getTime();
	            long secondDateMilliSeconds = secondDateTimeDate.getTime();
	            long subDateMilliSeconds = firstDateMilliSeconds - secondDateMilliSeconds;
	            //毫秒转化为秒
	            int totalSeconds = (int) (subDateMilliSeconds/1000);
	            // 毫秒数转化为总天数
	            float days_remains = totalSeconds/(3600*24);
	            float month_remains = days_remains / 30;
	            strCarYear = (int)Math.rint(month_remains);
	    	} catch (ParseException e) {
			    e.printStackTrace();
	    	}
		return strCarYear;
	}

}
