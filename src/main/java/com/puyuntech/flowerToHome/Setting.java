package com.puyuntech.flowerToHome;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.ScriptAssert;

/**
 * 
 * 系统设置. 
 * Created on 2015-8-17 上午11:11:11 
 * @author 王凯斌
 */
@ScriptAssert(lang = "javascript", script = "_this.usernameMaxLength >= _this.usernameMinLength && _this.passwordMaxLength >= _this.passwordMinLength")
public class Setting implements Serializable {

	private static final long serialVersionUID = -1478999889661796840L;

	/** 缓存名称 */
	public static final String CACHE_NAME = "setting";

	/** 分隔符 */
	private static final String SEPARATOR = ",";

	/**
	 * 水印位置
	 */
	public enum WatermarkPosition {

		/** 无 */
		no,

		/** 左上 */
		topLeft,

		/** 右上 */
		topRight,

		/** 居中 */
		center,

		/** 左下 */
		bottomLeft,

		/** 右下 */
		bottomRight
	}
	
	/**
	 * 小数位精确方式
	 */
	public enum RoundType {

		/** 四舍五入 */
		roundHalfUp,

		/** 向上取整 */
		roundUp,

		/** 向下取整 */
		roundDown
	}

	/**
	 * 验证码类型
	 */
	public enum CaptchaType {

		/** 会员登录 */
		memberLogin,

		/** 会员注册 */
		memberRegister,

		/** 后台登录 */
		adminLogin,

		/** 商品评论 */
		review,

		/** 商品咨询 */
		consultation,

		/** 找回密码 */
		findPassword,

		/** 重置密码 */
		resetPassword,

		/** 其它 */
		other
	}

	/**
	 * 账号锁定类型
	 */
	public enum AccountLockType {

		/** 会员 */
		member,

		/** 管理员 */
		admin
	}

	/**
	 * 区域设置
	 */
	public enum Locale {

		/** 中文(简体, 中国) */
		zh_CN,

		/** 中文(繁体, 台湾) */
		zh_TW,

		/** 英语(美国) */
		en_US
	}

	/** 网站名称 */
	private String siteName;

	/** 网站网址 */
	private String siteUrl;

	/** logo */
	private String logo;

	/** 评论维度1 */
	private String reviewOne;
	
	/** 评论维度2 */
	private String reviewTwo;
	
	/** 评论维度3 */
	private String reviewThree;

	/** 联系地址 */
	private String address;

	/** 联系电话 */
	private String phone;

	/** 邮政编码 */
	private String zipCode;

	/** E-mail */
	private String email;

	/** 备案编号 */
	private String certtext;

	/** 是否网站开启 */
	private Boolean isSiteEnabled;

	/** 网站关闭消息 */
	private String siteCloseMessage;

	/** 商品图片(大)宽度 */
	private Integer largeProductImageWidth;

	/** 商品图片(大)高度 */
	private Integer largeProductImageHeight;

	/** 商品图片(中)宽度 */
	private Integer mediumProductImageWidth;

	/** 商品图片(中)高度 */
	private Integer mediumProductImageHeight;

	/** 商品缩略图宽度 */
	private Integer thumbnailProductImageWidth;

	/** 商品缩略图高度 */
	private Integer thumbnailProductImageHeight;

	/** 默认商品图片(大) */
	private String defaultLargeProductImage;

	/** 默认商品图片(小) */
	private String defaultMediumProductImage;

	/** 默认缩略图 */
	private String defaultThumbnailProductImage;

	/** 价格精确位数 */
	private Integer priceScale;

	/** 价格精确方式 */
	private Setting.RoundType priceRoundType;

	/** 注册协议 */
	private String registerAgreement;

	/** 验证码类型 */
	private Setting.CaptchaType[] captchaTypes;

	/** 账号锁定类型 */
	private Setting.AccountLockType[] accountLockTypes;

	/** 连续登录失败最大次数 */
	private Integer accountLockCount;

	/** 自动解锁时间 */
	private Integer accountLockTime;

	/** 安全密匙有效时间 */
	private Integer safeKeyExpiryTime;

