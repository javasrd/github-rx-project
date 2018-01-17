package com.rx.service.impl.inputrx;

import org.springframework.stereotype.Service;

import com.rx.dao.PatientMapper;
import com.rx.entity.Patient;
import com.rx.service.impl.AbstractBaseService;
import com.rx.service.inputrx.IPatientService;

@Service
public class PatientServiceImpl extends AbstractBaseService<Patient, Long> implements IPatientService {

	PatientMapper patientMapper;
	
	/**
	 * @Description: set方式注入
	 * @param
	 *     @param patientMapper   
	 * @return 
	 *     void  
	 * @throws 
	 * @author Administrator
	 * @date 2018年1月16日-上午10:05:15
	 */
	public void setPatientMapper(PatientMapper patientMapper) {
		this.patientMapper=patientMapper;
		this.setMapper(patientMapper);
	}

	@Override
	public long addPatient(String old_id, String name, String sex, String old, String cr_no) {
		
		//查询此患者是否已经存在
		Patient searchRec=new Patient();
		searchRec.setOldId(old_id);	//采用原来的ID号进行查询
		
		Patient patient=patientMapper.selectOne(searchRec);
		if(patient!=null){   //患者已经存在
			return patient.getId(); 
		}
		else{  //新增患者
			Patient rec=new Patient();
			//设定对象属性
			rec.setOldId(old_id);
			rec.setName(name);
			if(sex.equals("男"))
				rec.setSex((byte)1);
			else
				rec.setSex((byte)2);
			rec.setOld(Integer.parseInt(old));
			rec.setCrNo(cr_no);
			
			//保存
			int row=patientMapper.insertSelective(rec);
			if(row>0)
				return rec.getId();
			else
				return 0;			
		}
				
	}

}
