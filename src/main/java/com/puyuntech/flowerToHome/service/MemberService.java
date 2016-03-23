package com.puyuntech.flowerToHome.service;

import com.puyuntech.flowerToHome.entity.Member;

/**
 * 
 * Service - 会员 . 
 * Created on 2015-9-9 下午3:05:01 
 * @author 王凯斌
 */
public interface MemberService extends BaseService<Member, Integer> {
	
	/**
	 * 判断用户名是否存在
	 * 
	 * @param username
	 *            用户名(忽略大小写)
	 * @return 用户名是否存在
	 */
	boolean usernameExists(String username);

		
	Member getCurrent(boolean lock);
	
	Member getCurrent();
}