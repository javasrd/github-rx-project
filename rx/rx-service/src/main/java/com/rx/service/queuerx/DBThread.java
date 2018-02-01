package com.rx.service.queuerx;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rx.bean.inputrx.PrescMsg;
import com.rx.bean.inputrx.RxAPI;
import com.rx.bean.inputrx.RxDisease;
import com.rx.bean.inputrx.RxDrug;
import com.rx.bean.inputrx.RxReqSendPrescription;
import com.rx.bean.inputrx.RxReqSendPrescriptionData;
import com.rx.common.util.HttpClientUtil;
import com.rx.entity.Department;
import com.rx.entity.Diagnosis;
import com.rx.entity.Doctor;
import com.rx.entity.Drug;
import com.rx.entity.LogSendPresc;
import com.rx.entity.Patient;
import com.rx.service.inputrx.IDepartmentService;
import com.rx.service.inputrx.IDiagnosisService;
import com.rx.service.inputrx.IDispensaryService;
import com.rx.service.inputrx.IDoctorService;
import com.rx.service.inputrx.IDrugService;
import com.rx.service.inputrx.ILogSendPrescService;
import com.rx.service.inputrx.IPatientService;

/**
 * @ClassName: DBThread
 * @Description: 用于实现实际的业务处理
 * @author Administrator
 * @date 2018年2月1日-上午12:05:25
 * @version 1.0.0
 */
@Component
@Scope("prototype")//spring 多例
public class DBThread implements Runnable {
    private PrescMsg msg;
    private Logger log = Logger.getLogger(DBThread.class);

   
	@Autowired
	IPatientService patientService;  	   //患者服务
	@Autowired
	IDepartmentService departmentService;  //科室服务	
	@Autowired
	IDoctorService doctorService;  	   		//医生服务
	@Autowired
	IDiagnosisService diagnosisService;		//诊断服务
	@Autowired
	IDrugService drugService;  				//药房服务
	
	@Autowired
	ILogSendPrescService logSendPrescService;  //发送处方日志服务
	@Autowired
	IDispensaryService dispensaryService;  //药房
	@Autowired
	ThreadPoolManager tpm;  //线程池    
    
    @Override
    public void run() {
    	
    	//实际的业务处理.
        /*Systemlog systemlog = new Systemlog();
        systemlog.setTime(new Date());
        systemlog.setLogdescribe(msg);*/
    	
    	//(1)生成数据包
    	String pack=createPrescPackage(msg.getPresc(),msg.getPrescNo(),msg.getPrescId());
    	
    	//(2)记录日志(将数据包包记录日志). 0:初始状态;1:成功; 2:失败; 3:网络连接失败		
		String url="http://localhost:8080/rx-web/prescapi1";
		//String url="http://222.222.66.25:8093/nmi";		
		long logId=logSendPrescService.addLog(url, pack, 0);  //记录日志,将日志的ID号传送给线程处理.
		
    	//(3)发送数据包
		sendPrecriptionToDispensary(url,pack,logId);
    	
        log.info("insert->" + msg);
    }
    
