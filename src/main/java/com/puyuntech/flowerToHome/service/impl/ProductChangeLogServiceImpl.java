
package com.puyuntech.flowerToHome.service.impl;



import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.puyuntech.flowerToHome.dao.ProductDao;
import com.puyuntech.flowerToHome.dao.SnDao;
import com.puyuntech.flowerToHome.entity.Product;
import com.puyuntech.flowerToHome.entity.ProductChangeLog;
import com.puyuntech.flowerToHome.entity.Sn;
import com.puyuntech.flowerToHome.service.ProductChangeLogService;

/**
 * 
 * Service - ProductChangeLog. 
 * Created on 2015-10-14 下午2:39:03 
 * @author 王凯斌
 */
@Service("productChangeLogServiceImpl")
public class ProductChangeLogServiceImpl extends BaseServiceImpl<ProductChangeLog, Integer> implements ProductChangeLogService {
	
	@Resource(name = "productDaoImpl")
	private ProductDao producteDao;
	
	@Resource(name = "snDaoImpl")
	private SnDao snDao;
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void check(ProductChangeLog productChangeLog,Integer actType,String auditMemo,Integer adminId){
		
		switch(actType){
			case 1:
				productChangeLog.setApplicationState(ProductChangeLog.state.ApplyingTwo);
				productChangeLog.setAuditMemo1(auditMemo);
				productChangeLog.setAuditAdmin1(adminId);
				productChangeLog.setAuditDate1(new Date());
				update(productChangeLog);
			break;
			case 2:
				try {
					Product product = new Product();
					BeanUtils.copyProperties(product, productChangeLog);
					product.setCreateDate(null);
					product.setModifyDate(null);
					product.setId(null);
					product.setSn(snDao.generate(Sn.Type.goods));
					producteDao.persist(product);
					
					productChangeLog.setApplicationState(ProductChangeLog.state.Pass);
					productChangeLog.setAuditMemo2(auditMemo);
					productChangeLog.setProductId(product.getId());
					productChangeLog.setAuditAdmin2(adminId);
					productChangeLog.setAuditDate2(new Date());
					update(productChangeLog);
				} catch (Exception e) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				}
				
			break;
		}
	}
}