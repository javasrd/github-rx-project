package com.rx.service.impl.inputrx;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.rx.dao.LogSendPrescMapper;
import com.rx.dao.LogSyncDrugMapper;
import com.rx.entity.LogSendPresc;
import com.rx.entity.LogSyncDrug;
import com.rx.service.impl.AbstractBaseService;
import com.rx.service.inputrx.ILogSyncDrugService;

@Service
public class LogSyncDrugServiceImpl extends AbstractBaseService<LogSyncDrug, Long> implements ILogSyncDrugService {

	LogSyncDrugMapper logSyncDrugMapper;
	
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
	public void setLogSyncDrugMapper(LogSyncDrugMapper logSyncDrugMapper) {
		this.logSyncDrugMapper=logSyncDrugMapper;
		this.setMapper(logSyncDrugMapper);
	}

	@Override
	public long addLog(String url, int status, String errorMsg) {
		LogSyncDrug rec=new LogSyncDrug();
		
		//设定对象属性		
		rec.setCreatedDate(new Date());
		rec.setUrl(url);
		rec.setErrormsg(errorMsg);		
		rec.setStatus(status);  
		
		
		//save record
		int row=logSyncDrugMapper.insertSelective(rec);
		
		if(row>0)
			return rec.getId();
		else
			return 0;
	}

}
