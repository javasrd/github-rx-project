package com.rx.service.inputrx;

import com.rx.entity.Patient;
import com.rx.service.IBaseService;

/**
 * @ClassName: IPatientService
 * @Description: 患者_服务-接口
 * @author Administrator
 * @date 2018年1月16日-上午11:11:49
 * @version 1.0.0
 */
public interface IPatientService extends IBaseService<Patient, Long> {
	
	/**
	 * @Description: 增加患者记录
	 * @param
	 *     @param old_id  患者在东华HIS中的ID
	 *     @param name  患者姓名
	 *     @param sex   患者性别.  男,女
	 *     @param old	年龄
	 *     @param cr_no 病历号
	 *     @return   
	 * @return 
	 *     long  返回所插入的记录主键
	 * @throws 
	 * @author Administrator
	 * @date 2018年1月16日-上午11:18:18
	 */
	public long addPatient(String old_id,String name,String sex,String old,String cr_no);
	
	
}
