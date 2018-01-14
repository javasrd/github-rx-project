package com.rx.service.back;

import java.util.List;

import com.rx.entity.ItemPicture;
import com.rx.service.IBaseService;

public interface IItemPictureService extends IBaseService<ItemPicture, Long> {
	
	/**
	 * 根据商品ID查询商品图片
	 * @param itemId
	 * @return
	 */
	public List<ItemPicture> getByItemId(Long itemId);
	
	/**
	 * 根据商品ID删除商品图片
	 * @param itemId
	 * @return
	 */
	public int deleteByItemId(Long itemId);
	
}
