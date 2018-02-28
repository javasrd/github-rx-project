package com.rx.service.back;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.rx.entity.ScheduleJob;
import com.rx.service.IBaseService;

public interface IScheduleJobService extends IBaseService<ScheduleJob, Long> {

	/**
	 * 根据map中的条件查询
	 * 
	 * @param map
	 * @return
	 */
	public List<ScheduleJob> getList(Map<String, Object> map);

	/**
	 * 任务创建 未用
	 * 
	 * @param job
	 * @return
	 */
	public Date createJob(ScheduleJob job);

	/**
	 * 任务创建与更新(未存在的就创建，已存在的则更新)
	 * 
	 * @param job
	 * @return
	 */
	public Date updateJob(ScheduleJob job);

	/**
	 * 暂停任务
	 * 
	 * @param job
	 */
	public void pauseJob(ScheduleJob job);

	/**
	 * 恢复
	 * 
	 * @param job
	 */
	public void resumeJob(ScheduleJob job);

	/**
	 * 删除任务
	 * 
	 * @param job
	 * @return
	 */
	public Boolean deleteJob(ScheduleJob job);

}
