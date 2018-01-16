package com.rx.service.inputrx;

import com.rx.entity.LogSendPresc;
import com.rx.service.IBaseService;

/**
 * @ClassName: ILogSendPrescService
 * @Description: 发送的处方药品日志
 * @author Administrator
 * @date 2018年1月16日-下午4:21:19
 * @version 1.0.0
 */
public interface ILogSendPrescService extends IBaseService<LogSendPresc, Long> {
	
	/**
	 * @Description: Add处方药品发送日志
	 * @param
	 *     @param url  接收方地址
	 *     @param data 药品数据包
	 *     @param status 发送状态。1:成功；2：失败；3：连接失败
	 *     @return   
	 * @return 
	 *     long  插入记录主键
	 * @throws 
	 * @author Administrator
	 * @date 2018年1月16日-上午9:54:55
	 */
	public long addLog(String url,String data,int status);
	
}
