package com.rx.service.impl.front;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rx.bean.DeletedType;
import com.rx.dao.SkuPictureMapper;
import com.rx.entity.SkuPicture;
import com.rx.service.front.ISkuPictureService;
import com.rx.service.impl.AbstractBaseService;

@Service
public class SkuPictureServiceImpl extends AbstractBaseService<SkuPicture, Long> implements ISkuPictureService {

	SkuPictureMapper skuPictureMapper;
	
	/**
	 * @param mapper
	 * the mapper to set set方式注入
	 */	
	public void setSkuPictureMapper(SkuPictureMapper skuPictureMapper) {
		this.skuPictureMapper=skuPictureMapper;
		this.setMapper(skuPictureMapper);
	}
	
	@Override
	public List<SkuPicture> getSkuPictureById(Long skuId) {
		SkuPicture record=new SkuPicture();
		record.setSkuId(skuId);		
		record.setDeleted(DeletedType.NO);  //增加在查询SKU图片时删除条件
		return skuPictureMapper.select(record);		
	}

	

	

}
