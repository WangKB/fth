package com.puyuntech.flowerToHome.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.puyuntech.flowerToHome.dao.PaymentLogDao;
import com.puyuntech.flowerToHome.dao.SnDao;
import com.puyuntech.flowerToHome.entity.PaymentLog;
import com.puyuntech.flowerToHome.entity.Sn;
import com.puyuntech.flowerToHome.service.MemberService;
import com.puyuntech.flowerToHome.service.PaymentLogService;

/**
 * Service - 支付记录* Created on 2015-10-23 下午1:43:44
 * 
 * @author 王凯斌
 */

@Service("paymentLogServiceImpl")
public class PaymentLogServiceImpl extends BaseServiceImpl<PaymentLog, Integer>
		implements PaymentLogService {

	@Resource(name = "paymentLogDaoImpl")
	private PaymentLogDao paymentLogDao;
	@Resource(name = "snDaoImpl")
	private SnDao snDao;
	@Resource(name = "memberServiceImpl")
	private MemberService memberService;

	/**
	 * 根据编号查找支付记录
	 * 
	 * @param sn
	 *            编号(忽略大小写)
	 * @return 支付记录，若不存在则返回null
	 */
	@Transactional(readOnly = true)
	public PaymentLog findBySn(String sn) {
		return paymentLogDao.findBySn(sn);
	}

	/**
	 * 支付处理
	 *
	 * @param paymentLog
	 *            支付记录
     * @param buyerInfo
	 */
	public void handle(PaymentLog paymentLog, String buyerInfo) {
		
	}

	/**
	 * 
	 * 保存支付记录
	 * 
	 * @param paymentLog
	 * @return
	 * @see com.puyuntech.ycmall.service.impl.BaseServiceImpl#save(com.puyuntech.ycmall.entity.BaseEntity)
	 */
	@Override
	@Transactional
	public PaymentLog save(PaymentLog paymentLog) {
		// 验证pagmentLog是否为空
		Assert.notNull(paymentLog);
		paymentLog.setSn(snDao.generate(Sn.Type.paymentLog));
		return super.save(paymentLog);
	}

}