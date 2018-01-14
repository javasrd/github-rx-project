package com.rx.service.back;

import java.util.List;

import com.rx.entity.SkuPicture;
import com.rx.service.IBaseService;

public interface ISkuPictureService extends IBaseService<SkuPicture, Long> {
	
	/**
	 * 根据skuID集合查询sku图片
	 * @param skuIds
	 * @return
	 */
	public List<SkuPicture> getBySkuIds(List<Long> skuIds);
	
	/**
	 * 根据skuID集合删除sku图片（逻辑删除）
	 * @param skuIds
	 * @return
	 */
	public int deleteBySkuIds(List<Long> skuIds);
	
}