	/** 上传文件最大限制 */
	private Integer uploadMaxSize;

	/** 允许上传图片扩展名 */
	private String uploadImageExtension;

	/** 允许上传媒体扩展名 */
	private String uploadMediaExtension;

	/** 允许上传文件扩展名 */
	private String uploadFileExtension;

	/** 图片上传路径 */
	private String imageUploadPath;

	/** 媒体上传路径 */
	private String mediaUploadPath;

	/** 文件上传路径 */
	private String fileUploadPath;

	/** 货币符号 */
	private String currencySign;

	/** 货币单位 */
	private String currencyUnit;

	/** Cookie路径 */
	private String cookiePath;

	/** Cookie作用域 */
	private String cookieDomain;

	/** 短信服务器IP */
	private String smsHost;

	/** 短信服务器端口 */
	private String smsPort;
	
	/** 短信主账号ID */
	private String smsSn;

	/** 短信主账号的token*/
	private String smsKey;
	
	/** 是否开启开发模式 */
	private Boolean isDevelopmentEnabled;
	
	/** 水印透明度 */
	private Integer watermarkAlpha;

	/** 水印图片 */
	private String watermarkImage;

	/** 水印位置 */
	private Setting.WatermarkPosition watermarkPosition;
	
	private String statusCode;
	
	/** 短信初始化应用ID */
	private String smsApplication;

	/** 区域设置 */
	private Setting.Locale locale;

	/** 主题 */
	private String theme;
	
    /** 关于我们 **/
    private String footerAbout;

    /** 联系我们 **/
    private String footerContact;

    /** 法律声明 **/
    private String footerLawInfo;
    
    /** 平台分账比例 **/
    private String platformRate;

    /** 店铺分账比例 **/
    private String shopRate;

    /** 供货商分账比例 **/
    private String supplierRate;

    /** 每消费一元获得积分数额 */
	private Integer rewardRate;
	
	 /** 每一积分相当于的人民币数额 */
	private Integer expenseRate;
	
	 /** 最小可以使用订单金额 */
	private Integer minimumAmount;

	/**
	 * 获取网站名称
	 * 
	 * @return 网站名称
	 */
	@NotEmpty
	@Length(max = 200)
	public String getSiteName() {
		return siteName;
	}

	/**
	 * 设置网站名称
	 * 
	 * @param siteName
	 *            网站名称
	 */
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	/**
	 * 获取是否开启开发模式
	 * 
	 * @return 是否开启开发模式
	 */
	@NotNull
	public Boolean getIsDevelopmentEnabled() {
		return isDevelopmentEnabled;
	}

	/**
	 * 设置是否开启开发模式
	 * 
	 * @param isDevelopmentEnabled
	 *            是否开启开发模式
	 */
	public void setIsDevelopmentEnabled(Boolean isDevelopmentEnabled) {
		this.isDevelopmentEnabled = isDevelopmentEnabled;
	}
	
	/**
	 * 获取水印透明度
	 * 
	 * @return 水印透明度
	 */
	@NotNull
	@Min(0)
	@Max(100)
	public Integer getWatermarkAlpha() {
		return watermarkAlpha;
	}

	/**
	 * 设置水印透明度
	 * 
	 * @param watermarkAlpha
	 *            水印透明度
	 */
	public void setWatermarkAlpha(Integer watermarkAlpha) {
		this.watermarkAlpha = watermarkAlpha;
	}

	/**
	 * 获取水印图片
	 * 
	 * @return 水印图片
	 */
	public String getWatermarkImage() {
		return watermarkImage;
	}

	/**
	 * 设置水印图片
	 * 
	 * @param watermarkImage
	 *            水印图片
	 */
	public void setWatermarkImage(String watermarkImage) {
		this.watermarkImage = watermarkImage;
	}

	/**
	 * 获取水印位置
	 * 
	 * @return 水印位置
	 */
	@NotNull
	public Setting.WatermarkPosition getWatermarkPosition() {
		return watermarkPosition;
	}

