package com.quicksure.insurpal.utils.json;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 阿里fastjson方法
 * Fastjson的最主要的使用入口是com.alibaba.fastjson.JSON
 * 本类仅供参考
 * @author Liu Dongbo
 *
 */
public class FastJsonUtil {
	
	/**
	 * 把JSON文本parse为JSONObject或者JSONArray
	 * @param text
	 * @return
	 */
	public static Object parse(String text){
		if(StringUtils.isEmpty(text)) return null;
		return JSON.parse(text);
	}
	
	/**
	 * 把JSON文本parse成JSONObject
	 * @param text
	 * @return
	 */
	public static JSONObject parseObject(String text){
		if(StringUtils.isEmpty(text)) return null;
		return JSON.parseObject(text);
	}
	
	/**
	 * 把JSON文本parse为JavaBean
	 * @param text
	 * @param clazz
	 * @return
	 */
	public static <T> T parseObject(String text,Class<T> clazz){
		if(StringUtils.isEmpty(text)) return null;
		return JSON.parseObject(text, clazz);
	}
	
	/**
	 * 把JSON文本parse成JSONArray
	 * @param text
	 * @return
	 */
	public static JSONArray parseArray(String text){
		if(StringUtils.isEmpty(text)) return null;
		return JSON.parseArray(text);
	}
	
	/**
	 * 把JSON文本parse成JavaBean集合
	 * @param text
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> parseArray(String text,Class<T> clazz){
		if(StringUtils.isEmpty(text)) return null;
		return JSON.parseArray(text, clazz);
	}
	
	/**
	 * 将JavaBean转换为JSONObject或者JSONArray;
	 * @param obj
	 * @return
	 */
	public static Object toJSON(Object obj){
		if(obj == null) return null;
		return JSON.toJSON(obj);
	}
	
	/**
	 * 将JavaBean转换为JSONObject或者JSONArray;
	 * @param obj
	 * @return
	 */
	public static String toJSONString(Object obj){
		if(obj == null) return null;
		return JSON.toJSONString(obj);
	}
	
//	public static final Object parse(String text); // 把JSON文本parse为JSONObject或者JSONArray
//	public static final JSONObject parseObject(String text)； // 把JSON文本parse成JSONObject
//	public static final <T> T parseObject(String text, Class<T> clazz); // 把JSON文本parse为JavaBean
//	public static final JSONArray parseArray(String text); // 把JSON文本parse成JSONArray
//	public static final <T> List<T> parseArray(String text, Class<T> clazz); //把JSON文本parse成JavaBean集合
//	public static final String toJSONString(Object object); // 将JavaBean序列化为JSON文本
//	public static final String toJSONString(Object object, boolean prettyFormat); // 将JavaBean序列化为带格式的JSON文本
//	public static final Object toJSON(Object javaObject); //将JavaBean转换为JSONObject或者JSONArray;
}
