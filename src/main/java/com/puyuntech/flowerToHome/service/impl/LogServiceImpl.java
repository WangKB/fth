package com.puyuntech.flowerToHome.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.puyuntech.flowerToHome.dao.LogDao;
import com.puyuntech.flowerToHome.entity.Log;
import com.puyuntech.flowerToHome.service.LogService;

/**
 * 
 * Service 日志 . 
 * Created on 2015-9-28 下午3:18:20 
 * @author 王凯斌
 */
@Service("logServiceImpl")
public class LogServiceImpl extends BaseServiceImpl<Log, Integer> implements LogService {
	
	@Resource(name = "logDaoImpl")
	private LogDao logDao;

	public void clear() {
		logDao.removeAll();
	}
	
}
