package com.rx.service.back;

import java.util.List;
import java.util.Map;

import com.rx.entity.DictCommon;
import com.rx.service.IBaseService;

public interface IDictCommonService extends IBaseService<DictCommon, Long> {
	
	/**
	 * 根据map中的条件查询
	 * @param map
	 * @return
	 */
	public List<DictCommon> getList(Map<String, Object> map);
	
	/**
	 * 逻辑删除
	 * @param id
	 * @return
	 */
	public int logicDelById(Long id);
	
}
