package com.rx.web.inputrx;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rx.common.util.RequestResultUtil;
import com.rx.entity.Doctor;
import com.rx.entity.Drug;
import com.rx.entity.Template;
import com.rx.service.inputrx.IDepartmentService;
import com.rx.service.inputrx.IDoctorService;
import com.rx.service.inputrx.IPatientService;
import com.rx.service.inputrx.ITemplateDetailService;
import com.rx.service.inputrx.ITemplateService;

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
public class TemplateController {
	final String RESPONSE_THYMELEAF = "thymeleaf/inputrx/";
	final String RESPONSE_THYMELEAF_BACK = "thymeleaf/back/";
	final String RESPONSE_JSP = "jsps/";
	
	
		
	@Autowired
	IPatientService patientService;  	   //患者服务
	@Autowired
	IDepartmentService departmentService;  //科室服务	
	@Autowired
	IDoctorService doctorService;  	   		//医生服务
	@Autowired
	ITemplateService templateService;  		//模板服务
	@Autowired
	ITemplateDetailService templateDetailService;  		//模板详细服务
		
	/**
	 * 使用模板
	 * @return 用户触发发送数据视图(demo)
	 */
	@RequestMapping(value = "/template/use")	
	public String template_use() {
		return RESPONSE_THYMELEAF + "usetemplate";
	}
	
	/**
	 * 保存模板
	 * @return 保存模板视图
	 */
	@RequestMapping(value = "/template/save")
	@ResponseBody
	public Object template_save(@RequestBody String presc) {
		
		long templateId=0; 	//模板id
		
		//(1)第一次解析
		JSONObject parm=JSON.parseObject(presc);
		//以下是解析结果测试
		long patient_id=parm.getLongValue("patientId");  //本地patient id
		long doctor_id=parm.getLongValue("doctorId");	//本地doctor id
		long department_id=parm.getLongValue("departmentId");  //本地 depatment id
		String templateName=parm.getString("templateName");  //获取模板名称
		
		//（1）第二次解析,处方中药品		
		JSONArray drugArray=JSON.parseArray(parm.getString("prescDrugs"));
		//(1)保存模板
		//TODO 此处应该加入事务处理
		if(drugArray.size()>0){
			templateId=addTemplate(templateName,doctor_id);
		}
		//(2)保存模板中药品及用药指导
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
			
			//(2)保存模板药品
			
		}
		
		//返回处方编号		
		Map<String, Object> result=RequestResultUtil.getResultAddSuccess();		
		result.put(RequestResultUtil.RESULT_MSG, "已经成功加入模板");		
		return result;
	}
	
	/**
	 * @Description: 增加模板
	 * @param
	 *     @param templateName  模板名称
	 *     @param doctorId 医生ID(本地ID)
	 *     @return   
	 * @return 
	 *     long  模板ID
	 * @throws 
	 * @author Administrator
	 * @date 2018年2月13日-下午10:34:36
	 */
	private long addTemplate(String templateName,long doctorId){
		Template template=new Template();
		Doctor doctor=doctorService.selectByPrimaryKey(doctorId);  //自数据库读取医生信息
		template.setName(templateName);
		template.setCreatedTime(new Date());
		template.setDoctorId(doctorId);
		template.setDoctorOldId(doctor.getOldId());
		int row= templateService.insertSelective(template);
		if(row>0)
			return template.getId();
		else
			return -1;
	}
	
	
	
	/**
	 * 编辑模板
	 * @return 编辑模板视图
	 */
	@RequestMapping(value = "/template/edit")	
	public String template_edit() {
		return RESPONSE_THYMELEAF + "edittemplate";
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
