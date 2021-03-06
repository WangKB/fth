package com.puyuntech.flowerToHome.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * 序列号  Entity . 
 * Created on 2015-8-27 上午11:52:32 
 * @author 王凯斌
 */
@Entity
@Table(name = "t_sn")
public class Sn extends BaseEntity<Integer> {

	private static final long serialVersionUID = -6794536665908329486L;

	/**
	 * 类型
	 */
	public enum Type {

		/** 货品 */
		goods,

		/** 订单 */
		order,

		/** 支付记录 */
		paymentLog,

		/** 收款单 */
		payment,

		/** 退款单 */
		refunds,

		/** 发货单 */
		shipping,

		/** 退货单 */
		returns
	}

	/** 类型 */
	private Sn.Type type;

	/** 末值 */
	private Integer lastValue;

	/**
	 * 获取类型
	 * 
	 * @return 类型
	 */
	@Column(name="type",nullable = false, updatable = false, unique = true)
	public Sn.Type getType() {
		return type;
	}

	/**
	 * 设置类型
	 * 
	 * @param type
	 *            类型
	 */
	public void setType(Sn.Type type) {
		this.type = type;
	}

	/**
	 * 获取末值
	 * 
	 * @return 末值
	 */
	@Column(name="last_value",nullable = false)
	public Integer getLastValue() {
		return lastValue;
	}

	/**
	 * 设置末值
	 * 
	 * @param lastValue
	 *            末值
	 */
	public void setLastValue(Integer lastValue) {
		this.lastValue = lastValue;
	}

}
