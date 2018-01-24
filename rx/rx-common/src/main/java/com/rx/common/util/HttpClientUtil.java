package com.rx.common.util;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * HTTP请求工具类
 * 
 * @author srd
 *
 */
public class HttpClientUtil {

	private static Logger log = Logger.getLogger(HttpClientUtil.class);
	
	/**
	 * HTTP GET请求
	 * 
	 * @param url
	 * @param param
	 * 		Map<String, String> param
	 * @return
	 */
	public static String doGet(String url, Map<String, String> param) {

		// 创建Httpclient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();

		String resultString = "";
		CloseableHttpResponse response = null;
		try {
			// 创建uri
			URIBuilder builder = new URIBuilder(url);
			if (param != null) {
				for (String key : param.keySet()) {
					builder.addParameter(key, param.get(key));
				}
			}
			URI uri = builder.build();

			// 创建http GET请求
			HttpGet httpGet = new HttpGet(uri);

			// 执行请求
			response = httpclient.execute(httpGet);
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == 200) {
				resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resultString;
	}

	/**
	 * HTTP GET请求
	 * @param url
	 * @return
	 */
	public static String doGet(String url) {
		return doGet(url, null);
	}

	/**
	 * HTTP POST请求
	 * @param url
	 * @param param
	 * 		Map<String, String> param
	 * @return
	 */
	public static String doPost(String url, Map<String, String> param) {
		
		log.info("HTTP请求："+url);
		//log.info("HTTP请求参数："+param.toString());
		System.out.println(new Date() + " HTTP请求："+url);
		//System.out.println(new Date() + " HTTP请求参数："+param.toString());
		
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();

		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("ContentType", "application/x-www-form-urlencoded");
			// 创建参数列表
			if (param != null) {
				List<NameValuePair> paramList = new ArrayList<NameValuePair>();
				for (String key : param.keySet()) {
					paramList.add(new BasicNameValuePair(key, param.get(key)));
				}
				// 模拟表单
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
				entity.setContentType("application/x-www-form-urlencoded");
				httpPost.setEntity(entity);
			}
			// 执行http请求
			response = httpClient.execute(httpPost);
			System.out.println("HTTP请求返回状态码："+response.getStatusLine().getStatusCode());
			log.info("HTTP请求返回状态码："+response.getStatusLine().getStatusCode());
			resultString = EntityUtils.toString(response.getEntity(), "utf-8");
			log.info("HTTP请求返回结果："+resultString);
			System.out.println(new Date() + " 请求结果：" + resultString);
			return resultString;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("HTTP请求异常", e);
			System.out.println(new Date() + " HTTP请求异常");
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}

	/**
	 * HTTP POST请求
	 * @param url
	 * @param param
	 * 		String param
	 * @return
	 */
	public static String doPost(String url, String param) {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();

		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("ContentType", "application/x-www-form-urlencoded");
			// 创建参数列表
			if (StringUtils.isNotEmpty(param)) {
				StringEntity se = new StringEntity(param, ContentType.APPLICATION_FORM_URLENCODED);
				httpPost.setEntity(se);
			}
			// 执行http请求
			response = httpClient.execute(httpPost);
			resultString = EntityUtils.toString(response.getEntity(), "utf-8");
			System.out.println("请求结果：" + resultString);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return resultString;
	}
		
}
