package com.puyuntech.flowerToHome.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.puyuntech.flowerToHome.dao.ProductCouponDao;
import com.puyuntech.flowerToHome.entity.Coupon;
import com.puyuntech.flowerToHome.entity.ProductCoupon;
import com.puyuntech.flowerToHome.service.CouponService;

/**
 * 
 * Service - 优惠券. Created on 2015-10-10 上午10:57:34
 * 
 * @author 严志森
 */
@Service("couponServiceImpl")
public class CouponServiceImpl extends BaseServiceImpl<Coupon, Integer> implements
		CouponService {
	
	@Resource(name = "productCouponDaoImpl")
	private ProductCouponDao productCouponDao;
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public Coupon save(Coupon coupon,Integer[] productIds){
		
		save(coupon);
		
		if(productIds!=null){
			ProductCoupon productCoupon=null;
			for(Integer id:productIds){
				productCoupon=new ProductCoupon();
				productCoupon.setCouponsId(coupon.getId());
				productCoupon.setProductId(id);
				productCouponDao.persist(productCoupon);
			}
		}
		
		return coupon;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public Coupon update(Coupon coupon,Integer[] productIds,List<ProductCoupon> productCoupons){
		
		update(coupon, "orders");
		if(productIds==null){
			for(ProductCoupon productCoupon:productCoupons){
				productCouponDao.remove(productCoupon);
			}
			return coupon;
		}
		Set<Integer> newIds = new HashSet<Integer>(Arrays.asList(productIds));
		
		Map<Integer,Integer> formerMap = new HashMap<Integer,Integer>();
		for(ProductCoupon productCoupon:productCoupons){
			formerMap.put(productCoupon.getProductId(), productCoupon.getId());
		}
		
		Set<Integer> delIds=new HashSet<Integer>(formerMap.keySet());
		delIds.removeAll(newIds);
		for(Integer delId:delIds){
			productCouponDao.remove(productCouponDao.find(formerMap.get(delId)));
		}
		
		Set<Integer> formerIds=new HashSet<Integer>(formerMap.keySet());
		newIds.removeAll(formerIds);
		ProductCoupon productCoupon=null;
		for(Integer id:newIds){
			productCoupon=new ProductCoupon();
			productCoupon.setCouponsId(coupon.getId());
			productCoupon.setProductId(id);
			productCouponDao.persist(productCoupon);
		}
		
		return coupon;
	}
}