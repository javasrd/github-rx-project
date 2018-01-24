package com.rx.back.task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.rx.common.util.DateUtils;
import com.rx.common.util.FileUploadUtil;
import com.rx.common.util.RequestResultUtil;
import com.rx.common.util.ZipUtil;
import com.rx.entity.Drug;
import com.rx.service.back.IDrugService;
import com.rx.service.excelutil.Common;
import com.rx.service.excelutil.ReadExcel;

/** 
 * 定义任务 
 */
public class SyncDrugInfoTask {
	
	@Resource(name="drugServiceBean")
	private IDrugService drugService;
	
	private final String url = "http://127.0.0.1/rx-back/api/demo";
	
	public void sync(){
		//System.out.println("sync drug info ...");
		//Map<String, String> map = null;
		//String respStr = HttpClientUtil.doPost(url, map);
		//System.out.println(respStr);
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();

		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("ContentType", "application/x-www-form-urlencoded");
			// 创建参数列表
			/*if (param != null) {
				List<NameValuePair> paramList = new ArrayList<NameValuePair>();
				for (String key : param.keySet()) {
					paramList.add(new BasicNameValuePair(key, param.get(key)));
				}
				// 模拟表单
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
				entity.setContentType("application/x-www-form-urlencoded");
				httpPost.setEntity(entity);
			}*/
			// 执行http请求
			response = httpClient.execute(httpPost);
			System.out.println("HTTP请求返回状态码："+response.getStatusLine().getStatusCode());
			//log.info("HTTP请求返回状态码："+response.getStatusLine().getStatusCode());
			//resultString = EntityUtils.toString(response.getEntity(), "utf-8");
			//log.info("HTTP请求返回结果："+resultString);
			//System.out.println(new Date() + " 请求结果：" + resultString);
			HttpEntity httpEntity = response.getEntity();  
	         if(httpEntity != null){  
	        	 System.out.println(httpEntity.getContentType());
	        	 InputStream inputStream = httpEntity.getContent();  
	             
	        	 //转换为字节输入流  
	             /*BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, Consts.UTF_8));  
	             String body = null;  
	             while ((body = br.readLine()) != null) {  
	                 System.out.println(body);  
	             } */ 
	             
	             String realPath = this.getRealPath();
	             System.out.println("real path:"+realPath);
	             
	             int index;  
	             byte[] bytes = new byte[1024];  
	             FileOutputStream downloadFile = new FileOutputStream(realPath);  
	             while ((index = inputStream.read(bytes)) != -1) {  
	                 downloadFile.write(bytes, 0, index); 
	             }  
	             downloadFile.flush();  
	             downloadFile.close();  
	             inputStream.close();
	             
	             this.parseExcelAndSave(realPath);//解析EXCEL文件并保存到数据库
	         } 
		} catch (Exception e) {
			e.printStackTrace();
			//log.error("HTTP请求异常", e);
			System.out.println(new Date() + " HTTP请求异常");
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
	 * @return
	 */
	private String getRealPath(){
		String subFolderName = DateUtils.getDate("yyyyMMdd");
		//String path = "/D:/Tomcat/apache-tomcat-9.0.0.M13/webapps/rx-back/WEB-INF/classes/";
		String path = SyncDrugInfoTask.class.getResource("/").getPath();
		System.out.println(path);
		path = path.substring(1);
		System.out.println(path);
		path = path.substring(0, path.indexOf("webapps"));
		System.out.println(path);
		path = path+"webapps";
		System.out.println(path);
		path = path + File.separator + "upload" + File.separator + "drug file download" + File.separator + subFolderName + File.separator;
		System.out.println(path);
		File filePath = new File(path);
		//如果文件夹不存在则创建    
		if (!filePath.exists() && !filePath.isDirectory()) {
			boolean flag = filePath.mkdirs();
			System.out.println("目录不存在   创建目录: "+filePath+" 结果："+flag);
		} else {
			System.out.println("目录存在 : "+filePath);
		}
		path = path + getRandomFileName();
		return path;
	}
	
	/**
	 * 解析EXCEL文件并保存到数据库
	 * @param drugFilePath
	 */
	private void parseExcelAndSave(String drugFilePath){
		try {
			
			File file = ZipUtil.unzip(drugFilePath);
	        System.out.println("解压后EXCEL文件目录："+file);
	        drugFilePath = file.getPath();
	        System.out.println("解压后EXCEL文件目录："+drugFilePath);
			
			System.out.println("定时任务，同步药品文件在磁盘的真实目录："+drugFilePath);
			Map<String, Object> reqMap = new HashMap<String, Object>();
			reqMap.put(Common.EXCEL_PATH, drugFilePath);
			Map<String, Object> respMap = ReadExcel.readExcel(reqMap);
			String result_code = respMap.get("result_code").toString();
			if(StringUtils.isNotBlank(result_code) && result_code.equalsIgnoreCase("success")){
				String drugListJSON = respMap.get("drugList").toString();
				List<Drug> drugList = JSONArray.parseArray(drugListJSON, Drug.class);
				int rows = drugService.insertListSelective(drugList);
				if(rows>0){
					System.out.println("保存数据库成功");
				}else{
					System.out.println("保存数据库异常");
				}
			}else{
				System.out.println("解析EXCEL文件异常");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		String subFolderName = DateUtils.getDate("yyyyMMdd");
		String path = "/D:/Tomcat/apache-tomcat-9.0.0.M13/webapps/rx-back/WEB-INF/classes/";
		System.out.println(path);
		path = path.substring(1);
		System.out.println(path);
		path = path.substring(0, path.indexOf("webapps"));
		System.out.println(path);
		path = path+"webapps";
		System.out.println(path);
		path = path + File.separator + "upload" + File.separator + "drug file download" + File.separator + subFolderName;
		System.out.println(path);
		path = path + getRandomFileName();
		File filePath = new File(path);
		//如果文件夹不存在则创建    
		if (!filePath.exists() && !filePath.isDirectory()) {
			boolean flag = filePath.mkdirs();
			System.out.println("目录不存在   创建目录: "+filePath+" 结果："+flag);
		} else {
			System.out.println("目录存在 : "+filePath);
		}
	}
}
