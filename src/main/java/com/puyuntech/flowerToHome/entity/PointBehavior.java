package com.puyuntech.flowerToHome.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 *  积分获得场景. 
 * Created on 2015-12-11 下午1:17:37 
 * @author 王凯斌
 */
@Entity
@Table(name="t_point_behavior")
public class PointBehavior extends BaseEntity<Integer> {

	private static final long serialVersionUID = -4030966034853116810L;

	/** 名字 */
	private String name;
	
	@Column(name="name",nullable=false,updatable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
