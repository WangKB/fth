package com.puyuntech.flowerToHome.service;

import com.puyuntech.flowerToHome.entity.ShopAudit;

/**
 * 
 * ShopAudit Service . 
 * Created on 2015-9-24 下午4:22:54 
 * @author 施长成
 */
public interface ShopAuditService extends BaseService<ShopAudit, Integer> {

	void check(ShopAudit shopAudit,Integer actType,String auditMemo);
}
