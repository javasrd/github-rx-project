package com.rx.web.inputrx;

import java.util.Iterator;
import java.util.List;

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
import com.rx.bean.inputrx.RxDisease;
import com.rx.bean.inputrx.RxProtocolConstant;
import com.rx.bean.inputrx.RxReqDataPatient;
import com.rx.bean.inputrx.RxReqProtocol;
import com.rx.bean.inputrx.RxRespProtocol;
import com.rx.service.inputrx.IDepartmentService;
import com.rx.service.inputrx.IDiagnosisService;
import com.rx.service.inputrx.IDoctorPatientService;
import com.rx.service.inputrx.IDoctorService;
import com.rx.service.inputrx.ILogReceivePatientService;
import com.rx.service.inputrx.IPatientService;

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
			//(3.2)保存医,患,诊断 数据到DB
			processMsg(patient);			
		}
			
		
		//(4)返回响应包
		RxRespProtocol resp=createResponse(RxProtocolConstant.STATUS_SUCCESS,
											reqProtocol.getVersion());		
		
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
	 * @Description: 保存患,科室,医,医-患,诊断信息
	 * 		在保存时存在一定的依赖关系.
	 * @param
	 *     @param dataSegment   
	 * @return 
	 *     void  
	 * @throws 
	 * @author Administrator
	 * @date 2018年1月16日-上午10:40:54
	 */
	private void processMsg(RxReqDataPatient dataSegment){
		
		//(1)保存患者
		long patientId=patientService.addPatient(dataSegment.getPatient().getId(),
				dataSegment.getPatient().getName(),
				dataSegment.getPatient().getGender(),
				dataSegment.getPatient().getOld(),
				dataSegment.getPatient().getCr_no());
		//(2)保存科室
		long departmentId=departmentService.addDepartment(dataSegment.getDepartment().getId(), 
				dataSegment.getDepartment().getName());
		
		//(3)保存医生
		long doctorId=doctorService.addDoctor(dataSegment.getDoctor().getId(),
				dataSegment.getDoctor().getName(), departmentId);
		
		//(4)保存医生-患者关系
		doctorPatientService.addDoctorPatient(doctorId, patientId);
		
		//(5)保存诊断信息
		List<RxDisease> diseaseList= dataSegment.getDiagnosis();
		for(Iterator<RxDisease> itor=diseaseList.iterator();itor.hasNext();){
			RxDisease disease=itor.next();
			diagnosisService.addDiagnosis(disease.getId(), doctorId, patientId, disease.getDisease());			
		}
			
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
