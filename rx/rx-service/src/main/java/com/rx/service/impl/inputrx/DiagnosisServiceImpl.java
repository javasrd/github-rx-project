package com.rx.service.impl.inputrx;

import org.springframework.stereotype.Service;

import com.rx.dao.DiagnosisMapper;
import com.rx.entity.Diagnosis;
import com.rx.service.impl.AbstractBaseService;
import com.rx.service.inputrx.IDiagnosisService;

@Service
public class DiagnosisServiceImpl extends AbstractBaseService<Diagnosis, Long> implements IDiagnosisService {

	DiagnosisMapper diagnosisMapper;
	
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
	public void setDiagnosisMapper(DiagnosisMapper diagnosisMapper) {
		this.diagnosisMapper=diagnosisMapper;
		this.setMapper(diagnosisMapper);
	}

	@Override
	public long addDiagnosis(String old_id,long doctor_id,long patient_id,String disease) {
		Diagnosis rec=new Diagnosis();
		
		//设定对象属性
		rec.setOldId(old_id);
		rec.setDoctorId(doctor_id);
		rec.setPatientId(patient_id);
		rec.setDisease(disease);
		
		//保存
		int row=diagnosisMapper.insertSelective(rec);
		
		if(row>0)
			return rec.getId();
		else
			return 0;		
	}

}
