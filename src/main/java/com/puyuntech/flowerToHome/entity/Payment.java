package com.puyuntech.flowerToHome.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * 
 * Entity - 收款单 . 
 * Created on 2015-8-28 下午5:50:16 
 * @author 王凯斌
 */
@Entity
@Table(name = "t_payment")
public class Payment extends BaseEntity<Integer> {

	private static final long serialVersionUID = -3279119834096319141L;

	/**
	 * 方式
	 */
	public enum Method {

		/** 在线支付 */
		online,

		/** 线下支付 */
		offline,

		/** 预存款支付 */
		deposit
	}

	/** 编号 */
	private String sn;

	/** 方式 */
	private Payment.Method method;

	/** 支付方式 */
	private String paymentMethod;

	/** 收款银行 */
	private String bank;

	/** 收款账号 */
	private String account;

	/** 支付手续费 */
	private BigDecimal fee;

	/** 付款金额 */
	private BigDecimal amount;

	/** 付款人 */
	private String payer;

	/** 操作员 */
	private String operator;

	/** 备注 */
	private String memo;

	/** 订单 */
	private Integer order;

	/**
	 * 获取编号
	 * 
	 * @return 编号
	 */
	@Column(name="sn",nullable = false, updatable = false, unique = true)
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
	 * 获取方式
	 * 
	 * @return 方式
	 */
	@NotNull
	@Column(name="method",nullable = false, updatable = false)
	public Payment.Method getMethod() {
		return method;
	}

	/**
	 * 设置方式
	 * 
	 * @param method
	 *            方式
	 */
	public void setMethod(Payment.Method method) {
		this.method = method;
	}

	/**
	 * 获取支付方式
	 * 
	 * @return 支付方式
	 */
	@Column(name="payment_method",updatable = false)
	public String getPaymentMethod() {
		return paymentMethod;
	}

	/**
	 * 设置支付方式
	 * 
	 * @param paymentMethod
	 *            支付方式
	 */
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	/**
	 * 获取收款银行
	 * 
	 * @return 收款银行
	 */
	@Length(max = 200)
	@Column(name="bank",updatable = false)
	public String getBank() {
		return bank;
	}

	/**
	 * 设置收款银行
	 * 
	 * @param bank
	 *            收款银行
	 */
	public void setBank(String bank) {
		this.bank = bank;
	}

	/**
	 * 获取收款账号
	 * 
	 * @return 收款账号
	 */
	@Length(max = 200)
	@Column(name="account",updatable = false)
	public String getAccount() {
		return account;
	}

	/**
	 * 设置收款账号
	 * 
	 * @param account
	 *            收款账号
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * 获取支付手续费
	 * 
	 * @return 支付手续费
	 */
	@Column(name="fee",nullable = false, updatable = false, precision = 21, scale = 6)
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
	 * 获取付款金额
	 * 
	 * @return 付款金额
	 */
	@NotNull
	@Min(0)
	@Digits(integer = 12, fraction = 3)
	@Column(name="amount",nullable = false, updatable = false, precision = 21, scale = 6)
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * 设置付款金额
	 * 
	 * @param amount
	 *            付款金额
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * 获取付款人
	 * 
	 * @return 付款人
	 */
	@Length(max = 200)
	@Column(name="payer",updatable = false)
	public String getPayer() {
		return payer;
	}

	/**
	 * 设置付款人
	 * 
	 * @param payer
	 *            付款人
	 */
	public void setPayer(String payer) {
		this.payer = payer;
	}

	/**
	 * 获取操作员
	 * 
	 * @return 操作员
	 */
	@Column(name="operator",updatable = false)
	public String getOperator() {
		return operator;
	}

	/**
	 * 设置操作员
	 * 
	 * @param operator
	 *            操作员
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * 获取备注
	 * 
	 * @return 备注
	 */
	@Length(max = 200)
	@Column(name="memo",updatable = false)
	public String getMemo() {
		return memo;
	}

	/**
	 * 设置备注
	 * 
	 * @param memo
	 *            备注
	 */
	public void setMemo(String memo) {
		this.memo = memo;
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

	/**
	 * 设置支付方式
	 * 
	 * @param paymentMethod
	 *            支付方式
	 */
	@Transient
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		setPaymentMethod(paymentMethod != null ? paymentMethod.getName() : null);
	}

	/**
	 * 设置操作员
	 * 
	 * @param operator
	 *            操作员
	 */
	@Transient
	public void setOperator(Admin operator) {
		setOperator(operator != null ? operator.getWebUsername() : null);
	}

}
