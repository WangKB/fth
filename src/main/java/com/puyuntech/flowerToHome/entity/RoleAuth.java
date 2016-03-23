package com.puyuntech.flowerToHome.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * 商品门店关联表.
 * Created on 2015-8-27 上午11:06:14 
 * @author 王凯斌
 * 
 */
@Entity
@Table(name = "t_role_auth")
public class RoleAuth extends BaseEntity<Integer> {
	
	//商品id
	private Integer roleId;
	
	//商家id
	private Integer authId;

	@Column(name="role_id")
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Column(name="auth_id")
	public Integer getAuthId() {
		return authId;
	}

	public void setAuthId(Integer authId) {
		this.authId = authId;
	}
	
}
