package com.puyuntech.flowerToHome.dao;

import com.puyuntech.flowerToHome.entity.PaymentLog;

/**
 *  Dao - 支付记录
 * Created on 2015-10-23 下午1:55:01 
 * @author 王凯斌
 */
 
public interface PaymentLogDao extends BaseDao<PaymentLog, Integer> {

	/**
	 * 根据编号查找支付记录
	 * 
	 * @param sn
	 *            编号(忽略大小写)
	 * @return 支付记录，若不存在则返回null
	 */
	PaymentLog findBySn(String sn);

}