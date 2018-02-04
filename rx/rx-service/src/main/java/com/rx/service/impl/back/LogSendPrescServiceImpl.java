package com.rx.service.impl.back;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rx.dao.LogSendPrescMapper;
import com.rx.entity.LogSendPresc;
import com.rx.entity.User;
import com.rx.service.back.ILogSendPrescService;
import com.rx.service.impl.AbstractBaseService;

import tk.mybatis.mapper.entity.Example;

@Service("logSendPrescServiceBean")
public class LogSendPrescServiceImpl extends AbstractBaseService<LogSendPresc, Long> implements ILogSendPrescService {

	private LogSendPrescMapper logSendPrescMapper;

	/**
	 * @param logSendPrescMapper the logSendPrescMapper to set
	 * set方式注入
	 */
	public void setLogSendPrescMapper(LogSendPrescMapper logSendPrescMapper) {
		this.logSendPrescMapper = logSendPrescMapper;
		this.setMapper(logSendPrescMapper);
	}

	@Override
	public List<LogSendPresc> getList(Map<String, Object> map) {
		Example example=new Example(User.class);
		/* orderBy:排序 = id desc 或 created_time asc 等 */
		example.setOrderByClause(map.get("orderBy").toString());
		List<LogSendPresc> list=logSendPrescMapper.selectByExample(example);
		return list;
	}
	
}
