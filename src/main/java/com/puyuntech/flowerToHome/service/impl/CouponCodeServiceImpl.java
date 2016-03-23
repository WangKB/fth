package com.puyuntech.flowerToHome.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;

import com.puyuntech.flowerToHome.dao.CouponCodeDao;
import com.puyuntech.flowerToHome.entity.Coupon;
import com.puyuntech.flowerToHome.entity.CouponCode;
import com.puyuntech.flowerToHome.entity.Member;
import com.puyuntech.flowerToHome.service.CouponCodeService;

/**
 * 
 * Service - 优惠码. 
 * Created on 2015-10-10 上午11:04:45 
 * @author 严志森
 */
@Service("couponCodeServiceImpl")
public class CouponCodeServiceImpl extends BaseServiceImpl<CouponCode, Integer> implements CouponCodeService {
	
	@Resource(name = "couponCodeDaoImpl")
	private CouponCodeDao couponCodeDao;
	
	public CouponCode generate(Coupon coupon, Member member) {

		CouponCode couponCode = new CouponCode();
		couponCode.setCode(coupon.getPrefix() + DigestUtils.md5Hex(UUID.randomUUID() + RandomStringUtils.randomAlphabetic(30)).toUpperCase());
		couponCode.setIsUsed(0);
		couponCode.setCouponId(coupon.getId());
		couponCode.setMemberId(member!=null?member.getId():null);
		return super.save(couponCode);
	}

	public List<CouponCode> generate(Coupon coupon, Member member, Integer count) {
		List<CouponCode> couponCodes = new ArrayList<CouponCode>();
		for (int i = 0; i < count; i++) {
			CouponCode couponCode = generate(coupon, member);
			couponCodes.add(couponCode);
			if (i % 50 == 0) {
				couponCodeDao.flush();
				couponCodeDao.clear();
			}
		}
		return couponCodes;
	}
}