
package com.puyuntech.flowerToHome.service.impl;



import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.puyuntech.flowerToHome.dao.OrderDao;
import com.puyuntech.flowerToHome.dao.SnDao;
import com.puyuntech.flowerToHome.entity.Order;
import com.puyuntech.flowerToHome.entity.Sn;
import com.puyuntech.flowerToHome.service.OrderService;

/**
 * 
 * Service - Order. 
 * Created on 2015-10-14 下午2:39:03 
 * @author 王凯斌
 */
@Service("orderServiceImpl")
public class OrderServiceImpl extends BaseServiceImpl<Order, Integer> implements OrderService {
	
	@Resource(name = "snDaoImpl")
	private SnDao snDao;
	
	@Resource(name = "orderDaoImpl")
	private OrderDao orderDao;
	
	@Transactional
	public Order save(Order order) {
		
		Assert.notNull(order);
		order.setSn(snDao.generate(Sn.Type.order));
		return super.save(order);
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public Integer changeStatus(Order order, Order.Status status){
		return orderDao.changeStatus(order, status);
	}
}