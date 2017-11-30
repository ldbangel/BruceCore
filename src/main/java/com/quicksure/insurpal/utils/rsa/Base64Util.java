package com.quicksure.insurpal.utils.rsa;

import org.apache.commons.codec.binary.Base64;

import com.quicksure.insurpal.utils.string.StringParseUtil;

public class Base64Util {
	
	/**
	 * 字符数组进行Base64编码
	 * @param str
	 * @return
	 */
	public static String encrypt(byte[] str){
		String strByte =  Base64.encodeBase64String(str);
		return strByte;
	}
	
	/**
	 * string字符串进行Base64编码
	 * @param str
	 * @return
	 */
	public static String encrypt(String str){
		String strByte =  Base64.encodeBase64String(StringParseUtil.safeToBytes(str));
		return strByte;
	} 
	
	/**
	 * string字符串进行Base64解码，返回字符串
	 * @param str
	 * @return 
	 */
	public static String decrypt(String str) {
		return StringParseUtil.safeToString(Base64.decodeBase64(str));
	}
	
	/**
	 * string字符串进行Base64解码，返回字符数组
	 * @param str
	 * @return
	 */
	public static byte[] decryptByte(String str) {
		return Base64.decodeBase64(str);
	}
	
	/**
	 * 字符数组进行Base64解码，返回字符串
	 * @param str
	 * @return
	 */
	public static String decrypt(byte[] str) {
		return StringParseUtil.safeToString(Base64.decodeBase64(str));
	}
	
	
	public static void main(String[] args) {
		System.out.println(decrypt(encrypt("liudongbo".getBytes())));
	}
	
}
