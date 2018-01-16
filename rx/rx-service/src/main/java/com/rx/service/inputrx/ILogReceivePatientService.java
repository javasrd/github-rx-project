package com.rx.service.inputrx;

import java.util.List;

import com.rx.entity.LogReceivePatient;
import com.rx.entity.User;
import com.rx.service.IBaseService;

/**
 * @ClassName: ILogReceivePatientService
 * @Description: 数据接收日志-接口.(医,患,诊断信息日志)
 * @author Administrator
 * @date 2018年1月16日-上午9:39:30
 * @version 1.0.0
 */
public interface ILogReceivePatientService extends IBaseService<LogReceivePatient, Long> {
	
	/**
	 * @Description: Add数据接收日志
	 * @param
	 *     @param url  请求方地址
	 *     @param data 请求数据包
	 *     @return   
	 * @return 
	 *     long  插入记录主键
	 * @throws 
	 * @author Administrator
	 * @date 2018年1月16日-上午9:54:55
	 */
	public long addLog(String url,String data);
	
	
}
