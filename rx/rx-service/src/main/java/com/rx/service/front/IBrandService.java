package com.rx.service.front;

import java.util.List;

import com.rx.entity.Brand;
import com.rx.service.IBaseService;

public interface IBrandService extends IBaseService<Brand, Long> {	
	
	
	/**
	 * @Description 按 关键字 查询品牌
	 * @param keywords 查询关键字（未分词）
	 * @return
	 */
	public List<Brand> getBrandByKeywords(String keywords);
	
}
