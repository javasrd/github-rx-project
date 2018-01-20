package com.rx.service.back;

import java.util.List;
import java.util.Map;

import com.rx.entity.DictTimes;
import com.rx.service.IBaseService;

public interface IDictTimesService extends IBaseService<DictTimes, Long> {
	
	/**
	 * 根据map中的条件查询
	 * @param map
	 * @return
	 */
	public List<DictTimes> getList(Map<String, Object> map);
	
}