	/**
	 * @Description: 将处方推送给药房.
	 * @param
	 *     @param url  药房系统的url
	 *     @param jsonPack 数据包  
	 * @return 
	 *     void  
	 * @throws 
	 * @author Administrator
	 * @date 2018年2月1日-上午1:59:53
	 */
	private void sendPrecriptionToDispensary(String url,String jsonPack,long logId){
		//(1)向其它的服务器发送请求		
		//String result=HttpClientUtil.doPost(url, parms);
		//String result=HttpClientUtil.doPostJson(url, jsonPack);
		
		try {
			int counter=0;
			while(counter<3){
				String result=HttpClientUtil.doPostJson(url, jsonPack);
				if(!result.equals("")){  //服务端响应   
					JSONObject jobj=JSON.parseObject(result);
					int status=jobj.getIntValue("result");
					int logStatus=0;
					switch(status){
					case 1://海典成功接收并加入数据库
						logStatus=1;
						break;
					case 0: //海典成功接收,但在加入数据库时发生错误
						logStatus=2;
						break;
					}
					//列新日志状态					
					updateLogStatus(logId,logStatus);
					break;
				}
				else{ //如果请求返回的结果为空时,则表明出现请求时错误(网络错误,URL地址错误等)
					log.info("请求发送处方时发生网络错误! 1000ms后重新发送请求......");
					Thread.sleep(1000);
					counter++;
					if(counter==3){						
						updateLogStatus(logId,3);  //列新日志状态
						log.info("重试三次后仍没有发送成功,更新处方发送日志......");
					}
				}
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println("模拟发送处方------返回结果:"+result);		
	}
	
	/**
	 * @Description: 生成发送到海典的数据包
	 * @param
	 *     @param presc    处方内容,JSON格式
	 *     @param presc_no 处方编号
	 *     @param prescId  处方ID
	 *     @return   	         发送到海典的数据包	
	 * @return 
	 *     String  发送到海典的数据包
	 * @throws 
	 * @author Administrator
	 * @date 2018年2月1日-上午1:38:57
	 */
	private String createPrescPackage(String presc,String presc_no,long prescId){
		//(1)第一次解析
		JSONObject parm=JSON.parseObject(presc);
		long patient_id=parm.getLongValue("patientId");
		long doctor_id=parm.getLongValue("doctorId");
		long department_id=parm.getLongValue("departmentId");
		
		//（2）第二次解析,处方中药品		
		JSONArray drugArray=JSON.parseArray(parm.getString("prescDrugs"));
		
		//(3)生成需要发送的数据包.
		RxReqSendPrescription protocol=new RxReqSendPrescription();
		RxReqSendPrescriptionData data=protocol.getData();
		protocol.setVersion("1.0");
		protocol.setToken("HDERP");
		protocol.setFunc(RxAPI.MSG_PRESC_PATIENT_NMI);
		
		//(4)生成数据段
		Patient patient=patientService.selectByPrimaryKey(patient_id);
		Doctor doctor=doctorService.selectByPrimaryKey(doctor_id);
		Department department=departmentService.selectByPrimaryKey(department_id);
		List<Diagnosis> diagnosisList=diagnosisService.getDiagnosisByPatientAndDoctor(patient_id, doctor_id);
		
		
		//患者信息
		data.getPatient().setCr_no(patient.getCrNo());  	//病历号
		data.getPatient().setGender(patient.getSex()==1 ? "男":"女");
		data.getPatient().setOld(patient.getOld().toString());
		data.getPatient().setId(patient.getOldId());  		//就诊号
		data.getPatient().setName(patient.getName());
		data.getPatient().setRn(patient.getRn());  			//登记号
		data.getPatient().setPresc_no(presc_no);				//处方编号
		
		
		//医生信息
		data.getDoctor().setId(doctor.getOldId());
		data.getDoctor().setName(doctor.getName());
		//部门信息
		data.getDepartment().setId(department.getOldId());
		data.getDepartment().setName(department.getName());
		
		List<RxDisease> diseaseList= data.getDiagnosis();
		
		//诊断信息
		for(int i=0;i<diagnosisList.size();i++){
			Diagnosis diagnosis=diagnosisList.get(i);
			
			RxDisease rxDisease=new RxDisease();			
			rxDisease.setId(diagnosis.getId().toString());
			rxDisease.setDisease(diagnosis.getDisease());
			
			diseaseList.add(rxDisease);
		}
		
		//药品信息
		List<RxDrug> drugList=data.getPrescription();
		for(int i=0;i<drugArray.size();i++){
			JSONObject jsonDrug= drugArray.getJSONObject(i);
			
			//long drugId=jsonDrug.getLongValue("id");	//ID		
			//BigDecimal dosage=jsonDrug.getBigDecimal("dosage");	//每次剂量
			
			//String mode=jsonDrug.getString("drugmode"); //给药方式
			//int quantity=jsonDrug.getIntValue("quantity");//数量
			//int days=jsonDrug.getIntValue("days");		  //服药天数	
			//String doseUnit=jsonDrug.getString("doseunit"); //服药剂量单位
			//String times=jsonDrug.getString("drugtimes");  //给药次数
			//long patientId=jsonDrug.getLongValue("patientid"); //患者ID
			//long doctorId=jsonDrug.getLongValue("doctorid");   //医生ID			
					
			RxDrug drug=new RxDrug();
			Drug drugCate=drugService.selectByPrimaryKey(jsonDrug.getLong("id"));
			drug.setId(drugCate.getWareid());  
			drug.setDrug_code(drugCate.getBarcode());
			drug.setDrug_name(jsonDrug.getString("warename"));
			drug.setQuantity(jsonDrug.getString("quantity"));
			
			drugList.add(drug);
		}
		
		String jsonPack=JSON.toJSON(protocol).toString();  //生成需要发送的数据包		
		System.out.println("------发送到海典:--------"+jsonPack);
		return jsonPack;
	}
	
	/**
	 * @Description: 更新日志状态
	 * @param
	 *     @param logId  日志ID
	 *     @param status 0:初始状态; 1:成功；2：失败；3：连接失败
	 * @return 
	 *     void  
	 * @throws 
	 * @author Administrator
	 * @date 2018年2月1日-下午1:15:18
	 */
	private void updateLogStatus(long logId,int status){
		LogSendPresc logSendPresc=new LogSendPresc();
		logSendPresc.setId(logId);
		logSendPresc.setStatus(status);
		//logSendPresc.setMsg(jobj.getString("msg"));
		logSendPrescService.updateByPrimaryKeySelective(logSendPresc);	
	}
	

	public PrescMsg getMsg() {
		return msg;
	}

	public void setMsg(PrescMsg msg) {
		this.msg = msg;
	}


}