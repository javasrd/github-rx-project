package com.rx.web.inputrx;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rx.bean.inputrx.RxAPI;
import com.rx.bean.inputrx.RxDepartment;
import com.rx.bean.inputrx.RxDisease;
import com.rx.bean.inputrx.RxDoctor;
import com.rx.bean.inputrx.RxDrug;
import com.rx.bean.inputrx.RxPatient;
import com.rx.bean.inputrx.RxProtocolConstant;
import com.rx.bean.inputrx.RxReqSendPrescription;
import com.rx.bean.inputrx.RxReqSendPrescriptionData;
import com.rx.common.util.HttpClientUtil;
import com.rx.common.util.RequestResultUtil;
import com.rx.entity.Department;
import com.rx.entity.Diagnosis;
import com.rx.entity.DictDoseUnit;
import com.rx.entity.DictMode;
import com.rx.entity.DictTimes;
import com.rx.entity.Doctor;
import com.rx.entity.Drug;
import com.rx.entity.Patient;
import com.rx.service.impl.inputrx.LogSendPrescServiceImpl;
import com.rx.service.inputrx.IDepartmentService;
import com.rx.service.inputrx.IDiagnosisService;
import com.rx.service.inputrx.IDictDoseUnitService;
import com.rx.service.inputrx.IDictModeService;
import com.rx.service.inputrx.IDictTimesService;
import com.rx.service.inputrx.IDirectionService;
import com.rx.service.inputrx.IDoctorPatientService;
import com.rx.service.inputrx.IDoctorService;
import com.rx.service.inputrx.IDrugService;
import com.rx.service.inputrx.ILogReceivePatientService;
import com.rx.service.inputrx.ILogSendPrescService;
import com.rx.service.inputrx.IPatientService;
import com.rx.service.inputrx.IPrescDrugService;
import com.rx.service.inputrx.IPrescriptionService;


/**
 * @ClassName: RxOpenController
 * @Description: 打开处方录入窗口控制器
 * @author Administrator
 * @date 2018年1月17日-下午4:17:47
 * @version 1.0.0
 */
@Controller
/*@RequestMapping("/")*/
public class RxOpenController {
	final String RESPONSE_THYMELEAF = "thymeleaf/inputrx/";
	final String RESPONSE_THYMELEAF_BACK = "thymeleaf/back/";
	final String RESPONSE_JSP = "jsps/";

	
	@Autowired
	ILogReceivePatientService logService;  //日志服务:医,患,诊断数据	
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
	@Autowired
	IDrugService drugService;  //药品服务
	@Autowired
	IDictModeService dictModeService;  //给药方式
	@Autowired
	IDictTimesService dictTimesService;  //给药次数
	@Autowired
	IDictDoseUnitService dictDoseUnitService;  //给药模式
	
	@Autowired
	IPrescriptionService prescriptionService; //处方服务		
	@Autowired
	IPrescDrugService prescDrugService;  //处方药品
	@Autowired
	IDirectionService directionService;  //用药指导
	@Autowired
	ILogSendPrescService logSendPrescService;
	
	
	
	
	
	/**
	 * @Description 
	 * @return 
	 */
	/**
	 * @Description: 打开医生处方录入界面,对外接口 
	 * @param
	 *     @param patientId 患者在东华系统中的ID
	 *     @param model 用来传送医,患,科室,诊断信息.
	 *     @return   开处方视图
	 * @return 
	 *     String  医生开处方视图(hospital.html)
	 * @throws 
	 * @author Administrator
	 * @date 2018年1月17日-下午4:17:38
	 */
	@RequestMapping(value = "/openwin")
	public String openWin(@RequestParam String patientId,Model model) {
		
		//System.out.println("patientId:"+patientId);
		
		getPatientDiagInfo(patientId,model);  //获取患者及诊断信息
		
		return RESPONSE_THYMELEAF + "hospital";
	}
	
