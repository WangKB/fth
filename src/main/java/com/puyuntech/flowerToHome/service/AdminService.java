package com.puyuntech.flowerToHome.service;

import java.util.List;

import com.puyuntech.flowerToHome.entity.Admin;

/***
 * 
 * @ClassName: AdminService
 * @Description: PC 运维端管理人员帐号管理和登录 
 * @date: 2015-8-12 下午3:12:32 
 * @author: 王凯斌
 */
public interface AdminService extends BaseService<Admin, Integer> {

	/**
	 * 判断用户名是否存在
	 * 
	 * @param username
	 *            用户名(忽略大小写)
	 * @return 用户名是否存在
	 */
	boolean usernameExists(String username);
	
	/**
	 * 判断Pos用户名是否存在
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

	/**
	 * 根据ID查找权限
	 * 
	 * @param id
	 *            ID
	 * @return 权限，若不存在则返回null
	 */
	List<String> findAuthorities(Integer id);

	/**
	 * 判断管理员是否登录
	 * 
	 * @return 管理员是否登录
	 */
	boolean isAuthenticated();

	/**
	 * 获取当前登录管理员
	 * 
	 * @return 当前登录管理员，若不存在则返回null
	 */
	Admin getCurrent();

	/**
	 * 获取当前登录用户名
	 * 
	 * @return 当前登录用户名，若不存在则返回null
	 */
	String getCurrentUsername();

	/**
	 * 获取登录令牌
	 * 
	 * @return 登录令牌
	 */
	String getLoginToken();

}