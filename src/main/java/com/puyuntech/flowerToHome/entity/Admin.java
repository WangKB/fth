package com.puyuntech.flowerToHome.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

/**
 * 
 * 管理员 实体类. 
 * Created on 2015-8-24 下午6:06:26 
 * @author 王凯斌
 */
@Entity
@Table(name = "t_admin")
public class Admin extends OrderEntity<Integer>{

	private static final long serialVersionUID = -6003470702148125699L;

	/** "登录令牌"Cookie名称 */
	public static final String LOGIN_TOKEN_COOKIE_NAME = "adminLoginToken";
	
	/** Web端用户名 */
	private String webUsername;

	/** Web端密码 */
	private String webPassword;
	
	/** 工号 唯一 **/
	private String jobNumber;
	
	/** 手机号 **/
	private String phone;

	/** E-mail */
	private String email;

	/** 姓名 */
	private String name;
	
	/** 照片 **/
	private String image;
	
	/** 是否启用 */
	private Integer isEnabled;

	/** 是否锁定 */
	private Integer isLocked;
	
	/** 锁定日期 */
	private Date lockedDate;

	/** 最后登录日期 */
	private Date loginDate;

	/** 最后登录IP */
	private String loginIp;
	
	/** 商铺id */
	private Integer shopId;
	
	/** 角色id */
	private Integer roleId;
	
	/** 来源 */
	private Integer source;

	/**
	 * 
	 * 设置用户名.
	 * author: 王凯斌
	 *   date: 2015-8-26 上午10:07:34
	 * @return
	 */
	@Pattern(regexp = "^[0-9a-zA-Z_\\u4e00-\\u9fa5]+$")
	@Length(min = 2, max = 20)
	@Column(name="web_username",unique = true)
	public String getWebUsername() {
		return webUsername;
	}

	public void setWebUsername(String webUsername) {
		this.webUsername = webUsername;
	}
	
	@Column(name="image",nullable=true)
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	/**
	 * 
	 * 设置密码.
	 * author: 王凯斌
	 *   date: 2015-8-26 上午10:10:34
	 * @return
	 */
	@Length(min = 4, max = 20)
	@Column(name = "web_password")
	public String getWebPassword() {
		return webPassword;
	}

	public void setWebPassword(String webPassword) {
		this.webPassword = webPassword;
	}

	/**
	 * 
	 * 员工工号.
	 * author: 王凯斌
	 *   date: 2015-8-26 上午10:12:02
	 * @return
	 */
	@Column(name="jobnumber",nullable=false,unique=true)
	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	@Column(name="phone",nullable=false,unique=true)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Email
	@Length(max = 200)
	@Column(name="email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotNull
	@Column(name="is_enabled",nullable = false)
	public Integer getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Integer isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	@NotNull
	@Column(name="is_locked")
	public Integer getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Integer isLocked) {
		this.isLocked = isLocked;
	}
	

	@Column(name="locked_date")
	public Date getLockedDate() {
		return lockedDate;
	}

	public void setLockedDate(Date lockedDate) {
		this.lockedDate = lockedDate;
	}

	@Column(name="login_date")
	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	@Column(name="login_ip")
	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	/**
	 * 持久化前处理
	 */
	@PrePersist
	public void prePersist() {
		
	}

	/**
	 * 更新前处理
	 */
	@PreUpdate
	public void preUpdate() {
		setEmail(StringUtils.lowerCase(getEmail()));
	}

	@Column(name="shop_id")
	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	@Column(name="role_id")
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Column(name="source")
	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	
}
