package com.rx.service.front;

import java.util.List;
import java.util.Map;

import com.rx.bean.AddSkuToOrderBean;
import com.rx.entity.OrderItems;
import com.rx.service.IBaseService;

public interface IOrderItemService extends IBaseService<OrderItems, Long> {
	
	
	/**
	 * @Description 向订单中增加sku条目
	 * @param itemList
	 * @param orderId
	 */
	public void addItemIntoOrder(List<AddSkuToOrderBean> itemList,String orderId);
	
	/**
	 * @Description 根据订单id读取订单数据
	 * @param orderId
	 * @return
	 */
	public List<Map<String,String>>  selectItemsByOrderId(String orderId);
	
	
	
}
