package com.rx.service.front;

import com.rx.entity.AttributeValue;
import com.rx.service.IBaseService;

public interface IAttrValueService extends IBaseService<AttributeValue, Long> {	
	
	
	/**
	 * @Description 根据属性ID，与属性值ID读取
	 * @param attrId
	 * @param valueId
	 * @return
	 */
	public AttributeValue getAttributeValueById(long attrId,long valueId);
	
}
