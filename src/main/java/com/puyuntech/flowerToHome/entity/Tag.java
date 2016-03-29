package com.puyuntech.flowerToHome.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * Entity - 标签. 
 * Created on 2015-8-26 下午4:39:46 
 * @author Liaozhen
 */
@Entity
@Table(name = "t_tags")
public class Tag extends OrderEntity<Integer> {

	private static final long serialVersionUID = -2862087339060963041L;
	
	//名称
	private String name;

	//图标
	private String images;

	//介绍
	private String introduction;

	//商品id
	private Integer productId;
	
	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "images")
	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	@Column(name = "introduction")
	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	@Column(name = "product_id")
	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

}
