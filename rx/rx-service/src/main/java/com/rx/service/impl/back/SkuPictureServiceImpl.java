package com.rx.service.impl.back;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rx.dao.SkuPictureMapper;
import com.rx.entity.SkuPicture;
import com.rx.service.back.ISkuPictureService;
import com.rx.service.impl.AbstractBaseService;

@Service("skuPictureServiceBean")
public class SkuPictureServiceImpl extends AbstractBaseService<SkuPicture, Long> implements ISkuPictureService {

	private SkuPictureMapper skuPictureMapper;

	/**
	 * @param skuPictureMapper the skuPictureMapper to set
	 * set方式注入
	 */
	public void setSkuPictureMapper(SkuPictureMapper skuPictureMapper) {
		this.skuPictureMapper = skuPictureMapper;
		this.setMapper(skuPictureMapper);
	}

	/**
	 * @see com.rx.service.back.ISkuPictureService#getBySkuIds(java.util.List)
	 * 根据skuID集合查询sku图片
	 */
	@Override
	public List<SkuPicture> getBySkuIds(List<Long> skuIds) {
		return skuPictureMapper.getBySkuIds(skuIds);
	}

	/**
	 * @see com.rx.service.back.ISkuPictureService#updateBySkuIds(java.util.List)
	 * 根据skuID集合删除sku图片
	 */
	@Override
	public int deleteBySkuIds(List<Long> skuIds) {
		return skuPictureMapper.updateBySkuIds(skuIds);
	}

}
