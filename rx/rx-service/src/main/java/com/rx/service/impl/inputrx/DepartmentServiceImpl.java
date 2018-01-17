package com.rx.service.impl.inputrx;

import org.springframework.stereotype.Service;

import com.rx.dao.DepartmentMapper;
import com.rx.entity.Department;
import com.rx.service.impl.AbstractBaseService;
import com.rx.service.inputrx.IDepartmentService;

@Service
public class DepartmentServiceImpl extends AbstractBaseService<Department, Long> implements IDepartmentService {

	DepartmentMapper departmentMapper; 
	
	/**
	 * @Description: set方式注入
	 * @param
	 *     @param departmentMapper   
	 * @return 
	 *     void  
	 * @throws 
	 * @author Administrator
	 * @date 2018年1月16日-上午10:05:15
	 */
	public void setDepartmentMapper(DepartmentMapper departmentMapper) {
		this.departmentMapper=departmentMapper;
		this.setMapper(departmentMapper);
	}

	@Override
	public long addDepartment(String old_id, String name) {
		
		//(1)查询部门是否已经存在.
		Department searchRec=new Department();		
		searchRec.setOldId(old_id);		
		
		//查询此部门是否已经存在		
		Department depart=departmentMapper.selectOne(searchRec);
		
		//返回插入记录的ID
		long returnId=0;  //返回的部门id-在本地系统中的id
		if(depart!=null)  //如果存在
			returnId= depart.getId();
		else{  //如果不存在,先插入记录,而后返回id
			
			Department rec=new Department();
			rec.setOldId(old_id);
			rec.setName(name);
			
			int row=departmentMapper.insertSelective(rec);  
			if(row>0){
				returnId=rec.getId();
			}
		}
		
		return returnId;
					
	}

}
