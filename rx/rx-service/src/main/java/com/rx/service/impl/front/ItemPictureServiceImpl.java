package com.rx.service.impl.front;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rx.bean.DeletedType;
import com.rx.dao.ItemPictureMapper;
import com.rx.entity.ItemPicture;
import com.rx.service.front.IItemPictureService;
import com.rx.service.impl.AbstractBaseService;

@Service
public class ItemPictureServiceImpl extends AbstractBaseService<ItemPicture, Long> implements IItemPictureService {
	
	ItemPictureMapper itemPictureMapper;
	/**
	 * @param mapper
	 * the mapper to set set方式注入
	 */	
	public void setItemPictureMapper(ItemPictureMapper itemPictureMapper) {
		this.itemPictureMapper=itemPictureMapper;
		this.setMapper(itemPictureMapper);
	}
	@Override
	public List<ItemPicture> getItemPictureByItemId(Long itemId){
		//通过一个对象查询
		ItemPicture itemPict=new ItemPicture();
		itemPict.setItemId(itemId);
		itemPict.setDeleted(DeletedType.NO);  //未删除标记
		return itemPictureMapper.select(itemPict);			
	}		
	

}
