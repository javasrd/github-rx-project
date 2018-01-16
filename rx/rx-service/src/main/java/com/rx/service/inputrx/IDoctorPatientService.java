package com.rx.service.inputrx;

import com.rx.entity.DoctorPatient;
import com.rx.service.IBaseService;

/**
 * @ClassName: IDoctorPatientService
 * @Description: 医生-患者关系 服务
 * @author Administrator
 * @date 2018年1月16日-上午11:11:49
 * @version 1.0.0
 */
public interface IDoctorPatientService extends IBaseService<DoctorPatient, Long> {
	
	/**
	 * @Description: 增加医生-患者关系记录
	 * @param
	 *     @param doctor_id  医生id
	 *     @param patient_id 患者id
	 *     @return   
	 * @return 
	 *     long  插入记录ID
	 * @throws 
	 * @author Administrator
	 * @date 2018年1月16日-下午3:14:45
	 */
	public long addDoctorPatient(long doctor_id,long patient_id);
	
	
}
