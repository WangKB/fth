package com.puyuntech.flowerToHome.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * 用户 收货地址 Entity. 
 * Created on 2015-8-26 下午1:24:25 
 * @author 王凯斌
 * 
 */
@Entity
@Table(name = "t_receiver")
public class Receiver extends BaseEntity<Integer> {

	private static final long serialVersionUID = 3311654780217867697L;

	// 收货人
	private String consignee;

	// 地址
	private String address;
	
	// 省份
	private String addrProvince;
	
	// 城市
	private String addrCity;
	
	// 区县
	private String addrDistrict;

	// 电话
	private String phone;

	// 是否默认
	private Integer isDefault;
	
	//x坐标
	private Integer pointX;
	
	//y坐标
	private Integer pointY;
	
	//会员id
	private Integer memberId;
	
	//地区id
	private Integer areaId;

	/**
	 * 获取收货人
	 * 
	 * @return 收货人
	 */
	@Column(name="consignee",nullable = false)
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

	/**
	 * 获取电话
	 * 
	 * @return 电话
	 */
	@NotEmpty
	@Length(max = 200)
	@Pattern(regexp = "^\\d{3,4}-?\\d{7,9}$")
	@Column(name="phone",nullable = false)
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
	 * 获取是否默认
	 * 
	 * @return 是否默认
	 */
	@NotNull
	@Column(name="is_default",nullable = false)
	public Integer getIsDefault() {
		return isDefault;
	}

	/**
	 * 设置是否默认
	 * 
	 * @param isDefault
	 *            是否默认
	 */
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
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

	@Column(name="member_id")
	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	@Column(name="area_id")
	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	
}
