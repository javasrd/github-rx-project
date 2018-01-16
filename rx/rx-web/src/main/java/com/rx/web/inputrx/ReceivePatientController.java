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
import com.rx.bean.inputrx.RxReqDataPatient;
import com.rx.bean.inputrx.RxReqProtocol;
import com.rx.bean.inputrx.RxRespProtocol;
import com.rx.service.inputrx.ILogReceivePatientService;

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
public class ReceivePatientController {
	final String RESPONSE_THYMELEAF = "thymeleaf/inputrx/";
	final String RESPONSE_THYMELEAF_BACK = "thymeleaf/back/";
	final String RESPONSE_JSP = "jsps/";
	
	
	@Autowired
	ILogReceivePatientService logService;  //医,患,诊断数据接收日志服务
	
	/**
	 * @Description: 
	 * 		对外接口(供东华调用)
	 * 		自东华获取医,患,诊断信息数据 .
	 * @param
	 *     @param pack  医,患,诊断信息数据包  
	 *     @param request
	 *     @return   
	 * @return 
	 *     Object  响应包 JSON格式
	 * @throws 
	 * @author Administrator
	 * @date 2018年1月16日-上午10:27:19
	 */
	@RequestMapping(value = "/api", method = RequestMethod.POST)
	@ResponseBody		
	public Object receivePatient(@RequestBody String pack,HttpServletRequest request) {
		//@RequestBody RxReqProtocol pack
		//System.out.println(pack.getFunc());
		
		System.out.println(pack);
		
		//(1)记录日志
		String remoteURL=getIpAddr(request);
		writeLog(remoteURL,pack);
		
		//(2)第一次解析数据包
		RxReqProtocol reqProtocol = JSON.parseObject(pack, new TypeReference<RxReqProtocol>() {}); 		
		
		//TODO 注: 此处未对token,version,进行处理.
		
		//(3)根据func的值对协议包中DATA进行解析		
		//TODO 重构建议:后面可以采用map方式将msg---->解析器. 采用查表的方式来处理消息数据.
		if(reqProtocol.getFunc().equals(RxAPI.MSG_PATIENT_NMI)){
			
			//(3.1)第二次解析数据包->医,患,诊断信息对象
			RxReqDataPatient patient = JSON.parseObject(reqProtocol.getData(), new TypeReference<RxReqDataPatient>() {});
			System.out.println(patient.getPatient().getName());
			//(3.2)保存医,患,诊断 数据
			savePatient(patient);
			
		}
			
		
		//(4)返回响应包
		RxRespProtocol resp=new RxRespProtocol(); 
		resp.setResult(RxProtocolConstant.STATUS_SUCCESS);
		resp.setVersion(reqProtocol.getVersion());
		
		return resp;
	}
	
	
	/**
	 * @Description: 记录数据接收日志
	 * @param
	 *     @param ip
	 *     @param recvData   
	 * @return 
	 *     void  
	 * @throws 
	 * @author Administrator
	 * @date 2018年1月16日-上午10:22:22
	 */
	private void writeLog(String ip,String recvData){
		logService.addLog(ip, recvData);
	}
	
	/**
	 * @Description: 保存医,患,诊断等信息
	 * @param
	 *     @param patient   
	 * @return 
	 *     void  
	 * @throws 
	 * @author Administrator
	 * @date 2018年1月16日-上午10:40:54
	 */
	private void savePatient(RxReqDataPatient patient){
		
	}
	
    /**
     * @Description: 获取请求方的IP
     * @param
     *     @param request 请求对象
     *     @return   
     * @return 
     *     String  请求方IP地址
     * @throws 
     * @author Administrator
     * @date 2018年1月16日-上午11:01:33
     */
    public  String getIpAddr(HttpServletRequest request)  {  
        String ip  =  request.getHeader( " x-forwarded-for " );  
         if (ip  ==   null   ||  ip.length()  ==   0   ||   " unknown " .equalsIgnoreCase(ip))  {  
            ip  =  request.getHeader( " Proxy-Client-IP " );  
        }   
         if (ip  ==   null   ||  ip.length()  ==   0   ||   " unknown " .equalsIgnoreCase(ip))  {  
            ip  =  request.getHeader( " WL-Proxy-Client-IP " );  
        }   
         if (ip  ==   null   ||  ip.length()  ==   0   ||   " unknown " .equalsIgnoreCase(ip))  {  
           ip  =  request.getRemoteAddr();  
       }   
        return  ip;  
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
