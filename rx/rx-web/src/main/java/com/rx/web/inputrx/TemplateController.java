package com.rx.web.inputrx;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.rx.bean.inputrx.RxPatientParams;
import com.rx.bean.inputrx.RxRespProtocolDH;
import com.rx.service.inputrx.IDepartmentService;
import com.rx.service.inputrx.IDiagnosisService;
import com.rx.service.inputrx.IDoctorPatientService;
import com.rx.service.inputrx.IDoctorService;
import com.rx.service.inputrx.ILogReceivePatientService;
import com.rx.service.inputrx.IPatientService;

import freemarker.template.utility.StringUtil;

/**
 * @ClassName: ReceivePatientController
 * @Description: 接收医,患,诊断信息 控制器.
 * 				 对外接口,用于接收东华发送的医,患诊断等信息
 * @author Administrator
 * @date 2018年1月16日-上午11:06:52
 * @version 1.0.0
 */
@Controller
@RequestMapping("/")
public class TemplateController {
	final String RESPONSE_THYMELEAF = "thymeleaf/inputrx/";
	final String RESPONSE_THYMELEAF_BACK = "thymeleaf/back/";
	final String RESPONSE_JSP = "jsps/";
	
	
	@Autowired
	ILogReceivePatientService logService;  //医,患,诊断数据接收日志服务	
	@Autowired
	IPatientService patientService;  	   //患者服务
	@Autowired
	IDepartmentService departmentService;  //科室服务	
	@Autowired
	IDoctorService doctorService;  	   		//医生服务
	@Autowired
	IDoctorPatientService doctorPatientService; //医生-患者服务	
	@Autowired
	IDiagnosisService diagnosisService;		//诊断服务
	
	/**
	 * 使用模板
	 * @return 用户触发发送数据视图(demo)
	 */
	@RequestMapping(value = "/template/usetemplate")	
	public String user_template() {
		return RESPONSE_THYMELEAF + "usetemplate";
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
