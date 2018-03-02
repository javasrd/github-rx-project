package com.rx.service.back;

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

}
