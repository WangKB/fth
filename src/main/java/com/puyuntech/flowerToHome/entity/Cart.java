package com.puyuntech.flowerToHome.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * Entity - 购物车 . Created on 2015-8-26 下午1:52:07
 * 
 * @author 施长成
 */
@Entity
@Table(name = "t_cart")
public class Cart extends BaseEntity<Integer> {

	private static final long serialVersionUID = -1497661248798010032L;

	// 过期时间
	private Date expire;

	//会员id
	private Integer memberId;
	
	/**
	 * 获取过期时间
	 * 
	 * @return 过期时间
	 */
	@Column(name = "expire", nullable = false)
	public Date getExpire() {
		return expire;
	}

	/**
	 * 设置过期时间
	 * 
	 * @param expire
	 *            过期时间
	 */
	public void setExpire(Date expire) {
		this.expire = expire;
	}

	@Column(name = "member_id", nullable = false)
	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

}
