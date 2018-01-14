package com.rx.service.back;

import java.util.List;
import java.util.Map;

import com.rx.entity.AttributeValue;
import com.rx.service.IBaseService;

public interface IAttributeValueService extends IBaseService<AttributeValue, Long> {
	
	/**
	 * 根据属性ID查询属性值
	 * @param attrId
	 * @return
	 * 		返回list
	 */
	List<Map<String, Object>> selectByAttrId(Long attrId);
}
