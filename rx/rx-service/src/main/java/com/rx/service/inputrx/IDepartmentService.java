package com.rx.service.inputrx;

import com.rx.entity.Department;
import com.rx.service.IBaseService;

/**
 * @ClassName: IDepartmentService
 * @Description: 部门-服务接口
 * @author Administrator
 * @date 2018年1月16日-上午11:11:49
 * @version 1.0.0
 */
public interface IDepartmentService extends IBaseService<Department, Long> {
	
	/**
	 * @Description: 增加部门记录
	 * @param
	 *     @param old_id 部门在东华HIS中的ID
	 *     @param name 部门名称
	 *     @return    
	 * @return 
	 *     long   如果此部门已经存在,则返回在本地系统中ID,如果不存在,则返回插入记录的ID
	 * @throws 
	 * @author Administrator
	 * @date 2018年1月16日-下午2:31:11
	 */
	public long addDepartment(String old_id,String name);
	
	
}