	/**
	 * @Description: TODO
	 * @param
	 *     @param abc
	 *     @param model
	 *     @return   
	 * @return 
	 *     String  
	 * @throws 
	 * @author Administrator
	 * @date 2018年1月18日-下午3:58:51
	 */
	@RequestMapping(value = "/drug/category")
	public String drugTable(String abc,Model model) {
		
		System.out.println("助记码:"+abc);
		
		getDrugTable(abc,model);  //获取药品目录
		
		return RESPONSE_THYMELEAF + "drugcategory";
	}
	
	@RequestMapping(value = "/drug/mode")
	public String drugMode(String abc,Model model) {
		
		System.out.println("助记码:"+abc);
		
		getDrugMode(abc,model);  //获取药品目录
		
		return RESPONSE_THYMELEAF + "dictmode";
	}
	
	private void getDrugMode(String abc,Model model){
		List<DictMode> modeList=dictModeService.getModeByAbc(abc);
		model.addAttribute("modeList", modeList);		
	}
	
	
	@RequestMapping(value = "/drug/times")
	public String drugTimes(String abc,Model model) {
		
		System.out.println("助记码:"+abc);
		
		getDrugTimes(abc,model);  //获取药品目录
		
		return RESPONSE_THYMELEAF + "dicttimes";
	}
	
	private void getDrugTimes(String abc,Model model){
		List<DictTimes> timesList=dictTimesService.getTimesByAbc(abc);
		model.addAttribute("timesList", timesList);
	}
	
	
	@RequestMapping(value = "/drug/doseunit")
	public String drugDoseUnit(String abc,Model model) {
		
		System.out.println("助记码:"+abc);
		
		getDoseUnit(abc,model);  //获取药品目录
		
		return RESPONSE_THYMELEAF + "dictdoseunit";
	}
	
	private void getDoseUnit(String abc,Model model){
		List<DictDoseUnit> doseUnitList=dictDoseUnitService.getDoseUnitByAbc(abc);
		model.addAttribute("doseUnitList", doseUnitList);
	}
	
	/**
	 * @Description: 加载处方打印模板
	 * @param
	 *     @param jsonPresc 处方信息(注:此参数名称需要与发送请求的参数据名称相同.)
	 *     @param model	数据传递对象
	 *     @return   
	 * @return 
	 *     String  打印模板视图
	 * @throws 
	 * @author Administrator
	 * @date 2018年1月20日-下午10:44:29
	 */
	@RequestMapping(value = "/presc/printtemplate")
	public String loadPrintTemplate(String jsonPresc,Model model) {		
		System.out.println("参数:"+jsonPresc);
		
		long patient_id=0;
		long doctor_id=0;
		long department_id=0;
		BigDecimal sum=new BigDecimal(0);
		
		JSONObject parm=JSON.parseObject(jsonPresc);
		patient_id=parm.getLongValue("patientId");
		doctor_id=parm.getLongValue("doctorId");
		department_id=parm.getLongValue("departmentId");
		
		//（1）解析参数
		List<Map<String,String>> drugList= new ArrayList<Map<String,String>>();
		JSONArray drugArray=JSON.parseArray(parm.getString("prescDrugs"));		
		for(int i=0;i<drugArray.size();i++){
			JSONObject jsonDrug= drugArray.getJSONObject(i);
			
			//long drugId=jsonDrug.getLongValue("id");	//ID		
			//BigDecimal dosage=jsonDrug.getBigDecimal("dosage");	//每次剂量
			
			String mode=jsonDrug.getString("drugmode"); //给药方式
			//int quantity=jsonDrug.getIntValue("quantity");//数量
			//int days=jsonDrug.getIntValue("days");		  //服药天数	
			String doseUnit=jsonDrug.getString("doseunit"); //服药剂量单位
			String times=jsonDrug.getString("drugtimes");  //给药次数
			//long patientId=jsonDrug.getLongValue("patientid"); //患者ID
			//long doctorId=jsonDrug.getLongValue("doctorid");   //医生ID
			
			//doctor_id=doctorId;
			//patient_id=patientId;
			
			Map<String,String> drugMap=new HashMap<String,String>();  
			//药品名称
			drugMap.put("warename", jsonDrug.getString("warename")+
						"["+jsonDrug.getString("warespec")+ "]"+" X "+
						jsonDrug.getString("quantity")+"("+	jsonDrug.getString("wareunit")+")");
			//用法
			BigDecimal subSum=jsonDrug.getBigDecimal("quantity").multiply(jsonDrug.getBigDecimal("saleprice"));
			drugMap.put("usermethod",jsonDrug.getString("dosage")+doseUnit+"   "+
						mode+"    "+
						times+ "   "+
						jsonDrug.getBigDecimal("quantity").multiply(jsonDrug.getBigDecimal("saleprice"))						
					);			
			drugList.add(drugMap);
			
			sum=sum.add(subSum);
		}
		
		Patient patient=patientService.selectByPrimaryKey(patient_id);
		Doctor doctor=doctorService.selectByPrimaryKey(doctor_id);
		if(doctor!=null){
			department_id=doctor.getDepartmentId();
		}
		Department department=departmentService.selectByPrimaryKey(department_id);
		List<Diagnosis> diagnosisList=diagnosisService.getDiagnosisByPatientAndDoctor(patient_id, doctor_id);
		
		model.addAttribute("diagnosisDate", new Date());		
		model.addAttribute("patient", patient);
		model.addAttribute("doctor", doctor);
		model.addAttribute("department", department);
		model.addAttribute("diagnosisList", diagnosisList);
		model.addAttribute("drugList", drugList);
		model.addAttribute("sum", sum);
		
		return RESPONSE_THYMELEAF + "printtemplate";
	}
	
