package com.rx.service.back;

import com.rx.entity.CategoryBrand;
import com.rx.service.IBaseService;

public interface ICategoryBrandService extends IBaseService<CategoryBrand, Long> {
	
	/**
	 * 保存类目品牌
	 * @param categoryBrandList
	 * @return
	 */
	public int saveCategoryBrand(Long secondLevCid, Long thirdLevCid, String brandListJson);
	
	/**
	 * 根据三级类目ID删除
	 * @param thirdLevCid
	 * @return
	 */
	public int deleteByThirdLevCid(Long thirdLevCid);
	
}
