package com.rx.service.impl.inputrx;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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


	@Override
	public String createPrescriptionNo() {
		final String CONTRACT_PREFIX="O";  //处方前缀
		final int SERIAL_LENGTH=5;  //序号格式化后长度
		//获取当前时间,生成查询条件
		Date currentTime=new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String dateString = formatter.format(currentTime);		
		String cond=CONTRACT_PREFIX+dateString; 
		
		String prescNo="";  //新生成的处方号
		
		//获取处方中指定日期最大处方号
		String maxContractNo=prescriptionMapper.getMaxPrescriptionNo(cond);
		if(StringUtils.isNotBlank(maxContractNo)){			
			String oldSerialStr=maxContractNo.substring(
									maxContractNo.length()-SERIAL_LENGTH, 
									maxContractNo.length());
			int newSerial=Integer.parseInt(oldSerialStr)+1;	  //新的序列号		
			
			String newSerialStr = String.format("%0"+SERIAL_LENGTH+"d", newSerial);
			prescNo=cond+ newSerialStr; 
		}
		else{
			prescNo=cond+"00001";
		}
		
		return prescNo;		
	}

	@Override
	public List<Map<String, Object>> getPrescInfoByCondition(Long doctorId, String keyword, String startTime, String endTime) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("doctorId", doctorId);
		condition.put("keyword", keyword);
		condition.put("startTime", startTime);
		condition.put("endTime", endTime);
		return prescriptionMapper.getPrescInfoByCondition(condition);
	}

}
