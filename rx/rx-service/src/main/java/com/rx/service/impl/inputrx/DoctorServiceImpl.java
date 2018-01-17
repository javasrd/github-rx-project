package com.rx.service.impl.inputrx;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rx.dao.DoctorMapper;
import com.rx.entity.Doctor;
import com.rx.service.impl.AbstractBaseService;
import com.rx.service.inputrx.IDoctorService;

@Service
public class DoctorServiceImpl extends AbstractBaseService<Doctor, Long> implements IDoctorService {

	DoctorMapper doctorMapper;
	
	/**
	 * @Description: set方式注入
	 * @param
	 *     @param mapper   
	 * @return 
	 *     void  
	 * @throws 
	 * @author Administrator
	 * @date 2018年1月16日-上午10:05:15
	 */
	public void setDoctorMapper(DoctorMapper doctorMapper) {
		this.doctorMapper=doctorMapper;
		this.setMapper(doctorMapper);
	}

	@Override
	public long addDoctor(String old_id, String name, long department_id) {
		
		//(1)查询是否已经存在
		Doctor searchRec=new Doctor();
		searchRec.setOldId(old_id);
		
		Doctor resultRec=doctorMapper.selectOne(searchRec);
		if(resultRec!=null){  //如果已经存在
			return resultRec.getId();
		}
		else{  //不存在,插入新的记录
			Doctor rec=new Doctor();		
			//设定对象属性
			rec.setOldId(old_id);
			rec.setName(name);
			rec.setDepartmentId(department_id);
			
			//保存
			int row=doctorMapper.insertSelective(rec);
			
			if(row>0)
				return rec.getId();
			else
				return 0;			
		}					
	}

	@Override
	public List<Map<String,Object>> getDoctorByPatientId(long patient_id) {
		
		return doctorMapper.getDoctorByPatientId(patient_id);
	}
	
	

}
