package com.rx.service.back;

import com.rx.entity.ItemAttrValue;
import com.rx.service.IBaseService;

public interface IItemAttrValueService extends IBaseService<ItemAttrValue, Long> {
	
	/**
	 * 根据商品ID删除
	 * @param itemId
	 * @return
	 */
	public int deleteByItemId(Long itemId);
}
