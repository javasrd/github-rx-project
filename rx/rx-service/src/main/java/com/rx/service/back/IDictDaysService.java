package com.rx.service.back;

import java.util.List;
import java.util.Map;

import com.rx.entity.DictDays;
import com.rx.service.IBaseService;

public interface IDictDaysService extends IBaseService<DictDays, Long> {
	
	/**
	 * 根据map中的条件查询
	 * @param map
	 * @return
	 */
	public List<DictDays> getList(Map<String, Object> map);
	
}
