package com.rx.service.back;

import java.util.List;
import java.util.Map;

import com.rx.entity.DictDoseUnit;
import com.rx.service.IBaseService;

public interface IDictDoseUnitService extends IBaseService<DictDoseUnit, Long> {

	/**
	 * 根据map中的条件查询
	 * @param map
	 * @return
	 */
	public List<DictDoseUnit> getList(Map<String, Object> map);
	
}
