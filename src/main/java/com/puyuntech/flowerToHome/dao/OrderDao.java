package com.puyuntech.flowerToHome.dao;

import java.util.List;

import com.puyuntech.flowerToHome.Page;
import com.puyuntech.flowerToHome.Pageable;
import com.puyuntech.flowerToHome.entity.Order;

/**
 * 
 * Order Dao . 
 * Created on 2015-9-6 上午10:39:21 
 * @author 王凯斌
 */
public interface OrderDao extends BaseDao<Order, Integer> {
	Integer changeStatus(Order order, Order.Status status);
	
	String changeShop(Integer orderId, Integer shopId);
	
	Page<Order> pageByTel(Pageable pageable);
	
	Page<Order> report(Pageable pageable,Order.Status status,Integer shopId,String memberTel,
			String province,String city,String distract,Integer isBlance,Order.FromType fromType);
	
	List<Order> findList(Pageable pageable,Order.Status status,Integer shopId,String memberTel,
			String province,String city,String distract,Integer isBlance,Order.FromType fromType);
}
