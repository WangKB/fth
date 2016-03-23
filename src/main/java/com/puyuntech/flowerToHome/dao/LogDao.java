package com.puyuntech.flowerToHome.dao;

import com.puyuntech.flowerToHome.entity.Log;

/***
 * 
 * 日志 Dao . 
 * Created on 2015-9-28 下午3:19:45 
 * @author 王凯斌
 */
public interface LogDao extends BaseDao<Log, Integer> {

	/**
	 * 删除所有日志
	 */
	void removeAll();
	
}
