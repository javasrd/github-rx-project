package com.rx.service.impl.back;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import com.rx.bean.ScheduleJobStatus;
import com.rx.common.util.RequestResultUtil;
import com.rx.dao.ScheduleJobMapper;
import com.rx.entity.ScheduleJob;
import com.rx.service.back.IScheduleJobService;
import com.rx.service.impl.AbstractBaseService;
import com.rx.service.quartz.QuartzJobFactory;

import tk.mybatis.mapper.entity.Example;

@Service("scheduleJobServiceBean")
public class ScheduleJobServiceImpl extends AbstractBaseService<ScheduleJob, Long> implements IScheduleJobService {

	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;
	
	private ScheduleJobMapper scheduleJobMapper;

	/**
	 * @param scheduleJobMapper the scheduleJobMapper to set
	 * set方式注入
	 */
	public void setScheduleJobMapper(ScheduleJobMapper scheduleJobMapper) {
		this.scheduleJobMapper = scheduleJobMapper;
		this.setMapper(scheduleJobMapper);
	}

	@Override
	public List<ScheduleJob> getList(Map<String, Object> map) {
		Example example=new Example(ScheduleJob.class);
		//example.createCriteria().andEqualTo("type", map.get("type").toString());
		List<ScheduleJob> list=scheduleJobMapper.selectByExample(example);
		return list;
	}

	@Override
	public Date createJob(ScheduleJob job) {
		
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			
			// 获取触发器标识
			TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
			// 获取触发器trigger
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			
			// Trigger不存在，那么创建任务
			// 创建任务
			JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactory.class)
					.withIdentity(job.getJobName(), job.getJobGroup()).build();

			jobDetail.getJobDataMap().put("scheduleJob", job);

			// 表达式调度构建器
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());

			// 按新的cronExpression表达式构建一个新的trigger
			trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup())
					.withSchedule(scheduleBuilder).build();

			return scheduler.scheduleJob(jobDetail, trigger);
			
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Date updateJob(ScheduleJob job) {
		
		try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            if(job!=null){
            	
            	//修改任务状态
				job.setJobStatus((byte)ScheduleJobStatus.JOB_ING.getIndex().intValue());
            	
                //获取触发器标识
                TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
                //获取触发器trigger
                CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
                if(null==trigger){//不存在任务
                    //创建任务
                    JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactory.class)
                            .withIdentity(job.getJobName(), job.getJobGroup())
                            .build();
                     
                    jobDetail.getJobDataMap().put("scheduleJob", job);
                     
                    //表达式调度构建器
                    CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job
                            .getCronExpression());
                     
                    //按新的cronExpression表达式构建一个新的trigger
                    trigger = TriggerBuilder.newTrigger()
                            .withIdentity(job.getJobName(), job.getJobGroup())
                            .withSchedule(scheduleBuilder)
                            .build();
                     
                    return scheduler.scheduleJob(jobDetail, trigger);
                     
                }else{//存在任务
                     
                    // Trigger已存在，那么更新相应的定时设置
                    //表达式调度构建器
                    CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job
                            .getCronExpression());
                     
                    //按新的cronExpression表达式重新构建trigger
                    trigger = trigger.getTriggerBuilder()
                            .withIdentity(triggerKey)
                            .withSchedule(scheduleBuilder)
                            .build();
                     
                    //按新的trigger重新设置job执行
                    return scheduler.rescheduleJob(triggerKey, trigger);
                     
                }
                 
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
		
		return null;
	}

	@Override
	public void pauseJob(ScheduleJob job) {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
		try {
			scheduler.pauseJob(jobKey);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void resumeJob(ScheduleJob job) {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
		try {
			scheduler.resumeJob(jobKey);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Boolean deleteJob(ScheduleJob job) {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
		try {
			return scheduler.deleteJob(jobKey);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
