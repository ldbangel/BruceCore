package com.quicksure.insurpal.utils.string;

/**
 * @author Liu Dongbo
 *
 */
public class StringParseUtil {
	/**
	 * 将对象安全转换成int类型，失败时返回0
	 */
	public static int safeToInt(String str) {
		int rs = 0;
		try {
			rs = Integer.parseInt(str);
		} catch (Exception ex) {
			rs = 0;
		}
		return rs;
	}
	
	/**
	 * 将对象安全转换成short类型
	 */
	public static int safeToShort(String str) {
		short rs = 0;
		try {
			rs = Short.parseShort(str);
		} catch (Exception ex) {
			rs = 0;
		}
		return rs;
	}
	
	/**
	 * 将对象安全转换成long类型
	 */
	public static long safeToLong(String str) {
		long rs = 0;
		try {
			rs = Long.parseLong(str.toString());
		} catch (Exception ex) {
			rs = 0;
		}
		return rs;
	}

	/**
	 * 将对象安全转换成double类型
	 */
	public static double safeToDouble(String str) {
		double rs = 0;
		try {
			rs = Double.parseDouble(str);
		} catch (Exception ex) {
			rs = 0;
		}
		return rs;
	}
	
	/**
	 * 将对象安全转换成float类型
	 */
	public static float safeToFloat(String str) {
		float rs = 0;
		try {
			rs = Float.parseFloat(str);
		} catch (Exception ex) {
			rs = 0;
		}
		return rs;
	}
	
	/**
	 * byte[]转String
	 * @param bytes
	 * @return
	 */
	public static String safeToString(byte[] bytes){
		if(bytes != null && bytes.length>0){
			try {
				return new String(bytes,"UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
				return "";
			}
		}
		return "";
	}
	
	public static byte[] safeToBytes(String str){
		byte[] strByte = null;
		try {
			strByte = str.getBytes("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			return strByte;
		}
		return strByte;
	}
}
