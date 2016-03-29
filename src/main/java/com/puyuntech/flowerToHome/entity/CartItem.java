package com.puyuntech.flowerToHome.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 *  Entity - 购物车项 . 
 * Created on 2015-8-26 下午1:53:13 
 * @author 施长成
 */
@Entity
@Table(name = "t_cart_item")
public class CartItem extends BaseEntity<Integer> {

	private static final long serialVersionUID = 7723575831051854260L;

	//数量
	private Integer quantity;

	//购物车id
	private Integer cartId;
	
	//商品id
	private Integer productId;
	
	//商家id
	private Integer shopId;
	
	/** 附近门店id */
	private Integer nearShopId;

	@Column(name="product_id")
	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	@Column(name="cart_id")
	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	@Column(name="shop_id")
	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	

	/**
	 * 获取数量
	 * 
	 * @return 数量
	 */
	@Column(name="quantity",nullable = false)
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * 设置数量
	 * 
	 * @param quantity
	 *            数量
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Column(name="near_shop_id")
	public Integer getNearShopId() {
		return nearShopId;
	}

	public void setNearShopId(Integer nearShopId) {
		this.nearShopId = nearShopId;
	}
}
