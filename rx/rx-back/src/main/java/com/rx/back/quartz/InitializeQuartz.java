package com.rx.back.quartz;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.rx.bean.ScheduleJobStatus;
import com.rx.entity.ScheduleJob;
import com.rx.service.back.IScheduleJobService;

@Component
public class InitializeQuartz {

	private final Logger log = Logger.getLogger(getClass());
	
	@Resource(name = "scheduleJobServiceBean")
	private IScheduleJobService scheduleJobService;
	
	public void initialize(){
		
		try {
			log.info("开始初始化Quartz...");
			
			List<ScheduleJob> jobList = scheduleJobService.selectAll();
			log.info("数据库中的任务个数："+jobList.size());
			
			for(int i=0; i<jobList.size(); i++){
				ScheduleJob job = jobList.get(i);
				if(job!=null){
					log.info("数据库中的任务 "+job.getJobGroup()+" 【 "+job.getJobName()+" 】 的状态："+ScheduleJobStatus.getValue(job.getJobStatus()));
					if(job.getJobStatus()!=null && job.getJobStatus()==ScheduleJobStatus.JOB_ING.getIndex().byteValue()){
						log.info("正在启动任务 "+job.getJobGroup()+" 【 "+job.getJobName()+" 】，请等待...");
						Date initDate = scheduleJobService.updateJob(job);
						if(initDate==null){
							log.info("启动任务 "+job.getJobGroup()+" 【 "+job.getJobName()+" 】失败，请手动启动！");
						}else{
							String initDateStr = DateFormatUtils.format(initDate, "yyyy-MM-dd HH:mm:ss");
							log.info("==================== [ "+initDateStr+" ] "+job.getJobGroup()+" 【 "+job.getJobName()+" 】 任务已启动！");
						}
					}else{
						log.info("数据库中的任务 "+job.getJobGroup()+" 【 "+job.getJobName()+" 】不需要启动！");
					}
				}
			}
			
			log.info("初始化Quartz完成！");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("部分任务启动失败，请手动启动！");
			log.info("初始化Quartz结束");
		}
		
	}
	
	public static void main(String[] args) {
		byte a = 1;
		byte b = 1;
		System.out.println(a==b);
		byte c = 127;
		byte d = 127;
		System.out.println(c==d);
	}
	
}
