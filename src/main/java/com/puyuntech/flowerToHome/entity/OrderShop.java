package com.puyuntech.flowerToHome.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * 订单附近商铺表.
 * Created on 2015-8-27 上午11:06:14 
 * @author 王凯斌
 * 
 */
@Entity
@Table(name = "t_order_shop")
public class OrderShop extends BaseEntity<Integer> {
	
	public enum Status{
		
		//待处理
		Waiting,
		
		//已处理
		Disposed
	}
	
	//商家id
	private Integer shopId;
	
	//状态
	private OrderShop.Status status;
	
	//是否默认
	private Integer isDefalut;
	
	/** 附近门店id */
	private String nearShopId;

	@Column(name="shop_id")
	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	@Column(name="status")
	public OrderShop.Status getStatus() {
		return status;
	}

	public void setStatus(OrderShop.Status status) {
		this.status = status;
	}

	@Column(name="is_default")
	public Integer getIsDefalut() {
		return isDefalut;
	}

	public void setIsDefalut(Integer isDefalut) {
		this.isDefalut = isDefalut;
	}
	
	@Column(name="near_shop_id")
	public String getNearShopId() {
		return nearShopId;
	}

	public void setNearShopId(String nearShopId) {
		this.nearShopId = nearShopId;
	}

}
