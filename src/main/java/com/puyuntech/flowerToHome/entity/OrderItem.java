package com.puyuntech.flowerToHome.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 
 * Entity - 订单项 . 
 * Created on 2015-8-27 下午4:09:49 
 * @author 王凯斌
 */
@Entity
@Table(name = "t_order_item")
public class OrderItem extends BaseEntity<Integer> {

	private static final long serialVersionUID = -5622329816683797837L;

	/** 商品编号 */
	private String sn;

	/** 商品名称 */
	private String name;

	/** 商品价格 */
	private BigDecimal price;

	/** 商品数量 */
	private Integer quantity;
	
	//商品id
	private Integer productId;
	
	//订单id
	private Integer orderId;
	
	//是否评论
	private Integer isComment;
		


	/**
	 * 获取商品编号
	 * 
	 * @return 商品编号
	 */
	@Column(name="product_sn",nullable = false, updatable = false)
	public String getSn() {
		return sn;
	}

	/**
	 * 设置商品编号
	 * 
	 * @param sn
	 *            商品编号
	 */
	public void setSn(String sn) {
		this.sn = sn;
	}

	/**
	 * 获取商品名称
	 * 
	 * @return 商品名称
	 */
	@Column(name="name",nullable = false, updatable = false)
	public String getName() {
		return name;
	}

	/**
	 * 设置商品名称
	 * 
	 * @param name
	 *            商品名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取商品价格
	 * 
	 * @return 商品价格
	 */
	@Column(name="price",nullable = false, updatable = false, precision = 21, scale = 6)
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * 设置商品价格
	 * 
	 * @param price
	 *            商品价格
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	/**
	 * 获取商品数量
	 * 
	 * @return 商品数量
	 */
	@Column(name="quantity",nullable = false, updatable = false)
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * 设置商品数量
	 * 
	 * @param quantity
	 *            商品数量
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	/**
	 * 获取小计
	 * 
	 * @return 小计
	 */
	@Transient
	public BigDecimal getSubtotal() {
		if (getPrice() != null && getQuantity() != null) {
			return getPrice().multiply(new BigDecimal(getQuantity()));
		} else {
			return BigDecimal.ZERO;
		}
	}

	@Column(name="product_id")
	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	@Column(name="order_id")
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	@Column(name="is_comment")
	public Integer getIsComment() {
		return isComment;
	}

	public void setIsComment(Integer isComment) {
		this.isComment = isComment;
	}

	
}
