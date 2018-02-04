package com.rx.back.commons;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONArray;
import com.rx.back.task.SyncDrugInfoTask;
import com.rx.common.util.DateUtils;
import com.rx.common.util.ZipUtil;
import com.rx.entity.Drug;
import com.rx.service.excelutil.Common;
import com.rx.service.excelutil.ReadExcel;
import com.rx.service.txtutil.ReadWriteTxtUtil;

public class SyncDrugInfoUtil{

	private final static Logger log = Logger.getLogger(SyncDrugInfoUtil.class);
	
	private final static String url = "http://127.0.0.1/rx-back/api/demo";

	/**
	 * 处理同步药品数据
	 * @return
	 */
	public static List<Drug> processSyncDrug() {
		
		CloseableHttpResponse response = null;
		try {
			
			// 创建Httpclient对象
			CloseableHttpClient httpClient = HttpClients.createDefault();
			
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("ContentType", "application/x-www-form-urlencoded");
			
			// 执行http请求
			response = httpClient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			System.out.println("HTTP请求返回状态码：" + statusCode);
			log.info("HTTP请求返回状态码："+statusCode);
			
			if(statusCode==200){//发送请求获取药品信息TXT文本文件成功
				HttpEntity httpEntity = response.getEntity();
				if (httpEntity != null) {
					String realPath = "";
					try {
						System.out.println(httpEntity.getContentType());
						InputStream is = httpEntity.getContent();

						realPath = getRealPath();
						System.out.println("real path:" + realPath);

						if(StringUtils.isBlank(realPath)){
							//TODO 获取文件目录错误
							log.error("获取文件目录错误");
							try {
								response.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
							return null;
						}
						
						int index;
						byte[] bytes = new byte[1024];
						FileOutputStream fos = new FileOutputStream(realPath);
						while ((index = is.read(bytes)) != -1) {
							fos.write(bytes, 0, index);
						}
						fos.flush();
						fos.close();
						is.close();
					} catch (Exception e) {
						e.printStackTrace();
						//TODO
						log.error("HTTP连接请求结果写入文本文件异常");
					}
					
					// parseExcel(realPath);//解析EXCEL文件并保存到数据库
					return parseTxt(realPath);//解析TXT文本文件并保存到数据库
					
				}else{
					log.error("HTTP连接返回为空");
				}
			}else{////发送请求获取药品信息TXT文本文件异常
				//TODO 添加日志
				log.error("HTTP连接异常");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("HTTP请求异常", e);
			System.out.println(new Date() + " HTTP请求异常");
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 获取由时间和5位随机数字组成的文件名 2015112512345.jpg
	 * 
	 * @param file
	 * @param date
	 * @return
	 */
	private static String getRandomFileName() {

		try {
			String fileName = "";

			String bufix = ".zip";
			String random = Integer.toString(new Random().nextInt(89999) + 10000);
			fileName = DateUtils.getDateRandom() + random + bufix;
			return fileName;// 当前时间
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取写入文件目录
	 * 
	 * @return
	 */
	private static String getRealPath() {
		String subFolderName = DateUtils.getDate("yyyyMMdd");
		// String path =
		// "/D:/Tomcat/apache-tomcat-9.0.0.M13/webapps/rx-back/WEB-INF/classes/";
		String path = SyncDrugInfoTask.class.getResource("/").getPath();
		System.out.println(path);
		path = path.substring(1);
		System.out.println(path);
		path = path.substring(0, path.indexOf("webapps"));
		System.out.println(path);
		path = path + "webapps";
		System.out.println(path);
		path = path + File.separator + "upload" + File.separator + "drug file download" + File.separator + subFolderName
				+ File.separator;
		System.out.println(path);
		File filePath = new File(path);
		// 如果文件夹不存在则创建
		if (!filePath.exists() && !filePath.isDirectory()) {
			boolean flag = filePath.mkdirs();
			System.out.println("目录不存在   创建目录: " + filePath + " 结果：" + flag);
		} else {
			System.out.println("目录存在 : " + filePath);
		}
		path = path + getRandomFileName();
		return path;
	}

	/**
	 * 解析EXCEL文件 
	 * 		未用（格式改为txt文本）
	 * 
	 * @param drugFilePath
	 */
	private static List<Drug> parseExcel(String drugFilePath) {
		try {

			File file = ZipUtil.unzip(drugFilePath);
			System.out.println("解压后EXCEL文件目录：" + file);
			drugFilePath = file.getPath();
			System.out.println("解压后EXCEL文件目录：" + drugFilePath);

			System.out.println("定时任务，同步药品文件在磁盘的真实目录：" + drugFilePath);
			Map<String, Object> reqMap = new HashMap<String, Object>();
			reqMap.put(Common.EXCEL_PATH, drugFilePath);
			Map<String, Object> respMap = ReadExcel.readExcel(reqMap);
			String result_code = respMap.get("result_code").toString();
			if (StringUtils.isNotBlank(result_code) && result_code.equalsIgnoreCase("success")) {
				String drugListJSON = respMap.get("drugList").toString();
				List<Drug> drugList = JSONArray.parseArray(drugListJSON, Drug.class);
				return drugList;
			} else {
				System.out.println("解析EXCEL文件异常");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解析TXT文本文件
	 * 
	 * @param drugFilePath
	 */
	private static List<Drug> parseTxt(String drugFilePath) {
		
		try {//解压文件
			System.out.println("解压前TXT文本文件目录：" + drugFilePath);
			File file = ZipUtil.unzip(drugFilePath);
			System.out.println("解压如果TXT文本文件：" + file);
			drugFilePath = file.getPath();
			System.out.println("解压后TXT文本文件目录：" + drugFilePath);

			System.out.println("定时任务，同步药品文件在磁盘的真实目录：" + drugFilePath);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			log.error("解压TXT文本文件异常");
			return null;
		}
		
		String drugInfoJSON = "";
		try {//读取文件
			drugInfoJSON = ReadWriteTxtUtil.readTxt(drugFilePath);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			log.error("读取TXT文本文件异常");
			return null;
		}
		
		try {//解析JSON并保存内容
			if(StringUtils.isNotBlank(drugInfoJSON)){
				List<Drug> drugList = JSONArray.parseArray(drugInfoJSON, Drug.class);
				return drugList;
			}else{
				//TODO 文件内容为空
				log.error("文件内容为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			log.error("解析JSON并保存内容异常");
		}
		return null;
	}

	public static void main(String[] args) {
		String subFolderName = DateUtils.getDate("yyyyMMdd");
		String path = "/D:/Tomcat/apache-tomcat-9.0.0.M13/webapps/rx-back/WEB-INF/classes/";
		System.out.println(path);
		path = path.substring(1);
		System.out.println(path);
		path = path.substring(0, path.indexOf("webapps"));
		System.out.println(path);
		path = path + "webapps";
		System.out.println(path);
		path = path + File.separator + "upload" + File.separator + "drug file download" + File.separator
				+ subFolderName;
		System.out.println(path);
		path = path + getRandomFileName();
		File filePath = new File(path);
		// 如果文件夹不存在则创建
		if (!filePath.exists() && !filePath.isDirectory()) {
			boolean flag = filePath.mkdirs();
			System.out.println("目录不存在   创建目录: " + filePath + " 结果：" + flag);
		} else {
			System.out.println("目录存在 : " + filePath);
		}
	}

}
