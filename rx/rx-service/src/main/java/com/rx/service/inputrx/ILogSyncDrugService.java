package com.rx.service.inputrx;

import com.rx.entity.LogSyncDrug;
import com.rx.service.IBaseService;

/**
 * @ClassName: ILogSendPrescService
 * @Description: 发送的处方药品日志
 * @author Administrator
 * @date 2018年1月16日-下午4:21:19
 * @version 1.0.0
 */
/**
 * @ClassName: ILogSyncDrugService
 * @Description: 同步药品库目录日志
 * @author Administrator
 * @date 2018年1月16日-下午4:33:49
 * @version 1.0.0
 */
public interface ILogSyncDrugService extends IBaseService<LogSyncDrug, Long> {
	
	/**
	 * @Description: 同步请求日志
	 * @param
	 *     @param url	请求接收方URL
	 *     @param status 请求处理状态
	 *     @param errorMsg 错误描述信息
	 *     @return   
	 * @return 
	 *     long  插入记录主键
	 * @throws 
	 * @author Administrator
	 * @date 2018年1月16日-下午4:35:30
	 */
	public long addLog(String url,int status,String errorMsg);
	
}
