package com.rx.service.impl.back;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rx.dao.LogReceivePatientMapper;
import com.rx.entity.LogReceivePatient;
import com.rx.entity.User;
import com.rx.service.back.ILogReceivePatientService;
import com.rx.service.impl.AbstractBaseService;

import tk.mybatis.mapper.entity.Example;

@Service("logReceivePatientServiceBean")
public class LogReceivePatientServiceImpl extends AbstractBaseService<LogReceivePatient, Long> implements ILogReceivePatientService {

	private LogReceivePatientMapper logReceivePatientMapper;

	/**
	 * @param logReceivePatientMapper the logReceivePatientMapper to set
	 * set方式注入
	 */
	public void setLogReceivePatientMapper(LogReceivePatientMapper logReceivePatientMapper) {
		this.logReceivePatientMapper = logReceivePatientMapper;
		this.setMapper(logReceivePatientMapper);
	}

	@Override
	public List<LogReceivePatient> getList(Map<String, Object> map) {
		Example example=new Example(User.class);
		/* orderBy:排序 = id desc 或 created_time asc 等 */
		example.setOrderByClause(map.get("orderBy").toString());
		List<LogReceivePatient> list=logReceivePatientMapper.selectByExample(example);
		return list;
	}
	
}
