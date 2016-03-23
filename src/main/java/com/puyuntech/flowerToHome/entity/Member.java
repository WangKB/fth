package com.puyuntech.flowerToHome.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import com.puyuntech.flowerToHome.interceptor.MemberInterceptor;

/**
 * 
 * Entity - 会员. 
 * Created on 2015-8-26 上午11:08:25 
 * @author 王凯斌
 * 
 * 
 */
@Entity
@Table(name = "t_member")
public class Member extends BaseEntity<Integer> {

	/** "身份信息"属性名称 */
	public static final String PRINCIPAL_ATTRIBUTE_NAME = MemberInterceptor.class.getName() + ".PRINCIPAL";
	
	private static final long serialVersionUID = 6711261139444413968L;

	/**
	 * 性别
	 */
	public enum Gender {

		/** 男 */
		male,

		/** 女 */
		female
	}

	/** 用户名 */
	private String username;

	/** 密码 */
	private String password;

	/** E-mail */
	private String email;

	/** 昵称 */
	private String nickname;

	/** 积分 */
	private Integer point;

	/** 消费金额 */
	private BigDecimal amount;

	/** 头像*/
	private String photoPath;
	
	/** 是否启用 */
	private Integer isEnabled;

	/** 是否锁定 */
	private Integer isLocked;

	/** 连续登录失败次数 */
	private Integer loginFailureCount;

	/** 锁定日期 */
	private Date lockedDate;

	/** 注册IP */
	private String registerIp;

	/** 最后登录日期 */
	private Date loginDate;

	/** 姓名 */
	private String name;

	/** 性别 */
	private Member.Gender gender;

	/** 出生日期 */
	private Date birth;

	/** 地址 */
	private String address;

	/** 邮编 */
	private String zipCode;

	/** 电话 */
	private String phone;

	/** 手机 */
	private String mobile;

	/** 登录插件ID */
	private String loginPluginId;

	/** openID */
	private String openId;
	
	/** 密钥 */
	private String value;

	/** 过期时间 */
	private Date expire;
	
	//x坐标
	private Integer pointX;
	
	//y坐标
	private Integer pointY;
	
	//购物车id
	private Integer cartId;

	/**
	 * 获取密钥
	 * 
	 * @return 密钥
	 */
	@Column(name = "safe_key_value")
	public String getValue() {
		return value;
	}

	/**
	 * 设置密钥
	 * 
	 * @param value
	 *            密钥
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * 获取过期时间
	 * 
	 * @return 过期时间
	 */
	@Column(name = "safe_key_expire")
	public Date getExpire() {
		return expire;
	}

	/**
	 * 设置过期时间
	 * 
	 * @param expire
	 *            过期时间
	 */
	public void setExpire(Date expire) {
		this.expire = expire;
	}


	/**
	 * 获取用户名
	 * 
	 * @return 用户名
	 */
	@Pattern(regexp = "^[0-9a-zA-Z_\\u4e00-\\u9fa5]+$")
	@Column(name="username",nullable = false, updatable = false, unique = true)
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
	 * 获取密码
	 * 
	 * @return 密码
	 */
	@Column(name="password",nullable = false)
	public String getPassword() {
		return password;
	}

	/**
	 * 设置密码
	 * 
	 * @param password
	 *            密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 获取E-mail
	 * 
	 * @return E-mail
	 */
	@Email
	@Length(max = 200)
	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	/**
	 * 设置E-mail
	 * 
	 * @param email
	 *            E-mail
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 获取头像地址
	 * 
	 * @return 头像地址
	 */
	@Length(max = 200)
	@Column(name="photoPath")
	public String getPhotoPath() {
		return photoPath;
	}

	/**
	 * 设置头像
	 * 
	 * @param photoPath
	 *            头像
	 */
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	
	/**
	 * 获取头像
	 * 
	 * @return 昵称
	 */
	@Length(max = 200)
	@Column(name="nickname")
	public String getNickname() {
		return nickname;
	}

	/**
	 * 设置昵称
	 * 
	 * @param nickname
	 *            昵称
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	/**
	 * 获取积分
	 * 
	 * @return 积分
	 */
	@Column(name="point",nullable = false)
	public Integer getPoint() {
		return point;
	}

	/**
	 * 设置积分
	 * 
	 * @param point
	 *            积分
	 */
	public void setPoint(Integer point) {
		this.point = point;
	}
	
	/**
	 * 获取消费金额
	 * 
	 * @return 消费金额
	 */
	@Column(name="amount",nullable = false, precision = 27, scale = 12)
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * 设置消费金额
	 * 
	 * @param amount
	 *            消费金额
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * 获取是否启用
	 * 
	 * @return 是否启用
	 */
	@NotNull
	@Column(name="is_enabled",nullable = false)
	public Integer getIsEnabled() {
		return isEnabled;
	}

	/**
	 * 设置是否启用
	 * 
	 * @param isEnabled
	 *            是否启用
	 */
	public void setIsEnabled(Integer isEnabled) {
		this.isEnabled = isEnabled;
	}

