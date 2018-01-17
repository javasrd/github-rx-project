package com.rx.service.impl.inputrx;

import org.springframework.stereotype.Service;

import com.rx.dao.DoctorPatientMapper;
import com.rx.entity.Doctor;
import com.rx.entity.DoctorPatient;
import com.rx.service.impl.AbstractBaseService;
import com.rx.service.inputrx.IDoctorPatientService;

@Service
public class DoctorPatientServiceImpl extends AbstractBaseService<DoctorPatient, Long> implements IDoctorPatientService {

	DoctorPatientMapper doctorPatientMapper;
	
	/**
	 * @Description: set方式注入mapper
	 * @param	
	 *     @param mapper   
	 * @return 
	 *     void  
	 * @throws 
	 * @author Administrator
	 * @date 2018年1月16日-上午10:05:15
	 */
	public void setDoctorPatientMapper(DoctorPatientMapper doctorPatientMapper) {
		this.doctorPatientMapper=doctorPatientMapper;
		this.setMapper(doctorPatientMapper);
	}

	@Override
	public long addDoctorPatient(long doctor_id, long patient_id) {
		
		//查询关系是否已经存在
		DoctorPatient searchRec=new DoctorPatient();
		searchRec.setDoctorId(doctor_id);
		searchRec.setPatientId(patient_id);
		
		DoctorPatient resultRec=doctorPatientMapper.selectOne(searchRec);
		
		if(resultRec!=null){  //如果已经存在
			return resultRec.getId();
		}
		else{  //不存在,插入新的记录
			
			DoctorPatient rec=new DoctorPatient();
			
			//设定对象属性
			rec.setDoctorId(doctor_id);
			rec.setPatientId(patient_id);
			
			//保存
			int row=doctorPatientMapper.insertSelective(rec);
			
			if(row>0)
				return rec.getId();
			else
				return 0;			
		}
		
	}
	
	
}
