package com.puyuntech.flowerToHome.dao.impl;

import org.springframework.stereotype.Repository;

import com.puyuntech.flowerToHome.dao.TagDao;
import com.puyuntech.flowerToHome.entity.Tag;

/**
 * 
 *Tag Dao . 
 * Created on 2015-9-24 下午4:29:17 
 * @author 施长成
 */
@Repository("tagDaoImpl")
public class TagDaoImpl extends BaseDaoImpl<Tag, Integer> implements TagDao {
	
}