	/**
	 * 获取是否锁定
	 * 
	 * @return 是否锁定
	 */
	@Column(name="is_locked",nullable = false)
	public Integer getIsLocked() {
		return isLocked;
	}

	/**
	 * 设置是否锁定
	 * 
	 * @param isLocked
	 *            是否锁定
	 */
	public void setIsLocked(Integer isLocked) {
		this.isLocked = isLocked;
	}

	/**
	 * 获取连续登录失败次数
	 * 
	 * @return 连续登录失败次数
	 */
	@Column(name="login_failure_count",nullable = false)
	public Integer getLoginFailureCount() {
		return loginFailureCount;
	}

	/**
	 * 设置连续登录失败次数
	 * 
	 * @param loginFailureCount
	 *            连续登录失败次数
	 */
	public void setLoginFailureCount(Integer loginFailureCount) {
		this.loginFailureCount = loginFailureCount;
	}

	/**
	 * 获取锁定日期
	 * 
	 * @return 锁定日期
	 */
	@Column(name="locked_date")
	public Date getLockedDate() {
		return lockedDate;
	}

	/**
	 * 设置锁定日期
	 * 
	 * @param lockedDate
	 *            锁定日期
	 */
	public void setLockedDate(Date lockedDate) {
		this.lockedDate = lockedDate;
	}

	/**
	 * 获取注册IP
	 * 
	 * @return 注册IP
	 */
	@Column(name="register_ip",nullable = false, updatable = false)
	public String getRegisterIp() {
		return registerIp;
	}

	/**
	 * 设置注册IP
	 * 
	 * @param registerIp
	 *            注册IP
	 */
	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}

	/**
	 * 获取最后登录日期
	 * 
	 * @return 最后登录日期
	 */
	@Column(name="login_date")
	public Date getLoginDate() {
		return loginDate;
	}

	/**
	 * 设置最后登录日期
	 * 
	 * @param loginDate
	 *            最后登录日期
	 */
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	/**
	 * 获取姓名
	 * 
	 * @return 姓名
	 */
	@Length(max = 200)
	@Column(name="name")
	public String getName() {
		return name;
	}

	/**
	 * 设置姓名
	 * 
	 * @param name
	 *            姓名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取性别
	 * 
	 * @return 性别
	 */
	@Column(name="gender")
	public Member.Gender getGender() {
		return gender;
	}

	/**
	 * 设置性别
	 * 
	 * @param gender
	 *            性别
	 */
	public void setGender(Member.Gender gender) {
		this.gender = gender;
	}

	/**
	 * 获取出生日期
	 * 
	 * @return 出生日期
	 */
	@Column(name="birth")
	public Date getBirth() {
		return birth;
	}

	/**
	 * 设置出生日期
	 * 
	 * @param birth
	 *            出生日期
	 */
	public void setBirth(Date birth) {
		this.birth = birth;
	}

	/**
	 * 获取地址
	 * 
	 * @return 地址
	 */
	@Length(max = 200)
	@Column(name="address")
	public String getAddress() {
		return address;
	}

	/**
	 * 设置地址
	 * 
	 * @param address
	 *            地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 获取邮编
	 * 
	 * @return 邮编
	 */
	@Length(max = 200)
	@Column(name="zip_code")
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * 设置邮编
	 * 
	 * @param zipCode
	 *            邮编
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * 获取电话
	 * 
	 * @return 电话
	 */
	@Length(max = 200)
	@Column(name="phone")
	public String getPhone() {
		return phone;
	}

	/**
	 * 设置电话
	 * 
	 * @param phone
	 *            电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 获取手机
	 * 
	 * @return 手机
	 */
	@Length(max = 200)
	@Column(name="mobile")
	public String getMobile() {
		return mobile;
	}

	/**
	 * 设置手机
	 * 
	 * @param mobile
	 *            手机
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 获取登录插件ID
	 * 
	 * @return 登录插件ID
	 */
	@Column(name="login_plugin_id",updatable = false)
	public String getLoginPluginId() {
		return loginPluginId;
	}

	/**
	 * 设置登录插件ID
	 * 
	 * @param loginPluginId
	 *            登录插件ID
	 */
	public void setLoginPluginId(String loginPluginId) {
		this.loginPluginId = loginPluginId;
	}

	/**
	 * 获取openID
	 * 
	 * @return openID
	 */
	@Column(name="open_id",updatable = false)
	public String getOpenId() {
		return openId;
	}

	/**
	 * 设置openID
	 * 
	 * @param openId
	 *            openID
	 */
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	@Column(name="point_x")
	public Integer getPointX() {
		return pointX;
	}

	public void setPointX(Integer pointX) {
		this.pointX = pointX;
	}

	@Column(name="point_y")
	public Integer getPointY() {
		return pointY;
	}

	public void setPointY(Integer pointY) {
		this.pointY = pointY;
	}

	@Column(name="cart_id")
	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}
	
}
