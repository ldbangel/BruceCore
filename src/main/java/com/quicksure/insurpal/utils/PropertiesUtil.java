package com.quicksure.insurpal.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.quicksure.insurpal.utils.string.StringUtils;

/**
 * 
 * @author Liu Dongbo
 *
 */
public final class PropertiesUtil extends PropertyPlaceholderConfigurer {

	private static Map<String, String> ctxPropertiesMap;
	//在springcontext.xml中配置添加
	private List<String> decryptProperties;
	
	@Override
	protected void loadProperties(Properties props) throws IOException {
		//读取配置文件中的值
		super.loadProperties(props);
		ctxPropertiesMap = new HashMap<String, String>();
		for(Object key:props.keySet()){
			String keyStr = key.toString();
			String value = props.getProperty(keyStr);
			if(decryptProperties != null && decryptProperties.contains(keyStr)){
				/*//解密方法（手动加密，可以使用Base64）
				value = Base64.decode(value);*/
				props.setProperty(keyStr, value);
			}
			ctxPropertiesMap.put(keyStr, value);
		}
	}

	/**
	 * 根据key获取值
	 * @param key：键
	 * @return
	 */
	public static String getString(String key){
		try {
			return ctxPropertiesMap.get(key);
		} catch (MissingResourceException e) {
			return null;
		}
	}
	
	/**
	 * 根据key获取值
	 * @param key：键
	 * @param defaultValue：值为空时的默认值
	 * @return
	 */
	public static String getString(String key,String defaultValue){
		String value = ctxPropertiesMap.get(key);
		if(StringUtils.isEmpty(value)){
			return defaultValue;
		}
		return value;
	}
	
	/**
	 * 根据key获取值
	 * @param key：键
	 * @return
	 */
	public static int getInt(String key){
		return Integer.parseInt(ctxPropertiesMap.get(key));
	}
	
	/**
	 * 根据key获取值
	 * @param key：键
	 * @param defaultValue：值为空时的默认值
	 * @return
	 */
	public static int getINt(String key,int defaultValue){
		String value = ctxPropertiesMap.get(key);
		if(StringUtils.isEmpty(value)){
			return defaultValue;
		}
		return Integer.parseInt(value);
	}
	
	public List<String> getDecryptProperties() {
		return decryptProperties;
	}

	public void setDecryptProperties(List<String> decryptProperties) {
		this.decryptProperties = decryptProperties;
	}
}
