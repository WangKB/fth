package com.puyuntech.flowerToHome.dao;

import java.util.List;
import java.util.Set;

import com.puyuntech.flowerToHome.entity.Product;

/**
 * 
 * 商品 Dao . 
 * Created on 2015-9-6 上午10:39:21 
 * @author 王凯斌
 */
public interface ProductDao extends BaseDao<Product, Integer> {
	
	List<Product> search(String keyword, Set<Product> excludes, Integer count);
	
}
