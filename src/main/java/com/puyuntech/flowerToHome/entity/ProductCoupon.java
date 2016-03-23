package com.puyuntech.flowerToHome.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * 商品门店关联表.
 * Created on 2015-8-27 上午11:06:14 
 * @author 王凯斌
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_product_coupon")
public class ProductCoupon extends BaseEntity<Integer> {
	
	//商品id
	private Integer productId;
	
	//优惠券id
	private Integer couponsId;

	@Column(name="product_id")
	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	@Column(name="coupons_Id")
	public Integer getCouponsId() {
		return couponsId;
	}

	public void setCouponsId(Integer couponsId) {
		this.couponsId = couponsId;
	}
	
}
