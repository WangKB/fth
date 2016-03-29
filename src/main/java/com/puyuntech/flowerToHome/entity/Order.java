package com.puyuntech.flowerToHome.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * Entity - 订单 . 
 * Created on 2015-8-27 下午3:45:17 
 * @author 王凯斌
 * 	
 */
@Entity
@Table(name = "t_order")
public class Order extends BaseEntity<Integer> {

	private static final long serialVersionUID = 5227373608478684362L;

	/**
	 * 状态
	 */
	public enum Status {
		
		/** 等待付款 0*/
		pendingPayment(0),

		/** 等待接单 1*/
		pendingConfirm(1),
		
		/** 已接单 2*/
		Confirmed(2),
		
		/** 配送中 3*/
		Delivery(3),
		
		/** 已收货 4*/
		Reciverd(4),
		
		/** 退款中 5*/
		Returning(5),
		
		/** 已退货 6*/
		Returned(6),
		
		/** 已取消 7*/
		Canceled(7);
		
		private Integer id;
		
		private Status(Integer id){
			this.id = id;
		}

		public Integer getId() {
			return id;
		}

	}
	
	/**
	 * 门店状态
	 */
	public enum Confirm {
		
		//1：等待应答
		Waiting,
		
		//2：接收
		Receive,
		
		//3：拒绝
		Reject,
		
		//4：无门店应答
		NoResponse
	}
	
	/**
	 * 配送方式
	 */
	public enum DeliveryMethod {
		
		//配送
		Delivery,
		
		//自提
		Self
		
	}
	
	/**
	 * 来源类型
	 */
	public enum FromType {
		
		//平台
		Platform,
		
		//商户
		Shop,
		
		//供货商
		Supplier
		
	}
	
	/** 编号 */
	private String sn;

	/** 状态 */
	private Order.Status status;
	
	/** 配送方式 */
	private Order.Confirm deliveryMethod;
	
	/** 门店状态 */
	private Order.DeliveryMethod isConfirm;
	
	/** 来源门店 */
	private Order.FromType fromType;
	
	/** 门店id */
	private Integer shopId;
	
	/** 来源门店id */
	private Integer fromShop;
	
	/** 附近门店id */
	private Integer nearShopId;
	
	/** 是否指定门店 */
	private Integer designation;
	
	/** 是否虚拟订单 */
	private Integer isVirtual;
	
	/** 是否平台结算 */
	private Integer isBalance;
	
	/** 是否评论 */
	private Integer isComment;
	
	/** 平台结算时间 */
	private Date balanceDate;
	
	/** 配送图片 */
	private String deliveryImages;
	
	/** 回调支付流水号 */
	private String paymentOrder;
	
	/** 商品价格 */
	private BigDecimal price;

	/** 支付手续费 */
	private BigDecimal fee;

	/** 运费 */
	private BigDecimal freight;

	/** 税金 */
	private BigDecimal tax;

	/** 促销折扣 */
	private BigDecimal promotionDiscount;

	/** 优惠券折扣 */
	private BigDecimal couponDiscount;

	/** 调整金额 */
	private BigDecimal offsetAmount;

	/** 订单金额 */
	private BigDecimal amount;

	/** 已付金额 */
	private BigDecimal amountPaid;
	
	/** 退款金额 只指现金 */
	private BigDecimal refundAmount;

	/** 赠送积分 */
	private Integer rewardPoint;

	/** 商品数量 */
	private Integer quantity;

	/** 收货人 */
	private String consignee;

	/** 邮编 */
	private String zipCode;
	
	/** 电话 */
	private String mobile;
	
	/** 备注 */
	private String remark;
	
	/** 贺卡id */
	private Integer greetingcardsId;
	
	/** 贺卡内容 */
	private String greetingCardsMemp;
	
	/** 地区id */
	private Integer areaId;
	
	/** 会员id */
	private Integer memberId;
	
	/** 优惠码id */
	private Integer couponCodeId;
	
	/** 支付方式id */
	private Integer paymentMethodId;
	
	/** 出发时间 */
	private Date deliveryDate;
	
	/** 预计到达时间 */
	private Date preArriveDate;
	
