package com.puyuntech.flowerToHome.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.puyuntech.flowerToHome.dao.OrganizationDao;
import com.puyuntech.flowerToHome.entity.Organization;
import com.puyuntech.flowerToHome.entity.ShopAudit;
import com.puyuntech.flowerToHome.service.ShopAuditService;

/**
 * Greetingcard Service .
 * Created on 2015-9-24 下午4:25:47 
 * @author 施长成
 */
@Service("shopAuditServiceImpl")
public class ShopAuditServiceImpl extends BaseServiceImpl<ShopAudit, Integer> implements ShopAuditService {

	@Resource(name = "organizationDaoImpl")
	private OrganizationDao organizationDao;
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void check(ShopAudit shopAudit,Integer actType,String auditMemo,Integer adminId){
		
		switch(actType){
			case 1:
				shopAudit.setApplicationState(ShopAudit.state.ApplyingTwo);
				shopAudit.setAuditMemo1(auditMemo);
				shopAudit.setAuditAdmin1(adminId);
				shopAudit.setAuditDate1(new Date());
				update(shopAudit);
			break;
			case 2:
				shopAudit.setApplicationState(ShopAudit.state.Pass);
				shopAudit.setAuditMemo2(auditMemo);
				shopAudit.setAuditAdmin2(adminId);
				shopAudit.setAuditDate2(new Date());
				update(shopAudit);
				Organization organization = organizationDao.find(shopAudit.getShopId());
				organization.setName(shopAudit.getName());
				organization.setTel(shopAudit.getTel());
				organization.setEmail(shopAudit.getEmail());
				organization.setOpeningStart(shopAudit.getOpeningStart());
				organization.setOpeningEnd(shopAudit.getOpeningEnd());
				organization.setAddress(shopAudit.getAddress());
				organization.setIntro(shopAudit.getIntro());
				organization.setPaymentDate(shopAudit.getPaymentDate());
				organization.setImage(shopAudit.getImage());
				organizationDao.merge(organization);
			break;
		}
	}
}
