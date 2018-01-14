package com.rx.service.back;

import java.util.List;
import java.util.Map;

import com.rx.entity.Attribute;
import com.rx.service.IBaseService;

public interface IAttributeService extends IBaseService<Attribute, Long> {
	
	/**
	 * 根据类目ID查询属性
	 * @param cid
	 * @return
	 */
	List<Map<String, Object>> selectByCid(Long cid);
}
