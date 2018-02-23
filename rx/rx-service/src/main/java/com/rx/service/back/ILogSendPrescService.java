package com.rx.service.back;

import java.util.List;
import java.util.Map;

import com.rx.entity.LogSendPresc;
import com.rx.service.IBaseService;

public interface ILogSendPrescService extends IBaseService<LogSendPresc, Long> {
	
	/**
	 * 根据map中的条件查询
	 * @param map
	 * 		orderBy:排序 = id desc 或 created_time asc 等
	 * @return
	 */
	public List<LogSendPresc> getList(Map<String, Object> map);
	
}
