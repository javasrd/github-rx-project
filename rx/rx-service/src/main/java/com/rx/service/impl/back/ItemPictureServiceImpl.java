package com.rx.service.impl.back;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rx.bean.DeletedType;
import com.rx.dao.ItemPictureMapper;
import com.rx.entity.ItemPicture;
import com.rx.service.back.IItemPictureService;
import com.rx.service.impl.AbstractBaseService;

import tk.mybatis.mapper.entity.Example;

@Service("itemPictureServiceBean")
public class ItemPictureServiceImpl extends AbstractBaseService<ItemPicture, Long> implements IItemPictureService {

	private ItemPictureMapper itemPictureMapper;

	/**
	 * @param itemPictureMapper the itemPictureMapper to set
	 * set方式注入
	 */
	public void setItemPictureMapper(ItemPictureMapper itemPictureMapper) {
		this.itemPictureMapper = itemPictureMapper;
		this.setMapper(itemPictureMapper);
	}

	/**
	 * @see com.rx.service.back.IItemPictureService#getByItemId(java.lang.Long)
	 * 根据商品ID查询商品图片
	 */
	@Override
	public List<ItemPicture> getByItemId(Long itemId) {
		Example example = new Example(ItemPicture.class);
		example.createCriteria().andEqualTo("itemId", itemId).andEqualTo("deleted", DeletedType.NO);
		return itemPictureMapper.selectByExample(example);
	}

	/**
	 * @see com.rx.service.back.IItemPictureService#deleteByItemId(java.lang.Long)
	 * 根据商品ID删除商品图片
	 */
	@Override
	public int deleteByItemId(Long itemId) {
		Example example = new Example(ItemPicture.class);
		example.createCriteria().andEqualTo("itemId", itemId);
		return itemPictureMapper.deleteByExample(example);
	}

}
