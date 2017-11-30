package com.quicksure.insurpal.utils.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttp3Util {
	private static final MediaType FORM = MediaType.parse("application/x-www-form-urlencoded");
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	public static final MediaType XML = MediaType.parse("application/xml; charset=utf-8");
	
	private static final OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
			.connectTimeout(30, TimeUnit.SECONDS)
			.readTimeout(20, TimeUnit.SECONDS)
			.build();
	
	/**
	 * okhttp3请求 	GET
	 * @param url
	 * @return
	 */
	public static String get(String url){
		Request request = new Request.Builder()
				.url(url)
				.build();
		try {
			Response response = mOkHttpClient.newCall(request).execute();
			if (!response.isSuccessful())
				throw new RuntimeException("Unexpected code " + response);
			return response.body().string();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
     * okhttp3请求	POST Xml
     * @param url       
     * @param params	请求参数
     * @return
     */
    public static String post(String url, String params) {
		RequestBody body = RequestBody.create(FORM, params);
		Request request = new Request.Builder()
				.url(url)
				.post(body)
				.build();
		try {
			Response response = mOkHttpClient.newCall(request).execute();
			if (!response.isSuccessful())
				throw new RuntimeException("Unexpected code " + response);
			return response.body().string();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
     * okhttp3请求	POST Xml
     * @param url       
     * @param params	请求参数
     * @return
     */
    public static String postXml(String url, String params) {
		RequestBody body = RequestBody.create(XML, params);
		Request request = new Request.Builder()
				.url(url)
				.post(body)
				.build();
		try {
			Response response = mOkHttpClient.newCall(request).execute();
			if (!response.isSuccessful())
				throw new RuntimeException("Unexpected code " + response);
			return response.body().string();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
    
    /**
     * okhttp3请求	POST json
     * @param url
     * @param json
     * @return
     */
    public static String postJson(String url, String json) {
		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder()
				.url(url)
				.post(body)
				.build();
		try {
			Response response = mOkHttpClient.newCall(request).execute();
			if (!response.isSuccessful())
				throw new RuntimeException("Unexpected code " + response);
			return response.body().string();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
    
	public static void main(String[] args) throws Exception, UnsupportedEncodingException, IOException{
		//测试get请求
		/*String url="https://api.weixin.qq.com/cgi-bin/token";
		List<NameValuePair> params=new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("grant_type","client_credential"));
		params.add(new BasicNameValuePair("appid","wx17131d4b6c21e514"));
		params.add(new BasicNameValuePair("secret","bee8f01c6a52c77e180ab2ea63c72fff"));
		String result1 = get(url+"?"+URLEncodedUtils.format(params, "utf-8"));
		System.out.println(result1);*/
		
		//测试POST请求kay-value(这个没测试)
		/*Map<String,String> map = new HashMap<String, String>();
		map.put("grant_type", "client_credential");
		map.put("appid", "wx17131d4b6c21e514");
		map.put("secret", "bee8f01c6a52c77e180ab2ea63c72fff");
		String result = postJson(url,JsonUtil.getJsonFromObject(map).toString());
		System.out.println(result);*/
		
		//测试POST请求json
		/*Map<String,String> map3 = new HashMap<String, String>();
		map3.put("userId", "496e79e32af74ae486b0b7362e72c1c7");
		map3.put("account", "test@qq.com");
		String result2 = postJson("https://dbl.1shb.net:8082/guduo-mobile/mobile/acount/unbundledPayAccount",JsonUtil.getJsonFromObject(map3).toString());
		System.out.println(result2);*/
		
		//测试POST请求xml
		String param2 = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\"?><PACKET type=\"REQUEST\" version=\"1.0\"><HEAD><TRANSTYPE>SNY</TRANSTYPE><SYSCODE></SYSCODE><TRANSCODE ></TRANSCODE><CONTENTTYPE></CONTENTTYPE><VERIFYTYPE></VERIFYTYPE><USER>ludi</USER><PASSWORD>ludi</PASSWORD><SVCSEQNO></SVCSEQNO></HEAD><THIRD><EXTENTERPCODE>CMBC0601037059</EXTENTERPCODE><PRODNO>0000</PRODNO><PLANNO>0000</PLANNO><TRANSCODE>100001</TRANSCODE><TRANSDATE>2017-09-06</TRANSDATE><TRANSTIME>17:41:54</TRANSTIME></THIRD><BODY><VhlTypeQuery><VEHICLE_NO></VEHICLE_NO><RACK_NO>MKLNBH77726253555</RACK_NO><VEHICLE_CODE></VEHICLE_CODE><VEHICLE_NAME>北京现代</VEHICLE_NAME><DEPT_NO>440305</DEPT_NO></VhlTypeQuery></BODY></PACKET>";
		String url = "http://agenttest.sinosafe.com.cn/carservice";
		String result = postXml(url,param2);
		System.out.println(result);
	}
}
