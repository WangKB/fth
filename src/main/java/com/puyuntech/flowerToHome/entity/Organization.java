package com.puyuntech.flowerToHome.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * 组织机构信息 实体 . 
 * Created on 2015-8-25 下午5:46:18 
 * @author 王凯斌
 */
@Entity
@Table(name="t_shop")
public class Organization extends OrderEntity<Integer> {

    private static final long serialVersionUID = -5956709918329061215L;
    /**
     *
     * 门店状态.
     * Created on 2015-8-25 下午5:51:50
     * @author 王凯斌
     */
    public enum Status{

        //正常
        normal,

        //关闭
        close,

    }
    
    /**
    *
    * 门店级别.
    * Created on 2015-8-25 下午5:51:50
    * @author 王凯斌
    */
    public enum Level{

        //一级
        One,

        //二级
        Two,

        //三级
        Three,

    }

    /** 名称*/
    private String name;
    
    private Organization.Status status;
    
    private Organization.Level level;

    /** 经度*/
    private String longitude;

    /** 纬度*/
    private String latitude;

    /** 地址*/
    private String address;

    /** 简介*/
    private String intro;

    /** 电话*/
    private String tel;

    /** 开门时间*/
    private String openingStart;
    
    /** 关门时间*/
    private String openingEnd;

    /** 门店图片*/
    private String image;
    
    /** 门店邮箱*/
    private String email;

    /** 关联地区*/
    private Integer area;
    
    /** 省份*/
    private String addrProvince;
    
    /** 市*/
    private String addrCity;
    
    /** 区*/
    private String addrDistrict;
    
    /** 关店时间*/
    private Date closeDate;
    
    /** 下次轮循时间*/
    private Date nextRollDate;
    
    /** 每月结算时间*/
    private Integer paymentDate;
    
    /** 上次结算时间*/
    private Date lastPaymentDate;
    
    /** 平台抽成比*/
    private String paymentPercent;
    
    /** 二维码地址*/
    private String qrcode;
    
    //qq
    private String qq;
    
    //qqKey
    private String qqKey;
    
    //微信号
    private String wechat;
    
    //设计师id
    private String designerId;
    
    /** 开关店 开关*/
    private Integer isOpen;

    @Column(name="intro")
    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    @Column(name="name" , nullable=false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="point_X" , nullable=false)
    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Column(name="point_Y",nullable=false)
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Column(name="addr_detail" , nullable=false)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name="tel" , nullable=false)
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Column(name="image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Column(name="area" )
    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    @Column(name="status")
	public Organization.Status getStatus() {
		return status;
	}

	public void setStatus(Organization.Status status) {
		this.status = status;
	}

	@Column(name="level")
	public Organization.Level getLevel() {
		return level;
	}

	public void setLevel(Organization.Level level) {
		this.level = level;
	}

	@Column(name="opening_start")
	public String getOpeningStart() {
		return openingStart;
	}

	public void setOpeningStart(String openingStart) {
		this.openingStart = openingStart;
	}

	@Column(name="opening_end")
	public String getOpeningEnd() {
		return openingEnd;
	}

	public void setOpeningEnd(String openingEnd) {
		this.openingEnd = openingEnd;
	}

	@Column(name="email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="close_date")
	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	@Column(name="next_roll_date")
	public Date getNextRollDate() {
		return nextRollDate;
	}

	public void setNextRollDate(Date nextRollDate) {
		this.nextRollDate = nextRollDate;
	}

	@Column(name="payment_date")
	public Integer getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Integer paymentDate) {
		this.paymentDate = paymentDate;
	}

	@Column(name="last_payment_date")
	public Date getLastPaymentDate() {
		return lastPaymentDate;
	}

	public void setLastPaymentDate(Date lastPaymentDate) {
		this.lastPaymentDate = lastPaymentDate;
	}

	@Column(name="payment_percent")
	public String getPaymentPercent() {
		return paymentPercent;
	}

	public void setPaymentPercent(String paymentPercent) {
		this.paymentPercent = paymentPercent;
	}

	@Column(name="qrcode")
	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	@Column(name="QQ")
	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Column(name="QQ_key")
	public String getQqKey() {
		return qqKey;
	}

	public void setQqKey(String qqKey) {
		this.qqKey = qqKey;
	}

	@Column(name="wechat")
	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	@Column(name="designer_id")
	public String getDesignerId() {
		return designerId;
	}

	public void setDesignerId(String designerId) {
		this.designerId = designerId;
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

	@Column(name="is_open")
	public Integer getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}

    
}

