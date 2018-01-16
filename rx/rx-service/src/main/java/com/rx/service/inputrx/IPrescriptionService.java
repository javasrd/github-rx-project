package com.rx.service.inputrx;

import com.rx.entity.Prescription;
import com.rx.service.IBaseService;

/**
 * @ClassName: IPrescriptionService
 * @Description: 处方 接口
 * @author Administrator
 * @date 2018年1月16日-上午11:11:49
 * @version 1.0.0
 */
public interface IPrescriptionService extends IBaseService<Prescription, Long> {
	
	/**
	 * @Description: 增加处方
	 * @param
	 *     @param patient_id  	患者ID
	 *     @param doctor_id		医生ID
	 *     @param rx_no			处方号
	 *     @return   
	 * @return 
	 *     long  新增记录ID
	 * @throws 
	 * @author Administrator
	 * @date 2018年1月16日-下午3:28:01
	 */
	public long addPrescription(long patient_id,long doctor_id,String rx_no);
	
	
}
