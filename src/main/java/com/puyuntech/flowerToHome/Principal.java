package com.puyuntech.flowerToHome;

import java.io.Serializable;
/**
 * 
 * 身份信息. 
 * Created on 2015-8-14 下午5:08:09 
 * @author 王凯斌
 */
public class Principal implements Serializable {

	private static final long serialVersionUID = 5798882004228239559L;

	/** ID */
	private Integer id;

	/** 用户名 */
	private String username;

	/**
	 * 构造方法
	 * 
	 * @param id
	 *            ID
	 * @param username
	 *            用户名
	 */
	public Principal(Integer id, String username) {
		this.id = id;
		this.username = username;
	}

	/**
	 * 获取ID
	 * 
	 * @return ID
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设置ID
	 * 
	 * @param id
	 *            ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取用户名
	 * 
	 * @return 用户名
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 设置用户名
	 * 
	 * @param username
	 *            用户名
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 重写toString方法
	 * 
	 * @return 字符串
	 */
	@Override
	public String toString() {
		return username;
	}

}