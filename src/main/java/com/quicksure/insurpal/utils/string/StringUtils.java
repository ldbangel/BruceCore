package com.quicksure.insurpal.utils.string;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

//******************************************************************
/**
* 类名:com.Utils.StringUtils <pre>
* 描述: 字符串处理类
* 	基本思路:
* 	public方法:
* 	特别说明:
* 修改说明: 类的修改说明
* </pre>
*/
//******************************************************************
public class StringUtils {
	/**
	 * 判断字符串是否为空
	 * 空：true
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.trim().isEmpty();
	}
	
	/**
	 * 判断字符串是否为非空
	 * 非空：true
	 */
	public static boolean isNotEmpty(String str) {
		return str != null && !str.trim().isEmpty();
	}
	
	/**
	 * 判断多个字符串是否为空
	 * 只要一个为空，返回true
	 */
	public static boolean isNullOrEmpty(String... values) {
		for (String value : values) {
			if (isEmpty(value))
				return true;
		}
		return false;
	}
    
	/**
	 * 去空格
	 */
	public static String trim(String str){
		return isEmpty(str) ? "" : str.trim();
	}
	
	/**
	 * 获取字符串长度，当字符串为空时返回0.
	 */
	public static int strLength(String str){
		if (isEmpty(str)){
			return 0;
		}else{
			return str.length();
		}
	}
	
	/**
	 * 方法getUTF8StringFromGBKString的详细说明  
	 * 字符集转换，从GBK转为UTF－8
	 * @param gbkStr
	 * @return
	 */
	public static String getUTF8StringFromGBKString(String gbkStr) {   
        try {   
            return new String(getUTF8BytesFromGBKString(gbkStr), "UTF-8");   
        } catch (UnsupportedEncodingException e) {   
            throw new InternalError();   
        }   
    }   
       
	/**
	 * GBK格式的字符串转换成UTF-8的字符数组
	 * @param gbkStr
	 * @return
	 */
    public static byte[] getUTF8BytesFromGBKString(String gbkStr) {   
        int n = gbkStr.length();   
        byte[] utfBytes = new byte[3 * n];   
        int k = 0;   
        for (int i = 0; i < n; i++) {   
            int m = gbkStr.charAt(i);   
            if (m < 128 && m >= 0) {   
                utfBytes[k++] = (byte) m;   
                continue;   
            }   
            utfBytes[k++] = (byte) (0xe0 | (m >> 12));   
            utfBytes[k++] = (byte) (0x80 | ((m >> 6) & 0x3f));   
            utfBytes[k++] = (byte) (0x80 | (m & 0x3f));   
        }   
        if (k < utfBytes.length) {   
            byte[] tmp = new byte[k];   
            System.arraycopy(utfBytes, 0, tmp, 0, k);   
            return tmp;   
        }   
        return utfBytes;   
    }
	
    /**
     * 压缩字符串<br>
     * @param 参数类型 参数名 说明
     * @return String 说明
     * @throws 异常类型 说明  
     * @param str
     * @return
     * @throws IOException
     */
    public static String compress(String str) throws IOException {    
    	System.out.println("压缩之前的字符串大小："+str.length());    
    	if (str == null || str.length() == 0) {    
    		return str;    
    	}    
    	ByteArrayOutputStream out = new ByteArrayOutputStream();    
      	GZIPOutputStream gzip = new GZIPOutputStream(out);    
      	gzip.write(str.getBytes());    
      	gzip.close();    
      	return out.toString("ISO-8859-1");    
    }    
  
