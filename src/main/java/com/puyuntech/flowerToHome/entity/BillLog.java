package com.puyuntech.flowerToHome.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.search.annotations.Indexed;

/**
 * 
 * Entity - 积分记录. 
 * Created on 2015-8-26 下午5:24:02 
 * @author 王凯斌
 */
@Indexed
@Entity
@Table(name = "t_bill_log")
public class BillLog extends BaseEntity<Integer> {
	
	private Integer shopId;
	
	private Integer sentState;
	
	private Integer confirmState;
	
	private String confirmMemo;

	@Column(name="shop_id")
	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	@Column(name="sent_state")
	public Integer getSentState() {
		return sentState;
	}

	public void setSentState(Integer sentState) {
		this.sentState = sentState;
	}

	@Column(name="confirm_state")
	public Integer getConfirmState() {
		return confirmState;
	}

	public void setConfirmState(Integer confirmState) {
		this.confirmState = confirmState;
	}

	@Column(name="confirm_memo")
	public String getConfirmMemo() {
		return confirmMemo;
	}

	public void setConfirmMemo(String confirmMemo) {
		this.confirmMemo = confirmMemo;
	}
	
	
}
