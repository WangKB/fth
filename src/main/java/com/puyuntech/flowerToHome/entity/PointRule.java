package com.puyuntech.flowerToHome.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * 积分获得限制. 
 * Created on 2015-12-11 下午1:17:37 
 * @author 王凯斌
 */
@Entity
@Table(name="t_point_rule")
public class PointRule extends BaseEntity<Integer> {

	private static final long serialVersionUID = 8692647093247311628L;

	/**
	 * 类型
	 */
	public enum Type {

		/** 每日总额上限 */
		MaxSumDaily,

		/** 每日次数上限 */
		MaxCountDaily,

		/** 每次获得额度 */
		ValueEach,
	}
	
	private PointRule.Type type;
	
	/** 数值 */
	private Integer value;
	
	@Column(name="type",nullable=false)
	public PointRule.Type getType() {
		return type;
	}

	public void setType(PointRule.Type type) {
		this.type = type;
	}

	@Column(name="value",nullable=false)
	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

}
