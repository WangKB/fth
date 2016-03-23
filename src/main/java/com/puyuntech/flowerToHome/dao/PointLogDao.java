package com.puyuntech.flowerToHome.dao;

import com.puyuntech.flowerToHome.Page;
import com.puyuntech.flowerToHome.Pageable;
import com.puyuntech.flowerToHome.entity.Member;
import com.puyuntech.flowerToHome.entity.PointLog;

/**
 * 
 * 积分记录 Dao . 
 * Created on 2015-9-13 下午4:04:47 
 * @author 王凯斌
 */
public interface PointLogDao extends BaseDao<PointLog, Integer> {

	/**
	 * 
	 * 查找积分记录分页 .
	 * author: 王凯斌
	 *   date: 2015-9-13 下午4:05:21
	 * @param member 会员 
	 * @param pageable 分页信息 
	 * @return 积分记录分页
	 */
	Page<PointLog> findPage(Member member, Pageable pageable);

	
	
}
