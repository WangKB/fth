package com.puyuntech.flowerToHome.dao.impl;

import org.springframework.stereotype.Repository;

import com.puyuntech.flowerToHome.dao.LogDao;
import com.puyuntech.flowerToHome.entity.Log;

/**
 * 
 * Dao 日志 . 
 * Created on 2015-9-28 下午3:21:14 
 * @author 王凯斌
 */
@Repository("logDaoImpl")
public class LogDaoImpl extends BaseDaoImpl<Log, Integer> implements LogDao {

	public void removeAll() {
		String jpql = "delete from Log log";
		entityManager.createQuery(jpql).executeUpdate();
	}
	
}
