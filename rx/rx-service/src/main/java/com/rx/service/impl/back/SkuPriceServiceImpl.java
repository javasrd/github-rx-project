package com.rx.service.impl.back;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rx.bean.DeletedType;
import com.rx.dao.SkuPriceMapper;
import com.rx.entity.Sku;
import com.rx.entity.SkuPrice;
import com.rx.service.back.ISkuPriceService;
import com.rx.service.impl.AbstractBaseService;

import tk.mybatis.mapper.entity.Example;

@Service("skuPriceServiceBean")
public class SkuPriceServiceImpl extends AbstractBaseService<SkuPrice, Long> implements ISkuPriceService {

	private SkuPriceMapper skuPriceMapper;

	/**
	 * @param skuPriceMapper the skuPriceMapper to set
	 * set方式注入
	 */
	public void setSkuPriceMapper(SkuPriceMapper skuPriceMapper) {
		this.skuPriceMapper = skuPriceMapper;
		this.setMapper(skuPriceMapper);
	}

	/**
	 * @see com.rx.service.back.ISkuPriceService#getByItemId(java.lang.Long)
	 * 根据itemId查询SKU价格
	 */
	@Override
	public List<SkuPrice> getByItemId(Long itemId) {
		Example example = new Example(Sku.class);
		example.createCriteria().andEqualTo("itemId", itemId).andEqualTo("deleted", DeletedType.NO);
		return skuPriceMapper.selectByExample(example);
	}

	/**
	 * @see com.rx.service.back.ISkuPriceService#deleteByItemId(java.lang.Long)
	 * 根据itemId删除SKU价格
	 */
	@Override
	public int deleteByItemId(Long itemId) {
		Example example = new Example(Sku.class);
		example.createCriteria().andEqualTo("itemId", itemId);
		return skuPriceMapper.deleteByExample(example);
	}

}
