package com.rx.service.back;

import java.util.List;

import com.rx.entity.SkuPrice;
import com.rx.service.IBaseService;

public interface ISkuPriceService extends IBaseService<SkuPrice, Long> {
	
	/**
	 * 根据itemId查询SKU价格
	 * @param itemId
	 * @return
	 */
	public List<SkuPrice> getByItemId(Long itemId);
	
	/**
	 * 根据itemId删除SKU价格
	 * @param itemId
	 * @return
	 */
	public int deleteByItemId(Long itemId);
	
}
