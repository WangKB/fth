
package com.puyuntech.flowerToHome.service;

import java.util.List;
import java.util.Set;

import com.puyuntech.flowerToHome.entity.Product;


/**
 * 
 * Service - 商品. 
 * Created on 2015-10-14 下午2:06:41 
 * @author 王凯斌
 */
public interface ProductService extends BaseService<Product, Integer> {

	List<Product> search(String keyword, Set<Product> excludes, Integer count);
}