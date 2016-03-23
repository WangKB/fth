package com.puyuntech.flowerToHome.service;


import java.util.List;

import com.puyuntech.flowerToHome.entity.Coupon;
import com.puyuntech.flowerToHome.entity.CouponCode;
import com.puyuntech.flowerToHome.entity.Member;


/**
 * 
 * Service - 优惠码. 
 * Created on 2015-10-10 上午10:51:59 
 * @author 严志森
 */
public interface CouponCodeService extends BaseService<CouponCode, Integer> {


	/**
	 * 生成优惠码
	 * 
	 * @param coupon
	 *            优惠券
	 * @param member
	 *            会员
	 * @return 优惠码
	 */
	CouponCode generate(Coupon coupon, Member member);

	/**
	 * 生成优惠码
	 * 
	 * @param coupon
	 *            优惠券
	 * @param member
	 *            会员
	 * @param count
	 *            数量
	 * @return 优惠码
	 */
	List<CouponCode> generate(Coupon coupon, Member member, Integer count);

}