	/**
	 * 设置水印位置
	 * 
	 * @param watermarkPosition
	 *            水印位置
	 */
	public void setWatermarkPosition(Setting.WatermarkPosition watermarkPosition) {
		this.watermarkPosition = watermarkPosition;
	}

	
	@Length(max = 200)
	public String getReviewOne() {
		return reviewOne;
	}

	public void setReviewOne(String reviewOne) {
		this.reviewOne = reviewOne;
	}

	@Length(max = 200)
	public String getReviewTwo() {
		return reviewTwo;
	}

	public void setReviewTwo(String reviewTwo) {
		this.reviewTwo = reviewTwo;
	}

	@Length(max = 200)
	public String getReviewThree() {
		return reviewThree;
	}

	public void setReviewThree(String reviewThree) {
		this.reviewThree = reviewThree;
	}

	/**
	 * 获取网站网址
	 * 
	 * @return 网站网址
	 */
	@NotEmpty
	@Length(max = 200)
	@Pattern(regexp = "^(?i)(http:\\/\\/|https:\\/\\/).*$")
	public String getSiteUrl() {
		return siteUrl;
	}

	/**
	 * 设置网站网址
	 * 
	 * @param siteUrl
	 *            网站网址
	 */
	public void setSiteUrl(String siteUrl) {
		this.siteUrl = StringUtils.removeEnd(siteUrl, "/");
	}

	/**
	 * 获取logo
	 * 
	 * @return logo
	 */
	@NotEmpty
	@Length(max = 200)
	@Pattern(regexp = "^(?i)(http:\\/\\/|https:\\/\\/|\\/).*$")
	public String getLogo() {
		return logo;
	}

	/**
	 * 设置logo
	 * 
	 * @param logo
	 *            logo
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}

	/**
	 * 获取联系地址
	 * 
	 * @return 联系地址
	 */
	@Length(max = 200)
	public String getAddress() {
		return address;
	}

	/**
	 * 设置联系地址
	 * 
	 * @param address
	 *            联系地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 获取联系电话
	 * 
	 * @return 联系电话
	 */
	@Length(max = 200)
	public String getPhone() {
		return phone;
	}

	/**
	 * 设置联系电话
	 * 
	 * @param phone
	 *            联系电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 获取邮政编码
	 * 
	 * @return 邮政编码
	 */
	@Length(max = 200)
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * 设置邮政编码
	 * 
	 * @param zipCode
	 *            邮政编码
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * 获取E-mail
	 * 
	 * @return E-mail
	 */
	@Email
	@Length(max = 200)
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
	 * 获取备案编号
	 * 
	 * @return 备案编号
	 */
	@Length(max = 200)
	public String getCerttext() {
		return certtext;
	}

	/**
	 * 设置备案编号
	 * 
	 * @param certtext
	 *            备案编号
	 */
	public void setCerttext(String certtext) {
		this.certtext = certtext;
	}

	/**
	 * 获取是否网站开启
	 * 
	 * @return 是否网站开启
	 */
	@NotNull
	public Boolean getIsSiteEnabled() {
		return isSiteEnabled;
	}

	/**
	 * 设置是否网站开启
	 * 
	 * @param isSiteEnabled
	 *            是否网站开启
	 */
	public void setIsSiteEnabled(Boolean isSiteEnabled) {
		this.isSiteEnabled = isSiteEnabled;
	}

	/**
	 * 获取网站关闭消息
	 * 
	 * @return 网站关闭消息
	 */
	@NotEmpty
	public String getSiteCloseMessage() {
		return siteCloseMessage;
	}

	/**
	 * 设置网站关闭消息
	 * 
	 * @param siteCloseMessage
	 *            网站关闭消息
	 */
	public void setSiteCloseMessage(String siteCloseMessage) {
		this.siteCloseMessage = siteCloseMessage;
	}

	/**
	 * 获取商品图片(大)宽度
	 * 
	 * @return 商品图片(大)宽度
	 */
	@NotNull
	@Min(1)
	public Integer getLargeProductImageWidth() {
		return largeProductImageWidth;
	}

	/**
	 * 设置商品图片(大)宽度
	 * 
	 * @param largeProductImageWidth
	 *            商品图片(大)宽度
	 */
	public void setLargeProductImageWidth(Integer largeProductImageWidth) {
		this.largeProductImageWidth = largeProductImageWidth;
	}

