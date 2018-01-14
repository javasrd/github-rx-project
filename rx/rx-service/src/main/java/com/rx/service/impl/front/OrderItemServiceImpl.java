package com.rx.service.impl.front;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rx.bean.AddSkuToOrderBean;
import com.rx.dao.OrderItemsMapper;
import com.rx.entity.OrderItems;
import com.rx.service.front.IOrderItemService;
import com.rx.service.impl.AbstractBaseService;

@Service
public class OrderItemServiceImpl extends AbstractBaseService<OrderItems, Long> implements IOrderItemService {
	
	OrderItemsMapper orderItemsMapper; 
	
	/**
	 * @param mapper
	 * the mapper to set set方式注入
	 */	
	public void setOrderItemsMapper(OrderItemsMapper orderItemsMapper) { 
		this.orderItemsMapper=orderItemsMapper;
		this.setMapper(orderItemsMapper);
	}

	@Override
	public void addItemIntoOrder(List<AddSkuToOrderBean> itemList, String orderId) {
		for(AddSkuToOrderBean item:itemList){
			OrderItems record=new OrderItems();
			
			record.setOrderId(orderId);  //订单号
			record.setCid(item.getCid());  //类目id
			record.setItemId(item.getItemId()); //spu id
			record.setSkuId(item.getSkuId());  //sku id
			record.setSkuName(item.getSkuName());  //sku name			
			record.setPrimitivePrice(item.getSkuPrice());  //原始价（未折减价格）
			record.setNum(item.getSkuNum());
			BigDecimal num=new BigDecimal(item.getSkuNum());
			record.setPayPriceTotal(item.getSkuPrice().multiply(num));
			
			
			record.setCreateTime(new Date());
			
			orderItemsMapper.insert(record);
			
		}
		
	}

	@Override
	public List<Map<String,String>> selectItemsByOrderId(String orderId) {
		
		return orderItemsMapper.selectItemsByOrderId(orderId);
	
	}
	
	

	

	
	
	

}
