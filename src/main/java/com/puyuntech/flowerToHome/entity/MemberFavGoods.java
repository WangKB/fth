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
@Table(name = "t_member_favorite_goods")
public class MemberFavGoods extends BaseEntity<Integer> {
	
	public enum Type{
		
		//商家
		Bussine,
		
		//店铺
		Shop
	}
	//商品id
	private Integer productId;
	
	//商家id
	private Integer shopId;
	
	private MemberFavGoods.Type type;

	@Column(name="favorite_goods_id")
	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	@Column(name="shop_id")
	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	@Column(name="favorite_type")
	public MemberFavGoods.Type getType() {
		return type;
	}

	public void setType(MemberFavGoods.Type type) {
		this.type = type;
	}
	
	
}
