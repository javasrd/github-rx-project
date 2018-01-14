package com.rx.service.impl.back;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rx.dao.AttributeValueMapper;
import com.rx.entity.AttributeValue;
import com.rx.service.back.IAttributeValueService;
import com.rx.service.impl.AbstractBaseService;

@Service("attributeValueServiceBean")
public class AttributeValueServiceImpl extends AbstractBaseService<AttributeValue, Long> implements IAttributeValueService {

	private AttributeValueMapper attributeValueMapper;

	/**
	 * @param attributeValueMapper the attributeValueMapper to set
	 * set方式注入
	 */
	public void setAttributeValueMapper(AttributeValueMapper attributeValueMapper) {
		this.attributeValueMapper = attributeValueMapper;
		this.setMapper(attributeValueMapper);
	}

	/** 
	 * @see com.ecp.service.IAttributeValueService#selectByAttrId(java.lang.Long)
	 * 根据属性ID查询属性值
	 */
	@Override
	public List<Map<String, Object>> selectByAttrId(Long attrId) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(attrId!=null && attrId>0){
			map.put("attrId", attrId);
		}else{
			return null;
		}
		return attributeValueMapper.selectByCondition(map);
	}

}
