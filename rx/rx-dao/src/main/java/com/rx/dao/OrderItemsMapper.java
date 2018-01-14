package com.rx.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.rx.entity.OrderItems;

import tk.mybatis.mapper.common.Mapper;

public interface OrderItemsMapper extends Mapper<OrderItems> {
	
	public List<Map<String,String>> selectItemsByOrderId(@Param("orderId") String orderId);
	
}