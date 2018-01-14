package com.rx.service.impl.front;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rx.bean.SkuPriceBean;
import com.rx.dao.SkuMapper;
import com.rx.entity.Sku;
import com.rx.service.front.ISkuService;
import com.rx.service.impl.AbstractBaseService;

@Service
public class SkuServiceImpl extends AbstractBaseService<Sku, Long> implements ISkuService {
	
	SkuMapper skuMapper;
	
	/**
	 * @param mapper
	 * the mapper to set set方式注入
	 */	
	public void setSkuMapper(SkuMapper skuMapper) { 
		this.skuMapper=skuMapper;
		this.setMapper(skuMapper);
	}

	@Override
	public List<SkuPriceBean> getSkuByIdAndType(Long itemId, int skuType) {
		
		return skuMapper.getSkuByIdAndType(itemId,skuType);
		
	}

	@Override
	public List<SkuPriceBean> getSkuByIdAndAttr(Long itemId, String skuAttribute) {
		return skuMapper.getSkuByIdAndAttr(itemId,skuAttribute);
	}

	@Override
	public SkuPriceBean getSkuBySkuId(long skuId) {			
		return skuMapper.getSkuBySkuId(skuId);
		
	}

	@Override
	public List<Map<String, String>> getSkuIntroduce(long skuId) {
		return skuMapper.getSkuIntroduce(skuId);
	}
	

		
	

}
