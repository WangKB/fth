package com.puyuntech.flowerToHome.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.puyuntech.flowerToHome.dao.OrganizationDao;
import com.puyuntech.flowerToHome.entity.Organization;
import com.puyuntech.flowerToHome.entity.Product;
import com.puyuntech.flowerToHome.entity.ProductChangeLog;
import com.puyuntech.flowerToHome.entity.ProductShop;
import com.puyuntech.flowerToHome.entity.ShopAudit;
import com.puyuntech.flowerToHome.entity.Sn;
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
				try {
					if(shopAudit.getType().equals(ShopAudit.Type.ADD)){
						Organization organizaiton = new Organization();
						BeanUtils.copyProperties(organizaiton, shopAudit);
						organizaiton.setCreateDate(null);
						organizaiton.setModifyDate(null);
						organizaiton.setId(null);
						organizaiton.setIsOpen(1);
						organizationDao.persist(organizaiton);
						
						shopAudit.setShopId(organizaiton.getId());
					}else{
						Organization organizaiton = organizationDao.find(shopAudit.getShopId());
						BeanUtils.copyProperties(organizaiton, shopAudit);
						organizaiton.setId(shopAudit.getShopId());
						organizationDao.merge(organizaiton);
					}
				} catch (Exception e) {
					e.printStackTrace();
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				}
			break;
		}
	}
}
