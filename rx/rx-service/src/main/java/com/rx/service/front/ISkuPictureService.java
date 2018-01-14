package com.rx.service.front;

import java.util.List;

import com.rx.entity.SkuPicture;
import com.rx.service.IBaseService;

public interface ISkuPictureService extends IBaseService<SkuPicture, Long> {	
	
	
	
	/**
	 * @Description 根据指定SPU下的sku
	 * @param itemId   SPU id
	 * @param sku_type 1:主sku   2:非主sku 
	 * @return
	 */
	public List<SkuPicture> getSkuPictureById(Long skuId);	
	
	
}
