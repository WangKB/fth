package com.puyuntech.flowerToHome.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * 商品规格 Entity .  菜单 规格管理
 * Created on 2015-8-27 上午11:06:14 
 * @author 施长成
 * 
 */
@Entity
@Table(name = "t_specification_value")
public class SpecificationValue extends OrderEntity<Integer> {

	private static final long serialVersionUID = -6873630703903474426L;

	public enum Specification{
		
		//0其他
		Other,
		
		//1目标 
		Target,
		
		//2花卉 
		Flower,
		
		//3花型
		Sort,
		
		//4设计
		Design,
		
		//5颜色 
		Color,
		
		//6系列
		Series,
		
		//7主题
		Theme
	}
	
	//名称
	private String specificationValue;
	
	//所属规格
	private SpecificationValue.Specification specification;

	@Column(name="specification_value")
	public String getSpecificationValue() {
		return specificationValue;
	}

	public void setSpecificationValue(String specificationValue) {
		this.specificationValue = specificationValue;
	}

	@Column(name="specification")
	public SpecificationValue.Specification getSpecification() {
		return specification;
	}

	public void setSpecification(SpecificationValue.Specification specification) {
		this.specification = specification;
	}

}
