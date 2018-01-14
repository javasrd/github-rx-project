package com.rx.service.back;

import com.rx.entity.Favourite;
import com.rx.service.IBaseService;

public interface IFavouriteService extends IBaseService<Favourite, Long> {	
	
	/**
	 * 删除购物车中的商品（逻辑删除）
	 * @param itemId
	 * @return
	 */
	public int deleteByItemId(long itemId);
	
}
