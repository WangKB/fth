
package com.puyuntech.flowerToHome.service;

import com.puyuntech.flowerToHome.entity.Order;


/**
 * 
 * Service - Order. 
 * Created on 2015-10-14 下午2:06:41 
 * @author 王凯斌
 */
public interface OrderService extends BaseService<Order, Integer> {
	public Integer changeStatus(Order order, Order.Status status);
}