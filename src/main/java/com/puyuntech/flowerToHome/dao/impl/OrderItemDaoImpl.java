package com.puyuntech.flowerToHome.dao.impl;

import org.springframework.stereotype.Repository;

import com.puyuntech.flowerToHome.dao.OrderItemDao;
import com.puyuntech.flowerToHome.entity.OrderItem;

/**
 * 
 * order Dao. Created on 2015-9-6 上午10:40:22
 * 
 * @author 王凯斌
 */
@Repository("orderItemDaoImpl")
public class OrderItemDaoImpl extends BaseDaoImpl<OrderItem, Integer> implements
OrderItemDao {
	
}
