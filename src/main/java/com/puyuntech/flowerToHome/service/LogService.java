package com.puyuntech.flowerToHome.service;

import com.puyuntech.flowerToHome.entity.Log;

/**
 * 
 * Service 日志 . 
 * Created on 2015-9-28 下午3:16:49 
 * @author 王凯斌
 */
public interface LogService extends BaseService<Log, Integer> {

	/**
	 * 清空日志
	 */
	void clear();
	
}
