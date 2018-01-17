package com.rx.service.inputrx;

import java.util.List;
import java.util.Map;

import com.rx.entity.Doctor;
import com.rx.service.IBaseService;

/**
 * @ClassName: IDoctorService
 * @Description: 医生-服务接口
 * @author Administrator
 * @date 2018年1月16日-上午11:11:49
 * @version 1.0.0
 */
public interface IDoctorService extends IBaseService<Doctor, Long> {
	
	/**
	 * @Description: 增加医生记录
	 * @param
	 *     @param old_id 医生在东华HIS中的ID
	 *     @param name  姓名
	 *     @param department_id  医生所在科室ID(此ID为在非医保系统中depatment中的本地ID)
	 *     @return   
	 * @return 
	 *     long  
	 * @throws 
	 * @author Administrator
	 * @date 2018年1月16日-上午11:37:42
	 */
	public long addDoctor(String old_id,String name,long department_id);
	
	/**
	 * @Description: 根据患者的本地ID查询医生对象
	 * @param
	 *     @param patient_id  患者本地ID
	 *     @return   医生信息(map格式)
	 * @return 
	 *     Doctor  如果查询到,则返回医生对象;否则返回null
	 * @throws 
	 * @author Administrator
	 * @date 2018年1月17日-下午4:36:57
	 */	
	public List<Map<String,Object>> getDoctorByPatientId(long patient_id);
	
}
