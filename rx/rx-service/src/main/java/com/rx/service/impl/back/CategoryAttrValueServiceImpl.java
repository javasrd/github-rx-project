package com.rx.service.impl.back;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rx.dao.CategoryAttrValueMapper;
import com.rx.entity.CategoryAttrValue;
import com.rx.service.back.ICategoryAttrValueService;
import com.rx.service.impl.AbstractBaseService;

import tk.mybatis.mapper.entity.Example;

@Service("categoryAttrValueServiceBean")
public class CategoryAttrValueServiceImpl extends AbstractBaseService<CategoryAttrValue, Long> implements ICategoryAttrValueService {

	private CategoryAttrValueMapper categoryAttrValueMapper;

	/**
	 * @param categoryAttrValueMapper the categoryAttrValueMapper to set
	 * set方式注入
	 */
	public void setCategoryAttrValueMapper(CategoryAttrValueMapper categoryAttrValueMapper) {
		this.categoryAttrValueMapper = categoryAttrValueMapper;
		this.setMapper(categoryAttrValueMapper);
	}

	/**
	 * @see com.rx.service.back.ICategoryAttrValueService#getByValueId(java.lang.Long)
	 * 根据属性值ID查询类目属性值
	 */
	@Override
	public CategoryAttrValue getByValueId(Long valueId) {
		Example example = new Example(CategoryAttrValue.class);
		example.createCriteria().andEqualTo("valueId", valueId);
		List<CategoryAttrValue> list = categoryAttrValueMapper.selectByExample(example);
		if(!list.isEmpty() && list.size()==1){
			return list.get(0);
		}
		return null;
	}

	/**
	 * @see com.rx.service.back.ICategoryAttrValueService#deleteByValueId(java.lang.Long)
	 * 根据属性值ID删除类目属性值
	 */
	@Override
	public int deleteByValueId(Long valueId) {
		Example example = new Example(CategoryAttrValue.class);
		example.createCriteria().andEqualTo("valueId", valueId);
		return categoryAttrValueMapper.deleteByExample(example);
	}

}
