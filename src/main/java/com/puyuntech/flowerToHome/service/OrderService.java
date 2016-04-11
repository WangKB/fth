
package com.puyuntech.flowerToHome.service;

import java.util.List;

import com.puyuntech.flowerToHome.Page;
import com.puyuntech.flowerToHome.Pageable;
import com.puyuntech.flowerToHome.entity.Order;


/**
 * 
 * Service - Order. 
 * Created on 2015-10-14 下午2:06:41 
 * @author 王凯斌
 */
public interface OrderService extends BaseService<Order, Integer> {
	
	Integer changeStatus(Order order, Order.Status status);
	
	String changeShop(Integer orderId, Integer shopId);
	
	Page<Order> pageByTel(Pageable pageable);
	
	Page<Order> report(Pageable pageable,Order.Status status,Integer shopId,String memberTel,
			String province,String city,String distract,Integer isBlance,Order.FromType fromType);
	
	List<Order> findList(Pageable pageable,Order.Status status,Integer shopId,String memberTel,
			String province,String city,String distract,Integer isBlance,Order.FromType fromType);
}