	/**
	 * 获取商品图片(大)高度
	 * 
	 * @return 商品图片(大)高度
	 */
	@NotNull
	@Min(1)
	public Integer getLargeProductImageHeight() {
		return largeProductImageHeight;
	}

	/**
	 * 设置商品图片(大)高度
	 * 
	 * @param largeProductImageHeight
	 *            商品图片(大)高度
	 */
	public void setLargeProductImageHeight(Integer largeProductImageHeight) {
		this.largeProductImageHeight = largeProductImageHeight;
	}

	/**
	 * 获取商品图片(中)宽度
	 * 
	 * @return 商品图片(中)宽度
	 */
	@NotNull
	@Min(1)
	public Integer getMediumProductImageWidth() {
		return mediumProductImageWidth;
	}

	/**
	 * 设置商品图片(中)宽度
	 * 
	 * @param mediumProductImageWidth
	 *            商品图片(中)宽度
	 */
	public void setMediumProductImageWidth(Integer mediumProductImageWidth) {
		this.mediumProductImageWidth = mediumProductImageWidth;
	}

	/**
	 * 获取商品图片(中)高度
	 * 
	 * @return 商品图片(中)高度
	 */
	@NotNull
	@Min(1)
	public Integer getMediumProductImageHeight() {
		return mediumProductImageHeight;
	}

	/**
	 * 设置商品图片(中)高度
	 * 
	 * @param mediumProductImageHeight
	 *            商品图片(中)高度
	 */
	public void setMediumProductImageHeight(Integer mediumProductImageHeight) {
		this.mediumProductImageHeight = mediumProductImageHeight;
	}

	/**
	 * 获取商品缩略图宽度
	 * 
	 * @return 商品缩略图宽度
	 */
	@NotNull
	@Min(1)
	public Integer getThumbnailProductImageWidth() {
		return thumbnailProductImageWidth;
	}

	/**
	 * 设置商品缩略图宽度
	 * 
	 * @param thumbnailProductImageWidth
	 *            商品缩略图宽度
	 */
	public void setThumbnailProductImageWidth(Integer thumbnailProductImageWidth) {
		this.thumbnailProductImageWidth = thumbnailProductImageWidth;
	}

	/**
	 * 获取商品缩略图高度
	 * 
	 * @return 商品缩略图高度
	 */
	@NotNull
	@Min(1)
	public Integer getThumbnailProductImageHeight() {
		return thumbnailProductImageHeight;
	}

	/**
	 * 设置商品缩略图高度
	 * 
	 * @param thumbnailProductImageHeight
	 *            商品缩略图高度
	 */
	public void setThumbnailProductImageHeight(Integer thumbnailProductImageHeight) {
		this.thumbnailProductImageHeight = thumbnailProductImageHeight;
	}

	/**
	 * 获取默认商品图片(大)
	 * 
	 * @return 默认商品图片(大)
	 */
	@NotEmpty
	@Length(max = 200)
	@Pattern(regexp = "^(?i)(http:\\/\\/|https:\\/\\/|\\/).*$")
	public String getDefaultLargeProductImage() {
		return defaultLargeProductImage;
	}

	/**
	 * 设置默认商品图片(大)
	 * 
	 * @param defaultLargeProductImage
	 *            默认商品图片(大)
	 */
	public void setDefaultLargeProductImage(String defaultLargeProductImage) {
		this.defaultLargeProductImage = defaultLargeProductImage;
	}

	/**
	 * 获取默认商品图片(小)
	 * 
	 * @return 默认商品图片(小)
	 */
	@NotEmpty
	@Length(max = 200)
	@Pattern(regexp = "^(?i)(http:\\/\\/|https:\\/\\/|\\/).*$")
	public String getDefaultMediumProductImage() {
		return defaultMediumProductImage;
	}

	/**
	 * 设置默认商品图片(小)
	 * 
	 * @param defaultMediumProductImage
	 *            默认商品图片(小)
	 */
	public void setDefaultMediumProductImage(String defaultMediumProductImage) {
		this.defaultMediumProductImage = defaultMediumProductImage;
	}

