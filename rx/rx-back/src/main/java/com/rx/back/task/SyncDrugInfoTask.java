package com.rx.back.task;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.rx.back.commons.SyncDrugInfoUtil;
import com.rx.common.util.RequestResultUtil;
import com.rx.entity.Drug;
import com.rx.entity.LogSyncDrug;
import com.rx.service.back.IDrugService;
import com.rx.service.back.ILogSyncDrugService;

/** 
 * 定时任务
 * 		同步药品信息 
 */
public class SyncDrugInfoTask {
	
	private final static Logger log = Logger.getLogger(SyncDrugInfoUtil.class);
	
	@Resource(name = "drugServiceBean")
	private IDrugService drugService;
	
	@Resource(name = "logSyncDrugServiceBean")
	private ILogSyncDrugService logSyncDrugService;
	
	public void sync(){
		System.out.println("================================ 定时任务 同步药品信息 开始 ================================");
		try {
			Map<String, Object> resMap = SyncDrugInfoUtil.processSyncDrug();
			String result_code = resMap.get(RequestResultUtil.RESULT_CODE).toString();
			String logJSON = resMap.get(RequestResultUtil.RESULT_LOG).toString();
			System.out.println("logJSON:"+logJSON);
			if(result_code.equals(RequestResultUtil.RESULT_CODE_SUCCESS)){
				String drugInfoJSON = resMap.get(RequestResultUtil.RESULT_DATA).toString();
				System.out.println("drugInfoJSON:"+drugInfoJSON);
				while(true){
					try {//解析JSON并保存内容
						if(StringUtils.isNotBlank(drugInfoJSON)){
							List<Drug> drugList = JSONArray.parseArray(drugInfoJSON, Drug.class);
							if(drugList!=null && drugList.size()>0){
								int rows = drugService.insertListSelective(drugList);
								if (rows > 0) {
									System.out.println("保存数据库成功");
									LogSyncDrug log = JSON.parseObject(logJSON, LogSyncDrug.class);
									logSyncDrugService.insertSelective(log);
									break;
								} else {
									System.out.println("保存数据库异常，正在重新保存。。。");
									continue;
								}
							}else{
								//TODO 解析JSON为空
								log.error("解析JSON为空");
							}
						}else{
							//TODO 文件内容为空
							log.error("文件内容为空");
						}
					} catch (Exception e) {
						e.printStackTrace();
						// TODO: handle exception
						log.error("解析JSON并保存内容异常");
						continue;
					}
					break;
				}
				
			}else{
				
				LogSyncDrug log = JSON.parseObject(logJSON, LogSyncDrug.class);
				logSyncDrugService.insertSelective(log);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("================================ 定时任务 同步药品信息 结束 ================================");
	}
	
}