	/** 到达时间 */
	private Date arriveDate;

	/** 过期时间 */
	private Date expire;
	
	/** 订购时间 */
	private Date preOrderDate;

	/** 是否已使用优惠码 */
	private Integer isUseCouponCode;

	/** 是否已兑换积分 */
	private Integer isExchangePoint;
	
	/** 是否线上订单 */
	private Integer isOnline;

	/** 支付方式名称 */
	private String paymentMethodName;

	/** 配送方式名称 */
	private String shippingMethodName;

	/** 锁定KEY */
	private String lockKey;

	/** 锁定过期时间 */
	private Date lockExpire;

	/** 完成日期 */
	private Date completeDate;
	
    /** 已退积分 **/
    private Integer returnExchagePoint;
    
    /** 使用积分 **/
    private Integer pointCount;
    
    /** 积分抵用金额 **/
    private Integer pointPaid;
	
    // 地址
 	private String address;
 	
 	// 省份
 	private String addrProvince;
 	
 	// 城市
 	private String addrCity;
 	
 	// 区县
 	private String addrDistrict;
 	
 	/** 抬头 */
	private String title;

	/** 内容 */
	private String content;
	
	/**
	 * 获取抬头
	 * 
	 * @return 抬头
	 */
	@Length(max = 200)
	@Column(name = "invoice_title")
	public String getTitle() {
		return title;
	}

	/**
	 * 设置抬头
	 * 
	 * @param title
	 *            抬头
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 获取内容
	 * 
	 * @return 内容
	 */
	@Length(max = 200)
	@Column(name = "invoice_content")
	public String getContent() {
		return content;
	}

	/**
	 * 设置内容
	 * 
	 * @param content
	 *            内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
    
	/**
	 * 获取编号
	 * 
	 * @return 编号
	 */
	@Column(name="sn",nullable = false, updatable = false, unique = true)
	public String getSn() {
		return sn;
	}

	/**
	 * 设置编号
	 * 
	 * @param sn
	 *            编号
	 */
	public void setSn(String sn) {
		this.sn = sn;
	}

	/**
	 * 获取状态
	 * 
	 * @return 状态
	 */
	@Column(name="status",nullable = false)
	public Order.Status getStatus() {
		return status;
	}

	/**
	 * 设置状态
	 * 
	 * @param status
	 *            状态
	 */
	public void setStatus(Order.Status status) {
		this.status = status;
	}

	/**
	 * 获取商品价格
	 * 
	 * @return 商品价格
	 */
	@Column(name="price",nullable = false, precision = 21, scale = 6)
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * 设置商品价格
	 * 
	 * @param price
	 *            商品价格
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

    /**
	 * 获取支付手续费
	 * 
	 * @return 支付手续费
	 */
	@Column(name="fee",nullable = false, precision = 21, scale = 6)
	public BigDecimal getFee() {
		return fee;
	}

	/**
	 * 设置支付手续费
	 * 
	 * @param fee
	 *            支付手续费
	 */
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	/**
	 * 获取运费
	 * 
	 * @return 运费
	 */
	@Min(0)
	@Digits(integer = 12, fraction = 3)
	@Column(name="freight",nullable = false, precision = 21, scale = 6)
	public BigDecimal getFreight() {
		return freight;
	}

