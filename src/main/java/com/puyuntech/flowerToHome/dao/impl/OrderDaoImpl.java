package com.puyuntech.flowerToHome.dao.impl;

import org.springframework.stereotype.Repository;

import com.puyuntech.flowerToHome.dao.OrderDao;
import com.puyuntech.flowerToHome.entity.Order;

/**
 * 
 * order Dao. Created on 2015-9-6 上午10:40:22
 * 
 * @author 王凯斌
 */
@Repository("orderDaoImpl")
public class OrderDaoImpl extends BaseDaoImpl<Order, Integer> implements
OrderDao {
	
}
