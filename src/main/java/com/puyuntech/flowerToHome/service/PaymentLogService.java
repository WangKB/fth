package com.puyuntech.flowerToHome.service;

import com.puyuntech.flowerToHome.entity.PaymentLog;



/**
 *  Service - 支付记录
 * Created on 2015-10-23 下午1:35:14 
 * @author 王凯斌
 */

public interface PaymentLogService extends BaseService<PaymentLog, Integer> {

	/**
	 * 根据编号查找支付记录
	 * 
	 * @param sn
	 *            编号(忽略大小写)
	 * @return 支付记录，若不存在则返回null
	 */
	PaymentLog findBySn(String sn);

	/**
	 * 支付处理
	 *
     * @param paymentLog
     *            支付记录
     * @param buyerInfo
     *          订单支付帐号
     *
     * @author shi.changcheng
     */
	void handle(PaymentLog paymentLog, String buyerInfo);

}