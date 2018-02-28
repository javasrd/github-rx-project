package com.rx.service.back;

import java.util.List;
import java.util.Map;

import com.rx.entity.JobCronExpression;
import com.rx.service.IBaseService;

public interface IJobCronExpressionService extends IBaseService<JobCronExpression, Long> {
	
	/**
	 * 根据map中的条件查询
	 * @param map
	 * @return
	 */
	public List<JobCronExpression> getList(Map<String, Object> map);
	
}
