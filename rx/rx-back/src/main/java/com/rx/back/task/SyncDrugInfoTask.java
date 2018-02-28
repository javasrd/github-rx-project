package com.rx.back.task;

/** 
 * 定时任务 
 */
public class SyncDrugInfoTask {
	
	//private final Logger logger = Logger.getLogger(getClass());
	
	/*@Resource(name="drugServiceBean")
	private IDrugService drugService;

	@Resource(name = "logSyncDrugServiceBean")
	private ILogSyncDrugService logSyncDrugService;
	
	@Resource(name="dictCommonServiceBean")
	private IDictCommonService dictCommonService;*/
	
	//private final String url = "http://127.0.0.1/rx-back/api/demo";
	
	/**
	 * 每天定时任务
	 */
	/*public void everyDayTask(){
		System.out.println("================================ 每天（00：00）定时任务 同步药品信息 开始 ================================");
		
		this.task();
		
		System.out.println("================================ 每天（00：00）定时任务 同步药品信息 结束 ================================");
	}*/
	
	/**
	 * 每分钟定时任务
	 */
	/*public void everyMinuteTask(){
		System.out.println("================================ 每分钟定时任务 同步药品信息 开始 ================================");
		
		Example example = new Example(LogSyncDrug.class);
		example.setOrderByClause("created_date DESC, id DESC");
		List<LogSyncDrug> logList = logSyncDrugService.selectByExample(example);
		if(logList!=null && logList.size()>0){
			LogSyncDrug log = logList.get(0);
			if(log==null || log.getStatus()==null || log.getStatus()!=SyncDrugLogStatus.SUCCESS.getIndex()){
				this.task();
			}
		}else{
			this.task();
		}
		
		System.out.println("================================ 每分钟定时任务 同步药品信息 结束 ================================");
	}*/
	
	/**
	 * 同步药品信息任务业务部分
	 */
	/*private void task(){
		try {
			
			String url = dictCommonService.getUrl(DictCommonCodeUtil.SYNC_DRUG_URL_CODE);
			if(StringUtils.isBlank(url)){
				System.out.println("================================ 获取同步药品信息URL失败 定时任务结束 ================================");
				return;
			}
			
			Map<String, Object> resMap = SyncDrugInfoUtil.processSyncDrug(url);
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
	}*/
	
}
