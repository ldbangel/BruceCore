package com.quicksure.insurpal.utils.rsa;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密
 * @author Liu Dongbo
 *
 */
public class Md5Utils {
	/**
	 * MD5加密方法一
	 * string字符串进行MD5加密
	 * @param inStr
	 * @return
	 */
	public static String MD5(String inStr){  
        MessageDigest md5 = null;  
        try{  
            md5 = MessageDigest.getInstance("MD5");  
        }catch (Exception e){  
            System.out.println(e.toString());  
            e.printStackTrace();  
            return "";  
        }  
        char[] charArray = inStr.toCharArray();  
        byte[] byteArray = new byte[charArray.length];  
  
        for (int i = 0; i < charArray.length; i++)  
            byteArray[i] = (byte) charArray[i];  
        byte[] md5Bytes = md5.digest(byteArray);  
        StringBuffer hexValue = new StringBuffer();  
        for (int i = 0; i < md5Bytes.length; i++){  
            int val = ((int) md5Bytes[i]) & 0xff;  
            if (val < 16)  
                hexValue.append("0");  
            hexValue.append(Integer.toHexString(val));  
        }  
        return hexValue.toString();  
    }  
	
	/** 
	 * MD5加密方法二
     * MD5数字签名 
     * @param src 
     * @return 
     * @throws Exception 
     */  
    public static String md5(String src) throws Exception {  
       // 定义数字签名方法, 可用：MD5, SHA-1  
       MessageDigest md = MessageDigest.getInstance("MD5");  
       byte[] b = md.digest(src.getBytes("utf-8"));  
       StringBuilder sb = new StringBuilder();  
       for (int i = 0; i < b.length; i++) {  
           String s = Integer.toHexString(b[i] & 0xFF);  
           if (s.length() == 1) {  
               sb.append("0");  
           }  
           sb.append(s.toUpperCase());  
       }  
       return sb.toString();   
    }  
	
	/**
	 * 字符数组进行MD5加密
	 * @param b
	 * @return
	 */
	public static String MD5(byte[] b) {
        try {
        	MessageDigest md = MessageDigest.getInstance("MD5");
        	md.reset();
            md.update(b);
            byte[] hash = md.digest();
            StringBuffer outStrBuf = new StringBuffer(32);
            for (int i = 0; i < hash.length; i++) {
                int v = hash[i] & 0xFF;
                if (v < 16) {
                	outStrBuf.append('0');
                }
                outStrBuf.append(Integer.toString(v, 16).toLowerCase());
            }
            return outStrBuf.toString();
        } catch (NoSuchAlgorithmException e) {
        	e.printStackTrace();
            return new String(b);
        }
    }
  
    /** 
     * 加密解密算法 执行一次加密，两次解密 
     */   
    public static String convertMD5(String inStr){  
        char[] a = inStr.toCharArray();  
        for (int i = 0; i < a.length; i++){  
            a[i] = (char) (a[i] ^ 't');  
        }  
        String s = new String(a);  
        return s;  
    }  
  
    // 测试主函数  
    public static void main(String args[]) {  
        String s = new String("sa");  
        System.out.println("原始：" + s);  
        System.out.println("string2MD5后：" + MD5(s));  
        System.out.println("MD5后：" + MD5(s.getBytes()));  
//        System.out.println("加密的：" + convertMD5(s));  
        System.out.println("解密的：" + convertMD5(convertMD5(s)));  
  
    } 
    
}
