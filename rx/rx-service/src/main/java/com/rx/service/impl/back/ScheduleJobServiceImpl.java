package com.rx.service.impl.back;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import com.rx.dao.ScheduleJobMapper;
import com.rx.entity.ScheduleJob;
import com.rx.service.back.IScheduleJobService;
import com.rx.service.impl.AbstractBaseService;

import tk.mybatis.mapper.entity.Example;

@Service("scheduleJobServiceBean")
public class ScheduleJobServiceImpl extends AbstractBaseService<ScheduleJob, Long> implements IScheduleJobService {

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

}
