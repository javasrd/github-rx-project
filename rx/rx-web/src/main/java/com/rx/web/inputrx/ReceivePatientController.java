package com.rx.web.inputrx;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.rx.bean.inputrx.RxAPI;
import com.rx.bean.inputrx.RxProtocolConstant;
import com.rx.bean.inputrx.RxReqDataPatient;
import com.rx.bean.inputrx.RxReqProtocol;
import com.rx.bean.inputrx.RxRespProtocol;



/**
 * 接收医,患,诊断信息 控制器.
 *
 */
@Controller
@RequestMapping("/")
public class ReceivePatientController {
	final String RESPONSE_THYMELEAF = "thymeleaf/inputrx/";
	final String RESPONSE_THYMELEAF_BACK = "thymeleaf/back/";
	final String RESPONSE_JSP = "jsps/";
	
	/**
	 * 对外接口(供东华调用)
	 * 
	 * @Description 自东华获取医,患,诊断信息数据 .
	 * @param pack 所获取医,患,诊断信息数据包 
	 * @return 响应包,JSON格式.协议格式见需求分析说明书.
	 */
	@RequestMapping(value = "/api", method = RequestMethod.POST)
	@ResponseBody		
	public Object receivePatient(@RequestBody RxReqProtocol pack) {
		System.out.println(pack.getFunc());		
		//System.out.println(pack);		
		
		
		//注: 此处未对token,version,进行处理.
		
		//根据func的值对协议包进行解析
		//将JSON字符串转换成相应的JAVA对象
		//重构建议:后面可以采用map方式将msg---->解析器. 采用查表的方式来处理消息数据.
		if(pack.getFunc().equals(RxAPI.MSG_PATIENT_NMI)){
			RxReqDataPatient patient = JSON.parseObject(pack.getData(), new TypeReference<RxReqDataPatient>() {});
			System.out.println(patient.getPatient().getName());
		}
			
		
		//返回响应包
		RxRespProtocol resp=new RxRespProtocol(); 
		resp.setResult(RxProtocolConstant.STATUS_SUCCESS);
		resp.setVersion(pack.getVersion());
		
		return resp;
	}
	
	/**
	 * 处理  模拟东华传送数据 界面
	 * @return 用户触发发送数据视图(demo)
	 */
	@RequestMapping(value = "/test_dh")	
	public String test_dh() {
		return RESPONSE_THYMELEAF + "demo";
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
