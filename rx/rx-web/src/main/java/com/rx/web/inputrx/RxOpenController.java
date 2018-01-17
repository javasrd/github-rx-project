package com.rx.web.inputrx;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * 打开处方录入窗口控制器
 *
 */
@Controller
/*@RequestMapping("/")*/
public class RxOpenController {
	final String RESPONSE_THYMELEAF = "thymeleaf/inputrx/";
	final String RESPONSE_THYMELEAF_BACK = "thymeleaf/back/";
	final String RESPONSE_JSP = "jsps/";

	
	/**
	 * @Description 打开医生处方录入界面,对外接口 
	 * @return 医生开处方视图(rxwin)
	 */
	@RequestMapping(value = "/openwin")
	public String openWin(@RequestParam String patientId) {
		System.out.println("patientId:"+patientId);
		return RESPONSE_THYMELEAF + "hospital";
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
