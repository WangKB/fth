package com.puyuntech.flowerToHome.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PreRemove;
import javax.persistence.Table;

/**
 * Entity - 优惠码 . 
 * Created on 2015-8-27 下午3:08:43 
 * @author 王凯斌
 */
@Entity
@Table(name = "t_coupon_code")
public class CouponCode extends BaseEntity<Integer> {

	private static final long serialVersionUID = -8236930317127141217L;

	/** 号码 */
	private String code;

	/** 是否已使用 */
	private Integer isUsed;

	/** 使用日期 */
	private Date usedDate;
	
	/** 订单id */
	private Integer orderId;
	
	/** 会员id */
	private Integer memberId;
	
	/** 优惠券id */
	private Integer couponId;

	/**
	 * 获取号码
	 * 
	 * @return 号码
	 */
	@Column(name="code",nullable = false, updatable = false, unique = true)
	public String getCode() {
		return code;
	}
	

	/**
	 * 设置号码
	 * 
	 * @param code
	 *            号码
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 获取是否已使用
	 * 
	 * @return 是否已使用
	 */
	@Column(name="is_used",nullable = false)
	public Integer getIsUsed() {
		return isUsed;
	}

	/**
	 * 设置是否已使用
	 * 
	 * @param isUsed
	 *            是否已使用
	 */
	public void setIsUsed(Integer isUsed) {
		this.isUsed = isUsed;
	}

	/**
	 * 获取使用日期
	 * 
	 * @return 使用日期
	 */
	@Column(name="used_used")
	public Date getUsedDate() {
		return usedDate;
	}

	/**
	 * 设置使用日期
	 * 
	 * @param usedDate
	 *            使用日期
	 */
	public void setUsedDate(Date usedDate) {
		this.usedDate = usedDate;
	}

	/**
	 * 删除前处理
	 */
	@PreRemove
	public void preRemove() {
		
	}

	@Column(name="order_id")
	public Integer getOrderId() {
		return orderId;
	}


	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	@Column(name="member_id")
	public Integer getMemberId() {
		return memberId;
	}


	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	@Column(name="coupon_id")
	public Integer getCouponId() {
		return couponId;
	}


	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}

	
}