	@RequestMapping(value = "/presc/loadcss")
	public String loadCss() {
		System.out.println("load css");
		return RESPONSE_THYMELEAF + "loadcss";
	}
	
	//static int presc_no=0;
	
	/**
	 * @Description: 保存处方
	 * @param
	 *     @param presc 处方(参数的名称可以任意)
	 *     @return      处方编号
	 * @return 
	 *     String  		处方编号
	 * @throws 
	 * @author Administrator
	 * @date 2018年1月19日-下午2:11:49
	 */
	@RequestMapping(value = "prescription/save")
	@ResponseBody
	public Object savaPrescription(@RequestBody String presc){
		//System.out.println(presc);
		
		
		String presc_no="";  	//处方编号
		long prescId=0; 	//处方id
		
		//(1)第一次解析
		JSONObject parm=JSON.parseObject(presc);
		//以下是解析结果测试
		long patient_id=parm.getLongValue("patientId");
		long doctor_id=parm.getLongValue("doctorId");
		long department_id=parm.getLongValue("departmentId");
		
		//（1）第二次解析,处方中药品		
		JSONArray drugArray=JSON.parseArray(parm.getString("prescDrugs"));
		//(1)保存处方
		if(drugArray.size()>0){
			presc_no=prescriptionService.createPrescriptionNo();  //生成处方号
			prescId=prescriptionService.addPrescription(patient_id, doctor_id, presc_no);	//增加处方
		}
		//(2)保存处方中药品
		for(int i=0;i<drugArray.size();i++){
			JSONObject jsonDrug= drugArray.getJSONObject(i);
			
			long drugId=jsonDrug.getLongValue("id");	//ID		
			BigDecimal dosage=jsonDrug.getBigDecimal("dosage");	//每次剂量
			
			String mode=jsonDrug.getString("drugmode"); //给药方式
			int quantity=jsonDrug.getIntValue("quantity");//数量
			int days=jsonDrug.getIntValue("days");		  //服药天数	
			String doseUnit=jsonDrug.getString("doseunit"); //服药剂量单位
			String times=jsonDrug.getString("drugtimes");  //给药次数
			long patientId=jsonDrug.getLongValue("patientid"); //患者ID
			long doctorId=jsonDrug.getLongValue("doctorid");   //医生ID			
					
			
			//(2)保存处方药品
			Drug drug=drugService.selectByPrimaryKey(drugId);  //读取药品信息
			long prescDrugId=prescDrugService.addPrescDrug(prescId, drug,quantity);
			//(3)保存处方指导
			directionService.addDirection(prescDrugId, mode, times, dosage, doseUnit,days);
		}
		
		//如果生成处方,则发送到海典ERP中.
		if(!presc_no.equals("")){
			sendPrecriptionToDispensary(presc,presc_no,prescId);
			//sendPrecriptionToDispensary(presc,"",prescId);
		}		
				
		//返回处方编号		
		Map<String, Object> result=RequestResultUtil.getResultAddSuccess();		
		result.put(RequestResultUtil.RESULT_MSG, presc_no);		
		return result;
	}
	
