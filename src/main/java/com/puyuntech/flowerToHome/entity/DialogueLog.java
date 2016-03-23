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
@Entity
@Table(name = "t_dialogue_log")
public class DialogueLog extends BaseEntity<Integer> {
	
	//商品id
	private Integer user;
	
	//商家id
	private Integer orderId;
	
	private String content;
	
	private String memo;
	
	private Integer product_id;

	@Column(name="user")
	public Integer getUser() {
		return user;
	}

	public void setUser(Integer user) {
		this.user = user;
	}

	@Column(name="content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name="memo")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name="product_id")
	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	@Column(name="order_id")
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	
}
