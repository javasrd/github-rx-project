package com.rx.service.txtutil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class ReadWriteTxtUtil {
	
	private final static Logger log = Logger.getLogger(ReadWriteTxtUtil.class);
	
	/**
	 * 读入TXT文本文件
	 * @param txtPath
	 * @return
	 */
	public static String readTxt(String txtPath){
		try {
			/* 读入TXT文本文件 */  
	        //String pathname = "D:\\en\\input_utf_8.txt"; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
			File filename = new File(txtPath); // 要读取以上路径的input。txt文件  
	        InputStreamReader isr = new InputStreamReader(new FileInputStream(filename), Charset.forName("UTF-8")); // 建立一个输入流对象reader  
	        BufferedReader br = new BufferedReader(isr); // 建立一个对象，它把文件内容转成计算机能读懂的语言  
	        //StringBuffer sb = new StringBuffer();
	        String result = "";
	        String line = "";  
	        int i = 0;
	        while (StringUtils.isNotBlank((line = br.readLine()))) {  
	            //line += br.readLine(); // 一次读入一行数据  
	        	//sb.append(line);
	        	result+=line;
	        	i++;
	        	log.info("正在读取第 "+i+" 行，请等待。。。");
	        }
	        br.close();
	        isr.close();
	        log.info("文件内容："+result);
	        return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 写入TXT文本文件
	 * 		未测试，用时需要自测
	 * @param txtPath
	 * @param txt
	 * @return
	 */
	public static boolean writeTxt(String txtPath, String txt){
		try {
			/* 写入TXT文本文件 */  
	        File writename = new File(txtPath); // 相对路径，如果没有则要建立一个新的output。txt文件  
	        writename.createNewFile(); // 创建新文件  
	        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(writename), Charset.forName("UTF-8")));  
	        out.write(txt); // \r\n即为换行  
	        out.flush(); // 把缓存区内容压入文件  
	        out.close(); // 最后记得关闭文件
	        return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void main(String[] args) {
		try { // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw  

	        /* 读入TXT文件 */  
			String pathname = "D:\\Tomcat\\apache-tomcat-9.0.0.M13\\webapps\\upload\\drug file download\\20180129\\20180129000000.txt";
	        //String pathname = "D:\\en\\input_utf_8.txt"; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径  
	        File filename = new File(pathname); // 要读取以上路径的input。txt文件  
	        InputStreamReader isr = new InputStreamReader(new FileInputStream(filename), Charset.forName("UTF-8")); // 建立一个输入流对象reader  
	        BufferedReader br = new BufferedReader(isr); // 建立一个对象，它把文件内容转成计算机能读懂的语言  
	        StringBuffer sb = new StringBuffer();
	        String line = "";  
	        while ((line = br.readLine()) != null) {  
	            //line += br.readLine(); // 一次读入一行数据  
	        	sb.append(line);
	            System.out.println("行内容："+line);
	        }
	        br.close();
	        isr.close();
	        System.out.println("文件内容："+sb.toString());

	        /* 写入Txt文件 */  
	        /*File writename = new File("D:\\en\\output.txt"); // 相对路径，如果没有则要建立一个新的output。txt文件  
	        writename.createNewFile(); // 创建新文件  
	        BufferedWriter out = new BufferedWriter(new FileWriter(writename));  
	        out.write("我会写入文件啦\r\n"); // \r\n即为换行  
	        out.flush(); // 把缓存区内容压入文件  
	        out.close(); // 最后记得关闭文件  
*/
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    }
	}
}
