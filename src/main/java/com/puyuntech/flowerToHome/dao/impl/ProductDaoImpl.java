package com.puyuntech.flowerToHome.dao.impl;

import org.springframework.stereotype.Repository;

import com.puyuntech.flowerToHome.dao.ProductDao;
import com.puyuntech.flowerToHome.entity.Product;

/**
 * 
 * 商品 Dao. Created on 2015-9-6 上午10:40:22
 * 
 * @author 王凯斌
 */
@Repository("productDaoImpl")
public class ProductDaoImpl extends BaseDaoImpl<Product, Integer> implements
		ProductDao {
	
}
