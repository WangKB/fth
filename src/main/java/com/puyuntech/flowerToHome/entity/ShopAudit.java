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
@Table(name="t_shop_audit")
public class ShopAudit extends OrderEntity<Integer> {
	
	public enum state{
		
		//一级申请中
		ApplyingOne,
		
		//二级申请中
		ApplyingTwo,
		
		//通过
		Pass,
		
		//驳回
		Rejected
		
	}

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

        //申请中
        applying,

        //驳回
        rejected,

    }
    
    /** 名称*/
    private String name;
    
    private ShopAudit.Status status;
    
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
    
    /** 关店时间*/
    private Date closeDate;
    
    /** 下次轮循时间*/
    private Date nextRollDate;
    
    /** 每月结算时间*/
    private Integer payment_date;
    
    /** 上次结算时间*/
    private Date lastPaymentDate;
    
    /** 平台抽成比*/
    private String paymentPercent;
    
    /** 二维码地址*/
    private String qrcode;
    
    //申请状态
	private ShopAudit.state applicationState;
	
	//申请备注
	private String applicationMemo;
	
	//申请备注
	private String auditMemo1;
	
	//申请备注
	private String auditMemo2;

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

    @Column(name="longitude" , nullable=false)
    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Column(name="latitude",nullable=false)
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Column(name="address" , nullable=false)
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
	public ShopAudit.Status getStatus() {
		return status;
	}

	public void setStatus(ShopAudit.Status status) {
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
	public Integer getPayment_date() {
		return payment_date;
	}

	public void setPayment_date(Integer payment_date) {
		this.payment_date = payment_date;
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

	@Column(name="application_state")
	public ShopAudit.state getApplicationState() {
		return applicationState;
	}

	public void setApplicationState(ShopAudit.state applicationState) {
		this.applicationState = applicationState;
	}

	@Column(name="application_memo")
	public String getApplicationMemo() {
		return applicationMemo;
	}

	public void setApplicationMemo(String applicationMemo) {
		this.applicationMemo = applicationMemo;
	}

	@Column(name="audit_memo1")
	public String getAuditMemo1() {
		return auditMemo1;
	}

	public void setAuditMemo1(String auditMemo1) {
		this.auditMemo1 = auditMemo1;
	}

	@Column(name="audit_memo2")
	public String getAuditMemo2() {
		return auditMemo2;
	}

	public void setAuditMemo2(String auditMemo2) {
		this.auditMemo2 = auditMemo2;
	}
}

