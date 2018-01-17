package com.rx.service.inputrx;

import java.util.List;

import com.rx.entity.Diagnosis;
import com.rx.service.IBaseService;

/**
 * @ClassName: IDiagnosisService
 * @Description: 诊断-服务接口
 * @author Administrator
 * @date 2018年1月16日-上午11:11:49
 * @version 1.0.0
 */
public interface IDiagnosisService extends IBaseService<Diagnosis, Long> {
	
	
	/**
	 * @Description: 增加诊断记录
	 * @param
	 *     @param old_id  在东华系统中的ID
	 *     @param doctor_id  医生ID(本系统中)
	 *     @param patient_id 患者ID(本系统中)
	 *     @param disease	  疾病名称
	 *     @return   
	 * @return 
	 *     long  返回插入记录的ID
	 * @throws 
	 * @author Administrator
	 * @date 2018年1月16日-下午3:00:13
	 */
	public long addDiagnosis(String old_id,long doctor_id,long patient_id,String disease);
	
	/**
	 * @Description: 以患者ID及医生ID查询诊断结果列表
	 * @param
	 *     @param patient_id
	 *     @param doctor_id
	 *     @return   
	 * @return 
	 *     long  
	 * @throws 
	 * @author Administrator
	 * @date 2018年1月17日-下午5:45:52
	 */
	public List<Diagnosis> getDiagnosisByPatientAndDoctor(long patient_id,long doctor_id);
}
