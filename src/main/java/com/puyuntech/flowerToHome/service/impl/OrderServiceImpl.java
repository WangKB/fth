
package com.puyuntech.flowerToHome.service.impl;



import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.puyuntech.flowerToHome.Page;
import com.puyuntech.flowerToHome.Pageable;
import com.puyuntech.flowerToHome.dao.OrderDao;
import com.puyuntech.flowerToHome.dao.SnDao;
import com.puyuntech.flowerToHome.entity.Order;
import com.puyuntech.flowerToHome.entity.Order.FromType;
import com.puyuntech.flowerToHome.entity.Order.Status;
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

	public Page<Order> pageByTel(Pageable pageable) {
		return orderDao.pageByTel(pageable);
	}

	public Page<Order> report(Pageable pageable, Status status, Integer shopId, String memberTel, String province,
			String city, String distract, Integer isBlance, FromType fromType) {
		return orderDao.report(pageable, status, shopId, memberTel, province, city, distract, isBlance, fromType);
	}

	public List<Order> findList(Pageable pageable, Status status, Integer shopId, String memberTel, String province,
			String city, String distract, Integer isBlance, FromType fromType) {
		return orderDao.findList(pageable, status, shopId, memberTel, province, city, distract, isBlance, fromType);
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public String changeShop(Integer orderId, Integer shopId) {
		return orderDao.changeShop(orderId, shopId);
	}
}