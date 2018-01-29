package com.rx.service.impl.inputrx;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.rx.dao.LogSendPrescMapper;
import com.rx.entity.LogSendPresc;
import com.rx.service.impl.AbstractBaseService;
import com.rx.service.inputrx.ILogSendPrescService;

@Service
public class LogSendPrescServiceImpl extends AbstractBaseService<LogSendPresc, Long> implements ILogSendPrescService {

	LogSendPrescMapper logSendPrescMapper;
	
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
	public void setLogSendPrescMapper(LogSendPrescMapper logSendPrescMapper) {
		this.logSendPrescMapper=logSendPrescMapper;
		this.setMapper(logSendPrescMapper);
	}


	@Override
	public long addLog(String url, String data,int status) {
		LogSendPresc rec=new LogSendPresc();
		
		//设定对象属性
		rec.setCreatedTime(new Date());
		rec.setUrl(url);
		rec.setData(data);
		rec.setStatus(status);  //默认为失败状态,发送成功后将状态置为成功.
		//rec.setPrescId(prescId);		
		
		
		//保存
		int row=logSendPrescMapper.insertSelective(rec);
		
		if(row>0)
			return rec.getId();
		else
			return 0;
	}

}
