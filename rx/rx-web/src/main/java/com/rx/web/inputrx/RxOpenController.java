package com.rx.web.inputrx;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
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
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rx.bean.inputrx.PrescMsg;
import com.rx.common.util.RequestResultUtil;
import com.rx.entity.Department;
import com.rx.entity.Diagnosis;
import com.rx.entity.DictDays;
import com.rx.entity.DictDoseUnit;
import com.rx.entity.DictMode;
import com.rx.entity.DictTimes;
import com.rx.entity.Dispensary;
import com.rx.entity.Doctor;
import com.rx.entity.Drug;
import com.rx.entity.Hospital;
import com.rx.entity.Patient;
import com.rx.service.inputrx.IDepartmentService;
import com.rx.service.inputrx.IDiagnosisService;
import com.rx.service.inputrx.IDictDaysService;
import com.rx.service.inputrx.IDictDoseUnitService;
import com.rx.service.inputrx.IDictModeService;
import com.rx.service.inputrx.IDictTimesService;
import com.rx.service.inputrx.IDirectionService;
import com.rx.service.inputrx.IDispensaryService;
import com.rx.service.inputrx.IDoctorPatientService;
import com.rx.service.inputrx.IDoctorService;
import com.rx.service.inputrx.IDrugService;
import com.rx.service.inputrx.IHospitalService;
import com.rx.service.inputrx.ILogReceivePatientService;
import com.rx.service.inputrx.ILogSendPrescService;
import com.rx.service.inputrx.IPatientService;
import com.rx.service.inputrx.IPrescDrugService;
import com.rx.service.inputrx.IPrescriptionService;
import com.rx.service.queuerx.DBThread;
import com.rx.service.queuerx.ThreadPoolManager;


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
	private Logger log = Logger.getLogger(DBThread.class);

	
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
	ILogSendPrescService logSendPrescService;  //发送处方日志服务
	@Autowired
	IDispensaryService dispensaryService;  //药房
	@Autowired
	ThreadPoolManager tpm;  //线程池
	@Autowired
	IHospitalService hospitalService; //医院名称
	@Autowired
	IDictDaysService dictDaysService; //疗程字典
	
	
	
	
	
	
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
	 * @Description: 用户输入药品码,查询相应的药目列表
	 * @param
	 *     @param abc 助词码
	 *     @param pageNum 页号
	 *     @param pageSize 页大小
	 *     @param model
	 *     @return   
	 * @return 
	 *     String  
	 * @throws 
	 * @author Administrator
	 * @date 2018年1月18日-下午3:58:51
	 */
	@RequestMapping(value = "/drug/category")
	public String drugTable(String abc,Integer pageNum, Integer pageSize,Model model) {
		
		System.out.println("助记码:"+abc);
		
		//默认的页大小及页号
		int DEFAULT_PAGE_SIZE=8;  //默认页大小
		int DEFAULT_PAGE_NUM=1;	  //默认页号
		
		
		if(pageNum==null || pageNum==0)
		{
			pageNum=DEFAULT_PAGE_NUM;
			pageSize=DEFAULT_PAGE_SIZE;
		}
		
		getDrugTable(abc,pageNum,pageSize,model);  //获取药品目录
		
		
		return RESPONSE_THYMELEAF + "drugcategory";
	}
	
	
	
	
	
	
	@RequestMapping(value = "/drug/doseunit")
	public String drugDoseUnitInput(String abc,Model model) {
		
		System.out.println("助记码-input panel:"+abc);
		
		getDoseUnit(abc,model);  //获取药品目录
		
		return RESPONSE_THYMELEAF + "dictdoseunit";
	}
	
	@RequestMapping(value = "/drug/times")
	public String drugTimes(String abc,Model model) {
		//System.out.println("助记码:"+abc);
		
		getDrugTimes(abc,model);  //获取药品目录
		
		return RESPONSE_THYMELEAF + "dicttimes";
	}
	
	private void getDrugTimes(String abc,Model model){
		List<DictTimes> timesList=dictTimesService.getTimesByAbc(abc);
		model.addAttribute("timesList", timesList);
	}
	
	@RequestMapping(value = "/drug/mode")
	public String drugMode(String abc,Model model) {
		
		//System.out.println("助记码:"+abc);
		
		getDrugMode(abc,model);  //获取药品目录
		
		return RESPONSE_THYMELEAF + "dictmode";
	}
	
	private void getDrugMode(String abc,Model model){
		List<DictMode> modeList=dictModeService.getModeByAbc(abc);
		model.addAttribute("modeList", modeList);		
	}
	
	@RequestMapping(value = "/drug/days")
	public String drugDays(String abc,Model model) {
		//System.out.println("助记码:"+abc);		
		getDrugDays(abc,model);  //获取药品目录
		return RESPONSE_THYMELEAF + "dictdays";
	}
	
	private void getDrugDays(String abc,Model model){
		List<DictDays> tempList=dictDaysService.getDaysByAbc(abc);
		model.addAttribute("daysList", tempList);
	}	
	
	//--------------------table---------------------------
	
	@RequestMapping(value = "/drug/doseunit_table")
	public String drugDoseUnit(String abc,Model model) {
		
		System.out.println("助记码-Table:"+abc);
		
		getDoseUnit(abc,model);  //获取药品目录
		
		return RESPONSE_THYMELEAF + "dictdoseunit_table";
	}
	
	@RequestMapping(value = "/drug/times_table")
	public String drugTimes_table(String abc,Model model) {
		//System.out.println("助记码:"+abc);
		
		getDrugTimes(abc,model);  //获取药品目录
		
		return RESPONSE_THYMELEAF + "dicttimes_table";
	}
	
	@RequestMapping(value = "/drug/mode_table")
	public String drugMode_table(String abc,Model model) {
		
		//System.out.println("助记码:"+abc);
		
		getDrugMode(abc,model);  //获取药品目录
		
		return RESPONSE_THYMELEAF + "dictmode_table";
	}
	
	
	@RequestMapping(value = "/drug/days_table")
	public String drugDays_table(String abc,Model model) {
		//System.out.println("助记码:"+abc);		
		getDrugDays(abc,model);  //获取药品目录
		return RESPONSE_THYMELEAF + "dictdays_table";
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
		//System.out.println("参数:"+jsonPresc);
		log.info("打印模板所接收参数:"+jsonPresc);
		
		long patient_id=0;
		long doctor_id=0;
		long department_id=0;
		BigDecimal sum=new BigDecimal(0);
		
		JSONObject parm=JSON.parseObject(jsonPresc);
		patient_id=parm.getLongValue("patientId");
		doctor_id=parm.getLongValue("doctorId");
		department_id=parm.getLongValue("departmentId");
		int type=parm.getInteger("type");
		String prescType="";
		if(type==1){
			prescType="【正方】";			
		}
		else{
			prescType="【底方】";
		}
		String prescNo=parm.getString("prescNo");
		
		//读取药房信息
		List<Dispensary> dispList=dispensaryService.selectAll();
		
			
		
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
			String subSumStr=format(subSum);
			drugMap.put("usermethod",jsonDrug.getString("dosage")+doseUnit+"   "+
						mode+"    "+
						times+ "   "+
						subSumStr+"元");			
			drugList.add(drugMap);
			
			sum=sum.add(subSum);
		}
		
		Patient patient=patientService.selectByPrimaryKey(patient_id);
		Doctor doctor=doctorService.selectByPrimaryKey(doctor_id);
		
		HashMap<String,String> doctorMap=new HashMap<String,String>();
		if(doctor!=null){
			department_id=doctor.getDepartmentId();
			doctorMap.put("name", doctor.getName());
		}
		Department department=departmentService.selectByPrimaryKey(department_id);
		List<Diagnosis> diagnosisList=diagnosisService.getDiagnosisByPatientAndDoctor(patient_id, doctor_id);
		List<Hospital> hospitalList=hospitalService.selectAll();  //读取医院名称配置
		
		
		model.addAttribute("prescType", prescType);
		model.addAttribute("prescNo",prescNo);
		model.addAttribute("dispensary", dispList.get(0));
		model.addAttribute("diagnosisDate", new Date());		
		model.addAttribute("patient", patient);
		model.addAttribute("doctor", doctorMap);
		model.addAttribute("department", department);
		model.addAttribute("diagnosisList", diagnosisList);
		model.addAttribute("drugList", drugList);
		model.addAttribute("hospital",hospitalList.get(0));
		model.addAttribute("sum", format(sum));
		
		
		return RESPONSE_THYMELEAF + "printtemplate";
	}
	
	@RequestMapping(value = "/presc/loadcss")
	public String loadCss() {
		//System.out.println("load css");
		return RESPONSE_THYMELEAF + "savetemplate";
	}
	
	
	
	
	/**
	 * @Description: 格式化成两位小数
	 * @param
	 *     @param value
	 *     @return   
	 * @return 
	 *     String  
	 * @throws 
	 * @author Administrator
	 * @date 2018年2月3日-上午11:27:12
	 */
	public  String format(BigDecimal value) {
		BigDecimal bd=value;
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		 return bd.toString();
	}
	
	 
	
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
			String days=jsonDrug.getString("days");		  //服药天数	
			String doseUnit=jsonDrug.getString("doseunit"); //服药剂量单位
			String times=jsonDrug.getString("drugtimes");  //给药次数
			long patientId=jsonDrug.getLongValue("patientid"); //患者ID
			long doctorId=jsonDrug.getLongValue("doctorid");   //医生ID			
					
			
			//(2)保存处方药品
			Drug drug=drugService.selectByPrimaryKey(drugId);  //读取药品信息
			long prescDrugId=prescDrugService.addPrescDrug(prescId, drug,quantity);
			//(3)保存处方指导
			directionService.addDirection(prescDrugId, mode, times, dosage, doseUnit,days);
			
			//(4)更新数据字典
			updateDict(mode, times, doseUnit,days);
			
		}
		
		//如果生成处方,则发送到海典ERP中.
		if(!presc_no.equals("")){
			//sendPrecriptionToDispensary(presc,presc_no,prescId);
			PrescMsg msg=new PrescMsg();
			msg.setPresc(presc);
			msg.setPrescId(prescId);
			msg.setPrescNo(presc_no);
			
			tpm.processOrders(msg);			
		}		
				
		//返回处方编号		
		Map<String, Object> result=RequestResultUtil.getResultAddSuccess();		
		result.put(RequestResultUtil.RESULT_MSG, presc_no);		
		return result;
	}
	
	/**
	 * 根据处方更新数据字典 
	 * @param mode
	 * @param times
	 * @param doseUnit
	 * @param days
	 */
	private void updateDict(String mode,String times,String doseUnit,String days){
		//疗程
		List<Map<String,Object>> daysList=dictDaysService.getDaysByDaysName(days);
		if(daysList.size()==0){
			DictDays daysObj=new DictDays();
			daysObj.setName(days);
			dictDaysService.insertSelective(daysObj);
		}
		
		//频次
		List<Map<String,Object>> timesList=dictTimesService.getTimesByTimesName(times);
		if(timesList.size()==0){
			DictTimes timesObj=new DictTimes();
			timesObj.setName(times);
			dictTimesService.insertSelective(timesObj);
		}
		
		//用法
		List<Map<String,Object>> modeList=dictModeService.getModeByModeName(mode);
		if(modeList.size()==0){
			DictMode modeObj=new DictMode();
			modeObj.setName(mode);
			dictModeService.insertSelective(modeObj);
		}
		
		//剂量单位
		List<Map<String,Object>> doseUnitList=dictDoseUnitService.getDoseUnitByDaysName(doseUnit);
		if(doseUnitList.size()==0){
			DictDoseUnit doseUnitObj=new DictDoseUnit();
			doseUnitObj.setName(doseUnit);
			dictDoseUnitService.insertSelective(doseUnitObj);
		}
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
	private void getDrugTable(String abc,Integer pageNum, Integer pageSize,Model model){
		
		
		//采用分布的方式查询药品目录.
		PageHelper.startPage(pageNum, pageSize); // PageHelper 分页开始
		List<Map<String,Object>> drugList=drugService.getDrugByAbc(abc);  //查询
		// (使用了拦截器或是AOP进行查询的再次处理)
		PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(drugList); //分页结束
		
		model.addAttribute("abc",abc);
		model.addAttribute("pageInfo",pageInfo);
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
