package com.rx.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.rx.entity.ItemAttrValue;

import tk.mybatis.mapper.common.Mapper;

public interface ItemAttrValueMapper extends Mapper<ItemAttrValue> {
	
	List<Map<String, String>> getItemAttrValList(@Param("itemId") Long itemId, @Param("attrId") Long attrId);
	
}