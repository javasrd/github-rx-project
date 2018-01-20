package com.rx.service.back;

import java.util.List;
import java.util.Map;

import com.rx.entity.DictMode;
import com.rx.service.IBaseService;

public interface IDictModeService extends IBaseService<DictMode, Long> {
	
	/**
	 * 根据map中的条件查询
	 * @param map
	 * @return
	 */
	public List<DictMode> getList(Map<String, Object> map);
	
}
