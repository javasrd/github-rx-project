package com.rx.service.back;

import java.util.List;
import java.util.Map;

import com.rx.entity.LogSyncDrug;
import com.rx.service.IBaseService;

public interface ILogSyncDrugService extends IBaseService<LogSyncDrug, Long> {
	
	/**
	 * 根据map中的条件查询
	 * @param map
	 * 		orderBy:排序 = id desc 或 created_date asc 等
	 * @return
	 */
	public List<LogSyncDrug> getList(Map<String, Object> map);
	
}
