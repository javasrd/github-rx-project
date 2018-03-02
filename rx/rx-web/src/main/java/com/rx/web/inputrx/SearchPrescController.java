package com.rx.web.inputrx;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rx.bean.PageBean;
import com.rx.entity.Department;
import com.rx.entity.Diagnosis;
import com.rx.entity.Doctor;
import com.rx.entity.Patient;
import com.rx.entity.Prescription;
import com.rx.service.inputrx.IDepartmentService;
import com.rx.service.inputrx.IDiagnosisService;
import com.rx.service.inputrx.IDoctorService;
import com.rx.service.inputrx.IPatientService;
import com.rx.service.inputrx.IPrescDrugService;
import com.rx.service.inputrx.IPrescriptionService;
import com.rx.service.queuerx.DBThread;


/**
 * @ClassName: SearchPrescController
 * @Description: 查询处方控制器
 * @author Administrator
 * @date 2018年3月1日-下午8:17:47
 * @version 1.0.0
 */
@Controller
@RequestMapping("/search")
public class SearchPrescController {
	
	final String RESPONSE_THYMELEAF = "thymeleaf/inputrx/";
	
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
	IPrescriptionService prescriptionService; //处方服务		
	@Autowired
	IPrescDrugService prescDrugService;  //处方药品
	
	/**
	 * 查询处方列表
	 * @param patientId
	 * @param model
	 * @param clickPageBtn
	 * @param pageBean
	 * @param pagehelperFun
	 * @return
	 */
	@RequestMapping(value = "/presc-item")
	public String searchPrescItem(Model model, Boolean clickPageBtn, PageBean pageBean, String pagehelperFun, Long doctorId, String keyword, String startTime, String endTime) {
		
		String viewName = RESPONSE_THYMELEAF + "search_presc";
		
		PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
		List<Map<String, Object>> prescList = prescriptionService.getPrescInfoByCondition(doctorId, keyword, startTime, endTime);
		PageInfo<Map<String, Object>> pagehelper = new PageInfo<Map<String, Object>>(prescList);
		model.addAttribute("pagehelper", pagehelper);
		
		if(clickPageBtn!=null && clickPageBtn){
			viewName = RESPONSE_THYMELEAF + "search_presc_table";
		}
		
		model.addAttribute("pagehelperFun", pagehelperFun);
		model.addAttribute("doctorId", doctorId);
		
		getDoctorInfo(doctorId, model);  //获取医生及医生所在科室的信息
		
		return viewName;
	}
	
	/**
	 * 查询处方详情
	 * @param patientId
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/presc-detail")
	public String openWin(Model model, Long id) {
		
		String viewName = RESPONSE_THYMELEAF + "search_presc_detail";
		
		Prescription presc = prescriptionService.selectByPrimaryKey(id);//处方信息
		
		Patient patient = patientService.selectByPrimaryKey(presc.getPatientId());//患者信息
		
		List<Diagnosis> diagnosisList = diagnosisService.getDiagnosisByPatientAndDoctor(presc.getPatientId(), presc.getDoctorId());//诊断信息
		
		List<Map<String, Object>> prescDrugList = prescDrugService.getByPrescId(presc.getId());//处方药品信息
		
		model.addAttribute("presc", presc);
		model.addAttribute("patient", patient);
		model.addAttribute("diagnosisList", diagnosisList);
		model.addAttribute("prescDrugList", prescDrugList);
		
		return viewName;
	}
	
	/**
	 * 查询医生信息（医生和医生所在科室信息）
	 * @param doctorId
	 * @param model
	 */
	private void getDoctorInfo(Long doctorId,Model model){
		
		//(1)医生
		Doctor doctor = doctorService.selectByPrimaryKey(doctorId);		
		
		//(2)科室
		long departmentId = doctor.getDepartmentId();
		Department department = departmentService.selectByPrimaryKey(departmentId);
		
		//加入model
		model.addAttribute("doctorId", doctor.getId());
		model.addAttribute("doctorName", doctor.getName());
		model.addAttribute("department",department);
		model.addAttribute("diagnosisDate", new Date());
	}

}
