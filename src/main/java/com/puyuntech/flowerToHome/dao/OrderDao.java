package com.puyuntech.flowerToHome.dao;

import com.puyuntech.flowerToHome.entity.Order;

/**
 * 
 * Order Dao . 
 * Created on 2015-9-6 上午10:39:21 
 * @author 王凯斌
 */
public interface OrderDao extends BaseDao<Order, Integer> {
	public Integer changeStatus(Order order, Order.Status status);
}
