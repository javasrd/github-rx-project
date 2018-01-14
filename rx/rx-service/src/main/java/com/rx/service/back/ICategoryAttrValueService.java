package com.rx.service.back;

import com.rx.entity.CategoryAttrValue;
import com.rx.service.IBaseService;

public interface ICategoryAttrValueService extends IBaseService<CategoryAttrValue, Long> {
	
	/**
	 * 根据属性值ID查询类目属性值
	 * @param valueId
	 * @return
	 */
	public CategoryAttrValue getByValueId(Long valueId);
	
	/**
	 * 根据属性值ID删除类目属性值
	 * @param valueId
	 * @return
	 */
	public int deleteByValueId(Long valueId);
}
