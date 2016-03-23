package com.puyuntech.flowerToHome.service;

import java.util.List;

import com.puyuntech.flowerToHome.entity.Coupon;
import com.puyuntech.flowerToHome.entity.ProductCoupon;


/**
 * 
 * Service - 优惠券. 
 * Created on 2015-10-10 上午10:54:16 
 * @author 严志森
 */
public interface CouponService extends BaseService<Coupon, Integer> {
	
	Coupon save(Coupon coupon,Integer[] productIds);
	
	Coupon update(Coupon coupon,Integer[] productIds,List<ProductCoupon> productCoupons);
}