package com.puyuntech.flowerToHome.service.impl;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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
	public void check(ShopAudit shopAudit,Integer actType,String auditMemo){
		
		switch(actType){
			case 1:
				shopAudit.setApplicationState(ShopAudit.state.ApplyingTwo);
				shopAudit.setAuditMemo1(auditMemo);
				update(shopAudit);
			break;
			case 2:
				shopAudit.setApplicationState(ShopAudit.state.Pass);
				shopAudit.setAuditMemo2(auditMemo);
				update(shopAudit);
				Organization organization = new Organization();
				try {
					BeanUtils.copyProperties(organization, shopAudit);
					organization.setCreateDate(null);
					organization.setModifyDate(null);
					organization.setId(null);
					organizationDao.persist(organization);
					
				} catch (Exception e) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				}
				
			break;
		}
	}
}
