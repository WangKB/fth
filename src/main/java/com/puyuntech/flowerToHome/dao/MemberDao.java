package com.puyuntech.flowerToHome.dao;

import com.puyuntech.flowerToHome.entity.Member;


/**
 * 
 * Dao - 会员 
 * Created on 2015-8-26 下午15:32:33 
 * @author 王凯斌
 */
public interface MemberDao extends BaseDao<Member, Integer> {
	
	/**
	 * 判断用户名是否存在
	 * 
	 * @param username
	 *            用户名(忽略大小写)
	 * @return 用户名是否存在
	 */
	boolean usernameExists(String username);

}