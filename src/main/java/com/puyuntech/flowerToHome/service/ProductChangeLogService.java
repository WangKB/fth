
package com.puyuntech.flowerToHome.service;

import com.puyuntech.flowerToHome.entity.ProductChangeLog;


/**
 * 
 * Service - ProductChangeLog. 
 * Created on 2015-10-14 下午2:06:41 
 * @author 王凯斌
 */
public interface ProductChangeLogService extends BaseService<ProductChangeLog, Integer> {

		void check(ProductChangeLog productChangeLog,Integer actType,String auditMemo,Integer adminId);
}