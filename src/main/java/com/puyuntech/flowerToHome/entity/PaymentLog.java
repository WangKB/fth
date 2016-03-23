package com.puyuntech.flowerToHome.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 
 * Entity - 支付记录. 
 * Created on 2015-8-27 下午4:05:36 
 * @author 王凯斌
 */
@Entity
@Table(name = "t_payment_log")
public class PaymentLog extends BaseEntity<Integer> {

	private static final long serialVersionUID = 982257779186778638L;

	/**
	 * 类型
	 */
	public enum Type {

		/** 预存款充值 */
		recharge,

		/** 订单支付 */
		payment,

        /** 维修支付 **/
        repair
	}

	/**
	 * 状态
	 */
	public enum Status {

		/** 等待支付 */
		wait,

		/** 支付成功 */
		success,

		/** 支付失败 */
		failure
	}

	/** 编号 */
	private String sn;

	/** 类型 */
	private PaymentLog.Type type;

	/** 状态 */
	private PaymentLog.Status status;

	/** 支付手续费 */
	private BigDecimal fee;

	/** 支付金额 */
	private BigDecimal amount;

	/** 支付插件ID */
	private String paymentPluginId;

	/** 支付插件名称 */
	private String paymentPluginName;

	/** 会员 */
	private Integer member;

	/** 订单 */
	private Integer order;

    /** 订单支付帐号 **/
    private String buyerInfo;

    /** ping++ 流水帐号 **/
    private String pingXXSN;

    /** 售后订单 **/
    private Integer returnOrder;

	/**
	 * 获取编号
	 * 
	 * @return 编号
	 */
	@Column(nullable = false, updatable = false, unique = true)
	public String getSn() {
		return sn;
	}

	/**
	 * 设置编号
	 * 
	 * @param sn
	 *            编号
	 */
	public void setSn(String sn) {
		this.sn = sn;
	}

	/**
	 * 获取类型
	 * 
	 * @return 类型
	 */
	@Column(nullable = false, updatable = false)
	public PaymentLog.Type getType() {
		return type;
	}

	/**
	 * 设置类型
	 * 
	 * @param type
	 *            类型
	 */
	public void setType(PaymentLog.Type type) {
		this.type = type;
	}

	/**
	 * 获取状态
	 * 
	 * @return 状态
	 */
	@Column(nullable = false)
	public PaymentLog.Status getStatus() {
		return status;
	}

	/**
	 * 设置状态
	 * 
	 * @param status
	 *            状态
	 */
	public void setStatus(PaymentLog.Status status) {
		this.status = status;
	}

	/**
	 * 获取支付手续费
	 * 
	 * @return 支付手续费
	 */
	@Column(nullable = false, updatable = false, precision = 21, scale = 6)
	public BigDecimal getFee() {
		return fee;
	}

	/**
	 * 设置支付手续费
	 * 
	 * @param fee
	 *            支付手续费
	 */
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	/**
	 * 获取支付金额
	 * 
	 * @return 支付金额
	 */
	@Column(nullable = false, updatable = false, precision = 21, scale = 6)
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * 设置支付金额
	 * 
	 * @param amount
	 *            支付金额
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * 获取支付插件ID
	 * 
	 * @return 支付插件ID
	 */
	@Column(nullable = false, updatable = false)
	public String getPaymentPluginId() {
		return paymentPluginId;
	}

	/**
	 * 设置支付插件ID
	 * 
	 * @param paymentPluginId
	 *            支付插件ID
	 */
	public void setPaymentPluginId(String paymentPluginId) {
		this.paymentPluginId = paymentPluginId;
	}

	/**
	 * 获取支付插件名称
	 * 
	 * @return 支付插件名称
	 */
	@Column(nullable = false, updatable = false)
	public String getPaymentPluginName() {
		return paymentPluginName;
	}

	/**
	 * 设置支付插件名称
	 * 
	 * @param paymentPluginName
	 *            支付插件名称
	 */
	public void setPaymentPluginName(String paymentPluginName) {
		this.paymentPluginName = paymentPluginName;
	}

	/**
	 * 获取会员
	 * 
	 * @return 会员
	 */
	@Column(name="member_id")
	public Integer getMember() {
		return member;
	}

	/**
	 * 设置会员
	 * 
	 * @param member
	 *            会员
	 */
	public void setMember(Integer member) {
		this.member = member;
	}

	/**
	 * 获取订单
	 * 
	 * @return 订单
	 */
	@Column(name="order_id")
	public Integer getOrder() {
		return order;
	}

	/**
	 * 设置订单
	 * 
	 * @param order
	 *            订单
	 */
	public void setOrder(Integer order) {
		this.order = order;
	}

	/**
	 * 获取有效金额
	 * 
	 * @return 有效金额
	 */
	@Transient
	public BigDecimal getEffectiveAmount() {
		BigDecimal effectiveAmount = getAmount().subtract(getFee());
		return effectiveAmount.compareTo(BigDecimal.ZERO) >= 0 ? effectiveAmount : BigDecimal.ZERO;
	}

    @Column(name="buyer_info")
    public String getBuyerInfo() {
        return buyerInfo;
    }

    public void setBuyerInfo(String buyerInfo) {
        this.buyerInfo = buyerInfo;
    }

    /**
     * PingXX  支付的流水帐号
     * @return
     */
    @Column(name="pingxx_sn")
    public String getPingXXSN() {
        return pingXXSN;
    }

    public void setPingXXSN(String pingXXSN) {
        this.pingXXSN = pingXXSN;
    }

    /**
     * 售后单
     *  类型为维修的订单状态
     * @return
     */
    @Column(name="return_order")
    public Integer getReturnOrder() {
        return returnOrder;
    }

    public void setReturnOrder(Integer returnOrder) {
        this.returnOrder = returnOrder;
    }
}
