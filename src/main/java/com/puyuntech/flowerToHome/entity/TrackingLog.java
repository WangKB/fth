package com.puyuntech.flowerToHome.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeConverter;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Converter;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.Valid;

import com.puyuntech.flowerToHome.BaseAttributeConverter;
import com.puyuntech.flowerToHome.util.kuaidi.ResultItem;

/**
 * 
 * 物流信息. 
 * Created on 2015-12-22 下午5:53:04 
 * @author 严志森
 */
@Entity
@Table(name = "t_tracking_log")
public class TrackingLog extends BaseEntity<Integer> {

	private static final long serialVersionUID = 2274284069509035733L;


	/** 物流单号 */
	private String trackingId;

	/** 时间点 */
	private Date trackingTime;
	
	/** 物流内容 */
	private List<ResultItem> trackingInfo = new ArrayList<ResultItem>();
	
	/** 物流状态
	 0：等待发货，1：在途，2：已签收，3：退货途中   */
	private Integer trackingState;

	/** 操作人 */
	private String operator;
	
	
	/**
	 * 获取物流单号
	 * 
	 * @return 物流单号
	 */
	@Column(name="tracking_id")
	public String getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}
	

	/**
	 * 获取时间点
	 * 
	 * @return 时间点
	 */
	@Column(name="tracking_time")
	public Date getTrackingTime() {
		return trackingTime;
	}

	public void setTrackingTime(Date trackingTime) {
		this.trackingTime = trackingTime;
	}
	
	/**
	 * 获取物流内容
	 * 
	 * @return 物流内容
	 */
	@Valid
	@Column(name = "tracking_info", length = 4000)
	@Convert(converter = TrackingLogValueConverter.class)
	public List<ResultItem> getTrackingInfo() {
		return trackingInfo;
	}

	public void setTrackingInfo(List<ResultItem> trackingInfo) {
		this.trackingInfo = trackingInfo;
	}
	
	/**
	 * 获取物流状态
	 * 
	 * @return 物流状态
	 */
	@Column(name="tracking_state")
	public Integer getTrackingState() {
		return trackingState;
	}

	public void setTrackingState(Integer trackingState) {
		this.trackingState = trackingState;
	}
	
	/**
	 * 获取操作人
	 * 
	 * @return 操作人
	 */
	@Column(name="operator")
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * 类型转换 - 规格值
	 * 
	 */
	@Converter
	public static class TrackingLogValueConverter extends
			BaseAttributeConverter<List<ResultItem>> implements
			AttributeConverter<Object, String> {
	}
}