    /**
     * 解压 <br>
     * 创建时间：Mar 12, 2013 5:47:01 PM </pre>
     * @param 参数类型 参数名 说明
     * @return String 说明
     * @throws 异常类型 说明  
     * @param str
     * @return
     * @throws IOException
     */
    public static String uncompress(String str) throws IOException {    
    	System.out.println("压缩之后的字符串大小："+str.length());    
    	if (str == null || str.length() == 0) {    
    		return str;    
    	}    
    	ByteArrayOutputStream out = new ByteArrayOutputStream();    
    	ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes("ISO-8859-1"));    
    	GZIPInputStream gunzip = new GZIPInputStream(in);    
      	byte[] buffer = new byte[256];    
      	int n;    
      	while ((n = gunzip.read(buffer)) >= 0) {    
      		out.write(buffer, 0, n);    
      	}    
      	return out.toString();    
    }  
  
    /**
     * 将sourceStr以splitElementFlag分割为若干个元素，再将每个元素以splitKeyAndValueFlag分割为一对key/value，
     * 再将每一对key/value封装到Map中<br>
     * @param sourceStr 需分割的字符串
     * @param splitKeyAndValueFlag Map中key与value的分隔符
     * @param splitElementFlag Map中两个元素之间的分隔符
     * @return Map<String,Object> 返回封装完成的map
     * @throws Exception 
     */
    public static Map<String, Object> parseStringToMap(String sourceStr,
    		String splitKeyAndValueFlag,String splitElementFlag) throws Exception{
		Map<String, Object> map = null;
		if(sourceStr==null || "".equalsIgnoreCase(sourceStr.trim())){
			throw new Exception("传递的sourceStr参数字符串为NULL或空");
		}
		if(splitKeyAndValueFlag==null || "".equalsIgnoreCase(splitKeyAndValueFlag.trim())){
			throw new Exception("传递的splitKeyAndValueFlag(Map中key与value的分隔符)参数字符串为NULL或空");
		}
		if(splitElementFlag==null || "".equalsIgnoreCase(splitElementFlag.trim())){
			throw new Exception("传递的splitElementFlag(Map中两个元素之间的分隔符)参数字符串为NULL或空");
		}
		
		String[] sArr = sourceStr.split(splitElementFlag);
		if(sArr!=null && sArr.length>0){
			map = new HashMap<String, Object>();
			for(int i=0;i<sArr.length;i++){
				String temp = sArr[i];
				if(temp!=null && !"".equalsIgnoreCase(temp.trim())){
					String[] t = temp.split(splitKeyAndValueFlag);
					map.put(t[0], (t.length==1 || t[1]==null)?"":t[1]);
				}
			}
		}
		return map;
	}
  
    /**
     * 将sourceStr以splitElementFlag分割为若干个元素，再将每个元素以splitKeyAndValueFlag分割为一对key/value，
     * 再将每一对key/value封装到Map中<br>
     * @param sourceStr 需分割的字符串
     * @param splitKeyAndValueFlag Map中key与value的分隔符
     * @param splitElementFlag Map中两个元素之间的分隔符
     * @return Map<String,Object> 返回封装完成的map
     * @throws Exception 
     */
    public static Map<String, String> parseStringToMap1(String sourceStr,
    		String splitKeyAndValueFlag,String splitElementFlag) throws Exception{
    	Map<String, String> map = null;
		//如果
		if(sourceStr==null || "".equalsIgnoreCase(sourceStr.trim())){
			throw new Exception("传递的sourceStr参数字符串为NULL或空");
		}
		if(splitKeyAndValueFlag==null || "".equalsIgnoreCase(splitKeyAndValueFlag.trim())){
			throw new Exception("传递的splitKeyAndValueFlag(Map中key与value的分隔符)参数字符串为NULL或空");
		}
		if(splitElementFlag==null || "".equalsIgnoreCase(splitElementFlag.trim())){
			throw new Exception("传递的splitElementFlag(Map中两个元素之间的分隔符)参数字符串为NULL或空");
		}
		
		String[] sArr = sourceStr.split(splitElementFlag);
		if(sArr!=null && sArr.length>0){
			map = new HashMap<String, String>();
			for(int i=0;i<sArr.length;i++){
				String temp = sArr[i];
				if(temp!=null && !"".equalsIgnoreCase(temp.trim())){
					String[] t = temp.split(splitKeyAndValueFlag);
					map.put(t[0], (t.length==1 || t[1]==null)?"":t[1]);
				}
			}
		}
		return map;
	}
  
    /**
     * 作用：将字符串首字母变为大写
	 * 方法firstCharToUpperCase的简要说明 
	 * @param paramString
	 * @return
	 */
	public static String firstCharToUpperCase(String paramString){
		return paramString.substring(0, 1).toUpperCase()+paramString.substring(1, paramString.length());
	}
	
    /**
     * 方法containsEnglishPunctuation的简要说明 <br>
     * 判断字符串中是否含有英文标点,默认校验的标点集合为：[?;'<>]
     * <pre>
     * </pre>
     * @param str
     * @param reg 正则表达式字符串
     * @return
     */
    public static boolean containsEnglishPunctuation(String str,String reg){
    	if("".equalsIgnoreCase(trim(str))){
    		return false;
    	}
    	if("".equalsIgnoreCase(trim(reg))){
    		reg = "[?;'<>]";
    	}
    	Pattern pattern=Pattern.compile(reg);//增加对应的标点
    	Matcher matcher = pattern.matcher(trim(str));
    	return matcher.find();
    }
    
    /**
     * list数组转换成字符串
     * @param list
     * @param separator
     * @return
     */
    public static String listToString(List<String> list, char separator) {
    	StringBuilder sb = new StringBuilder();
    	for (int i = 0; i < list.size(); i++) {
    		sb.append(list.get(i)).append(separator);
    	}
    	return sb.toString().substring(0,sb.toString().length()-1);
    }
    
    public static String[] split(String str,String expr){
		if("".equalsIgnoreCase(trim(str))){
			return new String[0];
		}
		return str.split(expr);
	}
}
