package com.puyuntech.flowerToHome.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.search.annotations.Indexed;

/**
 * 
 * Entity - 积分记录. 
 * Created on 2015-8-26 下午5:24:02 
 * @author 王凯斌
 */
@Indexed
@Entity
@Table(name = "t_point_log")
public class PointLog extends BaseEntity<Integer> {

	private static final long serialVersionUID = 38194600348925811L;

	/**
	 * 类型
	 */
	public enum Type {

		/** 积分赠送 */
		reward,

		/** 积分调整 */
		adjustment,
		 
		 /**注册7*/
		 register,
		 
		 /**登录8*/
		 login,
		 
		 /**订单支付9*/
		 payment,
		 
	}
	
	/** 类型 */
	private PointLog.Type type;
	
	/** 获取积分 */
	private Integer credit;
	
	/** 扣除积分 */
	private Integer debit;
	
	/** 当前积分*/
	private Integer balance;
	
	/** 操作员 */
	private String operator;
	
	/** 备注 */
	private String memo;
	
	/** 会员id */
	private Integer memberId;
	
	/** 订单id */
	private Integer orderId;

	
	@Column(name = "type", nullable = false)
	public PointLog.Type getType(){
		return type;
	}
	
	public void setType(PointLog.Type type){
		this.type = type;
	}
	
	@Column(name = "credit", nullable = false)
	public Integer getCredit() {
		return credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	@Column(name = "debit", nullable = false)
	public Integer getDebit() {
		return debit;
	}

	public void setDebit(Integer debit) {
		this.debit = debit;
	}

	@Column(name = "balance", nullable = false)
	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	@Column(name = "operator")
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(name = "memo")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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

	@Column(name = "member_id")
	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	@Column(name = "order_id")
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	
}
