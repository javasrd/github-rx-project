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
	
	/**
	 * @Description: 生成处方号
	 * @param
	 *     @return   处方号
	 * @return 
	 *     String    处方号 格式  O20180129XXXXX  O为前缀; 年月日(年4位,月2位,日2位); XXXXX: 5位序号
	 * @throws 
	 * @author Administrator
	 * @date 2018年1月29日-下午6:08:53
	 */
	public String createPrescriptionNo();
}