	/**
	 * @Description: 将处方推送给药房.
	 * @param
	 *     @param presc json处方
	 *     @param presc_no 处方编号
	 * @return  响应包,成功或失败信息
	 *     void  
	 * @throws 
	 * @author Administrator
	 * @date 2018年1月29日-下午9:58:20
	 */
	private void sendPrecriptionToDispensary(String presc,String presc_no,long prescId){
		//System.out.println(presc);
		
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
		Map<String,String> parms=new HashMap<String,String>();
		parms.put("pack", jsonPack);
		
		//TODO (1)将数据包包记录日志.
		//TODO (1)发送数据的状态常量需要定义; (2)将请求置于线程中进行处理.需要进一步的设计
		String url="http://localhost:8080/rx-web/prescapi";
		//String url="http://222.222.66.25:8093/nmi";		
		long logId=logSendPrescService.addLog(url, jsonPack, 1);  //记录日志,将日志的ID号传送给线程处理.
		
		//(2)向其它的服务器发送请求.
		
		//String result=HttpClientUtil.doPost(url, parms);
		String result=HttpClientUtil.doPostJson(url, jsonPack);
		
		System.out.println("模拟发送处方------返回结果:"+result);
		
	}
	
	
	/**
	 * @Description: 通过助记码模糊查询药品目录
	 * @param
	 *     @param abc  助记码
	 *     @param model 数据传递对象  
	 * @return 
	 *     void  
	 * @throws 
	 * @author Administrator
	 * @date 2018年1月18日-下午3:53:41
	 */
	private void getDrugTable(String abc,Model model){		
		List<Map<String,Object>> drugList=drugService.getDrugByAbc(abc);
		model.addAttribute("drugCategory", drugList);
	}
	
	/**
	 * @Description: 根据原患者ID获取患者及诊断信息
	 * @param
	 *     @param patientId 患者在东华系统中的ID
	 *     @param model   	用来传送医,患,科室,诊断信息.
	 * @return 
	 *     void  
	 * @throws 
	 * @author Administrator
	 * @date 2018年1月17日-下午5:59:11
	 */
	private void getPatientDiagInfo(String patientId,Model model){
		//根据患者ID查询如下数据.		
		//(1)患者
		Patient patient=patientService.getPatientByOldId(patientId);
		
		//(2)医生
		List<Map<String, Object>> doctorList=doctorService.getDoctorByPatientId(patient.getId());		
		
		//(3)科室
		long departmentId=(long)doctorList.get(0).get("department_id");
		Department department=departmentService.selectByPrimaryKey(departmentId);
		
		//(4)诊断结果列表
		long patientLocalId=patient.getId();
		long doctorLocalId=(long)doctorList.get(0).get("id");
		List<Diagnosis> diagnosisList=diagnosisService.getDiagnosisByPatientAndDoctor(patientLocalId,doctorLocalId);
		/*StringBuilder diagnosisNames=new StringBuilder("");
		int count=0;
		for(Iterator<Diagnosis> itor=diagnosisList.iterator();itor.hasNext();){			
			Diagnosis disease=itor.next();
			count=count+1;
			diagnosisNames.append(count+":"+disease.getDisease()+";");			
		}*/
		
		//加入model
		model.addAttribute("patient", patient);
		model.addAttribute("doctor", doctorList.get(0));
		model.addAttribute("department",department);
		model.addAttribute("diagnosisList", diagnosisList);
		
		model.addAttribute("diagnosisDate", new Date());
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
