package com.puyuntech.flowerToHome.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "t_message_config")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_message_config")
public class MessageConfig extends BaseEntity<Integer> {

	private static final long serialVersionUID = -4279536745372995559L;

	/**
	 * 类型
	 */
	public enum Type {

		/** 会员注册 */
		registerMember,

		/** 订单创建 */
		createOrder,

		/** 订单更新 */
		updateOrder,

		/** 订单取消 */
		cancelOrder,

		/** 订单审核 */
		reviewOrder,

		/** 订单收款 */
		paymentOrder,

		/** 订单退款 */
		refundsOrder,

		/** 订单发货 */
		shippingOrder,

		/** 订单退货 */
		returnsOrder,

		/** 订单收货 */
		receiveOrder,

		/** 订单完成 */
		completeOrder,

		/** 订单失败 */
		failOrder
	}

	/** 类型 */
	private MessageConfig.Type type;

	/** 是否启用邮件 */
	private Integer isMailEnabled;

	/** 是否启用短信 */
	private Integer isSmsEnabled;

	/**
	 * 获取类型
	 * 
	 * @return 类型
	 */
	@Column(nullable = false, updatable = false, unique = true)
	public MessageConfig.Type getType() {
		return type;
	}

	/**
	 * 设置类型
	 * 
	 * @param type
	 *            类型
	 */
	public void setType(MessageConfig.Type type) {
		this.type = type;
	}

	/**
	 * 获取是否启用邮件
	 * 
	 * @return 是否启用邮件
	 */
	@NotNull
	@Column(nullable = false)
	public Integer getIsMailEnabled() {
		return isMailEnabled;
	}

	/**
	 * 设置是否启用邮件
	 * 
	 * @param isMailEnabled
	 *            是否启用邮件
	 */
	public void setIsMailEnabled(Integer isMailEnabled) {
		this.isMailEnabled = isMailEnabled;
	}

	/**
	 * 获取是否启用短信
	 * 
	 * @return 是否启用短信
	 */
	@NotNull
	@Column(nullable = false)
	public Integer getIsSmsEnabled() {
		return isSmsEnabled;
	}

	/**
	 * 设置是否启用短信
	 * 
	 * @param isSmsEnabled
	 *            是否启用短信
	 */
	public void setIsSmsEnabled(Integer isSmsEnabled) {
		this.isSmsEnabled = isSmsEnabled;
	}

}
