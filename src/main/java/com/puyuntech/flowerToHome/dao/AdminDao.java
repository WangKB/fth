package com.puyuntech.flowerToHome.dao;

import com.puyuntech.flowerToHome.entity.Admin;

/**
 *
 * Dao - 管理员 . 
 * Created on 2015-8-25 下午5:04:34 
 * @author 王凯斌
 */
public interface AdminDao extends BaseDao<Admin, Integer> {

	/**
	 * 判断用户名是否存在
	 * 
	 * @param username
	 *            用户名(忽略大小写)
	 * @return 用户名是否存在
	 */
	boolean usernameExists(String username);
	
	/**
	 * 判断pos用户名是否存在
	 * 
	 * @param username
	 *            用户名(忽略大小写)
	 * @return 用户名是否存在
	 */
	boolean posUsernameExists(String username);

	/**
	 * 根据用户名查找管理员
	 * 
	 * @param username
	 *            用户名(忽略大小写)
	 * @return 管理员，若不存在则返回null
	 */
	Admin findByUsername(String username);

}