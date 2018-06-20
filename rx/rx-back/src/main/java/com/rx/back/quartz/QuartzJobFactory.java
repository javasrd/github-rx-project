package com.rx.back.quartz;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.rx.bean.SyncDrugLogStatus;
import com.rx.common.util.DictCommonCodeUtil;
import com.rx.common.util.RequestResultUtil;
import com.rx.entity.Drug;
import com.rx.entity.LogSyncDrug;
import com.rx.entity.ScheduleJob;
import com.rx.service.back.IDictCommonService;
import com.rx.service.back.IDrugService;
import com.rx.service.back.ILogSyncDrugService;
import com.rx.service.commons.QuartzJobStaticConstants;
import com.rx.service.commons.SyncDrugInfoUtil;

import tk.mybatis.mapper.entity.Example;

/**
 * 定时任务运行工厂
 * 
 * @author srd
 *
 */
public class QuartzJobFactory implements Job {

	private final Logger logger = Logger.getLogger(getClass());
	
	@Resource(name="drugServiceBean")
	private IDrugService drugService;

	@Resource(name = "logSyncDrugServiceBean")
	private ILogSyncDrugService logSyncDrugService;
	
	@Resource(name="dictCommonServiceBean")
	private IDictCommonService dictCommonService;
	
	/**
	 * 重写任务执行方法
	 * 
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		try {
			ScheduleJob job = (ScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
			logger.info("任务对象："+job);
			if(job==null){
				logger.info("任务对象为空");
			}else{
				logger.info("任务成功运行　任务分组和名称："+job.getJobGroup()+"【"+job.getJobName()+"】 任务执行时间："+job.getCronExpressionName());
				// 根据name 与 group组成的唯一标识来判别该执行何种操作……
				if(job.getJobGroup().equalsIgnoreCase(QuartzJobStaticConstants.QUARTZ_JOB_GROUP_NAME) && job.getJobName().equalsIgnoreCase(QuartzJobStaticConstants.QUARTZ_JOB_NAME_EVERY_MINUTE)){
					//每分钟执行
					this.everyMinuteTask();
				}else if(job.getJobGroup().equalsIgnoreCase(QuartzJobStaticConstants.QUARTZ_JOB_GROUP_NAME) && job.getJobName().equalsIgnoreCase(QuartzJobStaticConstants.QUARTZ_JOB_NAME_EVERY_DAY_TIME_ZERO)){
					//每天（00:00点）执行
					this.everyDayTask();
				}else{
					logger.info("未定义定时任务作业");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("执行Quartz异常...", e);
		}
		
	}
	
	/**
	 * 每天（00:00点）定时任务
	 */
	private void everyDayTask(){
		logger.info("================================ 每天（00：00）定时任务 同步药品信息 开始 ================================");
		
		this.task();
		
		logger.info("================================ 每天（00：00）定时任务 同步药品信息 结束 ================================");
	}
	
	/**
	 * 每分钟定时任务
	 */
	private void everyMinuteTask(){
		logger.info("================================ 每分钟定时任务 同步药品信息 开始 ================================");
		
		Example example = new Example(LogSyncDrug.class);
		example.setOrderByClause("created_date DESC, id DESC");
		List<LogSyncDrug> logList = logSyncDrugService.selectByExample(example);
		//if(logList!=null && logList.size()>0){
		if(logList!=null && !logList.isEmpty()){
			LogSyncDrug log = logList.get(0);
			if(log==null || log.getStatus()==null || log.getStatus()!=SyncDrugLogStatus.SUCCESS.getIndex()){
				this.task();
			}else{
				logger.info("未发现同步药品信息失败的日志，不需要再次同步。");
			}
		}else{
			this.task();
		}
		
		logger.info("================================ 每分钟定时任务 同步药品信息 结束 ================================");
	}
	
	/**
	 * 同步药品信息任务业务部分
	 */
	private void task(){
		try {
			
			String url = dictCommonService.getUrl(DictCommonCodeUtil.SYNC_DRUG_URL_CODE);
			if(StringUtils.isBlank(url)){
				logger.info("================================ 获取同步药品信息URL失败 定时任务结束 ================================");
				return;
			}
			
			Map<String, Object> resMap = SyncDrugInfoUtil.processSyncDrug(url);
			String result_code = resMap.get(RequestResultUtil.RESULT_CODE).toString();
			String logJSON = resMap.get(RequestResultUtil.RESULT_LOG).toString();
			if(result_code.equals(RequestResultUtil.RESULT_CODE_SUCCESS)){
				String drugInfoJSON = resMap.get(RequestResultUtil.RESULT_DATA).toString();
				//while(true){
					try {//解析JSON并保存内容
						if(StringUtils.isNotBlank(drugInfoJSON)){
							List<Drug> drugList = JSONArray.parseArray(drugInfoJSON, Drug.class);
							if(drugList!=null && drugList.size()>0){
								int rows = drugService.insertListSelective(drugList);
								if (rows > 0) {
									logger.info("保存药品信息到数据库成功，同步["+drugList.size()+"]条药品信息。");
									LogSyncDrug log = JSON.parseObject(logJSON, LogSyncDrug.class);
									logSyncDrugService.insertSelective(log);
									//break;
								} else {
									logger.error("保存数据库异常，等待重新保存。。。");
									//Thread.sleep(5*1000);
									//continue;
								}
							}else{
								//TODO 解析JSON为空
								logger.error("解析JSON为空");
							}
						}else{
							//TODO 文件内容为空
							logger.error("文件内容为空");
						}
					} catch (Exception e) {
						e.printStackTrace();
						// TODO: handle exception
						logger.error("解析JSON并保存内容异常");
						//continue;
					}
					//break;
				//}
				
			}else{
				
				LogSyncDrug log = JSON.parseObject(logJSON, LogSyncDrug.class);
				logSyncDrugService.insertSelective(log);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
