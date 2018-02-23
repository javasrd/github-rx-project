package com.rx.service.impl.back;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rx.dao.LogSyncDrugMapper;
import com.rx.entity.LogSyncDrug;
import com.rx.entity.User;
import com.rx.service.back.ILogSyncDrugService;
import com.rx.service.impl.AbstractBaseService;

import tk.mybatis.mapper.entity.Example;

@Service("logSyncDrugServiceBean")
public class LogSyncDrugServiceImpl extends AbstractBaseService<LogSyncDrug, Long> implements ILogSyncDrugService {

	private LogSyncDrugMapper logSyncDrugMapper;

	/**
	 * @param logSyncDrugMapper the logSyncDrugMapper to set
	 * set方式注入
	 */
	public void setLogSyncDrugMapper(LogSyncDrugMapper logSyncDrugMapper) {
		this.logSyncDrugMapper = logSyncDrugMapper;
		this.setMapper(logSyncDrugMapper);
	}
	
	@Override
	public List<LogSyncDrug> getList(Map<String, Object> map) {
		Example example=new Example(User.class);
		/* orderBy:排序 = id desc 或 created_date asc 等 */
		example.setOrderByClause(map.get("orderBy").toString());
		List<LogSyncDrug> list=logSyncDrugMapper.selectByExample(example);
		return list;
	}

}
