
package com.puyuntech.flowerToHome.service.impl;



import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.puyuntech.flowerToHome.dao.ProductDao;
import com.puyuntech.flowerToHome.dao.SnDao;
import com.puyuntech.flowerToHome.entity.Product;
import com.puyuntech.flowerToHome.entity.Sn;
import com.puyuntech.flowerToHome.service.ProductService;

/**
 * 
 * Service - Product. 
 * Created on 2015-10-14 下午2:39:03 
 * @author 王凯斌
 */
@Service("productServiceImpl")
public class ProductServiceImpl extends BaseServiceImpl<Product, Integer> implements ProductService {
	
	@Resource(name = "productDaoImpl")
	private ProductDao productDao;
	
	@Resource(name = "snDaoImpl")
	private SnDao snDao;
	
	@Transactional
	public Product save(Product product) {
		
		Assert.notNull(product);
		product.setSn(snDao.generate(Sn.Type.goods));
		return super.save(product);
	}
	
	@Transactional(readOnly = true)
	public List<Product> search(String keyword, Set<Product> excludes, Integer count) {
		return productDao.search(keyword, excludes, count);
	}
}