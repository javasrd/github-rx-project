package com.rx.service.impl.inputrx;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rx.bean.UserStatusType;
import com.rx.dao.LogReceivePatientMapper;
import com.rx.dao.UserMapper;
import com.rx.entity.LogReceivePatient;
import com.rx.entity.User;
import com.rx.service.impl.AbstractBaseService;
import com.rx.service.inputrx.ILogReceivePatientService;

import tk.mybatis.mapper.entity.Example;

@Service
public class LogReceivePatientServiceImpl extends AbstractBaseService<LogReceivePatient, Long> implements ILogReceivePatientService {

	LogReceivePatientMapper logReceivePatientMapper;
	
	/**
	 * @Description: set方式注入
	 * @param
	 *     @param logReceivePatientMapper   
	 * @return 
	 *     void  
	 * @throws 
	 * @author Administrator
	 * @date 2018年1月16日-上午10:05:15
	 */
	public void setLogReceivePatientMapper(LogReceivePatientMapper logReceivePatientMapper) {
		this.logReceivePatientMapper=logReceivePatientMapper;
		this.setMapper(logReceivePatientMapper);
	}

	@Override
	public long addLog(String url, String data) {
		
		LogReceivePatient rec=new LogReceivePatient();
		
		//设定对象属性
		rec.setCreatedTime(new Date());  
		rec.setUrl(url);		
		rec.setData(data);
		
		//保存
		int row=logReceivePatientMapper.insertSelective(rec);
		if(row>0)
			return rec.getId();
		else
			return 0;
	}

}
