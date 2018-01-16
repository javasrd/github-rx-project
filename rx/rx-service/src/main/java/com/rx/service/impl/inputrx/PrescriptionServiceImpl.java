package com.rx.service.impl.inputrx;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.rx.dao.PrescriptionMapper;
import com.rx.entity.Prescription;
import com.rx.service.impl.AbstractBaseService;
import com.rx.service.inputrx.IPrescriptionService;


@Service
public class PrescriptionServiceImpl extends AbstractBaseService<Prescription, Long> implements IPrescriptionService {

	PrescriptionMapper prescriptionMapper;
	
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
	public void setPrescriptionMapper(PrescriptionMapper prescriptionMapper) {
		this.prescriptionMapper=prescriptionMapper;
		this.setMapper(prescriptionMapper);
	}


	@Override
	public long addPrescription(long patient_id, long doctor_id, String rx_no) {
		Prescription rec=new Prescription();
		
		//设定对象属性
		rec.setCreatedTime(new Date());
		rec.setDoctorId(doctor_id);
		rec.setPatientId(patient_id);
		rec.setRxNo(rx_no);
		
		
		//保存
		int row=prescriptionMapper.insertSelective(rec);
		
		if(row>0)
			return rec.getId();
		else
			return 0;
	}

}
