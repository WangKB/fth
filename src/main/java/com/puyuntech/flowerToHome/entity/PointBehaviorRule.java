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
@Table(name = "t_point_behavior_point_rule")
public class PointBehaviorRule extends BaseEntity<Integer> {
	
	//商品id
	private Integer pointBehavior;
	
	//商家id
	private Integer pointRule;

	@Column(name="point_behavior")
	public Integer getPointBehavior() {
		return pointBehavior;
	}

	public void setPointBehavior(Integer pointBehavior) {
		this.pointBehavior = pointBehavior;
	}

	@Column(name="point_rule")
	public Integer getPointRule() {
		return pointRule;
	}

	public void setPointRule(Integer pointRule) {
		this.pointRule = pointRule;
	}

}