	/**
	 * 获取默认缩略图
	 * 
	 * @return 默认缩略图
	 */
	@NotEmpty
	@Length(max = 200)
	@Pattern(regexp = "^(?i)(http:\\/\\/|https:\\/\\/|\\/).*$")
	public String getDefaultThumbnailProductImage() {
		return defaultThumbnailProductImage;
	}

	/**
	 * 设置默认缩略图
	 * 
	 * @param defaultThumbnailProductImage
	 *            默认缩略图
	 */
	public void setDefaultThumbnailProductImage(String defaultThumbnailProductImage) {
		this.defaultThumbnailProductImage = defaultThumbnailProductImage;
	}


	/**
	 * 获取价格精确位数
	 * 
	 * @return 价格精确位数
	 */
	@NotNull
	@Min(0)
	@Max(3)
	public Integer getPriceScale() {
		return priceScale;
	}

	/**
	 * 设置价格精确位数
	 * 
	 * @param priceScale
	 *            价格精确位数
	 */
	public void setPriceScale(Integer priceScale) {
		this.priceScale = priceScale;
	}

	/**
	 * 获取价格精确方式
	 * 
	 * @return 价格精确方式
	 */
	@NotNull
	public Setting.RoundType getPriceRoundType() {
		return priceRoundType;
	}

	/**
	 * 设置价格精确方式
	 * 
	 * @param priceRoundType
	 *            价格精确方式
	 */
	public void setPriceRoundType(Setting.RoundType priceRoundType) {
		this.priceRoundType = priceRoundType;
	}

	/**
	 * 获取注册协议
	 * 
	 * @return 注册协议
	 */
	@NotEmpty
	public String getRegisterAgreement() {
		return registerAgreement;
	}

	/**
	 * 设置注册协议
	 * 
	 * @param registerAgreement
	 *            注册协议
	 */
	public void setRegisterAgreement(String registerAgreement) {
		this.registerAgreement = registerAgreement;
	}

	/**
	 * 获取验证码类型
	 * 
	 * @return 验证码类型
	 */
	public Setting.CaptchaType[] getCaptchaTypes() {
		return captchaTypes;
	}

	/**
	 * 设置验证码类型
	 * 
	 * @param captchaTypes
	 *            验证码类型
	 */
	public void setCaptchaTypes(Setting.CaptchaType[] captchaTypes) {
		this.captchaTypes = captchaTypes;
	}

	/**
	 * 获取账号锁定类型
	 * 
	 * @return 账号锁定类型
	 */
	public Setting.AccountLockType[] getAccountLockTypes() {
		return accountLockTypes;
	}

	/**
	 * 设置账号锁定类型
	 * 
	 * @param accountLockTypes
	 *            账号锁定类型
	 */
	public void setAccountLockTypes(Setting.AccountLockType[] accountLockTypes) {
		this.accountLockTypes = accountLockTypes;
	}

	/**
	 * 获取连续登录失败最大次数
	 * 
	 * @return 连续登录失败最大次数
	 */
	@NotNull
	@Min(1)
	public Integer getAccountLockCount() {
		return accountLockCount;
	}

	/**
	 * 设置连续登录失败最大次数
	 * 
	 * @param accountLockCount
	 *            连续登录失败最大次数
	 */
	public void setAccountLockCount(Integer accountLockCount) {
		this.accountLockCount = accountLockCount;
	}

	/**
	 * 获取自动解锁时间
	 * 
	 * @return 自动解锁时间
	 */
	@NotNull
	@Min(0)
	public Integer getAccountLockTime() {
		return accountLockTime;
	}

	/**
	 * 设置自动解锁时间
	 * 
	 * @param accountLockTime
	 *            自动解锁时间
	 */
	public void setAccountLockTime(Integer accountLockTime) {
		this.accountLockTime = accountLockTime;
	}

	/**
	 * 获取安全密匙有效时间
	 * 
	 * @return 安全密匙有效时间
	 */
	@NotNull
	@Min(0)
	public Integer getSafeKeyExpiryTime() {
		return safeKeyExpiryTime;
	}

