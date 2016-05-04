
package com.puyuntech.flowerToHome.service.impl;



import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.puyuntech.flowerToHome.dao.ProductDao;
import com.puyuntech.flowerToHome.dao.ProductShopDao;
import com.puyuntech.flowerToHome.dao.SnDao;
import com.puyuntech.flowerToHome.entity.Product;
import com.puyuntech.flowerToHome.entity.ProductChangeLog;
import com.puyuntech.flowerToHome.entity.ProductShop;
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
	
	@Resource(name = "productShopDaoImpl")
	private ProductShopDao productShopDao;
	
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
				productChangeLog.setApplicationState(ProductChangeLog.state.Pass);
				productChangeLog.setAuditMemo2(auditMemo);
				productChangeLog.setAuditAdmin2(adminId);
				productChangeLog.setAuditDate2(new Date());
				try {
					if(productChangeLog.getType().equals(ProductChangeLog.Type.ADD)){
						Product product = new Product();
						BeanUtils.copyProperties(product, productChangeLog);
						product.setCreateDate(null);
						product.setModifyDate(null);
						product.setId(null);
						product.setSn(snDao.generate(Sn.Type.goods));
						product.setIsMarketable(1);
						product.setIsList(1);
						producteDao.persist(product);
						
						ProductShop productShop = new ProductShop();
						productShop.setProductId(product.getId());
						if(productChangeLog.getIsStandard()==1){
							productShop.setShopId(1);
						}else{
							productShop.setShopId(productChangeLog.getShopId());
						}
						productShopDao.persist(productShop);
						
						productChangeLog.setProductId(product.getId());
					}else{
						Product product = producteDao.find(productChangeLog.getProductId());
						BeanUtils.copyProperties(product, productChangeLog);
						product.setId(productChangeLog.getProductId());
						producteDao.merge(product);
					}
				} catch (Exception e) {
					e.printStackTrace();
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				}
				update(productChangeLog);
			break;
		}
	}
}