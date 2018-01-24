package com.rx.web.inputrx;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.rx.bean.inputrx.RxAPI;
import com.rx.bean.inputrx.RxProtocolConstant;
import com.rx.bean.inputrx.RxReqProtocol;
import com.rx.bean.inputrx.RxRespProtocol;
import com.rx.service.inputrx.IDepartmentService;
import com.rx.service.inputrx.IDiagnosisService;
import com.rx.service.inputrx.IDoctorPatientService;
import com.rx.service.inputrx.IDoctorService;
import com.rx.service.inputrx.ILogReceivePatientService;
import com.rx.service.inputrx.IPatientService;


/**
 * 用于模拟接收海典接收数据.
 * @ClassName: ReceivePrescriptionController
 * @Description: TODO
 * @author Administrator
 * @date 2018年1月24日-下午5:26:52
 * @version 1.0.0
 */
@Controller
@RequestMapping("/")
public class ReceivePrescriptionController {
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
	 * @Description: 
	 * 		模拟海典接收数据接口
	 * 		接收百非医保处方.
	 * @param
	 *     @param pack  处方数据包 
	 *     @param request
	 *     @return   
	 * @return 
	 *     Object  响应包 JSON格式
	 * @throws 
	 * @author Administrator
	 * @date 2018年1月16日-上午10:27:19
	 */
	@RequestMapping(value = "/prescapi", method = RequestMethod.POST)
	@ResponseBody		
	public Object receivePrescription(@RequestBody String pack,HttpServletRequest request) {
		//@RequestBody RxReqProtocol pack
		
		System.out.println("模拟海典接收处方数据---接收到的数据包为:"+pack);
		
		//(1)记录日志		
		
		//(2)第一次解析数据包
		RxReqProtocol reqProtocol = JSON.parseObject(pack, new TypeReference<RxReqProtocol>() {}); 		
		
		//TODO 注: 此处未对token,version,进行处理.
		
		//(3)根据func的值对协议包中DATA进行解析		
		if(reqProtocol.getFunc().equals(RxAPI.MSG_PATIENT_NMI)){
			
			//第二次数据解析.
			//RxReqDataPatient patient = JSON.parseObject(reqProtocol.getData(), new TypeReference<RxReqDataPatient>() {});
			
			//根据业务规则进行处理.
			System.out.println("进行业务处理......");
		}
		
		//(4)返回响应包
		RxRespProtocol resp=createResponse(RxProtocolConstant.STATUS_SUCCESS,"v1.0");		
		
		return resp;
	}
	
	/**
	 * @Description: 生成响应数据包
	 * @param
	 *     @param status	状态
	 *     @param version	版本号
	 *     @return   
	 * @return 
	 *     RxRespProtocol  响应数据包
	 * @throws 
	 * @author Administrator
	 * @date 2018年1月17日-上午11:04:45
	 */
	private RxRespProtocol createResponse(String status,String version){
		RxRespProtocol resp=new RxRespProtocol(); 
		resp.setResult(RxProtocolConstant.STATUS_SUCCESS);
		resp.setVersion(version);
		return resp;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
