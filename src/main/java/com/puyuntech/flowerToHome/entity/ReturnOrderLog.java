package com.puyuntech.flowerToHome.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;


@Entity
@Table(name = "t_return_order_log")
public class ReturnOrderLog extends BaseEntity<Integer> {
	
	private static final long serialVersionUID = 3625604402946055518L;

	/**
	 * 类型
	 */
	public enum Type {

		/** 确认 0*/
		confirm,

		/** 处理 1*/
		deal,
		
		/** 完成 2**/
		complete,
		
	}
	
	private ReturnOrderLog.Type type;
	
	private Integer operator;
	
	private String content;
	
	private Integer order;
	
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
	private String payee;
	
	
	@Column(name="type",nullable = false)
	public ReturnOrderLog.Type getType() {
		return type;
	}

	public void setType(ReturnOrderLog.Type type) {
		this.type = type;
	}

	@Column(name = "operator_id")
	public Integer getOperator() {
		return operator;
	}

	public void setOperator(Integer operator) {
		this.operator = operator;
	}

	@Column(name="content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
	@Column(name="payee",updatable = false)
	public String getPayee() {
		return payee;
	}

	/**
	 * 设置付款人
	 * 
	 * @param payer
	 *            付款人
	 */
	public void setPayee(String payee) {
		this.payee = payee;
	}

	@Column(name="order_id")
	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	
}
