package com.puyuntech.flowerToHome.dao.impl;

import javax.persistence.NoResultException;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.puyuntech.flowerToHome.dao.PaymentLogDao;
import com.puyuntech.flowerToHome.entity.PaymentLog;

/**
 * Dao - 支付记录
 * Created on 2015-10-23 下午1:56:29 
 * @author 王凯斌
 */

@Repository("paymentLogDaoImpl")
public class PaymentLogDaoImpl extends BaseDaoImpl<PaymentLog, Integer> implements PaymentLogDao {

	/**
	 * 根据编号查找支付记录
	 * 
	 * @param sn
	 *            编号(忽略大小写)
	 * @return 支付记录，若不存在则返回null
	 */
	public PaymentLog findBySn(String sn) {
		if (StringUtils.isEmpty(sn)) {
			return null;
		}
		String jpql = "select paymentLog from PaymentLog paymentLog where lower(paymentLog.sn) = lower(:sn)";
		try {
			return entityManager.createQuery(jpql, PaymentLog.class).setParameter("sn", sn).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}