package com.quicksure.insurpal.utils.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
public class HttpRequestUtility {
	private static final Logger logger = Logger
			.getLogger(HttpRequestUtility.class);
	
	/**
	 * HttpClient Get 请求
	 * @param url
	 * @param params
	 * @return
	 */
	public static String getResponsebyGet(String url, List<NameValuePair> params) {
		logger.info("开始执行HttpClient get 请求，请求URL为： "+url);
		String result = "";
		HttpClient httpClient = new DefaultHttpClient();
		// 连接时间
		httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 3000);
		// 数据传输时间
		httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,2000);
		// Get请求
		HttpGet httpget = new HttpGet(url);
		try {
			// 设置参数
			String str = EntityUtils.toString(new UrlEncodedFormEntity(params,"UTF-8"));
			httpget.setURI(new URI(httpget.getURI().toString() + "?" + str));
			// 发送请求
			HttpResponse httpResponse = httpClient.execute(httpget);

			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				logger.error("执行HttpClient get 请求报错，statusCode为 ： "+statusCode);
			}
			// 获取返回数据
			HttpEntity entity = httpResponse.getEntity();
			String body = EntityUtils.toString(entity, "utf-8");
			result = body;
			if (entity != null) {
				EntityUtils.consume(entity);
			}
		} catch (Exception e) {
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		logger.info("结束执行HttpClient get 请求，返回结果为： "+result);
		return result;
	}
	
	/**
     * HttpURLConnection POST请求
     * 
     * @param url 发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        OutputStream out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST");
            conn.setReadTimeout(900000);
            conn.setConnectTimeout(600000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.connect();
            // 获取URLConnection对象对应的输出流
            out = conn.getOutputStream();
            out.write(param.getBytes("UTF-8"));
            out.close();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            System.err.println("返回结果为："+result);
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        } finally { 				
        	//使用finally块来关闭输出流、输入流
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
    
	/**
	 * POST Json请求
	 * @param args
	 */
	
	public static void main(String[] args) {
		String url="https://api.weixin.qq.com/cgi-bin/token";
		HttpRequestUtility httpRequestUtility=new HttpRequestUtility();
		List<NameValuePair> params=new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("grant_type","client_credential"));
		params.add(new BasicNameValuePair("appid","wx17131d4b6c21e514"));
		params.add(new BasicNameValuePair("secret","bee8f01c6a52c77e180ab2ea63c72fff"));
		String result = httpRequestUtility.getResponsebyGet(url, params);
		System.out.println(result);
	}
}
