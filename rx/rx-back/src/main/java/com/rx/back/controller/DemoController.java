package com.rx.back.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rx.back.commons.SessionConstants;
import com.rx.bean.UserBean;
import com.rx.common.util.ZipUtil;

@Controller
public class DemoController {

	private final Logger log = Logger.getLogger(getClass());
	
	/**
	 * 模拟【海典ERP】服务接口
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/api/demo")
	public void demo(HttpServletRequest request, HttpServletResponse response, String error) {
		
		Subject subject = SecurityUtils.getSubject();
		UserBean user = (UserBean)subject.getPrincipal();
		request.getSession().setAttribute(SessionConstants.USER, user);
		
		try {
			String targetPath = "D:\\Tomcat\\apache-tomcat-9.0.0.M13\\webapps\\upload\\drug file\\20180129\\20180129000000.txt";
			//File file = ZipUtil.zip(targetPath);
			File file = new File(targetPath);
		
			System.out.println("/api/demo:"+file.getPath());
			
            String filename = file.getName();// 获取文件名称
            InputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            response.reset();
            // 先去掉文件名称中的空格,然后转换编码格式为utf-8,保证不出现乱码,这个文件名称用于浏览器的下载框中自动显示的文件名
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.replaceAll(" ", "").getBytes("utf-8"),"iso8859-1"));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream os = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            os.write(buffer);// 输出文件
            os.flush();
            os.close();
        } catch (Exception e) {
        	e.printStackTrace();
        }
		
	}
	
}
