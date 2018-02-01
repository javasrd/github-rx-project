package com.rx.service.impl.inputrx;

import org.springframework.stereotype.Service;

import com.rx.dao.HospitalMapper;
import com.rx.entity.Hospital;
import com.rx.service.impl.AbstractBaseService;
import com.rx.service.inputrx.IHospitalService;

@Service
public class HospitalServiceImpl extends AbstractBaseService<Hospital, Long> implements IHospitalService {

	HospitalMapper hospitalMapper;
	
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
	public void setHospitalMapper(HospitalMapper hospitalMapper) {
		this.hospitalMapper=hospitalMapper;
		this.setMapper(hospitalMapper);
	}

}
