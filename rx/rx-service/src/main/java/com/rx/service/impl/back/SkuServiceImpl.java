package com.rx.service.impl.back;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rx.bean.DeletedType;
import com.rx.dao.SkuMapper;
import com.rx.entity.Sku;
import com.rx.service.back.ISkuService;
import com.rx.service.impl.AbstractBaseService;

import tk.mybatis.mapper.entity.Example;

@Service("skuServiceBean")
public class SkuServiceImpl extends AbstractBaseService<Sku, Long> implements ISkuService {

	private SkuMapper skuMapper;

	/**
	 * @param skuMapper the skuMapper to set
	 * set方式注入
	 */
	public void setSkuMapper(SkuMapper skuMapper) {
		this.skuMapper = skuMapper;
		this.setMapper(skuMapper);
	}

	/**
	 * @see com.rx.service.back.ISkuService#getByItemId(java.lang.Long)
	 * 根据商品ID查询商品SKU
	 */
	@Override
	public List<Sku> getByItemId(Long itemId) {
		Example example = new Example(Sku.class);
		example.createCriteria().andEqualTo("itemId", itemId).andEqualTo("deleted", DeletedType.NO);
		return skuMapper.selectByExample(example);
	}

	/**
	 * @see com.rx.service.back.ISkuService#deleteByItemId(java.lang.Long)
	 * 根据商品ID删除商品SKU
	 */
	@Override
	public int deleteByItemId(Long itemId) {
		Example example = new Example(Sku.class);
		example.createCriteria().andEqualTo("itemId", itemId);
		return skuMapper.deleteByExample(example);
	}

}