	/**
	 * 设置运费
	 * 
	 * @param freight
	 *            运费
	 */
	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}

	/**
	 * 获取税金
	 * 
	 * @return 税金
	 */
	@Min(0)
	@Digits(integer = 12, fraction = 3)
	@Column(name="tax",nullable = false, precision = 21, scale = 6)
	public BigDecimal getTax() {
		return tax;
	}

	/**
	 * 设置税金
	 * 
	 * @param tax
	 *            税金
	 */
	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	/**
	 * 获取促销折扣
	 * 
	 * @return 促销折扣
	 */
	@Column(name="promotion_discount",nullable = false, updatable = false, precision = 21, scale = 6)
	public BigDecimal getPromotionDiscount() {
		return promotionDiscount;
	}

	/**
	 * 设置促销折扣
	 * 
	 * @param promotionDiscount
	 *            促销折扣
	 */
	public void setPromotionDiscount(BigDecimal promotionDiscount) {
		this.promotionDiscount = promotionDiscount;
	}

	/**
	 * 获取优惠券折扣
	 * 
	 * @return 优惠券折扣
	 */
	@Column(name="coupon_discount",nullable = false, updatable = false, precision = 21, scale = 6)
	public BigDecimal getCouponDiscount() {
		return couponDiscount;
	}

	/**
	 * 设置优惠券折扣
	 * 
	 * @param couponDiscount
	 *            优惠券折扣
	 */
	public void setCouponDiscount(BigDecimal couponDiscount) {
		this.couponDiscount = couponDiscount;
	}

	/**
	 * 获取调整金额
	 * 
	 * @return 调整金额
	 */
	@NotNull
	@Digits(integer = 12, fraction = 3)
	@Column(name="offset_amount",nullable = false, precision = 21, scale = 6)
	public BigDecimal getOffsetAmount() {
		return offsetAmount;
	}

	/**
	 * 设置调整金额
	 * 
	 * @param offsetAmount
	 *            调整金额
	 */
	public void setOffsetAmount(BigDecimal offsetAmount) {
		this.offsetAmount = offsetAmount;
	}

	/**
	 * 获取订单金额
	 * 
	 * @return 订单金额
	 */
	@Column(name="amount",nullable = false, precision = 21, scale = 6)
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * 设置订单金额
	 * 
	 * @param amount
	 *            订单金额
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * 获取已付金额
	 * 
	 * @return 已付金额
	 */
	@Column(name="amount_paid",nullable = false, precision = 21, scale = 6)
	public BigDecimal getAmountPaid() {
		return amountPaid;
	}

	/**
	 * 设置已付金额
	 * 
	 * @param amountPaid
	 *            已付金额
	 */
	public void setAmountPaid(BigDecimal amountPaid) {
		this.amountPaid = amountPaid;
	}
	

	/**
	 * 获取退款金额
	 * 
	 * @return 退款金额
	 */
	@Column(name="refund_amount",nullable = false, precision = 21, scale = 6)
	public BigDecimal getRefundAmount() {
		return refundAmount;
	}

	/**
	 * 设置退款金额
	 * 
	 * @param refundAmount
	 *            退款金额
	 */
	public void setRefundAmount(BigDecimal refundAmount) {
		this.refundAmount = refundAmount;
	}

	/**
	 * 获取赠送积分
	 * 
	 * @return 赠送积分
	 */
	@Min(0)
	@Column(name="reward_point",nullable = false)
	public Integer getRewardPoint() {
		return rewardPoint;
	}

	/**
	 * 设置赠送积分
	 * 
	 * @param rewardPoint
	 *            赠送积分
	 */
	public void setRewardPoint(Integer rewardPoint) {
		this.rewardPoint = rewardPoint;
	}


	/**
	 * 获取商品数量
	 * 
	 * @return 商品数量
	 */
	@Column(name="quantity",nullable = false, updatable = false)
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * 设置商品数量
	 * 
	 * @param quantity
	 *            商品数量
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	/**
	 * 获取收货人
	 * 
	 * @return 收货人
	 */
	@Length(max = 200)
	@Column(name="consignee")
	public String getConsignee() {
		return consignee;
	}

	/**
	 * 设置收货人
	 * 
	 * @param consignee
	 *            收货人
	 */
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}


	/**
	 * 获取邮编
	 * 
	 * @return 邮编
	 */
	@Length(max = 200)
	@Pattern(regexp = "^\\d{6}$")
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
	 * 获取过期时间
	 * 
	 * @return 过期时间
	 */
	@Column(name="expire")
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
	 * 获取预定时间
	 * 
	 * @return 预定时间
	 */
	@Column(name="create_date",insertable= false , updatable = false)
	public Date getPreOrderDate() {
		return preOrderDate;
	}
	
	/**
	 * 
	 * 设置预定时间.
	 * 
	 * @param preOrderDate 预定时间
	 */
	public void setPreOrderDate(Date preOrderDate) {
		this.preOrderDate = preOrderDate;
	}

	/**
	 * 获取是否已使用优惠码
	 * 
	 * @return 是否已使用优惠码
	 */
	@Column(name="is_use_coupon_code",nullable = false)
	public Integer getIsUseCouponCode() {
		return isUseCouponCode;
	}

	/**
	 * 设置是否已使用优惠码
	 * 
	 * @param isUseCouponCode
	 *            是否已使用优惠码
	 */
	public void setIsUseCouponCode(Integer isUseCouponCode) {
		this.isUseCouponCode = isUseCouponCode;
	}

	@Column(name="is_online")
	public Integer getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(Integer isOnline) {
		this.isOnline = isOnline;
	}

	/**
	 * 获取是否已兑换积分
	 * 
	 * @return 是否已兑换积分
	 */
	@Column(name="is_exchange_point",nullable = false)
	public Integer getIsExchangePoint() {
		return isExchangePoint;
	}

	/**
	 * 设置是否已兑换积分
	 * 
	 * @param isExchangePoint
	 *            是否已兑换积分
	 */
	public void setIsExchangePoint(Integer isExchangePoint) {
		this.isExchangePoint = isExchangePoint;
	}

	/**
	 * 获取支付方式名称
	 * 
	 * @return 支付方式名称
	 */
	@Column(name="payment_method_name")
	public String getPaymentMethodName() {
		return paymentMethodName;
	}

	/**
	 * 设置支付方式名称
	 * 
	 * @param paymentMethodName
	 *            支付方式名称
	 */
	public void setPaymentMethodName(String paymentMethodName) {
		this.paymentMethodName = paymentMethodName;
	}

	/**
	 * 获取配送方式名称
	 * 
	 * @return 配送方式名称
	 */
	@Column(name="shipping_method_name")
	public String getShippingMethodName() {
		return shippingMethodName;
	}

	/**
	 * 设置配送方式名称
	 * 
	 * @param shippingMethodName
	 *            配送方式名称
	 */
	public void setShippingMethodName(String shippingMethodName) {
		this.shippingMethodName = shippingMethodName;
	}

	/**
	 * 获取锁定KEY
	 * 
	 * @return 锁定KEY
	 */
	@Column(name="lock_key")
	public String getLockKey() {
		return lockKey;
	}

	/**
	 * 设置锁定KEY
	 * 
	 * @param lockKey
	 *            锁定KEY
	 */
	public void setLockKey(String lockKey) {
		this.lockKey = lockKey;
	}

	/**
	 * 获取锁定过期时间
	 * 
	 * @return 锁定过期时间
	 */
	@Column(name="lock_expire")
	public Date getLockExpire() {
		return lockExpire;
	}

	/**
	 * 设置锁定过期时间
	 * 
	 * @param lockExpire
	 *            锁定过期时间
	 */
	public void setLockExpire(Date lockExpire) {
		this.lockExpire = lockExpire;
	}

	/**
	 * 获取完成日期
	 * 
	 * @return 完成日期
	 */
	@Column(name="complete_date")
	public Date getCompleteDate() {
		return completeDate;
	}

	/**
	 * 设置完成日期
	 * 
	 * @param completeDate
	 *            完成日期
	 */
	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}


    @Column(name="returned_exchange_point")
    public Integer getReturnExchagePoint() {
        return returnExchagePoint;
    }

    public void setReturnExchagePoint(Integer returnExchagePoint) {
        this.returnExchagePoint = returnExchagePoint;
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
		
	}

	/**
	 * 获取地址
	 * 
	 * @return 地址
	 */
	@NotEmpty
	@Length(max = 200)
	@Column(name="addr_detail",nullable = false)
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
	
	@Column(name="addr_province")
	public String getAddrProvince() {
		return addrProvince;
	}

	public void setAddrProvince(String addrProvince) {
		this.addrProvince = addrProvince;
	}

	@Column(name="addr_city")
	public String getAddrCity() {
		return addrCity;
	}

	public void setAddrCity(String addrCity) {
		this.addrCity = addrCity;
	}

	@Column(name="addr_district")
	public String getAddrDistrict() {
		return addrDistrict;
	}

	public void setAddrDistrict(String addrDistrict) {
		this.addrDistrict = addrDistrict;
	}

	@Column(name="delivery_method")
	public Order.Confirm getDeliveryMethod() {
		return deliveryMethod;
	}

	public void setDeliveryMethod(Order.Confirm deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}

	@Column(name="is_confirm")
	public Order.DeliveryMethod getIsConfirm() {
		return isConfirm;
	}

	public void setIsConfirm(Order.DeliveryMethod isConfirm) {
		this.isConfirm = isConfirm;
	}

	@Column(name="from_type")
	public Order.FromType getFromType() {
		return fromType;
	}

	public void setFromType(Order.FromType fromType) {
		this.fromType = fromType;
	}

	@Column(name="shop_id")
	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	@Column(name="from_shop")
	public Integer getFromShop() {
		return fromShop;
	}

	public void setFromShop(Integer fromShop) {
		this.fromShop = fromShop;
	}

	@Column(name="near_shop_id")
	public Integer getNearShopId() {
		return nearShopId;
	}

	public void setNearShopId(Integer nearShopId) {
		this.nearShopId = nearShopId;
	}

	@Column(name="designation")
	public Integer getDesignation() {
		return designation;
	}

	public void setDesignation(Integer designation) {
		this.designation = designation;
	}

	@Column(name="is_virtual")
	public Integer getIsVirtual() {
		return isVirtual;
	}

	public void setIsVirtual(Integer isVirtual) {
		this.isVirtual = isVirtual;
	}

	@Column(name="is_balance")
	public Integer getIsBalance() {
		return isBalance;
	}

	public void setIsBalance(Integer isBalance) {
		this.isBalance = isBalance;
	}

	@Column(name="is_comment")
	public Integer getIsComment() {
		return isComment;
	}

	public void setIsComment(Integer isComment) {
		this.isComment = isComment;
	}

	@Column(name="balance_date")
	public Date getBalanceDate() {
		return balanceDate;
	}

	public void setBalanceDate(Date balanceDate) {
		this.balanceDate = balanceDate;
	}

	@Column(name="delivery_images")
	public String getDeliveryImages() {
		return deliveryImages;
	}

	public void setDeliveryImages(String deliveryImages) {
		this.deliveryImages = deliveryImages;
	}

	@Column(name="payment_order")
	public String getPaymentOrder() {
		return paymentOrder;
	}

	public void setPaymentOrder(String paymentOrder) {
		this.paymentOrder = paymentOrder;
	}

	@Column(name="mobile")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name="remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name="greetingcards_id")
	public Integer getGreetingcardsId() {
		return greetingcardsId;
	}

	public void setGreetingcardsId(Integer greetingcardsId) {
		this.greetingcardsId = greetingcardsId;
	}

	@Column(name="greetingcards_memp")
	public String getGreetingCardsMemp() {
		return greetingCardsMemp;
	}

	public void setGreetingCardsMemp(String greetingCardsMemp) {
		this.greetingCardsMemp = greetingCardsMemp;
	}

	@Column(name="area_id")
	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	@Column(name="member_id")
	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	@Column(name="coupon_code_id")
	public Integer getCouponCodeId() {
		return couponCodeId;
	}

	public void setCouponCodeId(Integer couponCodeId) {
		this.couponCodeId = couponCodeId;
	}

	@Column(name="payment_method_id")
	public Integer getPaymentMethodId() {
		return paymentMethodId;
	}

	public void setPaymentMethodId(Integer paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}

	@Column(name="delivery_date")
	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	@Column(name="pre_arrive_date")
	public Date getPreArriveDate() {
		return preArriveDate;
	}

	public void setPreArriveDate(Date preArriveDate) {
		this.preArriveDate = preArriveDate;
	}

	@Column(name="arrive_date")
	public Date getArriveDate() {
		return arriveDate;
	}

	public void setArriveDate(Date arriveDate) {
		this.arriveDate = arriveDate;
	}

	@Column(name="point_count")
	public Integer getPointCount() {
		return pointCount;
	}

	public void setPointCount(Integer pointCount) {
		this.pointCount = pointCount;
	}

	@Column(name="point_paid")
	public Integer getPointPaid() {
		return pointPaid;
	}

	public void setPointPaid(Integer pointPaid) {
		this.pointPaid = pointPaid;
	}
	
	
}