	/**
	 * 设置安全密匙有效时间
	 * 
	 * @param safeKeyExpiryTime
	 *            安全密匙有效时间
	 */
	public void setSafeKeyExpiryTime(Integer safeKeyExpiryTime) {
		this.safeKeyExpiryTime = safeKeyExpiryTime;
	}

	/**
	 * 获取上传文件最大限制
	 * 
	 * @return 上传文件最大限制
	 */
	@NotNull
	@Min(0)
	public Integer getUploadMaxSize() {
		return uploadMaxSize;
	}

	/**
	 * 设置上传文件最大限制
	 * 
	 * @param uploadMaxSize
	 *            上传文件最大限制
	 */
	public void setUploadMaxSize(Integer uploadMaxSize) {
		this.uploadMaxSize = uploadMaxSize;
	}

	/**
	 * 获取允许上传图片扩展名
	 * 
	 * @return 允许上传图片扩展名
	 */
	@Length(max = 200)
	public String getUploadImageExtension() {
		return uploadImageExtension;
	}

	/**
	 * 设置允许上传图片扩展名
	 * 
	 * @param uploadImageExtension
	 *            允许上传图片扩展名
	 */
	public void setUploadImageExtension(String uploadImageExtension) {
		if (uploadImageExtension != null) {
			uploadImageExtension = uploadImageExtension.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "").toLowerCase();
		}
		this.uploadImageExtension = uploadImageExtension;
	}

	/**
	 * 获取允许上传媒体扩展名
	 * 
	 * @return 允许上传媒体扩展名
	 */
	@Length(max = 200)
	public String getUploadMediaExtension() {
		return uploadMediaExtension;
	}

	/**
	 * 设置允许上传媒体扩展名
	 * 
	 * @param uploadMediaExtension
	 *            允许上传媒体扩展名
	 */
	public void setUploadMediaExtension(String uploadMediaExtension) {
		if (uploadMediaExtension != null) {
			uploadMediaExtension = uploadMediaExtension.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "").toLowerCase();
		}
		this.uploadMediaExtension = uploadMediaExtension;
	}

	/**
	 * 获取允许上传文件扩展名
	 * 
	 * @return 允许上传文件扩展名
	 */
	@Length(max = 200)
	public String getUploadFileExtension() {
		return uploadFileExtension;
	}

	/**
	 * 设置允许上传文件扩展名
	 * 
	 * @param uploadFileExtension
	 *            允许上传文件扩展名
	 */
	public void setUploadFileExtension(String uploadFileExtension) {
		if (uploadFileExtension != null) {
			uploadFileExtension = uploadFileExtension.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "").toLowerCase();
		}
		this.uploadFileExtension = uploadFileExtension;
	}

	/**
	 * 获取图片上传路径
	 * 
	 * @return 图片上传路径
	 */
	@NotEmpty
	@Length(max = 200)
	public String getImageUploadPath() {
		return imageUploadPath;
	}

	/**
	 * 设置图片上传路径
	 * 
	 * @param imageUploadPath
	 *            图片上传路径
	 */
	public void setImageUploadPath(String imageUploadPath) {
		if (imageUploadPath != null) {
			if (!imageUploadPath.startsWith("/")) {
				imageUploadPath = "/" + imageUploadPath;
			}
			if (!imageUploadPath.endsWith("/")) {
				imageUploadPath += "/";
			}
		}
		this.imageUploadPath = imageUploadPath;
	}
	



	/**
	 * 获取媒体上传路径
	 * 
	 * @return 媒体上传路径
	 */
	@NotEmpty
	@Length(max = 200)
	public String getMediaUploadPath() {
		return mediaUploadPath;
	}

	/**
	 * 设置媒体上传路径
	 * 
	 * @param mediaUploadPath
	 *            媒体上传路径
	 */
	public void setMediaUploadPath(String mediaUploadPath) {
		if (mediaUploadPath != null) {
			if (!mediaUploadPath.startsWith("/")) {
				mediaUploadPath = "/" + mediaUploadPath;
			}
			if (!mediaUploadPath.endsWith("/")) {
				mediaUploadPath += "/";
			}
		}
		this.mediaUploadPath = mediaUploadPath;
	}

	/**
	 * 获取文件上传路径
	 * 
	 * @return 文件上传路径
	 */
	@NotEmpty
	@Length(max = 200)
	public String getFileUploadPath() {
		return fileUploadPath;
	}

	/**
	 * 设置文件上传路径
	 * 
	 * @param fileUploadPath
	 *            文件上传路径
	 */
	public void setFileUploadPath(String fileUploadPath) {
		if (fileUploadPath != null) {
			if (!fileUploadPath.startsWith("/")) {
				fileUploadPath = "/" + fileUploadPath;
			}
			if (!fileUploadPath.endsWith("/")) {
				fileUploadPath += "/";
			}
		}
		this.fileUploadPath = fileUploadPath;
	}



	/**
	 * 获取货币符号
	 * 
	 * @return 货币符号
	 */
	@NotEmpty
	@Length(max = 200)
	public String getCurrencySign() {
		return currencySign;
	}

	/**
	 * 设置货币符号
	 * 
	 * @param currencySign
	 *            货币符号
	 */
	public void setCurrencySign(String currencySign) {
		this.currencySign = currencySign;
	}

	/**
	 * 获取货币单位
	 * 
	 * @return 货币单位
	 */
	@NotEmpty
	@Length(max = 200)
	public String getCurrencyUnit() {
		return currencyUnit;
	}

	/**
	 * 设置货币单位
	 * 
	 * @param currencyUnit
	 *            货币单位
	 */
	public void setCurrencyUnit(String currencyUnit) {
		this.currencyUnit = currencyUnit;
	}

	/**
	 * 获取Cookie路径
	 * 
	 * @return Cookie路径
	 */
	@NotEmpty
	@Length(max = 200)
	public String getCookiePath() {
		return cookiePath;
	}

	/**
	 * 设置Cookie路径
	 * 
	 * @param cookiePath
	 *            Cookie路径
	 */
	public void setCookiePath(String cookiePath) {
		if (cookiePath != null && !cookiePath.endsWith("/")) {
			cookiePath += "/";
		}
		this.cookiePath = cookiePath;
	}

	/**
	 * 获取Cookie作用域
	 * 
	 * @return Cookie作用域
	 */
	@Length(max = 200)
	public String getCookieDomain() {
		return cookieDomain;
	}

	/**
	 * 设置Cookie作用域
	 * 
	 * @param cookieDomain
	 *            Cookie作用域
	 */
	public void setCookieDomain(String cookieDomain) {
		this.cookieDomain = cookieDomain;
	}

	/**
	 * 获取短信主账号的ID 
	 * 
	 * @return 短信主账号的ID 
	 */
	@Length(max = 200)
	public String getSmsSn() {
		return smsSn;
	}

	/**
	 * 设置短信主账号的ID 
	 * 
	 * @param smsSn
	 *            短信主账号的ID 
	 */
	public void setSmsSn(String smsSn) {
		this.smsSn = smsSn;
	}

	/**
	 * 获取短信主账号的token
	 * 
	 * @return 短信主账号的token
	 */
	@Length(max = 200)
	public String getSmsKey() {
		return smsKey;
	}

	/**
	 * 设置短信主账号的token
	 * 
	 * @param smsKey
	 *            短信主账号的token
	 */
	public void setSmsKey(String smsKey) {
		this.smsKey = smsKey;
	}
	

	/**
	 * 获取区域设置
	 * 
	 * @return 区域设置
	 */
	@NotNull
	public Setting.Locale getLocale() {
		return locale;
	}

	/**
	 * 设置区域设置
	 * 
	 * @param locale
	 *            区域设置
	 */
	public void setLocale(Setting.Locale locale) {
		this.locale = locale;
	}

	/**
	 * 获取主题
	 * 
	 * @return 主题
	 */
	@Null
	public String getTheme() {
		return theme;
	}

	/**
	 * 设置主题
	 * 
	 * @param theme
	 *            主题
	 */
	public void setTheme(String theme) {
		this.theme = theme;
	}

	/**
	 * 获取允许上传图片扩展名
	 * 
	 * @return 允许上传图片扩展名
	 */
	public String[] getUploadImageExtensions() {
		return StringUtils.split(uploadImageExtension, SEPARATOR);
	}

	/**
	 * 获取允许上传媒体扩展名
	 * 
	 * @return 允许上传媒体扩展名
	 */
	public String[] getUploadMediaExtensions() {
		return StringUtils.split(uploadMediaExtension, SEPARATOR);
	}

	/**
	 * 获取允许上传文件扩展名
	 * 
	 * @return 允许上传文件扩展名
	 */
	public String[] getUploadFileExtensions() {
		return StringUtils.split(uploadFileExtension, SEPARATOR);
	}

	/**
	 * 设置精度
	 * 
	 * @param amount
	 *            数值
	 * @return 数值
	 */
	public BigDecimal setScale(BigDecimal amount) {
		if (amount != null && getPriceScale() != null && getPriceRoundType() != null) {
			switch (getPriceRoundType()) {
			case roundUp:
				return amount.setScale(getPriceScale(), BigDecimal.ROUND_UP);
			case roundDown:
				return amount.setScale(getPriceScale(), BigDecimal.ROUND_DOWN);
			case roundHalfUp:
				return amount.setScale(getPriceScale(), BigDecimal.ROUND_HALF_UP);
			}
		}
		return amount;
	}

	
	/**
	 * 获取短信服务器IP
	 * 
	 * @return 短信服务器IP
	 */
	@Length(max = 200)
	public String getSmsHost() {
		return smsHost;
	}
	/**
	 * 设置短信服务器IP
	 * 
	 * @param smsHost
	 *            短信服务器IP
	 */
	public void setSmsHost(String smsHost) {
		this.smsHost = smsHost;
	}
	/**
	 * 获取短信服务器端口
	 * 
	 * @return 短信服务器端口
	 */
	@Length(max = 200)
	public String getSmsPort() {
		return smsPort;
	}
	/**
	 * 设置短信服务器端口
	 * 
	 * @param smsPort
	 *            短信服务器端口
	 */
	public void setSmsPort(String smsPort) {
		this.smsPort = smsPort;
	}
	/**
	 * 获取短信初始化应用ID
	 * 
	 * @return 短信初始化应用ID
	 */
	@Length(max = 200)
	public String getSmsApplication() {
		return smsApplication;
	}
	/**
	 * 设置短信初始化应用ID
	 * 
	 * @param smsApplication
	 *            短信初始化应用ID
	 */
	public void setSmsApplication(String smsApplication) {
		this.smsApplication = smsApplication;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

    @NotEmpty
    public String getFooterAbout() {
        return footerAbout;
    }

    public void setFooterAbout(String footerAbout) {
        this.footerAbout = footerAbout;
    }

    @NotEmpty
    public String getFooterContact() {
        return footerContact;
    }

    public void setFooterContact(String footerContact) {
        this.footerContact = footerContact;
    }

    @NotEmpty
    public String getFooterLawInfo() {
        return footerLawInfo;
    }

    public void setFooterLawInfo(String footerLawInfo) {
        this.footerLawInfo = footerLawInfo;
    }

	public Integer getRewardRate() {
		return rewardRate;
	}

	public void setRewardRate(Integer rewardRate) {
		this.rewardRate = rewardRate;
	}

	public Integer getExpenseRate() {
		return expenseRate;
	}

	public void setExpenseRate(Integer expenseRate) {
		this.expenseRate = expenseRate;
	}

	public Integer getMinimumAmount() {
		return minimumAmount;
	}

	public void setMinimumAmount(Integer minimumAmount) {
		this.minimumAmount = minimumAmount;
	}

	public String getPlatformRate() {
		return platformRate;
	}

	public void setPlatformRate(String platformRate) {
		this.platformRate = platformRate;
	}

	public String getShopRate() {
		return shopRate;
	}

	public void setShopRate(String shopRate) {
		this.shopRate = shopRate;
	}

	public String getSupplierRate() {
		return supplierRate;
	}

	public void setSupplierRate(String supplierRate) {
		this.supplierRate = supplierRate;
	}
    
}