package com.rx.web.inputrx;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rx.entity.Department;
import com.rx.entity.Diagnosis;
import com.rx.entity.DictDoseUnit;
import com.rx.entity.DictMode;
import com.rx.entity.DictTimes;
import com.rx.entity.Drug;
import com.rx.entity.Patient;
import com.rx.service.inputrx.IDepartmentService;
import com.rx.service.inputrx.IDiagnosisService;
import com.rx.service.inputrx.IDictDoseUnitService;
import com.rx.service.inputrx.IDictModeService;
import com.rx.service.inputrx.IDictTimesService;
import com.rx.service.inputrx.IDoctorPatientService;
import com.rx.service.inputrx.IDoctorService;
import com.rx.service.inputrx.IDrugService;
import com.rx.service.inputrx.ILogReceivePatientService;
import com.rx.service.inputrx.IPatientService;


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
	 * @Description: 保存处方
	 * @param
	 *     @param presc 处方
	 *     @return      处方编号
	 * @return 
	 *     String  		处方编号
	 * @throws 
	 * @author Administrator
	 * @date 2018年1月19日-下午2:11:49
	 */
	@RequestMapping(value = "prescription/save")
	@ResponseBody
	public String savaPrescription(@RequestBody String presc){
		System.out.println(presc);
		return "presc_no";
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
		List<Drug> drugList=drugService.getDrugByAbc(abc);
		model.addAttribute("drugList", drugList);
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
