package com.rx.service.impl.back;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rx.dao.JobCronExpressionMapper;
import com.rx.entity.JobCronExpression;
import com.rx.service.back.IJobCronExpressionService;
import com.rx.service.impl.AbstractBaseService;

import tk.mybatis.mapper.entity.Example;

@Service("jobCronExpressionServiceBean")
public class JobCronExpressionServiceImpl extends AbstractBaseService<JobCronExpression, Long> implements IJobCronExpressionService {

	private JobCronExpressionMapper jobCronExpressionMapper;

	/**
	 * @param jobCronExpressionMapper the jobCronExpressionMapper to set
	 * set方式注入
	 */
	public void setJobCronExpressionMapper(JobCronExpressionMapper jobCronExpressionMapper) {
		this.jobCronExpressionMapper = jobCronExpressionMapper;
		this.setMapper(jobCronExpressionMapper);
	}

	@Override
	public List<JobCronExpression> getList(Map<String, Object> map) {
		Example example=new Example(JobCronExpression.class);
		//example.createCriteria().andEqualTo("type", map.get("type").toString());
		List<JobCronExpression> list=jobCronExpressionMapper.selectByExample(example);
		return list;
	}
	
}
